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
	import org.openqa.selenium.interactions.Actions;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class vouchermanagment {
		
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
		
		@Test(priority = 1,enabled = true)
		public void VoucherRedeembyMonth() throws InterruptedException {
			driver.findElement(By.xpath("//a[contains(., 'Voucher Management')]")).click();
		
			//driver.findElement(By.xpath("//div[8]//div[1]//div[2]//ul[1]//li[1]//a[1]")).click();
			driver.findElement(By.xpath("//a[@id='dropdownMenuLink']")).click();
			Thread.sleep(1000);
			//driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
			driver.findElement(By.xpath("//a[normalize-space()='Last 30 Days']")).click();
			Thread.sleep(1000);
			Actions act = new Actions(driver);
			WebElement search = driver.findElement(By.xpath("(//*[name()='path'][contains(@fill,'currentColor')])[8]"));
			act.doubleClick(search).perform();
			Thread.sleep(2000);
			WebElement cell = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cell);
			cell.click();

			//driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Redeem']")).click();
			driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys("Testing");
			driver.findElement(By.xpath("//div[@id='addDealerGroupBody']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText().trim();
			String expectedMessage = "Status updated for 1 voucher(s)";
			if (actualMessage.startsWith(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			driver.findElement(By.xpath("//a[@class='tss-anchor' and text()='Home']")).click();
		
		}
		
		@Test(priority = 2, enabled = true)
		public void OrderReconciliationbymonth() throws InterruptedException {
			

			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[8]//div[1]//div[2]//ul[1]//li[2]//a[1]")).click();
			driver.findElement(By.xpath("//a[@id='dropdownMenuLink']")).click();
			driver.findElement(By.xpath("//a[normalize-space()='Last 30 Days']")).click();
			
			//driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
			Thread.sleep(1000);
			Actions act = new Actions(driver);
			WebElement search = driver.findElement(By.xpath(
					"//div[@class='row']//div[2]//div[1]//div[1]//div[3]//span[1]//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]"));
			act.doubleClick(search).perform();
			driver.findElement(By.xpath("(//table/tbody/tr/td[4])[1]/div/button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(2000);
			String actualMessage = driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText().trim();
			String expectedMessage = "SUCCESS";
			if (actualMessage.equals(expectedMessage)) {
				System.out.println(" Success message verified: " + actualMessage);
			} else {
				System.out.println(" Message mismatch. Found: " + actualMessage);
				Assert.fail();
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();

		}
		
		
		
	

}
