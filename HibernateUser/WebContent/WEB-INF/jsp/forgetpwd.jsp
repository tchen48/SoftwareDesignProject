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
	
	<div style="padding:0">
		<div style="height:300px; width: 900px; padding:50px; margin:0px 50px; text-align:center">
			<div style="height:200px;width:400px; padding:20px; border: 1;border:thin dashed;float:center; margin:auto auto">
				<h4 style="text-align:left;margin-bottom:0;margin-top:10px;">Forget Password</h4>
                <form:form method="post" action="sendpwd.html" commandName="user" style="border:0px;padding-top:0">
                	<table style="border-spacing: 0px 25px;">                		
                		<tr>
                			<td>User ID</td>
                			<td style="padding-right:60px;"><form:input path="strID" required="true" style="width:200px;height: 30px;"/></td>
                		</tr>                		
                	</table>
                	<input type="submit" id="submit" value="Send Temporary Password" style="margin-bottom:30px; margin-right:60px; padding:5px 10px;"/><br />
                	<p style="color:red; float:left;margin-top:20px">${ErrorMsg}</p>  
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
		  <p>Banking products are provided by ASUBank, N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
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