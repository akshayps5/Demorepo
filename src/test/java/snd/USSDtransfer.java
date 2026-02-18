package snd;




	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.security.cert.X509Certificate;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Duration;

	import javax.net.ssl.SSLContext;
	import javax.net.ssl.TrustManager;
	import javax.net.ssl.X509TrustManager;

	import org.apache.http.client.methods.CloseableHttpResponse;
	import org.apache.http.client.methods.HttpGet;
	import org.apache.http.conn.ssl.NoopHostnameVerifier;
	import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
	import org.apache.http.impl.client.CloseableHttpClient;
	import org.apache.http.impl.client.HttpClients;
	import org.apache.http.util.EntityUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class USSDtransfer extends dataprovider {
		
		private static String txnId;
		private static String orderNumber;
		
		public static WebDriver driver;
		public WebDriverWait wait;

		@BeforeClass
		public void setupDriver() {

			driver = new ChromeDriver(); // ✔ pass options to driver
			driver.manage().window().maximize();
			setup();

		}

	public void setup() 
	{
			
		driver.get(GUI_URL);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@id='details-button']")).click();
		driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='User ID']")))
				.sendKeys(GUI_USER);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(GUI_PASSWORD);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
	}


	// ----------------------------
	// Send prepaid API
	// ----------------------------
	public String UssdtransferpreAPI(String subscriber, String dealer, String pin,String pin2, String amount) throws Exception {

		// SSL trust all
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} }, new java.security.SecureRandom());

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

		try (CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build()) {

			String url = ussdAPIurl + "snd&snd&" + dealer + "&" + pin + ussdtransferpretype + pin2 + "%20" + subscriber + "%20" + amount;
			System.out.println("Sending API: " + url);

			HttpGet get = new HttpGet(url);

			// Step 1: Call API & extract txnId
			try (CloseableHttpResponse response = client.execute(get)) {
				String resp = EntityUtils.toString(response.getEntity());
				System.out.println("API Response: " + resp);

				txnId = extractTxnId(resp);
				System.out.println("this is transaction id : "+txnId);

				if (txnId == null) {
					System.out.println("ERROR: Transaction ID not found.");
					return null; // stop execution – no point querying DB now
				} else {
					System.out.println("Extracted Transaction ID: " + txnId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			// Step 2: Query DB using txnId
			String dburl = SndDB_url;
			String user = DB_USER;
			String pass = DB_PASSWORD;

			String query = "SELECT order_number FROM orders WHERE REF_ID = ?";

			try (Connection connection = DriverManager.getConnection(dburl, user, pass);
					PreparedStatement ps = connection.prepareStatement(query)) {

				ps.setString(1, txnId);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					orderNumber = rs.getString("order_number");
					System.out.println("Fetched Order Number from DB: " + orderNumber);
				} else {
					System.out.println("No matching order number found for txnId: " + txnId);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return orderNumber;

		}
	}

	// ----------------------------
	// Extract Transaction ID Helper
	// ----------------------------
	private static String extractTxnId(String resp) {

		String txnId = null;

		if (resp.contains("Transaction ID is")) {
			txnId = resp.split("Transaction ID is")[1].trim();
		} else if (resp.contains("Transaction ID:")) {
			txnId = resp.split("Transaction ID:")[1].trim();
		} else {
			for (String p : resp.split("\\s+")) {
				if (p.matches("\\d{8,20}")) {
					txnId = p;
					break;
				}
			}
		}

		if (txnId != null) {
			txnId = txnId.replaceAll("[^0-9]", "");
			if (txnId.isEmpty())
				return null;
		}

		return txnId;
	}

	// ----------------------------
	// main flow
	// ----------------------------
	@Test(priority = 1, enabled = true)
	public void apiprepaidTest() throws Exception {

		System.out.println("=== Starting USSD prepaid transfer API Flow ===");

		String dealer = transferdealer;
		String pin = transferpin;
		String subscriber = transfersubscriber;
		String pin2 = transferpin2;
		String amount = transferamount;

		String txnId = UssdtransferpreAPI(subscriber, dealer, pin,pin2 ,amount);

		if (txnId == null) {
			System.out.println("transfer failed or Transaction ID missing.");
			return;
		}

		System.out.println("=== API Flow Completed ===");
	}


	@Test(priority = 2, enabled = true, dependsOnMethods = "apiprepaidTest")
	public void Ussdprepaidtransfer_Statement() throws InterruptedException, AWTException {
		    System.out.println("=== starting statement flow ===");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(2000);
			Robot robot2 = new Robot();
			robot2.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot2.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(2000);
			driver.findElement(By.linkText("Statements")).click(); // statements
			Thread.sleep(2000);
			// dealer
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys(ussdtransferdealerstate);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			// accounts
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@id='rightSectionDiv']//ul[1]//li[4]")).click();
			driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='content-body']/div/div[@class='tss-tab-body']/div/div/div[@class='card']/div[@class='card-body']/div[1]/div[2]/div[1]/div[1]/fieldset[1]")).click();
			// statement date
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"))
					.click();
			driver.findElement(By.cssSelector("li[title='Current Month']")).click();
			// search button click
			driver.findElement(By.cssSelector(".fa-magnifying-glass")).click();
			Thread.sleep(2000);

			// search orderno
			WebElement ordeno = driver.findElement(By.xpath(
			"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/div[1]/input[1]"));
			ordeno.sendKeys(orderNumber);
			//System.out.println("validated");
			Robot robot3 = new Robot();
			robot3.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot3.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(3000);
			// xpath of orderNumber
			String orderNo1 = driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[8]"))
					.getText();
			System.out.println("OrderNumber from xpath: " + orderNo1);

			// Assertion
			Assert.assertTrue(orderNo1.trim().equalsIgnoreCase(orderNumber.trim()),
					"Order Number mismatch between UI and file");

			// Database validation using kiosk ordernum
			try (Connection connection = DriverManager.getConnection(SndDB_url,DB_USER,
					DB_PASSWORD)) {

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
	@Test(priority = 3, enabled = true ,dependsOnMethods = "apiprepaidTest")
	public void ussd_prepaidtransfer_ledger() throws InterruptedException, AWTException {
		System.out.println("=== starting ledger flow ===");
	    driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Partner Directory")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys(ledgerdealertrans);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]")).click();
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
		System.out.println("prepaid OrderNumber Ledger Validation : " + message11);

		Assert.assertTrue(message11.equalsIgnoreCase(orderNumber),
				"❌ FAIL: Expected '" + orderNumber + "' but got '" + message11 + "'");

		
	}

	@Test(priority = 4, enabled = true)
	public void ussd_prepaidtransfer_tracker() throws InterruptedException, AWTException {
		System.out.println("=== staring the tracker flow ===");
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		driver.findElement(By.linkText("Dealer Tracker")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='card']//div[1]//div[1]//div[1]//div[3]//span[1]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
		driver.findElement(By.xpath("//div[@class='row']//div[2]//div[1]//div[1]//div[3]//span[1]")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Transfer and Settelement']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//input[contains(@class,'p-inputtext')]")));
	    searchBox.sendKeys(orderNumber);
		
	    WebElement modalBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='#'][data-toggle='modal']")));
	    modalBody.click();
	    Thread.sleep(2000);
		String Remarks = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[13]/div[2]/p[1]")).getText();
		Thread.sleep(2000);
	    System.out.println("Remarks from GUI " + Remarks);
		Thread.sleep(180000);

	}

	@Test(priority = 5, enabled =  false, dependsOnMethods="apiprepaidTest")
	public void ussd_prepaidtransfer_notifcation() throws InterruptedException, AWTException {
		System.out.println("=== starting notification flow ===");
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		driver.findElement(By.linkText("Dealer Tracker")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span[id='basic-addon1'] div[class='dropdown']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
		driver.findElement(By.xpath("//div[@class='row']//div[2]//div[1]//div[1]//div[3]//span[1]")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Notification']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		
		WebElement searchBox = wait.until(
		ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'p-inputtext')]")));
		searchBox.sendKeys(txnId);
		Robot robot6 = new Robot();
		robot6.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot6.keyRelease(KeyEvent.VK_PAGE_DOWN);
	    WebElement xpath = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]"));
	    xpath.getText();
	    System.out.println(xpath);
	    String remarksText = xpath.isDisplayed() ? xpath.getText().trim() : "";
	    String expectedOutput = "Notification Completed successfully for Order";
	    Assert.assertTrue(remarksText.contains(expectedOutput), "Remarks validation failed");

	    System.out.println("Remarks Alert Text: " + remarksText);
	    Thread.sleep(1000);
		
	} 


	//////////------------------------ussd postpaid transfer----------------------------------------------------------------------

	//----------------------------
	//Send postpaid transfer API
	//----------------------------
	public String UssdpostpaidtransferpostAPI(String subscriber1, String dealer1, String pin1,String pin2, String amount1 ) throws Exception {

		// SSL trust all
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} }, new java.security.SecureRandom());

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

		try (CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build()) {

			//USSD postpaid API
			String url = ussdAPIurl + "snd&snd&" + dealer1 + "&" + pin1 + ussdtransposttype + pin2 + "%20" + subscriber1 + "%20" + amount1;
			


			System.out.println("Sending API: " + url);

			HttpGet get = new HttpGet(url);

			// Step 1: Call API & extract txnId
			try (CloseableHttpResponse response = client.execute(get)) {
				String resp = EntityUtils.toString(response.getEntity());
				System.out.println("API Response: " + resp);

				txnId = extractTxnId1(resp);
				System.out.println("this is transaction id : "+txnId);

				if (txnId == null) {
					System.out.println("ERROR: Transaction ID not found.");
					return null; // stop execution – no point querying DB now
				} else {
					System.out.println("Extracted Transaction ID: " + txnId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			// Step 2: Query DB using txnId
			String dburl = SndDB_url;
			String user = DB_USER;
			String pass = DB_PASSWORD;

			String query = "SELECT order_number FROM orders WHERE REF_ID = ?";

			try (Connection connection = DriverManager.getConnection(dburl, user, pass);
					PreparedStatement ps = connection.prepareStatement(query)) {

				ps.setString(1, txnId);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					orderNumber = rs.getString("order_number");
					System.out.println("Fetched Order Number from DB: " + orderNumber);
				} else {
					System.out.println("No matching order number found for txnId: " + txnId);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return orderNumber;

		}
	}

	//----------------------------
	//Extract Transaction ID Helper
	//----------------------------
	private static String extractTxnId1(String resp) {

		String txnId = null;

		if (resp.contains("Transaction ID is")) {
			txnId = resp.split("Transaction ID is")[1].trim();
		} else if (resp.contains("Transaction ID:")) {
			txnId = resp.split("Transaction ID:")[1].trim();
		} else {
			for (String p : resp.split("\\s+")) {
				if (p.matches("\\d{8,20}")) {
					txnId = p;
					break;
				}
			}
		}

		if (txnId != null) {
			txnId = txnId.replaceAll("[^0-9]", "");
			if (txnId.isEmpty())
				return null;
		}

		return txnId;
	}

	//----------------------------
	//main flow
	//----------------------------
	@Test(priority = 6, enabled = true)
	public void apipostpaidTest1() throws Exception {

		System.out.println("=== Starting USSD postpaid transfer API Flow ===");

		String dealer1 = transferdealer;
		String pin1 = transferpin;
		String subscriber1 = transfersubscriber;
		String pin2 =transferpin2;
		String amount1 = transferamount;

		String txnId = UssdpostpaidtransferpostAPI(subscriber1, dealer1, pin1,pin2 ,amount1);

		if (txnId == null) {
			System.out.println("transfer failed or Transaction ID missing.");
			return;
		}

		System.out.println("=== API Flow Completed ===");
	}


	@Test(priority = 7, enabled = true, dependsOnMethods = "apipostpaidTest1")
	public void Ussdtransferpost_Statement() throws InterruptedException, AWTException {
		    System.out.println("=== starting statement flow ===");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(2000);
			Robot robot2 = new Robot();
			robot2.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot2.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(2000);
			driver.findElement(By.linkText("Statements")).click(); // statements
			Thread.sleep(2000);
			// dealer
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys(ussdtransferdealerstate);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			// accounts
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@id='rightSectionDiv']//ul[1]//li[4]")).click();
			driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='content-body']/div/div[@class='tss-tab-body']/div/div/div[@class='card']/div[@class='card-body']/div[1]/div[2]/div[1]/div[1]/fieldset[1]")).click();
			// statement date
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"))
					.click();
			driver.findElement(By.cssSelector("li[title='Current Month']")).click();
			// search button click
			driver.findElement(By.cssSelector(".fa-magnifying-glass")).click();
			Thread.sleep(2000);

			// search orderno
			WebElement ordeno = driver.findElement(By.xpath(
			"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/div[1]/input[1]"));
			ordeno.sendKeys(orderNumber);
			//System.out.println("validated");
			Robot robot3 = new Robot();
			robot3.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot3.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(3000);
			// xpath of orderNumber
			String orderNo1 = driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[8]"))
					.getText();
			System.out.println("OrderNumber from xpath: " + orderNo1);

			// Assertion
			Assert.assertTrue(orderNo1.trim().equalsIgnoreCase(orderNumber.trim()),
					"Order Number mismatch between UI and file");

			// Database validation using kiosk ordernum
			try (Connection connection = DriverManager.getConnection(SndDB_url, DB_USER,
					DB_PASSWORD)) {

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
	@Test(priority = 8, enabled = true ,dependsOnMethods = "apipostpaidTest1")
	public void ussd_transfer_ledger() throws InterruptedException, AWTException {
		System.out.println("=== starting ledger flow ===");
	 driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Partner Directory")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys(ledgerdealertrans);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]")).click();
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
		System.out.println("postpaid OrderNumber Ledger Validation : " + message11);

		Assert.assertTrue(message11.equalsIgnoreCase(orderNumber),
				"❌ FAIL: Expected '" + orderNumber + "' but got '" + message11 + "'");

		
	}

	@Test(priority = 9, enabled = true)
	public void ussd_transfertracker() throws InterruptedException, AWTException {
		System.out.println("=== staring the tracker flow ===");
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		driver.findElement(By.linkText("Dealer Tracker")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='card']//div[1]//div[1]//div[1]//div[3]//span[1]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
		driver.findElement(By.xpath("//div[@class='row']//div[2]//div[1]//div[1]//div[3]//span[1]")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Transfer and Settelement']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//input[contains(@class,'p-inputtext')]")));
	 searchBox.sendKeys(orderNumber);
		
	 WebElement modalBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='#'][data-toggle='modal']")));
	 modalBody.click();
	 Thread.sleep(2000);
		String Remarks = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[13]/div[2]/p[1]")).getText();
		Thread.sleep(2000);
	 System.out.println("Remarks from GUI " + Remarks);
		Thread.sleep(180000);

	}

	@Test(priority = 10, enabled =  true, dependsOnMethods="apipostpaidTest1")
	public void ussd_transfer_notifcation() throws InterruptedException, AWTException {
		System.out.println("=== starting notification flow ===");
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		driver.findElement(By.linkText("Dealer Tracker")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span[id='basic-addon1'] div[class='dropdown']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
		driver.findElement(By.xpath("//div[@class='row']//div[2]//div[1]//div[1]//div[3]//span[1]")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Notification']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		
		WebElement searchBox = wait.until(
		ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'p-inputtext')]")));
		searchBox.sendKeys(txnId);
		Robot robot6 = new Robot();
		robot6.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot6.keyRelease(KeyEvent.VK_PAGE_DOWN);
	 WebElement xpath = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]"));
	 xpath.getText();
	 System.out.println(xpath);
	 String remarksText = xpath.isDisplayed() ? xpath.getText().trim() : "";
	 String expectedOutput = "Notification Completed successfully for Order";
	 Assert.assertTrue(remarksText.contains(expectedOutput), "Remarks validation failed");

	 System.out.println("Remarks Alert Text: " + remarksText);
	 Thread.sleep(1000);
		
	} 



	}


