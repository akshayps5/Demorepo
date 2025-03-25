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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class senderid_ft {
	public static WebDriver driver;
	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the  browser
	public void setUp() throws InterruptedException {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
	senderid_ft.driver= driver;
//		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance


		 driver.get("https://10.0.1.210:3002/bmp/auth/login");

//		driver.manage().window().maximize();
			driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("pankaj"); //entering the username 
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj123");//entering the password
			driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();//performing the click action
			driver.manage().window().maximize();
			//driver.findElement(By.id(browser)
			
		Thread.sleep(2000);	
		
		
}
// to login as a client and send sender_id request	
@Test(priority = 1)
public void adding_sender_id() throws InterruptedException{
	//clicks on the profile icon
	driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/img[1]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[normalize-space()='Preferences']")).click(); //click on the preferences
	Thread.sleep(3000);
	driver.manage().window().maximize();
	JavascriptExecutor js = (JavascriptExecutor) driver; // top scroll down and click on add sender_id
//	// Scroll down by 1100,888 pixels
js.executeScript("window.scrollBy(1230,32)");
	driver.findElement(By.xpath("//span[normalize-space()='Add Sender ID']")).click();//clicks on sender id 
	Thread.sleep(3000);
	driver.findElement(By.xpath("//input[@id='senderid-0']")).sendKeys("Automation1872"); // enter the value for sender_id
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//input[@id='termsCheckbox'])[1]")).click();// checks the checkbox
	Thread.sleep(4000);
	driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();//clicks on next button
	Thread.sleep(4000);
	//driver.findElement(By.xpath("//div[@id='addSenderIdResponseModal']//p[1]"))
	WebElement messageElement = driver.findElement(By.xpath("//div[@id='addSenderIdResponseModal']//p[1]"));
	// Extract the text from the element
		
	String actualText = messageElement.getText().trim();// to remove the extra spaces and lines
	actualText = actualText.replaceAll("\\s+", " ");
	// Expected text that should be in the element (you can customize it)
	String expectedText = "Hello pankaj, We have successfully processed your request for additional Sender IDs";
	

	// Assertion to verify the actual text matches the expected text
	Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)

}

//To login as superadmin and approve the sender id request
@Test(priority = 2)
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
    WebElement optionElement = driver.findElement(By.xpath("//div[contains(text(), 'Sender ID')]")); // clicks on sender_id
    // Replace 'Desired Option' with the actual text or locator of the option
    optionElement.click();
    Thread.sleep(2000);// Wait for the options to appear (you might need to adjust the wait time or use WebDriverWait)

    WebElement iframeElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));//main iframe
	 // Perform the click action on the input element
	   
       driver.switchTo().frame(iframeElement);
	     iframeElement.click();
	        WebElement inputElement = driver.findElement(By.cssSelector("tr.normTextEven:nth-child(2) > td:nth-child(4) > input:nth-child(1)"));//inside iframe
	        // Perform the click action on the input element
	        inputElement.click();
	        
	     /// Switch to alert message
	        Alert alert = driver.switchTo().alert();
	        Thread.sleep(1000);

	        // Print alert message
	        System.out.println("Gui_message: " + alert.getText());

	        // Accept (click OK) to close the alert
	        alert.accept();
}

@Test(priority = 3)
public void Sending_Message_Using_newSender_Id() throws InterruptedException{
//	driver.get("https://10.0.1.210:3002/bmp/auth/login");
 setUp(); // to perfom the action ie logging into the client gui 
//	driver.manage().window().maximize();
	//	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("pankaj");
		//driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj123");
		//driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();
		//driver.manage().window().maximize();
		//driver.findElement(By.id(browser)
		
	Thread.sleep(3000);	
    driver.findElement(By.xpath("//div[@id='senderID']")).click(); //click on sender id icon 
	driver.findElement(By.xpath("//li[normalize-space()='Automation1872']")).click(); // select the sender id
	Thread.sleep(3000);
	
	driver.findElement(By.id("mui-4")).sendKeys("9748745"); // entering the rescipeint details
	driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hello"); // typing the message 
	driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();// click on send
	driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click(); // to handle the prompt message 
	
}
@Test(priority = 4)// for this case the cron should be running in every 2 mins
public void to_send_sender_id_removal_request() throws InterruptedException{
	//setUp();
	Thread.sleep(3000);
	driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/img[1]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[normalize-space()='Preferences']")).click(); //click on the preferences
	Thread.sleep(3000);
	driver.manage().window().maximize();
	driver.findElement(By.xpath("//i[@id='dropdownMenuButton445']")).click();
	driver.findElement(By.xpath("//div[@class='dropdown-menu show']//span[@class='mb-5'][normalize-space()='Remove']")).click();
}

@Test(priority = 5)// to reject an sender_id reuqest
public void sender_id_rejection() throws InterruptedException{
adding_sender_id(); // to send a sender_id request
	Thread.sleep(3000);	
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
    WebElement optionElement = driver.findElement(By.xpath("//div[contains(text(), 'Sender ID')]")); // clicks on sender_id
    // Replace 'Desired Option' with the actual text or locator of the option
    optionElement.click();
    Thread.sleep(2000);// Wait for the options to appear (you might need to adjust the wait time or use WebDriverWait)

    WebElement iframeElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));//main iframe
	 // Perform the click action on the input element
	   
     driver.switchTo().frame(iframeElement);
     iframeElement.click();
	 driver.findElement(By.xpath("//input[@title='Click here to Reject']")).click();
	 driver.findElement(By.xpath("//textarea[@id='rejectReason']")).sendKeys("Sample");
	 driver.findElement(By.xpath("//button[@id='confirmReject']")).click();
	/// Switch to alert message
     Alert alert = driver.switchTo().alert();
     Thread.sleep(1000);

     // Print alert message
     System.out.println("Gui_message: " + alert.getText());

     // Accept (click OK) to close the alert
     alert.accept();
	
}

@AfterClass
public void setUps() {
//	driver.quit();
}
}