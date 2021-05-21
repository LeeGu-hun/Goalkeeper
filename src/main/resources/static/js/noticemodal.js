function openModal(content){
	$("#noticemodal").fadeIn(300);
	$("#modalcontent").fadeIn(300);
	$('#modaltext').text(content);
	$("#noticemodal, .modalclose, #confirmbtn").on('click',function(){
		  $("#noticemodal").fadeOut(300);
		  $(".modal-con").fadeOut(300);
	});
}