window.onload = function() {
	$('#doLogin').click(function(e){	 
		
	  
	    if($('#userId1').val() == ""){
	        alert("아이디를 입력해주세요");
	        $('#userId1').focus();
	        return false;
	    }
	    else if($('#userPw1').val() == ""){
	        alert("비밀번호를 입력해주세요"); 
	        $('#userPw1').focus();
	        return false;
	    }
	    this.submit(); 
	});
	
}
