<%@include file="templates/header.jsp" %>
    <%
        if(session.getAttribute("username") != null){
            response.sendRedirect("index.jsp");
        }
    %>
    <div id="wrapper">



        <div id="content" style="text-align: center; margin-top: 110px;">
            <div class="w3-container defaultcolor">
                <h2 style="color: white;">Login</h2>
            </div>
            <form action="login" method="post" class="w3-container">
                <label>Email</label>
                <input type="text" name="username" class="w3-input" required/></br><br>
                <label>Password</label>
                <input type="password" name="password" class="w3-input" required/></br><br>
                <input type="submit" value="Login" class="w3-button w3-light-grey w3-round-xlarge">
            </form>
            <%
                if(request.getSession().getAttribute("LoginError") != null){

            %>
            ${LoginError}
            <%
                    request.getSession().setAttribute("LoginError", null);
                }
            %>
        </div>

    </div>
<%@include file="templates/footer.html" %>
