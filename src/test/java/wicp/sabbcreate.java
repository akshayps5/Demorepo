package wicp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class sabbcreate {
	private WebDriver driver;
	private String filePath = "C:/Users/akshay.ps/Downloads/data.xlsx"; // Path to Excel file

	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://10.0.6.253:8443/wicp/welcome/jsp/login.jsp");
	}

	@Test(priority = 1, dataProvider = "loginData") // Keep data-driven approach for login
	public void login(String username, String password) throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://10.0.6.253:8443/wicp/welcome/jsp/login.jsp");

		// Login steps
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("subBtn")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 2, dataProvider = "serviceData")
	public void sabb3(String serviceName, String sabbName) throws InterruptedException {
	
		driver.findElement(By.xpath("//*[@id='contentDiv']/section[2]/div[2]/div/div/div[5]/div/div[2]/ul/li[2]/a/b"))
				.click();
		Thread.sleep(1000);
		{
			WebElement element = driver.findElement(By.cssSelector(".tss-add"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector(".tss-add")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("sabbName")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("sabbName")).sendKeys(sabbName);
		driver.findElement(By.id("serviceSB")).click();
		Thread.sleep(1000);

		// Select dropdown and replace "Originating Call" dynamically
		WebElement dropdown = driver.findElement(By.id("serviceSB"));
		dropdown.findElement(By.xpath("//option[contains(text(), '" + serviceName + "')]")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("descCfgTA")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("descCfgTA")).sendKeys("sample");
		driver.findElement(By.id("categorySB_G0_G1_R1")).click();
		Thread.sleep(1000);
		{
			WebElement dropdown1 = driver.findElement(By.id("categorySB_G0_G1_R1"));
			dropdown1.findElement(By.xpath("//option[. = 'Parameter']")).click();
		}
		// driver.findElement(By.id("paramSB_G0_G1_R1")).click();
		// Thread.sleep(1000);
		{
			WebElement dropdown2 = driver.findElement(By.id("paramSB_G0_G1_R1"));
			dropdown2.findElement(By.xpath("//option[contains(text(),'Called PartyAddress')]")).click();

			Thread.sleep(1000);
		}
		driver.findElement(By.id("select2-outputVal_1-container")).click();
		driver.findElement(By.cssSelector(".select2-container--open .select2-selection")).click();

		Thread.sleep(1000);
		WebElement dropdown3 = driver.findElement(By.id("enumOpSB_G0_G1_R1"));
		dropdown3.findElement(By.xpath("//option[contains(text(),'IN')]")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"paramVal_G0_G1_R1\"]")).sendKeys("12454646");
		Thread.sleep(1000);
		driver.findElement(By.id("select2-categoryVal_1-container")).click();
		WebElement dropdown5 = driver.findElement(By.cssSelector(".select2-search__field"));
		dropdown5.sendKeys("DOB");
		Thread.sleep(2000); // Wait for the dropdown options to load

		// Press ENTER to select the first matching result
		dropdown5.sendKeys(Keys.ENTER);
		Thread.sleep(2000); // Wait for the selection to reflect
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[2]/div/section[2]/div[3]/div/div/form/div/div[1]/div[1]/div[2]/div[1]/ul/li/div/div/div/div[2]/span/span[1]/span"))
				.click();
		WebElement dropdown6 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
		dropdown6.sendKeys("Today");
		dropdown6.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='select2-categoryVal-container']")).click();
		WebElement dropdown7 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
		dropdown7.sendKeys("DOB");
		Thread.sleep(2000);
		dropdown7.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id='select2-outputVal-container']")).click();
		WebElement dropdown8 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
		dropdown8.sendKeys("Today");
		dropdown8.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[2]/div/section[2]/div[3]/div/div/form/div/div[1]/div[1]/div[4]/div[2]/button"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[6]/div[7]/div/button")).click();
  
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollTo(0,144.6666717529297)");
		  
		WebElement dropdown9 = driver
				.findElement(By.xpath("/html/body/div[2]/div[2]/header/nav/div/div[2]/ul[1]/li/a"));
		dropdown9.click();
	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("sabb");

		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount - 1][2]; // Only username & password

		for (int i = 1; i < rowCount; i++) { // Start from row index 1 (skip headers)
			Row row = sheet.getRow(i);
			data[i - 1][0] = row.getCell(0).getStringCellValue(); // Username
			data[i - 1][1] = row.getCell(1).getStringCellValue(); // Password
		}

		workbook.close();
		fileInputStream.close();
		return data;
	}

	@DataProvider(name = "serviceData")
	public Object[][] getServiceData() throws IOException {
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("sabb");

		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount - 1][2]; // Only serviceName & sabbName

		for (int i = 1; i < rowCount; i++) { // Start from row index 1 (skip headers)
			Row row = sheet.getRow(i);
			data[i - 1][0] = row.getCell(2).getStringCellValue(); // Service Name
			data[i - 1][1] = row.getCell(3).getStringCellValue(); // Sabb Name
		}

		workbook.close();
		fileInputStream.close();
		return data;
	}

	@AfterMethod
	public void tear3Down() {
		// driver.quit(); // Close browser after tests
	}

	@AfterClass
	public void tearDown() {
		// driver.quit(); // Close browser after tests
	}
}
