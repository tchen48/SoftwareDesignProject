<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
	<div id="wrapper">
  <div id="header">
	  <div id="logo">
			<h1><a href="login.html">ASU Bank </a></h1>
		</div>
		
  </div>
	<div id="menu">
		<ul>
			<li class="first current_page_item"><a href="login.html">Homepage</a></li>
					</ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
				<h4>400 Error</h4>
                
                <form:form method="post" action="errorhandling.html" style="border:0; text-align:center; margin:auto auto">                	                	
                	<input type="submit" value="Log In" style="padding: 5px 10px;float:center; margin-right: 50px;display:inline;" /><br /><br />
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