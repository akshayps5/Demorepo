package ssfc;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MT_ssfc {

	public static WebDriver driver;
	String file_path = "NULL";

	@BeforeClass
	public void setUp() {
		WebDriver driver = new FirefoxDriver();
		MT_ssfc.driver = driver;

		driver.get("https://10.0.6.65:8001/ssfc/login");
		driver.manage().window().maximize();

		// Use WebDriverWait for the login page elements
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
		driver.findElement(By.id("tsslogin-form_password")).sendKeys("Tayana@123");
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
	}

//Delete the existing keyword of spam rule for MT system.	    
	@Test(priority = 1, enabled = true)
	public void Fraud_MTspam_delete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/a")).click();// click on fraud
																									// control
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click();// click on
																											// Rules
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/ul/li[2]/a")).click();// click on Spam
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"mtSysPanel\"]/div[1]")).click();// click on MT system
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div/div[2]/div[2]/div/div/div[2]/table/tbody/tr[2]/td[4]"))
				.click();// click on row(cell)
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#mtSysBody > div > div > div.p-datatable-wrapper > table > tbody > tr:nth-child(7) > td:nth-child(4) > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path"))
				.click();// click on delete button.
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();// click on yes for popup
		Thread.sleep(1000);
		// To check weather it's success or not
		//WebElement errorMessageElement = driver.findElement(By.id("swal2-title"));
		WebElement errorMessageElement = driver.findElement(By.xpath("//*[@id=swal2-title']"));
		String s1 = errorMessageElement.getText();
		int endIndex = s1.indexOf("Deletion successful");
		// Trim the string to keep only the part until the specified index
		String trimmedString = s1.substring(0, endIndex);
		System.out.println(trimmedString);
		String expectedOutput = "Deletion successful";
		System.out.println(s1);
		// System.out.println("Deletion successful");
		Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(3000);
	}

//Add keyword for spam rule for MT system
	@Test(priority = 2, enabled = true)
	public void Fraud_MTspam_add() throws InterruptedException {

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.cssSelector(
				"#rightSectionDiv > section > div > div > div > div.d-flex.justify-content-end.tss-pull-right > svg > path"))
				.click();// click on "+" button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click();// click on dropdown of config type
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@title='MT System']")).click();// select MT system
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("TQAteam");// pass the value in
																									// keyword
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click();// click on treatment
		driver.findElement(By.xpath("//*[text()='Spam']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"addBtn\"]")).click();// click on add button
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();// click on yes for popup
		Thread.sleep(2000);
		// To check weather it's success or not
		WebElement errorMessageElement1 = driver.findElement(By.id("swal2-title"));
		String s11 = errorMessageElement1.getText();
		int endIndex1 = s11.indexOf("addition successful");
		// Trim the string to keep only the part until the specified index
		String trimmedString1 = s11.substring(0, endIndex1);
		System.out.println(trimmedString1);
		String expectedOutput1 = "addition successful";
		System.out.println(s11);
		// System.out.println("addition successful");
		Assert.assertTrue(s11.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(3000);

	}

//check the "+" button is working on other local under networks
	@Test(priority = 3, enabled = true)
	public void Local_operator() throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/a")).click();// click on fraud
																									// control
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/ul/li[1]/a/p")).click();// click
																												// on
																												// Network
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/ul/li[2]/a")).click();// click on other local
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".fa-plus")).click();// click on "+" button
		Thread.sleep(2000);

	}

//Modifying the created operator in the International 
	@Test(priority = 4, enabled = true)
	public void International_operator() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/a")).click();// click on fraud
																									// control
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/ul/li[1]/a/p")).click();// click
																												// on
																												// Network
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/ul/li[3]/a")).click();// click on
																									// international
																									// operator
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div/div/div/div/div[4]/div/div[1]"))
				.click();// click on maldives
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div/div/div/div/div[4]/div/div[2]/div/div/div[2]/table/tbody/tr[2]/td[1]"))
				.click();// click on row cell of opName2
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#NetworkInternationalBody-2 > div > div > div.p-datatable-wrapper > table > tbody > tr.p-row-odd > td:nth-child(4) > div > svg"))
				.click();// click on modify button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("21");// enter the text in
																								// operator field
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#ProceedAddComponent > div.card-body.align-items-center.py-8 > div:nth-child(2) > div > button:nth-child(1)"))
				.click();// click on modify button
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		Thread.sleep(2000);
		// To check weather it's success or not
		WebElement errorMessageElement1 = driver.findElement(By.id("swal2-title"));
		String s11 = errorMessageElement1.getText();
		int endIndex1 = s11.indexOf("Updated the Region and opName Successfully");
		// Trim the string to keep only the part until the specified index
		String trimmedString1 = s11.substring(0, endIndex1);
		System.out.println(trimmedString1);
		String expectedOutput1 = "Updated the Region and opName Successfully";
		System.out.println(s11);
		// System.out.println("addition successful");
		Assert.assertTrue(s11.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(3000);
	}

//Adding bulk IMSI with proper MCC-MNC using the file
	@Test(priority = 5, enabled = true)
	public void Bulk_IMSI() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/a/p")).click();// click on fraud
																										// control
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click();// click on
																											// Rules
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/ul/li[5]/a")).click();// click on imsi
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div.button-container > svg.svg-inline--fa.fa-plus.tss-add.tss-icon > path"))
				.click();// click on "+" button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click();// click on dropdown
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@title='Bulk']")).click();// click on bulk
		Thread.sleep(2000);
		System.out.println("hello");
		WebElement file_input = driver
				.findElement(By.xpath("//*[@id=\"TSSGUI_InputFileFieldSetStyle\"]/div/div/button"));
		file_input.click();
		Thread.sleep(3000);
		file_path = "C:\\Users\\Moulya P\\Desktop\\imsi.txt";
		file_input.sendKeys(file_path);

		// String projectpath = System.getProperty("user.dir");
		// String projectPath = System.getProperty("user.dir");
		// String filePath = projectpath + "\\files\\imsi.txt";
		// Check if the file exists
		// File file = new File(filePath);
		// if (file.exists()) {
		// WebElement fileInput =
		// driver.findElement(By.xpath("//*[@id=\\\"TSSGUI_InputFileFieldSetStyle\\\"]/div/div/button"));
		// fileInput.sendKeys(filePath);
		// Wait for the file to be uploaded (you may need to adjust the wait time)
		// Thread.sleep(2000);

		// Continue with your code
		// } else {
		// System.out.println("File not found: " + filePath);
		// }

	}
	

	@Test
	public void testcase() {
		System.out.println("This is an GUI testing");
	}
}
