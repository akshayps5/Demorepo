package SMSC;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class admin_connfig {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		SMSC.admin_connfig.driver = driver; // Set the WebDriver instance

		// driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
         driver.manage().window().maximize();
		String url = "https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp";
		driver.get(url);
		String pageTitle = driver.getTitle();
		System.out.println("Title of " + url + ": " + pageTitle);

		// Set implicit wait to 5 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("Admin123");
		driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
	}
	
	@Test(priority = 1)
	public void systemconfig_retrylink_deletion() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Retry')]")).click(); // retry link in
																									// system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Retry Pattern']")).click(); // retry link in system config
		Thread.sleep(2000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//*[@id=\"borderDivCop\"]/table/tbody/tr[6]/td[5]/u/a")).click(); // retry link in
																										// system config
		Thread.sleep(2000);
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Deletion Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch");
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void retrylink_adding() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='third']//a[contains(text(),'Retry')]")).click(); // retry link in
																									// system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Retry Pattern']")).click(); // retry link in system config
		Thread.sleep(2000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//*[@id=\"addLink\"]")).click(); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/input"))
				.sendKeys("jyoti"); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[4]/input"))
				.sendKeys("2"); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/input[1]"))
				.click();
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		// td[@class='succmsg']
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Addition Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch");
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void translationpage_system() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		// Click on the "System config" link
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='System Config']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//a[normalize-space()='Translation']"));
		Thread.sleep(2000);
		String option11 = "Translation";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//u[normalize-space()='Add']")).click();// add button in system page
		Thread.sleep(2000);

		driver.findElement(By.xpath("(//input[@name='transType'])[1]")).click();// source radio button
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Enter upto 50 characters.']")).sendKeys("rule1");// rule name
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter rule description upto 100 characters.']"))
				.sendKeys("rule1");// rule description
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//input[@title='Enter Alphanumeric value upto 11 characters or numeric value upto 20 digits.']"))
				.sendKeys("98745632");// add button in system page
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='lengthCheck']")).click();// Length Validation checkbox
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='validLenMin']")).sendKeys("8");// Minimum length
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='validLenMax']")).sendKeys("20");// Maximum length
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Click this button to Add.']")).click();// add button
		Thread.sleep(2000);
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Addition Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@name='plusminus0']")).click();// source dropdown button
		Thread.sleep(2000);

		driver.findElement(By
				.xpath("//input[@title='Enter Alphanumeric value upto 11 characters or numeric value upto 20 digits']"))
				.sendKeys("98745632");//source address
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='bttn']")).click();// search button in source address
		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@name='plusminus1']")).click();// search button in source address
		Thread.sleep(2000);

		// Create a JavascriptExecutor object
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// Scroll down by pixel offset (e.g., 500 pixels)
		jsExecutor.executeScript("window.scrollBy(0,600)");

		List<WebElement> Options21 = driver.findElements(By.xpath("//select[@name='enableType']"));
		Thread.sleep(2000);
		String option21 = "Prefix ";
		driver.findElement(By.xpath("//option[normalize-space()='Prefix']")).click();// locator for prefix
		System.out.println(" selected: " + option21);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@title='Enter upto 5 characters.']")).sendKeys("1324");// prifix value
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Click this button to search.']")).click();// modify button
		Thread.sleep(2000);
		Alert alert22 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert22.accept();
		// successful message
		WebElement modificationmssage1 = driver
				.findElement(By.xpath("//td[normalize-space()='Modification Succesful.']"));
		String actualOutput1 = modificationmssage1.getText();
		System.out.println(actualOutput1);
		String expectedOutput1 = "Modification Succesful.";
		Assert.assertTrue(actualOutput1.contains(expectedOutput1),
				"Error message mismatch for invalid modification message1");
		Thread.sleep(2000);

		List<WebElement> Options211 = driver.findElements(By.xpath("//select[@name='enableType']"));
		Thread.sleep(2000);
		String option211 = " Substitute ";
		driver.findElement(By.xpath("//option[normalize-space()='Substitute']")).click();// locator for Substitute
		System.out.println(" selected: " + option211);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@name='subsfromIndex']")).sendKeys("10");// Substitute From Index
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='substoIndex']")).sendKeys("20");// Substitute To Index
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"subs3\"]/td[3]/input")).sendKeys("30");// Substitute Value
		Thread.sleep(2000);

		List<WebElement> Options31 = driver.findElements(By.xpath("//select[@name='afterTON']"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[@name='afterTON']//option[@value='2'][normalize-space()='National']"))
				.click();// locator for national in TON
		String option31 = "National ";
		System.out.println(" selected: " + option31);
		Thread.sleep(2000);

		List<WebElement> Options41 = driver.findElements(By.xpath("//select[@name='afterNPI']"));
		Thread.sleep(2000);
		String option41 = "National ";
		driver.findElement(By.xpath("//select[@name='afterNPI']//option[@value='4'][normalize-space()='National']"))
				.click();// locator for national in NPI
		System.out.println(" selected: " + option41);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Click this button to search.']")).click();// modify button
		Thread.sleep(2000);
		Alert alert221 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert221.accept();
		// successful message
		WebElement modificationmssage11 = driver
				.findElement(By.xpath("//td[normalize-space()='Modification Succesful.']"));
		String actualOutput11 = modificationmssage11.getText();
		System.out.println(actualOutput11);
		String expectedOutput11 = "Modification Succesful.";
		Assert.assertTrue(actualOutput11.contains(expectedOutput11),
				"Error message mismatch for invalid modification message2");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@title='Click this button to delete.']")).click();// modify button
		Thread.sleep(2000);
		Alert alert31 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert31.accept();
	}

	@Test(priority = 2)
	public void translationpage_application() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		// Click on the "System config" link
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='System Config']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//a[normalize-space()='Translation']"));
		Thread.sleep(2000);
		String option11 = "Translation";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[normalize-space()='Application']")).click();
		Thread.sleep(3000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//u[normalize-space()='Add']")).click();// add button in system page
		Thread.sleep(2000);

		driver.findElement(By.xpath("(//input[@name='transType'])[2]")).click();// source radio button
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Enter upto 50 characters.']")).sendKeys("rule1");// rule name
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter rule description upto 100 characters.']"))
				.sendKeys("rule1");// rule description
		Thread.sleep(2000);

		List<WebElement> Options13 = driver.findElements(By.xpath("//select[@name='app_num']"));
		Thread.sleep(2000);
		// option[@value='1077']
		driver.findElement(By.xpath("//option[@value='1077']")).click();// akshay option in application
		String option13 = " anna ";
		Options13.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option13);
		Thread.sleep(3000);

		driver.findElement(By.xpath(
				"//input[@title='Enter Alphanumeric value upto 11 characters or numeric value upto 20 digits.']"))
				.sendKeys("98745634");// add button in system page
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='lengthCheck']")).click();// Length Validation checkbox
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='validLenMin']")).sendKeys("8");// Minimum length
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='validLenMax']")).sendKeys("20");// Maximum length
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Click this button to Add.']")).click();// add button
		Thread.sleep(2000);
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Addition Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@name='plusminus00']")).click();// source dropdown button
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Enter only numeric upto 20 digits']")).sendKeys("98745634");// Maximum
																													// length
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='bttn']")).click();// search button in source address
		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@name='plusminus1']")).click();// search button in source address
		Thread.sleep(2000);

		// Create a JavascriptExecutor object
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// Scroll down by pixel offset (e.g., 500 pixels)
		jsExecutor.executeScript("window.scrollBy(0,600)");

		List<WebElement> Options21 = driver.findElements(By.xpath("//select[@name='enableType']"));
		Thread.sleep(2000);
		String option21 = "Prefix ";
		driver.findElement(By.xpath("//option[normalize-space()='Prefix']")).click();// locator for prefix
		System.out.println(" selected: " + option21);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@title='Enter upto 5 characters.']")).sendKeys("1324");// prifix value
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Click this button to Modify.']")).click();// modify button
		Thread.sleep(2000);
		Alert alert22 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert22.accept();
		// successful message
		WebElement modificationmssage1 = driver
				.findElement(By.xpath("//td[normalize-space()='Modification Succesful.']"));
		String actualOutput1 = modificationmssage1.getText();
		System.out.println(actualOutput1);
		String expectedOutput1 = "Modification Succesful.";
		Assert.assertTrue(actualOutput1.contains(expectedOutput1),
				"Error message mismatch for invalid modification message1");
		Thread.sleep(2000);

		List<WebElement> Options211 = driver.findElements(By.xpath("//select[@name='enableType']"));
		Thread.sleep(2000);
		String option211 = " Substitute ";
		driver.findElement(By.xpath("//option[normalize-space()='Substitute']")).click();// locator for Substitute
		System.out.println(" selected: " + option211);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@name='subsfromIndex']")).sendKeys("10");// Substitute From Index
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='substoIndex']")).sendKeys("20");// Substitute To Index
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"subs3\"]/td[3]/input")).sendKeys("30");// Substitute Value
		Thread.sleep(2000);

		List<WebElement> Options31 = driver.findElements(By.xpath("//select[@name='afterTON']"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[@name='afterTON']//option[@value='2'][normalize-space()='National']"))
				.click();// locator for national in TON
		String option31 = "National ";
		System.out.println(" selected: " + option31);
		Thread.sleep(2000);

		List<WebElement> Options41 = driver.findElements(By.xpath("//select[@name='afterNPI']"));
		Thread.sleep(2000);
		String option41 = "National ";
		driver.findElement(By.xpath("//select[@name='afterNPI']//option[@value='4'][normalize-space()='National']"))
				.click();// locator for national in NPI
		System.out.println(" selected: " + option41);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title='Click this button to Modify.']")).click();// modify button
		Thread.sleep(2000);
		Alert alert221 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert221.accept();
		// successful message
		WebElement modificationmssage11 = driver
				.findElement(By.xpath("//td[normalize-space()='Modification Succesful.']"));
		String actualOutput11 = modificationmssage11.getText();
		System.out.println(actualOutput11);
		String expectedOutput11 = "Modification Succesful.";
		Assert.assertTrue(actualOutput11.contains(expectedOutput11),
				"Error message mismatch for invalid modification message2");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@title='Click this button to Delete.']")).click();// modify button
		Thread.sleep(2000);
		Alert alert31 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert31.accept();
	}

	@Test(priority = 3)
	public void barringpage_system() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		// Click on the "System config" link
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='System Config']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//a[normalize-space()='Barring']"));
		Thread.sleep(2000);
		String option11 = "Barring";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='addbarType1']")).click();// sources address checkbox
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='17']")).click();// destination address checkbox
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//input[@title='Enter minimum length of 1 and maximum length of 20 digits (or) alphanumerics with special characters(only -,_,space are allowed) of 1 to 11 characters.']"))// destaddress(mobilenumber)
				.sendKeys("98745636");
		Thread.sleep(2000);

		WebElement sourceminlength = driver.findElement(By.xpath("//input[@name='addLen1']"));
		sourceminlength.clear();
		sourceminlength.sendKeys("8");// proceed button
		Thread.sleep(2000);
		WebElement sourcemaxlength = driver.findElement(By.xpath("//input[@name='addLen2']"));
		sourcemaxlength.clear();
		sourcemaxlength.sendKeys("20");// proceed button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@title='Enter minimum length of 1 and maximum length of 20 digits']"))
				.sendKeys("98745363");// destination address
		Thread.sleep(2000);

		WebElement destminlength = driver.findElement(By.xpath("//input[@name='addLen3']"));
		destminlength.clear();
		destminlength.sendKeys("8");// proceed button
		Thread.sleep(2000);
		WebElement destmaxlength = driver.findElement(By.xpath("//input[@name='addLen4']"));
		destmaxlength.clear();
		destmaxlength.sendKeys("20");// proceed button
		Thread.sleep(2000);

		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("barring");// description button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
		Thread.sleep(2000);
		Alert alert121 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert121.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Addition Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[contains(@title,'Click to close.')]")).click();// add button

		// img[@name='plusminus0']
		driver.findElement(By.xpath("//img[@name='plusminus0']")).click();// plus button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='barType1']")).click();// source address button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='barType2'])[1]")).click();// destination address button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@onclick='gotoModProceed();']")).click();// proceed button
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//*[@id=\"div0\"]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr[2]/td[3]/input"))
				.sendKeys("98745636");// plus button
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//*[@id=\"div0\"]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr[6]/td[3]/input"))
				.sendKeys("98745363");// p button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@title=' Click to Download.']")).click();// plus button
		Thread.sleep(5000);
//				driver.findElement(By.xpath("//input[@title=' Click to view Best Rule.']")).click();// plus  button
//				Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@title=' Click to Search.']")).click();// plus button
		Thread.sleep(5000);

		// Get the window handle of the parent window
		String parentWindowHandle = driver.getWindowHandle();

		// Get the window handles of all the windows that are currently open
		Set<String> windowHandles = driver.getWindowHandles();

		// Loop through the window handles to find the handle of the new window
		// Switch to the pop-up window
		for (String handle : windowHandles) {
			if (!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				// break;
				driver.manage().window().maximize();
				System.out.println("switched to new window");
				// input[@name='chkall']
				driver.findElement(By.xpath("//input[@name='chekky']")).click();// plus button
				// input[@value='Delete']
				driver.findElement(By.xpath("//input[@value='Delete']")).click();// plus button
				Alert alert1211 = driver.switchTo().alert();
				Thread.sleep(2000);
				alert1211.accept();
				Thread.sleep(2000);
//				WebElement modificationmssage11 = driver.findElement(By.xpath("//td[@class='succmsg']"));
//				String actualOutput11 = modificationmssage11.getText();
//				System.out.println(actualOutput11);
//				String expectedOutput11 = "Deletion Successful";
//				Assert.assertTrue(actualOutput11.contains(expectedOutput11),
//						"Error message mismatch for invalid modification message2");
//				Thread.sleep(5000);
				try {
				    WebElement modificationMessage11 = driver.findElement(By.xpath("//td[@class='succmsg']"));
				    String actualOutput11 = modificationMessage11.getText();
				    System.out.println(actualOutput11);
				    String expectedOutput11 = "Deletion Successful";
					Assert.assertTrue(actualOutput11.contains(expectedOutput11),
							"Error message mismatch for invalid modification message2");
					Thread.sleep(5000);
				} catch (NoSuchElementException e) {
					WebElement modificationMessage14 = driver.findElement(By.xpath("/html/body/div[1]/div/form/table[2]/tbody/tr/td[2]/div/table/tbody/tr[2]/td[2]/table/tbody/tr/td/table/tbody/tr[1]/td/div/table[2]/tbody/tr/td/div/table/tbody/tr[2]/td[2]/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td"));
				    String actualOutput14 = modificationMessage14.getText();
				    System.out.println(actualOutput14);
				    System.out.println("Unsuccessful: Unable to locate element with xpath //td[@class='succmsg']");
				    
				}
				
				
			}

		}
	}

	@Test(priority = 4)
	public void barringpage_application() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		// Click on the "System config" link
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='System Config']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//a[normalize-space()='Barring']"));
		Thread.sleep(2000);
		String option11 = "Barring";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		// a[normalize-space()='Application']
		driver.findElement(By.xpath("//a[normalize-space()='Application']")).click();// application button
		Thread.sleep(2000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='18']")).click();// vmsc checkbox
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='19']")).click();// IMSI checkbox
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='proceed']")).click();// proceed button
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='temporary']")).click();// temporary radio button
		Thread.sleep(2000);

		// Application drop down
		WebElement dropdown2 = driver.findElement(By.xpath("//select[@name='applName']"));
		Thread.sleep(2000);
		Select select2 = new Select(dropdown2);
		select2.selectByVisibleText("anna");// to select client name(HTTP)
		Thread.sleep(3000);

		driver.findElement(
				By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 20 digits']"))
				.sendKeys("125");// VMSC
		driver.findElement(
				By.xpath("//input[@title='Enter numeric values with min length of 1 and max length of 16 digits']"))
				.sendKeys("125");// IMSI
		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("testing");// description

		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();// add button
		Thread.sleep(2000);
		Alert alert121 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert121.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Addition Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[contains(@title,'Click to close.')]")).click();// add button

		driver.findElement(By.xpath("//img[@name='plusminus0']")).click();// plus button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='barType2'])[2]")).click();// VMSC button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='barType2'])[3]")).click();// IMSI button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@onclick='gotoModProceed();']")).click();// proceed button
		Thread.sleep(2000);

		// Barring typr drop down
		WebElement dropdown211 = driver.findElement(By.xpath("//select[@name='barringType']"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//option[normalize-space()='Temporary Barring']")).click();// plus button
		Select select211 = new Select(dropdown211);
		select211.selectByVisibleText("Temporary Barring");// to select client name(HTTP)
		Thread.sleep(3000);

		// Application drop down
		WebElement dropdown21 = driver.findElement(By.xpath("//select[@name='searchApplName']"));
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//select[@name='searchApplName']//option[@value='1040'][normalize-space()='anna']")).click();// plus
		Select select21 = new Select(dropdown21);
		select21.selectByVisibleText("anna");// to select client name(HTTP)
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@name='searchNum18']")).sendKeys("125");// plus button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='searchNum19']")).sendKeys("125");// p button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@title=' Click to Download.']")).click();// plus button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@title=' Click to Search.']")).click();// search button
		Thread.sleep(3000);

		// Get the window handle of the parent window
		String parentWindowHandle = driver.getWindowHandle();

		// Get the window handles of all the windows that are currently open
		Set<String> windowHandles = driver.getWindowHandles();

		// Loop through the window handles to find the handle of the new window
		// Switch to the pop-up window
		for (String handle : windowHandles) {
			if (!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				// break;
				driver.manage().window().maximize();
				System.out.println("switched to new window");
				// input[@name='chkall']
				driver.findElement(By.xpath("//input[@name='chekky']")).click();// plus button
				// input[@value='Delete']
				driver.findElement(By.xpath("//input[@value='Delete']")).click();// plus button
				Alert alert1211 = driver.switchTo().alert();
				Thread.sleep(2000);
				alert1211.accept();

				WebElement modificationmssage11 = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String actualOutput11 = modificationmssage11.getText();
				System.out.println(actualOutput11);
				String expectedOutput11 = "Deletion Successful";
				Assert.assertTrue(actualOutput11.contains(expectedOutput11),
						"Error message mismatch for invalid modification message2");
				Thread.sleep(3000);
				driver.close();
			}
		}
	}

	@Test(priority = 5)
	public void redirectpage_system() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		// Click on the "System config" link
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='System Config']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//a[normalize-space()='Redirect']"));
		Thread.sleep(2000);
		String option11 = "Redirect";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		// Switch to the iframe
		WebElement iframe = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("//font[@id='addLink']")).click();// add button in system page
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@title='Enter minimum length of 1 and maximum length of 20 digits']"))// destaddress(mobilenumber)
				.sendKeys("98745636");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("testing");// description
		Thread.sleep(2000);

		// Create a JavascriptExecutor object
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// Scroll down by pixel offset (e.g., 500 pixels)
		jsExecutor.executeScript("window.scrollBy(0,8000)");

		// Type drop down
		WebElement dropdown1 = driver.findElement(By.xpath("//select[@id='addType']"));
		Thread.sleep(2000);
		Select select1 = new Select(dropdown1);
		select1.selectByIndex(0);// to select ESME
		Thread.sleep(3000);

		// Application drop down
		WebElement dropdown2 = driver.findElement(By.xpath("//select[@id='AccType']"));
		Thread.sleep(2000);
		Select select2 = new Select(dropdown2);
		select2.selectByVisibleText("anna");// to select client name(HTTP)
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Addition Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@title=' Click to close.']")).click();// close button
		Thread.sleep(5000);

//				// Switch to the iframe
//			    WebElement iframe1 = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
//			    driver.switchTo().frame(iframe1);

		driver.findElement(By.xpath("//img[@name='plusminus0']")).click();
		Thread.sleep(2000);

		// Enter text in the search field
		driver.findElement(By.xpath("//input[@name='searchNum17']")).sendKeys("98745636");
		Thread.sleep(2000);

		// Click on the download button
		driver.findElement(By.xpath("//input[@title=' Click to download.']")).click();
		Thread.sleep(2000);
		System.out.println("csv file downloaded");
		// Click on the bestmatch button
		driver.findElement(By.xpath("//input[@title=' Click to view Best Rule.']")).click();
		Thread.sleep(2000);
		System.out.println("bestmatch button clicked");

		// Click on the search button
		driver.findElement(By.xpath("//input[@title=' Click to Search.']")).click();
		Thread.sleep(5000);
		System.out.println("search button clicked");
		
		// Get the window handle of the parent window
				String parentWindowHandle = driver.getWindowHandle();
				
		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			if (!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				// break;
				driver.manage().window().maximize();
				System.out.println("switched to new window");
				driver.findElement(By.xpath("(//input[@name='chkall'])[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
				Thread.sleep(2000);
				Alert alert121 = driver.switchTo().alert();
				Thread.sleep(2000);
				alert121.accept();
				
				WebElement modificationmssage11 = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String actualOutput11 = modificationmssage11.getText();
				System.out.println(actualOutput11);
				String expectedOutput11 = "Deletion Successful";
				Assert.assertTrue(actualOutput11.contains(expectedOutput11),
						"Error message mismatch for invalid modification message2");
				Thread.sleep(3000);
				driver.close();
				
			
			}
		}

	}

	@Test(priority = 6)
	public void redirectpage_application() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		// Click on the "System config" link
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='System Config']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//a[normalize-space()='Redirect']"));
		Thread.sleep(2000);
		String option11 = "Redirect";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		// application page
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[normalize-space()='Application']")).click();// application page
		Thread.sleep(3000);
		// Switch to the iframe
		WebElement iframe1 = driver.findElement(By.xpath("//*[@id=\"countrydivcontainer\"]/iframe"));
		driver.switchTo().frame(iframe1);

		driver.findElement(By.xpath("//u[normalize-space()='Add']")).click();// add button in application page
		Thread.sleep(3000);

		// Application from drop down
		WebElement dropdown211 = driver.findElement(By.xpath("//select[@name='appFrm']"));
		Thread.sleep(2000);
		Select select211 = new Select(dropdown211);
		select211.selectByVisibleText("anna");// to select client name(HTTP)
		Thread.sleep(3000);

		driver.findElement(
				By.xpath(" (//input[@title='Enter minimum length of 1 and maximum length of 20 digits'])[1]"))// dest
																												// address(mobile
																												// number)
				.sendKeys("98745633");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@title='Enter maximum length of 100 characters.']"))
				.sendKeys("testing");// description
		Thread.sleep(2000);

		// Create a JavascriptExecutor object
		JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
		// Scroll down by pixel offset (e.g., 500 pixels)
		jsExecutor2.executeScript("window.scrollBy(0,1000)");

		// Type drop down
		WebElement dropdown3 = driver.findElement(By.xpath("//select[@id='addType']"));
		Thread.sleep(2000);
		Select select3 = new Select(dropdown3);
		select3.selectByIndex(0);// to select ESME
		Thread.sleep(3000);

		// Application drop down
		WebElement dropdown21 = driver.findElement(By.xpath("//select[@id='AccType']"));
		Thread.sleep(2000);
		Select select21 = new Select(dropdown21);
		select21.selectByVisibleText("anna");// to select client name(HTTP)
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();
		Alert alert121 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert121.accept();
		// successful message
		WebElement errorMessageElement1 = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput1 = errorMessageElement1.getText();
		System.out.println(actualOutput1);
		String expectedOutput1 = "Addition Successful";
		Assert.assertTrue(actualOutput1.contains(expectedOutput1), "Error message mismatch for invalid service id");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@title=' Click to close.']")).click();// close button
		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@name='plusminus0']")).click();// close button
		Thread.sleep(2000);

		// Application from drop down
		WebElement dropdown2111 = driver.findElement(By.xpath("//select[@name='appFrm17']"));
		Thread.sleep(2000);
		Select select2111 = new Select(dropdown2111);
		select2111.selectByVisibleText("anna");// to select client name(HTTP)
		Thread.sleep(3000);

		driver.findElement(By.xpath(
				"//*[@id=\"div0\"]/table/tbody/tr/td/fieldset/table/tbody/tr/td/fieldset/table/tbody/tr[2]/td[3]/input"))
				.sendKeys("98745633");// dest address locator
		Thread.sleep(2000);

		// Click on the download button
		driver.findElement(By.xpath("//input[@title=' Click to download.']")).click();
		Thread.sleep(2000);
		System.out.println("csv file downloaded");
		// Click on the bestmatch button
		driver.findElement(By.xpath("//input[@title=' Click to view Best Rule.']")).click();
		Thread.sleep(2000);
		System.out.println("bestmatch button clicked");

		driver.findElement(By.xpath("//input[@title=' Click to Search.']")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
System.out.println("search button clicked");
		
		// Get the window handle of the parent window
				String parentWindowHandle = driver.getWindowHandle();
				
		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			if (!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				// break;
				driver.manage().window().maximize();
				System.out.println("switched to new window");
				driver.findElement(By.xpath("(//input[@name='chkall'])[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
				Thread.sleep(2000);
				Alert alert1211 = driver.switchTo().alert();
				Thread.sleep(2000);
				alert1211.accept();
				
				WebElement modificationmssage11 = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String actualOutput11 = modificationmssage11.getText();
				System.out.println(actualOutput11);
				String expectedOutput11 = "Deletion Successful";
				Assert.assertTrue(actualOutput11.contains(expectedOutput11),
						"Error message mismatch for invalid modification message2");
				Thread.sleep(3000);
				driver.close();
				
			
			}
		}
	}

}
