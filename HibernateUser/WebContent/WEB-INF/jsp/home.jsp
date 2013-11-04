<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ASU Bank Home</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrapper">
  <div id="header">
	  <div id="logo">
			<h1><a href="#">ASU Bank </a></h1>
		</div>
		<div id="slogan"> <ul>
			<li class="first current_page_item"><a href="#">Home</a> </li>
			<li><a href="#">${user.strID}</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">LogOut</a></li>
          </ul>		</div>
		
  	</div>
	<div id="menu">
		<ul>
			<li class="first current_page_item">
				<form:form class="menuform" action="profilesetting.html" method="POST" commandName="user" style="border:0px; float:left; display:inline; ">
					<form:hidden path="strID" value="${user.strID}" />
					<input class="menuinput" type="submit" value="Accounts" />
				</form:form>
			</li>
			<li>
				<form:form class="menuform" action="profilesetting.html" method="POST" commandName="user" style="border:0px; float:left; display:inline;">
					<form:hidden path="strID" value="${user.strID}" />
					<input class="menuinput" type="submit" value="Transfer" />
				</form:form>
			</li>
			<li>
				<form:form class="menuform" action="profilesetting.html" method="POST" commandName="user" style="border:0px; float:left; display:inline;">
					<form:hidden path="strID" value="${user.strID}" />
					<input class="menuinput" type="submit" value="Profile & Settings" />
				</form:form>			
			</li>			
			<li>
				<form:form class="menuform" action="profilesetting.html" method="POST" commandName="user" style="border:0px; float:left; display:inline;">
					<form:hidden path="strID" value="${user.strID}" />
					<input class="menuinput" type="submit" value="Help & Support" />
				</form:form>
			</li></ul>
	</div>
	<div>
		<div style="height:500px; width: 800px; padding:20px">
			<div id="OTP">
				<form:form action="createotp.html" method="POST" commandName="user" style="border:0px;">				
					<form:hidden path="strID" value="${user.strID}" />
					<input type="submit" value="Create OTP" style="float:left;padding:5px 10px;margin-right:20px;margin-top:7px;"/>	
				</form:form>			
				<p style="float:left; display:inline;">${security.otp}</p><br/><br/>
				<form:form action="validateotp.html" method="POST" commandName="security" style="border:0px;">
					<p style="float:left;display:inline;margin-top:5px;margin-right:20px;height:20px;line-height:20px;">OTP</p>	
					<form:hidden path="strID" value="${user.strID}" />			
					<form:input path="otpInput" placeholder="Enter your OTP" value="" style="width:150px;height:25px;float:left;display:inline;margin-right:20px;"/>
					<input type="submit" value="Validate OTP" style="float:left;display:inline;padding:5px 10px;margin-right:20px;"/>	
				</form:form>
				<p style="float:left;display:inline;margin-top:0px;height:20px;line-height:20px;">${status}</p>
			</div>
			<br/><br/>
			<div id="Captcha">
				<div style="float:left; display:inline;margin-right:20px;" >	
					<img src="data:image/png;base64,${encodedImage}" alt="Click Refresh" width="250px" height="50px" />			
		    	</div>
	        	<form:form style="border:0px;" action="createcaptcha.html" method="POST" commandName="user" >
	        		<form:hidden path="strID" value="${user.strID}" />	
	        		<input type="submit" value="Refresh" style="padding: 5px 10px;float:left; display:inline; margin-right:20px;" />
				</form:form>
				<br/>
				<form:form style="margin-top:20px;border:0px;" action="validatecaptcha.html" method="POST" commandName="security">
					<form:hidden path="strID" value="${user.strID}" />
			        <form:input path="captchaInput" type="text" id="txtInput" placeholder="Case Insensitive" style="float:left; display:inline; margin-right:20px;width:150px;height:25px;"/>    		    
			        <input type="submit" value="Validate Captcha" onclick="alert(ValidCaptcha());"  style="padding: 5px 10px;float:left; display:inline; margin-right:20px;"/>
				</form:form>	
				<p style="float:left;display:inline;margin-top:0px;height:20px;line-height:20px;">${statusCap}</p>		
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