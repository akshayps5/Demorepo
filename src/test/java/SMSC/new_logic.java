package SMSC;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class new_logic {
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
        driver.get("https://10.0.1.210:3002/bmp/auth/login");

        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("transferpts");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Pankaj@2000");
        driver.findElement(By.xpath("//button[@id='kt_sign_in_submit']")).click();
        driver.manage().window().maximize();
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
    public void sending_normal_msg1() throws InterruptedException {
        // clicks on the profile icon
        driver.findElement(By.id("mui-4")).sendKeys("9748745");
        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message 1");
        driver.findElement(By.xpath("//div[@class='col-lg-4']//div[2]//button[1]")).click();
        Thread.sleep(1000);

        // Accept (click OK) to close the alert
        driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='Send']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[normalize-space()='New Message']")).click();
        Thread.sleep(2000);
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
            driver.quit();
        }
    }
}
