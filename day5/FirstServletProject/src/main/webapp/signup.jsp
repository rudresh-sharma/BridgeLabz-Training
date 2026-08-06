<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Signup</title>

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
	    margin:60px auto;
	    background:#fff;
	    padding:25px;
	    border-radius:12px;
	    box-shadow:0 0 15px rgba(0,0,0,0.2);
	}

	h2{
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
	    background:#007bff;
	    color:white;
	    border:none;
	    border-radius:5px;
	    cursor:pointer;
	}

	button:hover{
	    background:#0056b3;
	}

	p{
	    margin-top:20px;
	    text-align:center;
	}

</style>

</head>

<body>

<div class="container">

	<h2>User Registration</h2>

	<form action="${pageContext.request.contextPath}/signup" method="post">
	    <input
	        type="text"
	        name="fullName"
	        placeholder="Enter Full Name"
	        required>

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
	        Register
	    </button>

	</form>

	<p>
	    Already have an account?
	    <a href="login.jsp">Login</a>
	</p>

</div>

</body>

</html>