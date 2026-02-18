package snd;


	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Timestamp;
	import java.time.Duration;
	import java.util.List;
	import java.util.NoSuchElementException;
	import org.testng.Assert;

	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import java.time.LocalDate;

	public class sch_tranf {
		WebDriver driver;
	    FluentWait<WebDriver> wait;
	    
	    public void highlightElement(WebDriver driver, WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].style.border='3px solid red'", element);
	    }

	    @BeforeClass
		// logic to login one time in the browser and execute all test cases within the  browser
		public void loginToPorta() throws InterruptedException {
	    	driver = new FirefoxDriver();
	    driver.get("https://" + dataprovider.host + ":" + dataprovider.port + "/snd/login");
	    
	 // driver.get("https://10.0.5.49:8443/bmpportal/");
	    System.out.println("SND GUI Launched");
	    driver.manage().window().maximize();
		Thread.sleep(3000);
		// Locate and highlight the User ID field
		WebElement userId = driver.findElement(By.xpath("//input[@placeholder='User ID']"));
		highlightElement(driver, userId);
		userId.sendKeys("admin");

		// Locate and highlight the Password field
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		highlightElement(driver, password);
		password.sendKeys("Admin@123");
		
		//Locate and highlight the submit button
		WebElement submit = driver.findElement(By.xpath("//button[normalize-space()='Sign in']"));
		highlightElement(driver, submit);
		submit.click();
		}
	    


	@Test(priority = 1, enabled = true)
	public void create_schedule() throws InterruptedException {
		
		Thread.sleep(3000);
		WebElement scheduleTransfer = driver.findElement(By.xpath("/html/body/div[1]/div/div/section/div/div/div[7]/div/div[2]/ul/li[1]/a"));
		highlightElement(driver, scheduleTransfer);
		scheduleTransfer.click();
		Thread.sleep(3000); // click on schedule transfer link 
		// Click to add
		WebElement addIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]"));
		highlightElement(driver, addIcon);
		addIcon.click();
		Thread.sleep(3000);

		// Enter mobile number
		WebElement sourceMobile = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"));
		highlightElement(driver, sourceMobile);
		sourceMobile.sendKeys("2512352");
		Thread.sleep(3000);

		// Enter destination number
		WebElement destinationMobile = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"));
		highlightElement(driver, destinationMobile);
		destinationMobile.sendKeys("2653625");
		Thread.sleep(3000);

		// Enter the amount
		WebElement amountField = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[2]"));
		highlightElement(driver, amountField);
		amountField.sendKeys("4");
		Thread.sleep(3000);

		// Click on calendar
		WebElement calendarIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[4]/div[1]/fieldset[1]/div[1]/i[1]"));
		highlightElement(driver, calendarIcon);
		calendarIcon.click();
		Thread.sleep(3000);

		// Select today's date
		WebElement todaysDate = driver.findElement(By.xpath("//span[normalize-space()='28']"));
		highlightElement(driver, todaysDate);
		todaysDate.click();
		Thread.sleep(3000);

		// Click on frequency dropdown
		WebElement frequencyDropdown = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"));
		highlightElement(driver, frequencyDropdown);
		frequencyDropdown.click();
		Thread.sleep(3000);

		// Select weekly
		WebElement weeklyOption = driver.findElement(By.xpath("//li[@title='Weekly']"));
		highlightElement(driver, weeklyOption);
		weeklyOption.click();
		Thread.sleep(3000);

		// Click on add button
		WebElement addButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/button[1]/*[name()='svg'][1]"));
		highlightElement(driver, addButton);
		addButton.click();
		Thread.sleep(3000);

		// Click on Yes button
		WebElement yesButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]"));
		highlightElement(driver, yesButton);
		yesButton.click();
		Thread.sleep(3000);
		// Wait for the toast message to appear (you may need to adjust the XPath based on actual DOM)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath("//div[contains(@class, 'toast') or contains(@class, 'notification') or contains(text(),'Created Successful') or contains(text(),'Duplicate')]")
		));

		String message = toastMsg.getText();
		System.out.println("Popup message: " + message);

		if (message.contains("Created Successful")) {
		    System.out.println("✅ Transfer creation successful.");
		} else if (message.contains("Duplicate request") || message.contains("Schedule already exists")) {
		    System.out.println("❌ Duplicate schedule. Transfer not created.");
		} else {
		    System.out.println("⚠️ Unrecognized message: " + message);
		}

		
	}
	@Test(priority = 2, enabled = true)
	public void delete_schedule() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//i[@title='Site Map']")).click();
		Thread.sleep(3000);
		WebElement scheduleTransfer = driver.findElement(By.xpath("/html/body/div[1]/div/div/section/div/div/div[7]/div/div[2]/ul/li[1]/a"));
		highlightElement(driver, scheduleTransfer);
		scheduleTransfer.click();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
		searchBox.sendKeys("2653625");

		// Search input
		//WebElement searchInput = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/input[1]"));
		//highlightElement(driver, searchInput);
		//searchInput.sendKeys("2653625");

		Thread.sleep(4000); // wait for results

		// Click delete icon
		WebElement deleteIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/*[name()='svg'][1]/*[name()='path'][1]"));
		highlightElement(driver, deleteIcon);
		deleteIcon.click();

		Thread.sleep(4000); // wait for confirmation dialog

		// Click Yes button
		WebElement yesBtn = driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
		highlightElement(driver, yesBtn);
		yesBtn.click();

		Thread.sleep(4000); // wait for popup

		// Capture and validate toast message
		try {
		    WebElement toastMsg = driver.findElement(By.xpath("//*[contains(text(),'Deleted Successfully') or contains(text(),'Failed') or contains(@class,'toast') or contains(@class,'notification')]"));
		    highlightElement(driver, toastMsg);
		    String message = toastMsg.getText().trim();
		    System.out.println("Toast Message: " + message);

		    if (message.contains("Deleted Successfully")) {
		        System.out.println("✅ Test Passed: Deletion successful.");
		    } else {
		        System.out.println("❌ Test Failed: Unexpected message - " + message);
		    }
		} catch (NoSuchElementException e) {
		    System.out.println("❌ Test Failed: No toast message found.");
		}


	}
	@Test(priority = 3, enabled = true)
	public void create_monthly() throws InterruptedException {
	Thread.sleep(3000);
	WebElement scheduleTransfer = driver.findElement(By.xpath("/html/body/div[1]/div/div/section/div/div/div[7]/div/div[2]/ul/li[1]/a"));
	highlightElement(driver, scheduleTransfer);
	scheduleTransfer.click();
	Thread.sleep(3000); // click on schedule transfer link 
	// Click to add
	WebElement addIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]"));
	highlightElement(driver, addIcon);
	addIcon.click();
	Thread.sleep(3000);

	// Enter mobile number
	WebElement sourceMobile = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"));
	highlightElement(driver, sourceMobile);
	sourceMobile.sendKeys("2512352");
	Thread.sleep(3000);

	// Enter destination number
	WebElement destinationMobile = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"));
	highlightElement(driver, destinationMobile);
	destinationMobile.sendKeys("2653725");
	Thread.sleep(3000);

	// Enter the amount
	WebElement amountField = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[2]"));
	highlightElement(driver, amountField);
	amountField.sendKeys("4");
	Thread.sleep(3000);

	// Click on calendar
	WebElement calendarIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[4]/div[1]/fieldset[1]/div[1]/i[1]"));
	highlightElement(driver, calendarIcon);
	calendarIcon.click();
	Thread.sleep(3000);

	// Select today's date
	WebElement todaysDate = driver.findElement(By.xpath("//span[normalize-space()='28']"));
	highlightElement(driver, todaysDate);
	todaysDate.click();
	Thread.sleep(3000);

	// Click on frequency dropdown
	WebElement frequencyDropdown = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"));
	highlightElement(driver, frequencyDropdown);
	frequencyDropdown.click();
	Thread.sleep(3000);

	// Select weekly
	WebElement weeklyOption = driver.findElement(By.xpath("//li[@title='Monthly']"));
	highlightElement(driver, weeklyOption);
	weeklyOption.click();
	Thread.sleep(3000);

	// Click on add button
	WebElement addButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/button[1]/*[name()='svg'][1]"));
	highlightElement(driver, addButton);
	addButton.click();
	Thread.sleep(3000);

	// Click on Yes button
	WebElement yesButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]"));
	highlightElement(driver, yesButton);
	yesButton.click();
	Thread.sleep(3000);
	// Wait for the toast message to appear (you may need to adjust the XPath based on actual DOM)
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    By.xpath("//div[contains(@class, 'toast') or contains(@class, 'notification') or contains(text(),'Created Successful') or contains(text(),'Duplicate')]")
	));

	String message = toastMsg.getText();
	System.out.println("Popup message: " + message);

	if (message.contains("Created Successful")) {
	    System.out.println("✅ Transfer creation successful.");
	} else if (message.contains("Duplicate request") || message.contains("Schedule already exists")) {
	    System.out.println("❌ Duplicate schedule. Transfer not created.");
	} else {
	    System.out.println("⚠️ Unrecognized message: " + message);
	}
	}
	@Test(priority = 4, enabled = true)
	public void unregistered_dealer() throws InterruptedException {
		Thread.sleep(3000);
		WebElement scheduleTransfer = driver.findElement(By.xpath("/html/body/div[1]/div/div/section/div/div/div[7]/div/div[2]/ul/li[1]/a"));
		highlightElement(driver, scheduleTransfer);
		scheduleTransfer.click();
		Thread.sleep(3000); // click on schedule transfer link 
		// Click to add
		WebElement addIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]"));
		highlightElement(driver, addIcon);
		addIcon.click();
		Thread.sleep(3000);

		// Enter mobile number
		WebElement sourceMobile = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"));
		highlightElement(driver, sourceMobile);
		sourceMobile.sendKeys("25123502");
		Thread.sleep(3000);

		// Enter destination number
		WebElement destinationMobile = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"));
		highlightElement(driver, destinationMobile);
		destinationMobile.sendKeys("2653625");
		Thread.sleep(3000);

		// Enter the amount
		WebElement amountField = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/input[2]"));
		highlightElement(driver, amountField);
		amountField.sendKeys("4");
		Thread.sleep(3000);

		// Click on calendar
		WebElement calendarIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[4]/div[1]/fieldset[1]/div[1]/i[1]"));
		highlightElement(driver, calendarIcon);
		calendarIcon.click();
		Thread.sleep(3000);

		// Select today's date
		WebElement todaysDate = driver.findElement(By.xpath("//span[normalize-space()='28']"));
		highlightElement(driver, todaysDate);
		todaysDate.click();
		Thread.sleep(3000);

		// Click on frequency dropdown
		WebElement frequencyDropdown = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/fieldset[1]/div[1]/i[1]"));
		highlightElement(driver, frequencyDropdown);
		frequencyDropdown.click();
		Thread.sleep(3000);

		// Select weekly
		WebElement weeklyOption = driver.findElement(By.xpath("//li[@title='Weekly']"));
		highlightElement(driver, weeklyOption);
		weeklyOption.click();
		Thread.sleep(3000);

		// Click on add button
		WebElement addButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/button[1]/*[name()='svg'][1]"));
		highlightElement(driver, addButton);
		addButton.click();
		Thread.sleep(3000);

		// Click on Yes button
		WebElement yesButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]"));
		highlightElement(driver, yesButton);
		yesButton.click();
		Thread.sleep(3000);
		// Wait for the toast message to appear (you may need to adjust the XPath based on actual DOM)
		// Wait for the toast or notification message to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath("//div[contains(@class, 'toast') or contains(@class, 'notification') or contains(text(),'Created Successful') or contains(text(),'Duplicate') or contains(text(),'User not found') or contains(text(),'User does not exist')]")
		));

		String message = toastMsg.getText();
		System.out.println("Popup message: " + message);

		// Message validation
		if (message.contains("Created Successful")) {
		    System.out.println("✅ Transfer creation successful.");
		} else if (message.contains("Duplicate request") || message.contains("Schedule already exists")) {
		    System.out.println("❌ Duplicate schedule. Transfer not created.");
		} else if (message.contains("User not found") || message.contains("User does not exist")) {
		    System.out.println("❌ User does not exist. Test case failed.");
		    Assert.fail("User does not exist.");
		} else {
		    System.out.println("⚠️ Unrecognized message: " + message);
		}

		

	}
	@Test(priority = 5, enabled = true)
	public void unregistered_date() throws InterruptedException, SQLException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement scheduleTransfer = driver.findElement(By.xpath("/html/body/div[1]/div/div/section/div/div/div[7]/div/div[2]/ul/li[1]/a"));
		highlightElement(driver, scheduleTransfer);
		scheduleTransfer.click();
		Thread.sleep(3000);

		WebElement addIcon = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/div/div/div/div/div/div/div/div[2]/div[3]/*[name()='svg'][1]"));
		highlightElement(driver, addIcon);
		addIcon.click();
		Thread.sleep(3000);

		WebElement sourceMobile = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[1]/div/div/div[2]/div/div/fieldset/div/div/input"));
		highlightElement(driver, sourceMobile);
		sourceMobile.sendKeys("2512352");
		Thread.sleep(3000);

		WebElement destinationMobile = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[2]/div/div/div[2]/div/div/fieldset/div/div/input"));
		highlightElement(driver, destinationMobile);
		destinationMobile.sendKeys("2653625");
		Thread.sleep(3000);

		// ✅ Get the MSISDN to verify in DB later
		String msisdnToAdd = destinationMobile.getAttribute("value");

		WebElement amountField = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[3]/div/fieldset/div/div/input[2]"));
		highlightElement(driver, amountField);
		amountField.sendKeys("4");
		Thread.sleep(3000);

		WebElement calendarIcon = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[4]/div/fieldset/div/i[1]"));
		highlightElement(driver, calendarIcon);
		calendarIcon.click();
		Thread.sleep(3000);

		int today = java.time.LocalDate.now().getDayOfMonth();
		List<WebElement> dateOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//span[normalize-space()='" + today + "']")));

		if (dateOptions.size() > 1) {
			highlightElement(driver, dateOptions.get(1));
			dateOptions.get(1).click();
		} else if (dateOptions.size() == 1) {
			highlightElement(driver, dateOptions.get(0));
			dateOptions.get(0).click();
		} else {
			System.out.println("❌ Today's date not found in calendar.");
			Assert.fail("Date not found");
		}

		Thread.sleep(3000);

		WebElement frequencyDropdown = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[5]/div/div/fieldset/div/i[1]"));
		highlightElement(driver, frequencyDropdown);
		frequencyDropdown.click();
		Thread.sleep(3000);

		WebElement weeklyOption = driver.findElement(By.xpath("//li[@title='Weekly']"));
		highlightElement(driver, weeklyOption);
		weeklyOption.click();
		Thread.sleep(3000);

		WebElement addButton = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div[2]/button[1]/*[name()='svg'][1]"));
		highlightElement(driver, addButton);
		addButton.click();
		Thread.sleep(3000);

		WebElement yesButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]"));
		highlightElement(driver, yesButton);
		yesButton.click();
		Thread.sleep(3000);

		WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class, 'toast') or contains(text(),'Created Successful') or contains(text(),'Duplicate') or contains(text(),'User not found')]")));

		String message = toastMsg.getText();
		System.out.println("Popup message: " + message);

		if (message.contains("Created Successful")) {
			System.out.println("✅ Transfer creation successful.");

			// ✅ Now verify in DB
			checkRecordInDB(msisdnToAdd);

		} else if (message.contains("Duplicate") || message.contains("Schedule already exists")) {
			System.out.println("❌ Duplicate schedule. Transfer not created.");
		} else {
			System.out.println("⚠️ Message not recognized: " + message);
		}
	}

	private void checkRecordInDB(String msisdnToAdd) throws SQLException {
		String jdbcUrl = "jdbc:mysql://10.0.5.49:3306/cws_cbx";
		String dbUser = "sndapi";
		String dbPassword = "T55_tssgui";

		Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

		// Add 248 prefix for DB
		String prefixedMsisdn = "248" + msisdnToAdd;

		String query = "SELECT DEST_MSISDN, CREATED_AT FROM qtqr_schd_transfers WHERE DEST_MSISDN = ? ORDER BY CREATED_AT DESC LIMIT 1";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, prefixedMsisdn);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			String destMsisdn = rs.getString("DEST_MSISDN");
			Timestamp createdAt = rs.getTimestamp("CREATED_AT");
			System.out.println("✅ Record added successfully in table 'qtqr_schd_transfers'");
			System.out.println("MSISDN: " + destMsisdn + " | CREATED_AT: " + createdAt);
		} else {
			System.out.println("❌ Data not inserted into the DB for MSISDN: " + prefixedMsisdn);
			Assert.fail("No matching DB record found");
		}

		rs.close();
		pstmt.close();
		conn.close();
	}


	@Test(priority = 6, enabled = true)
	public void User_does_not_exist() throws InterruptedException, SQLException {
	    Thread.sleep(3000);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement scheduleTransfer = driver.findElement(By.xpath("/html/body/div[1]/div/div/section/div/div/div[7]/div/div[2]/ul/li[1]/a"));
	    highlightElement(driver, scheduleTransfer);
	    scheduleTransfer.click();
	    Thread.sleep(3000);

	    WebElement addIcon = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/div/div/div/div/div/div/div/div[2]/div[3]/*[name()='svg'][1]"));
	    highlightElement(driver, addIcon);
	    addIcon.click();
	    Thread.sleep(3000);

	    WebElement sourceMobile = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[1]/div/div/div[2]/div/div/fieldset/div/div/input"));
	    highlightElement(driver, sourceMobile);
	    sourceMobile.sendKeys("2512302");
	    Thread.sleep(3000);

	    WebElement destinationMobile = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[2]/div/div/div[2]/div/div/fieldset/div/div/input"));
	    highlightElement(driver, destinationMobile);
	    destinationMobile.sendKeys("2653627");
	    Thread.sleep(3000);

	    String msisdnToAdd = destinationMobile.getAttribute("value");

	    WebElement amountField = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[3]/div/fieldset/div/div/input[2]"));
	    highlightElement(driver, amountField);
	    amountField.sendKeys("3");
	    Thread.sleep(3000);

	    WebElement calendarIcon = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[4]/div/fieldset/div/i[1]"));
	    highlightElement(driver, calendarIcon);
	    calendarIcon.click();
	    Thread.sleep(3000);

	    int today = java.time.LocalDate.now().getDayOfMonth();
	    List<WebElement> dateOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	            By.xpath("//span[normalize-space()='" + today + "']")));

	    if (dateOptions.size() > 1) {
	        highlightElement(driver, dateOptions.get(1));
	        dateOptions.get(1).click();
	    } else if (dateOptions.size() == 1) {
	        highlightElement(driver, dateOptions.get(0));
	        dateOptions.get(0).click();
	    } else {
	        System.out.println("❌ Today's date not found in calendar.");
	        Assert.fail("Date not found");
	    }

	    Thread.sleep(3000);

	    WebElement frequencyDropdown = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div/div[5]/div/div/fieldset/div/i[1]"));
	    highlightElement(driver, frequencyDropdown);
	    frequencyDropdown.click();
	    Thread.sleep(3000);

	    WebElement weeklyOption = driver.findElement(By.xpath("//li[@title='Weekly']"));
	    highlightElement(driver, weeklyOption);
	    weeklyOption.click();
	    Thread.sleep(3000);

	    WebElement addButton = driver.findElement(By.xpath("/html/body/div/div/div/section/div/div/section/div/div/div[2]/button[1]/*[name()='svg'][1]"));
	    highlightElement(driver, addButton);
	    addButton.click();
	    Thread.sleep(3000);

	    WebElement yesButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]"));
	    highlightElement(driver, yesButton);
	    yesButton.click();
	    Thread.sleep(3000);

	    WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class, 'toast') or contains(text(),'Created Successful') or contains(text(),'Duplicate') or contains(text(),'User not found') or contains(text(),'User does not exist')]")));

	    String message = toastMsg.getText().trim();
	    System.out.println("Popup message: " + message);

	    // ✅ Always check in DB based on the popup
	    checkRecordInDB1(msisdnToAdd, message);
	}


	private void checkRecordInDB1(String msisdnToAdd, String popupMessage) throws SQLException {
	    String jdbcUrl = "jdbc:mysql://10.0.5.49:3306/cws_cbx";
	    String dbUser = "sndapi";
	    String dbPassword = "T55_tssgui";

	    Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
	    String prefixedMsisdn = "248" + msisdnToAdd;

	    String query = "SELECT DEST_MSISDN, CREATED_AT FROM qtqr_schd_transfers WHERE DEST_MSISDN = ? ORDER BY CREATED_AT DESC LIMIT 1";
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setString(1, prefixedMsisdn);

	    ResultSet rs = pstmt.executeQuery();

	    if (popupMessage.contains("User not found") || popupMessage.contains("User does not exist")) {
	        if (rs.next()) {
	            String destMsisdn = rs.getString("DEST_MSISDN");
	            Timestamp createdAt = rs.getTimestamp("CREATED_AT");
	            System.out.println("⚠️ GUI said 'User not found' but record exists in DB.");
	            System.out.println("✅ DB Record: MSISDN = " + destMsisdn + ", CREATED_AT = " + createdAt);
	        } else {
	            System.out.println("❌ GUI message: " + popupMessage);
	            System.out.println("❌ No record found in DB for: " + prefixedMsisdn);
	            Assert.fail("GUI says user not found and DB also has no record.");
	        }
	    } else if (popupMessage.contains("Created Successful")) {
	        if (rs.next()) {
	            String destMsisdn = rs.getString("DEST_MSISDN");
	            Timestamp createdAt = rs.getTimestamp("CREATED_AT");
	            System.out.println("✅ Record found in DB: MSISDN = " + destMsisdn + ", CREATED_AT = " + createdAt);
	        } else {
	            System.out.println("❌ GUI says Created Successful, but no DB entry found for: " + prefixedMsisdn);
	            Assert.fail("DB record missing for successful GUI transaction.");
	        }
	    } else {
	        System.out.println("⚠️ Unknown GUI message: " + popupMessage);
	        // You can optionally still check DB if needed
	    }

	    rs.close();
	    pstmt.close();
	    conn.close();
	}
}
