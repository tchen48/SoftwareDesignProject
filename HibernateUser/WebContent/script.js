/**
 * 
 */

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
//		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){			
//			return true;
//		}
//		else{
//			alert("Invalid DOB!");
//			return false;
//		}
	}
}