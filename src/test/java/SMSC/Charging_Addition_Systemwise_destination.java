package SMSC;

	 
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	 
	public class Charging_Addition_Systemwise_destination {
	    static WebDriver driver;
	 
	    @BeforeClass
	    public void setUp() {
	        // Initialize WebDriver
	        driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        driver.get("https://10.0.6.115:3003/smsc");
	    }
	 
	    @Test(priority = 1)
	    public void loginTest() throws InterruptedException {
	        // Login Process
	        driver.findElement(By.xpath("//*[@id=\"tsslogin-form_username\"]")).sendKeys("admin");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys("Admin123");
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".ant-btn")).click();
	        Thread.sleep(3000);
	    }
	 
	    @Test(priority = 2)
	    public void modifyCharging() throws InterruptedException {
	        // Navigate to Charging Module
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[6]/a")).click();
	        Thread.sleep(2000);
	        try {
	            // Locate and click on the Modification icon
	            driver.findElement(By.cssSelector("#rightSectionDiv > section > div:nth-child(3) > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg")).click();
	            Thread.sleep(2000);
	            // Select and click on the destination address
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/fieldset/input")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/div/ul/li")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button")).click();
	            Thread.sleep(2000);
	            // Fill in the destination address and search
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[1]/div/fieldset/input")).sendKeys("9845687516");
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[2]/button[1]")).click();
	            Thread.sleep(2000);
	            // Click delete icon
	            driver.findElement(By.cssSelector("#rightSectionDiv > section > div:nth-child(3) > div:nth-child(3) > div > div.card-body.d-flex.align-items-center.py-8 > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	            Thread.sleep(2000);
	            System.out.println("Existing charging deleted successfully.");
	        } catch (Exception e) {
	            System.out.println("No existing charging, proceeding to add a new one.");
	        }
	    }
	 
	    @Test(priority = 3)
	    public void addCharging() throws InterruptedException {
	        // Locate and click on Add icon
	        driver.findElement(By.xpath("//*[name()='svg']/*[name()='path' and @d='M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32l0 144L48 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l144 0 0 144c0 17.7 14.3 32 32 32s32-14.3 32-32l0-144 144 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-144 0 0-144z']")).click();
	        Thread.sleep(2000);
	        // Select destination option
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/fieldset/input")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/div/ul/li")).click();
	        Thread.sleep(2000);
	        // Click process button
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button[1]")).click();
	        Thread.sleep(2000);
	        // Fill destination address and length
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[1]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9845687516");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[2]/div/fieldset/div[1]/div/input")).sendKeys("10");
	        Thread.sleep(2000);
	        // Fill description
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[3]/div/fieldset/textarea")).sendKeys("This is automated Charging creation");
	        Thread.sleep(2000);
	        // Select status
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[5]/div/div/fieldset/input")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[5]/div/div/div/div/ul/li[1]")).click();
	        Thread.sleep(2000);
	        // Click Add icon to save
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[2]/button[1]")).click();
	        Thread.sleep(2000);
	        // Handle alert
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	        Thread.sleep(2000);
	 
	        System.out.println("Charging added successfully.");
	    }
	 
	    @AfterClass
	    public void tearDown() {
	        // Close the browser after the test is done
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}