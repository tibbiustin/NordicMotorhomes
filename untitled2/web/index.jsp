<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.xml.transform.Result" %>
<%@ page import="java.sql.ResultSet" %>
<%@include file="templates/header.jsp" %>
<div id="wrapper">
    <div id="content">
        <h1>Our Vehicles</h1>
        <%
            // Set up the connection to the database using the class MYSQL
            Connection connHandle = MYSQL.getConnection();
            try {
                // Select all vehicle types from the table 'typeofvehicle'

                ResultSet rs = connHandle.prepareStatement("SELECT * FROM typeofvehicle").executeQuery();
                //Output all the vehicles
                while(rs.next()){
        %>
                <div class="vehicle">
                    <div class="block">
                        <div class="left"><img src="images/vehicle1.JPG"></div>
                        <form action="checkbooking">
                        <div class="middle">
                            <h3><%=rs.getString(3)%></h3>

                                Departure:<br>
                                <input type="date" name="departure" min="2017-01-01" required>
                                <br>
                                Return:<br>
                                <input type="date" name="return" min="2017-01-01" required>
                                <input type="hidden" name="id" value="<%=rs.getInt(1)%>">
                        </div>
                        <div class="right">
                                <input type="submit" value="Book Now">

                        </div>
                        </form>
                    </div>
                </div>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connHandle.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </div>

</div>

<%--Check if the user has been redirecte from the booking system--%>
<%
    if(request.getSession().getAttribute("errorReservation") != null){
        request.getSession().setAttribute("errorReservation", null);
%>
<%--If the user is redirected from the booking system this message will pop up--%>
<script>alert('This period is already booked.')</script>
<%
    }
%>
<%@include file="templates/footer.html" %>