package snd;



	import org.json.JSONObject;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import org.openqa.selenium.*;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.*;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Duration;

	public class prepaidtopup {

		public static WebDriver driver;
		public WebDriverWait wait;

		@BeforeClass
		public void setupDriver() {

			driver = new ChromeDriver(); // ✔ pass options to driver
			driver.manage().window().maximize();

		}

		private String refId;
		private String orderNumber;

		private static final String ORDER_FILE = "C:\\Users\\Moulya\\eclipse-workspace\\QA\\src\\test\\java\\SND_GUI\\datause\\order.txt";
		private static final String REF_FILE = "C:\\Users\\Moulya\\eclipse-workspace\\QA\\src\\test\\java\\SND_GUI\\datause\\ref.txt";

		@Test(priority = 1, enabled=true)
		public void generateRefIdAndOrderNo() throws Exception {

			// Disable SSL
			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((h, s) -> true);
			javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[] {
					new javax.net.ssl.X509TrustManager() {
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						public void checkClientTrusted(java.security.cert.X509Certificate[] c, String a) {
						}

						public void checkServerTrusted(java.security.cert.X509Certificate[] c, String a) {
						}
					} };
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Generate random RefId
			String randomRefId = String.valueOf((long) (Math.random() * 100000000000L));
			URL url = new URL("https://10.0.5.49:7066/etopup/v2/topup?refId=" + randomRefId);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("userName", "snd");
			conn.setRequestProperty("password", "snd");
			conn.setDoOutput(true);

			String jsonBody = "{" + "\"serviceId\":1000," + "\"serviceNo\":\"2482579354\"," + "\"buyerNo\":\"2509997\","
					+ "\"price\":400," + "\"channel\":16," + "\"paymentMode\":1," + "\"pin\":1234,"
					+ "\"extension\":{\"saleDateTime\":\"2025-10-31 11:45:00\",\"quantity\":1}" + "}";

			try (OutputStream os = conn.getOutputStream()) {
				os.write(jsonBody.getBytes());
			}

			StringBuilder response = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
			}

			JSONObject jsonObj = new JSONObject(response.toString());
			JSONObject resp = jsonObj.getJSONObject("response");

			refId = resp.getString("refId");
			orderNumber = resp.getString("orderNumber");

			Assert.assertNotNull(refId, "RefId is null");
			Assert.assertNotNull(orderNumber, "OrderNumber is null");

			System.out.println("API Returned RefId = " + refId);
			System.out.println("API Returned OrderNumber = " + orderNumber);

			// Save to files
			try (FileWriter writer = new FileWriter(REF_FILE)) {
				writer.write(refId);
			}
			try (FileWriter writer = new FileWriter(ORDER_FILE)) {
				writer.write(orderNumber);
			}

			System.out.println("RefId saved to: " + REF_FILE);
			System.out.println("OrderNumber saved to: " + ORDER_FILE);
		}

		@Test(priority = 2, dependsOnMethods = "generateRefIdAndOrderNo")
		public void readRefIdFromFile() throws IOException {
			try (BufferedReader br = new BufferedReader(new FileReader(REF_FILE))) {
				refId = br.readLine().trim();
			}
			Assert.assertNotNull(refId, "RefId file is empty");
			System.out.println("RefId read from file: " + refId);
		}

		@Test(priority = 3, dependsOnMethods = "generateRefIdAndOrderNo")
		public void readOrderNumberFromFile() throws IOException {
			try (BufferedReader br = new BufferedReader(new FileReader(ORDER_FILE))) {
				orderNumber = br.readLine().trim();
			}
			Assert.assertNotNull(orderNumber, "Order file is empty");
			System.out.println("OrderNumber read from file: " + orderNumber);
		}

		@Test(priority = 2, enabled = true)
		public void Statementwith_ordernumber() throws InterruptedException, AWTException {

			driver.get("https://10.0.5.49:8443/snd/login");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='User ID']")))
					.sendKeys("admin");
			driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Tayana123");
			driver.findElement(By.cssSelector("button[type='submit']")).click();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(2000);
			Robot robot2 = new Robot();
			robot2.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot2.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(2000);
			driver.findElement(By.linkText("Statements")).click(); // statements
			Thread.sleep(2000);
			// dealer
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys("10002");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			System.out.println("clicked ");
			// accounts
			driver.findElement(By.xpath(
					"//div[contains(@class,'SelectContainer')]//div//div[contains(@class,'selectHover selectForm')]//i[contains(@class,'')]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[5]"))
					.click();
			// statement date
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"))
					.click();
			driver.findElement(By.cssSelector("li[title='Current Month']")).click();
			// search button click
			driver.findElement(By.cssSelector(".fa-magnifying-glass")).click();
			Thread.sleep(2000);
			
			//search orderno
			WebElement ordeno = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/div[1]/input[1]")));
			ordeno.sendKeys(orderNumber);
			System.out.println("validated");
			Robot robot3 = new Robot();
			robot3.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot3.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(3000);
			//xpath of orderNumber
			String orderNo1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[7]")).getText();
			System.out.println("OrderNumber from xpath: " + orderNo1);

			// Assertion
			Assert.assertTrue(
			        orderNo1.trim().equalsIgnoreCase(orderNumber.trim()),
			        "Order Number mismatch between UI and file"
			);


			// Database validation using kiosk ordernum
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://10.0.5.49:3306/snd", "root",
					"Root@t4y4n4")) {

				Statement statement = connection.createStatement();
				Thread.sleep(2000);

				// Correct SQL query
				String sqlQuery = "SELECT BDR_ID, DESCRIPTION " + "FROM snd.bdr_transactions " + "WHERE ORDER_NUMBER = '"
						+ orderNumber + "' " + "AND ACCOUNT_ID = 107";

				ResultSet resultSet = statement.executeQuery(sqlQuery);

				// Fetch data
				if (resultSet.next()) {
					long bdrId = resultSet.getLong("BDR_ID");
					String description = resultSet.getString("DESCRIPTION");

					System.out.println("BDR_ID: " + bdrId);
					System.out.println("DESCRIPTION: " + description);
				} else {
					System.out.println("No data found for ORDER_NUMBER = '" + orderNumber + "'");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		@Test(priority = 3, enabled = true)
		public void ladgerwith_ordernumber() throws InterruptedException, AWTException {
	        driver.navigate().refresh();
			driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[26]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys("2482579354");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//h5[normalize-space()='PankajVerification'])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[@id='tab-dealer-wallet'])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[2]//div[1]//div[2]//div[1]//div[2]//a[1]//span[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@class='p-inputtext p-component']")).sendKeys(orderNumber);
			Thread.sleep(2000);
			WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement alert11 = wait11.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

			String message11 = alert11.getText().trim();
			System.out.println("Topup OrderNumber Ledger Validation : " + message11);

			Assert.assertTrue(message11.equalsIgnoreCase(orderNumber),
					"❌ FAIL: Expected '" + orderNumber + "' but got '" + message11 + "'");

		}
		@Test(priority = 4, enabled = true)
		public void trackerwith_ordernumber() throws InterruptedException, AWTException {
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
	    driver.findElement(By.linkText("Dealer Tracker")).click();
	    driver.findElement(By.xpath("//div[@class='card-body']//div[2]//div[1]//div[1]//div[3]//span[1]")).click();
	    driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
	    driver.findElement(By.xpath("//div[3]//div[1]//div[1]//div[3]//span[1]//*[name()='svg']")).click();
	    driver.findElement(By.xpath("//button[normalize-space()='Etopup']")).click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//input[contains(@class,'p-inputtext')]")
	    ));
	    searchBox.sendKeys(refId);

	    WebElement txnLink = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//table//a[text()='" + refId + "']"))
	    );
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", txnLink);

	    Thread.sleep(1000);

	    WebElement remarksElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//label[contains(text(),'Remarks')]/parent::div/following-sibling::div/p")
	    ));

	    String remarksText = remarksElement.isDisplayed() ? remarksElement.getText().trim() : "";
	    String expectedOutput = "Order Completed successfully";
	    Assert.assertTrue(remarksText.contains(expectedOutput), "Remarks validation failed");

	    System.out.println("Remarks Alert Text: " + remarksText);

	    WebElement closeModalBtn = driver.findElement(By.xpath("//div[@class='modal-dialog modal-xl']//button[@aria-label='Close']"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", closeModalBtn);

	    WebElement homeLink = driver.findElement(By.linkText("Home"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", homeLink);
	    Thread.sleep(1000);
	}
		
	}

