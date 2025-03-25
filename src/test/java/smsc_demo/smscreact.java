package smsc_demo;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class smscreact {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() throws InterruptedException {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		smscreact.driver = driver;
//		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance

		// Now you can proceed with your test automation script
		driver.get("https://10.0.1.86:8000/smsc");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form_username\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='tsslogin-form_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
		Thread.sleep(2000);
		/*
		 * driver.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/aside/section/nav/ul/li[4]/a/p/i")).click();
		 * Thread.sleep(2000); driver.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/aside/section/nav/ul/li[4]/ul/li[3]/a/p")).click();
		 * Thread.sleep(3000); driver.findElement(By.xpath(
		 * "/html/body/div/div/div[1]/section/div/div/div/div/div/div/div/div[1]/div/div[2]/span/div/input"
		 * )).sendKeys("Xavier"); Thread.sleep(2000); driver.findElement(By.xpath(
		 * "//div[@class='alignCenter']//*[name()='svg'][2]/*[name()='path'][1]")).click
		 * (); Thread.sleep(2000);
		 * driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(
		 * ); Thread.sleep(3000);
		 */
	}

	@Test(priority = 1, enabled = true)

	public void deleting_existing_client() throws InterruptedException {
		// setUp();

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[4]/a/p/i")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[4]/ul/li[3]/a/p")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div/div/div/div/div/div[1]/div/div[2]/span/div/input"))
				.sendKeys("Pankaj1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='alignCenter']//*[name()='svg'][2]/*[name()='path'][1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		Thread.sleep(3000);

	}

	@Test(priority = 2, enabled = true)
	public void smpp_client_creation() throws InterruptedException {

		// Locate the SVG element using XPath
		// driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")).click();
		driver.findElement(By.xpath("//p[normalize-space()='ESME']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Client']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]"))
				.click();
		driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("Pankaj121");
		driver.findElement(By.xpath("//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("Pankaj123");
		driver.findElement(By.xpath("//div[4]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("1");
		driver.findElement(By.xpath("//div[5]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("10");
		driver.findElement(By.xpath("//input[@placeholder='Select Expiry Date']")).click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div/div/div[1]/div/button[2]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div[2]/span[10]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div[2]/span[8]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div/div/div[2]/table/tbody/tr[5]/td[1]"))
				.click();
		// driver.findElement(By.xpath("//span[@class='p-highlight']")).click();
		driver.findElement(By.xpath("//div[8]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("Pankaj123@gmail.com");
		driver.findElement(By.xpath("//div[11]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("789789");

		driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[12]/div/div/fieldset/input"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[12]/div/div/div/div/ul/li[3]"))
				.click();
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[14]/div[1]/div[1]/fieldset[1]/input[1]"))
				.click();
		driver.findElement(By.xpath("//li[@class='TSSGUI_SelectFieldSelectAllOption ']")).click();
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div[1]/section/div/div/div[1]/section/div/div/div[2]/div/button[1]"))
				.click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		Thread.sleep(4000);
		// driver.findElement(By.xpath("//div[@id='AccountsPanel']//div[@class='card-header
		// tss-panel-header tss-info-panel']")).click();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * WebElement element =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
		 * xpath("//div[@id='AccountsPanel']//div[@class='card-header tss-panel-header tss-info-panel']"
		 * ))); element.click(); driver.findElement(By.xpath(
		 * "//div[@id='AccountsPanel']//div[@class='row']//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"
		 * )).sendKeys("Pankaj1"); driver.findElement(By.xpath(
		 * "//div[@id='AccountsPanelBody']//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"
		 * )).sendKeys("Pankaj123"); Thread.sleep(2000);
		 */
		// driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click();
		// driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/section/div/div/div[1]/section/div/div[2]/div/div[4]/div/div[2]/div[2]/div[3]/div/div/fieldset/div/i")).click();
		// driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div[2]/div/div[4]/div/div[2]/div/div[3]/div/div/div/div/ul/li[1]"));

		/*
		 * driver.findElement(By.
		 * xpath("//div[@class='form-group col-md-1']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']"
		 * )).sendKeys("5");
		 * driver.findElement(By.xpath("//input[@placeholder='Select IP Address']")).
		 * click(); driver.findElement(By.xpath(
		 * "/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div[2]/div/div[4]/div/div[2]/div/div[5]/div/div/div/div/ul/li[1]"
		 * )).click(); driver.findElement(By.xpath(
		 * "//div[@class='col-md-12']//div//div[3]//div[1]//div[1]//fieldset[1]//input[1]"
		 * )).click(); driver.findElement(By.xpath("//li[@title='TXRX']")).click();
		 * driver.findElement(By.xpath(
		 * "//div[@id='AccountsPanel']//div[7]//*[name()='svg']")).click();
		 * driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(
		 * ); Thread.sleep(2000); driver.findElement(By.
		 * xpath("//div[@id='DSRSupportPanel']//div[contains(@class,'card-header tss-panel-header tss-info-panel')]"
		 * )).click(); driver.findElement(By.
		 * xpath("//div[@id='DSRSupportPanelBody']//div//div[contains(@class,'row')]//div[contains(@class,'form-group col-md-3')]//div//input[@id='TSSGUI_SelectFieldStyle']"
		 * )).click(); driver.findElement(By.xpath("//li[@title='Enable']")).click();
		 * driver.findElement(By.xpath("//div[@id='DSRSupportPanel']//button[1]")).click
		 * ();
		 * driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(
		 * );
		 */

		// Thread.sleep(3000);
		// driver.close();

		// driver.findElement(By.xpath("//input[@class='p-inputtext
		// p-component']")).sendKeys("varma");
		// Perform actions to search for the client using the search field
		WebElement searchField = driver.findElement(By.xpath("//input[@class='p-inputtext p-component']"));
		searchField.sendKeys("Pankaj121");

		// Allow time for the search to complete (adjust as per the application’s
		// behavior)
		try {
			Thread.sleep(2000); // You can replace this with explicit wait if needed
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Check if the client appears in the search result
		List<WebElement> clientList = driver
				.findElements(By.xpath("//table[@id='client_list']//td[contains(text(), 'Pankaj121')]"));

		if (clientList.size() > 0) {
			// If the client is found, assert creation was successful
			Assert.assertTrue(true, "Client creation successful!");
		} else {
			// If the client is not found, assert that creation failed
			Assert.fail("Client creation failed!");
		}

	}

	@Test(priority = 3, enabled = true)
	public void http_client_creation() throws InterruptedException {

		// Locate the SVG element using XPath
		// driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]")).click();
		driver.findElement(By.xpath("//p[normalize-space()='ESME']")).click();
		driver.findElement(By.xpath("//p[normalize-space()='Client']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='HTTP']")).click();
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/*[name()='svg'][1]"))
				.click();
		driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("Pankaj2");
		driver.findElement(By.xpath("//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("Pankaj123");
		driver.findElement(By.xpath("//div[4]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("1");
		driver.findElement(By.xpath("//div[5]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("10");
		driver.findElement(By.xpath("//input[@placeholder='Select Expiry Date']")).click();
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/fieldset/div/span/div/div/div/div[1]/div/button[2]"))
				.click();
		driver.findElement(By.xpath("//div[8]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("Pankaj123@gmail.com");
		driver.findElement(By.xpath("//div[9]//div[1]//div[1]//fieldset[1]//input[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[@title='On Accept']")).click();
		driver.findElement(By.xpath("//div[14]//div[1]//div[1]//fieldset[1]//input[1]")).click();
		driver.findElement(By.xpath("//li[@title='Disable']")).click();
		// driver.findElement(By.xpath("//div[15]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("pankaj002@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[15]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("t457");
		driver.findElement(By.xpath("//div[14]//div[1]//div[1]//fieldset[1]//input[1]")).click();

		driver.findElement(By.xpath("//div[16]//div[1]//div[1]//fieldset[1]//input[1]")).click();
		driver.findElement(By.xpath("//div[16]//div[1]//div[1]//fieldset[1]//input[1]")).click();
		driver.findElement(By.xpath("//div[17]//div[1]//div[1]//fieldset[1]//input[1]")).click();
		driver.findElement(By.xpath("//li[@title='TXRX']")).click();
		driver.findElement(By.xpath("//div[@class='selectHover selectForm ']//input[@id='TSSGUI_SelectFieldStyle']"))
				.click();

	}

	@Test(priority = 4, enabled = true)
	public void smpp_client_modification() throws InterruptedException {
		driver.findElement(By.xpath("//p[normalize-space()='ESME']")).click(); // to click on ESME
		driver.findElement(By.xpath("//p[normalize-space()='Client']")).click(); // click on client
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html/body/div/div/div[1]/section/div/div/div/div/div/div/div/div[1]/div/div[2]/span/div/input"))
				.sendKeys("Pankaj11"); // search field
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@class='p-inputtext
		// p-component']")).sendKeys("Pankaj1");
		// Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='alignCenter']//*[name()='svg']")).click(); // edit icon button
		Thread.sleep(2000);
		// accessing the quota
		// driver.findElement(By.xpath("//label[normalize-space()='Quota']")).click();
		// //click on quota
		driver.findElement(By.cssSelector("div[id='QuotaPanel'] label")).click();
		Thread.sleep(3000);
		/// driver.findElement(By.xpath("//label[normalize-space()='Quota']")).click();
		/// driver.findElement(By.xpath("//div[@id='QuotaPanelBody']//div//div[contains(@class,'row')]//div[contains(@class,'form-group
		/// col-md-3')]//div//input[@id='TSSGUI_SelectFieldStyle']")).click();
		// driver.findElement(By.xpath("//div[@id='QuotaPanel']//fieldset[1]//input[1]")).click();
		driver.findElement(By.cssSelector(
				"body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > input:nth-child(2)"))
				.click();
		// making quota status dropdown
		driver.findElement(By.xpath("//li[@title='Enable']")).click(); // enabling the quota status
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("//div[@id='QuotaPanel']//div[@class='row']//div[1]//div[1]//div[1]//fieldset[1]//input[1]"))
				.click();
		// driver.findElement(By.xpath("//li[@title='Enable']")).click(); //enable the
		// quota status
		driver.findElement(By.xpath("//div[@id='QuotaPanelBody']//div[2]//div[1]//div[1]//fieldset[1]//input[1]"))
				.click(); // quota reset interval as daily
		driver.findElement(By.xpath("//li[@title='Daily']")).click();
		// driver.findElement(By.xpath("//div[@id='QuotaPanel']//div[3]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("200");
		// // ddaily quota value
		// driver.findElement(By.xpath("//div[@id='QuotaPanel']//div[5]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("70");
		// //add current day quota
		// driver.findElement(By.xpath("//div[@class='col-md-12']//div[6]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("40");
		// //enter the threshold percentage value
		driver.findElement(By.xpath("//div[@id='QuotaPanel']//button[1]")).click(); // modify button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Modify']")).click(); // modify prompt message
		// modifying Parameters
		driver.findElement(
				By.xpath("//div[@id='ParametersPanel']//div[@class='card-header tss-panel-header tss-info-panel']"))
				.click(); // Paramters panel
		driver.findElement(By.xpath(
				"//div[@id='ParametersPanelBody']//div//div[@class='row']//div[@class='form-group col-md-3']//div//div[@class='selectForm ']//input[@id='TSSGUI_SelectFieldStyle']"))
				.click();
		// clicking on command
		driver.findElement(By.xpath("//ul[1]//li[1]//input[1]")).click(); // select all checkbox
		// driver.findElement(By.xpath("//div[@id='ParametersPanelBody']//div[2]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("5");
		// //inactive timeout as 5
		// driver.findElement(By.xpath("//div[@id='ParametersPanel']//div[3]//div[1]//fieldset[1]//div[1]//div[1]//input[1]")).sendKeys("5");
		// //alive timeout
		driver.findElement(By.xpath("//div[@id='ParametersPanel']//button[1]")).click(); // modify button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()='Modify']")).click(); // modify prompt message
		JavascriptExecutor js = (JavascriptExecutor) driver; // top scroll down and click on accounts
// 	// Scroll down by 1100,888 pixels
		js.executeScript("window.scrollBy(332,887)"); // 337,535
		// adding accounts
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[normalize-space()='Accounts']")).click(); // Accounts
		driver.findElement(By.xpath(
				"//div[@id='AccountsPanel']//div[@class='row']//div[1]//div[1]//fieldset[1]//div[1]//div[1]//input[1]"))
				.sendKeys("Pankaj");
		// username
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='tss-tab-body']/div/div/section[@class='content']/div[@id='proceedDiv']/div[@class='row']/div[@class='col-md-12']/div/div[@id='AccountsPanel']/div[@id='AccountsPanelBody']/div[@class='row']/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"))
				.sendKeys("Pankaj123");
		// password
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='tss-tab-body']/div/div/section[@class='content']/div[@id='proceedDiv']/div[@class='row']/div[@class='col-md-12']/div/div[@id='AccountsPanel']/div[@id='AccountsPanelBody']/div[@class='row']/div[@class='form-group col-md-1']/div[@id='fieldSetDiv']/fieldset[@id='TSSGUI_InputTextFieldSetStyle']/div/div/input[1]"))
				.sendKeys("3");
		// Number of sessions
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='tss-tab-body']/div/div/section[@class='content']/div[@id='proceedDiv']/div[@class='row']/div[@class='col-md-12']/div/div[@id='AccountsPanel']/div[@id='AccountsPanelBody']/div[@class='row']/div[5]/div[1]/div[1]/fieldset[1]/input[1]"))
				.click();
		// Ip address
		driver.findElement(By.xpath("//li[contains(@title,'10.0.1.86:9000')]")).click(); // selecting the ip adress
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='tss-tab-body']/div/div/section[@class='content']/div[@id='proceedDiv']/div[@class='row']/div[@class='col-md-12']/div/div[@id='AccountsPanel']/div[@id='AccountsPanelBody']/div[@class='row']/div[7]//*[name()='svg']"))
				.click();
		// adding the account
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
		// prompt message
		JavascriptExecutor js1 = (JavascriptExecutor) driver; // top scroll down and click on shortcode
//  	// Scroll down by 1100,888 pixels
		js1.executeScript("window.scrollBy(337,535)");
		Thread.sleep(2000);

		// Adding the shortcode
		driver.findElement(By.xpath("//label[normalize-space()='ShortCode']")).click(); // accessing shortcode
		driver.findElement(By.xpath(
				"//div[@id='ShortCodePanel']//div[@class='form-group col-md-3']//div//div[1]//fieldset[1]//input[1]"))
				.click();
		// shortcode dropdown
		driver.findElement(By.xpath("//li[@title='Add New Shortcode']")).click(); // select add new shortcode
		driver.findElement(By.xpath(
				"//div[@id='ShortCodePanelBody']//div//div[@class='row']//div[@class='form-group col-md-3']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//input[@id='TSSGUI_InputTextFieldStyle']"))
				.sendKeys("452");
		// adding the shortcode value
		driver.findElement(By.xpath("//div[@id='ShortCodePanel']//div[3]//div[1]//fieldset[1]//div[1]//i[1]")).click(); // to
																														// click
																														// on
																														// calender
																														// button
		driver.findElement(By.xpath("//span[normalize-space()='17']")).click();
		// please change the date , update the current date
		driver.findElement(
				By.xpath("//div[contains(@class,'col-md-12')]//div//div[4]//div[1]//fieldset[1]//div[1]//i[1]"))
				.click();
		// to click on the expiry date calender icon
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='2024']")).click(); // click on the year to change the
																					// year
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='2029']")).click(); // updating the expiry year
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Feb']")).click(); // selecting a month
		Thread.sleep(1000);
		// driver.findElement(By.xpath("//span[contains(@class,'p-highlight')]")).click();
		// // selectiing a date
		driver.findElement(By.xpath("//span[contains(@aria-disabled,'false')][normalize-space()='1']")).click();
		Thread.sleep(1000);
		// driver.findElement(By.xpath("//div[@id='ShortCodePanel']//button[1]")).click();
		// //Modify button
		driver.findElement(
				By.xpath("//div[contains(@class,'col-md-12')]//div//div[4]//div[1]//fieldset[1]//div[1]//i[1]"))
				.click();
		driver.findElement(By.xpath("//div[@id='ShortCodePanel']//button[1]//*[name()='svg']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click(); // prompt message
		Thread.sleep(1000);

		// adding sender_id details
		driver.findElement(By.xpath("//label[normalize-space()='Sender Id Details']")).click();// click on sender_id
																								// details
		driver.findElement(By.xpath(
				"//div[@id='SenderIdDetailsPanel']//div[contains(@class,'row')]//div[1]//div[1]//div[1]//fieldset[1]//input[1]"))
				.click();
		// sender id dropdown
		driver.findElement(By.xpath("//li[@title='Enable']")).click();
		driver.findElement(By.xpath("//div[@id='SenderIdDetailsPanel']//button[1]")).click(); // click on modify button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Modify']")).click(); // modify prompt message

		// adding holiday list
		driver.findElement(By.xpath("//label[normalize-space()='Holiday List']")).click(); // click on holiday list
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"//div[@id='HolidayListBody']//div//div[@class='row']//div[@class='col-md-3']//div//input[@id='TSSGUI_SelectFieldStyle']"))
				.click();
		// click on select holiday dropdown
		driver.findElement(By.xpath("//ul[1]//li[1]//input[1]")).click(); // check the checkbos for any holidays
		driver.findElement(By.xpath("//div[@id='HolidayListPanel']//button[1]")).click(); // click on modify (if alredy
																							// enabled it will disable
																							// it)
		driver.findElement(By.xpath("//button[normalize-space()='Modify']")).click(); // prompt message

		// adding Time zone details
		JavascriptExecutor js2 = (JavascriptExecutor) driver; // top scroll down and click on zone details
//	// Scroll down by  pixels
		js2.executeScript("window.scrollBy(336,795)");
		driver.findElement(By.xpath("//label[normalize-space()='Time Zone Details']")).click(); // click on time zone
		driver.findElement(By.xpath("//span[@class='slider']")).click(); // activae slider
		// driver.findElement(By.xpath("//div[@id='TimeZoneDetailsBodyPanel']//div//div[@class='row']//div[@class='form-group
		// col-md-3']//div//div[@class='selectForm
		// ']//input[@id='TSSGUI_SelectFieldStyle']")).click();
		driver.findElement(By.cssSelector(
				"body > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > section:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(8) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > input:nth-child(2)"))
				.click();
		// configure time zone dropdown
		// driver.findElement(By.xpath("//li[@title='Day Of Week']")).click();
		// driver.findElement(By.cssSelector("//li[@title='Day Of Week']")).click();
		driver.findElement(By.xpath(
				"//div[@id='TimeZoneDetailsBodyPanel']//div//div[@class='row']//div[@class='form-group col-md-3']//div//div[@class='selectForm ']//input[@id='TSSGUI_SelectFieldStyle']"))
				.click();
		// click on week dropdown
		// driver.findElement(By.xpath("//div[@id='TimeZoneDetailsBodyPanel']//div//div[@class='row']//div[@class='form-group
		// col-md-3']//div//div[@class='selectForm
		// ']//input[@id='TSSGUI_SelectFieldStyle']")).click();
		// click on time zone dropdown
		// driver.findElement(By.xpath("//ul[1]//li[1]//input[1]")).click();
		driver.findElement(By.xpath("//div[@id='TimeZoneDetailsPanel']//button[1]")).click(); // click on modify button
		driver.findElement(By.xpath("//button[normalize-space()='Modify']")).click(); // mpdify prompt

		// enable the DSR support
		// driver.findElement(By.xpath("//div[@id='DSRSupportPanel']//div[@class='card-header
		// tss-panel-header tss-info-panel']")).click();
		// click on dsr support
		// driver.findElement(By.xpath("//div[@id='DSRSupportPanelBody']//div//div[contains(@class,'row')]//div[contains(@class,'form-group
		// col-md-3')]//div//input[@id='TSSGUI_SelectFieldStyle']")).click();
		// click on Dsr support dropdown
		// driver.findElement(By.xpath("//li[@title='Enable']")).click(); //select the
		// enable dropdown
		// driver.findElement(By.xpath("//div[@id='DSRSupportPanel']//button[1]")).click();
		// // click on modify
		// driver.findElement(By.xpath("//button[normalize-space()='Modify']")).click();
		// // click on prompt

		// Alert management

		driver.findElement(By.xpath("//label[normalize-space()='Alert Management']")).click(); // click on alert
																								// management
		driver.findElement(By.xpath(
				"//div[@id='AlertManageMentPanelBody']//div//div[1]//div[1]//div[1]//div[1]//fieldset[1]//input[1]"))
				.click();
		// click on alert mode dropdown
		driver.findElement(By.xpath("//li[@title='Both']")).click(); // to select the alert mode as both
		driver.findElement(By.xpath("//div[@id='AlertManageMentPanel']//button[1]")).click(); // click on modify
		driver.findElement(By.xpath("//button[normalize-space()='Modify']")).click(); // select the prompt message

		// Retry management
		driver.findElement(By.xpath("//label[normalize-space()='Retry Management']")).click(); // click on Retry
																								// management
		// driver.findElement(By.xpath(""))
	}
		@Test(priority = 4, enabled = true)
		public void mis_report()  {
			
			driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[6]/a/p/i")).click();			
		}
		
		@AfterClass
		public void logout() throws InterruptedException {
			
			
	}}
	


