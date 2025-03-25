package mfslyca;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class login {
    public static WebDriver driver;

    @Test
    public void logr() throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://10.0.6.107");
        Thread.sleep(1000);

       // WebElement captchaImage = driver.findElement(By.id("captchaUsr"));
        WebElement captchaImage = driver.findElement(By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img"));
        
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

        String captchaText = tess.doOCR(destFile);
        System.out.println("Extracted Captcha Text: " + captchaText);

        driver.findElement(By.xpath("/html/body/div/div/form/div/div[2]/div/input")).sendKeys("tayana");
        Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/form/div/div[3]/div/input")).sendKeys("tayana");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='captchaUsr']")).sendKeys(captchaText);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='btnSearch']")).click();
		Thread.sleep(2000);
		//WebElement dropdown = driver.findElement(By.id("serviceSB"));
		driver.findElement(By.xpath("/html/body/div[2]/aside[2]/section[2]/div/form/div[1]/div[2]/div/ul/li[2]/div/a/font")).click();
		 
		//driver.findElement(By.xpath("/html/body/div[2]/aside[2]/section[2]/div/form/div[1]/div[2]/div/ul/li[1]/a/font")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("/html/body/div[1]/div/header/nav/div/div[2]/div/ul/li[1]/a/i")).click();
		Thread.sleep(1000); 
		
		    driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(6) font")).click();
		    driver.findElement(By.id("serviceSelect")).click();
		    {
		      WebElement dropdown = driver.findElement(By.id("serviceSelect"));
		      dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
        
        // Close WebDriver
        // driver.quit();
    }}}

