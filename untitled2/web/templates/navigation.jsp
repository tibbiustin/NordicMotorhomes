<div id="navigation">
    <a href="index.jsp">Home</a>
    <div id="loginregister">
        <%
            if(session.getAttribute("username") == null){
        %>
                <a href="login.jsp">Login</a> | <a href="register.jsp">Sign Up</a>
        <%
            }
            else{
        %>
                <a href="logout.jsp">Logout</a>
        <%
            }
        %>
    </div>
</div>