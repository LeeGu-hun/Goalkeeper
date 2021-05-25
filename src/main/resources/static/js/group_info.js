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
function getDataList(){
	if($('#dataOption option:selected').text()=="선택해주세요"){
		return false;
	}
	var param = $('#dataFrm').serialize();
	$.ajax({
	    url: "/group_getData",
	    type: "POST",
	    cache: false,
	    data: param,
	    datatype : 'text',
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
	    success: function(data) {
	    	if(data==""){
	    		openModal("데이터가 없습니다.");
	    	}
	    	$.each(data, function(index, value){
	    		var dataBox = $('#ajaxDataBox').clone();
	    		dataBox.find('#ajaxDataId').html(value.userId);
	    		dataBox.find('#ajaxDataName').html(value.goal_name);
	    		dataBox.find('#ajaxDataCnt').html(value.data_cnt);
	    		var regdate = new Date(value.data_regdate);
	    		dataBox.find('#ajaxDataDate').html(regdate.getFullYear() + "/" + (regdate.getMonth()+1) + "/" +(regdate.getDate()));
	    		dataBox.attr('style', 'display:flex;');
	    		$('#ajaxDataContainer').append(dataBox);
	    		
	    	});
	    	
	    	
		},
		error: function(request, status, error){
   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}