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

	public class MO_ssfc_oct {

		public static WebDriver driver;

		@BeforeClass
		public void setUp() {
			WebDriver driver = new FirefoxDriver();
			MO_ssfc_oct.driver = driver;

			driver.get("https://10.0.6.65:8001/ssfc/login");
			driver.manage().window().maximize();

			// Use WebDriverWait for the login page elements
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
			driver.findElement(By.id("tsslogin-form_password")).sendKeys("Tayana@123");
			driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
		}

		@Test(priority = 1, enabled = true)
		public void Adding_MoSystemRule() throws InterruptedException {

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
			
			// Deletion of MO system Rules
			
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement errorMessageElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText = errorMessageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText);
			actualText = actualText.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText = "Deletion successful";

		 
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)
			
			
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
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement messageElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText1 = messageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText1);
			actualText1 = actualText1.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText1 = "addition successful";

		 
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)

		}

		@Test(priority = 2, enabled = true)
		public void Adding_Imsi4Operator() throws InterruptedException {

			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a/p/i")).click(); // Click on Fraud
																										// control
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click(); // Click on
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
			
			// Deletion of existing IMSI
			
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement errorMessageElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText = errorMessageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText);
			actualText = actualText.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText = "IMSI deleted successfully";

		 
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)
			
			// Creating IMSI for an operator.
			
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
			
			// to check weather it's success or not

			// to check weather it's success or not
					WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement messageElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
					String actualText1 = messageElement.getText().trim();// to remove the extra spaces and lines
					System.out.println(actualText1);
					actualText1 = actualText1.replaceAll("\\s+", " ");
					// Expected text that should be in the element (you can customize it)
					String expectedText1 = "IMSI added successfully";
					
					// Assertion to verify the actual text matches the expected text
					Assert.assertEquals(actualText1, expectedText1, "The message in the modal does not match!"); //comparing the expected and actual text)


		}

		// Service Operator adding

		@Test(priority = 3, enabled = true)
		public void Adding_Service4Operator() throws InterruptedException {

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

			// to check weather it's success or not
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement messageElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText1 = messageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText1);
			actualText1 = actualText1.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText1 = "Addition Successful";
			
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText1, expectedText1, "The message in the modal does not match!"); //comparing the expected and actual text)

		}

		// Protocol addition

		@Test(priority = 4, enabled = true)
		public void AddingProtoRules() throws InterruptedException {

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

			// to check weather it's success or not
					WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement messageElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
					String actualText1 = messageElement.getText().trim();// to remove the extra spaces and lines
					System.out.println(actualText1);
					actualText1 = actualText1.replaceAll("\\s+", " ");
					// Expected text that should be in the element (you can customize it)
					String expectedText1 = "addition successful";
					
					// Assertion to verify the actual text matches the expected text
					Assert.assertEquals(actualText1, expectedText1, "The message in the modal does not match!"); //comparing the expected and actual text)

		}

		@AfterClass
		public void setUps() {
//			driver.quit();
		}
	}


