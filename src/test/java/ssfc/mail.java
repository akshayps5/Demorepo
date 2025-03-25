package ssfc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class mail {
	public static WebDriver driver;
	private static final String DB_URL = "jdbc:mysql://10.0.1.210:3306/BMPDB";
    private static final String DB_USER = "bmp";
    private static final String DB_PASSWORD = "bmp@Tayana123";
	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("https://10.0.6.65:8001/ssfc/login");
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
		driver.findElement(By.id("tsslogin-form_password")).sendKeys("Tayana@234");
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
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

	@Test(priority = 1, enabled = true)
	public void Fraud_MTspam() throws InterruptedException {
		// Test steps for adding/deleting spam keywords
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/a")).click();
		Thread.sleep(1000);
		// Remaining test logic...
	}

	@Test
	public void testcase() {
		System.out.println("This is a GUI testing");
	}

	@AfterSuite
	public void sendEmailReport() {
		// Email configuration
		final String senderEmail = "indira.bs@tayana.in"; // Replace with your email
		final String senderPassword = "$IBStamotec38$"; // Replace with your email password
		final String recipientEmails = "indira.bs@tayana.in,akshay.ps@tayana.in"; // TO recipients
		final String ccEmails = "sushmita.c@tayana.in,pappu.kiran@tayana.in"; // CC recipients
		final String subject = "TestNG Emailable Report";
		final String messageBody = "Please find attached the emailable report of GUI automation cases";

		// List of files to be attached
		String[] reportPaths = { System.getProperty("user.dir") + "/test-output/emailable-report.html",
				System.getProperty("user.dir") + "/test-output/index.html",
				System.getProperty("user.dir") + "/test-output/failed.png" };

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
			Multipart multipart = new MimeMultipart();
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
