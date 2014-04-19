function newDept() {  
    var depName = $('#depName').val();  
    alert(depName);
    $.ajax({  
    	type : "Post",   
    	url : "newDept.html",   
    	data : "depName=" + depName,  
     	success : function(response) {  
     		addAlert()
      		alert(response);   
     		},  
     	error : function(e) {  
      		alert('Error: ' + e);   
     	}  
    });  
}  

function addAlert(){
	
}