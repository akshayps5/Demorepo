package mfslyca;
		 
	import java.io.File;
	import java.time.Duration;
	import java.util.Map;
	import java.util.Set;
	import java.util.HashMap;
	 
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.io.FileHandler;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	 
	import net.sourceforge.tess4j.ITesseract;
	import net.sourceforge.tess4j.Tesseract;
	 
	public class ttttt {
		private WebDriver driver;
	    private Map<String, Object> vars = new HashMap<>(); // Initialize vars

	 
	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    }
	 
	    @Test
	    public void logr() throws Exception {
	        driver.get("http://10.0.6.107");
	 
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 
	        // Capture Captcha
	        WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='captchaDiv']/table/tbody/tr/td/img")));
	 
	        File srcFile = captchaImage.getScreenshotAs(OutputType.FILE);
	        File destFile = new File("C:\\Users\\Moulya\\eclipse-workspace\\QA\\src\\test\\java\\captchaimages\\captcha.png");
	        FileHandler.copy(srcFile, destFile);
	 
	        System.setProperty("TESSDATA_PREFIX", "C:\\Program Files\\Tesseract-OCR\\tessdata");
	 
	        ITesseract tess = new Tesseract();
	        tess.setDatapath(System.getProperty("TESSDATA_PREFIX"));
	        tess.setLanguage("eng");
	 
	        String captchaText = tess.doOCR(destFile);
	        System.out.println("Extracted Captcha Text: " + captchaText);
	 
	        driver.findElement(By.xpath("/html/body/div/div/form/div/div[2]/div/input")).sendKeys("tayana");
	        driver.findElement(By.xpath("/html/body/div/div/form/div/div[3]/div/input")).sendKeys("tayana");
	        driver.findElement(By.xpath("//*[@id='captchaUsr']")).sendKeys(captchaText);
	        driver.findElement(By.xpath("//*[@id='btnSearch']")).click();
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
	 
	        if (whThen == null) {
	            System.out.println("`window_handles` not initialized! Returning first available window.");
	            return whNow.iterator().next();
	        }
	 
	        if (whNow.size() > whThen.size()) {
	            whNow.removeAll(whThen);
	            return whNow.iterator().next();
	        }
	 
	        System.out.println(" No new window detected! Returning current window.");
	        return driver.getWindowHandle();
	    }
	 
	    @Test
	    public void Dealercreation() {
	        String newWindow = waitForWindow(2000);
	        if (newWindow == null || newWindow.isEmpty()) {
	            System.out.println("No new window found! Exiting test.");
	            return;
	        }
	 
	        driver.switchTo().window(newWindow);
	        // ✅ Fixed XPath Selector
	        driver.findElement(By.xpath("/html/body/div[2]/aside[2]/section[2]/div/form/div[1]/div[2]/div/ul/li[1]/a/font")).click();
	    }
	 
	}

