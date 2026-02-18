package snd;



	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Duration;
	import java.util.Map;

	import org.junit.Assert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import snd.dataprovider;

	public class creditpurchase {
		private WebDriver driver;
		JavascriptExecutor js;

		// DB validation
		String DB_URL = "jdbc:mariadb://10.0.6.161:3306/snd";
		String DB_USER = "root";
		String DB_PASSWORD = "Root@t4y4n4";

		@BeforeClass
		public void Testing() throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			js = (JavascriptExecutor) driver;
			driver.manage().window().maximize();
//			Thread.sleep(2000);
			driver.get("https://" + dataprovider.host + ":" + dataprovider.port + "/snd/snd/login");
//			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/form/div[1]/input"))
					.sendKeys(dataprovider.USERNAME);
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(dataprovider.PASSWORD);
			driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[4]/button")).click();
			Thread.sleep(2000);
		}

		@Test(priority = 1)
		public void success_case() throws InterruptedException, SQLException, ClassNotFoundException {
			driver.findElement(By.xpath("//a[contains(.,'Partner Directory')]")).click();
			driver.findElement(By.xpath("//*[@id='input_Dealer Name']")).sendKeys("BillPayDealer BPDL");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='card-body']//div//button[@type='button']")).click();
			driver.findElement(By.xpath("//div[@class='d-flex justify-content-between align-items-start']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='tab-dealer-transfer']")).click();
			driver.findElement(By.xpath("//div[contains(@class,'TSSGUI_SelectFieldDropDownIcon')]//i[contains(@class,'')]"))
					.click();
			driver.findElement(By.xpath("//li[@title='10 (Airtime)']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[2]")).sendKeys("10");
			driver.findElement(By.xpath("//button[@data-toggle='modal']//*[name()='svg']")).click();
			// navigating to Password field
			driver.findElement(By.xpath(
					"//div[@id='PasswordModal']//div[@role='document']//div[@class='modal-content']//div[@id='addDealerGroupBody']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//div//input[@id='TSSGUI_InputTextFieldStyle']"))
					.sendKeys("Admin@123");
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[2]/div[3]/button[1]"))
					.click();
			Thread.sleep(2000);
			//greping the msge
			String actualMsge=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")).getText();
			System.out.println(actualMsge);
			String expectedMsge="Transaction successful";
			if(actualMsge.equals(expectedMsge))
				System.out.println("Success Msge Verified "+ actualMsge);
			else {
				System.out.println("Success Msge Failed to get the msge: "+ actualMsge);
				Assert.fail();
			}
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
			

	        // ORDER NUMBER
			Thread.sleep(1000);
	        String orderIdraw = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[2]")).getText().trim();
	        String orderId = orderIdraw.replaceAll("\\D+", "");
	        System.out.println("Clean Order ID: " + orderId);


	        // Wallet Balance
	        String walletText = driver.findElement(By.xpath("//*[@id='table']/tbody/tr/td[4]/div")).getText().trim();
	        
	        double uiWalletBalance = Double.parseDouble(walletText);
	        System.out.println("UI Wallet Balance: " + uiWalletBalance);

	        
	     // Database validation using kiosk order number
	        try (Connection connection = DriverManager.getConnection(
	        		"jdbc:mariadb://10.0.6.161:3306/snd", "root", "Root@t4y4n4")) {

	            Thread.sleep(2000);

	            String sqlQuery =
	                "SELECT BALANCE " +
	                "FROM snd.bdr_transactions " +
	                "WHERE ORDER_NUMBER = ? AND ACCOUNT_ID = ?";

	            PreparedStatement ps = connection.prepareStatement(sqlQuery);
	            ps.setString(1, orderId);  // order number from UI
	            ps.setInt(2, 10);          // account_id

	            ResultSet resultSet = ps.executeQuery();

	            if (resultSet.next()) {
	                //long bdrId = resultSet.getLong("BDR_ID");
	                long dbBalance = resultSet.getLong("BALANCE");

	                double uiBalanceInPaise = uiWalletBalance * 100;

	                //System.out.println("BDR_ID: " + bdrId);
	                System.out.println("DB Balance (paise): " + dbBalance);
	                System.out.println("UI Balance (paise): " + uiBalanceInPaise);

	                Assert.assertEquals(
	                    "Wallet balance mismatch!",uiBalanceInPaise,dbBalance,0.01);

	                System.out.println("DB validation passed");
	            } else {
	                Assert.fail("No data found for ORDER_NUMBER = " + orderId);
	            }

	            resultSet.close();
	            ps.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

			}
		
		@Test(priority = 2)
		public void negative_case() throws InterruptedException {
			driver.findElement(By.xpath("//i[@title='Site Map']")).click();
			driver.findElement(By.xpath("//a[contains(.,'Partner Directory')]")).click();
			driver.findElement(By.xpath("//*[@id='input_Dealer Name']")).sendKeys("BillPayDealer BPDL");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='card-body']//div//button[@type='button']")).click();
			driver.findElement(By.xpath("//div[@class='d-flex justify-content-between align-items-start']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='tab-dealer-transfer']")).click();
			driver.findElement(By.xpath("//div[contains(@class,'TSSGUI_SelectFieldDropDownIcon')]//i[contains(@class,'')]"))
					.click();
			driver.findElement(By.xpath("//li[@title='10 (Airtime)']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[2]")).sendKeys("10000");
			driver.findElement(By.xpath("//button[@data-toggle='modal']//*[name()='svg']")).click();
			// navigating to Password field
			driver.findElement(By.xpath(
					"//div[@id='PasswordModal']//div[@role='document']//div[@class='modal-content']//div[@id='addDealerGroupBody']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//div//input[@id='TSSGUI_InputTextFieldStyle']"))
					.sendKeys("Admin@123");
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[2]/div[3]/button[1]"))
					.click();
			Thread.sleep(2000);
			//greping the msge
			String actualMsge=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")).getText();
			System.out.println(actualMsge);
			String expectedMsge="FAILURE";
			System.out.println(expectedMsge);
			if(actualMsge.endsWith(expectedMsge))
				System.out.println("Success Msge Verified "+ actualMsge);
			else {
				System.out.println("Success Msge Failed to get the msge: "+ actualMsge);
				Assert.fail();
			}
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
		}
		
	     

		@AfterClass
		public void exit() {
			driver.quit();
		}

	}


