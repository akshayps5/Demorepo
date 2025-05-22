package bmp_react;



	import java.time.Duration;
	import java.util.List;
	import java.util.NoSuchElementException;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import javax.mail.Authenticator;
	import javax.mail.Message;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.ElementNotInteractableException;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.jcraft.jsch.ChannelExec;
	import com.jcraft.jsch.JSch;

	import bmp_react.resenddatprovide;
	import io.netty.handler.timeout.TimeoutException;

	import java.time.LocalTime;
	import java.time.format.DateTimeFormatter;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import java.awt.*;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;

	public class resend{
		public static WebDriver driver;
		private FluentWait<WebDriver> wait11;
		@BeforeClass
		// logic to login one time in the browser and execute all test cases within the  browser
		public void setUp() throws InterruptedException {
		//public void runRemoteScriptViaSSH() {
		    String user = "bmp";
		    String host = "10.0.6.137";
		    int port = 22;
		    String password = "bmp";
		    String command = "bash /home/bmp/pre_run_gui_automation.sh";

		    try {
		        JSch jsch = new JSch();
		        com.jcraft.jsch.Session session = jsch.getSession(user, host, port);
		        session.setPassword(password);
		        session.setConfig("StrictHostKeyChecking", "no");

		        System.out.println(">>> Connecting to remote server...");
		        session.connect();

		        ChannelExec channel = (ChannelExec) session.openChannel("exec");
		        channel.setCommand(command);
		        channel.setErrStream(System.err);
		        InputStream in = channel.getInputStream();
		        channel.connect();

		        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		        String line;
		        while ((line = reader.readLine()) != null) {
		            System.out.println(line);
		        }

		        channel.disconnect();
		        session.disconnect();
		        System.out.println(">>> Remote script executed.");

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
		


			// Now start GUI automation		
		   // public void loginToPortal() throws InterruptedException {
			driver = new FirefoxDriver();
	        driver.get("https://" + resenddatprovide.host + ":" + resenddatprovide.portalport + "/bmpportal/auth/login");
	        
	     // driver.get("https://10.0.6.137:3000/bmpportal/");
	        System.out.println("Portal GUI Launched");

		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[1]/div/div/input"))
				.sendKeys(resenddatprovide.portalusername); // entering the username
		driver.findElement(
				By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[2]/div/div/input"))
				.sendKeys(resenddatprovide.portalpass);// entering the password
		driver.findElement(
				By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button/span[1]"))
				.click();// performing the click action
	        driver.manage().window().maximize();
	        Thread.sleep(3000);		
	}
		
		@Test(priority = 1, enabled = true)
		public void sending_normal_msg() throws InterruptedException {

		    Thread.sleep(3000);
		    driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();

		    driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
		    driver.findElement(By.xpath("//div[@id='senderID']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//li[normalize-space()='april21']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681");
		    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");

		    Thread.sleep(2000);
		    WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
		    Thread.sleep(2000);
		    highlightElement(sendButton);
		    sendButton.click();

		    Thread.sleep(2000);
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));

		    WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
		    highlightElement(confirmSendButton);
		    confirmSendButton.click();

		    // ✅ Wait for 2 minutes before checking message status
		    System.out.println("Waiting for 2 minutes to check message status...");
		    
		   Thread.sleep(Duration.ofMinutes(2).toMillis());
		 		 // ✅ Start stub after wait time
		 		try {
			        JSch jsch = new JSch();
			        com.jcraft.jsch.Session session = jsch.getSession("bmp", "10.0.6.137", 22);
			        session.setPassword("bmp");
			        session.setConfig("StrictHostKeyChecking", "no");
			        System.out.println(">>> Connecting to start stub...");
			        session.connect();

			        ChannelExec channel = (ChannelExec) session.openChannel("exec");
			        channel.setCommand ("cd /home/bmp/ && nohup /opt/bmp/bin/STUB_RespGen 1 1 1 1 1 10 0 > /dev/null 2>&1 &");
			        channel.setErrStream(null);  // Suppress error logs
			        channel.setInputStream(null);  // Disable input reading
			        channel.connect();

			        // Do not read or print the output
			        channel.disconnect();
			        session.disconnect();
			        System.out.println("✅ Stub started successfully.");
			    } catch (Exception e) {
			        System.out.println("❌ Failed to start stub:");
			        e.printStackTrace();
			    }



		    // ✅ Refresh by clicking the dashboard/menu link
		   // driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/ul/a[1]/li/div")).click();
		    //Thread.sleep(3000);

		    // ✅ Go to Sent Items
		 		Thread.sleep(2000); // Ensure login completes fully

			    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));

			    WebElement sentItems = wait1.until(ExpectedConditions.presenceOfElementLocated(
			        By.xpath("//span[normalize-space()='Sent Items']")));

			    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", sentItems);
			    Thread.sleep(500); // Let the scroll complete

			    // Optional debug: highlight the element
			    highlightElement(sentItems);

			    // Use JS click if regular click still fails
			    try {
			        wait.until(ExpectedConditions.elementToBeClickable(sentItems)).click();
			    } catch (ElementNotInteractableException e) {
			        System.out.println("Standard click failed. Trying JS click...");
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sentItems);
			    }
		    // ✅ Proceed to message details and status
		    //driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/span[1]")).click();
			    driver.findElement(By.xpath("(//span[@title='9607995681'][normalize-space()='9607995681'])[1]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
		    Thread.sleep(1000);

		    // ✅ Check for "GSM Retry Attempt Excd" or "Network TimeOut"
		    boolean retryNeeded = false;

		    List<WebElement> gsmRetry = driver.findElements(By.xpath("//*[contains(text(),'GSM Retry Attempt Excd')]"));
		    List<WebElement> netTimeout = driver.findElements(By.xpath("//*[contains(text(),'Network TimeOut')]"));

		    if (!gsmRetry.isEmpty()) {
		        System.out.println("⚠ GSM Retry Attempt Excd found. Attempting resend...");
		        retryNeeded = true;
		    } else if (!netTimeout.isEmpty()) {
		        System.out.println("⚠ Network TimeOut found. Attempting resend...");
		        retryNeeded = true;
		    }

		    if (retryNeeded) {
		        // ✅ Click the resend button
		    	try {
		    	    // Click on the resend icon (SVG inside td)
		    	    WebDriverWait wait21 = new WebDriverWait(driver, Duration.ofSeconds(15));
		    	    WebElement resendIcon = wait21.until(ExpectedConditions.elementToBeClickable(
		    	        By.cssSelector("td > .svg-inline--fa")));
		    	        Thread.sleep(1000);
		    	    highlightElement(resendIcon);
		    	    resendIcon.click();
		    	    System.out.println("✅ Resend icon clicked.");
		       
		        } catch (TimeoutException | NoSuchElementException e) {
		            System.out.println("❌ Resend icon or 'New Message' button not found.");
		        }
		    	
		    }
		}

		@Test(priority = 2, enabled = false)
		public void checking_the_status() throws InterruptedException {
		   // loginToPortal();
			 Thread.sleep(2000); // wait for Sent Items to load
			    
			    try {
			        WebElement newMsg = wait11.until(ExpectedConditions.elementToBeClickable(
			            By.xpath("//span[normalize-space()='New Message']")));

			        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", newMsg);
			        Thread.sleep(500); // small delay after scroll

			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newMsg);
			        System.out.println("Clicked on New Message");
			    } catch (Exception e) {
			        System.out.println("New Message click failed: " + e.getMessage());
			    }
			    Thread.sleep(2000); // Ensure login completes fully

			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			    WebElement sentItems = wait.until(ExpectedConditions.presenceOfElementLocated(
			        By.xpath("//span[normalize-space()='Sent Items']")));

			    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", sentItems);
			    Thread.sleep(500); // Let the scroll complete

			    // Optional debug: highlight the element
			    highlightElement(sentItems);

			    // Use JS click if regular click still fails
			    try {
			        wait.until(ExpectedConditions.elementToBeClickable(sentItems)).click();
			    } catch (ElementNotInteractableException e) {
			        System.out.println("Standard click failed. Trying JS click...");
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sentItems);
			    }
			    // ✅ Proceed to message details and status
			    driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/span[1]")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
			    Thread.sleep(1000);

			    
		}

		
		


		@Test(priority = 3, enabled = false)
		public void resending_multiple_msg_single_resend() throws InterruptedException {
			setUp();
			Thread.sleep(3000);
		    driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();

		    driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
		    driver.findElement(By.xpath("//div[@id='senderID']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//li[normalize-space()='april21']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681,9609789098,9607654678,9607890162");
		    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");

		    Thread.sleep(2000);
		    WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
		    Thread.sleep(2000);
		    highlightElement(sendButton);
		    sendButton.click();

		    Thread.sleep(2000);
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));

		    WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
		    highlightElement(confirmSendButton);
		    confirmSendButton.click();

		    // ✅ Wait for 2 minutes before checking message status
		    System.out.println("Waiting for 2 minutes to check message status...");
		    
		   Thread.sleep(Duration.ofMinutes(2).toMillis());
		 		 // ✅ Start stub after wait time
		 		try {
			        JSch jsch = new JSch();
			        com.jcraft.jsch.Session session = jsch.getSession("bmp", "10.0.6.137", 22);
			        session.setPassword("bmp");
			        session.setConfig("StrictHostKeyChecking", "no");
			        System.out.println(">>> Connecting to start stub...");
			        session.connect();

			        ChannelExec channel = (ChannelExec) session.openChannel("exec");
			        channel.setCommand ("cd /home/bmp/ && nohup /opt/bmp/bin/STUB_RespGen 1 1 1 1 1 10 0 > /dev/null 2>&1 &");
			        channel.setErrStream(null);  // Suppress error logs
			        channel.setInputStream(null);  // Disable input reading
			        channel.connect();

			        // Do not read or print the output
			        channel.disconnect();
			        session.disconnect();
			        System.out.println("✅ Stub started successfully.");
			    } catch (Exception e) {
			        System.out.println("❌ Failed to start stub:");
			        e.printStackTrace();
			    }



		    // ✅ Refresh by clicking the dashboard/menu link
		    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/ul/a[1]/li/div")).click();
		    Thread.sleep(3000);

		    // ✅ Go to Sent Items
		    driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();
		    Thread.sleep(2000);

		    // ✅ Proceed to message details and status
		    driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/span[1]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
		    Thread.sleep(1000);

		    // ✅ Check for "GSM Retry Attempt Excd" or "Network TimeOut"
		    boolean retryNeeded = false;

		    List<WebElement> gsmRetry = driver.findElements(By.xpath("//*[contains(text(),'GSM Retry Attempt Excd')]"));
		    List<WebElement> netTimeout = driver.findElements(By.xpath("//*[contains(text(),'Network TimeOut')]"));

		    if (!gsmRetry.isEmpty()) {
		        System.out.println("⚠ GSM Retry Attempt Excd found. Attempting resend...");
		        retryNeeded = true;
		    } else if (!netTimeout.isEmpty()) {
		        System.out.println("⚠ Network TimeOut found. Attempting resend...");
		        retryNeeded = true;
		    }

		    if (retryNeeded) {
		        // ✅ Click the resend button
		    	try {
		    	    // Click on the resend icon (SVG inside td)
		    	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
		    	    WebElement resendIcon = wait1.until(ExpectedConditions.elementToBeClickable(
		    	        By.cssSelector("td > .svg-inline--fa")
		    	    ));
		    	    Thread.sleep(1000);
		    	    highlightElement(resendIcon);
		    	    resendIcon.click();
		    	    System.out.println("✅ Resend icon clicked.");
		        } catch (TimeoutException | NoSuchElementException e) {
		            System.out.println("❌ Resend icon or 'New Message' button not found.");
		        }
		    
		    }
		}
			
		
		@Test(priority = 4, enabled = false)
		public void resending_multiple_msg_All_resend() throws InterruptedException {
			setUp();
			Thread.sleep(3000);
		    driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();

		    driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
		    driver.findElement(By.xpath("//div[@id='senderID']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//li[normalize-space()='april21']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681,9609789098,9607654678,9607890162,9607890900");
		    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");

		    Thread.sleep(2000);
		    WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
		    Thread.sleep(2000);
		    highlightElement(sendButton);
		    sendButton.click();

		    Thread.sleep(2000);
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));

		    WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
		    highlightElement(confirmSendButton);
		    confirmSendButton.click();

		    // ✅ Wait for 2 minutes before checking message status
		    System.out.println("Waiting for 2 minutes to check message status...");
		    
		   Thread.sleep(Duration.ofMinutes(2).toMillis());
		 		 // ✅ Start stub after wait time
		 		try {
			        JSch jsch = new JSch();
			        com.jcraft.jsch.Session session = jsch.getSession("bmp", "10.0.6.137", 22);
			        session.setPassword("bmp");
			        session.setConfig("StrictHostKeyChecking", "no");
			        System.out.println(">>> Connecting to start stub...");
			        session.connect();

			        ChannelExec channel = (ChannelExec) session.openChannel("exec");
			        channel.setCommand ("cd /home/bmp/ && nohup /opt/bmp/bin/STUB_RespGen 1 1 1 1 1 10 0 > /dev/null 2>&1 &");
			        channel.setErrStream(null);  // Suppress error logs
			        channel.setInputStream(null);  // Disable input reading
			        channel.connect();

			        // Do not read or print the output
			        channel.disconnect();
			        session.disconnect();
			        System.out.println("✅ Stub started successfully.");
			    } catch (Exception e) {
			        System.out.println("❌ Failed to start stub:");
			        e.printStackTrace();
			    }



		    // ✅ Refresh by clicking the dashboard/menu link
		    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/ul/a[1]/li/div")).click();
		    Thread.sleep(3000);

		    // ✅ Go to Sent Items
		    driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();
		    Thread.sleep(2000);

		    // ✅ Proceed to message details and status
		    driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/span[1]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
		    Thread.sleep(1000);

		    // ✅ Check for "GSM Retry Attempt Excd" or "Network TimeOut"
		    boolean retryNeeded = false;

		    List<WebElement> gsmRetry = driver.findElements(By.xpath("//*[contains(text(),'GSM Retry Attempt Excd')]"));
		    List<WebElement> netTimeout = driver.findElements(By.xpath("//*[contains(text(),'Network TimeOut')]"));

		    if (!gsmRetry.isEmpty()) {
		        System.out.println("⚠ GSM Retry Attempt Excd found. Attempting resend...");
		        retryNeeded = true;
		    } else if (!netTimeout.isEmpty()) {
		        System.out.println("⚠ Network TimeOut found. Attempting resend...");
		        retryNeeded = true;
		    }

		    if (retryNeeded) {
		        // ✅ Click the resend button
		    	try {
		    	    // Click on the resend icon (SVG inside td)
		    	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
		    	    WebElement resendIcon = wait1.until(ExpectedConditions.elementToBeClickable(
		    	        By.cssSelector("td > .svg-inline--fa")
		    	    ));
		    	    Thread.sleep(1000);
		    	    highlightElement(resendIcon);
		    	    resendIcon.click();
		    	    System.out.println("✅ Resend icon clicked.");
		        } catch (TimeoutException | NoSuchElementException e) {
		            System.out.println("❌ Resend icon or 'New Message' button not found.");
		        }
		    
		    }
		}
			
		
		@Test(priority = 5, enabled = false)
		public void resending_multiple_msg_All_test() throws InterruptedException {
			setUp();

		    Thread.sleep(3000);
		  //  driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/d]")).click();

		    driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
		    driver.findElement(By.xpath("//div[@id='senderID']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//li[normalize-space()='april21']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681,9609878909,9607678909,9607654246,9609876439");
		    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");

		    Thread.sleep(2000);
		    WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
		    Thread.sleep(2000);
		    highlightElement(sendButton);
		    sendButton.click();

		    Thread.sleep(2000);
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));

		    WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
		    highlightElement(confirmSendButton);
		    confirmSendButton.click();

		    // ✅ Wait for 2 minutes before checking message status
		    System.out.println("Waiting for 2 minutes to check message status...");
		    
		   Thread.sleep(Duration.ofMinutes(2).toMillis());
		 		 // ✅ Start stub after wait time
		 		try {
			        JSch jsch = new JSch();
			        com.jcraft.jsch.Session session = jsch.getSession("bmp", "10.0.6.137", 22);
			        session.setPassword("bmp");
			        session.setConfig("StrictHostKeyChecking", "no");
			        System.out.println(">>> Connecting to start stub...");
			        session.connect();

			        ChannelExec channel = (ChannelExec) session.openChannel("exec");
			        channel.setCommand ("cd /home/bmp/ && nohup /opt/bmp/bin/STUB_RespGen 1 1 1 1 1 10 0 > /dev/null 2>&1 &");
			        channel.setErrStream(null);  // Suppress error logs
			        channel.setInputStream(null);  // Disable input reading
			        channel.connect();

			        // Do not read or print the output
			        channel.disconnect();
			        session.disconnect();
			        System.out.println("✅ Stub started successfully.");
			    } catch (Exception e) {
			        System.out.println("❌ Failed to start stub:");
			        e.printStackTrace();
			    }



		    // ✅ Refresh by clicking the dashboard/menu link
		  //  driver.findElement(By.cssSelector(".MuiBox-root.css-2r9v8s")).click();
		 		 driver.findElement(By.cssSelector("div:nth-child(1)")).click();
		 	    driver.findElement(By.cssSelector("a:nth-child(5) .MuiTypography-root")).click();
		    Thread.sleep(3000);

		    // ✅ Go to Sent Items
		    driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();
		    Thread.sleep(2000);

		    // ✅ Proceed to message details and status
		    driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/span[1]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
		    Thread.sleep(1000);

		    // ✅ Check for "GSM Retry Attempt Excd" or "Network TimeOut"
		    boolean retryNeeded = false;

		    List<WebElement> gsmRetry = driver.findElements(By.xpath("//*[contains(text(),'GSM Retry Attempt Excd')]"));
		    List<WebElement> netTimeout = driver.findElements(By.xpath("//*[contains(text(),'Network TimeOut')]"));

		    if (!gsmRetry.isEmpty()) {
		        System.out.println("⚠ GSM Retry Attempt Excd found. Attempting resend...");
		        retryNeeded = true;
		    } else if (!netTimeout.isEmpty()) {
		        System.out.println("⚠ Network TimeOut found. Attempting resend...");
		        retryNeeded = true;
		    }

		    if (retryNeeded) {
		        // ✅ Click the resend button
		    	try {
		    	    // Click on the resend icon (SVG inside td)
		    	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
		    	    WebElement resendIcon = wait1.until(ExpectedConditions.elementToBeClickable(
		    	        By.cssSelector("td > .svg-inline--fa")
		    	    ));
		    	    Thread.sleep(1000);
		    	    highlightElement(resendIcon);
		    	    resendIcon.click();
		    	    System.out.println("✅ Resend icon clicked.");
		       
		        } catch (TimeoutException | NoSuchElementException e) {
		            System.out.println("❌ Resend icon or 'New Message' button not found.");
		        }
		    
		    }
		}

		
		 public void Dbconnect(String testName, String result, String failureReason, String timestamp, String product) {
		        try (Connection connection = DriverManager.getConnection(
		        		resenddatprovide.DB_URL+ "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", 
		                resenddatprovide.DB_USER, 
		                resenddatprovide.DB_PASSWORD)) 
		        {
		            // SQL query with the new 'product' column
		            String sqlQuery = "INSERT INTO testng_results (test_name, result, failure_reason, run_timestamp, product) " +
		                              "VALUES (?, ?, ?, ?, ?)";
		            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
		                // Bind the parameters to the query
		                preparedStatement.setString(1, testName);
		                preparedStatement.setString(2, result);
		                preparedStatement.setString(3, failureReason);
		                preparedStatement.setString(4, timestamp);
		                preparedStatement.setString(5, product);
		 
		                // Execute the SQL query
		                int rowsAffected = preparedStatement.executeUpdate();
		 
		                // Log the result
		                System.out.println("Number of rows affected: " + rowsAffected);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }}
		    
		// to login as a client and send sender_id request	
		

		private WebElement highlightElement(WebElement element) {
			// TODO Auto-generated method stub
			return null;
		}

		@AfterClass
		public void setUps() {
//			driver.quit();
		}
		// Method to highlight elements
				public static WebElement highlightElement1(WebElement element) {
				    JavascriptExecutor js = (JavascriptExecutor) driver;
				    String originalStyle = element.getAttribute("style");
				    js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red; background: yellow;');", element);
				    try {
				        Thread.sleep(500); // Highlight for visibility
				    } catch (InterruptedException e) {
				        Thread.currentThread().interrupt();
				    }
				    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
				    return element; // ✅ Return WebElement to allow method chaining
				}
				@AfterSuite (enabled = false)
				public void sendEmailReport() {
					// Email configuration
					final String senderEmail = "pankaj.v@tayana.in"; // Replace with your email
					final String senderPassword = "Bangalore@2607"; // Replace with your email password
					final String recipientEmails = "nagabindu.g@tayana.in"; // TO recipients
					final String ccEmails = ""; // CC recipients
					final String subject = "TestNG Emailable Report";
					final String messageBody = "Please find attached the emailable report of Admin GUI automation cases";

					// List of files to be attached
					String[] reportPaths = { System.getProperty("user.dir") + "/test-output/emailable-report.html",
							System.getProperty("user.dir") + "/test-output/index.html"
							//System.getProperty("user.dir") + "/test-output/passed.png"
							};

					// Set up properties for the email
					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.office365.com");
					props.put("mail.smtp.port", "587");

					// Create session with authentication
					Session session = Session.getInstance(props, new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(senderEmail, senderPassword);
						}
					});

					try {
						// Create message
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress(senderEmail));
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmails)); // TO recipients
						message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmails)); // CC recipients
						message.setSubject(subject);

						// Create message body part
						MimeBodyPart messageBodyPart = new MimeBodyPart();
						messageBodyPart.setText(messageBody);

						// Create a multipart for attaching files
						MimeMultipart multipart = new MimeMultipart();
						multipart.addBodyPart(messageBodyPart);

						// Loop through each file and attach
						for (String filePath : reportPaths) {
							MimeBodyPart attachmentPart = new MimeBodyPart();
							attachmentPart.attachFile(new File(filePath));
							multipart.addBodyPart(attachmentPart);
						}

						// Set content
						message.setContent(multipart);

						// Send email
						Transport.send(message);
						System.out.println("Email sent successfully with multiple attachments!");
						//System.out.println("Number of rows affected: " + rowsAffected);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
		
		


