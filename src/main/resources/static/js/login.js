window.onload = function() {
	$('#doLogin').click(function(e){	 
		
	  
	    if($('#u_id').val() == ""){
	        alert("아이디를 입력해주세요");
	        $('#u_id').focus();
	        return false;
	    }
	    else if($('#u_password').val() == ""){
	        alert("비밀번호를 입력해주세요"); 
	        $('#u_password').focus();
	        return false;
	    }
	    this.submit(); 
	});
	
}
