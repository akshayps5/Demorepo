package ssfc;


	import java.io.File;
	import java.time.Duration;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import javax.mail.Authenticator;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Multipart;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;

	import org.openqa.selenium.By;
	import org.openqa.selenium.SearchContext;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;

	import org.testng.annotations.Test;

public class mainmailclass  extends indira{

		public static WebDriver driver;
		

		@SuppressWarnings("deprecation")
		@BeforeClass
		public void access () {
			WebDriver driver= new ChromeDriver();	
			mainmailclass.driver= driver;
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://tayana.greythr.com/uas/portal/auth/login");
		}
		
		@SuppressWarnings("deprecation")
		@Test(dataProvider = "getDetails", priority = 1)
		public void loginDetails(String id, String password, String name) throws InterruptedException {
			//System.out.println(id + " " + password);
			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(id);
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
			driver.findElement(By.xpath("/html/body/app-root/uas-portal/div/div/main/div/section/div[1]/o-auth/section/div/app-login/section/div/div/div/form/button")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// Assertion
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement atualmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainSidebar\"]/div[1]/div[2]/div[1]/p")));
			String actualText = atualmsg.getText().trim();
			//System.out.println(actualText);
			Assert.assertEquals(actualText, name);
			Thread.sleep(2000);
		}
		
		
		@Test( dependsOnMethods = "loginDetails", dataProvider = "getDetails")
		public void checkAttendance(String host, String port, final String username, final String password, String to, String subject, String messageText) throws InterruptedException {
			
			// Locate the host element (gt-ess-menu) and get its shadow root
			SearchContext shadow = driver.findElement(By.cssSelector("body > app:nth-child(2) > ng-component:nth-child(2) > div:nth-child(1) > div:nth-child(2) > gt-sidebar:nth-child(1) > aside:nth-child(1) > div:nth-child(2) > gt-ess-menu:nth-child(1)")).getShadowRoot();
			Thread.sleep(1000); // Optional: Wait for the shadow DOM to be ready
			// Locate the option inside the shadow DOM using the CSS selector
			WebElement menuItem = shadow.findElement(By.cssSelector("nav:nth-child(1) > ul:nth-child(1) > span:nth-child(7) > li:nth-child(1) > a:nth-child(1) > span:nth-child(2)"));
			// Click the option
			menuItem.click();

			// Attendance Info page
			//This Element is inside single shadow DOM.
			@SuppressWarnings("unused")
			String cssSelectorForHost1 = "body > app:nth-child(2) > ng-component:nth-child(2) > div:nth-child(1) > div:nth-child(2) > gt-sidebar:nth-child(1) > aside:nth-child(1) > div:nth-child(2) > gt-ess-menu:nth-child(1)";
			Thread.sleep(1000);
			SearchContext shadow1 = driver.findElement(By.cssSelector("body > app:nth-child(2) > ng-component:nth-child(2) > div:nth-child(1) > div:nth-child(2) > gt-sidebar:nth-child(1) > aside:nth-child(1) > div:nth-child(2) > gt-ess-menu:nth-child(1)")).getShadowRoot();
			Thread.sleep(1000);
			WebElement attendInfo = shadow1.findElement(By.cssSelector(" nav:nth-child(1) > ul:nth-child(1) > span:nth-child(7) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)"));
			
			attendInfo.click();
			
			// Checking the swipes
			
			driver.findElement(By.xpath("//span[@class='text-secondary text-4']")).click();
			
			// Login time check
			WebElement loginInfo = driver.findElement(By.cssSelector("body > app:nth-child(2) > ng-component:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > gt-attendance-info-calendar:nth-child(2) > div:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > accordion:nth-child(1) > accordion-group:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1) > p:nth-child(1)"));
			String loginTimeElement= loginInfo.getText();
			//System.out.println("Your Login time is " + loginTimeElement);
			
			    
		        // Path to emailable report
		       // @SuppressWarnings("unused")
				// String reportPath = System.getProperty("user.dir") + "/test-output/emailable-report.html";
		        
		        // Set up mail server properties
		        Properties props = new Properties();
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.host", host);
		        props.put("mail.smtp.port", port);

		        // Create a session with authentication
		        Session session = Session.getInstance(props, new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username, password);
		            }
		        });

		        try {
		            // Create message
		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress(username));
		            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		            message.setSubject(subject);

		            // Create message body part
		            MimeBodyPart messageBodyPart = new MimeBodyPart();
		            messageBodyPart.setText(messageText + loginTimeElement);

		            // Create attachment part
//		            MimeBodyPart attachmentPart = new MimeBodyPart();
//		            attachmentPart.attachFile(new File(reportPath));

		            // Combine body and attachment
		            Multipart multipart = new MimeMultipart();
		            multipart.addBodyPart(messageBodyPart);
		           // multipart.addBodyPart(attachmentPart);

		            // Set content
		            message.setContent(multipart);

		            // Send email
		            Transport.send(message);
		            System.out.println("Email sent successfully with emailable report!");

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			
		}

		@AfterClass
		public void CloseBrowser() {
			driver.quit();
			
			   
		}
	}


