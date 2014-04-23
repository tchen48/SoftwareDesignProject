<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Department Manager Home Page</title>
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/mainstyle.css" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="resources/js/jsfunc.js"></script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<h1>Department Manager Home</h1>
			<h3>Hello <%=session.getAttribute("userName")%>&nbsp;(id: <span id="userId"><%=session.getAttribute("userId")%></span>)</h3>		
			<h5>Dept: <%=session.getAttribute("depName")%>&nbsp;(id: <span id="depIdSpan"><%=session.getAttribute("depId")%></span>)</h5>
			<div id="alertdiv" class="span12" style="margin-top:60px; height:50px">					
			</div>
			
			<div class="subdiv span12">
				<div class="row">
					<div class="span7 offset1">
						<input type="text" id="groName" class="span12" placeholder="Enter the name of the new group"  required="true" />
					</div>
					<div id="btndiv" class="span3">
						<button class="btn btn-primary btn-block" onclick="newGroup();"/>Create Group</button>
					</div>
				</div>
			</div>
			
			<div class="subdiv span12">
				<div class="row">
					<div class="span3 offset1">
						<input type="text" id="oldGroName" class="span12" placeholder="Enter the group name"  required="true" />
					</div>
					<div class="span3">
						<input type="text" id="newGroName" class="span12" placeholder="Enter the new group name"  required="true" />
					</div>
					<div id="btndiv" class="span3 offset1">
						<button class="btn btn-primary btn-block" onclick="modifyGroup();">Modify Group</button>
					</div>
				</div>
			</div>
			
			<div class="subdiv span12">
				<div class="row">
					<div class="span4 offset1">
						<input id="fieldName" type="text"  class="span12" placeholder="Enter the name of the new field"  required="true" />
					</div>
					<div class="span3">
						<select id="typeList" class="form-control span12">
						    <option value="0">Integer</option>
						    <option value="1">String</option>
						    <option value="2">Date</option>
						</select>
					</div>
					<div id="btndiv" class="span3">
						<button class="btn btn-primary btn-block" onclick="addField('dept');">Add</button>
					</div>
				</div>
			</div>
			
			<div class="subdiv span12">
				<div class="row">
					<div class="span2 offset1">
						<select id="empList" class="form-control span12">
						</select>
					</div>
					<div class="span1 labelcenter">
						<label>Assign to</label>
					</div>
				
					<div class="span3">
						<select id="groList" class="form-control span12">
						</select>
					</div>
					<div class="span2">
						<div class="span10 offset2 labelcenter">
							<label class="checkbox" style="margin:auto auto">
								<input id="groMngBox" type="checkbox">
								Group Leader?
							</label>
						</div>
					</div>
					<div class="span2">
						<button class="btn btn-primary btn-block" onclick="assignEmp();">Assign Employee</button>
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
	<script>
		getDeptEmp();
		getDeptGro();
	</script>
</body>
</html>