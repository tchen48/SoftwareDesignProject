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
	 $.ajax({
		 type:"Post",
		 url:"newEmp.html",
		 data:"empName="+empName+"&depList="+depList+"&isManager="+isManager,
		 success:function(response){
			 var alertText = "A new employee called "+empName+" is successfully created ! Emp ID is "+response;
			 $("#idLabel").text("ID: " + response);
			 addAlert("alert-success",alertText,"#createEmpAlert");
		 },
		 error:function(e){
			 var alertText = 'Error: ' + e;
	     	 addAlert("alert-error", alertText,"#createEmpAlert");
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

//General Funcations
function addAlert(alertClass, alertText, alertID){
	$(alertID).empty();
	var newalert = document.createElement("div");
	newalert.className = "alert " + alertClass;
	newalert.innerHTML = alertText;
	$(alertID).append(newalert);
	$(newalert).fadeIn(1000).delay(2000).fadeOut(1500);
}