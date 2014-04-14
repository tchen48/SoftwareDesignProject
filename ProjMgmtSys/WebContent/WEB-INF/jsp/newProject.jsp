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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script>
		$(function() {
	   		$( "#datepicker" ).datepicker();
	  	});
	</script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<h1>New Project</h1>
			<h3><%=session.getAttribute("userName")%>&nbsp;(id: <%=session.getAttribute("userId")%>)</h3>		
			<h5><%=session.getAttribute("depName")%>&nbsp;(id: <%=session.getAttribute("depId")%>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("groName")%>&nbsp;(id: <%=session.getAttribute("groId")%>)</h5>
			
			<div class="span12 alertdiv" style="margin-top:60px; height:50px">					
			</div>
			
			<div class="subdiv span12">
				<div class="content span8 offset2">
				<div>
					<input type="text"  class="span12" placeholder="Enter the name of the new project"  required="true" />
				</div>
				<div>
					<input class="span6" placeholder="Choose Start Date" type="text" id="datepicker">
				</div>
				<div>
					<textarea class="input-block-level" placeholder="Enter project description" rows="5"></textarea>
				</div>
				<div class="span12" id="customdiv"></div>
				<div class="span12" style="margin-bottom: 30px;">
					<div class="span3 offset4">
					<input type="submit" class="btn btn-primary btn-block" value="Create"/>
					</div>
					<div class="span3 offset1">
					<a href="groMngHome.html" class="btn btn-block">Cancel</a>
					</div>
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
</body>
</html>