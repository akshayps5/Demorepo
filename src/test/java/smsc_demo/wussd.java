package smsc_demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class wussd {

	public static WebDriver driver;

	@BeforeClass

	public void setUp() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		wussd.driver = driver;

		driver.get("https://10.0.6.63:8443/wussd/ussd/provussd/serviceCode");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form_username\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='tsslogin-form_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
		Thread.sleep(2000);

	}

	@Test(priority = 1, enabled = true)
	public void prov_menu() throws InterruptedException {

		
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[3]/a/p")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[3]/ul/li[7]/a/p")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[1]/div[1]/div/div/fieldset/div/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[1]/div[1]/div/div/div/ul/li[4]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("TSSGUI_InputTextFieldStyle")).sendKeys("345");
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[2]/div[1]/div/div/fieldset/div[1]/i")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[2]/div[1]/div/div/div/ul/li[4]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#tss-inputGroup > div:nth-child(1) > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).sendKeys("78945754324");
		Thread.sleep(3000);
		driver.findElement(By.id("previewButton")).click();
		Thread.sleep(3000);
		// WebElement iframe = wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[1]/section/div/section/div[2]/div/div[1]"))); // Replace with actual iframe XPath
	      //  driver.switchTo().frame(iframe);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// Scroll down by pixel offset (e.g., 500 pixels)
		jsExecutor.executeScript("window.scrollBy(0,800)");
		
	  
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div[2]/div/div[1]/div[2]/input")).sendKeys("*123");
	        // Perform an action on the located input element (e.g., sending text)
	        Thread.sleep(3000);  
	        
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div[2]/div/div[1]/div[2]/div/button[1]")).click();
	        Thread.sleep(2000);   
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div[2]/div/div[1]/div[2]/input")).sendKeys("*123");
		}

	@AfterClass
	public void logout() throws InterruptedException {

	}
}
