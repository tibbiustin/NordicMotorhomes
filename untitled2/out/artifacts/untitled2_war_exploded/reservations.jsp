<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@include file="templates/header.jsp" %>
<div id="wrapper">
    <div id="content" style="text-align: center; margin-top: 110px;">
        <%
            try {
                Connection connHandle = MYSQL.getConnection();
                PreparedStatement query = connHandle.prepareStatement("SELECT id_customer FROM customer WHERE email_customer = ?");
                query.setString(1, request.getSession().getAttribute("username").toString());
                ResultSet rs = query.executeQuery();

                System.out.println(query);
                if(rs.next()){
                    query = connHandle.prepareStatement("SELECT * FROM reservation WHERE reservation_customer_id = ? ORDER BY finish_reservation");
                    int customer = rs.getInt(1);
                    query.setInt(1, customer );
                    rs = query.executeQuery();
                    System.out.println(query);
                    if(rs.next()){
        %>
                    <table class="w3-table w3-bordered">
                        <tr>
                            <th>Reservation ID</th>
                            <th>Vehicle ID</th>
                            <th>Departure</th>
                            <th>Return</th>
                            <th>Cancellation</th>
                        </tr>
                        <tr>
                            <td><%=rs.getInt(1)%></td>
                            <td><%=rs.getInt(5)%></td>
                            <td><%=rs.getString(2)%></td>
                            <td><%=rs.getString(3)%></td>
                            <td><a href="cancel.jsp?id=<%=rs.getInt(1)%>&customer=<%=customer%>">Cancel</a></td>
                        </tr>
            <%
                        while (rs.next()){
            %>
                        <tr>
                            <td><%=rs.getInt(1)%></td>
                            <td><%=rs.getInt(5)%></td>
                            <td><%=rs.getString(2)%></td>
                            <td><%=rs.getString(3)%></td>
                            <td><a href="cancel.jsp?id=<%=rs.getInt(1)%>&customer=<%=customer%>">Cancel</a></td>
                        </tr>
            <%
                        }
                    }
                        else{
                    %>
                    <h2 style="text-align: center;"> You didn't make any reservation until now.</h2>
                    <%
                    }
                    %>
                    </table>
            <%
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        %>
    </div>
</div>
<%@include file="templates/footer.html" %>