package bmp_react;


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	public class Does {
	    private WebDriver driver;
	    private String filePath = "C:/Users/akshay.ps/Documents/"; // Update with actual path

	    @BeforeClass
	    public void setUp() {
	        driver = new FirefoxDriver(); 
	        driver.manage().window().maximize();
	        driver.get("https://10.0.6.137:8443/bmp");
	    }

	    @Test(dataProvider = "loginData")
	    public void loginTest(String username, String password) throws InterruptedException {
	        System.out.println("Testing login for user: " + username);

	        driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).clear();
	        driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).sendKeys(username);
	        driver.findElement(By.xpath("//*[@id='tsslogin-form_password']")).clear();
	        driver.findElement(By.xpath("//*[@id='tsslogin-form_password']")).sendKeys(password);
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        Thread.sleep(5000); // Wait for the login process
	    }

	    @DataProvider(name = "loginData")
	    public Object[][] getLoginData() throws IOException {
	        File file = new File(filePath);
	        FileInputStream fileInputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(fileInputStream);
	        Sheet sheet = workbook.getSheet("data.xlsx");

	        int rowCount = sheet.getPhysicalNumberOfRows();
	        Object[][] data = new Object[rowCount - 1][2]; // Skipping header row

	        for (int i = 1; i < rowCount; i++) { // Start from 1 to skip headers
	            Row row = sheet.getRow(i);
	            if (row == null || row.getCell(0) == null || row.getCell(1) == null) {
	                continue;
	            }
	            data[i - 1][0] = row.getCell(0).getStringCellValue();
	            data[i - 1][1] = row.getCell(1).getStringCellValue();
	        }

	        workbook.close();
	        fileInputStream.close();
	        return data;
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}

