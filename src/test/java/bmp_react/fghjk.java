package bmp_react;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class fghjk {

	WebDriver driver;

	@BeforeMethod
	public void syso() {
		
	
	driver= new ChromeDriver();

        driver.get("https://www.yatra.com/react-home?");
        driver.manage().window().maximize();  
        try {
            driver.switchTo().alert().accept(); // Accept alert if present
        } catch (Exception e) {
            System.out.println("No alert present.");
        }
        
        WebElement fgh = driver.findElement(By.xpath("//*[contains.text(),'yatraLogo')]"));
 String text= fgh.getText();
      
        System.out.println(text);
      
        System.out.println("Logo Text: " + text);
	}
        @Test
    	public void rtyu() {
        	
       WebElement fgty =driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[3]/div[1]"));
     //  WebElement someElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[3]/div[1]"));
       String elementText = fgty.getText();
       System.out.println("Element Text: " + elementText);
    	
       
     
        }

	@AfterMethod
	public void rtui() {

		driver.quit();
	
	}
}