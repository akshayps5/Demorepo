package snd;



	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.time.Duration;
	import java.util.Map;

	import org.testng.Assert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import snd.dataprovider;

	public class scheduketransfer {
		private  WebDriver driver;
		private Map<String, Object> vars;
		JavascriptExecutor js;
	    public WebDriverWait wait ;


		@BeforeClass
		public void login() throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			js = (JavascriptExecutor) driver;
			driver.manage().window().maximize();
//			Thread.sleep(2000);
			driver.get("https://" + dataprovider.host + ":" + dataprovider.port + "/snd/login");
//			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/form/div[1]/input"))
					.sendKeys(dataprovider.USERNAME);
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(dataprovider.PASSWORD);
			driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/form/div[4]/button")).click();
			Thread.sleep(2000);
		}
		
		@BeforeMethod
	    public void waiting() {
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after driver
	    }
		@Test(priority = 1)
		public void po_to_pre_weekly_Freq() throws InterruptedException {
			driver.findElement(By.xpath("//a[contains(.,'Schedule Transfer')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='button-container']//*[name()='svg']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@align='left']//div//div[@class='row']//div[@id='tss-inputGroup-noappend']//div//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("2570415");
			driver.findElement(By.xpath("//div[@class='card-body']//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//div[1]//input[1]")).sendKeys("2510029");
			driver.findElement(By.xpath("//input[2]")).sendKeys("10");
			driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='content-body']/div/div/section[@class='content']/div[@class='card']/div[@class='card-body']/div[@class='row']/div[@class='form-group col-md-4']/div[1]/fieldset[1]/div[1]/i[1]")).click();
		    driver.findElement(By.xpath("//span[@class='p-highlight']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//div[@class='SelectContainer']//fieldset[1]//div[1]//i[1]")).click();
		    driver.findElement(By.xpath("//li[@title='Weekly']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		    Thread.sleep(2000);
		    String actualMessage=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")).getText();
		    Thread.sleep(3000);
		    System.out.println(actualMessage);
		    String expectedMesage="Created Successful";
		    if(actualMessage.equals(expectedMesage)) {
		    	System.out.println("Success Message verified" + actualMessage);
		    }
		    else {
		    	System.out.println("Success Message Verification failed " + actualMessage);
		    	Assert.fail();
		    }
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
		    Thread.sleep(3000);
		    
		    long msisdn=2482570415l;
		    long dest_msisdn= 2482510029l;
		 // Database validation
	        try (Connection connection = DriverManager.getConnection(
	        		"jdbc:mariadb://10.0.6.161:3306/cws_cbx", "root", "Root@t4y4n4")) {

	            Thread.sleep(2000);

	            String sqlQuery =
	                "SELECT subs_id " +
	                "FROM cws_cbx.qtqr_schd_transfers " +
	                "WHERE dest_msisdn = ?";

	            PreparedStatement ps = connection.prepareStatement(sqlQuery);
	            ps.setLong(1, dest_msisdn);

	            ResultSet resultSet = ps.executeQuery();

	            if (resultSet.next()) {
	                //long bdrId = resultSet.getLong("BDR_ID");
	                long dbmsisdn = resultSet.getLong("subs_id");

//	                double uiBalanceInPaise = uiWalletBalance * 100;

	                //System.out.println("BDR_ID: " + bdrId);
	                System.out.println("source msisdn" + dbmsisdn);

	                Assert.assertEquals(dbmsisdn,msisdn);

	                System.out.println("DB validation passed");
	            } else {
	                Assert.fail("No data found for dest_msisdn = " + dest_msisdn);
	            }

	            resultSet.close();
	            ps.close();
	            connection.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		    
		}
		

		@Test(priority = 2)
		public void po_to_pre_monthly_Freq_duplicate_req() throws InterruptedException {
			driver.findElement(By.xpath("//ul[contains(@class,'navbar-nav ml-3')]//li[1]")).click();
			driver.findElement(By.xpath("//a[contains(.,'Schedule Transfer')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='button-container']//*[name()='svg']")).click();
//			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/*[name()='svg'][1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@align='left']//div//div[@class='row']//div[@id='tss-inputGroup-noappend']//div//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("2570415");
			driver.findElement(By.xpath("//div[@class='card-body']//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//div[1]//input[1]")).sendKeys("2555555");
			driver.findElement(By.xpath("//input[2]")).sendKeys("100");
			driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='content-body']/div/div/section[@class='content']/div[@class='card']/div[@class='card-body']/div[@class='row']/div[@class='form-group col-md-4']/div[1]/fieldset[1]/div[1]/i[1]")).click();
		    driver.findElement(By.xpath("//span[@class='p-highlight']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//div[@class='SelectContainer']//fieldset[1]//div[1]//i[1]")).click();
		    driver.findElement(By.xpath("//li[@title='Monthly']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		    String error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]"))).getText().trim();
//	        cltElement.click();
	 
//		    String error = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/h2[1]")).getText().trim();


		    System.out.println("Actual message: " + error);

		    Assert.assertTrue(error.contains("Duplicate request"),
		            "Expected duplicate request error but got: " + error);
		
//		    String expectedMesage="Duplicate request Schedule already exists with this number";
//		    if(actualMessage.startsWith(expectedMesage)) {
//		    	System.out.println("Success Message verified " + actualMessage);
//		    }
//		    else {
//		    	System.out.println("Success Message Verification failed " + actualMessage);
//		    	Assert.fail();
//		    }
//		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
		    Thread.sleep(2000);
		}
		
		
		@Test(priority = 3)
		public void user_not_found() throws InterruptedException {
			driver.findElement(By.xpath("//ul[contains(@class,'navbar-nav ml-3')]//li[1]")).click();
			driver.findElement(By.xpath("//div[7]//div[1]//div[2]//ul[1]//li[1]//a[1]")).click();
			driver.findElement(By.xpath("//div[@class='button-container']//*[name()='svg']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@align='left']//div//div[@class='row']//div[@id='tss-inputGroup-noappend']//div//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//div//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys("2570455");
			driver.findElement(By.xpath("//div[@class='card-body']//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//div[1]//input[1]")).sendKeys("2555555");
			driver.findElement(By.xpath("//input[2]")).sendKeys("100");
			driver.findElement(By.xpath("//body/div[@id='root']/div[@class='wrapper']/div[@id='rightSectionDiv']/section[@class='content']/div[@class='content-body']/div/div/section[@class='content']/div[@class='card']/div[@class='card-body']/div[@class='row']/div[@class='form-group col-md-4']/div[1]/fieldset[1]/div[1]/i[1]")).click();
		    driver.findElement(By.xpath("//span[@class='p-highlight']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//div[@class='SelectContainer']//fieldset[1]//div[1]//i[1]")).click();
		    driver.findElement(By.xpath("//li[@title='Monthly']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
		    driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
		    Thread.sleep(1000);
		    String actualMessage=driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText();
		 //   xpath=
		    System.out.println("this is actual meesage "+actualMessage);
		    String expectedMesage="User not found";
		    if(actualMessage.contains(expectedMesage)) {
		    	System.out.println("Success Message verified " + actualMessage);
		    }
		    else {
		    	System.out.println("Success Message Verification failed " + actualMessage);
		    	Assert.fail();
		    }
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
		    Thread.sleep(2000);
		}

		
		
		@AfterClass
		public void exit() {
			if(driver!=null) {
				driver.close();
			}
		}
		

	

}
