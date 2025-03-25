package wmc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dealer {

	public static WebDriver driver;
	// public String subscriberId; // Class-level variable to store MSISDN

	@BeforeClass
	public void setUp() throws InterruptedException {

		driver = new FirefoxDriver();
		driver.get("http://10.0.1.232:8000");

		// Enter username and password
		driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[3]/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input"))
				.sendKeys("tayana");
		driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[3]/div/table/tbody/tr/td/table/tbody/tr[3]/td[2]/input"))
				.sendKeys("t4y4n4");
		driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[3]/div/table/tbody/tr/td/table/tbody/tr[5]/td/input[1]"))
				.click();
		// Wait for the new window and switch to it
		Thread.sleep(3000); // Brief delay for the new window to open
		String originalWindow = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
			//	break;
			}
		}
	}
	@Test(dataProvider = "MoSys",   priority = 1,enabled = true)
	public void dealer_creation(String i) throws InterruptedException {
		// Wait for the element in the new window to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/table/tbody/tr/td/form/table[2]/tbody/tr/td[2]/ul[1]/li[1]/a/font")));
		element.click();
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr/td/fieldset/table/tbody/tr[1]/td[2]/input"))
				.sendKeys("aarti");
		driver.findElement(
				By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[2]/select"))
				.click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(
				"/html/body/table/tbody/tr/td/table[7]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[2]/select/option[3]"))
				.click();
		driver.findElement(By.xpath("//*[@id='mobnum']/table/tbody/tr/td[2]/input")).sendKeys(i);
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr/td/fieldset/table/tbody/tr[6]/td/input")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[8]/tbody/tr/td/input[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By
				.xpath("/html/body/table/tbody/tr/td/table[6]/tbody/tr/td/fieldset/form/table/tbody/tr[1]/td[2]/input"))
				.sendKeys(i);
		Thread.sleep(3000);
		driver.findElement(By
				.xpath("/html/body/table/tbody/tr/td/table[6]/tbody/tr/td/fieldset/form/table/tbody/tr[5]/td[2]/input"))
				.sendKeys(i);
		Thread.sleep(3000);
		driver.findElement(By
				.xpath("/html/body/table/tbody/tr/td/table[6]/tbody/tr/td/fieldset/form/table/tbody/tr[8]/td[2]/input"))
				.sendKeys(i);
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr/td/input[1]")).click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();

		alert.accept();
	//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[1]/tbody/tr/td[2]/font/a[1]")).click();
	}
	@DataProvider(name = "MoSys")
	public Object[] [] MoData(){
		return new Object [][] { { "97517653093" } 
		
		};
		
		
	}
	@Test(priority = 2, enabled = false)
	public void corporate_dealer_creation() {
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[1]/tbody/tr/td[2]/font/a[1]")).click();

		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table[2]/tbody/tr/td[1]/ul[2]/li[2]/a/font"))
				.click();
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[1]/td[2]/input")).sendKeys("akki");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[2]/td[2]/input"))
				.sendKeys("97517123");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[3]/td[2]/input"))
				.sendKeys("97517456666");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[6]/td/input")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver; // top scroll down and click on accounts

		js.executeScript("window.scrollBy(332,887)"); // 337,535
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[17]/td/input[2]")).click();

		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[1]/td[2]/input")).sendKeys("akki");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[5]/td[2]/input"))
				.sendKeys("975174566");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[7]/td[2]/input"))
				.sendKeys("975174566");
		js.executeScript("window.scrollBy(567,887)");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[18]/td/input[1]")).click();
	}

@Test(priority = 3)
public void dealer_deletion() throws InterruptedException {
driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[1]/tbody/tr/td[2]/font/a[1]")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table[2]/tbody/tr/td[2]/ul[1]/li[2]/a/font")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr/td/fieldset/table/tbody/tr[1]/td[2]/input")).sendKeys("97517656023");
Thread.sleep(3000);
driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[8]/tbody/tr/td/input[1]")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/table[1]/tbody/tr[3]/td[2]/a/font")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[7]/tbody/tr[1]/td/input[3]")).click();
Alert alert = driver.switchTo().alert();

alert.accept();
Thread.sleep(1000);

}}