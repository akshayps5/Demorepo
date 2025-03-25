package SMSC;




	import java.time.Duration;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class smscdisplay {
		public static WebDriver driver;

		@BeforeClass
		// logic to login one time in the browser and execute all test cases within the
		// browser
		public void setUp() {
			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
			SMSC.smscdisplay.driver = driver; // Set the WebDriver instance

			driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
			// driver.get("https://10.0.0.21:8443/bmpportal/");

			// Set implicit wait to 5 seconds
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
			driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
			driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
		}

		// logic to create client
		@Test(priority = 1, enabled=false)
		public void client_creation() throws InterruptedException {
			driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Client')]")).click(); // portal gui
			Thread.sleep(3000);

			// Assuming 'driver' is an instance of WebDriver
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Execute JavaScript to click the link
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='addLink']")));
			
			
//			// Wait for the "addLink" element to be clickable
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			WebElement addLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='javascript:void(0);' and @onclick=\\\"showAddDiv('divAdd','','')\\\"]")));
//			addLink.click();

			
//			WebElement table1 = driver.findElement(By.xpath("//*[@id=\"pageDiv\"]/form/table[2]/tbody/tr[2]/td[2]/table"));
//			WebElement dataRow1 = table1.findElement(By.xpath("//*[@id=\"pageDiv\"]/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[1]"));
//			 WebElement ak =driver.findElement(By.xpath("/html/body/div/form/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[1]/td[9]"));
//			 ak.click();
//			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@title='Ente r alphanumeric value min:4 to max:10 characters']]"))
					.sendKeys("jyoti");// username
			driver.findElement(By.xpath("//input[@title='Enter alphan umeric value min:4 to max:10 characters']"))
					.sendKeys("jyoti");// password
			driver.findElement(By.xpath("//input[@name='throughput']")).sendKeys("1");// Min throughput
			driver.findElement(By.xpath("//input[@name='maxthroughput']")).sendKeys("100");// Max throughput
			driver.findElement(By.xpath("//input[@title='Enter valid email address']")).sendKeys("support@tayana.in");// customercare email
			driver.findElement(By.xpath("//input[@name='BillingFlag']")).click();
			driver.findElement(By.xpath("//input[@name='distListSupport']")).click();
			
																													
		}

		@Test(priority = 2)
		public void SMSpage_display() throws InterruptedException {
			driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
			Thread.sleep(2000);

			// Get the handles of all open windows
			Set<String> windowHandles = driver.getWindowHandles();

			// Iterate through the window handles
			for (String handle : windowHandles) {
				// Switch to the new window
				driver.switchTo().window(handle);

				// Check if it's the desired window based on the title, URL, or other criteria
				if (driver.getTitle().equals("Bulk Messaging Platform")) {

					Thread.sleep(2000);

					driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("will"); // username
					driver.findElement(By.xpath("//input[@name='password']")).sendKeys("will"); // password
					Thread.sleep(2000);
					driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
					Thread.sleep(2000);

					WebElement textElement = driver.findElement(By.xpath("//td[normalize-space()='Send Text Messages']"));
					String s1 = textElement.getText();
					System.out.println(s1);
					System.out.println("SMS page displayed");
				}
			}
		}

		@Test(priority = 3)
		public void Contactpage_display() throws InterruptedException {

			driver.findElement(By.xpath("//tbody//tr//td[5]")).click(); // contact admin
			Thread.sleep(2000);
			WebElement textElement = driver.findElement(By.xpath("//td[@align='left'][normalize-space()='Contact Admin']"));
			String s1 = textElement.getText();
			System.out.println(s1);
			System.out.println("contact admin page displayed");

		}
	}


