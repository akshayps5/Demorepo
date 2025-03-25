package SMSC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnect {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://10.0.5.248:3306/tqa?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", "tqa", "Tqa@12345")) {
            // Creating a statement object
            Statement statement = connection.createStatement();

            // Executing the SQL query
            String sqlQuery = "SELECT SERVICE_VALUE FROM wdbs_subscriber_services where SUBSCRIBER_ID =222";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Processing the query results
            while (resultSet.next()) {
                // Retrieve data from the result set row
                int subscriberId = resultSet.getInt("SERVICE_VALUE");
              
                // Print or process the retrieved data
                System.out.println("SERVICE_VALUE " + subscriberId );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
