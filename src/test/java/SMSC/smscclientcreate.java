package SMSC;
	import java.time.Duration;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class smscclientcreate {
		public static WebDriver driver;

		@BeforeClass
		// logic to login one time in the browser and execute all test cases within the
		// browser
		public void setUp() {
			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
			SMSC.smscclientcreate.driver = driver; // Set the WebDriver instance

			driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			// Set implicit wait to 5 seconds
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
			driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
			driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
		}

		@Test(priority = 1)
		public void client_creation_HTTP() throws InterruptedException {
			// Click on the "Client" link
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Client')]")).click();
			Thread.sleep(3000);

			// click on HTTP button
			driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();

			// Switch to the iframe
			WebElement iframe = driver
					.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
			driver.switchTo().frame(iframe);

			// Click on the "Add" link
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[normalize-space()='Add']")));
			addLink.click();
			// Fill client information
			fillClientInformation();

			// Switch back to the main window
			String parentWindow = driver.getWindowHandle();
			switchToCalendarPopup();

			// Pick date from calendar
			pickDateFromCalendar();

			// Switch back to the parent window
			driver.switchTo().window(parentWindow);
			System.out.println("Switched back to the parent window");

			// Switch to the iframe
			WebElement iframe1 = driver
					.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
			driver.switchTo().frame(iframe1);

			// Wait for the "AddBasicButton" element to be clickable
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addBasicButton = wait1
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='AddBasicButton']")));

			// Click on the "AddBasicButton"
			addBasicButton.click();
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();

			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String actualOutput = errorMessageElement.getText();
			System.out.println(actualOutput);
			String expectedOutput = "Addition Successful";
			Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(5000);
			
			driver.switchTo().defaultContent();
				
			
		}

		private void fillClientInformation() throws InterruptedException {

			WebElement userNameInput = driver.findElement(By.xpath("//input[@name='userName']"));
			userNameInput.sendKeys("smsc");
			Thread.sleep(1000);
			WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
			passwordInput.sendKeys("smsc21");
			Thread.sleep(1000);
			WebElement minThroughputInput = driver.findElement(By.xpath("//input[@name='throughput']"));
			minThroughputInput.sendKeys("1");
			Thread.sleep(1000);
			WebElement maxThroughputInput = driver.findElement(By.xpath("//input[@name='maxthroughput']"));
			maxThroughputInput.sendKeys("100");
			Thread.sleep(1000);
			WebElement emailInput = driver.findElement(By.xpath("//input[@title='Enter valid email address']"));
			emailInput.sendKeys("support@tayana.in");
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"borderDivAdd\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[6]/td[2]/input"))
					.sendKeys("1234");
			Thread.sleep(2000);

			// logic to select node select
			WebElement dropdown1 = driver.findElement(By.xpath("//*[@id=\"NodeSelect\"]"));
			Thread.sleep(2000);
			Select select1 = new Select(dropdown1);
			select1.selectByIndex(1);// to select node2
			Thread.sleep(2000);

			// logic to select FDA fails
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='failureAction']")));
			addLink.click();
			Thread.sleep(2000);
			Select select12 = new Select(addLink);
			select12.selectByIndex(1);// to select retry
			Thread.sleep(2000);

			// logic to select user configuration
			WebElement dropdown2 = driver.findElement(By.xpath("//select[@title='Select User Configuration']"));
			Thread.sleep(2000);
			Select select2 = new Select(dropdown2);
			select2.selectByIndex(2);// to admin(admin)
			Thread.sleep(1000);

			WebElement billingFlagCheckbox = driver.findElement(By.xpath("//input[@name='BillingFlag']"));
			billingFlagCheckbox.click();
			Thread.sleep(1000);
			WebElement distListSupportCheckbox = driver.findElement(By.xpath("//input[@name='distListSupport']"));
			distListSupportCheckbox.click();
			Thread.sleep(1000);
			WebElement cannedMsgSupport = driver.findElement(By.xpath("//input[@name='cannedMsgSupport']"));
			cannedMsgSupport.click();
			Thread.sleep(1000);
			// WebElement smtpSupport =
			// driver.findElement(By.xpath("//input[@name='smtpSupport']"));
			// smtpSupport.click();
			// input[@name='ssEmail']
			// driver.findElement(By.xpath("//input[@name='ssEmail']")).sendKeys("support@tayana.in");
			// driver.findElement(By.xpath("//input[@name='rrEmail']")).sendKeys("work.jyotik@gmail.com");
			// Thread.sleep(1000);
			WebElement carryFwdSupport = driver.findElement(By.xpath("//input[@name='carryFwdSupport']"));
			carryFwdSupport.click();
			Thread.sleep(1000);
		}

		private void switchToCalendarPopup() {
			driver.findElement(By.xpath(
					"//*[@id=\"borderDivAdd\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[1]/table/tbody/tr[7]/td[2]/a"))
					.click();// calendar locator

			Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().equals("Calendar")) {
					System.out.println("Switched to the calendar popup window");
					break;
				}
			}
		}

		private void pickDateFromCalendar() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr/td/table")));
			addLink.click();

			String amonth;
			do {
				driver.findElement(By.xpath("//img[@alt='next month']")).click();
				Thread.sleep(1000);
				amonth = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td[3]/font"))
						.getText();
			} while (!amonth.equals("July 2024"));

			driver.findElement(By.xpath("//font[normalize-space()='14']")).click();
			Thread.sleep(3000);

		}

		
		@Test(priority = 2)
		public void client_activation() throws InterruptedException {
			driver.switchTo().defaultContent();
			// logic to active the status in http list of newly created client
			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Client')]")).click();
			Thread.sleep(2000);
			// click on HTTP button
			driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();
			Thread.sleep(3000);

			// Switch to the iframe
			WebElement iframe = driver
					.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
			driver.switchTo().frame(iframe);

			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='pageDiv']/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[14]/td[1]/a")));
			element.click();

			// logic to select active
			WebElement dropdown2 = driver.findElement(By.xpath("//select[@name='statusMod7']"));
			Thread.sleep(2000);
			Select select2 = new Select(dropdown2);
			select2.selectByIndex(1);// to admin(admin)
			Thread.sleep(1000);

			// Create a JavascriptExecutor object
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			// Scroll down by pixel offset (e.g., 500 pixels)
			jsExecutor.executeScript("window.scrollBy(0,500)");

			//logic to select quota
			Thread.sleep(3000);
			driver.findElement(By
					.xpath("//*[@id=\"borderDiv7\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[7]/td/table/tbody/tr[1]/td[1]")).click();
			WebElement dropdown21 =	driver.findElement(By.xpath("//input[@name='quotaMod7']"));
			dropdown21.clear();
			dropdown21.sendKeys("500");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@name='ModifyQuotaModifyButton7']")).click();
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
			Thread.sleep(2000);
			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String actualOutput = errorMessageElement.getText();
			System.out.println(actualOutput + "for quota");
			String expectedOutput = "Modification Successful";
			Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@id='hrefAdvQuota7']")).click();//close the quota
			Thread.sleep(2000);
			
			//logic to select retry management
			driver.findElement(By.xpath("//a[@id='hrefAdvRM7']")).click();
			driver.findElement(By.xpath("//input[@name='ModifyRMModifyButton7']")).click();
			Alert alert1 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert1.accept();
			Thread.sleep(2000);
//			WebElement errorMessageElement1 = driver.findElement(By.xpath("//td[@class='succmsg']"));
//			String actualOutput1 = errorMessageElement.getText();
//			System.out.println(actualOutput1 + "for retry management");
//			String expectedOutput1 = "Modification Successful";
//			Assert.assertTrue(actualOutput1.contains(expectedOutput1), "Error message mismatch for invalid service id");
//			Thread.sleep(3000);
			
			// Create a JavascriptExecutor object
			JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
			// Scroll down by pixel offset (e.g., 500 pixels)
			jsExecutor1.executeScript("window.scrollBy(400,700)");
			
			driver.findElement(By.xpath("//img[@name='plusminusAdvRM7']")).click();//close the retry management	
			Thread.sleep(2000);
			
			// Create a JavascriptExecutor object
					JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
					// Scroll down by pixel offset (e.g., 500 pixels)
					jsExecutor3.executeScript("window.scrollBy(0,500)");
			
			driver.findElement(By.xpath("//input[@name='ModifyBasicButton7']")).click();
			Alert alert12 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert12.accept();
			Thread.sleep(5000);
		}
		
		
		@Test(priority = 4)
		public void retrylink() throws InterruptedException {
			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Retry')]")).click(); // retry link in system config
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Retry Pattern']")).click(); // retry link in system config
			Thread.sleep(2000);
			
			// Switch to the iframe
					WebElement iframe = driver
							.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
					driver.switchTo().frame(iframe);
					
			driver.findElement(By.xpath("//*[@id=\"addLink\"]")).click(); // retry link in system config
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/input")).sendKeys("smsc"); // retry link in system config
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[4]/input")).sendKeys("2"); // retry link in system config
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/input[1]")).click();
			Alert alert12 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert12.accept();
			//Thread.sleep(5000);
		}
		
		
		
		@Test(priority = 5)
		public void client_deletion() throws InterruptedException {
			driver.switchTo().defaultContent();
			// logic to active the status in http list of newly created client
			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Client')]")).click();
			Thread.sleep(2000);
			// click on HTTP button
			driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();
			Thread.sleep(3000);

			// Switch to the iframe
			WebElement iframe = driver
					.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
			driver.switchTo().frame(iframe);

			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='pageDiv']/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[14]/td[1]/a")));
			element.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='DeleteBasicButton7']")).click();
			Alert alert12 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert12.accept();
		}
		
	}


