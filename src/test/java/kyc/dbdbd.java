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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dbdbd {

	public static WebDriver driver;
	public String subscriberId; // Class-level variable to store MSISDN
	public String SIM_STATUS;

	@BeforeClass
	public void setUp() throws InterruptedException {
		driver = new FirefoxDriver(); 
		driver.get("http://10.0.0.174:8080/tssgui/welcome/jsp/login.jsp");

		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin"); 
		driver.findElement(By.xpath("//*[@id=\"passdiv\"]/div/input")).sendKeys("admin@123");
		driver.findElement(By.xpath("//*[@id=\"subBtn\"]")).click(); 
		Thread.sleep(2000); 
	}

	@Test(priority = 1)
	public void Dbconnect() {
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://10.0.0.174:3306/SRM?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
				"srm", "SrmT4y4n4_EKYC")) {
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
		// Initialize WebDriverWait with a timeout of 10 seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// Click on KYC Form link in GUI
		driver.findElement(By
				.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/div[2]/div/form/div[3]/div/div[2]/ul/li[4]/a/b"))
				.click();
		Thread.sleep(1000);
		// After KYC form page opened, click on GO button
		WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='']")));
		goButton.click();
		// Click on PreBook info button
		// WebElement prebook =
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ModalToDisplayPreBook']")));
		// prebook.click();
		// Click on Document type dropdown
		Thread.sleep(6000);
		driver.findElement(By.xpath("//button[@id='ModalToDisplayPreBook']")).click(); // Click on prebook
		Thread.sleep(5000);
		driver.findElement(By.xpath("//select[@id='preBookDocType']")).click(); // Click on Document type dropdown
		Thread.sleep(2000);
		// Select second option i.e. passport in that dropdown
		WebElement dropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"preBookDocType\"]/option[2]")));
		dropdown.click();
		// Send Document number in document number field
		driver.findElement(By.xpath("//*[@id=\"preBookFilterVal\"]")).sendKeys("E00007730");
		Thread.sleep(2000);
		// After passing Document number, click on search button
		WebElement searchbutton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"viewPreBookQueryModal\"]/div[2]/div[2]/div/div/span")));
		searchbutton.click();
		// Select any one from PreBook info details
		WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkbox_0")));
		checkbox.click();
		// Fill MSISDN in the input field using the value provided by DataProvider
		driver.findElement(By.xpath("//*[@id=\"MSISDN\"]")).sendKeys(msisdn);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1100,888)");
		driver.findElement(By.xpath("//*[@id=\"agree\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ADDRESS_LINE1_0\"]")).sendKeys("Bhuthan");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ADDRESS_LINE1_1\"]")).sendKeys("Trinity");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[@id='REGION_0']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"REGION_0\"]/option[9]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"REGION_1\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"REGION_1\"]/option[8]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ADDRESS_LINE3_1\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ADDRESS_LINE3_1\"]/option[14]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ADDRESS_LINE4_1\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ADDRESS_LINE4_1\"]/option[8]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"COUNTRY_1\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"COUNTRY_1\"]/option[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[26]/div[7]/div/button")).click();
		Thread.sleep(4000);
		WebElement messageElement = driver.findElement(
				By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/form/div/section/div/div[1]/div[1]/div/div"));
		String messageText = messageElement.getText();
		System.out.println("Extracted Message: " + messageText);

		// Execute the additional query to check SIM_STATUS
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://10.0.0.174:3306/SRM?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
				"srm", "SrmT4y4n4_EKYC")) {
			// Creating a statement object
			Statement statement = connection.createStatement();
			// Execute SQL query to check SIM_STATUS
			String sqlQuery = "SELECT SIM_STATUS FROM SRM_SIM_INVENTORY WHERE MSISDN = '" + msisdn + "'";
			ResultSet resultSet = statement.executeQuery(sqlQuery);

			if (resultSet.next()) {
				// Get the SIM_STATUS
				SIM_STATUS = resultSet.getString("SIM_STATUS");
				System.out.println("SIM_STATUS: " + SIM_STATUS);
				// Check if SIM_STATUS is 12
				if ("12".equals(SIM_STATUS)) {
					System.out.println("Success");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {
		// if (driver != null) {
		// driver.quit(); // Close the browser
	}
}
