package kyc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class akss {

	public static WebDriver driver;
	@BeforeClass
	public void test01() throws InterruptedException {
		System.out.println("Your in test01 method " + Thread.currentThread().getId());
		  driver = new FirefoxDriver();
    	akss.driver = driver;
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://tayana.greythr.com/uas/portal/auth/login");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("T425");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Shelake@2121");
		driver.findElement(By.xpath("/html/body/app-root/uas-portal/div/div/main/div/section/div[1]/o-auth/section/div/app-login/section/div/div/div/form/button")).click();
		//driver.findElement(By.xpath("/html/body/app-root/uas-portal/div/div/main/div/section/div[1]/o-auth/section/div/app-login/section/div/div/div/form/button")).click();
		//Thread.sleep(5000);
		//driver.findElement(By.xpath("/nav/ul/span[7]/li/a")).click();
		//driver.findElement(By.cssSelector("span.collapsed:nth-child(7) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)")).click();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("/nav/ul/span[4]/li/a/img")).click();
	}
	@Test
	public void Adding_Service4Operator() throws InterruptedException {
		driver.findElement(By.xpath("/nav/ul/span[4]/li/a/img")).click();	
	}
	@AfterClass
	public void setUps() {
	driver.quit();
	}

}
