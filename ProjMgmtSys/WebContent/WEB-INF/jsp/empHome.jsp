<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home Page</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/mainstyle.css" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="resources/js/jsfunc.js"></script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<div id="content">
				<h1>Staff Home</h1>
				<h3>Hello <%=session.getAttribute("userName")%>&nbsp;(id: <span id="userId"><%=session.getAttribute("userId")%></span>)</h3>		
				<h5><%=session.getAttribute("depName")%>&nbsp;(id: <%=session.getAttribute("depId")%>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("groName")%>&nbsp;(id: <%=session.getAttribute("groId")%>)</h5>
				
				</br>
	            <div id="newPassAlert" class="span12 alertdiv" style="margin-top:-20px; height:50px; position:absolute; opacity: .90;z-index:10;  ">					
				</div>
				</br>
				<div class=" span12">
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
					<div class="subdiv span12" style="margin-left:0">
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
							      <td>1</td>
							      <td><a href="project.html" class="btn btn-link">Project1</a></td>
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