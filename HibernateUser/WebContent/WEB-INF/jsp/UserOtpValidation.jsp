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
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);
  %>
<body>
<div id="wrapper">
  <div id="header">
	  <div id="logo">
			<h1><a href="account.html">ASU Bank </a></h1>
		</div>
		<div id="slogan"> <ul>
			<li class="first current_page_item"><a href="<%=session.getAttribute("employeepage") %>">${employee}</a> </li>
			<li class="first current_page_item"><a href="account.html">Home</a> </li>
			<li><a href="profilesetting.html" id="strID"><%=session.getAttribute("strID") %></a></li>
			<li><a href="logout.html">LogOut</a></li>
          </ul>		</div>		
  </div>
	<div id="menu">
		<ul>
			<li class="account"><a href="account.html">Accounts</a></li>
			<li><a href="Transfer.html">Transfers</a></li>
			<li><a href="Payment.html">Make Payment</a></li>
			<li><a href="Merchant.html">Merchants Click Here!</a>
			<li><a href="profilesetting.html">Profile &amp; Settings</a></li>				
			<li><a href="HelpAndSupport.html">Help &amp; Support</a></li>
		</ul>
	</div>
	
	<div style="padding:0 0 80px 0">
		<div style="height:400px; width: 900px; padding:50px; margin:0px 50px; text-align:center">
		<h4 style="text-align:left;margin-bottom:0;margin-top:10px;">A One-Time-Password(OTP) has been sent to your email.</h4><br />
		<h4 style="text-align:left;margin-bottom:0;margin-top:10px;">Please validate it as soon as possible, in order to finish registration.</h4><br />
			<div style="height:300px;width:400px; padding:20px; border: 1;border:thin dashed;float:center; margin:auto auto">
				<h4 style="text-align:left;margin-bottom:0;margin-top:10px;">OTP Validation</h4><br />				
				<div style="float:center; margin:auto auto">
					<form:form action="usercreateotp.html" method="POST" style="border:0px;">				
						<input type="submit" value="Create another OTP" style="float:center;padding:5px 15px;margin-top:7px;"/>	
					</form:form>	
	                <form:form action="uservalidateotp.html" method="POST" commandName="security" style="border:0px;">
	                	<form:hidden  path="strID" value="<%=session.getAttribute(\"strID\") %>" />
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
		  <p>Banking products are provided by ASUBank, N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
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
	Copyright (c) 2013 ASUBank.com. All rights reserved.
</div>
</body>
</html>