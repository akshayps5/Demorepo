package bmp;

import java.io.File;
import java.time.Duration;
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

public class BMPportal_gui_smsc {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		BMPportal_gui_smsc.driver = driver; // Set the WebDriver instance

		driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
		// driver.get("https://10.0.0.21:8443/bmpportal/");

		// Set implicit wait to 5 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
		driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
		driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
	}
	
	@Test(priority = 1)
	public void retrylink_deletion() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Retry')]")).click(); // retry link in
																									// system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Retry Pattern']")).click(); // retry link in system config
		Thread.sleep(2000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//*[@id=\"borderDivCop\"]/table/tbody/tr[6]/td[5]/u/a")).click(); // retry link in
																										// system config
		Thread.sleep(2000);
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Deletion Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);
	}


	@Test(priority = 2)
	public void retrylink_adding_demo10() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Retry')]")).click(); // retry link in
																									// system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Retry Pattern']")).click(); // retry link in system config
		Thread.sleep(2000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//*[@id=\"addLink\"]")).click(); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/input"))
				.sendKeys("demo10"); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[4]/input"))
				.sendKeys("2"); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/input[1]"))
				.click();
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		//td[@class='succmsg']
		// successful message
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String actualOutput = errorMessageElement.getText();
					System.out.println(actualOutput);
					String expectedOutput = "Addition Successful";
					Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
					Thread.sleep(2000);
	}
	
	@Test(priority = 3)
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
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
	
	@Test(priority = 4)
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
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
				
				driver.findElement(By.xpath("//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[8]/td[1]/input")).click(); // file upload radio button
				Thread.sleep(2000);
				
				String projectPath = System.getProperty("user.dir");
				String filePath = projectPath + "\\SMSC\\files\\demo 1.txt";

				// Check if the file exists
				File file = new File(filePath);
				if (file.exists()) {
					WebElement fileInput = driver.findElement(By.xpath("//input[@title='Select File to be uploaded with one mobile no. per line']"));
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
				driver.findElement(By.xpath("//input[@value=' Yes ']")).click();
				
				WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String s1 = errorMessageElement.getText();
				System.out.println(s1);
				int endIndex = s1.indexOf("Reference Number for uploaded file");
				String trimmedString = s1.substring(0, endIndex);
				System.out.println(trimmedString);
				String expectedOutput = "Reference Number for uploaded file";
				Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);

			}
		}
	}
	
	@Test(priority = 5)
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
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
				driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio button
				Thread.sleep(2000);
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@name='distList']"));
				Thread.sleep(2000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(1);// to select message name as tashi

				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value=' Yes ']")).click();
			 	//Msg Sent To : [smsc16 ] Reference : 1112
				
				WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String s1 = errorMessageElement.getText();
				System.out.println(s1);
				int endIndex = s1.indexOf("//Msg Sent To : [smsc16 ]");
				// Trim the string to keep only the part until the specified index
				String trimmedString = s1.substring(0, endIndex);
				System.out.println(trimmedString);
				String expectedOutput = "//Msg Sent To : [smsc16 ]";
				Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);

			}
		}
	}
@Test(priority = 6)
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
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
				Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);
			}
		}
	}
	
	@Test(priority = 7)
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // binary message
				driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
				driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio button
				Thread.sleep(2000);
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@name='distList']"));
				Thread.sleep(2000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(1);// to select message name as tashi
				
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
				Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);
			}
		}
	}
	
	@Test(priority = 8)
	public void SMS_sending_cannedmessage() throws InterruptedException {
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // user name
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='Canned Message']")).click(); // canned message
				driver.findElement(By.xpath("//input[@title='Enter Message Description']")).sendKeys("good message"); // message
																														// description
				// message name
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@title='select message name']"));
				Thread.sleep(2000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(1);// to select message name as tashi
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
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

				WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String s1 = errorMessageElement.getText();
				System.out.println(s1);
				int endIndex = s1.indexOf("Reference Number for this File is 1110");
				// Trim the string to keep only the part until the specified index
				String trimmedString = s1.substring(0, endIndex);
				System.out.println(trimmedString);
				String expectedOutput = "Reference Number for this File is 1110";
				Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
				Thread.sleep(3000);
			}
		}
	}
	
	@Test(priority = 9)
	public void SMS_configuation_page_listmgmt_cannedmsg_addinglist() throws InterruptedException {
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
				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Configuration']")).click();// configuration message
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value='Add']")).click();
				driver.findElement(By.xpath("//*[@id=\"one\"]/div/table/tbody/tr[3]/td/table/tbody/tr[1]/td/input"))
						.sendKeys("smsc1"); // addlist1
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//body[1]/table[1]/tbody[1]/tr[4]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/form[1]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/div[2]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/input[1]"))
						.click();//
				driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
				Thread.sleep(5000);
				// add number list1
				driver.findElement(By.xpath("(//input[@name='button'])[8]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("/html/body//input[@name='msisdn']")).sendKeys("97596385278");
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//input[@name='button'])[9]")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/input[1]")).click();
				Thread.sleep(2000);

				// canned message
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
				Thread.sleep(4000);
			}
		}
	}

	@Test(priority = 10)
	public void SMSpage_binary_text_unicode_canned_inputverify() throws InterruptedException {
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
				Thread.sleep(2000);

				driver.findElement(By.xpath("//td[@class='nav_td_border_select']")).click();// username
				// sms page
				WebElement textElement1 = driver.findElement(By.xpath("//td[normalize-space()='Send Text Messages']"));
				String s1 = textElement1.getText();
				System.out.println(s1);
				System.out.println("SMS text page displayed");
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

				driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio
		
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
				driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
				Thread.sleep(3000);

				driver.findElement(By.xpath("//td[@id='ClearLink']")).click();
				Thread.sleep(3000);
				System.out.println("sms text page input field verified");

				// input field verification for binary message
				driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // login button
				Thread.sleep(2000);
				WebElement textElement2 = driver
						.findElement(By.xpath("//td[normalize-space()='Send Binary Messages']"));
				String s2 = textElement2.getText();
				System.out.println(s2);
				System.out.println("SMS binary page displayed");

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
				System.out.println("sms binary page input field verified");

				// input field verification for binary message
				driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // unicode page button
				Thread.sleep(2000);
				WebElement textElement3 = driver
						.findElement(By.xpath("//td[normalize-space()='Send Unicode Messages']"));
				String s3 = textElement3.getText();
				System.out.println(s3);
				System.out.println("SMS Unicode page displayed");
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
				System.out.println("sms binary page input field verified");

				// input field verification for canned message
				driver.findElement(By.xpath("//a[normalize-space()='Canned Message']")).click(); // login button
				Thread.sleep(2000);
				WebElement textElement4 = driver
						.findElement(By.xpath("//td[normalize-space()='Send Canned Messages']"));
				String s4 = textElement4.getText();
				System.out.println(s4);
				System.out.println("SMS Canned page displayed");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//input[@title='Enter Message Description']")).sendKeys("good message"); // messagedescription
				// message name
				WebElement dropdown31 = driver.findElement(By.xpath("//select[@title='select message name']"));
				Thread.sleep(2000);
				Select select31 = new Select(dropdown31);
				select31.selectByIndex(0);// to select schedule
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
				WebElement wait1 = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("input[title='Select File to be uploaded']")));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//td[@id='SendLink']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
				Thread.sleep(2000);
				System.out.println("sms canned page input field verified");
				Thread.sleep(5000);
			}
		}
	}

	@Test(priority = 11)
	public void SMSpage_myaccount_contactadmin_status() throws InterruptedException {
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

				driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("demo10"); // username
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo10"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
				Thread.sleep(2000);

				// my account page
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

				// contact admin page
				driver.findElement(By.xpath("//a[normalize-space()='Contact Admin']")).click(); // login button
				Thread.sleep(2000);
				WebElement textElement9 = driver
						.findElement(By.xpath("//td[@align='left'][normalize-space()='Contact Admin']"));
				String s9 = textElement9.getText();
				System.out.println(s9);
				System.out.println("contact admin page displayed");
				Thread.sleep(3000);

				// status page
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
						// Assuming the URL of the pop-up window is
						// "https://10.0.0.21:8443/bmpportal/pages/status_current.jsp"
						if (driver.getCurrentUrl()
								.equals("https://10.0.0.36:8443/bmpportal/pages/status_current.jsp")) {

							WebElement textElement10 = driver
									.findElement(By.xpath("//td[normalize-space()='File Status-SMS']"));
							String s10 = textElement10.getText();
							System.out.println(s10);
							System.out.println("status File Status-SMS page displayed");

							driver.findElement(By.xpath("//a[normalize-space()='Scheduled']")).click(); // login button
							Thread.sleep(2000);
							WebElement textElement11 = driver
									.findElement(By.xpath("//td[normalize-space()='Scheduled Status']"));
							String s111 = textElement11.getText();
							System.out.println(s111);
							System.out.println("status Scheduled Status page displayed");
							driver.close();
							// Switch back to the main window
							//driver.switchTo().window(handle);
							break; // Exit the loop after handling the pop-up window

						}
					}
				}
			}
		}
	}

}
