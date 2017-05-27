<%
    request.getSession().setAttribute("username", null);
    response.sendRedirect("index.jsp");
%>