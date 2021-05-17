window.onload = function() {
	$('#addGoal').click(function(e){
		e.preventDefault();
		var name = $('#goal_name');
		var cnt = $('#goal_allcnt');
		var date = $('#goal_enddate');
		if(name.val()==""){
			alert("목표이름을 정해주세요.");
			return false;
		}
		if(cnt.val() == ""){
			alert("목표횟수를 정해주세요.");
			return false;
		}
		if(date.val() == ""){
			alert("달성날짜를 정해주세요.");
			return false;
		}
		alert("추가가 완료되었습니다.");
		$('#addFrm').submit();
	});
}