package snd_cbx;

public class CredxTestData {


		
		 // ================= UI =================
	    public static final String BASE_URL =
	            "https://10.0.5.49:8443/snd/login";

	    // ================= AUTH =================
	    public static final String USERNAME = "admin";
	    public static final String PASSWORD = "Admin@123";

	    // ================= TRANSACTION =================
	    public static final String TRANSACTION_ID =
	            "14532591016197024733147";

	    // ================= SSH =================
	    public static final String SSH_HOST = "10.0.5.49";
	    public static final int SSH_PORT = 22;
	    public static final String SSH_USER = "cbx";
	    public static final String SSH_PASSWORD = "cbx";

	    public static final String EXPECTED_SCRIPT =
	            "python3 /home/cbx/credxtemplate.py ";

	    // ================= TIMEOUTS =================
	    public static final int UI_WAIT_SECONDS = 180;
	    public static final int SSH_TIMEOUT_MS = 10000;

	}

