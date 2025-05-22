package bmp_react;



	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.time.Duration;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;

	import io.github.bonigarcia.wdm.WebDriverManager;
	public class authlogic {
		
		 public static String multi_factor_auth() {
			 String  otp = null ;
		        try {
		            @SuppressWarnings("deprecation")
		            URL url = new URL("http://10.0.6.137/cgi-bin/get_otp.cgi");

		            // Open HTTP connection
		            HttpURLConnection http = (HttpURLConnection) url.openConnection();
		            http.setRequestMethod("GET");
		            http.setRequestProperty("Content-Type", "text/html");
		            http.setRequestProperty("Accept", "text/html");
		            http.setDoOutput(true);

		            // Read the response
		            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
		            StringBuilder response1 = new StringBuilder();
		            String responseLine;
		            while ((responseLine = br.readLine()) != null) {
		                response1.append(responseLine.trim());
		            }

		            br.close();
		            http.disconnect();
		           
		            Pattern pattern = Pattern.compile("\\b\\d{6}\\b"); // Matches a 6-digit OTP
		            Matcher matcher = pattern.matcher(response1.toString());
		              otp = matcher.find() ? matcher.group() : "Not Found";

		            // Print extracted OTP
		            System.out.println("\nExtracted OTP: " + otp);
		            return otp;

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				return otp;
				
		    }
		 
		

	    public static void main(String[] args) {
	    	
	    	 String otp = authlogic.multi_factor_auth();   
	         System.out.println("Processing OTP in another class: " + otp);
	       
	     
	    }

	   
	}


