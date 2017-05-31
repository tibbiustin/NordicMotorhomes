package motorhome;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Constantine on 5/25/2017.
 */
//
public class MYSQL {
    public static Connection getConnection(){
        String connectionUrl = "jdbc:mysql://5.196.26.114:3306/motorhomes";
        Connection connection = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, "dat16j", "JPxVe0doGx0ezf5zJxGC");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

