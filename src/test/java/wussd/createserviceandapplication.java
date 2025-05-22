package wussd;

	import java.net.URI;
	import java.time.Duration;
	import java.util.List;

	import org.bouncycastle.cert.ocsp.Req;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import wussd.dataproviderussd;
	import io.github.bonigarcia.wdm.WebDriverManager;



	public class createserviceandapplication {
		
		public static WebDriver driver ;
		public WebDriverWait wait ;
		public static String browser = "Firefox";
		
		 @BeforeMethod
		    public void waiting() {
		        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after driver
		    }
		@BeforeClass (enabled=true)
		 //   @Test
		public void login() throws InterruptedException {
			
			 System.out.println("Initializing WebDriver for Admin GUI");

		        if (browser.equalsIgnoreCase("Firefox")) {
		            WebDriverManager.firefoxdriver().setup();
		            FirefoxOptions options = new FirefoxOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new FirefoxDriver(options);
		        } else if (browser.equalsIgnoreCase("Chrome")) {
		            WebDriverManager.chromedriver().setup();
		            ChromeOptions options = new ChromeOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new ChromeDriver(options);
		        } else {
		            WebDriverManager.edgedriver().setup();
		            EdgeOptions options = new EdgeOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new EdgeDriver(options);
		        }

		        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		        // Launch Admin GUI
		        driver.get("https://" + dataproviderussd.host + ":" + dataproviderussd.port + "/wussd/");
		        driver.manage().window().maximize();
		        System.out.println("Admin GUI Launched");
		    
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).sendKeys(dataproviderussd.admin);
			driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys(dataproviderussd.adminpasss);
			driver.findElement(By.xpath("//button[@type='submit']")).click();

		}
		// ======================================================================================================================================
					@Test(priority = 1, enabled = true)
					public void delete_Application() throws InterruptedException {
							
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
						Applicationbutt.click();
//						WebElement serchtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='p-inputtext p-component']")));
//						serchtf.sendKeys(dataproviderussd.username);
//						WebElement elementToCheck = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]"));
	//
//						// If the element exists and matches, exit the loop
//						if (elementToCheck.isDisplayed()) {
//						    System.out.println("Element matches the given XPath. Exiting...");
//						} else {	
					int count =15;
					for (int i = 1 ; i<= count ; i++ ) {
					WebElement serchtf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='p-inputtext p-component']")));
					serchtf1.sendKeys(dataproviderussd.username);
					WebElement deletebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/*[name()='svg'][3]/*[name()='path'][1]")));
					deletebutton.click();
					WebElement addalertbutton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
					addalertbutton1.click();
					//i++;
					
					
					}	
					 Thread.sleep(2000); 
					
				}
		
		


		// ======================================================================================================================================
			@Test(priority = 2, enabled = true)
			public void Create_Apl_http_text_get() throws InterruptedException {
						
				WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
				sitemapElement.click();
				WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
				Applicationbutt.click();
				WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
				Addbutton.click();
				WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				Nametf.sendKeys(dataproviderussd.username+"1");
				WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
				URItypedropdown.click();
				WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
				httpuritype.click();
				WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
				req_method.click();
				WebElement reqdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
				reqdropdown.click();
				WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				hostname.sendKeys(dataproviderussd.host);
				WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				portnumber.sendKeys("8444");
				WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				usernametf.sendKeys(dataproviderussd.username+"1");
				WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				passwordtf.sendKeys("Abcd123");
				WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
				descriptiontf.sendKeys("hello automation testing");
				WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
				encodedropdown.click();
				WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Text')]")));
				encodeselect.click();
				WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
				addbutton.click();
				WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
				addalertbutton.click();
			}
			// ======================================================================================================================================
					@Test(priority = 3, enabled = true)
					public void Create_Apl_http_xml_get() throws InterruptedException {
						
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
						Applicationbutt.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						Nametf.sendKeys(dataproviderussd.username+"2");
						WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						URItypedropdown.click();
						WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
						httpuritype.click();
						WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						req_method.click();
						WebElement reqdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
						reqdropdown.click();
						WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						hostname.sendKeys(dataproviderussd.host);
						WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						portnumber.sendKeys("8444");
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						usernametf.sendKeys(dataproviderussd.username+"2");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						passwordtf.sendKeys("Abcd123");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("hello automation testing");
						WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						encodedropdown.click();
						WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='XML']")));
						encodeselect.click();
						WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
						addbutton.click();
						WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
						addalertbutton.click();
					}
					// ======================================================================================================================================
					@Test(priority = 4, enabled = true)
					public void Create_Apl_http_html_get() throws InterruptedException {
						
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
						Applicationbutt.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						Nametf.sendKeys(dataproviderussd.username+"3");
						WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						URItypedropdown.click();
						WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
						httpuritype.click();
						WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						req_method.click();
						WebElement reqdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
						reqdropdown.click();
						WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						hostname.sendKeys(dataproviderussd.host);
						WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						portnumber.sendKeys("8444");
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						usernametf.sendKeys(dataproviderussd.username+"3");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						passwordtf.sendKeys("Abcd123");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("hello automation testing");
						WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						encodedropdown.click();
						WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='HTML']")));
						encodeselect.click();
						WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
						addbutton.click();
						WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
						addalertbutton.click();
					}

					// ======================================================================================================================================
					@Test(priority = 5, enabled = true)
					public void Create_Apl_http_text_post() throws InterruptedException {
						
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
						Applicationbutt.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						Nametf.sendKeys(dataproviderussd.username+"4");
						WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						URItypedropdown.click();
						WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
						httpuritype.click();
						WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						req_method.click();
						WebElement reqdropdownpost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]")));
						reqdropdownpost.click();
						WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						hostname.sendKeys(dataproviderussd.host);
						WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						portnumber.sendKeys("8444");
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						usernametf.sendKeys(dataproviderussd.username+"4");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						passwordtf.sendKeys("Abcd123");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("hello automation testing");
						WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						encodedropdown.click();
						WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Text')]")));
						encodeselect.click();
						WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
						addbutton.click();
						WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
						addalertbutton.click();
					}
					// ======================================================================================================================================
		@Test(priority = 6, enabled = true)
		public void Create_Apl_http_xml_post() throws InterruptedException {
			
			WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
			sitemapElement.click();
			WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
			Applicationbutt.click();
			WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
			Addbutton.click();
			WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			Nametf.sendKeys(dataproviderussd.username+"5");
			WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			URItypedropdown.click();
			WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
			httpuritype.click();
			WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			req_method.click();
			WebElement reqdropdownpost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]")));
			reqdropdownpost.click();
			WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			hostname.sendKeys(dataproviderussd.host);
			WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			portnumber.sendKeys("8444");
			WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			usernametf.sendKeys(dataproviderussd.username+"5");
			WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			passwordtf.sendKeys("Abcd123");
			WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
			descriptiontf.sendKeys("hello automation testing");
			WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			encodedropdown.click();
			WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='XML']")));
			encodeselect.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
			addbutton.click();
			WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
			addalertbutton.click();
		}
		// ======================================================================================================================================
		@Test(priority = 7, enabled = true)
		public void Create_Apl_http_html_post() throws InterruptedException {
			
			WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
			sitemapElement.click();
			WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
			Applicationbutt.click();
			WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
			Addbutton.click();
			WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			Nametf.sendKeys(dataproviderussd.username+"6");
			WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			URItypedropdown.click();
			WebElement httpsuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
			httpsuritype.click();
			WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			req_method.click();
			WebElement reqdropdownpost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]")));
			reqdropdownpost.click();
			WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			hostname.sendKeys(dataproviderussd.host);
			WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			portnumber.sendKeys("8444");
			WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			usernametf.sendKeys(dataproviderussd.username+"6");
			WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			passwordtf.sendKeys("Abcd123");
			WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
			descriptiontf.sendKeys("hello automation testing");
			WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			encodedropdown.click();
			WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='HTML']")));
			encodeselect.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
			addbutton.click();
			WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
			addalertbutton.click();
		}
		
	      // ======================================================================================================================================
		@Test(priority = 8, enabled = true)
		public void Create_Apl_https_text_get() throws InterruptedException {
			
			WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
			sitemapElement.click();
			WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
			Applicationbutt.click();
			WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
			Addbutton.click();
			WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			Nametf.sendKeys(dataproviderussd.username+"7");
			WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			URItypedropdown.click();
			WebElement httpsuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'HTTPS')]")));
			httpsuritype.click();
			WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			req_method.click();
			WebElement reqdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
			reqdropdown.click();
			WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			hostname.sendKeys(dataproviderussd.host);
			WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			portnumber.sendKeys("8444");
			WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			usernametf.sendKeys(dataproviderussd.username+"7");
			WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			passwordtf.sendKeys("Abcd123");
			WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
			descriptiontf.sendKeys("hello automation testing");
			WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			encodedropdown.click();
			WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Text')]")));
			encodeselect.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
			addbutton.click();
			WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
			addalertbutton.click();
							}
							// ======================================================================================================================================
									@Test(priority = 9, enabled = true)
		public void Create_Apl_https_xml_get() throws InterruptedException {
			
			WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
			sitemapElement.click();
			WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
			Applicationbutt.click();
			WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
			Addbutton.click();
			WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			Nametf.sendKeys(dataproviderussd.username+"8");
			WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			URItypedropdown.click();
			WebElement httpsuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'HTTPS')]")));
			httpsuritype.click();
			WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			req_method.click();
			WebElement reqdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
			reqdropdown.click();
			WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			hostname.sendKeys(dataproviderussd.host);
			WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			portnumber.sendKeys("8444");
			WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			usernametf.sendKeys(dataproviderussd.username+"8");
			WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			passwordtf.sendKeys("Abcd123");
			WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
			descriptiontf.sendKeys("hello automation testing");
			WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			encodedropdown.click();
			WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='XML']")));
			encodeselect.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
			addbutton.click();
			WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
			addalertbutton.click();
		}
		// ======================================================================================================================================
		@Test(priority = 10, enabled = true)
		public void Create_Apl_https_html_get() throws InterruptedException {
			
			WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
			sitemapElement.click();
			WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
			Applicationbutt.click();
			WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
			Addbutton.click();
			WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			Nametf.sendKeys(dataproviderussd.username+"9");
			WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			URItypedropdown.click();
			WebElement httpsuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'HTTPS')]")));
			httpsuritype.click();
			WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			req_method.click();
			WebElement reqdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")));
			reqdropdown.click();
			WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			hostname.sendKeys(dataproviderussd.host);
			WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			portnumber.sendKeys("8444");
			WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			usernametf.sendKeys(dataproviderussd.username+"9");
			WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			passwordtf.sendKeys("Abcd123");
			WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
			descriptiontf.sendKeys("hello automation testing");
			WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			encodedropdown.click();
			WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='HTML']")));
			encodeselect.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
			addbutton.click();
			WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
			addalertbutton.click();
		}

									// ======================================================================================================================================
		@Test(priority = 11, enabled = true)
		public void Create_Apl_https_text_post() throws InterruptedException {
			
			WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
			sitemapElement.click();
			WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
			Applicationbutt.click();
			WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
			Addbutton.click();
			WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			Nametf.sendKeys(dataproviderussd.username+"10");
			WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			URItypedropdown.click();
			WebElement httpsuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'HTTPS')]")));
			httpsuritype.click();
			WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			req_method.click();
			WebElement reqdropdownpost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]")));
			reqdropdownpost.click();
			WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			hostname.sendKeys(dataproviderussd.host);
			WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			portnumber.sendKeys("8444");
			WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			usernametf.sendKeys(dataproviderussd.username+"10");
			WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			passwordtf.sendKeys("Abcd123");
			WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
			descriptiontf.sendKeys("hello automation testing");
			WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			encodedropdown.click();
			WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Text')]")));
			encodeselect.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
			addbutton.click();
			WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
			addalertbutton.click();
		}
		// ======================================================================================================================================
		@Test(priority = 12, enabled = true)
		public void Create_Apl_https_xml_post() throws InterruptedException {
			
			WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
			sitemapElement.click();
			WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
			Applicationbutt.click();
			WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
			Addbutton.click();
			WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			Nametf.sendKeys(dataproviderussd.username+"11");
			WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			URItypedropdown.click();
			WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'HTTPS')]")));
			httpuritype.click();
			WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			req_method.click();
			WebElement reqdropdownpost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]")));
			reqdropdownpost.click();
			WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			hostname.sendKeys(dataproviderussd.host);
			WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			portnumber.sendKeys("8444");
			WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			usernametf.sendKeys(dataproviderussd.username+"11");
			WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
			passwordtf.sendKeys("Abcd123");
			WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
			descriptiontf.sendKeys("hello automation testing");
			WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
			encodedropdown.click();
			WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='XML']")));
			encodeselect.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
			addbutton.click();
			WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
			addalertbutton.click();
		}
		// ======================================================================================================================================
		@Test(priority = 13, enabled = true)
		public void Create_Apl_https_html_post() throws InterruptedException {
												
				WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
				sitemapElement.click();
				WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
				Applicationbutt.click();
				WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
				Addbutton.click();
				WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				Nametf.sendKeys(dataproviderussd.username+"12");
				WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
				URItypedropdown.click();
				WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'HTTPS')]")));
				httpuritype.click();
				WebElement req_method = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
				req_method.click();
				WebElement reqdropdownpost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]")));
				reqdropdownpost.click();
				WebElement hostname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				hostname.sendKeys(dataproviderussd.host);
				WebElement portnumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				portnumber.sendKeys("8444");
				WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				usernametf.sendKeys(dataproviderussd.username+"12");
				WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				passwordtf.sendKeys("Abcd123");
				WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
				descriptiontf.sendKeys("hello automation testing");
				WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
				encodedropdown.click();
				WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='HTML']")));
				encodeselect.click();
				WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
				addbutton.click();
				WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
												addalertbutton.click();
				}
				

			// ======================================================================================================================================
			@Test(priority = 14, enabled = true)
			public void Create_Apl_smpp_xml() throws InterruptedException {
				
				WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
				sitemapElement.click();
				WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
				Applicationbutt.click();
				WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
				Addbutton.click();
				WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				Nametf.sendKeys(dataproviderussd.username+"13");
				WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
				URItypedropdown.click();
				WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='SMPP']")));
				httpuritype.click();
				WebElement smppappid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				smppappid.sendKeys("8444");
				WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				usernametf.sendKeys(dataproviderussd.username+"13");
				WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
				passwordtf.sendKeys("Abcd123");
				WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/textarea[1]")));
				descriptiontf.sendKeys("hello automation testing");
				WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]")));
				encodedropdown.click();
				WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'XML')]")));
				encodeselect.click();
				WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
				addbutton.click();
				WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
				addalertbutton.click();
			}
			// ======================================================================================================================================
					@Test(priority = 15, enabled = true)
					public void Create_Apl_smpp_text() throws InterruptedException {
						
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
						Applicationbutt.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						Nametf.sendKeys(dataproviderussd.username+"14");
						WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						URItypedropdown.click();
						WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='SMPP']")));
						httpuritype.click();
						WebElement smppappid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						smppappid.sendKeys("8444");
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						usernametf.sendKeys(dataproviderussd.username+"14");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						passwordtf.sendKeys("Abcd123");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/textarea[1]")));
						descriptiontf.sendKeys("hello automation testing");
						WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]")));
						encodedropdown.click();
						WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Text')]")));
						encodeselect.click();
						WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
						addbutton.click();
						WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
						addalertbutton.click();
					}
									// ======================================================================================================================================
					@Test(priority = 16, enabled = true)
					public void Create_Apl_smpp_html() throws InterruptedException {
						
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement Applicationbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]//div[1]//ul[1]//li[1]//a[1]")));
						Applicationbutt.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement Nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						Nametf.sendKeys(dataproviderussd.username+"15");
						WebElement URItypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						URItypedropdown.click();
						WebElement httpuritype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='SMPP']")));
						httpuritype.click();
						WebElement smppappid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						smppappid.sendKeys("8444");
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						usernametf.sendKeys(dataproviderussd.username+"15");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));
						passwordtf.sendKeys("Abcd123");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/textarea[1]")));
						descriptiontf.sendKeys("hello automation testing");
						WebElement encodedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/fieldset[1]")));
						encodedropdown.click();
						WebElement encodeselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'HTML')]")));
						encodeselect.click();
						WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[5]/button[1]")));
						addbutton.click();
						WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
						addalertbutton.click();
					}	
					
					// ======================================================================================================================================
					@Test(priority = 17, enabled = true)
					public void Create_Service_MI_del() throws InterruptedException {
						Thread.sleep(2000);
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement serviceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/div[1]/ul[1]/li[2]/a[1]")));
						serviceElement.click();
						Thread.sleep(2000);
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement typedrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						typedrpdwn.click();
						WebElement selectMIservice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'MI Service')]")));
						selectMIservice.click();
						WebElement appldrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//i[1]")));
						appldrpdwn.click();
						WebElement selectappl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Xavier1']")));
						selectappl.click();
						WebElement nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/input[1]")));
						nametf.sendKeys("Xavier mi_service_del");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("A_mi_service automation testing");
						WebElement respexptdrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						respexptdrpdwn.click();
						WebElement selectrespexp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Deliver Ack']")));
						selectrespexp.click();
						WebElement tagnametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf.sendKeys("message");
						WebElement tagdescprition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition.sendKeys("message");
						WebElement tagnametf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf1.sendKeys("message1");
						WebElement tagdescprition1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition1.sendKeys("message1");
						WebElement delbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/div[3]/i[1]")));
						delbutton.click();
						WebElement firsturlbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FirstURLPanel']//div[contains(@class,'card-header tss-panel-header tss-info-panel')]")));
						firsturlbutton.click();
						WebElement urlendpointtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/input[1]")));
						urlendpointtf.sendKeys("/cgi-bin/$GET_BODY$");
						WebElement bodytagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")));
						bodytagbutton.click();
						String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r"
						        + "<TayanaCompanyData>\r"
						        + "<Employees>\r"
						        + "<Total_number_employees></Total_number_employees>\r"
						        + "<Total_Female_emp></Total_Female_emp>\r"
						        + "<Total_Male_emp></Total_Male_emp>\r"
						        + "</Employees>\r"
						        + "<Products>\r"
						        + "<Total_number_products>_$message$_</Total_number_products>\r"
						        + "</Products>\r"
						        + "</TayanaCompanyData>\r";
						WebElement firsturlbodytf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/textarea[1]")));
						firsturlbodytf.sendKeys(xmlData);
						WebElement headtagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FirstURLBody']//div//a[@title='Header'][normalize-space()='Header']")));
						headtagbutton.click();
						WebElement checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[3]/input[1]")));
						checkbox1.click();
						WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[8]/input[1]")));
						checkbox2.click();
						WebElement checkbox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[13]/input[1]")));
						checkbox3.click();									
						WebElement contiunebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]")));
						contiunebutton.click();
						Thread.sleep(2000);
						contiunebutton.click();
						WebElement Addbutton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//div[@id='rightSectionDiv']//div//section[contains(@class,'content')]//button[1]")));
						Addbutton2.click();
					//	WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
					//	addalertbutton.click();
					WebElement Addbutton3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[6]/button[1]")));
						Addbutton3.click();
						
						}
					
					// ======================================================================================================================================
					@Test(priority = 18, enabled = true)
					public void Create_Service_MI_sub() throws InterruptedException {
						Thread.sleep(2000);
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement serviceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/div[1]/ul[1]/li[2]/a[1]")));
						serviceElement.click();
						Thread.sleep(2000);
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement typedrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						typedrpdwn.click();
						WebElement selectMIservice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'MI Service')]")));
						selectMIservice.click();
						WebElement appldrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//i[1]")));
						appldrpdwn.click();
						WebElement selectappl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Xavier2']")));
						selectappl.click();
						WebElement nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/input[1]")));
						nametf.sendKeys("Xavier mi_service_sub");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("A_mi_service automation testing");
						WebElement respexptdrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						respexptdrpdwn.click();
						WebElement selectrespexp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Submit']")));
						selectrespexp.click();
						WebElement tagnametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf.sendKeys("message");
						WebElement tagdescprition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition.sendKeys("message");
						WebElement tagnametf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf1.sendKeys("message1");
						WebElement tagdescprition1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition1.sendKeys("message1");
						WebElement delbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/div[3]/i[1]")));
						delbutton.click();
						WebElement firsturlbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FirstURLPanel']//div[contains(@class,'card-header tss-panel-header tss-info-panel')]")));
						firsturlbutton.click();
						WebElement urlendpointtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/input[1]")));
						urlendpointtf.sendKeys("/cgi-bin/$GET_BODY$");
						WebElement bodytagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")));
						bodytagbutton.click();
						String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r"
						        + "<TayanaCompanyData>\r"
						        + "<Employees>\r"
						        + "<Total_number_employees></Total_number_employees>\r"
						        + "<Total_Female_emp></Total_Female_emp>\r"
						        + "<Total_Male_emp></Total_Male_emp>\r"
						        + "</Employees>\r"
						        + "<Products>\r"
						        + "<Total_number_products>_$message$_</Total_number_products>\r"
						        + "</Products>\r"
						        + "</TayanaCompanyData>\r";
						WebElement firsturlbodytf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/textarea[1]")));
						firsturlbodytf.sendKeys(xmlData);
						WebElement headtagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FirstURLBody']//div//a[@title='Header'][normalize-space()='Header']")));
						headtagbutton.click();
						WebElement checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[3]/input[1]")));
						checkbox1.click();
						WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[8]/input[1]")));
						checkbox2.click();
						WebElement checkbox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[13]/input[1]")));
						checkbox3.click();									
						WebElement contiunebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]")));
						contiunebutton.click();
						Thread.sleep(2000);
						contiunebutton.click();
						WebElement Addbutton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//div[@id='rightSectionDiv']//div//section[contains(@class,'content')]//button[1]")));
						Addbutton2.click();
					//	WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
					//	addalertbutton.click();
					WebElement Addbutton3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[6]/button[1]")));
						Addbutton3.click();
						
						}
					// ======================================================================================================================================
					@Test(priority = 19, enabled = true)
					public void Create_Service_MI_del_sub() throws InterruptedException {
						Thread.sleep(2000);
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement serviceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/div[1]/ul[1]/li[2]/a[1]")));
						serviceElement.click();
						Thread.sleep(2000);
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement typedrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						typedrpdwn.click();
						WebElement selectMIservice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'MI Service')]")));
						selectMIservice.click();
						WebElement appldrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//i[1]")));
						appldrpdwn.click();
						WebElement selectappl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Xavier3']")));
						selectappl.click();
						WebElement nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/input[1]")));
						nametf.sendKeys("Xavier mi_service_Del_sub");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("A_mi_service automation testing");
						WebElement respexptdrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						respexptdrpdwn.click();
						WebElement selectrespexp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Deliver Ack + Submit']")));
						selectrespexp.click();
						WebElement tagnametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf.sendKeys("message");
						WebElement tagdescprition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition.sendKeys("message");
						WebElement tagnametf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf1.sendKeys("message1");
						WebElement tagdescprition1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition1.sendKeys("message1");
						WebElement delbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/div[3]/i[1]")));
						delbutton.click();
						WebElement firsturlbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FirstURLPanel']//div[contains(@class,'card-header tss-panel-header tss-info-panel')]")));
						firsturlbutton.click();
						WebElement urlendpointtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/input[1]")));
						urlendpointtf.sendKeys("/cgi-bin/$GET_BODY$");
						WebElement bodytagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")));
						bodytagbutton.click();
						String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r"
						        + "<TayanaCompanyData>\r"
						        + "<Employees>\r"
						        + "<Total_number_employees></Total_number_employees>\r"
						        + "<Total_Female_emp></Total_Female_emp>\r"
						        + "<Total_Male_emp></Total_Male_emp>\r"
						        + "</Employees>\r"
						        + "<Products>\r"
						        + "<Total_number_products>_$message$_</Total_number_products>\r"
						        + "</Products>\r"
						        + "</TayanaCompanyData>\r";
						WebElement firsturlbodytf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/textarea[1]")));
						firsturlbodytf.sendKeys(xmlData);
						WebElement headtagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FirstURLBody']//div//a[@title='Header'][normalize-space()='Header']")));
						headtagbutton.click();
						WebElement checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[3]/input[1]")));
						checkbox1.click();
						WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[8]/input[1]")));
						checkbox2.click();
						WebElement checkbox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[13]/input[1]")));
						checkbox3.click();									
						WebElement contiunebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]")));
						contiunebutton.click();
						Thread.sleep(2000);
						contiunebutton.click();
						WebElement Addbutton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//div[@id='rightSectionDiv']//div//section[contains(@class,'content')]//button[1]")));
						Addbutton2.click();
					//	WebElement addalertbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
					//	addalertbutton.click();
					WebElement Addbutton3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[6]/button[1]")));
						Addbutton3.click();
						
						}
					// ======================================================================================================================================
					@Test(priority = 20, enabled = true)
					public void Create_Service_NI_del() throws InterruptedException {
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement serviceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/div[1]/ul[1]/li[2]/a[1]")));
						serviceElement.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement typedrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						typedrpdwn.click();
						WebElement selectMIservice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'NI Service')]")));
						selectMIservice.click();
						WebElement appldrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//i[1]")));
						appldrpdwn.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div[1]/section/div/div/div[1]/div[2]/div/div/div[2]/div/div/div/div/ul/li[2]/input")).sendKeys("Xavier4");
						Thread.sleep(2000);
						WebElement nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/input[1]")));
						nametf.sendKeys("Xavier del ni_service");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("A_ni_service automation testing");
						WebElement respexptdrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						respexptdrpdwn.click();
						WebElement selectrespexp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Deliver Ack']")));
						selectrespexp.click();
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[6]/div[1]/fieldset[1]/input[1]")));
						usernametf.sendKeys("NIservice");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[7]/div[1]/fieldset[1]/input[1]")));
						passwordtf.sendKeys("Niservice123");
						
						WebElement msisdndropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[9]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						msisdndropdown.click();
						WebElement selectmsisdndropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'White List')]")));
						selectmsisdndropdown.click();
						WebElement msisdnlistdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[10]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						msisdnlistdropdown.click();
						WebElement selectmsisdnlistdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='rightSectionDiv']//ul[1]//li[4]")));
						selectmsisdnlistdropdown.click();
						WebElement tagnametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf.sendKeys("message");
						WebElement tagdescprition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition.sendKeys("message");
						WebElement throughputf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[11]/div[1]/fieldset[1]/input[1]")));
						throughputf1.sendKeys("10");
//						WebElement tagdescprition1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
//						tagdescprition1.sendKeys("message1");
						WebElement delbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/div[3]/i[1]")));
						delbutton.click();
						WebElement firsturlbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLPanel']//div[@class='card-header tss-panel-header tss-info-panel']")));
						firsturlbutton.click();
						//WebElement urlendpointtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLBody']//div//div[contains(@class,'form-group col')]//div//div[contains(@class,'row')]//div[@id='tss-inputGroup']//input[@id='TSSGUI_InputTextFieldStyle']")));
						WebElement urlendpointtf = new WebDriverWait(driver, Duration.ofSeconds(20))
							    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLBody']//div//div[contains(@class,'form-group col')]//div//div[contains(@class,'row')]//div[@id='tss-inputGroup']//input[@id='TSSGUI_InputTextFieldStyle']")));
						urlendpointtf.sendKeys("/cgi-bin/$GET_BODY$");
						WebElement bodytagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")));
						bodytagbutton.click();
						String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r"
						        + "<TayanaCompanyData>\r"
						        + "<Employees>\r"
						        + "<Total_number_employees></Total_number_employees>\r"
						        + "<Total_Female_emp></Total_Female_emp>\r"
						        + "<Total_Male_emp></Total_Male_emp>\r"
						        + "</Employees>\r"
						        + "<Products>\r"
						        + "<Total_number_products>_$message$_</Total_number_products>\r"
						        + "</Products>\r"
						        + "</TayanaCompanyData>\r";
						WebElement firsturlbodytf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/textarea[1]")));
						firsturlbodytf.sendKeys(xmlData);
						WebElement headtagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLBody']//div//a[@title='Header'][normalize-space()='Header']")));
						headtagbutton.click();
						WebElement checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[3]/input[1]")));
						checkbox1.click();
						WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[8]/input[1]")));
						checkbox2.click();
						WebElement checkbox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[13]/input[1]")));
						checkbox3.click();									
//						WebElement contiunebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]")));
//						contiunebutton.click();
//						Thread.sleep(2000);
//						contiunebutton.click();
						WebElement Addbutton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//div[@id='rightSectionDiv']//div//section[contains(@class,'content')]//button[1]")));
						Addbutton2.click();
					
					//WebElement Addbutton3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[6]/button[1]")));
					//	Addbutton3.click();
						
						}

					// ======================================================================================================================================
					@Test(priority = 21, enabled = true)
					public void Create_Service_NI_sub() throws InterruptedException {
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement serviceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/div[1]/ul[1]/li[2]/a[1]")));
						serviceElement.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement typedrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						typedrpdwn.click();
						WebElement selectMIservice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'NI Service')]")));
						selectMIservice.click();
						WebElement appldrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//i[1]")));
						appldrpdwn.click();
						WebElement selectappl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Xavier5']")));
						selectappl.click();
						WebElement nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/input[1]")));
						nametf.sendKeys("Xavier sub_ni_service");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("A_ni_service automation testing");
						WebElement respexptdrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						respexptdrpdwn.click();
						WebElement selectrespexp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Deliver Ack']")));
						selectrespexp.click();
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[6]/div[1]/fieldset[1]/input[1]")));
						usernametf.sendKeys("NIservice");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[7]/div[1]/fieldset[1]/input[1]")));
						passwordtf.sendKeys("Niservice123");
						
						WebElement msisdndropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[9]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						msisdndropdown.click();
						WebElement selectmsisdndropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'White List')]")));
						selectmsisdndropdown.click();
						WebElement msisdnlistdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[10]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						msisdnlistdropdown.click();
						WebElement selectmsisdnlistdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='rightSectionDiv']//ul[1]//li[4]")));
						selectmsisdnlistdropdown.click();
						WebElement tagnametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf.sendKeys("message");
						WebElement tagdescprition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition.sendKeys("message");
						WebElement throughputf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[11]/div[1]/fieldset[1]/input[1]")));
						throughputf1.sendKeys("10");
//						WebElement tagdescprition1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
//						tagdescprition1.sendKeys("message1");
						WebElement delbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/div[3]/i[1]")));
						delbutton.click();
						WebElement firsturlbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLPanel']//div[@class='card-header tss-panel-header tss-info-panel']")));
						firsturlbutton.click();
						WebElement urlendpointtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLBody']//div//div[contains(@class,'form-group col')]//div//div[contains(@class,'row')]//div[@id='tss-inputGroup']//input[@id='TSSGUI_InputTextFieldStyle']")));
						urlendpointtf.sendKeys("/cgi-bin/$GET_BODY$");
						WebElement bodytagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")));
						bodytagbutton.click();
						String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r"
						        + "<TayanaCompanyData>\r"
						        + "<Employees>\r"
						        + "<Total_number_employees></Total_number_employees>\r"
						        + "<Total_Female_emp></Total_Female_emp>\r"
						        + "<Total_Male_emp></Total_Male_emp>\r"
						        + "</Employees>\r"
						        + "<Products>\r"
						        + "<Total_number_products>_$message$_</Total_number_products>\r"
						        + "</Products>\r"
						        + "</TayanaCompanyData>\r";
						WebElement firsturlbodytf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/textarea[1]")));
						firsturlbodytf.sendKeys(xmlData);
						WebElement headtagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLBody']//div//a[@title='Header'][normalize-space()='Header']")));
						headtagbutton.click();
						WebElement checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[3]/input[1]")));
						checkbox1.click();
						WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[8]/input[1]")));
						checkbox2.click();
						WebElement checkbox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[13]/input[1]")));
						checkbox3.click();									
//						WebElement contiunebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]")));
//						contiunebutton.click();
//						Thread.sleep(2000);
//						contiunebutton.click();
						WebElement Addbutton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//div[@id='rightSectionDiv']//div//section[contains(@class,'content')]//button[1]")));
						Addbutton2.click();
					
					//WebElement Addbutton3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[6]/button[1]")));
					//	Addbutton3.click();
						
						}				

					// ======================================================================================================================================
					@Test(priority = 21, enabled = true)
					public void Create_Service_NI_del_sub() throws InterruptedException {
						WebElement sitemapElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Site Map']")));
						sitemapElement.click();
						WebElement serviceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/div[1]/ul[1]/li[2]/a[1]")));
						serviceElement.click();
						WebElement Addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")));
						Addbutton.click();
						WebElement typedrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						typedrpdwn.click();
						WebElement selectMIservice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'NI Service')]")));
						selectMIservice.click();
						WebElement appldrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//i[1]")));
						appldrpdwn.click();
						WebElement selectappl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Xavier6']")));
						selectappl.click();
						WebElement nametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/input[1]")));
						nametf.sendKeys("Xavier sub_ni_service");
						WebElement descriptiontf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
						descriptiontf.sendKeys("A_ni_service automation testing");
						WebElement respexptdrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						respexptdrpdwn.click();
						WebElement selectrespexp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Deliver Ack']")));
						selectrespexp.click();
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[6]/div[1]/fieldset[1]/input[1]")));
						usernametf.sendKeys("NIservice");
						WebElement passwordtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[7]/div[1]/fieldset[1]/input[1]")));
						passwordtf.sendKeys("Niservice123");
						
						WebElement msisdndropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[9]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						msisdndropdown.click();
						WebElement selectmsisdndropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'White List')]")));
						selectmsisdndropdown.click();
						WebElement msisdnlistdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[10]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
						msisdnlistdropdown.click();
						WebElement selectmsisdnlistdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='rightSectionDiv']//ul[1]//li[4]")));
						selectmsisdnlistdropdown.click();
						WebElement tagnametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/input[1]")));
						tagnametf.sendKeys("message");
						WebElement tagdescprition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
						tagdescprition.sendKeys("message");
						WebElement throughputf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[11]/div[1]/fieldset[1]/input[1]")));
						throughputf1.sendKeys("10");
//						WebElement tagdescprition1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/input[1]")));
//						tagdescprition1.sendKeys("message1");
						WebElement delbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/div[3]/i[1]")));
						delbutton.click();
						WebElement firsturlbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLPanel']//div[@class='card-header tss-panel-header tss-info-panel']")));
						firsturlbutton.click();
						WebElement urlendpointtf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLBody']//div//div[contains(@class,'form-group col')]//div//div[contains(@class,'row')]//div[@id='tss-inputGroup']//input[@id='TSSGUI_InputTextFieldStyle']")));
						urlendpointtf.sendKeys("/cgi-bin/$GET_BODY$");
						WebElement bodytagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")));
						bodytagbutton.click();
						String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r"
						        + "<TayanaCompanyData>\r"
						        + "<Employees>\r"
						        + "<Total_number_employees></Total_number_employees>\r"
						        + "<Total_Female_emp></Total_Female_emp>\r"
						        + "<Total_Male_emp></Total_Male_emp>\r"
						        + "</Employees>\r"
						        + "<Products>\r"
						        + "<Total_number_products>_$message$_</Total_number_products>\r"
						        + "</Products>\r"
						        + "</TayanaCompanyData>\r";
						WebElement firsturlbodytf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/textarea[1]")));
						firsturlbodytf.sendKeys(xmlData);
						WebElement headtagbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContinueURLBody']//div//a[@title='Header'][normalize-space()='Header']")));
						headtagbutton.click();
						WebElement checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[3]/input[1]")));
						checkbox1.click();
						WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[8]/input[1]")));
						checkbox2.click();
						WebElement checkbox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[13]/input[1]")));
						checkbox3.click();									
//						WebElement contiunebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[1]")));
//						contiunebutton.click();
//						Thread.sleep(2000);
//						contiunebutton.click();
						WebElement Addbutton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//div[@id='rightSectionDiv']//div//section[contains(@class,'content')]//button[1]")));
						Addbutton2.click();
					
					//WebElement Addbutton3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[6]/button[1]")));
					//	Addbutton3.click();
						
						}
					// ======================================================================================================================================
					@Test(priority = 21, enabled = true)
					public void Otp_login_feature() throws InterruptedException {
					
						WebElement profileicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Click to change Theme']")));
						profileicon.click();
						Thread.sleep(2000);
						WebElement signout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/nav[1]/ul[3]/span[1]/pf-dropdown[1]/div[2]/li[2]/button[2]")));
						signout.click();
						WebElement loginotp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Login with OTP']")));
						loginotp.click();
						WebElement usernametf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='tsslogin-form_username']")));
						usernametf.sendKeys("Xavier");	
						WebElement genotpbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Generate OTP']")));
						genotpbutt.click();					
						Object otp_generate;
						String otp = (otp_generate).multi_factor_auth();					
						WebElement otptext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Please enter OTP character 1']")));
						otptext.sendKeys(otp);
						Thread.sleep(1000);
						WebElement loginbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
						loginbutt.click();
						
						
					}
					
	}



