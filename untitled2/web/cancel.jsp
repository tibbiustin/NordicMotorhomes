<%@ page import="java.sql.Connection" %>
<%@ page import="motorhome.MYSQL" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="motorhome.JulianDay" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%
    // If the user isn't connected it will redirected to the login page.
    if(request.getSession().getAttribute("username") == null){
        response.sendRedirect("login.jsp");
    }
    else{

        Connection connHandle = MYSQL.getConnection();
        PreparedStatement query = null;
        try {
            query = connHandle.prepareStatement("SELECT start_reservation,price_reservation FROM reservation WHERE reservation_customer_id = ? AND id_reservation = ?");
            query.setString(1, request.getParameter("customer"));
            query.setString(2, request.getParameter("id"));
            ResultSet rs = query.executeQuery();

            System.out.println(query);
            if(rs.next()){

                // Get the current date
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();

                // Split the month, the date and the year
                String[] departureD = rs.getString(1).split("-");
                String[] currentDate = dateFormat.format(date).split("-");

                System.out.println(dateFormat.format(date));
                System.out.println(rs.getString(1));
                // Compare the dates using the Julian Calendar
                int days = JulianDay.diff(Integer.parseInt(departureD[0]), Integer.parseInt(departureD[1]), Integer.parseInt(departureD[2]), Integer.parseInt(currentDate[0]), Integer.parseInt(currentDate[1]), Integer.parseInt(currentDate[2]));
                int due = 0;
                if(days <= 1){
                    due = 95;
                }
                else if(days < 15){
                    due = 80;
                }
                else if(days > 14 && days < 50){
                    due = 50;
                }
                else {
                    due = 20;
                }
                double dueAmount = rs.getDouble(2) * due / 100;

                if(days > 49 && dueAmount < 200)
                    dueAmount = 200;

                query = connHandle.prepareStatement("DELETE FROM reservation WHERE reservation_customer_id = ? AND id_reservation = ?");
                query.setString(1, request.getParameter("customer"));
                query.setString(2, request.getParameter("id"));
                query.executeUpdate();

                System.out.println(query);
                request.getSession().setAttribute("MessageIndex", "You canceled your reservation with " + days + " days before and the due amount is " + dueAmount + " &euro;. Price of the rent was:" + rs.getDouble(2));
                response.sendRedirect("index.jsp");
            }
            else{
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
%>