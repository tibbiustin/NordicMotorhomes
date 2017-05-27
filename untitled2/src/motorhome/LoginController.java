package motorhome;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Constantine on 5/25/2017.
 */
@WebServlet(name="motorhome.LoginController", urlPatterns={"/login"})
public class LoginController extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // Fetch the given username and password.
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        try {
            Connection connHandle = MYSQL.getConnection();
            // Query: We check if the given username and password match any record from the database.
            PreparedStatement checkUserQuery = connHandle.prepareStatement("SELECT `id_customer` FROM customer WHERE `email_customer` = ? AND `password_customer` = ? LIMIT 1");

            // SQLi Protection - Here we escape the strings inputted by the user to prevent any SQLi.
            checkUserQuery.setString(1, username);
            checkUserQuery.setString(2, password);


            // Execute the query
            ResultSet rs = checkUserQuery.executeQuery();
            System.out.println(checkUserQuery);

            if(!rs.next()){
                // If the system doesn't find any record in our database that matches the given password and given username
                // it means that he typed wrong the password or he didn't sign up an account.
                response.sendRedirect("login.jsp");
            }
            else {// If we find a match for the inputted username and password we redirect him/her to the index

                //We create a session for the user.
               request.getSession().setAttribute("username", username);;

                // Redirect to the main page
                response.sendRedirect("index.jsp");
            }
            try {
                connHandle.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
