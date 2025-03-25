package mfslyca;


	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Set;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import net.sourceforge.tess4j.ITesseract;
	import net.sourceforge.tess4j.Tesseract;
	import net.sourceforge.tess4j.TesseractException;

	public class gggg {

		private WebDriver driver;
		private Map<String, Object> vars;
		JavascriptExecutor js;

		@BeforeClass
		public void setUp1() throws IOException, TesseractException, InterruptedException {
			driver = new ChromeDriver();
			js = (JavascriptExecutor) driver;
			vars = new HashMap<String, Object>();

			driver = new FirefoxDriver();
			driver.get("http://10.0.6.107");
			driver.manage().window().maximize();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img")));

			// Take a screenshot of the captcha
			File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
			Thread.sleep(2000);
			File destFile = new File("C:\\Users\\Moulya\\eclipse-workspace\\QA\\src\\test\\java\\captchaimages\\captcha.png");
			FileUtils.copyFile(srcFile, destFile);

			// OCR Processing
			ITesseract tess = new Tesseract();
			tess.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
			tess.setLanguage("eng");

			String captchaText = tess.doOCR(destFile);
			System.out.println("Extracted Captcha Text: " + captchaText);
			driver.manage().window().setSize(new Dimension(1280, 672));
			driver.findElement(By.name("Username")).click();
			driver.findElement(By.name("Username")).sendKeys("tayana");
			driver.findElement(By.name("Password")).click();
			driver.findElement(By.name("Password")).sendKeys("tayana");
			driver.findElement(By.id("captchaUsr")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("captchaUsr")).sendKeys(captchaText);
			vars.put("window_handles", driver.getWindowHandles());
			driver.findElement(By.id("btnSearch")).click();

		}

		public String waitForWindow1(int timeout) {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Set<String> whNow = driver.getWindowHandles();
			@SuppressWarnings("unchecked")
			Set<String> whThen = (Set<String>) vars.get("window_handles");
			if (whNow.size() > whThen.size()) {
				whNow.removeAll(whThen);
			}
			return whNow.iterator().next();
		}

		@Test(priority = 1, enabled = true)
		public void DealerCreation() throws InterruptedException, AWTException {
			vars.put("win5750", waitForWindow1(2000));
			vars.put("root", driver.getWindowHandle());
			driver.switchTo().window(vars.get("win5750").toString());
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row:nth-child(4) > .col-md-4:nth-child(2) li > a > font")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#msisdnTypeId")).sendKeys("9123456789");
	        driver.findElement(By.id("btnSearch")).click();
	        Thread.sleep(2000);
			driver.findElement(By.name("dealerTypes")).click();
			Thread.sleep(4000);
			{
			      WebElement dropdown = driver.findElement(By.name("dealerTypes"));
			      Thread.sleep(2000);
			      dropdown.findElement(By.xpath("//option[. = 'Branch Office']")).click();
			      Thread.sleep(5000);
			      driver.findElement(By.id("userCheck")).click();
			      Thread.sleep(2000);
			      driver.findElement(By.name("dlrPvlgOpt")).click();
			      Thread.sleep(2000);
			      js.executeScript("window.scrollBy(0, 300);");
			      driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
			      Thread.sleep(4000);
			      driver.findElement(By.cssSelector("div:nth-child(3) > .panel .panel-title")).click();
			      Thread.sleep(2000);
			      driver.findElement(By.id("custTypeId1")).click();
			      Thread.sleep(2000);
			      driver.findElement(By.id("genderId2")).click();
			      Thread.sleep(2000);
			      js.executeScript("window.scrollTo(0,379.3333435058594)");
			      Thread.sleep(2000);
			      driver.findElement(By.name("subsName")).click();
			      Thread.sleep(2000);
			      driver.findElement(By.name("subsName")).sendKeys("lyca");
			      Thread.sleep(2000);
			      driver.findElement(By.name("emailId")).click();
			      Thread.sleep(2000);
			      driver.findElement(By.name("emailId")).sendKeys("abcd123@gmail.com");
			      js.executeScript("window.scrollBy(0, 300);");
			      Thread.sleep(6000);
}
		}}
