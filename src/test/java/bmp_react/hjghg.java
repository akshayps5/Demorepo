package bmp_react;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	public class hjghg {

	    WebDriver driver;
	    @BeforeMethod
	    public void setup() {
	        // Set path to your ChromeDriver executable if not set in system PATH
	        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

	        driver = new ChromeDriver(); // Corrected instantiation
	        driver.get("https://www.yatra.com/react-home?"); // Corrected get() method
	        driver.manage().window().maximize(); // Corrected window maximize method

	        // Note: No alert appears here by default — check manually if an alert exists
	        // To handle an alert, it must be present — add conditionally
	        try {
	            driver.switchTo().alert().accept(); // Accept alert if present
	        } catch (Exception e) {
	            System.out.println("No alert present.");
	        }

	        WebElement yatraLogo = driver.findElement(By.xpath("//*[contains(text(),'Yatra')]"));
	        String text = yatraLogo.getText();
	        System.out.println("Logo Text: " + text);
	    }
	    @Test
	    public void rtyu() {
	        WebElement someElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[3]/div[1]"));
	        String elementText = someElement.getText();
	        System.out.println("Element Text: " + elementText);

	        String windowHandle = driver.getWindowHandle();
	        System.out.println("Window Handle: " + windowHandle);
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit(); // Corrected spelling of 'quit'
	    }
	}


