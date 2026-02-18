package snd;




	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class deviceinventory {
		ChromeOptions options = new ChromeOptions();

		WebDriver driver = new ChromeDriver(options);

		public void Snd() {
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			options.setBinary("/path/to/chromium");
			options.setBinary("C:/Path/To/Chromium.exe");
			options.setBinary("C:/Path/To/Chromium.exe"); // or chromium-browser
			options.addArguments("user-data-dir=C:/temp/ChromeProfile_" + System.currentTimeMillis());
			options.addArguments("--disable-save-password-bubble");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--no-default-browser-check");
			options.addArguments("--disable-infobars");
			options.addArguments("--start-maximized");
			options.addArguments("--incognito");
		}

		@BeforeClass
		public void Login() throws InterruptedException {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			driver.get("https://10.0.5.49:8443/snd/");
			driver.findElement(By.xpath("//button[@id='details-button']")).click();

			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();

			driver.findElement(By.xpath("//input[@placeholder='User ID']")).sendKeys("admin");

			driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Admin@123");

			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
		}
		
		
		@Test(priority = 1)
		public void DeviceposDetach() throws InterruptedException {
			
			driver.findElement(By.xpath("//a[contains(., 'Device Inventory Management')]")).click();
			driver.findElement(By.xpath("//div[@class='card']//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='POS']")).click();
			Thread.sleep(2000);
			WebElement cell = driver.findElement(By.xpath("//tbody/tr[2]/td[6]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cell);
			cell.click();
			
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText().trim();
			String expectedMessage = "Dealer detached successfully.";
			if (actualMessage.startsWith(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
			
		}
		
		@Test(priority = 2)
		public void DeviceposAattach() throws InterruptedException {
			
			//driver.findElement(By.xpath("//a[contains(., 'Device Inventory Management')]")).click();
			
			driver.navigate().refresh();
			driver.findElement(By.xpath("//div[@class='card']//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='POS']")).click();
		
			Thread.sleep(2000);
			WebElement cell = driver.findElement(By.xpath("//tbody/tr[2]/td[6]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cell);
			cell.click();
			
			driver.findElement(By.xpath("//div[@class='SelectContainer']//div//div[@class='selectHover selectForm ']//input[@id='TSSGUI_SelectFieldStyle']")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[4]")).click();
			
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/input[1]")).click();
			
			Thread.sleep(2000);  	
			driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys("12343092");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/input[1]")).click();
			
			
			driver.findElement(By.xpath("//button[normalize-space()='Attach']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText().trim();
			String expectedMessage = "Dealer attached successfully.";
			if (actualMessage.startsWith(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			

			}
		@Test(priority = 3)
		public void DevicekioskDetach() throws InterruptedException {
			
			
			//driver.findElement(By.xpath("//a[contains(., 'Device Inventory Management')]")).click();
			
			driver.navigate().refresh();
			driver.findElement(By.xpath("//div[@class='card']//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='KIOSK']")).click();
			Thread.sleep(2000);
			WebElement cell = driver.findElement(By.xpath("//tbody/tr[1]/td[6]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cell);
			cell.click();
			
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText().trim();
			String expectedMessage = "Dealer detached successfully.";
			if (actualMessage.startsWith(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
		}
		
		@Test(priority = 4)
		public void DevicekioskAttach() throws InterruptedException {
			
			//driver.findElement(By.xpath("//a[contains(., 'Device Inventory Management')]")).click();
			driver.navigate().refresh();
			driver.findElement(By.xpath("//div[@class='card']//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='KIOSK']")).click();
			Thread.sleep(2000);
			WebElement cell = driver.findElement(By.xpath("//tbody/tr[1]/td[6]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cell);
			cell.click();
			
			driver.findElement(By.xpath("//div[@class='SelectContainer']//div//div[@class='selectHover selectForm ']//input[@id='TSSGUI_SelectFieldStyle']")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[4]")).click();
			
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/input[1]")).click();
			
			Thread.sleep(2000);  	
			driver.findElement(By.cssSelector(".TSSGUI_SelectSearchBoxStyle")).sendKeys("2482562191");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/input[1]")).click();
		
			driver.findElement(By.xpath("//button[normalize-space()='Attach']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText().trim();
			String expectedMessage = "Dealer attached successfully.";
			if (actualMessage.startsWith(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
			
			
		}
		@Test(priority = 5)
		public void Deviceposmodification() throws InterruptedException {
			
			
			//driver.findElement(By.xpath("//a[contains(., 'Device Inventory Management')]")).click();
			driver.navigate().refresh();
			
			driver.findElement(By.xpath("//a[normalize-space()='Update Device Status']")).click();
			driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='POS']")).click();
			driver.findElement(By.xpath("//div[2]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='🟠Maintenance']")).click();
			driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys(dataprovider.devices);
			driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Status updation successful";
			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
			driver.navigate().refresh();
			driver.findElement(By.xpath("//a[normalize-space()='Update Device Status']")).click();
			driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='POS']")).click();
			driver.findElement(By.xpath("//div[2]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='🔵In-Use/🟢Available']")).click();
			driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys(dataprovider.devices);
			driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage1 = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage1 = "Status updation successful";
			if (actualMessage.equals(expectedMessage1)) {
				System.out.println(" Success message verified: " + actualMessage1);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage1);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
		}
		
		@Test(priority = 6)
		public void Devicekioskmodification() throws InterruptedException {
			
			//driver.findElement(By.xpath("//a[contains(., 'Device Inventory Management')]")).click();
			driver.navigate().refresh();
			driver.findElement(By.xpath("//a[normalize-space()='Update Device Status']")).click();
			driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='KIOSK']")).click();
			driver.findElement(By.xpath("//div[2]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='🟠Maintenance']")).click();
			driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys(dataprovider.devices1);
			driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Status updation successful";
			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
			driver.navigate().refresh();
			driver.findElement(By.xpath("//a[normalize-space()='Update Device Status']")).click();
			driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='KIOSK']")).click();
			driver.findElement(By.xpath("//div[2]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='🔵In-Use/🟢Available']")).click();
			driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys(dataprovider.devices1);
			driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage1 = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage1 = "Status updation successful";
			if (actualMessage.equals(expectedMessage1)) {
				System.out.println(" Success message verified: " + actualMessage1);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage1);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();	
			
		}
		@AfterClass
		public void tearDown() {
			if (driver != null) {
				driver.quit();
			}
		}

			
		}
		
