<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ASU Bank</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrapper">
  <div id="header">
	  <div id="logo">
			<h1><a href="#">ASU Bank </a></h1>
		</div>
		
  </div>
	<div id="menu">
		<ul>
			<li class="first current_page_item"><a href="#">Homepage</a></li>
			<li><a href="#">Products</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Clients</a></li>
			<li><a href="#">Support</a></li>
			<li><a href="#">About</a></li>
			<li class="last"><a href="#">Contact</a><br class="clearfix" />
	</li>
		</ul>
	</div>
	<div id="splash">
		<img class="pic" src="images/pic01.jpg" width="980" height="230" alt="" /></div>
	<div id="page">
	  <div id="content">
		  <div class="box">
				<h2>Welcome to ASU BANK</h2>
                <h3>
                LOGIN 
:				</h3>
                <form:form id="form1" method="post" action="home.html" commandName="user">
                	<table style="margin-top: 5px; margin-right:200px">
                		<tr style="color:red;">
                			<c:out value="${ErrorMsg}" />
                		</tr>
                		<tr>
                			<td>User ID:</td>
                			<td><form:input  id="textfield" path="strID"/></td>
                		</tr>
                		<tr>
                			<td>Password:</td>
                			<td><form:input type="password" id="textfield2" path="password"/></td>
                		</tr>
                		<tr>
                			<td>
                				<input type="checkbox" id="checkbox" value="checkbox" />
                			</td>
                			<td>
                				<label for="checkbox">Remember me</label>
                			</td>
                			<td>
                				<input type="submit" id="submit" value="Submit" />
                			</td>
                			
                		</tr>
                		
                	</table>
            
                  <p class="member"><a href="#">Lost your Password?</a><br />Not a member yet? <a href="userinfo.html">Create Account now!</a></p>
	        </form:form>
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
				<li class="first"><a href="#">Order checks</a></li>
				<li><a href="#">Order a Debit Card</a></li>
				<li class="last"><a href="#">Order a Foreign Currency</a></li>
			</ul>
		</div>
		<br class="clearfix" />
	</div>
</div>
<div id="footer">
	Copyright (c) 2013 ASUBank.com. All rights reserved.
</div>
</body>
</html>