package bmp;

import java.util.UUID;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

	public class bmpsender {
		public static WebDriver driver;
		@BeforeMethod
		// logic to login one time in the browser and execute all test cases within the
		// browser
		public void setUp() throws InterruptedException {
			//WebDriver driver  = new ChromeDriver();
			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		//	SMSC.reporting_gui.driver = driver; // Set the WebDriver instance
			bmpsender.driver= driver;
//			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
//			SMSC.barring_issue.driver = driver; // Set the WebDriver instance

			 driver.get("https://10.0.1.210:3002/bmp/auth/login");

//			driver.manage().window().maximize();
				driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("pankaj");
				driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj123");
				driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();
				driver.manage().window().maximize();
				//driver.findElement(By.id(browser)
				
			Thread.sleep(2000);	
				
	}
		
	@Test(priority = 1)
	public void adding_sender_id() throws InterruptedException{
		
		String uniqueSenderID = "sample" + UUID.randomUUID().toString().substring(0, 8);

	    // Click on the required elements to navigate to the Sender ID addition section
	    //driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/img[1]")).click();
	    //Thread.sleep(1000);
	    driver.findElement(By.xpath("//a[normalize-space()='Preferences']")).click();
	    Thread.sleep(3000);

	    // Maximize the window and scroll down if needed
	    driver.manage().window().maximize();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(1100,888)");

	    // Click on 'Add Sender ID' and input the dynamically generated Sender ID
	    driver.findElement(By.xpath("//span[normalize-space()='Add Sender ID']")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@id='senderid-0']")).sendKeys(uniqueSenderID);

		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@id='termsCheckbox'])[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
		Thread.sleep(4000);
		//driver.findElement(By.xpath("//div[@id='addSenderIdResponseModal']//p[1]"))
		WebElement messageElement = driver.findElement(By.xpath("//div[@id='addSenderIdResponseModal']//p[1]"));
		// Extract the text from the element
		
//		String actualText = messageElement.getText();	
		String actualText = messageElement.getText().trim();
		actualText = actualText.replaceAll("\\s+", " ");
		// Expected text that should be in the element (you can customize it)
		String expectedText = "Hello pankaj, We have successfully processed your request for additional Sender IDs";
		

		// Assertion to verify the actual text matches the expected text
		Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!");

	}

	@Test(priority = 2)

	    // Initialize WebDriver (assuming WebDriver is already set up elsewhere)
		public void Accepting_sender_id_request() throws InterruptedException{
			driver.get("https://10.0.1.210:8443/bmp/");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[2]/td[2]/input")).sendKeys("superadmin");
			driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[3]/td[2]/input")).sendKeys("superadmin");
			driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[5]/td/input")).click();
			
			// Hover over "Customer Care" and click on the desired option
		    Actions actions = new Actions(driver);
			
			// Locate the "Customer Care" element
		    WebElement customerCareElement = driver.findElement(By.xpath("//div[normalize-space()='Customer Care']"));

		    // Perform hover action
		    actions.moveToElement(customerCareElement).perform();
		 // Click on the desired option from the revealed menu
		    WebElement optionElement = driver.findElement(By.xpath("//div[contains(text(), 'Sender ID')]")); // Replace 'Desired Option' with the actual text or locator of the option
		    optionElement.click();
		    Thread.sleep(2000);

		    // Wait for the options to appear (you might need to adjust the wait time or use WebDriverWait)
		    WebElement iframeElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
			 // Perform the click action on the input element
			   // WebElement iframeElement = driver.findElement(By.xpath("//iframe[@id='iframeId']")); // Adjust XPath as needed
		       driver.switchTo().frame(iframeElement);
			     iframeElement.click();
			        WebElement inputElement = driver.findElement(By.cssSelector("tr.normTextEven:nth-child(2) > td:nth-child(4) > input:nth-child(1)"));
			        // Perform the click action on the input element
			        inputElement.click();
			        
			     /// Switch to alert
			        Alert alert = driver.switchTo().alert();
			        Thread.sleep(1000);

			        // Print alert message
			        System.out.println("Gui_message: " + alert.getText());

			        // Accept (click OK) to close the alert
			        alert.accept();
		}
	@Test(priority = 3)
	public void Sending_Message_Using_newSender_Id() throws InterruptedException{
//	public void Sending_Message_Using_newSender_Id() throws InterruptedException{
//		driver.get("https://10.0.1.210:3002/bmp/auth/login");
		setUp();
//		driver.manage().window().maximize();
		//	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("pankaj");
			//driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj123");
			//driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();
			//driver.manage().window().maximize();
			//driver.findElement(By.id(browser)
			
		//Thread.sleep(3000);	
	    driver.findElement(By.xpath("//div[@id='senderID']")).click();
		driver.findElement(By.xpath("//li[normalize-space()='sample332']")).click();
		Thread.sleep(3000);
		
		//driver.findElement(By.cssSelector("#mui-2")).sendKeys("9748574");
		driver.findElement(By.id("mui-4")).sendKeys("9748745");
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hello");
		driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
		/// Switch to alert
	   // Alert alert = driver.switchTo().alert();
	    //Thread.sleep(1000);

	    // Print alert message
	    //System.out.println("Gui_message: " + alert.getText());

	    // Accept (click OK) to close the alert
	    //alert.accept();
		driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
		
	}


	@AfterMethod
	public void setUps() {
		driver.quit();
	}
	}
	

