function openJoin(result){
	$('.modal-con').attr('style','min-height:24%');
	if(result == "joinDenied"){
		openModal("이미 가입된 그룹입니다.");
		return false;
	} else if(result =="loginRequire"){
		openModal("로그인이 필요합니다");
		return false;
	} else if(result == "alreadyApply"){ 
		openModal("이미 가입신청을 했습니다.");
		return false;
	} else{
		var popupWidth = 1215;
		var popupHeight = 391;
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		window.open('../../group_join', '_blank', 'height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
	}
};
function openMgJoin(result){
	if(result=="joinDinied"){
		openCautionModal("권한이 없습니다.");
		return false;
	}
	$('#goManage').submit();
}