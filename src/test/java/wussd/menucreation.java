package wussd;
	import java.net.URI;
	import java.time.Duration;
	import java.util.List;


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




	import org.bouncycastle.cert.ocsp.Req;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	//import com.aventstack.extentreports.util.Assert;

	import wussd.dataproviderussd;
	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.interactions.Actions;

	public class menucreation { 
	  public WebDriver driver;
	  public WebDriverWait wait;
	  //private Map<String, Object> vars;
	//  JavascriptExecutor js;


	  @BeforeMethod
	  public void setUp() throws InterruptedException {

	    //  WebDriverManager.firefoxdriver().setup();
	      driver = new FirefoxDriver();
	      driver.get("https://10.0.6.63:8444/wussd");
	      driver.manage().window().maximize();
		 //   driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).click();
		    Thread.sleep(1500);
		    driver.findElement(By.id("tsslogin-form_username")).sendKeys("admin");
		    driver.findElement(By.cssSelector(".ant-input-password")).click();
		    Thread.sleep(1500);
		    driver.findElement(By.id("tsslogin-form_password")).sendKeys("Admin@123");
		    driver.findElement(By.cssSelector(".ant-btn")).click();
		    Thread.sleep(1500);
		    driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[3]/a/p")).click();
		  		Thread.sleep(1500);
		  		driver.findElement(By.cssSelector(".nav-treeview > .tss-menu-nav-item:nth-child(1) p")).click();
		  		Thread.sleep(1500);
		        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after driver

		  		
			
		    
	  }

	  @Test (priority = 1, enabled = true)
	  public void Existing_menu_deletion() throws InterruptedException {
		 
		// Check if the menu exists
		   driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/div/div/div/div/div[1]/div/div[2]/span/div/input")).sendKeys("Testing123");
		   Thread.sleep(1500);
		   
		   
		   try {
				WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@role='cell']//div//*[name()='svg'][3]/*[name()='path'][1]")));
				element5.click();

				 } catch (TimeoutException e) {
				        System.out.println("No Menu present.");
				    } catch (NoSuchElementException e) {
				        System.out.println("Element not found.");
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
		  // driver.findElement(By.xpath("//td[@role='cell']//div//*[name()='svg'][3]/*[name()='path'][1]")).click();
		   Thread.sleep(1500);
		   driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		   Thread.sleep(1500);
		   
		   System.out.println("Existing menu deleted successfully");
		   WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));
		   
			// Check if the element is displayed
			boolean deliveredDisplayed = alertElement.isDisplayed();
			String alertText = alertElement.getText();

			String expectedOutput = "Menu deleted Successfully";
			Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
			Thread.sleep(1500);

			if (deliveredDisplayed) {
				// Extract and print the text
				// String alertText = alertElement.getText();
				System.out.println("Existing_menu_deletion: " + alertText  );
			} else {
				System.out.println("The alert element is not displayed.");
			}
	  }
		
	  @Test (priority = 2, enabled = true)   
		      
		      public void new_menu_creation () throws InterruptedException {
		  
		  // Continue with menu creation
		  
		  driver.findElement(By.cssSelector(".fa-plus")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.id("TSSGUI_InputTextFieldStyle")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.id("TSSGUI_InputTextFieldStyle")).sendKeys("Testing123");
		  Thread.sleep(1500);
		  driver.findElement(By.id("TSSGUI_BootstrapTextArea")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.id("TSSGUI_BootstrapTextArea")).sendKeys("Testing only");
		  Thread.sleep(1500);
		  WebElement element = driver.findElement(By.cssSelector(".rc-tree-title > .tss-ussd-language-node"));
		  Actions actions = new Actions(driver);
		  actions.contextClick(element).perform();
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("//div[@id='addDiv']/div/div[2]/div/div[3]/div/div/span")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("//div[@id='addDiv']/div/div[2]/div/div[2]/div[3]/div/div/div/div[3]/span[3]/span/span")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea")).click();
		  Thread.sleep(1500);
		  WebElement textArea = driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea"));
		  textArea.click();
		  Thread.sleep(1500);
		  textArea.clear();
		  textArea.sendKeys("Name");
		  Thread.sleep(1500);
		  driver.findElement(By.cssSelector(".rc-tree-title > .tss-ussd-menu-node")).click();
		  Thread.sleep(1500);
		// Locate the element to right-click
		  WebElement element1 = driver.findElement(By.cssSelector("span.tss-ussd-menu-node:nth-child(1)"));

		  // Create Actions object
		  Actions actions1 = new Actions(driver);

		  // Perform right-click on the element
		  actions1.contextClick(element1).perform();

		  // Wait for the context menu to appear
		  Thread.sleep(1500); // Replace with explicit wait if needed

		  // Locate and click the second item from the context menu
		  WebElement menuItem = driver.findElement(By.cssSelector(".contexify_item:nth-child(2) > .contexify_itemContent"));
		  menuItem.click();
		  Thread.sleep(1500);
		  driver.findElement(By.cssSelector(".rc-tree-title > .tss-ussd-interactive-node"));
		  Thread.sleep(1500);
		  driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea")).click();
		  Thread.sleep(1500);
		  WebElement textArea1 = driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea"));
		  textArea1.click();
		  Thread.sleep(1500);
		  textArea1.clear();
		  textArea1.sendKeys("Enter Your Name");
		  Thread.sleep(1500);
		// Locate the element to right-click
			  WebElement element11 = driver.findElement(By.cssSelector("span.tss-ussd-interactive-node:nth-child(1)"));

			  // Create Actions object
			  Actions actions11 = new Actions(driver);

			  // Perform right-click on the element
			  actions11.contextClick(element11).perform();

			  // Wait for the context menu to appear
			  Thread.sleep(1500); // Replace with explicit wait if needed

			  // Locate and click the second item from the context menu
			  WebElement menuItem1 = driver.findElement(By.cssSelector(".contexify_item:nth-child(1) span"));
			  menuItem1.click();
		  Thread.sleep(1500);
		  driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea")).click();
		  Thread.sleep(1500);
		  WebElement textArea11 = driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea"));
		  textArea11.click();
		  Thread.sleep(1500);
		  textArea11.clear();
		  textArea11.sendKeys("Contact");
		  // Locate the element to right-click
		  WebElement element111 = driver.findElement(By.cssSelector(".rc-tree-node-content-wrapper-normal > span:nth-child(1) > span:nth-child(1)"));

		  // Create Actions object
		  Actions actions111 = new Actions(driver);

		  // Perform right-click on the element
		  actions111.contextClick(element111).perform();

		  // Wait for the context menu to appear
		  Thread.sleep(1500); // Replace with explicit wait if needed

		  // Locate and click the second item from the context menu
		  WebElement menuItem11 = driver.findElement(By.cssSelector(".contexify_item:nth-child(2) > .contexify_itemContent"));
		  menuItem11.click();
		  Thread.sleep(1500);
		  driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea")).click();
		  Thread.sleep(1500);
		  WebElement textArea12 = driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea"));
		  textArea12.click();
		  Thread.sleep(1500);
		  textArea12.clear();
		  textArea12.sendKeys("Enter Your Contact Number");
		  Thread.sleep(1500);
		// Locate the element to right-click on
		    WebElement elementToRightClick = driver.findElement(By.cssSelector(".rc-tree-node-content-wrapper-normal > span:nth-child(1) > span:nth-child(1)"));

		    // Create Actions instance
		    Actions actions31 = new Actions(driver);

		    // Perform right-click (context click)
		    actions31.contextClick(elementToRightClick).perform();
		    
		    WebElement menuItem12 = driver.findElement(By.cssSelector(".contexify_item:nth-child(1) span"));
		   
		    menuItem12.click();
		    // Now, click the context menu option
		    //elementToRightClick.click();
		  //  JavascriptExecutor js = (JavascriptExecutor) driver;
		    //js.executeScript("window.scrollBy(500,600)");
	    //    driver.findElement(By.cssSelector(".contexify_item:nth-child(1) span"));
		  Thread.sleep(1500);
		  WebElement textArea121 = driver.findElement(By.cssSelector(".form > #TSSGUI_TextAreaFieldSetStyle #TSSGUI_BootstrapTextArea"));
		  textArea121.click();
		  Thread.sleep(1500);
		  textArea121.clear();
		  textArea121.sendKeys("Test");
	      Thread.sleep(1500);
		  driver.findElement(By.cssSelector("#leafNodePanel label")).click(); 
		  Thread.sleep(1500);
		  driver.findElement(By.cssSelector("#leafNodePanel .icheck-material-purple")).click();
		  Thread.sleep(1500);
		  //driver.findElement(By.cssSelector(".row:nth-child(1) > .form-group #TSSGUI_SelectFieldLabelStyle")).click();
		  //Thread.sleep(1500);
		  driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div[1]/div[2]/div[2]/div[2]/div/div[2]/div[1]/div/div/div/div[2]/div/div/div/fieldset/div/i")).click();
		  Thread.sleep(1500);
		  WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[2]/input")));
		  inputElement.click();
		  Thread.sleep(1500);
		  inputElement.click();
		  inputElement.sendKeys("Xavier");
		  driver.switchTo().defaultContent();  // Switch back to the main page
		  driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div[1]/div[2]/div[2]/div[2]/div/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/ul/li[4]")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]"));
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div[1]/div[2]/div[2]/div[2]/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/fieldset/div/i")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div[1]/div[2]/div[2]/div[2]/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/ul/li[4]")).click();
		  Thread.sleep(1500);
		  JavascriptExecutor js = (JavascriptExecutor) driver;
	      js.executeScript("window.scrollBy(387,569)");
		  driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div[1]/div[2]/div[2]/div[2]/div/div[2]/fieldset/div/div/div[2]/div/div/fieldset/div/i")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div[1]/div[2]/div[2]/div[2]/div/div[2]/fieldset/div/div/div[2]/div/div/div/div/ul/li[1]")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div[1]/div[4]/button[1]")).click();
		  Thread.sleep(1500);
		  driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
		  Thread.sleep(1500);
		  
		 
		  System.out.println("Existing menu deleted successfully");
	      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after driver
	   WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));
	   
		// Check if the element is displayed
		boolean deliveredDisplayed = alertElement.isDisplayed();
		String alertText = alertElement.getText();

		String expectedOutput = "Menu Added Successfully";
		Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(1500);

		if (deliveredDisplayed) {
			// Extract and print the text
			// String alertText = alertElement.getText();
			System.out.println("new_menu_creation: " + alertText  );
		} else {
			System.out.println("The alert element is not displayed.");
		}

		  
	}
	 /*  @AfterMethod
	  public void tearDown() {
	    if (driver != null) {
	     driver.quit();
	      }*/
	  
}
