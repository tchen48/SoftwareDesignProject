<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<body>
<div id="wrapper">
  <div id="header">
    <div id="logo">
			<h1><a href="#">ASU Bank </a></h1>
	</div>
		<div id="slogan"> <ul>
			<li class="first current_page_item"><a href="employeeaccount.html">${employee}</a> </li>
			<li class="first current_page_item"><a href="#">Home</a> </li>
			<li><a href="#" id="strID"><%=session.getAttribute("strID") %></a></li>
			<li><a href="#">Services</a></li>
			<li><a href="logout.html">LogOut</a></li>
          </ul>		</div>
  </div>
		<div id="menu">
		<ul>
				<li class="account"><a href="account.html">Accounts</a></li>
				<li><a href="Transfer.html">Transfers</a></li>
				<li><a href="merchant.html">Merchants Click Here!</a>
				<li><a href="profilesetting.html">Profile &amp; Settings</a></li>				
				<li><a href="#">Help &amp; Support</a></li>
			</ul>
	</div>
	
	<div id="page">
	  <div id="content">
		  <div class="box">
			<h2>&nbsp;</h2>
			<form:form id="form1" method="post" action="MakeTransfer.html" commandName="transfer">
			<p style="float:left;display:inline;margin-top:0px;height:20px;line-height:20px;">${message}</p>
			<table width="680" height="313" border="0" align="center">
			  <tr>
			    <td width="150" style="text-align: left"><a href="Transfer.html">Make Transfer</a></td>
			    <td width="142" style="text-align: right">From</td>
			    <td width="302"><label for="textfield">:</label>
                <form:input type="text" name="textfield" id="textfield" required="true" autocomplete="off" path="fromID" style="height: 20px;" /></td>
		      </tr>
			  <tr>
			    <td style="text-align: left"><a href="#">Review Transfers</a></td>
			    <td style="text-align: right">To</td>
			    <td><label for="textfield2">:</label>
                <form:input type="text" name="textfield2" id="textfield2" required="true" autocomplete="off" path="toID" style="height: 20px;" /></td>
		      </tr>
			  <tr>
			    <td style="text-align: left"><a href="RecipientInfo.html">Add Recipients</a></td>
			    <td style="text-align: right">Amount $</td>
			    <td><label for="textfield3">:</label>
                <form:input type="text" name="textfield3" id="textfield3" required="true" autocomplete="off" path="amount" style="height: 20px;" /></td>
		      </tr>
			  <tr>
			    <td style="text-align: left"><a href="#">Edit Recipients</a></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
		      </tr>
			  <tr>
			    <td><a href="#"></a></td>
			    <td style="text-align: right"><input type="submit" name="action" id="button" value="Continue" align="middle"></td>
			    <td style="text-align: center"><input type="button" name="action" id="button2" value="Cancel"></td>
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
	    <p>Banking products are provided by Bank of America, N.A. and affiliated banks, Members FDIC and wholly owned subsidiaries of Bank of America Corporation.<br>
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
	Copyright (c) 2012 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/" rel="nofollow">FreeCSSTemplates.org</a>.
</div>
</body>
</html>