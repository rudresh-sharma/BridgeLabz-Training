<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login</title>


<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
}


body{

    font-family:Arial, sans-serif;
    background:#f2f2f2;

}


.container{

    width:400px;
    margin:70px auto;
    background:white;
    padding:25px;
    border-radius:12px;
    box-shadow:0 0 15px rgba(0,0,0,0.2);

}


h2{

    text-align:center;
    margin-bottom:25px;

}


input,
button{

    width:100%;
    padding:12px;
    margin:10px 0;
    font-size:16px;

}


button{

    background:#28a745;
    color:white;
    border:none;
    border-radius:5px;
    cursor:pointer;

}


button:hover{

    background:#218838;

}


.error{

    color:red;
    text-align:center;
    margin-bottom:10px;

}


p{

    text-align:center;
    margin-top:15px;

}


</style>


</head>


<body>


<div class="container">


<h2>User Login</h2>



<%
String error = (String)request.getAttribute("error");

if(error != null){
%>

<p class="error">
    <%= error %>
</p>

<%
}
%>




<form action="login" method="post">


<input
    type="email"
    name="email"
    placeholder="Enter Email"
    required>



<input
    type="password"
    name="password"
    placeholder="Enter Password"
    required>



<button type="submit">
    Login
</button>


</form>



<p>
    Don't have an account?
    <a href="signup.jsp">
        Signup
    </a>
</p>



</div>


</body>


</html>