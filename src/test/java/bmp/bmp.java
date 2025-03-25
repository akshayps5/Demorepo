package bmp;

	import java.time.Duration;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class bmp {
		public static WebDriver driver;

		@BeforeClass
		// logic to login one time in the browser and execute all test cases within the
		// browser
		public void setUp() {
			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
			bmp.driver = driver; // Set the WebDriver instance

			//driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
			
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
		public void SMS_sending() throws InterruptedException {
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

					driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo1"); // username
					driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo1"); // password
					Thread.sleep(2000);
					driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
					Thread.sleep(2000);

					driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good mesage");// message
																											// description
					driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("hi hello");// text message
					driver.findElement(By.xpath("//input[@name='mobNo']")).sendKeys("12345678");// mobile number

					// dispatch time
					WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
					Thread.sleep(2000);
					Select select3 = new Select(dropdown3);
					select3.selectByIndex(0);// to select schedule
					Thread.sleep(3000);

					// logic to select date from calendar validity period
					driver.findElement(By.xpath("//*[@id=\"trVal\"]/td[2]/img")).click();// calender opening

					WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement addLink2 = wait2
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]")));
					addLink2.click();
					String amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
					System.out.println(amonth2);

					while (!(amonth2.equals("December 2023"))) {
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();
						amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
						System.out.println(amonth2);

					}
					driver.findElement(By.xpath("//a[normalize-space()='22']")).click();
					driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
					Thread.sleep(3000);
					
					// logic to select date from calendar date
					driver.findElement(By.xpath("//tr[@id='DateTr']//img[@title='Click to Choose Date']")).click();// calender opening

					WebDriverWait wait22 = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement addLink22 = wait22
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ui-datepicker-div']")));
					addLink22.click();
					String amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
					System.out.println(amonth22);

					while (!(amonth22.equals("December 2023"))) {
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();
						amonth22 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
						System.out.println(amonth22);

					}
					driver.findElement(By.xpath("//a[normalize-space()='25']")).click();
					driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
					Thread.sleep(3000);

					driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String s1 = errorMessageElement.getText();
					int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");
					// Trim the string to keep only the part until the specified index
					String trimmedString = s1.substring(0, endIndex);
					System.out.println(trimmedString);
					String expectedOutput = "Msg sent to : [97512345678 ]";
					Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for invalid service id");
					Thread.sleep(3000);

				}
			}
		}
		
		@Test(priority = 2)
		public void SMS_textmessage_input() throws InterruptedException {
			driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
			Thread.sleep(2000);

			// Get the handles of all open windows
			Set<String> windowHandles = driver.getWindowHandles();

			// Iterate through the window handles/
			for (String handle : windowHandles) {
				// Switch to the new window
				driver.switchTo().window(handle);

//				// Check if it's the desired window based on the title, URL, or other criteria
				if (driver.getTitle().equals("Bulk Messaging Platform")) {

					Thread.sleep(2000);

					driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo1"); // username
					driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo1"); // password
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id=\"ViewLink1\"]")).click(); // select messages
					Thread.sleep(2000);
					driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[1]")).click();// select1
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id=\"ViewLink1\"]")).click(); // select messages
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@title='Click here to Close']")).click(); // select messages close
																									// button

					driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys(
							"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/");// message
					// description
					driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys(
							"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");// text
					Thread.sleep(2000);

					Thread.sleep(2000);
					driver.findElement(By.xpath("//tbody/tr/td[2]/input[2]")).click(); // Concatenate radio button
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//input[@name='messageType'])[3]")).click(); // split radio button
					Thread.sleep(2000);
					driver.findElement(By.xpath(
							"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input[1]"))
							.click(); // single radio button
					Thread.sleep(2000);
					WebElement mobilenumber = driver.findElement(By.xpath("//input[@name='mobNo']"));
					mobilenumber.sendKeys("97596385274"); // mobile number entering 11 digit number including 275
					mobilenumber.clear();
					mobilenumber.sendKeys("96385274"); // mobile number entering 8 digit number excluding 275
					Thread.sleep(2000);

					driver.findElement(By.xpath("//tbody/tr[8]/td[1]/input[1]")).click(); // file upload radio button
					Thread.sleep(2000);
					WebElement dropdown2 = driver.findElement(By.xpath("//select[@title='select Message Type']"));
					Thread.sleep(2000);
					Select select2 = new Select(dropdown2);
					select2.selectByIndex(1);// to number&message
					Thread.sleep(3000);
					// driver.findElement(By.xpath("//input[@name='fname' and
					// @class='bbttn']")).click(); //file upload button
					// Thread.sleep(2000);

					driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio
																								// button
					// distribution list
					WebElement dropdown4 = driver.findElement(By.xpath("//select[@name='distList']"));
					Thread.sleep(2000);
					Select select4 = new Select(dropdown4);
					select4.selectByIndex(1);// to select schedule
					Thread.sleep(3000);

					// dispatch time
					WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
					Thread.sleep(2000);
					Select select3 = new Select(dropdown3);
					select3.selectByIndex(1);// to select schedule
					Thread.sleep(3000);

					// logic to select date from calendar
					driver.findElement(By.xpath("//*[@id=\"trVal\"]/td[2]/img")).click();// calendar opening

					WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement addLink2 = wait2
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]")));
					addLink2.click();
					String amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
					System.out.println(amonth2);

					while (!(amonth2.equals("December 2023"))) {
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();
						amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
						System.out.println(amonth2);

					}
					driver.findElement(By.xpath("//a[normalize-space()='20']")).click();
					Thread.sleep(3000);

					// dsr
					WebElement dropdown45 = driver.findElement(By.xpath("//select[@title='select dsr flag']"));
					Thread.sleep(2000);
					Select select45 = new Select(dropdown45);
					select45.selectByIndex(1);// to select schedule
					Thread.sleep(3000);

					driver.findElement(By.xpath("//td[@id='ClearLink']")).click();
					Thread.sleep(3000);
					
					driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click();// binary message
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys(
							"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/");// message
					// description
					driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys(
							"08050B8423F0\r\n" + "112233445566778899AABBCCDDEEFF00112233445566778899AABBCCDDEEFF");// text
					Thread.sleep(2000);

					driver.findElement(By.xpath(
							"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
							.sendKeys("05000368656C6C6F");
					Thread.sleep(2000);
					driver.findElement(By.xpath("//td[@id='SendLink']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
					Thread.sleep(3000);
					
					//unicode message tab verification
					driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click();// unicode message
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys(
							"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/");// message
					// description
					driver.findElement(By.xpath("//textarea[@name='textMessage']"))
							.sendKeys("08050B8423F014569865744865F00112233445566778899AABBCCDDEEFF");// text

					Thread.sleep(2000);
					driver.findElement(By.xpath("//td[@id='SendLink']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
					
					Thread.sleep(2000);

					driver.findElement(By.xpath("//a[normalize-space()='Canned Message']")).click();// unicode message
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@title='Enter Message Description']")).sendKeys("good message"); // message
																															// description
					Thread.sleep(2000);
					// message name
					WebElement dropdown31 = driver.findElement(By.xpath("//select[@title='select message name']"));
					Thread.sleep(2000);
					Select select31 = new Select(dropdown31);
					select31.selectByIndex(2);// to select schedule
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
					WebElement wait1 = wait.until(ExpectedConditions
							.presenceOfElementLocated(By.cssSelector("input[title='Select File to be uploaded']")));
					Thread.sleep(1000);
					driver.findElement(By.xpath("//td[@id='SendLink']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
					
					
				}

			}
		}
		
		@Test(priority = 3)
		public void SMSpage_verification() throws InterruptedException {
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

					driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo1"); // username
					driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo1"); // password
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
					Thread.sleep(2000);
					
					//sms page
					WebElement textElement1 = driver.findElement(By.xpath("//td[normalize-space()='Send Text Messages']"));
					String s1 = textElement1.getText();
					System.out.println(s1);
					System.out.println("SMS text page displayed");
					
					driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement2 = driver.findElement(By.xpath("//td[normalize-space()='Send Binary Messages']"));
					String s2 = textElement2.getText();
					System.out.println(s2);
					System.out.println("SMS binary page displayed");
					
					driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement3 = driver.findElement(By.xpath("//td[normalize-space()='Send Unicode Messages']"));
					String s3 = textElement3.getText();
					System.out.println(s3);
					System.out.println("SMS Unicode page displayed");
					
					driver.findElement(By.xpath("//a[normalize-space()='Canned Message']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement4 = driver.findElement(By.xpath("//td[normalize-space()='Send Canned Messages']"));
					String s4 = textElement4.getText();
					System.out.println(s4);
					System.out.println("SMS Canned page displayed");
					Thread.sleep(3000);
					
					
					//configuration page
					driver.findElement(By.xpath("//a[normalize-space()='Configuration']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement5 = driver.findElement(By.xpath("//td[normalize-space()='List Management']"));
					String s5 = textElement5.getText();
					System.out.println(s5);
					System.out.println("Configuration List Management page displayed");

					driver.findElement(By.xpath("//a[normalize-space()='Canned Msg.']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement6 = driver.findElement(By.xpath("//td[normalize-space()='Canned Message']"));
					String s6 = textElement6.getText();
					System.out.println(s6);
					System.out.println("Configuration Canned page displayed");
					Thread.sleep(3000);
					
					//my account page
					driver.findElement(By.xpath("//a[normalize-space()='My Account']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement7 = driver.findElement(By.xpath("//td[normalize-space()='Profile']"));
					String s7 = textElement7.getText();
					System.out.println(s7);
					System.out.println("my account Profile page displayed");
					
					driver.findElement(By.xpath("//a[normalize-space()='Change Password']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement8 = driver.findElement(By.xpath("//td[normalize-space()='Change Password']"));
					String s8 = textElement8.getText();
					System.out.println(s8);
					System.out.println("my account Change Password page displayed");
					Thread.sleep(3000);
					
					
					//contact admin page
					driver.findElement(By.xpath("//a[normalize-space()='Contact Admin']")).click(); // login button
					Thread.sleep(2000);
					WebElement textElement9 = driver.findElement(By.xpath("//td[@align='left'][normalize-space()='Contact Admin']"));
					String s9 = textElement9.getText();
					System.out.println(s9);
					System.out.println("contact admin page displayed");
					Thread.sleep(3000);
					
					//status page
					driver.findElement(By.xpath("//a[normalize-space()='Status']")).click(); // login button
					Thread.sleep(2000);
					
					// Get the handles of all open windows (including pop-ups)
					Set<String> windowHandles1 = driver.getWindowHandles();

					// Store the main window handle for later reference
					String mainWindowHandle = driver.getWindowHandle();

					// Iterate through the window handles
					for (String handle1 : windowHandles1) {
					    // Switch to the new window
					    driver.switchTo().window(handle1);

					    // Check if it's the desired pop-up window based on the URL
					    if (!handle1.equals(mainWindowHandle)) {
					        // Assuming the URL of the pop-up window is "https://10.0.0.21:8443/bmpportal/pages/status_current.jsp"
					        if (driver.getCurrentUrl().equals("https://10.0.0.21:8443/bmpportal/pages/status_current.jsp")) {


							WebElement textElement10 = driver.findElement(By.xpath("//td[normalize-space()='File Status-SMS']"));
							String s10 = textElement10.getText();
							System.out.println(s10);
							System.out.println("status File Status-SMS page displayed");
							
							driver.findElement(By.xpath("//a[normalize-space()='Scheduled']")).click(); // login button
							Thread.sleep(2000);
							WebElement textElement11 = driver.findElement(By.xpath("//td[normalize-space()='Scheduled Status']"));
							String s11 = textElement11.getText();
							System.out.println(s11);
							System.out.println("status Scheduled Status page displayed");
							 driver.close();
							// Switch back to the main window
				                driver.switchTo().window(handle);
				                break; // Exit the loop after handling the pop-up window
							
						}
					}
				}
				}
			}
		}
		
		
		@Test(priority = 4)
		public void SMS_configuation_page_display() throws InterruptedException {
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
					driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo1"); // username
					driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo1"); // password
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[normalize-space()='Configuration']")).click();// configuration message
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@value='Add']")).click();
					driver.findElement(By.xpath("//*[@id=\"one\"]/div/table/tbody/tr[3]/td/table/tbody/tr[1]/td/input"))
							.sendKeys("smsc12"); // addlist1
					Thread.sleep(2000);
					driver.findElement(By.xpath(
							"//body[1]/table[1]/tbody[1]/tr[4]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/form[1]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/div[2]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/input[1]"))
							.click();//
					driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
					Thread.sleep(5000);
					
					driver.findElement(By.xpath("//input[@name='button' and @id='button' and @value='Add']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id=\"two\"]/div/table/tbody/tr[3]/td/table/tbody/tr[1]/td/input[2]"))
							.sendKeys("97596385274");
				}
			}
		}
		
		@Test(priority = 5)
		public void SMS_configuation_page_canned_message_display() throws InterruptedException {
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
					driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo1"); // username
					driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo1"); // password
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[normalize-space()='Configuration']")).click();// configuration message
					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[normalize-space()='Canned Msg.']")).click();// canned message

					driver.findElement(By.xpath("//*[@id=\"button\"]")).click();
					driver.findElement(By.xpath("//input[@name='msgname']")).sendKeys("tashi1"); // addlist1
					Thread.sleep(2000);
					driver.findElement(By.xpath("//textarea[@title='Place holders should be like __$1__,__$2__ format.']"))
							.sendKeys("tashi__$1__"); // addlist1
					Thread.sleep(2000);
					driver.findElement(By.xpath(
							"/html/body/table/tbody/tr[4]/td/table/tbody/tr/td[2]/div/form/div[2]/div/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/div[2]/div/table/tbody/tr[3]/td/table/tbody/tr[8]/td/table/tbody/tr/td[1]/input"))
							.click();

					driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
					Thread.sleep(2000);
					WebElement success = driver.findElement(By.xpath("//*[@id=\"addMessage\"]/td/table/tbody/tr/td"));
				String s1 = success.getText();
				System.out.println(s1);
				String expectedOutput = "Addition Successful";
				Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for invalid service id");
				
				}
			}
		}

	}
	
	


