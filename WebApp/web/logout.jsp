<%
    // Terminate the session by removing the attribute 'username'.
    request.getSession().setAttribute("username", null);
    response.sendRedirect("index.jsp");
%>