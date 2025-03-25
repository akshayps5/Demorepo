package bmp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class allcases {
    public static WebDriver driver;
	private static final String DB_URL = "jdbc:mysql://10.0.1.210:3306/BMPDB";
    private static final String DB_USER = "bmp";
    private static final String DB_PASSWORD = "bmp@Tayana123";
   
    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new FirefoxDriver(); // Use class-level driver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://10.0.6.137:8443/bmp/auth/login");

        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("transferpts");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj@2000");
        driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();
        driver.manage().window().maximize();
    }

   
    public void Dbconnect(String testName, String result, String failureReason, String timestamp) {
        try (Connection connection = DriverManager.getConnection(
                DB_URL + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", 
                DB_USER, 
                DB_PASSWORD)) 
        {
            // Creating a prepared statement for dynamic values
            String sqlQuery = "INSERT INTO testng_results (test_name, result, failure_reason, run_timestamp) " +
                              "VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                // Bind the parameters to the query
                preparedStatement.setString(1, testName);
                preparedStatement.setString(2, result);
                preparedStatement.setString(3, failureReason);
                preparedStatement.setString(4, timestamp);

                // Execute the SQL query
                int rowsAffected = preparedStatement.executeUpdate();

                // Log the result
                System.out.println("Number of rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
		
			// to login as a client and send sender_id request	
			@Test(priority = 1)
			public void sending_normal_msg1() throws InterruptedException{
				// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Correct usage in Selenium 4.x
				  //  WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

				    // Now you can interact with the element
				  //  senderInput.sendKeys("9607995681");
				driver.findElement(By.xpath("(//input[@id='mui-4'])[1]")).sendKeys("9897654");
				    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");
				    Thread.sleep(1000);

				driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
				/// Switch to alert
			   // Alert alert = driver.switchTo().alert();
			    Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
				Thread.sleep(3000);
				


		}	@Test(priority = 2)
		public void sending_deffered_message() throws InterruptedException {
		    // Get the current system time and add 2 minutes
		    LocalTime currentTime = LocalTime.now().plusMinutes(2);

		    // Extract the hour and minute from the new time
		    int hour = currentTime.getHour();
		    int minute = currentTime.getMinute();

		    // Convert hour and minute to strings, formatted to two digits (e.g., "08" instead of "8")
		    String hourStr = String.format("%02d", hour);
		    String minuteStr = String.format("%02d", minute);

		    // Print the resulting time for debugging purposes (optional)
		    System.out.println("Updated Time: " + hourStr + ":" + minuteStr);  // Example: "17:28"

		    // Wait for the profile icon input element to be visible
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Correct usage in Selenium 4.x
		    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

		    // Now you can interact with the element
		    senderInput.sendKeys("9607995681");
		    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello deferred message");

		    // Open the time picker and enter the updated time (hour and minute)
		    driver.findElement(By.xpath("//input[@placeholder='Now']")).click();
		    driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys(hourStr);  // Set the hour
		    driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys(minuteStr);  // Set the minute

		    // Click on the next button (time deferred confirmation)
		    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
		    Thread.sleep(1000);  // Wait for the time to be set (optional, adjust as needed)

		    // Click the "Send" button to send the deferred message
		    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();

		    // Optional: Wait for a few seconds to ensure the action is completed before ending the test
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
		    Thread.sleep(3000);
		}
		
			@Test(priority = 3)
			public void sending_normal_msg10() throws InterruptedException{
				//clicks on the profile icon
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Correct usage in Selenium 4.x
			    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

			    // Now you can interact with the element
			    senderInput.sendKeys("9607995681,9607998905,9607923456,9607992348,9607998908,9607992351,9607990127,9607995679,9607995670,9607993456");
			    Thread.sleep(2000);
				driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message 10 ");
				driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);
			    //alert.accept();
				driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
				Thread.sleep(3000);
			}
			@Test(priority = 4,enabled=false)
			public void personalized_msg1() throws InterruptedException, AWTException {
				driver.findElement(By.xpath("//div[@class='form-check form-check-custom form-check-solid form-switch']//input[@id='flash-message-checkbox']")).click();
				//enable the personalized toggle button
				driver.findElement(By.xpath("//span[normalize-space()='Upload File']")).click();
			    Thread.sleep(2000);

			    // Use Robot to handle the file upload dialog
			    Robot robot = new Robot();

			    // Set file path to clipboard
			    String filePath = "C:\\Users\\Pankaj\\Downloads\\personaliseMessageXlsSample.xlsx";
			    StringSelection selection = new StringSelection(filePath);
			    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			    // Paste the file path (Ctrl + V)
			    robot.keyPress(KeyEvent.VK_CONTROL);
			    robot.keyPress(KeyEvent.VK_V);
			    robot.keyRelease(KeyEvent.VK_V);
			    robot.keyRelease(KeyEvent.VK_CONTROL);

			    // Press Enter to confirm
			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);
			    Thread.sleep(6000);

			    // Continue with the test steps
			    driver.findElement(By.xpath("//textarea[@id='usrMessage']")).sendKeys("Hello MR.{{Full Name}} your balance for mobile number {{Mobile}} is RS {{Amount}}");
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);

			}
			@Test(priority = 5,enabled=false)
			public void personalized_deferred_msg() throws InterruptedException, AWTException {
				driver.findElement(By.xpath("//div[@class='form-check form-check-custom form-check-solid form-switch']//input[@id='flash-message-checkbox']")).click();
				//enable the personalized toggle button
				driver.findElement(By.xpath("//span[normalize-space()='Upload File']")).click();
			    Thread.sleep(2000);

			    // Use Robot to handle the file upload dialog
			    Robot robot = new Robot();

			    // Set file path to clipboard
			    String filePath = "C:\\Users\\Pankaj\\Downloads\\personaliseMessageXlsSample.xlsx";
			    StringSelection selection = new StringSelection(filePath);
			    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			    // Paste the file path (Ctrl + V)
			    robot.keyPress(KeyEvent.VK_CONTROL);
			    robot.keyPress(KeyEvent.VK_V);
			    robot.keyRelease(KeyEvent.VK_V);
			    robot.keyRelease(KeyEvent.VK_CONTROL);

			    // Press Enter to confirm
			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);
			    Thread.sleep(3000);
			    
			    
			 // Get the current system time and add 2 minutes
			    LocalTime currentTime = LocalTime.now().plusMinutes(2);

			    // Extract the hour and minute from the new time
			    int hour = currentTime.getHour();
			    int minute = currentTime.getMinute();

			    // Convert hour and minute to strings, formatted to two digits (e.g., "08" instead of "8")
			    String hourStr = String.format("%02d", hour);
			    String minuteStr = String.format("%02d", minute);

			    // Print the resulting time for debugging purposes (optional)
			    System.out.println("Updated Time: " + hourStr + ":" + minuteStr); 
			    //Thread.sleep(6000);
			    // Continue with the test steps
			    driver.findElement(By.xpath("//textarea[@id='usrMessage']")).sendKeys("Hello MR.{{Full Name}} your balance for mobile number {{Mobile}} is RS {{Amount}}");
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);

			}	
			@Test(priority = 6, enabled = false)
			public void sending_normal_deferred_message() throws InterruptedException {
			    // Get the current system time and add 2 minutes
			    LocalTime currentTime = LocalTime.now().plusMinutes(2);

			    // Extract the hour and minute from the new time
			    int hour = currentTime.getHour();
			    int minute = currentTime.getMinute();

			    // Convert hour and minute to strings, formatted to two digits (e.g., "08" instead of "8")
			    String hourStr = String.format("%02d", hour);
			    String minuteStr = String.format("%02d", minute);

			    // Print the resulting time for debugging purposes (optional)
			    System.out.println("Updated Time: " + hourStr + ":" + minuteStr);  // Example: "17:28"

			    // Wait for the profile icon input element to be visible
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Correct usage in Selenium 4.x
			    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

			    // Now you can interact with the element
			    senderInput.sendKeys("9748745");
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello deferred message");

			    // Open the time picker and enter the updated time (hour and minute)
			    driver.findElement(By.xpath("//input[@placeholder='Now']")).click();
			    driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys(hourStr);  // Set the hour
			    driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys(minuteStr);  // Set the minute

			    // Click on the next button (time deferred confirmation)
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);  // Wait for the time to be set (optional, adjust as needed)

			    // Click the "Send" button to send the deferred message
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();

			    // Optional: Wait for a few seconds to ensure the action is completed before ending the test
			    Thread.sleep(3000);
			    //driver.findElement(By.xpath("//span[normalize-space()='Sent Items']")).click();
			    //Thread.sleep(1500);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			}
			@Test(priority = 6,enabled=false)
			public void sending_normal_deferred_message1() throws InterruptedException {
			    // Get the current system time and add 2 minutes
			    LocalTime currentTime = LocalTime.now().plusMinutes(2);

			    // Extract the hour and minute from the new time
			    int hour = currentTime.getHour();
			    int minute = currentTime.getMinute();

			    // Convert hour and minute to strings, formatted to two digits (e.g., "08" instead of "8")
			    String hourStr = String.format("%02d", hour);
			    String minuteStr = String.format("%02d", minute);

			    // Print the resulting time for debugging purposes (optional)
			    System.out.println("Updated Time: " + hourStr + ":" + minuteStr);  // Example: "17:28"

			    // Wait for the profile icon input element to be visible
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Correct usage in Selenium 4.x
			    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

			    // Now you can interact with the element
			    senderInput.sendKeys("9607995681");
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello deferred message");

			    // Open the time picker and enter the updated time (hour and minute)
			    driver.findElement(By.xpath("//input[@placeholder='Now']")).click();
			    driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys(hourStr);  // Set the hour
			    driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys(minuteStr);  // Set the minute

			    // Click on the next button (time deferred confirmation)
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);  // Wait for the time to be set (optional, adjust as needed)

			    // Click the "Send" button to send the deferred message
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();

			    // Optional: Wait for a few seconds to ensure the action is completed before ending the test
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			}
			@Test(priority = 7,enabled=false)
			public void sending_normal_deferred_message10() throws InterruptedException {
			    // Get the current system time and add 2 minutes
			    LocalTime currentTime = LocalTime.now().plusMinutes(2);

			    // Extract the hour and minute from the new time
			    int hour = currentTime.getHour();
			    int minute = currentTime.getMinute();

			    // Convert hour and minute to strings, formatted to two digits (e.g., "08" instead of "8")
			    String hourStr = String.format("%02d", hour);
			    String minuteStr = String.format("%02d", minute);

			    // Print the resulting time for debugging purposes (optional)
			    System.out.println("Updated Time: " + hourStr + ":" + minuteStr);  // Example: "17:28"

			    // Wait for the profile icon input element to be visible
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Correct usage in Selenium 4.x
			    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

			    // Now you can interact with the element
			    senderInput.sendKeys("9607995681,9607998905,9607923456,9607992348,9607998908,9607992351,9607990127,9607995679,9607995670,9607993456");
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello deferred message10");

			    // Open the time picker and enter the updated time (hour and minute)
			    driver.findElement(By.xpath("//input[@placeholder='Now']")).click();
			    driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys(hourStr);  // Set the hour
			    driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys(minuteStr);  // Set the minute

			    // Click on the next button (time deferred confirmation)
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);  // Wait for the time to be set (optional, adjust as needed)

			    // Click the "Send" button to send the deferred message
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();

			    // Optional: Wait for a few seconds to ensure the action is completed before ending the test
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			}
			@Test(priority = 8,enabled=false)
			public void sending_deferred_unicode_msg10() throws InterruptedException {
			    // Get the current system time and add 2 minutes
			    LocalTime currentTime = LocalTime.now().plusMinutes(2);

			    // Extract the hour and minute from the new time
			    int hour = currentTime.getHour();
			    int minute = currentTime.getMinute();

			    // Convert hour and minute to strings, formatted to two digits (e.g., "08" instead of "8")
			    String hourStr = String.format("%02d", hour);
			    String minuteStr = String.format("%02d", minute);

			    // Print the resulting time for debugging purposes (optional)
			    System.out.println("Updated Time: " + hourStr + ":" + minuteStr);  // Example: "17:28"

			    // Wait for the profile icon input element to be visible
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Correct usage in Selenium 4.x
			    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

			    // Now you can interact with the element
			    senderInput.sendKeys("9607995681,9607998905,9607923456,9607992348,9607998908,9607992351,9607990127,9607995679,9607995670,9607993456");
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(" hello ನಮಸ್ಕಾರ");

			    // Open the time picker and enter the updated time (hour and minute)
			    driver.findElement(By.xpath("//input[@placeholder='Now']")).click();
			    driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys(hourStr);  // Set the hour
			    driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys(minuteStr);  // Set the minute

			    // Click on the next button (time deferred confirmation)
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);  // Wait for the time to be set (optional, adjust as needed)

			    // Click the "Send" button to send the deferred message
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();

			    // Optional: Wait for a few seconds to ensure the action is completed before ending the test
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			}
			
			@Test(priority = 9,enabled=false)
			public void sending_unicode_msg() throws InterruptedException{
				//clicks on the profile icon
				driver.findElement(By.xpath("//*[@id='mui-2']")).sendKeys("9607995681");
				driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("ನಮಸ್ಕಾರ");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			}
			
			@Test(priority = 10,enabled=false)
			public void sending_concat_unicodemsg() throws InterruptedException{
				//clicks on the profile icon
				driver.findElement(By.xpath("//*[@id='mui-2']")).sendKeys("9607995681");
				driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("ಕನ್ನಡ, ಕರ್ನಾಟಕ ರಾಜ್ಯದ ಅಧಿಕೃತ ಭಾಷೆಯಾಗಿದೆ. ಇದು ದಕ್ಷಿಣ ಭಾರತದಲ್ಲಿ ಹೊಂದಿಕೊಂಡಿರುವ ಭಾಷೆಗಳಲ್ಲಿ ಒಂದು. ಕನ್ನಡ ಭಾಷೆಯ ಉತ್ಪತ್ತಿ ಹಿಂದಿನ ಕಾಲದಲ್ಲಿಯೇ ಆಗಿದೆ. ಇದು ಕನ್ನಡ ಲಿಪಿಯಲ್ಲಿ ಬರೆಯಲು ಬಳಸುವ ಭಾಷೆ. ಕನ್ನಡದಲ್ಲಿ ಕಾವ್ಯ ಸಾಹಿತ್ಯವು ಬಹಳ ಪ್ರಮುಖವಾಗಿದೆ. ಹೆಚ್ಚುವರಿ ಕನ್ನಡ ಸಾಹಿತ್ಯದ ಕೃತಿಗಳು ಕಾವ್ಯ ರೂಪದಲ್ಲಿವೆ. ವಾಸ್ತವದಲ್ಲಿ, ಕನ್ನಡ ಭಾಷೆಯು ಭಾರತೀಯ ಭಾಷೆಗಳಲ್ಲಿ ಒಂದು ಅತ್ಯಂತ ಪ್ರಾಚೀನ ಭಾಷೆಯಾಗಿದೆ.");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			}
			
			@Test(priority = 11,enabled=false)
			public void sending_unicode_concat_deferredmsg() throws InterruptedException {
			    // Get the current system time and add 2 minutes
			    LocalTime currentTime = LocalTime.now().plusMinutes(2);

			    // Extract the hour and minute from the new time
			    int hour = currentTime.getHour();
			    int minute = currentTime.getMinute();

			    // Convert hour and minute to strings, formatted to two digits (e.g., "08" instead of "8")
			    String hourStr = String.format("%02d", hour);
			    String minuteStr = String.format("%02d", minute);

			    // Print the resulting time for debugging purposes (optional)
			    System.out.println("Updated Time: " + hourStr + ":" + minuteStr);  // Example: "17:28"

			    // Wait for the profile icon input element to be visible
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Correct usage in Selenium 4.x
			    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

			    // Now you can interact with the element
			    senderInput.sendKeys("9607995681");
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("ಕನ್ನಡ, ಕರ್ನಾಟಕ ರಾಜ್ಯದ ಅಧಿಕೃತ ಭಾಷೆಯಾಗಿದೆ. ಇದು ದಕ್ಷಿಣ ಭಾರತದಲ್ಲಿ ಹೊಂದಿಕೊಂಡಿರುವ ಭಾಷೆಗಳಲ್ಲಿ ಒಂದು. ಕನ್ನಡ ಭಾಷೆಯ ಉತ್ಪತ್ತಿ ಹಿಂದಿನ ಕಾಲದಲ್ಲಿಯೇ ಆಗಿದೆ. ಇದು ಕನ್ನಡ ಲಿಪಿಯಲ್ಲಿ ಬರೆಯಲು ಬಳಸುವ ಭಾಷೆ. ಕನ್ನಡದಲ್ಲಿ ಕಾವ್ಯ ಸಾಹಿತ್ಯವು ಬಹಳ ಪ್ರಮುಖವಾಗಿದೆ. ಹೆಚ್ಚುವರಿ ಕನ್ನಡ ಸಾಹಿತ್ಯದ ಕೃತಿಗಳು ಕಾವ್ಯ ರೂಪದಲ್ಲಿವೆ. ವಾಸ್ತವದಲ್ಲಿ, ಕನ್ನಡ ಭಾಷೆಯು ಭಾರತೀಯ ಭಾಷೆಗಳಲ್ಲಿ ಒಂದು ಅತ್ಯಂತ ಪ್ರಾಚೀನ ಭಾಷೆಯಾಗಿದೆ.");
			    Thread.sleep(3000);
			    // Open the time picker and enter the updated time (hour and minute)
			    driver.findElement(By.xpath("//input[@placeholder='Now']")).click();
			    driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys(hourStr);  // Set the hour
			    driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys(minuteStr);  // Set the minute

			    // Click on the next button (time deferred confirmation)
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);  // Wait for the time to be set (optional, adjust as needed)

			    // Click the "Send" button to send the deferred message
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();

			    // Optional: Wait for a few seconds to ensure the action is completed before ending the test
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(1000);
			}
			@Test(priority = 12, enabled=false)
		    public void barred_msisdn() throws InterruptedException {
				 driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
				    Thread.sleep(2000);
		        // Dynamically get the MSISDN from the input field
		        String enteredMsisdn = "9609890923";  // The MSISDN you will send, or extract dynamically from the input field later
		        
		        // Enter MSISDN
		        driver.findElement(By.xpath("//*[@id='mui-2']")).sendKeys(enteredMsisdn);

		        // Enter Message
		        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello barred_msisdn message");
		        Thread.sleep(1000);

		        // Click the first button
		        driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
		        Thread.sleep(1000);

		        // Click the Send button
		        driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
		        Thread.sleep(3000);

		        // Click the 'Click here to view details' link
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Click here to view details.']")));
		        detailsLink.click();

		        // Wait for the modal to be visible
		        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal') and contains(@class, 'show')]")));

		        // Extract the message from the modal
		        String modalText = modal.getText();

		        // Print the modal text for debugging
		        System.out.println("Modal message: " + modalText);

		        // Extract MSISDN from the message (checking dynamically)
		        String expectedMsisdn = enteredMsisdn;  // Use the MSISDN entered dynamically earlier
		        String message = modalText.contains("Msg not sent due to") ? modalText : "";

		        // Check if the message contains the MSISDN and verify it
		        if (message.contains("Msisdn [" + expectedMsisdn + "] is Blacklisted")) {
		            System.out.println("PASS: MSISDN " + expectedMsisdn + " is Blacklisted");
		            Assert.assertTrue(true);  // This will make the test pass
		        } else {
		            System.out.println("FAIL: MSISDN does not match or is not Blacklisted");
		            Assert.fail("MSISDN mismatch or not blacklisted");  // Fail the test if the MSISDN doesn't match
		        }
		     // Assuming you have identified the close button's XPath or CSS selector
		        WebDriverWait waitr = new WebDriverWait(driver, Duration.ofSeconds(20));
		        WebElement closeButton = waitr.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div[3]/button"))); // Adjust XPath to match the actual close button
		        Thread.sleep(1000);
		        closeButton.click(); // Click to close the modal
		        System.out.println("Modal closed successfully.");
		        Thread.sleep(1000);
		        driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
		       
		        }
		    

			@Test(priority = 13,enabled=false)
		    public void blacklist_msisdn() throws InterruptedException {

		        // Dynamically get the MSISDN from the input field
		        String enteredMsisdn = "9609789098";  // The MSISDN you will send, or extract dynamically from the input field later
		        
		        // Enter MSISDN
		        driver.findElement(By.xpath("//*[@id='mui-2']")).sendKeys(enteredMsisdn);

		        // Enter Message
		        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello blacklisted message");
		        Thread.sleep(1000);

		        // Click the first button
		        driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
		        Thread.sleep(2000);

		        // Click the Send button
		        driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
		        Thread.sleep(3000);

		        // Click the 'Click here to view details' link
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Click here to view details.']")));
		        detailsLink.click();

		        // Wait for the modal to be visible
		        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal') and contains(@class, 'show')]")));

		        // Extract the message from the modal
		        String modalText = modal.getText();

		        // Print the modal text for debugging
		        System.out.println("Modal message: " + modalText);

		        // Extract MSISDN from the message (checking dynamically)
		        String expectedMsisdn = enteredMsisdn;  // Use the MSISDN entered dynamically earlier
		        String message = modalText.contains("Msg not sent due to") ? modalText : "";

		        // Check if the message contains the MSISDN and verify it
		        if (message.contains("Msisdn [" + expectedMsisdn + "] is Blacklisted")) {
		            System.out.println("PASS: MSISDN " + expectedMsisdn + " is Blacklisted");
		            Assert.assertTrue(true);  // This will make the test pass
		        } else {
		            System.out.println("FAIL: MSISDN does not match or is not Blacklisted");
		            Assert.fail("MSISDN mismatch or not blacklisted");  // Fail the test if the MSISDN doesn't match
		        }
		     // Assuming you have identified the close button's XPath or CSS selector
		        WebDriverWait waitr = new WebDriverWait(driver, Duration.ofSeconds(20));
		        WebElement closeButton = waitr.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div[3]/button"))); // Adjust XPath to match the actual close button
		        Thread.sleep(1000);
		        closeButton.click(); // Click to close the modal
		        System.out.println("Modal closed successfully.");
		        Thread.sleep(1000);
		        driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
		        }
			@Test(priority = 14)
			public void invalid_msisdn() throws InterruptedException {
				driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			    // Dynamically get the MSISDN from the input field
			    String enteredMsisdn = "+9196098768";  // The MSISDN you will send, or extract dynamically from the input field later
			    
			    // Enter MSISDN
			    driver.findElement(By.xpath("//*[@id='mui-2']")).sendKeys(enteredMsisdn);

			    // Enter Message
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello invalid_msisdn message");
			    Thread.sleep(1000);

			    // Click the first button
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);

			    // Click the Send button
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
			    Thread.sleep(3000);

			    // Click the 'Click here to view details' link
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			    WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Click here to view details.']")));
			    
			    // Attempt to click on the 'view details' link
			    try {
			        detailsLink.click(); 
			    } catch (org.openqa.selenium.ElementClickInterceptedException e) {
			        // Handle overlay or modal blocking
			        System.out.println("Modal is blocking the click. Trying to close it...");
			        WebDriverWait waitForModal = new WebDriverWait(driver, Duration.ofSeconds(20));
			        waitForModal.until(ExpectedConditions.invisibilityOfElementLocated(By.id("sendMessageModal")));
			        detailsLink = driver.findElement(By.xpath("//a[normalize-space()='Click here to view details.']"));
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", detailsLink);
			    }

			    // Wait for the modal to be visible
			    WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal') and contains(@class, 'show')]")));
			    
			    // Extract the message from the modal
			    String modalText = modal.getText().trim();
			    System.out.println("Modal message: " + modalText);

			    // Extract MSISDN from the message (checking dynamically)
			    String expectedMsisdn = enteredMsisdn.replace("+", "");  // Remove '+' from the entered MSISDN
			    String message = modalText.contains("Msg not sent to") ? modalText : "";

			    // Debugging: Check if the message matches
			    System.out.println("Checking for message: Msg not sent to : [" + expectedMsisdn + "] Invalid Moblile Number");

			    // Check for the exact message with the typo "Moblile"
			    if (message.contains("Msg not sent to : [" + expectedMsisdn + "] Invalid Moblile Number")) {
			        System.out.println("PASS: MSISDN " + expectedMsisdn + " is Invalid Mobile Number");
			        Assert.assertTrue(true);  // This will make the test pass
			    } else {
			        System.out.println("FAIL: MSISDN does not match or is not an Invalid Mobile Number");
			        Assert.fail("MSISDN mismatch or Invalid Mobile Number case not found");  // Fail the test if the MSISDN doesn't match or invalid number case is not found
			    }

			    // Now close the modal using JavaScript to avoid click obstructions
			    WebDriverWait waitr = new WebDriverWait(driver, Duration.ofSeconds(20));
			    WebElement closeButton = waitr.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Close']"))); // Adjust XPath to match the actual close button
			    Thread.sleep(1000);

			    // Use JavaScript click to avoid overlay obstruction
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton); 
			    System.out.println("Modal closed successfully.");
			    Thread.sleep(1000);
		   		}
			@Test(priority = 15)
			public void invalid_msisdn2() throws InterruptedException {
				driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(3000);
			    // Dynamically get the MSISDN from the input field
			    String enteredMsisdn = "+876543311111";  // The MSISDN you will send, or extract dynamically from the input field later
			    
			    // Enter MSISDN
			    driver.findElement(By.xpath("//*[@id='mui-2']")).sendKeys(enteredMsisdn);

			    // Enter Message
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello invalid_msisdn message");
			    Thread.sleep(1000);

			    // Click the first button
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);

			    // Click the Send button
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
			    Thread.sleep(3000);

			    // Click the 'Click here to view details' link
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			    WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Click here to view details.']")));
			    
			    // Attempt to click on the 'view details' link
			    try {
			        detailsLink.click(); 
			    } catch (org.openqa.selenium.ElementClickInterceptedException e) {
			        // Handle overlay or modal blocking
			        System.out.println("Modal is blocking the click. Trying to close it...");
			        WebDriverWait waitForModal = new WebDriverWait(driver, Duration.ofSeconds(20));
			        waitForModal.until(ExpectedConditions.invisibilityOfElementLocated(By.id("sendMessageModal")));
			        detailsLink = driver.findElement(By.xpath("//a[normalize-space()='Click here to view details.']"));
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", detailsLink);
			    }

			    // Wait for the modal to be visible
			    WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal') and contains(@class, 'show')]")));
			    
			    // Extract the message from the modal
			    String modalText = modal.getText().trim();
			    System.out.println("Modal message: " + modalText);

			    // Extract MSISDN from the message (checking dynamically)
			    String expectedMsisdn = enteredMsisdn.replace("+", "");  // Remove '+' from the entered MSISDN
			    String message = modalText.contains("Msg not sent to") ? modalText : "";

			    // Debugging: Check if the message matches
			    System.out.println("Checking for message: Msg not sent to : [" + expectedMsisdn + "] Invalid Moblile Number");

			    // Check for the exact message with the typo "Moblile"
			    if (message.contains("Msg not sent to : [" + expectedMsisdn + "] Invalid Moblile Number")) {
			        System.out.println("PASS: MSISDN " + expectedMsisdn + " is Invalid Mobile Number");
			        Assert.assertTrue(true);  // This will make the test pass
			    } else {
			        System.out.println("FAIL: MSISDN does not match or is not an Invalid Mobile Number");
			        Assert.fail("MSISDN mismatch or Invalid Mobile Number case not found");  // Fail the test if the MSISDN doesn't match or invalid number case is not found
			    }

			    // Now close the modal using JavaScript to avoid click obstructions
			    WebDriverWait waitr = new WebDriverWait(driver, Duration.ofSeconds(20));
			    WebElement closeButton = waitr.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Close']"))); // Adjust XPath to match the actual close button
			    Thread.sleep(1000);

			    // Use JavaScript click to avoid overlay obstruction
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton); 
			    System.out.println("Modal closed successfully.");
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(2000);
			    

			   	}
			@Test(priority = 16)
			public void time_elapsed_duplicates() throws InterruptedException {
			    // Get the current system time
			    LocalTime currentTime = LocalTime.now();

			    // Extract the hour and minute from the current time
			    int hour = currentTime.getHour();
			    int minute = currentTime.getMinute();

			    // Convert hour and minute to strings, formatted to two digits
			    String hourStr = String.format("%02d", hour);
			    String minuteStr = String.format("%02d", minute);

			    // Debugging: Print the current time
			    System.out.println("Current Time: " + hourStr + ":" + minuteStr);

			    // Wait for the profile icon input element to be visible
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			    WebElement senderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mui-2']")));

			    // Enter the recipient MSISDN(s)
			    senderInput.sendKeys("9607995681,9607995681");

			    // Enter the message
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello current time message");

			    // Open the time picker and set the current time
			    driver.findElement(By.xpath("//input[@placeholder='Now']")).click();

			    // Set the hour
			    WebElement hourInput = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
			    hourInput.clear();
			    hourInput.sendKeys(hourStr);

			    // Set the minute
			    WebElement minuteInput = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
			    minuteInput.clear();
			    minuteInput.sendKeys(minuteStr);

			    // Confirm the deferred time
			    driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
			    Thread.sleep(1000);

			    // Click the "Send" button to send the message
			    driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
			    Thread.sleep(3000);

			    // Handle "Click here to view details" link
			    WebDriverWait linkWait = new WebDriverWait(driver, Duration.ofSeconds(20));
			    WebElement detailsLink = linkWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Click here to view details.']")));

			    try {
			        detailsLink.click();
			    } catch (org.openqa.selenium.ElementClickInterceptedException e) {
			        System.out.println("Modal is blocking the click. Trying to close it...");
			        WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(20));
			        modalWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("sendMessageModal")));
			        detailsLink = driver.findElement(By.xpath("//a[normalize-space()='Click here to view details.']"));
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", detailsLink);
			    }

			    // Wait for the modal to appear
			    WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal') and contains(@class, 'show')]")));
			    String modalText = modal.getText().trim();
			    System.out.println("Modal message: " + modalText);

			    // Check for "Time Elapsed" error message
			    if (modalText.contains("Msg not sent due to :Time Elapsed")) {
			        System.out.println("PASS: Message not sent due to Time Elapsed");
			        Assert.assertTrue(true);
			    } else {
			        System.out.println("FAIL: 'Time Elapsed' error message not found");
			        Assert.fail("'Time Elapsed' error message not found");
			    }

			    // Close the modal
			    WebDriverWait closeButtonWait = new WebDriverWait(driver, Duration.ofSeconds(20));
			    WebElement closeButton = closeButtonWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Close']")));
			    Thread.sleep(1000);
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
			    System.out.println("Modal closed successfully.");
			    Thread.sleep(1000);

			    // Reset for the next message
			    driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
			    Thread.sleep(1000);

			    System.out.println("Time elapsed with duplicates test completed successfully.");
			    
			}

		@AfterClass
		public void setUps() {
		driver.quit();
		}
		}

