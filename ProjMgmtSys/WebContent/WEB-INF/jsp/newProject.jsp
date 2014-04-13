<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Group Manager Home Page</title>
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/mainstyle.css" />
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<div id="content">
				<h1>Group Manager Home</h1>
				<h3>Hello <%=session.getAttribute("userName")%>(<%=session.getAttribute("userId")%>)</h3>		
				<h5><%=session.getAttribute("depName")%>(<%=session.getAttribute("depId")%>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("groName")%>(<%=session.getAttribute("groId")%>)</h5>
				
				<div class="span12 alertdiv" style="margin-top:60px; height:50px">					
				</div>
				
				<div class="subdiv span12">
					<div class="row">
						<div class="span4 offset1">
							<input type="text"  class="span12" placeholder="Enter the name of the new field"  required="true" />
						</div>
						<div class="span3">
							<select class="form-control span12">
							    <option value="0">Integer</option>
							    <option value="1">String</option>
							    <option value="2">Date</option>
							</select>
						</div>
						<div id="btndiv" class="span3">
							<input type="submit" class="btn btn-primary btn-block" value="Add"/>
						</div>
					</div>
				</div>
				
				<div class="subdiv span12">
					<div class="row">
						<div class="span4 offset1">
							<input type="text"  class="span12" placeholder="Enter the name of the new project"  required="true" />
						</div>
						<div class="span2 offset2 labelcenter">
							<label>Start Date</label>
						</div>
						<div class="span1">
							<input id="dobYear" type="text" placeholder="YYYY" pattern=".{4}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
				  		</div>
				  		<div class="span1">
				  		   	<input id="dobDay" type="text"  placeholder="DD" pattern=".{1,2}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
				  		</div>
				  		<div class="span1">
				  		    <input id="dobMonth" type="text" placeholder="MM" pattern=".{1,2}" onkeypress="return event.charCode >= 48 && event.charCode <= 57" />
						</div>
					</div>
				</div>
				
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span2">
							<select class="form-control span12">
							    <option value="0">Integer</option>
							    <option value="1">String</option>
							    <option value="2">Date</option>
							</select>
						</div>
						<div class="span10">
							<input type="submit" class="btn btn-primary btn-block" value="Add"/>
						</div>
					</div>
				</div>
				
				
				
				
				
				
				
				
				
				
				
				<!--<h3>Group 17 <br/>
					Shihuan Shao&nbsp;&nbsp;&nbsp;&nbsp;Tuyue Chen&nbsp;&nbsp;&nbsp;&nbsp;Yongming Zhang</h3>
				<form:form method="post" action="home.html" commandName="user">
					<div id="resultdiv">
						
					</div>
					<div id="logindiv" class="span6 offset3">
						<form:input id="input1" class="span12" placeholder="UserID" type="text" value="" path="userId" required="true" />
						<form:input id="input2" class="span12" placeholder="Password" type="password" path="password" required="true" />
					</div>
					<div id="btndiv" class="span8 offset2">
						<div class="span6 offset3">
							<input type="submit" class="btn btn-large btn-success btn-block" value="Log In"/>
						</div>
					</div>
				</form:form>-->
			</div>
		</div>
	</div>
</body>
</html>