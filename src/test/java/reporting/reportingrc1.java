package reporting;


	import java.time.Duration;

	import org.apache.commons.io.filefilter.FalseFileFilter;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.Test;
	import java.util.HashMap;
	import java.util.Map;
	import java.awt.AWTException;

	public class reportingrc1 {
		
		ChromeOptions options = new ChromeOptions();
	    
	    WebDriver driver= new ChromeDriver(options);

	public  void sample1(){
		Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	   prefs.put("profile.password_manager_enabled", false);
	   options.setExperimentalOption("prefs", prefs);
	   
	   options.setBinary("/path/to/chromium");
	   options.setBinary("C:/Path/To/Chromium.exe");
	   options.setBinary("C:/Path/To/Chromium.exe"); // or chromium-browser
	   options.addArguments("user-data-dir=C:/temp/ChromeProfile_" + System.currentTimeMillis());
	   options.addArguments("--disable-save-password-bubble");
	   options.addArguments("--disable-extensions");
	   options.addArguments("--disable-popup-blocking");
	   options.addArguments("--no-default-browser-check");
	   options.addArguments("--disable-infobars");
	   options.addArguments("--start-maximized");
	     
	}
		@Test
//		WebDriver driver= new ChromeDriver();
		public void demo1() throws InterruptedException {
			System.out.println("hi naren testng working fine");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			driver.get("https://10.0.5.159:8003/reporting/login");
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
			
			 driver.findElement(By.xpath("//input[@placeholder='User ID']")).sendKeys(dataprovider.USERNAME);
			
			 driver.findElement(By.xpath("//input[@type='password']")).sendKeys(dataprovider.PASSWORD);
			 
			 driver.findElement(By.xpath("//button[@type='submit']")).click();
			 Thread.sleep(2000);
				 
		}
		@Test(dependsOnMethods ="demo1",enabled = true)
		public void Reporthub() throws InterruptedException  {
			
			
			driver.findElement(By.xpath("//a[contains(., 'Report Hub')]")).click();
			 
			 Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@title='Mobile Initiated Report']")).click();
			
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//button[contains(text(),'Monthly')]")).click();
			 
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//button[contains(.,'Go')]")).click();
			 
			 
//			// ------------------Report Sharing With in the Report------------------//
//			 Thread.sleep(2000);
//			 driver.findElement(By.xpath("//body/div[@id='root']/div[contains(@class,'wrapper')]/div[@id='rightSectionDiv']/section[contains(@class,'content')]/div[contains(@class,'content-body')]/div[contains(@class,'card')]/div[contains(@class,'card-body align-items-center py-8')]/div[contains(@class,'row')]/div[contains(@class,'col-md-12')]/div[contains(@class,'mrt-table-paper MRT_TablePaper-module_root__q0v5L m_1b7284a3 mantine-Paper-root')]/div[contains(@class,'common-styles-module_common-toolbar-styles__DnjR8 MRT_TopToolbar-module_root__r4-V9')]/div[contains(@class,'MRT_TopToolbar-module_actions-container__-uL0u MRT_TopToolbar-module_actions-container-stack-alert__OYDL6 m_8bffd616 mantine-Flex-root __m__-r8')]/div[1]//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();
//			 
//			 driver.findElement(By.xpath("//input[@id='TSSGUI_SelectFieldStyle']")).click();
//				
//				Thread.sleep(2000);
//				driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
//				
//				Thread.sleep(2000);
//				driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys("Just for Testing Purpose");
//				driver.findElement(By.xpath("(//button[normalize-space()='Share Separately'])[1]")).click();
//				driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
//				Thread.sleep(2000);
//				
//				//-----------------------Adding to Favourite-------------//
//				
//				driver.findElement(By.xpath("(//i[@title='Add to Favourites'])[1]")).click();
//				Thread.sleep(2000);
//				driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[2]")).sendKeys("SampleNaren");
//				
//				
//				driver.findElement(By.xpath("(//textarea[@id='TSSGUI_BootstrapTextArea'])[2]")).sendKeys("For Testing Purpose");
//				Thread.sleep(2000);
//				driver.findElement(By.xpath("(//button[@type='button'])[152]")).click();			
//				 driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
//			 Thread.sleep(2000);
//			 driver.findElement(By.xpath("(//button[@aria-label='Close this dialog'])[1]")).click();
//			 Thread.sleep(2000);
			 
			 driver.findElement(By.xpath("//a[@class='tss-anchor' and text()='Home']")).click();
			
	}
		
		
//		@Test(dependsOnMethods ="Reporthub",enabled = true)
//		public void Share() throws InterruptedException {
//			
//			driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[6]")).click();
//			
//			driver.findElement(By.xpath("//input[@id='TSSGUI_SelectFieldStyle']")).click();
//			
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
//			
//			driver.findElement(By.xpath("(//button[normalize-space()='Share Separately'])[1]")).click();
//			
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys("Just for Testing Purpose");
//			
//			
//			
//		}
		
		@Test(dependsOnMethods = "Reporthub",enabled = true)
		public void ReportAccessDetails() throws InterruptedException {
			
			driver.findElement(By.xpath("//a[contains(.,'Report Access Details')]")).click();
			 
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//button[@aria-label='Page 2']")).click();
			 
			 WebElement search = driver.findElement(By.xpath("//section[@class='content']//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//span[1]//div[1]//input[1]"));
			 search.sendKeys("bmp");
			 Thread.sleep(2000);
			 
			 WebElement Reportsearch = driver.findElement(By.xpath("//section[2]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//span[1]//div[1]//input[1]"));
			Reportsearch.sendKeys("prs");
			
			Thread.sleep(2000);
		    driver.findElement(By.xpath("//section[@class='content']//section[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//div[3]//div[1]//button[1]//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();
		    //----Download-------//
//	        Thread.sleep(2000);
//	        driver.findElement(By.xpath("//li[@id='pr_id_5_3']//a[@class='p-menuitem-link']//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")).click();
	        
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//a[@class='tss-anchor' and text()='Home']")).click();
	              
		}
		
		@Test(dependsOnMethods = "ReportAccessDetails",enabled =true)
		public void ReportConfigurator() throws InterruptedException {
			
			//----------------------------------------Report configurator-----------------------------------//
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(.,'Report Configurator')]")).click();
			
//			Thread.sleep(2000);
//			WebElement drop = driver.findElement(By.xpath("//body//div[@id='root']//div[@class='row']//div[@class='row']//fieldset[1]//div[1]//i[1]"));
//			drop.click();
//			Select opt1= new Select(drop);
//			opt1.selectByValue("BMP");
			
			driver.findElement(By.xpath("//body//div[@id='root']//div[@class='row']//div[@class='row']//fieldset[1]//div[1]//i[1]")).click();
			driver.findElement(By.xpath("//li[@title='BMP']")).click();
			
			Thread.sleep(2000);
			WebElement name = driver.findElement(By.xpath("//div[@class='row']//div[2]//div[1]//fieldset[1]//div[1]//input[1]"));
			Thread.sleep(2000);
			name.click();
			Thread.sleep(2000);
			name.sendKeys("naren");
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(@class,'row')]//div[3]//div[1]//div[1]//fieldset[1]//input[1]")).click();
			driver.findElement(By.xpath("//li[@title='Tayana Reports']")).click();
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[normalize-space()='JSON Configurable Report']")).click();
			
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[@class='card-body py-8']//div[@class='form-group col-md-12']//textarea[@id='TSSGUI_BootstrapTextArea']")).sendKeys("naren{id:01,number:6380857089}");
			
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='selectForm ']//input[@id='TSSGUI_SelectFieldStyle']")).click();
			driver.findElement(By.xpath("//li[@id='TSSGUI_MultiSelectOptionsStyle']")).click();
			
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//body//div[@id='root']//section[@class='content']//section[@class='content']//button[1]")).click();
//			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='tss-anchor' and text()='Home']")).click();
			
		}
		
		@Test(priority = 5,enabled=true)
		public void accountcreation() throws InterruptedException, AWTException {
			

			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(.,'Accounts')]")).click();
			
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@id='rightSectionDiv']//div[1]//div[1]//div[1]//div[2]//div[3]//*[name()='svg']")).click();
			
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class='form-group col-md-4']//div[@id='fieldSetDiv']//fieldset[@id='TSSGUI_InputTextFieldSetStyle']//div//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys(dataprovider.PASSWORD);
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//div[@class='form-group col-md-2 pt-2']//button[@id='confirmButton']")).click();
			
			//----------------------------creation of account-----------------------------------//
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='card-body align-items-center py-8']//div[1]//div[1]//div[1]//fieldset[1]//div[1]//div[1]//div[1]//input[1]")).sendKeys("vishwa");
			
			driver.findElement(By.xpath("//div[@id='rightSectionDiv']//div[2]//div[1]//fieldset[1]//div[1]//div[1]//div[1]//input[1]")).sendKeys(dataprovider.VishwaPASSWORD);
					
			driver.findElement(By.xpath("//div[@class='row']//div[3]//div[1]//fieldset[1]//div[1]//div[1]//div[1]//input[1]")).sendKeys(dataprovider.VishwaPASSWORD);
					
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[4]")).sendKeys("t25");
			
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[8]")).sendKeys("vishwa@gmail.com");
			
			driver.findElement(By.xpath("(//input[@id='TSSGUI_InputTextFieldStyle'])[9]")).sendKeys("8675377109");
			
			driver.findElement(By.xpath("//tbody//tr//fieldset[1]")).click();
			driver.findElement(By.xpath("//li[@title='Administrator']")).click();
			
			driver.findElement(By.xpath("//button[@id='previewButton']")).click();
			
			
			//------Conformation-------//
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[@id='confirmButton'])[1]")).click();	
			
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();
		}
		
		@Test(priority=6,enabled=true)
		public void deletion() throws InterruptedException{
			
			driver.navigate().refresh();
			driver.findElement(By.xpath("(//*[name()='svg'][contains(@role,'img')])[12]")).click();
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='TSSGUI_InputTextFieldStyle']")).sendKeys(dataprovider.PASSWORD);
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[@id='confirmButton'])[1]")).click();
		
			driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();	
		}

	}

