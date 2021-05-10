$( document ).ready(function() {
	$('#groupSearchBtn').click(function(){
		if($('#checkFail').val == 'fail'){
		alert('검색 결과가 없습니다.');
		}	
	});
});