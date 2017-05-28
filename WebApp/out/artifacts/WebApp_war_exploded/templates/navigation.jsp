<div id="navigation">
    <a href="index.jsp"><img class="left" src="images/logo.png"></a>

    <div id="right">
        <%
            if(session.getAttribute("username") == null){
        %>
                <a href="login.jsp"><button class="w3-button w3-white w3-round-xlarge">Login</button></a> <a href="register.jsp"><button class="w3-button w3-white w3-round-xlarge">Sign Up</button></a>
        <%
            }
            else{
        %>
                <a href="reservations.jsp"><button class="w3-button w3-white w3-round-xlarge">My reservations</button></a>
                <a href="logout.jsp"><button class="w3-button w3-white w3-round-xlarge">Logout</button></a>
        <%
            }
        %>
    </div>
</div>