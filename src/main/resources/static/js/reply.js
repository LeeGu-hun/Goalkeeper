var toggle = true;
function openReply(){
	if(toggle){
	 	$('#replyBox').attr('style', 'display:block');
	 	toggle = false;	
	} else{
		$('#replyBox').attr('style', 'display:none');
		toggle = true;
	}
}
var reToggle = true;
function openRecmt(rno){
	if(reToggle){
	 	$('#recmtPost'+rno).attr('style', 'display:block');
	 	reToggle = false;	
	} else{
		$('#recmtPost'+rno).attr('style', 'display:none');
		reToggle = true;
	}
}
var count = 1;
$('#replyBtn').click(function(){
	var params = $('#replyFrm').serialize();
	$.ajax({
		url : '/group_reply',
		type : 'POST',
		cache: false,
	    data: params,
	    datatype : 'text',
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
	    success: function(data) {
	    	var cloneReply = $('#ajaxAddReply').clone();
	    	cloneReply.attr('id', 'ajaxAddReply'+count);
	    	if(data.profileCheck==1){
	    		cloneReply.find('#ajaxReplyProfile').replaceWith('<figure><img class="hexagon-image-30-32" id="ajaxReplyProfile" src="/user/profileId/'+data.replyWriter+'"></figure>');
	    	} else{
		    	cloneReply.find('#ajaxReplyProfile').replaceWith('<div class="hexagon-image-30-32" id="ajaxReplyProfile" data-src="../../img/user_baseProfile.png"></div>');
	    	}	    	
	    	var date = new Date(data.replyDate);
	    	var month = date.getMonth();
	    	if(month <10){
	    		month = '0' + month;
	    	}
	    	cloneReply.find('#ajaxReplyId').html(data.replyWriter);
	    	cloneReply.find('#ajaxReplyContent').html(data.replyContent);
	    	cloneReply.find('#ajaxReplyDate').html(date.getFullYear() + '-' + month + '-' + date.getDate() + ' ' + date.getHours() + '-' + date.getMinutes());
	    	cloneReply.attr('style', 'display:block; margin-left:40px;');
	    	$('#ajaxAddReply').before(cloneReply);
	    	count ++;
	    	
	    },
	    error: function(request, status, error){
   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
});
function react(no, id){
	var param = {"bno" : no, "userId" : id};
	var reactDiv = $('#react'+no);
	$.ajax({
	    url: "/react",
	    type: "POST",
	    cache: false,
	    data: JSON.stringify(param),
		contentType:'application/json; charset=utf-8',
	    success: function(data) {
	    	if(data.reactCount == 0){
	    		reactDiv.find('p').html('');
	    		$('#reactStartDiv'+no).attr('style', 'display:none');
	    		$('#userDiv'+no).find('#user'+id).remove();
	    	} else{
	    		$('#reactStartDiv'+no).attr('style', 'display:flex');
	    		reactDiv.find('p').html(data.reactCount);
	    		if(data.reactType==1){
	    			$('#userDiv'+no).append('<p class="simple-dropdown-text" id="user' + id + '">' + id + '</p>');
	    		} else if(data.reactType==-1){
	    			$('#user'+id).remove();
	    		}
	    	}
		},
		error: function(request, status, error){
   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}
var reCount = 1;
function recmtBtn(rno){
	var params = $('#recmtFrm'+rno).serialize();
	$.ajax({
		url : '/group_recmt',
		type : 'POST',
		cache: false,
	    data: params,
	    datatype : 'text',
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
	    success: function(data) {
	    	var cloneRecmt = $('#ajaxAddRecmt').clone();
	    	cloneRecmt.attr('id', 'ajaxAddRecmt'+reCount);
	    	if(data.profileCheck==1){
	    		cloneRecmt.find('#ajaxRecmtProfile').replaceWith('<figure><img class="hexagon-image-30-32" id="ajaxReplyProfile" src="/user/profileId/'+data.recmtWriter+'"></figure>');
	    	} else{
		    	cloneRecmt.find('#ajaxRecmtProfile').replaceWith('<div class="hexagon-image-30-32" id="ajaxReplyProfile" data-src="../../img/user_baseProfile.png"></div>');
	    	}	    	
	    	var date = new Date(data.recmtDate);
	    	var month = date.getMonth();
	    	if(month <10){
	    		month = '0' + month;
	    	}
	    	cloneRecmt.find('#ajaxRecmtId').html(data.recmtWriter);
	    	cloneRecmt.find('#ajaxRecmtContent').html(data.recmtContent);
	    	cloneRecmt.find('#ajaxRecmtDate').html(date.getFullYear() + '-' + month + '-' + date.getDate() + ' ' + date.getHours() + '-' + date.getMinutes());
	    	cloneRecmt.attr('style', 'display:block;');
	    	$('#recmtPost'+data.rno).before(cloneRecmt);
	    	reCount ++;
	    	
	    },
	    error: function(request, status, error){
   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}