window.onload = function() {
	$('#logOutFriendPage').click(function(e){
	 alert("로그인 후 이용해주세요!");
	   
	});
	
	function openDetailModal(){
		alert("모달 오픈");
		$("#noticemodal").fadeIn(300);
		$("#noticemodal, .modalclose, #confirmbtn").on('click',function(){
		$("#noticemodal").fadeOut(300);
		$(".modal-con").fadeOut(300);
		});
		
	};
	

}
