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

	public class eposeapirc9 {
		
	       
		 private WebDriver driver;
		    private WebDriverWait wait;

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

	    
	    
	    
	    
	    
	    @Test(priority = 2)
	    public void validatePOSEAPIRefIdInDealerTracker() throws AWTException, InterruptedException {
	    	 driver = new ChromeDriver();
	         driver.manage().window().maximize();
	         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	         wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	         
	        String refId = RefIdStore.getPosRefId();
	        String EAPIRefId = RefIdStore.getEapiRefId();
//	        String POSstatus = "Success";
	        String expectedStatus = "Success";

	        driver.get("https://10.0.5.49:8443/snd/");

	        wait.until(ExpectedConditions.elementToBeClickable(By.id("details-button"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("proceed-link"))).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@placeholder='User ID']"))).sendKeys(TestData.USERNAME);

	        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(TestData.PASSWORD);
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[contains(., 'Dealer Tracker')]")));

	        Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	        
	        Thread.sleep(10000);

	        driver.findElement(By.xpath("//a[contains(., 'Dealer Tracker')]")).click();

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	                "//div[@class='card-body']//div[2]//div[1]//div[1]//div[3]//span[1]"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[normalize-space()='This Month']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	                "//div[3]//div[1]//div[1]//div[3]//span[1]//*[name()='svg']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[normalize-space()='Etopup']"))).click();

	        WebElement searchBox = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.p-inputtext")));
	        searchBox.sendKeys(refId);
	        
	        Thread.sleep(2000);

	        WebElement txnCell = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td:nth-child(2)")));
	        WebElement statusCell = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td:nth-child(6)")));

	        Assert.assertEquals(txnCell.getText().trim(), refId);
	        Assert.assertEquals(statusCell.getText().trim(), expectedStatus);
	        
	        Thread.sleep(2000);
	        
	        searchBox.clear();
	        
	        
	        WebElement searchBox2 = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.p-inputtext")));
	        searchBox2.sendKeys(EAPIRefId);
	        
	        Thread.sleep(2000);

	        WebElement POSCell = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td:nth-child(2)")));
//	        WebElement StatusCell = wait.until(
//	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td:nth-child(6)")));

	        Assert.assertEquals(POSCell.getText().trim(), EAPIRefId);
	        Assert.assertEquals(statusCell.getText().trim(), expectedStatus);
	        Thread.sleep(2000);
	        
	        
	        
	        
	        
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
	    @Test(priority = 3)
	    public void validateEAPIRefIdInLedger() throws InterruptedException {
	    	
//	    	String EAPIRefId = RefIdStore.getEapiRefId();
	    	String refId = RefIdStore.getPosRefId();
	    	
	    	driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
	    	Thread.sleep(2000);
	        driver.findElement(By.linkText("Partner Directory")).click();
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys("2482511111");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("(//h5[normalize-space()='TopUp Dealer'])[1]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("(//button[@id='tab-dealer-wallet'])[1]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > a:nth-child(5) > span:nth-child(1)")).click();
	        Thread.sleep(2000);
	        String EAPIRefId = RefIdStore.getEapiRefId();
	        System.out.println("EAPI TransactionId is  : " + EAPIRefId);

	        String Ordernumber = getOrderNumberFromDB(EAPIRefId);

	        System.out.println("EAPI Order Number from DB : " + Ordernumber);

	        Assert.assertNotNull(Ordernumber, "Order Number not found in DB for EAPI RefId");
	        
	        
	        driver.findElement(By.xpath("//input[@class='p-inputtext p-component']")).sendKeys(Ordernumber);
	        Thread.sleep(2000);
//	        WebElement txnCell = driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4)"));
//	        Assert.assertEquals(txnCell.getText().trim(), Ordernumber);
	        
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4)")));

	        String message = alert.getText().trim();
	        System.out.println("EAPI OrderNumber found Ledger table Validation : " + message);

	        Assert.assertTrue(message.equalsIgnoreCase(Ordernumber),
	                "❌ FAIL: Expected '" + Ordernumber + "' but got '" + message + "'");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//*[@id=\"walletStatementBody\"]/div[2]")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > a:nth-child(5) > span:nth-child(1)")).click();
	        Thread.sleep(2000);
	        
	        
	        
	        String refId1 = RefIdStore.getPosRefId();

	        String Ordernumber2 = getOrderNumberFromDB(refId1);

	        System.out.println("POS Order Number from DB : " + Ordernumber2);

	        Assert.assertNotNull(Ordernumber2, "Order Number not found in DB for POS RefId");
	        
	        driver.findElement(By.xpath("//input[@class='p-inputtext p-component']")).sendKeys(Ordernumber2);
	        Thread.sleep(2000);
//	        WebElement txnCell = driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4)"));
//	        Assert.assertEquals(txnCell.getText().trim(), Ordernumber);
	        
	        
	        WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement alert11 = wait11.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4)")));

	        String message11 = alert11.getText().trim();
	        System.out.println("EPOS OrderNumber Ledger Validation : " + message11);

	        Assert.assertTrue(message11.equalsIgnoreCase(Ordernumber2),
	                "❌ FAIL: Expected '" + Ordernumber2 + "' but got '" + message11 + "'");

	 
	    	
	    }

	    
	    
	    // =====================================================
	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    
	    
	}


