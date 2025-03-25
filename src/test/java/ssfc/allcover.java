package ssfc;



	import java.io.BufferedReader;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebDriverException;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	public class allcover {
		private static final String protoCheck = null;
		public static WebDriver driver;

		@BeforeClass
		public void setUp() {
			WebDriver driver = new FirefoxDriver();
			allcover.driver = driver;

			  try {
			        driver.get("https://10.0.6.65:8001/ssfc/login");
			        driver.manage().window().maximize();
			 
			        // Use WebDriverWait for the login page elements
			        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
			        driver.findElement(By.id("tsslogin-form_password")).sendKeys("Tayana@123");
			        driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
			 
			        try {
			            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='swal2-title']")));
			            System.out.println("Assertion: Server.js got killed (Error popup detected)");
			            Assert.fail("Server.js is down, detected error popup.");
			        } catch (TimeoutException e) {
			            // No error popup found, continue with the test.
			            System.out.println("Login successful, no error popup.");
			        }
			 
			    } catch (WebDriverException e) {
			            System.out.println("Assertion: Network error, server.js might be down.");
			            Assert.fail("NetworkError when attempting to fetch resource. Server.js got killed.");
			    }
			 
			}

		@Test(dataProvider = "MoSys",   priority = 1, enabled = true)
		public void Adding_MoSystemRule(String i) throws InterruptedException {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a/p/i")).click(); // Click on Fraud
																										// control
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click(); // Click on
																												// Rules
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/ul/li[2]/a")).click(); // Click on spam
			Thread.sleep(2000);
			driver.findElement(By.id("moSysPanel")).click(); // Click on MO system
			Thread.sleep(2000);
			WebElement elements = driver.findElement(By.xpath("//*[contains(text(), 'SSFC')]"));//click on row cell
			elements.click();
			driver.findElement(By.cssSelector(
					"#moSysBody > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4) > svg:nth-child(2) > path:nth-child(2)"))
					.click(); // Click on delete button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(1239,366)");
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();  // Click on Yes popupp. 
			
			Thread.sleep(3000);
			
			// Deletion of MO system Rules
			
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement errorMessageElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText = errorMessageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText);
			actualText = actualText.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText = "Deletion successful";

		 
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)
			
			/// Checking the API call is success or not.
					
					
					try {
			            // Construct the URL
			            @SuppressWarnings("deprecation")
						URL url = new URL("http://10.0.6.65:80/cgi-bin/ApiCheck?2");
			            
			            // Open HTTP connection
			            HttpURLConnection http = (HttpURLConnection) url.openConnection();
			            http.setRequestMethod("GET");
			            http.setRequestProperty("Content-Type", "application/json");
			            http.setRequestProperty("Accept", "application/json");
			            http.setDoOutput(true);

			            // Read the response
			            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) http.getContent(), "utf-8"));
			            StringBuilder response1 = new StringBuilder();
			            String responseLine = null;
			            while ((responseLine = br.readLine()) != null) {
			                response1.append(responseLine.trim());
			            }

			            // Print the response
			            System.out.println("\nResponse: " + response1.toString());

			            // Close the connection
			            br.close();
			            http.disconnect();

			            // Assert the expected response
			            Assert.assertTrue(response1.toString().contains("Pass"), "Expected 'Pass' string not found in response");

			        } catch (Exception e) {
			            e.printStackTrace();
			            Assert.fail("Exception occurred during API call: " + e.getMessage());
			        }
			
			
			driver.findElement(By.cssSelector(
					"#rightSectionDiv > section > div > div > div > div.d-flex.justify-content-end.tss-pull-right > svg > path"))
					.click(); // Click for +
			
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click(); // Click for dropdown
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='MO System']")).click(); // select MO system
			Thread.sleep(3000);

			driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys(i); // To type the
																										// keyword.
			Thread.sleep(3000);
			

			driver.findElement(By.xpath("//*[@title='Spam']")).click(); // To select Spam in the dropdown
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"addBtn\"]")).click();
			Thread.sleep(3000);
			// Click on Add button
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // To select yes when it ask for
																							// are u sure
			Thread.sleep(3000);
			// to check weather it's success or not
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement messageElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText1 = messageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText1);
			actualText1 = actualText1.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText1 = "addition Unsuccessful";

		 
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)

			 try {
		            // Construct the URL
		            @SuppressWarnings("deprecation")
					URL url = new URL("http://10.0.6.65:80/cgi-bin/ApiCheck?1");
		            
		            // Open HTTP connection
		            HttpURLConnection http = (HttpURLConnection) url.openConnection();
		            http.setRequestMethod("GET");
		            http.setRequestProperty("Content-Type", "application/json");
		            http.setRequestProperty("Accept", "application/json");
		            http.setDoOutput(true);

		            // Read the response
		            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) http.getContent(), "utf-8"));
		            StringBuilder response1 = new StringBuilder();
		            String responseLine = null;
		            while ((responseLine = br.readLine()) != null) {
		                response1.append(responseLine.trim());
		            }

		            // Print the response
		            System.out.println("\nResponse: " + response1.toString());

		            // Close the connection
		            br.close();
		            http.disconnect();

		            // Assert the expected response
		            Assert.assertTrue(response1.toString().contains("Pass"), "Expected 'Pass' string not found in response");

		        } catch (Exception e) {
		            e.printStackTrace();
		            Assert.fail("Exception occurred during API call: " + e.getMessage());
		        }
		}
		
		@DataProvider(name = "MoSys")
		public Object[] [] MoData(){
			return new Object [][] { { "30123" } };
			
		}
		
		
		

		@Test(dataProvider = "ImsiCheck" , priority = 2, enabled = true)
		public void Adding_Imsi4Operator(String j,  String k) throws InterruptedException {
			
			

			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a/p/i")).click(); // Click on Fraud
																										// control
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click(); // Click on
																												// Rules
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[5]/a")).click(); // Click on IMSI
			Thread.sleep(2000);

			// 1st delete the existing IMSI.

			driver.findElement(By.cssSelector(".fa-trash > path:nth-child(2)")).click(); // Click on delete button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys(k); // Clicking
																													// on
																													// IMSI
																													// Box
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"confirmButton\"]")).click(); // Click on Search button
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(1239,366)");
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"/html/body/div/div/div[1]/section/div/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr/td[1]/div/input"))
					.click(); // Check box
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div.tss-pull-right:nth-child(1) > button:nth-child(1)")).click(); // Click on
																													// delete
																													// box
																													// button
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Click on yes popup
			Thread.sleep(3000);
			
			// Deletion of existing IMSI
			
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement errorMessageElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText = errorMessageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText);
			actualText = actualText.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText = "IMSI deleted successfully";

		 
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)
			
			/// Checking the API call is success or not.
					
					
					try {
			            // Construct the URL
			            @SuppressWarnings("deprecation")
						URL url = new URL("http://10.0.6.65:80/cgi-bin/ApiCheck?2");
			            
			            // Open HTTP connection
			            HttpURLConnection http = (HttpURLConnection) url.openConnection();
			            http.setRequestMethod("GET");
			            http.setRequestProperty("Content-Type", "application/json");
			            http.setRequestProperty("Accept", "application/json");
			            http.setDoOutput(true);

			            // Read the response
			            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) http.getContent(), "utf-8"));
			            StringBuilder response1 = new StringBuilder();
			            String responseLine = null;
			            while ((responseLine = br.readLine()) != null) {
			                response1.append(responseLine.trim());
			            }

			            // Print the response
			            System.out.println("\nResponse: " + response1.toString());

			            // Close the connection
			            br.close();
			            http.disconnect();

			            // Assert the expected response
			            Assert.assertTrue(response1.toString().contains("Pass"), "Expected 'Pass' string not found in response");

			        } catch (Exception e) {
			            e.printStackTrace();
			            Assert.fail("Exception occurred during API call: " + e.getMessage());
			        }
					
			
			// Creating IMSI for an operator.
			
			driver.findElement(By.cssSelector(".fa-plus > path:nth-child(2)")).click(); // Click on + to add IMSI
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div[1]/div[1]/div/div"))
					.click(); // Click on IMSI Type box
			Thread.sleep(2000);
			driver.findElement(By
					.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div[1]/div[1]/div/div/div/div/ul/li[1]"))
					.click(); // Click on Manual.
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys(j); // Adding
																													// IMSI
			Thread.sleep(2000);
			
			

			
			
			driver.findElement(By.xpath("//*[@id=\"confirmButton\"]")).click(); // Click on Add
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Click on Yes for the popup
			Thread.sleep(1000);
			System.out.println("Adding IMSI");
			
			// to check weather it's success or not

			// to check weather it's success or not
					WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement messageElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
					String actualText1 = messageElement.getText().trim();// to remove the extra spaces and lines
					System.out.println(actualText1);
					actualText1 = actualText1.replaceAll("\\s+", " ");
					// Expected text that should be in the element (you can customize it)
					String expectedText1 = "IMSI added successfully";
					
					// Assertion to verify the actual text matches the expected text
					Assert.assertEquals(actualText1, expectedText1, "The message in the modal does not match!"); //comparing the expected and actual text)
					
				
					/// Checking the API call is success or not.
					
					
					try {
			            // Construct the URL
			            @SuppressWarnings("deprecation")
						URL url = new URL("http://10.0.6.65:80/cgi-bin/ApiCheck?1");
			            
			            // Open HTTP connection
			            HttpURLConnection http = (HttpURLConnection) url.openConnection();
			            http.setRequestMethod("GET");
			            http.setRequestProperty("Content-Type", "application/json");
			            http.setRequestProperty("Accept", "application/json");
			            http.setDoOutput(true);

			            // Read the response
			            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) http.getContent(), "utf-8"));
			            StringBuilder response1 = new StringBuilder();
			            String responseLine = null;
			            while ((responseLine = br.readLine()) != null) {
			                response1.append(responseLine.trim());
			            }

			            // Print the response
			            System.out.println("\nResponse: " + response1.toString());

			            // Close the connection
			            br.close();
			            http.disconnect();

			            // Assert the expected response
			            Assert.assertTrue(response1.toString().contains("Pass"), "Expected 'Pass' string not found in response");

			        } catch (Exception e) {
			            e.printStackTrace();
			            Assert.fail("Exception occurred during API call: " + e.getMessage());
			        }
					
		}
			 




		@DataProvider (name = "ImsiCheck")
		public Object[] [] Test2() {
			return new Object [] [] {
				{  "887664345675558", "887664345675558" }
			};
			
			
		}
		
		// Service Operator adding

		@Test(priority = 3, enabled = true)
		public void Adding_Service4Operator() throws InterruptedException {

			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a/p")).click(); // Click on Fraud
																										// control
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[3]/a/p")).click(); // Click on
																												// services
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[2]/a")).click(); // Click on operator
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(
					"#rightSectionDiv > section > div > div.card > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div > svg.svg-inline--fa.fa-plus.tss-add.tss-icon > path"))
					.click(); // Click on "+" to add operator
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click(); // Click on dropdown
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("li.options:nth-child(2)")).click(); // Click on checkbox
			Thread.sleep(400);
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div[1]/div/div[5]/button[1]")).click(); // Click
																														// on
																														// add
																														// button
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Click on yes for adding
			Thread.sleep(3000);

			// Assertion for addition successful.

			// to check weather it's success or not
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement messageElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText1 = messageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText1);
			actualText1 = actualText1.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText1 = "Addition Successful";
			
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText1, expectedText1, "The message in the modal does not match!"); //comparing the expected and actual text)

			
			
			/// Checking the API call is success or not.
			
			
			try {
	            // Construct the URL
	            @SuppressWarnings("deprecation")
				URL url = new URL("http://10.0.6.65:80/cgi-bin/ApiCheck?1");
	            
	            // Open HTTP connection
	            HttpURLConnection http = (HttpURLConnection) url.openConnection();
	            http.setRequestMethod("GET");
	            http.setRequestProperty("Content-Type", "application/json");
	            http.setRequestProperty("Accept", "application/json");
	            http.setDoOutput(true);

	            // Read the response
	            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) http.getContent(), "utf-8"));
	            StringBuilder response1 = new StringBuilder();
	            String responseLine = null;
	            while ((responseLine = br.readLine()) != null) {
	                response1.append(responseLine.trim());
	            }

	            // Print the response
	            System.out.println("\nResponse: " + response1.toString());

	            // Close the connection
	            br.close();
	            http.disconnect();

	            // Assert the expected response
	            Assert.assertTrue(response1.toString().contains("Pass"), "Expected 'Pass' string not found in response");

	        } catch (Exception e) {
	            e.printStackTrace();
	            Assert.fail("Exception occurred during API call: " + e.getMessage());
	        }
			
		}

		// Protocol addition

		@Test(dataProvider = "protoCheck" , priority = 4, enabled = true)
		public void AddingProtoRules(String l, String m) throws InterruptedException {

			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/a")).click(); // Click on Fraud
																										// control
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click(); // Click on
																												// Rules
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/ul/li[7]/a")).click(); // click on Protocol
			Thread.sleep(2000);
			
			// Deleting  the existing Protocol rule
			
			WebElement elements = driver.findElement(By.xpath("//*[contains(text(), 'SSFC')]"));//click on row cell
			elements.click();
			driver.findElement(By.cssSelector(
					".fa-pen-to-square > path:nth-child(2)"))
					.click(); // Click on modify button
			Thread.sleep(2000);
			
			driver.findElement(By.cssSelector("button.btn:nth-child(2)")).click(); // Click on delete
			Thread.sleep(3000);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(1239,366)");
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();  // Click on Yes popupp. 
			
			Thread.sleep(3000);
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
			String actualText = messageElement.getText().trim();// to remove the extra spaces and lines
			System.out.println(actualText);
			actualText = actualText.replaceAll("\\s+", " ");
			// Expected text that should be in the element (you can customize it)
			String expectedText = "Deletion successful";
			
			// Assertion to verify the actual text matches the expected text
			Assert.assertEquals(actualText, expectedText, "The message in the modal does not match!"); //comparing the expected and actual text)

			
			
			// Adding the new Protocol rule
			
			driver.findElement(By.cssSelector(
					"#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div > svg.svg-inline--fa.fa-plus.tss-add.tss-icon > path"))
					.click(); // Click on "+"
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys(l); // adding parameters
																										// to Rule name
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"TSSGUI_BootstrapTextArea\"]")).sendKeys(m); // Adding
																											// despcrition
																											// to create
																											// protocol
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"ProceedAddComponent\"]/div/div[2]/button[1]")).click(); // Clicking on
																											// add button
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Clicking on Yes pop up button
			Thread.sleep(2000);

			// Assertion for addition successful.

			// to check weather it's success or not
					WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement messageElement11 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
					String actualText1 = messageElement11.getText().trim();// to remove the extra spaces and lines
					System.out.println(actualText1);
					actualText1 = actualText1.replaceAll("\\s+", " ");
					// Expected text that should be in the element (you can customize it)
					String expectedText1 = "success";
					
					// Assertion to verify the actual text matches the expected text
					Assert.assertEquals(actualText1, expectedText1, "The message in the modal does not match!"); //comparing the expected and actual text)

					
	/// Checking the API call is success or not.
					
					
					try {
			            // Construct the URL
			            @SuppressWarnings("deprecation")
						URL url = new URL("http://10.0.6.65:80/cgi-bin/ApiCheck?1");
			            
			            // Open HTTP connection
			            HttpURLConnection http = (HttpURLConnection) url.openConnection();
			            http.setRequestMethod("GET");
			            http.setRequestProperty("Content-Type", "application/json");
			            http.setRequestProperty("Accept", "application/json");
			            http.setDoOutput(true);

			            // Read the response
			            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) http.getContent(), "utf-8"));
			            StringBuilder response1 = new StringBuilder();
			            String responseLine = null;
			            while ((responseLine = br.readLine()) != null) {
			                response1.append(responseLine.trim());
			            }

			            // Print the response
			            System.out.println("\nResponse: " + response1.toString());

			            // Close the connection
			            br.close();
			            http.disconnect();

			            // Assert the expected response
			            Assert.assertTrue(response1.toString().contains("Pass"), "Expected 'Pass' string not found in response");

			        } catch (Exception e) {
			            e.printStackTrace();
			            Assert.fail("Exception occurred during API call: " + e.getMessage());
			        }
					
					
		}

		@DataProvider (name = "protoCheck" )
		public Object[] [] Protocol() {
			return new Object[] [] {
				{	"SSFC", "Testing GUI" }
			};
		} 
		
		@AfterClass
		public void setUps() {
//			driver.quit();
		}
	}

