package mfslyca;


	import java.io.File;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Set;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import net.sourceforge.tess4j.ITesseract;
	import net.sourceforge.tess4j.Tesseract;
	import net.sourceforge.tess4j.TesseractException;

	public class commissioncreation extends dataproviderforview  {

		private WebDriver driver;
		  private Map<String, Object> vars;
		  JavascriptExecutor js;
		  
		  @BeforeClass
		  public void setUp() throws IOException, TesseractException {
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
		    driver.findElement(By.name("Username")).sendKeys("indira");
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
		  public void CommissionRuleCreation (String RuleName, String Slab1, String Slab2, String Slab3, String Slab4) throws InterruptedException {
			  	// Scroll to rate card rule
			  	vars.put("window_handles", driver.getWindowHandles());
			    driver.findElement(By.id("btnSearch")).click();
			    vars.put("win6182", waitForWindow(2000));
			    driver.switchTo().window(vars.get("win6182").toString());
			    driver.findElement(By.cssSelector(".row:nth-child(6) > .col-md-4:nth-child(1) li:nth-child(4) font")).click();
			    // Enter a rule name
			    driver.findElement(By.xpath("//*[@id=\"rulename\"]")).sendKeys(RuleName);
			    Thread.sleep(2000);
			    
			    // Wholesaller to branch office
			    
			    // Click on add new rule 
			    driver.findElement(By.xpath("//*[@id=\"addBoxBtn\"]")).click();
			    // enter the source stake type - 1 
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[1]/select/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the category type
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[2]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the category type for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[2]/select[2]/option[3]")).click();
			    Thread.sleep(2000);
			    
			    //enter the account type for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[3]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the account type for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[3]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operaton type for source 
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[4]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operation type for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[4]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the slab value
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div/div[5]/input")).sendKeys(Slab1);
			    Thread.sleep(2000);
			    
			    
			    // Branch office to sub wholeseller
			    // Click on add new rule 
			    driver.findElement(By.xpath("//*[@id=\"addBoxBtn\"]")).click();
			    
			 // enter the source stake type - 2
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[1]/select/option[3]")).click();
			    Thread.sleep(2000);
			    
			    // enter the category type for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[2]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			   // enter the categor type for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[2]/select[2]/option[3]")).click();
			    Thread.sleep(2000);
			    
			    // enter the account type for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[3]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the account type for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[3]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operation for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[4]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operation for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[4]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the slab value
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[2]/div[5]/input")).sendKeys(Slab2);
			    Thread.sleep(2000);
			    
			    // Sub wholeseller to retailer
			    
			 // Click on add new rule 
			    driver.findElement(By.xpath("//*[@id=\"addBoxBtn\"]")).click();
			    Thread.sleep(2000);
			    
			    // enter the source type - 3
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[1]/select/option[4]")).click();
			    Thread.sleep(2000);
			    
			    // enter the categoer type for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[2]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the category type for dest 
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[2]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the account type for source 
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[3]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the account type for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[3]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operation for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[4]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operation for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[4]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the slab value
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[3]/div[5]/input")).sendKeys(Slab3);
			    Thread.sleep(2000);
			    
			    // Retailer to Staff
			    
			    // Click on add new rule 
			    driver.findElement(By.xpath("//*[@id=\"addBoxBtn\"]")).click();
			    Thread.sleep(2000);
			    
			    // enter the source stake type - 4
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[1]/select/option[5]")).click();
			    Thread.sleep(2000);
			    
			    // enter the category type for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[2]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			   
			    // enter the category type for dest 
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[2]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the account type for source 
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[3]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the account type for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[3]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operation for source
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[4]/select[1]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the operation for dest
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[4]/select[2]/option[2]")).click();
			    Thread.sleep(2000);
			    
			    // enter the slab value
			    driver.findElement(By.xpath("//*[@id=\"boxContainer\"]/div[4]/div[5]/input")).sendKeys(Slab4);
			    Thread.sleep(2000);
			    
			    // click on add btn
			    driver.findElement(By.xpath("//*[@id=\"addRuleBtn\"]")).click();
			    Thread.sleep(2000);
			    
			 // Assertion for the alert
			    Alert alert = driver.switchTo().alert();
				
				 String expectedTitle = "Rule operation successful!"; 
				 String actualTitle = alert.getText(); // Get text of alert
				System.out.println(actualTitle);
				 // Assert that the page title is as expected
				 Assert.assertEquals(actualTitle,
				expectedTitle, "Rule operation successful!");
				alert.dismiss();
		  }
		  
		  @AfterClass
		  public void logOut() {
		    driver.quit();
		  }
	}


