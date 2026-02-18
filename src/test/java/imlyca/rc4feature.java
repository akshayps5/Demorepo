package imlyca;



	import org.testng.annotations.Test;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import org.bouncycastle.crypto.modes.PGPCFBBlockCipher;
	import org.bouncycastle.voms.VOMSAttribute.FQAN;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.SearchContext;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import io.netty.handler.timeout.TimeoutException;
	import imlyca.dataprovider;

	import java.security.PublicKey;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Scanner;

	import javax.swing.JOptionPane;
	import java.util.List;
	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;

	public class rc4feature {
		private static WebDriver driver;
		private Map<String, Object> vars;

		public String subscriberId;
		public String subscriberId2;
		public String subscriberId3;
		public String unitno;
		public String msisdn;
		public String unitno_20;

		JavascriptExecutor js;

		@BeforeClass
		public void setUp() {
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// js = (JavascriptExecutor) driver;
			// vars = new HashMap<String, Object>();
			driver.get("http://" + dataprovider.HOST + ":" + dataprovider.PORT + "/tssgui/");
			driver.manage().window().maximize();
			WebElement usernameField = driver.findElement(By.id("username"));
			highlightElement(usernameField);
			usernameField.click();
			usernameField.sendKeys(dataprovider.GUI_USER);
			WebElement passwordField = driver.findElement(By.name("password"));
			highlightElement(passwordField);
			passwordField.sendKeys(dataprovider.GUI_PASSWORD);
			WebElement submit = driver.findElement(By.id("subBtn"));
			highlightElement(submit);
			submit.click();
		}

		// Method to highlight elements
		public static void highlightElement(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String originalStyle = element.getAttribute("style");
			js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid black; background: yellow;');",
					element);
			try {
				Thread.sleep(500); // Highlight for visibility
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
		}

		@Test(priority = 1, enabled = true)
		public void Dbconnect() {
			try (Connection connection = DriverManager.getConnection(
					dataprovider.DB_url + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
					dataprovider.DB_USER, dataprovider.DB_PASSWORD)) {
				// Creating a statement object
				Statement statement = connection.createStatement();

				// Executing the SQL query
				String sqlQuery = "SELECT MSISDN FROM SIM_ACT_DEACT WHERE OPERATION_TYPE=1 LIMIT 1;";
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

		@Test(priority = 2, enabled = true)
		public void DbconnectForOpType2() {
			try (Connection connection = DriverManager.getConnection(
					dataprovider.DB_url + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
					dataprovider.DB_USER, dataprovider.DB_PASSWORD)) {
				Statement statement = connection.createStatement();
				String sqlQuery1 = "SELECT MSISDN FROM SIM_ACT_DEACT WHERE OPERATION_TYPE=2 LIMIT 1;";
				ResultSet resultSet1 = statement.executeQuery(sqlQuery1);

				if (resultSet1.next()) {
					subscriberId2 = resultSet1.getString("MSISDN");
					System.out.println("MSISDN1: " + subscriberId2);
				} else {
					System.out.println("No MSISDN found for OPERATION_TYPE=2");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Test(priority = 3, enabled = true)
		public void DbconnectForOpType3() {
			try (Connection connection = DriverManager.getConnection(
					dataprovider.DB_url + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
					dataprovider.DB_USER, dataprovider.DB_PASSWORD)) {
				Statement statement = connection.createStatement();
				String sqlQuery2 = "SELECT MSISDN FROM SIM_ACT_DEACT WHERE OPERATION_TYPE=3 LIMIT 1;";
				ResultSet resultSet2 = statement.executeQuery(sqlQuery2);

				if (resultSet2.next()) {
					subscriberId3 = resultSet2.getString("MSISDN");
					System.out.println("MSISDN2: " + subscriberId3);
				} else {
					System.out.println("No MSISDN found for OPERATION_TYPE=3");
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

		@DataProvider(name = "MSISDN2")
		public Object[][] MSISDN2() {
			return new Object[][] { { subscriberId2 } };
		}

		@DataProvider(name = "MSISDN3")
		public Object[][] MSISDN3() {
			return new Object[][] { { subscriberId3 } };
		}

		@Test(priority = 4, enabled = false)
		public void sRM() throws InterruptedException {
			// implicit wait
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
			// click on package details
			WebElement pkgdetails = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Package Details')]")));
			highlightElement(pkgdetails);
			pkgdetails.click();
			Thread.sleep(2000);
			// click on get live plan details
			WebElement getliveplan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SIMselBtn']")));
			highlightElement(getliveplan);
			getliveplan.click();
			Thread.sleep(2000);
			// assertion for confirm message
			WebElement ConfirmMsg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msgDisp\"]/div/div")));
			String confirmText = ConfirmMsg.getText().replace("×", "").trim().replaceAll("\n", " ");
			// ANSI escape codes
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";
			// Clean and analyze the confirm text
			String cleanText = confirmText.replace("×", "").trim().replaceAll("\n", " ");

			// Decide color based on message content
			String color = cleanText.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Print: label in default, message in color
			System.out.println("Extracted Message in get live plan details: " + color + cleanText + ANSI_RESET);
		}

		@Test(priority = 5, enabled = false)

		public void simtype() throws InterruptedException {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout

		    // Click on sitemap
		    WebElement sitemap1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a > .fa-sitemap")));
		    highlightElement(sitemap1);
		    sitemap1.click();
		    Thread.sleep(2000);

		    // Click on sim type creation
		    WebElement createsimtype = wait
		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Sim Type Creation')]")));
		    highlightElement(createsimtype);
		    createsimtype.click();

//		    // Search for SIM type
//		    WebElement searchTextField = wait
//		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
//		    highlightElement(searchTextField);
//		    searchTextField.sendKeys(dataprovider.SIM_TYPE);
//		    
//		    // click on delete button to remove the package
//		    WebElement dltbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"simTypeTab\"]/tbody/tr/td[6]/button")));
//		    highlightElement(dltbtn);
//		    dltbtn.click();
//		    
//		    // confirm delete by clicking on yes button
//		    WebElement yesbtn5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
//		    highlightElement(yesbtn5);
//		    yesbtn5.click();
		    
		    // Continue with SIM type creation
		    WebElement simtypeadd = wait.until(ExpectedConditions
		            .elementToBeClickable(By.xpath("//u[contains(.,'SIM Type Addition')]")));
		    highlightElement(simtypeadd);
		    simtypeadd.click();
		    Thread.sleep(2000);

		    WebElement comodityType = wait.until(ExpectedConditions
		            .elementToBeClickable(By.xpath("//div[@id='commoditySelect']/select")));
		    highlightElement(comodityType);
		    comodityType.click();
		    Thread.sleep(2000);

		    Select dropdown = new Select(comodityType);
		    dropdown.selectByVisibleText("SIM");
		    Thread.sleep(2000);

		    WebElement simTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.id("simType")));
		    highlightElement(simTypeField);
		    simTypeField.sendKeys(dataprovider.SIM_TYPE);
		    Thread.sleep(2000);

		    WebElement memoryField = wait.until(ExpectedConditions.elementToBeClickable(By.id("memory")));
		    highlightElement(memoryField);
		    memoryField.sendKeys(dataprovider.MEMORY);
		    Thread.sleep(2000);

		    WebElement networkSupportField = wait.until(ExpectedConditions.elementToBeClickable(By.id("networkSupport")));
		    highlightElement(networkSupportField);
		    networkSupportField.click();
		    networkSupportField.sendKeys(dataprovider.NETWORK);
		    Thread.sleep(2000);

		    WebElement sizeField = wait.until(ExpectedConditions.elementToBeClickable(By.id("size")));
		    highlightElement(sizeField);
		    sizeField.click();
		    sizeField.sendKeys(dataprovider.SIZE);
		    Thread.sleep(2000);

		    WebElement volteSupportField = wait.until(ExpectedConditions.elementToBeClickable(By.id("volteSupport")));
		    highlightElement(volteSupportField);
		    volteSupportField.click();
		    volteSupportField.sendKeys(dataprovider.VOLTE);

		    WebElement profileField = wait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		    highlightElement(profileField);
		    profileField.click();
		    profileField.sendKeys(dataprovider.PROFILE);
		    Thread.sleep(2000);

		    WebElement addbtn = wait
		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='AddSimType']")));
		    highlightElement(addbtn);
		    addbtn.click();
		    Thread.sleep(2000);

		    WebElement yesbtn = wait
		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Yes')]")));
		    highlightElement(yesbtn);
		    yesbtn.click();
		    Thread.sleep(2000);

		    // Assertion for confirm message
		    WebElement ConfirmMsg1 = wait
		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msgDisp\"]/div/div")));
		    highlightElement(ConfirmMsg1);
		    String confirmText1 = ConfirmMsg1.getText();

		    // ANSI escape codes for color
		    final String ANSI_GREEN = "\u001B[32m";
		    final String ANSI_RED = "\u001B[31m";
		    final String ANSI_RESET = "\u001B[0m";

		    String cleanText1 = confirmText1.replace("×", "").trim().replaceAll("\n", " ");
		    String color = cleanText1.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

		    // Final output
		    System.out.println("Extracted Message after SIM type creation: " + color + cleanText1 + ANSI_RESET);
		    
		 // ✅ Assertions based on expected messages
		    if (cleanText1.equalsIgnoreCase("SIM Type Creation Successfull")) {
		        System.out.println("✅ Assertion Passed: SIM Type Creation success");
		        Assert.assertTrue(true, "Sim type creation passed");
		    } else if (cleanText1.contains("Failed to create SIM Type,Name already exists")) {
		        System.out.println("❌ Assertion Failed: Failed to create SIM Type");
		        Assert.fail("Sim type creation failed");
		    } else {
		        System.out.println("❌ Assertion Failed: Unexpected message: " + cleanText1);
		        Assert.fail("Unexpected alert message: " + cleanText1);
		    }
		}

		
		@Test(priority = 6, enabled = false)
		public void mappUpload() throws InterruptedException {

//			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
//			{
//				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
//				Actions builder = new Actions(driver);
//				builder.moveToElement(element).perform();
//			}
//			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement refresh1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(refresh1);
			refresh1.click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//b[contains(.,'Mapped Inventory Upload')]")).click();
				//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				// click on mapped inventory upload link
				WebElement mappedInventory =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[contains(.,'Mapped Inventory Upload')]")));
				highlightElement(mappedInventory);
				mappedInventory.click();
				Thread.sleep(2000);
				// click on comodity dropdown 
				WebElement selectcomodity=  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='commoditySelect']/select")));
				highlightElement(selectcomodity);
				selectcomodity.click();
				Thread.sleep(1000);
				//select the comodity type sim
				Select dropdown = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown.selectByVisibleText("SIM");
				//select the package field
				WebElement pkgField = wait.until(ExpectedConditions.elementToBeClickable(By.id("pkgDet")));
				highlightElement(pkgField);
				pkgField.click();
				WebElement pkgsel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pkgDet\"]/option[2]")));
				pkgsel.click();
				//pkgField.sendKeys("4 GB DATA");
				Thread.sleep(1000);
				// select the im type
				WebElement simField = wait.until(ExpectedConditions.elementToBeClickable(By.id("simTypeDet")));
				highlightElement(simField);
				simField.click();
				//select the sim type
				WebElement simsel= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"simTypeDet\"]/option[3]")));
				highlightElement(simsel);
				simsel.click();
				Thread.sleep(2000);

				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("document.getElementById('cisFile').style.display='block';");
				WebElement fileField = wait.until(ExpectedConditions.elementToBeClickable(By.id("cisFile")));
				highlightElement(fileField);
				// fileField.click();
				// fileField.sendKeys("C:\\Users\\Monika\\Downloads\\MapUpload_2.txt");
				fileField.sendKeys("C:\\Users\\Nagabindu\\Downloads\\MapUpload_2.txt");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@name='AddCIS']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
				Thread.sleep(2000);
				// assertion of mapped inventory upload
//				WebElement confirmMsg3 = driver.findElement(By.xpath("//*[@id=\"msgDisp\"]/div/div"));
//				String confirmText3 = confirmMsg3.getText();
//				System.out.println("Extracted Message: " + confirmText3);
				// Assertion for confirm message
			    WebElement ConfirmMsg2 = wait
			            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msgDisp\"]/div/div")));
			    highlightElement(ConfirmMsg2);
			    String confirmText2 = ConfirmMsg2.getText();

			    // ANSI escape codes for color
			    final String ANSI_GREEN = "\u001B[32m";
			    final String ANSI_RED = "\u001B[31m";
			    final String ANSI_RESET = "\u001B[0m";

			    String cleanText2 = confirmText2.replace("×", "").trim().replaceAll("\n", " ");
			    String color = cleanText2.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			    // Final output
			    System.out.println("Extracted Message after mapping inventory: " + color + cleanText2 + ANSI_RESET);
			    
			 // ✅ Assertions based on expected messages
			    if (cleanText2.equalsIgnoreCase("Inventory Upload Successful. Qty:1")) {
			        System.out.println("✅ Assertion Passed: Mapped inventory Upload was successful.");
			        Assert.assertTrue(true, "Mapped inventory upload passed");
			    } else if (cleanText2.contains("Inventory Upload Failed. Click Here to download Error File.")) {
			        System.out.println("❌ Assertion Failed: Mapped inventory Upload failed.");
			        Assert.fail("Mapped inventory upload failed");
			    } else {
			        System.out.println("❌ Assertion Failed: Unexpected message: " + cleanText2);
			        Assert.fail("Unexpected alert message: " + cleanText2);
			    }
		        
			}

		@Test(priority = 7, enabled = false)
		public void unmap() throws InterruptedException {
//			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
//			{
//				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
//				Actions builder = new Actions(driver);
//				builder.moveToElement(element).perform();
//			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh6 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh6);
			Refresh6.click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//b[contains(.,'Unmapped Inventory Upload')]")).click();
				Thread.sleep(2000);
				// click on unmapped inventory upload
				WebElement Unmappclk= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[contains(.,'Unmapped Inventory Upload')]")));
				highlightElement(Unmappclk);
				Unmappclk.click();
				Thread.sleep(2000);
				//click on comodity type
				WebElement comtyp1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='commoditySelect']/select")));
				highlightElement(comtyp1);
				comtyp1.click();
				Thread.sleep(1000);
				//select the comodity type sim
				Select dropdown = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
				dropdown.selectByVisibleText("SIM");
				// click on simtype
				WebElement simField = wait.until(ExpectedConditions.elementToBeClickable(By.id("simTypeDet")));
				highlightElement(simField);
				simField.click();
				//select the sim type 
				WebElement simtype1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"simTypeDet\"]/option[3]")));
				highlightElement(simtype1);
				simtype1.click();
				//simField.sendKeys("Syscom 3G");
				Thread.sleep(1000);
				//click on package dropdown
				WebElement pkgField = wait.until(ExpectedConditions.elementToBeClickable(By.id("pkgDet")));
				highlightElement(pkgField);
				pkgField.click();
				//select the package from the fropdown
				WebElement pkgsel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pkgDet\"]/option[1]")));
				highlightElement(pkgsel);
				pkgField.click();
				Thread.sleep(1000);

				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("document.getElementById('cisFile').style.display='block';");
				WebElement fileField = driver.findElement(By.id("cisFile"));
				highlightElement(fileField);
				// fileField.click();
				fileField.sendKeys("C:\\Users\\Nagabindu\\Downloads\\MapUpload_1.txt");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@name='AddCIS']")).click();
				Thread.sleep(2000);
				WebElement yesbtn = driver.findElement(By.xpath("//button[contains(.,'Yes')]"));
				highlightElement(yesbtn);
				yesbtn.click();
				Thread.sleep(2000);
				WebElement ConfirmMsg3 = wait
			            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msgDisp\"]/div/div")));
			    highlightElement(ConfirmMsg3);
			    String confirmText3 = ConfirmMsg3.getText();

			    // ANSI escape codes for color
			    final String ANSI_GREEN = "\u001B[32m";
			    final String ANSI_RED = "\u001B[31m";
			    final String ANSI_RESET = "\u001B[0m";

			    String cleanText3 = confirmText3.replace("×", "").trim().replaceAll("\n", " ");
			    String color = cleanText3.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			    // Final output
			    System.out.println("Extracted Message after unmap inventory: " + color + cleanText3 + ANSI_RESET);
			    
			 // ✅ Assertions based on expected messages
			    if (cleanText3.equalsIgnoreCase("Unmap Inventory Upload Successful. Qty:1")) {
			        System.out.println("✅ Assertion Passed: unmapped inventory Upload was successful.");
			        Assert.assertTrue(true, "Unmapped inventory upload passed");
			    } else if (cleanText3.contains("Unmap Inventory Upload Failed. Click Here to download Error File.")) {
			        System.out.println("❌ Assertion Failed: unmapped inventory Upload failed.");
			        Assert.fail("Unmapped inventory upload failed");
			    } else {
			        System.out.println("❌ Assertion Failed: Unexpected message: " + cleanText3);
			        Assert.fail("Unexpected alert message: " + cleanText3);
			    }
		        
		}

		@Test(priority = 8, enabled = false)
		public void freeMsisdn() throws InterruptedException {
//			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
//			{
//				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
//				Actions builder = new Actions(driver);
//				builder.moveToElement(element).perform();
//			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh6 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh6);
			Refresh6.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//b[contains(.,'Free Msisdn Upload')]")).click();
				//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				//Thread.sleep(2000);
				//click on msisdn upload
				WebElement msisdnupload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[contains(.,'Msisdn Upload')]")));
				highlightElement(msisdnupload);
				msisdnupload.click();
				Thread.sleep(1000);
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("document.getElementById('msisdnFile').style.display='block';");
				WebElement fileField = driver.findElement(By.id("msisdnFile"));
				highlightElement(fileField);
				// fileField.click();
				fileField.sendKeys("C:\\Users\\Nagabindu\\Downloads\\MapUpload_3.txt");
				Thread.sleep(1000);
				// click on add msisdn button
				WebElement addmsisdnbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='AddMsisdn']")));
				highlightElement(addmsisdnbtn);
				addmsisdnbtn.click();
				Thread.sleep(2000);
				WebElement yesbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Yes')]")));
				highlightElement(yesbtn);
				yesbtn.click();
				Thread.sleep(2000);
				
				WebElement ConfirmMsg4 = wait
			            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msgDisp\"]/div/div")));
			    highlightElement(ConfirmMsg4);
			    String confirmText3 = ConfirmMsg4.getText();

			    // ANSI escape codes for color
			    final String ANSI_GREEN = "\u001B[32m";
			    final String ANSI_RED = "\u001B[31m";
			    final String ANSI_RESET = "\u001B[0m";

			    String cleanText4 = confirmText3.replace("×", "").trim().replaceAll("\n", " ");
			    String color = cleanText4.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			    // Final output
			    System.out.println("Extracted Message after free msisdn upload: " + color + cleanText4 + ANSI_RESET);
			    
			 // ✅ Assertions based on expected messages
			    if (cleanText4.equalsIgnoreCase("Free MSISDN Inventory Upload Successful. Qty:1")) {
			        System.out.println("✅ Assertion Passed: Free MSISDN Inventory Upload Successful");
			        Assert.assertTrue(true, "Free MSISDN Inventory Upload passed");
			    } else if (cleanText4.contains("Free MSISDN Inventory Upload Failed. Click Here to download Error File.")) {
			        System.out.println("❌ Assertion Failed: Free MSISDN Inventory Upload Failed");
			        Assert.fail("Free MSISDN Inventory Upload Failed");
			    } else {
			        System.out.println("❌ Assertion Failed: Unexpected message: " + cleanText4);
			        Assert.fail("Unexpected alert message: " + cleanText4);
			    }
		}

		@Test(priority = 10, enabled = false)
		public void freeMsisdn1() throws InterruptedException {
//			driver.findElement(By.cssSelector("a > .fa-sitemap")).click();
//			{
//				WebElement element = driver.findElement(By.cssSelector("a > .fa-sitemap"));
//				Actions builder = new Actions(driver);
//				builder.moveToElement(element).perform();
//			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh6 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh6);
			Refresh6.click();
			Thread.sleep(2000);
			//click on free msisdn generation
			driver.findElement(By.xpath("//b[contains(.,'Free Msisdn Upload')]")).click();
				//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
				Thread.sleep(2000);
				//click on free msisdn generation
				WebElement Freemsisdngen= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[contains(.,'Generate Free MSISDN')]")));
				highlightElement(Freemsisdngen);
				Freemsisdngen.click();
				Thread.sleep(1000);
				// driver.findElement(By.xpath("//div[@id='msisdnGenerate']/div[2]/span[2]/input")).click();
				// pass the count of msisdn generation
				WebElement pkgField = driver.findElement(By.id("msisdnCnt"));
				highlightElement(pkgField);
				pkgField.click();
				pkgField.sendKeys("1");
				Thread.sleep(1000);
				// click on gen button
				WebElement Genbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Generate')]")));
				highlightElement(Genbtn);
				Genbtn.click();
				Thread.sleep(2000);
				// click on yes button
				WebElement Yesbtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Yes')]")));
				highlightElement(Yesbtn1);
				Yesbtn1.click();
				Thread.sleep(2000);

		}
		
		@Test(priority = 11, enabled = false)
		public void Dbconnect4() {
			try (Connection connection = DriverManager.getConnection(
					dataprovider.DB_url + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
					dataprovider.DB_USER, dataprovider.DB_PASSWORD)) {
				// Creating a statement object
				Statement statement = connection.createStatement();

				// Executing the SQL query
				String sqlQuery4 = "SELECT RIGHT(UNIT_NO, 5) AS LAST_5_UDIGITS FROM UNMAP_SIM_INVENTORY WHERE STATUS = 0 LIMIT 1;";
				ResultSet resultSet4 = statement.executeQuery(sqlQuery4);

				// Processing the query results
				if (resultSet4.next()) {
					// Retrieve MSISDN value from the result set as String
					unitno = resultSet4.getString("LAST_5_UDIGITS"); // Assuming MSISDN is a phone number (String)
					// Print the value for verification
					System.out.println("UNITNO: " + unitno);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Test(priority = 12, enabled = false)
		public void Dbconnect5() {
			try (Connection connection = DriverManager.getConnection(
					dataprovider.DB_url + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true",
					dataprovider.DB_USER, dataprovider.DB_PASSWORD)) {
				// Creating a statement object
				Statement statement = connection.createStatement();

				// Executing the SQL query
				String sqlQuery5 = "SELECT RIGHT(MSISDN, 5) AS LAST_5_MDIGITS FROM FREE_MSISDN_INVENTORY WHERE STATUS = 0 LIMIT 1;";
				ResultSet resultSet5 = statement.executeQuery(sqlQuery5);

				// Processing the query results
				if (resultSet5.next()) {
					// Retrieve MSISDN value from the result set as String
					msisdn = resultSet5.getString("LAST_5_MDIGITS"); // Assuming MSISDN is a phone number (String)
					// Print the value for verification
					System.out.println("MSISDN4: " + msisdn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		@Test(priority = 13, enabled = false)
		public void msisdnMapping() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh6 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh6);
			Refresh6.click();
			
			Thread.sleep(2000);
			//click on mapping msisdn link
			WebElement mappingmsisdn= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Mapping MSISDN')]")));
			highlightElement(mappingmsisdn);
			mappingmsisdn.click();
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
			Thread.sleep(2000);
			// click on sim no/ msisdn dropdown
			driver.findElement(By.xpath("//u[contains(.,'Mapping Msisdn to Unmap SIM Inventory')]")).click();
			Thread.sleep(1000);
			//select the sim no/msisdn from dropdown
			driver.findElement(By.xpath("//select[@id='MappingType']")).click();
			Thread.sleep(1000);
			Select dropdown1 = new Select(driver.findElement(By.id("MappingType")));
			dropdown1.selectByVisibleText("SIM No & MSISDN");
			Thread.sleep(2000);
			// click on last digit of sim number text field
			WebElement SimNo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='lastDigSimNo']")));
			highlightElement(SimNo);
			SimNo.sendKeys(unitno);
			Thread.sleep(2000);
			// click on search button
			WebElement search1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@id='searchIconFilter']")));
			highlightElement(search1);
			search1.click();
			Thread.sleep(5000);
			// click sim number dropdown
			WebElement unitnosel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"simNoDiv\"]/span[2]/div/button")));
			highlightElement(unitnosel);
			unitnosel.click();
			Thread.sleep(2000);
			// select the sim number
			WebElement unitno1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"simNoDiv\"]/span[2]/div/ul/li[3]/a/label/input")));
			highlightElement(unitno1);
			unitno1.click();
			Thread.sleep(2000);
			// click on msisdn last digit text field
			WebElement searchmsisdn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='lastDigMsisdn']")));
			highlightElement(searchmsisdn2);
			searchmsisdn2.sendKeys(msisdn);
			Thread.sleep(2000);
			// click on search button
			WebElement search2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchSpanFilter1\"]")));
			highlightElement(search2);
			search2.click();
			Thread.sleep(5000);
			// click on msisdn last digit dropdown
			WebElement msisdnsel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdnDiv\"]/span[2]/div/button")));
			highlightElement(msisdnsel);
			msisdnsel.click();
			Thread.sleep(2000);
			// select the msisdn from dropdown
			WebElement msisdn6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdnDiv\"]/span[2]/div/ul/li[3]/a/label")));
			highlightElement(msisdn6);
			msisdn6.click();
			Thread.sleep(2000);
			// click on package dropdown
			WebElement pkg4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='pkg']")));
			highlightElement(pkg4);
			pkg4.click();
			Thread.sleep(2000);
			// select the package
			WebElement pkgsel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pkg\"]/option[3]")));
			highlightElement(pkgsel);
			pkgsel.click();
			Thread.sleep(2000);
			// click on map button to map the sim with msisdn
			WebElement mapbtn= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Map']")));
			highlightElement(mapbtn);
			mapbtn.click();
			Thread.sleep(2000);
			// click on yes button to confirm mapping
			WebElement yesbtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[7]/div/button")));
			highlightElement(yesbtn1);
			yesbtn1.click();
			Thread.sleep(5000);
			// assert result the after mapping
			WebElement ConfirmMsg5 = wait
		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msgDisp\"]/div/div")));
		    highlightElement(ConfirmMsg5);
		    String confirmText5 = ConfirmMsg5.getText();

		    // ANSI escape codes for color
		    final String ANSI_GREEN = "\u001B[32m";
		    final String ANSI_RED = "\u001B[31m";
		    final String ANSI_RESET = "\u001B[0m";

		    String cleanText5 = confirmText5.replace("×", "").trim().replaceAll("\n", " ");
		    String color = cleanText5.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

		    // Final output
		    System.out.println("Extracted Message after mapping msisdn: " + color + cleanText5 + ANSI_RESET);
		    
		 // ✅ Assertions based on expected messages
		    if (cleanText5.equalsIgnoreCase("MSISDN Mapping Successful")) {
		        System.out.println("✅ Assertion Passed: MSISDN Mapping Successful");
		        Assert.assertTrue(true, "MSISDN Mapping passed");
		    } else if (cleanText5.contains("Failed to map MSISDN")) {
		        System.out.println("❌ Assertion Failed: map msisdn Failed");
		        Assert.fail("Failed to map MSISDN");
		    } else {
		        System.out.println("❌ Assertion Failed: Unexpected message: " + cleanText5);
		        Assert.fail("Unexpected alert message: " + cleanText5);
		    }
		}
		

		@Test(priority = 14, enabled = false)
		public void pkgLabel() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh7 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh7);
			Refresh7.click();
			Thread.sleep(2000);
			// click on package labeling link
			WebElement pkglblg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Package Labeling')]")));
			highlightElement(pkglblg);
			pkglblg.click();
			Thread.sleep(2000);
			// click on comodity type dropdown
			WebElement comtype = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='commoditySelect']/select")));
			highlightElement(comtype);
			comtype.click();
			Thread.sleep(2000);
		    // select the comodity type sim from the dropdown
			Select dropdown = new Select(driver.findElement(By.xpath("//div[@id='commoditySelect']/select")));
			dropdown.selectByVisibleText("SIM");
			//select the simtype from simtype dropdown
			WebElement simField = wait.until(ExpectedConditions.elementToBeClickable(By.id("simType")));
			highlightElement(simField);
			simField.click();
			simField.sendKeys("TestSim1");
			Thread.sleep(2000);
			// click on package dropdown and select the package
			WebElement pkgField = wait.until(ExpectedConditions.elementToBeClickable(By.id("pkg")));
			highlightElement(pkgField);
			pkgField.click();
			pkgField.sendKeys("UK Plan Mega Plus");
			Thread.sleep(2000);
			// enter the quantity of package need to label
			WebElement QuantityField = wait.until(ExpectedConditions.elementToBeClickable(By.id("Quantity")));
			highlightElement(QuantityField);
			//QuantityField.click();
			QuantityField.sendKeys("1");
			Thread.sleep(2000);
			// scroll up the page to select the package
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(1179,570)"); // Scrolls down by 500 pixels
			// click on get sim details button
			WebElement getsimdet = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Get SIM Details')]")));
			highlightElement(getsimdet);
			getsimdet.click();
			Thread.sleep(2000);
			// scroll up the page to select the package
			JavascriptExecutor js11 = (JavascriptExecutor) driver;
			js11.executeScript("window.scrollBy(2000,570)");
			// click on sort based on request date
			WebElement sortreqdate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"CommPkgingDetTab\"]/thead/tr[1]/td[1]")));
			highlightElement(sortreqdate);
			sortreqdate.click();
			// select the package need to label
			WebElement selpkg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[1]")));
			highlightElement(selpkg);
			selpkg.click();
			Thread.sleep(2000);
			// click on generate button
			WebElement genbtn1 =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Generate')]")));
			highlightElement(genbtn1);
			genbtn1.click();
			Thread.sleep(2000);
			//click on yes button
			WebElement yesbtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Yes')]")));
			highlightElement(yesbtn1);
			yesbtn1.click();
			Thread.sleep(5000);
			// scroll up to see the packages need to label
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(2000,500)"); // Scrolls down by 500 pixels
			// scroll down the page to select the package
			JavascriptExecutor js12 = (JavascriptExecutor) driver;
			js12.executeScript("window.scrollTo(0, 0);");
			// assert result the after mapping
			WebElement ConfirmMsg6 = wait
				            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addMsg\"]/div[2]")));
			highlightElement(ConfirmMsg6);
			String confirmText5 = ConfirmMsg6.getText();

			//ANSI escape codes for color
		    final String ANSI_GREEN = "\u001B[32m";
		    final String ANSI_RED = "\u001B[31m";
		    final String ANSI_RESET = "\u001B[0m";

		    String cleanText5 = confirmText5.replace("×", "").trim().replaceAll("\n", " ");
		    String color = cleanText5.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

		    // Final output
			System.out.println("Extracted Message after package labeling: " + color + cleanText5 + ANSI_RESET);
				    
//			// ✅ Assertions based on expected messages
//			if (cleanText5.equalsIgnoreCase("Labeling successful for BatchId")) {
//			System.out.println("✅ Assertion Passed: labeling successful");
//			Assert.assertTrue(true, "package labeling passed");
//			} else if (cleanText5.contains("Failed to package labeling")) {
//			System.out.println("❌ Assertion Failed: package labeling Failed");
//			Assert.fail("Failed to package label");
//			} else {
//		    System.out.println("❌ Assertion Failed: Unexpected message: " + cleanText5);
//			Assert.fail("Unexpected alert message: " + cleanText5);
//			}
		}

		@Test(priority = 15, enabled = false)
		public void approval() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbar-collapse\"]/ul[2]/li[5]/a")));
			highlightElement(logout);
			logout.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout);
			signout.click();
			Thread.sleep(2000);
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			driver.get("http://" + dataprovider.HOST + ":" + dataprovider.PORT + "/tssgui/");
//			driver.manage().window().maximize();
			WebElement usernameField1 = driver.findElement(By.id("username"));
			highlightElement(usernameField1);
			usernameField1.click();
			usernameField1.sendKeys(dataprovider.APPROVAL_USER);
			WebElement passwordField1 = driver.findElement(By.name("password"));
			highlightElement(passwordField1);
			passwordField1.sendKeys(dataprovider.APPROVAL_PWD);
			WebElement submit1 = driver.findElement(By.id("subBtn"));
			highlightElement(submit1);
			submit1.click();
			Thread.sleep(2000);
			WebElement approvalLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Approval')]")));
			highlightElement(approvalLink);
			approvalLink.click();
			Thread.sleep(2000);
			// click on request date sort icon
			WebElement sort15 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='simTypeTab']/thead/tr/th[3])")));
			highlightElement(sort15);
			sort15.click();
			Thread.sleep(2000);
			// click on sort again
			WebElement sort15_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='simTypeTab']/thead/tr/th[3])")));
			highlightElement(sort15_1);
			sort15_1.click();
			Thread.sleep(2000);
			//click on approve icon
			WebElement approve1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fas fa-user-check']")));
			highlightElement(approve1);
			approve1.click();
			Thread.sleep(2000);
			// Enter the account password
			WebElement acctpwd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='verAcntPwd']")));
			highlightElement(acctpwd);
			acctpwd.sendKeys(dataprovider.APPROVAL_PWD);
			Thread.sleep(2000);
			// Enter the comment while approving
			//WebElement cmt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='comment_259']")));
			WebElement cmt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder, 'Enter Your Comment')]")));
			highlightElement(cmt);
			cmt.sendKeys("Package approval done");
			Thread.sleep(10000);
			// click on approve button
			WebElement approveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Approve']")));
			// Optional: highlight it
			highlightElement(approveBtn);
			approveBtn.click();
//			// Perform double-click
//			Actions actions = new Actions(driver);
//			actions.doubleClick(approveBtn).perform();

			Thread.sleep(5000);
			// click on yes button to confirm
			WebElement yesbtn15 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']")));
			highlightElement(yesbtn15);
			yesbtn15.click();
			Thread.sleep(2000);
			// fetch the confirmation message
			WebElement ConfirmMsg6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/form/div/section/div/div[1]/div[1]/div/div")));
			highlightElement(ConfirmMsg6);
			String confirmText5 = ConfirmMsg6.getText();

			//ANSI escape codes for color
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";

			String cleanText5 = confirmText5.replace("×", "").trim().replaceAll("\n", " ");
			String color = cleanText5.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Final output
			System.out.println("Extracted Message after package labeling approval: " + color + cleanText5 + ANSI_RESET);
			
		}


		@Test(priority = 16, enabled = false)
		public void pkgConfirm() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-toggle']")));
			highlightElement(logout1);
			logout1.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout1);
			signout1.click();
			Thread.sleep(2000);
			WebElement usernameField2 = driver.findElement(By.id("username"));
			highlightElement(usernameField2);
			usernameField2.click();
			usernameField2.sendKeys(dataprovider.GUI_USER);
			WebElement passwordField2 = driver.findElement(By.name("password"));
			highlightElement(passwordField2);
			passwordField2.sendKeys(dataprovider.GUI_PASSWORD);
			WebElement submit2 = driver.findElement(By.id("subBtn"));
			highlightElement(submit2);
			submit2.click();
			Thread.sleep(2000);
			// click on package confirmation
			WebElement pkgcnfm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Package Confirmation')]")));
			highlightElement(pkgcnfm);
			pkgcnfm.click();
			Thread.sleep(2000);
			// sort the request based on request date
			WebElement sort16= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"stockDet\"]/thead/tr/th[2]")));
			highlightElement(sort16);
			sort16.click();
			Thread.sleep(2000);
			WebElement sort16_1= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"stockDet\"]/thead/tr/th[2]")));
			highlightElement(sort16_1);
			sort16_1.click();
			Thread.sleep(2000);
			// select the first check box 
			WebElement chkbx1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='Checkbox'])[1]")));
			highlightElement(chkbx1);
			chkbx1.click();
			Thread.sleep(2000);
			// scroll down to click on confirm button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels
			Thread.sleep(2000);
			// click on confirm button
			WebElement confirm1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"SIMselBtn\"]")));
			highlightElement(confirm1);
			confirm1.click();
			Thread.sleep(2000);
			// click on yes button
			WebElement yesbtn16 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Yes')]")));
			highlightElement(yesbtn16);
			yesbtn16.click();
			Thread.sleep(2000);
			// scroll down the page to select the package
			JavascriptExecutor js15 = (JavascriptExecutor) driver;
			js15.executeScript("window.scrollTo(0, 0);");
			// assert result the after mapping
			WebElement ConfirmMsg7 = wait
						            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"labelDiv\"]/div/div[1]/div/div")));
			highlightElement(ConfirmMsg7);
			String confirmText7 = ConfirmMsg7.getText();

			//ANSI escape codes for color
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";
			String cleanText7 = confirmText7.replace("×", "").trim().replaceAll("\n", " ");
			String color = cleanText7.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Final output
			System.out.println("Extracted Message after package confirmation: " + color + cleanText7 + ANSI_RESET);
		}

		@Test(priority = 17, enabled = false)
		public void bundling() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh8 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh8);
			Refresh8.click();
			Thread.sleep(2000);
			// click on bundling
			WebElement bundling = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='Bundling']")));
			highlightElement(bundling);
			bundling.click();
			Thread.sleep(2000);
			// will sort the data w.r.t request date
			WebElement sort17= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"stockDet2\"]/thead/tr/th[2]")));
			highlightElement(sort17);
			sort17.click();
			WebElement sort17_1= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"stockDet2\"]/thead/tr/th[2]")));
			highlightElement(sort17_1);
			sort17_1.click();
			Thread.sleep(2000);
			// fetch the unit number from the table
			WebElement unitno1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"stockDet2\"]/tbody/tr[1]/td[4]")));
			highlightElement(unitno1);
			String UnitnoVal = unitno1.getText();
			System.out.println("Start unit no:" + UnitnoVal);
			Thread.sleep(2000);
			// fetching quantity to bundle
			WebElement quantity1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"stockDet2\"]/tbody/tr[1]/td[8]")));
			highlightElement(quantity1);
			String quantityval = quantity1.getText();
			System.out.println("quantity to bundle:" + quantityval);
			Thread.sleep(2000);
			// click on start unit number text field
			WebElement startUnitno = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchsim\"]")));
			highlightElement(startUnitno);
			startUnitno.sendKeys(UnitnoVal);
			Thread.sleep(2000);
			// click on required bundle size text field
			WebElement bundlesize = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bundlesize\"]")));
			highlightElement(bundlesize);
			bundlesize.sendKeys(quantityval);
			Thread.sleep(2000);
			// click on search button
			WebElement searchbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
			highlightElement(searchbutton);
			searchbutton.click();
			Thread.sleep(2000);
			// scroll down to click on confirm button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels
			Thread.sleep(2000);
			//click on confirm button
			WebElement cnfbtn17 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bundleDet\"]/div[2]/div[2]/button")));
			highlightElement(cnfbtn17);
			cnfbtn17.click();
			// click on yes button
			WebElement yesbtn17 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
			highlightElement(yesbtn17);
			yesbtn17.click();
			// scroll up
			JavascriptExecutor js17 = (JavascriptExecutor) driver;
			js17.executeScript("window.scrollTo(0, 0);");
			// assert result the after mapping
			WebElement ConfirmMsg17 = wait
						            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"labelDiv\"]/div/div[1]/div/div")));
			highlightElement(ConfirmMsg17);
			String confirmText17 = ConfirmMsg17.getText();

			//ANSI escape codes for color
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";
			String cleanText17 = confirmText17.replace("×", "").trim().replaceAll("\n", " ");
			String color = cleanText17.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Final output
			System.out.println("Extracted Message after bundling: " + color + cleanText17 + ANSI_RESET);
		}
		
		@Test(priority = 18, enabled = false)
		public void approval1() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbar-collapse\"]/ul[2]/li[5]/a")));
			highlightElement(logout1);
			logout1.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout1);
			signout1.click();
			Thread.sleep(2000);
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			driver.get("http://" + dataprovider.HOST + ":" + dataprovider.PORT + "/tssgui/");
//			driver.manage().window().maximize();
			WebElement usernameField2 = driver.findElement(By.id("username"));
			highlightElement(usernameField2);
			usernameField2.click();
			usernameField2.sendKeys(dataprovider.APPROVAL_USER);
			WebElement passwordField2 = driver.findElement(By.name("password"));
			highlightElement(passwordField2);
			passwordField2.sendKeys(dataprovider.APPROVAL_PWD);
			WebElement submit2 = driver.findElement(By.id("subBtn"));
			highlightElement(submit2);
			submit2.click();
			Thread.sleep(2000);
			WebElement approvalLink1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Approval')]")));
			highlightElement(approvalLink1);
			approvalLink1.click();
			Thread.sleep(2000);
			// click on request date sort icon
			WebElement sort18 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='simTypeTab']/thead/tr/th[3])")));
			highlightElement(sort18);
			sort18.click();
			Thread.sleep(2000);
			// click on sort again
			WebElement sort18_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='simTypeTab']/thead/tr/th[3])")));
			highlightElement(sort18_1);
			sort18_1.click();
			Thread.sleep(2000);
			//click on approve icon
			WebElement approve2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fas fa-user-check']")));
			highlightElement(approve2);
			approve2.click();
			Thread.sleep(5000);
			// Enter the account password
			WebElement acctpwd1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='verAcntPwd']")));
			highlightElement(acctpwd1);
			acctpwd1.sendKeys(dataprovider.APPROVAL_PWD);
			Thread.sleep(2000);
			// Enter the comment while approving
			//WebElement cmt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='comment_259']")));
			WebElement cmt1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder, 'Enter Your Comment')]")));
			highlightElement(cmt1);
			cmt1.sendKeys("Package approval done");
			Thread.sleep(10000);
			// click on approve button
			WebElement approveBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Approve']")));
			// Optional: highlight it
			highlightElement(approveBtn1);
			approveBtn1.click();
//			// Perform double-click
//			Actions actions = new Actions(driver);
//			actions.doubleClick(approveBtn1).perform();

			Thread.sleep(5000);
			// click on yes button to confirm
			WebElement yesbtn18 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']")));
			highlightElement(yesbtn18);
			yesbtn18.click();
			Thread.sleep(3000);
			// fetch the confirmation message
			WebElement ConfirmMsg18 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/form/div/section/div/div[1]/div[1]/div/div")));
			highlightElement(ConfirmMsg18);
			String confirmText18 = ConfirmMsg18.getText();

			//ANSI escape codes for color
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";

			String cleanText18 = confirmText18.replace("×", "").trim().replaceAll("\n", " ");
			String color = cleanText18.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Final output
			System.out.println("Extracted Message after bundling approval: " + color + cleanText18 + ANSI_RESET);
			
		}
		
		@Test(priority = 19, enabled = false)
		public void qrCode() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-toggle']")));
			highlightElement(logout1);
			logout1.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout1);
			signout1.click();
			Thread.sleep(2000);
			WebElement usernameField2 = driver.findElement(By.id("username"));
			highlightElement(usernameField2);
			usernameField2.click();
			usernameField2.sendKeys(dataprovider.GUI_USER);
			WebElement passwordField2 = driver.findElement(By.name("password"));
			highlightElement(passwordField2);
			passwordField2.sendKeys(dataprovider.GUI_PASSWORD);
			WebElement submit2 = driver.findElement(By.id("subBtn"));
			highlightElement(submit2);
			submit2.click();
			Thread.sleep(2000);
			// click on QR upload link
			WebElement qrupld = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'QR Upload')]")));
			highlightElement(qrupld);
			qrupld.click();
			Thread.sleep(2000);
			// click on QR upload 
			WebElement qrupld1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[normalize-space()='QR Upload']")));
			highlightElement(qrupld1);
			qrupld1.click();
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("document.getElementById('QRFile').style.display='block';");
			WebElement fileField = wait.until(ExpectedConditions.elementToBeClickable(By.id("QRFile")));
			highlightElement(fileField);
			Thread.sleep(2000);
			fileField.sendKeys("C:\\Users\\Nagabindu\\Downloads\\0019554510621028083.zip");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(.,'Upload')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[text()='Yes']")).click();
			// Thread.sleep(2000);
			// driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
			//driver.switchTo().alert().accept();
			Thread.sleep(2000);
			// fetch the confirmation message
			WebElement ConfirmMsg19 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msgDisp\"]/div/div")));
			highlightElement(ConfirmMsg19);
			String confirmText19 = ConfirmMsg19.getText();

			//ANSI escape codes for color
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";

			String cleanText19 = confirmText19.replace("×", "").trim().replaceAll("\n", " ");
			String color = cleanText19.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Final output
			System.out.println("Extracted Message after QR upload: " + color + cleanText19 + ANSI_RESET);
		}

		@Test(priority = 20, enabled = false)
		public void stockTrans() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-toggle']")));
			highlightElement(logout2);
			logout2.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout2);
			signout2.click();
			Thread.sleep(2000);
			WebElement usernameField3 = driver.findElement(By.id("username"));
			highlightElement(usernameField3);
			usernameField3.click();
			usernameField3.sendKeys(dataprovider.MFS_USER);
			WebElement passwordField3 = driver.findElement(By.name("password"));
			highlightElement(passwordField3);
			passwordField3.sendKeys(dataprovider.MFS_PWD);
			WebElement submit3 = driver.findElement(By.id("subBtn"));
			highlightElement(submit3);
			submit3.click();
			Thread.sleep(2000);
			// click on stock transfer link
			WebElement stocktransfer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='Stock Transfer']")));
			highlightElement(stocktransfer);
			stocktransfer.click();
			Thread.sleep(10000);
			// click on bundle creation date to sort
			WebElement sort20 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"StockTransfer\"]/thead/tr/th[3]")));
			highlightElement(sort20);
			sort20.click();
			Thread.sleep(2000);
			// click on bundle creation date to sort
//			WebElement sort20_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"StockTransfer\"]/thead/tr/th[3]")));
//			highlightElement(sort20_1);
//			sort20_1.click();
			// fetch the unit number
			WebElement unitElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"StockTransfer\"]/tbody/tr[1]/td[4]")));
			highlightElement(unitElement);
			System.out.println(unitElement);
			unitno_20 = unitElement.getText();
			Thread.sleep(2000);
			// select the bundle need to transfer 
			WebElement chkbx20 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox' and @value='1'])[1]")));
			highlightElement(chkbx20);
			chkbx20.click();
			Thread.sleep(2000);
			// click on select category dropdown
			WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"category\"]")));
			highlightElement(category);
			category.click();
			Thread.sleep(2000);
			// select the retailer from category dropdown
			WebElement dealer1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"category\"]/option[4]")));
			highlightElement(dealer1);
			dealer1.click();
			Thread.sleep(2000);
			// click on region dropdown
			WebElement zone1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"zone\"]")));
			highlightElement(zone1);
			zone1.click();
			Thread.sleep(2000);
			// select the region from dropdown
			WebElement region1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"zone\"]/option[3]")));
			highlightElement(region1);
			region1.click();
			Thread.sleep(2000);
			// click on distributor agent
			WebElement dist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"distSelectDiv\"]/select")));
			highlightElement(dist);
			dist.click();
			Thread.sleep(2000);
			// select the distributor
			WebElement distagent = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"distSelectDiv\"]/select/option[2]")));
			highlightElement(distagent);
			distagent.click();
			Thread.sleep(2000);
			// scroll down to click on confirm
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(1202,570)");
			// click on confirm to transfer
			WebElement cnfm2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"transfer\"]")));
			highlightElement(cnfm2);
			cnfm2.click();
			Thread.sleep(2000);
			// click on yes team
			WebElement yes20 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
			highlightElement(yes20);
			yes20.click();
			Thread.sleep(2000);
			// scroll up
			JavascriptExecutor js20 = (JavascriptExecutor) driver;
			js20.executeScript("window.scrollTo(0, 0);");
			// fetch the confirmation message
			WebElement ConfirmMsg20 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"StockTrandferDiv\"]/div/div[1]/div/div")));
			highlightElement(ConfirmMsg20);
			String confirmText20 = ConfirmMsg20.getText();

			//ANSI escape codes for color
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";

			String cleanText20 = confirmText20.replace("×", "").trim().replaceAll("\n", " ");
			String color = cleanText20.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Final output
			System.out.println("Extracted Message after stock transfer: " + color + cleanText20 + ANSI_RESET);
		}
		
		@Test(priority = 21, enabled = false)
		public void Stocktransfer_approval1() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbar-collapse\"]/ul[2]/li[5]/a")));
			highlightElement(logout2);
			logout2.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout2);
			signout2.click();
			Thread.sleep(2000);
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			driver.get("http://" + dataprovider.HOST + ":" + dataprovider.PORT + "/tssgui/");
//			driver.manage().window().maximize();
			WebElement usernameField3 = driver.findElement(By.id("username"));
			highlightElement(usernameField3);
			usernameField3.click();
			usernameField3.sendKeys(dataprovider.APPROVAL_USER);
			WebElement passwordField3 = driver.findElement(By.name("password"));
			highlightElement(passwordField3);
			passwordField3.sendKeys(dataprovider.APPROVAL_PWD);
			WebElement submit3 = driver.findElement(By.id("subBtn"));
			highlightElement(submit3);
			submit3.click();
			Thread.sleep(2000);
			WebElement approvalLink2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[contains(.,'Approval')]")));
			highlightElement(approvalLink2);
			approvalLink2.click();
			Thread.sleep(2000);
			// click on request date sort icon
			WebElement sort21 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='simTypeTab']/thead/tr/th[3])")));
			highlightElement(sort21);
			sort21.click();
			Thread.sleep(2000);
			// click on sort again
			WebElement sort21_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='simTypeTab']/thead/tr/th[3])")));
			highlightElement(sort21_1);
			sort21_1.click();
			Thread.sleep(2000);
			//click on approve icon
			WebElement approve3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='fas fa-user-check']")));
			highlightElement(approve3);
			approve3.click();
			Thread.sleep(2000);
			// Enter the account password
			WebElement acctpwd2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='verAcntPwd']")));
			highlightElement(acctpwd2);
			acctpwd2.sendKeys(dataprovider.APPROVAL_PWD);
			Thread.sleep(2000);
			// Enter the comment while approving
			//WebElement cmt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='comment_259']")));
			WebElement cmt2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@placeholder, 'Enter Your Comment')]")));
			highlightElement(cmt2);
			cmt2.sendKeys("Package approval done");
			Thread.sleep(10000);
			// click on approve button
			WebElement approveBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Approve']")));
			// Optional: highlight it
			highlightElement(approveBtn2);
			approveBtn2.click();
//			// Perform double-click
//			Actions actions = new Actions(driver);
//			actions.doubleClick(approveBtn2).perform();

			Thread.sleep(5000);
			// click on yes button to confirm
			WebElement yesbtn21= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']")));
			highlightElement(yesbtn21);
			yesbtn21.click();
			Thread.sleep(10000);
			// fetch the confirmation message
			WebElement ConfirmMsg21 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div[2]/div/section[2]/form/div/section/div/div[1]/div[1]/div/div")));
			highlightElement(ConfirmMsg21);
			String confirmText21 = ConfirmMsg21.getText();

			//ANSI escape codes for color
			final String ANSI_GREEN = "\u001B[32m";
			final String ANSI_RED = "\u001B[31m";
			final String ANSI_RESET = "\u001B[0m";

			String cleanText21 = confirmText21.replace("×", "").trim().replaceAll("\n", " ");
			String color = cleanText21.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

			// Final output
			System.out.println("Extracted Message after stock transfer approval: " + color + cleanText21 + ANSI_RESET);
			
		}
		
		@Test(priority = 22, enabled = false)
		public void stockPullBack() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-toggle']")));
			highlightElement(logout3);
			logout3.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout3);
			signout3.click();
			Thread.sleep(2000);
			WebElement usernameField4 = driver.findElement(By.id("username"));
			highlightElement(usernameField4);
			usernameField4.click();
			usernameField4.sendKeys(dataprovider.MFS_USER);
			WebElement passwordField4 = driver.findElement(By.name("password"));
			highlightElement(passwordField4);
			passwordField4.sendKeys(dataprovider.MFS_PWD);
			WebElement submit4 = driver.findElement(By.id("subBtn"));
			highlightElement(submit4);
			submit4.click();
			Thread.sleep(2000);
			// click on stock pullback link
			WebElement stockpullback = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='Stock PullBack']")));
			highlightElement(stockpullback);
			stockpullback.click();
			Thread.sleep(2000);
			// pass the unit number in search text field
			WebElement search22 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"AgentPastTrans_filter\"]/label/input")));
			highlightElement(search22);
			System.out.println(unitno_20);
			search22.sendKeys(unitno_20);
			// fetch the dealer name
			WebElement delear22 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"AgentPastTrans\"]/tbody/tr/td[11]")));
			highlightElement(delear22);
			String dealername = delear22.getText();
			System.out.println("Dealer name:" + dealername);
			Thread.sleep(2000);
			//pass the unit number to do pullback
			WebElement unitno22 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='SimNo']")));
			highlightElement(unitno22);
			unitno22.sendKeys(unitno_20);
			Thread.sleep(2000);
			// select the dealer from the dropdown
			WebElement dealer22_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"distAgent\"]")));
			highlightElement(dealer22_1);
			dealer22_1.sendKeys(dealername);
			Thread.sleep(2000);
			// click on get details button to do pullback
			WebElement chkbx22 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox' and @value='1'])[1]")));
			highlightElement(chkbx22);
			chkbx22.click();
			Thread.sleep(2000);
			// click on pullback
			WebElement pullback = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pullback\"]")));
			highlightElement(pullback);
			pullback.click();
			Thread.sleep(2000);
			// click on yes button
			WebElement yes22 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
			highlightElement(yes22);
			yes22.click();
			Thread.sleep(2000);
			// get text and assertion
			 // Assertion for confirm message
		    WebElement ConfirmMsg22 = wait
		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ViewpageDiv\"]/div[3]/div/div")));
		    highlightElement(ConfirmMsg22);
		    String confirmText22 = ConfirmMsg22.getText();

		    // ANSI escape codes for color
		    final String ANSI_GREEN = "\u001B[32m";
		    final String ANSI_RED = "\u001B[31m";
		    final String ANSI_RESET = "\u001B[0m";

		    String cleanText22 = confirmText22.replace("×", "").trim().replaceAll("\n", " ");
		    String color = cleanText22.toLowerCase().contains("fail") ? ANSI_RED : ANSI_GREEN;

		    // Final output
		    System.out.println("Extracted Message after stock pullback: " + color + cleanText22 + ANSI_RESET);
		    
		 // ✅ Assertions based on expected messages
		    if (cleanText22.equalsIgnoreCase("Success")) {
		        System.out.println("✅ Assertion Passed: stock pullback success");
		        Assert.assertTrue(true, "stock pullback passed");
		    } else if (cleanText22.contains("failed")) {
		        System.out.println("❌ Assertion Failed: stock pullback failed");
		        Assert.fail("Stock pullback failed");
		    } else {
		        System.out.println("❌ Assertion Failed: Unexpected message: " + cleanText22);
		        Assert.fail("Unexpected alert message: " + cleanText22);
		    }
		}

		@Test(priority = 23, enabled = true)
		public void BatchProcessing_Tracker_WPD() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Logout as admin user
			WebElement logout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-toggle']")));
			highlightElement(logout1);
			logout1.click();
			Thread.sleep(2000);
			// click on signout
			WebElement signout1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"signoutDiv\"]/a")));
			highlightElement(signout1);
			signout1.click();
			Thread.sleep(2000);
			WebElement usernameField2 = driver.findElement(By.id("username"));
			highlightElement(usernameField2);
			usernameField2.click();
			usernameField2.sendKeys(dataprovider.GUI_USER);
			WebElement passwordField2 = driver.findElement(By.name("password"));
			highlightElement(passwordField2);
			passwordField2.sendKeys(dataprovider.GUI_PASSWORD);
			WebElement submit2 = driver.findElement(By.id("subBtn"));
			highlightElement(submit2);
			submit2.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker23 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker23);
			SPA_Tracker23.click();
			Thread.sleep(2000);
			// click on Batch tab ton track the batch processing operations
			WebElement Batch_Tracker23 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/div[2]/button[2]")));
			highlightElement(Batch_Tracker23);
			Batch_Tracker23.click();
			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown23 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/form[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/span[3]/div[1]/button[1]")));
			highlightElement(operation_dropdown23);
			operation_dropdown23.click();
			Thread.sleep(2000);
			// select the stock transfer operations for dropdown
			WebElement batchprocessingop23 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='multiselect-all']")));
			highlightElement(batchprocessingop23);
			batchprocessingop23.click();
			Thread.sleep(2000);
			// click on predefined date
			WebElement predefined23 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Predefined']")));
			highlightElement(predefined23);
			predefined23.click();
			Thread.sleep(2000);
			// select last 1hour
			WebElement hour1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Last 1 Hour']")));
			highlightElement(hour1);
			hour1.click();
			Thread.sleep(2000);
			
			// Click on Go button
			WebElement Gobutton23 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-refresh']")));
			highlightElement(Gobutton23);
			Gobutton23.click();
			Thread.sleep(2000);
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/thead/tr/th[3]")));
			WebElement sort23 = driver.findElement(By.xpath("//*[contains(text(),'Req Time')]"));
			highlightElement(sort23);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort23);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort23);
			sort23.click();
			Thread.sleep(2000);
			WebElement Operation1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[2]")));
			highlightElement(Operation1);
			String messageText1 = Operation1.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted batch processing Operation ID : " + messageText1);
			WebElement ResponseMsg1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[4]/b")));
			highlightElement(ResponseMsg1);
			String messaageText2 = ResponseMsg1.getText();
			System.out.println("Extracted response message for batch processing Operation ID : " + messaageText2);
			System.out.println("========================================================================================");
		}
		
		
		@Test(priority = 24, dataProvider = "MSISDN", enabled = true)
		public void Sequential_batch_WPD(String msisdn) throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh3 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh3);
			Refresh3.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker4 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker4);
			SPA_Tracker4.click();
			Thread.sleep(5000);
			// click on msisdn text field
			WebElement MSISDN_Textfield1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdn\"]")));
			highlightElement(MSISDN_Textfield1);
			MSISDN_Textfield1.sendKeys(msisdn);
			// click on predefined
			WebElement predefined24 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Predefined']")));
			highlightElement(predefined24);
			predefined24.click();
			Thread.sleep(2000);
			// select last 1hour
			WebElement hour2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Last 1 Hour']")));
			highlightElement(hour2);
			hour2.click();
			Thread.sleep(2000);
			// click on operator 
			WebElement oprclk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default btn-flat']")));
			highlightElement(oprclk);
			oprclk.click();
			// select the operator
			WebElement operation24 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='multiselect-all']")));
			highlightElement(operation24);
			operation24.click();
			Thread.sleep(2000);
			// Click on Go button
			WebElement Gobutton24 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[5]/button")));
			highlightElement(Gobutton24);
			Gobutton24.click();
			Thread.sleep(2000);
			WebElement sort24 = driver.findElement(By.xpath("//*[contains(text(),'Request Time')]"));
			highlightElement(sort24);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort24);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort24);
			sort24.click();
			Thread.sleep(2000);
			WebElement Operation24 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[1]")));
			highlightElement(Operation24);
			String messageText24 = Operation24.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted operation of sequential Tracker: " + messageText24);
			WebElement ResponseMsg24 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[5]/b")));
			highlightElement(ResponseMsg24);
			String messaageText24 = ResponseMsg24.getText();
			System.out.println("Extracted response message for operation in sequential tracker: " + messaageText24);
			System.out.println("========================================================================================");
		}
		
		@Test(priority = 16, enabled = false)
		public void BatchProcessing_Tracker() throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// click on SPA operation Tracker
			WebElement SPA_Tracker3 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker3);
			SPA_Tracker3.click();
			Thread.sleep(2000);
			// click on Batch tab ton track the batch processing operations
			WebElement Batch_Tracker3 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/div[2]/button[2]")));
			highlightElement(Batch_Tracker3);
			Batch_Tracker3.click();
			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/form[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/span[3]/div[1]/button[1]")));
			highlightElement(operation_dropdown3);
			operation_dropdown3.click();
			Thread.sleep(2000);
			// select the stock transfer operations for dropdown
			WebElement batchprocessingop3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='1']")));
			highlightElement(batchprocessingop3);
			batchprocessingop3.click();
			Thread.sleep(2000);
			// click on from date
			WebElement FromDate3 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate3);
			FromDate3.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton3 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton3);
			backbutton3.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect3 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect3);
			Dateselect3.click();
			Thread.sleep(2000);
			// click on the To date
			WebElement Todate3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate3);
			Todate3.click();
			Thread.sleep(2000);
			// select the to date

			// Step 2: Get today's date number
			int today = LocalDate.now().getDayOfMonth();
			String todayStr = String.valueOf(today);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement = null;
			for (WebElement td : activeTds) {
				if (td.getText().trim().equals(todayStr)) {
					todayElement = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement != null) {
				highlightElement(todayElement);
				todayElement.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			// Click on Go button
			WebElement Gobutton3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-refresh']")));
			highlightElement(Gobutton3);
			Gobutton3.click();
			Thread.sleep(2000);
			// WebElement sort1 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/thead/tr/th[3]")));
			WebElement sort1 = driver.findElement(By.xpath("//*[contains(text(),'Req Time')]"));
			highlightElement(sort1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort1);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort1);
			sort1.click();
			Thread.sleep(2000);
			WebElement Operation1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[2]")));
			highlightElement(Operation1);
			String messageText1 = Operation1.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted batch processing Operation ID : " + messageText1);
			WebElement ResponseMsg1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[4]/b")));
			highlightElement(ResponseMsg1);
			String messaageText2 = ResponseMsg1.getText();
			System.out.println("Extracted response message for batch processing Operation ID : " + messaageText2);
			System.out.println("========================================================================================");
		}

		@Test(priority = 17, enabled = false)
		public void Bundle_approval_Tracker() throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh2 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh2);
			Refresh2.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker2 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker2);
			SPA_Tracker2.click();
			Thread.sleep(2000);
			// click on Batch tab ton track the batch processing operations
			WebElement Batch_Tracker2 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/div[2]/button[2]")));
			highlightElement(Batch_Tracker2);
			Batch_Tracker2.click();
			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/form[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/span[3]/div[1]/button[1]")));
			highlightElement(operation_dropdown2);
			operation_dropdown2.click();
			Thread.sleep(2000);
			// select the stock transfer operations for dropdown
			WebElement batchprocessingop2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='2']")));
			highlightElement(batchprocessingop2);
			batchprocessingop2.click();
			Thread.sleep(2000);
			// click on from date
			WebElement FromDate2 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate2);
			FromDate2.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton2 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton2);
			backbutton2.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect2 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect2);
			Dateselect2.click();
			Thread.sleep(2000);
			// click on the To date
			WebElement Todate2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate2);
			Todate2.click();
			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect2);
//			Todateselect2.click();
//			Thread.sleep(2000);

			// Step 2: Get today's date number
			int today1 = LocalDate.now().getDayOfMonth();
			String todayStr1 = String.valueOf(today1);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds1 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds1.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement1 = null;
			for (WebElement td : activeTds1) {
				if (td.getText().trim().equals(todayStr1)) {
					todayElement1 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement1 != null) {
				highlightElement(todayElement1);
				todayElement1.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			// Click on Go button
			WebElement Gobutton2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-refresh']")));
			highlightElement(Gobutton2);
			Gobutton2.click();
			Thread.sleep(2000);
			WebElement sort2 = driver.findElement(By.xpath("//*[contains(text(),'Req Time')]"));
			highlightElement(sort2);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort2);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort2);
			sort2.click();
			Thread.sleep(2000);
			WebElement Operation2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[2]")));
			highlightElement(Operation2);
			String messageText2 = Operation2.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted the bundle approval operation ID: " + messageText2);
			WebElement ResponseMsg2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[4]/b")));
			highlightElement(ResponseMsg2);
			String messaageText2 = ResponseMsg2.getText();
			System.out.println("Extracted response message for bundle approval operation ID: " + messaageText2);
			System.out.println("========================================================================================");
		}

		@Test(priority = 18, enabled = false)
		public void Stock_trasfer_Tracker() throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh1);
			Refresh1.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker1 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker1);
			SPA_Tracker1.click();
			Thread.sleep(2000);
			// click on Batch tab ton track the batch processing operations
			WebElement Batch_Tracker1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/div[2]/button[2]")));
			highlightElement(Batch_Tracker1);
			Batch_Tracker1.click();
			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/form[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/span[3]/div[1]/button[1]")));
			highlightElement(operation_dropdown1);
			operation_dropdown1.click();
			Thread.sleep(2000);
			// select the stock transfer operations for dropdown
			WebElement batchprocessingop1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='3']")));
			highlightElement(batchprocessingop1);
			batchprocessingop1.click();
			Thread.sleep(2000);
			// click on from date
			WebElement FromDate1 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate1);
			FromDate1.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton1);
			backbutton1.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect1);
			Dateselect1.click();
			Thread.sleep(2000);
			// click on the To date
			WebElement Todate1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate1);
			Todate1.click();
			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect1);
//			Todateselect1.click();

			// Step 2: Get today's date number
			int today2 = LocalDate.now().getDayOfMonth();
			String todayStr2 = String.valueOf(today2);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds2 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds2.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement2 = null;
			for (WebElement td : activeTds2) {
				if (td.getText().trim().equals(todayStr2)) {
					todayElement2 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement2 != null) {
				highlightElement(todayElement2);
				todayElement2.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			Thread.sleep(2000);
			// Click on Go button
			WebElement Gobutton1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-refresh']")));
			highlightElement(Gobutton1);
			Gobutton1.click();
			Thread.sleep(2000);
			// WebElement sort3 = driver.findElement(By.xpath("//*[contains(text(),'Req
			// Time')]"));
			// highlightElement(sort3);
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", sort3);
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort3);
			// sort3.click();
			// Thread.sleep(2000);
			System.out.println("========================================================================================");
			WebElement Operation3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[2]")));
			highlightElement(Operation3);
			String messageText3 = Operation3.getText();
			System.out.println("Extracted Stock transfer operation ID: " + messageText3);
			// WebElement ResponseMsg3 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[4]/b")));
			// highlightElement(ResponseMsg3);
			// String messaageText3 = ResponseMsg3.getText();
			// System.out.println("Extracted response message for operation(stock transfer):
			// " + messaageText3);
			System.out.println("========================================================================================");
		}

		@Test(priority = 19, dataProvider = "MSISDN", enabled = false)
		public void Sequential_batch(String msisdn) throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh3 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh3);
			Refresh3.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker4 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker4);
			SPA_Tracker4.click();
			Thread.sleep(5000);
			// click on msisdn text field
			WebElement MSISDN_Textfield1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdn\"]")));
			highlightElement(MSISDN_Textfield1);
			MSISDN_Textfield1.sendKeys(msisdn);
			// click on from date
			WebElement FromDate4 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate4);
			FromDate4.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton4 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton4);
			backbutton4.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect4 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect4);
			Dateselect4.click();
			Thread.sleep(2000);
			// click on the To date
			WebElement Todate4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate4);
			Todate4.click();
			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect4);
//			Todateselect4.click();

			// Step 2: Get today's date number
			int today3 = LocalDate.now().getDayOfMonth();
			String todayStr3 = String.valueOf(today3);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds3 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds3.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement3 = null;
			for (WebElement td : activeTds3) {
				if (td.getText().trim().equals(todayStr3)) {
					todayElement3 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement3 != null) {
				highlightElement(todayElement3);
				todayElement3.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown4 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/button")));
			highlightElement(operation_dropdown4);
			operation_dropdown4.click();
			Thread.sleep(2000);
			// select the batch processing operation for dropdown
			WebElement batchprocessingop4 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/ul/li[3]/a/label/input")));
			highlightElement(batchprocessingop4);
			batchprocessingop4.click();
			Thread.sleep(2000);
			// Click on Go button
			WebElement Gobutton4 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[5]/button")));
			highlightElement(Gobutton4);
			Gobutton4.click();
			Thread.sleep(2000);
			WebElement sort4 = driver.findElement(By.xpath("//*[contains(text(),'Request Time')]"));
			highlightElement(sort4);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort4);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort4);
			sort4.click();
			Thread.sleep(2000);
			WebElement Operation4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[1]")));
			highlightElement(Operation4);
			String messageText4 = Operation4.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted operation of sequential Tracker: " + messageText4);
			WebElement ResponseMsg4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[5]/b")));
			highlightElement(ResponseMsg4);
			String messaageText4 = ResponseMsg4.getText();
			System.out.println("Extracted response message for operation in sequential tracker: " + messaageText4);
			System.out.println("========================================================================================");
		}

		@Test(priority = 20, dataProvider = "MSISDN3", enabled = false)
		public void Sequential_stock_transfer(String msisdn) throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh5 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh5);
			Refresh5.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker5 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker5);
			SPA_Tracker5.click();
			Thread.sleep(5000);
			// click on msisdn text field
			WebElement MSISDN_Textfield2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdn\"]")));
			highlightElement(MSISDN_Textfield2);
			MSISDN_Textfield2.sendKeys(msisdn);
			// click on from date
			WebElement FromDate5 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate5);
			FromDate5.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton5 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton5);
			backbutton5.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect5 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect5);
			Dateselect5.click();
			Thread.sleep(2000);
			// click on To date
			WebElement Todate5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate5);
			Todate5.click();
			Thread.sleep(2000);

			// select the To date
//			WebElement Todate5 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
//			highlightElement(Todate5);
//			Todate5.click();

			// Step 2: Get today's date number
			int today4 = LocalDate.now().getDayOfMonth();
			String todayStr4 = String.valueOf(today4);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds4 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds4.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement4 = null;
			for (WebElement td : activeTds4) {
				if (td.getText().trim().equals(todayStr4)) {
					todayElement4 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement4 != null) {
				highlightElement(todayElement4);
				todayElement4.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect5);
//			Todateselect5.click();
//			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown5 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/button")));
			highlightElement(operation_dropdown5);
			operation_dropdown5.click();
			Thread.sleep(2000);
			// select the stock transfer operation for dropdown
			WebElement batchprocessingop5 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/ul/li[5]/a/label/input")));
			highlightElement(batchprocessingop5);
			batchprocessingop5.click();
			Thread.sleep(2000);
			// Click on Go button
			WebElement Gobutton5 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[5]/button")));
			highlightElement(Gobutton5);
			Gobutton5.click();
			Thread.sleep(2000);
			WebElement sort5 = driver.findElement(By.xpath("//*[contains(text(),'Request Time')]"));
			highlightElement(sort5);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort5);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort5);
			sort5.click();
			Thread.sleep(2000);
			WebElement Operation5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[1]")));
			highlightElement(Operation5);
			String messageText5 = Operation5.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted operation in sequential Tracker: " + messageText5);
			WebElement ResponseMsg5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[5]/b")));
			highlightElement(ResponseMsg5);
			String messaageText5 = ResponseMsg5.getText();
			System.out.println("Extracted response message for operation in sequential tracker: " + messaageText5);
			System.out.println("========================================================================================");
		}

		@Test(priority = 21, dataProvider = "MSISDN2", enabled = false)
		public void sequential_bundle(String msisdn) throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh6 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh6);
			Refresh6.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker6 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker6);
			SPA_Tracker6.click();
			Thread.sleep(5000);
			// click on msisdn text field
			WebElement MSISDN_Textfield3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdn\"]")));
			highlightElement(MSISDN_Textfield3);
			MSISDN_Textfield3.sendKeys(msisdn);
			// click on from date
			WebElement FromDate6 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate6);
			FromDate6.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton6 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton6);
			backbutton6.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect6 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect6);
			Dateselect6.click();
			Thread.sleep(2000);
			// click on To date
			WebElement Todate6 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate6);
			Todate6.click();
			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect6);
//			Todateselect6.click();

			// Step 2: Get today's date number
			int today5 = LocalDate.now().getDayOfMonth();
			String todayStr5 = String.valueOf(today5);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds5 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds5.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement5 = null;
			for (WebElement td : activeTds5) {
				if (td.getText().trim().equals(todayStr5)) {
					todayElement5 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement5 != null) {
				highlightElement(todayElement5);
				todayElement5.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown6 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/button")));
			highlightElement(operation_dropdown6);
			operation_dropdown6.click();
			Thread.sleep(2000);
			// select the bundle approval operation for dropdown
			WebElement batchprocessingop6 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/ul/li[4]/a/label/input")));
			highlightElement(batchprocessingop6);
			batchprocessingop6.click();
			Thread.sleep(2000);
			// Click on Go button
			WebElement Gobutton6 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[5]/button")));
			highlightElement(Gobutton6);
			Gobutton6.click();
			Thread.sleep(2000);
			// WebElement sort6 = driver.findElement(By.xpath("//*[contains(text(),'Request
			// Time')]"));
			// highlightElement(sort6);
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", sort6);
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort6);
			// sort6.click();
			// Thread.sleep(2000);
			WebElement Operation6 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[1]")));
			highlightElement(Operation6);
			String messageText6 = Operation6.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted operation in sequential Tracker: " + messageText6);
			// WebElement ResponseMsg6 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[5]/b")));
			// highlightElement(ResponseMsg6);
			// String messaageText6 = ResponseMsg6.getText();
			// System.out.println("Extracted response message for opration in sequential
			// tracker: " + messaageText6);
			System.out.println("========================================================================================");
		}

		@Test(priority = 22, enabled = false)
		public void BatchProcessing_Tracker_search() throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// click on refresh button on browser
			WebElement Refresh7 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh7);
			Refresh7.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker7 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker7);
			SPA_Tracker7.click();
			Thread.sleep(2000);
			// click on Batch tab ton track the batch processing operations
			WebElement Batch_Tracker7 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/div[2]/button[2]")));
			highlightElement(Batch_Tracker7);
			Batch_Tracker7.click();
			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/form[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/span[3]/div[1]/button[1]")));
			highlightElement(operation_dropdown7);
			operation_dropdown7.click();
			Thread.sleep(2000);
			// select the stock transfer operations for dropdown
			WebElement batchprocessingop7 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='1']")));
			highlightElement(batchprocessingop7);
			batchprocessingop7.click();
			Thread.sleep(2000);
			// click on from date
			WebElement FromDate7 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate7);
			FromDate7.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton7 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton7);
			backbutton7.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect7 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect7);
			Dateselect7.click();
			Thread.sleep(2000);
			// click on the To date
			WebElement Todate7 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate7);
			Todate7.click();
			Thread.sleep(2000);
			// select the to date

			// Step 2: Get today's date number
			int today6 = LocalDate.now().getDayOfMonth();
			String todayStr6 = String.valueOf(today6);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds6 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement6 = null;
			for (WebElement td : activeTds6) {
				if (td.getText().trim().equals(todayStr6)) {
					todayElement6 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement6 != null) {
				highlightElement(todayElement6);
				todayElement6.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			// Click on Go button
			WebElement Gobutton7 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-refresh']")));
			highlightElement(Gobutton7);
			Gobutton7.click();
			Thread.sleep(2000);

			// search for particular record
			WebElement searchbtn1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE_filter\"]/label/input")));
			highlightElement(searchbtn1);
			searchbtn1.sendKeys(dataprovider.SEARCH_ID);

			// WebElement sort1 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/thead/tr/th[3]")));
			WebElement sort7 = driver.findElement(By.xpath("//*[contains(text(),'Req Time')]"));
			highlightElement(sort7);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort7);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort7);
			sort7.click();
			Thread.sleep(2000);
			WebElement Operation7 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[2]")));
			highlightElement(Operation7);
			String messageText7 = Operation7.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted of Search ID for batch processing operation: " + messageText7);
			WebElement ResponseMsg7 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[4]/b")));
			highlightElement(ResponseMsg7);
			String messaageText7 = ResponseMsg7.getText();
			System.out.println("Extracted response message for batch processing operation ID: " + messaageText7);
			System.out.println("========================================================================================");
		}

		@Test(priority = 23, enabled = false)
		public void Bundle_approval_Tracker_search() throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh8 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh8);
			Refresh8.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker8 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker8);
			SPA_Tracker8.click();
			Thread.sleep(2000);
			// click on Batch tab ton track the batch processing operations
			WebElement Batch_Tracker8 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/div[2]/button[2]")));
			highlightElement(Batch_Tracker8);
			Batch_Tracker8.click();
			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/section[2]/form[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/span[3]/div[1]/button[1]")));
			highlightElement(operation_dropdown8);
			operation_dropdown8.click();
			Thread.sleep(2000);
			// select the stock transfer operations for dropdown
			WebElement batchprocessingop8 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='2']")));
			highlightElement(batchprocessingop8);
			batchprocessingop8.click();
			Thread.sleep(2000);
			// click on from date
			WebElement FromDate8 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate8);
			FromDate8.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton8 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton8);
			backbutton8.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect8 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect8);
			Dateselect8.click();
			Thread.sleep(2000);
			// click on the To date
			WebElement Todate8 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate8);
			Todate8.click();
			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect2);
//			Todateselect2.click();
//			Thread.sleep(2000);

			// Step 2: Get today's date number
			int today7 = LocalDate.now().getDayOfMonth();
			String todayStr7 = String.valueOf(today7);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds1 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds1.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement7 = null;
			for (WebElement td : activeTds1) {
				if (td.getText().trim().equals(todayStr7)) {
					todayElement7 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement7 != null) {
				highlightElement(todayElement7);
				todayElement7.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			// Click on Go button
			WebElement Gobutton8 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-refresh']")));
			highlightElement(Gobutton8);
			Gobutton8.click();
			Thread.sleep(2000);

			// search for particular record
			WebElement searchbtn2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE_filter\"]/label/input")));
			highlightElement(searchbtn2);
			searchbtn2.sendKeys(dataprovider.SEARCH_ID1);

			WebElement sort8 = driver.findElement(By.xpath("//*[contains(text(),'Req Time')]"));
			highlightElement(sort8);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort8);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort8);
			sort8.click();
			Thread.sleep(2000);
			WebElement Operation8 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[2]")));
			highlightElement(Operation8);
			String messageText8 = Operation8.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted for search ID for bundle approval operation: " + messageText8);
			WebElement ResponseMsg8 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr[1]/td[4]/b")));
			highlightElement(ResponseMsg8);
			String messaageText8 = ResponseMsg8.getText();
			System.out.println("Extracted response message for search bundle approval operation: " + messaageText8);
			System.out.println("========================================================================================");
		}

		@Test(priority = 24, enabled = false)
		public void Sequential_batch_search() throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh9 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh9);
			Refresh9.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker9 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker9);
			SPA_Tracker9.click();
			Thread.sleep(5000);
			// click on msisdn text field
			WebElement MSISDN_Textfield9 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdn\"]")));
			highlightElement(MSISDN_Textfield9);
			MSISDN_Textfield9.sendKeys(dataprovider.MSISDN1);
			// click on from date
			WebElement FromDate9 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate9);
			FromDate9.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton9 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton9);
			backbutton9.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect9 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect9);
			Dateselect9.click();
			Thread.sleep(2000);
			// click on the To date
			WebElement Todate9 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate9);
			Todate9.click();
			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect4);
//			Todateselect4.click();

			// Step 2: Get today's date number
			int today8 = LocalDate.now().getDayOfMonth();
			String todayStr8 = String.valueOf(today8);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds8 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds3.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement8 = null;
			for (WebElement td : activeTds8) {
				if (td.getText().trim().equals(todayStr8)) {
					todayElement8 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement8 != null) {
				highlightElement(todayElement8);
				todayElement8.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown9 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/button")));
			highlightElement(operation_dropdown9);
			operation_dropdown9.click();
			Thread.sleep(2000);
			// select the batch processing operation for dropdown
			WebElement batchprocessingop9 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/ul/li[3]/a/label/input")));
			highlightElement(batchprocessingop9);
			batchprocessingop9.click();
			Thread.sleep(2000);
			// Click on Go button
			WebElement Gobutton9 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[5]/button")));
			highlightElement(Gobutton9);
			Gobutton9.click();
			Thread.sleep(2000);
			WebElement sort9 = driver.findElement(By.xpath("//*[contains(text(),'Request Time')]"));
			highlightElement(sort9);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort9);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort9);
			sort9.click();
			Thread.sleep(2000);
			WebElement Operation9 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[1]")));
			highlightElement(Operation9);
			String messageText4 = Operation9.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted for particular msisdn in sequential Tracker: " + messageText4);
			WebElement ResponseMsg9 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[5]/b")));
			highlightElement(ResponseMsg9);
			String messaageText9 = ResponseMsg9.getText();
			System.out.println("Extracted response message for operation in sequential tracker: " + messaageText9);
			System.out.println("========================================================================================");
		}

		@Test(priority = 25, enabled = false)
		public void Sequential_stock_transfer_search() throws InterruptedException {
			// Initialize WebDriverWait with a timeout of 10 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			Thread.sleep(3000);
			// click on refresh button on browser
			WebElement Refresh10 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html[1]/body[1]/div[2]/div[2]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/i[1]")));
			highlightElement(Refresh10);
			Refresh10.click();
			Thread.sleep(2000);
			// click on SPA operation Tracker
			WebElement SPA_Tracker10 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//b[normalize-space()='SPA Operation Tracker']")));
			highlightElement(SPA_Tracker10);
			SPA_Tracker10.click();
			Thread.sleep(5000);
			// click on msisdn text field
			WebElement MSISDN_Textfield10 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"msisdn\"]")));
			highlightElement(MSISDN_Textfield10);
			MSISDN_Textfield10.sendKeys(dataprovider.MSISDN3);
			// click on from date
			WebElement FromDate10 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='fromDate']//i[@class='fa fa-calendar']")));
			highlightElement(FromDate10);
			FromDate10.click();
			Thread.sleep(2000);
			// click on back button
			WebElement backbutton10 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/thead/tr[1]/th[1]")));
			highlightElement(backbutton10);
			backbutton10.click();
			Thread.sleep(2000);
			// select the required date
			WebElement Dateselect10 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/table/tbody/tr[1]/td[3]")));
			highlightElement(Dateselect10);
			Dateselect10.click();
			Thread.sleep(2000);
			// click on To date
			WebElement Todate10 = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
			highlightElement(Todate10);
			Todate10.click();
			Thread.sleep(2000);

			// select the To date
//			WebElement Todate5 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#toDate > .input-group-addon .fa")));
//			highlightElement(Todate5);
//			Todate5.click();

			// Step 2: Get today's date number
			int today9 = LocalDate.now().getDayOfMonth();
			String todayStr9 = String.valueOf(today9);

			// Step 3: Debug print all active td elements after popup opens
			List<WebElement> activeTds9 = driver.findElements(By.xpath(
					"//div[contains(@class,'datepicker')]//td[not(contains(@class,'disabled')) and normalize-space(text()) != '']"));
			// System.out.println("Active date cells count: " + activeTds4.size());

			// for (WebElement td : activeTds) {
			// System.out.println("TD: " + td.getText() + " | class: " +
			// td.getAttribute("class"));
			// }

			// Step 4: Find today's date element
			WebElement todayElement9 = null;
			for (WebElement td : activeTds9) {
				if (td.getText().trim().equals(todayStr9)) {
					todayElement9 = td;
					break;
				}
			}

			// Step 5: Click today's date if found
			if (todayElement9 != null) {
				highlightElement(todayElement9);
				todayElement9.click();
				Thread.sleep(2000);
			} else {
				System.out.println("Today's date not found in the calendar.");
			}

			Thread.sleep(2000);
			// select the to date
//			WebElement Todateselect10 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div/div[1]/table/tbody/tr[3]/td[5]")));
//			highlightElement(Todateselect10);
//			Todateselect10.click();
//			Thread.sleep(2000);
			// click on operations dropdown
			WebElement operation_dropdown10 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/button")));
			highlightElement(operation_dropdown10);
			operation_dropdown10.click();
			Thread.sleep(2000);
			// select the stock transfer operation for dropdown
			WebElement batchprocessingop10 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[4]/span[3]/div/ul/li[5]/a/label/input")));
			highlightElement(batchprocessingop10);
			batchprocessingop10.click();
			Thread.sleep(2000);
			// Click on Go button
			WebElement Gobutton10 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"SPATrackerDiv\"]/div/div[2]/div[5]/button")));
			highlightElement(Gobutton10);
			Gobutton10.click();
			Thread.sleep(2000);
			WebElement sort10 = driver.findElement(By.xpath("//*[contains(text(),'Request Time')]"));
			highlightElement(sort10);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sort10);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sort10);
			sort10.click();
			Thread.sleep(2000);
			WebElement Operation10 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[1]")));
			highlightElement(Operation10);
			String messageText10 = Operation10.getText();
			System.out.println("========================================================================================");
			System.out.println("Extracted for particular msisdn in sequential tracker: " + messageText10);
			WebElement ResponseMsg10 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ACTIVE\"]/tbody/tr/td[5]/b")));
			highlightElement(ResponseMsg10);
			String messaageText10 = ResponseMsg10.getText();
			System.out.println("Extracted response message for operation in sequential tracker: " + messaageText10);
			System.out.println("========================================================================================");
		}

		@AfterClass
		public void tearDown1() {
			//driver.quit();
		}

	}

