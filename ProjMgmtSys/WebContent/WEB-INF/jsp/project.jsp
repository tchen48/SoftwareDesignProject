<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Project Page</title>
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/mainstyle.css" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script>
		$(function() {
	   		$( ".datepicker" ).datepicker();
	  	});
	</script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<h1>Project</h1>
			<h3><%=session.getAttribute("userName")%>&nbsp;(id: <%=session.getAttribute("userId")%>)</h3>		
			<h5>Dept: <%=session.getAttribute("depName")%>&nbsp;(id: <%=session.getAttribute("depId")%>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Group: <%=session.getAttribute("groName")%>&nbsp;(id: <%=session.getAttribute("groId")%>)</h5>
			<h5>Project: <%=session.getAttribute("projName")%>&nbsp;(id: <%=session.getAttribute("projId")%>)</h5>
			
			<div class="span12 alertdiv" style="margin-top:60px; height:50px">					
			</div>
			
			<div class="span12 subdiv">
				<div class="span10 offset1" style="margin-bottom:30px">
					<div class="row">
						<div class="span1">
							<span class="label label-info">status</span>
						</div>
						<div id="statusdiv"class="span2 labelcenter">
							<label id="statusLabel">Hidden Status</label>
						</div>
						<div class="span3">
							<select id="statusList" class="form-control span12">
							    <option value="0">Not Started</option>
							    <option value="1">In-Progress</option>
							    <option value="2">Completed</option>
							    <option value="3">Abandoned</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="margin-bottom:30px">
					<div class="span4 offset1">
						<input class="datepicker" placeholder="Choose Start Date" type="text">
					</div>
					<div class="span4 offset">
						<input class="datepicker" placeholder="Choose End Date" type="text">
					</div>
				</div>
				<div class="row" style="margin-bottom:30px">
					<div class="span7 offset1">
						<input type="text"  class="span12" placeholder="Progress" />
					</div>
					<div class="span3">
						<input type="submit" class="btn btn-primary btn-block" value="Submit"/>
					</div>
				</div>
				<table id="progTable" class="table table-striped table-bordered">
					<caption>Progress Log</caption>
					<thead>
					    <tr>
					      <th>Name</th>
					      <th>Start</th>
					      <th>End</th>
					      <th>Progress</th>
					    </tr>
				  	</thead>
					<tbody>
				    	<tr>
					      <td>...</td>
					      <td>...</td>
					      <td>...</td>
					      <td>...</td>
					    </tr>
					    <tr>
					      <td>...</td>
					      <td>...</td>
					      <td>...</td>
					      <td>...</td>
					    </tr>
					</tbody>
				</table>
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
</body>
</html>