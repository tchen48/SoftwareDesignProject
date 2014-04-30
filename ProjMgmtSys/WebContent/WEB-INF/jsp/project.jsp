<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Project Page</title>
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
			<h1>Project</h1>
			<h3><span id="userNameSpan"><%=session.getAttribute("userName")%></span>&nbsp;(id: <span id="userIdSpan"><%=session.getAttribute("userId")%></span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="userTypeSpan"><%=session.getAttribute("userType")%></span>)</h3>		
			<h5>Dept: <%=session.getAttribute("depName")%>&nbsp;(id: <span id="depIdSpan"><%=session.getAttribute("depId")%></span>)
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Group: <%=session.getAttribute("groName")%>&nbsp;(id: <span id="groIdSpan"><%=session.getAttribute("groId")%></span>)</h5>
			<h5>Project: ${ProjName}&nbsp;(id: <span id="projIdSpan">${ProjId}</span>)</h5>
			
			<div class="span12" id="alertdiv" style="margin-top:60px; height:50px">					
			</div>
			
			<div class="span12 subdiv">
				<div class="span10 offset1" style="margin-bottom:30px">
					<div class="row" id = "originaldiv">
						<div class="span1">
							<label class="label label-info">Status</label>
						</div>
						<div class="span4 offset1" id="statusListDiv">
						</div>
					</div>
				</div>
				<script>
					getCustomizedField("proj",$('#projIdSpan').text());
				</script>
				<div class="row" style="margin-bottom:30px">
					<div class="span4 offset1">
						<input id="6" class="datepicker" placeholder="Choose Start Date" type="text">
					</div>
					<div class="span4">
						<input id="7" class="datepicker" placeholder="Choose End Date" type="text">
					</div>
				</div>
				<div class="row" style="margin-bottom:30px">
					<div class="span7 offset1">
						<input id="8" type="text"  class="span12" placeholder="Progress" />
					</div>
					<div class="span3">
						<button class="btn btn-primary btn-block" onclick="newDetail();">Submit</button>
					</div>
				</div>
				<div class="span10 offset1">
					<table id="progTable" class="table table-striped table-bordered">
						<caption>Progress Log</caption>
						<thead>
						    <tr>
						      <th>Progress ID</th>	
						      <th>Name</th>
						      <th>Start</th>
						      <th>End</th>
						      <th>Progress</th>
						    </tr>
					  	</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- <script>getCustomizedData()</script>-->
	<script>
		addStatus();
		getDetails();
	</script>
</body>
</html>