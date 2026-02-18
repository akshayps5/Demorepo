package cpaas;


	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;


	import io.netty.handler.timeout.TimeoutException;

	public class bmpdealerportal {
		

			public static WebDriver driver;
			@BeforeClass
			// logic to login one time in the browser and execute all test cases within the  browser
			public void setUp() throws InterruptedException {
				WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
				bmpdealerportal.driver= driver;
//				WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance


				 driver.get("https://10.0.5.212:3005/bmpportal/auth/login");

		driver.manage().window().maximize();
		Thread.sleep(3000);
					//driver.findElement(By.xpath("")).sendKeys("PankajTest123"); //entering the username 
					driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("PankajRC26");//entering the password
					driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("Pankajrc26");//performing the click action
					driver.findElement(By.xpath("//button[@type='submit']")).click();
					Thread.sleep(3000);
					driver.manage().window().maximize();
					//driver.findElement(By.id(browser)
					
				Thread.sleep(3000);	
				
				
		}
				
			@Test(priority = 1)
			public void sending_RCS_TEXT_message() throws InterruptedException{
				
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
				
				driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("+918660748446");
				  Thread.sleep(2000);
				    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hello text message");
				  Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='rcsMessageType']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[normalize-space()='TEXT']")).click();
				//driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div[1]/div/div[2]/button[2]")).click();
			
			  
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
				confirmSendButton.click();
				Thread.sleep(4000);
				   // Navigate to Sent Items
				WebElement whatsappMessageElement = driver.findElement(By.xpath("//*[contains(text(),'Sent Items')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsappMessageElement);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsappMessageElement);
				Thread.sleep(2000);
	            driver.findElement(By.xpath("(//span[@title='+918660748446'][normalize-space()='+918660748446'])[1]")).click();
	         // 1️⃣ Implicit wait (global)
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            // 2️⃣ Explicit wait (only where needed)
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	            // 3️⃣ Wait for toast to disappear (if present)
	            try {
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.className("toast-link")
	                ));
	            } catch (TimeoutException e) {
	                // Toast not present – safe to continue
	            }

	            // 4️⃣ Click Message Status
	            WebElement messageStatusBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Message Status']")
	                    )
	            );

	            Assert.assertTrue(messageStatusBtn.isDisplayed(), "Message Status button not visible");
	            messageStatusBtn.click();

	            // 5️⃣ Click Recipient tab
	            driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();

	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

	            // Locate the LATEST message status (Sent / Delivered / Read)
	            WebElement statusElement = wait1.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath(
	                                "(//*[normalize-space()='Delivered' " +
	                                "or normalize-space()='Sent' " +
	                                "or normalize-space()='Read'])[last()]"
	                            )
	                    )
	            );

	            // Read status text
	            String status = statusElement.getText().trim();

	            // Print result
	            if (status.equalsIgnoreCase("Delivered")) {
	                System.out.println("Message is delivered successfully ✅");
	            }
	            else if (status.equalsIgnoreCase("Read")) {
	                System.out.println("The RCS message has been delivered and has been read by the user 📖✅");
	            }
	            else if (status.equalsIgnoreCase("Sent")) {
	                System.out.println(
	                    "Message status is Sent ⚠️. Data may be disabled or handset is in airplane mode."
	                );
	            }
	            else {
	                System.out.println("Message status is: " + status);
	            }
			
	        }
			@Test(priority = 2)
			public void sending_text_with_image() throws InterruptedException, Exception{
				driver.navigate().refresh();
				new WebDriverWait(driver, Duration.ofSeconds(10))
				        .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
				
				driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("+918660748446");
				  Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='rcsMessageType']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[normalize-space()='TEXT WITH IMAGE']")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//button[normalize-space()='Upload Image']")).click();
			    Thread.sleep(2000);
			 // Use Robot to handle the file upload dialog
			    Robot robot = new Robot();

			    // Set file path to clipboard
			    String filePath = "C:\\Users\\Pankaj\\Downloads\\happy-birthday-card-with-flowers-assortment.jpg";
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
			    Thread.sleep(6000);
			    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Image with text");
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
				confirmSendButton.click();
				Thread.sleep(8000);
				WebElement successMessage = wait.until(
			            ExpectedConditions.visibilityOfElementLocated(
			                    By.xpath("//b[normalize-space()='Message Submitted Successfully']")
			            )
			    );

			    System.out.println("Success message displayed: " + successMessage.getText());

					WebElement whatsappMessageElement = driver.findElement(By.xpath("//*[contains(text(),'Sent Items')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsappMessageElement);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsappMessageElement);
					Thread.sleep(4000);
	            driver.findElement(By.xpath("(//span[@title='+918660748446'][normalize-space()='+918660748446'])[1]")).click();
	         // 1️⃣ Implicit wait (global)
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            // 2️⃣ Explicit wait (only where needed)
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	            // 3️⃣ Wait for toast to disappear (if present)
	            try {
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.className("toast-link")
	                ));
	            } catch (TimeoutException e) {
	                // Toast not present – safe to continue
	            }
	             driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")).click();
	            Thread.sleep(2000);
	            // 4️⃣ Click Message Status
	            WebElement messageStatusBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Message Status']")
	                    )
	            );

	            Assert.assertTrue(messageStatusBtn.isDisplayed(), "Message Status button not visible");
	            messageStatusBtn.click();

	            // 5️⃣ Click Recipient tab
	            driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();

	         // Wait setup
	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

	            // Locate the LATEST message status (Sent / Delivered / Read)
	            WebElement statusElement = wait1.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath(
	                                "(//*[normalize-space()='Delivered' " +
	                                "or normalize-space()='Sent' " +
	                                "or normalize-space()='Read'])[last()]"
	                            )
	                    )
	            );

	            // Read status text
	            String status = statusElement.getText().trim();

	            // Print result
	            if (status.equalsIgnoreCase("Delivered")) {
	                System.out.println("Message is delivered successfully ✅");
	            }
	            else if (status.equalsIgnoreCase("Read")) {
	                System.out.println("The RCS message has been delivered and has been read by the user 📖✅");
	            }
	            else if (status.equalsIgnoreCase("Sent")) {
	                System.out.println(
	                    "Message status is Sent ⚠️. Data may be disabled or handset is in airplane mode."
	                );
	            }
	            else {
	                System.out.println("Message status is: " + status);
	            }
			}
		
		
			@Test(priority = 3)
			public void sending_mp4_video_via_MEDIA() throws InterruptedException, Exception{
				driver.navigate().refresh();
				new WebDriverWait(driver, Duration.ofSeconds(10))
				        .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
				
				driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("+918660748446");
				  Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='rcsMessageType']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[normalize-space()='MEDIA']")).click();
			    Thread.sleep(4000);
			    driver.findElement(By.xpath("//button[normalize-space()='Upload Media']")).click();
			    Thread.sleep(2000);
			 // Use Robot to handle the file upload dialog
			    Robot robot = new Robot();

			    // Set file path to clipboard
			    String filePath = "C:\\Users\\Pankaj\\Downloads\\Rcb.mp4";
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
			    Thread.sleep(6000);
				  
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
				Thread.sleep(10000);
				   // Navigate to Sent Items
							// 5️sec Wait for success message (right top corner)
						    WebElement successMessage = wait.until(
						            ExpectedConditions.visibilityOfElementLocated(
						                    By.xpath("//b[normalize-space()='Message Submitted Successfully']")
						            )
						    );

						    System.out.println("Success message displayed: " + successMessage.getText());
				   // Navigate to Sent Items
				WebElement whatsappMessageElement = driver.findElement(By.xpath("//*[contains(text(),'Sent Items')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsappMessageElement);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsappMessageElement);
				Thread.sleep(4000);
	            driver.findElement(By.xpath("(//span[@title='+918660748446'][normalize-space()='+918660748446'])[1]")).click();
	         // 1️⃣ Implicit wait (global)
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            // 2️⃣ Explicit wait (only where needed)
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	            // 3️⃣ Wait for toast to disappear (if present)
	            try {
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.className("toast-link")
	                ));
	            } catch (TimeoutException e) {
	                // Toast not present – safe to continue
	            }

	            // 4️⃣ Click Message Status
	            WebElement messageStatusBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Message Status']")
	                    )
	            );

	            Assert.assertTrue(messageStatusBtn.isDisplayed(), "Message Status button not visible");
	            messageStatusBtn.click();

	            // 5️⃣ Click Recipient tab
	            driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();

	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

	            // Locate the LATEST message status (Sent / Delivered / Read)
	            WebElement statusElement = wait1.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath(
	                                "(//*[normalize-space()='Delivered' " +
	                                "or normalize-space()='Sent' " +
	                                "or normalize-space()='Read'])[last()]"
	                            )
	                    )
	            );

	            // Read status text
	            String status = statusElement.getText().trim();

	            // Print result
	            if (status.equalsIgnoreCase("Delivered")) {
	                System.out.println("Message is delivered successfully ✅");
	            }
	            else if (status.equalsIgnoreCase("Read")) {
	                System.out.println("The RCS message has been delivered and has been read by the user 📖✅");
	            }
	            else if (status.equalsIgnoreCase("Sent")) {
	                System.out.println(
	                    "Message status is Sent ⚠️. Data may be disabled or handset is in airplane mode."
	                );
	            }
	            else {
	                System.out.println("Message status is: " + status);
	            }
			}
		
			@Test(priority = 4)
			public void sending_mp3_via_MEDIA() throws InterruptedException, Exception{
				driver.navigate().refresh();
				new WebDriverWait(driver, Duration.ofSeconds(10))
				        .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
				
				driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("+918660748446");
				  Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='rcsMessageType']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[normalize-space()='MEDIA']")).click();
			    Thread.sleep(4000);
			    driver.findElement(By.xpath("//button[normalize-space()='Upload Media']")).click();
			    Thread.sleep(2000);
			 // Use Robot to handle the file upload dialog
			    Robot robot = new Robot();

			    // Set file path to clipboard
			    String filePath = "C:\\Users\\Pankaj\\Downloads\\Dabidi Dibidi-320kbps.mp3";
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
			    Thread.sleep(6000);
				  
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
				Thread.sleep(10000);
				   // Navigate to Sent Items
				// 5️sec Wait for success message (right top corner)
			    WebElement successMessage = wait.until(
			            ExpectedConditions.visibilityOfElementLocated(
			                    By.xpath("//b[normalize-space()='Message Submitted Successfully']")
			            )
			    );

			    System.out.println("Success message displayed: " + successMessage.getText());

					WebElement whatsappMessageElement = driver.findElement(By.xpath("//*[contains(text(),'Sent Items')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsappMessageElement);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsappMessageElement);
					Thread.sleep(4000);
	            driver.findElement(By.xpath("(//span[@title='+918660748446'][normalize-space()='+918660748446'])[1]")).click();
	         // 1️⃣ Implicit wait (global)
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            // 2️⃣ Explicit wait (only where needed)
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	            // 3️⃣ Wait for toast to disappear (if present)
	            try {
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.className("toast-link")
	                ));
	            } catch (TimeoutException e) {
	                // Toast not present – safe to continue
	            }

	            // 4️⃣ Click Message Status
	            WebElement messageStatusBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Message Status']")
	                    )
	            );

	            Assert.assertTrue(messageStatusBtn.isDisplayed(), "Message Status button not visible");
	            messageStatusBtn.click();

	            // 5️⃣ Click Recipient tab
	            driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();
	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

	            // Locate the LATEST message status (Sent / Delivered / Read)
	            WebElement statusElement = wait1.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath(
	                                "(//*[normalize-space()='Delivered' " +
	                                "or normalize-space()='Sent' " +
	                                "or normalize-space()='Read'])[last()]"
	                            )
	                    )
	            );

	            // Read status text
	            String status = statusElement.getText().trim();

	            // Print result
	            if (status.equalsIgnoreCase("Delivered")) {
	                System.out.println("Message is delivered successfully ✅");
	            }
	            else if (status.equalsIgnoreCase("Read")) {
	                System.out.println("The RCS message has been delivered and has been read by the user 📖✅");
	            }
	            else if (status.equalsIgnoreCase("Sent")) {
	                System.out.println(
	                    "Message status is Sent ⚠️. Data may be disabled or handset is in airplane mode."
	                );
	            }
	            else {
	                System.out.println("Message status is: " + status);
	            }
			}
		
			@Test(priority = 5)
			public void sending_pdf_via_MEDIA() throws InterruptedException, Exception{
				driver.navigate().refresh();
				new WebDriverWait(driver, Duration.ofSeconds(10))
				        .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
				
				driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("+918660748446");
				  Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='rcsMessageType']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[normalize-space()='MEDIA']")).click();
			    Thread.sleep(4000);
			    driver.findElement(By.xpath("//button[normalize-space()='Upload Media']")).click();
			    Thread.sleep(2000);
			 // Use Robot to handle the file upload dialog
			    Robot robot = new Robot();

			    // Set file path to clipboard
			    String filePath = "C:\\Users\\Pankaj\\Downloads\\MCA1stsem.pdf";
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
			    Thread.sleep(6000);
				  
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
				Thread.sleep(10000);
				   // Navigate to Sent Items
				// 5️sec Wait for success message (right top corner)
			    WebElement successMessage = wait.until(
			            ExpectedConditions.visibilityOfElementLocated(
			                    By.xpath("//b[normalize-space()='Message Submitted Successfully']")
			            )
			    );

			    System.out.println("Success message displayed: " + successMessage.getText());

					WebElement whatsappMessageElement = driver.findElement(By.xpath("//*[contains(text(),'Sent Items')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsappMessageElement);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsappMessageElement);
					Thread.sleep(4000);
	            driver.findElement(By.xpath("(//span[@title='+918660748446'][normalize-space()='+918660748446'])[1]")).click();
	         // 1️⃣ Implicit wait (global)
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            // 2️⃣ Explicit wait (only where needed)
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	            // 3️⃣ Wait for toast to disappear (if present)
	            try {
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.className("toast-link")
	                ));
	            } catch (TimeoutException e) {
	                // Toast not present – safe to continue
	            }

	            // 4️⃣ Click Message Status
	            WebElement messageStatusBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Message Status']")
	                    )
	            );

	            Assert.assertTrue(messageStatusBtn.isDisplayed(), "Message Status button not visible");
	            messageStatusBtn.click();

	            // 5️⃣ Click Recipient tab
	            driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();

	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

	            // Locate the LATEST message status (Sent / Delivered / Read)
	            WebElement statusElement = wait1.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath(
	                                "(//*[normalize-space()='Delivered' " +
	                                "or normalize-space()='Sent' " +
	                                "or normalize-space()='Read'])[last()]"
	                            )
	                    )
	            );

	            // Read status text
	            String status = statusElement.getText().trim();

	            // Print result
	            if (status.equalsIgnoreCase("Delivered")) {
	                System.out.println("Message is delivered successfully ✅");
	            }
	            else if (status.equalsIgnoreCase("Read")) {
	                System.out.println("The RCS message has been delivered and has been read by the user 📖✅");
	            }
	            else if (status.equalsIgnoreCase("Sent")) {
	                System.out.println(
	                    "Message status is Sent ⚠️. Data may be disabled or handset is in airplane mode."
	                );
	            }
	            else {
	                System.out.println("Message status is: " + status);
	            }
			}
		
			@Test(priority = 6)
			public void sending_image_via_MEDIA() throws InterruptedException, Exception{
				driver.navigate().refresh();
				new WebDriverWait(driver, Duration.ofSeconds(10))
				        .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
				
				driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("+918660748446");
				  Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='rcsMessageType']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[normalize-space()='MEDIA']")).click();
			    Thread.sleep(4000);
			    driver.findElement(By.xpath("//button[normalize-space()='Upload Media']")).click();
			    Thread.sleep(2000);
			 // Use Robot to handle the file upload dialog
			    Robot robot = new Robot();

			    // Set file path to clipboard
			    String filePath = "C:\\Users\\Pankaj\\Downloads\\SD-720x480.jfif";
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
			    Thread.sleep(6000);
				  
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
				Thread.sleep(10000);
				   // Navigate to Sent Items
				// 5️sec Wait for success message (right top corner)
			    WebElement successMessage = wait.until(
			            ExpectedConditions.visibilityOfElementLocated(
			                    By.xpath("//b[normalize-space()='Message Submitted Successfully']")
			            )
			    );

			    System.out.println("Success message displayed: " + successMessage.getText());

					WebElement whatsappMessageElement = driver.findElement(By.xpath("//*[contains(text(),'Sent Items')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsappMessageElement);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsappMessageElement);
					Thread.sleep(4000);
	            driver.findElement(By.xpath("(//span[@title='+918660748446'][normalize-space()='+918660748446'])[1]")).click();
	         // 1️⃣ Implicit wait (global)
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            // 2️⃣ Explicit wait (only where needed)
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	            // 3️⃣ Wait for toast to disappear (if present)
	            try {
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.className("toast-link")
	                ));
	            } catch (TimeoutException e) {
	                // Toast not present – safe to continue
	            }
	             driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")).click();
	            Thread.sleep(2000);
	            // 4️⃣ Click Message Status
	            WebElement messageStatusBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Message Status']")
	                    )
	            );

	            Assert.assertTrue(messageStatusBtn.isDisplayed(), "Message Status button not visible");
	            messageStatusBtn.click();

	            // 5️⃣ Click Recipient tab
	            driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();

	         // Wait setup
	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

	            // Locate the LATEST message status (Sent / Delivered / Read)
	            WebElement statusElement = wait1.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath(
	                                "(//*[normalize-space()='Delivered' " +
	                                "or normalize-space()='Sent' " +
	                                "or normalize-space()='Read'])[last()]"
	                            )
	                    )
	            );

	            // Read status text
	            String status = statusElement.getText().trim();

	            // Print result
	            if (status.equalsIgnoreCase("Delivered")) {
	                System.out.println("Message is delivered successfully ✅");
	            }
	            else if (status.equalsIgnoreCase("Read")) {
	                System.out.println("The RCS message has been delivered and has been read by the user 📖✅");
	            }
	            else if (status.equalsIgnoreCase("Sent")) {
	                System.out.println(
	                    "Message status is Sent ⚠️. Data may be disabled or handset is in airplane mode."
	                );
	            }
	            else {
	                System.out.println("Message status is: " + status);
	            }
			}
			@Test(priority = 7)
			public void Delete_rcs_message() throws InterruptedException{
			//	driver.navigate().refresh();
				//new WebDriverWait(driver, Duration.ofSeconds(10))
				  //      .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/a[3]/li[1]/div[1]")).click();
				
				driver.findElement(By.xpath("//input[@id='recipients']")).sendKeys("+918660748446");
				  Thread.sleep(2000);
				    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hello text message");
				  Thread.sleep(2000);
			    driver.findElement(By.xpath("//div[@id='rcsMessageType']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[normalize-space()='TEXT']")).click();
				//driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div[1]/div/div[2]/button[2]")).click();
			
			  
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
				confirmSendButton.click();
				Thread.sleep(4000);
				   // Navigate to Sent Items
				WebElement whatsappMessageElement = driver.findElement(By.xpath("//*[contains(text(),'Sent Items')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsappMessageElement);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", whatsappMessageElement);
				Thread.sleep(2000);
	            driver.findElement(By.xpath("(//span[@title='+918660748446'][normalize-space()='+918660748446'])[1]")).click();
	         // 1️⃣ Implicit wait (global)
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            // 2️⃣ Explicit wait (only where needed)
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	            // 3️⃣ Wait for toast to disappear (if present)
	            try {
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.className("toast-link")
	                ));
	            } catch (TimeoutException e) {
	                // Toast not present – safe to continue
	            }

	            // 4️⃣ Click Message Status
	            WebElement messageStatusBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Message Status']")
	                    )
	            );

	            Assert.assertTrue(messageStatusBtn.isDisplayed(), "Message Status button not visible");
	            messageStatusBtn.click();

	         // 5️⃣ Click Recipient tab
	            driver.findElement(By.xpath("//p[normalize-space()='Recipient']")).click();

	            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

	            // Locate the LATEST message status
	            WebElement statusElement = wait1.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath(
	                                "(//*[normalize-space()='Delivered' " +
	                                "or normalize-space()='Sent' " +
	                                "or normalize-space()='Read'])[last()]"
	                            )
	                    )
	            );

	            // Read status text
	            String status = statusElement.getText().trim();

	            // Act based on status
	            if (status.equalsIgnoreCase("Delivered")) {

	                System.out.println("Message is delivered successfully ✅");

	            }
	            else if (status.equalsIgnoreCase("Read")) {

	                System.out.println("The RCS message has been delivered and has been read by the user 📖✅");

	            }
	            else if (status.equalsIgnoreCase("Sent")) {

	                System.out.println(
	                    "Message status is Sent ⚠️. Data may be disabled or handset is in airplane mode."
	                );

	                // 👉 DELETE LOGIC GOES HERE
	                WebElement deleteIcon = wait1.until(
	                        ExpectedConditions.elementToBeClickable(
	                                By.xpath("//tbody//tr//td[9]//*[name()='svg']")
	                        )
	                );
	                deleteIcon.click();

	                WebElement toastMessage = wait1.until(
	                        ExpectedConditions.visibilityOfElementLocated(
	                                By.xpath("//div[@class='custom-toast-title']")
	                        )
	                );

	                System.out.println("Toast message: " + toastMessage.getText());
	                System.out.println("The RCS message has been deleted 🗑️");

	            }
	            else {

	                System.out.println("Message status is: " + status);

	            }
			}
			
			@AfterClass
			public void setUps() {
//				driver.quit();
			}

		}
	
