package wmc;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.TesseractException;

public class assign {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeClass
	public void setUp() {
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}

	@Test
	public void idid() throws InterruptedException, IOException, TesseractException, AWTException {
		driver.get("http://10.0.6.125:8000/");
		driver.manage().window().setSize(new Dimension(1050, 652));
		driver.findElement(By.name("Username")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("Username")).sendKeys("tayana");
		driver.findElement(By.name("Password")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("Password")).sendKeys("t4y4n4");
		vars.put("window_handles", driver.getWindowHandles());
		driver.findElement(By.xpath("//input[@value=\' Login \']")).click();
		vars.put("win8850", waitForWindow(5000));
		vars.put("root", driver.getWindowHandle());
		driver.switchTo().window(vars.get("win8850").toString());
		 Thread.sleep(3000);


		driver.findElement(By.cssSelector(".template:nth-child(1) > ul:nth-child(2) > li:nth-child(3) font")).click();
	
	driver.findElement(By.id("promoSelector")).click();
	 Thread.sleep(3000);
    
      WebElement dropdown = driver.findElement(By.id("promoSelector"));
      dropdown.findElement(By.xpath("//option[contains(text(),'auto')]")).click();

    Thread.sleep(3000);
    
  //*[@id="check4"]
    driver.findElement(By.id("check4")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("/html/body/table[1]/tbody/tr/td/table[7]/tbody/tr/td/fieldset/table[2]/tbody/tr/td/input[1]")).click();
   
	WebDriverWait wait3= new WebDriverWait(driver, Duration.ofSeconds(5));
	Alert firstAlert1 = wait3.until(ExpectedConditions.alertIsPresent());
	Thread.sleep(3000);

	firstAlert1.accept(); 
  }
}
