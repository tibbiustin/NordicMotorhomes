<%@include file="templates/header.jsp" %>
    <%
        if(session.getAttribute("username") != null){
            response.sendRedirect("index.jsp");
        }
    %>
    <div id="wrapper">
        <div id="content">
            <h1>Register</h1>
            <div style="color:#575b5e;margin-bottom:20px;">
                <form action="register" method="post">
                    Name: <input type="text" name="customer_name" / required></br><br>
                    CPR: <input name="customer_cpr"  required/><br><br>
                    E-mail: <input type="text" name="customer_email" required/></br></br>
                    Password: <input type="password" name="customer_password" required/></br></br>
                    <input type="submit" value="Register">
                    <%
                        if(request.getSession().getAttribute("Error") != null){
                    %>
                        ${Error}
                    <%
                        request.getSession().setAttribute("Error", null);
                        }
                    %>

                </form>
            </div>
        </div>
<%@include file="templates/footer.html" %>