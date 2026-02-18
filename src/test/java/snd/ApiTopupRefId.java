package snd;



	import org.json.JSONObject;
	import org.testng.annotations.Test;

	import java.io.*;
	import java.net.HttpURLConnection;
	import java.net.URL;

	public class ApiTopupRefId {

	    public static String FILE_PATH = "C:\\Users\\akshay.ps\\eclipse-workspace\\SMSC\\src\\test\\java\\snd\\ref.txt";

	    @Test
	    public void generateRefId() throws Exception {

	        // Disable SSL
	        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
	        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
	                new javax.net.ssl.X509TrustManager() {
	                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
	                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
	                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
	                }
	        };
	        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	        // Generate random refId
	        String randomRefId = String.valueOf((long)(Math.random() * 100000000000L));

	        String urlString = "https://10.0.5.49:7066/etopup/v2/topup?refId=" + randomRefId;
	        URL url = new URL(urlString);

	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("userName", "snd");
	        conn.setRequestProperty("password", "snd");
	        conn.setDoOutput(true);

	        String jsonBody = "{\n" +
	                " \"serviceId\": 1000,\n" +
	                " \"serviceNo\": \"2482579354\",\n" +
	                " \"buyerNo\": \"2509997\",\n" +
	                " \"price\": 400,\n" +
	                " \"channel\": 16,\n" +
	                " \"paymentMode\": 1,\n" +
	                " \"pin\": 1234,\n" +
	                " \"extension\": { \"saleDateTime\": \"2025-10-31 11:45:00\", \"quantity\": 1 }\n" +
	                "}";

	        try (OutputStream os = conn.getOutputStream()) {
	            os.write(jsonBody.getBytes());
	        }

	        StringBuilder response = new StringBuilder();
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                response.append(line.trim());
	            }
	        }

	        JSONObject jsonObj = new JSONObject(response.toString());
	        String finalRefId = jsonObj.getJSONObject("response").getString("refId");

	        System.out.println("API Returned RefId = " + finalRefId);

	        // SAVE TO FILE
	        try (FileWriter writer = new FileWriter(FILE_PATH)) {
	            writer.write(finalRefId);
	        }

	        System.out.println("RefId saved to file: " + FILE_PATH);
	    }
	}


