function openModal(content){
	$('.modal-con').attr('style','min-height:24%');
	$("#noticemodal").fadeIn(300);
	$("#modalcontent").fadeIn(300);
	$('#modaltext').text(content);
	$("#noticemodal, .modalclose, #confirmbtn").on('click',function(){
		  $("#noticemodal").fadeOut(300);
		  $("#modalcontent").fadeOut(300);
	});
}