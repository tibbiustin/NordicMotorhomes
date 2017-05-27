<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="app.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Nordic Motorhomes Rental</title>
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>

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
            <p><a style="color:white; text-decoration:none" href="index.jsp">home</a> | <a style="color:white; text-decoration:none" href="login.jsp">login</a> | <a style="color:white; text-decoration:none" href="register.jsp">register</a></p>
        </div>
        <div id="content">
            <h1>Login</h1>
            <div style="color:#575b5e;margin-bottom:20px;">
                <form action="login">
                    E-mail: <input type="text" name="email" /></br></br>
                    Password: <input type="password" name="password" /></br></br>
					<input type="submit" value="Login">
                </form>
            </div>
        </div>
        <div id="footer">
            <div class="block">
                <div class="left">
                    <b>Company & Site Information</b>
                    <ul><li><a style="color:#70818b; text-decoration:none" href="index.jsp">home</li><li><a style="color:#70818b; text-decoration:none" href="login.jsp">login</li><li><a style="color:#70818b; text-decoration:none" href="register.jsp">register</li></ul>
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