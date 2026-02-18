package snd;


	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class tarifcodeadd {
		
		
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
		public void createupdateDeleteTariff() throws InterruptedException {
			
			driver.findElement(By.xpath("//a[contains(., 'Tariff Code')]")).click();
			
			driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[6]")).click();
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]")).sendKeys(dataprovider.Tarif);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys(dataprovider.Tarifcode);
			driver.findElement(By.xpath("//input[@id='TSSGUI_SelectFieldStyle']")).click();
			driver.findElement(By.xpath("//li[contains(@title,'📲→Prepaid→Topup')]")).click();
			driver.findElement(By.xpath("//span[@class='slider']")).click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage = "Addition Successful";
			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
			driver.navigate().refresh();
			driver.findElement(By.xpath("//input[contains(@class,'p-inputtext p-component')]")).sendKeys(dataprovider.Tarif);
			
			driver.findElement(By.xpath("//div[contains(@aria-label,'modules.Generic.TssDataTable.buttonGroup')]//*[name()='svg'][1]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//input[@id='TSSGUI_SelectFieldStyle']")).click();
			driver.findElement(By.xpath("//li[contains(@title,'🌐→Postpaid→Bill Pay')]")).click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage1 = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage1 = "Successful";
			if (actualMessage.endsWith(expectedMessage1)) {
				System.out.println(" Success message verified: " + actualMessage1);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage1);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
			driver.navigate().refresh();
		
			driver.findElement(By.xpath("//input[contains(@class,'p-inputtext p-component')]")).sendKeys(dataprovider.Tarif);
		
			driver.findElement(By.xpath("//div[contains(@aria-label,'modules.Generic.TssDataTable.buttonGroup')]//*[name()='svg'][2]/*[name()='path'][1]")).click();
			
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(2000);
			String actualMessage2 = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
			String expectedMessage2 = "successful";
			if (actualMessage.endsWith(expectedMessage2)) {
				System.out.println(" Success message verified: " + actualMessage2);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage2);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			
		
			
		}

	

}
