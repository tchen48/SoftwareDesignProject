function newDept() {  
    var depName = $('#depName').val();  
    $.ajax({  
    	type : "Post",   
    	url : "newDept.html",   
    	data : "depName=" + depName,  
     	success : function(response) {
     		var alertText = "Department " + depName + " is successfully created! Dept ID: " + response;
     		$('#depName').val("");
     		addAlert("alert-success", alertText,"#createDeptAlert");
     		},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		$('#depName').val("");
     		addAlert("alert-error", alertText,"#createDeptAlert");
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
			 $('#empName').val("");
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
function addAlert(alertClass, alertText,alertID){
	$(alertID).empty();
	var newalert = document.createElement("div");
	newalert.className = "alert " + alertClass;
	newalert.innerHTML = alertText;
	$(alertID).append(newalert);
	$(alertID).fadeIn(1000).delay(2000).fadeOut(1500);
}