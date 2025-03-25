package SMSC;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class trackerter {
	public static WebDriver driver;

	public void m1() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		SMSC.trackerter.driver = driver;
		driver.get("https://10.0.6.71:8443/PCRF");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("akshay");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("akshay1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Subscriber Tracker']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='macUser']")).sendKeys("akshay96@kablonet");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//b[normalize-space()='Subscriber Details']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//b[normalize-space()='Package Details']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//b[normalize-space()='Activity Details']")).click();
		Thread.sleep(2000);

		WebElement calendar = driver
				.findElement(By.xpath("//div[@id='startDate']//i[@class='far fa-calendar-alt fa fa-calendar']"));
		calendar.click();

		WebElement date = driver.findElement(By.xpath("//td[@class='day' and text()='9']"));
		date.click();
		WebElement calendar2 = driver
				.findElement(By.xpath("//div[@id='endDate']//i[@class='far fa-calendar-alt fa fa-calendar']"));
		calendar2.click();

		WebElement date2 = driver.findElement(By.xpath("(//td[contains(text(),'14')])[3]"));
		date2.click();

		driver.findElement(By.xpath("//button[normalize-space()='Go']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,5000)");

		driver.findElement(By.xpath("//li[@id='t1']//a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@id='t2']//a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@id='t3']//a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@id='t5']//a")).click();
		Thread.sleep(3000);
		// driver.quit();
	}

	public static void main(String[] args) throws InterruptedException {
		trackerter obj = new trackerter();
		obj.m1();
	}

}
