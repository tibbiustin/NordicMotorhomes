<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.xml.transform.Result" %>
<%@ page import="java.sql.ResultSet" %>
<%@include file="templates/header.jsp" %>
<div id="wrapper">
    <div id="content" style="margin-bottom: 75px">
        <h1>Our Vehicles</h1>
        <%
            // Set up the connection to the database using the class MYSQL.
            Connection connHandle = MYSQL.getConnection();
            try {
                // Select all vehicle types from the table 'typeofvehicle'.

                ResultSet rs = connHandle.prepareStatement("SELECT * FROM typeofvehicle").executeQuery();
                //Output all the vehicles available in the database.
                while(rs.next()){
        %>
                <div class="vehicle">
                    <div class="block">
                        <div class="left"><img src="images/vehicle1.JPG"></div>
                        <form action="checkbooking">
                        <div class="middle">
                            <%--Output the vehicle category name--%>
                            <strong><%=rs.getString(3)%></strong><br>

                                Departure:<br>
                                <input type="date" name="departure" min="2017-06-08" required>
                                <br>
                                Return:<br>
                                <input type="date" name="return" min="2017-06-08" required>
                                <input type="hidden" name="id" value="<%=rs.getInt(1)%>">
                        </div>
                        <div class="right">
                                <input type="submit" value="Book Now" class="w3-button w3-light-grey w3-round-xlarge">

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


<%
    if(request.getSession().getAttribute("MessageIndex") != null){

%>

<script>alert('<%=request.getSession().getAttribute("MessageIndex")%>')</script>
<%
        request.getSession().setAttribute("MessageIndex", null);
    }
%>
<%@include file="templates/footer.html" %>