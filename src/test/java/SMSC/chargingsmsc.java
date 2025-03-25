
	package SMSC;
	 
	import java.util.concurrent.TimeUnit;
	 
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	 
	public class chargingsmsc {
	 
	    static WebDriver driver;
	 
	    @BeforeClass
	    public void setup() {
	        // Initialize the WebDriver before running tests
	        driver = new FirefoxDriver();
	        driver.get("https://10.0.6.115:3003/smsc");
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    }
	    @Test(priority = 1)
	    public void loginTest() throws InterruptedException {
	        // Login process
	        driver.findElement(By.xpath("//*[@id=\"tsslogin-form_username\"]")).sendKeys("admin");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys("T4y4n4");
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".ant-btn")).click();
	        Thread.sleep(3000);
	    }
	    @Test(priority = 2)
	    public void Charging_Deletion_Systemwise_source() throws InterruptedException {
	        try {
	            // Locates and clicks on charging module in site map
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[6]/a")).click();
	            Thread.sleep(2000);
	 
	            // Locate and click on the Modification icon
	            driver.findElement(By.cssSelector(
	                    "#rightSectionDiv > section > div:nth-child(3) > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg"))
	                    .click();
	            Thread.sleep(2000);
	 
	            // Select and click on the source address
	            driver.findElement(By.xpath(
	                    "/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/fieldset/input"))
	                    .click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath(
	                    "/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/div/ul/li"))
	                    .click();
	            Thread.sleep(2000);
	            driver.findElement(
	                    By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button[1]"))
	                    .click();
	            Thread.sleep(2000);
	 
	            // Fill in the source address and search
	            driver.findElement(By.xpath(
	                    "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[1]/div/fieldset/input"))
	                    .sendKeys("9874563255");
	            Thread.sleep(2000);
	            driver.findElement(
	                    By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[2]/button[1]"))
	                    .click();
	            Thread.sleep(2000);
	 
	            // Click delete icon
	            driver.findElement(By.cssSelector(
	                    "#rightSectionDiv > section > div:nth-child(3) > div:nth-child(3) > div > div.card-body.d-flex.align-items-center.py-8 > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path"))
	                    .click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	            Thread.sleep(2000);
	 
	            System.out.println("Existing charging deleted successfully.");
	        } catch (Exception e) {
	            System.out.println("No existing charging, proceeding to add a new one.");
	        }
	    }
	    @Test(priority = 3)
	    public void Charging_Addition_Systemwise_source() throws InterruptedException {
	        // Locates and clicks on add icon
	        driver.findElement(By.xpath(
	                "//*[name()='svg']/*[name()='path' and @d='M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32l0 144L48 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l144 0 0 144c0 17.7 14.3 32 32 32s32-14.3 32-32l0-144 144 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-144 0 0-144z']"))
	                .click();
	        Thread.sleep(2000);
	 
	        // Locates and select the source option
	        driver.findElement(By.xpath(
	                "/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/fieldset/input"))
	                .click();
	        Thread.sleep(2000);
	        driver.findElement(By
	                .xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/div/ul/li"))
	                .click();
	        Thread.sleep(2000);
	 
	        // Locates and clicks on the proceed Button
	        driver.findElement(
	                By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button[1]"))
	                .click();
	        Thread.sleep(2000);
	 
	        // Locates and Fills the source VMSC
	        driver.findElement(By.xpath(
	                "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[1]/div/fieldset/div[1]/div/input"))
	                .sendKeys("9874563255");
	        Thread.sleep(2000);
	 
	        // Locates and Fills the description
	        driver.findElement(By.xpath(
	                "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[2]/div/fieldset/textarea"))
	                .sendKeys("This is automated Charging creation");
	        Thread.sleep(2000);
	 
	        // Locates and selects the status of the action
	        driver.findElement(By.xpath(
	                "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[4]/div/div/fieldset/input"))
	                .click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(
	                "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[4]/div/div/div/div/ul/li[1]"))
	                .click();
	        Thread.sleep(2000);
	 
	        // Locates and clicks on Add icon
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[2]/button[1]"))
	                .click();
	        Thread.sleep(2000);
	 
	        // To Click add on the alert message tab
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	        Thread.sleep(2000);
	 
	        System.out.println("Charging rule added successfully.");
	    }
	 
	 
	@AfterClass
	public void tearDown() {
	    // Quit the WebDriver after tests are completed
	    if (driver != null) {
	        driver.quit();
	    }
	}
	}


