package motorhome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Constantine on 5/26/2017.
 */
@WebServlet(name="motorhome.CheckBookingController", urlPatterns={"/checkbooking"})
public class CheckBookingController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Redirect the user to login page if isn't logged in
        if(request.getSession().getAttribute("username") == null){
            response.sendRedirect("login.jsp");
        }
        else {
            // Fetch the parameters sent using the GET request
            String id = request.getParameter("id");
            String departureDateGET = request.getParameter("departure");
            String returnDateGET = request.getParameter("return");

            //Convert the string to a date
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Connection connHandle = MYSQL.getConnection();

                // Get the total number of vehicles available for this category
                PreparedStatement queryVehicles = connHandle.prepareStatement("SELECT COUNT(`id_vehicle`) FROM `vehicle` WHERE type_vehicle = ?");
                queryVehicles.setString(1, id);
                ResultSet rsVehicle = queryVehicles.executeQuery();

                System.out.println(queryVehicles);

                // Get the reservations for this category
                PreparedStatement queryReservations = connHandle.prepareStatement(
                        "SELECT * FROM reservation " +
                                "WHERE vehicle_reservation IN (SELECT id_vehicle FROM vehicle WHERE type_vehicle = ? )");
                queryReservations.setString(1, id);
                ResultSet rs = queryReservations.executeQuery();


                int reservations = 0;
                while (rs.next()) {
                    String reservationDepartureDate = rs.getString(2);
                    String reservationReturnDate = rs.getString(3);

                    if (date.parse(reservationDepartureDate).compareTo(date.parse(departureDateGET)) >= 0) {
                        reservations++;
                    } else if (date.parse(reservationReturnDate).compareTo(date.parse(returnDateGET)) >= 0) {
                        reservations++;
                    }

                }
                rsVehicle.next();
                // If the number of reservations is equal with the number of the vehicles the user can't reserve a vehicle for this period
                if (reservations == rsVehicle.getInt(1) && reservations != 0) {
                    request.getSession().setAttribute("errorReservation", "This period is already booked.");
                    response.sendRedirect("index.jsp");
                } else {
                    request.getSession().setAttribute("validBooking", "valid");
                    String redirectURL = "book.jsp?departure=" + departureDateGET + "&return=" + returnDateGET + "&id=" + id;
                    response.sendRedirect(redirectURL);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

}
