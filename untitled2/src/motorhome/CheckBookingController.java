package motorhome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Constantine on 5/26/2017.
 */
@WebServlet(name="motorhome.CheckBookingController", urlPatterns={"/checkbooking"})
public class CheckBookingController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if the session attribute 'username' has been set up. If it isn't the user will be redirected to the login page.
        if(request.getSession().getAttribute("username") == null){
            response.sendRedirect("login.jsp");
        }
        else {
            // Fetch the parameters sent using the 'GET' method.
            String id = request.getParameter("id");
            String departureDateGET = request.getParameter("departure");
            String returnDateGET = request.getParameter("return");

            try {
                Connection connHandle = MYSQL.getConnection();

                // This query counts the number of the vehicles that are registered for this category.
                PreparedStatement queryVehicles = connHandle.prepareStatement("SELECT COUNT(`id_vehicle`) FROM `vehicle` WHERE type_vehicle = ?");
                queryVehicles.setString(1, id);
                ResultSet rsVehicle = queryVehicles.executeQuery();
                rsVehicle.next();


                System.out.println(queryVehicles);


                // This query counts the number of the reservations made between the inserted departure and return date.
                PreparedStatement queryVehicle = connHandle.prepareStatement("SELECT COUNT(id_vehicle) FROM vehicle" +
                        " WHERE id_vehicle NOT IN (SELECT vehicle_reservation FROM reservation WHERE (finish_reservation >= ? AND ? >= start_reservation ) OR (finish_reservation >= ? AND ? >= start_reservation) )" +
                        " AND vehicle.type_vehicle = ?");
                queryVehicle.setString(1, departureDateGET);
                queryVehicle.setString(2, departureDateGET);
                queryVehicle.setString(3, returnDateGET);
                queryVehicle.setString(4, returnDateGET);
                queryVehicle.setString(5, id);
                System.out.println(queryVehicle);
                ResultSet rs = queryVehicle.executeQuery();
                rs.next();
                int vehicleStock = 0;
                vehicleStock = rs.getInt(1);

                // Invalid departure and return date
                if(returnDateGET.compareTo(departureDateGET) <= 0) {
                    request.getSession().setAttribute("MessageIndex", "You inserted invalid dates.");
                    response.sendRedirect("index.jsp");
                }
                // If the number of reservations is equal to the number of the vehicles, the user can't reserve a car for this period because there isn't any vehicle available.
                else if (vehicleStock == 0) {
                    request.getSession().setAttribute("MessageIndex", "This period is already booked.");
                    response.sendRedirect("index.jsp");
                } else {
                    //We set an attribute session called 'validBooking' to avoid malicious attempts of testing our system for security vulnerabilities.
                    //If the user doesn't have this attribute available when he is trying to make a booking, it will be redirected to the homepage.
                    request.getSession().setAttribute("validBooking", "valid");
                    String redirectURL = "book.jsp?departure=" + departureDateGET + "&return=" + returnDateGET + "&id=" + id;
                    response.sendRedirect(redirectURL);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
