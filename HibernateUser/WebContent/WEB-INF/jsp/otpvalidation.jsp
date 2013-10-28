<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
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
	
	<div style="padding:0 0 80px 0">
		<div style="height:400px; width: 900px; padding:50px; margin:0px 50px; text-align:center">
		<h4 style="text-align:left;margin-bottom:0;margin-top:10px;">A One-Time-Password(OTP) has been sent to your email.</h4><br />
		<h4 style="text-align:left;margin-bottom:0;margin-top:10px;">Please validate it as soon as possible, in order to finish registration.</h4><br />
			<div style="height:300px;width:400px; padding:20px; border: 1;border:thin dashed;float:center; margin:auto auto">
				<h4 style="text-align:left;margin-bottom:0;margin-top:10px;">OTP Validation</h4><br />				
				<div style="float:center; margin:auto auto">
					<form:form action="createotp.html" method="POST" style="border:0px;">				
						<input type="submit" value="Create another OTP" style="float:center;padding:5px 15px;margin-top:7px;"/>	
					</form:form>	
	                <form:form action="validateotp.html" method="POST" commandName="visitor" style="border:0px;">
	                	<form:hidden  path="machineID" value="<%=session.getAttribute(\"machineID\") %>" />
						<form:input path="otpInput" placeholder="Enter your OTP" required="true" autocomplete="off" style="width:150px;height:25px;float:center;margin-bottom:30px;margin-top:30px;" /><br />
						<input type="submit" value="Validate OTP" style="float:center;padding:5px 15px;"/>	<br />
					</form:form>
					<p style="float:left;display:inline;margin-top:0px;height:20px;line-height:20px;color:red">${status}</p>
				</div>
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