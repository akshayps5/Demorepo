package SMSC;

	import java.time.Duration;
	import org.openqa.selenium.NoSuchElementException;

	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	//import dev.failsafe.internal.util.Assert;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.NoAlertPresentException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	//import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class createclinetbmp {
	public static String browser = "Firefox";
	public static FirefoxOptions options;


		public static void main(String[] args) throws Exception {
			WebDriver driver = null;
			if (browser.equals("Firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.setAcceptInsecureCerts(true);
				options.setPageLoadTimeout(Duration.ofMinutes(1));
				driver = new FirefoxDriver(options);
			}
			else if(browser.equals("Chrome"))
			{
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				options.setPageLoadTimeout(Duration.ofMinutes(1));
				driver = new ChromeDriver(options);
			}
			else
			{
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.setAcceptInsecureCerts(true);
				options.setPageLoadTimeout(Duration.ofMinutes(1));
				driver = new EdgeDriver(options);
				
			}
			
			
			driver.get("https://10.0.6.115:8443/bmp/");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[2]/td[2]/input")).sendKeys("superadmin");
			driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[3]/td[2]/input")).sendKeys("superadmin");
			driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[2]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[5]/td/input")).click();
			//driver.findElement(By.id(browser)
			driver.findElement(By.xpath("/html/body/div[1]/div[3]")).click();
			driver.findElement(By.cssSelector("body > div:nth-child(9) > div:nth-child(3)")).click();
			
			driver.switchTo().frame(0);
			try {
	        // Wait for and interact with the search box
	        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement searchBox = waitt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='searchBoxText']")));
	        searchBox.sendKeys("Xavier");
	        

	        // Wait for and click the image element
	        WebElement plusminus60Img = waitt.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table[2]/tbody/tr[113]/td[1]/a/img")));
	        plusminus60Img.click();

	        // Wait for and click the delete button
	        WebElement deleteButton = waitt.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table[2]/tbody/tr[114]/td/div/table[2]/tbody/tr/td/div/table/tbody/tr[2]/td[2]/table/tbody/tr[5]/td/input[2]")));
	        deleteButton.click();
	        
	        
	            // Handle the alert
	            Alert alertt = driver.switchTo().alert();
	            String alertText = alertt.getText();
	            System.out.println("Alert text: " + alertText);
	            alertt.accept(); // Or alert.dismiss(); if needed

	            // Wait for and retrieve the success message
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='succmsg']")));
	            String actualOutput = successMessageElement.getText();
	            System.out.println("Success message: " + actualOutput);
	            
	            // Verify the message
	            String expectedOutput = "Deletion Successful";
	            Assert.assertTrue(actualOutput.contains(expectedOutput), "Deletion Unsuccessful");

	        } catch (NoAlertPresentException e) 
			{
	            System.out.println("No alert present."); 
			}
			driver.switchTo().defaultContent();
			//driver.findElement(By.xpath("/html/body/div[1]/div[3]")).click();
			//driver.findElement(By.cssSelector("body > div:nth-child(9) > div:nth-child(3)")).click();
			driver.switchTo().frame(0);
			driver.findElement(By.xpath("//u[normalize-space()='Add']")).click();
			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("Xavier");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Xavi@1234");
			driver.findElement(By.xpath("//input[@name='throughput']")).sendKeys("1");
			driver.findElement(By.xpath("//input[@name='maxthroughput']")).sendKeys("10");
			driver.findElement(By.xpath("//input[@name='ccEmail']")).sendKeys("xavier@gmail.com");
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			WebElement dropdownElement = driver.findElement(By.id("senderId"));

			//Create a Select object
			Select senderIdDropdown = new Select(dropdownElement);
			senderIdDropdown.selectByVisibleText("t457");
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			WebElement dropsubtypElement = driver.findElement(By.id("subscription"));
			Select subscriptionDropdown = new Select(dropsubtypElement);
			subscriptionDropdown.selectByVisibleText("Postpaid");
			//System.out.println("out side");
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.xpath("//input[@name='BillingNumber']")).sendKeys("10203020");
			driver.findElement(By.xpath("//input[@name=\"custAcctNo\"]")).sendKeys("10abc");
			driver.findElement(By.xpath("//input[@name='distListSupport']")).click();
			driver.findElement(By.xpath("//input[@name='fileFlag']")).click();
			driver.findElement(By.xpath("//input[@name='RetryEnabled']")).click();
			driver.findElement(By.xpath("//input[@name='transPts']")).click();
			driver.findElement(By.xpath("//input[@name='multiBooster']")).click();
			driver.findElement(By.xpath("//input[@name='detRep']")).click();
			driver.findElement(By.xpath("//input[@name='corpFlag']")).click();
			driver.findElement(By.xpath("//input[@name='apiSupport']")).click();
			driver.findElement(By.xpath("//input[@name='carryFwdSupport']")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			WebElement dropbasepaElement = driver.findElement(By.id("basePackages"));
			Select basepackDropdown = new Select(dropbasepaElement);
			basepackDropdown.selectByVisibleText("tayana4");
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.xpath("//input[@name='AddBasicButton']")).click(); 
			
			try {
	            // Handle the alert
	            Alert alert = driver.switchTo().alert();
	            String alertText = alert.getText();
	            System.out.println("Alert text: " + alertText);
	            alert.accept(); // Or alert.dismiss(); if needed

	            // Wait for and retrieve the success message
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='succmsg']")));
	            String actualOutput = successMessageElement.getText();
	            System.out.println("Success message: " + actualOutput);
	            
	            // Verify the message
	            String expectedOutput = "Addition Successful,Pack Purchase Success";
	            Assert.assertTrue(actualOutput.contains(expectedOutput), "Addition Unsuccessful, User Name already exists");

	        } catch (NoAlertPresentException e) {
	            System.out.println("No alert present.");
	        }
			driver.switchTo().defaultContent();
			
			driver.get("https://10.0.1.210:3002/bmp/");
			driver.manage().window().maximize();
			driver.findElement(By.name("username")).sendKeys("Xavier");
			driver.findElement(By.name("password")).sendKeys("Xavi@1234");
			driver.findElement(By.xpath("//*[@id=\"kt_sign_in_submit\"]")).click();
			WebDriverWait waiit = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement inputNumbersLabel = waiit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div/form[2]/div/div/div/div/input")));
			inputNumbersLabel.sendKeys("9745612");
			driver.findElement(By.xpath("//*[@id=\"message\"]")).sendKeys("Hi hello this message is from automation");
			driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div/div[2]/div[1]/div/div/div/div[2]/div/div[2]/button/span")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/div/div[3]/button[2]")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/div/div[3]/button[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div/div[7]/a/span[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[2]")).click();
			//driver.findElement(By.xpath("//td[normalize-space()='Delivered']")).isDisplayed();
			try {
	            boolean deliveredDisplayed = driver.findElement(By.xpath("//td[normalize-space()='Delivered']")).isDisplayed();
	            boolean failedDisplayed = driver.findElement(By.xpath("//td[normalize-space()='Failed']")).isDisplayed();

	            if (deliveredDisplayed) {
	                System.out.println("The message is'Delivered' .");
	            } else {
	                System.out.println("'Delivered' element is not present on the page.");
	            }

	            if (failedDisplayed) {
	                System.out.println("The message is 'Failed'");
	            } else {
	                System.out.println("'Failed' element is not present on the page.");
	            }
	        } catch (NoSuchElementException ex) {
	            //System.out.println("One or both elements ('Delivered' or 'Failed') were not found on the page.");
	            // Check if 'Delivered' element is not found
	            try {
	                driver.findElement(By.xpath("//td[normalize-space()='Delivered']")).isDisplayed();
	                System.out.println("The message is'Delivered'");
	            } catch (NoSuchElementException e) {
	                System.out.println("'Delivered' element is not present on the page.");
	            }
	            // Check if 'Failed' element is not found
	            try {
	                driver.findElement(By.xpath("//td[normalize-space()='Failed']")).isDisplayed();
	                System.out.println("The message is 'Failed'");
	            } catch (NoSuchElementException e) {
	                System.out.println("'Failed' element is not present on the page.");
	            }
	        }

	        System.out.println("full end to end is complete client creation and sending the message ");
	        
	        
	        
	        Thread.sleep(3000);
	        driver.close();
		}

	}

