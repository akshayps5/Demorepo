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
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import net.sourceforge.tess4j.ITesseract;
	import net.sourceforge.tess4j.Tesseract;
	import net.sourceforge.tess4j.TesseractException;

	public class mfs_dealerView {

		private WebDriver driver;
		  private Map<String, Object> vars;
		  JavascriptExecutor js;
		  
		  @BeforeClass
		  public void login() throws IOException, TesseractException {
		    driver = new ChromeDriver();
		    js = (JavascriptExecutor) driver;
		    vars = new HashMap<String, Object>();
		    
		    driver.get("http://10.0.6.107/");
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	      WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	              By.xpath("/html/body/div/div/form/div/div[4]/div[1]/table/tbody/tr/td/img")));

	      // Take a screenshot of the captcha
	      File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
	      File destFile = new File("C:\\Users\\Indira\\eclipse-workspace\\GuiAuto\\src\\test\\java\\MFS_Lyca\\GuiAuto\\capcha.png");
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
		    driver.findElement(By.id("captchaUsr")).sendKeys(captchaText);
	      driver.findElement(By.id("btnSearch")).click();  
		  }
		  
		  public String waitForWindow(int timeout) {
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
		  
		  @Test
		  public void DealerNew() throws InterruptedException {
			  
			  vars.put("window_handles", driver.getWindowHandles());
			  driver.findElement(By.id("btnSearch")).click();
			  vars.put("win1532", waitForWindow(2000));
			  driver.switchTo().window(vars.get("win1532").toString());
			  // Click on the Dealer view 
			  driver.findElement(By.cssSelector(".row:nth-child(4) > .col-md-4:nth-child(2) li:nth-child(2) font")).click();
			  Thread.sleep(2000);
			  // Click on the msisdn
			  driver.switchTo().frame(0);  // Switch to iframe
			 driver.findElement(By.name("msisdn")).click();
			 driver.findElement(By.name("msisdn")).sendKeys("9345678987");
			 Thread.sleep(2000);
			    driver.findElement(By.name("submit")).click();
			  Thread.sleep(2000);
			  
		  }
		  
		  @AfterClass
		  public void logOut() {
		    driver.quit();
		  }
	}


