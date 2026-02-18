package snd;


	import java.awt.AWTException;
	 
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	 
	import io.github.bonigarcia.wdm.WebDriverManager;
	 
	public class xvxv {
	 
	    WebDriver driver;
	 
	    @BeforeClass
	    public void setUp() {
	    	ChromeOptions options = new ChromeOptions();
	          options.setAcceptInsecureCerts(true); // Accept self-signed HTTPS certs
	  		driver = new ChromeDriver();// Set the WebDriver instance
	    }
	 
	    @Test
	    public void loginTest() throws InterruptedException, AWTException {
	        driver.get("https://10.0.5.49:8443/snd/snd/provisioning/rolepermission");
	        driver.manage().window().maximize();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//input[@placeholder='User ID']")).sendKeys("admin");
	        Thread.sleep(2000);
	 
	        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[3]/input"))
	                .sendKeys("Admin@123");
	        Thread.sleep(2000);
	 
	        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[4]/button")).click();
	        Thread.sleep(2000);
	    }
	 
	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
