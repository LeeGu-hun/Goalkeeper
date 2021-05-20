window.onload = function() {
	$('#btnInsert').click(function(e) {
		e.preventDefault();
		
		if(!$('#file').val()){
			$('#text').append("<input type='hidden' name='fileCheck' value='true'>");
		} else {
			$('#text').append("<input type='hidden' name='fileCheck' value='false'>");
		}
		alert("등록되었습니다.");
		$('#groupWrite').submit();
	})
	
	
	$(function() {
		$("#photo").click(function() {
			$(".backmodal").fadeIn();
			$(".modal").fadeIn();
		});
	})
	$(function() {
		$("#photobtn").click(function() {
			$(".backmodal").fadeOut();
			$(".modal").fadeOut();
			document.getElementById("pic").setAttribute("value",
					$('div[name=picdiv]').length + " 장의 사진");
		});
	})

	function CheckEnter(frm, objName) {
		var keycode = event.keyCode;
		var i = 0;

		if (keycode == 13) {
			for (i = 0; i < frm.length; ++i) {
				if (objName.id == frm[i].id)
					break;
			}
			frm[++i].focus();
		}
	}

	$("input[type='file']").change(function(e) {
			if($('#file')[0].files.length > 5) {
				alert("5장 이하의 사진을 올려주세요");
				return false;
			}
		//div 내용 비워주기
		$('#imgsrc').empty();

		var files = e.target.files;
		var arr = Array.prototype.slice.call(files);
		//업로드 가능 파일인지 체크
		for (var i = 0; i < files.length; i++) {
			if (!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
		}
		preview(arr);
		$("#imgdiv").show();
	});//file change
	function checkExtension(fileName, fileSize) {
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 20971520; //20MB

		if (fileSize >= maxSize) {
			alert('파일 사이즈 초과');
			$("input[type='file']").val(""); //파일 초기화
			return false;
		}
		if (regex.test(fileName)) {
			alert('업로드 불가능한 파일이 있습니다.');
			$("input[type='file']").val(""); //파일 초기화
			return false;
		}
		return true;
	}

	function preview(arr) {
		arr.forEach(function(f) {
					//파일명이 길면 파일명...으로 처리
					var fileName = f.name;
					if (fileName.length > 10) {
						fileName = fileName.substring(0, 7) + "...";
					}
					//div에 이미지 추가
					var str = '<div style="display: inline-flex; padding: 10px;"  name="picdiv">';

					//이미지 파일 미리보기
					if (f.type.match('image.*')) {
						var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
						reader.onload = function(e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
							str += '<img src="'+e.target.result+'" title="'+f.name+  '" width=100 height=100 />';
							$(str).appendTo('#imgsrc');
						}
						reader.readAsDataURL(f);
					} else {
						str += '<img src="/resources/img/fileImg.png" title="'+f.name+'" width=100 height=100 />';
						$(str).appendTo('#imgsrc');
					}
				});//arr.forEach
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
}
function fnEdit(bno){	
		$('#'+bno).hide();
		$('#modify'+bno).show();	
		$('.edit_btn').click(function(e){
			e.preventDefault();
			$('#modify_form').submit();
			alert("수정완료");
		});	
		$(".edit_cancelbtn").click(function() {
			$('#'+bno).show();
			$('#modify'+bno).hide();
		});		
	}
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
	    	reactDiv.find('p').remove();
	    	if(data.reactCount == 0){
	    		reactDiv.append('<p class="meta-line-text"></p>');
	    		$('#reactStartDiv').attr('style', 'display:none');
	    		$('#userDiv'+no).find('p').remove();
	    	} else{
	    		$('#reactStartDiv').attr('style', 'display:block');
	    		reactDiv.append('<p class="meta-line-text">'+data.reactCount+'</p>');
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

