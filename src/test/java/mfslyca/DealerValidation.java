package mfslyca;



	import java.awt.AWTException;
	import java.io.File;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Set;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import net.sourceforge.tess4j.ITesseract;
	import net.sourceforge.tess4j.Tesseract;
	import net.sourceforge.tess4j.TesseractException;

	public class DealerValidation {
		private WebDriver driver;
		private Map<String, Object> vars;
		JavascriptExecutor js;

		@BeforeClass
		public void setUp() throws IOException, TesseractException, InterruptedException {
			driver = new ChromeDriver();
			js = (JavascriptExecutor) driver;
			vars = new HashMap<String, Object>();

			driver.get("http://10.0.6.107/");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img")));

			// Take a screenshot of the captcha
			File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
			Thread.sleep(2000);
			File destFile = new File(
					"C:\\Users\\Moulya\\eclipse-workspace\\QA\\src\\test\\java\\captchaimages\\captcha.png");
			FileUtils.copyFile(srcFile, destFile);

			// OCR Processing
			ITesseract tess = new Tesseract();
			tess.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
			tess.setLanguage("eng");

			String captchaText = tess.doOCR(destFile);
			System.out.println("Extracted Captcha Text: " + captchaText);
			driver.manage().window().setSize(new Dimension(1280, 672));
			driver.findElement(By.name("Username")).click();
			driver.findElement(By.name("Username")).sendKeys("tayana");
			driver.findElement(By.name("Password")).click();
			driver.findElement(By.name("Password")).sendKeys("tayana");
			driver.findElement(By.id("captchaUsr")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("captchaUsr")).sendKeys(captchaText);
			vars.put("window_handles", driver.getWindowHandles());
			driver.findElement(By.id("btnSearch")).click();

		}

		public String waitForWindow1(int timeout) {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Set<String> whNow = driver.getWindowHandles();
			@SuppressWarnings("unchecked")
			Set<String> whThen = (Set<String>) vars.get("window_handles");
			if (whNow.size() > whThen.size()) {
				whNow.removeAll(whThen);
			}
			return whNow.iterator().next();
		}

		   // Credit Control Dealer validation
			@Test(priority = 1, enabled = true, dataProvider = "getDetails")
			public void CreditControlValidation(String search) throws InterruptedException, ClassNotFoundException
			{
			vars.put("win4056", waitForWindow1(2000));
			driver.switchTo().window(vars.get("win4056").toString());
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(5) font")).click();
			WebElement dropdown = driver.findElement(By.id("serviceSelect"));
			dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
			driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys("search");
			vars.put("window_handles", driver.getWindowHandles());
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("td > button")).click();
			vars.put("win6614", waitForWindow1(2000));
			driver.switchTo().window(vars.get("win6614").toString());
			Thread.sleep(2000);
			driver.close();
			driver.switchTo().window(vars.get("win4056").toString());
			{
				WebElement element = driver.findElement(By.cssSelector(".navbar-nav > li:nth-child(3) > a"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			{
				WebElement element = driver.findElement(By.tagName("body"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element, 0, 0).perform();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='action1_accept']")).click();
			driver.findElement(By.cssSelector("#tab1 #modalBtn > .glyphicon")).click();

			// Check the db status.
			// Class.forName("oracle.jdbc.OracleDriver");
			try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs")) {
				// Creating statement object
				Statement statement = connection.createStatement();
				// Executing the SQL query
				String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN='9234567898'";
				ResultSet resultSet = statement.executeQuery(sqlQuery);
				System.out.println(resultSet);
				// Check if any results exist
				if (resultSet.next()) {
					int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
					System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
				} else {
					System.out.println("No data found for STAKE_MSISDN = 9234567823");
				}

				// Close resources resultSet.close(); statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// AML Dealer Validation Test Method
	    @Test(priority = 1, enabled = true, dataProvider = "getDetails")
		public void AMLValidation(String search) throws InterruptedException {

			// Store the new window handle after waiting for a new window to appear
			vars.put("win4056", waitForWindow1(2000));

			// Switch to the newly opened window
			driver.switchTo().window(vars.get("win4056").toString());
			Thread.sleep(2000);

			// Click on the AMLValidation link
			driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(6) font")).click();

			// Click on the service selection dropdown
			driver.findElement(By.id("serviceSelect")).click();
			Thread.sleep(2000);
			{
				// Locate the dropdown and select the option with text 'Dealer Creation'
				WebElement dropdown = driver.findElement(By.id("serviceSelect"));
				dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
			}

			// Click on the search field inside the table filter section
			driver.findElement(By.cssSelector("#myTable1_filter .form-control")).click();

			// Enter the search text 'asas' into the search field
			driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys("search");

			// Store the current set of window handles before opening a new window
			vars.put("window_handles", driver.getWindowHandles());

			// Click on view button(possibly to open a new window)
			driver.findElement(By.cssSelector("td > button")).click();

			// Store the new window handle after waiting for a new window to appear
			vars.put("win7388", waitForWindow1(2000));

			// Switch to the newly opened window
			driver.switchTo().window(vars.get("win7388").toString());

			// Store the main window handle before opening a new one String mainWindow =
			driver.getWindowHandle();

			// Click on a link with the text 'Click Here' to download the document
			driver.findElement(By.linkText("Click Here")).click();
			Thread.sleep(2000);

			// Close the newly opened window driver.close();

			// Switch back to the original window
			driver.switchTo().window(vars.get("win4056").toString());

			{
				// Hover over the third menu item in the navbar
				WebElement element = driver.findElement(By.cssSelector(".navbar-nav > li:nth-child(3) > a"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}

			{
				// Move the mouse to the top-left corner of the page (possibly resetpin focus)
				WebElement element = driver.findElement(By.tagName("body"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element, 0, 0).perform();
			}

			// Enter '9876' into the reseller ID field
			driver.findElement(By.id("reseller_id_7")).sendKeys("9876");

			// Click on the third button inside a section (possibly submitting a form)
			driver.findElement(By.cssSelector("button:nth-child(3)")).click();

			// Click on the 'Accept' button for action confirmation
			driver.findElement(By.id("action7_accept")).click();
			// Click on submit button
			driver.findElement(By.id("modalBtn")).click();

			// Check the db status.
			// Class.forName("oracle.jdbc.OracleDriver");
			try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs"))
			{
				// Creating statement object
				Statement statement = connection.createStatement();
				// Executing the SQL query
				String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN='?'";
				ResultSet resultSet = statement.executeQuery(sqlQuery);
				System.out.println(resultSet);
				// Check if any results exist
				if (resultSet.next()) {
					int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
					System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
				} else {
					System.out.println("No data found for STAKE_MSISDN = 9234567823");
				}

				// Close resources resultSet.close(); statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}



	    	//Final Validation
	       @Test(priority = 1, enabled = true, dataProvider = "getDetails")
			public void FinalValidation(String search) throws InterruptedException {
			vars.put("win1479", waitForWindow1(2000));
			driver.switchTo().window(vars.get("win1479").toString());
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(7) font")).click();
			driver.findElement(By.id("serviceSelect")).click();
			{
				WebElement dropdown = driver.findElement(By.id("serviceSelect"));
				dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
			}
			driver.findElement(By.cssSelector("#myTable1_filter .form-control")).click();
			driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys("search");
			vars.put("window_handles", driver.getWindowHandles());
			driver.findElement(By.cssSelector("td > button")).click();
			vars.put("win784", waitForWindow1(2000));
			driver.switchTo().window(vars.get("win784").toString());
			driver.findElement(By.cssSelector(".col-sm-4:nth-child(3) .btn")).click();
			driver.close();
			driver.switchTo().window(vars.get("win1479").toString());
			{
				WebElement element = driver.findElement(By.cssSelector(".nav > li:nth-child(4) > a"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
			}
			{
				WebElement element = driver.findElement(By.tagName("body"));
				Actions builder = new Actions(driver);
				builder.moveToElement(element, 0, 0).perform();
			}
			driver.findElement(By.id("action3_accept")).click();
			driver.findElement(By.id("modalBtn")).click();

			// Check the db status.
			// Class.forName("oracle.jdbc.OracleDriver");
			try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs")) 
			{
				// Creating statement object
				Statement statement = connection.createStatement();
				// Executing the SQL query
				String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN='9234567898'";
				ResultSet resultSet = statement.executeQuery(sqlQuery);
				System.out.println(resultSet);
				// Check if any results exist
				if (resultSet.next()) {
					int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
					System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
				} else {
					System.out.println("No data found for STAKE_MSISDN = 9234567823");
				}

				// Close resources resultSet.close(); statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


