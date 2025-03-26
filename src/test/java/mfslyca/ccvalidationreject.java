package mfslyca;


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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ccvalidationreject 
{
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeClass
	public void setUp() throws IOException, TesseractException, InterruptedException {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();

		driver.get("http://10.0.6.107/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img")));

		// Take a screenshot of the captcha
		File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
		Thread.sleep(2000);
		File destFile = new File(
				"C:\\Users\\Moulya\\eclipse-workspace\\QA\\src\\test\\java\\captchaimages\\captcha.png");
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
	
	//Reject CreditControl Validation
	@Test(priority = 1, enabled = true, dataProvider = "getDetails")
	public void RejectCreditControlValidation(String search,String remark) throws InterruptedException 
	{
		vars.put("win1429", waitForWindow1(2000));
	    driver.switchTo().window(vars.get("win1429").toString());
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(5) font")).click();
	    WebElement dropdown = driver.findElement(By.id("serviceSelect"));
	    dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
	    driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys("search");
	    vars.put("window_handles", driver.getWindowHandles());
	    driver.findElement(By.cssSelector("td > button")).click();
	    vars.put("win4857", waitForWindow1(2000));
	    driver.switchTo().window(vars.get("win4857").toString());
	    driver.findElement(By.cssSelector(".col-sm-4:nth-child(2) .btn")).click();
	    driver.findElement(By.linkText("Click Here")).click();
	    driver.close();
	    Thread.sleep(2000);
	    driver.switchTo().window(vars.get("win1429").toString());
	    {
	      WebElement element = driver.findElement(By.cssSelector(".navbar-nav > li:nth-child(3) > a"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.id("action1_reject")).click();
	    driver.findElement(By.id("remark_33564")).sendKeys("remark");
	    driver.findElement(By.id("modalBtn")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("tab2-link")).click();
	  }
	
	//Reject AML Validation
	@Test(priority = 1, enabled = true, dataProvider = "getDetails")
	public void RejectAMLValidation(String search,String remark) throws InterruptedException 
	{
		vars.put("win8595", waitForWindow1(2000));
		driver.switchTo().window(vars.get("win8595").toString());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(6) font")).click();
	    WebElement dropdown = driver.findElement(By.id("serviceSelect"));
	    dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
	    driver.findElement(By.cssSelector("#myTable1_filter > label > input")).sendKeys("search");
	    driver.findElement(By.id("action1_reject")).click();
	    driver.findElement(By.id("remark_33564")).sendKeys("remark");
	    driver.findElement(By.id("modalBtn")).click();
	    driver.findElement(By.id("tab2-link")).click();
	  }
	
	//Reject Final Validation
	@Test(priority = 1, enabled = true, dataProvider = "getDetails")
	public void RejectFianlValidation(String search,String remark) throws InterruptedException 
	{
		vars.put("win3780", waitForWindow1(2000));
	    driver.switchTo().window(vars.get("win3780").toString());
	    driver.findElement(By.cssSelector(".col-md-4:nth-child(2) li:nth-child(7) font")).click();
	    WebElement dropdown = driver.findElement(By.id("serviceSelect"));
	    dropdown.findElement(By.xpath("//option[. = 'Dealer Creation']")).click();
	    driver.findElement(By.cssSelector("#myTable1_filter .form-control")).sendKeys("search");
	    driver.findElement(By.id("action1_reject")).click();
	    driver.findElement(By.id("remark_33564")).sendKeys("remark");
	    driver.findElement(By.id("modalBtn")).click();
	    driver.findElement(By.id("tab2-link")).click();
}
}





