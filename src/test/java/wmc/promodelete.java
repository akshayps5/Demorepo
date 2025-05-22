package wmc;


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

public class promodelete {
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
	  public void idid()throws InterruptedException, IOException, TesseractException, AWTException {
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
	    vars.put("win7840", waitForWindow(5000));
	    vars.put("root", driver.getWindowHandle());
	    driver.switchTo().window(vars.get("win7840").toString());
	    driver.findElement(By.cssSelector(".template:nth-child(1) > ul:nth-child(2) > li:nth-child(2) font")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//option[contains(text(),'auto')]")).click();
	    Thread.sleep(1000);
	   
	    driver.findElement(By.name("view2")).click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	    String firstAlertText = driver.switchTo().alert().getText();
	    if (!firstAlertText.equals("Do you want to delete this promotion?")) {
	        throw new AssertionError("Unexpected alert text: " + firstAlertText);
	    }

	    // Accept the first alert
	    driver.switchTo().alert().accept();

	    // Wait for the second alert to appear
	    new WebDriverWait(driver, Duration.ofSeconds(5))
	        .until(ExpectedConditions.alertIsPresent());

	    // Get the second alert text and verify it
	    String secondAlertText = driver.switchTo().alert().getText();
	    if (!secondAlertText.equals("Promotion deleted successfully")) {
	        throw new AssertionError("Unexpected alert text: " + secondAlertText);
	    }

	    // Accept the second alert
	    driver.switchTo().alert().accept();
}
}