package SMSC;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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

public class client_activation {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the browser
	public void setUp() {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		SMSC.client_activation.driver = driver; // Set the WebDriver instance

		String url = "https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp";
		driver.get(url);
		String pageTitle = driver.getTitle();
		System.out.println("Title of " + url + ": " + pageTitle);

		// Set implicit wait to 5 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//Login code
		driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
		driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
		driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
	}

	//existing client 'demo10' deletion code
	@Test(priority = 1)
	public void client_deletion() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		// Click on the "Client" link
		driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Client')]")).click();
		Thread.sleep(3000);

		// click on HTTP button
		driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();

		// Switch to the iframe
		WebElement iframe = driver
				.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
		driver.switchTo().frame(iframe);

		//dynamically locate the demo10 client in the table HTTP client table
		WebElement table1 = driver.findElement(
				By.xpath("//*[@id=\"pageDiv\"]/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table"));

		List<WebElement> rows = table1.findElements(By.tagName("tr"));//it will read the all the rows in the form of list and it will stored matchedrow variable
		WebElement matchedRow = null; // Variable to store the matched row
		List<String> value1 = new ArrayList<String>();
		System.out.println("Total rows: " + rows.size());//It will print the total number of rows present inside the table

		//logic to locate the client name inside the second column  of table and fetching that client by comparing each row clint name
		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));

			// Check if there is at least one column in the row
			if (columns.size() >= 2) {
				WebElement secondColumn = columns.get(1);
				String columnText = secondColumn.getText();

				System.out.println("Text in the second column: " + columnText);
				value1.add(columnText);

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (value1.contains("demo10")) {
					System.out.println("Value 'demo1' is matched");
					matchedRow = row; // Store the matched row
					break; // Stop the loop once the target row is found
				} else {
					System.out.println("Value 'demo1' is not matched");
				}
			}
		}
		Thread.sleep(3000);
		// Create a JavascriptExecutor object
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// Scroll down by pixel offset (e.g., 500 pixels)
		jsExecutor.executeScript("window.scrollBy(200,600)");

		// Perform actions on the matched row (if any)
		if (matchedRow != null) {
			// Example: Click the anchor tag in the matched row
			WebElement anchorInMatchedRow = matchedRow.findElement(By.tagName("a"));
			anchorInMatchedRow.click();
		} else {
			System.out.println("No row matched the criteria");
		}

		String xpath = "//img[contains(@name, 'plusminusAdvQuota')]";

		// Attempt to locate the element
		List<WebElement> elements = driver.findElements(By.xpath(xpath));

		// Perform actions on the element if found
		if (!elements.isEmpty()) {
			WebElement element = elements.get(0);
			element.click();
		} else {
			System.out.println("Element not found for dynamic suffix");
		}
		
		driver.findElement(By.cssSelector("input[value=' Delete ']")).click();
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		System.out.println("client deleted");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		//clicking HTTP link got to know whether client is deleted or not
		// Click on the "HTTP" link
		driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();
		Thread.sleep(3000);
	}
	
	
//client creation in HTTP 
	@Test(priority = 2)
	public void client_creation() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();//sitemap locator
		Thread.sleep(2000);
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

		// input parameters
		WebElement userNameInput = driver.findElement(By.xpath("//input[@name='userName']"));
		userNameInput.sendKeys("demo10");// username
		Thread.sleep(1000);
		WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
		passwordInput.sendKeys("demo10");// password
		Thread.sleep(1000);
		WebElement minThroughputInput = driver.findElement(By.xpath("//input[@name='throughput']"));
		minThroughputInput.sendKeys("1");// minThroughputInput
		Thread.sleep(1000);
		WebElement maxThroughputInput = driver.findElement(By.xpath("//input[@name='maxthroughput']"));
		maxThroughputInput.sendKeys("100");// maxThroughputInput
		Thread.sleep(1000);
		WebElement emailInput = driver.findElement(By.xpath("//input[@title='Enter valid email address']"));
		emailInput.sendKeys("support@tayana.in");// emailInput
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//*[@id=\"borderDivAdd\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[6]/td[2]/input"))
				.sendKeys("1234");// senderID
		Thread.sleep(2000);

		// logic to select node select
		WebElement dropdown1 = driver.findElement(By.xpath("//*[@id=\"NodeSelect\"]"));
		Thread.sleep(2000);
		Select select1 = new Select(dropdown1);
		select1.selectByIndex(0);// to select node1
		Thread.sleep(2000);

		// logic to select FDAfails
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement FDAfails = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='failureAction']")));
		FDAfails.click();
		Thread.sleep(2000);
		Select select12 = new Select(FDAfails);
		select12.selectByIndex(1);// to select retry
		Thread.sleep(2000);

		// logic to select user configuration
		WebElement dropdown2 = driver.findElement(By.xpath("//select[@title='Select User Configuration']"));
		Thread.sleep(2000);
		Select select2 = new Select(dropdown2);
		select2.selectByIndex(2);// to admin(admin)
		Thread.sleep(1000);

		// billing flag checkbox
		WebElement billingFlagCheckbox = driver.findElement(By.xpath("//input[@name='BillingFlag']"));
		billingFlagCheckbox.click();
		Thread.sleep(1000);
		//distListSupportCheckbox
		WebElement distListSupportCheckbox = driver.findElement(By.xpath("//input[@name='distListSupport']"));
		distListSupportCheckbox.click();
		Thread.sleep(1000);
		//distListSupportCheckbox
		WebElement cannedMsgSupport = driver.findElement(By.xpath("//input[@name='cannedMsgSupport']"));
		cannedMsgSupport.click();
		Thread.sleep(1000);
		//carryFwdSupport
		WebElement carryFwdSupport = driver.findElement(By.xpath("//input[@name='carryFwdSupport']"));
		carryFwdSupport.click();
		Thread.sleep(1000);

		// Switch back to the main window
		String parentWindow = driver.getWindowHandle();
        
		//logic to handle the expire date calendar 
		driver.findElement(By.xpath(
				"//*[@id=\"borderDivAdd\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[1]/table/tbody/tr[7]/td[2]/a"))
				.click();// calendar opening locator

		//switching to calendar popup from admin gui portal using title of the browser
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("Calendar")) {
				System.out.println("Switched to the calendar popup window");
				break;
			}
		}
        //wait statement till the proper calendar structure is visible
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement calendartable = wait3
				.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr/td/table")));
		calendartable.click();
		String amonth;
		do {
			driver.findElement(By.xpath("//img[@alt='next month']")).click();//locator for next button in calendar
			Thread.sleep(1000);
			amonth = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td[3]/font"))
					.getText();//locator for month and year text field from the calendar
		} while (!amonth.equals("July 2024"));

		driver.findElement(By.xpath("//font[normalize-space()='14']")).click();//locator for date in calendar
		Thread.sleep(3000);

		// Switch back to the parent window
		driver.switchTo().window(parentWindow);
		System.out.println("Switched back to the parent window");

		// Switch to the iframe
		WebElement iframe1 = driver
				.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
		driver.switchTo().frame(iframe1);
		
		driver.manage().window().maximize();
		resizeBrowser(driver,1800,1000);
		
	  
			 
		
		// Click on the "AddBasicButton"
		WebElement addBasicButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='AddBasicButton']")));
		addBasicButton.click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		

		// Get the text inside the alert
		String alertText = alert.getText();
		System.out.println("Alert Text: " + alertText);

		// Check the text of the alert and perform actions accordingly
		if (alertText.contains("Are you sure? you want to add this Client ?")) {
			// Code to handle the "Are you sure you want to add this" alert
			Thread.sleep(2000);
			alert.accept();
			// successful message
			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String actualOutput = errorMessageElement.getText();
			System.out.println(actualOutput);
			String expectedOutput = "Addition Successful";
			Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='CloseBasicButton']")).click();
			driver.switchTo().defaultContent();
			Thread.sleep(10000);
			
		} else if (alertText.contains("Max Client Count Reached - Not able to add Additional clients")) {
			// Code to handle the "Max Client Count Reached" alert
			System.out.println("Handling 'Max Client Count Reached' alert");
			Thread.sleep(2000);
			alert.accept();
		}
	}

	private void resizeBrowser(WebDriver driver2, int width, int height ) {
		driver.manage().window().setSize(new org
				.openqa.selenium.Dimension(width, height));
	// TODO Auto-generated method stub
	
}

	//code to add quota and daily allocated quota to newly created client
	@Test(priority = 3)
	public void client_activation() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();//locator for sitemap
		Thread.sleep(2000);
		// Click on the "Client" link
		driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Client')]")).click();
		Thread.sleep(3000);

		// click on HTTP button
		driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();

		// Switch to the iframe
		WebElement iframe = driver
				.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
		driver.switchTo().frame(iframe);

		
		//dynamically locate the demo10 client in the table HTTP client table
		WebElement table1 = driver.findElement(
				By.xpath("//*[@id=\"pageDiv\"]/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table"));

		List<WebElement> rows = table1.findElements(By.tagName("tr"));//it will read the all the rows in the form of list and it will stored matchedrow variable
		WebElement matchedRow = null; // Variable to store the matched row
		List<String> value1 = new ArrayList<String>();
		System.out.println("Total rows: " + rows.size());//It will print the total number of rows present inside the table

		//logic to locate the client name inside the second column  of table and fetching that client by comparing each row clint name
		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));

			// Check if there is at least one column in the row
			if (columns.size() >= 2) {
				WebElement secondColumn = columns.get(1);
				String columnText = secondColumn.getText();

				System.out.println("Text in the second column: " + columnText);
				value1.add(columnText);

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (value1.contains("demo10")) {
					System.out.println("Value 'demo10' is matched");
					matchedRow = row; // Store the matched row
					break; // Stop the loop once the target row is found
				} else {
					System.out.println("Value 'demo10' is not matched");
				}
			}
		}
		Thread.sleep(3000);
		
		//logic to scrolldown the windows
		// Scroll down by pixel offset (e.g., 500 pixels)
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,300)");
		
		
		// Perform  click actions on the matched row (if any)
		if (matchedRow != null) {
			// Example: Click the anchor tag in the matched row
			WebElement anchorInMatchedRow = matchedRow.findElement(By.tagName("a"));
			anchorInMatchedRow.click();
		} else {
			System.out.println("demo10 row is not found");
		}

		//logic to locate the quota plus button
		String xpath = "//img[contains(@name, 'plusminusAdvQuota')]";
		// Attempt to locate the element
		List<WebElement> elements = driver.findElements(By.xpath(xpath));

		// Perform  click action on the element if found
		if (!elements.isEmpty()) {
			WebElement element = elements.get(0);
			element.click();
			System.out.println("quota is clicked");
		} else {
			System.out.println("quota plus button clicked");
		}
		
		// Create a JavascriptExecutor object
		JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
		// Scroll down by pixel offset (e.g., 500 pixels)
		jsExecutor1.executeScript("window.scrollBy(100,400)");
        
		//logic to locate the daily allocated quota text field inside the quota
		// Dynamic XPath for input with name 'quotaMod' followed by a number
		String dynamicQuotaXPath = "//input[contains(@name, 'quotaMod')]";// 
		// Attempt to locate the element
		List<WebElement> quotaElements = driver.findElements(By.xpath(dynamicQuotaXPath));
		// Perform click action on the element if found
		if (!quotaElements.isEmpty()) {
			WebElement quotaElement = quotaElements.get(0);
			quotaElement.clear();
			quotaElement.sendKeys("500");
			System.out.println("daily allocated quota value entered");
		} else {
			System.out.println("daily allocated quota field  not found");
		}

		Thread.sleep(3000);
		
		//logic to locate the modify button inside the quota plus button
		// Dynamic XPath for input with name 'ModifyQuotaModifyButton' followed by a number
		String dynamicModifyQuotaButtonXPath = "//input[contains(@name, 'ModifyQuotaModifyButton')]";
		// Attempt to locate the element
		List<WebElement> modifyButtonElements = driver.findElements(By.xpath(dynamicModifyQuotaButtonXPath));
		// Perform actions on the element if found
		if (!modifyButtonElements.isEmpty()) {
			WebElement modifyButtonElement = modifyButtonElements.get(0);
			modifyButtonElement.click();
			System.out.println("quota modification is done");
		} else {
			System.out.println("modify button not found in quota ");
		}
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();//click on 'ok' button  //confirmation popup handling
		Thread.sleep(3000);	
		
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Modification Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);
		
	//Basic modify button
		// Dynamic XPath for input with name 'ModifyBasicButton' followed by a number
		String dynamicModifyBasicButtonXPath = "//input[contains(@name, 'ModifyBasicButton')]";
		// Attempt to locate the element
		List<WebElement> modifyBasicButtonElements = driver.findElements(By.xpath(dynamicModifyBasicButtonXPath));
		// Perform actions on the element if found
		if (!modifyBasicButtonElements.isEmpty()) {
		    WebElement modifyBasicButtonElement = modifyBasicButtonElements.get(0);
		    modifyBasicButtonElement.click();
		    System.out.println("Basic modification is done");
		} else {
		    System.out.println("modify button not found in demo10");
		}
		Alert alert1 = driver.switchTo().alert();
		String alertmessage =alert1.getText();
		System.out.println(alertmessage);
		Thread.sleep(2000);
		alert1.accept();//click on 'ok' button  //confirmation popup handling
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		// click on HTTP button
		driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();
	}


	@Test(priority = 4)
	public void SMS_sending() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();// sitemap locator
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui locator
		Thread.sleep(2000);

		//switching to BMPportal window or popup
		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("Bulk Messaging Platform")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
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

				//wait statement to display the calendar structure properly
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addLink2 = wait2
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]")));
				addLink2.click();
				//locator for getting the month and year text from the calendar
				String amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth2);

				while (!(amonth2.equals("December 2023"))) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();//next button locator
					amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();//locator for getting the month and year text from the calendar
					System.out.println(amonth2);

				}
				driver.findElement(By.xpath("//a[normalize-space()='29']")).click();//locator for date inside the calendar
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				// logic to select date from calendar date in dispatch time
				driver.findElement(By.xpath("//tr[@id='DateTr']//img[@title='Click to Choose Date']")).click();// calendar opening
				//wait statement to display the calendar structure properly
				WebDriverWait wait22 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addLink22 = wait22
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
				addLink22.click();
				//locator for getting the month and year text from the calendar
				String amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth22);

				while (!(amonth22.equals("December 2023"))) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();//next button locator
					amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();//locator for getting the month and year text from the calendar
					System.out.println(amonth22);

				}
				driver.findElement(By.xpath("//a[normalize-space()='30']")).click();//locator for date inside the calendar
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();//send button
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value=' Yes ']")).click();//confirmation locator( OK button)

				WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String s1 = errorMessageElement.getText();
				System.out.println(s1);
				int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");
				// Trim the string to keep only the part until the specified index
				String trimmedString = s1.substring(0, endIndex);
				System.out.println(trimmedString);
				String expectedOutput = "Msg sent to : [97512345678 ]";
				Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);
			}
		}
	}
	
	
	@Test(priority = 5)
	public void retrylink() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();// sitemap locator
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Retry')]")).click(); // retry link in system config
		Thread.sleep(2000);
		resizeBrowser2(driver,1800,1000);
		driver.findElement(By.xpath("//a[normalize-space()='Retry Pattern']")).click(); // retry pattern locator of retry  in system config
		Thread.sleep(2000);
		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//*[@id=\"addLink\"]")).click(); //add button in  retry link of system config
	
		
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/input"))
				.sendKeys("demo10"); // locator for retry name 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[4]/input"))
				.sendKeys("2"); // locator for max number of retries
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/input[1]"))
				.click();//locator for add
		Alert alert12 = driver.switchTo().alert();
		String alertmessage =alert12.getText();
		System.out.println(alertmessage);
		Thread.sleep(2000);
		alert12.accept();
		Thread.sleep(5000);
	}
		private void resizeBrowser2(WebDriver driver2, int width, int height ) {
			driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
		// TODO Auto-generated method stub
}}
