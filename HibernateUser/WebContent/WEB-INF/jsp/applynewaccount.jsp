<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
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
			<h2>Account Registration</h2>
			<form:form action="createuser.html" method="post" modelAttribute="command" commandName="userinformation" style="width:800px; height=600px; border:0; text-align:center; margin:auto auto">
				<table style="border-spacing: 0px 25px;">
					<tr>
						<td style="text-align: right;">First name</td><td style="text-align: left;"><form:input type="text" placeholder="only letters" maxlength="20" path="firstname"  onkeypress='return (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122)' required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Last name</td><td style="text-align: left;"><form:input path="lastname"  placeholder="only letters" type="text" maxlength="20" onkeypress='return (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122)' required="true" autocomplete="off" style="height:30px;width:200px" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">Birth</td>
						<td style="text-align: left;padding:0"><form:input id="dobYear" path="dobYear" type="text" placeholder="YYYY" pattern=".{4}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' autocomplete="off" style="margin-left:18px;height:30px;float:right;display:inline;width:50px;"/>
													  		   <form:input id="dobDay" path="dobDay" type="text"  placeholder="DD" pattern=".{1,2}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' autocomplete="off" style="margin-left:18px;height:30px;float:right;display:inline;width:50px;" />
													  		   <form:input id="dobMonth" path="dobMonth" type="text" placeholder="MM" pattern=".{1,2}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' autocomplete="off" style="height:30px;float:right;display:inline;width:50px;" />
						</td> 
				<!-- 		<td style="text-align: left;padding:0"><form:input id="dobYear" path="dobYear" type="number" min="1900" max = "2013" autocomplete="off" style="margin-left:18px;height:30px"/>
													  		   <form:input id="dobDay" path="dobDay" type="number" min="1" max = "31" autocomplete="off" style="margin-left:18px;height:30px" />
													  		   <form:input id="dobMonth" path="dobMonth" type="number" min="1" max = "12" autocomplete="off" style="height:30px" />
						</td> -->	
						<td style="text-align: right;">SSN</td><td style="text-align: left;"><form:input path="ssn" placeholder="9 digits" pattern=".{9}" required="true" autocomplete="off" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="height:30px;width:200px" type="text" maxlength="9" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">Address</td><td style="text-align: left;"><form:input path="address" placeholder="100 letters and numbers" type="text" maxlength="100" onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122) || event.charCode == 32' required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Email</td><td style="text-align: left;"><form:input type="email" path="email" placeholder="example@sth.com" maxlength="50" required="true" autocomplete="off" style="height:30px;width:200px"  /></td>
					</tr>
					<tr>
						<td style="text-align: right;">Telephone</td><td style="text-align: left;"><form:input path="telephone" type="text" placeholder="10 digits" pattern=".{10}" required="true" maxlength="10" autocomplete="off" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="height:30px;width:200px"  /></td>
						<td style="text-align: right;">Role Type</td>
						<td style="text-align: right; height:30px; ;width:200px">
							<form:select path="roletype" style="height:30px;width:200px">
								<form:option value="1">Personal User</form:option>
								<form:option value="2">Merchant</form:option>
								<form:option value="0">Internal User</form:option>
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
		  <p>Banking products are provided by ASU Bank, N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
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