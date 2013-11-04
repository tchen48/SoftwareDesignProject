<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Account Home Page Bank</title>
<!--  <link rel="stylesheet" type="text/css" href= "WebContent/WEB-INF/jsp/css/style.css"/>-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"  media="screen">
</head>
<body>
<body>
<div id="wrapper">
  <div id="header">
    <div id="logo">
			<h1><a href="SystemAdmin.html">ASU Bank </a></h1>
		</div>
		<div id="slogan"><ul>
			<li><a href="account.html">Customer</a></li>
			<li ><a href="SystemAdmin.html">Home</a> </li>
			<li><a href="profilesetting.html"><%=session.getAttribute("strID") %></a></li>				
			<li><a href="logout.html">LogOut</a></li>
          </ul>			</div>
  </div>
	<div id="menu">
		<ul>
			<li ><a href="ViewEmployeesAll.html">List of Employees</a></li>
			<li><a href="accounts.html">Accounts</a></li>
			<li><a href="ViewLogs.html">View Logs</a></li>			
			<li><a href="profilesetting.html">Profile &amp;Settings</a></li>
			<li><a href="HelpAndSupport.html">Help &amp;Support</a></li>
			
		</ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
				<h2>Welcome System Administrator! <br></h2><h4>You can Perform Your Administrative Tasks From This Page. </h4>
				
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
	Copyright (c) 2012 ASUBank.com. All rights reserved. .
</div>
</body>
</html>