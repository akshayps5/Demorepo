package snd;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;

	import snd.dataprovider;

	import javax.net.ssl.*;
	import java.security.cert.X509Certificate;

	import org.apache.http.client.methods.HttpGet;
	import org.apache.http.impl.client.CloseableHttpClient;
	import org.apache.http.impl.client.HttpClients;
	import org.apache.http.conn.ssl.NoopHostnameVerifier;
	import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
	import org.apache.http.util.EntityUtils;
	import org.apache.http.client.methods.CloseableHttpResponse;

	public class ussdete {

	    // ----------------------------
	    // Build and send the correct URL
	    // ----------------------------
	    public static String sendRechargeAPI(String subscriber, String dealer, String pin, String amount) throws Exception {

	        // Trust-all SSL (equivalent to curl -k)
	        SSLContext sslContext = SSLContext.getInstance("SSL");
	        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	            public X509Certificate[] getAcceptedIssuers() { return null; }
	        }}, new java.security.SecureRandom());

	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslContext, NoopHostnameVerifier.INSTANCE);

	        CloseableHttpClient client = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();

	        // Correct URL pattern:
	        // https://10.0.6.83:8012/fcgi-bin/WMC_eTopUpRequest?snd&snd&<dealer>&<pin>&RECH%20<pin>%20<subscriber>%20<amount>
	        String url = "https://10.0.5.49:8012/fcgi-bin/WMC_eTopUpRequest?"
	                + "snd&snd&" + dealer + "&" + pin
	                + "&RECH%20" + pin + "%20" + subscriber + "%20" + amount;

	        System.out.println("Sending API: " + url);

	        HttpGet get = new HttpGet(url);
	        CloseableHttpResponse response = client.execute(get);

	        String resp = EntityUtils.toString(response.getEntity());
	        System.out.println("API Response: " + resp);

	        // Extract Transaction ID (robust-ish)
	        String txnId = null;
	        // Look for common phrases and extract trailing token(s)
	        if (resp.contains("Transaction Id is")) {
	            txnId = resp.split("Transaction Id is")[1].trim();
	        } else if (resp.contains("Transaction Id:")) {
	            txnId = resp.split("Transaction Id:")[1].trim();
	        } else {
	            // Try to find a 18-30 digit like token (fallback)
	            String[] parts = resp.split("\\s+");
	            for (String p : parts) {
	                if (p.matches("\\d{12,30}")) { // heuristics
	                    txnId = p;
	                    break;
	                }
	            }
	        }

	        if (txnId != null) {
	            // remove trailing punctuation if any
	            txnId = txnId.replaceAll("[^0-9]", "");
	            if (txnId.isEmpty()) txnId = null;
	        }

	        if (txnId != null) {
	            System.out.println("Extracted Transaction ID: " + txnId);
	        } else {
	            System.out.println("ERROR: Transaction ID not found in response.");
	        }

	        client.close();
	        return txnId;
	    }


	    // ----------------------------
	    // Selenium: login and search tracker
	    // ----------------------------
	  
	    
	    

	    // ----------------------------
	    // main flow
	    // ----------------------------
	    public static void main(String[] args) throws Exception {

	        System.out.println("=== Starting Recharge + Tracker Flow ===");

	        // correct mapping:
	        String dealer = "2482511111";
	        String pin = "4567";
	        String subscriber = "2482686370";
	        String amount = "50";

	        String txnId = sendRechargeAPI(subscriber, dealer, pin, amount);

	        if (txnId == null) {
	            System.out.println("ERROR: Transaction ID not found! Please check API response for errors (e.g. 'Failed to parse number from message').");
	            return;
	        }

	        // proceed to GUI lookup only if txnId present
	 

	        System.out.println("=== Flow Completed ===");
	    }}
	
	

