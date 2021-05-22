function openModal(content){

	$(".noticemodal").fadeIn(300);
	$("#modalcontent").fadeIn(300);
	$('#modaltext').text(content);
	$(".noticemodal, .modalclose, #confirmbtn").on('click',function(){
		  $(".noticemodal").fadeOut(300);
		  $("#modalcontent").fadeOut(300);
	});
}

function openCautionModal(content){

	$(".noticemodal").fadeIn(300);
	$("#warningmodalcontent").fadeIn(300);
	$('#warningmodaltext').text(content);
	$(".noticemodal, .modalclose, #confirmbtn").on('click',function(){
		  $(".noticemodal").fadeOut(300);
		  $("#warningmodalcontent").fadeOut(300);
	});
}