package bmp;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.testng.Assert;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.apache.commons.io.FileUtils; // For screenshot handling
import java.io.File;
import org.testng.Reporter;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bmpnew {
	public static WebDriver driver;

		private static final String DB_URL = "jdbc:mysql://10.0.1.210:3306/BMPDB";
	    private static final String DB_USER = "bmp";
	    private static final String DB_PASSWORD = "bmp@Tayana123";
	   
	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() throws InterruptedException {

		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		bmpnew.driver = driver;
//		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance

		driver.get("https://10.0.1.210:3002/bmp/auth/login");

//		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("transferpts"); // entering the
																									// username
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj@2000");// entering the
																									// password
		driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();// performing the click action
		driver.manage().window().maximize();
		// driver.findElement(By.id(browser)

		Thread.sleep(3000);
	}

	@Test(priority = 1)
	public void sending_nsg_for_10msisdns() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		String inputMsisdn = "9607995681,9607998905,9607923456,9607992348,9607998908,9607992351,9607990127,9607995679,9607995670,9607993456";
		String[] msisdns = inputMsisdn.split(",");

		try {
			// Enter MSISDN in the input field
			driver.findElement(By.xpath("(//input[@id='mui-4'])[1]")).sendKeys(inputMsisdn);

			// Enter the message
			driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello message for 10 msisdns");
			Thread.sleep(1000);

			// Click the send button
			driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			Thread.sleep(1000);

			// Confirm sending
			driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
			Thread.sleep(3000);

			// Navigate to Sent Items
			driver.findElement(By.xpath("//span[normalize-space()='Sent Items']")).click();
			Thread.sleep(2000);

			// Click on the specific cell in the first record
			WebElement firstRecordCell = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]/td[3]")));
			firstRecordCell.click();

			// Verify "Message Details" section is displayed
			WebElement messageDetails = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//th[normalize-space()='Message Details']")));
			Assert.assertTrue(messageDetails.isDisplayed(), "'Message Details' section is not visible.");
			System.out.println("Assertion Passed: 'Message Details' is displayed.");

			// Extract all MSISDNs from the "To" column
			List<WebElement> allToNumbers = driver
					.findElements(By.xpath("//td[normalize-space()='" + inputMsisdn + "']"));
			System.out.println("Found the following MSISDNs in the 'To' field:");

			// Print each MSISDN in the "To" field
			for (WebElement element : allToNumbers) {
				String msisdnText = element.getText().trim();
				System.out.println("Found MSISDN in 'To' field: " + msisdnText);
			}

			// Check if all MSISDNs are found
			boolean allMsisdnsFound = true;
			for (String msisdn : msisdns) {
				boolean msisdnFound = false;
				for (WebElement element : allToNumbers) {
					String msisdnText = element.getText().trim();
					if (msisdnText.equals(msisdn)) {
						msisdnFound = true;
						break;
					}
				}
				// If any MSISDN is not found, set flag to false and exit the loop
				if (!msisdnFound) {
					allMsisdnsFound = false;
					break;
				}
			}

			// Only print "Pass" if all MSISDNs are found
			if (allMsisdnsFound) {
				System.out.println("Pass: All MSISDNs found in 'To' field.");
			}

			// Extract and validate "Status" field
			String status = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//td[contains(text(),'Delivered') or contains(text(),'Sent')]"))).getText();

			if (status.equals("Delivered") || status.equals("Sent")) {
				System.out.println("Assertion Passed: Message status is '" + status + "'.");
			} else {
				System.out.println("Assertion Failed: Message status is not delivered or sent.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void sending_normal_msg1() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String inputMsisdn = "9609897654"; // Define the MSISDN for validation

		try {
			// Enter MSISDN in the input field
			driver.findElement(By.xpath("(//input[@id='mui-4'])[1]")).sendKeys(inputMsisdn);

			// Enter the message
			driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");
			Thread.sleep(1000);

			// Click the send button
			driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			Thread.sleep(1000);

			// Confirm sending
			driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
			Thread.sleep(3000);

			// Navigate to Sent Items
			driver.findElement(By.xpath("//span[normalize-space()='Sent Items']")).click();

			// Wait for the MSISDN row in the table and click on it (Use the variable
			// inputMsisdn)
			WebElement msisdnRow = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//td[normalize-space()='" + inputMsisdn + "']")));
			msisdnRow.click();

			// Verify "Message Details" section is displayed
			WebElement messageDetails = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//th[normalize-space()='Message Details']")));
			if (messageDetails.isDisplayed()) {
				System.out.println("'Message Details' is displayed.");
			} else {
				throw new Exception("'Message Details' section is not visible.");
			}

			// Extract and validate "To" field
			String toNumber = wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//td[normalize-space()='" + inputMsisdn + "']")))
					.getText(); // Grab the MSISDN from the "To" field.

			if (toNumber.equals(inputMsisdn)) {
				System.out.println("To number matches the input MSISDN.");
			} else {
				System.out.println("Test Failed: To number does not match the input MSISDN.");
				return;
			}

			// Extract and validate "Status" field from the <td> containing the status
			String status = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//td[contains(text(),'Delivered') or contains(text(),'Sent')]"))).getText();

			// Check the status and pass/fail accordingly
			if ("Delivered".equalsIgnoreCase(status)) {
				System.out.println("Test Passed: Message status is Delivered.");
			} else if ("Sent".equalsIgnoreCase(status)) {
				System.out.println("Test Failed: Message status is Sent.");
				Assert.fail("Test failed because message status is Sent.");
			} else {
				System.out.println("Test Failed: Unknown message status.");
				Assert.fail("Test failed because message status is unknown.");
			}

		} catch (TimeoutException e) {
			System.out.println("Timeout waiting for an element: " + e.getMessage());
			Assert.fail("Timeout waiting for an element: " + e.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			Assert.fail("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			Assert.fail("An error occurred: " + e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() {
		// Close the browser after test execution
		if (driver != null) {
			driver.quit();
		}
	}
}
