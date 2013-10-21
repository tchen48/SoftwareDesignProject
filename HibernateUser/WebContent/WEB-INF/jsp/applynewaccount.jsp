<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script.js"></script>
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
	
	<div id="page">
	  <div id="content">
		  <div class="box">
			<h2>Account Registration</h2>
			<form:form action="createuser.html" method="post" modelAttribute="command" commandName="userinformation" style="width:800px; height=600px; border:0; text-align:center; margin:auto auto">
				<table style="border-spacing: 0px 25px;">
					<tr>
						<td style="text-align: right;">First name</td><td style="text-align: left;"><form:input type="text" maxlength="20" path="firstname"  required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Last name</td><td style="text-align: left;"><form:input path="lastname" type="text" maxlength="20" required="true" autocomplete="off" style="height:30px;width:200px" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">Birth</td>
						<td style="text-align: left;padding:0"><form:input id="dobYear" path="dobYear" type="number" min="1900" max = "2013" autocomplete="off" style="margin-left:18px;height:30px"/>
													  		   <form:input id="dobDay" path="dobDay" type="number" min="1" max = "31" autocomplete="off" style="margin-left:18px;height:30px" />
													  		   <form:input id="dobMonth" path="dobMonth" type="number" min="1" max = "12" autocomplete="off" style="height:30px" />
						</td>	
						<td style="text-align: right;">SSN</td><td style="text-align: left;"><form:input path="ssn" pattern=".{9}" required="true" autocomplete="off" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="height:30px;width:200px" type="text" maxlength="9" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">Address</td><td style="text-align: left;"><form:input path="address" type="text" maxlength="100" required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Email</td><td style="text-align: left;"><form:input type="email" path="email" maxlength="50" required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
					</tr>
					<tr>
						<td style="text-align: right;">Telephone</td><td style="text-align: left;"><form:input path="telephone" type="text"  pattern=".{10}" required="true" maxlength="10" autocomplete="off" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Role Type</td>
						<td style="text-align: right; height:30px; ;width:200px">
							<form:select path="roletype" style="height:30px;width:200px">
								<form:option value="1">Internal User</form:option>
								<form:option value="2">Personal User</form:option>
								<form:option value="3">Commercial User</form:option>
							</form:select>						
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">Account Password</td><td style="text-align: left;"><form:input path="password" type="password" pattern=".{6,20}" maxlength="20" required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Confirm Account Password</td><td style="text-align: left;"><form:input path="pwdConfirm" type="password"  pattern=".{6,20}" maxlength="20" required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
					</tr>
					<tr>
						<td style="text-align: right;">Transaction Password</td><td style="text-align: left;"><form:input path="transPwd" type="password"  pattern=".{6,20}"  maxlength="20" required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Confirm Transaction Password</td><td style="text-align: left;"><form:input path="transPwdConfirm" type="password"  pattern=".{6,20}" maxlength="20" required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
					</tr>					
				</table>
				<div style="margin:5px 8px 5px 8px">
					<form:hidden path="visitor.machineID" value="${visitor.machineID}" />
					<form:input path="visitor.captchaInput" type="text" id="txtInput" placeholder="Input the captcha below" required="true" autocomplete="off" pattern=".{6}" maxlength="6s" style="float:left; display:inline;width:200px;height:30px;"/>
               	 	<p style="float:left;display:inline;margin:0px 20px;height:25px;line-height:25px;color:red">${captchaStatus}</p>
               		<input type="submit" name="action" value="Submit" style="padding:5px 10px;margin-right: 20px;float:left; display:inline;" />	
					<input type="reset" value="Reset" style="padding:5px 10px;float:left; display:inline;" />	
               	</div>
		    </form:form>
		    <br />
		    <form:form action="createuser.html" method="post" modelAttribute="command" commandName="userinformation" style="width:800px; height=600px; border:0; text-align:center; margin-top:10px; margin-left:90px">
		    	<img src="data:image/png;base64,${encodedImage}" alt="Click Refresh" width="250px" height="50px" style="float:left;display:inline;margin:0px 20px 0px 9px" />	
               	<input type="submit" name="action" value="Refresh" style="margin-top:10px;padding: 5px 10px;float:left; margin-right:20px;display:inline;" /><br /><br />
               	<form:hidden path="visitor.machineID" value="${visitor.machineID}" />
               	<p style="color:red">${userInfoError}</p>
               	<p style="font-size:10px">* Password must include at least one uppercase letter, one lowercase letter, one number, and the length should be 6-20 characters.</p>
		    </form:form>
		    
			
			
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