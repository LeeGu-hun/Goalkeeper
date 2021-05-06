window.onload = function() {
	$('#insertReplyBtn').click(function(e){	 
		
	    if(th:text="${userInfo}"=null){
	        alert("로그인 후, 이용해주세요.");
	        return false;
	    }else if($('#replyInsert').val() == ""){
	        alert("댓글을 입력해주세요");
	        $('#replyInsert').focus();
	        return false;
	    }
	    
	    this.submit(); 
	});
	
}
