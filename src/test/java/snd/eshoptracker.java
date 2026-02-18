package snd;



	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class eshoptracker {
		
		
		
		
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

			driver.findElement(By.xpath("//input[@placeholder='User ID']")).sendKeys(dataprovider.USERNAME);

			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(dataprovider.PASSWORD);

			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
		}
		
		
		@Test
		public void Eshoptrack() throws InterruptedException{
			
			
			driver.findElement(By.xpath("//a[contains(., 'EShop Reconciliation')]")).click();
			
			driver.findElement(By.xpath("//div[@class='card-body']//div[2]//div[1]//fieldset[1]//div[1]//div[1]//div[1]//input[1]")).sendKeys(dataprovider.EshopTrack);
			driver.findElement(By.xpath("//a[@id='dropdownMenuLink']")).click();
			driver.findElement(By.xpath("//a[normalize-space()='Last 30 Days']")).click();
			
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/button[1]")).click();
			
			WebElement cell = driver.findElement(By.xpath("//tbody/tr[1]/td[12]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cell);
			cell.click();
			
			
			driver.findElement(By.xpath("//input[@id='TSSGUI_SelectFieldStyle']")).click();
			driver.findElement(By.xpath("//li[@title='Success']")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/textarea[1]")).sendKeys(dataprovider.EshopDes);
			driver.findElement(By.xpath("//div[@id='ActionModalbody']//button[1]")).click();
			
			
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Transaction Successful";
			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
					
			Thread.sleep(2000);
			String actualMessage1 = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage1 = "Transaction Successful";
			if (actualMessage.equals(expectedMessage1)) {
				System.out.println(" Success message verified: " + actualMessage1);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage1);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
		
			
			
			
		}

	

}
