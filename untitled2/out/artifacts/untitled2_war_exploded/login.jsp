<%@include file="templates/header.jsp" %>
    <%
        if(session.getAttribute("username") != null){
            response.sendRedirect("index.jsp");
        }
    %>
    <div id="wrapper">



        <div id="content" style="text-align: center">
            <h1>Login</h1>
            <form action="login" method="post">
                <input type="text" name="username" placeholder="Username"/></br><br>
                <input type="password" name="password" placeholer="Password"/></br><br>
                <input type="submit" value="Login">
            </form>
        </div>

    </div>
<%@include file="templates/footer.html" %>
