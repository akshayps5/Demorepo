package SMSC;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class reporting_gui {
	public static WebDriver driver;

	@BeforeClass
	// logic to login one time in the browser and execute all test cases within the
	// browser
	public void setUp() {
		WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
		SMSC.reporting_gui.driver = driver; // Set the WebDriver instance

		driver.get("https://10.0.0.21:8443/smsc/welcome/jsp/cluster_Main.jsp");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='id1']")).sendKeys("tssadmin");
		driver.findElement(By.xpath("//input[@name='pwd1']")).sendKeys("t4y4n4");
		driver.findElement(By.xpath("//input[@class='btn-primary']")).click();
	}
	
	
	@Test(priority = 5)
	public void tracker() throws InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Site Map']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[normalize-space()='Reporting GUI']")).click();
		Thread.sleep(3000);

		// Get the handles of all open windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through the window handles
		for (String handle : windowHandles) {
			// Switch to the new window
			driver.switchTo().window(handle);

			// Check if it's the desired window based on the title, URL, or other criteria
			if (driver.getTitle().equals("smsc")) {

				driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin"); // username
				driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Admin123"); // password
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='subBtn']")).click();
				Thread.sleep(3000);

				// tracker locator
				WebElement tracker = driver.findElement(By.xpath("//b[normalize-space()='Tracker']"));
				tracker.click();
				Thread.sleep(5000);
				WebElement TrackerMessage_id = driver.findElement(By.xpath("//input[@id='TrackerMessage_id']"));
				TrackerMessage_id.sendKeys("0203090124093254377655104");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[normalize-space()='Go']")).click();
				Thread.sleep(3000);

				WebElement tracker2385214 = driver.findElement(By.xpath("//*[@id=\"viewProvTable\"]/tbody/tr/td[1]/a"));
				tracker2385214.click();

				// div[@id='showModalDiv']
				WebElement contentdisplay2385214 = driver.findElement(By.xpath("//div[@id='showModalDiv']"));
				String conentdisplay = contentdisplay2385214.getText();
				System.out.println(conentdisplay);

				// fetching the
				WebElement table2 = driver.findElement(By.xpath("//table[@id='viewTable']"));
				WebElement dataRow2 = table2.findElement(By.xpath("//*[@id=\"viewTable\"]/tbody"));
				String tabledetails = dataRow2.getText();
				System.out.println(tabledetails);
				Thread.sleep(3000);

			}

		}
	}

}
