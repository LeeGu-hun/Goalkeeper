window.onload = function() {
	$('#insertReplyBtn').click(function(e){	 
		
	   if($('#replyInsert').val() == ""){
	        alert("댓글을 입력해주세요");
	        $('#replyInsert').focus();
	        return false;
	    }
	    
	    function commentInsert(insertData){
	    $.ajax({
	        url : '/comment/insert',
	        type : 'post',
	        data : insertData,
	        success : function(data){
	            if(data == 1) {
	                commentList(); //댓글 작성 후 댓글 목록 reload
	                $('[name=replyContent]').val('');
	            }
	        }
	    });
	});
	
}
