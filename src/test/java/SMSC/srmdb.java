package SMSC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class srmdb {
	public static void main(String[] args)
    {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://10.0.0.174:3306/SRM?autoReconnect=true&sslMode=DISABLED&allowPublicKeyRetrieval=true", "srm", "SrmT4y4n4_EKYC"))
        {
            // Creating a statement object
            Statement statement = connection.createStatement();

            // Executing the SQL query
            String sqlQuery = "SELECT MSISDN FROM SRM_SIM_INVENTORY WHERE PKG_ID=10 AND MSISDN LIKE '17%' AND SIM_STATUS=6 LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Processing the query results
            while (resultSet.next()) {
                // Retrieve data from the result set row
                int subscriberId = resultSet.getInt("MSISDN");
                // Print or process the retrieved data
                System.out.println("MSISDN " +subscriberId );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
