window.onload = function() {
	$('#doModify').click(function(e){	 
		
	  
	    if($('#userMail') == ""){
	        alert("이메일을 입력해주세요");
	        $("#userMail").focus();
	        return false;
	    }else if('#userDirthdate' == ""){
	        alert("생년월일을 입력해주세여");
	        $("#userBirthdate").focus();
	        return false;
	    }else if($('#userPhone') == ""){
	        alert("전화번호를 입력해주세요");
	        $("#userPhone").focus();
	        return false;
	    }
	    alert("회원 정보 수정이 완료되었습니다.");
	    $(this).submit();
	});
	
}
