<%@ page import="java.sql.Connection" %>
<%@ page import="motorhome.MYSQL" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %><%
    Connection connHandle = MYSQL.getConnection();

    try {
        PreparedStatement query = connHandle.prepareStatement("SELECT id_customer FROM customer WHERE email_customer = ?");
        query.setString(1, request.getSession().getAttribute("username").toString());
        ResultSet rs = query.executeQuery();

        System.out.println(query);
        if(rs.next()){
            query = connHandle.prepareStatement("UPDATE reservation SET paid_reservation = 1 WHERE reservation_customer_id = ? ORDER BY id_reservation DESC LIMIT 1");
            query.setInt(1, rs.getInt(1) );
            query.executeUpdate();
        }
        else{
            response.sendRedirect("index.jsp");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>