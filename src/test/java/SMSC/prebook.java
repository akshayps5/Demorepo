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
	 

	 
	 
	public class prebook
	{
		public static   WebDriver driver;
		@BeforeClass
		// logic to login one time in the browser and execute all test cases within the  browser
		public void setUp() throws InterruptedException {
			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
			prebook.driver= driver;
//			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
	 
	 
			 driver.get("http://10.0.0.174:8080/tssgui/welcome/jsp/login.jsp");
	 
//			driver.manage().window().maximize();
				driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin"); //entering the username
				driver.findElement(By.xpath("//*[@id=\"passdiv\"]/div/input")).sendKeys("admin@123");//entering the password
			    driver.findElement(By.xpath("//*[@id=\"subBtn\"]")).click();//performing the click action
			//	driver.manage().window().maximize();
				//driver.findElement(By.id(browser)
			Thread.sleep(2000);	
		}
		@Test
		public void KYCFORM() throws InterruptedException {
		
		    // Timeout after 10 seconds
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        // Replace this XPath with the appropriate title if it's not empty
			 
			 WebElement oButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/div[2]/div/form/div[3]/div/div[2]/ul/li[4]/a/b")));
			 oButton.click();
			 
			 
			 
	        WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='']")));
	        goButton.click();

	        // Click the second button after waiting for it to be clickable
	        WebElement secondButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".box-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1)")));
	        secondButton.click();

	        // Wait for the modal to appear and then click on it
	       WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ModalToDisplayPreBook']")));
	    modal.click();

	        // Wait for the dropdown to become clickable
	       WebElement docTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='preBookDocType']")));
	        docTypeDropdown.click();

	        // Selecting 'PASSPORT' from dropdown
	        Select documentSelect = new Select(docTypeDropdown);
	        documentSelect.selectByVisibleText("PASSPORT");
	        
	        driver.findElement(By.xpath("//*[@id=\"preBookFilterVal\"]")).sendKeys("E00007730");
	        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/form/div/section/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div/span/i")).click();
	        Thread.sleep(30000);
	        
	        
	        driver.findElement(By.id("checkbox_0")).click();
	        
	        
	    }
		
	@AfterClass
	public void demo()
	{
		
	}
	}

