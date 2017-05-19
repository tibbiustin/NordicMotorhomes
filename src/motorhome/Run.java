package motorhome;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;


public class Run {

	public Connection getConnection(){
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
	
	public void create(Customer customer){
		
		String sql = "insert into customer (name_customer, cpr_customer, email_customer, password_customer ) values (?,?,?,?)";
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getName());;
			statement.setLong(2, customer.getCpr());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
            statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
        try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	
	public void login(String email, String password){
		boolean login = false;
		try{
		Connection connection = getConnection();
	    Statement statement = connection.createStatement();
	    ResultSet result = statement.executeQuery("select * from customer");
	    while(result.next()){
	    	if ((result.getString(3).equals(email)) && (result.getString(4).equals(password))){
	    		login = true;
	    	}
	    	if(login == true){
	    		System.out.println("Succesfully logged in!");
	    	}		
	    }
		
		}catch(Exception e){System.out.println(e);}
	}
	

	public static void main(String[] args) {
	Run run = new Run();
	}

}

