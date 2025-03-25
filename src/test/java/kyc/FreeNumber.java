package kyc;


import org.testng.Assert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FreeNumber {

    public static WebDriver driver;
    public String subscriberId;

    // Declare these variables as class-level variables
    private int dbNonTouristTotal;
    private int dbNonTouristAllowed;
    private int dbTouristTotal;
    private int dbTouristAllowed;

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new FirefoxDriver(); // Initialize the WebDriver instance
        driver.get("http://10.0.0.174:8080/tssgui/welcome/jsp/login.jsp");

        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin"); // Entering the username
        driver.findElement(By.xpath("//*[@id=\"passdiv\"]/div/input")).sendKeys("admin@123"); // Entering the password
        driver.findElement(By.xpath("//*[@id=\"subBtn\"]")).click(); // Performing the click action
        Thread.sleep(2000); // Wait for the page to load
    }

    @Test(priority = 1, enabled = true)
    public void FreeMsisdn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://10.0.0.174:3306/SRM?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
                "srm",
                "SrmT4y4n4_EKYC")) {
            Statement statement = connection.createStatement();

            // Query 1: Get total count for Non-Tourist MSISDN
            String sqlQuery8 = "SELECT COUNT(DISTINCT(MSISDN)) AS TotalCount FROM SRM_FREE_MSISDN_INVENTORY WHERE STATUS = 0 AND MSISDN NOT LIKE '172%';";
            ResultSet resultSet8 = statement.executeQuery(sqlQuery8);
            if (resultSet8.next()) {
                dbNonTouristTotal = resultSet8.getInt("TotalCount");
                System.out.println("DBTotalNonTouristCountsubs: " + dbNonTouristTotal);
            }

            // Query 2: Get total allowed count for Non-Tourist MSISDN
            String sqlQuery1 = "SELECT COUNT(DISTINCT(a.MSISDN)) AS TotalCount1 FROM SRM_FREE_MSISDN_INVENTORY a WHERE a.STATUS = 0 AND a.ENTRY_DATE <= NOW() - INTERVAL 90 DAY AND a.MSISDN NOT LIKE '16%' AND a.MSISDN NOT LIKE '170%' AND a.MSISDN NOT LIKE '171%' AND a.MSISDN NOT LIKE '172%' AND NOT EXISTS (SELECT 1 FROM SRM_SIM_INVENTORY d WHERE d.MSISDN = a.MSISDN);";
            ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
            if (resultSet1.next()) {
                dbNonTouristAllowed = resultSet1.getInt("TotalCount1");
                System.out.println("DBAllowedNonTouristCountsubs: " + dbNonTouristAllowed);
            }

            // Query 3: Get total count for Tourist MSISDN
            String sqlQuery2 = "SELECT COUNT(DISTINCT(MSISDN)) AS TotalCount2 FROM SRM_FREE_MSISDN_INVENTORY WHERE STATUS = 0 AND MSISDN LIKE '172%';";
            ResultSet resultSet2 = statement.executeQuery(sqlQuery2);
            if (resultSet2.next()) {
                dbTouristTotal = resultSet2.getInt("TotalCount2");
                System.out.println("DBTotalTouristCountsubs: " + dbTouristTotal);
            }

            // Query 4: Get total allowed count for Tourist MSISDN
            String sqlQuery3 = "SELECT COUNT(DISTINCT(MSISDN)) AS TotalCount3 FROM SRM_FREE_MSISDN_INVENTORY WHERE STATUS = 0 AND MSISDN LIKE '172%' AND ENTRY_DATE <= NOW() - INTERVAL 90 DAY;";
            ResultSet resultSet3 = statement.executeQuery(sqlQuery3);
            if (resultSet3.next()) {
                dbTouristAllowed = resultSet3.getInt("TotalCount3");
                System.out.println("DBAllowedTouristCountsubs: " + dbTouristAllowed);
            }

            // Now call the compare method with the fetched DB values
         //   compareWithDb(dbNonTouristTotal, dbNonTouristAllowed, dbTouristTotal, dbTouristAllowed);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Click on Free Msisdn link in GUI
        WebElement FreeMsisdnUpload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='Free Msisdn Upload']")));
        FreeMsisdnUpload.click();

        // Click on Generate free msisdn
        WebElement GenFreeMsisdn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-id-3\"]/label/u")));
        GenFreeMsisdn.click();

        // Extract the total count for non-tourist MSISDN from GUI
        WebElement TotalNonTouristCount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Total Number of Free MSISDN in Inventory')]")));
        String messageText1 = TotalNonTouristCount.getText();
        String[] parts1 = messageText1.split(":");
        int TotalCountOfNonTouristSubs = Integer.parseInt(parts1[1].trim());
        System.out.println("Extracted Total count of Non Tourist subscriber: " + TotalCountOfNonTouristSubs);

        // Extract the total count for allowed non-tourist MSISDN from GUI
        WebElement allowedNonTouristCount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Allowed Number of Free MSISDN')]")));
        String messageText2 = allowedNonTouristCount.getText();
        String[] parts2 = messageText2.split(":");
        int AllowedCountOfNonTouristSubs = Integer.parseInt(parts2[1].trim());
        System.out.println("Extracted Allowed count of Non Tourist subscriber: " + AllowedCountOfNonTouristSubs);
        
        // Click on Generate free msisdn for Tourist link
        WebElement GenFreeMsisdnTourist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-id-5\"]/label/u")));
        GenFreeMsisdnTourist.click();
        
        // Extract the total count for tourist MSISDN from GUI
        WebElement TotalTouristCount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Total Number of Free MSISDN in Inventory for Tourist')]")));
        String messageText3 = TotalTouristCount.getText();
        String[] parts3 = messageText3.split(":");
        int TotalCountOfTouristSubs = Integer.parseInt(parts3[1].trim());
        System.out.println("Extracted Total count of Tourist Subscriber: " + TotalCountOfTouristSubs);

        // Extract the allowed count for tourist MSISDN from GUI
        WebElement AllowedTouristCount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Allowed Number of Tourist Free MSISDN')]")));
        String messageText4 = AllowedTouristCount.getText();
        String[] parts4 = messageText4.split(":");
        int AllowedCountOfTouristSubs = Integer.parseInt(parts4[1].trim());
        System.out.println("Extracted Allowed count of Tourist subscriber: " + AllowedCountOfTouristSubs);
        
        System.out.println("GUI Non-Tourist Total: " + TotalCountOfNonTouristSubs);
        
        // Now call the method to compare GUI with DB values
     //   compareWithDb(TotalCountOfNonTouristSubs, AllowedCountOfNonTouristSubs, TotalCountOfTouristSubs, AllowedCountOfTouristSubs);
        Assert.assertEquals(TotalCountOfNonTouristSubs, dbNonTouristTotal, "Non-Tourist Total MSISDN mismatch between GUI and DB. GUI value: " + TotalCountOfNonTouristSubs + ", DB value: " + dbNonTouristTotal);
        Assert.assertEquals(AllowedCountOfNonTouristSubs, dbNonTouristAllowed, "Non-Tourist Allowed MSISDN mismatch between GUI and DB");
        Assert.assertEquals(TotalCountOfTouristSubs, dbTouristTotal, "Tourist Total MSISDN mismatch between GUI and DB");
        Assert.assertEquals(AllowedCountOfTouristSubs, dbTouristAllowed, "Tourist Allowed MSISDN mismatch between GUI and DB");
 //   @Test(priority = 2)
   // public void Dbconnect() {
       
    }

    // Method to compare GUI and DB values
   // public void compareWithDb(int TotalCountOfNonTouristSubs, int AllowedCountOfNonTouristSubs, int TotalCountOfTouristSubs, int AllowedCountOfTouristSubs) {
        // Assert that the GUI values match the DB values
    

    @DataProvider(name = "MSISDN")
    public Object[][] MSISDN() {
        return new Object[][]{{subscriberId}};
    }
}
