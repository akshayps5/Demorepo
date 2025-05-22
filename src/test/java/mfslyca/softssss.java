package mfslyca;

import java.awt.AWTException;
import java.io.File;
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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class softssss {

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
	  public void didid() throws InterruptedException, IOException, TesseractException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		  driver.get("http://10.0.6.107/");
		  driver.manage().window().setSize(new Dimension(1296, 688));
		    Thread.sleep(1000);
		    driver.findElement(By.name("Username")).click();
		    Thread.sleep(1000);
		   
	
	SoftAssert softAssert = new SoftAssert();
	int maxRetries = 1;
	boolean captchaSolved = false;
	 
	for (int attempt = 1; attempt <= maxRetries; attempt++) {
	    try {
	        WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img")));
	 
	        File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
	        File destFile = new File("C:/Users/akshay.ps/eclipse-workspace/SMSC/src/test/java/capcha/captcha.png");
	        FileUtils.copyFile(srcFile, destFile);
	 
	        // OCR
	        ITesseract tess = new Tesseract();
	        tess.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
	        tess.setLanguage("eng");
	 
	        String captchaText = tess.doOCR(destFile).replaceAll("[^A-Za-z0-9]", "").trim();
	        System.out.println("Attempt " + attempt + " - Extracted Captcha Text: " + captchaText);
	 
	        // Fill form
	        driver.manage().window().setSize(new Dimension(1280, 672));
	        driver.findElement(By.name("Username")).clear();
	        driver.findElement(By.name("Username")).sendKeys("indira");
	 
	        driver.findElement(By.name("Password")).clear();
	        driver.findElement(By.name("Password")).sendKeys("tayana");
	 
	        driver.findElement(By.id("captchaUsr")).clear();
	        driver.findElement(By.id("captchaUsr")).sendKeys(captchaText);
	        driver.findElement(By.id("btnSearch")).click();
	 
	        // Optional: wait for error message or dashboard to confirm login
	        // Assuming success means redirect or presence of a specific element
	        WebDriverWait postLoginWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        try {
	            postLoginWait.until(ExpectedConditions.urlContains("dashboard")); // Change as needed
	            captchaSolved = true;
	            break;
	        } catch (TimeoutException e) {
	            System.out.println("Captcha might have failed, retrying...");
	        }
	 
	    } catch (Exception e) {
	        System.out.println("Error during captcha attempt " + attempt + ": " + e.getMessage());
	    }
	}
	 
	// Final soft assert
	softAssert.assertTrue(captchaSolved, "Captcha failed even after " + maxRetries + " attempts.");
	softAssert.assertAll();
}
}