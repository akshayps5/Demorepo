package bmp_react;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class akshay {
    public static WebDriver driver;
    private static final String DB_URL = "jdbc:mysql://10.0.1.210:3306/BMPDB";
    private static final String DB_USER = "bmp";
    private static final String DB_PASSWORD = "bmp@Tayana123";

    String result;
    String failureReason = "";
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String product = "BMP";

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new FirefoxDriver(); // Use class-level driver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.get("https://10.0.6.137:8443/bmp");
        driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys("Admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().window().maximize();
	//	Thread.sleep(2000);

      /*  
        driver.get("https://10.0.1.210:3002/bmp/auth/login");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("transferpts");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj@2000");
        driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();
        driver.manage().window().maximize();*/
    }

    public void Dbconnect(String testName, String result, String failureReason, String timestamp, String product) {
        try (Connection connection = DriverManager.getConnection(
                DB_URL + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", 
                DB_USER, 
                DB_PASSWORD)) 
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
        }
    }

    @Test(priority = 1)
    public void clientdeletion() throws InterruptedException {
    
        
        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[3]/a")).click();
        Thread.sleep(4000);
        
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/input[1]")).sendKeys("Xavier");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[9]/div[1]/*[name()='svg'][2]/*[name()='path'][1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")).click();
        
        Thread.sleep(4000);}
    
        @Test(priority = 2, enabled=false)  
        public void clientcreate() throws InterruptedException {  
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/*[name()='svg'][1]")).click();
        driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("Xavier");

        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("Xavier1234");
        
        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[4]/div/fieldset/div[1]/div/input")).sendKeys("10");
        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[9]/div/fieldset/div[1]/div/input")).sendKeys("xavier@gmail.com");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'BMP1234')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[12]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Postpaid')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[13]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("12345678");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[14]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("9876");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[15]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[16]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[17]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[22]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[23]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[19]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[24]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Retry')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[25]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[26]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[28]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
        driver.findElement(By.xpath("//li[contains(@title,'testpk')]")).click();
        
        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[3]/div/fieldset/div[1]/div/input")).sendKeys("1");
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/button[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")).click();
               
        Thread.sleep(4000);
        
       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[1]/label[1]")).click();
       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")).click();
 
        System.out.println("success xavier");
    }

    @AfterMethod
    public void logTestResult(ITestResult testResult) {
        // Determine the result of the test
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            result = "Passed";
            failureReason = ""; // No failure reason for successful tests
        } else if (testResult.getStatus() == ITestResult.FAILURE) {
            result = "Failed";
            
            // Capture the throwable and limit the failure reason to the first two lines
            Throwable throwable = testResult.getThrowable();
            if (throwable != null) {
                String[] lines = throwable.toString().split("\n");
                failureReason = String.join("\n", lines[0], lines[1]); // First two lines
            } else {
                failureReason = "Unknown failure reason.";
            }
        } else {
            result = "Skipped";
            failureReason = "Test skipped.";
        }

        // Log the result to the database
        Dbconnect(testResult.getMethod().getMethodName(), result, failureReason, timestamp, product);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
      //      driver.quit();
        }
    }
    
    @AfterSuite(enabled = true)
    public void sendEmailReport() {
        // Email configuration
        final String senderEmail = "xavier.t@tayana.in"; // Replace with your email
        final String senderPassword = "packed@45657#"; // Replace with your email password
        final String recipientEmails = "akshay.ps@tayana.in"; // TO recipients
        // final String ccEmails = "xavier.t@tayana.in,pappu.kiran@tayana.in"; // CC recipients

        final String subject = "TestNG Emailable Report";
        final String messageBody = "Please find attached the emailable report of GUI automation cases";

        // Absolute paths to the report files
        String[] reportPaths = { 
            "C:\\Users\\akshay.ps\\eclipse-workspace\\SMSC\\test-output\\emailable-report.html",
            "C:\\Users\\akshay.ps\\eclipse-workspace\\SMSC\\test-output\\index.html"
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
            // message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmails)); // CC recipients
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
