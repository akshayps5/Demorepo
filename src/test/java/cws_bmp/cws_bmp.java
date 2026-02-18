package cws_bmp;






	import java.io.File;
	import java.time.Duration;
	import java.time.LocalDate;
	import java.util.List;
	import java.util.Random;

	import javax.xml.xpath.XPath;

	import org.checkerframework.checker.index.qual.SearchIndexBottom;
	//import org.hamcrest.Description;
	import org.openqa.selenium.By;
	import org.openqa.selenium.By.ByXPath;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import java.util.Random;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	//import gnu.cajo.Cajo.Searchable;
	//import io.netty.util.collection.ShortCollections;

	public class cws_bmp {

		public static WebDriver driver;
		private String createdShortCode1;
		private String createdShortCode2;

		@BeforeClass
		public void setUp() throws InterruptedException {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			int maxAttempts = 5;
		    boolean loginPageLoaded = false;

		    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
		        driver.get(dataprovider_java1.AdminIP);

		        try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		            // Wait for username field and send value
		            WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='User ID']")));
		            userField.sendKeys(dataprovider_java1.Username);

		            // Wait for password field and send value
		            WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
		            passField.sendKeys(dataprovider_java1.Password);

		            // Wait for login button and click
		            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		            loginBtn.click();

		            // Wait for post-login element (replace with dashboard locator if available)
		            Thread.sleep(2000);

		            loginPageLoaded = true;
		            break;  // exit loop if successful
		        } catch (TimeoutException e) {
		            System.out.println("Login form not loaded. Retrying attempt " + attempt + "...");
		            Thread.sleep(2000);  // wait before retry
		        }
		    }

		    if (!loginPageLoaded) {
		        System.out.println("Failed to load login page after " + maxAttempts + " attempts.");
		        throw new RuntimeException("Login failed after multiple attempts.");
		    }
		    //driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/div/div/div/div[2]/div/div[2]/ul/li[2]/a")).click();
		}	
		

		public void deleteRecordIfExists(String recordName, By searchBox, By deleteIcon) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			driver.findElement(searchBox).clear();
			driver.findElement(searchBox).sendKeys(recordName);
			List<WebElement> records = driver.findElements(deleteIcon);

			if (!records.isEmpty()) {
				records.get(0).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']"))).click();
				System.out.println("Record '" + recordName + "' deleted successfully.");
			} else {
				System.out.println("Record '" + recordName + "' not found. Skipping delete.");
			}
		}

		@Test(priority = 1, enabled = true)
		public void Systemkernal() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();// sysytem
			Thread.sleep(2000);
			WebElement Invalidmsg = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[21]"));
			Thread.sleep(2000);

			// Clear old text
			Invalidmsg.clear();

			// Enter new test input
			Invalidmsg.sendKeys(dataprovider_java1.Invalid_msg);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement modifyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
			        By.xpath("//button[contains(text(),'Modify')]")));  // change text accordingly
			Thread.sleep(3000);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", modifyBtn);
			Thread.sleep(3000);
			modifyBtn.click();
			driver.findElement(By.xpath("(//button[normalize-space()='Yes'])[1]")).click();
			Thread.sleep(2000);
			WebElement Verification = driver.findElement(By.xpath("//h2[@id='swal2-title']"));
			String text = Verification.getText();
			Thread.sleep(3000);
			String verifytext = "Kernel Modified successfully";
			if (text.equals(verifytext))
				System.out.println("the message is modified successfully");
			Thread.sleep(2000);

			String actualMessage = driver
					.findElement(By.xpath("(//h2[normalize-space()='Kernel Modified successfully'])[1]")).getText();
			String expectedMessage = "Kernel Modified successfully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("This is 1 script");
				Assert.fail();
			}
			Thread.sleep(2000);

		}

		@Test(priority = 2, enabled = true)
		public void TimeZone() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click(); // sysytem
			driver.findElement(By.xpath("//a[normalize-space()='TimeZone']")).click(); // TimeZone
			Thread.sleep(1000);

			// ------------------Create the new Time Zone ---------------//

			// Click Add button
			driver.findElement(By.xpath("//*[local-name()='svg' and @data-icon='plus']")).click();

			// --- Create telecom-style TimeZone name ---
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			WebElement TimeZonen_Name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			TimeZonen_Name.sendKeys(dataprovider_java1.TimeZone_Search);
			Thread.sleep(1000);
			driver.findElement(By.id("TSSGUI_SelectFieldStyle")).click();
			driver.findElement(By.xpath("//li[text()=4]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[3]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@title='23']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);

			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "TimeZone added successfully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the TimeZone test script was failed");
				Assert.fail();
			}

			Thread.sleep(2000);
			deleteRecordIfExists(dataprovider_java1.TimeZone_Search, By.xpath("//input[@placeholder='Search']"),
					By.xpath("(//*[name()='path'][@fill='currentColor'])[22]"));

			Thread.sleep(2000);

			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "TimeZone deleted successfully";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the TimeZone test script was failed");
				Assert.fail();
			}

		}

		@Test(priority = 3, enabled = true)
		public void HolidayList1() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click(); // sysytem
			driver.findElement(By.xpath("//a[@title='HolidayList']")).click();
			Thread.sleep(2000);
			// ------------------Create the new Holidaylist--------------//
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			driver.findElement(By.xpath("//fieldset[1]//div[1]//i[1]")).click();
			// Get current day of month (1–31)
			String currentDay = String.valueOf(LocalDate.now().getDayOfMonth());

			// Wait for calendar to be visible (if needed)
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

			// Click the current date in the calendar
			String currentDay1 = String.valueOf(LocalDate.now().getDayOfMonth());

			// Click only enabled (not disabled) current-month date
			WebElement dateElement = driver.findElement(By.xpath(
			    "//td[not(contains(@class,'p-datepicker-other-month'))]//span[normalize-space()='" + currentDay1 + "' and not(contains(@class,'p-disabled'))]"
			));

			// Use JavaScript click to avoid interception issues
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateElement);
			WebElement HolidayList_Name = driver.findElement(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']"));
			HolidayList_Name.sendKeys(dataprovider_java1.HolidayList_search);
			driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);

			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "HolidayList added successfully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the HolidayList1 got test script3 was failed");
				Assert.fail();
			}
			Thread.sleep(2000);

			// --------------deleteing------------//
			deleteRecordIfExists(dataprovider_java1.HolidayList_search,
					By.xpath("//input[@class='p-inputtext p-component']"),
					By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]"));
			Thread.sleep(2000);

			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "HolidayList deleted successfully";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the HolidayList1 test script3 was failed");
				Assert.fail();
			}
			Thread.sleep(2000);

		}

		@Test(priority = 4, enabled = true)
		public void Connection() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
			driver.findElement(By.xpath("//a[@title='Connection']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[2]")).click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("10");
			driver.findElement(By.xpath("//span[2]//input[1]")).sendKeys("0");
			driver.findElement(By.xpath("//span[3]//input[1]")).sendKeys("3");
			driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("22");
			// Locate the element
			WebElement port = driver.findElement(By.xpath("//input[@name='inputField']"));

			// Generate random 4-digit number
			Random rand1 = new Random();
			int randomNum = 1000 + rand1.nextInt(9000); // gives number between 1000–9999

			// Send it
			port.sendKeys(String.valueOf(randomNum));

			System.out.println("Random 4-digit value entered: " + randomNum);

			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);
			// ------------------check the addition condition------///
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Connection added successfully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Connection test script4 was failed");
				Assert.fail();
			}
			Thread.sleep(2000);

			deleteRecordIfExists(String.valueOf(randomNum),
	                By.xpath("//input[@class='p-inputtext p-component']"),
	                By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]"));
			Thread.sleep(2000);

			// -----------------------cheking the deleting condition------//
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Connection deleted successfully";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Connection test script4 was failed");
				Assert.fail();
			}

			Thread.sleep(2000);

		}

		@Test(priority = 5, enabled = true)
		public void Routing() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
			driver.findElement(By.xpath("//a[@title='Routing']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			name.sendKeys(dataprovider_java1.Routing_Real_name);

			// Generate 8-digit random number starting with 9 or 7
			Random rand = new Random();
			int firstDigit = rand.nextBoolean() ? 9 : 7; // choose 9 or 7
			int remaining = 1000000 + rand.nextInt(8999999); // ensures 7 remaining digits not starting with 0
			String randomAddress = firstDigit + String.valueOf(remaining);

			// ---------------- ADDITION PART ----------------
			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(randomAddress);
			System.out.println("Random 8-digit Routing Address entered: " + randomAddress);

			WebElement GT = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[3]"));
			GT.sendKeys(dataprovider_java1.Routing_GT);

			WebElement point_code = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			point_code.sendKeys(dataprovider_java1.Routing_point_code);

			WebElement Rule_Desription = driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']"));
			Rule_Desription.sendKeys(dataprovider_java1.Routing_Description);

			driver.findElement(By.xpath("//div[@class='content-body']//button[1]")).click();

			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);

			// ------------------check the addition condition------///
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			   } 
			else {
				
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Routing test script5 was failed");
				Assert.fail();
			     }

		
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
			driver.findElement((By.xpath("(//*[name()='svg'][contains(@role,'img')])[6]"))).click();

			WebElement deleting_address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			deleting_address.sendKeys(randomAddress);
			System.out.println("Deleting record for Routing Address: " + randomAddress);
			
			driver.findElement(By.xpath("//div[@class='form-group col-md-4 mt-2']//button[@type='button']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody/tr[@role='row']/td//*[name()='svg'][2]/*[name()='path'][1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			// -----------------------cheking the deleting condition------//
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion successful";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Routing test script5 was failed");
				Assert.fail();
			}

			Thread.sleep(2000);
		}

		@Test(priority = 6, enabled = true)
		public void Transfer_points() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
			driver.findElement(By.xpath("//a[@title='Transfer Points']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@placeholder='Select Options'])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[text()='Pankajrc26']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@placeholder='Select Options'])[2]")).click();
			driver.findElement(By.xpath("//li[@title='afsey']")).click();
			Thread.sleep(1000);
			// ------number of points--//
			WebElement points = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[3]"));
			points.sendKeys(dataprovider_java1.Transfer_Poits);
			driver.findElement(By.xpath("//button[normalize-space()='Transfer']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);

			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Transferred Successfully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Transfer_points test script6 was failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 7, enabled = true)
		public void GSM_Error() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
			driver.findElement(By.xpath("//a[@title='GSM Error']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//tbody/tr[3]/td[7]/div[1]//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]"))
					.click();
			driver.findElement(By.xpath("//div[8]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[text()=5]")).click();
			driver.findElement(
					By.xpath("//div[contains(@class,'content-body')]//section[contains(@class,'content')]//button[1]"))
					.click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);

			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Modification successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the GSM_Error test script7 was failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 8, enabled = true)
		public void Black_list() throws InterruptedException {

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // ------------------- NAVIGATION -------------------

		    driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		    driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
		    driver.findElement(By.xpath("//a[normalize-space()='BlackList']")).click();
		    Thread.sleep(2000);

		    // ------------------- ADD BLACKLIST -------------------

		    // Click Add (+) button
		    driver.findElement(
		            By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")
		    ).click();

		    // Generate 7-digit MSISDN
		    Random rand = new Random();
		    int msisdnNumber = 1000000 + rand.nextInt(9000000);
		    String msisdn = String.valueOf(msisdnNumber);

		    System.out.println("Generated 7-digit MSISDN: " + msisdn);

		    // Enter MSISDN (WITHOUT country code)
		    WebElement addMsisdnField = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(
		                    By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		            )
		    );
		    addMsisdnField.sendKeys(msisdn);

		    // Click Add
		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);

		    // Verify success message
		    String actualMessage =
		            driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();

		    String expectedMessage = "MSISDN added successfully";

		    if (actualMessage.equals(expectedMessage)) {
		        System.out.println("Add Success: " + actualMessage);
		    } else {
		        System.out.println("Add Failed. Message: " + actualMessage);
		    }

		    // ------------------- DELETE BLACKLIST -------------------

		    // Click delete icon
		    WebElement deleteIcon = wait.until(
		            ExpectedConditions.elementToBeClickable(
		                    By.xpath("(//*[name()='path'][@fill='currentColor'])[6]/parent::*")
		            )
		    );
		    deleteIcon.click();

		    // Wait for Full MSISDN field (already contains 248)
		    WebElement deleteMsisdnField = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(
		                    By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		            )
		    );

		    // Clear and enter full MSISDN (248 + number)
		    deleteMsisdnField.clear();
		    String fullMsisdn = "248" + msisdn;
		    deleteMsisdnField.sendKeys(fullMsisdn);

		    System.out.println("Deleting MSISDN: " + fullMsisdn);

		    // Click Search
		    driver.findElement(By.xpath("//*[@id=\"confirmButton\"]")).click();

		    // Select result
		    driver.findElement(By.xpath("//button[normalize-space()='CheckAll']")).click();

		    // Confirm delete
		    driver.findElement(By.xpath("(//button[@id='confirmButton'])[3]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);
		}


		@Test(priority = 9, enabled = true)
		public void Keyword() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Keyword']")).click();
			Thread.sleep(2000);
			WebElement value = driver.findElement(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']"));

			// Clear existing text
			value.clear();

			// Enter new test value
			value.sendKeys(dataprovider_java1.Keyword_value);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(text(),'Modify')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);

			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Modification successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Keyword test script9 was failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 10, enabled = true)
		public void Download() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
			driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[normalize-space()='Client Details']/..//td[2]//a")).click();

			Thread.sleep(2000);
		}

		@Test(priority = 11, enabled = true)
		public void Retry() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[7]")).click();
			driver.findElement(By.xpath("//a[normalize-space()='Retry']")).click();
			Thread.sleep(1000);
			// --------------creating the retry-----------------//
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			name.sendKeys(dataprovider_java1.Retry_name);
			WebElement number = driver.findElement(By.xpath("(//input[@placeholder='Enter Max No Of Retry'])[1]"));
			number.sendKeys(dataprovider_java1.max_no_retry);

			driver.findElement(By.xpath("//button[@id='addButton']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(1000);

			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Added Successfully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Retry test script 11 was failed");
				Assert.fail();
			}

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Retry_name, By.xpath("//input[@class='p-inputtext p-component']"),
					By.xpath("//td[3]//*[name()='svg'][2]/*[name()='path'][1]"));

			Thread.sleep(2000);
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion Successful.";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Retry test script 11 was failed");
				Assert.fail();
			}

			Thread.sleep(2000);
		}

		// =============this is for the pool inbox sc================//

		

		// -------------------Sender id------------//

		@Test(priority = 12, enabled = true)
		public void Sender_id() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[19]")).click();
			Thread.sleep(2000);	
			driver.findElement(By.xpath("//a[@title='Sender Id']")).click();
			Thread.sleep(2000);
			// ---------------creating the inbox sc withoout assign the
			// client-------------//
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			WebElement short_code = driver.findElement(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']"));
			short_code.sendKeys(dataprovider_java1.Pool_Sender_id);

			driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			// --------------------creating the client with assing the client----------//
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			WebElement short_code1 = driver.findElement(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']"));
			short_code1.sendKeys(dataprovider_java1.Pool_Sender_id_ass_cli);
			driver.findElement(By.xpath("//label[normalize-space()='Assign to client']")).click();
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@title='Pankajrc26']")).click();
			driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);

		}

		@Test(priority = 13, enabled = true)
		public void Sender_id_deleting() throws InterruptedException {

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // -------- Navigation --------
		    driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		    driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[19]")).click();
		    driver.findElement(By.xpath("//a[@title='Sender Id']")).click();

		    Thread.sleep(2000);

		    // -------- Click Delete Icon --------
		    driver.findElement(
		            By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]")
		    ).click();

		    // -------- Enter Sender ID --------
		    WebElement senderIdField =
		            wait.until(ExpectedConditions.visibilityOfElementLocated(
		                    By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		            ));
		    senderIdField.sendKeys(dataprovider_java1.Pool_Sender_id);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    // -------- IF CONDITION : Check record exists --------
		    if (driver.findElements(By.xpath("//*[text()='No Records found']")).size() > 0) {
		        System.out.println("Sender ID not found, skipping delete and moving forward");
		        driver.findElement(By.xpath("//*[@id=\"cancelButton\"]")).click();
		        return; // move forward
		    }

		    // -------- Delete if record exists --------
		    driver.findElement(By.xpath("//button[normalize-space()='CheckAll']")).click();
		    driver.findElement(By.xpath("(//button[@id='confirmButton'])[3]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);

		    // -------- Delete Assigned Client Sender ID --------
		    driver.findElement(
		            By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]")
		    ).click();

		    WebElement senderIdField2 =
		            wait.until(ExpectedConditions.visibilityOfElementLocated(
		                    By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		            ));
		    senderIdField2.sendKeys(dataprovider_java1.Pool_Sender_id_ass_cli);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    // -------- IF CONDITION --------
		    if (driver.findElements(By.xpath("//*[text()='No Records found']")).size() > 0) {
		        System.out.println("Assigned Sender ID not found, skipping delete");
		        return;
		    }

		    driver.findElement(By.xpath("//button[normalize-space()='CheckAll']")).click();
		    driver.findElement(By.xpath("(//button[@id='confirmButton'])[3]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);
		}

		
		@Test(priority = 14, enabled = true)
		public void Inbox_sc_Add_Deallocate_Delete() throws InterruptedException {

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // ---------------- NAVIGATION ----------------
		    driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		    driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[19]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//a[normalize-space()='Inbox SC']")).click();
		    Thread.sleep(2000);

		    Random rand = new Random();

		    // ================= ADD INBOX SC (WITHOUT CLIENT) =================
		    String shortCodeWithoutClient = String.valueOf(1000 + rand.nextInt(9000));
		    System.out.println("SC Without Client: " + shortCodeWithoutClient);

		    driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(shortCodeWithoutClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);

		    // ================= ADD INBOX SC (WITH CLIENT) =================
		    String shortCodeWithClient = String.valueOf(1000 + rand.nextInt(9000));
		    System.out.println("SC With Client: " + shortCodeWithClient);

		    driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
		    Thread.sleep(1000);

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(shortCodeWithClient);

		    driver.findElement(By.xpath("//label[normalize-space()='Assign to client']")).click();
		    driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
		    driver.findElement(By.xpath("//li[@title='Pankajrc26']")).click();

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);

		    // ================= DELETE INBOX SC (WITHOUT CLIENT) =================
		    driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]")).click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(shortCodeWithoutClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    if (driver.findElements(By.xpath("//*[text()='No Records found']")).size() == 0) {
		        driver.findElement(By.xpath("//button[normalize-space()='CheckAll']")).click();
		        driver.findElement(By.xpath("(//button[@id='confirmButton'])[3]")).click();
		        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		        System.out.println("Deleted SC Without Client");
		    }

		    Thread.sleep(2000);

		    // ================= DEALLOCATE SC (WITH CLIENT) =================
		    driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[6]")).click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("//input[@id='TSSGUI_SelectFieldStyle']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//li[@title='Deallocate']")).click();
		    Thread.sleep(2000);

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(shortCodeWithClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    // -------- DYNAMIC RADIO BUTTON CLICK --------
		    WebElement radioLabel = wait.until(
		            ExpectedConditions.elementToBeClickable(
		                    By.xpath("//label[normalize-space()='" + shortCodeWithClient + "']")
		            )
		    );
		    radioLabel.click();

		    driver.findElement(By.xpath("//div[@class='card-body align-items-center py-8 ']//div[1]//button[1]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		    System.out.println("Client deallocated successfully");

		    Thread.sleep(2000);

		    // ================= DELETE INBOX SC (WITH CLIENT) =================
		    driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]")).click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(shortCodeWithClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    if (driver.findElements(By.xpath("//*[text()='No Records found']")).size() == 0) {
		        driver.findElement(By.xpath("//button[normalize-space()='CheckAll']")).click();
		        driver.findElement(By.xpath("(//button[@id='confirmButton'])[3]")).click();
		        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		        System.out.println("Deleted SC With Client");
		    }

		    Thread.sleep(2000);
		}




		// --------pull shrot code---------//

		@Test(priority = 15, enabled = true)
		public void Pull_Short_Code_Add_And_Delete() throws InterruptedException {

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // ---------------- NAVIGATION ----------------
		    driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
		    driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[19]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//a[@title='Pull Short Code']")).click();
		    Thread.sleep(2000);

		    Random rand = new Random();

		    // ================= ADD PULL SC (WITHOUT CLIENT) =================
		    String pullSCWithoutClient = String.valueOf(1000 + rand.nextInt(9000));
		    System.out.println("Pull SC Without Client: " + pullSCWithoutClient);

		    driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(pullSCWithoutClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);

		    // ================= ADD PULL SC (WITH CLIENT) =================
		    String pullSCWithClient = String.valueOf(1000 + rand.nextInt(9000));
		    System.out.println("Pull SC With Client: " + pullSCWithClient);

		    driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(pullSCWithClient);

		    driver.findElement(By.xpath("//label[normalize-space()='Assign to client']")).click();
		    driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
		    driver.findElement(By.xpath("//li[@title='Pankajrc26']")).click();

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		    Thread.sleep(2000);

		    // ================= DELETE PULL SC (WITHOUT CLIENT) =================
		    driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]")).click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(pullSCWithoutClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    if (driver.findElements(By.xpath("//*[text()='No Records found']")).size() == 0) {
		        driver.findElement(By.xpath("//button[normalize-space()='CheckAll']")).click();
		        driver.findElement(By.xpath("(//button[@id='confirmButton'])[3]")).click();
		        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		        System.out.println("Deleted Pull SC Without Client");
		    }

		    Thread.sleep(2000);
		    
		    // ================ DEALLOCATE PULL SC FROM CLIENT ===============
		    
		    driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[6]")).click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("//input[@id='TSSGUI_SelectFieldStyle']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//li[@title='Deallocate']")).click();
		    Thread.sleep(2000);

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(pullSCWithClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    // -------- DYNAMIC RADIO BUTTON CLICK --------
		    WebElement radioLabel = wait.until(
		            ExpectedConditions.elementToBeClickable(
		                    By.xpath("//label[normalize-space()='" + pullSCWithClient + "']")
		            )
		    );
		    radioLabel.click();

		    driver.findElement(By.xpath("//div[@class='card-body align-items-center py-8 ']//div[1]//button[1]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		    System.out.println("Client deallocated successfully");

		    Thread.sleep(2000);
		    

		    // ================= DELETE PULL SC (WITH CLIENT) =================
		    driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg'][2]/*[name()='path'][1]")).click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")
		    )).sendKeys(pullSCWithClient);

		    driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
		    Thread.sleep(2000);

		    if (driver.findElements(By.xpath("//*[text()='No Records found']")).size() == 0) {
		        driver.findElement(By.xpath("//button[normalize-space()='CheckAll']")).click();
		        driver.findElement(By.xpath("(//button[@id='confirmButton'])[3]")).click();
		        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		        System.out.println("Deleted Pull SC With Client");
		    }

		    Thread.sleep(2000);
		}

		// ----------PAckage Rate Profile Creating -------------//

//		@Test(priority = 18, enabled = true )
//		public void Rate_profile() throws InterruptedException {
//			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
//			element0.click();
//			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[8]")).click();
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
//			Thread.sleep(2000);
//			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
//			name.sendKeys(dataprovider_java1.Profile_name);
	//
//			WebElement profile_desription = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
//			profile_desription.sendKeys(dataprovider_java1.Rate_profile_description);
	//
//			driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
//			driver.findElement(By.xpath("(//fieldset[@id='TSSGUI_InputTextFieldSetStyle'])[2]")).sendKeys("1");
	//
//			driver.findElement(By.xpath("(//span[@id='basic-addon1'])[2]")).click();
//			driver.findElement(By.xpath("(//fieldset[@id='TSSGUI_InputTextFieldSetStyle'])[3]")).sendKeys("1");
	//
//			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
//			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
//			Thread.sleep(1000);
	//
//			deleteRecordIfExists(dataprovider_java1.Profile_name, By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
//					By.xpath("//td[contains(@role,'cell')]//div//*[name()='svg'][2]/*[name()='path'][1]"));
//			Thread.sleep(2000);
	//
//		}

//		@Test(priority = 19, enabled = true )
//		public void Client_creation_prepaid() throws InterruptedException {
	//////////////////////////////////////////////////////senderID//////////////////////////////////////////////
//			// (Senderid creation)
//			driver.findElement(By.cssSelector("li.mb-2:nth-child(10) > a:nth-child(1)")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.cssSelector(".nav-tabs > li:nth-child(3) > a:nth-child(1)")).click();
//			Thread.sleep(2000);
//			WebElement icon = driver.findElement(By.xpath("//div[@class='button-container']//*[name()='svg']"));
//			Actions actions = new Actions(driver);
//			actions.moveToElement(icon).click().perform();
//			Thread.sleep(2000);
//			String uniqueSenderId = "TST" + new Random().nextInt(100000); // e.g., TST38724
//			driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys(uniqueSenderId);
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//i[@title='Site Map']")).click();
//			Thread.sleep(2000);
	//
	//////////////////////////////////////////////////////senderID//////////////////////////////////////////////
//			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
//			element0.click();
//			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[9]")).click();
//			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
//			Thread.sleep(2000);
//			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
//			name.sendKeys(dataprovider_java1.Client_name);
	//
//			WebElement password = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
//			password.sendKeys(dataprovider_java1.Client_pass);
	//
//			WebElement Min_throughput = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[3]"));
//			Min_throughput.sendKeys(dataprovider_java1.min_throughput);
	//
//			WebElement Max_throughput = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
//			Max_throughput.sendKeys(dataprovider_java1.max_throughput);
	//
//			WebElement Email = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[7]"));
//			Email.sendKeys(dataprovider_java1.Client_Email);
	//
//			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
//			driver.findElement(By.xpath("(//ul[@id='tss-optionList'])[1]//li[3]")).click();
	//
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[4]")).click();
//			driver.findElement(By.xpath("(//li[@title='Prepaid'])[1]")).click();
	//
//			WebElement Billing_number = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[8]"));
//			Billing_number.sendKeys(dataprovider_java1.Billing_no);
	//
//			WebElement Account_id = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[9]"));
//			Account_id.sendKeys(dataprovider_java1.Account_id);
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[18]")).click();
//			driver.findElement(By.xpath("(//ul[@id='tss-optionList'])[1]//li[1]")).click();
	//
//			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
//			driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
//		}

//		@Test(priority = 20, enabled = true )
//		public void Client_Creation_postpaid() throws InterruptedException {
	//
//			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
//			element0.click();
//			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[9]")).click();
//			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
//			Thread.sleep(2000);
//			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
//			name.sendKeys(dataprovider_java1.Client_name_postpaid);
	//
//			WebElement password = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
//			password.sendKeys(dataprovider_java1.Client_pass_postpaid);
	//
//			WebElement Min_throughput = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[3]"));
//			Min_throughput.sendKeys(dataprovider_java1.min_throughput_postpaid);
	//
//			WebElement Max_throughput = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
//			Max_throughput.sendKeys(dataprovider_java1.max_throughput_postpaid);
	//
//			WebElement Email = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[7]"));
//			Email.sendKeys(dataprovider_java1.Client_Email);
	//
//			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
//			driver.findElement(By.xpath("(//ul[@id='tss-optionList'])[1]//li[3]")).click();
	//
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[4]")).click();
//			driver.findElement(By.xpath("(//li[@title='Postpaid'])[1]")).click();
	//
//			WebElement Billing_number = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[8]"));
//			Billing_number.sendKeys(dataprovider_java1.Billing_no_postpaid);
	//
//			WebElement Account_id = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[9]"));
//			Account_id.sendKeys(dataprovider_java1.Account_id_postpaid);
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[18]")).click();
//			driver.findElement(By.xpath("(//ul[@id='tss-optionList'])[1]//li[1]")).click();
	//
//			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
//			driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
//			Thread.sleep(2000);
//		}

		@Test(priority = 16, enabled = true )
		public void System_push() throws InterruptedException {

			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[29]")).click();

			// -------yearly------//
			driver.findElement(By.xpath("//button[normalize-space()='Yearly']")).click();
			driver.findElement(By.xpath("//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//span[normalize-space()='2025']")).click();
			driver.findElement(By.xpath("//div[@class='d-flex justify-content-end tss-pull-right']//button[@type='button']")).click();
			Thread.sleep(2000);

			List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
			System.out.println("This message for the System_Push");
			for (int i = 1; i <= rows.size(); i++) {
				String Application_name = driver.findElement(By.xpath("(//table/tbody/tr/td[1])[" + i + "]")).getText();
				String Recived = driver.findElement(By.xpath("(//table/tbody/tr/td[2])[" + i + "]")).getText();

				String Accepted = driver.findElement(By.xpath("(//table/tbody/tr/td[3])[" + i + "]")).getText();
				String Rejected = driver.findElement(By.xpath("(//table/tbody/tr/td[4])[" + i + "]")).getText();

				// ------converting the String format into the Int format----------------//
				int received = Recived.isEmpty() ? 0 : Integer.parseInt(Recived);
				int accepted = Accepted.isEmpty() ? 0 : Integer.parseInt(Accepted);
				int rejected = Rejected.isEmpty() ? 0 : Integer.parseInt(Rejected);

				if (!(received == accepted + rejected)) {
					System.out.println(Application_name + "Mismatch: Received=" + received + ", Accepted=" + accepted
							+ ", Rejected=" + rejected);
					Assert.fail();
				}

			}
			Thread.sleep(2000);

		}

		@Test(priority = 17, enabled = true )
		public void System_Pull() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[30]")).click();
			// -------yearly------//
			driver.findElement(By.xpath("//button[normalize-space()='Yearly']")).click();
			driver.findElement(By.xpath("//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//span[normalize-space()='2025']")).click();
			driver.findElement(By.xpath("//div[@class='d-flex justify-content-end tss-pull-right']//button[@type='button']")).click();
			Thread.sleep(2000);

			List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
			System.out.println("This message for the System_Pull");
			for (int i = 1; i <= rows.size(); i++) {
				String Application_name = driver.findElement(By.xpath("(//table/tbody/tr/td[1])[" + i + "]")).getText();
				String Recived = driver.findElement(By.xpath("(//table/tbody/tr/td[2])[" + i + "]")).getText();

				String Accepted = driver.findElement(By.xpath("(//table/tbody/tr/td[3])[" + i + "]")).getText();
				String Rejected = driver.findElement(By.xpath("(//table/tbody/tr/td[4])[" + i + "]")).getText();

				// ------converting the String format into the Int format----------------//
				int received = Recived.isEmpty() ? 0 : Integer.parseInt(Recived);
				int accepted = Accepted.isEmpty() ? 0 : Integer.parseInt(Accepted);
				int rejected = Rejected.isEmpty() ? 0 : Integer.parseInt(Rejected);

				if (!(received == accepted + rejected)) {
					System.out.println(Application_name + "Mismatch: Received=" + received + ", Accepted=" + accepted
							+ ", Rejected=" + rejected);
					Assert.fail();
				}

			}

			Thread.sleep(2000);
		}

		@Test(priority = 18, enabled = true )
		public void Whatsapp_push() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[31]")).click();
			// -------yearly------//
			driver.findElement(By.xpath("//button[normalize-space()='Yearly']")).click();
			driver.findElement(By.xpath("//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//span[normalize-space()='2025']")).click();
			driver.findElement(By.xpath("//div[@class='d-flex justify-content-end tss-pull-right']//button[@type='button']")).click();

			// Wait until the expected data is visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tbody//tr"))); // Update XPath based on actual element displayed

			List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
			System.out.println("This message for the Whatsapp_push");
			for (int i = 1; i <= rows.size(); i++) {
				String Application_name = driver.findElement(By.xpath("(//table/tbody/tr/td[1])[" + i + "]")).getText();
				String Recived = driver.findElement(By.xpath("(//table/tbody/tr/td[2])[" + i + "]")).getText();

				String Accepted = driver.findElement(By.xpath("(//table/tbody/tr/td[3])[" + i + "]")).getText();
				String Rejected = driver.findElement(By.xpath("(//table/tbody/tr/td[4])[" + i + "]")).getText();

//				// ------converting the String format into the Int format----------------//
	//
//				int received = Recived.isEmpty() ? 0 : Integer.parseInt(Recived);
//				int accepted = Accepted.isEmpty() ? 0 : Integer.parseInt(Accepted);
//				int rejected = Rejected.isEmpty() ? 0 : Integer.parseInt(Rejected);
	//
//				if (!(received == accepted + rejected)) {
//					System.out.println(Application_name + "Mismatch: Received=" + received + ", Accepted=" + accepted
//							+ ", Rejected=" + rejected);
//					Assert.fail();
//				}

			}
			Thread.sleep(2000);
		}

		@Test(priority = 19, enabled = true )
		public void Whatsapp_pull() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[32]")).click();
			// -------yearly------//
			driver.findElement(By.xpath("//button[normalize-space()='Yearly']")).click();
			driver.findElement(By.xpath("//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//span[normalize-space()='2025']")).click();
			driver.findElement(By.xpath("//div[@class='d-flex justify-content-end tss-pull-right']//button[@type='button']")).click();
			Thread.sleep(2000);

			List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
			System.out.println("This message for the Whatsapp_pull");
			for (int i = 1; i <= rows.size(); i++) {
				String Application_name = driver.findElement(By.xpath("(//table/tbody/tr/td[1])[" + i + "]")).getText();
				String Recived = driver.findElement(By.xpath("(//table/tbody/tr/td[2])[" + i + "]")).getText();

				String Accepted = driver.findElement(By.xpath("(//table/tbody/tr/td[3])[" + i + "]")).getText();
				String Rejected = driver.findElement(By.xpath("(//table/tbody/tr/td[4])[" + i + "]")).getText();

//				// ------converting the String format into the Int format----------------//
//				int received = Recived.isEmpty() ? 0 : Integer.parseInt(Recived);
//				int accepted = Accepted.isEmpty() ? 0 : Integer.parseInt(Accepted);
//				int rejected = Rejected.isEmpty() ? 0 : Integer.parseInt(Rejected);
	//
//				if (!(received == accepted + rejected)) {
	//
//					System.out.println(Application_name + "Mismatch: Received=" + received + ", Accepted=" + accepted
//							+ ", Rejected=" + rejected);
//					Assert.fail();
//				}

			}
			Thread.sleep(2000);
		}

		// this is for the barring rules

		@Test(priority = 20, enabled = true)
		public void Barring_Rules_com() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//div[contains(@class,'content-body')]//section[contains(@class,'content')]//button[1]")).click();
			Thread.sleep(1000);
			WebElement SourceAdd = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			SourceAdd.sendKeys(dataprovider_java1.source_Address);

			WebElement Dest_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[3]"));
			Dest_Add.sendKeys(dataprovider_java1.Dest_adddress);

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Barring_Des);

			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Barring_Rules_com was seript 25 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 21, enabled = true)
		public void Barring_Rules_com_delete() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			WebElement Source_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Source_Add.sendKeys(dataprovider_java1.source_Address);

			WebElement Dest_add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Dest_add.sendKeys(dataprovider_java1.Dest_adddress);

			driver.findElement(By.xpath("//div[contains(@class,'row tss-pull-right')]//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody/tr[@role='row']/td//*[name()='svg'][2]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion Successful";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + acutal_deleted_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_deleted_message);
				System.out.println("the Barring_Rules_com_delete was seript 26 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 22, enabled = true)
		public void Barring_source_only() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);
			WebElement SourceAdd = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			SourceAdd.sendKeys(dataprovider_java1.Barring_source_only);

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Barring_Des);

			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Barring_source_only was seript 27 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 23, enabled = true)
		public void Barring_source_only_delete() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			WebElement Source_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Source_Add.sendKeys(dataprovider_java1.Barring_source_only);

			driver.findElement(By.xpath("//div[contains(@class,'row tss-pull-right')]//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody/tr[@role='row']/td//*[name()='svg'][2]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion Successful";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + acutal_deleted_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_deleted_message);
				System.out.println("the Barring_source_only_delete was seript 28 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);

		}

		@Test(priority = 24, enabled = true)
		public void Barring_Dest_only() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			WebElement Dest_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Add.sendKeys(dataprovider_java1.Barring_Dest_Only);

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Barring_Des);

			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Barring_Dest_only was seript 29 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 25, enabled = true)
		public void Barring_Dest_only_delete() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			WebElement Dest_Add_delete = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Add_delete.sendKeys(dataprovider_java1.Barring_Dest_Only);

			driver.findElement(By.xpath("//div[contains(@class,'row tss-pull-right')]//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody/tr[@role='row']/td//*[name()='svg'][2]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion Successful";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + acutal_deleted_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_deleted_message);
				System.out.println("the Barring_Dest_only_delete was seript 30 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		// barring rule for the client

		@Test(priority = 26, enabled = true)
		public void Barring_Rules_com_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();

			WebElement SourceAdd = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			SourceAdd.sendKeys(dataprovider_java1.source_Address);

			WebElement Dest_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[3]"));
			Dest_Add.sendKeys(dataprovider_java1.Dest_adddress);

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Barring_Des);

			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Barring_Rules_com was seript 25 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 27, enabled = true)
		public void Barring_Rules_com_delete_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();

			WebElement Source_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Source_Add.sendKeys(dataprovider_java1.source_Address);

			WebElement Dest_add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Dest_add.sendKeys(dataprovider_java1.Dest_adddress);

			driver.findElement(By.xpath("//div[contains(@class,'row tss-pull-right')]//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody/tr[@role='row']/td//*[name()='svg'][2]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion Successful";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + acutal_deleted_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_deleted_message);
				System.out.println("the Barring_Rules_com_delete was seript 26 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 28, enabled = true)
		public void Barring_source_only_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[6]")).click();

			WebElement SourceAdd = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			SourceAdd.sendKeys(dataprovider_java1.Barring_source_only);

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Barring_Des);

			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Barring_source_only was seript 27 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 29, enabled = true)
		public void Barring_source_only_delete_clit() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[6]")).click();

			WebElement Source_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Source_Add.sendKeys(dataprovider_java1.Barring_source_only);

			driver.findElement(By.xpath("//div[contains(@class,'row tss-pull-right')]//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody/tr[@role='row']/td//*[name()='svg'][2]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion Successful";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + acutal_deleted_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_deleted_message);
				System.out.println("the Barring_source_only_delete was seript 28 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);

		}

		@Test(priority = 30, enabled = true)
		public void Barring_Dest_only_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[7]")).click();

			WebElement Dest_Add = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Add.sendKeys(dataprovider_java1.Barring_Dest_Only);

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Barring_Des);

			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				System.out.println("the Barring_Dest_only was seript 29 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 31, enabled = true)
		public void Barring_Dest_only_delete_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("(//li[@id='TSSGUI_MultiSelectOptionsStyle'])[1]")).click();

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[7]")).click();

			WebElement Dest_Add_delete = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Add_delete.sendKeys(dataprovider_java1.Barring_Dest_Only);

			driver.findElement(By.xpath("//div[contains(@class,'row tss-pull-right')]//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody/tr[@role='row']/td//*[name()='svg'][2]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String acutal_deleted_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_deleted_message = "Deletion Successful";

			if (acutal_deleted_message.equals(expected_deleted_message)) {
				System.out.println(" Success message verified: " + acutal_deleted_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_deleted_message);
				System.out.println("the Barring_Dest_only_delete was seript 30 got failed");
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		// this is for the translaction

		@Test(priority = 32, enabled = true)
		public void Translation_Rules_add_sys() throws InterruptedException {

		    WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]"));
		    element0.click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("//li[@title='Source']")).click();
		    Thread.sleep(2000);

		    WebElement Rule_name =
		            driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
		    Rule_name.sendKeys(dataprovider_java1.Rule_name);
		    Thread.sleep(2000);

		    WebElement Address =
		            driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
		    Address.sendKeys(dataprovider_java1.Transaltion_Address);
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("//li[contains(@title,'Prefix')]")).click();
		    Thread.sleep(2000);

		    WebElement prefix =
		            driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
		    prefix.sendKeys(dataprovider_java1.Prefix);
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		    Thread.sleep(2000);

		    String actualMessage =
		            driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
		    Thread.sleep(2000);

		    String expectedMessage = "addition successful";

		    if (actualMessage.equals(expectedMessage)) {
		        System.out.println("Success message verified: " + actualMessage);
		    } else {
		        System.out.println("Message mismatch. Found: " + actualMessage);
		        Assert.fail();
		    }
		    Thread.sleep(2000);

		    driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[8]")).click();
		    Thread.sleep(2000);

		    deleteRecordIfExists(
		            dataprovider_java1.Transaltion_Address,
		            By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
		            By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]")
		    );
		    Thread.sleep(2000);
		}


		@Test(priority = 38, enabled = true)
		public void Translation_Rules_sub_sys() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@title='Source']")).click();

			WebElement Rule_name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Rule_name.sendKeys(dataprovider_java1.Rule_name);

			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(dataprovider_java1.Transaltion_Sub_Address);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
			driver.findElement(By.xpath("//li[@title='Substitute']")).click();

			WebElement index1 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			index1.sendKeys(dataprovider_java1.index1);
			WebElement index2 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[5]"));
			index2.sendKeys(dataprovider_java1.index2);
			WebElement Sub_val = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[6]"));
			Sub_val.sendKeys(dataprovider_java1.Subval);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "addition successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[8]")).click();

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Transaltion_Sub_Address,
					By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
					By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));
			Thread.sleep(2000);

		}

		@Test(priority = 39, enabled = true)
		public void Translation_Rules_add_Des_sys() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);

			WebElement Rule_name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Rule_name.sendKeys(dataprovider_java1.Rule_name);

			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(dataprovider_java1.Transaltion_Des_Address);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Prefix')]")).click();
			WebElement prefix = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			prefix.sendKeys(dataprovider_java1.Prefix);

			driver.findElement(By.xpath("//*[@id=\"ProceedAddComponent\"]/div/div[2]/button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String acutal_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_message = "addition successful";

			if (acutal_message.equals(expected_message)) {
				System.out.println(" Success message verified: " + acutal_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_message);
				System.out.println("the Barring_Dest_only_delete was seript 35 got failed");
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[9]")).click();

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Transaltion_Des_Address,
					By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
					By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));

			Thread.sleep(2000);
		}

		@Test(priority = 40, enabled = true)
		public void Translation_Rules_add_Des_sub_sys() throws InterruptedException {

			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);

			WebElement Rule_name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Rule_name.sendKeys(dataprovider_java1.Rule_name);

			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(dataprovider_java1.Transaltion_Sub_Des_Address);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
			driver.findElement(By.xpath("//li[@title='Substitute']")).click();

			WebElement index1 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			index1.sendKeys(dataprovider_java1.index1);
			WebElement index2 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[5]"));
			index2.sendKeys(dataprovider_java1.index2);
			WebElement Sub_val = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[6]"));
			Sub_val.sendKeys(dataprovider_java1.Subval);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "addition successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[9]")).click();

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Transaltion_Sub_Des_Address,
					By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
					By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));

			Thread.sleep(2000);
		}

		// this is the translation rule in the client

		@Test(priority = 41, enabled = true)
		public void Translation_Rules_add_clie() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@title='Source']")).click();

			WebElement Rule_name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Rule_name.sendKeys(dataprovider_java1.Rule_name);

			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(dataprovider_java1.Transaltion_Address_client);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Prefix')]")).click();
			WebElement prefix = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			prefix.sendKeys(dataprovider_java1.Prefix);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[8]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "addition successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[8]")).click();

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Transaltion_Address_client,
					By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
					By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));

			Thread.sleep(2000);
		}

		@Test(priority = 42, enabled = true)
		public void Translation_Rules_sub_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//li[@title='Source']")).click();

			WebElement Rule_name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Rule_name.sendKeys(dataprovider_java1.Rule_name);

			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(dataprovider_java1.Transaltion_Sub_Add_client);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
			driver.findElement(By.xpath("//li[@title='Substitute']")).click();

			WebElement index1 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			index1.sendKeys(dataprovider_java1.index1);
			WebElement index2 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[5]"));
			index2.sendKeys(dataprovider_java1.index2);
			WebElement Sub_val = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[6]"));
			Sub_val.sendKeys(dataprovider_java1.Subval);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[8]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "addition successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[8]")).click();

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Transaltion_Sub_Add_client,
					By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
					By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));
			Thread.sleep(2000);

		}

		@Test(priority = 43, enabled = true)
		public void Translation_Rules_add_Des_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);

			WebElement Rule_name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Rule_name.sendKeys(dataprovider_java1.Rule_name);

			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(dataprovider_java1.Transaltion_Des_Address);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Prefix')]")).click();
			WebElement prefix = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			prefix.sendKeys(dataprovider_java1.Prefix);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[8]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String acutal_message = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expected_message = "addition successful";

			if (acutal_message.equals(expected_message)) {
				System.out.println(" Success message verified: " + acutal_message);
			} else {
				System.out.println(" Message mismatch. Found: " + acutal_message);
				System.out.println("the Barring_Dest_only_delete was seript 35 got failed");
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[9]")).click();

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Transaltion_Des_Address,
					By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
					By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));

			Thread.sleep(2000);
		}

		@Test(priority = 44, enabled = true)
		public void Translation_Rules_add_Des_sub_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[15]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);

			WebElement Rule_name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Rule_name.sendKeys(dataprovider_java1.Rule_name);

			WebElement Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"));
			Address.sendKeys(dataprovider_java1.Transaltion_Sub_Des_Address);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[7]")).click();
			driver.findElement(By.xpath("//li[@title='Substitute']")).click();

			WebElement index1 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]"));
			index1.sendKeys(dataprovider_java1.index1);
			WebElement index2 = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[5]"));
			index2.sendKeys(dataprovider_java1.index2);
			WebElement Sub_val = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[6]"));
			Sub_val.sendKeys(dataprovider_java1.Subval);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[8]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "addition successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[9]")).click();

			Thread.sleep(1000);
			deleteRecordIfExists(dataprovider_java1.Transaltion_Sub_Des_Address,
					By.xpath("(//input[@class='p-inputtext p-component'])[1]"),
					By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));

			Thread.sleep(2000);
		}

		// this is the redirect rule module

		@Test(priority = 45, enabled = true)
		public void Redirect_rule() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Dest_Add);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[4]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Description);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 46, enabled = true)
		public void Redirect_Delet() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			driver.findElement(By.xpath("//*[local-name()='svg' and @data-icon='plus']")).click();
			Thread.sleep(1000);
			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Dest_Add);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[9]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Deletion SuccessFully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 47, enabled = true)
		public void Redirect_rule_failOver() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Fail_Add);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[3]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[4]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[5]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Description);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition SuccessFull";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 48, enabled = true)
		public void Redirect_rule_failOver_det() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			driver.findElement(By.xpath("//*[local-name()='svg' and @data-icon='plus']")).click();
			Thread.sleep(1000);
			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Fail_Add);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[9]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Deletion SuccessFully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		// this is also redirect module

		@Test(priority = 49, enabled = true)
		public void Redirect_rule_client() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[4]")).click();

			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Dest_Add);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[5]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Description);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(1000);
//			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
//			String expectedMessage = "Addition Successful";
	//
//			if (actualMessage.equals(expectedMessage)) {
//				System.out.println(" Success message verified: " + actualMessage);
//			} else {
//				System.out.println(" Message mismatch. Found: " + actualMessage);
//				Assert.fail();
//			}
//			Thread.sleep(2000);
		}

		@Test(priority = 50, enabled = true)
		public void Redirect_Delet_clint() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[local-name()='svg' and @data-icon='plus']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[4]")).click();

			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Dest_Add);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[9]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

//			Thread.sleep(1000);
//			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
//			String expectedMessage = "Deletion SuccessFully";
	//
//			if (actualMessage.equals(expectedMessage)) {
//				System.out.println(" Success message verified: " + actualMessage);
//			} else {
//				System.out.println(" Message mismatch. Found: " + actualMessage);
//				Assert.fail();
//			}
			Thread.sleep(2000);
		}

		@Test(priority = 51, enabled = true)
		public void Redirect_rule_failOver_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[4]")).click();

			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Fail_Add);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[3]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[3]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[4]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[5]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[1]")).click();

			WebElement Description = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Description.sendKeys(dataprovider_java1.Description);

			driver.findElement(By.xpath("//*[@id=\"ProceedAddComponent\"]/div/div[2]/button[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);

		}

		@Test(priority = 52, enabled = true)
		public void Redirect_rule_failOver_det_cli() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[17]")).click();
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[name()='svg'][contains(@role,'img')])[8]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[4]")).click();

			WebElement Dest_Address = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			Dest_Address.sendKeys(dataprovider_java1.Redirect_Fail_Add);

			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			driver.findElement(By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[9]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Deletion SuccessFully";

			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 53, enabled = true)
		public void Whatsapp_template_img() throws InterruptedException {

			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[9]")).click();
			driver.findElement(By.xpath("(//a[normalize-space()='Whatsapp Template'])[1]")).click();

			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[normalize-space()='Next'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();
			Thread.sleep(2000);

			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			name.sendKeys(dataprovider_java1.template_name);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();

			ClassLoader classLoader = getClass().getClassLoader();
			String filePath = "C:\\Users\\sanjay dharshan\\eclipse-workspace\\bmp\\src\\test\\java\\bmp\\bmp\\image\\img.jpg";

			WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));

			WebElement Des = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Des.sendKeys(dataprovider_java1.Description);
			upload.sendKeys(filePath);
			driver.findElement(By.xpath("(//button[@id='confirmButton'])[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
//			File file = new File(classLoader.getResource("image/img.jpg").getFil se());
//			String imagePath = file.getAbsolutePath();

		}

		@Test(priority = 54, enabled = true)
		public void Whatsapp_template() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[9]")).click();
			driver.findElement(By.xpath("(//a[normalize-space()='Whatsapp Template'])[1]")).click();

			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[normalize-space()='Next'])[1]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[3]")).click();
			Thread.sleep(2000);

			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			name.sendKeys(dataprovider_java1.template_name);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]"))
					.sendKeys("this is the verification");

			WebElement body = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			body.sendKeys(dataprovider_java1.template_Body);
			driver.findElement(By.xpath("(//button[@id='confirmButton'])[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		}

		@Test(priority = 55, enabled = true)
		public void Whatsapp_template_doc() throws InterruptedException {

			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[9]")).click();
			driver.findElement(By.xpath("(//a[normalize-space()='Whatsapp Template'])[1]")).click();

			driver.findElement(By.xpath("(//div[@class='button-container']//*[name()='svg'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[normalize-space()='Next'])[1]")).click();

			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			name.sendKeys(dataprovider_java1.template_name);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[7]")).click();

			ClassLoader classLoader = getClass().getClassLoader();
			String filePath = "C:\\Users\\sanjay dharshan\\eclipse-workspace\\bmp\\src\\test\\java\\bmp\\bmp\\image\\test.pdf";

			WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));

			WebElement Des = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Des.sendKeys(dataprovider_java1.Description);
			upload.sendKeys(filePath);
			driver.findElement(By.xpath("(//button[@id='confirmButton'])[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(1000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Template created successfully!";

			if (actualMessage.contains(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(2000);
		}

		@Test(priority = 56, enabled = true)
		public void Order_Details() throws InterruptedException {
			WebElement element0 = driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")); // this is siteMap
			element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[9]")).click();
			driver.findElement(By.xpath("(//a[normalize-space()='Whatsapp Template'])[1]")).click();

			driver.findElement(By.xpath("//*[local-name()='svg' and @data-icon='plus']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='orderDetails']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[normalize-space()='Next'])[1]")).click();

			WebElement name = driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]"));
			name.sendKeys(dataprovider_java1.template_name);

			driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[1]")).click();
			driver.findElement(By.xpath("//ul[@id='tss-optionList']//li[5]")).click();

			WebElement body = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			body.sendKeys(dataprovider_java1.template_Body);

			ClassLoader classLoader = getClass().getClassLoader();
			String filePath = "C:\\Users\\sanjay dharshan\\eclipse-workspace\\bmp\\src\\test\\java\\bmp\\bmp\\image\\img.jpg";

			WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));

			WebElement Des = driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[1]"));
			Des.sendKeys(dataprovider_java1.Description);
			upload.sendKeys(filePath);
			driver.findElement(By.xpath("(//button[@id='confirmButton'])[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(1000);
//			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
//			String expectedMessage = "Template created successfully!";
	//
//			if (actualMessage.contains(expectedMessage)) {
//				System.out.println(" Success message verified: " + actualMessage);
//			} else {
//				System.out.println(" Message mismatch. Found: " + actualMessage);
//				Assert.fail();
//			}
		}

		@AfterClass
		public void tearDown() {
			if (driver != null) {
				// driver.quit();
			}
		}
	
}


