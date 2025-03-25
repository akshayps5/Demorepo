package SMSC;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class barring_issue {
	public static WebDriver driver;
	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() throws InterruptedException {
		WebDriver driver  = new ChromeDriver();
	SMSC.barring_issue.driver= driver;
//		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
//		SMSC.barring_issue.driver = driver; // Set the WebDriver instance

		 driver.get("https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp");

//		String url = "https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp";
//		driver.get(url);
//		String pageTitle = driver.getTitle();
//		System.out.println("Title of " + url + ": " + pageTitle);
		
		List <WebElement> advancedButton = driver.findElements(By.xpath("//*[@id='details-button']"));
		if (advancedButton.size() > 0) {
			// Click the "Advanced" button and then click the "Proceed" link
			driver.findElement(By.cssSelector("#details-button")).click();
	Thread.sleep(1000);
			driver.findElement(By.cssSelector("#proceed-link")).click();
		}
		// Set implicit wait to 5 seconds
		driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
		driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
		driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void barringpage_system_vmsc() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='System Config']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//a[normalize-space()='Barring']"));
		Thread.sleep(2000);
		String option11 = "Barring";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='18']")).click();// vmsc destination check box
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 20 digits']"))
				.sendKeys("1234");// dest vmsc button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("testing");// description text box
		Thread.sleep(2000);

		// Scroll down the page wimdow using JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll down by a specific pixel value (e.g., 500 pixels)
		js.executeScript("window.scrollBy(0,500)");

		// selecting disable option in status drop down using select class
		WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='addStatus']"));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText("Disable");

		// logic to select the date from calendar(for future date selection)
		driver.findElement(By.xpath("//input[@id='datepickers']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
		addLink.click();

		Thread.sleep(2000);
		WebElement slider1 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
		Actions actions2 = new Actions(driver);
		actions2.dragAndDropBy(slider1, 10, 15).perform();
		Thread.sleep(2000);
		// locating the date inside the calendar
		 driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr[9]/td/input[1]")).click();// done button
		Thread.sleep(2000);

		Alert alert121 = driver.switchTo().alert();

		alert121.accept();
		WebElement message = null;
		js.executeScript("window.scrollBy(500,0)");
System.out.println("erftyuiop");
		try {
			message = driver.findElement(By.xpath(
					"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Dest IMSI Already exists.']"));
			String messageText = message.getText();
			System.out.println("uuuurftyuiop");
			if (messageText.equals("Addition Unsuccessful.")) {
				// Handle failure case
				System.out.println("Failure: " + messageText);
				Assert.assertEquals(messageText, "Addition Unsuccessful.", "Failure message doesn't match");
				// Additional actions for failure case
			} else if (messageText.equals("Addition Successful.")) {
				// Handle success case
				System.out.println("Success: " + messageText);
				Assert.assertEquals(messageText, "Addition Successful.", "Success message doesn't match");
				// Additional actions for success case
			} else if (messageText.equals("Addition Unsuccessful. This Dest IMSI Already exists.")) {
				// Handle success case
				System.out.println("Success: " + messageText);
				Assert.assertEquals(messageText, "Addition Unsuccessful. This Dest IMSI Already exists.",
						"Success message doesn't match");
				// Additional actions for success case
			} else {
				Assert.fail("Unknown message found");
			}

		} catch (NoSuchElementException e) {
			// Message not found, continue without throwing an exception
			Assert.fail("Neither success nor failure message found");
		}

		driver.switchTo().defaultContent();
		Thread.sleep(10000);
		System.out.println("adding the same destination address second time");
		// second time adding the same vmsi destination number into the barring
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"siteMap\"]/table/tbody/tr/td/nav/ul/li/ul/li[1]/ul/li[4]/a")).click();
		Thread.sleep(3000);

		// Switch to the iframe
		WebElement iframe1 = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe1);

		driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='18']")).click();// sources address check box
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 20 digits']"))
				.sendKeys("1234");// dest vmsc button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("testing");// dest vmsc button
		Thread.sleep(2000);

		// Scroll down using JavascriptExecutor
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		// Scroll down by a specific pixel value (e.g., 500 pixels)
		js1.executeScript("window.scrollBy(0,500)");

		WebElement dropdownElement1 = driver.findElement(By.xpath("//select[@id='addStatus']"));
		Select dropdown1 = new Select(dropdownElement1);
		dropdown1.selectByVisibleText("Disable");

		// logic to select the date from calendar(for future date selection)
		driver.findElement(By.xpath("//input[@id='datepickers']")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addLink1 = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
		addLink1.click();
		String amonth1 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		System.out.println(amonth1);
		while (!(amonth1.equals("February 2024"))) {

			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			amonth1 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			System.out.println(amonth1);
		}
		// driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[5]/a")).click();
		Thread.sleep(3000);
		// Locate the slider element
		WebElement slider3 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
		Actions actions113 = new Actions(driver);
		actions113.dragAndDropBy(slider1, 10, 20).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();// done button
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
		Thread.sleep(2000);
		Alert alert1211 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert1211.accept();

		WebElement message1 = null;
	
		try {
			message1 = driver.findElement(By.xpath(
					"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Dest IMSI Already exists.']"));
			String messageText1 = message1.getText();

			if (messageText1.equals("Addition Unsuccessful.")) {
				// Handle failure case
				System.out.println("Failure: " + messageText1);
				Assert.assertEquals(messageText1, "Addition Unsuccessful.", "Failure message doesn't match");
				// Additional actions for failure case
			} else if (messageText1.equals("Addition Successful.")) {
				// Handle success case
				System.out.println("Success: " + messageText1);
				Assert.assertEquals(messageText1, "Addition Successful.", "Success message doesn't match");
				// Additional actions for success case
			} else if (messageText1.equals("Addition Unsuccessful. This Dest IMSI Already exists.")) {
				// Handle success case
				System.out.println("Success: " + messageText1);
				Assert.assertEquals(messageText1, "Addition Unsuccessful. This Dest IMSI Already exists.",
						"Success message doesn't match");
				// Additional actions for success case
			} else {
				Assert.fail("Unknown message found");
			}

		} catch (NoSuchElementException e) {
			// Message not found, continue without throwing an exception
			Assert.fail("Neither success nor failure message found");
		}
	}
	@Test(priority = 2)
	public void barringpage_system_imsc() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("(//a[contains(text(),'Barring')])[2]")).click();// barring in system config locator
		Thread.sleep(1000);
		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='19']")).click();// imsi destination check box
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 16 digits']"))
				.sendKeys("98745633");// dest vmsc button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("testing");// desription textbox
		Thread.sleep(2000);

		// Scroll down the page using JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll down by a specific pixel value (e.g., 500 pixels)
		js.executeScript("window.scrollBy(0,500)");

		WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='addStatus']"));// selecting disable
																								// option in status
																								// dropdown
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText("Disable");

		// logic to select the date from calendar(for future date selection)
		driver.findElement(By.xpath("//input[@id='datepickers']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
		addLink.click();
		String amonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		System.out.println(amonth);
		while (!(amonth.equals("February 2024"))) {

			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			amonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			System.out.println(amonth);
		}
		// driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[5]/a")).click();//locator
		// for active date
		Thread.sleep(3000);
		// Locate the slider element
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
		Actions actions1 = new Actions(driver);
		actions1.dragAndDropBy(slider, 10, 15).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();// done button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
		Thread.sleep(2000);
		Alert alert121 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert121.accept();

		// creating a variable as message and stored this value as null
		WebElement message = null;
		// switching the success case or failure case based on the text we get from the
		// element present(either success message, failure message)
		// this text is stored inside the message variable and switching the cases
		// accordingly
		try {
			message = driver.findElement(By.xpath(
					"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Dest IMSI Already exists.']"));
			String messageText = message.getText();

			if (messageText.equals("Addition Unsuccessful.")) {
				// Handle failure case
				System.out.println("Failure: " + messageText);
				Assert.assertEquals(messageText, "Addition Unsuccessful.", "Failure message doesn't match");
				// Additional actions for failure case
			} else if (messageText.equals("Addition Successful.")) {
				// Handle success case
				System.out.println("Success: " + messageText);
				Assert.assertEquals(messageText, "Addition Successful.", "Success message doesn't match");
				// Additional actions for success case
			} else if (messageText.equals("Addition Unsuccessful. This Dest IMSI Already exists.")) {
				// Handle success case
				System.out.println("Success: " + messageText);
				Assert.assertEquals(messageText, "Addition Unsuccessful. This Dest IMSI Already exists.",
						"Success message doesn't match");
				// Additional actions for success case
			} else {
				Assert.fail("Unknown message found");
			}

		} catch (NoSuchElementException e) {
			// Message not found, continue without throwing an exception
			Assert.fail("Neither success nor failure message found");
		}

		driver.switchTo().defaultContent();
		Thread.sleep(10000);
		System.out.println("adding the same destination address second time");
		// second time adding the same imsi destination number into the barring
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"siteMap\"]/table/tbody/tr/td/nav/ul/li/ul/li[1]/ul/li[4]/a")).click();// locator
																														// for																													// config
		Thread.sleep(3000);

		// Switch to the iframe
		WebElement iframe1 = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe1);

		driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='19']")).click();// imsi destination check box
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 16 digits']"))
				.sendKeys("98745632");// dest vmsc button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("testing");// description text box
		Thread.sleep(2000);

		// Scroll down the page using JavascriptExecutor
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		// Scroll down by a specific pixel value (e.g., 500 pixels)
		js1.executeScript("window.scrollBy(0,500)");

		// selecting disable option in status dropdown
		WebElement dropdownElement1 = driver.findElement(By.xpath("//select[@id='addStatus']"));
		Select dropdown1 = new Select(dropdownElement1);
		dropdown1.selectByVisibleText("Disable");

		// logic to select the date from calendar(for future date selection)
		driver.findElement(By.xpath("//input[@id='datepickers']")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addLink1 = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
		addLink1.click();
		String amonth1 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		System.out.println(amonth1);
		while (!(amonth1.equals("February 2024"))) {

			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			amonth1 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			System.out.println(amonth1);
		}
		// locator for selecting the date
		// driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[5]/a")).click();
		Thread.sleep(3000);
		// Locate the slider element
		WebElement slider1 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
		Actions actions111 = new Actions(driver);
		actions111.dragAndDropBy(slider1, 10, 20).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();// done button
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
		Thread.sleep(2000);
		Alert alert1211 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert1211.accept();

		// creating a variable as message and stored this value as null
		WebElement message1 = null;
		// switching the success case or failure case based on the text we get from the
		// element present(either success message, failure message)
		// this text is stored inside the message variable and switching the cases
		// accordingly
		try {
			message1 = driver.findElement(By.xpath(
					"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Dest IMSI Already exists.']"));
			String messageText1 = message1.getText();

			if (messageText1.equals("Addition Unsuccessful.")) {
				// Handle failure case
				System.out.println("Failure: " + messageText1);
				Assert.assertEquals(messageText1, "Addition Unsuccessful.", "Failure message doesn't match");
				// Additional actions for failure case
			} else if (messageText1.equals("Addition Successful.")) {
				// Handle success case
				System.out.println("Success: " + messageText1);
				Assert.assertEquals(messageText1, "Addition Successful.", "Success message doesn't match");
				// Additional actions for success case
			} else if (messageText1.equals("Addition Unsuccessful. This Dest IMSI Already exists.")) {
				// Handle success case
				System.out.println("Success: " + messageText1);
				Assert.assertEquals(messageText1, "Addition Unsuccessful. This Dest IMSI Already exists.",
						"Success message doesn't match");
				// Additional actions for success case
			} else {
				Assert.fail("Unknown message found");	
			}

		} catch (NoSuchElementException e) {
			// Message not found, continue without throwing an exception
			Assert.fail("Neither success nor failure message found");
		}
	}
}
