<%@include file="templates/header.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="motorhome.MYSQL" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.time.temporal.ChronoUnit" %>

<%
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

    if(child == null)
        child = "0";
    if(bike == null)
        bike = "0";
    if(picnic == null)
        picnic = "0";
    if(dropoff == null)
        dropoff = "0";
    if(pickup == null)
        pickup = "0";
    if(payment == null)
        payment = "cash";

    double finalPrice = Integer.parseInt(price);
    String[] departureD = departure.split("-");
    String[] returnD = returnDate.split("-");

    // Calculate the price after the month of the departure
    int month = Integer.parseInt(departureD[1]);
    if(month > 2 && month < 5 || month > 7 && month < 12){
        finalPrice = finalPrice * 130 / 100;
    }
    else if(month > 4 && month < 8){
        finalPrice = finalPrice * 130 / 100 * 160 / 100;
    }
    /*
    -------------------------------------------------------------------------------------------------------------------
    ==================[Calculate the number of the days between the departure and return date]=========================
    -------------------------------------------------------------------------------------------------------------------
    */
    finalPrice = finalPrice * JulianDay.diff(Integer.parseInt(returnD[0]), Integer.parseInt(returnD[1]), Integer.parseInt(returnD[2]), Integer.parseInt(departureD[0]), Integer.parseInt(departureD[1]), Integer.parseInt(departureD[2]));




%>
<%!
    public String printOption(String x) {
        if(x == null)
            return "No";
        else
            return "Yes";
    }
%>
<style>
    table {
        border-collapse: collapse;
    }

    table, td, th {
        border: 1px solid black;
        padding: 10px;
    }
</style>

<div id="wrapper">
    <div id="content" style="text-align: center; margin-top: 100px; color:black;">

        <h1>Receipt</h1>
        <table style="width:70%" align="center">
            <tr>
                <td>Departure Date</td>
                <td><%=departure%></td>
            </tr>
            <tr>
                <td>Return Date</td>
                <td><%=returnDate%></td>
            </tr>
            <tr>
                <td>Price for drop off</td>
                <td><%=(double) Integer.parseInt(dropoff)*0.7%> &euro;</td>
            </tr>
            <tr>
                <td>Price for pickup</td>
                <td><%=(double) Integer.parseInt(pickup)*0.7%> &euro;</td>

            </tr>
            <tr>
                <td>Bike</td>
                <td><%=printOption(bike)%></td>
            </tr>
            <tr>
                <td>Child Seat</td>
                <td><%=printOption(child)%></td>
            </tr>
            <tr>
                <td>Picnic table</td>
                <td><%=printOption(picnic)%></td>
            </tr>
            <tr>
                <td>Total Price</td>
                <td><%=finalPrice+= (double) Integer.parseInt(dropoff) * 0.7 + (double) Integer.parseInt(pickup) * 0.7%> &euro;</td>
            </tr>
        </table>
        <%
            if(payment == "Card"){
        %>
        <h2>Credit Card Information:</h2>
        <form action="payment.jsp" class="post">
            <input type="text" placeholder="Card Number" size="14" required><br><br>
            <input type="text" placeholder="MM" size="2" required>
            <input type="text" placeholder="DD" size="2" required><br><br>
            <input type="text" placeholder="VCC" size="3"><br><br>
            <input type="submit" value="Pay">
        </form>
        <%
            }
        %>
    </div>
</div>



<%
    Connection connHandle = MYSQL.getConnection();
    try {
        int customerID = 0;
        PreparedStatement queryGetCusomterID = connHandle.prepareStatement("SELECT id_customer FROM customer WHERE email_customer = ?");
        String username = request.getSession().getAttribute("username").toString();

        queryGetCusomterID.setString(1, username);
        ResultSet rs = queryGetCusomterID.executeQuery();
        System.out.println(queryGetCusomterID);
        if(rs.next()){
            customerID = rs.getInt(1);
            PreparedStatement queryInsertReservation = connHandle.prepareStatement("INSERT INTO reservation (start_reservation, finish_reservation, pickup_reservation, dropoff_reservation, price_reservation, bike_reservation, "+
                    " child_reservation, picnic_reservation, payment_type_reservation, vehicle_reservation, reservation_customer_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            queryInsertReservation.setString(1, departure);
            queryInsertReservation.setString(2, returnDate);
            queryInsertReservation.setString(3, pickup);
            queryInsertReservation.setString(4, dropoff);
            queryInsertReservation.setDouble(5, finalPrice);
            queryInsertReservation.setString(6, bike);
            queryInsertReservation.setString(7, child);
            queryInsertReservation.setString(8, picnic);
            queryInsertReservation.setString(9, payment);
            queryInsertReservation.setString(10, vehicleID);
            queryInsertReservation.setInt(11, customerID);
            queryInsertReservation.executeUpdate();

            System.out.println(queryInsertReservation);

        }
        else{
            System.out.println("[ERROR receipt.js] -> Username not found in the database.");
            response.sendRedirect("index.jsp");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<%@include file="templates/footer.html" %>