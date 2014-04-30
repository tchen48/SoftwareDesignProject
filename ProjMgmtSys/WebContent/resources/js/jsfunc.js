//Admin Functions
function newDept() {  
    var depName = $('#depName').val();  
    if(depName.trim().length == 0){
    	var alertText = "Department name can't be blank";
    	addAlert("alert-error", alertText, "#createDeptAlert");
    	return;
    }
    if(!DEP_REG.test(depName)){
    	var alertText = "Format of the department name is incorrect! Please try again";
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
    if(!DEP_REG.test(oldName)||!DEP_REG.test(newName)){
    	var alertText = "Format of the department name is incorrect! Please try again";
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
	if(!ID_REG.test(userId)){
    	var alertText = "Format of the id is incorrect! It contains only number!";
    	addAlert("alert-error", alertText, "#createDeptAlert");
    	return;
    }
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
	if(!EMP_REG.test(empName)){
    	var alertText = "Format of the name is incorrect! Please try again!";
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
	if ( oldPass == "" || newPass == ""||reNewPass == "" ){
		addAlert("alert-error","Passwords cannot be blank!","#alertdiv");
		return;
	}
	if(reNewPass != newPass){
		addAlert("alert-error","The passwords you typed do not match! Please type again!","#alertdiv");
		return;
	}
	if(!PASS_REG.test(newPass)){
    	var alertText = "The password does not satisfy the minimum requriement! Please try again!";
    	addAlert("alert-error", alertText, "#alertdiv");
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
    if(!GRO_REG.test(groName)){
    	var alertText = "The format of the group name is incorrect! Please try again!";
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
    if(!GRO_REG.test(newName)){
    	var alertText = "The format of the group name is incorrect! Please try again!";
    	addAlert("alert-error", alertText,  "#alertdiv");
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
    if(!PROJ_REG.test($("#1").val())){
    	var alertText = "The format of the project name is incorrect! Please try again!";
    	addAlert("alert-error", alertText, "#alertdiv");
    	return;
    }
    if(!DATE_REG.test($("#3").val())){
    	var alertText = "The format of the date is incorrect! Please try again!";
    	addAlert("alert-error", alertText, "#alertdiv");
    	return;
    }
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	var objId = OBJ_PROJ;
	var data = [];
	var id = 1;
	$('#field').children('div').each(function () {
		var field = $(this).children(":first");
		var id = field.attr('id');
		data.push({
			"id" : id,
			"val" : field.val()
		});
		if(id == 3){
			data.push({
				"id" : 4,
				"val" : STATUS_NOT_START
			});
		}
	});
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

function newDetail(){
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	var objId = OBJ_DETAIL;
	var projId = $('#projIdSpan').text();
	var userName = $('#userNameSpan').text();
	var status = "";
	var data = [];
	
	if($('#statusList').length > 0)
		status = $('#statusList').val();
	
	data.push({
		"id" : "5",
		"val" : userName
	});
	var id = 6;
	while($('#' + id).length > 0){
		data.push({
			"id" : id,
			"val" : $("#" + id).val()
		});
		id++;
	};
	data.push({
		"id" : "9",
		"val" : projId
	});
	alert("jsonArray=" + JSON.stringify(data) + "&depId=" + depId + "&groId=" + groId + "&objId=" + objId + "&projId=" + projId + "&status=" + status);
	$.ajax({
		type : "Post",
		url : "newDetail.html",
		data : "jsonArray=" + JSON.stringify(data) + "&depId=" + depId + "&groId=" + groId + "&objId=" + objId + "&projId=" + projId + "&status=" + status,
		success : function(response){
			var alertText = "A new progress is successfully created!";
			addAlert("alert-success", alertText, "#alertdiv");
			var newTr = $("<tr></tr>").prependTo($('#progTable tbody'));
			
			newTr.append("<td>" + ($("#progTable tbody tr").length) +"</td>");
			newTr.append("<td>" + userName +"</td>");
			newTr.append("<td>" + $('#6').val() +"</td>");
			newTr.append("<td>" + $('#7').val() +"</td>");
			newTr.append("<td>" + $('#8').val() +"</td>");
			$("input").val("");
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
     		$("input").val("");
		}
	});
}

function getDetails(){
	var depId = $('#depIdSpan').text();
	var groId = $('#groIdSpan').text();
	var objId = OBJ_DETAIL;
	var projId = $('#projIdSpan').text();
	
	$.ajax({
		type : "Get",
		url : "getDetails.html",
		data : "depId=" + depId + "&groId=" + groId + "&objId=" + objId + "&projId=" + projId,
		success : function(response){
			var json = $.parseJSON(response);
			json.sort(predicatBy('progId'));
			var table = $('#progTable tbody');
			alert(JSON.stringify(json));
			for(var i = 0; i < json.length; i++){
				var tr = $("<tr></tr>").appendTo(table);
				tr.append("<td>" + (json.length - i) + "</td>");
				tr.append("<td>" + json[i].userName + "</td>");
				tr.append("<td>" + json[i].startDate + "</td>");
				tr.append("<td>" + json[i].endDate + "</td>");
				tr.append("<td>" + json[i].progress + "</td>");
				
				/*
				 *  this is another way of sending the project information(just a GET method). 
				 *  this way won't change the relative path.
				 *  it will map to "/project" instead of "/project/{depId}/{groId}/{rowId}"
				 *  
				 * */
				//nameLink.attr("href", "project.html?depId=" + depId + "&groId=" + groId + "&rowId=" + json[i].id);
			}
		},
		error : function(e){
			var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText, "#alertdiv");
     		$("input").val("");
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
		//nameLink.attr("href", "project/" + depId + "/" + groId + "/" + json[i].id + ".html");
		/*
		 *  this is another way of sending the project information(just a GET method). 
		 *  this way won't change the relative path.
		 *  it will map to "/project" instead of "/project/{depId}/{groId}/{rowId}"
		 *  
		 * */
		nameLink.attr("href", "project.html?depId=" + depId + "&groId=" + groId + "&rowId=" + json[i].id);
		var nameTd = $("<td></td>").html(nameLink);
		tr.append(nameTd);
		tr.append("<td>" + json[i].startDate + "</td>");
		tr.append("<td>" + json[i].status + "</td>");
		tr.append("<td>" + json[i].description + "</td>");
	}
	//$("#" + tableId + "");
}

//Sort the JSON array
function predicatBy(prop){
   return function(a,b){
      if( a[prop] > b[prop]){
          return -1;
      }else if( a[prop] < b[prop] ){
          return 1;
      }
      return 0;
   }
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
    if(!FIELD_REG.test(fieldName)){
    	var alertText = "Format of the field name is incorrect! Please try again";
    	addAlert("alert-error", alertText, "#alertdiv");
    	return;
    }
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

function getCustomizedField(type,extra){
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
				if(extra == "input")
					addCustomizedField(json, NEW_PROJ_LOCATION);
				else{
					var list = new Array();
					for(var i = 0; i < json.length; i++){
						list.push(json[i].id);
					}
					var jsonArray = JSON.stringify(list);
					var detail = {
							depId: depId,
							groId:groId,
							objId:objId,
							projId:extra
					};
					$.ajax({   
				        type:'get',   
				        url:"getData.html", 
				        data : "depId=" + detail.depId +  "&groId=" + detail.groId  +  "&objId=" + detail.objId + "&projId=" + detail.projId+"&fieldIds="+jsonArray, 
				        success:function(data){  
				        	addCustomizedFieldAsLable(json, NEW_PROJ_LOCATION, data);
				    	} 
					}) ;
					
				}
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
function addCustomizedFieldAsLable(json, location,data){
	var fieldData = $.parseJSON(data);
	alert(fieldData);
	for(var i = 0; i < json.length; i++){
		var name = json[i].name;
		var id = json[i].id;
		var newDiv = $("<div><div></div></div>").insertAfter($(location));
		newDiv.attr("id","customdiv" + i);
		newDiv.addClass("customdiv");
		newDiv.addClass("row");
		var lableSelector = "#" + newDiv.attr("id") + " div";
		$(lableSelector).addClass("span=1");
		$(lableSelector).append("<label class='label label-info'>"+name+"</label>");
		$('<div  class="span4"><div>'+fieldData[i]+"</div></div>").insertAfter($(lableSelector));
	}
}
function addCustomizedField(json, location){
	for(var i = 0; i < json.length; i++){
		var name = json[i].name;
		var id = json[i].id;
		var type;
		if(json[i].type == TYPE_NUMBER)
			type = "number";
		else
			type = "text";
		var newDiv = $("<div><input type='text'></div>").insertAfter($(location));
		newDiv.attr("id","customdiv" + i);
		newDiv.addClass("customdiv");
		var inputSelector = "#" + newDiv.attr("id") + " input";
		$(inputSelector).addClass("span6");
		if(json[i].type == TYPE_DATE){
			$(".datepicker").datepicker("destroy");
			$(inputSelector).attr("type", "text");
			$(inputSelector).addClass("datepicker");

			name = "Manually input " + name + "(MM/DD/YYYY)";
		}
		else{
			$(inputSelector).attr("type", type);
			if(json[i].type == TYPE_NUMBER){
				$(inputSelector).attr("step", "0.01");
			}
		}
		$(inputSelector).attr({
			placeholder: name,
			id: id
		});
		/*
		 * this line and .datepicker("destroy") is nessary, or dynamimcally 
		 * generated type datepicker cannot beused 
		 */
		if(json[i].type == TYPE_DATE){
			$(".datepicker").datepicker();
		}
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
				select = $('<select></select>');
				select.attr('id', 'statusList');
				for(var i = 0; i < STATUS.length; i++){
					select.append('<option value="' + i + '">' + STATUS[i] + '</option>');
				}
				$('#statusListDiv').append(select);
				$('#statusList').val(status).attr('selected', true);
			}
			else{
				$statusLabel = $('#statusListDiv').append($('<label></label>'));
				$statusLabel.text(STATUS[status]);
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

/* if you insert after #3, the level relation will be uncorrect) 
 */
var NEW_PROJ_LOCATION = "#originaldiv";

var OBJ_PROJ = 0;
var OBJ_DETAIL = 1;

var TYPE_NUMBER = 0;
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

//regular expressions
var ID_REG = /^\d+$/;
var PASS_REG =/ */;
var EMP_REG = /^[a-zA-Z]+( [a-zA-Z]+)*$/;
var DEP_REG = /^[0-9a-zA-Z]+([-_ @#%][0-9a-zA-Z]+)*([+]{0,3})$/;
var GRO_REG = /^[0-9a-zA-Z]+([-_ @#%][0-9a-zA-Z]+)*([+]{0,3})$/;
var PROJ_REG = /^[0-9a-zA-Z]+([-_ @#%][0-9a-zA-Z]+)*([+]{0,3})$/;
var FIELD_REG =/^[0-9a-zA-Z]+([ +_&\-][0-9a-zA-Z]+){0,4}$/;
var DATE_REG = /^(1[0-2]|0[1-9])\/(0[1-9]|[1-2][0-9]|3[01])\/20[0-9][0-9]$/;

