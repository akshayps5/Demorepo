package wussd;


	import java.net.URI;
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import wussd.dataproviderussd;
	import org.bouncycastle.cert.ocsp.Req;
	import org.junit.After;
	import org.junit.Before;
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
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.interactions.Actions;
	public class loadmenu {
	public WebDriver driver;
	//public WebDriverWait wait;
	private Map<String, Object> vars;
	JavascriptExecutor js;



	@Before
	public void setUp() {
	 driver = new ChromeDriver();
	 js = (JavascriptExecutor) driver;
	 vars = new HashMap<String, Object>();
	}
	/*@After
	public void tearDown() {
	 driver.quits();
	}*/

	@Test 
	public void loadmenutest() throws InterruptedException {
		driver = new FirefoxDriver();
	    driver.get("https://10.0.6.63:8444/wussd");
	    driver.manage().window().maximize();
	    Thread.sleep(500);
	    driver.findElement(By.id("tsslogin-form_username")).click();
	    Thread.sleep(500);
	    driver.findElement(By.id("tsslogin-form_username")).sendKeys("admin");
	    Thread.sleep(500);
	    driver.findElement(By.id("tsslogin-form_password")).click();
	    Thread.sleep(500);
	    driver.findElement(By.id("tsslogin-form_password")).sendKeys("Admin@123");
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector(".ant-btn")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector(".tss-menu-nav-item:nth-child(3) p")).click();
	    Thread.sleep(500);
	    driver.findElement(By.linkText("Load Menu")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector(".p-icon-field > .p-inputtext")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div/div/div/div/div/div[1]/div/div[2]/span/div/input")).sendKeys("Testing123");
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector(".fa-arrow-right-to-bracket > path")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector(".swal2-confirm")).click();
	    
	/* // Wait for menu load confirmation
	    try {
	        FluentWait<WebDriver> wait = null;
			WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Menu Loaded Successfully')]")));
	        if (confirmationMessage.isDisplayed()) {
	            System.out.println("Menu is already loaded");
	        }
	    } catch (Exception e) {
	        System.out.println("Menu Loaded Successfully");
	    } */
	    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after driver

		WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")));
		// Check if the element is displayed
		boolean deliveredDisplayed = alertElement.isDisplayed();
		String alertText = alertElement.getText();

		String expectedOutput = "Menu loaded Successfully";
		Assert.assertTrue(alertText.contains(expectedOutput), "Error message mismatch for invalid service id");
		Thread.sleep(2000);

		if (deliveredDisplayed) {
			// Extract and print the text
			// String alertText = alertElement.getText();
			System.out.println(": " + alertText  );
		} else {
			System.out.println("The alert element is not displayed.");
		}
	    
	}
	}


