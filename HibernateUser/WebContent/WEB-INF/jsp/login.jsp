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
	<div id="splash">
		<img class="pic" src="images/pic01.jpg" width="980" height="230" alt="" /></div>
	<div id="page">
	  <div id="content">
		  <div class="box">
				<h2>Welcome to ASU BANK</h2>
                <h3>
                LOGIN 
:				</h3>
                <form:form id="form1" method="post" action="home.html" commandName="uservisitor">
                	<table style="margin-top: 5px; margin-right:200px">
                		<tr>
                			<div style="padding-left:10px; color:red;">
                				${ErrorMsg}
                			</div>
                		</tr>
                		<tr>
                			<td>User ID:</td>
                			<td><form:input  id="textfield" path="user.strID" required="true" autocomplete="off" style="height: 20px;"/></td>
                		</tr>
                		<tr>
                			<td>Password:</td>
                			<td><form:input type="password" id="textfield2" required="true" autocomplete="off" path="user.password" style="height: 20px;" /></td>
                			<td>
                				<input type="submit" name="action" id="submit" value="Login" style="padding:3px 10px;"/>
                			</td>
                		</tr>
                	</table>
                	<div style="margin:5px 8px 5px 8px">
						<form:input path="visitor.captchaInput" type="text" id="txtInput" autocomplete="off" placeholder="Input the captcha below" style="float:left; display:inline;width:200px;height:20px;"/>
	               	 	<p style="float:left;display:inline;margin-left:10px;margin-top:0px; margin-bottom:0px;height:20px;line-height:20px;color:red">${captchaStatus}</p><br />
	               	</div>            		
            		<img src="data:image/png;base64,${encodedImage}" alt="Click Refresh" width="250px" height="50px" style="float:left;display:inline;margin:0px 20px 0px 9px" />	
                	
                	<input type="submit" name="action" value="Refresh" style="padding: 5px 10px;float:left; margin-right: 50px;display:inline;" /><br /><br />
                	<form:hidden path="visitor.machineID" value="${visitor.machineID}" />
			        
	                <p class="member" style="margin-left: 2px; margin-bottom:0px; margin-top:0px"><a href="forgetpwd.html">Lost your Password?</a></p>
	                <p class="member" style="margin-left: 7px; margin-bottom:5px">Not a member yet? <a href="applynewaccount.html">Create Account now!</a></p>
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
		  <p>Banking products are provided by ASU Bank, N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of ASU Bank Corporation.<br>
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