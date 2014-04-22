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
	var userId = $("#userId").text();
	var newPass = $("#newPass").val();
	var reNewPass = $("#reNewPass").val();
	var oldPass = $("#oldPass").val();
	if ( oldPass == "" || newPass == ""||reNewPass == "" ){
		addAlert("alert-error","Passwords cannot be blank!","#newPassAlert");
		return;
	}
	if(reNewPass != newPass){
		addAlert("alert-error","The passwords you typed do not match! Please type again!","#newPassAlert");
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
			addAlert("alert-success",alertText,"#newPassAlert");
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
			addAlert("alert-error",alertText,"#newPassAlert");
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
			addTable(json, "#empTable");
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
	$(newalert).fadeIn(1000).delay(2000).fadeOut(1500);
}


function addOptions(json, selectId){
	for(var i = 0; i < json.length; i++){
		$(selectId).append('<option value="' + json[i].id + '">' + json[i].name + '</option>');
	}
}

function addTable(json, tableId){
	var table = tableId + " tbody";
	for(var i = 0; i < json.length; i++){
		var tr = $("<tr></tr>").appendTo(table);
		tr.append("<td>" + json[i].name + "</td>");
//		.append('<option value="' + json[i].id + '">' + json[i].name + '</option>');
	}
}

//Generate Department Option
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

