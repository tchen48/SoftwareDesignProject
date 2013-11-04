<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"> 
	function show_alert() 
	{ 
		alert("You need Authorization for this action !!! "); 
		
	} 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>View Transactions Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"  media="screen">
</head>
<body>
<body>
<div id="wrapper">
  <div id="header">
    <div id="logo">
			<h1><a href="RegularEmployee.html">ASU Bank </a></h1>
		</div>
		<div id="slogan"><ul>
			<li><a href="account.html">Customer</a></li>
			<li class="first current_page_item"><a href="RegularEmployee.html">Home</a> </li>
			<li><a href="profilesetting.html" id="strID"><%=session.getAttribute("strID") %></a></li>
			<li><a href="logout.html">LogOut</a></li>
          </ul>			</div>
  </div>
	<div id="menu">
		<ul><li class="account"><a href="account.html">Accounts</a></li>
				<li><a href="Transfer.html">Transfers</a></li>
				<li><a href="Payment.html">Make Payment</a></li>
				<li><a href="Merchant.html">Merchants Click Here!</a>
				<li><a href="profilesetting.html">Profile &amp; Settings</a></li>
				<li><a href="HelpAndSupport.html">Help &amp; Support</a></li></ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
				<h2>View Accounts <span style="text-align: right">
				<form:form id="form1" method="post" action="ViewAccount.html" commandName="user">
				  <input type="submit" name="action" id="button" value="view" align="left">
			</span><br></h2>
				</form:form>
				<table width="900" height="235" border="1" align="center">
				
			  		
				<td> <b><u> CheckingID </u></b></td>
				<td> <b><u> SavingID </u></b></td>
				<td> <b><u> CreditID</u></b></td>
				<td> <b><u> CheckingBalance</u></b></td>
				<td> <b><u> SavingBalance</u></b></td>
				<td> <b><u> CreditBalance</u></b></td>
				<td> <b><u> Action</u></b></td>
				
				</tr>
				  <c:forEach var="account" items="${account}" >
    				<tr>
    				
      				<td><c:out value="${account.getCheckingID()}"/></td>  
      				<td><c:out value="${account.getSavingID()}"/></td>  				
    				<td><c:out value="${account.getCreditID()}"/></td>
    				<td><c:out value="${account.getCheckingBalance()}"/></td>
    				<td><c:out value="${account.getSavingBalance()}"/></td>
    				<td><c:out value="${account.getCreditBalance()}"/></td> 
    				<td onclick="show_alert()"><a href=#">Modify</a> <a href=#">Delete</a></td>   				
    				
    				
    				</tr>
  				</c:forEach>			      
   		    </table>
   		    
		   		 
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
	Copyright (c) 2012 ASUBank.com. All rights reserved. 
</div>
</body>
</html>