<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Error Page Bank</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"  media="screen">
</head>
<body>
<body>
<div id="wrapper">
  <div id="header">
    <div id="logo">
			<h1><a href="account.html">ASU Bank </a></h1>
		</div>
		<div id="slogan"><ul>
		    <li><a href="account.html">Customer</a></li>
            <li class="first current_page_item"><a href="account.html">Home</a> </li>
            <li><a href="profilesetting.html" id="strID"><%=session.getAttribute("strID") %></a></li>
            <li><a href="HelpAndSupport.html">ContactUs</a></li>
            <li><a href="logout.html">LogOut</a></li>	
          </ul>			</div>
  </div>
	<div id="menu">
		<ul>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>		
			<li><a href="HelpAndSupport.html">Help &amp;Support</a></li>
						
	
		</ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
		  <img src="${pageContext.request.contextPath}/resources/images/SomethingWentWrong.jpg"  width="425" height="188">
				<h2><br><br>Error!!!<br><br></h2><h4>Something went wrong in this operation. <br>Please make sure all the inputs are correct, else try again later. <br><br> If you need further help, contact the support team for assistance.  <br><br></h4><h4>We Are Sorry For The Inconvenience !<br></h4>
				
		</div>
			<br class="clearfix" />
		</div>
		
		<br class="clearfix" />
	</div>
	<div id="page-bottom">
	  <div id="page-bottom-content">
		<h3>ASU Bank Policy</h3>
		  <p>Banking products are provided by ASU Bank, N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
			  Investing in securities involves risks, and there is always the potential of losing money when you invest in securities. You should review any planned financial transactions that may have tax or legal implications with your personal tax or legal advisor.<br>
		  </p>
		</div>
		
		<br class="clearfix" />
	</div>
</div>
<div id="footer">
	Copyright (c) 2012 ASUBank.com. All rights reserved. 
</div>
</body>
</html>