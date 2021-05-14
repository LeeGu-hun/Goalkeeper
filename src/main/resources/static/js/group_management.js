window.onload = function() {
	$('button[type="submit"]').click(function(e)(
		e.preventDefault();
		alert("가입이 완료되었습니다.);
		$('#joinFrm').submit();
	});
}