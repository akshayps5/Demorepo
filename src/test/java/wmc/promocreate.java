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

public class promocreate {
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
		driver.findElement(By.cssSelector(".template:nth-child(1) > ul:nth-child(2) > li:nth-child(2) font")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("PromoName")).sendKeys("auto");
		driver.findElement(By.name("vFromDate")).click();
		Thread.sleep(1000);

		// Get the current date and format it as "dd-MM-yyyy"
		LocalDate currentDate = LocalDate.now();
		String dateStr = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Get the current time and format it as "HH:mm:ss"
		LocalTime currentTime = LocalTime.now();
		String timeStr = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

		// Print the results
		System.out.println("Date: " + dateStr);
		System.out.println("Time: " + timeStr);

		LocalTime updatedTime = currentTime.plusMinutes(2);
		String updatedTimeStr = updatedTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

		driver.findElement(By.name("vFromDate")).sendKeys(dateStr);
		driver.findElement(By.name("vFromTime")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("vFromTime")).sendKeys(timeStr);
		Thread.sleep(1000);
		driver.findElement(By.name("vToDate")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("vToDate")).sendKeys(dateStr);
		Thread.sleep(1000);
		driver.findElement(By.name("vToTime")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("vToTime")).sendKeys(updatedTimeStr);
		Thread.sleep(1000);

		{
			WebElement element = driver.findElement(By.linkText("Add ↓"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).release().perform();
		}
		driver.findElement(By.cssSelector(".legtabClass")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Add ↓")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("dealerBasedException")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("tr:nth-child(3) #singleDel")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("s_delnum")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("s_delnum")).sendKeys("97517717257");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("table:nth-child(7) > tbody > tr:nth-child(3) > .template:nth-child(4)"))
				.click();
		driver.findElement(By.name("promotions")).click();
		driver.findElement(By.name("dealer_incl_freeBeeType")).click();
		{
			WebElement dropdown = driver.findElement(By.name("dealer_incl_freeBeeType"));
			dropdown.findElement(By.xpath("//option[. = 'FreeTalktime']")).click();
		}
		driver.findElement(By.name("dealer_incl_freeBeeVal")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("dealer_incl_freeBeeVal")).sendKeys("67");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("table:nth-child(2) tr:nth-child(5) input:nth-child(1)")).click();
		driver.findElement(By.name("dealer_incl_freeBeeValdity")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("dealer_incl_freeBeeValdity")).sendKeys("19");
		Thread.sleep(1000);

		driver.findElement(By.xpath(
				"/html/body/table[1]/tbody/tr/td/form/table[2]/tbody/tr/td/fieldset/table[4]/tbody/tr[9]/td/table[2]/tbody/tr[6]/td[2]/a"))
				.click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/input[1]")).click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert firstAlert = wait.until(ExpectedConditions.alertIsPresent());
		Thread.sleep(3000);

		firstAlert.accept(); // Click OK


		Alert secondAlert = wait.until(ExpectedConditions.alertIsPresent());
	
		secondAlert.accept(); // Click OK

	}
}
