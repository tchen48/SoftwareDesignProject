<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer User To Other Department</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"  media="screen">
</head>
<script type="text/javascript"> 
	function show_alert() 
	{ 
		alert("A Request Will Be Sent To System Administrator To Transfer The User From Current Department To The Requested Department. Authorizations May Be Required. "); 
	} 
</script>
<body>
<div id="wrapper">
  <div id="header">
    <div id="logo">
			<h1><a href="DepartmentManager.html">ASU Bank </a></h1>
	</div>
		<div id="slogan"> <ul>
			<li><a href="account.html">Customer</a></li>
            <li class="first current_page_item"><a href="#">Home</a> </li>
            <li><a href="profilesetting.html" id="strID"><%=session.getAttribute("strID") %></a></li>
            <li><a href="logout.html">LogOut</a></li>
          </ul>		</div>
  </div>
	<div id="menu">
		<ul><li><a href="DepartmentManager.html">Go Back to Account Management &emsp;</a></li>			 
			<li><a href="profilesetting.html">Profile&amp;Settings &emsp;</a></li>
			<li><a href="HelpAndSupport.html">Help &amp;Support</a></li></ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
			<h3>Welcome Department Manager !!!</h3>
			<h4> <br> To Transfer A User, Enter the UserID and Target Department Below.<br>This Will Send Authorization Request to Target Department's Manager. </h4>
			
		<form:form  method="post" action="submit_transfer_user_dept.html">
			<table width="470" height="200" border="0" align="center">
			  
			  <tr>
			    <td style="text-align: left">Employee ID</td>
			    <td><label for=textfield3>:</label>
                <input type="text" name="user_id" id="user_id"></td>
		      </tr>		      
		      <tr>
		      	<td style="text-align: justify">Transfer To </td>			    
			    <td><label for="textfield6">:</label>
                <select name="department" id="department" style='font-style: normal; font-size: 13px; font-family: Georgia, "Times New Roman", Serif'>                	
 				  <option value="HR">HR</option>
 				  	<option value="Transaction Management">Transaction Management</option>
  					<option value="Sales">Sales</option>
  					<option value="IT">IT and Technical Support</option>
  					<option value="Corporate Management">Corporate Management</option>
				</select></td>
		      </tr>
			  <tr>
			    <td style="text-align: left"><input type="submit" value="Submit to SystemAdministrator" align="left" onclick="show_alert()" ></td>
			    <td style="text-align: center"><input type="submit" value="Cancel" style="width: 157px; " ></td>
		      </tr>
		    </table>
		    </form:form>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
			<br class="clearfix" />
	  </div>
		
		<br class="clearfix" />
	</div>
	<div id="page-bottom">
	  <div id="page-bottom-content">
		<h3>ASU Bank Policy</h3>
		  <p>Banking products are provided by ASU Bank , N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
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
	Copyright (c) 2012 asubank.com. All rights reserved. 
</div>
</body>
</html>