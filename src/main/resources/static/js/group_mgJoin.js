window.onload = function() {
	$('#acceptUser').click(function(e){
		e.preventDefault();
		openModal("가입이 완료되었습니다.");
		$('#joinFrm').submit();
	});
}