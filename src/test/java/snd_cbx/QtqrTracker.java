package snd_cbx;


	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.time.Duration;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.jcraft.jsch.ChannelExec;
	import com.jcraft.jsch.JSch;
	import com.jcraft.jsch.Session;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class QtqrTracker {

		WebDriver driver;
		WebDriverWait wait;

		private static final String SSH_HOST = QtqrTestData.SSH_HOST;
		private static final String SSH_USER = QtqrTestData.SSH_USER;
		private static final String SSH_PASSWORD = QtqrTestData.SSH_PASSWORD;
		private static final String EXPECTED_SCRIPT = QtqrTestData.EXPECTED_SCRIPT;

		@BeforeClass
		public void setUp() throws Exception {

//	        System.setProperty("webdriver.chrome.silentOutput", "true");

			// Automatically setup compatible ChromeDriver
			WebDriverManager.chromedriver().setup();

			// Chrome configuration
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);

			// Launch browser
			driver = new ChromeDriver(options);

			// Explicit wait for UI elements
			wait = new WebDriverWait(driver, Duration.ofSeconds(QtqrTestData.UI_WAIT_SECONDS));

			// Open SND login page
			driver.get(QtqrTestData.BASE_URL);
			driver.manage().window().maximize();

			// Enter username
			WebElement usernameField = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='User ID']")));
			usernameField.sendKeys(QtqrTestData.USERNAME);

			// Enter password
			driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/div[2]/form/div[2]/input"))
					.sendKeys(QtqrTestData.PASSWORD);

			// Click login
			driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/div[2]/form/div[4]/button")).click();
		}

		// ================= TEST =================
		@Test
		public void ValidateNotiInDealerTracker() throws Exception {

			// Transaction ID to validate
			String transactionId = QtqrTestData.TRANSACTION_ID;

			// Small delay for UI stability
			Thread.sleep(2000);

			// Scroll page down using Robot
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(1000);

			// ================= NAVIGATION =================

//			// Click Tracker menu
//			WebElement subscriberTracker = wait.until(
//			        ExpectedConditions.elementToBeClickable(
//			                By.xpath("//a[contains(.,'Subscriber Tracker')]")));
	//
//			subscriberTracker.click();
			driver.findElement(By.xpath("//a[contains(.,'Subscriber Tracker')]")).click();

			Thread.sleep(2000);

			// Open Date filter dropdown
			WebElement dateDropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='dropdownMenuLink']")));
			dateDropdown.click();
			Thread.sleep(2000);

			// Select "This Month"
			WebElement thisMonth = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='This Month']")));
			thisMonth.click();
			Thread.sleep(2000);

			// Click Filter button
			WebElement filterBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@type,'button')])[11]")));
			filterBtn.click();

			Thread.sleep(2000);

			// Select qtqr service
			WebElement qtqrbtn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id=\"rightSectionDiv\"]/section/div/div/div/div/div[2]/div[4]/ul/li[5]/button")));
			qtqrbtn.click();
			Thread.sleep(2000);

			// ================= TRANSACTION SEARCH =================

			// Enter Transaction ID
			WebElement txnInput = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'p-inputtext')]")));
			txnInput.sendKeys(transactionId);

			// Click Transaction ID link
			driver.findElement(By.xpath("//a[normalize-space()='" + transactionId + "']")).click();

			// ================= ACTUAL MESSAGE =================

			// Fetch notification text from UI
			WebElement noti = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"InfoModalbody\"]/div[10]/div[2]")));

			// Wait until notification text is fully loaded
			wait.until(driver -> !noti.getText().trim().isEmpty());

//			/ Full actual notification text
			String actualAll = noti.getText().trim();
			System.out.println("FULL ACTUAL:\n" + actualAll);

			// Get expected message from SSH
			String expectedRaw = fetchExpectedMessageViaSSH(transactionId);
			System.out.println("EXPECTED RAW:\n" + expectedRaw);

			// ✅ Direct validation
			// Normalize decimal and spacing differences
			String normalizedActual = actualAll
			        .replaceAll("(\\d+)\\.0\\b", "$1")
			        .replaceAll("\\s+", " ")
			        .trim();

			String normalizedExpected = expectedRaw
			        .replaceAll("(\\d+)\\.0\\b", "$1")
			        .replaceAll("\\s+", " ")
			        .trim();

			// Compare normalized values
			if (!normalizedActual.contains(normalizedExpected)) {
			    Assert.fail("Expected subscriber notification not found.\nExpected: "
			            + normalizedExpected + "\nActual: " + normalizedActual);
			}

		}
		// ================= SSH EXECUTION =================
		private String fetchExpectedMessageViaSSH(String transactionId) {

		    Session session = null;
		    ChannelExec channel = null;

		    try {
		        JSch jsch = new JSch();
		        session = jsch.getSession(SSH_USER, SSH_HOST, QtqrTestData.SSH_PORT);
		        session.setPassword(SSH_PASSWORD);
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect(QtqrTestData.SSH_TIMEOUT_MS);

		        channel = (ChannelExec) session.openChannel("exec");
		        channel.setCommand(EXPECTED_SCRIPT + transactionId);

		        InputStream in = channel.getInputStream();
		        channel.connect();

		        BufferedReader br = new BufferedReader(new InputStreamReader(in));

		        // Since your script returns only one line
		        String result = br.readLine();

		        return result != null ? result.trim() : "";

		    } catch (Exception e) {
		        throw new RuntimeException("SSH execution failed", e);
		    } finally {
		        if (channel != null && channel.isConnected()) {
		            channel.disconnect();
		        }
		        if (session != null && session.isConnected()) {
		            session.disconnect();
		        }
		    }
		}
	}


