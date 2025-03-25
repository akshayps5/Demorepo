package SMSC;

	import java.time.Duration;
	import java.util.List;
	import java.util.NoSuchElementException;
	import java.util.Set;
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

	public class barring_all  {
		public static WebDriver driver;

		@BeforeClass
		// logic to login one time in the browser and execute all test cases within the
		// browser
		public void setUp() throws InterruptedException {
			WebDriver driver  = new ChromeDriver();
		SMSC.barring_all.driver= driver;
//			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
//			SMSC.barring_issue.driver = driver; // Set the WebDriver instance

			 driver.get("https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp");

//			String url = "https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp";
//			driver.get(url);
//			String pageTitle = driver.getTitle();
//			System.out.println("Title of " + url + ": " + pageTitle);
			
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
			Thread.sleep(3000);

			// Click on the "barring" button
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Barring')]")).click();
			Thread.sleep(2000);

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
					.sendKeys("9876528");// dest vmsc button
			Thread.sleep(2000);
			
			// selecting drop option in status drop down using select class
					WebElement dropdownElement12 = driver.findElement(By.xpath("//select[@id='addAction']"));
					Select dropdown12 = new Select(dropdownElement12);
					dropdown12.selectByVisibleText("Drop");
			driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
					.sendKeys("testing");// description text box
			Thread.sleep(2000);

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js.executeScript("window.scrollBy(0,200)");

			// selecting disable option in status drop down using select class
			WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='addStatus']"));
			Select dropdown = new Select(dropdownElement);
			dropdown.selectByVisibleText("Disable");

			// logic to select the date from calendar(for future date selection)
			driver.findElement(By.xpath("//input[@id='datepickers']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
			addLink.click();

			Thread.sleep(2000);
			WebElement slider1 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
			Actions actions2 = new Actions(driver);
			actions2.dragAndDropBy(slider1, 10, 30).perform();
			Thread.sleep(2000);
			// locating the date inside the calendar
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
			Thread.sleep(2000);
			Alert alert121 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert121.accept();
			WebElement message = null;

			// switching the success case or failure case based on the text we get from the
			// element present(either success message, failure message)
			// this text is stored inside the message variable and switching the cases
			// accordingly
			try {
				Thread.sleep(2000);
				message = driver.findElement(By.xpath(
						"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Dest VMSC Already exists.']"));
				String messageText = message.getText();
				System.out.println(messageText);

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
				} else if (messageText.equals("Addition Unsuccessful. This Dest VMSC Already exists.")) {
					// Handle success case
					System.out.println("Success: " + messageText);
					Assert.assertEquals(messageText, "Addition Unsuccessful. This Dest VMSC Already exists.",
							"Success message doesn't match");
					// Additional actions for success case
				} else {
					Assert.fail("Unknown message found");
				}

			} catch (NoSuchElementException e) {
				// Message not found, continue without throwing an exception
				Assert.fail("Neither success nor failure message found");
			}
			System.out.println("scroll down");
			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js1.executeScript("window.scrollBy(200,500)");
			System.out.println("scroll down");
			// img[@name='plusminus0']
			driver.findElement(By.xpath("//img[@name='plusminus0']")).click();// plus button
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@name='barType2'])[2]")).click();// source address button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@onclick='gotoModProceed();']")).click();// proceed button button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='searchNum18']")).sendKeys("9876528");// dest vmsc button
			Thread.sleep(2000);

			// selecting disable option in status drop down using select class
			WebElement dropdownElement121 = driver.findElement(By.xpath("//select[@name='barringType']"));
			Select dropdown121 = new Select(dropdownElement121);
			dropdown121.selectByVisibleText("Temporary Barring");
			driver.findElement(By.xpath("//input[@title=' Click to Search.']")).click();// plus button
			Thread.sleep(10000);

			// Get the window handle of the parent window
			String parentWindowHandle = driver.getWindowHandle();
			System.out.println("switched to new window6666");
			// Get the window handles of all the windows that are currently open
			Set<String> windowHandles = driver.getWindowHandles();
			System.out.println("switched to new window5657");
			// Loop through the window handles to find the handle of the new window
			// Switch to the pop-up window
			for (String handle : windowHandles) {
				if (!handle.equals(parentWindowHandle)) {
					driver.switchTo().window(handle);
					// break;
					driver.manage().window().maximize();
					Thread.sleep(2000);
					message = driver.findElement(By.cssSelector("#borderDiv1 > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > font"));
					String messageText1 = message.getText();
					//driver.close();
					System.out.println("switched to new window");
					// Switch back to the parent window
			        driver.switchTo().window(parentWindowHandle);
			        break;  // Break out of the loop after handling the pop-up window
				}
				driver.switchTo().defaultContent();
				Thread.sleep(5000);
			}
			
		}

		@Test(priority = 2)
		public void barringpage_system_imsi() throws InterruptedException {
			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(3000);

			// Click on the "barring" button
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Barring')]")).click();
			Thread.sleep(2000);

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
					.sendKeys("9876543");// dest vmsc button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
					.sendKeys("testing");// description text box
			Thread.sleep(2000);

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js.executeScript("window.scrollBy(0,200)");

			// selecting disable option in status drop down using select class
			WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='addStatus']"));
			Select dropdown = new Select(dropdownElement);
			dropdown.selectByVisibleText("Disable");

			// logic to select the date from calendar(for future date selection)
			driver.findElement(By.xpath("//input[@id='datepickers']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
			addLink.click();

			Thread.sleep(2000);
			WebElement slider1 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
			Actions actions2 = new Actions(driver);
			actions2.dragAndDropBy(slider1, 10, 15).perform();
			Thread.sleep(2000);
			// locating the date inside the calendar
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
			Thread.sleep(2000);
			Alert alert121 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert121.accept();
			WebElement message = null;

			// switching the success case or failure case based on the text we get from the
			// element present(either success message, failure message)
			// this text is stored inside the message variable and switching the cases
			// accordingly
			try {
				Thread.sleep(2000);
				message = driver.findElement(By.xpath(
						"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Dest IMSI Already exists.']"));
				String messageText = message.getText();
				System.out.println(messageText);

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

			System.out.println("barring the same vmsc destination second time");

			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(3000);

			// Click on the "barring" button
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Barring')]")).click();
			Thread.sleep(2000);

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
					.sendKeys("1234");// dest vmsc button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
					.sendKeys("testing");// description text box
			Thread.sleep(2000);

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js1.executeScript("window.scrollBy(0,200)");

			// selecting disable option in status drop down using select class
			WebElement dropdownElement1 = driver.findElement(By.xpath("//select[@id='addStatus']"));
			Select dropdown1 = new Select(dropdownElement1);
			dropdown1.selectByVisibleText("Disable");

			// logic to select the date from calendar(for future date selection)
			driver.findElement(By.xpath("//input[@id='datepickers']")).click();
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink1 = wait1.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
			addLink1.click();

			Thread.sleep(2000);
			WebElement slider11 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
			Actions actions21 = new Actions(driver);
			actions21.dragAndDropBy(slider11, 10, 15).perform();
			Thread.sleep(2000);
			// locating the date inside the calendar
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
			Thread.sleep(2000);
			Alert alert1211 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert1211.accept();
			WebElement message1 = null;

			// switching the success case or failure case based on the text we get from the
			// element present(either success message, failure message)
			// this text is stored inside the message variable and switching the cases
			// accordingly
			try {
				Thread.sleep(2000);
				message1 = driver.findElement(By.xpath(
						"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Dest IMSI Already exists.']"));
				String messageText1 = message1.getText();
				System.out.println(messageText1);

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
			driver.switchTo().defaultContent();
			Thread.sleep(5000);
		}

		@Test(priority = 3)
		public void barringpage_system_source_vmsc() throws InterruptedException {
			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(3000);

			// Click on the "barring" button
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Barring')]")).click();
			Thread.sleep(2000);

			// Switch to the iframe
			WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
			driver.switchTo().frame(iframe);

			driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='addbarType1']")).click();// source address check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='18']")).click();// vmsc destination check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr[2]/td[4]/input"))
					.sendKeys("99876451");// source adress button
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 20 digits']"))
					.sendKeys("9876541");// dest vmsc button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
					.sendKeys("testing");// description text box
			Thread.sleep(2000);

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js.executeScript("window.scrollBy(0,150)");

			// selecting disable option in status drop down using select class
			WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='addStatus']"));
			Select dropdown = new Select(dropdownElement);
			dropdown.selectByVisibleText("Disable");

			// logic to select the date from calendar(for future date selection)
			driver.findElement(By.xpath("//input[@id='datepickers']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
			addLink.click();

			Thread.sleep(2000);
			WebElement slider1 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
			Actions actions2 = new Actions(driver);
			actions2.dragAndDropBy(slider1, 10, 15).perform();
			Thread.sleep(2000);
			// locating the date inside the calendar
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
			Thread.sleep(2000);
			Alert alert121 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert121.accept();
			System.out.println("scroll down");
			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js54 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js54.executeScript("window.scrollBy(0,-200)");
			System.out.println("scroll down");
			Thread.sleep(3000);
			WebElement message = null;

			// switching the success case or failure case based on the text we get from the
			// element present(either success message, failure message)
			// this text is stored inside the message variable and switching the cases
			// accordingly
			try {
				Thread.sleep(2000);
				message = driver.findElement(By.xpath(
						"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Source Address and Dest VMSC combination already exists.']"));
				String messageText = message.getText();
				System.out.println(messageText);

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
				} else if (messageText
						.equals("Addition Unsuccessful. This Source Address and Dest VMSC combination already exists.")) {
					// Handle success case
					System.out.println("Success: " + messageText);
					Assert.assertEquals(messageText,
							"Addition Unsuccessful. This Source Address and Dest VMSC combination already exists.",
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

			System.out.println("barring the same vmsc destination with source address second time");

			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(3000);

			// Click on the "barring" button
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Barring')]")).click();
			Thread.sleep(2000);

			// Switch to the iframe
			WebElement iframe1 = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
			driver.switchTo().frame(iframe1);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='addbarType1']")).click();// source address check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='18']")).click();// vmsc destination check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr[2]/td[4]/input"))
					.sendKeys("99876454");// source adress button
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 20 digits']"))
					.sendKeys("99876451");// dest vmsc button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
					.sendKeys("testing");// description text box
			Thread.sleep(2000);

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js1.executeScript("window.scrollBy(0,200)");

			// selecting disable option in status drop down using select class
			WebElement dropdownElement1 = driver.findElement(By.xpath("//select[@id='addStatus']"));
			Select dropdown1 = new Select(dropdownElement1);
			dropdown1.selectByVisibleText("Disable");

			// logic to select the date from calendar(for future date selection)
			driver.findElement(By.xpath("//input[@id='datepickers']")).click();
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink1 = wait1.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
			addLink1.click();

			Thread.sleep(2000);
			WebElement slider11 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
			Actions actions21 = new Actions(driver);
			actions21.dragAndDropBy(slider11, 10, 15).perform();
			Thread.sleep(2000);
			// locating the date inside the calendar
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
			Thread.sleep(2000);
			Alert alert1211 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert1211.accept();

			System.out.println("scroll down");
			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js541 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js541.executeScript("window.scrollBy(0,-200)");
			System.out.println("scroll down");
			Thread.sleep(3000);

			WebElement message1 = null;

			// switching the success case or failure case based on the text we get from the
			// element present(either success message, failure message)
			// this text is stored inside the message variable and switching the cases
			// accordingly
			try {
				Thread.sleep(2000);
				message1 = driver.findElement(By.xpath(
						"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] |  //td[normalize-space()='Addition Unsuccessful. This Source Address and Dest VMSC combination already exists.']"));
				String messageText1 = message1.getText();
				System.out.println(messageText1);

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
				} else if (messageText1
						.equals("Addition Unsuccessful. This Source Address and Dest VMSC combination already exists.")) {
					// Handle success case
					System.out.println("Success: " + messageText1);
					Assert.assertEquals(messageText1,
							"Addition Unsuccessful. This Source Address and Dest VMSC combination already exists.",
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
			Thread.sleep(5000);
		}

		@Test(priority = 4)
		public void barringpage_system_source_imsi() throws InterruptedException {
			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(3000);

			// Click on the "barring" button
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Barring')]")).click();
			Thread.sleep(2000);

			// Switch to the iframe
			WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
			driver.switchTo().frame(iframe);

			driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='addbarType1']")).click();// source address check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='19']")).click();// imsi destination check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr[2]/td[4]/input"))
					.sendKeys("99876454");// source adress button
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 16 digits']"))
					.sendKeys("9876543");// dest imsi button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
					.sendKeys("testing");// description text box
			Thread.sleep(2000);

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js.executeScript("window.scrollBy(0,200)");

			// selecting disable option in status drop down using select class
			WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='addStatus']"));
			Select dropdown = new Select(dropdownElement);
			dropdown.selectByVisibleText("Disable");

			// logic to select the date from calendar(for future date selection)
			driver.findElement(By.xpath("//input[@id='datepickers']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
			addLink.click();

			Thread.sleep(2000);
			WebElement slider1 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
			Actions actions2 = new Actions(driver);
			actions2.dragAndDropBy(slider1, 10, 15).perform();
			Thread.sleep(2000);
			// locating the date inside the calendar
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
			Thread.sleep(2000);
			Alert alert121 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert121.accept();
			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js33 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js33.executeScript("window.scrollBy(0,-200)");

			WebElement message = null;

			// switching the success case or failure case based on the text we get from the
			// element present(either success message, failure message)
			// this text is stored inside the message variable and switching the cases
			// accordingly
			try {
				Thread.sleep(2000);
				message = driver.findElement(By.xpath(
						"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Source Address and Dest IMSI combination already exists.']"));
				String messageText = message.getText();
				System.out.println(messageText);

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
				} else if (messageText
						.equals("Addition Unsuccessful. This Source Address and Dest IMSI combination already exists.")) {
					// Handle success case
					System.out.println("Failure: " + messageText);
					Assert.assertEquals(messageText,
							"Addition Unsuccessful. This Source Address and Dest IMSI combination already exists.",
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

			System.out.println("barring the same imsi destination with source address second time");

			driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
			Thread.sleep(3000);

			// Click on the "barring" button
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Barring')]")).click();
			Thread.sleep(2000);

			// Switch to the iframe
			WebElement iframe1 = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
			driver.switchTo().frame(iframe1);

			driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='addbarType1']")).click();// source address check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='19']")).click();// imsi destination check box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary button
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr[2]/td[4]/input"))
					.sendKeys("99876454");// source adress button
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 16 digits']"))
					.sendKeys("99876451");// dest imsi button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
					.sendKeys("testing");// description text box
			Thread.sleep(2000);

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js1.executeScript("window.scrollBy(0,200)");

			// selecting disable option in status drop down using select class
			WebElement dropdownElement1 = driver.findElement(By.xpath("//select[@id='addStatus']"));
			Select dropdown1 = new Select(dropdownElement1);
			dropdown1.selectByVisibleText("Disable");

			// logic to select the date from calendar(for future date selection)
			driver.findElement(By.xpath("//input[@id='datepickers']")).click();
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink1 = wait1.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td.ui-datepicker-today")));
			addLink1.click();

			Thread.sleep(2000);
			WebElement slider11 = driver.findElement(By.xpath("//*[@id=\"ui_tpicker_minute_datepickers\"]/a"));
			Actions actions21 = new Actions(driver);
			actions21.dragAndDropBy(slider11, 10, 15).perform();
			Thread.sleep(2000);
			// locating the date inside the calendar
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/button[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
			Thread.sleep(2000);
			Alert alert1211 = driver.switchTo().alert();
			Thread.sleep(2000);
			alert1211.accept();

			// Scroll down the page window using JavascriptExecutor
			JavascriptExecutor js19 = (JavascriptExecutor) driver;
			// Scroll down by a specific pixel value (e.g., 500 pixels)
			js19.executeScript("window.scrollBy(0,-200)");

			WebElement message1 = null;

			// switching the success case or failure case based on the text we get from the
			// element present(either success message, failure message)
			// this text is stored inside the message variable and switching the cases
			// accordingly
			try {
				Thread.sleep(2000);
				message1 = driver.findElement(By.xpath(
						"//td[normalize-space()='Addition Unsuccessful.'] | //td[@class='succmsg'] | //td[normalize-space()='Addition Unsuccessful. This Source Address and Dest IMSI combination already exists.']"));
				String messageText1 = message1.getText();
				System.out.println(messageText1);

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
				} else if (messageText1
						.equals("Addition Unsuccessful. This Source Address and Dest IMSI combination already exists.")) {
					// Handle success case
					System.out.println("Success: " + messageText1);
					Assert.assertEquals(messageText1,
							"Addition Unsuccessful. This Source Address and Dest IMSI combination already exists.",
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
			Thread.sleep(5000);
		}

	}

