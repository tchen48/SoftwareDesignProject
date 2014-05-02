<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Group Manager Home Page</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mainstyle.css" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jsfunc.js"></script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<h1>Group Manager Home</h1>
			<h3>Hello <%=session.getAttribute("userName")%>&nbsp;(id: <span id="userIdSpan"><%=session.getAttribute("userId")%></span>)&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn" href="logout.html">Log Out</a></h3>	
			<h5>Dept: <%=session.getAttribute("depName")%>&nbsp;(id: <span id="depIdSpan"><%=session.getAttribute("depId")%></span>)
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Group: <%=session.getAttribute("groName")%>&nbsp;(id: <span id="groIdSpan"><%=session.getAttribute("groId")%></span>)</h5>	
			
			<div id="alertdiv" class="span12" style="margin-top:60px; height:50px">					
			</div>
			
			<div class="subdiv span12">
				<div class="row">
					<div class="span4 offset1">
						<input id="fieldName" type="text"  class="span12" placeholder="Enter the name of the new field"  required="true" />
					</div>
					<div class="span3">
						<select id="typeList" class="form-control span12">
						    <option value="0">Number</option>
						    <option value="1">String</option>
						    <option value="2">Date</option>
						</select>
					</div>
					<div id="btndiv" class="span3">
						<button class="btn btn-primary btn-block" onclick="addField('gro');">Add</button>
					</div>
				</div>
			</div>
			<div class="subdiv span12">
				<div class="span3" style="margin-left: 50px">
					<input type="password"  id = "oldPass" class="span12" placeholder="Original Password"  required="true" />
				</div>
				<div class="span3">
					<input type="password"  id="newPass" class="span12" placeholder="New Password"  required="true" />
				</div>
				<div class="span3">
					<input type="password"  id="reNewPass" class="span12" placeholder="Confirm"  required="true" />
				</div>
				<div class="span2">
					<input type="submit" onClick = "newPass()" class="btn btn-warning btn-block" value="Change Password"/>
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
							      <th>Status</th>
							      <th>Description</th>
							    </tr>
						  	</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		getGroEmp();
		getGroProj();
	</script>
</body>
</html>