package snd;


	

	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.FileWriter;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Duration;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	public class kisokrc9 {

		String orderNumber;  
		
		public static WebDriver driver;
		public WebDriverWait wait;

		@BeforeClass
		public void setupDriver() {

			driver = new ChromeDriver(); // ✔ pass options to driver
			driver.manage().window().maximize();

		}

		@BeforeMethod
		public void waiting() {
			wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		}

		@Test
		public void Login() {

			driver.get("https://10.0.5.49:8443/snd/login");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='User ID']")))
					.sendKeys("admin");
			driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Tayana123");
			driver.findElement(By.cssSelector("button[type='submit']")).click();
		}

		// kiosk backend flow
		public static String whtsotp() {
			HttpURLConnection http = null;
			BufferedReader br = null;
			try {
				URL url = new URL("http://10.0.6.132:8000/cgi-bin/kiosk1.cgi");
				http = (HttpURLConnection) url.openConnection();
				http.setRequestMethod("GET");

				int status = http.getResponseCode();

				br = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
				StringBuilder response = new StringBuilder();
				String line;

				while ((line = br.readLine()) != null) {
					response.append(line);
				}
				String responseStr = response.toString();

				Pattern pattern = Pattern.compile("\\d{15,25}");
				Matcher matcher = pattern.matcher(responseStr);
		        String responseStrtrimed = responseStr.replaceAll("\u001B\\[[;\\d]*m", "").replaceAll("<html><body><h3>Latest OTP:", "").replaceAll("</h3></body></html>", "").trim();
				System.out.println(responseStrtrimed);
				

				if (matcher.find()) {
					return matcher.group(); // ✅ RETURN OTP ONLY
				} else {
					return "OTP_NOT_FOUND";
				}

			} catch (Exception e) {
				e.printStackTrace();
				return "ERROR";
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (Exception ignored) {
				}
				if (http != null)
					http.disconnect();
			}
		}

		// kiosk statements
		@Test(priority = 1, enabled = true)
		public void Statementwith_prepaidtopup() throws InterruptedException, AWTException {
			
			//String orderNumber="";
			orderNumber = "";
			String fetchorderNumber = whtsotp();
			
			////////////////////////////
			//kiosk db ordernum fetch
			try (Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://10.0.5.49:3306/etopup",
	                "root",
	                "Root@t4y4n4")) {

	            Statement statement = connection.createStatement();

	            String sqlQuery =
	                "SELECT order_number " +
	                "FROM etopup.etopup_transactions " +
	                "WHERE trans_id = '" + fetchorderNumber + "'";

	            ResultSet resultSet = statement.executeQuery(sqlQuery);

	            if (resultSet.next()) {
	                 orderNumber = resultSet.getString("order_number");
	                System.out.println("order_number from Database: " + orderNumber);
	            } else {
	                System.out.println("No data found for trans_id = " + fetchorderNumber);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			////////////////////////
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Robot robot2 = new Robot();
			robot2.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot2.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(2000);
			driver.findElement(By.linkText("Statements")).click(); // statements
			Thread.sleep(2000);
			driver.navigate().refresh();
			//dealer 
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			Thread.sleep(2000);
		    driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys("kiosk");
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("(//li[@id=\'TSSGUI_MultiSelectOptionsStyle\'])[2]")).click();
			System.out.println("clicked ");
			// accounts
			driver.findElement(By.xpath("//div[contains(@class,'SelectContainer')]//div//div[contains(@class,'selectHover selectForm')]//i[contains(@class,'')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[4]")).click();
			// statement date
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"))
					.click();
			driver.findElement(By.cssSelector("li[title='Current Month']")).click();
			// search button click
			driver.findElement(By.cssSelector(".fa-magnifying-glass")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
			//search orderNo
			Thread.sleep(4000);
			WebElement ordeno= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/div[1]/input[1]")));
			ordeno.sendKeys(orderNumber);
			//System.out.println("her am i ");
			Robot robot3 = new Robot();
			robot3.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot3.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(2000);
			
			//xpath of orderNumber
			String orderNo1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[7]")).getText();
			System.out.println("kiosk OrderNumber statement validation : " + orderNo1);
			
			// Assertion
			Assert.assertTrue(
					orderNo1.trim().equalsIgnoreCase(orderNumber.trim()),
			        "Order Number mismatch between UI and file"
			);

		
		//Database validation using kiosk ordernum
			
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://10.0.5.49:3306/snd", "root", "Root@t4y4n4")) {

			    Statement statement = connection.createStatement();
			    Thread.sleep(2000);

			    // Correct SQL query
			    String sqlQuery ="SELECT BDR_ID, DESCRIPTION " + "FROM snd.bdr_transactions " + "WHERE ORDER_NUMBER = '" + orderNumber + "' " +
			     "AND ACCOUNT_ID = 107";

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
		
		@Test(priority = 5, enabled = false,dependsOnMethods = "Statementwith_prepaidtopup")
		public void ladgerwith_ordernumber() throws InterruptedException, AWTException {
			
		//orderNumber = resultSet.getString("orderNumber");
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[26]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys("24825627000");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@id='tab-dealer-wallet'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[2]//div[1]//div[2]//a[1]//span[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='p-inputtext p-component']")).sendKeys(orderNumber);
		System.out.println("ordernumber searched");
		Thread.sleep(2000);
		WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement alert11 = wait11.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

		String message11 = alert11.getText().trim();
		System.out.println("kiosk OrderNumber Ledger Validation : " + message11);

		Assert.assertTrue(message11.equalsIgnoreCase(orderNumber),
				"❌ FAIL: Expected '" + orderNumber + "' but got '" + message11 + "'");
		driver.navigate().refresh();
	}

	@Test(priority = 6, enabled = false,dependsOnMethods = "Statementwith_prepaidtopup")
	public void trackerwith_ordernumber() throws InterruptedException, AWTException {
		String fetchorderNumber = whtsotp();
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		driver.findElement(By.linkText("Dealer Tracker")).click();
		driver.findElement(By.xpath("//div[@class='card-body']//div[2]//div[1]//div[1]//div[3]//span[1]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
		driver.findElement(By.xpath("//div[3]//div[1]//div[1]//div[3]//span[1]//*[name()='svg']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Etopup']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		WebElement searchBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'p-inputtext')]")));
		searchBox.sendKeys(orderNumber);
		Robot robot2 = new Robot();
		robot2.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot2.keyRelease(KeyEvent.VK_PAGE_DOWN);

		WebElement txnLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//a[text()='\" + fetchorderNumber + \"'] ")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", txnLink);

		Thread.sleep(1000);

		WebElement remarksElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//label[contains(text(),'Remarks')]/parent::div/following-sibling::div/p")));

		String remarksText = remarksElement.isDisplayed() ? remarksElement.getText().trim() : "";
		String expectedOutput = "Order Completed successfully";
		Assert.assertTrue(remarksText.contains(expectedOutput), "Remarks validation failed");

		System.out.println("Remarks Alert Text: " + remarksText);

		WebElement closeModalBtn = driver.findElement(By.xpath("//div[@class='modal-dialog modal-xl']//button[@aria-label='Close']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();",
				closeModalBtn);

		WebElement homeLink = driver.findElement(By.linkText("Home"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", homeLink);
		Thread.sleep(180000);
	}

	@Test(priority = 7, enabled = false , dependsOnMethods="Statementwith_prepaidtopup")
	public void notifcationwith_transId() throws InterruptedException, AWTException {
	    
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
		searchBox.sendKeys(orderNumber);
		Robot robot6 = new Robot();
		robot6.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot6.keyRelease(KeyEvent.VK_PAGE_DOWN);
	    WebElement xpath = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]"));
	    xpath.getText();
	    System.out.println(xpath);
	    String remarksText = xpath.isDisplayed() ? xpath.getText().trim() : "";
	    String expectedOutput = "Notification Completed successfully Order";
	    Assert.assertTrue(remarksText.contains(expectedOutput), "Remarks validation failed");

	    System.out.println("Remarks Alert Text: " + remarksText);
	    Thread.sleep(1000);
		
	}    
		
	
}
