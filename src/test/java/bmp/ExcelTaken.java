package bmp;



	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.*;
	import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
	import org.testng.annotations.*;

	public class ExcelTaken {
	    public static WebDriver driver;
	    private static final String EXCEL_PATH = "C:\\Users\\akshay.ps\\Documents\\login_data.xlsx";

	    public Object[][] readExcelData(String filePath, String sheetName) throws IOException {
	        FileInputStream file = new FileInputStream(new File(filePath));
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheet(sheetName);

	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getLastCellNum();
	        Object[][] data = new Object[rowCount - 1][colCount];

	        for (int i = 1; i < rowCount; i++) { // Skip header row
	            Row row = sheet.getRow(i);
	            for (int j = 0; j < colCount; j++) {
	                Cell cell = row.getCell(j);
	                data[i - 1][j] = (cell != null) ? cell.toString() : "";
	            }
	        }
	        workbook.close();
	        return data;}
	    
	    @BeforeClass
	    public void setUp() {
	        driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        driver.get("https://10.0.6.137:8443/bmp");
	    }

	    @DataProvider(name = "loginData")
	    public Object[][] getLoginData() throws IOException {
	        return readExcelData(EXCEL_PATH, "Sheet1"); // Reads data from "Sheet1"
	    }

	    @Test(dataProvider = "loginData")
	    public void testLogin(String username, String password) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Login process
	        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username")));
	        usernameField.sendKeys(username);

	        WebElement passwordField = driver.findElement(By.id("tsslogin-form_password"));
	        passwordField.sendKeys(password);

	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        // Validate Login Success
	        boolean isLoginSuccessful = driver.findElements(By.xpath("//div[contains(text(),'Dashboard')]")).size() > 0;
	        Assert.assertTrue(isLoginSuccessful, "Login Failed for user: " + username);
	        
	        System.out.println("Login successful for: " + username);

	        // Instead of back button, reload the login page
	        driver.get("https://10.0.6.137:8443/bmp");

	        // Wait for login page to load
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username")));

	        System.out.println("Navigated back to Login Page.");
	    }


	    @AfterClass
	    public void tearDown() {
	        //if (driver != null) {
	        //    driver.quit();
	      //  }
	    }

	    
	}


