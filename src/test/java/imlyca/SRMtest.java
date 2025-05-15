package imlyca;



	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import io.github.bonigarcia.wdm.WebDriverManager;

	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;

	public class SRMtest {
		private WebDriver driver;
		private Map<String, Object> vars;
		JavascriptExecutor js;

		@BeforeClass
		public void setUp() {
			WebDriverManager.firefoxdriver().setup();
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
			if (driver != null) {
				driver.quit();
			}
		}

		@Test (priority = 1)
		public void sRM() throws InterruptedException {

			driver.findElement(By.xpath("//b[contains(.,'Package Details')]")).click();
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='SIMselBtn']")).click();
				Thread.sleep(3000);
			}
		}
			
		@Test (priority = 2)
		
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

			}
		}
		@Test (priority =3)
		public void mappUpload() throws InterruptedException {
		
			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
			{
				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			Thread.sleep(2000);

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
		
		@Test (priority = 4)
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
		@Test (priority = 5)
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
				}
			}
		}
	}


