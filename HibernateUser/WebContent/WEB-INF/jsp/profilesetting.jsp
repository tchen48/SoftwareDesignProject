<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ASU Bank Profile and Settings</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
</head>
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
				<li class="first current_page_item"><a href="account.html">Accounts</a></li>
				<li><a href="Transfer.html">Transfers</a></li>
				<li><a href="Payment.html">Make Payment</a></li>
				<li><a href="Merchant.html">Merchants Click Here!</a>
				<li><a href="profilesetting.html">Profile &amp; Settings</a></li>				
				<li><a href="HelpAndSupport.html">Help &amp; Support</a></li>
			</ul>
	</div>
	<div style="padding:0">
		<div style="height:500px; width: 900px; padding:50px; margin:0px 50px">
			<div id="profile" style="height:400px;width:400px; padding:20px; margin:0;border:thin dashed;float:left;display:inline">
				<h4>Profile</h4>
				<a href="checkingid.html">Checking ID</a> <p id="acctnoField">${checkingID}</p><br /><br />
				<a href="savingid.html">Saving ID</a> <p id="acctnoField">${savingID}</p><br /><br />
				<a href="creditid.html">Credit ID</a> <p id="acctnoField">${creditID}</p><br /><br />
				<a href="email.html">Email</a><p id="emailField" >${email}</p><br /><br />
				<a href="address.html">Address</a><p id="addressField">${address}</p><br /><br />
				<a href="telephone.html">Phone</a><p id="phoneField">${telephone}</p>
			</div>
			<div id="settings" style="height:400px;width:400px; padding:20px; margin:0;border: 1;border:thin dashed;float:right;display:inline">
				<h4>Settings</h4>
				<form:form action="setting.html" method="POST" commandName="user" style="border:0px; float:left; display:inline; ">
					<form:hidden path="strID" value="<%=session.getAttribute(\"strID\") %>" />
					<input class="settingsubmit" type="submit" name="action" value="Change Password" style="margin-bottom:30px" /><br /><br />
					<input class="settingsubmit" type="submit" name="action" value="Change Contact" style="margin-bottom:30px" /><br /><br />
				</form:form>
			</div>			
		</div>
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