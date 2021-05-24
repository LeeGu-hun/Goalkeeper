function getChart(){
	if($('#goal_name option:selected').text()=="선택해주세요"){
		openModal("목표명을 선택해주세요.");
		return false;
	}
	var param = $('#statFrm').serialize();
	$.ajax({
		url : '/group_stat',
		type : 'POST',
		cache: false,
	    data: param,
	    datatype : 'text',
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
	    success: function(data) {
		
		},
		error: function(request, status, error){
   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}