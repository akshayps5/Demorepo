package mfslyca;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class fffffff {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeClass
	public void setUp1() throws IOException, TesseractException, InterruptedException {
		driver = new FirefoxDriver();
		driver.get("http://10.0.6.107");
		driver.manage().window().maximize();
		Thread.sleep(2000);

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
		driver.findElement(By.xpath("//*[@id='btnSearch']")).click();
		Thread.sleep(1000);
	}
		/*
		 * public String waitForWindow1(int timeout) { try { Thread.sleep(timeout); }
		 * catch (InterruptedException e) { e.printStackTrace(); } Set<String> whNow =
		 * driver.getWindowHandles(); if (whNow.size() > 1) {
		 * whNow.remove(driver.getWindowHandle()); return whNow.iterator().next(); }
		 * return null; }
		 */

		// Store the main window handle
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
			driver.findElement(By.xpath("/html/body/div[2]/aside[2]/section[2]/div/form/div[1]/div[2]/div/ul/li[1]/a/font")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#msisdnTypeId")).sendKeys("9123456789");
	        driver.findElement(By.id("btnSearch")).click();
	        Thread.sleep(2000);
			driver.findElement(By.name("dealerTypes")).click();
			Thread.sleep(4000);
			{
			      WebElement dropdown = driver.findElement(By.name("dealerTypes"));
			      Thread.sleep(2000);

		driver.findElement(By.id("userCheck")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("dlrPvlgOpt")).click();
		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,144.6666717529297)"); // Your required logic
		Thread.sleep(2000);

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
		driver.findElement(By.name("subsName")).sendKeys("lyca");
		Thread.sleep(2000);

		driver.findElement(By.name("emailId")).click();
		driver.findElement(By.name("emailId")).sendKeys("abcd123@gmail.com");

		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(6000);

	}}

	@AfterClass
	public void tearDown() {
		// if (driver != null) {
		// driver.quit();
	}
}
