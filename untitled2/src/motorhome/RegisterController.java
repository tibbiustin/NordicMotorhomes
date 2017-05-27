package motorhome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Constantine on 5/25/2017.
 */
@WebServlet(name="motorhome.RegisterController", urlPatterns={"/register"})
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Fetch all the variables which were sent using the post method
        String customer_name =  request.getParameter("customer_name");
        String customer_cpr =  request.getParameter("customer_cpr");
        String customer_email =  request.getParameter("customer_email");
        String customer_password =  request.getParameter("customer_password");

        //Verifications for invalid CPR
        if(!isInteger(customer_cpr,10) || customer_cpr.length() != 10){

            request.getSession().setAttribute("Error", "Invalid CPR.");
            response.sendRedirect("register.jsp");
        }
        //In case all the data provided by the user is right
        else{
            Connection connHandle = MYSQL.getConnection();
            try {
                // Check in the database if the email is already used
                PreparedStatement queryInsertCustomer = connHandle.prepareStatement("SELECT `email_customer` FROM `customer` WHERE `email_customer` = ?");
                queryInsertCustomer.setString(1, customer_email);
                ResultSet rs = queryInsertCustomer.executeQuery();
                // In case of email doesn't exist in our database
                if(!rs.next()) {
                    // Insert the record in our database
                    queryInsertCustomer = connHandle.prepareStatement("INSERT INTO `customer` (`name_customer`,`email_customer`, `password_customer`, `cpr_customer`) VALUES(?,?,?,?)");
                    queryInsertCustomer.setString(1, customer_name);
                    queryInsertCustomer.setString(2, customer_email);
                    queryInsertCustomer.setString(3, customer_password);
                    queryInsertCustomer.setString(4, customer_cpr);
                    System.out.println(queryInsertCustomer);

                    queryInsertCustomer.executeUpdate();
                    // Set the user session attributes
                    request.getSession().setAttribute("username", customer_email);
                    request.getSession().setAttribute("Error", null);
                    response.sendRedirect("index.jsp");
                }
                // In case of email exists in our database
                else{
                    // Set the a session attribute called Error which, in case of is not null, will be displayed on the page 'register.jsp'
                    request.getSession().setAttribute("Error", "This email is already used.");
                    response.sendRedirect("register.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connHandle.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Method used to validate the CPR
    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
