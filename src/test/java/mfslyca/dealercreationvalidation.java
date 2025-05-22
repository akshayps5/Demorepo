package mfslyca;

	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.FileWriter;
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

	public class dealercreationvalidation  extends dataproviderfordealercreation {

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
			driver.findElement(By.name("Username")).sendKeys("moulya");
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
		// Global variable to store MSISDN, ICCID, IMSI

		

	public static String uniqueMsisdn; // Static variable	

	// Dealer creation test
	@Test(priority = 1, enabled = true)
	public void DealerCreation() throws InterruptedException, AWTException, IOException
	{
		
		 String Subsname = "lyca";
	     String emailId = "abcd1236@gmail.com";
	     String contactnum = "23456789043567898";
	     String Servname = "Recharge";
	     int count = 1; // Number of test cases
	     vars.put("win5750", waitForWindow1(2000));
			vars.put("root", driver.getWindowHandle());
			driver.switchTo().window(vars.get("win5750").toString());
			Thread.sleep(5000);
			driver.findElement(By.cssSelector(".row:nth-child(4) > .col-md-4:nth-child(2) li > a > font")).click();
			//driver.findElement(By.xpath("//*[contains(text(),'Dealer Creation')]")).click();
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//WebElement dealerCreation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Dealer Creation')]")));
			//dealerCreation.click();
			Thread.sleep(2000);
			//driver.findElement(By.cssSelector("#msisdnTypeId")).sendKeys(msisdn);
			  uniqueMsisdn = "976" +  String.format("%07d", System.currentTimeMillis() % 10000000); // Ensures 7-digit suffix
			    System.out.println("Generated MSISDN: " + uniqueMsisdn);
			    driver.findElement(By.cssSelector("#msisdnTypeId")).sendKeys(uniqueMsisdn);
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
			
			//document upload
			// Wait for the element to be available
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement fileInput = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocId")));
			// Use JavaScript to remove style restrictions (if any)
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].style.display='block';", fileInput);
			WebElement uploadInput1 = driver.findElement(By.id("upldDocId"));
			uploadInput1.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");
			
			//document upload
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement fileInput1 = wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocReg")));
			// Use JavaScript to remove style restrictions (if any)
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].style.display='block';", fileInput);
			WebElement uploadInput2 = driver.findElement(By.id("upldDocReg"));
			uploadInput2.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");
			
			//document upload
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement fileInput2 = wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("upldDocvat")));
			// Use JavaScript to remove style restrictions (if any)
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].style.display='block';", fileInput);
			WebElement uploadInput3 = driver.findElement(By.id("upldDocvat"));
			uploadInput3.sendKeys("C:\\Users\\Moulya\\Downloads\\new.pdf");
			
			driver.findElement(By.xpath("//*[@id='subsDetToggle']/div/div[19]/input")).sendKeys(contactnum);
			Thread.sleep(2000);
			//driver.findElement(By.name("IccId")).sendKeys(iccid);
			String uniqueiccid = "40499" + String.format("%010d", System.currentTimeMillis() % 10000000000L);
			System.out.println("Generated IMSI: " + uniqueiccid);
			    driver.findElement(By.name("IccId")).sendKeys(uniqueiccid);
			Thread.sleep(2000);
			//driver.findElement(By.name("reseller")).sendKeys(resellerID);
			//Thread.sleep(2000);
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
			//driver.findElement(By.name("imsiNum")).sendKeys(imsi);
			String uniqueimsi = "40499" + String.format("%010d", System.currentTimeMillis() % 10000000000L);
			System.out.println("Generated IMSI: " + uniqueimsi);
			 driver.findElement(By.name("imsiNum")).sendKeys(uniqueimsi);
			
			js.executeScript("window.scrollBy(0, 400);");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"rateCardSection\"]/div/div/div[1]/h4/b")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[1]/div/select/option[2]")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"subsDetToggle14\"]/fieldset/div[2]/div/select/option[2]")).click();
		    Thread.sleep(5000);
			//System.out.println("Click on register");
			js.executeScript("window.scrollBy(0, 400);");
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"register\"]")).click();
			Thread.sleep(2000);
			System.out.println("successfully dealer registered");
		    driver.findElement(By.xpath("//*[@id=\"successModal\"]/div/div/div[2]/div/button")).click();
		    
		    // Accept the second alert
		    //driver.switchTo().alert().dismiss();
		  	//driver.findElement(By.xpath("//*[@id=\"successModal\"]/div/div/div[2]/div/button")).click();
		
			
	        // Check the database status.
			// Class.forName("oracle.jdbc.OracleDriver");
				try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs")) 
				{
					// Creating statement object
					Statement statement = connection.createStatement();
					// Executing the SQL query
					String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN= '" + uniqueMsisdn + "'";
					ResultSet resultSet = statement.executeQuery(sqlQuery);
					System.out.println(resultSet);
					// Check if any results exist
					if (resultSet.next()) 
					{
						int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
						System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
					} else {
						System.out.println("No data found for STAKE_MSISDN = '" + uniqueMsisdn + "'");
					}

					// Close resources resultSet.close(); statement.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

						// Credit Control Dealer validation
						@Test(priority = 2, enabled = true, dependsOnMethods = "DealerCreation")
						public void CreditControlValidation() throws InterruptedException, ClassNotFoundException
						{
						vars.put("win4056", waitForWindow1(2000));
						driver.switchTo().window(vars.get("win4056").toString());
					    driver.findElement(By.cssSelector("body > div.row > div > header > nav > div > div.col-xs-10 > div > ul > li:nth-child(1) > a")).click();
						Thread.sleep(2000);
						//driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(5) font")).click();
						//driver.findElement(By.xpath("//*[contains(text(),'Credit Control validation')]")).click();
						driver.findElement(By.cssSelector("body > div.wrapper.row-offcanvas.row-offcanvas-left > aside.right-side > section.content > div > form > div:nth-child(4) > div:nth-child(2) > div > ul > li:nth-child(6) > div > a > font")).click();
						WebElement dropdown = driver.findElement(By.id("serviceSelect"));
						dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
						//public void validateDealer() {
				        //String search = DealerCreation.uniqueMsisdn; // Access static variable
						driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys(uniqueMsisdn);
						if (uniqueMsisdn == null || uniqueMsisdn.trim().isEmpty()) {
						    throw new IllegalStateException("uniqueMsisdn is null or not initialized.");
						}
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
						Thread.sleep(2000);
						//driver.findElement(By.xpath("#myTable1 > tbody > tr:nth-child(1)")).click();
						driver.findElement(By.className("radio-inline")).click();
						//driver.findElement(By.xpath("//*[@id='action1_accept']")).click();
						driver.findElement(By.cssSelector("#tab1 #modalBtn > .glyphicon")).click();

						// Check the db status.
						// Class.forName("oracle.jdbc.OracleDriver");
						try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs")) {
							// Creating statement object
							Statement statement = connection.createStatement();
							// Executing the SQL query
							String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN='" + uniqueMsisdn + "'";
							ResultSet resultSet = statement.executeQuery(sqlQuery);
							System.out.println(resultSet);
							// Check if any results exist
							if (resultSet.next()) {
								int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
								System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
							} else {
								System.out.println("No data found for STAKE_MSISDN = '" + uniqueMsisdn + "'");
							}

							// Close resources resultSet.close(); statement.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					// AML Dealer Validation Test Method
				  @Test(priority = 3, enabled = true, dependsOnMethods = "DealerCreation")
					public void AMLValidation() throws InterruptedException 
				  {
					  //vars.put("win4056", waitForWindow1(2000));
					    vars.put("win4056", driver.getWindowHandle());
						driver.switchTo().window(vars.get("win4056").toString());
						driver.findElement(By.cssSelector("body > div.row > div > header > nav > div > div.col-xs-10 > div > ul > li:nth-child(1) > a")).click();
						Thread.sleep(2000);
						//driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(6) font")).click();
						driver.findElement(By.cssSelector("body > div.wrapper.row-offcanvas.row-offcanvas-left > aside.right-side > section.content > div > form > div:nth-child(4) > div:nth-child(2) > div > ul > li:nth-child(7) > div > a > font")).click();
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
						driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys (uniqueMsisdn);

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
						driver.findElement(By.id("reseller_id_4")).sendKeys("9876");
						//*[@id="reseller_id_9"]

						// Click on the third button inside a section (possibly submitting a form)
						driver.findElement(By.cssSelector("button:nth-child(3)")).click();

						// Click on the 'Accept' button for action confirmation
						//driver.findElement(By.className("radio-inline")).click();
						driver.findElement(By.id("action4_accept")).click();
						// Click on submit button
						driver.findElement(By.id("modalBtn")).click();

						// Check the db status.
						// Class.forName("oracle.jdbc.OracleDriver");
						try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs"))
						{
							// Creating statement object
							Statement statement = connection.createStatement();
							// Executing the SQL query
							String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN='" + uniqueMsisdn + "'";
							ResultSet resultSet = statement.executeQuery(sqlQuery);
							System.out.println(resultSet);
							// Check if any results exist
							if (resultSet.next()) {
								int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
								System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
							} else {
								System.out.println("No data found for STAKE_MSISDN = '" + uniqueMsisdn + "'");
							}

							// Close resources resultSet.close(); statement.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}



				    	//Final Validation
				       @Test(priority = 4, enabled = true, dependsOnMethods = "DealerCreation")
						public void FinalValidation() throws InterruptedException 
				       {
						//vars.put("win1479", waitForWindow1(2000));
						vars.put("win4056", driver.getWindowHandle());
						driver.switchTo().window(vars.get("win1479").toString());
						driver.findElement(By.cssSelector("body > div.row > div > header > nav > div > div.col-xs-10 > div > ul > li:nth-child(1) > a")).click();
						Thread.sleep(2000);
						driver.findElement(By.cssSelector("body > div.wrapper.row-offcanvas.row-offcanvas-left > aside.right-side > section.content > div > form > div:nth-child(4) > div:nth-child(2) > div > ul > li:nth-child(8) > div > a > font")).click();
						driver.findElement(By.id("serviceSelect")).click();
						{
							WebElement dropdown = driver.findElement(By.id("serviceSelect"));
							dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
						}
						driver.findElement(By.cssSelector("#myTable1_filter .form-control")).click();
						driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys(uniqueMsisdn);
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
						driver.findElement(By.id("action1_accept")).click();
						driver.findElement(By.id("modalBtn")).click();

						// Check the db status.
						// Class.forName("oracle.jdbc.OracleDriver");
						try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", "mfs","mfs")) 
						{
							// Creating statement object
							Statement statement = connection.createStatement();
							// Executing the SQL query
							String sqlQuery = "select STAKE_GUI_STATUS from MFS_STAKE_DET where STAKE_MSISDN= '" + uniqueMsisdn + "'";
							ResultSet resultSet = statement.executeQuery(sqlQuery);
							System.out.println(resultSet);
							// Check if any results exist
							if (resultSet.next()) {
								int Stake_gui_stake = resultSet.getInt("STAKE_GUI_STATUS");
								System.out.println("STAKE_GUI_STATUS: " + Stake_gui_stake);
							} else {
								System.out.println("No data found for STAKE_MSISDN = '" + uniqueMsisdn + "'");
							}

							// Close resources resultSet.close(); statement.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}


		}

