window.onload = function() {
	 function moreContent(id, cnt){
    	
    	var list_length = $("#"+id+" tr").length-1; //tr갯수 구하기 , 1을 빼는 이유는 제목,작성자 tr이 하나 존재하기 때문.
    	var aname = id+"_btn";
    	var callLength = list_length;
    	
    	$('#startCount').val(callLength);
    	$('#viewCount').val(cnt);
    	
   	    $.ajax({
   	        type    :   "post",
   	        url     :   "/getMoreContents_ajax.do",
   	        data    :   $('#searchTxtForm').serialize(),
   	        dataType:   "json",
   	        success :   function(result) {
   	                       if(result.resultCnt > 0){
   	                    	   var list = result.resultList;
   	                    		   if(resultVO.title != '') {
   	                    			 $('#'+aname).attr('href',"javascript:moreContent('"+id+"', "+cnt+");");
   	                    			   getMoreList(list);
   	                    		   }else{
   	                    			$("#"+id+"_div").remove();
   	                    		   }
   	                    	   }
   	                       }else{
   	                       }
   	                    },
   	        error   :   function(request,status,error){
   	                    alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
   	                    }
   	    });
   	    
   	    function getMoreList(list){
   	    	
   	    	var content = "";
   	    	var length = list.length;
   	    	for(i=0; i<list.length; i++){
   	    		var resultVO = list[i];
   	    		if(resultVO.title != ''){
	   	    		content += "<tr>";
	   	   	    	content += "<td>"+resultVO.title+"</td>";
	   	   	    	content += "<td>"+resultVO.reg_date+"</td>";
	   	 	    	content += "</tr>";
   	    		}
   	    	}
   	    	 $("#more_list tr:last").after(content); 
             // id가 more_list 인 tr의 마지막에 content 값을 추가함
   	    }

</script>
}
