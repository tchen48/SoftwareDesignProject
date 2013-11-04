<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
				<h2>View Transactions <br></h2>
				<form:form id="form1" method="post" action="ViewTransaction.html" commandName="user">
				<table width="413" height="107" border="0" align="center">
				<tr> 
			    <td width="206" height="52" style="text-align: Left">Enter UserID</td>
			    <td width="300"><label for="textfield2">:</label>
                <form:input type="text" name="textfield2" id="textfield2" required="true" autocomplete="off" path="strID" style="height: 20px;" /></td>
		      </tr>
			  
			   <tr>
			    <td height="49"><a href="#"></a></td>
			    <td style="text-align: center"><input type="submit" name="action" id="button" value="view" align="left"></td>
			 
		      </tr>
		      </table >
		      <table width="900" height="235" border="1" align="center">
				<tr>
				<td> <b><u> ID</u></b></td>
				<td> <b><u> From </u></b></td>
				<td> <b><u> To </u></b></td>
				<td> <b><u> Amount</u></b></td>
				
				</tr>
				  <c:forEach var="transaction" items="${transaction}" >
    				<tr>
    				<td><c:out value="${transaction.getId()}"/></td>
      				<td><c:out value="${transaction.getFromID()}"/></td>  
      				<td><c:out value="${transaction.getToID()}"/></td>  				
    				<td><c:out value="${transaction.getAmount()}"/></td>    				
    				
    				
    				</tr>
  				</c:forEach>			      
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
	Copyright (c) 2012 ASUBank.com. All rights reserved. 
</div>
</body>
</html>