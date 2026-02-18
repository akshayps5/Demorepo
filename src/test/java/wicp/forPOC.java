package wicp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class forPOC {
	private WebDriver driver;
	private String filePath = "C:/Users/akshay.ps/Downloads/data.xlsx"; // Path to Excel file

	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeClass
	public void setUp() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://10.0.6.46:8442/pcc");
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		driver.findElement(By.id("username")).sendKeys("sayan");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).sendKeys("Admin123");
		driver.findElement(By.xpath("//*[@id=\"subBtn\"]")).click();
		Thread.sleep(2000);

	}

	@Test(priority = 1) // Keep data-driven approach for login
	public void login() throws InterruptedException {

		driver.findElement(By.cssSelector(".form-group:nth-child(5) li:nth-child(3) b")).click();
		Thread.sleep(2000);
		{
			WebElement element = driver.findElement(By.cssSelector(".odd:nth-child(3) .tss-attachment"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);

		}
		{
			WebElement element = driver.findElement(By.cssSelector(".tss-add"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);

		}
		{
			WebElement element = driver.findElement(By.cssSelector(".tss-add"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector(".tss-add")).click();
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		Thread.sleep(1000);
		driver.findElement(By.id("chrgTreeName")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("rateActionButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".tss-add")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("raName")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("raName")).sendKeys("ak");
		driver.findElement(By.id("raServiceSB")).click();
		Thread.sleep(1000);

		// Locate the <select> element
		WebElement dropdown = driver.findElement(By.id("raServiceSB"));

		// Create Select object
		Select select = new Select(dropdown);

		// Select by visible text
		select.selectByVisibleText(" Data");

		Thread.sleep(1000);

		driver.findElement(By.id("rateActionDescTA")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("rateActionDescTA")).sendKeys("yty");
		// Click on the dropdown

		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[2]/div/section[2]/div[3]/div/form/div/div[1]/div/div[2]/div/div[1]"))
				.click();

		driver.findElement(By.id("smartTag_0")).click();
		driver.findElement(By.id("smartTag_0")).sendKeys("1");
		driver.findElement(By.id("actionSB_0")).click();
		driver.findElement(By.id("actionSB_0")).click();
		driver.findElement(By.id("chargeVal_0")).click();
		driver.findElement(By.id("chargeVal_0")).sendKeys("1");
		driver.findElement(By.id("servVal_0")).click();
		{
			WebElement element = driver.findElement(By.cssSelector(".form-group:nth-child(2) > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.id("servVal_0")).sendKeys("1");

		WebElement dropdown4 = driver.findElement(By.id("chargeUnitSB_0"));
		((WebDriver) dropdown4).findElement(By.xpath("//option[. = 'KB']")).click();

		driver.findElement(By.id("servUnitSB_0")).click();

		WebElement dropdown5 = driver.findElement(By.id("servUnitSB_0"));
		((WebDriver) dropdown5).findElement(By.xpath("//option[. = 'KB']")).click();

		{
			WebElement element = driver.findElement(By.cssSelector(".form-group:nth-child(3) > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		WebElement dropdown7 = driver.findElement(By.xpath("//*[@id='select2-bucketSB_0-container']"));
		//dropdown8.click();
		Select select2 = new Select(dropdown7);

		// Select by visible text
		select2.selectByVisibleText("Sayed_Data");
		// Wait for options to appear, then select the desired one
		
		{
			WebElement element = driver.findElement(By.cssSelector(".form-group:nth-child(2) > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector(".form-group:nth-child(2) > .btn")).click();
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.cssSelector(".confirm")).click();
	}

	@AfterMethod
	public void tear3Down() {
		// driver.quit(); // Close browser after tests
	}

	@AfterClass
	public void tearDown() {
		// driver.quit(); // Close browser after tests
	}
}
