<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="motorhome.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Nordic Motorhomes Rental</title>
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>

<%
Run run = new Run();
String name = request.getParameter("name");
String id = request.getParameter("id");
String email = request.getParameter("email");
String cpr = request.getParameter("cpr");
String password = request.getParameter("password");
String action = request.getParameter("action");
if("register".equals(action)){
	long cprA = Long.parseLong(cpr);
	Customer customer = new Customer(name, cprA, email, password);
	run.create(customer);
}

%>

    <div id="wrapper">
        <div id="header">
            <div class="left">
                <img src="images/logo.png" width="320px">
            </div>
            <div class="right">
                call us now on<br>
                <span style="font-size: 32px;color: #ed1c24;">+45 50 12 34 56</span><br><br>
                <img src="images/facebook.jpg" style="float: right;">
            </div>
        </div>
        <div id="navigation">
            <p>home | login | register</p>
        </div>
        <div id="content">
            <h1>Register</h1>
            <div style="color:#575b5e;margin-bottom:20px;">
                <form action="Register.jsp">
                          <input type="hidden" name="id"/>
                    Name: <input type="text" name="name" /></br><br>
                    CPR: <input name="cpr"  /><br></br>
                    E-mail: <input type="text" name="email" /></br></br>
                    Password: <input type="password" name="password" /></br></br>
                    <button name="action" value="register"> Register </button>
                </form>
            </div>
        </div>
        <div id="footer">
            <div class="block">
                <div class="left">
                    <b>Company & Site Information</b>
                    <ul><li>home</li><li>login</li><li>register</li></ul>
                    <hr />
                    <span>
                        &copy;2017 Nordic Motorhomes ApS. All Rights Reserved.</br>
                        CVR-nr. 31 65 62 06. Lygten 37, 2400 Copenhagen NV.
                    </span>
                </div>
                <div class="right">
                    <b>Hours of Business</b></br></br>
                    <span>
                        Monday - Thursday: 09:00 - 17:00</br>
                        Friday: 10:00 - 17:00</br>
                        Saturday: 12:00 - 17:00</br>
                        Sunday: 12:00 - 15:00
                    </span>
                </div>
            </div>
        </div>
    </div>
</body>
</html>