package bmp_react;
	import java.time.Duration;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import io.github.bonigarcia.wdm.WebDriverManager;

	public class clientcreate {
		public static WebDriver driver;
		public static void main(String[] args) throws InterruptedException {
		

			WebDriver driver = new FirefoxDriver(); // Initialize the WebDriver instance
			clientcreate.driver = driver; // Set

	     
	        // Now you can proceed with your test automation script
	        driver.get("https://10.0.6.137:8443/bmp");
	        driver.manage().window().maximize();
	        Thread.sleep(4000);
	        driver.findElement(By.xpath("//input[@id='tsslogin-form_username']")).sendKeys("admin");
	        driver.findElement(By.xpath("//*[@id=\"tsslogin-form_password\"]")).sendKeys("Admin123");
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
	        Thread.sleep(4000);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[3]/a")).click();
	        Thread.sleep(4000);
	        
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/input[1]")).sendKeys("Xavier");
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[9]/div[1]/*[name()='svg'][2]/*[name()='path'][1]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")).click();
	        
	        Thread.sleep(4000);
	        
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/*[name()='svg'][1]")).click();
	        driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("Xavier");
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("Xavier1234");
	        
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[4]/div/fieldset/div[1]/div/input")).sendKeys("10");
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[9]/div/fieldset/div[1]/div/input")).sendKeys("xavier@gmail.com");
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'BMP1234')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[12]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Postpaid')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[13]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("12345678");
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[14]/div[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("9876");
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[15]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[16]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[17]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[22]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[23]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[19]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[24]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Retry')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[25]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[26]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'Enable')]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[28]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	        driver.findElement(By.xpath("//li[contains(@title,'testpk')]")).click();
	        
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[3]/div/fieldset/div[1]/div/input")).sendKeys("1");
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/button[1]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")).click();
	        
	        
	        Thread.sleep(4000);
	        
	       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[1]/label[1]")).click();
	       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/i[1]")).click();
	       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[9]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]")).click();
	        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[6]/button[1]")).click();
	        
	          
	        
	        
	        System.out.println("susccess xavier");

		}

	}
