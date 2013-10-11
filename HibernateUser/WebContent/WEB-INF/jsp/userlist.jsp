<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
<head>
<title>User List</title>
</head>
<body>
        <c:forEach var="user" items="${list}">
        	<c:out value="${user.firstname}" />
        	<c:out value="${user.lastname}" />
        	<c:out value="${user.userID}" />
    	</c:forEach>
</body>
</html>