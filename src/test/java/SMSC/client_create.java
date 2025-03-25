package SMSC;

import java.time.Duration;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class client_create {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() throws InterruptedException {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		client_create.driver = driver;
//			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance

		// Now you can proceed with your test automation script
		driver.get("https://10.0.1.86:8000/smsc");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form_username\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='tsslogin-form_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
		Thread.sleep(2000);

	}

	@Test(priority = 1)

	public void deleting_existing_client() throws InterruptedException {
	//	setUp();

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[4]/a/p/i")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[4]/ul/li[3]/a/p")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div/div/div/div/div/div[1]/div/div[2]/span/div/input"))
				.sendKeys("akshay");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='alignCenter']//*[name()='svg'][2]/*[name()='path'][1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		Thread.sleep(3000);

	}

	@Test(priority = 2)
	public void smpp_client_creation() throws InterruptedException {

		// Locate the SVG element using XPath
		// driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")).click();
		driver.findElement(By.xpath("//p[normalize-space()='ESME']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Client']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]"))
				.click();
		driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("akshay");
		driver.findElement(By.xpath("//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("akshay");
		driver.findElement(By.xpath("//div[4]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("1");
		driver.findElement(By.xpath("//div[5]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("10");
		driver.findElement(By.xpath("//input[@placeholder='Select Expiry Date']")).click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div/div/div[1]/div/button[2]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div[2]/span[10]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div[2]/span[8]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div/div/div[2]/table/tbody/tr[5]/td[1]"))
				.click();
		// driver.findElement(By.xpath("//span[@class='p-highlight']")).click();
		driver.findElement(By.xpath("//div[8]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("akshay3@gmail.com");
		driver.findElement(By.xpath("//div[11]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("7899789");

		driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[12]/div/div/fieldset/input"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[12]/div/div/div/div/ul/li[3]"))
				.click();
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[14]/div[1]/div[1]/fieldset[1]/input[1]"))
				.click();
		driver.findElement(By.xpath("//li[@class='TSSGUI_SelectFieldSelectAllOption ']")).click();
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div[1]/section/div/div/div[1]/section/div/div/div[2]/div/button[1]"))
				.click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		Thread.sleep(4000);
		// driver.findElement(By.xpath("//div[@id='AccountsPanel']//div[@class='card-header
		// tss-panel-header tss-info-panel']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='AccountsPanel']//div[@class='card-header tss-panel-header tss-info-panel']")));
		element.click();
		driver.findElement(By.xpath(
				"//div[@id='AccountsPanel']//div[@class='row']//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("akshay");
		driver.findElement(
				By.xpath("//div[@id='AccountsPanelBody']//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("akshay");
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click();
		// driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/section/div/div/div[1]/section/div/div[2]/div/div[4]/div/div[2]/div[2]/div[3]/div/div/fieldset/div/i")).click();
		// driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div[2]/div/div[4]/div/div[2]/div/div[3]/div/div/div/div/ul/li[1]"));

		driver.findElement(By.xpath(
				"//div[@class='form-group col-md-1']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']"))
				.sendKeys("5");
		driver.findElement(By.xpath("//input[@placeholder='Select IP Address']")).click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div[2]/div/div[4]/div/div[2]/div/div[5]/div/div/div/div/ul/li[1]"))
				.click();
		driver.findElement(By.xpath("//div[@class='col-md-12']//div//div[3]//div[1]//div[1]//fieldset[1]//input[1]"))
				.click();
		driver.findElement(By.xpath("//li[@title='TXRX']")).click();
		driver.findElement(By.xpath("//div[@id='AccountsPanel']//div[7]//*[name()='svg']")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//div[@id='DSRSupportPanel']//div[contains(@class,'card-header tss-panel-header tss-info-panel')]"))
				.click();
		driver.findElement(By.xpath(
				"//div[@id='DSRSupportPanelBody']//div//div[contains(@class,'row')]//div[contains(@class,'form-group col-md-3')]//div//input[@id='TSSGUI_SelectFieldStyle']"))
				.click();
		driver.findElement(By.xpath("//li[@title='Enable']")).click();
		driver.findElement(By.xpath("//div[@id='DSRSupportPanel']//button[1]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		Thread.sleep(3000);
		// driver.close();
		System.out.println("Automation script executed successfully.");

	}
}
