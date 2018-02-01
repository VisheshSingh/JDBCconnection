package car_dealership;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CreateDBConnection {

	public static void main(String[] args) {
		Properties props = new Properties();

		try {
			props.load(CreateDBConnection.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// String driver = props.getProperty("jdbc.driver");
		// if (driver != null) {
		// try {
		// Class.forName(driver);
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		try {

			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(url, username, password);

			// 2. Create a statement
			Statement myStmt = myConn.createStatement();

			// 3. Execute SQL query
			ResultSet myRS = myStmt.executeQuery("Select * from customer");

			// 4. Process the result set
			while (myRS.next()) {
				System.out.println(myRS.getString("C_FirstName") + ", " + myRS.getString("C_LastName"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}