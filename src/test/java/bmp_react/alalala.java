package bmp_react;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class alalala {
	    public static void main(String[] args) {
	        String url = "jdbc:mysql://10.0.1.210:3306/BMPDB?useSSL=false";
	        String username = "bmp";
	        String password = "bmp@Tayana123";

	        try (Connection connection = DriverManager.getConnection(url, username, password)) {
	            System.out.println("Connection Successful!");
	        } catch (SQLException e) {
	            System.err.println("Connection failed!");
	            e.printStackTrace();
	        }
	    }
	}


