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
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="resources/js/jsfunc.js"></script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<h1>Group Manager Home</h1>
			<h3>Hello <%=session.getAttribute("userName")%>&nbsp;(id: <%=session.getAttribute("userId")%>)</h3>	
			<h5>Dept: <%=session.getAttribute("depName")%>&nbsp;(id: <span id="depIdSpan"><%=session.getAttribute("depId")%></span>)
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Group: <%=session.getAttribute("groName")%>&nbsp;(id: <span id="groIdSpan"><%=session.getAttribute("groId")%></span>)</h5>	
			
			<div class="span12 alertdiv" style="margin-top:60px; height:50px">					
			</div>
			
			<div class="subdiv span12">
				<div class="row">
					<div class="span4 offset1">
						<input type="text"  class="span12" placeholder="Enter the name of the new field"  required="true" />
					</div>
					<div class="span3">
						<select id="typeList" class="form-control span12">
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
			<div class="span12">
				<div class="span3 offset8" style="margin-bottom:20px; margin-top:-40px;">
					<!--  <input type="submit" class="btn btn-large btn-primary btn-block" value="New Project"/>-->
					<a href="newProject.html" class="btn btn-large btn-primary btn-block">New Project</a>
				</div>
			</div>
			<div class="span12">
				<div class="row" style="margin-left: -100px; margin-right:-100px;">
					<div class="span2 subdiv">
						<div class="span12">
							<table id="empTable" class="table table-striped">
								<thead>
								    <tr>
								      <th>Group Members</th>
								    </tr>
							  	</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="span10 subdiv">
						<table id="projTable" class="table table-striped">
							<thead>
							    <tr>
							      <th>Project ID</th>
							      <th>Name</th>
							      <th>Start</th>
							      <th>End</th>
							      <th>Status</th>
							      <th>Description</th>
							    </tr>
						  	</thead>
							<tbody>
						    	<tr>
							      <td>...</td>
							      <td>...</td>
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
							      <td>...</td>
							      <td>...</td>
							    </tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		getGroEmp();
	</script>
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
</body>
</html>