package snd;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.time.Duration;
	public class GuiUsingRefId {
	    private WebDriver driver;
	    private static final String REF_FILE = 
	        "C:\\\\Users\\\\akshay.ps\\\\eclipse-workspace\\\\SMSC\\\\src\\\\test\\\\java\\\\snd\\\\ref.txt";
	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    }
	    @Test(priority = 1)
	    public void tracker() throws IOException, InterruptedException {
	        // Read latest RefId from file
	        String refId = null;
	        try (BufferedReader br = new BufferedReader(new FileReader(REF_FILE))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                refId = line.trim();
	            }
	        }
	        if (refId == null) throw new RuntimeException("RefId file is empty!");
	        System.out.println("Using RefId in GUI: " + refId);
	        // Open GUI page
	        driver.get("https://10.0.5.49:8443/snd/");
	        driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
			driver.findElement(By.xpath("//input[@placeholder='User ID']")).sendKeys(dataprovider.USERNAME);
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(snd.dataprovider.PASSWORD);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
			 driver.navigate().refresh();
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//a[contains(., 'Dealer Tracker')]")).click();
		        driver.findElement(By.xpath("//div[@class='card-body']//div[2]//div[1]//div[1]//div[3]//span[1]")).click();
		        driver.findElement(By.xpath("//a[normalize-space()='This Month']")).click();
		        driver.findElement(By.xpath("//div[3]//div[1]//div[1]//div[3]//span[1]//*[name()='svg']")).click();
		        driver.findElement(By.xpath("//button[normalize-space()='Etopup']")).click();
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        // Use 10 seconds as a max wait
		        wait.until(ExpectedConditions.visibilityOfElementLocated(
		        	    By.xpath("//input[contains(@class,'p-inputtext')]")));
		        //driver.findElement(By.xpath("//input[contains(@class,'p-inputtext')]"))
		                //.sendKeys(refId);
		        WebElement refer = driver.findElement(By.xpath("//input[contains(@class,'p-inputtext')]"));
		        refer.sendKeys(refId);

//		        String dynamicXpath = "//*[text()='" + refId + "' or normalize-space()='" + refId + "']";
//		        driver.findElement(By.xpath(dynamicXpath)).click();
		       // driver.findElement(By.xpath("//div[@class='modal-dialog modal-xl']//button[@aria-label='Close']")).click();
		        driver.findElement(By.xpath("//a[text()='Home']")).click();

	    }
	    @Test(priority = 2)
	    public void getStatements() {
	        driver.findElement(By.xpath("//div[9]//div[1]//div[2]//ul[1]//li[5]//a[1]")).click();
	        driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='content-body']/div/div[@class='tss-tab-body']/div/div/div[@class='card']/div[@class='card-body']/div[@class='row']/div[@class='form-group col-md-6']/div/div[@class='selectHover selectForm ']/fieldset[@id='TSSGUI_SelectFieldLabelStyle']/input[1]")).click();
	        driver.findElement(By.xpath("//input[@class='TSSGUI_SelectSearchBoxStyle']")).sendKeys("75");
	        driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
	        driver.findElement(By.xpath("//div[@class='SelectContainer']//div//div[@class='selectHover selectForm ']//input[@id='TSSGUI_SelectFieldStyle']")).click();
	        driver.findElement(By.xpath("//li[@title='2019 (Sales, Commission, Transfer Discount)']")).click();
	        driver.findElement(By.xpath("(//input[@id='TSSGUI_SelectFieldStyle'])[4]")).click();
	        driver.findElement(By.xpath("//li[@title='Current Month']")).click();
	        driver.findElement(By.xpath("//div[@class='col-12 d-flex justify-content-end']//button[@type='button']")).click();
	        driver.findElement(By.xpath("//button[@type='button']")).click();
	        driver.findElement(By.xpath("//button[contains(@title,'Click to Download')]//*[name()='svg']")).click();
	        driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[47]")).click();
	    }
	    @AfterClass
	    public void tearDown() {
	        if (driver != null) driver.quit();
	    }
	}
	 
	 
