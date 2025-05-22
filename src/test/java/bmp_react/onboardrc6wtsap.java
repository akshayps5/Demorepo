package bmp_react;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.support.ui.FluentWait;
	import java.time.Duration;
	import org.testng.Assert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;
	 
	import java.awt.*;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;

	public class onboardrc6wtsap {
		
		WebDriver driver;
	    FluentWait<WebDriver> wait;
	    @BeforeMethod
	    public void setUp() {
	        driver = new FirefoxDriver();  // Initialize only once
	        driver.manage().window().maximize();
	        
	    }
			
			@Test(priority = 1, enabled = true)
		    public void register() throws InterruptedException, AWTException {
		    //	driver = new FirefoxDriver();
		    	Thread.sleep(2000);
		    driver.get(dataprovideronboardrc6.IP);
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@id=\"logindiv\"]/div/div/div/div[2]/div/div[1]/div[3]/form/div[5]/h6/span")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@id=\":r3:\"]")).sendKeys(dataprovideronboardrc6.msisdn);
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//span[@class='indicator-label']")).click();
		    Thread.sleep(2000);
		    String otp = otphandle.multi_factor_auth();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement otptext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\":r5:\"]")));otptext.sendKeys(otp);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\":rb:\"]")).sendKeys(dataprovideronboardrc6.name);
			Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@id=\":rc:\"]")).sendKeys("harsha.m@tayana.in");
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@id=\":rd:\"]")).sendKeys("Tayana");
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@id=\":re:\"]")).sendKeys("MG road Bangalore");
		    Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\":rf:\"]")).sendKeys("Tayana");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"logindiv\"]/div/div/div/div[2]/div/div[1]/div[2]/div/form/div[6]/div[2]/button")).click();
	        Thread.sleep(2000);
		    // Use Robot to handle the file upload dialog
		    Robot robot = new Robot();

		    // Set file path to clipboard
		    String filePath = "C:\\Users\\Harsha\\Downloads\\TransferpointsApedited.txt";
		    StringSelection selection = new StringSelection(filePath);
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		    // Paste the file path (Ctrl + V)
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);

		    // Press Enter to confirm
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    Thread.sleep(3000);
		    
		   driver.findElement(By.xpath("//*[@id=\":rg:\"]")).sendKeys(dataprovideronboardrc6.senderID);
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[2]/div/form/div[8]/div[1]/span/input")).click();
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/button[2]")).click();
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[2]/div/form/button")).click();
		   Thread.sleep(3000);
		   
		   driver.quit();
		   

		}
			 @Test(priority = 2, enabled = true)
			    public void Accept_req_adminGUI() throws InterruptedException, AWTException {
			    	driver = new FirefoxDriver();
			    	Thread.sleep(2000);
			    driver.get(dataprovideronboardrc6.AdminIP);
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@id=\"tsslogin-form_username\"]")).sendKeys(dataprovideronboardrc6.Username);
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys(dataprovideronboardrc6.Password);
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div/div/div/form/div[4]/div/div/div/div/button")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[3]/a")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[6]/a")).click();
			  //*[@id="rightSectionDiv"]/section/ul/li[6]/a
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[5]/div/i[1]")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys(dataprovideronboardrc6.Adminpass);
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@id=\"confirmButton\"]")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/section/div/div/div[2]/div/div/div[2]/div/div[2]/button")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
			    Thread.sleep(9000);
			    
			 // Set the implicit wait once, typically after initializing the driver
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			 // Then continue with your operations
			    String link = resetlink.reset_link();   
			    driver.get(link);
			    
			    driver.findElement(By.xpath("//*[@id=\":r3:\"]")).sendKeys(dataprovideronboardrc6.ResetPass);
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@id=\":r4:\"]")).sendKeys(dataprovideronboardrc6.ConfirmPassword);
			    Thread.sleep(2000);
			    //driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[2]/form/button"));
			    driver.findElement(By.cssSelector(".indicator-label")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/button[2]")).click();
			    Thread.sleep(2000);
			    Thread.sleep(10000);
			    
			    
			 }
			 
			  @Test(priority = 3, enabled = false)
			   
			  public void Registar_mail() throws InterruptedException, AWTException {
				//  driver = new FirefoxDriver();
			    	Thread.sleep(2000);
			        
			        driver.get("https://outlook.office.com/");
			        driver.manage().window().maximize();
			        System.out.println("outlook Launched");
			        
			        
			        Thread.sleep(2000);
			        WebElement cltElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")));
			        //WebElement cltElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
			        cltElement.sendKeys("harsha.m@tayana.in");
					//String senderPassword = System.getenv("MAIL_PASSWORD");
					
//				     WebElement cltElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/input[1]")));
					 WebElement cltElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9")));
					 cltElement1.click(); // next button		
					 WebElement cltElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/input[1]")));
					 cltElement2.sendKeys("bangalore@60"); // password
					 WebElement cltElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[5]/div/div/div/div/input")));
					 cltElement4.click(); // next button
					 WebElement cltElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[1]/input")));
					 cltElement3.click(); // next button
					
					
//					 WebElement cltElement5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/input[1]")));
//					 cltElement5.click();
					 WebElement cltElement6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='topSearchInput']")));
					 cltElement6.click();	
					 //highlightElement(cltElement);
						cltElement6.sendKeys("Here is the link to Reset Your Password");
						cltElement6.sendKeys(Keys.ENTER);
						//Thread.sleep(4000);
						WebElement cltElement8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/button[2]/span[1]/span[1]/span[1]")));
						//highlightElement(cltElement);
						cltElement8.click();
						WebElement serchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/span[1]")));
						//highlightElement(serchElement);	
						serchElement.click();
						WebElement cltElement7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]")));
						//highlightElement(cltElement);
						cltElement7.click();
						driver.findElement(By.xpath("//*[@id=\":r3:\"]")).sendKeys("Password@04");
					    Thread.sleep(2000);
					    driver.findElement(By.xpath("//*[@id=\":r4:\"]")).sendKeys("Password@04");
					    Thread.sleep(2000);
					    //driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[2]/form/button"));
					    driver.findElement(By.cssSelector(".indicator-label")).click();
					    Thread.sleep(2000);
					    driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/button[2]")).click();
					    Thread.sleep(2000);
			    
			    
			    }
	      
			 @Test(priority = 4, enabled = true)
			    public void Login_to_registared_user() throws InterruptedException, AWTException {
			    //	driver = new FirefoxDriver();
			    	Thread.sleep(2000);
			    	driver.get(dataprovideronboardrc6.IP);
			        Thread.sleep(1000);
			        driver.findElement(By.xpath("//*[@id=\":r0:\"]")).sendKeys(dataprovideronboardrc6.name);
			        Thread.sleep(1000);
			        driver.findElement(By.xpath("//*[@id=\":r1:\"]")).sendKeys(dataprovideronboardrc6.ConfirmPassword);
			        Thread.sleep(1000);
			        driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button")).click();
			    }
			 
			 @Test(priority = 5, enabled = false)
			    public void MenuCreatiopn() throws InterruptedException, AWTException {
			    	//driver = new FirefoxDriver();
			    	Thread.sleep(2000);
			    driver.get(dataprovideronboardrc6.IP);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//*[@id=\":r0:\"]")).sendKeys(dataprovideronboardrc6.Pusername);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//*[@id=\":r1:\"]")).sendKeys(dataprovideronboardrc6.Ppassword);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/ul/a[2]/li/div")).click();
			    Thread.sleep(1000);
			    // Find the element
			    WebElement element = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div/span[3]/span[2]/div/div"));

			    // Create Actions instance
			    Actions actions = new Actions(driver);

			    // Perform right-click (context click)
			    actions.contextClick(element).perform();
			    
			    Thread.sleep(1000);

			    driver.findElement(By.cssSelector(".contexify_item:nth-child(1) > .contexify_itemContent")).click();
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[2]/span[3]/span[2]/div/div")).click();
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).clear();
			    Thread.sleep(500); // optional short wait to ensure field is cleared
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).sendKeys("English");
			    Thread.sleep(1000);
			    
			    // Find the element
			    WebElement element1 = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[2]/span[3]/span[2]/div/div"));

			    // Create Actions instance
			    Actions actions1 = new Actions(driver);

			    // Perform right-click (context click)
			    actions1.contextClick(element1).perform();
			    
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//div[@id='app']/div[3]/div/div/div[3]/div[2]/div")).click();
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[3]/span[3]/span[2]/div/div")).click();
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).clear();
			    Thread.sleep(500); // optional short wait to ensure field is cleared
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).sendKeys("Bill Payment");
			    Thread.sleep(1000);
			    
			    // Find the element
			    WebElement element11 = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[3]/span[3]/span[2]/div/div"));

			    // Create Actions instance
			    Actions actions11 = new Actions(driver);

			    // Perform right-click (context click)
			    actions11.contextClick(element11).perform();
			    
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//div[@id='app']/div[3]/div/div/div[3]/div[2]/div")).click();
			    Thread.sleep(1000); 
			    
			    driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[4]/span[3]/span[2]/div/div")).click();
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).clear();
			    Thread.sleep(500); // optional short wait to ensure field is cleared
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).sendKeys("Mobile recharge");
			    Thread.sleep(1000);
			    
			    driver.findElement(By.cssSelector("#\\:rm\\:")).sendKeys("payment status");
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//*[@id=\":rn:\"]")).sendKeys("mobile recharge successful");
			    Thread.sleep(2000);
			    
			    // Find the element
			    WebElement element111 = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[3]/span[3]/span[2]/div/div"));

			    // Create Actions instance
			    Actions actions111 = new Actions(driver);

			    // Perform right-click (context click)
			    actions111.contextClick(element111).perform();
			    
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//div[@id='app']/div[3]/div/div/div[3]/div[2]/div")).click();
			    Thread.sleep(1000); 
			   
			    driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[5]/span[3]/span[2]/div/div")).click();
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).clear();
			    Thread.sleep(500); // optional short wait to ensure field is cleared
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).sendKeys("Broadband Payment");
			    Thread.sleep(1000);
			    
			    driver.findElement(By.cssSelector("#\\:rm\\:")).sendKeys("payment status");
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//*[@id=\":rn:\"]")).sendKeys("Broadband Payment successful");
			    Thread.sleep(2000);
			    
			    // Find the element
			    WebElement element1111 = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[3]/span[3]/span[2]/div/div"));

			    // Create Actions instance
			    Actions actions1111 = new Actions(driver);

			    // Perform right-click (context click)
			   actions1111.contextClick(element1111).perform();
			    
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//div[@id='app']/div[3]/div/div/div[3]/div[2]/div")).click();
			    Thread.sleep(1000); 
			    
			    driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/div[6]/span[3]/span[2]/div/div")).click();
			    Thread.sleep(1000);
			    
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).clear();
			    Thread.sleep(500); // optional short wait to ensure field is cleared
			    driver.findElement(By.xpath("//*[@id=\":rk:\"]")).sendKeys("Electricity Bill");
			    Thread.sleep(1000);
			    
			    driver.findElement(By.cssSelector("#\\:rm\\:")).sendKeys("payment status");
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//*[@id=\":rn:\"]")).sendKeys("Electricity Bill successful");
			    Thread.sleep(2000);
			    
			    driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[1]/div[2]/button")).click();
			    Thread.sleep(1000);
			    
			    driver.findElement(By.cssSelector(".MuiIconButton-sizeMedium:nth-child(3) path")).click();
			    Thread.sleep(1000);
			   
			    }
//			 @AfterMethod
//			    public void tearDown() {
//			        if (driver != null) {
//			            driver.quit();
//			        }
//			    }
		
	}





