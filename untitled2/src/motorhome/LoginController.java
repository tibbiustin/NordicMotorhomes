package motorhome;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Created by Constantine on 5/25/2017.
 */
@WebServlet(name="motorhome.LoginController", urlPatterns={"/login"})
public class LoginController extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //================================[Fetch the given username and password]=======================================
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        try {
            Connection connHandle = MYSQL.getConnection();

            /*====================================[HASHING & SALTING]==================================*/
            /*
                  -> Used hashing algorithm: SHA-256
                  -> Salting: add the username to the end of the password
            */
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            password += username;
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            password = String.format("%064x", new java.math.BigInteger(1, digest));
            /*====================================[END HASHING & SALTING]=============================*/

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
                request.getSession().setAttribute("LoginError", "You inserted an invalid username / password.");
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
