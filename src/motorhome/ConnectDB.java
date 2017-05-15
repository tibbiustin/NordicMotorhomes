package motorhome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection getConnection(){
		String connectionUrl = "jdbc:mysql://localhost:3306/motorhome";
		Connection connection = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl, "root", "1234");
			}catch(Exception e) {
				e.printStackTrace();
			}
		return connection;
		}
		
		public void closeConnection(Connection connection){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
