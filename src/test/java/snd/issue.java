package snd;

import java.awt.AWTException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import bmp.BMP_portal_SMS_PAGE;
import io.github.bonigarcia.wdm.WebDriverManager;

public class issue {
	 WebDriver driver;

	@BeforeMethod
	public void setUp() throws InterruptedException, AWTException {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Accept self-signed HTTPS certs
		driver = new ChromeDriver();// Set the WebDriver instance


		// Open your URL
		driver.get("https://10.0.5.49:8443/snd/login");
		driver.manage().window().maximize();
		

		    driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();
		    driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='User ID']")).sendKeys("admin");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[3]/input"))
				.sendKeys("Admin@123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[4]/button")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void Add() throws InterruptedException, AWTException {

		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[4]/div/div[2]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[contains(text(),'Catalog')])[1]")).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("(//div[contains(text(),'Catalog')])[1]"));
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".contexify_itemContent")).click();
		Thread.sleep(2000);
//	        driver.findElement(By.xpath("//div[4]//div[1]//div[2]//ul[1]//li[1]//a[1]")).click();
//	        Thread.sleep(2000);
//	        driver.findElement(By.cssSelector(".rc-tree-treenode:nth-child(5) div")).click();
//	        Thread.sleep(2000);
//	        WebElement element = driver.findElement(By.xpath("//div[5]//span[3]//span[2]//div[1]"));
//	        Actions actions = new Actions(driver);
//	        actions.contextClick(element).perform();
//	        Thread.sleep(2000);
//	        driver.findElement(By.cssSelector(".contexify_itemContent")).click();
//	        Thread.sleep(2000);
		driver.findElement(By.id("TSSGUI_InputTextFieldStyle")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("TSSGUI_InputTextFieldStyle")).sendKeys("MPreTopCat18");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='tss-inputGroup']//div//div//input[@id='TSSGUI_SelectFieldStyle']"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys("This is New Catalog");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='card-body align-items-center py-8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[class='row'] button:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		Thread.sleep(4000);
		WebElement alert = driver.findElement(By.id("swal2-title"));
		String message = alert.getText().trim();
		System.out.println("Alert Message: " + message);
		System.out.println("Catalog Addition result : " + message);

		Assert.assertTrue(message.equalsIgnoreCase("Addition Successful"),
				"❌ FAIL: Expected 'Addition Successful' but got '" + message + "'");
		Thread.sleep(2000);
	}
	
    @AfterMethod
    public void tearDown() {
      //  driver.quit(); // Corrected spelling of 'quit'
    
}}
