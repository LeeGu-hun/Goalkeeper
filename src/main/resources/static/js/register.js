window.onload = function() {
	$('#doSignUp').click(function(e){	   
	    var userid = $("#u_id").val();
	    var userpwd = $("#u_password").val();
	    var inputPwdCfm = $("#re_password").val();
	    var email = $("#u_mail").val();
	    var birthday = $("#u_birthdate").val();
	    var phoneNumber = $("#u_number").val();
	  
	    if($('#u_name').val() == ""){
	        alert("이름을 입력해주세요");
	        $('#u_name').focus();
	        return false;
	    }else if(userid == ""){
	        alert("아이디를 입력해 주세요"); 
	        $("#u_id").focus();
	        return false;
	    }else if(userpwd == ""){
	        alert("비밀번호를 입력해 주세요"); 
	        $("#u_password").focus();
	        return false;
	    }else if(userpwd != inputPwdCfm){
	        alert("비밀번호가 서로 다릅니다. 비밀번호를 확인해 주세요."); 
	        $("#re_password").focus();
	        return false; 
	    }else if(email == ""){
	        alert("이메일을 입력해주세요");
	        $("#u_mail").focus();
	        return false;
	    }else if(birthday == ""){
	        alert("생년월일을 입력해주세여");
	        $("#u_birthdate").focus();
	        return false;
	    }else if(phoneNumber == ""){
	        alert("전화번호를 입력해주세요");
	        $("#u_number").focus();
	        return false;
	    }	   
	    this.submit(); 
	});
	
	
});
}