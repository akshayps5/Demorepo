//test cases covered 1)retrylink 2)translation 3)redirect 4)barring
package smsc_demo;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class admin_config {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		smsc_demo.admin_config.driver = driver; // Set the WebDriver instance

		// driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");

		String url = "https://10.0.0.36:8443/smsc/welcome/jsp/cluster_Main.jsp";
		driver.get(url);
		String pageTitle = driver.getTitle();
		System.out.println("Title of " + url + ": " + pageTitle);

		// Set implicit wait to 5 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
		driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
		driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
	}
	
	@Test(priority = 1)
	public void retrylink_deletion() throws InterruptedException {
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

		driver.findElement(By.xpath("(//a[@href='#'][normalize-space()='Delete'])[5]")).click(); // retry link in
																										// system config
		Thread.sleep(2000);
		Alert alert12 = driver.switchTo().alert();
		alert12.accept();
		// successful message
		WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput = errorMessageElement.getText();
		System.out.println(actualOutput);
		String expectedOutput = "Deletion Successful";
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);
	}
	@Test(priority = 2)
	public void retrylink_adding_guidemo() throws InterruptedException {
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
				.sendKeys("guidemo"); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[4]/input"))
				.sendKeys("2"); // retry link in system config
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/input[1]"))
				.click();
		Alert alert12 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert12.accept();
		//td[@class='succmsg']
		// successful message
					WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
					String actualOutput = errorMessageElement.getText();
					System.out.println(actualOutput);
					String expectedOutput = "Addition Successful";
					Assert.assertTrue(actualOutput.contains(expectedOutput), "Error message mismatch for invalid service id");
					Thread.sleep(2000);
	}	
	@Test(priority = 3)
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
				.sendKeys("98745632");// Maximum length
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
	@Test(priority = 4)
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

	@Test(priority = 5)
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
		destminlength.sendKeys("8");//  button
		Thread.sleep(2000);
		WebElement destmaxlength = driver.findElement(By.xpath("//input[@name='addLen4']"));
		destmaxlength.clear();
		destmaxlength.sendKeys("20");//  button
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

				WebElement modificationmssage11 = driver.findElement(By.xpath("//td[@class='succmsg']"));
				String actualOutput11 = modificationmssage11.getText();
				System.out.println(actualOutput11);
				String expectedOutput11 = "Deletion Successful";
				Assert.assertTrue(actualOutput11.contains(expectedOutput11),
						"Error message mismatch for invalid modification message2");
				Thread.sleep(5000);
			}

		}
	}

	@Test(priority = 6)
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

	@Test(priority = 7)
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

	@Test(priority = 8)
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
	
	
	

@Test(priority = 9)
	public void shortmessage_group() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);

		// Click on the "System config" link
		Actions actions = new Actions(driver);
		WebElement systemConfigHover = driver.findElement(
				By.xpath("//a[@onmouseover=\"$(this).css('cursor','pointer');\"][normalize-space()='Short Msg']"));
		actions.moveToElement(systemConfigHover).perform();
		List<WebElement> Options1 = driver.findElements(By.xpath("//li[@id='3_1']//a[contains(text(),'Group')]"));
		Thread.sleep(2000);
		String option11 = "Group";
		Options1.get(0).click(); // selecting Barring option in system config list
		System.out.println(" selected: " + option11);
		Thread.sleep(3000);

		// Switch to the iframe  //iframe[@name='_ddajaxtabsiframe-countrydivcontainer']
		WebElement iframe1 = driver.findElement(By.xpath("//iframe[@name='_ddajaxtabsiframe-countrydivcontainer']"));
		driver.switchTo().frame(iframe1);
		
		//add link
		driver.findElement(By.xpath("//u[normalize-space()='Add']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[5]/input")).sendKeys("guidemo");
		Thread.sleep(2000);

		// group type drop down
		WebElement dropdown11 = driver.findElement(By.xpath("//select[@id='groptype']"));
		Thread.sleep(2000);
		Select select11 = new Select(dropdown11);
		select11.selectByVisibleText("Open CUG");// to select client name(HTTP)
		Thread.sleep(1000);
		
		// type drop down
				WebElement dropdown21 = driver.findElement(By.xpath("//select[@id='membtype']"));
				Thread.sleep(2000);
				Select select21 = new Select(dropdown21);
				select21.selectByVisibleText("Default VP ");// to select client name(HTTP)
				Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[5]/td[5]/input")).sendKeys("98745632");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@title=' Click to add.']")).click();
		Thread.sleep(2000);
		Alert alert1211 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert1211.accept();
		
		WebElement modificationmssage11 = driver.findElement(By.xpath("//td[@class='succmsg']"));
		String actualOutput11 = modificationmssage11.getText();
		System.out.println(actualOutput11);
		String expectedOutput11 = "Addition Successful";
		Assert.assertTrue(actualOutput11.contains(expectedOutput11),
				"Error message mismatch for invalid success message");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@title='Click to close ']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//img[@name='plusminus1']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='black1']")).click();
		Thread.sleep(2000);
		
		//  drop down
		WebElement dropdown31 = driver.findElement(By.xpath("//select[@id='membtype1']"));
		Thread.sleep(2000);
		Select select31 = new Select(dropdown31);
		select31.selectByVisibleText("Normal");// to select normal
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@title='click to Modify']")).click();
		Thread.sleep(2000);
		Alert alert21 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert21.accept();
	
		
		WebElement modificationmssage21 = driver.findElement(By.xpath("//td[normalize-space()='Modification Successful']"));
		String actualOutput21 = modificationmssage21.getText();
		System.out.println(actualOutput21);
		String expectedOutput21 = "Modification Successful";
		Assert.assertTrue(actualOutput21.contains(expectedOutput21),
				"Error message mismatch for invalid modification message");
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//input[@name='black1']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@title='click to delete']")).click();
		Thread.sleep(2000);
		Alert alert31 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert31.accept();
		
		WebElement deletemssage = driver.findElement(By.xpath("//*[@id=\"borderDiv1\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td"));
		String actualOutput31 = deletemssage.getText();
		System.out.println(actualOutput31);
//		String expectedOutput31 = "Deletion Successful";
//		Assert.assertTrue(actualOutput31.contains(expectedOutput31),
//				"Error message mismatch for invalid delete message for MSISDN");
		Thread.sleep(5000);
		
		
		driver.findElement(By.xpath("//input[@value='Delete Group']")).click();
		Thread.sleep(2000);
		Alert alert41 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert41.accept();
	
		WebElement deletemssage1 = driver.findElement(By.xpath("(//td[@class='succmsg'])[1]"));
		String actualOutput41 = deletemssage1.getText();
		System.out.println(actualOutput41);
		String expectedOutput41 = "Deletion Successful";
		Assert.assertTrue(actualOutput41.contains(expectedOutput41),
				"Error message mismatch for invalid delete message for group delete");
		Thread.sleep(2000);
	}

@Test(priority = 10)
public void SMS_sending_binaryformat_mobile() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
			Thread.sleep(2000);

			driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // binary message
			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
			driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
					.sendKeys("05000368656C6C6F");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='mobNo']")).sendKeys("97512345678");// mobile number
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String s1 = errorMessageElement.getText();
			System.out.println(s1);
			int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");

		}
	}
}

@Test(priority = 11)
public void SMS_sending_binaryformat_fileupload() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
			Thread.sleep(2000);

			driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // binary message
			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
			driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
					.sendKeys("05000368656C6C6F");
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[8]/td[1]/input")).click(); // file upload radio button
			Thread.sleep(2000);
			
			String projectPath = System.getProperty("user.dir");
			String filePath = projectPath + "\\files\\demo.txt";

			// Check if the file exists
			File file = new File(filePath);
			if (file.exists()) {
				WebElement fileInput = driver.findElement(By.xpath("//input[@title='Select File to be uploaded with one mobile no. per line']"));
				fileInput.sendKeys(filePath);
				// Wait for the file to be uploaded (you may need to adjust the wait time)
				Thread.sleep(2000);

				// Continue with your code
			} else {
				System.out.println("File not found: " + filePath);
			}
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value=' Yes ']")).click();
			
			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String s1 = errorMessageElement.getText();
			System.out.println(s1);
			int endIndex = s1.indexOf("Reference Number for uploaded file");
			String trimmedString = s1.substring(0, endIndex);
			System.out.println(trimmedString);
			String expectedOutput = "Reference Number for uploaded file";
			Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
			Thread.sleep(3000);

		}
	}
}

@Test(priority = 12)
public void SMS_sending_binaryformat_distributionlist() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
			Thread.sleep(2000);

			driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // binary message
			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
			driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
					.sendKeys("05000368656C6C6F");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio button
			Thread.sleep(2000);
			WebElement dropdown31 = driver.findElement(By.xpath("//select[@name='distList']"));
			Thread.sleep(2000);
			Select select31 = new Select(dropdown31);
			select31.selectByIndex(1);// to select message name as tashi

			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value=' Yes ']")).click();
		 	//Msg Sent To : [smsc16 ] Reference : 1112
			
			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String s1 = errorMessageElement.getText();
			System.out.println(s1);
			int endIndex = s1.indexOf("//Msg Sent To : [smsc16 ]");
			// Trim the string to keep only the part until the specified index
			String trimmedString = s1.substring(0, endIndex);
			System.out.println(trimmedString);
			String expectedOutput = "//Msg Sent To : [smsc16 ]";
			Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
			Thread.sleep(3000);

		}
	}
}
@Test(priority = 13)
public void SMS_sending_unicodeformat_mobile() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
			Thread.sleep(2000);

			driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // binary message
			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
			driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='mobNo']")).sendKeys("97512345678");// mobile number
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String s1 = errorMessageElement.getText();
			System.out.println(s1);
			int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");
			// Trim the string to keep only the part until the specified index
			String trimmedString = s1.substring(0, endIndex);
			System.out.println(trimmedString);
			String expectedOutput = "Msg sent to : [97512345678 ]";
			Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
			Thread.sleep(3000);
		}
	}
}

@Test(priority = 14)
public void SMS_sending_unicodeformat_distributionlist() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
			Thread.sleep(2000);

			driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // binary message
			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys("good message");// messagedescription
			driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys("112245566778899AABBCCDDEEFF");// text
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio button
			Thread.sleep(2000);
			WebElement dropdown31 = driver.findElement(By.xpath("//select[@name='distList']"));
			Thread.sleep(2000);
			Select select31 = new Select(dropdown31);
			select31.selectByIndex(1);// to select message name as tashi
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String s1 = errorMessageElement.getText();
			System.out.println(s1);
			int endIndex = s1.indexOf("Msg sent to : [97512345678 ]");
			// Trim the string to keep only the part until the specified index
			String trimmedString = s1.substring(0, endIndex);
			System.out.println(trimmedString);
			String expectedOutput = "Msg sent to : [97512345678 ]";
			Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
			Thread.sleep(3000);
		}
	}
}

@Test(priority = 15)
public void SMS_sending_cannedmessage() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // user name
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@class='small_button_red']")).click(); // login button
			Thread.sleep(2000);

			driver.findElement(By.xpath("//a[normalize-space()='Canned Message']")).click(); // canned message
			driver.findElement(By.xpath("//input[@title='Enter Message Description']")).sendKeys("good message"); // message
																													// description
			// message name
			WebElement dropdown31 = driver.findElement(By.xpath("//select[@title='select message name']"));
			Thread.sleep(2000);
			Select select31 = new Select(dropdown31);
			select31.selectByIndex(1);// to select message name as tashi
			Thread.sleep(2000);

			String projectPath = System.getProperty("user.dir");
			String filePath = projectPath + "\\files\\demo.txt";

			// Check if the file exists
			File file = new File(filePath);
			if (file.exists()) {
				WebElement fileInput = driver.findElement(By.xpath("//input[@title='Select File to be uploaded']"));
				fileInput.sendKeys(filePath);
				// Wait for the file to be uploaded (you may need to adjust the wait time)
				Thread.sleep(2000);

				// Continue with your code
			} else {
				System.out.println("File not found: " + filePath);
			}

			driver.findElement(By.xpath("//*[@id=\"SendLink\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value=' Yes ']")).click();

			WebElement errorMessageElement = driver.findElement(By.xpath("//td[@class='succmsg']"));
			String s1 = errorMessageElement.getText();
			System.out.println(s1);
			int endIndex = s1.indexOf("Reference Number for this File is 1110");
			// Trim the string to keep only the part until the specified index
			String trimmedString = s1.substring(0, endIndex);
			System.out.println(trimmedString);
			String expectedOutput = "Reference Number for this File is 1110";
			Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for actualOutput from expectedOutput");
			Thread.sleep(3000);
		}
	}
}

@Test(priority = 16)
public void SMS_configuation_page_listmgmt_cannedmsg_addinglist() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {
			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Configuration']")).click();// configuration message
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='Add']")).click();
			driver.findElement(By.xpath("//*[@id=\"one\"]/div/table/tbody/tr[3]/td/table/tbody/tr[1]/td/input"))
					.sendKeys("smsc1"); // addlist1
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//body[1]/table[1]/tbody[1]/tr[4]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/form[1]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/div[2]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/input[1]"))
					.click();//
			driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
			Thread.sleep(5000);
			// add number list1
			driver.findElement(By.xpath("(//input[@name='button'])[8]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body//input[@name='msisdn']")).sendKeys("97596385278");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@name='button'])[9]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/input[1]")).click();
			Thread.sleep(2000);

			// canned message
			driver.findElement(By.xpath("//a[normalize-space()='Canned Msg.']")).click();// canned message

			driver.findElement(By.xpath("//*[@id=\"button\"]")).click();
			driver.findElement(By.xpath("//input[@name='msgname']")).sendKeys("tashi1"); // addlist1
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Place holders should be like __$1__,__$2__ format.']"))
					.sendKeys("tashi__$1__"); // addlist1
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"/html/body/table/tbody/tr[4]/td/table/tbody/tr/td[2]/div/form/div[2]/div/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/div[2]/div/table/tbody/tr[3]/td/table/tbody/tr[8]/td/table/tbody/tr/td[1]/input"))
					.click();

			driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
			Thread.sleep(2000);
			WebElement success = driver.findElement(By.xpath("//*[@id=\"addMessage\"]/td/table/tbody/tr/td"));
			String s1 = success.getText();
			System.out.println(s1);
			String expectedOutput = "Addition Successful";
			Assert.assertTrue(s1.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(4000);
		}
	}
}

@Test(priority = 17)
public void SMSpage_binary_text_unicode_canned_inputverify() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
			Thread.sleep(2000);

			driver.findElement(By.xpath("//td[@class='nav_td_border_select']")).click();// username
			// sms page
			WebElement textElement1 = driver.findElement(By.xpath("//td[normalize-space()='Send Text Messages']"));
			String s1 = textElement1.getText();
			System.out.println(s1);
			System.out.println("SMS text page displayed");
			driver.findElement(By.xpath("//*[@id=\"ViewLink1\"]")).click(); // select messages
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@title='Click here to Close']")).click(); // select messages close
																							// button

			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/");// message
			// description
			driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");// text
			Thread.sleep(2000);

			Thread.sleep(2000);
			driver.findElement(By.xpath("//tbody/tr/td[2]/input[2]")).click(); // Concatenate radio button
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@name='messageType'])[3]")).click(); // split radio button
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input[1]"))
					.click(); // single radio button
			Thread.sleep(2000);
			WebElement mobilenumber = driver.findElement(By.xpath("//input[@name='mobNo']"));
			mobilenumber.sendKeys("97596385274"); // mobile number entering 11 digit number including 275
			mobilenumber.clear();
			mobilenumber.sendKeys("96385274"); // mobile number entering 8 digit number excluding 275
			Thread.sleep(2000);

			driver.findElement(By.xpath("//tbody/tr[8]/td[1]/input[1]")).click(); // file upload radio button
			Thread.sleep(2000);
			WebElement dropdown2 = driver.findElement(By.xpath("//select[@title='select Message Type']"));
			Thread.sleep(2000);
			Select select2 = new Select(dropdown2);
			select2.selectByIndex(1);// to number&message
			Thread.sleep(3000);

			driver.findElement(By.xpath("//*[@id=\"distTr\"]/td[1]/input")).click(); // distribution list radio
	
			// dispatch time
			WebElement dropdown3 = driver.findElement(By.xpath("//select[@name='dispTime']"));
			Thread.sleep(2000);
			Select select3 = new Select(dropdown3);
			select3.selectByIndex(1);// to select schedule
			Thread.sleep(3000);

			// logic to select date from calendar
			driver.findElement(By.xpath("//*[@id=\"trVal\"]/td[2]/img")).click();// calendar opening

			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addLink2 = wait2
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ui-datepicker-div\"]")));
			addLink2.click();
			String amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			System.out.println(amonth2);

			while (!(amonth2.equals("December 2023"))) {
				Thread.sleep(3000);
				driver.findElement(By.xpath("(//*[@id='ui-datepicker-div']/div[1]/a[2])")).click();
				amonth2 = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				System.out.println(amonth2);
			}
			driver.findElement(By.xpath("//a[normalize-space()='20']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
			Thread.sleep(3000);

			driver.findElement(By.xpath("//td[@id='ClearLink']")).click();
			Thread.sleep(3000);
			System.out.println("sms text page input field verified");

			// input field verification for binary message
			driver.findElement(By.xpath("//a[normalize-space()='Binary']")).click(); // login button
			Thread.sleep(2000);
			WebElement textElement2 = driver
					.findElement(By.xpath("//td[normalize-space()='Send Binary Messages']"));
			String s2 = textElement2.getText();
			System.out.println(s2);
			System.out.println("SMS binary page displayed");

			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/");// message
			// description
			driver.findElement(By.xpath("//textarea[@name='textMessage']")).sendKeys(
					"08050B8423F0\r\n" + "112233445566778899AABBCCDDEEFF00112233445566778899AABBCCDDEEFF");// text
			Thread.sleep(2000);

			driver.findElement(By.xpath(
					"//*[@id=\"SubsNoDiv\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"))
					.sendKeys("05000368656C6C6F");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@id='SendLink']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
			Thread.sleep(3000);
			System.out.println("sms binary page input field verified");

			// input field verification for binary message
			driver.findElement(By.xpath("//a[normalize-space()='Unicode']")).click(); // unicode page button
			Thread.sleep(2000);
			WebElement textElement3 = driver
					.findElement(By.xpath("//td[normalize-space()='Send Unicode Messages']"));
			String s3 = textElement3.getText();
			System.out.println(s3);
			System.out.println("SMS Unicode page displayed");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='msgDescription']")).sendKeys(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce commodo fringilla odio! 1234567890 ~!@#$%^&*()_+{}[]|;:'\\\",.<>?/");// message
			// description
			driver.findElement(By.xpath("//textarea[@name='textMessage']"))
					.sendKeys("08050B8423F014569865744865F00112233445566778899AABBCCDDEEFF");// text

			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@id='SendLink']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
			Thread.sleep(2000);
			System.out.println("sms binary page input field verified");

			// input field verification for canned message
			driver.findElement(By.xpath("//a[normalize-space()='Canned Message']")).click(); // login button
			Thread.sleep(2000);
			WebElement textElement4 = driver
					.findElement(By.xpath("//td[normalize-space()='Send Canned Messages']"));
			String s4 = textElement4.getText();
			System.out.println(s4);
			System.out.println("SMS Canned page displayed");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@title='Enter Message Description']")).sendKeys("good message"); // messagedescription
			// message name
			WebElement dropdown31 = driver.findElement(By.xpath("//select[@title='select message name']"));
			Thread.sleep(2000);
			Select select31 = new Select(dropdown31);
			select31.selectByIndex(0);// to select schedule
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
			WebElement wait1 = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.cssSelector("input[title='Select File to be uploaded']")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[@id='SendLink']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='popup_ok']")).click();
			Thread.sleep(2000);
			System.out.println("sms canned page input field verified");
			Thread.sleep(5000);
		}
	}
}

@Test(priority = 18)
public void SMSpage_myaccount_contactadmin_status() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Portal GUI']")).click(); // portal gui
	Thread.sleep(2000);

	// Get the handles of all open windows
	Set<String> windowHandles = driver.getWindowHandles();

	// Iterate through the window handles
	for (String handle : windowHandles) {
		// Switch to the new window
		driver.switchTo().window(handle);

		// Check if it's the desired window based on the title, URL, or other criteria
		if (driver.getTitle().equals("Bulk Messaging Platform")) {

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("guidemo"); // username
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("guidemo"); // password
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Login']")).click(); // login button
			Thread.sleep(2000);

			// my account page
			driver.findElement(By.xpath("//a[normalize-space()='My Account']")).click(); // login button
			Thread.sleep(2000);
			WebElement textElement7 = driver.findElement(By.xpath("//td[normalize-space()='Profile']"));
			String s7 = textElement7.getText();
			System.out.println(s7);
			System.out.println("my account Profile page displayed");

			driver.findElement(By.xpath("//a[normalize-space()='Change Password']")).click(); // login button
			Thread.sleep(2000);
			WebElement textElement8 = driver.findElement(By.xpath("//td[normalize-space()='Change Password']"));
			String s8 = textElement8.getText();
			System.out.println(s8);
			System.out.println("my account Change Password page displayed");
			Thread.sleep(3000);

			// contact admin page
			driver.findElement(By.xpath("//a[normalize-space()='Contact Admin']")).click(); // login button
			Thread.sleep(2000);
			WebElement textElement9 = driver
					.findElement(By.xpath("//td[@align='left'][normalize-space()='Contact Admin']"));
			String s9 = textElement9.getText();
			System.out.println(s9);
			System.out.println("contact admin page displayed");
			Thread.sleep(3000);

			// status page
			driver.findElement(By.xpath("//a[normalize-space()='Status']")).click(); // login button
			Thread.sleep(2000);

			// Get the handles of all open windows (including pop-ups)
			Set<String> windowHandles1 = driver.getWindowHandles();

			// Store the main window handle for later reference
			String mainWindowHandle = driver.getWindowHandle();

			// Iterate through the window handles
			for (String handle1 : windowHandles1) {
				// Switch to the new window
				driver.switchTo().window(handle1);

				// Check if it's the desired pop-up window based on the URL
				if (!handle1.equals(mainWindowHandle)) {
					// Assuming the URL of the pop-up window is
					// "https://10.0.0.21:8443/bmpportal/pages/status_current.jsp"
					if (driver.getCurrentUrl()
							.equals("https://10.0.0.36:8443/bmpportal/pages/status_current.jsp")) {

						WebElement textElement10 = driver
								.findElement(By.xpath("//td[normalize-space()='File Status-SMS']"));
						String s10 = textElement10.getText();
						System.out.println(s10);
						System.out.println("status File Status-SMS page displayed");

						driver.findElement(By.xpath("//a[normalize-space()='Scheduled']")).click(); // login button
						Thread.sleep(2000);
						WebElement textElement11 = driver
								.findElement(By.xpath("//td[normalize-space()='Scheduled Status']"));
						String s111 = textElement11.getText();
						System.out.println(s111);
						System.out.println("status Scheduled Status page displayed");
						driver.close();
						// Switch back to the main window
						//driver.switchTo().window(handle);
						break; // Exit the loop after handling the pop-up window

					}
				}
			}
		}
	}
}

}
