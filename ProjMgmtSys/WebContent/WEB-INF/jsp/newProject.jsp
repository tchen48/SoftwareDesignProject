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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jsfunc.js"></script>
	<script>
		$(function() {
	   		$( ".datepicker" ).datepicker();
	  	});
	</script>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<h1>New Project</h1>
			<h3><%=session.getAttribute("userName")%>&nbsp;(id: <span id="userIdSpan"><%=session.getAttribute("userId")%></span>)</h3>		
			<h5>Dept: <%=session.getAttribute("depName")%>&nbsp;(id: <span id="depIdSpan"><%=session.getAttribute("depId")%></span>)
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Group: <%=session.getAttribute("groName")%>&nbsp;(id: <span id="groIdSpan"><%=session.getAttribute("groId")%></span>)</h5>
			
			<div id="alertdiv" class="span12 " style="margin-top:60px; height:50px">					
			</div>
			
			<div class="subdiv span12">
				<div class="content span8 offset2">
				<div id = field>
				<div>
					<input type="text"  class="span12" placeholder="Enter the name of the new project" required="true" id="1" />
				</div>
				<div id = originaldiv>
					<input class="span6 datepicker" placeholder="Choose Start Date" type="text" id="3" />
				</div>
				<script>
					getCustomizedField("proj","input");
				</script>

				<div>
					<textarea class="input-block-level" placeholder="Enter project description" rows="5" id="2"></textarea>
				</div>
				</div>
				<div class="span12" style="margin-bottom: 30px;">
					<div class="span3 offset4">
						<button class="btn btn-primary btn-block" onclick="createProject();">Create</button>
					</div>
					<div class="span3 offset1">
					<a href="groMngHome.html" class="btn btn-block">Cancel</a>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>