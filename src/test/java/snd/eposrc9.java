package snd;


	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.security.cert.X509Certificate;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.text.SimpleDateFormat;
	import java.time.Duration;
	import java.util.Date;
	import java.util.Random;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.testng.annotations.Test;

	import javax.net.ssl.*;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.security.cert.X509Certificate;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.Random;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import javax.net.ssl.HttpsURLConnection;
	import javax.net.ssl.SSLContext;
	import javax.net.ssl.TrustManager;
	import javax.net.ssl.X509TrustManager;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

 public class eposrc9 {

		private static final String Ordernumber = null;
		private WebDriver driver;
		private static String EAPIRefId;

		@BeforeClass
		public void setup() {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}

		@Test(priority = 1)
	    public void executeBothCurls() throws Exception {

	        disableSSL();

	        // ================= POS CURL =================
	        String posTxnId = executePosCurl();
	        System.out.println("POS Transaction Id = " + posTxnId);
	        RefIdStore.setPosRefId(posTxnId);

	        Thread.sleep(10_000);

	        // ================= EAPI CURL =================
	        String eapiRefId = executeEapiCurl();
	        System.out.println("EAPI RefId = " + eapiRefId);
	        RefIdStore.setEapiRefId(eapiRefId);
	    }

	    // =====================================================
	    private void disableSSL() throws Exception {

	        TrustManager[] trustAllCerts = new TrustManager[]{
	                new X509TrustManager() {
	                    public X509Certificate[] getAcceptedIssuers() { return null; }
	                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
	                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	                }
	        };

	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HttpsURLConnection.setDefaultHostnameVerifier((h, s) -> true);
	    }

	    // =====================================================
	    private String executePosCurl() throws Exception {

	        String urlString =
	                "https://10.0.5.49:8012/fcgi-bin/WMC_eTopupPosRequest" +
	                "?user=snd&pass=snd&from=2130" +
	                "&message=27%201111%202482654458%2010000%200%200%200%202311111111%200" +
	                "&to=369";

	        HttpURLConnection conn =
	                (HttpURLConnection) new URL(urlString).openConnection();
	        conn.setRequestMethod("GET");

	        StringBuilder response = new StringBuilder();
	        try (BufferedReader br =
	                     new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                response.append(line);
	            }
	            int httpCode = conn.getResponseCode();
	            System.out.println("EPOS HTTP Code = " + httpCode);
	            System.out.println(response);
	        }

	        Matcher matcher =
	                Pattern.compile("Transaction Id is (\\d+)").matcher(response.toString());

	        if (!matcher.find()) {
	            throw new RuntimeException("❌ POS Transaction Id not found!");
	        }

	        return matcher.group(1);
	    }

	    // =====================================================
	    private String executeEapiCurl() throws Exception {

	        String reqId = generateReqId();
	        String dateTime = getCurrentDateTime();

	        URL url = new URL("https://10.0.5.49:8098/fcgi-bin/reqMsg");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);

	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("USERID", "1");
	        conn.setRequestProperty("PASSWD", "MTExMQ==");
	        conn.setRequestProperty("REQID", reqId);
	        conn.setRequestProperty("MSISDN_A", "248288801");
	        conn.setRequestProperty("AMOUNT", "200");
	        conn.setRequestProperty("MSISDN_B", "2482511112");
	        conn.setRequestProperty("REQTYPE", "10");
	        conn.setRequestProperty("DATE_TIME", dateTime);
	        conn.setRequestProperty("TERMID", "10");

	        conn.getOutputStream().write(new byte[0]);

	        int httpCode = conn.getResponseCode();
	        System.out.println("EAPI HTTP Code = " + httpCode);

	        String respId = conn.getHeaderField("RESPID");

	        if (respId == null || respId.isEmpty()) {
	            throw new RuntimeException("❌ EAPI RESPID not received!");
	        }

	        return respId;
	    }

	    // =====================================================
	    private String generateReqId() {
	        return System.currentTimeMillis() + "" + (100 + new Random().nextInt(900));
	    }

	    private String getCurrentDateTime() {
	        return new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
	    }
	    
	    public static class RefIdStore {

	        private static String posRefId;
	        private static String eapiRefId;

	        public static void setPosRefId(String refId) {
	            posRefId = refId;
	        }

	        public static String getPosRefId() {
	            if (posRefId == null) {
	                throw new RuntimeException("❌ POS RefId not generated yet!");
	            }
	            return posRefId;
	        }

	        public static void setEapiRefId(String refId) {
	            eapiRefId = refId;
	        }

	        public static String getEapiRefId() {
	            if (eapiRefId == null) {
	                throw new RuntimeException("❌ EAPI RefId not generated yet!");
	            }
	            return eapiRefId;
	        }
	    }
	    
	    private String getOrderNumberFromDB(String transactionId) {

	        String orderNumber = null;

	        String dbUrl = "jdbc:mysql://10.0.5.49:3306/ETOPUP";
	        String dbUser = "etopup";
	        String dbPassword = "T55_Etopup";

	        String query = "SELECT order_number FROM etopup_transactions WHERE trans_id = ?";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	            java.sql.PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, transactionId);

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                orderNumber = rs.getString("order_number");
	            }

	            rs.close();
	            ps.close();
	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return orderNumber;
	    }
		// =====================================================
		@Test(priority = 2, enabled = true)
		public void validateEAPIRefIdInLedger() throws InterruptedException {

//	    	String EAPIRefId = RefIdStore.getEapiRefId();
			String refId = RefIdStore.getPosRefId();
			
			System.out.println("=== starting ledger flow ===");	
			driver.get("https://10.0.5.49:8443/snd/login");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='User ID']")))
					.sendKeys("admin");
			driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Tayana123");
			driver.findElement(By.cssSelector("button[type='submit']")).click();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			
			driver.findElement(By.linkText("Partner Directory")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys("2482511111");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//h5[normalize-space()='TopUp Dealer'])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[@id='tab-dealer-wallet'])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/a[1]/span[1]")).click();
			Thread.sleep(2000);
			String EAPIRefId = RefIdStore.getEapiRefId();
			System.out.println("EAPI TransactionId is  : " + EAPIRefId);

			String Ordernumber = getOrderNumberFromDB(EAPIRefId);

			System.out.println("EAPI Order Number from DB : " + Ordernumber);

			Assert.assertNotNull(Ordernumber, "Order Number not found in DB for EAPI RefId");

			driver.findElement(By.xpath("//input[@class='p-inputtext p-component']")).sendKeys(Ordernumber);

			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4)")));

			String message = alert.getText().trim();
			System.out.println("EAPI OrderNumber Ledger Validation : " + message);

			Assert.assertTrue(message.equalsIgnoreCase(Ordernumber),
					"❌ FAIL: Expected '" + Ordernumber + "' but got '" + message + "'");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@id=\"walletStatementBody\"]/div[2]")).click();
			Thread.sleep(1000);
			driver.findElement(
			By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/a[1]/span[1]")).click();
			Thread.sleep(2000);

			String refId1 = RefIdStore.getPosRefId();

			String Ordernumber2 = getOrderNumberFromDB(refId1);

			System.out.println("POS Order Number from DB : " + Ordernumber2);

			Assert.assertNotNull(Ordernumber2, "Order Number not found in DB for POS RefId");

			driver.findElement(By.xpath("//input[@class='p-inputtext p-component']")).sendKeys(Ordernumber2);
			Thread.sleep(2000);

			WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement alert11 = wait11.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4)")));

			String message11 = alert11.getText().trim();
			System.out.println("EPOS OrderNumber Ledger Validation : " + message11);

			Assert.assertTrue(message11.equalsIgnoreCase(Ordernumber2),
					"❌ FAIL: Expected '" + Ordernumber2 + "' but got '" + message11 + "'");

		}

		// =====================================================
		
		@Test(priority = 3, enabled = true)
		public void Statementwith_ordernumber() throws InterruptedException, AWTException {
			System.out.println("=== starting statements flow ===");	
			String EAPIRefId = RefIdStore.getEapiRefId();
			System.out.println("EAPI TransactionId is  : " + EAPIRefId);

			String OrdernumberEAPI = getOrderNumberFromDB(EAPIRefId);
			System.out.println("EAPI Order Number from DB : " + OrdernumberEAPI);
			driver.navigate().refresh();
			
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
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
			driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys("57");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			System.out.println("clicked ");
			// accounts
			driver.findElement(By.xpath(
					"//div[contains(@class,'SelectContainer')]//div//div[contains(@class,'selectHover selectForm')]//i[contains(@class,'')]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[5]")).click();
			// statement date
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			driver.findElement(By.cssSelector("li[title='Current Month']")).click();
			// search button click
			driver.findElement(By.cssSelector(".fa-magnifying-glass")).click();
			Thread.sleep(2000);
			//search orderno
			WebElement orderno = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/div[1]/input[1]")));
			orderno.clear();
			orderno.sendKeys(OrdernumberEAPI);
			System.out.println("validated");
			Robot robot3 = new Robot();
			robot3.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot3.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(3000);
			//xpath of orderNumber
			String orderNo1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[7]")).getText();
			System.out.println("OrderNumber from xpath: " + orderNo1);

			// Assertion
			
			 Assert.assertTrue( orderNo1.trim().equalsIgnoreCase(OrdernumberEAPI.trim()),
			 "Order Number mismatch between UI and file" );
			 


			// Database validation using kiosk ordernum
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://10.0.5.49:3306/snd", "root",
					"Root@t4y4n4")) {

				Statement statement = connection.createStatement();
				Thread.sleep(2000);

				// Correct SQL query
				String sqlQuery = "SELECT BDR_ID, DESCRIPTION " + "FROM snd.bdr_transactions " + "WHERE ORDER_NUMBER = '"
						+ OrdernumberEAPI + "' " + "AND ACCOUNT_ID = 107";

				ResultSet resultSet = statement.executeQuery(sqlQuery);

				// Fetch data
				if (resultSet.next()) {
					long bdrId = resultSet.getLong("BDR_ID");
					String description = resultSet.getString("DESCRIPTION");

					System.out.println("BDR_ID: " + bdrId);
					System.out.println("DESCRIPTION: " + description);
				} else {
					System.out.println("No data found for ORDER_NUMBER = '" + OrdernumberEAPI + "'");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			Thread.sleep(180000);
		}
		@Test(priority = 4, enabled = true , dependsOnMethods="validateEAPIRefIdInLedger")
		public void notifcationwith_transId() throws InterruptedException, AWTException {
			String EAPIRefId = RefIdStore.getEapiRefId();
			System.out.println("=== starting notification flow ===");
			driver.navigate().refresh();
			driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
			driver.findElement(By.linkText("Dealer Tracker")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(
			"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/span[1]/div[1]/a[1]"))
					.click();
			driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
			driver.findElement(By.xpath("//div[3]//div[1]//div[1]//div[3]//span[1]//*[name()='svg']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Notification']")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
			
			WebElement searchBox = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'p-inputtext')]")));
			searchBox.sendKeys(EAPIRefId);
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
		
		/*@AfterClass
		public void tearDown() {
			if (driver != null) {
				driver.quit();
			}*/
		}



