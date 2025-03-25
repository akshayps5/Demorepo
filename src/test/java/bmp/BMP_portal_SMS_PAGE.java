package bmp;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BMP_portal_SMS_PAGE {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		BMP_portal_SMS_PAGE.driver = driver; // Set the WebDriver instance

		// driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
        driver.manage().window().maximize();
		String url = "https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp";
		driver.get(url);
		String pageTitle = driver.getTitle();
		System.out.println("Title of " + url + ": " + pageTitle);

		// Set implicit wait to 5 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
		driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
		driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
	}

	@Test(priority = 1)
	public void SMS_sending_textformat_mobilenumber() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();// sitemap locator
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui locator
		Thread.sleep(2000);

		// switching to BMPportal window or popup
		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//td[@class='nav_td_border_select']")).click();// sms oage
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good mesage");// message
																										// description
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("hi hello");// text message
				driver.findElement(By.xpath("//input[@name='mobNo']")).sendKeys("12345678");// mobile number

				// dispatch time
				WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
				Thread.sleep(2000);
				Select select3 = new Select(dropdown3);
				select3.selectByIndex(1);// to select schedule
				Thread.sleep(3000);

				// logic to select date from calendar validity period
				driver.findElement(By.xpath("//*[@id=\"trVal\"]/td[2]/img")).click();// calender opening

				// wait statement to display the calendar structure properly
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addLink2 = wait2
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]")));
				addLink2.click();
				// locator for getting the month and year text from the calendar
				String amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth2);

				while (!(amonth2.equals("January 2024"))) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();// next button
																										// locator
					amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();// locator
					System.out.println(amonth2);

				}
				driver.findElement(By.xpath("//a[normalize-space()='6']")).click();// locator for date inside the
																					// calendar
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				// logic to select date from calendar date in dispatch time
				driver.findElement(By.xpath("//tr[@id='DateTr']//img[@title='Click to Choose Date']")).click();// calendar
																												// opening
				// wait statement to display the calendar structure properly
				WebDriverWait wait22 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addLink22 = wait22
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
				addLink22.click();
				// locator for getting the month and year text from the calendar
				String amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth22);

				while (!(amonth22.equals("January 2024"))) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();// next button
																										// locator
					amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();// locator
					System.out.println(amonth22);

				}
				driver.findElement(By.xpath("//a[normalize-space()='6']")).click();// locator for date inside the
																					// calendar
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();// send button
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value=' Yes ']")).click();// confirmation locator( OK button)

				WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String s1 = errorMessageElement.getText();
				System.out.println(s1);
				int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");
				// Trim the string to keep only the part until the specified index
				String trimmedString = s1.substring(0, endIndex);
				System.out.println(trimmedString);
				String expectedOutput = "Msg sent to : [97512345678 ]";
				Assert.assertTrue(s1.contains(expectedOutput),
						"Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);
			}
		}
	}

	@Test(priority = 2)
	public void SMS_sending_textformat_fileupload1() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();// sitemap locator
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui locator
		Thread.sleep(2000);

		// switching to BMPportal window or popup
		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//td[@class='nav_td_border_select']")).click();// sms oage
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good mesage");// message
																										// description
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("hi hello");// text message
				// driver.findElement(By.xpath("//input[@name='mobNo']")).sendKeys("12345678");//
				// mobile number

				// dispatch time
				WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
				Thread.sleep(2000);
				Select select3 = new Select(dropdown3);
				select3.selectByIndex(1);// to select schedule
				Thread.sleep(3000);

				// logic to select date from calendar date in dispatch time
				driver.findElement(By.xpath("//tr[@id='DateTr']//img[@title='Click to Choose Date']")).click();// calendar
																												// opening
				// wait statement to display the calendar structure properly
				WebDriverWait wait22 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addLink22 = wait22
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
				addLink22.click();
				// locator for getting the month and year text from the calendar
				String amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth22);

				while (!(amonth22.equals("January 2024"))) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();// next button
																										// locator
					amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();// locator
					System.out.println(amonth22);

				}
				driver.findElement(By.xpath("//a[normalize-space()='5']")).click();// locator for date inside the
																					// calendar
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				driver.findElement(By.xpath(
						"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[8]/td[1]/input"))
						.click(); // file upload radio button
				Thread.sleep(2000);

				String projectPath = System.getProperty("user.dir");
				String filePath = projectPath + "\\files\\demo.txt";

				// Check if the file exists
				File file = new File(filePath);
				if (file.exists()) {
					WebElement fileInput = driver.findElement(By.xpath("//input[@name='fname']"));
					fileInput.sendKeys(filePath);
					// Wait for the file to be uploaded (you may need to adjust the wait time)
					Thread.sleep(2000);

					// Continue with your code
				} else {
					System.out.println("File not found: " + filePath);
				}

				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();// send button
				Thread.sleep(2000);
				// Check if confirmation message contains the specified text
				WebElement confirmationMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
				String confirmationMessage = confirmationMessageElement.getText();

				if (confirmationMessage.contains("Limit Exceeded , Max.Number of files are under processing")) {
					// If the confirmation message contains the specified text
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
					String errorMessage = errorMessageElement.getText();
					System.out.println(errorMessage);

					String expectedOutput = "Limit Exceeded , Max.Number of files are under processing";
					//System.out.println(expectedOutput);
				} else {
					// If the confirmation message does not contain the specified text
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();// confirmation locator( OK button)
					WebElement successMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String successMessage = successMessageElement.getText();
					System.out.println(successMessage);

					int endIndex = successMessage.indexOf("Reference Number for uploaded file");
					String trimmedString = successMessage.substring(0, endIndex);
					System.out.println(trimmedString);

					String expectedOutput = "Reference Number for uploaded file";
					Assert.assertTrue(successMessage.contains(expectedOutput),
							"Error message mismatch for actualOutput from expectedOutput");
					Thread.sleep(3000);
				}
			}
		}

	}

	@Test(priority = 3)
	public void SMS_sending_textformat_distributionlist() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();// sitemap locator
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui locator
		Thread.sleep(2000);

		// switching to BMPportal window or popup
		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//td[@class='nav_td_border_select']")).click();// sms oage
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good mesage");// message
																										// description
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("hi hello");// text message

				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list
																							// radiobutton
				Thread.sleep(2000);
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@name='distList']"));
				Thread.sleep(2000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(1);// to select message name as tashi

				// dispatch time
				WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
				Thread.sleep(2000);
				Select select3 = new Select(dropdown3);
				select3.selectByIndex(1);// to select schedule
				Thread.sleep(3000);

				// logic to select date from calendar date in dispatch time
				driver.findElement(By.xpath("//tr[@id='DateTr']//img[@title='Click to Choose Date']")).click();// calendar
																												// opening
				// wait statement to display the calendar structure properly
				WebDriverWait wait22 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addLink22 = wait22
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
				addLink22.click();
				// locator for getting the month and year text from the calendar
				String amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth22);

				while (!(amonth22.equals("January 2024"))) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();// next button
																										// locator
					amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();// locator
					System.out.println(amonth22);

				}
				driver.findElement(By.xpath("//a[normalize-space()='5']")).click();// locator for date inside the
																					// calendar
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();// send button
				Thread.sleep(2000);
				// Check if confirmation message contains the specified text
				WebElement confirmationMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
				String confirmationMessage = confirmationMessageElement.getText();

				if (confirmationMessage.contains("Limit Exceeded , Max.Number of files are under processing")) {
					// If the confirmation message contains the specified text
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
					String errorMessage = errorMessageElement.getText();
					System.out.println(errorMessage);

					String expectedOutput = "Limit Exceeded , Max.Number of files are under processing";
					System.out.println(expectedOutput);
				} else {
					// If the confirmation message does not contain the specified text
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();// confirmation locator( OK button)
					WebElement successMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String successMessage = successMessageElement.getText();
					System.out.println(successMessage);

					int endIndex = successMessage.indexOf("Reference Number for uploaded file");
					String trimmedString = successMessage.substring(0, endIndex);
					System.out.println(trimmedString);

					String expectedOutput = "Reference Number for uploaded file";
					Assert.assertTrue(successMessage.contains(expectedOutput),
							"Error message mismatch for actualOutput from expectedOutput");
					Thread.sleep(3000);
				}
			}
		}

	}

	@Test(priority = 4)
	public void SMS_sending_binaryformat_mobile() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
		Thread.sleep(2000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {

				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // binary message
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
						.sendKeys("05000368656C6C6F");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='mobNo']")).sendKeys("97512345678");// mobile number
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

				WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String s1 = errorMessageElement.getText();
				System.out.println(s1);
				int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");

			}
		}
	}

	@Test(priority = 5)
	public void SMS_sending_binaryformat_fileupload() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
		Thread.sleep(2000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {

				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // binary message
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
						.sendKeys("05000368656C6C6F");
				Thread.sleep(2000);

				driver.findElement(By.xpath(
						"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[8]/td[1]/input"))
						.click(); // file upload radio button
				Thread.sleep(2000);

				String projectPath = System.getProperty("user.dir");
				String filePath = projectPath + "\\files\\demo.txt";

				// Check if the file exists
				File file = new File(filePath);
				if (file.exists()) {
					WebElement fileInput = driver.findElement(
							By.xpath("//input[@title='Select File to be uploaded with one mobile no. per line']"));
					fileInput.sendKeys(filePath);
					// Wait for the file to be uploaded (you may need to adjust the wait time)
					Thread.sleep(2000);

					// Continue with your code
				} else {
					System.out.println("File not found: " + filePath);
				}

				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
				Thread.sleep(2000);

				// Check if confirmation message contains the specified text
				WebElement confirmationMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
				String confirmationMessage = confirmationMessageElement.getText();

				if (confirmationMessage.contains("Limit Exceeded , Max.Number of files are under processing")) {
					// If the confirmation message contains the specified text
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
					String errorMessage = errorMessageElement.getText();
					System.out.println(errorMessage);

					String expectedOutput = "Limit Exceeded , Max.Number of files are under processing";
					System.out.println(expectedOutput);
				} else {
					// If the confirmation message does not contain the specified text
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();// confirmation locator( OK button)
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String s1 = errorMessageElement.getText();
					System.out.println(s1);
					int endIndex = s1.indexOf("Reference Number for uploaded file");
					String trimmedString = s1.substring(0, endIndex);
					System.out.println(trimmedString);
					String expectedOutput = "Reference Number for uploaded file";
					Assert.assertTrue(s1.contains(expectedOutput),
							"Error message mismatch for actualOutput from expectedOutput");
					Thread.sleep(3000);

				}
			}
		}

	}

	@Test(priority = 6)
	public void SMS_sending_binaryformat_distributionlist() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
		Thread.sleep(2000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {

				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // binary message
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// textmessage
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
						.sendKeys("05000368656C6C6F");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list
																							// radiobutton
				Thread.sleep(2000);
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@name='distList']"));
				Thread.sleep(2000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(1);// to select message name as tashi

				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
				Thread.sleep(2000);
				// Check if confirmation message contains the specified text
				WebElement confirmationMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
				String confirmationMessage = confirmationMessageElement.getText();

				if (confirmationMessage.contains("Limit Exceeded , Max.Number of files are under processing")) {
					// If the confirmation message contains the specified text
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
					String errorMessage = errorMessageElement.getText();
					System.out.println(errorMessage);

					String expectedOutput = "Limit Exceeded , Max.Number of files are under processing";
					System.out.println(expectedOutput);
				} else {
					// If the confirmation message does not contain the specified text
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();// confirmation locator( OK button)
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String s1 = errorMessageElement.getText();
					System.out.println(s1);
					int endIndex = s1.indexOf("//Msg Sent To : [smsc16 ]");
					// Trim the string to keep only the part until the specified index
					String trimmedString = s1.substring(0, endIndex);
					System.out.println(trimmedString);
					String expectedOutput = "//Msg Sent To : [smsc16 ]";
					Assert.assertTrue(s1.contains(expectedOutput),
							"Error message mismatch for actualOutput from expectedOutput");
					Thread.sleep(3000);

				}
			}
		}
	}

	@Test(priority = 7)
	public void SMS_sending_unicodeformat_mobile() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
		Thread.sleep(2000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {

				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // binary message
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='mobNo']")).sendKeys("97512345678");// mobile number
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

				WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String s1 = errorMessageElement.getText();
				System.out.println(s1);
				int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");
				// Trim the string to keep only the part until the specified index
				String trimmedString = s1.substring(0, endIndex);
				System.out.println(trimmedString);
				String expectedOutput = "Msg sent to : [97512345678 ]";
				Assert.assertTrue(s1.contains(expectedOutput),
						"Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);
			}
		}
	}

	@Test(priority = 8)
	public void SMS_sending_unicodeformat_distributionlist() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
		Thread.sleep(2000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {

				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // binary message
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
				Thread.sleep(2000);

				driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio
																							// button
				Thread.sleep(2000);
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@name='distList']"));
				Thread.sleep(2000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(1);// to select message name as tashi

				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
				Thread.sleep(2000);
				// Check if confirmation message contains the specified text
				WebElement confirmationMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
				String confirmationMessage = confirmationMessageElement.getText();

				if (confirmationMessage.contains("Limit Exceeded , Max.Number of files are under processing")) {
					// If the confirmation message contains the specified text
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
					String errorMessage = errorMessageElement.getText();
					System.out.println(errorMessage);

					String expectedOutput = "Limit Exceeded , Max.Number of files are under processing";
					System.out.println(expectedOutput);

				} else {
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String s1 = errorMessageElement.getText();
					System.out.println(s1);
					int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");
					// Trim the string to keep only the part until the specified index
					String trimmedString = s1.substring(0, endIndex);
					System.out.println(trimmedString);
					String expectedOutput = "Msg sent to : [97512345678 ]";
					Assert.assertTrue(s1.contains(expectedOutput),
							"Error message mismatch for actualOutput from expectedOutput");
					Thread.sleep(3000);
				}
			}
		}
	}

	@Test(priority = 9)
	public void SMS_sending_unicodeformat_fileupload() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
		Thread.sleep(2000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {

				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // binary message
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
				Thread.sleep(2000);

				// dispatch time
				WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
				Thread.sleep(2000);
				Select select3 = new Select(dropdown3);
				select3.selectByIndex(1);// to select schedule
				Thread.sleep(3000);

				// logic to select date from calendar date in dispatch time
				driver.findElement(By.xpath("//tr[@id='DateTr']//img[@title='Click to Choose Date']")).click();// calendar
																												// opening
				// wait statement to display the calendar structure properly
				WebDriverWait wait22 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addLink22 = wait22
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
				addLink22.click();
				// locator for getting the month and year text from the calendar
				String amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth22);

				while (!(amonth22.equals("January 2024"))) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();// next button
																										// locator
					amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();// locator																										
					System.out.println(amonth22);

				}
				driver.findElement(By.xpath("//a[normalize-space()='5']")).click();// locator for date inside the
																					// calendar
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				driver.findElement(By.xpath(
						"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[8]/td[1]/input"))
						.click(); // file upload radio button
				Thread.sleep(2000);

				String projectPath = System.getProperty("user.dir");
				String filePath = projectPath + "\\files\\demo.txt";

				// Check if the file exists
				File file = new File(filePath);
				if (file.exists()) {
					WebElement fileInput = driver.findElement(By.xpath("//input[@name='fname']"));
					fileInput.sendKeys(filePath);
					// Wait for the file to be uploaded (you may need to adjust the wait time)
					Thread.sleep(2000);

					// Continue with your code
				} else {
					System.out.println("File not found: " + filePath);
				}

				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();// send button
				Thread.sleep(2000);
				// Check if confirmation message contains the specified text
				WebElement confirmationMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
				String confirmationMessage = confirmationMessageElement.getText();

				if (confirmationMessage.contains("Limit Exceeded , Max.Number of files are under processing")) {
					// If the confirmation message contains the specified text
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
					String errorMessage = errorMessageElement.getText();
					System.out.println(errorMessage);

					String expectedOutput = "Limit Exceeded , Max.Number of files are under processing";
					System.out.println(expectedOutput);
				} else {
					// If the confirmation message does not contain the specified text
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();// confirmation locator( OK button)
					WebElement successMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String successMessage = successMessageElement.getText();
					System.out.println(successMessage);

					int endIndex = successMessage.indexOf("Reference Number for uploaded file");
					String trimmedString = successMessage.substring(0, endIndex);
					System.out.println(trimmedString);

					String expectedOutput = "Reference Number for uploaded file";
					Assert.assertTrue(successMessage.contains(expectedOutput),
							"Error message mismatch for actualOutput from expectedOutput");
					Thread.sleep(3000);
				}
			}
		}

	}

	@Test(priority = 10)
	public void SMS_sending_cannedmessage_fileupload() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
		Thread.sleep(2000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {

				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("jyoti"); // user name
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jyoti"); // password
				Thread.sleep(1000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Canned Message']")).click(); // canned message
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@title='Enter Message Description']")).sendKeys("good message"); // message
				Thread.sleep(1000); // description
				// message name
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@title='select message name']"));
				Thread.sleep(1000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(1);// to select message name as tashi
				Thread.sleep(2000);

				// dispatch time
				WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
				Thread.sleep(1000);
				Select select3 = new Select(dropdown3);
				select3.selectByIndex(0);// to select now option
				Thread.sleep(2000);

				String projectPath = System.getProperty("user.dir");
				String filePath = projectPath + "\\files\\demo.txt";

				// Check if the file exists
				File file = new File(filePath);
				if (file.exists()) {
					WebElement fileInput = driver.findElement(By.xpath("//input[@title='Select File to be uploaded']"));
					fileInput.sendKeys(filePath);
					// Wait for the file to be uploaded (you may need to adjust the wait time)
					Thread.sleep(2000);

					// Continue with your code
				} else {
					System.out.println("File not found: " + filePath);
				}

				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
				Thread.sleep(1000);
				// Check if confirmation message contains the specified text
				WebElement confirmationMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
				String confirmationMessage = confirmationMessageElement.getText();

				if (confirmationMessage.contains("Limit Exceeded , Max.Number of files are under processing")) {
					// If the confirmation message contains the specified text
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='bg_report_01']"));
					String errorMessage = errorMessageElement.getText();
					System.out.println(errorMessage);

					String expectedOutput = "Limit Exceeded , Max.Number of files are under processing";
					//System.out.println(expectedOutput);
				} else {
					// If the confirmation message does not contain the specified text
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();// confirmation locator( OK button)
					WebElement successMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String successMessage = successMessageElement.getText();
					System.out.println(successMessage);

					int endIndex = successMessage.indexOf("Reference Number for uploaded file");
					String trimmedString = successMessage.substring(0, endIndex);
					System.out.println(trimmedString);

					String expectedOutput = "Reference Number for uploaded file";
					Assert.assertTrue(successMessage.contains(expectedOutput),
							"Error message mismatch for actualOutput from expectedOutput");
					Thread.sleep(3000);
				}
			}
		}

	}
}
