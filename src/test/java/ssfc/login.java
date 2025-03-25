package ssfc;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class login {

@Test
public void logn() {
    WebDriver driver = new FirefoxDriver();
    MT_ssfc.driver = driver;

    try {
        driver.get("https://10.0.6.65:8001/ssfc/login");
        driver.manage().window().maximize();

        // Use WebDriverWait for the login page elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tsslogin-form_username"))).sendKeys("admin");
        driver.findElement(By.id("tsslogin-form_password")).sendKeys("Tayana@123");
        driver.findElement(By.xpath("//*[@id=\"tsslogin-form\"]/div[3]/div/div/div/div/button")).click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='swal2-title']")));
            System.out.println("Assertion: Server.js got killed (Error popup detected)");
            Assert.fail("Server.js is down, detected error popup.");
        } catch (TimeoutException e) {
            // No error popup found, continue with the test.
            System.out.println("Login successful, no error popup.");
        }

    } catch (WebDriverException e) {
  
            System.out.println("Assertion: Network error, server.js might be down.");
            Assert.fail("NetworkError when attempting to fetch resource. Server.js got killed.");
    
    }

}}