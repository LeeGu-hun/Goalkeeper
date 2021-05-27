window.onload = function() {
	$('#acceptUser').click(function(e){
		e.preventDefault();
		openModal("가입이 완료되었습니다.");
		if('#confirmBtn').click(function(){
			$('#joinFrm').submit();
		});
	});
	$('#cancelBtn').click(function(e){
		e.preventDefault();
		openModal("취소가 완료되었습니다.");
		$('#joinFrm').attr('action','/join_delete');
		$('#joinFrm').submit();
	});
}