package kyc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dbcheck {

    public static WebDriver driver;
    public String subscriberId; // Class-level variable to store MSISDN

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new FirefoxDriver(); // Initialize the WebDriver instance
        driver.get("http://10.0.0.174:8080/tssgui/welcome/jsp/login.jsp");

        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin"); // Entering the username
        driver.findElement(By.xpath("//*[@id=\"passdiv\"]/div/input")).sendKeys("admin@1234"); // Entering the password
        driver.findElement(By.xpath("//*[@id=\"subBtn\"]")).click(); // Performing the click action
        Thread.sleep(2000); // Wait for the page to load
    }

    @Test(priority = 1)
    public void Dbconnect() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://10.0.0.174:3306/SRM?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", 
                "srm", 
                "SrmT4y4n4_EKYC")) 
        {
            // Creating a statement object
            Statement statement = connection.createStatement();
            // Executing the SQL query
            String sqlQuery = "SELECT MSISDN FROM SRM_SIM_INVENTORY WHERE PKG_ID=10 AND MSISDN LIKE '17%' AND SIM_STATUS=6 LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Processing the query results
            if (resultSet.next()) {
                // Retrieve MSISDN value from the result set as String
                subscriberId = resultSet.getString("MSISDN"); // Assuming MSISDN is a phone number (String)
                // Print the value for verification
                System.out.println("MSISDN: " + subscriberId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "MSISDN")
    public Object[][] MSISDN() {
        // Return the subscriberId as an Object array
        return new Object[][] { { subscriberId } };
    }

    @Test(priority = 2, dataProvider = "MSISDN")
    public void KYCFORM(String msisdn) throws InterruptedException {
   	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     // Replace this XPath with the appropriate title if it's not empty
		 
		 WebElement oButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/div[2]/div/form/div[3]/div/div[2]/ul/li[4]/a/b")));
		 oButton.click();
		 
		 
		 
     WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='']")));
     goButton.click();

     // Click the second button after waiting for it to be clickable
     WebElement secondButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".box-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1)")));
     secondButton.click();

     // Wait for the modal to appear and then click on it
    WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ModalToDisplayPreBook']")));
 modal.click();

     // Wait for the dropdown to become clickable
    WebElement docTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='preBookDocType']")));
     docTypeDropdown.click();

     // Selecting 'PASSPORT' from dropdown
     Select documentSelect = new Select(docTypeDropdown);
     documentSelect.selectByVisibleText("PASSPORT");
     
     driver.findElement(By.xpath("//*[@id=\"preBookFilterVal\"]")).sendKeys("E00007730");
     driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/form/div/section/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div/span/i")).click();
     Thread.sleep(30000);
        driver.findElement(By.cssSelector("#checkbox_0")).click();
        Thread.sleep(2000);
        // Fill MSISDN in the input field using the value provided by DataProvider
        driver.findElement(By.xpath("//*[@id=\"MSISDN\"]")).sendKeys(msisdn); // Passing the value
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(1100,888)");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("agree"))).click(); // Agree checkbox

        // Fill in other form fields
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ADDRESS_LINE1_0"))).sendKeys("Bhuthan");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ADDRESS_LINE1_1"))).sendKeys("Trinity");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("REGION_0"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"REGION_0\"]/option[9]"))).click(); // Region 1
        wait.until(ExpectedConditions.elementToBeClickable(By.id("REGION_1"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"REGION_1\"]/option[8]"))).click(); // Region 2
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ADDRESS_LINE3_1"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ADDRESS_LINE3_1\"]/option[14]"))).click(); // Address line 3
    
        driver.findElement(By.xpath("//*[@id=\"ADDRESS_LINE4_1\"]")).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("COUNTRY_1"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"COUNTRY_1\"]/option[1]"))).click(); // Country

        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click(); // Submit form
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[26]/div[7]/div/button"))).click(); // Confirmation button

        // Extract message text
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/form/div/section/div/div[1]/div[1]/div/div")));
        String messageText = messageElement.getText();
        System.out.println("Extracted Message: " + messageText);

        // Query to check status 12 in database
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://10.0.0.174:3306/SRM?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", 
                "srm", 
                "SrmT4y4n4_EKYC")) 
        {
            Statement statement1 = connection.createStatement();
            String sqlQuery2 = "SELECT SIM_STATUS FROM SRM_SIM_INVENTORY WHERE MSISDN = '" + msisdn + "' AND SIM_STATUS = 12;";
            ResultSet resultSet1 = statement1.executeQuery(sqlQuery2);

            if (resultSet1.next()) {
                String simStatus = resultSet1.getString("SIM_STATUS");
                System.out.println("SIM Status for MSISDN " + msisdn + " is: " + simStatus);
            } else {
                System.out.println("No records found with SIM_STATUS 12 for MSISDN " + msisdn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        // Optional: driver.quit(); // Close the browser after tests are complete
    }
}
