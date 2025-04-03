package mfslyca;

import java.util.Set;

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
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class dealercreation {
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
	//    driver.quit();
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
	    driver.get("http://10.0.6.107/");
	    driver.manage().window().setSize(new Dimension(1296, 688));
	    Thread.sleep(1000);
	    driver.findElement(By.name("Username")).click();
	    Thread.sleep(1000);
	    WebElement captchaImage = driver
				.findElement(By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img"));

		// Take a screenshot of the captcha
		File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:/Users/akshay.ps/eclipse-workspace/SMSC/src/test/java/capcha/captcha.png");
		FileHandler.copy(srcFile, destFile);

		// Debugging - Print environment variable
		System.out.println("TESSDATA_PREFIX: " + System.getenv("TESSDATA_PREFIX"));

		// Manually setting TESSDATA_PREFIX
		System.setProperty("TESSDATA_PREFIX", "C:\\Program Files\\Tesseract-OCR\\tessdata");

		// Use OCR to extract text
		ITesseract tess = new Tesseract();
		tess.setDatapath(System.getProperty("TESSDATA_PREFIX")); // Ensure correct path
		tess.setLanguage("eng"); // Set language

		String captchaText = tess.doOCR(destFile).replaceAll("[^a-zA-Z0-9]", ""); // Clean extracted text
		System.out.println("Extracted Captcha Text: " + captchaText);

		driver.findElement(By.xpath("/html/body/div/div/form/div/div[2]/div/input")).sendKeys("tayana");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/form/div/div[3]/div/input")).sendKeys("tayana");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='captchaUsr']")).sendKeys(captchaText);
		Thread.sleep(1000);
	    vars.put("window_handles", driver.getWindowHandles());
	    driver.findElement(By.id("btnSearch")).click();
	    vars.put("win5274", waitForWindow(2000));
	    driver.switchTo().window(vars.get("win5274").toString());
		
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/div[2]/aside[1]/section/ul/ul/li[3]/a/font/span")).click();
	    Thread.sleep(1000);
	    driver.manage().window().maximize();
	    driver.findElement(By.xpath("/html/body/div[2]/aside[1]/section/ul/ul/li[3]/ul/li[1]/a/font/span")).click();
	    driver.findElement(By.id("msisdnTypeId")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("msisdnTypeId")).sendKeys("9765434576");
	    Thread.sleep(1000);
	    driver.findElement(By.id("btnSearch")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("/html/body/div[2]/aside[2]/section[2]/form/div[1]/div[2]/div/div/div/div[1]/div[3]/select")).click();
	    Thread.sleep(1000);
	    {
	      WebElement dropdown = driver.findElement(By.name("dealerTypes"));
	      dropdown.findElement(By.xpath("//option[. = 'wholesaler']")).click();
	    }
	    

	    Thread.sleep(3000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,500)"); // Scrolls down by 500 pixels

	    driver.findElement(By.xpath("/html/body/div[2]/aside[2]/section[2]/form/div[1]/div[3]/div/div[1]/div[1]/h4/b")).click();
	    Thread.sleep(1000);
	//    driver.findElement(By.id("btnSearch")).click();
	  //  Thread.sleep(2000);
	    JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    js1.executeScript("document.getElementById('upldDoc').style.display='block';");

	    WebElement uploadInput = driver.findElement(By.id("upldDoc"));
	    uploadInput.sendKeys("C:\\Users\\akshay.ps\\Downloads\\Sujit_Jadhav.pdf");

	   // Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

	    WebElement alertElement2 = driver.findElement(By.xpath("//*[@id='register']"));	    
	    alertElement2.click();
	  }
	}


