package bmp_react;


	import java.sql.Connection;
	import java.sql.Driver;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
	 
	import org.openqa.selenium.support.ui.WebDriverWait;
	import java.time.Duration;
	 
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	 
	import io.github.bonigarcia.wdm.WebDriverManager;
	 
	public class xavier {
		private static final String DB_URL = "jdbc:mysql://10.0.1.210:3306/BMPDB";
	    private static final String DB_USER = "bmp";
	    private static final String DB_PASSWORD = "bmp@Tayana123";
	    String result;
	    String failureReason = "";
	    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    String product = "BMP";
		WebDriver driver = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
	 
		public static String browser = "Firefox";
		@BeforeClass
		public void login() throws InterruptedException
		{
			System.out.println(" Test NG project ");
	 
		        
	 
		        if (browser.equals("Firefox")) {
		            WebDriverManager.firefoxdriver().setup();
		            FirefoxOptions options = new FirefoxOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new FirefoxDriver(options);  // Use the options when creating the driver
		        } else if (browser.equals("Chrome")) {
		            WebDriverManager.chromedriver().setup();
		            ChromeOptions options = new ChromeOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new ChromeDriver(options);  // Use the options when creating the driver
		        } else {
		            WebDriverManager.edgedriver().setup();
		            EdgeOptions options = new EdgeOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new EdgeDriver(options);  // Use the options when creating the driver
		        }
		        // Now you can proceed with your test automation script
		        driver.get("https://10.0.6.137:8443/bmp");
		        driver.manage().window().maximize();
		        Thread.sleep(4000);
		        driver.navigate().refresh();
		        Thread.sleep(2000);
		        driver.navigate().refresh();
		        Thread.sleep(2000);
				 driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).sendKeys("admin");
			     driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys("Admin123");
		         driver.findElement(By.xpath("//button[@type='submit']")).click();
		        
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
		
		//======================================================================================================================================
		@Test (priority = 1 , enabled = true)
		public void cltdel() throws InterruptedException
		{
			
		        Thread.sleep(4000);
		        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[3]/a")).click();
		        Thread.sleep(4000);
		        
		        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/input[1]")).sendKeys("Xavier");
		        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[9]/div[1]/*[name()='svg'][2]/*[name()='path'][1]")).click();
		        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")).click();
		        
		        
		        Thread.sleep(5000);
				// Locate the element
				WebElement alertElement = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

				// Check if the element is displayed
				boolean deliveredDisplayed = alertElement.isDisplayed();
				 String alertText = alertElement.getText();
				 
				 String expectedOutput = "Client Deletion Successful";
					Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
					Thread.sleep(2000);

				if (deliveredDisplayed) {
				    // Extract and print the text
				   // String alertText = alertElement.getText();
				    System.out.println("Alert Text: " + alertText);
				} else {
				    System.out.println("The alert element is not displayed.");
				}
		}
		//======================================================================================================================================
		@Test (priority = 2, enabled = true)
		public void cltcreate() throws InterruptedException
		{
			
			Thread.sleep(4000);
			driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[3]/a")).click();
			Thread.sleep(2000);
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
	        
	        
	        Thread.sleep(6000);
			// Locate the element
			WebElement alertElement = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			 String alertText = alertElement.getText();
			 
			 String expectedOutput = "Client Creation Successful,Pack Purchase Success";
				Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

			if (deliveredDisplayed) {
			    // Extract and print the text
			   // String alertText = alertElement.getText();
			    System.out.println("cltcreate Alert Text: " + alertText);
			} else {
			    System.out.println("The alert element is not displayed.");
			}
	        
	       
	        Thread.sleep(2000);
	   
	       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='DSRSupportPanel']//div[@class='card-header tss-panel-header tss-info-panel']")));
	        element.click();
	        Thread.sleep(2000);
	        element.click();
	       WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
	        WebElement element1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Retry Management']")));
	        element1.click();
	        Thread.sleep(2000);
	       driver.findElement(By.xpath("//div[@id='RetryManageMentPanelBody']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	       driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	       driver.findElement(By.xpath("//div[@id='RetryManageMentPanel']//button[1]")).click();
	       
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	        
	        Thread.sleep(4000);
	        driver.findElement(By.xpath("//a[normalize-space()='SMPP']")).click();
	        
	        driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/input[1]")).sendKeys("Xavier");
	 
//	        
			
		}
		//======================================================================================================================================
		@Test (priority = 3, enabled = true)
		public void barsys() throws InterruptedException
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[5]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@type,'checkbox')]")).click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			driver.findElement(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("94716163");
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[5]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Drop')]")).click();
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			 String alertText = alertElement.getText();
			 
			 String expectedOutput = "Addition Successful";
				Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

			if (deliveredDisplayed) {
			    // Extract and print the text
			   // String alertText = alertElement.getText();
			    System.out.println("barsys Alert Text: " + alertText);
			} else {
			    System.out.println("The alert element is not displayed.");
			}

		
			
		}
		//======================================================================================================================================
		@Test (priority = 4 , enabled = true)
		public void barclt() throws InterruptedException
		{
			driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			driver.findElement(By.xpath("//a[@title='Client']")).click();
			driver.findElement(By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("(//i[contains(@class,'')])[33]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]//*[name()='svg']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[@title='Akshay1234']")).click();
			driver.findElement(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("93456123");
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			 String alertText = alertElement.getText();
			 
			 String expectedOutput = "Addition Successful";
				Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

			if (deliveredDisplayed) {
			
			    System.out.println("barclt Alert Text: " + alertText);
			} else {
			    System.out.println("The alert element is not displayed.");
			}	
			
		}
		//======================================================================================================================================
		@Test (priority = 5 , enabled = true)
		public void redrtclt() throws InterruptedException
		{
			driver.findElement(By.xpath("(//i[@title='Site Map'])[1]")).click();
			driver.findElement(By.xpath("(//a[contains(@class,'tss-paragraph')])[17]")).click();
			driver.findElement(By.xpath("//div[contains(@class,'button-container')]//*[name()='svg']")).click();
			driver.findElement(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("92251523");
			driver.findElement(By.xpath("//div[5]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Tayana123')]")).click();
			driver.findElement(By.xpath("//div[6]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[@title='Disable']")).click();
			driver.findElement(By.xpath("//div[contains(@class,'tss-tab-body')]//section[contains(@class,'content')]//button[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			//this is client redirection
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[contains(@title,'Client')]")).click();
			driver.findElement(By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")).click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[contains(@class,'content')]//section[contains(@class,'content')]//div[contains(@class,'row')]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Akshay1234')]")).click();
			driver.findElement(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("92456422");
			driver.findElement(By.xpath("//div[6]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Tayana123')]")).click();
			driver.findElement(By.xpath("//div[7]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Disable')]")).click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			
			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			 String alertText = alertElement.getText();
			 
			 String expectedOutput = "Addition Successful";
				Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

			if (deliveredDisplayed) {
			 
			    System.out.println("redrtclt Alert Text: " + alertText);
			} else {
			    System.out.println("The alert element is not displayed.");
			}

			
		}
		//======================================================================================================================================
		@Test (priority = 6 , enabled = true)
		public void packageclt() throws InterruptedException
		{
			Thread.sleep(4000);
			//site map
			driver.findElement(By.xpath("(//i[contains(@title,'Site Map')])[1]")).click();
			driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")).click();
			Thread.sleep(2000);
			//rate plan pg
			driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[5]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("rateauto5");
			
			driver.findElement(By.xpath("//fieldset[1]//div[1]//i[1]")).click();
			
			driver.findElement(By.xpath("//li[@title='PayG']")).click();
			Thread.sleep(2000);
			WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
		        WebElement descelement = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
		        descelement.sendKeys("rateauto1");
			
			driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
			driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
			driver.findElement(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("10000");
			driver.findElement(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("2000");
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			
			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			 String alertText = alertElement.getText();
			 
			 String expectedOutput = "Addition Successful";
				Assert.assertTrue(alertText.contains(expectedOutput), "addition was failed");
				Thread.sleep(2000);

			if (deliveredDisplayed) {
		
			    System.out.println("packageclt Alert Text: " + alertText);
			} else {
			    System.out.println("The alert element is not displayed.");
			}
			Thread.sleep(4000);
			
			
			
			//site map
			driver.findElement(By.xpath("(//i[contains(@title,'Site Map')])[1]")).click();
			driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")).click();
			Thread.sleep(2000);
			//rate plan
			driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[5]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("rateauto34");
			
			driver.findElement(By.xpath("//fieldset[1]//div[1]//i[1]")).click();
			
			driver.findElement(By.xpath("//li[@title='PayG']")).click();
			Thread.sleep(2000);
			WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
		        WebElement desc1element = wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
		        desc1element.sendKeys("rateauto1");
			
			driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
			driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
			driver.findElement(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("10000");
			driver.findElement(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("2000");
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
			Thread.sleep(4000);
			
			
			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement1 = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

			// Check if the element is displayed
			boolean deliveredDisplayed1 = alertElement1.isDisplayed();
			 String alertText1 = alertElement1.getText();
			 
			 String expectedOutput1 = "Addition Successful";
				Assert.assertTrue(alertText1.contains(expectedOutput1), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

			if (deliveredDisplayed1) {
		
			    System.out.println("packageclt Alert Text: " + alertText);
			} else {
			    System.out.println("The alert element is not displayed.");
			}
		
			
			//package
			driver.findElement(By.xpath("//a[@title='Package']")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[7]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]")).sendKeys("Packageauto1");
			driver.findElement(By.xpath("//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//li[contains(@title,'Postpaid')]")).click();
			driver.findElement(By.xpath("//div[3]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//li[contains(@title,'Base')]")).click();
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
	        WebElement descelement1 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
	        descelement1.sendKeys("packageauto");
			driver.findElement(By.xpath("//div[5]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//li[contains(@title,'rateauto')]")).click();
			driver.findElement(By.xpath("//div[6]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("1000");
			driver.findElement(By.xpath("//div[7]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'payg_profile')]")).click();
			driver.findElement(By.xpath("//div[8]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("2000");
			driver.findElement(By.xpath("//div[9]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("2000");
			driver.findElement(By.xpath("//div[10]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("2000");
			driver.findElement(By.xpath("//div[11]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("2000");
			driver.findElement(By.xpath("//div[12]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("2");
			driver.findElement(By.xpath("//div[13]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("499");
			driver.findElement(By.xpath("//div[14]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("5");
			driver.findElement(By.xpath("//div[15]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("5");
			driver.findElement(By.xpath("//div[16]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("23");
			driver.findElement(By.xpath("//div[17]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("24");
			
			WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
			WebElement cldr = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[20]//div[1]//fieldset[1]//div[1]//i[1]")));
					cldr.click();
			driver.findElement(By.xpath("//button[normalize-space()='2025']")).click();
			driver.findElement(By.xpath("//span[normalize-space()='2029']")).click();
			driver.findElement(By.xpath("//span[normalize-space()='Jan']")).click();
			driver.findElement(By.xpath("//span[contains(@aria-disabled,'false')][normalize-space()='1']")).click();
			driver.findElement(By.xpath("//div[21]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Disable')]")).click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();

			
			
			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement2 = driver.findElement(By.xpath("//h2[@id='swal2-title']"));

			// Check if the element is displayed
			boolean deliveredDisplayed2 = alertElement2.isDisplayed();
			 String alertText2 = alertElement2.getText();
			 
			 String expectedOutput2 = "Addition Successful";
				Assert.assertTrue(alertText2.contains(expectedOutput2), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

			if (deliveredDisplayed2) {
			
			    System.out.println("packageclt Alert Text: " + alertText1);
			} else {
			    System.out.println("The alert element is not displayed.");
			}
	}
		
		
		
	}

