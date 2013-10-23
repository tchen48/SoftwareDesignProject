/**
 * 
 */
//var script = document.createElement('script');
//script.src = 'jquery-2.0.3.min.js';
//script.type = 'text/javascript';
//document.getElementsByTagName('head')[0].appendChild(script);

function checkDate(){
	var year = document.getElementById("dobYear").value;
	var day = document.getElementById("dobDay").value;
	var month = document.getElementById("dobMonth").value;
		
	var smallMonth = [4,6,9,11];
	if((month in smallMonth && day == 31) || (month == 2 && day > 29)){
		alert("Invalid DOB!");
		return false;
	}
	if(month == 2 && day == 29){
		if(year % 4 != 0 || year % 400 != 0){
			alert("Invalid DOB!");
			return false;
		}
	}
}

//function getAcctno() {
//	var strID = $('#strID').html();
//    $.ajax({  
//    	type : "Get",   
//    	url : "profilesetting/acctno.html",   
//    	data : "strID=" + strID,  
//    	success : function(response) {
//    		var obj = jQuery.parseJSON(response);    		
//    		$("#acctnoField").html(obj.acctno);
//    	},  
//    	error : function(e) {  
//    		alert('Error: ' + e);   
//    	}  
//    });  
//}  
//
//function getEmail() {
//	var strID = $('#strID').html();
//    $.ajax({  
//    	type : "Get",   
//    	url : "profilesetting/email.html",   
//    	data : "strID=" + strID,  
//    	success : function(response) {
//    		var obj = jQuery.parseJSON(response);    		
//    		$("#emailField").html(obj.email);
//    	},  
//    	error : function(e) {  
//    		alert('Error: ' + e);   
//    	}  
//    });  
//}  
//
//function getAddress() {
//	var strID = $('#strID').html();
//    $.ajax({  
//    	type : "Get",   
//    	url : "profilesetting/address.html",   
//    	data : "strID=" + strID,  
//    	success : function(response) {
//    		var obj = jQuery.parseJSON(response);    		
//    		$("#addressField").html(obj.address);
//    	},  
//    	error : function(e) {  
//    		alert('Error: ' + e);   
//    	}  
//    });  
//}  
//
//function getPhone() {
//	var strID = $('#strID').html();
//    $.ajax({  
//    	type : "Get",   
//    	url : "profilesetting/phone.html",   
//    	data : "strID=" + strID,  
//    	success : function(response) {
//    		var obj = jQuery.parseJSON(response);    		
//    		$("#phoneField").html(obj.phone);
//    	},  
//    	error : function(e) {  
//    		alert('Error: ' + e);   
//    	}  
//    });  
//}  
//function getProfile(item) {
//	var strID = $('#strID').html();
//	alert(item),
//    $.ajax({  
//    	type : "Get",   
//    	url : "profilesetting/" + item + ".html",   
//    	data : "strID=" + strID,  
//    	success : function(response) {
//    		var obj = jQuery.parseJSON(response);    		
//    		alert(obj.result);   
//    	},  
//    	error : function(e) {  
//    		alert('Error: ' + e);   
//    	}  
//    });  
//   }  