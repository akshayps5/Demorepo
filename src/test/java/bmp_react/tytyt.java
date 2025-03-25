package bmp_react;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class tytyt {

	private WebDriver driver;

    @BeforeClass
    public void setUp() {
       // Update with actual geckodriver path
        driver = new FirefoxDriver(); 
        driver.manage().window().maximize();
    	driver.get("https://10.0.6.253:8443/wicp/welcome/jsp/login.jsp");
    }

	@Test
	public void sabb2() throws InterruptedException {
	
		driver.manage().window().setSize(new Dimension(1296, 688));

		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("praveena");
		driver.findElement(By.name("password")).click();

		driver.findElement(By.name("password")).sendKeys("T4y4n4");
		driver.findElement(By.id("subBtn")).click();
		

		driver.findElement(By.cssSelector(".form-group:nth-child(5) li:nth-child(2) b")).click();
		Thread.sleep(1000);
		{
			WebElement element = driver.findElement(By.cssSelector(".odd:nth-child(3) .tss-attachment"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".tss-add"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector(".tss-add")).click();
		Thread.sleep(1000);
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,144.6666717529297)");
		{
			WebElement element = driver.findElement(By.cssSelector(".form-group:nth-child(4) > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".form-group:nth-child(3) > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		{
			WebElement element = driver
					.findElement(By.cssSelector(".row:nth-child(5) > .form-group:nth-child(2) > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
			Thread.sleep(1000);
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.id("sabbName")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("sabbName")).sendKeys("akssabb34");
		driver.findElement(By.id("serviceSB")).click();
		Thread.sleep(1000);
		{
			WebElement dropdown = driver.findElement(By.id("serviceSB"));
		//	dropdown.findElement(By.xpath("//option[. = 'regexp:\\s+Originating Call']")).click();
			dropdown.findElement(By.xpath("//option[contains(text(), 'Originating Call')]")).click();
			Thread.sleep(1000);
		}
		driver.findElement(By.id("descCfgTA")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("descCfgTA")).sendKeys("sample");
		driver.findElement(By.id("categorySB_G0_G1_R1")).click();
		Thread.sleep(1000);
		{
			WebElement dropdown = driver.findElement(By.id("categorySB_G0_G1_R1"));
			dropdown.findElement(By.xpath("//option[. = 'Parameter']")).click();
		}
		driver.findElement(By.id("paramSB_G0_G1_R1")).click();
		Thread.sleep(1000);
		{
			WebElement dropdown = driver.findElement(By.id("paramSB_G0_G1_R1"));
			dropdown.findElement(By.xpath("//option[. = 'RoleOfNode']")).click();
			Thread.sleep(1000);
		}
		driver.findElement(By.id("select2-outputVal_1-container")).click();
		driver.findElement(By.cssSelector(".select2-container--open .select2-selection")).click();
		driver.findElement(By.id("enumOpSB_G0_G1_R1")).click();
		Thread.sleep(1000);
		{
			WebElement dropdown = driver.findElement(By.id("enumOpSB_G0_G1_R1"));
			dropdown.findElement(By.xpath("//option[. = 'IN']")).click();
			Thread.sleep(1000);
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".multiselect"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector(".multiselect")).click();
		Thread.sleep(1000);
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.cssSelector("li:nth-child(3) .checkbox")).click();
		driver.findElement(By.cssSelector(".select2-container--above .select2-selection")).click();
		driver.findElement(By.id("select2-outputVal_1-container")).click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0,245.3333282470703)");
		driver.findElement(By.cssSelector(".select2-container--open .select2-selection__arrow")).click();
		driver.findElement(By.id("select2-outputVal-container")).click();
		Thread.sleep(1000);
		{
			WebElement element = driver
					.findElement(By.cssSelector(".row:nth-child(5) > .form-group:nth-child(2) > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector(".row:nth-child(5) > .form-group:nth-child(2) > .btn")).click();
		Thread.sleep(1000);
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.cssSelector(".confirm")).click();
	}
	 @AfterClass
	    public void tearDown() {
	       // driver.quit(); // Close the browser after all tests
	    }
}
