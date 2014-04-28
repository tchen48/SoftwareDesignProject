//Admin Functions
function newDept() {  
    var depName = $('#depName').val();  
    if(depName.trim().length == 0){
    	var alertText = "Department name can't be blank";
    	addAlert("alert-error", alertText, "#createDeptAlert");
    	return;
    }
    	
    $.ajax({  
    	type : "Post",   
    	url : "newDept.html",   
    	data : "depName=" + depName,  
     	success : function(response) {
     		if(response == ""){
     			var alertText = "Department " + depName + " is already existed";
				$('#depName').val("");
	     		addAlert("alert-error", alertText, "#createDeptAlert");
     		}
     		else{
				$('#depList').empty();
	     		getDepList();
	     		var alertText = "Department " + depName + " is successfully created! Dept ID: " + response;
				$('#depName').val("");
	     		addAlert("alert-success", alertText, "#createDeptAlert");
     		}
     	},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		$('#depName').val("");
     		addAlert("alert-error", alertText,"#createDeptAlert");
     	}  
    });  
}  

function modifyDept() {  
    var oldName = $('#oldDepName').val();  
    var newName = $('#newDepName').val();
    if(oldName.trim().length == 0 || newName.trim().length == 0){
    	var alertText = "Department name can't be blank";
    	addAlert("alert-error", alertText, "#createDeptAlert");
    	return;
    }
    
    $.ajax({  
    	type : "Post",   
    	url : "modifyDept.html",   
    	data : "oldName=" + oldName + "&newName=" + newName,  
     	success : function(response) {
     		if(response == "0"){
     			var alertText = "Illegal department name!";
	     		addAlert("alert-error", alertText, "#createDeptAlert");
     		}
     		else{
				$('#depList').empty();
	     		getDepList();
	     		var alertText = "Department " + oldName + " is changed to " + newName;
	     		addAlert("alert-success", alertText, "#createDeptAlert");
     		}
     	},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#createDeptAlert");
     	}  
    });  
}  

function getDepList(){
	$.ajax({   
        type:'get',   
        url:"getDept.html",   
        dataType: 'json',   
        success:function(data){  
            $.each(data,function(i,list){ 
            	$('#depList')
                .append($("<option></option>")
                .attr("value",list.depId)
                .text(list.depName)); 
        	}) ;
    	} 
	}) ;
}

function unblock(){
	var userId = $("#unblockId").val();
	$.ajax({
		type: "Post",
		url: "unblockUser.html",
		data: "userId=" + userId, 
		success : function(response) {
			if(response == "1"){
	     		var alertText = "Unblock succeed";
	     		addAlert("alert-success", alertText, "#createDeptAlert");
			}
			else{
				var alertText = "Unblock failed";
	     		addAlert("alert-error", alertText, "#createDeptAlert");
			}
     	},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#createDeptAlert");
     	}  
	});
}

function newEmp(){
	var empName = $("#empName").val();
	var depList = $("#depList").val();
	var isManager = $("#isManager").is(':checked')?true:false;
	if(empName == ""){
    	var alertText = "Employee name can't be blank";
    	addAlert("alert-error", alertText, "#createEmpAlert");
    	return;
    }
	 $.ajax({
		 type:"Post",
		 url:"newEmp.html",
		 data:"empName="+empName+"&depList="+depList+"&isManager="+isManager,
		 success:function(response){
			 var alertText = "A new employee called "+empName+" is successfully created ! Emp ID is "+response;
			 $('#empName').val("");
			 $("#idLabel").text("ID: " + response);
			 addAlert("alert-success",alertText,"#createEmpAlert");
		 },
		 error:function(e){
			 var alertText = 'Error: ' + e;
			 $('#empName').val("");
	     	addAlert("alert-error", alertText,"#createEmpAlert");
		 }
	 });
	
}

function newPass(){
	var userId = $("#userIdSpan").text();
	var newPass = $("#newPass").val();
	var reNewPass = $("#reNewPass").val();
	var oldPass = $("#oldPass").val();
	alert(userId);
	if ( oldPass == "" || newPass == ""||reNewPass == "" ){
		addAlert("alert-error","Passwords cannot be blank!","#alertdiv");
		return;
	}
	if(reNewPass != newPass){
		addAlert("alert-error","The passwords you typed do not match! Please type again!","#alertdiv");
		return;
	}
	$.ajax({
		type:"Post",
		url:"newPass.html",
		data:"oldPass="+oldPass+"&newPass="+newPass+"&userId="+userId,
		success:function(response){
			
			$('#oldPass').val("");
			$('#newPass').val("");
			$('#reNewPass').val("");
			var alertText = response;
			if(response ==  "A new Password is created!")
				addAlert("alert-success",alertText,"#alertdiv");
			else
				addAlert("alert-error",alertText,"#alertdiv")
		},
		error:function( jqXHR, textStatus,errorThrown){
			
			$('#oldPass').val("");
			$('#newPass').val("");
			$('#reNewPass').val("");
			var alertText;
			if(errorThrown == "Internal Server Error")
				alertText = "Uncorrect password!";
			else
				alertText = "Unkown Error!";
			addAlert("alert-error",alertText,"#alertdiv");
		}
	});
}
//Dept Manager Functions
function newGroup() {  
    var groName = $('#groName').val();  
    var depId = $('#depIdSpan').text();
    if(groName.trim().length == 0){
    	var alertText = "Group name can't be blank";
    	addAlert("alert-error", alertText, "#alertdiv");
    	return;
    }
    	
    $.ajax({  
    	type : "Post",   
    	url : "newGroup.html",   
    	data : "groName=" + groName + "&depId=" + depId,  
     	success : function(response) {
     		if(response == ""){
     			var alertText = "Group " + groName + " is already existed in Department " + depId;
				$('#groName').val("");
	     		addAlert("alert-error", alertText, "#alertdiv");
     		}
     		else{
	     		var alertText = "Group " + groName + " is successfully created! Group ID: " + response;
				$('#groName').val("");
				$('#groList').empty();
				getDeptGro();
	     		addAlert("alert-success", alertText, "#alertdiv");
     		}
     	},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		$('#groName').val("");
     		addAlert("alert-error", alertText, "#alertdiv");
     	}  
    });  
}  

function modifyGroup() {  
    var oldName = $('#oldGroName').val();  
    var newName = $('#newGroName').val();
    var depId = $('#depIdSpan').text();
    if(oldName.trim().length == 0 || newName.trim().length == 0){
    	var alertText = "Group name can't be blank";
    	addAlert("alert-error", alertText, "#alertdiv");
    	return;
    }
    
    $.ajax({  
    	type : "Post",   
    	url : "modifyGroup.html",   
    	data : "oldName=" + oldName + "&newName=" + newName + "&depId=" + depId,  
     	success : function(response) {
     		if(response == "0"){
     			var alertText = "Illegal group name!";
	     		addAlert("alert-error", alertText, "#alertdiv");
     		}
     		else{
	     		var alertText = "Group " + oldName + " is changed to " + newName;
				$('#groList').empty();
	     		getDeptGro();
	     		addAlert("alert-success", alertText, "#alertdiv");
     		}
     	},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
     	}  
    });  
}

function getDeptEmp(){
	var depId = $('#depIdSpan').text();
	$.ajax({
		type : "Get",
		url : "getEmpList.html",
		data : "depId=" + depId +  "&groId=" + 0 + "&unassigned=" + 1,
		success : function(response){
			var json = $.parseJSON(response);
			addOptions(json, "#empList");
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
		}
	});
}

function getDeptGro(){
	var depId = $('#depIdSpan').text();
	$.ajax({
		type : "Get",
		url : "getDepGroList.html",
		data : "depId=" + depId,
		success : function(response){
			var json = $.parseJSON(response);
			addOptions(json, "#groList");
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
		}
	});
}

function assignEmp(){
	var userId = $('#empList').val();
	var userName = $('#empList option:selected').text();
	var groId = $('#groList').val();
	var groName = $('#groList option:selected').text();
	var isGroMng = $('#groMngBox').prop('checked');
	$.ajax({
		type : "Post",
		url : "assignEmp.html",
		data : "userId=" + userId + "&groId=" + groId + "&isGroMng=" + isGroMng,
		success : function(response) {
			if(response == '1'){
				$('#empList  option:selected').remove();
	     		var alertText = userName + " is assigned to " + groName;
	     		addAlert("alert-success", alertText, "#alertdiv");
			}
			else{
				var alertText = "Assignment failed";
	     		addAlert("alert-error", alertText, "#alertdiv");
			}
     	},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
     	}  
	});
}

//Group Manager Functions
function getGroEmp(){
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	$.ajax({
		type : "Get",
		url : "getEmpList.html",
		data : "depId=" + depId +  "&groId=" + groId + "&unassigned=" + 0,
		success : function(response){
			var json = $.parseJSON(response);
			addEmpTable(json, "#empTable");
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
		}
	});
}

function createProject(){
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	var objId = OBJ_PROJ;
	var data = [];
//	data.push({
//		"id" : 1,
//		"val" : $('#projectName').val()
//	});
//	data.push({
//		"id" : 2,
//		"val" : $('#description').val()
//	});
//	data.push({
//		"id" : 3,
//		"val" : $('#startdate').val()
//	});
//	data.push({
//		"id" : 4,
//		"val" : STATUS_NOT_START
//	});
	var id = 1;
	while($("#" + id).length > 0){
		data.push({
			"id" : id,
			"val" : $("#" + id).val()
		});
		id++;
		if(id == 4){
			data.push({
				"id" : 4,
				"val" : STATUS_NOT_START
			});
			id++;
		}
	}
	$.ajax({
		type : "Post",
		url : "createProject.html",
		data : "jsonArray=" + JSON.stringify(data) + "&depId=" + depId + "&groId=" + groId + "&objId=" + objId,
		success : function(response){
			var alertText = "Project " + $('#projectName').val() + " is successfully created! Project ID: " + response;
			addAlert("alert-success", alertText, "#alertdiv");
			id = 1;
			while($("#" + id).length > 0){
				$("#" + id).val("");
				id++;
				if(id == 4)
					id = 5;
			}
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
     		id = 1;
			while($("#" + id).length > 0){
				$("#" + id).val("");
				id++;
				if(id == 4)
					id = 5;
			}
		}
	});
}

function getGroProj(){
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	var objId = OBJ_PROJ;
	
	$.ajax({
		type : "Get",
		url : "getGroProj.html",
		data : "depId=" + depId + "&groId=" + groId + "&objId=" + objId,
		success : function(response){
			var json = $.parseJSON(response);
			addProjTable(json, "#projTable", depId, groId);
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
		}
	});
}

//General Functions
function addAlert(alertClass, alertText, alertID){
	$(alertID).empty();
	var newalert = document.createElement("div");
	newalert.className = "alert " + alertClass;
	newalert.innerHTML = alertText;
	$(alertID).append(newalert);
	
	if(alertClass == 'alert-error'){
		 $("html,body").animate({scrollTop: $(alertID).offset().top}, 500);
	}
	
	$(newalert).fadeIn(1500).delay(2000).fadeOut(1500);
}


function addOptions(json, selectId){
	for(var i = 0; i < json.length; i++){
		$(selectId).append('<option value="' + json[i].id + '">' + json[i].name + '</option>');
	}
}

function addEmpTable(json, tableId){
	var table = tableId + " tbody";
	for(var i = 0; i < json.length; i++){
		var tr = $("<tr></tr>").appendTo(table);
		tr.append("<td>" + json[i].name + "</td>");
	}
}

function addProjTable(json, tableId, depId, groId){
	var table = tableId + " tbody";
	for(var i = 0; i < json.length; i++){
		var tr = $("<tr></tr>").appendTo(table);
		tr.append("<td>" + json[i].id + "</td>");
		
		var nameLink = $("<a>" + json[i].name + "</a>");
		nameLink.attr("href", "project/" + depId + "/" + groId + "/" + json[i].id + ".html");
		/*
		 *  this is another way of sending the project information(just a GET method). 
		 *  this way won't change the relative path.
		 *  it will map to "/project" instead of "/project/{depId}/{groId}/{rowId}"
		 *  
		 * */
		//nameLink.attr("href", "project.html?depId=" + depId + "&groId=" + groId + "&rowId=" + json[i].id);
		var nameTd = $("<td></td>").html(nameLink);
		tr.append(nameTd);
		tr.append("<td>" + json[i].startDate + "</td>");
		tr.append("<td>" + json[i].status + "</td>");
		tr.append("<td>" + json[i].description + "</td>");
	}
	$("#" + tableId + "");
}

//Customization Function
function addField(level){
	var depId = $('#depIdSpan').text();
	var groId;
	if(level == "dept")
		groId = 0;
	else
		groId = $('#groIdSpan').text();
	var objId = 0;
	var fieldName = $('#fieldName').val();
	var fieldType = $("#typeList").val();
	$.ajax({
		type : "Post",
		url : "addField.html",
		data : "depId=" + depId +  "&groId=" + groId  +  "&objId=" + objId + "&fieldName=" + fieldName + "&fieldType=" + fieldType,
		success : function(response){
			$('#fieldName').val("");
			if(response == "1"){
				alertText = "Field " + fieldName + " is added successfully!";
				addAlert("alert-success", alertText, "#alertdiv");
			}
			else{
				alertText = "Field " + fieldName + " is failed to be added!";
				addAlert("alert-error", alertText, "#alertdiv");
			}
		},
		error : function(e){
			$('#fieldName').val("");
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
		}
	});
}

function getCustomizedField(type){
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	var objId;
	if(type == PROJ_FIELD)
		objId = OBJ_PROJ;
	else
		objId = OBJ_DETAIL;
	$.ajax({
		type : "Get",
		url : "getCustomizedField.html",
		data : "depId=" + depId +  "&groId=" + groId  +  "&objId=" + objId,
		success : function(response){
			if(type == PROJ_FIELD){
				var json = $.parseJSON(response);
				addCustomizedField(json, NEW_PROJ_LOCATION);
			}
			else{
				alertText = "Field " + fieldName + " is failed to be added!";
				addAlert("alert-error", alertText, "#alertdiv");
			}
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
		}
	});
}

function addCustomizedField(json, location){
	for(var i = 0; i < json.length; i++){
		var name = json[i].name;
		var id = json[i].id;
		var type;
		if(json[i].type == TYPE_INTEGER)
			type = "number";
		else
			type = "text";
		
		var newDiv = $("<div><input type='text'></div>").insertAfter($(location));
		newDiv.attr("id","customdiv" + i);
		newDiv.addClass("customdiv");
		var inputSelector = "#" + newDiv.attr("id") + " input";
		$(inputSelector).addClass("span6");
		if(json[i].type == TYPE_DATE){
			$(inputSelector).attr("type", "text");
			$(inputSelector).addClass("datepicker");
			$(inputSelector).datepicker();
			name = "Manually input " + name + "(MM/DD/YYYY)";
		}
		else
			$(inputSelector).attr("type", type);
		$(inputSelector).attr({
			placeholder: name,
			id: id
		});
		location = "#" + newDiv.attr("id");
	}
}

function addStatus(){
	var projId = $('#projIdSpan').text();
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	var objId = "0";
	var userType = $('#userTypeSpan').text();
	$.ajax({
		type : "Get",
		url : "getStatus.html",
		data : "depId=" + depId +  "&groId=" + groId  +  "&objId=" + objId + "&projId=" + projId,
		success : function(response){
			var status = parseInt(response);
			if(userType == USER_GRO){
				alert(STATUS.length);
				select = $('<select></select>');
				select.attr('id', 'statusList');
				for(var i = 0; i < STATUS.length; i++){
					select.append('<option value="' + i + '">' + STATUS[i] + '</option>');
				}
				$('#statusListDiv').append(select);
				$('#statusList').val(status).attr('selected', true);
			}
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
		}
	});
}

//Global Variables used for customized field
var PROJ_FIELD = "proj";
var DETAIL_FIELD = "detail";

var NEW_PROJ_LOCATION = "#3";

var OBJ_PROJ = 0;
var OBJ_DETAIL = 1;

var TYPE_INTEGER = 0;
var TYPE_STRING = 1;
var TYPE_DATE = 2;

var STATUS_NOT_START = 0;
var STATUS_IN_PROGRESS = 1;
var STATUS_COMPLETED = 2;
var STATUS_ABANDONED = 2;

var STATUS = ["Not Start", "In Progress", "Completed", "Abandoned"];


var USER_ADMIN = "Admin";
var USER_DEPT = "Dept Manager";
var USER_GRO = "Group Manager";
var USER_EMP = "Employee";

