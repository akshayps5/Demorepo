package bmp_react;


	import java.awt.AWTException;
	 
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import java.time.Duration;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.NoSuchElementException;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.BeforeMethod;

	import io.github.bonigarcia.wdm.WebDriverManager;

	 
	public class xgjhxgqjgx {
	

			
			
			private int rowsAffected = 0 ;
			 
		    String result;
		    String failureReason = "";
		    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		    String product = "BMP";
		    
			public static WebDriver driver ;
			public WebDriverWait wait ;
			public static String browser = "Firefox";
			
			 @BeforeMethod
			    public void waiting() {
			        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after driver
			    }
			 
			 public static void highlightElement(WebElement element) {
				    JavascriptExecutor js = (JavascriptExecutor) driver;
				    @SuppressWarnings("deprecation")
					String originalStyle = element.getAttribute("style");
				    js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red; background: yellow;');", element);
				    try {
				        Thread.sleep(500); // Highlight for visibility
				    } catch (InterruptedException e) {
				        e.printStackTrace();
				    }
				    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
				}


		public void login() throws InterruptedException {

		    System.out.println("Initializing WebDriver for Admin GUI");

		    if (browser.equalsIgnoreCase("Firefox")) {
		        WebDriverManager.firefoxdriver().setup();
		        FirefoxOptions options = new FirefoxOptions();
		        options.setAcceptInsecureCerts(true);
		        driver = new FirefoxDriver(options);
		    } else if (browser.equalsIgnoreCase("Chrome")) {
		        WebDriverManager.chromedriver().setup();
		        ChromeOptions options = new ChromeOptions();
		        options.setAcceptInsecureCerts(true);
		        driver = new ChromeDriver(options);
		    } else {
		        WebDriverManager.edgedriver().setup();
		        EdgeOptions options = new EdgeOptions();
		        options.setAcceptInsecureCerts(true);
		        driver = new EdgeDriver(options);
		    }

		    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // Launch Admin GUI
		    driver.get("https://10.0.6.137:8443/bmp/");
		    driver.manage().window().maximize();
		    System.out.println("Admin GUI Launched");

		    // Retry logic
		    boolean pageLoaded = false;
		    By usernameField = By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[1]/input");

		    for (int i = 1; i <= 4; i++) {
		        try {
		            // Wait max 8 sec for element
		            wait.withTimeout(Duration.ofSeconds(12));
		            driver.findElement(usernameField);
		            pageLoaded = true;
		            break;
		        } catch (Exception e) {
		            System.out.println("Attempt " + i + ": Login page not loaded, refreshing...");
		            driver.navigate().refresh();
		            Thread.sleep(2000); // wait after refresh
		        }
		    }

		    if (!pageLoaded) {
		        System.out.println("GUI is not loaded after 4 attempts");
		        driver.quit();
		        return; // stop execution
		    }

		    // If loaded, proceed with login
		    driver.findElement(usernameField).sendKeys("admin");
		    driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[3]/input"))
		            .sendKeys("Admin@123");
		    driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[4]/button")).click();
		}
		
		}

	

