window.onload = function() {
	$('#saveImage').click(function(){
		var proCheck = $('#group_profile').val();
		var bgiCheck = $('#group_bgi').val();
		if(proCheck && bgiCheck){
			alert("수정할 파일을 업로드해주세요.");
			return false;
		}
		if(proCheck){
			$('#modify_profile').submit();
		}
		if(bgiCheck){
			$('#modify_bgi').submit();
		}
		alert("저장이 완료되었습니다.");
	});
}