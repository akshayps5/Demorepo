package mfslyca;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ETEdealercreation extends Dataprovider4LycaMFS {

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

	// Dealer creation
	@Test(priority = 1, enabled = true, dataProvider = "getDetails")
	public void DealerCreation(String msisdn, String Subsname, String emailId, String contactnum, String iccid,
			String Servname, String imsi) throws InterruptedException, AWTException {
		vars.put("win5750", waitForWindow1(2000));
		vars.put("root", driver.getWindowHandle());
		driver.switchTo().window(vars.get("win5750").toString());
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".row:nth-child(4) > .col-md-4:nth-child(2) li > a > font")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#msisdnTypeId")).sendKeys(msisdn);
		driver.findElement(By.id("btnSearch")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("dealerTypes")).click();
		Thread.sleep(4000);

		WebElement dropdown = driver.findElement(By.name("dealerTypes"));
		Thread.sleep(2000);
		dropdown.findElement(By.xpath("//option[. = 'Branch Office']")).click();
		Thread.sleep(10000);

		driver.findElement(By.id("userCheck")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("dlrPvlgOpt")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, 300);");
		driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div:nth-child(3) > .panel .panel-title")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("custTypeId1")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("genderId2")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,379.3333435058594)");
		Thread.sleep(2000);
		driver.findElement(By.name("subsName")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("subsName")).sendKeys(Subsname);
		Thread.sleep(2000);
		driver.findElement(By.name("emailId")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("emailId")).sendKeys(emailId);
		js.executeScript("window.scrollBy(0, 300);");
		Thread.sleep(6000);

		// document upload
		// Wait for the element to be available
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocId")));
		// Use JavaScript to remove style restrictions (if any)
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].style.display='block';", fileInput);
		WebElement uploadInput1 = driver.findElement(By.id("upldDocId"));
		uploadInput1.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");

		// document upload
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocReg")));
		// Use JavaScript to remove style restrictions (if any)
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].style.display='block';", fileInput);
		WebElement uploadInput2 = driver.findElement(By.id("upldDocReg"));
		uploadInput2.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");

		// document upload
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocvat")));
		// Use JavaScript to remove style restrictions (if any)
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].style.display='block';", fileInput);
		WebElement uploadInput3 = driver.findElement(By.id("upldDocvat"));
		uploadInput3.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");

		driver.findElement(By.xpath("//*[@id='subsDetToggle']/div/div[19]/input")).sendKeys(contactnum);
		Thread.sleep(2000);
		driver.findElement(By.name("IccId")).sendKeys(iccid);
		Thread.sleep(2000);
		// driver.findElement(By.name("reseller")).sendKeys(resellerID);
		// Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, 300);");
		driver.findElement(
				By.cssSelector("#dealerDetBlck > div > div:nth-child(2) > div > div.panel-heading.collapsed > h4"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.name("servName")).sendKeys(Servname);
		Thread.sleep(2000);
		WebElement districtDropdown = driver.findElement(By.name("selDistrict"));
		Select selectDistrict = new Select(districtDropdown);
		selectDistrict.selectByVisibleText("Port Louis");
		Thread.sleep(5000);
		WebElement islandDropdown = driver.findElement(By.xpath("//*[@id='changeIsland']/select"));
		Select selectIsland = new Select(islandDropdown);
		selectIsland.selectByVisibleText("Port Louis");

		WebElement element = driver.findElement(By.name("servAdressCheck"));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).perform();

		driver.findElement(By.name("imsiNum")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("imsiNum")).sendKeys(imsi);

		js.executeScript("window.scrollBy(0, 400);");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rateCardSection\"]/div/div/div[1]/h4/b")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[1]/div/select/option[2]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[2]/div/select/option[2]")).click();
		Thread.sleep(5000);
		// System.out.println("Click on register");
		js.executeScript("window.scrollBy(0, 400);");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"register\"]")).click();
		Thread.sleep(2000);
		System.out.println("successfully dealer registered");

		/*
		 * String secondAlertText = driver.switchTo().alert().getText(); if
		 * (!secondAlertText.equals("add/update successfully")) { throw new
		 * AssertionError("Unexpected alert text: " + secondAlertText); }
		 */
		driver.findElement(By.xpath("//*[@id=\"successModal\"]/div/div/div[2]/div/button")).click();

		// Accept the second alert
		// driver.switchTo().alert().dismiss();
		// driver.findElement(By.xpath("//*[@id=\"successModal\"]/div/div/div[2]/div/button")).click();

		// Check the database status.
		// Class.forName("oracle.jdbc.OracleDriver");
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs",
				"mfs")) {
			// Creating statement object
			Statement statement = connection.createStatement();
			// Executing the SQL query
			String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN= '" + msisdn + "'";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println(resultSet);
			// Check if any results exist
			if (resultSet.next()) {
				int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
				System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
			} else {
				System.out.println("No data found for STAKE_MSISDN = '" + msisdn + "'");
			}

			// Close resources resultSet.close(); statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Create Dealer with Duplicate ICCID
	@Test(priority = 2, enabled = true, dataProvider = "getDetails")
	public void DealerCreationwithdupICCID(String msisdn1, String Subsname1, String emailId1, String contactnum1,
			String iccid1, String Servname1, String imsi1) throws InterruptedException, AWTException {
		vars.put("win5750", waitForWindow1(2000));
		vars.put("root", driver.getWindowHandle());
		driver.switchTo().window(vars.get("win5750").toString());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".row:nth-child(4) > .col-md-4:nth-child(2) li > a > font")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#msisdnTypeId")).sendKeys(msisdn1);
		driver.findElement(By.id("btnSearch")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("dealerTypes")).click();
		Thread.sleep(4000);
		{
			WebElement dropdown = driver.findElement(By.name("dealerTypes"));
			Thread.sleep(2000);
			dropdown.findElement(By.xpath("//option[. = 'Branch Office']")).click();
			Thread.sleep(10000);
		}
		driver.findElement(By.id("userCheck")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("dlrPvlgOpt")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, 300);");
		driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div:nth-child(3) > .panel .panel-title")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("custTypeId1")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("genderId2")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,379.3333435058594)");
		Thread.sleep(2000);
		driver.findElement(By.name("subsName")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("subsName")).sendKeys(Subsname1);
		Thread.sleep(2000);
		driver.findElement(By.name("emailId")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("emailId")).sendKeys(emailId1);
		js.executeScript("window.scrollBy(0, 300);");
		Thread.sleep(6000);
		// document upload
		// Wait for the element to be available
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocId")));
		// Use JavaScript to remove style restrictions (if any)
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].style.display='block';", fileInput);
		WebElement uploadInput1 = driver.findElement(By.id("upldDocId"));
		uploadInput1.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");

		// document upload
		WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput1 = wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocReg")));
		// Use JavaScript to remove style restrictions (if any)
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].style.display='block';", fileInput);
		WebElement uploadInput2 = driver.findElement(By.id("upldDocReg"));
		uploadInput2.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");

		// document upload
		WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput7 = wait7.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocvat")));
		// Use JavaScript to remove style restrictions (if any)
		JavascriptExecutor js8 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].style.display='block';", fileInput);
		WebElement uploadInput3 = driver.findElement(By.id("upldDocvat"));
		uploadInput3.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");

		driver.findElement(By.xpath("//*[@id='subsDetToggle']/div/div[19]/input")).sendKeys(contactnum1);
		Thread.sleep(2000);
		driver.findElement(By.name("IccId")).sendKeys(iccid1);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, 300);");
		driver.findElement(
				By.cssSelector("#dealerDetBlck > div > div:nth-child(2) > div > div.panel-heading.collapsed > h4"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.name("servName")).sendKeys(Servname1);
		Thread.sleep(2000);
		WebElement districtDropdown = driver.findElement(By.name("selDistrict"));
		Select selectDistrict = new Select(districtDropdown);
		selectDistrict.selectByVisibleText("Port Louis");
		Thread.sleep(5000);
		WebElement islandDropdown = driver.findElement(By.xpath("//*[@id='changeIsland']/select"));
		Select selectIsland = new Select(islandDropdown);
		selectIsland.selectByVisibleText("Port Louis");
		{
			WebElement element = driver.findElement(By.name("servAdressCheck"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.name("imsiNum")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("imsiNum")).sendKeys(imsi1);
		js.executeScript("window.scrollBy(0, 400);");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rateCardSection\"]/div/div/div[1]/h4/b")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[1]/div/select/option[2]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[2]/div/select/option[2]")).click();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0, 500);");
		Thread.sleep(2000);
		driver.findElement(By.id("register")).click();
		Thread.sleep(2000);
		System.out.println("ICCID already exist");

		// Check the db status.
		// Class.forName("oracle.jdbc.OracleDriver");
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs",
				"mfs")) {
			// Creating statement object
			Statement statement = connection.createStatement();
			// Executing the SQL query
			String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN='" + msisdn1 + "'";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println(resultSet);
			// Check if any results exist
			if (resultSet.next()) {
				int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
				System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
			} else {
				System.out.println("No data found for STAKE_MSISDN = '" + msisdn1 + "'");
			}

			// Close resources resultSet.close(); statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Create Dealer with duplicate imsi
	@Test(priority = 3, enabled = true, dataProvider = "getDetails")
			public void DealerCreationwithdupimsi(String msisdn3, String Subsname3, String emailId3, String contactnum3, String iccid3, String Servname3, String imsi3) throws InterruptedException, AWTException 
			{
				vars.put("win5750", waitForWindow1(2000));
				vars.put("root", driver.getWindowHandle());
				driver.switchTo().window(vars.get("win5750").toString());
				Thread.sleep(2000);
				driver.findElement(By.cssSelector(".row:nth-child(4) > .col-md-4:nth-child(2) li > a > font")).click();
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("#msisdnTypeId")).sendKeys(msisdn3);
				driver.findElement(By.id("btnSearch")).click();
				Thread.sleep(4000);
				driver.findElement(By.name("dealerTypes")).click();
				Thread.sleep(4000);
				{
					WebElement dropdown = driver.findElement(By.name("dealerTypes"));
					Thread.sleep(2000);
					dropdown.findElement(By.xpath("//option[. = 'Branch Office']")).click();
					Thread.sleep(10000);
				}
				driver.findElement(By.id("userCheck")).click();
				Thread.sleep(4000);
				driver.findElement(By.name("dlrPvlgOpt")).click();
				Thread.sleep(2000);
				js.executeScript("window.scrollBy(0, 300);");
				driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
				Thread.sleep(4000);
				driver.findElement(By.cssSelector("div:nth-child(3) > .panel .panel-title")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("custTypeId1")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("genderId2")).click();
				Thread.sleep(2000);
				js.executeScript("window.scrollTo(0,379.3333435058594)");
				Thread.sleep(2000);
				driver.findElement(By.name("subsName")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("subsName")).sendKeys(Subsname3);
				Thread.sleep(2000);
				driver.findElement(By.name("emailId")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("emailId")).sendKeys(emailId3);
				js.executeScript("window.scrollBy(0, 300);");
				Thread.sleep(6000);
				//document upload
				// Wait for the element to be available
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocId")));
				// Use JavaScript to remove style restrictions (if any)
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].style.display='block';", fileInput);
				WebElement uploadInput1 = driver.findElement(By.id("upldDocId"));
				uploadInput1.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");
				
				//document upload
				WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement fileInput1 = wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocReg")));
				// Use JavaScript to remove style restrictions (if any)
				JavascriptExecutor js2 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].style.display='block';", fileInput);
				WebElement uploadInput2 = driver.findElement(By.id("upldDocReg"));
				uploadInput2.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");
				
				//document upload
				WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement fileInput7 = wait7.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocvat")));
				// Use JavaScript to remove style restrictions (if any)
				JavascriptExecutor js8 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].style.display='block';", fileInput);
				WebElement uploadInput3 = driver.findElement(By.id("upldDocvat"));
				uploadInput3.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");
				 
				driver.findElement(By.xpath("//*[@id='subsDetToggle']/div/div[19]/input")).sendKeys(contactnum3);
				Thread.sleep(2000);
				driver.findElement(By.name("IccId")).sendKeys(iccid3);
				Thread.sleep(2000);
				js.executeScript("window.scrollBy(0, 300);");
				driver.findElement(By.cssSelector("#dealerDetBlck > div > div:nth-child(2) > div > div.panel-heading.collapsed > h4")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("servName")).sendKeys(Servname3);
				Thread.sleep(2000);
				WebElement districtDropdown = driver.findElement(By.name("selDistrict"));
				Select selectDistrict = new Select(districtDropdown);
				selectDistrict.selectByVisibleText("Port Louis");
				Thread.sleep(5000);
				WebElement islandDropdown = driver.findElement(By.xpath("//*[@id='changeIsland']/select"));
				Select selectIsland = new Select(islandDropdown);
				selectIsland.selectByVisibleText("Port Louis");
				{
					WebElement element = driver.findElement(By.name("servAdressCheck"));
					Actions builder = new Actions(driver);
					builder.moveToElement(element).perform();
				}
				driver.findElement(By.name("imsiNum")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("imsiNum")).sendKeys(imsi3);
				js.executeScript("window.scrollBy(0, 400);");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"rateCardSection\"]/div/div/div[1]/h4/b")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[1]/div/select/option[2]")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[2]/div/select/option[2]")).click();
			    Thread.sleep(5000);
				js.executeScript("window.scrollBy(0, 500);");
				Thread.sleep(2000);
				driver.findElement(By.id("register")).click();
				System.out.println("IMSI already exist");
				
			
			// Check the db status.
					// Class.forName("oracle.jdbc.OracleDriver");
						try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs")) {
							// Creating statement object
							Statement statement = connection.createStatement();
							// Executing the SQL query
							String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN='" + msisdn3 + "'";
							ResultSet resultSet = statement.executeQuery(sqlQuery);
							System.out.println(resultSet);
							// Check if any results exist
							if (resultSet.next()) {
								int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
								System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
							} else {
								System.out.println("No data found for STAKE_MSISDN = '" + msisdn3 + "'");
							}

							// Close resources resultSet.close(); statement.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}

			
						@AfterClass
						public void logOut() {
						driver.quit();
						}
			}
}
