<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrapper">
  <div id="header">
    <div id="logo">
			<h1><a href="#">ASU Bank </a></h1>
	</div>
		<div id="slogan"> <ul>
			<li class="first current_page_item"><a href="#">Home</a> </li>
			<li><a href="#">UserName</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">LogOut</a></li>
          </ul>		</div>
  </div>
	<div id="menu">
		<ul>
			<li class="first current_page_item"><a href="#">Accounts</a></li>
			<li><a href="#">Transfers</a></li>
			<li><a href="#">Profile &amp;Settings</a></li>
			
			<li><a href="#">Help &amp;Support</a></li></ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
			<h2>&nbsp;</h2>
			<form:form action="createuser.html" method="post" commandName="user" style="width:1000px; height=600px; border:0; text-align:center">
				<table>
					<tr>
						<td>First name</td><td><form:input path="firstname" /></td>
						<td>Last name</td><td><form:input path="lastname" /></td>
					</tr>
					<tr>
						<td>User ID</td><td><form:input path="userID" /></td>
						<td>Department ID</td><td><form:input path="deptID" /></td>
					</tr>
					<tr>
						<td>Role ID</td><td><form:input path="roleID" /></td>
						<td>Account No.</td><td><form:input path="accno" /></td>
					</tr>
					<tr>
						<td>Amount</td><td><form:input path="amount" /></td>
						<td>Card No.</td><td><form:input path="cardno" /></td>
					</tr>
					<tr>
						<td>PIN No.</td><td><form:input path="pinno" /></td>
						<td>Password</td><td><form:input path="password" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit"></td>
						
					</tr>
				</table>			  
		    </form:form>
<!-- 		    <form:form action="userlist.html" method="post">
				<td><input  type="submit" value="View" ></td>
			</form:form>-->
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
		  <p>Banking products are provided by Bank of America, N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
			  Investing in securities involves risks, and there is always the potential of losing money when you invest in securities. You should review any planned financial transactions that may have tax or legal implications with your personal tax or legal advisor.<br>
		  </p>
	  </div>
		<div id="page-bottom-sidebar">
			<h3>Popular Links</h3>
			<ul class="list">
				<li><a href="#">Order checks</a></li>
				<li><a href="#">Order a Debit Card</a></li>
				<li class="last"><a href="#">Order a Foreign Currency</a></li>
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