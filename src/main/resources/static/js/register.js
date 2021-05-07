window.onload = function() {
	$('#doSignUp').click(function(e){	 
	    var userid = $("#userId").val();
	    var userpwd = $("#userPw").val();
	    var inputPwdCfm = $("#re_password").val();
	    var email = $("#userMail").val();
	    var birthday = $("#userBirthdate").val();
	    var phoneNumber = $("#userPhone").val();
	  
	    if($('#userName').val() == ""){
	        alert("이름을 입력해주세요");
	        $('#u_name').focus();
	        return false;
	    }else if(userid == ""){
	        alert("아이디를 입력해 주세요"); 
	        $("#userId").focus();
	        return false;
	    }else if(userpwd == ""){
	        alert("비밀번호를 입력해 주세요"); 
	        $("#userPw").focus();
	        return false;
	    }else if(userpwd != inputPwdCfm){
	        alert("비밀번호가 서로 다릅니다. 비밀번호를 확인해 주세요."); 
	        $("#re_password").focus();
	        return false; 
	    }else if(email == ""){
	        alert("이메일을 입력해주세요");
	        $("#userMail").focus();
	        return false;
	    }else if(birthday == ""){
	        alert("생년월일을 입력해주세여");
	        $("#userBirthdate").focus();
	        return false;
	    }else if(phoneNumber == ""){
	        alert("전화번호를 입력해주세요");
	        $("#userPhone").focus();
	        return false;
	    }	   
	    alert($('#u_name').val() + "님 환영합니다!");
	    $(this).submit(); 
	});
	$('#birth_check').click(function(){
		var birthDate = $('#type_birth').val();
		var splitDate = birthDate.split("-");
		if(birthDate == "" || splitDate.length !=3 || splitDate[1].length !=2 || splitDate[2].length !=2 || splitDate[0].length !=4){
			alert("맞는 양식을 입력하세요");
			return false;
		}
		$("#userBirthdate").attr('value', birthDate);
	});
}
