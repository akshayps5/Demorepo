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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class doesit {
	private WebDriver driver;
	private String filePath = "C:/Users/akshay.ps/Downloads/data.xlsx"; // Escaped backslashes

	@BeforeClass
	public void setUp() {
		// Update with actual geckodriver path
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://10.0.6.137:8443/bmp");
	}

	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password) throws InterruptedException {
		System.out.println("Testing login for user: " + username);

		// Clear existing values and enter new credentials
		driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).clear();
		WebElement nameInput1 = driver.findElement(By.xpath("//input[@id='tsslogin-form_username']"));
		nameInput1.sendKeys(null);
		driver.findElement(By.xpath("//*[@id='tsslogin-form_password']")).clear();
		driver.findElement(By.xpath("//*[@id='tsslogin-form_password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// driver.navigate().back();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".tss-icon > .fas")).click();

		driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		Thread.sleep(3000); // Wait for the login process

	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new IOException("Excel file not found at: " + filePath);
		}

		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("data");

		if (sheet == null) {
			workbook.close();
			throw new IOException("Sheet 'data' not found in Excel file.");
		}

		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount][2];

		for (int i = 0; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row == null || row.getCell(0) == null || row.getCell(1) == null) {
				System.out.println("Skipping row " + i + " due to missing data");
				continue;
			}

			data[i][0] = row.getCell(0).getStringCellValue();
			data[i][1] = row.getCell(1).getStringCellValue();
		}

		workbook.close();
		fileInputStream.close();
		return data;
	}

	@AfterClass
	public void tearDown() {
		// driver.quit(); // Close the browser after all tests
	}
}
