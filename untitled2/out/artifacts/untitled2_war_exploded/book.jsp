<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@include file="templates/header.jsp" %>
<%
    // Check if the user was redirected from our system
    if(request.getSession().getAttribute("validBooking") == null){
        response.sendRedirect("index.jsp");
        System.out.println("[ERROR Book.jsp] -> The user hasn't been redirected here by the server.");
    }
    else
        request.getSession().setAttribute("validBooking" , null);

    // Setup the connection to the database
    Connection connHandle = MYSQL.getConnection();
    PreparedStatement queryTypeOfVehicle = null;
    ResultSet rs = null;
    try {
        // Select the vehicle type to display the name of the vehicle and to check if parameter 'id' is correct
        queryTypeOfVehicle = connHandle.prepareStatement("SELECT * FROM `typeofvehicle` WHERE `id_typeOfVehicle` = ?");
        queryTypeOfVehicle.setString(1, request.getParameter("id"));

        System.out.println(queryTypeOfVehicle);

        rs = queryTypeOfVehicle.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    // If the user is trying to find an exploit
    if(!rs.next()) {
        response.sendRedirect("index.jsp");
        System.out.println("[ERROR Book.jsp] -> " + request.getParameter("id") +" vehicle category doesn't exist.");
    }
    else{

%>
<div id="wrapper">
    <div id="content" style="text-align: center">
        <%--Output the name of the vehicle type--%>
        <h1><%=rs.getString(3)%></h1>
            <form action="proceedbook" method="post">
                Pickup Location: <input type="text" name="pickup" required> <br><br>
                Drop off Location: <input type="text" name="dropoff" required> <br>
                Price:<%
                    PreparedStatement queryVehicle = null;
                    int vehicleID = 0, price = 0;
                    String departureDateGET = request.getParameter("departure");
                    String returnDateGET = request.getParameter("return");

                    try {

                        PreparedStatement queryVehicles = connHandle.prepareStatement("SELECT COUNT(`id_vehicle`) FROM `vehicle` WHERE type_vehicle = ?");
                        queryVehicles.setString(1, request.getParameter("id"));
                        ResultSet rsVehicle = queryVehicles.executeQuery();
                        rsVehicle.next();

                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                        PreparedStatement queryReservations = connHandle.prepareStatement(
                            "SELECT * FROM reservation " +
                            "WHERE vehicle_reservation IN (SELECT id_vehicle FROM vehicle WHERE type_vehicle = ? )"
                        );
                        queryReservations.setString(1, request.getParameter("id"));
                        rs = queryReservations.executeQuery();
                        System.out.println(queryReservations);

                        int reservations = 0;
                        while (rs.next()) {
                            String reservationDepartureDate = rs.getString(2);
                            String reservationReturnDate = rs.getString(3);

                            if (date.parse(reservationDepartureDate).compareTo(date.parse(departureDateGET)) < 0 && date.parse(reservationReturnDate).compareTo(date.parse(returnDateGET)) < 0) {
                                vehicleID = rs.getInt(5);
                                break;
                            }
                            reservations ++;

                        }
                        if(vehicleID == 0 && reservations != rsVehicle.getInt(1)){
                            queryVehicle  = connHandle.prepareStatement("SELECT id_vehicle FROM vehicle WHERE type_vehicle = ?");
                            queryVehicle.setString(1, request.getParameter("id"));
                            rs = queryVehicle.executeQuery();
                            rs.next();
                            vehicleID = rs.getInt(1);

                        }
                        rs = connHandle.prepareStatement("SELECT price_vehicle FROM vehicle WHERE id_vehicle = " + vehicleID).executeQuery();
                        System.out.println(vehicleID);

                        if(rs.next()){
                            price = rs.getInt(1);
                            %>
                            <%--Print out the price of the vehicle--%>
                            <%=price%> - <%=vehicleID%>
                        <%
                        }
                        else{
                            response.sendRedirect("index.jsp");
                            System.out.println("[ERROR Book.jsp] -> Price can't be found.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                %>
                    <br>
                Bike: <input type="checkbox" name="bike" value="1"> <br>
                Child: <input type="checkbox" name="child" value="1"> <br>
                Picnic: <input type="checkbox" name="picnic" value="1"> <br>
                Payment Type: <input type="checkbox" name="payment" value="Card">Card <br>

                <input type="hidden" name="vehicleID" value="<%=vehicleID%>">
                <input type="hidden" name="return" value="<%=request.getParameter("return")%>">
                <input type="hidden" name="departure" value="<%=request.getParameter("departure")%>">
                <input type="hidden" name="price" value="<%=price%>">
                <input type="submit" value="Book">
            </form>
<%
    }
%>
<%@include file="templates/footer.html" %>