package SMSC;



	import java.time.Duration;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class smsccreation {
		public static WebDriver driver;

		@BeforeClass
		// logic to login one time in the browser and execute all test cases within the
		// browser
		public void setUp() {
			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
			SMSC.smsccreation.driver = driver; // Set the WebDriver instance

			driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
			// driver.get("https://10.0.0.21:8443/bmpportal/");

			// Set implicit wait to 5 seconds
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
			driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
			driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
		}

		// logic to create client
		@Test(priority = 1)
		public void client_creation() throws InterruptedException {
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Client')]")).click(); // portal gui
			Thread.sleep(5000);
			
			WebElement iframe = driver
					.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td/div/table/tbody/tr/td/div/iframe"));
			driver.switchTo().frame(iframe);

			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink3 = wait3.until(ExpectedConditions.elementToBeClickable(By.id("addLink")));
			addLink3.click();

			// Code to fill client information
			WebElement userNameInput = driver.findElement(By.xpath("//input[@name='userName']"));
			userNameInput.sendKeys("jyoti");
			Thread.sleep(1000);
			WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
			passwordInput.sendKeys("jyoti");
			Thread.sleep(1000);
			WebElement minThroughputInput = driver.findElement(By.xpath("//input[@name='throughput']"));
			minThroughputInput.sendKeys("1");
			Thread.sleep(1000);
			WebElement maxThroughputInput = driver.findElement(By.xpath("//input[@name='maxthroughput']"));
			maxThroughputInput.sendKeys("100");
			Thread.sleep(1000);
			WebElement emailInput = driver.findElement(By.xpath("//input[@title='Enter valid email address']"));
			emailInput.sendKeys("support@tayana.in");
			Thread.sleep(1000);
			WebElement billingFlagCheckbox = driver.findElement(By.xpath("//input[@name='BillingFlag']"));
			billingFlagCheckbox.click();
			Thread.sleep(1000);
			WebElement distListSupportCheckbox = driver.findElement(By.xpath("//input[@name='distListSupport']"));
			distListSupportCheckbox.click();
			Thread.sleep(1000);
			
			//logic to pick the date from calendar
			driver.findElement(By.xpath("//*[@id=\"borderDivAdd\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[1]/table/tbody/tr[6]/td[2]/a")).click();//calendar locator
			
			// Switch back to the main window
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().equals("about:blank")) {
					Thread.sleep(2000);
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					Thread.sleep(1000);
					WebElement addLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table")));
					addLink.click();
					String amonth = driver.findElement(By.xpath("//font[normalize-space()='December 2023']"))
							.getText();
					
					while (!(amonth.equals("July 2024"))) {

						driver.findElement(By.xpath("//img[@alt='next month']")).click();
						Thread.sleep(1000);
						amonth = driver.findElement(By.xpath("/html/body/div[5]/ul/li[1]/div/div[1]/table/thead/tr[1]/th[2]"))
								.getText();
					}
					driver.findElement(By.xpath("//font[normalize-space()='14']")).click();
				}
			}
		}
		

	}


