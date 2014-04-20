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
			 addAlert("alert-success",alertText,"#createEmpAlert");
		 },
		 error:function(e){
			 var alertText = 'Error: ' + e;
	     		addAlert("alert-error", alertText,"#createEmpAlert");
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