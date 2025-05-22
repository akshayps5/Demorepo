package imlyca;

	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import io.github.bonigarcia.wdm.WebDriverManager;

	import java.time.Duration;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Scanner;

	import javax.swing.JOptionPane;

	public class monika {
		private WebDriver driver;
		private Map<String, Object> vars;
		JavascriptExecutor js;

		@BeforeClass
		public void setUp() {
			//WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			  driver = new FirefoxDriver();
			js = (JavascriptExecutor) driver;
			vars = new HashMap<String, Object>();
			driver.get("http://10.0.6.15:8080/tssgui/");
			driver.manage().window().setSize(new Dimension(1280, 672));
			WebElement usernameField = driver.findElement(By.id("username"));
			usernameField.click();
			usernameField.sendKeys("admin");

			WebElement passwordField = driver.findElement(By.name("password"));
			passwordField.sendKeys("Admin@123");

			driver.findElement(By.id("subBtn")).click();	
		}

		@AfterClass
		public void tearDown() {
			//if (driver != null) {
			//	driver.quit();
			
		}

		@Test (priority = 1, enabled=false)
		public void sRM() throws InterruptedException {

			driver.findElement(By.xpath("//b[contains(.,'Package Details')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='SIMselBtn']")).click();
				Thread.sleep(3000);
				//*[@id="msgDisp"]/div/div
				WebElement errorMessageElement = driver.findElement(By.xpath("//*[@id='msgDisp']/div/div"));
				String actualOutput = errorMessageElement.getText();
				System.out.println(actualOutput);
				String expectedOutput = "Plan details sync up successfull";
				Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message Failed to Sync up plan details");
				Thread.sleep(2000);
				
			}
		}
			
		@Test (priority = 2, enabled=false)
		
		public void simtype() throws InterruptedException {
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//b[contains(.,'Sim Type Creation')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//u[contains(.,'SIM Type Addition')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@id='commoditySelect']/select")).click();
				Thread.sleep(1000);
				Select dropdown = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown.selectByVisibleText("SIM");
				// Thread.sleep(10000);

				// driver.findElement(By.xpath("//select[@id='commodityType']")).click();
				WebElement simTypeField = driver.findElement(By.id("simType"));
				simTypeField.click();
				simTypeField.sendKeys("Syscom 3G");
				Thread.sleep(1000);

				WebElement memoryField = driver.findElement(By.id("memory"));
				memoryField.click();
				memoryField.sendKeys("64");
				Thread.sleep(1000);

				WebElement networkSupportField = driver.findElement(By.id("networkSupport"));
				networkSupportField.click();
				networkSupportField.sendKeys("4G");

				WebElement sizeField = driver.findElement(By.id("size"));
				sizeField.click();
				sizeField.sendKeys("3G");
				// Thread.sleep(1000);

				WebElement volteSupportField = driver.findElement(By.id("volteSupport"));
				volteSupportField.click();
				volteSupportField.sendKeys("Enabled");

				WebElement profileField = driver.findElement(By.id("profile"));
				profileField.click();
				profileField.sendKeys("4G");
				Thread.sleep(1000);

				driver.findElement(By.xpath("//button[@name='AddSimType']")).click();
				Thread.sleep(2000);

				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(2000);
				WebElement errorMessageElement = driver.findElement(By.xpath("//*[@id='msgDisp']/div/div"));
				String actualOutput = errorMessageElement.getText();
				System.out.println(actualOutput);
				String expectedOutput = "SIM Type Creation Successful!";
				Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message Failed to create a SIM type name. Already exist");
				Thread.sleep(2000);
				//*[@id="msgDisp"]/div/div
				
			}
		}
		@Test (priority =3, enabled=false)
		public void mappUpload() throws InterruptedException {
		
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(1000);

			driver.findElement(By.xpath("//b[contains(.,'Mapped Inventory Upload')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//u[contains(.,'Mapped Inventory Upload')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@id='commoditySelect']/select")).click();
				Thread.sleep(1000);
				Select dropdown = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown.selectByVisibleText("SIM");

				WebElement pkgField = driver.findElement(By.id("pkgDet"));
				pkgField.click();
				pkgField.sendKeys("4 GB DATA");
				Thread.sleep(1000);

				WebElement simField = driver.findElement(By.id("simTypeDet"));
				simField.click();
				simField.sendKeys("Syscom 3G");
				Thread.sleep(1000);

				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("document.getElementById('cisFile').style.display='block';");
				WebElement fileField = driver.findElement(By.id("cisFile"));
				// fileField.click();
				fileField.sendKeys("C:\\Users\\Monika\\Downloads\\MapUpload_2.txt");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@name='AddCIS']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(2000);
			}
		}
		
		@Test (priority = 4, enabled=false)
		public void unmap() throws InterruptedException {	
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);

			driver.findElement(By.xpath("//b[contains(.,'Unmapped Inventory Upload')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//u[contains(.,'Unmapped Inventory Upload')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@id='commoditySelect']/select")).click();
				Thread.sleep(1000);
				Select dropdown = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown.selectByVisibleText("SIM");
				
				driver.findElement(By.xpath("//div[@id='commoditySelect']/select")).click();
				Thread.sleep(1000);
				Select dropdown1 = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown1.selectByVisibleText("eSIM");
				
				driver.findElement(By.xpath("//div[@id='commoditySelect']/select")).click();
				Thread.sleep(1000);
				Select dropdown2 = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown2.selectByVisibleText("SIM");
				
				WebElement simField = driver.findElement(By.id("simTypeDet"));
				simField.click();
				simField.sendKeys("Syscom 3G");
				Thread.sleep(1000);

				WebElement pkgField = driver.findElement(By.id("pkgDet"));
				pkgField.click();
				pkgField.sendKeys("4 GB DATA");
				Thread.sleep(1000);
				
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("document.getElementById('cisFile').style.display='block';");
				WebElement fileField = driver.findElement(By.id("cisFile"));
				// fileField.click();
				fileField.sendKeys("C:\\Users\\Monika\\Downloads\\MapUpload_2.txt");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@name='AddCIS']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(2000);
			}
		}
		@Test (priority = 5, enabled=false)
		public void freeMsisdn() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			/*{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}*/
			Thread.sleep(2000);
			driver.findElement(By.xpath("//b[contains(.,'Free Msisdn Upload')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//u[contains(.,'Msisdn Upload')]")).click();
				Thread.sleep(1000);
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("document.getElementById('msisdnFile').style.display='block';");
				WebElement fileField = driver.findElement(By.id("msisdnFile"));
				// fileField.click();
				fileField.sendKeys("C:\\Users\\akshay.ps\\Downloads\\TransferpointsApedited.txt");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@name='AddMsisdn']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(2000);	
			}
		}
		@Test (priority = 6)
		public void freeMsisdn1() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//b[contains(.,'Free Msisdn Upload')]")).click();
			
				//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//u[contains(.,'Generate Free MSISDN')]")).click();
				Thread.sleep(1000);
				//driver.findElement(By.xpath("//div[@id='msisdnGenerate']/div[2]/span[2]/input")).click();
				WebElement pkgField = driver.findElement(By.id("msisdnCnt"));
				pkgField.click();
				pkgField.sendKeys("1");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[contains(.,'Generate')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(2000);	
			
			driver.findElement(By.xpath("//b[contains(.,'Free Msisdn Upload')]")).click();
	 
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[contains(.,'Generate Free MSISDN')]"))).click();
	 
			WebElement pkgField1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("msisdnCnt")));
			pkgField1.click();
			pkgField1.clear();
			pkgField1.sendKeys("1");
			pkgField1.sendKeys(Keys.TAB);
			WebElement generateBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Generate')]")));
			generateBtn.click();
	        Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Yes')]"))).click();
			Thread.sleep(2000);
	 
		}
		@Test (priority = 7, enabled=false)
		public void msisdnMapping() throws InterruptedException{
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000); 
			driver.findElement(By.xpath("//b[contains(.,'Mapping MSISDN')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//u[contains(.,'Mapping Msisdn to Unmap SIM Inventory')]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//select[@id='MappingType']")).click();
				Thread.sleep(1000);
				Select dropdown1 = new Select(driver.findElement(By.id("MappingType")));
				dropdown1.selectByVisibleText("SIM No & MSISDN");
				Thread.sleep(2000);
			}
			
		}
		
		@Test (priority = 8, enabled=false)
		public void pkgLabel() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);

			driver.findElement(By.xpath("//b[contains(.,'Package Labeling')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@id='commoditySelect']/select")).click();
				Thread.sleep(1000);
				Select dropdown = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown.selectByVisibleText("SIM");

				WebElement simField = driver.findElement(By.id("simType"));
				simField.click();
				simField.sendKeys("TestSim1");
				Thread.sleep(1000);

				WebElement pkgField = driver.findElement(By.id("pkg"));
				pkgField.click();
				pkgField.sendKeys("4 GB DATA");
				Thread.sleep(1000);

				WebElement QuantityField = driver.findElement(By.id("Quantity"));
				QuantityField.click();
				QuantityField.sendKeys("1");
				Thread.sleep(1000);

				driver.findElement(By.xpath("//button[contains(.,'Get SIM Details')]")).click();
				Thread.sleep(2000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
			    js.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels
			    
				driver.findElement(By.xpath("//table[@id='CommPkgingDetTab']/tbody/tr/td[7]/input")).click();
				Thread.sleep(2000);

				driver.findElement(By.xpath("//button[contains(.,'Generate')]")).click();
				Thread.sleep(2000);

				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(5000);
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
			    js1.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels

			}
		}
		
		
		@Test (priority = 9, enabled=false)
		public void approval() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);

			{   
			    driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul[2]/li[4]/a")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='signoutDiv']/a")).click();
			    Thread.sleep(1000);
			    WebElement usernameField = driver.findElement(By.id("username"));
				usernameField.click();
				usernameField.sendKeys("approv");

				WebElement passwordField = driver.findElement(By.name("password"));
				passwordField.sendKeys("Approv@1234");

				driver.findElement(By.id("subBtn")).click();
				
				Thread.sleep(1000);
				driver.findElement(By.xpath("//b[contains(.,'Approval')]")).click();
				{
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
					Thread.sleep(2000);	
					driver.findElement(By.xpath("//table[@id=\'simTypeTab\']/tbody/tr[2]/td[5]/a")).click();
				    {
				      WebElement element = driver.findElement(By.tagName("body"));
				      Actions builder = new Actions(driver);
				      builder.moveToElement(element, 0, 0).perform();
				    }
					//WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
					//WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='simTypeTab']/tbody/tr[2]/td[5]/a")));
				    driver.findElement(By.id("verAcntPwd")).click();
				    Thread.sleep(1000);
				    driver.findElement(By.cssSelector("#simTypeTab_filter .form-control")).sendKeys("approv");
				    driver.findElement(By.id("verAcntPwd")).sendKeys("Approv@1234");
				    Thread.sleep(1000);
				    driver.findElement(By.xpath("//div[@id='verModal']/div[2]/div/textarea")).click();
				    Thread.sleep(1000);
				    driver.findElement(By.xpath("//div[@id='verModal']/div[2]/div/textarea")).sendKeys("Test");
				    Thread.sleep(1000);
				    
				    //driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				    //Thread.sleep(1000);
				    //driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				    Thread.sleep(1000);
				    //JavascriptExecutor js = (JavascriptExecutor) driver;
				    //js.executeScript("window.scrollBy(500,0)"); // Scrolls down by 500 pixels
				    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				    Thread.sleep(1000);
				    driver.findElement(By.cssSelector(".confirm")).click();
				    Thread.sleep(1000);
				    driver.findElement(By.cssSelector(".box-header")).click();
				    Thread.sleep(2000);
				}
			}
		}
		
		@Test (priority = 10, enabled=false)
		
		public void pkgConfirm1() throws InterruptedException{
			{   
			    //driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul[2]/li[4]/a")).click();
			    //Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='signoutDiv']/a")).click();
			    Thread.sleep(1000);
			    WebElement usernameField = driver.findElement(By.id("username"));
				usernameField.click();
				usernameField.sendKeys("admin");

				WebElement passwordField = driver.findElement(By.name("password"));
				passwordField.sendKeys("Admin@123");

				driver.findElement(By.id("subBtn")).click();
				
				Thread.sleep(5000);
			}
		}
		@Test (priority = 11, enabled=false)
		public void pkgConfirm() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//b[contains(.,'Package Confirmation')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
				Thread.sleep(2000);
				/*driver.findElement(By.xpath("//input[@id='BATCH_PR_Apr25_147']")).click();*/
				List<WebElement> checkboxes = driver.findElements(By.xpath("//input[starts-with(@id, 'BATCH_PR_Apr25_')]"));

			    if (!checkboxes.isEmpty()) {
			        checkboxes.get(0).click(); 
			    } else {
			        System.out.println("No matching checkboxes found.");
			    }
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    js.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels

				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='SIMselBtn']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(2000);
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(160));
				WebElement confirmationElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(
				    By.xpath("//div[contains(@class, 'alert-success')]")
				));

				String actualOutput = confirmationElement.getText();
				System.out.println("Actual output: " + actualOutput);

				String expectedOutput = "Confirmation Successful For Batch";
				Assert.assertTrue(actualOutput.contains(expectedOutput), "Confirmation message mismatch");
				Thread.sleep(2000);
				
			}
		}
		
		@Test (priority = 12, enabled=false)
		public void bundling() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//b[contains(.,'Bundling')]")).click();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels
		    
		 String userInput = JOptionPane.showInputDialog("Please enter SIM No:");
	 
		 if (userInput != null && !userInput.trim().isEmpty()) {
		     WebElement searchField = driver.findElement(By.name("searchsim"));
		     searchField.sendKeys(userInput);
		 } else {
		     System.out.println("No input provided. Exiting or skipping input.");
		    
		 }
	     
		 String userInput1 = JOptionPane.showInputDialog("Please enter bundlesize:");
	 
		 if (userInput1 != null && !userInput1.trim().isEmpty()) {
		     WebElement searchField = driver.findElement(By.name("bundlesize"));
		     searchField.sendKeys(userInput1);
		 } else {
		     System.out.println("No input provided. Exiting or skipping input.");
		    
		 }
			driver.findElement(By.xpath("//button[contains(.,'Search')]")).click();
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels
			driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
			Thread.sleep(2000);
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("window.scrollBy(500,0)"); // Scrolls up by 500 pixels
		}

		@Test (priority = 13, enabled=false)
		public void qrCode() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//b[contains(.,'QR Upload')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//u[contains(.,'QR Upload')]")).click();
			Thread.sleep(2000);
			
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("document.getElementById('QRFile').style.display='block';");
			WebElement fileField = driver.findElement(By.id("QRFile"));
			// fileField.click();
			fileField.sendKeys("C:\\Users\\Monika\\Downloads\\sample_qr_codes.zip");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(.,'Upload')]")).click();
			Thread.sleep(2000);	
			//driver.findElement(By.xpath("//button[@name='ADDQR']")).click();
			//Thread.sleep(2000);
			//driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
		}
		@Test (priority = 14, enabled=false)
		public void stockTrans() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul[2]/li[4]/a")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//div[@id='signoutDiv']/a")).click();
		    Thread.sleep(1000);
		    WebElement usernameField = driver.findElement(By.id("username"));
			usernameField.click();
			usernameField.sendKeys("MFS");

			WebElement passwordField = driver.findElement(By.name("password"));
			passwordField.sendKeys("mfs@123");

			driver.findElement(By.id("subBtn")).click();
			
			Thread.sleep(1000);
		}
		@Test (priority = 15, enabled=false)
		public void stockPullBack() throws InterruptedException{
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);
			
		}
	}


