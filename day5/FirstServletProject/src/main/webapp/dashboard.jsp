<%@ page import="com.servletlearning.model.User" %>


<%

User user =
(User)session.getAttribute("user");


if(user == null){

    response.sendRedirect("login.jsp");

    return;
}


%>



<!DOCTYPE html>
<html>

<head>

<title>Dashboard</title>

</head>


<body>


<h1>
Welcome <%= user.getFullName() %>
</h1>


<p>
Email:
<%= user.getEmail() %>
</p>


<a href="logout">
Logout
</a>


</body>

</html>