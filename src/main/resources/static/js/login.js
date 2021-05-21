window.onload = function() {
	function checkLogin(frm){
		var param = frm.serialize();
		$.ajax({
			url : '/login_check',
			type : 'POST',
			cache : false,
			data : param,
			datatype : 'text',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		    success: function(data) {
		    	if(data==null){
		    		openModal("아이디 혹은 비밀번호가 틀렸습니다.");
		    	} else{
		    		frm.submit();
		    	}
		    },
		    error: function(request, status, error){
	   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	$('#doLogin').click(function(e){
		e.preventDefault();	 
	    if($('#userId1').val() == ""){
	        openModal("아이디를 입력해주세요");
	        $('#userId1').focus();
	        return false;
	    } 
	    if($('#userPw1').val() == ""){
	        openModal("비밀번호를 입력해주세요"); 
	        $('#userPw1').focus();
	        return false;
	    }
	    var param = $('#loginFrm');
	    checkLogin(param);
	});
	
}
