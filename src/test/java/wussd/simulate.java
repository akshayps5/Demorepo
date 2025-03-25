package wussd;


	import java.time.Duration;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class simulate {

	    public static WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        WebDriver driver = new FirefoxDriver();
	        simulate.driver = driver;

	        driver.get("https://10.0.6.119:8443/wussd/ussd/provussd/serviceCode");
	        driver.manage().window().maximize();

	        // Use WebDriverWait for the login page elements
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
	        driver.findElement(By.id("tsslogin-form_password")).sendKeys("Admin123");
	        driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
	    }

	    @Test(priority = 1, enabled = true)
	    public void prov_menu() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait for and click on the necessary elements
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/aside/section/nav/ul/li[3]/a/p"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/aside/section/nav/ul/li[3]/ul/li[7]/a/p"))).click();

	        // Wait for the dropdown icon and click it
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[1]/div[1]/div/div/fieldset/div/i"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[1]/div[1]/div/div/div/ul/li[4]"))).click();

	        // Enter text in the input field
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TSSGUI_InputTextFieldStyle"))).sendKeys("345");

	        // Wait for and interact with more elements
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[2]/div[1]/div/div/fieldset/div[1]/i"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[1]/section/div/section/div/div/div/div[2]/div[1]/div/div/div/ul/li[4]"))).click();

	        // Enter phone number
	        driver.findElement(By.cssSelector("#tss-inputGroup > div:nth-child(1) > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).sendKeys("78945754324");

	        // Click preview button
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("previewButton"))).click();

	        // Use JavaScriptExecutor to scroll down
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        jsExecutor.executeScript("window.scrollBy(0,800)");

	        // Wait for input and send USSD code
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/section/div/section/div[2]/div/div[1]/div[2]/input"))).sendKeys("*123");

	        // Click the button
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[1]/section/div/section/div[2]/div/div[1]/div[2]/div/button[1]"))).click();	     
	    }

	    @AfterClass
	    public void logout() {
	        // Include logout logic if needed
	    }
	}

