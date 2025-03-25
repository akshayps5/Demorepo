package ssfc;


import java.time.Duration;
	 
	import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
	 
	 
	public class Assu {
	 
	    public static WebDriver driver;
	 
	    @BeforeClass
	    public void setUp() {
	        WebDriver driver = new FirefoxDriver();
	        Assu.driver = driver;
	 
	        driver.get("https://10.0.6.65:8001/ssfc/login");
	        driver.manage().window().maximize();
	 
	        // Use WebDriverWait for the login page elements
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
	        driver.findElement(By.id("tsslogin-form_password")).sendKeys("admin@123");
	        driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();
	    }
	    //Add spam keyword for MT message
	    @Test(priority= 1, enabled = true)
	    public void Fraud_MTspam() throws InterruptedException
	    {
	    	//Delete the existing keyword of spam rule
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	driver.findElement(By.xpath("//*[@id=\"root\"]/div/aside/section/nav/ul/li[2]/a")).click();//click on fraud control
	    	Thread.sleep(1000);
	    	driver.findElement(By.xpath("/html/body/div/div/aside/section/nav/ul/li[2]/ul/li[2]/a/p")).click();// click on Rules
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//*[@id=\"rightSectionDiv\"]/section/ul/li[2]/a")).click();//click on Spam
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//*[@id=\"mtSysPanel\"]/div[1]")).click();//click on MT system
	    	Thread.sleep(2000);
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div/div[2]/div[2]/div/div/div[2]/table/tbody/tr[2]/td[4]")).click();//click on row
			Thread.sleep(2000);
	    	driver.findElement(By.cssSelector("#mtSysBody > div > div > div.p-datatable-wrapper > table > tbody > tr:nth-child(7) > td:nth-child(4) > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path")).click();//click on delete button.
	    	Thread.sleep(1000);
	    	driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();//click on yes for popup
	    	Thread.sleep(1000);
	    	
	    	//Add keyword for spam rule
	    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div.d-flex.justify-content-end.tss-pull-right > svg > path")).click();// click on "+" button
	    	Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click();// click on dropdown of config type
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//*[@title='MT System']")).click();//select MT system
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("TQAteam");//pass the value in keyword
	    	driver.findElement(By.xpath("//*[@id=\"TSSGUI_SelectFieldStyle\"]")).click();//click on treatment
	    	driver.findElement(By.xpath("//*[text()='Spam']")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//*[@id=\"addBtn\"]")).click();// click on add button
	    	driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();//click on yes for popup
	    	Thread.sleep(2000); 
	    	//To check weather it's success or not 
	    	WebElement errorMessageElement = driver.findElement(By.id("swal2-title")); 
			 String s1 = errorMessageElement.getText(); 
			 int endIndex = s1.indexOf("addition successful"); 
			 // Trim the string to keep only the part until the specified index 
			 String trimmedString = s1.substring(0, endIndex);
			 System.out.println(trimmedString); 
			 String expectedOutput = "addition successful"; 
			 Assert.assertTrue(s1.contains(expectedOutput),"Error message mismatch for invalid service id"); Thread.sleep(3000);
	    	
	    	
}
	    
	    @Test
	    public void testcase() {
	    System.out.println("This is an GUI testing");
	    }   
}
