package ssfc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ssfc_api_check {
	@Test
    public void callApiAndCheckResponse() {
     //   String j = "someValue";  // Define the variable `j` properly
 
        try {
            // Construct the URL
            URL url = new URL("http://10.0.6.65:80/cgi-bin/ApiCheck?1");
            
            // Open HTTP connection
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            http.setDoOutput(true);
 
            // Read the response
            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) http.getContent(), "utf-8"));
            StringBuilder response1 = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response1.append(responseLine.trim());
            }
 
            // Print the response
            System.out.println("\nResponse: " + response1.toString());
 
            // Close the connection
            br.close();
            http.disconnect();
 
            // Assert the expected response
            Assert.assertTrue(response1.toString().contains("Pass"), "Expected 'Pass' string not found in response");
 
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during API call: " + e.getMessage());
        }
    }
}
