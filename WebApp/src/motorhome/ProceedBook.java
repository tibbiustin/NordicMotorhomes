package motorhome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Constantine on 5/27/2017.
 */
@WebServlet(name="motorhome.ProceedBook", urlPatterns={"/proceedbook"})
public class ProceedBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pickup = request.getParameter("pickup");
        String dropoff = request.getParameter("dropoff");
        String bike = request.getParameter("bike");
        String child = request.getParameter("child");
        String picnic = request.getParameter("picnic");
        String payment = request.getParameter("payment");
        String vehicleID = request.getParameter("vehicleID");
        String departure = request.getParameter("departure");
        String returnDate = request.getParameter("return");
        String price = request.getParameter("price");



        Connection connHandle = MYSQL.getConnection();
        try {
            int customerID = 0;
            PreparedStatement queryGetCusomterID = connHandle.prepareStatement("SELECT id_customer FROM customer WHERE email_customer = ?");
            String customerName = request.getSession().getAttribute("username").toString();

            queryGetCusomterID.setString(1, customerName);
            ResultSet rs = queryGetCusomterID.executeQuery();
            if(rs.next()){
                customerID = rs.getInt(1);
                PreparedStatement queryInsertReservation = connHandle.prepareStatement("INSERT INTO reservation (start_reservation, finish_reservation, pickup_reservation, dropoff_reservation, price_reservation, bike_reservation, "+
                        " child_reservation, picnic_reservation, payment_type_reservation, vehicle_reservation, reservation_customer_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                queryInsertReservation.setString(1, departure);
                queryInsertReservation.setString(2, returnDate);
                queryInsertReservation.setString(3, pickup);
                queryInsertReservation.setString(4, dropoff);
                queryInsertReservation.setString(5, price);
                queryInsertReservation.setString(6, bike);
                queryInsertReservation.setString(7, child);
                queryInsertReservation.setString(8, picnic);
                queryInsertReservation.setString(9, payment);
                queryInsertReservation.setString(10, vehicleID);
                queryInsertReservation.setInt(11, customerID);
                queryInsertReservation.executeUpdate();

                System.out.println(queryInsertReservation);
                response.sendRedirect("index.jsp");
            }
            else{
                response.sendRedirect("index.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
