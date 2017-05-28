<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
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
    <div id="content" style="text-align: center; margin-bottom: 527px;">
        <%--Output the name of the vehicle type--%>
        <h1><%=rs.getString(3)%></h1>
            <form action="receipt.jsp" method="post">

                <strong>Price</strong>:<%
                    PreparedStatement queryVehicle = null;
                    int vehicleID = 0, price = 0;
                    //Fetch of the sent parameters using the method 'GET'
                    String departureDateGET = request.getParameter("departure");
                    String returnDateGET = request.getParameter("return");
                    String id = request.getParameter("id");

                    try {
                        queryVehicle = connHandle.prepareStatement("SELECT id_vehicle, price_vehicle FROM vehicle" +
                                " WHERE id_vehicle NOT IN (SELECT vehicle_reservation FROM reservation WHERE (finish_reservation >= ? AND ? >= start_reservation ) OR (finish_reservation >= ? AND ? >= start_reservation) )" +
                                " AND vehicle.type_vehicle = ? LIMIT 1");
                        queryVehicle.setString(1, departureDateGET);
                        queryVehicle.setString(2, departureDateGET);
                        queryVehicle.setString(3, returnDateGET);
                        queryVehicle.setString(4, returnDateGET);
                        queryVehicle.setString(5, id);
                        System.out.println(queryVehicle);
                        rs = queryVehicle.executeQuery();
                        if(rs.next()){
                            price = rs.getInt(2);
                            vehicleID = rs.getInt(1);
                            %>
                            <%--Print out the price of the vehicle--%>
                <%=price%><br> <strong>Vehicle ID:</strong> <%=vehicleID%>
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
                Distance from our pickup location: <input type="text" name="pickup" required> <br><br>
                Distance from our drop off location: <input type="text" name="dropoff" required> <br>
                Bike: <input type="checkbox" name="bike" value="1"> <br>
                Child: <input type="checkbox" name="child" value="1"> <br>
                Picnic: <input type="checkbox" name="picnic" value="1"> <br>
                Payment Type: <input type="checkbox" name="payment" value="Card">Card <br>

                <input type="hidden" name="vehicleID" value="<%=vehicleID%>">
                <input type="hidden" name="return" value="<%=request.getParameter("return")%>">
                <input type="hidden" name="departure" value="<%=request.getParameter("departure")%>">
                <input type="hidden" name="price" value="<%=price%>">
                <input type="submit" value="Book" class="w3-button w3-light-grey w3-round-xlarge">
            </form>
<%
    }
%>
    </div>
</div>
<%@include file="templates/footer.html" %>