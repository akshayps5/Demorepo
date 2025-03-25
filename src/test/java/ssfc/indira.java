package ssfc;



	import java.lang.reflect.Method;

	import org.testng.annotations.DataProvider;

	public class indira {

		@DataProvider
		public static Object[] [] getDetails(Method deatils){
		switch (deatils.getName()) {
		case "loginDetails":
			return new Object[][] {
				{"T392", "Rockyindhu@123" , "Hi INDIRA"}
			};
			
		case "checkAttendance":
			return new Object[][] {
				{ 
	                "smtp.office365.com", // SMTP Host
	                "587",                // SMTP Port
	                "indira.bs@tayana.in", // Email Username
	                "$IBStamotec38$",     // Email Password
	                "indira.bs@tayana.in", // Recipient Email
	                "Daily Reminder",     // Email Subject
	                "Hello Indira, You logged in today at " // Email Message
	            }
			};
			
			
		}	
			return null;
		}
		
	}



