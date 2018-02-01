package car_dealership;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CreateDBConnection {

	private static CreateDBConnection instance = null;
	private static Properties props;

	// CREATING A PRIVATE CONSTRUCTOR
	private CreateDBConnection() {
		// PROPERTIES CLASS IS USED TO GET THE PROPERTY VALUE
		// BASED ON PROPERTY KEY
		props = new Properties();
		try {
			props.load(CreateDBConnection.class.getClassLoader().getResourceAsStream("/META-INF/db.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static CreateDBConnection getInstance() {
		if (instance == null) {
			instance = new CreateDBConnection();
		}
		return instance;
	}

	public String getValue(String key) {
		return props.getProperty(key);
	}

	public static void main(String[] args) {

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
