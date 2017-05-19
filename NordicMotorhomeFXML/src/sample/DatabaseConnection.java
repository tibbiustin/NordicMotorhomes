package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by George Stratulat on 17/05/2017.
 */
public class DatabaseConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/";
    private final static String DB_NAME = "motorhome";
    private final static String USER = "root";
    private final static String PASS = "1234";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    URL + DB_NAME,
                    USER,
                    PASS);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
