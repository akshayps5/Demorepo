package wicp;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.testng.Assert;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.apache.commons.io.FileUtils; // For screenshot handling
import java.io.File;
import org.testng.Reporter;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
	public class testforme {
	  private WebDriver driver;
	
	  JavascriptExecutor js;
	  @BeforeClass
	  public void setUp() {
	    driver = new ChromeDriver();
	    js = (JavascriptExecutor) driver;
	 //   vars = new HashMap<String, Object>();
	  }
	  @AfterClass
	  public void tearDown() {
	   // driver.quit();
	  }
	  @Test
	  public void testcase() throws InterruptedException {
	    driver.get("https://10.0.6.46:8442/pcc");
	//    driver.manage().window().setSize(new Dimension(1280, 672));
	    driver.manage().window().maximize();
	    driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();
	    driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("username")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("username")).sendKeys("sayan");
	    driver.findElement(By.name("password")).sendKeys("Admin123");
	    Thread.sleep(1000);
	    driver.findElement(By.id("login")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("subBtn")).click();
	    Thread.sleep(1000);
	    {
	      WebElement element = driver.findElement(By.id("240000"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.id("235000"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector(".form-group:nth-child(5) li:nth-child(1) b")).click();
	    js.executeScript("window.scrollTo(0,0)");
	    {
	      WebElement element = driver.findElement(By.cssSelector("span:nth-child(1) > .fa"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    driver.findElement(By.cssSelector("span:nth-child(1) > .fa")).click();
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    js.executeScript("window.scrollTo(0,144.6666717529297)");
	    driver.findElement(By.id("outputServiceSB")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("outputServiceSB"));
	      dropdown.findElement(By.xpath("//option[. = 'regexp:\\s+Originating Call']")).click();
	    }
	    driver.findElement(By.cssSelector(".input-group-addon > .fa")).click();
	    driver.findElement(By.id("newoutputServiceSB")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("newoutputServiceSB"));
	      dropdown.findElement(By.xpath("//option[. = 'regexp:\\s+Originating Call']")).click();
	    }
	    driver.findElement(By.id("categoryName")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("categoryName")).sendKeys("Testing");
	    driver.findElement(By.cssSelector("#addDiv > #addCategoryModal .form-group:nth-child(2) > .btn")).click();
	    driver.findElement(By.cssSelector(".confirm")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("select2-categorySB-container")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("tes");
	    driver.findElement(By.id("paramName_0")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("paramName_0")).sendKeys("Output1");
	    driver.findElement(By.id("outputParamCommentTA_0")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector(".form-group > a > .fa"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    driver.findElement(By.id("outputParamCommentTA_0")).sendKeys("1");
	    driver.findElement(By.cssSelector(".form-group > a > .fa")).click();
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.id("paramName_1")).click();
	    {
	      WebElement element = driver.findElement(By.id("submit"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.id("paramName_1")).sendKeys("Output2");
	    driver.findElement(By.id("outputParamCommentTA_1")).click();
	    {
	      WebElement element = driver.findElement(By.id("submit"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    driver.findElement(By.id("outputParamCommentTA_1")).sendKeys("2");
	    driver.findElement(By.id("submit")).click();
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector(".confirm")).click();
	  }
	}


