package bmp_react;




	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.sql.*;
	import java.time.LocalDateTime;
	import java.time.LocalTime;
	import java.time.format.DateTimeFormatter;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import java.util.Date;
	import javax.mail.Authenticator;
	import javax.mail.Flags;
	import javax.mail.Folder;
	import javax.mail.Message;
	import javax.mail.Multipart;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Store;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;

	//import org.apache.hc.core5.http.Message;
	import org.openqa.selenium.*;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import java.io.File;
	import java.sql.Connection;
	import java.sql.Driver;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.text.SimpleDateFormat;
	import java.time.Duration;
	import java.util.NoSuchElementException;
	import java.util.Properties;

	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.testng.Assert;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;
	//import jakarta.mail.Authenticator;
	//import jakarta.mail.PasswordAuthentication;
	//import jakarta.mail.Session;
	//import jakarta.mail.Transport;
	//import jakarta.mail.internet.InternetAddress;
	//import jakarta.mail.internet.MimeBodyPart;
	import javax.mail.Multipart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;


	public class utprc6wtsap {
		
		
		private int rowsAffected = 0 ;
		 
	    String result;
	    String failureReason = "";
	    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    String product = "BMP";
	    
		public static WebDriver driver ;
		public WebDriverWait wait ;
		public static String browser = "Firefox";
		
		 @BeforeMethod
		    public void waiting() {
		        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after driver
		    }
		
		 public static void highlightElement(WebElement element) {
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    @SuppressWarnings("deprecation")
				String originalStyle = element.getAttribute("style");
			    js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red; background: yellow;');", element);
			    try {
			        Thread.sleep(500); // Highlight for visibility
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
			}

		@BeforeClass (enabled=true)
		public void login() throws InterruptedException {
			
			 System.out.println("Initializing WebDriver for Admin GUI");

		        if (browser.equalsIgnoreCase("Firefox")) {
		            WebDriverManager.firefoxdriver().setup();
		            FirefoxOptions options = new FirefoxOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new FirefoxDriver(options);
		        } else if (browser.equalsIgnoreCase("Chrome")) {
		            WebDriverManager.chromedriver().setup();
		            ChromeOptions options = new ChromeOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new ChromeDriver(options);
		        } else {
		            WebDriverManager.edgedriver().setup();
		            EdgeOptions options = new EdgeOptions();
		            options.setAcceptInsecureCerts(true);
		            options.setPageLoadTimeout(Duration.ofMinutes(1));
		            driver = new EdgeDriver(options);
		        }

		        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		        // Launch Admin GUI
		        driver.get("https://" + dataproviderrc6.host + ":" + dataproviderrc6.port + "/bmp/");
		        driver.manage().window().maximize();
		        System.out.println("Admin GUI Launched");
		    
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).sendKeys(dataproviderrc6.admin);
			driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys(dataproviderrc6.adminpasss);
			driver.findElement(By.xpath("//button[@type='submit']")).click();

		}

	public void protallogin() throws InterruptedException {
			
		    System.out.println("portal login launch");
		    
		    if (browser.equalsIgnoreCase("Firefox")) {
	            WebDriverManager.firefoxdriver().setup();
	            FirefoxOptions options = new FirefoxOptions();
	            options.setAcceptInsecureCerts(true);
	            options.setPageLoadTimeout(Duration.ofMinutes(1));
	            driver = new FirefoxDriver(options);
	        } else if (browser.equalsIgnoreCase("Chrome")) {
	            WebDriverManager.chromedriver().setup();
	            ChromeOptions options = new ChromeOptions();
	            options.setAcceptInsecureCerts(true);
	            options.setPageLoadTimeout(Duration.ofMinutes(1));
	            driver = new ChromeDriver(options);
	        } else {
	            WebDriverManager.edgedriver().setup();
	            EdgeOptions options = new EdgeOptions();
	            options.setAcceptInsecureCerts(true);
	            options.setPageLoadTimeout(Duration.ofMinutes(1));
	            driver = new EdgeDriver(options);
	        }

	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.get("https://" + dataproviderrc6.host + ":" + dataproviderrc6.portalport + "/bmpportal/");
		   // driver.get("https://10.0.6.137:3000/bmpportal/");
	        System.out.println("Portal GUI Launched");


		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[1]/div/div/input"))
				.sendKeys(dataproviderrc6.portalusername); // entering the username
		driver.findElement(
				By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[2]/div/div/input"))
				.sendKeys(dataproviderrc6.portalpass);// entering the password
		driver.findElement(
				By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button/span[1]"))
				.click();// performing the click action
		try {
			String otp = authlogic.multi_factor_auth();
			WebElement otptext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));otptext.sendKeys(otp);
			
			driver.findElement(By.xpath("//span[@class='indicator-label']")).click();
		 } catch (TimeoutException e) {
		        System.out.println("No element present.");
		    } catch (NoSuchElementException e) {
		        System.out.println("Element not found.");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
		
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
		// driver.findElement(By.id(browser)
		Thread.sleep(3000);
		}


	public void Dbconnect(String testName, String result, String failureReason, String timestamp, String product) {
		
	    try (Connection connection = DriverManager.getConnection(
	    		dataproviderrc6.DB_URL + "?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", 
	    		dataproviderrc6.DB_USER, 
	    		dataproviderrc6.DB_PASSWORD)) 
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
	            rowsAffected += preparedStatement.executeUpdate();
	          
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	  
	}



		
		// ======================================================================================================================================
		@Test(priority = 1, enabled = true)
		public void client_deletion() throws InterruptedException {
			

			Thread.sleep(4000);
			WebElement cltElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[3]/a")));
			highlightElement(cltElement);
			cltElement.click();
			//Thread.sleep(4000);
			WebElement serchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/input[1]")));
			highlightElement(serchElement);	
			serchElement.sendKeys(dataproviderrc6.username);
			//Thread.sleep(2000);
			try {
			WebElement click_screch_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[9]/div[1]/*[name()='svg'][2]/*[name()='path'][1]")));		
			highlightElement(click_screch_Element);
			click_screch_Element.click();
			 System.out.println("User deleted successfully.");
			
		
		
			
			//Thread.sleep(2000);
			WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")));
	         highlightElement(button);
			button.click();

			 } catch (TimeoutException e) {
			        System.out.println("No user present.");
			    } catch (NoSuchElementException e) {
			        System.out.println("Element not found.");
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			
			Thread.sleep(5000);
			
				WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Client Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("cltdel Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}
		}
		
		//============================================================================================
		
				@Test(priority = 7, enabled = false)
				public void package_delete_client() throws InterruptedException {
					WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@title,'Site Map')])[1]")));highlightElement(element0);element0.click();
				//	Thread.sleep(2000);
					WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")));highlightElement(element1);element1.click();
				//	Thread.sleep(2000);
					WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Package']")));highlightElement(element2);element2.click();
				//	Thread.sleep(4000);
					WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='p-inputtext p-component']")));highlightElement(element3);element3.sendKeys(dataproviderrc6.packagename);
					//Thread.sleep(2000);
					WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[7]/div[1]//*[name()='svg'][2]/*[name()='path'][1]")));highlightElement(element4);element4.click();
					//Thread.sleep(2000);
					try {
					WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Delete']")));highlightElement(element5);element5.click();

					 } catch (TimeoutException e) {
					        System.out.println("No package present.");
					    } catch (NoSuchElementException e) {
					        System.out.println("Element not found.");
					    } catch (Exception e) {
					        e.printStackTrace();
					    }
					Thread.sleep(6000);
					// Locate the element
					WebElement alertElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

					// Check if the element is displayed
					boolean deliveredDisplayed = alertElement.isDisplayed();
					String alertText = alertElement.getText();

					String expectedOutput = "Deletion Successful";
					Assert.assertTrue(alertText.contains(expectedOutput), "Addition was failed");
					Thread.sleep(2000);

					if (deliveredDisplayed) {

						System.out.println("packagedelclt Alert Text: " + alertText);
					} else {
						System.out.println("The alert element is not displayed.");
					}
					
					
				}
		
		// ======================================================================================================================================
		@Test(priority = 3, enabled = true)
		public void rate_delete_client_point() throws InterruptedException {
		
		//	Thread.sleep(4000);
			// site map
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@title,'Site Map')])[1]")));highlightElement(element0);element0.click();
		//	Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")));highlightElement(element1);element1.click();
		//	Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='p-inputtext p-component']")));highlightElement(element2);element2.sendKeys(dataproviderrc6.rateusername1);
		//	Thread.sleep(2000);
			
			try {
				WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='path'][@fill='currentColor'])[19]")));highlightElement(element3);element3.click();

				 } catch (TimeoutException e) {
				        System.out.println("No package present.");
				    } catch (NoSuchElementException e) {
				        System.out.println("Element not found.");
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
			
			//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element4);element4.click();

			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Deletion was failed");
			Thread.sleep(2000);

			if (deliveredDisplayed) {

				System.out.println("ratedelcltpoint Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}
			
		}	
		
		// ======================================================================================================================================
		@Test(priority = 4, enabled = true)
		public void rate_delete_client_pay() throws InterruptedException {
		
			//Thread.sleep(4000);
			// site map
			
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@title,'Site Map')])[1]")));highlightElement(element0);element0.click();
		//	Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")));highlightElement(element1);element1.click();
		//Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='p-inputtext p-component']")));highlightElement(element2);element2.sendKeys(dataproviderrc6.rateusername);
		//	Thread.sleep(2000);
		
			try {
				WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='path'][@fill='currentColor'])[19]")));highlightElement(element3);element3.click();

				 } catch (TimeoutException e) {
				        System.out.println("No package present.");
				    } catch (NoSuchElementException e) {
				        System.out.println("Element not found.");
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
			
			//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element4);element4.click();

			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Deletion was failed");
			Thread.sleep(2000);

			if (deliveredDisplayed) {

				System.out.println("ratedelcltpay Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}
			
		}
		// ======================================================================================================================================
			@Test(priority = 5, enabled = true)
			public void rate_client_pay() throws InterruptedException {
				
				Thread.sleep(4000);
				// site map
				WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@title,'Site Map')])[1]")));highlightElement(element0);element0.click();
				Thread.sleep(2000);
				WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")));highlightElement(element1);element1.click();
				Thread.sleep(2000);
				// rate plan pg
				WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[5]")));highlightElement(element2);element2.click();
				Thread.sleep(2000);
				WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element3);element3.sendKeys(dataproviderrc6.rateusername);
				Thread.sleep(2000);
				WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[1]//div[1]//i[1]")));highlightElement(element4);element4.click();
				Thread.sleep(2000);
				WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='PayG']")));highlightElement(element5);element5.click();
				Thread.sleep(2000);
			//	WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
				WebElement descelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
				highlightElement(descelement);
				descelement.sendKeys(dataproviderrc6.rateusername);

				WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));highlightElement(element6);element6.click();
				Thread.sleep(2000);
				WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[1]")));highlightElement(element7);element7.click();
				Thread.sleep(2000);
				WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element8);element8.sendKeys("1");
				Thread.sleep(2000);
				//WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")));highlightElement(element9);element9.sendKeys("2");
				WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));highlightElement(element9);element9.sendKeys("2");
				Thread.sleep(2000);
				
				WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element10);element10.click();
				Thread.sleep(2000);

				driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

				Thread.sleep(6000);
				// Locate the element
				WebElement alertElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

				// Check if the element is displayed
				boolean deliveredDisplayed = alertElement.isDisplayed();
				String alertText = alertElement.getText();

				String expectedOutput = "Addition Successful";
				Assert.assertTrue(alertText.contains(expectedOutput), "Addition was failed");
				Thread.sleep(2000);

				if (deliveredDisplayed) {

					System.out.println("ratecltpay Alert Text: " + alertText);
				} else {
					System.out.println("The alert element is not displayed.");
				}
				Thread.sleep(4000);
			}
			
			
		
		// ======================================================================================================================================
				@Test(priority = 6, enabled = true)
				public void rate_client_point() throws InterruptedException {
					

				// site map
					WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@title,'Site Map')])[1]")));highlightElement(element0);element0.click();
				Thread.sleep(2000);
				WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")));highlightElement(element1);element1.click();
				Thread.sleep(2000);
				// rate plan
				WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[5]")));highlightElement(element2);element2.click();
				Thread.sleep(2000);
				WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element3);element3.sendKeys(dataproviderrc6.rateusername1);
				Thread.sleep(2000);
				WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[1]//div[1]//i[1]")));highlightElement(element4);element4.click();
				Thread.sleep(2000);
				WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Points']")));highlightElement(element5);element5.click();
				Thread.sleep(2000);
				//WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
				WebElement desc1element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
				highlightElement(desc1element);
				desc1element.sendKeys(dataproviderrc6.rateusername1);

				WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[2]")));highlightElement(element6);element6.click();
				Thread.sleep(2000);
				WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[1]")));highlightElement(element7);element7.click();
				Thread.sleep(2000);
				WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element8);element8.sendKeys("1");
				Thread.sleep(2000);
				WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));highlightElement(element9);element9.sendKeys("2");
				Thread.sleep(2000);
				WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element10);element10.click();
				Thread.sleep(2000);

				WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element11);element11.click();
				Thread.sleep(4000);

				Thread.sleep(6000);
				// Locate the element
				WebElement alertElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

				// Check if the element is displayed
				boolean deliveredDisplayed1 = alertElement1.isDisplayed();
				String alertText1 = alertElement1.getText().trim();

				String expectedOutput1 = "Addition Successful";
				Assert.assertTrue(alertText1.contains(expectedOutput1), "Addition unsuccessful");
				Thread.sleep(2000);

				if (deliveredDisplayed1) {

					System.out.println("ratecltpoint Alert Text: " + alertText1);
				} else {
					System.out.println("The alert element is not displayed.");
				}
		}

				// ======================================================================================================================================
						@Test(priority = 8, enabled = false)
						public void package_client() throws InterruptedException {
							WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@title,'Site Map')])[1]")));highlightElement(element0);element0.click();
				Thread.sleep(2000);
				WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div/div[@class='row']/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]")));highlightElement(element1);element1.click();
				Thread.sleep(2000);
				// package
				WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Package']")));highlightElement(element2);element2.click();
				Thread.sleep(4000);
				WebElement addbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));
				addbutt.click();
				Thread.sleep(2000);
				WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]")));highlightElement(element3);element3.sendKeys(dataproviderrc6.packagename);
				WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element4);element4.click();
				Thread.sleep(1000);
				WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Postpaid')]")));highlightElement(element5);element5.click();
				WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element6);element6.click();
				Thread.sleep(1000);
				WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Base')]")));highlightElement(element7);element7.click();
				//WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
				WebElement descelement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));
				descelement1.sendKeys(dataproviderrc6.packagename);
				WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element8);element8.click();
				Thread.sleep(1000);
				WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='rateauto6']")));highlightElement(element9);element9.click();
				Thread.sleep(2000);
				WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[6]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")));highlightElement(element10);element10.sendKeys("1000");
				Thread.sleep(2000);
				WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element11);element11.click();
				Thread.sleep(2000);
				WebElement element12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'payg_profile')]")));highlightElement(element12);element12.click();
				Thread.sleep(2000);
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

				//WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
				WebElement cldr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[20]//div[1]//fieldset[1]//div[1]//i[1]")));
				cldr.click();
				driver.findElement(By.xpath("//button[normalize-space()='2025']")).click();
				driver.findElement(By.xpath("//span[normalize-space()='2029']")).click();
				driver.findElement(By.xpath("//span[normalize-space()='Jan']")).click();
				driver.findElement(By.xpath("//span[contains(@aria-disabled,'false')][normalize-space()='1']")).click();
				driver.findElement(By.xpath("//div[21]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
				driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
				driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
				driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();

				Thread.sleep(4000);
				// Locate the element
				WebElement alertElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

				// Check if the element is displayed
				boolean deliveredDisplayed2 = alertElement2.isDisplayed();
				String alertText2 = alertElement2.getText();

				String expectedOutput2 = "Addition Successful";
				Assert.assertTrue(alertText2.contains(expectedOutput2), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

				if (deliveredDisplayed2) {

					System.out.println("packageclt Alert Text: " + alertText2);
				} else {
					System.out.println("The alert element is not displayed.");
				}
			}

		// ======================================================================================================================================
		@Test(priority = 2, enabled = true)
		public void client_creatation() throws InterruptedException {
			

			//Thread.sleep(4000);
			WebElement button0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));
			highlightElement(button0);
			button0.click();
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[3]/a")).click();
			//Thread.sleep(2000);
			WebElement button1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/*[name()='svg'][1]")));
			highlightElement(button1);	
			button1.click();
			driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys(dataproviderrc6.username);
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys(dataproviderrc6.password);

			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[4]/div/fieldset/div[1]/div/input")).sendKeys("10");
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[9]/div/fieldset/div[1]/div/input")).sendKeys("xavier.t@tayana.in");
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[contains(@title,'t1237')]")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[12]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Postpaid')]")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[13]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("12345678");
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[14]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("9876");
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[15]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Disable')]")).click();
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
			//driver.findElement(By.xpath("//li[contains(@title,'" + dataproviderrc6.packagename + "')]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'NewTestBasePack')]")).click();
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[33]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
			driver.findElement(By.xpath("//li[contains(@title,'Disable')]")).click();

			
			driver.findElement(By.xpath(
					"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[3]/div/fieldset/div[1]/div/input"))
					.sendKeys("1");
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/button[1]"))
					.click();
			Thread.sleep(2000);
			WebElement button2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")));
			highlightElement(button2);
			button2.click();

			Thread.sleep(6000);
			
			//WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10)); 
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

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

		//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='DSRSupportPanel']//div[@class='card-header tss-panel-header tss-info-panel']")));
			element.click();
			Thread.sleep(2000);
			element.click();
		//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds
			WebElement element1 = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Retry Management']")));
			element1.click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//div[@id='RetryManageMentPanelBody']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]"))
					.click();
			driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
			driver.findElement(By.xpath("//div[@id='RetryManageMentPanel']//button[1]")).click();

			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();

			Thread.sleep(4000);
			driver.findElement(By.xpath("//a[normalize-space()='SMPP']")).click();

			driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();
			//Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/input[1]")));
			element2.sendKeys("Xavier");
		}

	// ============================================================================================================================     



		@Test(priority = 9, enabled = true)
		public void delete_barring_system_destination() throws InterruptedException {
			
		//	Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));
			highlightElement(element0);
			element0.click();
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='tss-paragraph'])[16]")));
			highlightElement(element1);
			element1.click();
			//Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));
			highlightElement(element3);
			element3.click(); // edit button

			//Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));
			highlightElement(element4);
			element4.click();
			//Thread.sleep(2000);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));
			highlightElement(element5);
			element5.click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();

			WebElement textElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));
			highlightElement(textElement);		
			textElement.sendKeys(dataproviderrc6.barrednumber);
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click(); // search button
			Thread.sleep(2000);
			WebElement del = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//tbody/tr[contains(@role,'row')]/td//*[name()='svg'][2]/*[name()='path'][1]"))));
			highlightElement(del);		
			del.click(); // delete button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(6000);
			// Locate the element
		//	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10)); 
			WebElement alertElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("delbarsysdest Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 10, enabled = true)
		public void barred_system_destination() throws InterruptedException {
			
			//Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='tss-paragraph'])[16]")));highlightElement(element1);element1.click();
			
			//Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[5]")));highlightElement(element2);element2.click();
			
			//Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element3);element3.click();
			//Thread.sleep(2000);
			
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element4);element4.click();
			
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element5);element5.click();
			
			//Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element6);element6.sendKeys(dataproviderrc6.barrednumber);
			
			//Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[5]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element7);element7.click();
			
			driver.findElement(By.xpath("//li[contains(@title,'Drop')]")).click();
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
		//	Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element8);element8.click();
			
			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();
			System.out.println(alertText);
			String expectedOutput = "Addition Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("barsysdest Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}
		// ======================================================================================================================================

		@Test(priority = 11, enabled = true)
		public void barred_system_srource() throws InterruptedException {
			
			//Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			//Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='tss-paragraph'])[16]")));highlightElement(element1);element1.click();
			
			//Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[5]")));highlightElement(element2);element2.click();
			
			//Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element3);element3.click(); // down
																															// arrow
			//Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));element4.click();
			
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));element5.click(); // proceed button
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));element6.sendKeys(dataproviderrc6.barrednumber);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));element7.click();
		//	Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Enable')]")));element8.click();

			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")));element9.sendKeys("this is sourceing barring");
			WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row tss-pull-right']//button[1]")));element10.click(); // add button
			//Thread.sleep(2000);
			WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));element11.click();

		//	Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText().trim();

			String expectedOutput = "Addition Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("barsyssrc Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================

		@Test(priority = 12, enabled = true)
		public void delete_barred_system_source() throws InterruptedException {
			
			Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='tss-paragraph'])[16]")));
			highlightElement(element1);
			element1.click();
			//Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));
			highlightElement(element2);
			element2.click();

			//Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element3);element3.click(); // down
																															// arrow
			//Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element4);element4.click();
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element5);element5.click(); // proceed button
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element6);element6.sendKeys(dataproviderrc6.barrednumber);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element7);element7.click();
		//	Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Enable')]")));highlightElement(element8);element8.click();
			//Thread.sleep(2000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row tss-pull-right']//button[1]")));highlightElement(element9);element9.click(); // search button
			//Thread.sleep(2000);
			WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@role,'row')]/td//*[name()='svg'][2]/*[name()='path'][1]")));highlightElement(element10);element10.click(); // delete button
			//Thread.sleep(2000);
			WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element11);element11.click();

			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("delbarsyssrc Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 13, enabled = true)
		public void barrred_client_source() throws InterruptedException {
			
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='tss-paragraph'])[16]")));highlightElement(element1);element1.click();
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Client']")));highlightElement(element2);element2.click();
			WebElement addbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")));
			addbutton.click(); // add button
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@class,'')])[33]")));highlightElement(element3);element3.click();
		//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")));highlightElement(element4);element4.click();
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]//*[name()='svg']")));highlightElement(element5);element5.click();
			//Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element6);element6.click();
			driver.findElement(By.xpath("//li[@title='Akshay1234']")).click();
			driver.findElement(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys(dataproviderrc6.barrednumber);
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();
			Thread.sleep(1000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element7);element7.click();

			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Addition Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {

				System.out.println("barcltsrc Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 14, enabled = true)
		public void barred_client_destination() throws InterruptedException {
			
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			driver.findElement(By.xpath("//a[@title='Client']")).click(); // click on the client tab
			driver.findElement(By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")).click(); // proceed button

			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element1);element1.click();
		//	Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element2);element2.click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element3);element3.click();
		//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Akshay1234']")));highlightElement(element4);element4.click();
			driver.findElement(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys(dataproviderrc6.barrednumber);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row tss-pull-right']//button[1]")));highlightElement(element5);element5.click();
		//	Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element6);element6.click();

			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();
			System.out.println(alertText);
			String expectedOutput = "Addition Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {

				System.out.println("barcltdest Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 15, enabled = true)
		public void delete_barred_client_destination() throws InterruptedException {
			
			Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			driver.findElement(By.xpath("//a[@title='Client']")).click(); // click on the client tab
			Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));highlightElement(element1);element1.click(); // edit button
		//	Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element2);element2.click();
		//	Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element3);element3.click();
		//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element4);element4.click();
		//	Thread.sleep(2000);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element5);element5.click();
		//	Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Akshay1234']")));highlightElement(element6);element6.click();
		//	Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element7);element7.sendKeys(dataproviderrc6.barrednumber);
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click(); // search button
		//	Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@role,'row')]/td//*[name()='svg'][2]/*[name()='path'][1]")));highlightElement(element8);element8.click(); // delete button
		//	Thread.sleep(2000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element9);element9.click();

			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("delbarcltdest Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}
		// ======================================================================================================================================

		@Test(priority = 16, enabled = true)
		public void delete_barred_client_source() throws InterruptedException {
			
			Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Client']")));highlightElement(element1);element1.click(); // click on the client tab
		//	Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));highlightElement(element2);element2.click(); // edit button

			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@class,'')])[33]")));highlightElement(element3);element3.click();
		//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")));highlightElement(element4);element4.click();

		//	Thread.sleep(2000);
		
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element5);element5.click();
		//	Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element6);element6.click();
		//	Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Akshay1234']")));highlightElement(element7);element7.click();

			driver.findElement(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys(dataproviderrc6.barrednumber);
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click(); // search button
			//Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@role,'row')]/td//*[name()='svg'][2]/*[name()='path'][1]")));highlightElement(element8);element8.click(); // delete button
		//	Thread.sleep(2000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element9);element9.click();

			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("delbarcltsrc Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 17, enabled = true)
		public void barred_client_combination() throws InterruptedException {
			
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			driver.findElement(By.xpath("//a[@title='Client']")).click(); // click on the client tab
		//	Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")));highlightElement(element1);element1.click(); // add button

			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
		//	Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element1);element2.click();
		//	Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element3);element3.click();
		//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element4);element4.click();
			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
			driver.findElement(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
			Thread.sleep(2000);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Akshay1234']")));highlightElement(element5);element5.click();
			driver.findElement(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys(dataproviderrc6.barrednumber);
			driver.findElement(By.xpath("//div[6]//div[1]//div[1]//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys(dataproviderrc6.barnumberdest);
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/div[9]/div[1]/fieldset[1]/input[1]")).sendKeys("8");
			driver.findElement(By.xpath("//div[@class='row tss-pull-right']//button[1]")).click();

			Thread.sleep(1000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element6);element6.click();

			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText().trim();
			System.out.println(alertText);
			String expectedOutput = "Addition Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {

				System.out.println("barcltcomb Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 18, enabled = true)
		public void delete_barred_client_combination() throws InterruptedException {
			
			Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			driver.findElement(By.xpath("(//a[@class='tss-paragraph'])[16]")).click();
			driver.findElement(By.xpath("//a[@title='Client']")).click(); // click on the client tab
//			Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));highlightElement(element1);element1.click(); // edit button

			driver.findElement(By.xpath("(//i[contains(@class,'')])[33]")).click(); // src drop down
//			Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")));highlightElement(element2);element2.click(); // check box

//			Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element3);element3.click(); // dest drop down
//			Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element4);element4.click(); // check box

//			Thread.sleep(2000);

			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element5);element5.click();
//			Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element6);element6.click();
//			Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Akshay1234']")));highlightElement(element7);element7.click();
//			Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element8);element8.sendKeys(dataproviderrc6.barrednumber);
//			Thread.sleep(2000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")));highlightElement(element9);element9.sendKeys(dataproviderrc6.barnumberdest);
		//	Thread.sleep(2000);
			WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row tss-pull-right']//button[1]")));highlightElement(element10);element10.click(); // search button
		//	Thread.sleep(2000);
			WebElement del = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@role,'row')]/td//*[name()='svg'][2]/*[name()='path'][1]")));
	        del.click(); // delete button
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='swal2-title']")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("delbarcltcomb Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 19, enabled = true)
		public void barred_system_combination() throws InterruptedException {
			
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
		//	Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='tss-paragraph'])[16]")));highlightElement(element1);element1.click();
		//	Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")));highlightElement(element2);element2.click(); // add button
		//	Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element3);element3.click();
		//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element4);element4.click();
		//	Thread.sleep(2000);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element5);element5.click();
		//	Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element6);element6.click();
		//	Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element7);element7.click();
		//	Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tss-inputGroup']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element8);element8.sendKeys(dataproviderrc6.barrednumber);
		//	Thread.sleep(2000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")));highlightElement(element9);element9.sendKeys(dataproviderrc6.barnumberdest);
		//	Thread.sleep(2000);
			WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/div[4]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")));highlightElement(element10);element10.sendKeys("8");
		//	Thread.sleep(2000);
			WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row tss-pull-right']//button[1]")));highlightElement(element11);element11.click();
		//	Thread.sleep(1000);
			WebElement element12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element12);element12.click();
			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Addition Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {

				System.out.println("barsyscomb Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}
	//===================================================================================================================================================
		@Test(priority = 20, enabled = true)
		public void delete_barred_system_combination() throws InterruptedException {
			
			Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
		//	Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='tss-paragraph'])[16]")));highlightElement(element1);element1.click();
		//	Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));highlightElement(element2);element2.click(); // edit button
		//	Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@class,'')])[33]")));highlightElement(element3);element3.click(); // src drop down
		//	Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")));highlightElement(element4);element4.click(); // check box
		//	Thread.sleep(2000);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//div[2]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element5);element5.click(); // dest drop down
		//	Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@type,'checkbox')]")));highlightElement(element6);element6.click(); // check box
			//Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element7);element7.click();
			//Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element8);element8.sendKeys(dataproviderrc6.barrednumber);
		//	Thread.sleep(2000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[1]//div[1]//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")));highlightElement(element9);element9.sendKeys(dataproviderrc6.barnumberdest);
		//	Thread.sleep(2000);
			WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row tss-pull-right']//button[1]")));highlightElement(element10);element10.click(); // search button
			//Thread.sleep(2000);
			WebElement deletebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@role,'row')]/td//*[name()='svg'][2]/*[name()='path'][1]")));
					deletebutton.click(); // delete button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

			Thread.sleep(6000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Deletion Successful";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("delbarsyscomb Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}

		// ======================================================================================================================================
		@Test(priority = 21, enabled = true)
		public void redirect_system() throws InterruptedException {
			Thread.sleep(2000);
			WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
			//Thread.sleep(2000);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'tss-paragraph')])[17]")));highlightElement(element1);element1.click();
			//Thread.sleep(2000);
			WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'button-container')]//*[name()='svg']")));highlightElement(element2);element2.click();
			//Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element3);element3.sendKeys(dataproviderrc6.redirectnumber);
			//Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element4);element4.click();
			//Thread.sleep(2000);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Tayana123')]")));highlightElement(element5);element5.click();
			//Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[6]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element6);element6.click();
			//Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Disable']")));highlightElement(element7);element7.click();
			//Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'tss-tab-body')]//section[contains(@class,'content')]//button[1]")));highlightElement(element8);element8.click();
			//Thread.sleep(1000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element9);element9.click();
			
			Thread.sleep(4000);
			// Locate the element
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText().trim();
			System.out.println(alertText);
			String expectedOutput = "Addition SuccessFull";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed) {

				System.out.println("redrtsysclt Alert Text: " + alertText);
			} else {
				System.out.println("The alert element is not displayed.");
			}
		}
		// ======================================================================================================================================

			@Test(priority = 22, enabled = true)
			public void redirect_client() throws InterruptedException {
				//Thread.sleep(2000);
				WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
				//Thread.sleep(2000);
				WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'tss-paragraph')])[17]")));highlightElement(element1);element1.click();
			
			// this is client redirection
			//Thread.sleep(1000);
				WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,'Client')]")));highlightElement(element2);element2.click();
			//Thread.sleep(2000);
			WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-container']//*[name()='svg'][1]/*[name()='path'][1]")));highlightElement(element3);element3.click();
			//Thread.sleep(2000);
			WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[contains(@class,'content')]//section[contains(@class,'content')]//div[contains(@class,'row')]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element4);element4.click();
			//Thread.sleep(2000);
			WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Akshay1234')]")));highlightElement(element5);element5.click();
			//Thread.sleep(2000);
			WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element6);element6.sendKeys(dataproviderrc6.redirectnumber);
			//Thread.sleep(2000);
			WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[6]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element7);element7.click();
			//Thread.sleep(2000);
			WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Tayana123')]")));highlightElement(element8);element8.click();
			//Thread.sleep(2000);
			WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));highlightElement(element9);element9.click();
		//	Thread.sleep(2000);
			WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Disable')]")));highlightElement(element10);element10.click();
		//	Thread.sleep(2000);
			WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element11);element11.click();
		//	Thread.sleep(2000);
			WebElement element12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element12);element12.click();
			//Thread.sleep(2000);
			// Locate the element
			WebElement alertElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

			// Check if the element is displayed
			boolean deliveredDisplayed1 = alertElement1.isDisplayed();
			String alertText1 = alertElement1.getText().trim();
			//System.out.println(alertText1);
			String expectedOutput1 = "Addition SuccessFull";
			Assert.assertTrue(alertText1.contains(expectedOutput1), "Error message mismatch for invalid service id");
			Thread.sleep(2000);

			if (deliveredDisplayed1) {

				System.out.println("redrtclt Alert Text: " + alertText1);
			} else {
				System.out.println("The alert element is not displayed.");
			}

		}
		// ======================================================================================================================================
			@Test(priority = 23, enabled = true)
			public void delete_redirect_system_clientt() throws InterruptedException {
				
				WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
				Thread.sleep(2000);
				WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'tss-paragraph')])[17]")));highlightElement(element1);element1.click();
				Thread.sleep(1000);
				WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));highlightElement(element2);element2.click();
				Thread.sleep(2000);
				WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element3);element3.sendKeys(dataproviderrc6.redirectnumber);
				Thread.sleep(2000);
				WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element4);element4.click();
				Thread.sleep(2000);
				WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='path'][@fill='currentColor'])[9]")));highlightElement(element5);element5.click();
				Thread.sleep(1000);
				WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element6);element6.click();
				Thread.sleep(6000);
				// Locate the element
				WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

				// Check if the element is displayed
				boolean deliveredDisplayed = alertElement.isDisplayed();
				String alertText = alertElement.getText();

				String expectedOutput = "Deletion SuccessFully";
				Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
				Thread.sleep(2000);

				if (deliveredDisplayed) {
					// Extract and print the text
					// String alertText = alertElement.getText();
					System.out.println("delredrtsysclt Alert Text: " + alertText);
				} else {
					System.out.println("The alert element is not displayed.");
				}
			}
			// ======================================================================================================================================

			@Test(priority = 24, enabled = true)
			public void delete_redirect_client() throws InterruptedException {
				
				WebElement element0= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@title='Site Map'])[1]")));highlightElement(element0);element0.click();
				Thread.sleep(2000);
				WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'tss-paragraph')])[17]")));highlightElement(element1);element1.click();
				Thread.sleep(1000);
				// this is client redirection
				Thread.sleep(10000);
				WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,'Client')]")));highlightElement(element2);element2.click();
				Thread.sleep(1000);
				WebElement element3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='svg'][@role='img'])[7]")));highlightElement(element3);element3.click();
			
				WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[contains(@class,'content')]//section[contains(@class,'content')]//div[contains(@class,'row')]//div[1]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")));
				highlightElement(element4);
				element4.click();
				Thread.sleep(2000);
				WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@title,'Akshay1234')]")));highlightElement(element5);element5.click();
				Thread.sleep(2000);
				WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")));highlightElement(element6);element6.sendKeys(dataproviderrc6.redirectnumber);
				Thread.sleep(2000);
				WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")));highlightElement(element7);element7.click();
				Thread.sleep(2000);
				WebElement element8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[name()='path'][@fill='currentColor'])[9]")));highlightElement(element8);element8.click();
				Thread.sleep(2000);
				WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));highlightElement(element9);element9.click();

		Thread.sleep(6000);
		// Locate the element
		WebElement alertElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));

		// Check if the element is displayed
		boolean deliveredDisplayed1 = alertElement1.isDisplayed();
		String alertText1 = alertElement1.getText().trim();

		String expectedOutput1 = "Deletion SuccessFully";
		Assert.assertTrue(alertText1.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(2000);

		if (deliveredDisplayed1) {
			// Extract and print the text
			// String alertText = alertElement.getText();
			System.out.println("delredrtclt Alert Text: " + alertText1);
		} else {
			System.out.println("The alert element is not displayed.");
		}

	}

		
			
		
		//=====================================================================================================================================================
				@Test(priority = 25, enabled = true)
					
					 public void switchToPortalGUI() throws InterruptedException {
					        System.out.println("Closing Admin GUI and launching Portal GUI...");

					        if (driver != null) {
					        	Thread.sleep(4000);
					            driver.quit();
					            driver = null; 
					        }

					        protallogin(); 
					       
					}
	//==========================================================================================================================================================
					
					

					@Test(priority = 26, enabled = true)
					public void sending_normal_msg1() throws InterruptedException {
						
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys(dataproviderrc6.sentnumber);
						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");
						Thread.sleep(2000);
						
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						highlightElement(sendButton);
						sendButton.click();
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
						Thread.sleep(3000);

//						driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();
	//
//						Thread.sleep(2000);

						WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						highlightElement(refresh);
						refresh.click();
						Thread.sleep(2000);
						refresh.click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
						
							if (deliveredDisplayed) {
								System.out.println("sending_normal_msg1 Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
						
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}

					// ========================================================================================================================
					@Test(priority = 27, enabled = true)
					public void sending_normal_msg10() throws InterruptedException {
						
						
						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();

						
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys(
								"9607995681,9607998905,9607923456,9607992348,9607998908,9607992351,9607990127,9607995679,9607995670,9607993456");
						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");
						Thread.sleep(2000);
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						highlightElement(sendButton);
						sendButton.click();
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
						Thread.sleep(3000);

						//driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();

						//Thread.sleep(2000);

						WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						highlightElement(refresh);
						refresh.click();
						// driver.findElement(By.xpath("")).click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							
							if (deliveredDisplayed) {
								System.out.println("sending_normal_msg10 Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}
					// ========================================================================================================================

					@Test(priority = 28, enabled = true)
					public void sending_templete() throws InterruptedException {
						
						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						Thread.sleep(1000);

						driver.findElement(By.xpath("(//div[@id='template'])[1]")).click();
						Thread.sleep(1000);
						
						driver.findElement(By.xpath("//li[normalize-space()='New Template']")).click();
						driver.findElement(By.xpath("//input[@id='templateName']")).sendKeys("CretebyAuto1");
						driver.findElement(By.xpath("//input[@id='templateIndex']")).sendKeys("1");
						driver.findElement(By.xpath("//textarea[@id='msgBox']")).sendKeys("the message is here Craete by Auto");
						driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Cancel']")).click();
						driver.findElement(By.xpath("(//div[@id='template'])[1]")).click();

						driver.findElement(By.xpath("//li[normalize-space()='CretebyAuto1']")).click();
						//driver.findElement(By.xpath("")).click();
						//driver.findElement(By.xpath("")).click();
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys(dataproviderrc6.sentnumber);
						driver.findElement(By.xpath("//input[@id='recipients']")).click();
						driver.findElement(By.xpath("//button[normalize-space()='SEND']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();

						Thread.sleep(1000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						highlightElement(refresh);
						refresh.click();
						
						// refresh.click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						Thread.sleep(2000);
						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
						
							if (deliveredDisplayed) {
								System.out.println("sending_templete Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}


						} catch (NoSuchElementException ex) {
							
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}
					}
					// ========================================================================================================================

					@Test(priority = 29, enabled = true)
					public void sending_file() throws InterruptedException, AWTException {
						
						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						Thread.sleep(2000);
						driver.findElement(By.xpath("//span[2]//span[1]//input[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Upload File']//*[name()='svg']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='Number']")).click();

						String filePath = "C:\\Users\\Xavier\\Desktop\\msisdnnonduplicate.txt";
						//String filePath = "/bmpp/test-output/msisdnnonduplicate.txt";

						// Reading the file
						try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
							String line;
							while ((line = reader.readLine()) != null) {
								System.out.println(line); // Print each line of the file
							}
						} catch (IOException e) {
							System.err.println("Error reading the file: " + e.getMessage());
						}

						Thread.sleep(2000);
						// Use Robot to handle the file upload dialog
						Robot robot = new Robot();

						// Set file path to clipboard
						StringSelection selection = new StringSelection(filePath);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

						// Paste the file path (Ctrl + V)
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);

						// Press Enter to confirm
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(4000);

						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello file message");
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[normalize-space()='SEND']")).click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();

						Thread.sleep(2000);
						
//						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[4]/li[1]/div[1]")).click();
//						Thread.sleep(4000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

						Thread.sleep(2000);

						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();
						
						Thread.sleep(6000);
						refresh.click();
						Thread.sleep(1000);
						refresh.click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);

						driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
						Thread.sleep(1000);
						WebElement sentno = driver
								.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]"));

						String sentnoText = sentno.getText();
						System.out.println("sent msisdn\n" + sentnoText);

						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							if (deliveredDisplayed) {
								System.out.println("sending_file Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}

					// ========================================================================================================================

					@Test(priority = 30, enabled = true)
					public void sending_duplfile() throws InterruptedException, AWTException {
						
						Thread.sleep(4000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						Thread.sleep(2000);
						driver.findElement(By.xpath("//span[2]//span[1]//input[1]")).click();
						Thread.sleep(1000);
						//driver.findElement(By.xpath("(//input[@type='checkbox'])[7]")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[normalize-space()='Upload File']//*[name()='svg']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='Number']")).click();

						String filePath = "C:\\Users\\Xavier\\Desktop\\msisdnduplicate.txt";
					//String filePath = "/bmpp/test-output/old/msisdnandmessage";

						// Reading the file
						try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
							String line;
							while ((line = reader.readLine()) != null) {
								System.out.println(line); // Print each line of the file
							}
						} catch (IOException e) {
							System.err.println("Error reading the file: " + e.getMessage());
						}

						Thread.sleep(2000);
						// Use Robot to handle the file upload dialog
						Robot robot = new Robot();

						// Set file path to clipboard
						StringSelection selection = new StringSelection(filePath);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

						// Paste the file path (Ctrl + V)
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);

						// Press Enter to confirm
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(4000);

						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello file message");
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[normalize-space()='SEND']")).click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();

						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
						Thread.sleep(4000);


//						driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();
	//
//						Thread.sleep(6000);

						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();
						Thread.sleep(5000);
						refresh.click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();

						driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
						Thread.sleep(1000);
						WebElement sentno = driver
								.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]"));

						String sentnoText = sentno.getText();
						System.out.println("sent msisdn\n" + sentnoText);

						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							
							if (deliveredDisplayed) {
								System.out.println("sending_duplfile Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}

					// ========================================================================================================================

					@Test(priority = 31, enabled = true)
					public void sending_flash() throws InterruptedException {
						
						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						driver.findElement(By.xpath("//span[4]//span[1]//input[1]")).click();
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681");
						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");
						Thread.sleep(2000);
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						highlightElement(sendButton);
						sendButton.click();
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();

						Thread.sleep(3000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

						Thread.sleep(2000);

						WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							// boolean failedDisplayed =
							// driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();

							if (deliveredDisplayed) {
								System.out.println("sending_flash Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
						
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}
					// ========================================================================================================================

					@Test(priority = 32, enabled = true)
					public void ads_msge() throws InterruptedException {
						
						Thread.sleep(3000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[2]/span[4]/span[1]/input[1]")).click();
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681");
						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello normal message");
						Thread.sleep(2000);
					
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						sendButton.click();
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();

						Thread.sleep(3000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

						Thread.sleep(2000);

						WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						// driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
						
							if (deliveredDisplayed) {
								System.out.println("adsmsge Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}
					// ========================================================================================================================

					@Test(priority = 33, enabled = true)
					public void sending_concate() throws InterruptedException {
						

						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681");
						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("A paragraph is a series of sentences that are organized and coherent, and are all related to a single topic. Almost every piece of writing you do that is longer than a few sentences should be organized into paragraphs. This is because paragraphs show a reader where the subdivisions of an essay begin and end, and thus help the reader see the organization of the essay and grasp its main points.Paragraphs can contain many different kinds of information. A paragraph could contain a series of brief examples or a single long illustration of a general point. It might describe a place, character, or process; narrate a series of events; compare or contrast two or more things; classify items into categories; or describe causes and effects. Regardless of the kind of information they contain, all paragraphs share certain characteristics. One of the most important of these is a topic sentence");
						Thread.sleep(2000);
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						highlightElement(sendButton);
						sendButton.click();
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
//						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
//						Thread.sleep(3000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

						Thread.sleep(2000);

						WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						highlightElement(refresh);
						refresh.click();
						Thread.sleep(5000);
						refresh.click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						Thread.sleep(2000);
						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
						
							if (deliveredDisplayed) {
								System.out.println("sending_concate Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}
					// ========================================================================================================================

					@Test(priority = 34, enabled = true)
					public void send_multi_lang() throws InterruptedException {
						

						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						driver.findElement(By.xpath("//textarea[@id='message']"))
								.sendKeys("English: Unity in diversity—Language connects us all!\n"
										+ "Hindi (हिन्दी): विविधता में एकता—भाषा हम सभी को जोड़ती है!\n"
										+ "Kannada (ಕನ್ನಡ): ವೈವಿಧ್ಯದಲ್ಲಿ ಏಕತೆ—ಭಾಷೆ ನಮಗೆಲ್ಲರನ್ನೂ ಸಂಪರ್ಕಿಸುತ್ತದೆ!\n"
										+ "Tamil (தமிழ்): विविधத்திலே ஒற்றுமை—மொழி அனைவரையும் இணைக்கிறது!\n"
										+ "Telugu (తెలుగు): వైవిధ్యంలో ఏకత్వం—భాష మనందరిని కలుపుతుంది!\n"
										+ "Malayalam (മലയാളം): വൈവിധ്യത്തിൽ ഐക്യം—ഭാഷ എല്ലാവരെയും ബന്ധിപ്പിക്കുന്നു!");
						Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681");
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						sendButton.click();
						Thread.sleep(2000);
						//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						//WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
						Thread.sleep(3000);

//						driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();
	//
//						Thread.sleep(2000);

						WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();
						Thread.sleep(2000);
						refresh.click();
						Thread.sleep(4000);
						refresh.click();
						
						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						Thread.sleep(2000);
						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							
							if (deliveredDisplayed) {
								System.out.println("send_multi_lang Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}
					// ========================================================================================================================

					@Test(priority = 35, enabled = true)
					public void sending_schedule() throws InterruptedException {
						

						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681");

						// Get the current system time and add 2 minutes
						LocalTime currentTime = LocalTime.now().plusMinutes(1);

						// Extract the hour and minute from the new time
						int hour = currentTime.getHour();
						int minute = currentTime.getMinute();
						int second = currentTime.getSecond();

						// Convert hour and minute to strings, formatted to two digits (e.g., "08"
						// instead of "8")
						String hourStr = String.format("%02d", hour);
						String minuteStr = String.format("%02d", minute);
						String secondStr = String.format("%02d", second);

						// Print the resulting time for debugging purposes (optional)
						System.out.println("Updated Time: " + hourStr + ":" + minuteStr );
						Thread.sleep(2000);
						WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement calender = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[4]/div[1]/div[1]/input[1]")));
						highlightElement(calender);
						calender.click();
						driver.findElement(By.xpath("(//input[@aria-label='Hour'])[1]")).sendKeys(hourStr);
						Thread.sleep(2000);
						driver.findElement(By.xpath("(//input[@aria-label='Minute'])[1]")).sendKeys(minuteStr);
						driver.findElement(By.xpath("(//input[@type='number'])[4]")).sendKeys(secondStr);
						
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[3]/span[2]")).click();

						Thread.sleep(2000);
						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(" scheduled message for 1 min");
					
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						sendButton.click();
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						confirmSendButton.click();
						//driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[4]/li[1]/div[1]")).click();
						Thread.sleep(3000);

//						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
	//
//						Thread.sleep(2000);
					//	WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						//refresh.click();
						//out box xpath
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[4]/li[1]/div[1]")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/span[1]/input[1]")).click(); // check box
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]")).click(); // to box
						WebElement schdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[6]/h6[1]")));
						//schdate.click(); // scdule date  box
						schdate.getText().trim();
						String schdateText = schdate.getText().trim();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
						Thread.sleep(3000);
						WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));

						Thread.sleep(40000);
						refresh.click();
						Thread.sleep(1000);
						refresh.click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						Thread.sleep(2000);

						WebElement time = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/h6[1]"));
						//WebElement timeElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[2]/h6[1]"));
						time.getText().trim();
						String alertText = time.getText().trim();
						
						String expectedOutput = hourStr + ":" + minuteStr ;
					//	String expectedOutput = hourStr + ":" + minuteStr + ":" + secondStr;
						Thread.sleep(2000);

					//	String alertText1 = timeElement.getText().trim(); // Get and trim the text

						// Print the extracted time
						//System.out.println("Extracted Time: " + alertText1);
						System.out.println("Expected Time: " + expectedOutput);
						System.out.println("Outbox Time: " + schdateText);

						// Validate the displayed time with expected time
						Assert.assertTrue(alertText.contains(expectedOutput), "Time mismatch!");
						Assert.assertTrue(schdateText.contains(expectedOutput), "Time mismatch!");

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
						
							if (deliveredDisplayed) {
								System.out.println("sending_seduled Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}
					// ========================================================================================================================

					@Test(priority = 36, enabled = true)
					public void sending_personal() throws InterruptedException, AWTException {
						
						Thread.sleep(4000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
						Thread.sleep(2000);

						driver.findElement(By.xpath("//button[normalize-space()='Upload File']")).click();

						String filePath = "C:\\Users\\Xavier\\Desktop\\personaliseMessageXlsSample.xlsx";
						//String filePath = "/bmpp/test-output/personaliseMessageXlsSample.xlsx";

						// Reading the file
						try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
							String line;
							while ((line = reader.readLine()) != null) {
								System.out.println(line); // Print each line of the file
							}
						} catch (IOException e) {
							System.err.println("Error reading the file: " + e.getMessage());
						}

						Thread.sleep(2000);
						// Use Robot to handle the file upload dialog
						Robot robot = new Robot();

						// Set file path to clipboard
						StringSelection selection = new StringSelection(filePath);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

						// Paste the file path (Ctrl + V)
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);

						// Press Enter to confirm
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(4000);

						driver.findElement(By.xpath("//textarea[@id='usrMessage']"))
								.sendKeys("hello{{Mobile}}fdshfadfnudnj{{Full Name}}wkjenfewnjd{{Amount}}");
						Thread.sleep(4000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[4]/span[2]/span[1]/input[1]")).click();
						Thread.sleep(6000);
						driver.findElement(By.xpath("//button[normalize-space()='SEND']")).click();

						Thread.sleep(4000);
						driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();

						Thread.sleep(5000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

						Thread.sleep(10000);

						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();
						Thread.sleep(4000);
						refresh.click();
						Thread.sleep(2000);
						refresh.click();
						

						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();

						driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
						Thread.sleep(1000);
						WebElement sentno = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]"));

						String sentnoText = sentno.getText();
						System.out.println("sent msisdn\n" + sentnoText);

						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							
							if (deliveredDisplayed) {
								System.out.println("sending_personal Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
						
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

					}
					// ========================================================================================================================

					@Test(priority = 37, enabled = true)
					public void sending_MSISDN_and_Msge() throws InterruptedException, AWTException {
						
						Thread.sleep(3000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						Thread.sleep(2000);
						driver.findElement(By.xpath("//span[2]//span[1]//input[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Upload File']//*[name()='svg']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='Number & Message']")).click();

						String filePath = "C:\\Users\\Xavier\\Desktop\\msisdnandmessage.txt";
						//String filePath = "/bmpp/test-output/msisdnandmessage";

						// Reading the file
						try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
							String line;
							while ((line = reader.readLine()) != null) {
								System.out.println(line); // Print each line of the file
							}
						} catch (IOException e) {
							System.err.println("Error reading the file: " + e.getMessage());
						}

						Thread.sleep(2000);
						// Use Robot to handle the file upload dialog
						Robot robot = new Robot();

						// Set file path to clipboard
						StringSelection selection = new StringSelection(filePath);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

						// Paste the file path (Ctrl + V)
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);

						// Press Enter to confirm
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(4000);

						// file message");
						Thread.sleep(2000);
						WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

						WebElement sendbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='SEND']")));
						highlightElement(sendbutton);		
						sendbutton.click();

						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();

						Thread.sleep(2000);

						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[4]/li[1]/div[1]")).click();
						Thread.sleep(4000);


						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();

						Thread.sleep(5000);
						refresh.click();
						Thread.sleep(1000);
						refresh.click();

						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
					

						driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
						Thread.sleep(1000);
						WebElement sentno = driver
								.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]"));

						String sentnoText = sentno.getText();
						System.out.println("sent msisdn\n" + sentnoText);

						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						Thread.sleep(2000);

						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							
							if (deliveredDisplayed) {
								System.out.println("sending_NoandMsge Message is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {

							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}}
						}
					
	//========================================================================================================================================================================
					@Test(priority = 38, enabled = false)
					public void sending_whatsapp_msge() throws InterruptedException, AWTException {
					
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[2]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys(dataproviderrc6.sentnumber);
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[3]/div[1]/div[1]/div[1]/*[name()='svg'][1]")).click();
						driver.findElement(By.xpath("//div[@id='template']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='hello_world']")).click();
						//driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();
						
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[2]/button[2]")).click();
						Thread.sleep(3000);

						driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();

						Thread.sleep(2000);

//						driver.findElement(By.xpath("")).click();
//						
						
						
						
					}
					//========================================================================================================================================================================
					@Test(priority = 39, enabled = false)
					public void sending_whatsapp_filemsge() throws InterruptedException, AWTException {
					
						//driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[2]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/span[2]/span[1]/input[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Upload File']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='Number']")).click();
						String filePath = "C:\\Users\\Xavier\\Desktop\\whatsappNumberFile.txt";
						//String filePath = "/bmpp/test-output/personaliseMessageXlsSample.xlsx";

						// Reading the file
						try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
							String line;
							while ((line = reader.readLine()) != null) {
								System.out.println(line); // Print each line of the file
							}
						} catch (IOException e) {
							System.err.println("Error reading the file: " + e.getMessage());
						}

						Thread.sleep(2000);
						// Use Robot to handle the file upload dialog
						Robot robot = new Robot();

						// Set file path to clipboard
						StringSelection selection = new StringSelection(filePath);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

						// Paste the file path (Ctrl + V)
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);

						// Press Enter to confirm
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(4000);

					
						
						//driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys(dataproviderrc6.sentnumber);
						driver.findElement(By.xpath("//div[@id='template']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='hello_world']")).click();
						//driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();
						
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[2]/button[2]")).click();
						Thread.sleep(3000);

						driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();

						
//						driver.findElement(By.xpath("")).click();
//						driver.findElement(By.xpath("")).click();
						
						
						
					}
					//========================================================================================================================================================================
					@Test(priority = 39, enabled = false)
					public void sending_whatsapp_file_placeholder() throws InterruptedException, AWTException {
					
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[2]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/span[2]/span[1]/input[1]")).click();
						//driver.findElement(By.xpath("")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Upload File']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='Number with Placeholders']")).click();

						String filePath = "C:\\Users\\Xavier\\Desktop\\whatsappNumberPlaceholderFile.txt";
						//String filePath = "/bmpp/test-output/personaliseMessageXlsSample.xlsx";

						// Reading the file
						try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
							String line;
							while ((line = reader.readLine()) != null) {
								System.out.println(line); // Print each line of the file
							}
						} catch (IOException e) {
							System.err.println("Error reading the file: " + e.getMessage());
						}

						Thread.sleep(2000);
						// Use Robot to handle the file upload dialog
						Robot robot = new Robot();

						// Set file path to clipboard
						StringSelection selection = new StringSelection(filePath);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

						// Paste the file path (Ctrl + V)
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);

						// Press Enter to confirm
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(4000);

					
						
						//driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys(dataproviderrc6.sentnumber);
						driver.findElement(By.xpath("//div[@id='template']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='hello_world']")).click();
						//driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();
						
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[2]/button[2]")).click();
						Thread.sleep(3000);

						driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();

						
						
						
					}
					
					@Test(priority = 38,enabled = true)
					public void Contact_Basic_Case() throws InterruptedException {
				 
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[5]/li[1]/div[1]")).click(); //click contact 
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[2]/button[1]")).click(); // new contact
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("Xavier"); // name text feild 
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("AT"); // last text feild
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[2]/div[2]/div[2]/div[1]/div[1]/input[1]")).sendKeys("9876567");
						driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click(); //back button
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[2]/button[1]")).click(); // new contact
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("Xavier1"); // name text feild 
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("aT"); // last text feild
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[2]/div[2]/div[2]/div[1]/div[1]/input[1]")).sendKeys("9876561");
						driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click(); //back button
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[5]/div[2]/button[1]")).click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("New group one");
						driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/h5[1]")).click(); //all contact
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/label[1]")).click(); //all contact check box
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/button[2]/*[name()='svg'][1]")).click(); // add group
						//driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).click(); //group text feild 
						
						// 1. Click the dropdown to reveal options
						//WebElement dropdown = driver.findElement(By.xpath("//label[text()='Group *']/following::div[contains(@class,'MuiSelect-root')]"));
						WebElement dropdown = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
						dropdown.click();

						// 2. Wait until the option appears (it’s dynamically rendered)
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
						WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
						    By.xpath("//li[normalize-space()='New group one']") // sometimes it's <li> or <div> depending on MUI version
						));

						// 3. Click the desired option
						option.click();		
						//driver.findElement(By.xpath("MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputAdornedEnd MuiAutocomplete-input MuiAutocomplete-inputFocused css-1xr7jc4-MuiInputBase-input-MuiOutlinedInput-input")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Save']")).click(); //save 
						Thread.sleep(2000);
						//driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]/*[name()='path'][1]")).click(); //back button 
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[6]/div[1]/div[1]/h6[1]")).click(); // group 
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/label[1]/h5[1]")).click(); //select grp
						Thread.sleep(2000);
//						WebElement sendmsge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/button[3]")));
//						sendmsge.click(); // send message
//						Thread.sleep(2000);
//						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]")).click(); // msge desc
//						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[5]/div[1]/div[1]/textarea[1]")).sendKeys(" this messge is from the contact");
//						
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
						driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
						//driver.findElement(By.xpath("//input[@id='recipients']")).click();
						WebElement dropdown1 = driver.findElement(By.xpath("//input[@id='recipients']"));
						dropdown1.click();

						// 2. Wait until the option appears (it’s dynamically rendered)
						//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
						WebElement option1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
						    By.xpath("//li[normalize-space()='New group one [Group]']") // sometimes it's <li> or <div> depending on MUI version
						));

						// 3. Click the desired option
						option1.click();	
						driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello  contact select  message");
						Thread.sleep(2000);
						
						WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
						Thread.sleep(2000);
						sendButton.click();
						Thread.sleep(2000);
						//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						//WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
						WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
						highlightElement(confirmSendButton);
						confirmSendButton.click();
						driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
						Thread.sleep(3000);
						WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
						refresh.click();
						Thread.sleep(2000);
						refresh.click();
						Thread.sleep(4000);
						refresh.click();
						Thread.sleep(1000);
						refresh.click();
						
						Thread.sleep(1000);
						driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
						driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

						Thread.sleep(2000);
						try {
							boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
									.isDisplayed();
							
							if (deliveredDisplayed) {
								System.out.println(" Message by selecting is delivered");
							} else {
								System.out.println("'Delivered' element is not present on the page.");
							}

						} catch (NoSuchElementException ex) {
							
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
								System.out.println("The message is'Delivered'");
							} catch (NoSuchElementException e) {
								System.out.println("'Delivered' element is not present on the page.");
							}
							// Check if 'Failed' element is not found
							try {
								driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
								System.out.println("The message is 'Failed'");
							} catch (NoSuchElementException e) {
								System.out.println("'Failed' element is not present on the page.");
							}
						}

						
						
//						driver.findElement(By.xpath(""));
//						driver.findElement(By.xpath(""));

//						// driver.navigate().refresh();
//						Thread.sleep(1000);
//						// Add assertion before clicking the element
////						By contactModuleLocator = By.xpath("//*[contains.text('Contacts')]");
//						By contactModuleLocator = By.xpath("//*[contains(text(), 'Contacts')]");
////						By contactModuleLocator = By.xpath("(//div[@class='MuiBox-root css-mvs481'])[5]");
//				 
//						if (isElementPresent(contactModuleLocator)) {
//							driver.findElement(contactModuleLocator).click();
//						} else {
//							System.out.println("Contact module not available");
//							Assert.fail("Contact module not available"); // Fails the test
//						}
//					}
//				 
//					public boolean isElementPresent(By locator) {
//						try {
//							driver.findElement(locator);
//							return true; // Element is found
//						} catch (NoSuchElementException e) {
//							return false; // Element not found
//						}
					}
					
					
					
	//========================================================================================================================================================================
//					@Test(priority = 38, enabled = false)
//					public void transfer_points() throws InterruptedException, AWTException {
//						driver.findElement(By.xpath("//span[normalize-space()='account_circle']")).click();
//						driver.findElement(By.xpath("//div[@role='presentation']//li[1]")).click();
//						
//						WebElement transferbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Transfer']")));
//						highlightElement(transferbutt);
//						transferbutt.click();
//						driver.findElement(By.xpath("//div[@id='receivingAccount']")).click();
//						WebElement dropbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='" +dataproviderrc6.anotherusername+"']")));
//						highlightElement(dropbutt);
//						dropbutt.click();
//						driver.findElement(By.xpath("//input[@id='inputNumbers']")).sendKeys("10");
//						driver.findElement(By.xpath("//input[@id='termsCheckbox']")).click();
//						WebElement submitbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Submit']")));
//						highlightElement(submitbutt);
//						submitbutt.click();
//						Thread.sleep(3000);
//					 driver.navigate().refresh();
//						
//					}
	//===========================================================================================================================================================================				
					@Test(priority = 39, enabled = false)
					public void Multifactor_auth_sms() throws InterruptedException, AWTException {
						
						//Thread.sleep(3000);
						WebElement togglebutt1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='account_circle']")));
						highlightElement(togglebutt1);
						togglebutt1.click();
						driver.findElement(By.xpath("//div[@role='presentation']//li[1]")).click();
						WebElement togglebutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[3]/div[1]/div/div/div[2]/div[1]/div[2]/div/div[2]/div/div/div/div/div/div[7]/div[1]/span[2]/span[1]")));
						highlightElement(togglebutt);
						togglebutt.click();
						
						driver.findElement(By.xpath("//button[@aria-label='Click to edit contact number']//*[name()='svg']//*[name()='path' and contains(@d,'M3 17.25V2')]")).click();
						WebElement conttext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]")));
						highlightElement(conttext);
						conttext.sendKeys("9482100");
						
						
						
						WebElement updatebutt1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium mx-2 css-mvba87-MuiButtonBase-root-MuiButton-root'][normalize-space()='Update'])[1]")));
						highlightElement(updatebutt1);
						updatebutt1.click();
						WebElement updatebutt2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/div[2]/button[2]")));
						highlightElement(updatebutt2);
						updatebutt2.click();
						driver.findElement(By.xpath("//div[@id='mfatype']")).click();
						driver.findElement(By.xpath("//li[normalize-space()='SMS']")).click();
						Thread.sleep(2000);
						
						WebElement updatebutt3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[7]/div[3]/button[1]")));
						highlightElement(updatebutt3);
						updatebutt3.click();
						WebElement submitbutt3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Submit']")));
						highlightElement(submitbutt3);
						submitbutt3.click();
						driver.navigate().refresh();
						Thread.sleep(5000);
						driver.findElement(By.xpath("//span[normalize-space()='account_circle']")).click();
						driver.findElement(By.xpath("//div[@role='presentation']//li[2]")).click();
						
						
						Thread.sleep(3000);
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[1]/div/div/input"))
								.sendKeys(dataproviderrc6.portalusername); // entering the username
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[2]/div/div/input"))
								.sendKeys(dataproviderrc6.portalpass);// entering the password
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button/span[1]"))
								.click();// performing the click action
						String otp = authlogic.multi_factor_auth();
						WebElement otptext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));otptext.sendKeys(otp);
						
						driver.findElement(By.xpath("//span[@class='indicator-label']")).click();
					}
	//=================================================================================================================================================
						@Test(priority = 40, enabled = false)
						public void Multifactor_auth__email() throws InterruptedException, AWTException {
						
						Thread.sleep(5000);
						driver.findElement(By.xpath("//span[normalize-space()='account_circle']")).click();
						driver.findElement(By.xpath("//div[@role='presentation']//li[2]")).click();
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[1]/div/div/input"))
								.sendKeys(dataproviderrc6.portalusername); // entering the username
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[2]/div/div/input"))
								.sendKeys(dataproviderrc6.portalpass);// entering the password
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button/span[1]"))
								.click();// performing the click action
						Thread.sleep(2000);
						String otp = authlogic.multi_factor_auth();
						WebElement otptext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
						highlightElement(otptext);
						otptext.sendKeys(otp);
						
						driver.findElement(By.xpath("//span[@class='indicator-label']")).click();
						Thread.sleep(3000);
						driver.findElement(By.xpath("//span[normalize-space()='account_circle']")).click();
						driver.findElement(By.xpath("//div[@role='presentation']//li[1]")).click();
//						WebElement togglebutt1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[3]/div[1]/div/div/div[2]/div[1]/div[2]/div/div[2]/div/div/div/div/div/div[7]/div[1]/span[2]/span[1]")));
//						togglebutt1.click();
						WebElement dropdownbutt4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mfatype']")));
						highlightElement(dropdownbutt4);
						dropdownbutt4.click();
						WebElement dropdownbutt5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Email']")));
						highlightElement(dropdownbutt5);
						dropdownbutt5.click();
						WebElement updatebutt4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[3]/div[1]/div/div/div[2]/div[1]/div[2]/div/div[2]/div/div/div/div/div/div[7]/div[3]/button")));
						highlightElement(updatebutt4);
						updatebutt4.click();
						WebElement submitbutt4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Submit']")));
						highlightElement(submitbutt4);
						submitbutt4.click();
						
						
						driver.navigate().refresh();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//span[normalize-space()='account_circle']")).click();
						driver.findElement(By.xpath("//div[@role='presentation']//li[2]")).click();
						
						
						Thread.sleep(3000);
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[1]/div/div/input"))
								.sendKeys(dataproviderrc6.portalusername); // entering the username
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[2]/div/div/input"))
								.sendKeys(dataproviderrc6.portalpass);// entering the password
						driver.findElement(
								By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button/span[1]"))
								.click();// performing the click action
						String otp1 = authlogic.multi_factor_auth();
						WebElement otptext1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
						highlightElement(otptext1);
						otptext1.sendKeys(otp1);
						
						driver.findElement(By.xpath("//span[@class='indicator-label']")).click();

						 
					}
	////=========================================================================================================================================================================
//						
//						@Test(priority = 35, enabled = true)
//						public void sending_schedule_admin() throws InterruptedException {
//							
//							driver.findElement(By.xpath("//span[normalize-space()='account_circle']")).click();
//							driver.findElement(By.xpath("//div[@role='presentation']//li[2]")).click();
	//
//							Thread.sleep(3000);
//							driver.findElement(
//									By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[1]/div/div/input"))
//									.sendKeys(dataproviderrc6.portalusername1); // entering the username
//							driver.findElement(
//									By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[2]/div/div/input"))
//									.sendKeys(dataproviderrc6.portalpass1);// entering the password
//							driver.findElement(
//									By.xpath("/html/body/div/div[1]/div/div/div/div[2]/div/div[1]/div[3]/form/div[3]/button/span[1]"))
//									.click();// performing the click action
	//
//							Thread.sleep(3000);
//							driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
//							driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
//							driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("9607995681");
	//
//							// Get the current system time and add 2 minutes
//							LocalTime currentTime = LocalTime.now().plusMinutes(4);
	//
//							// Extract the hour and minute from the new time
//							int hour = currentTime.getHour();
//							int minute = currentTime.getMinute();
//							int second = currentTime.getSecond();
	//
//							// Convert hour and minute to strings, formatted to two digits (e.g., "08"
//							// instead of "8")
//							String hourStr = String.format("%02d", hour);
//							String minuteStr = String.format("%02d", minute);
//							String secondStr = String.format("%02d", second);
	//
//							// Print the resulting time for debugging purposes (optional)
//							System.out.println("Updated Time: " + hourStr + ":" + minuteStr );
//							Thread.sleep(2000);
//							WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
//							WebElement calender = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[4]/div[1]/div[1]/input[1]")));
//							highlightElement(calender);
//							calender.click();
//							driver.findElement(By.xpath("(//input[@aria-label='Hour'])[1]")).sendKeys(hourStr);
//							Thread.sleep(2000);
//							driver.findElement(By.xpath("(//input[@aria-label='Minute'])[1]")).sendKeys(minuteStr);
//							driver.findElement(By.xpath("(//input[@type='number'])[4]")).sendKeys(secondStr);
//							
//							driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[3]/span[2]")).click();
	//
//							Thread.sleep(2000);
//							driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(" scheduled message for 1 min");
//						
//							WebElement sendButton = driver.findElement(By.xpath("//button[normalize-space()='SEND']"));
//							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
//							Thread.sleep(2000);
//							sendButton.click();
//							Thread.sleep(2000);
//							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//							WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
//									By.xpath("//*[contains(text(),'Are you sure? you want to send this message?')]")));
//							WebElement confirmSendButton = driver.findElement(By.xpath("//button[normalize-space()='Send']"));
//							confirmSendButton.click();
//							driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
//							Thread.sleep(3000);
	//
//							driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/ul/a[3]/li/div")).click();
//							
//							
//							
							
							

//							Thread.sleep(2000);
//							WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
//							refresh.click();
//							Thread.sleep(69000);
//							refresh.click();
//							Thread.sleep(1000);
//							driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
//							driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
//							Thread.sleep(2000);
//							driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();
	//
//							Thread.sleep(2000);
	//
//							WebElement time = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[2]/h6[1]"));
//							WebElement timeElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[2]/h6[1]"));
//							time.getText().trim();
//							String alertText = time.getText().trim();
//							
//							String expectedOutput = hourStr + ":" + minuteStr ;
//						//	String expectedOutput = hourStr + ":" + minuteStr + ":" + secondStr;
//							Thread.sleep(2000);
	//
//						//	String alertText1 = timeElement.getText().trim(); // Get and trim the text
	//
//							// Print the extracted time
//							//System.out.println("Extracted Time: " + alertText1);
//							System.out.println("Expected Time: " + expectedOutput);
	//
//							// Validate the displayed time with expected time
//							Assert.assertTrue(alertText.contains(expectedOutput), "Time mismatch!");
	//
//							try {
//								boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
//										.isDisplayed();
//							
//								if (deliveredDisplayed) {
//									System.out.println("sending_seduled Message is delivered");
//								} else {
//									System.out.println("'Delivered' element is not present on the page.");
//								}
	//
//							} catch (NoSuchElementException ex) {
//								
//								try {
//									driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
//									System.out.println("The message is'Delivered'");
//								} catch (NoSuchElementException e) {
//									System.out.println("'Delivered' element is not present on the page.");
//								}
//								// Check if 'Failed' element is not found
//								try {
//									driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
//									System.out.println("The message is 'Failed'");
//								} catch (NoSuchElementException e) {
//									System.out.println("'Failed' element is not present on the page.");
//								}
//							}

//						}
						// ========================================================================================================================

						
						
						
						
						
						
						@Test(priority = 41, enabled = true)
						public void sending_file_sms_tracker() throws InterruptedException, AWTException {
							
							
							 String  extractedNumber =null;
							Thread.sleep(3000);
							driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[1]/li[1]/div[1]")).click();
							driver.findElement(By.xpath("//input[@id='msgDesc']")).sendKeys("Sample");
							Thread.sleep(2000);
							driver.findElement(By.xpath("//span[2]//span[1]//input[1]")).click();
							driver.findElement(By.xpath("//button[normalize-space()='Upload File']//*[name()='svg']")).click();
							driver.findElement(By.xpath("//li[normalize-space()='Number']")).click();

							String filePath = "C:\\Users\\Xavier\\Desktop\\msisdnnonduplicate.txt";
					
							try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
								String line;
								while ((line = reader.readLine()) != null) {
									System.out.println(line); // Print each line of the file
								}
							} catch (IOException e) {
								System.err.println("Error reading the file: " + e.getMessage());
							}

							Thread.sleep(2000);
							// Use Robot to handle the file upload dialog
							Robot robot = new Robot();

							// Set file path to clipboard
							StringSelection selection = new StringSelection(filePath);
							Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

							// Paste the file path (Ctrl + V)
							robot.keyPress(KeyEvent.VK_CONTROL);
							robot.keyPress(KeyEvent.VK_V);
							robot.keyRelease(KeyEvent.VK_V);
							robot.keyRelease(KeyEvent.VK_CONTROL);

							// Press Enter to confirm
							robot.keyPress(KeyEvent.VK_ENTER);
							robot.keyRelease(KeyEvent.VK_ENTER);
							Thread.sleep(4000);

							driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("hello file message");
							Thread.sleep(2000);
							driver.findElement(By.xpath("//button[normalize-space()='SEND']")).click();

							Thread.sleep(1000);
							driver.findElement(By.xpath("//button[normalize-space()='Send']")).click();

							Thread.sleep(2000);

							driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();

							Thread.sleep(2000);

							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
							WebElement refresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
							refresh.click();
							
							Thread.sleep(10000);
							refresh.click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]")).click();
							WebElement filereff = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[6]/h6[1]")));
							 String reff = filereff.getText();
							 Pattern pattern = Pattern.compile("\\((\\d+)_");
						        Matcher matcher = pattern.matcher(reff);
						       
						        if (matcher.find()) {
						           extractedNumber = matcher.group(1); // Get the first capture group
						            System.out.println("Extracted Number: " + extractedNumber);
						        } else {
						            System.out.println("No number found!");
						        }
							
							 
							Thread.sleep(1000);
							driver.findElement(By.xpath("//button[normalize-space()='Message Status']")).click();
							Thread.sleep(2000);

							driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
							Thread.sleep(1000);
							WebElement sentno = driver
									.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]"));

							String sentnoText = sentno.getText();
							System.out.println("sent msisdn\n" + sentnoText);

							Thread.sleep(2000);
							driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/h2[1]/div[1]/*[name()='svg'][1]")).click();

							try {
								boolean deliveredDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Delivered']"))
										.isDisplayed();
								if (deliveredDisplayed) {
									System.out.println("sending_file Message is delivered");
								} else {
									System.out.println("'Delivered' element is not present on the page.");
								}

							} catch (NoSuchElementException ex) {
								try {
									driver.findElement(By.xpath("//h6[normalize-space()='Delivered']")).isDisplayed();
									System.out.println("The message is'Delivered'");
								} catch (NoSuchElementException e) {
									System.out.println("'Delivered' element is not present on the page.");
								}
								// Check if 'Failed' element is not found
								try {
									driver.findElement(By.xpath("//h6[normalize-space()='Failed']")).isDisplayed();
									System.out.println("The message is 'Failed'");
								} catch (NoSuchElementException e) {
									System.out.println("'Failed' element is not present on the page.");
								}
							}
							
							Thread.sleep(5000);
							
							
						
							   
							        driver.get("https://" + dataproviderrc6.host + ":" + dataproviderrc6.port + "/bmp/");
							        driver.manage().window().maximize();
							        System.out.println("Admin GUI Launched");
							    
								Thread.sleep(2000);
								driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).sendKeys(dataproviderrc6.admin);
								driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys(dataproviderrc6.adminpasss);
								driver.findElement(By.xpath("//button[@type='submit']")).click();

							
				
							
						//	login();
							WebElement filesmsbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[1]//div[2]//ul[1]//li[2]//a[1]")));
							highlightElement(filesmsbutt);
							filesmsbutt.click();
							WebElement serch_text_feild = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='CurrentdayfilesBody']//div[contains(@class,'row')]//div//input[contains(@placeholder,'Search')]")));
							highlightElement(serch_text_feild);
							serch_text_feild.sendKeys(dataproviderrc6.username);
							WebElement filterbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Currentdayfiles']//th[5]//div[1]//span[2]//*[name()='svg']")));
							highlightElement(filterbutt);
							filterbutt.click();
							WebElement filterbutt1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(@class,'p-sortable-column p-highlight')]//span[contains(@data-pc-section,'sort')]//*[name()='svg']")));
							highlightElement(filterbutt1);
							filterbutt1.click();
							
							
							WebElement profilelink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")));
							highlightElement(profilelink);
							profilelink.click();
							
							WebElement filereffadmin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/p[1]")));
							 String reffadmin = filereffadmin.getText();

							 Assert.assertTrue(extractedNumber.contains(reffadmin), "file reference id mismatch!");
					      
					      Thread.sleep(2000);
					      driver.findElement(By.xpath("//div[@class='modal-dialog modal-xl']//button[@aria-label='Close']")).click();
					      
					      Thread.sleep(2000);
					      WebElement histbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/ul[1]/li[2]/a[1]")));
					      highlightElement(histbutt);
					      histbutt.click();
					    //  driver.findElement(By.xpath("//a[normalize-space()='History']")).click();
					      
					      WebElement histcltdrpdwn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")));
					      highlightElement(histcltdrpdwn);
					      histcltdrpdwn.click();
					      WebElement histcltselect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='" + dataproviderrc6.username +"']")));
					      highlightElement(histcltselect);
					      histcltselect.click();
					      
					      SimpleDateFormat formatter = new SimpleDateFormat("d");
					        String today = formatter.format(new Date());
					        System.out.println("Today's date: " + today);
					      
					      WebElement calender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='TSSGUI_DateFieldSetStyle']")));highlightElement(calender); calender.click();
					      WebElement calenderdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='" + today + "']")));highlightElement(calenderdate); calenderdate.click();
					      WebElement hsubmitbutt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/button[1]")));
					      highlightElement(hsubmitbutt);
					      hsubmitbutt.click();
					      Thread.sleep(2000);
					      

					      WebElement filterbutt2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='p-inputtext p-component']")));
							highlightElement(filterbutt2);
							filterbutt2.sendKeys(reffadmin);

					      
					      WebElement fileclick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")));
					      highlightElement(fileclick); 
					      fileclick.click();

					      WebElement filereffadmin1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/p[1]")));
							 String reffadmin1 = filereffadmin1.getText();
							 System.out.println(reffadmin1);
					   Assert.assertTrue(extractedNumber.contains(reffadmin1), "file reference id mismatch!");
					      
					  
					    Thread.sleep(2000);
					   driver.findElement(By.xpath("//div[@class='modal-dialog modal-xl']//button[@aria-label='Close']")).click();
					   Thread.sleep(2000);
					   
//					   //==================================================================schdule_msge_admin=========
//					   driver.findElement(By.xpath("//i[@title='Site Map']")).click();
//					   driver.findElement(By.xpath("//div[4]//div[1]//div[2]//ul[1]//li[3]//a[1]")).click();
//					   driver.findElement(By.xpath("//fieldset[@id='TSSGUI_SelectFieldLabelStyle']")).click();
//					   driver.findElement(By.xpath("//li[contains(@title,'pankaj124')]")).click();
//					   driver.findElement(By.xpath("//fieldset[@id='TSSGUI_DateFieldSetStyle']")).click();
//					   driver.findElement(By.xpath("//span[normalize-space()='" + today + "']")).click();
//					   driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/button[1]")).click();
//					   driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/a[1]")).click();
//					   
					   
					   
					   
					   
						WebElement element0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[contains(@title,'Site Map')])[1]")));highlightElement(element0);
						element0.click();

					   //driver.findElement(By.xpath("//i[contains(@title,'Site Map')]")).click();
					   driver.findElement(By.xpath("//div[3]//div[1]//div[2]//ul[1]//li[1]//a[1]")).click();
					   Thread.sleep(2000);
					   driver.findElement(By.xpath("//a[normalize-space()='BDR Tracker']")).click();
					   driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]")).click();
					   driver.findElement(By.xpath("//span[normalize-space()='" + today + "']")).click();
					   driver.findElement(By.xpath("//div[2]//div[1]//fieldset[1]")).click();
					   driver.findElement(By.xpath("//span[normalize-space()='" + today + "']")).click();
					   driver.findElement(By.xpath("//div[contains(@class,'TSSGUI_SelectFieldDropDownIcon')]//i[contains(@class,'')]")).click();
					   driver.findElement(By.xpath("//li[@title='"+ dataproviderrc6.username +"']")).click();
					   Thread.sleep(2000);
					   WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@data-toggle,'modal')]")));highlightElement(element1);
					   element1.click();
					   element1.click();
					   //driver.findElement(By.xpath("//button[contains(@data-toggle,'modal')]")).click();
					   Thread.sleep(2000);
					   driver.findElement(By.xpath("//button[@class='tssDTDownloadBtn p-button p-component p-button-icon-only']//*[name()='svg']")).click();
					   Thread.sleep(2000);
					   driver.findElement(By.xpath("/html[1]/body[1]/div[3]/ul[1]/li[3]/div[1]/a[1]")).click();
					  // driver.findElement(By.xpath("")).click();
					   
					   
						}
	//=====================================================================================================================================================================================


					
					@AfterMethod (enabled = true)
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
				            	if (lines.length >= 3) {
				            	    failureReason = String.join("\n", lines[0], lines[1], lines[2]);
				            	} else {
				            	    failureReason = String.join("\n", lines);
				            	}
				 
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
					
	//=====================================================================================================================================================================================
					
					@AfterClass
					public void setup() throws InterruptedException {
						
						Thread.sleep(6000);
						driver.quit();	

					}
	@AfterSuite (enabled = true)
	public void sendEmailReport() {
		// Email configuration
		final String senderEmail = "xavier.t@tayana.in"; // Replace with your email
//		final String senderPassword = "packed@45657#"; 
		final String senderPassword = "abcd"; // in the cmd set MAIL_PASSWORD=your_password restart your eclipse
		final String recipientEmails = "akshay.ps@tayana.in"; // TO recipients
		final String ccEmails = "akshay.ps@tayana.in ,harsha.m@tayana.in "; // CC recipients
		final String subject = "TestNG Emailable Report";
		final String messageBody = "Please find attached the emailable report of Admin GUI automation cases";

		// List of files to be attached
		String[] reportPaths = { System.getProperty("user.dir") + "/test-output/emailable-report.html",
				//System.getProperty("user.dir") + "/test-output/index.html"
				//System.getProperty("user.dir") + "/test-output/passed.png"
				};

		// Set up properties for the email
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");

		// Create session with authentication
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// Create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmails)); // TO recipients
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmails)); // CC recipients
			message.setSubject(subject);

			// Create message body part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(messageBody);

			// Create a multipart for attaching files
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Loop through each file and attach
			for (String filePath : reportPaths) {
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.attachFile(new File(filePath));
				multipart.addBodyPart(attachmentPart);
			}

			// Set content
			message.setContent(multipart);

			// Send email
			Transport.send(message);
			System.out.println("Email sent successfully with multiple attachments!");
			System.out.println("Number of rows affected: " + rowsAffected);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}

