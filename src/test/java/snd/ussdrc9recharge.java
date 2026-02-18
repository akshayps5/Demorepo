package snd;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.net.ssl.*;

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

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

public class ussdrc9recharge {

	private static String txnId;
	private static String orderNumber;
	
	public static WebDriver driver;
	public WebDriverWait wait;

	@BeforeClass
	public void setupDriver() {

		driver = new ChromeDriver(); // ✔ pass options to driver
		driver.manage().window().maximize();

	}


	// ----------------------------
	// Send Recharge API
	// ----------------------------
public String sendRechargeAPI(String subscriber, String dealer, String pin, String amount) throws Exception {

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
			// String url = "https://10.0.5.49:8012/fcgi-bin/WMC_eTopUpRequest?"snd&snd" +
			// 2482511111&122&RECH%201111%202482586370%20312'

			String url = "https://10.0.5.49:8012/fcgi-bin/WMC_eTopUpRequest?" + "snd&snd&" + dealer + "&" + pin
					+ "&RECH%20" + pin + "%20" + subscriber + "%20" + amount;

			System.out.println("Sending API: " + url);

			HttpGet get = new HttpGet(url);

			// Step 1: Call API & extract txnId
			try (CloseableHttpResponse response = client.execute(get)) {
				String resp = EntityUtils.toString(response.getEntity());
				System.out.println("API Response: " + resp);

				txnId = extractTxnId(resp);

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
			String dburl = "jdbc:mysql://10.0.5.49:3306/etopup";
			String user = "root";
			String pass = "Root@t4y4n4";

			String query = "SELECT order_number FROM etopup_transactions WHERE trans_id = ?";

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

		if (resp.contains("Transaction Id is")) {
			txnId = resp.split("Transaction Id is")[1].trim();
		} else if (resp.contains("Transaction Id:")) {
			txnId = resp.split("Transaction Id:")[1].trim();
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
public void apiRechargeTest() throws Exception {

		System.out.println("=== Starting USSD Recharge API Flow ===");

		String dealer = "2482511111";
		String pin = "1111";
		String subscriber = "2482686370";
		String amount = "312";

		String txnId = sendRechargeAPI(subscriber, dealer, pin, amount);

		if (txnId == null) {
			System.out.println("Recharge failed or Transaction ID missing.");
			return;
		}

		System.out.println("=== API Flow Completed ===");
	}

@Test(priority = 2, enabled = true, dependsOnMethods = "apiRechargeTest")
public void Statementwith_ordernumber() throws InterruptedException, AWTException {
		System.out.println("=== starting statements flow ===");	
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
		driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys("57");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
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

		// search orderno
		WebElement ordeno = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/div[1]/input[1]")));
		ordeno.sendKeys(orderNumber);
		//System.out.println("validated");
		Robot robot3 = new Robot();
		robot3.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot3.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(3000);
		// xpath of orderNumber
		String orderNo1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[7]"))
				.getText();
		System.out.println("OrderNumber from xpath: " + orderNo1);

		// Assertion
		Assert.assertTrue(orderNo1.trim().equalsIgnoreCase(orderNumber.trim()),
				"Order Number mismatch between UI and file");

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
@Test(priority = 3, enabled = true ,dependsOnMethods = "apiRechargeTest")
public void ledgerwith_ordernumber() throws InterruptedException, AWTException {
	System.out.println("=== starting ledger flow ===");
    driver.navigate().refresh();
	driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[26]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys("2482511111");
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
	System.out.println("Topup OrderNumber Ledger Validation : " + message11);

	Assert.assertTrue(message11.equalsIgnoreCase(orderNumber),
			"❌ FAIL: Expected '" + orderNumber + "' but got '" + message11 + "'");

	Thread.sleep(180000);
}

@Test(priority = 4, enabled = true , dependsOnMethods="apiRechargeTest")
public void notifcationwith_transId() throws InterruptedException, AWTException {
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

