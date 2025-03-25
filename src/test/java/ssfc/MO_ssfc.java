package ssfc;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MO_ssfc {

	public static WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriver driver = new FirefoxDriver();
		MO_ssfc.driver = driver;

		driver.get("https://10.0.6.65:8001/ssfc/login");
	//	driver.manage().window().maximize();

		// Use WebDriverWait for the login page elements
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
		driver.findElement(By.id("tsslogin-form_password")).sendKeys("Tayana@123");
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
	}

	@Test(priority = 1, enabled = true)
	public void prov_menu() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a/p/i")).click(); // Click on Fraud
																									// control
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click(); // Click on
																											// Rules
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/ul/li[2]/a")).click(); // Click on spam
		Thread.sleep(2000);
		driver.findElement(By.id("moSysPanel")).click(); // Click on MO system
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#moSysBody > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4) > svg:nth-child(2) > path:nth-child(2)"))
				.click(); // Click on delete button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1239,366)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); 
		
		WebElement errorMessageElement = driver.findElement(By.id("swal2-title"));
		String s1 = errorMessageElement.getText();
		int endIndex = s1.indexOf("Deletion successful"); // Trim the string to keep only the part until the specified
															// index
		String trimmedString = s1.substring(0, endIndex);
		System.out.println(trimmedString);
		String expectedOutput ="Deletion successful";
		System.out.println(s1);
		Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(3000);
		
		
		
		driver.findElement(By.cssSelector(
				"#rightSectionDiv > section > div > div > div > div.d-flex.justify-content-end.tss-pull-right > svg > path"))
				.click(); // Click for +
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click(); // Click for dropdown
		driver.findElement(By.xpath("//*[@title='MO System']")).click(); // select MO system

		driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("SSFC"); // To type the
																									// keyword.

		driver.findElement(By.xpath("//*[@title='Spam']")).click(); // To select Spam in the dropdown
		driver.findElement(By.xpath("//*[@id=\"addBtn\"]")).click();
		// Click on Add button
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // To select yes when it ask for
																						// are u sure

		// to check weather it's success or not
		WebElement errorMessageElement1 = driver.findElement(By.id("swal2-title"));
		String s2 = errorMessageElement1.getText();
		int endIndex1 = s2.indexOf("addition successful"); // Trim the string to keep only the part until the specified
															// index
		String trimmedString1 = s2.substring(0, endIndex1);
		System.out.println(trimmedString1);
		String expectedOutput1 = "addition successful";
		System.out.println(s2);
		Assert.assertTrue(s2.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(3000);

	}

	@Test(priority = 2, enabled = true)
	public void replace_menu() throws InterruptedException {

		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a/p")).click(); // Click on Fraud
																									// control
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click(); // Click on
																											// Rules
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[5]/a")).click(); // Click on IMSI
		Thread.sleep(2000);

		// 1st delete the existing IMSI.

		driver.findElement(By.cssSelector(".fa-trash > path:nth-child(2)")).click(); // Click on delete button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("887664345675558"); // Clicking
																												// on
																												// IMSI
																												// Box
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"confirmButton\"]")).click(); // Click on Search button
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1239,366)");
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr/td[1]/div/input"))
				.click(); // Check box
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.tss-pull-right:nth-child(1) > button:nth-child(1)")).click(); // Click on
																												// delete
																												// box
																												// button
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Click on yes popup
		Thread.sleep(3000);

		driver.findElement(By.cssSelector(".fa-plus > path:nth-child(2)")).click(); // Click on + to add IMSI
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div[1]/div[1]/div/div"))
				.click(); // Click on IMSI Type box
		Thread.sleep(2000);
		driver.findElement(By
				.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div[1]/div[1]/div/div/div/div/ul/li[1]"))
				.click(); // Click on Manual.
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("887664345675558"); // Adding
																												// IMSI
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"confirmButton\"]")).click(); // Click on Add
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Click on Yes for the popup
		Thread.sleep(1000);
		System.out.println("Adding IMSI");

		WebElement errorMessageElement1 = driver.findElement(By.id("swal2-title"));
		String s2 = errorMessageElement1.getText();
		int endIndex1 = s2.indexOf("IMSI addition successful"); // Trim the string to keep only the part until the
																// specified index
		String trimmedString1 = s2.substring(0, endIndex1);
		System.out.println(trimmedString1);
		String expectedOutput1 = "IMSI addition successful";
		System.out.println(s2);
		Assert.assertTrue(s2.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(3000);

	}

	// Service Operator adding

	@Test(priority = 3, enabled = true)
	public void service_Menu() throws InterruptedException {

		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a/p")).click(); // Click on Fraud
																									// control
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[3]/a/p")).click(); // Click on
																											// services
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[2]/a")).click(); // Click on operator
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(
				"#rightSectionDiv > section > div > div.card > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div > svg.svg-inline--fa.fa-plus.tss-add.tss-icon > path"))
				.click(); // Click on "+" to add operator
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click(); // Click on dropdown
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("li.options:nth-child(2)")).click(); // Click on checkbox
		Thread.sleep(400);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div[1]/div/div[5]/button[1]")).click(); // Click
																													// on
																													// add
																													// button
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Click on yes for adding
		Thread.sleep(3000);

		// Assertion for addition successful.

		WebElement errorMessageElement1 = driver.findElement(By.id("swal2-title"));
		String s2 = errorMessageElement1.getText();
		int endIndex1 = s2.indexOf("addition successful"); // Trim the string to keep only the part until the specified
															// index
		String trimmedString1 = s2.substring(0, endIndex1);
		System.out.println(trimmedString1);
		String expectedOutput1 = "addition successful";
		System.out.println(s2 + ", Services ");
		Assert.assertTrue(s2.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(3000);

	}

	// Protocol addition

	@Test(priority = 4, enabled = true)
	public void protocol_disp() throws InterruptedException {

		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a")).click(); // Click on Fraud
																									// control
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click(); // Click on
																											// Rules
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/ul/li[7]/a")).click(); // click on Protocol
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div > svg.svg-inline--fa.fa-plus.tss-add.tss-icon > path"))
				.click(); // Click on "+"
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("SSFC12"); // adding parameters
																									// to Rule name
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_BootstrapTextArea\"]")).sendKeys("Testing GUI"); // Adding
																										// despcrition
																										// to create
																										// protocol
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ProceedAddComponent\"]/div/div[2]/button[1]")).click(); // Clicking on
																										// add button
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Clicking on Yes pop up button
		Thread.sleep(2000);

		// Assertion for addition successful.

		WebElement errorMessageElement1 = driver.findElement(By.id("swal2-title"));
		String s2 = errorMessageElement1.getText();
		int endIndex1 = s2.indexOf("success"); // Trim the string to keep only the part until the specified index
		String trimmedString1 = s2.substring(0, endIndex1);
		System.out.println(trimmedString1);
		String expectedOutput1 = "success";
		System.out.println(s2 + ", Protocol ");
		Assert.assertTrue(s2.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(3000);

	}

	@AfterClass
	public void setUps() {
//		driver.quit();
	}
}
