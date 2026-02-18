package snd;



	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import java.time.Duration;
	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;
	import java.util.Map;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.*;

	import io.github.bonigarcia.wdm.WebDriverManager;

	// ✅ Importing TestData class for input data

	public class ufagf {

	    private WebDriver driver;
	    private Map<String, Object> vars;
	    JavascriptExecutor js;

	    @BeforeClass
	    public void setUp() throws InterruptedException, AWTException {

	    	  ChromeOptions options = new ChromeOptions();
	          options.setAcceptInsecureCerts(true); // Accept self-signed HTTPS certs
	  		driver = new ChromeDriver();// Set the WebDriver instance


	  		// Open your URL
	  		driver.get("https://10.0.5.49:8443/snd/login");
	  		driver.manage().window().maximize();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();
		    driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();
		Thread.sleep(2000);

	        // ✅ Using data from TestData class
	        driver.findElement(By.xpath("//input[@placeholder='User ID']")).sendKeys(TestData.USERNAME);
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[3]/input")).sendKeys(TestData.PASSWORD);
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[4]/button")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//div[4]//div[1]//div[2]//ul[1]//li[2]//a[1]")).click();
	        Thread.sleep(5000);
	    }

	    @Test(priority = 1)
	    public void ProductAdd() throws InterruptedException, AWTException {
	        Thread.sleep(2000);

	        driver.findElement(By.xpath("(//div[contains(text(),'Product')])[2]")).click();
	        Thread.sleep(2000);
	        WebElement element = driver.findElement(By.xpath("(//div[contains(text(),'Product')])[2]"));
	        Actions actions = new Actions(driver);
	        actions.contextClick(element).perform();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".contexify_itemContent")).click();
	        Thread.sleep(2000);

	        // ✅ Using product name from TestData
	        driver.findElement(By.id("TSSGUI_InputTextFieldStyle")).sendKeys(TestData.PRODUCT_NAME);
	        Thread.sleep(2000);

	        driver.findElement(By.cssSelector(".form-group:nth-child(2) > #fieldSetDiv > #TSSGUI_InputTextFieldSetStyle #TSSGUI_InputTextFieldStyle")).click();
	        Thread.sleep(2000);

	        // ✅ Using product code from TestData
	        driver.findElement(By.cssSelector(".form-group:nth-child(2) > #fieldSetDiv > #TSSGUI_InputTextFieldSetStyle #TSSGUI_InputTextFieldStyle")).sendKeys(TestData.PRODUCT_CODE);
	        Thread.sleep(2000);

	        driver.findElement(By.id("TSSGUI_SelectFieldStyle")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".tss-selected-option")).click();
	        Thread.sleep(2000);

	        LocalDate today = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = today.format(formatter);
	        String cssSelector = "td[aria-label='" + formattedDate + "'] span[aria-selected='false']";

	        driver.findElement(By.cssSelector(".form-group:nth-child(4) .fa")).click();
	        Thread.sleep(1000);
	        WebElement todayDate = driver.findElement(By.cssSelector(cssSelector));
	        todayDate.click();

	        driver.findElement(By.cssSelector(".form-group:nth-child(5) .fa")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector("button[aria-label='Choose Year']")).click();
	        driver.findElement(By.xpath("//span[normalize-space()='2027']")).click();
	        driver.findElement(By.xpath("//span[normalize-space()=\"Aug\"]")).click();
	        driver.findElement(By.cssSelector("td[aria-label='2027-08-17'] span[aria-selected='false']")).click();

	        driver.findElement(By.cssSelector(".form-group:nth-child(6) #TSSGUI_SelectFieldStyle")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("TSSGUI_MultiSelectOptionsStyle")).click();
	        Thread.sleep(2000);

	        driver.findElement(By.cssSelector(".form-group:nth-child(7) > #fieldSetDiv > #TSSGUI_InputTextFieldSetStyle #TSSGUI_InputTextFieldStyle")).click();
	        Thread.sleep(2000);

	        // ✅ Using price from TestData
	        driver.findElement(By.cssSelector(".form-group:nth-child(7) > #fieldSetDiv > #TSSGUI_InputTextFieldSetStyle #TSSGUI_InputTextFieldStyle")).sendKeys(TestData.PRICE);
	        Thread.sleep(2000);

	        driver.findElement(By.cssSelector(".form-group:nth-child(8) > #fieldSetDiv > #TSSGUI_InputTextFieldSetStyle #TSSGUI_InputTextFieldStyle")).click();
	        Thread.sleep(2000);

	        // ✅ Using MRP from TestData
	        driver.findElement(By.cssSelector(".form-group:nth-child(8) > #fieldSetDiv > #TSSGUI_InputTextFieldSetStyle #TSSGUI_InputTextFieldStyle")).sendKeys(TestData.MRP);
	        Thread.sleep(2000);

	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div[2]/div/div[1]/div/div/div/div[1]/div[9]/div/div/fieldset/div/i")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div[2]/div/div[1]/div/div/div/div[1]/div[9]/div/div/div/div/ul/li[1]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".form-group:nth-child(10) .bi")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector("#tss-optionList > li:nth-child(1)")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".TSSGUI_FileIputStyle")).click();
	        Thread.sleep(2000);

	        Robot robot = new Robot();
	        String filePath = "C:\\Users\\Panchakshari\\eclipse-workspace\\snd\\src\\test\\java\\snd\\snd\\datause\\oskar-smethurst-B1GtwanCbiw-unsplash.jpg";
	        StringSelection selection = new StringSelection(filePath);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        Thread.sleep(3000);

	        // ✅ Using short description from TestData
	        driver.findElement(By.id("TSSGUI_BootstrapTextArea")).sendKeys(TestData.SHORT_DESCRIPTION);
	        Thread.sleep(2000);

	        WebElement element2 = driver.findElement(By.cssSelector(".form-group:nth-child(14) #TSSGUI_BootstrapTextArea"));

	        // ✅ Using long description from TestData
	        element2.sendKeys(TestData.LONG_DESCRIPTION);
	        Thread.sleep(2000);

	        Robot robot2 = new Robot();
	        robot2.keyPress(KeyEvent.VK_PAGE_DOWN);
	        robot2.keyRelease(KeyEvent.VK_PAGE_DOWN);
	        Thread.sleep(2000);

	        WebElement element1 = driver.findElement(By.cssSelector(".tss-btn-bg:nth-child(1)"));
	        element1.click();
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	        Thread.sleep(2000);

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));

	        String message = alert.getText().trim();
	        System.out.println("Product Addition result : " + message);

	        Assert.assertTrue(message.equalsIgnoreCase("Addition Successful"),
	                "❌ FAIL: Expected 'Addition Successful' but got '" + message + "'");
	    }

	    public void navigateToProductNode() throws InterruptedException, AWTException {
	        Thread.sleep(2000);
	        driver.findElement(By.tagName("body")).click();

	        Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_PAGE_UP);
	        robot.keyRelease(KeyEvent.VK_PAGE_UP);
	        Thread.sleep(2000);

//	        driver.findElement(By.cssSelector(".rc-tree-treenode:nth-child(4) > .rc-tree-switcher")).click();
//	        Thread.sleep(2000);
	//
//	        driver.findElement(By.xpath("//div[contains(text(),'" + TestData.PRODUCT_NAME + "')]")).click();
//	        Thread.sleep(2000);
	    }

	    
	    
	    @Test(priority = 2, dependsOnMethods = { "ProductAdd" })
	    public void productModification() throws InterruptedException, AWTException {
	        navigateToProductNode();
	        driver.findElement(By.cssSelector(".rc-tree-treenode:nth-child(4) > .rc-tree-switcher")).click();
	        Thread.sleep(2000);

	        driver.findElement(By.xpath("//div[contains(text(),'" + TestData.PRODUCT_NAME + "')]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".form-group:nth-child(2) > #fieldSetDiv > #TSSGUI_InputTextFieldSetStyle #TSSGUI_InputTextFieldStyle"))
	              .sendKeys(TestData.MOD_PRODUCT_CODE);
	        Thread.sleep(2000);

	        driver.findElement(By.id("TSSGUI_BootstrapTextArea")).sendKeys(TestData.MODIFIED_DESCRIPTION);
	        Thread.sleep(2000);

	        Robot robot2 = new Robot();
	        robot2.keyPress(KeyEvent.VK_PAGE_DOWN);
	        robot2.keyRelease(KeyEvent.VK_PAGE_DOWN);
	        Thread.sleep(2000);

	        driver.findElement(By.xpath("(//button[@type='button'])[12]")).click(); // likely "Submit" or "Save"
	        Thread.sleep(2000);

	        driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click(); // Confirm modal
	        Thread.sleep(2000);

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
	        String message = alert.getText().trim();

	        System.out.println("Product Modification result : " + message);
	        Assert.assertTrue(message.equalsIgnoreCase("Modification successful"),
	                "❌ FAIL: Expected 'Modification successful' but got '" + message + "'");
	    }
	    @Test(priority = 3)
	    public void productSlabModification() throws InterruptedException, AWTException {
	    	navigateToProductNode();
	    	driver.findElement(By.cssSelector("a[title=\"Slab\"]")).click();
	    	Thread.sleep(2000);
	        LocalDate today = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = today.format(formatter);
	        String cssSelector = "td[aria-label='" + formattedDate + "'] span[aria-selected='false']";

	        driver.findElement(By.xpath("//body//div[@id=\"root\"]//div[contains(@class,\"row\")]//div[contains(@class,\"row\")]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	        Thread.sleep(1000);
	        WebElement todayDate = driver.findElement(By.cssSelector(cssSelector));
	        todayDate.click();	

	        driver.findElement(By.xpath("//div[contains(@class,\"col-md-9 p-2\")]//div[2]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector("button[aria-label='Choose Year']")).click();
	        driver.findElement(By.xpath("//span[normalize-space()='2027']")).click();
	        driver.findElement(By.xpath("//span[normalize-space()=\"Aug\"]")).click();
	        driver.findElement(By.cssSelector("td[aria-label='2027-08-19'] span[aria-selected='false']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//div[contains(@class,\"col-md-9 p-2\")]//div[2]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[1]")).sendKeys("234");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys("678");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[normalize-space()=\"Update\"]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("(//button[normalize-space()='Yes'])[1]")).click();
	        Thread.sleep(2000);
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
	        String message = alert.getText().trim();

	        System.out.println("Product Slab Modification result : " + message);
	        Assert.assertTrue(message.equalsIgnoreCase("Modification successful"),
	                "❌ FAIL: Expected 'Modification successful' but got '" + message + "'");
	    }
	    
	    
	    @Test(priority = 4)
	    public void productcommissionAddition() throws InterruptedException, AWTException {
	    	navigateToProductNode();
	    driver.findElement(By.xpath("(//a[normalize-space()='Commission Rule'])[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[6]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[3]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//li[contains(@title,'All-Time')]")).click();
	    driver.findElement(By.xpath("//div[3]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[4]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("li:nth-child(1) input:nth-child(1)")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[5]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[6]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();  // Same as earlier, confirm if duplicate
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[7]//div[1]//div[1]//fieldset[1]//div[1]//i[1]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//li[contains(@title,'XSaleCommission')]")).click();
	    Thread.sleep(2000);
	    
	    WebElement element1 = driver.findElement(By.cssSelector(".tss-btn-bg:nth-child(1)"));
	    element1.click();
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	    Thread.sleep(2000);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));

	    String message = alert.getText().trim();
	    System.out.println("Product Commission Addition result : " + message);

	    Assert.assertTrue(message.equalsIgnoreCase("Addition Successful"),
	            "❌ FAIL: Expected 'Product Commission Addition Successful' but got '" + message + "'");

	    }
	    
	    @Test(priority = 5)
	    public void productDeletion() throws InterruptedException, AWTException {
	        navigateToProductNode();
	        driver.findElement(By.xpath("//div[contains(text(),'" + TestData.PRODUCT_NAME + "')]")).click();
	        Thread.sleep(2000);
	       WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + TestData.PRODUCT_NAME + "')]"));
	     Actions actions = new Actions(driver);
	        actions.contextClick(element).perform();
	        Thread.sleep(2000);
	       driver.findElement(By.cssSelector(".contexify_itemContent")).click(); // Delete option
	        Thread.sleep(4000);

	       driver.findElement(By.cssSelector(".swal2-confirm")).click(); // Confirm deletion
	      Thread.sleep(2000);

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
	        String message = alert.getText().trim();

	       System.out.println("Product Deletion result : " + message);
	        Assert.assertTrue(message.equalsIgnoreCase("Product deleted successfully."),
	                "❌ FAIL: Expected 'Product deleted successfully.' but got '" + message + "'");
	  
	}
	    private void println(String string) {
			// TODO Auto-generated method stub
			
		}

		@AfterClass
	    public void tearDown() {
	        //if (driver != null) {
	       //     driver.quit();
	        //}
	    }
	}


