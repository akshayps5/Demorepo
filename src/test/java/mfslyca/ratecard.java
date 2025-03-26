package mfslyca;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import java.io.File;
	import java.io.IOException;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Set;

	import java.sql.Connection;  // ✅ Correct for JDBC
	import net.sourceforge.tess4j.ITesseract;
	import net.sourceforge.tess4j.Tesseract;
	import net.sourceforge.tess4j.TesseractException;

	import org.apache.commons.io.FileUtils;


	public class ratecard  extends DataProvider4lycaMFS {
		  private WebDriver driver;
		  private Map<String, Object> vars;
		  JavascriptExecutor js;
		  
		  @BeforeClass
		  public void setUp() throws IOException, TesseractException {
		    driver = new ChromeDriver();
		    js = (JavascriptExecutor) driver;
		    vars = new HashMap<String, Object>();
		    
		    driver.get("http://10.0.6.107/");
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img")));

	        // Take a screenshot of the captcha
	        File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
	        File destFile = new File("C:\\Users\\Indira\\eclipse-workspace\\GuiAuto\\src\\test\\java\\MFS_Lyca\\GuiAuto\\capcha.png");
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
		    driver.findElement(By.id("captchaUsr")).sendKeys(captchaText);
	        driver.findElement(By.id("btnSearch")).click();  
		  }
		  
		  public String waitForWindow(int timeout) {
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
		  
		  @Test(enabled = true, priority = 1,dataProvider = "getDetails")
		  public void RateCard(String ratecardName, String PlanID , String Month, String Amt, String Subsi) throws InterruptedException, ClassNotFoundException {
		   
		    vars.put("window_handles", driver.getWindowHandles());
		    driver.findElement(By.id("btnSearch")).click();
		    vars.put("win3026", waitForWindow(2000));
		    // Scroll To Rate Card
		    driver.switchTo().window(vars.get("win3026").toString());
		    Thread.sleep(2000);
		    // Click on theRate Card
		    driver.findElement(By.cssSelector(".row:nth-child(6) > .col-md-4:nth-child(1) li:nth-child(3) font")).click();
		    Thread.sleep(2000);
		   // Enter the Rate Card name 
		    driver.findElement(By.cssSelector("#ratecardname")).sendKeys(ratecardName);
		    Thread.sleep(2000);
		    // give planId
		    driver.findElement(By.cssSelector("#planid")).sendKeys(PlanID);
		    Thread.sleep(2000);
		    // Click on dropdown
		    driver.findElement(By.cssSelector("#channel")).click();
		    Thread.sleep(2000);
		    // Select one
		    driver.findElement(By.cssSelector("#channel > option:nth-child(2)")).click();
		    Thread.sleep(2000);
		    // Select the payment method
		    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(1)")).click();
		    Thread.sleep(2000);
		    // select any one
		    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(2)")).click();
		    Thread.sleep(2000);
		    //select the payment gateway
		    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(1)")).click();
		    Thread.sleep(2000);
		    // select the gateway
		    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(2)")).click();
		    Thread.sleep(2000);
		    // select event flag
		    driver.findElement(By.cssSelector("#eventflag > option:nth-child(1)")).click();
		    Thread.sleep(2000);
		    // select from dropdown
		    driver.findElement(By.cssSelector("#eventflag > option:nth-child(2)")).click();
		    Thread.sleep(2000);
		    //enter the inst month
		    driver.findElement(By.cssSelector("#inst")).sendKeys(Month);
		    Thread.sleep(2000);
		    // enter the actual bundle
		    driver.findElement(By.cssSelector("#actualprice")).sendKeys(Amt);
		    Thread.sleep(2000);
		    // enter the subsidizied bundle
		    driver.findElement(By.cssSelector("#subprice")).sendKeys(Subsi);
		    Thread.sleep(2000);
		    
		    // click on add btn
		    driver.findElement(By.cssSelector("#trackIn > div:nth-child(2) > div > div:nth-child(4) > div.form-group.col-sm-2 > button")).click();
		    Thread.sleep(2000);
		    
		    // click on create btn
		    driver.findElement(By.cssSelector("#createPromo")).click();
		    Thread.sleep(2000);
		    
		 // Assertion for the alert
		    Alert alert = driver.switchTo().alert();
		    String expectedTitle = "Created successfully!";		    
	        String actualTitle = alert.getText(); // Get text of alert

	        // Assert that the page title is as expected
	        Assert.assertEquals(actualTitle, expectedTitle, "Created successfully!");
	        
	        // Check the db status.
	        
	        Class.forName("oracle.jdbc.OracleDriver");
	        try (Connection connection =  DriverManager.getConnection(
	                "jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", 
	                "mfs", 
	                "mfs")) 
	        {
	            // Creating a statement object
	            Statement statement = connection.createStatement();
	            // Executing the SQL query
	            String sqlQuery = "SELECT RATE_CARD_ID FROM MFS_RATE_CARD_MAST WHERE RATE_CARD_NAME = '"  + ratecardName + "' ";
	            ResultSet resultSet = statement.executeQuery(sqlQuery);
	            // Check if any results exist
	            if (resultSet.next()) {
	                int rateCardId = resultSet.getInt("RATE_CARD_ID");
	                System.out.println("RATE_CARD_ID: " + rateCardId);
	            } else {
	                System.out.println("No data found for RATE_CARD_NAME = '" + ratecardName + "'");
	            }

	            // Close resources
	            resultSet.close();
	            statement.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		  }
		  
		  @Test(priority = 2 , enabled = false, dataProvider = "getDetails")
		  public void DuplicatePlanId(String CardName, String ID1 , String Inst, String amount, String subsid, String ID2 , String Inst2, String amount2, String subsid2 ) throws InterruptedException, ClassNotFoundException {
			  
			  	vars.put("window_handles", driver.getWindowHandles());
			    driver.findElement(By.id("btnSearch")).click();
			    vars.put("win3026", waitForWindow(2000));
			    
			    // Scroll To Rate Card
			    driver.switchTo().window(vars.get("win3026").toString());
			    Thread.sleep(2000);
			    // Click on theRate Card
			    driver.findElement(By.cssSelector(".row:nth-child(6) > .col-md-4:nth-child(1) li:nth-child(3) font")).click();
			    Thread.sleep(2000);
			   // Enter the Rate Card name 
			    driver.findElement(By.cssSelector("#ratecardname")).sendKeys(CardName);
			    Thread.sleep(2000);
			    // give planId
			    driver.findElement(By.cssSelector("#planid")).sendKeys(ID1);
			    Thread.sleep(2000);
			    // Click on dropdown
			    driver.findElement(By.cssSelector("#channel")).click();
			    Thread.sleep(2000);
			    // Select one
			    driver.findElement(By.cssSelector("#channel > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // Select the payment method
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select any one
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //select the payment gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select the gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // select event flag
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select from dropdown
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //enter the inst month
			    driver.findElement(By.cssSelector("#inst")).sendKeys(Inst);
			    Thread.sleep(2000);
			    // enter the actual bundle
			    driver.findElement(By.cssSelector("#actualprice")).sendKeys(amount);
			    Thread.sleep(2000);
			    // enter the subsidizied bundle
			    driver.findElement(By.cssSelector("#subprice")).sendKeys(subsid);
			    Thread.sleep(2000);
			    
			    // click on add btn
			    driver.findElement(By.cssSelector("#trackIn > div:nth-child(2) > div > div:nth-child(4) > div.form-group.col-sm-2 > button")).click();
			    Thread.sleep(2000);
			    
		/////// Duplicate PlanID for same rate card user //// 	    
			    
			 // give planId
			    driver.findElement(By.cssSelector("#planid")).sendKeys(ID2);
			    Thread.sleep(2000);
			    // Click on dropdown
			    driver.findElement(By.cssSelector("#channel")).click();
			    Thread.sleep(2000);
			    // Select one
			    driver.findElement(By.cssSelector("#channel > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // Select the payment method
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select any one
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //select the payment gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select the gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // select event flag
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select from dropdown
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //enter the inst month
			    driver.findElement(By.cssSelector("#inst")).sendKeys(Inst2);
			    Thread.sleep(2000);
			    // enter the actual bundle
			    driver.findElement(By.cssSelector("#actualprice")).sendKeys(amount2);
			    Thread.sleep(2000);
			    // enter the subsidizied bundle
			    driver.findElement(By.cssSelector("#subprice")).sendKeys(subsid2);
			    Thread.sleep(2000);
			    
			    // click on add btn
			    driver.findElement(By.cssSelector("#trackIn > div:nth-child(2) > div > div:nth-child(4) > div.form-group.col-sm-2 > button")).click();
			    Thread.sleep(2000);
			    
			 // click on create btn
			    driver.findElement(By.cssSelector("#createPromo")).click();
			    Thread.sleep(2000);
			    System.out.println("Created successfully");
			   
			    // Assertion for the alert
			    Alert alert = driver.switchTo().alert();
			    String expectedTitle = "Created successfully!";		    
		        String actualTitle = alert.getText(); // Get text of alert

		        // Assert that the page title is as expected
		        Assert.assertEquals(actualTitle, expectedTitle, "Created successfully!");
		        
		        // Check the db status.
		        
		        Class.forName("oracle.jdbc.OracleDriver");
		        try (Connection connection =  DriverManager.getConnection(
		                "jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", 
		                "mfs", 
		                "mfs")) 
		        {
		            // Creating a statement object
		            Statement statement = connection.createStatement();
		            // Executing the SQL query
		            String sqlQuery = "SELECT RATE_CARD_ID FROM MFS_RATE_CARD_MAST WHERE RATE_CARD_NAME = '" + CardName + "' ";
		            ResultSet resultSet = statement.executeQuery(sqlQuery);
		            // Check if any results exist
		            if (resultSet.next()) {
		                int rateCardId = resultSet.getInt("RATE_CARD_ID");
		                System.out.println("RATE_CARD_ID: " + rateCardId);
		            } else {
		                System.out.println("No data found for RATE_CARD_NAME = '" + CardName + "' ");
		            }

		            // Close resources
		            resultSet.close();
		            statement.close();
		 
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        
		  }
		  
		  @Test(priority = 3 , enabled = false)
		  public void MultiplePlanId(String CardName1, String ID3, String Inst3, String amount3, String subsid3, String ID4 , String Inst4, String amount4, String subsid4) throws InterruptedException, ClassNotFoundException {
			  
			  	vars.put("window_handles", driver.getWindowHandles());
			    driver.findElement(By.id("btnSearch")).click();
			    vars.put("win3026", waitForWindow(2000));
			    
			    // Scroll To Rate Card
			    driver.switchTo().window(vars.get("win3026").toString());
			    Thread.sleep(2000);
			    // Click on theRate Card
			    driver.findElement(By.cssSelector(".row:nth-child(6) > .col-md-4:nth-child(1) li:nth-child(3) font")).click();
			    Thread.sleep(2000);
			   // Enter the Rate Card name 
			    driver.findElement(By.cssSelector("#ratecardname")).sendKeys(CardName1);
			    Thread.sleep(2000);
			    // give planId
			    driver.findElement(By.cssSelector("#planid")).sendKeys(ID3);
			    Thread.sleep(2000);
			    // Click on dropdown
			    driver.findElement(By.cssSelector("#channel")).click();
			    Thread.sleep(2000);
			    // Select one
			    driver.findElement(By.cssSelector("#channel > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // Select the payment method
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select any one
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //select the payment gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select the gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // select event flag
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select from dropdown
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //enter the inst month
			    driver.findElement(By.cssSelector("#inst")).sendKeys(Inst3);
			    Thread.sleep(2000);
			    // enter the actual bundle
			    driver.findElement(By.cssSelector("#actualprice")).sendKeys(amount3);
			    Thread.sleep(2000);
			    // enter the subsidizied bundle
			    driver.findElement(By.cssSelector("#subprice")).sendKeys(subsid3);
			    Thread.sleep(2000);
			    
			    // click on add btn
			    driver.findElement(By.cssSelector("#trackIn > div:nth-child(2) > div > div:nth-child(4) > div.form-group.col-sm-2 > button")).click();
			    Thread.sleep(2000);
			    
		/////// Duplicate PlanID for same rate card user //// 	    
			    
			 // give planId
			    driver.findElement(By.cssSelector("#planid")).sendKeys(ID4);
			    Thread.sleep(2000);
			    // Click on dropdown
			    driver.findElement(By.cssSelector("#channel")).click();
			    Thread.sleep(2000);
			    // Select one
			    driver.findElement(By.cssSelector("#channel > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // Select the payment method
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select any one
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //select the payment gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select the gateway
			    driver.findElement(By.cssSelector("#paymentgateway > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // select event flag
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select from dropdown
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //enter the inst month
			    driver.findElement(By.cssSelector("#inst")).sendKeys(Inst4);
			    Thread.sleep(2000);
			    // enter the actual bundle
			    driver.findElement(By.cssSelector("#actualprice")).sendKeys(amount4);
			    Thread.sleep(2000);
			    // enter the subsidizied bundle
			    driver.findElement(By.cssSelector("#subprice")).sendKeys(subsid4);
			    Thread.sleep(2000);
			    
			    // click on add btn
			    driver.findElement(By.cssSelector("#trackIn > div:nth-child(2) > div > div:nth-child(4) > div.form-group.col-sm-2 > button")).click();
			    Thread.sleep(2000);
			    
			 // click on create btn
			    driver.findElement(By.cssSelector("#createPromo")).click();
			    Thread.sleep(2000);
			    System.out.println("Created successfully");
			   
			    // Assertion for the alert
			    Alert alert = driver.switchTo().alert();
			    String expectedTitle = "Created successfully!";		    
		        String actualTitle = alert.getText(); // Get text of alert

		        // Assert that the page title is as expected
		        Assert.assertEquals(actualTitle, expectedTitle, "Created successfully!");
		        
		        // Check the db status.
		        
		        Class.forName("oracle.jdbc.OracleDriver");
		        try (Connection connection =  DriverManager.getConnection(
		                "jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", 
		                "mfs", 
		                "mfs")) 
		        {
		            // Creating a statement object
		            Statement statement = connection.createStatement();
		            // Executing the SQL query
		            String sqlQuery = "SELECT RATE_CARD_ID FROM MFS_RATE_CARD_MAST WHERE RATE_CARD_NAME = '" + CardName1 + "' ";
		            ResultSet resultSet = statement.executeQuery(sqlQuery);
		            // Check if any results exist
		            if (resultSet.next()) {
		                int rateCardId = resultSet.getInt("RATE_CARD_ID");
		                System.out.println("RATE_CARD_ID: " + rateCardId);
		            } else {
		                System.out.println("No data found for RATE_CARD_NAME = '" + CardName1 + " '");
		            }

		            // Close resources
		            resultSet.close();
		            statement.close();
		 
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		  }
		  
		  @Test(priority = 4, enabled = false)
		  public void MissingDatatoRateCard(String RatecardName, String Planid , String Instant, String Money, String Subsidized ) throws InterruptedException, ClassNotFoundException {
			  
			  	vars.put("window_handles", driver.getWindowHandles());
			    driver.findElement(By.id("btnSearch")).click();
			    vars.put("win3026", waitForWindow(2000));
			    
			    // Scroll To Rate Card
			    driver.switchTo().window(vars.get("win3026").toString());
			    Thread.sleep(2000);
			    // Click on theRate Card
			    driver.findElement(By.cssSelector(".row:nth-child(6) > .col-md-4:nth-child(1) li:nth-child(3) font")).click();
			    Thread.sleep(2000);
			   // Enter the Rate Card name 
			    driver.findElement(By.cssSelector("#ratecardname")).sendKeys(RatecardName);
			    Thread.sleep(2000);
			    // give planId
			    driver.findElement(By.cssSelector("#planid")).sendKeys(Planid);
			    Thread.sleep(2000);
			    // Click on dropdown
			    driver.findElement(By.cssSelector("#channel")).click();
			    Thread.sleep(2000);
			    // Select one
			    driver.findElement(By.cssSelector("#channel > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    // Select the payment method
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select any one
			    driver.findElement(By.cssSelector("#paymentmode > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			   
			   /// Not providing the mandatory parameters case
			    
			    // select event flag
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(1)")).click();
			    Thread.sleep(2000);
			    // select from dropdown
			    driver.findElement(By.cssSelector("#eventflag > option:nth-child(2)")).click();
			    Thread.sleep(2000);
			    //enter the inst month
			    driver.findElement(By.cssSelector("#inst")).sendKeys(Instant);
			    Thread.sleep(2000);
			    // enter the actual bundle
			    driver.findElement(By.cssSelector("#actualprice")).sendKeys(Money);
			    Thread.sleep(2000);
			    // enter the subsidized bundle
			    driver.findElement(By.cssSelector("#subprice")).sendKeys(Subsidized);
			    Thread.sleep(2000);
			    
			    // click on add btn
			    driver.findElement(By.cssSelector("#trackIn > div:nth-child(2) > div > div:nth-child(4) > div.form-group.col-sm-2 > button")).click();
			    Thread.sleep(2000);
			    
			    
			    // Assertion for the alert
			    Alert alert = driver.switchTo().alert();
			    String expectedTitle = "Please fill all fields before adding.";		    
		        String actualTitle = alert.getText(); // Get text of alert

		        // Assert that the page title is as expected
		        Assert.assertEquals(actualTitle, expectedTitle, "Missing the mandatory parameter");
		        
			    
			    // Check the db status.
		        
		        Class.forName("oracle.jdbc.OracleDriver");
		        try (Connection connection =  DriverManager.getConnection(
		                "jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", 
		                "mfs", 
		                "mfs")) 
		        {
		            // Creating a statement object
		            Statement statement = connection.createStatement();
		            // Executing the SQL query
		            String sqlQuery = "SELECT RATE_CARD_ID FROM MFS_RATE_CARD_MAST WHERE RATE_CARD_NAME = '" + RatecardName + "'";
		            ResultSet resultSet = statement.executeQuery(sqlQuery);
		            // Check if any results exist
		            if (resultSet.next()) {
		                int rateCardId = resultSet.getInt("RATE_CARD_ID");
		                System.out.println("RATE_CARD_ID: " + rateCardId);
		            } else {
		                System.out.println("No data found for RATE_CARD_NAME = '" + RatecardName + "'");
		            }

		            // Close resources
		            resultSet.close();
		            statement.close();
		 
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		  }
		  
		  @Test(enabled = false, priority = 5,dataProvider = "getDetails")
		  public void ExisitingName(String Name) throws InterruptedException, ClassNotFoundException {
		   
		    vars.put("window_handles", driver.getWindowHandles());
		    driver.findElement(By.id("btnSearch")).click();
		    vars.put("win3026", waitForWindow(2000));
		    // Scroll To Rate Card
		    driver.switchTo().window(vars.get("win3026").toString());
		    Thread.sleep(2000);
		    // Click on theRate Card
		    driver.findElement(By.cssSelector(".row:nth-child(6) > .col-md-4:nth-child(1) li:nth-child(3) font")).click();
		    Thread.sleep(2000);
		   // Enter the Rate Card name 
		    driver.findElement(By.cssSelector("#ratecardname")).sendKeys(Name);
		    Thread.sleep(2000);
		    
		 // Assertion for the alert
		    Alert alert = driver.switchTo().alert();
		    String expectedTitle = "Rate Card Name already exists. Please enter a different name.";		    
	        String actualTitle = alert.getText(); // Get text of alert

	        // Assert that the page title is as expected
	        Assert.assertEquals(actualTitle, expectedTitle, "Error as already exists");
	        
	        // Check the db status.
	        
	        Class.forName("oracle.jdbc.OracleDriver");
	        try (Connection connection =  DriverManager.getConnection(
	                "jdbc:oracle:thin:@//10.0.6.107:1521/BPLORA", 
	                "mfs", 
	                "mfs")) 
	        {
	            // Creating a statement object
	            Statement statement = connection.createStatement();
	            // Executing the SQL query
	            String sqlQuery = "SELECT RATE_CARD_ID FROM MFS_RATE_CARD_MAST WHERE RATE_CARD_NAME = ' " + Name + "' ";
	            ResultSet resultSet = statement.executeQuery(sqlQuery);
	            // Check if any results exist
	            if (resultSet.next()) {
	                int rateCardId = resultSet.getInt("RATE_CARD_ID");
	                System.out.println("RATE_CARD_ID: " + rateCardId);
	            } else {
	                System.out.println("No data found for RATE_CARD_NAME = ' " + Name + " '");
	            }

	            // Close resources
	            resultSet.close();
	            statement.close();
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		  }
		  
	@AfterClass
		  public void logOut() {
		    driver.quit();
		  }
		}
