<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>View Transactions Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"  media="screen">
</head>
<body>
<body>
<div id="wrapper">
  <div id="header">
    <div id="logo">
			<h1><a href="DepartmentManager.html">ASU Bank </a></h1>
		</div>
		<div id="slogan"><ul>
			<li><a href="account.html">Customer</a></li>
            <li class="first current_page_item"><a href="#">Home</a> </li>
            <li><a href="profilesetting.html" id="strID"><%=session.getAttribute("strID") %></a></li>
            <li><a href="logout.html">LogOut</a></li>
          </ul>			</div>
  </div>
	<div id="menu">
		<ul><li><a href="DepartmentManager.html">Go Back to Account Management &emsp;</a></li>			 
			<li><a href="profilesetting.html">Profile&amp;Settings &emsp;</a></li>
			<li><a href="HelpAndSupport.html">Help &amp;Support</a></li></ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
				<h2>Welcome Department Manager! <br></h2><h4>Below is the list of Employees in your Department/s !</h4>
				<table width="690" height="235" border="1" align="center">
				<tr>
				<td> <b><u> ID</u></b></td>
				<td> <b><u> Department </u></b></td>
				<td> <b><u> Role </u></b></td>
				
				</tr>
				  <c:forEach var="employee" items="${list}" >
    				<tr>
    				<td><c:out value="${employee.user_id}"/></td>
      				<td><c:out value="${employee.department}"/></td>  
      				<td><c:out value="${employee.role}"/></td>  				
    				    				
    				</tr>
  				</c:forEach>			      
		   		 </table>
		   		 
				<p>&nbsp;</p>
				<p>&nbsp;</p>
		</div>
			<br class="clearfix" />
		</div>
		
		<br class="clearfix" />
	</div>
	<div id="page-bottom">
	  <div id="page-bottom-content">
		<h3>ASU Bank Policy</h3>
		  <p>Banking products are provided by ASU Bank , N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
			  Investing in securities involves risks, and there is always the potential of losing money when you invest in securities. You should review any planned financial transactions that may have tax or legal implications with your personal tax or legal advisor.<br>
		  </p>
		</div>
		<div id="page-bottom-sidebar">
			<h3>Popular Links</h3>
			<ul class="list">
				<li><a href="NoServicePage.html">Order checks</a></li>
				<li><a href="NoServicePage.html">Order a Debit Card</a></li>
				<li class="last"><a href="NoServicePage.html">Order a Foreign Currency</a></li>
			</ul>
		</div>
		<br class="clearfix" />
	</div>
</div>
<div id="footer">
	Copyright (c) 2012 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/" rel="nofollow">FreeCSSTemplates.org</a>.
</div>
</body>
</html>