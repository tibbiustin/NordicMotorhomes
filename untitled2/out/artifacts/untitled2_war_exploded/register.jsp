<%@include file="templates/header.jsp" %>
    <%
        if(session.getAttribute("username") != null){
            response.sendRedirect("index.jsp");
        }
    %>
    <div id="wrapper">
        <div id="content" style="margin-bottom: 220px; text-align: center; margin-top: 100px;">
            <div class="w3-container defaultcolor">
                <h2 style="color: white;">Register</h2>
            </div>
            <form action="register" method="post" class="w3-container">
                <label>Name</label>
                <input type="text" name="customer_name" class="w3-input" required/><br><br>
                <label>CPR</label>
                <input name="customer_cpr" class="w3-input"  required/><br><br>
                <label>Email</label>
                <input type="email" name="customer_email" class="w3-input" required/><br><br>
                <label>Password</label>
                <input type="password" name="customer_password" class="w3-input" required/><br><br>
                <input type="submit" value="Register" class="w3-button w3-light-grey w3-round-xlarge">
            </form>
            <%
                //If the attribute of the session "Error" has been initialized an informative message will be shown to the user.
                if(request.getSession().getAttribute("Error") != null){
            %>
            ${Error}
            <%
                    request.getSession().setAttribute("Error", null);
                }
            %>
        </div>
    </div>
<%@include file="templates/footer.html" %>