package snd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kioskete {

	public static String whtsotp() {
	HttpURLConnection http = null;
	BufferedReader br = null;

try {       URL url = new URL("http://10.0.6.132:8000/cgi-bin/kiosk1.cgi");

 http = (HttpURLConnection) url.openConnection();
http.setRequestMethod("GET");

       int status = http.getResponseCode();
	// System.out.println("HTTP STATUS: " + status);

        br = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
	       StringBuilder response = new StringBuilder();       
	       String line;
	  

	        while ((line = br.readLine()) != null) {
           response.append(line);
	}

     String responseStr = response.toString();
//	          System.out.println("----- RAW RESPONSE START -----");
//	          System.out.println(responseStr);
//	          System.out.println("----- RAW RESPONSE END -----");

	// 🔥 EXTRACT OTP (17–25 digit number)
        Pattern pattern = Pattern.compile("\\d{15,25}");
	        Matcher matcher = pattern.matcher(responseStr);

	       if (matcher.find()) {
            return matcher.group();   // ✅ RETURN OTP ONLY
	       } else {
            return "OTP_NOT_FOUND";
            }

	 } catch (Exception e) {
	       e.printStackTrace();
	        return "ERROR";
    } finally {
	        try { if (br != null) br.close(); } catch (Exception ignored) {}
	        if (http != null) http.disconnect();
	        }
	}

	public static void main(String[] args) {
		System.out.println("Hello world");

		// Call the OTP fetcher
		String otp = kioskete.whtsotp();
		System.out.println("Processing OTP in another class: " + otp);
	}
}
