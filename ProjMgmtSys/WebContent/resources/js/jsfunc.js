function newDept() {  
    var depName = $('#depName').val();  
    $.ajax({  
    	type : "Post",   
    	url : "newDept.html",   
    	data : "depName=" + depName,  
     	success : function(response) {
     		var alertText = "Department " + depName + " is successfully created! Dept ID: " + response;
     		addAlert("alert-success", alertText);
     		},  
     	error : function(e) { 
     		var alertText = 'Error: ' + e;
     		addAlert("alert-error", alertText);
     	}  
    });  
}  

function addAlert(alertClass, alertText){
	$("#alertdiv").empty();
	var newalert = document.createElement("div");
	newalert.className = "alert " + alertClass;
	newalert.innerHTML = alertText;
	$("#alertdiv").append(newalert);
}