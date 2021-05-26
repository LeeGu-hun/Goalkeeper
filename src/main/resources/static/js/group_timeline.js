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
			openModal("5장 이하의 사진을 올려주세요");
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
		openModal('파일 사이즈 초과');
		$("input[type='file']").val(""); //파일 초기화
		return false;
	}
	if (regex.test(fileName)) {
		openModal('업로드 불가능한 파일이 있습니다.');
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
function goInsert(){
	if (document.getElementById("text").value == "" && 
		document.getElementById("file").value == "") {
		openCautionModal("아무것도 입력되지 않았습니다.");
	return false;
	} 
	if(!$('#file').val()){
		$('#text').append("<input type='hidden' name='fileCheck' value='true'>");
	} else {
		$('#text').append("<input type='hidden' name='fileCheck' value='false'>");
	}
	$("#noticemodal").fadeIn(300);
		$("#modalcontent").fadeIn(300);
		$("#modaltext").text("저장되었습니다.");
		
		$("#noticemodal, .modalclose, #confirmbtn").on('click',function(){
		$("#noticemodal").fadeOut(300);
		$(".modal-con").fadeOut(300);
		$("#groupWrite").submit();
	}); 
}
function fnEdit(bno){	
	$('#'+bno).hide();
	$('#modify'+bno).show();	
	$('.edit_btn').click(function(e){
		e.preventDefault();
		$('#modify_form').submit();
		openModal("수정완료");
	});	
	$(".edit_cancelbtn").click(function() {
		$('#'+bno).show();
		$('#modify'+bno).hide();
	});		
}
function addData(){
	var rtn = true;
	if($('#selectGoalName option:selected').text()=="선택해주세요"){
		openCautionModal("목표 이름을 선택해주세요.");
		return false;
	}else if($('#dataCnt').val()==""){
		openCautionModal("달성 수치를 적어주세요.");
		$('#dataCnt').focus();
		return false;
	} 
	var selVal = $('#selectGoalName option:selected').val();
	var param = $('#dataFrm').serialize();
	$.ajax({
		url : '/group_oneGoal',
		type : 'POST',
		cache: false,
	    data: param,
	    datatype : 'text',
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
	    success: function(data) {
	    	addResult(data, selVal,rtn);
	    },
	    error: function(request, status, error){
   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	
}

function addResult(res, selVal, rtn) {
	if(res.dno == selVal){
		openCautionModal("이미 오늘 입력했습니다.");
	} else{
		openGoalModal("저장되었습니다.");
		$('#submitBtn').click(function(){
			$(".noticemodal").fadeOut(300);
			$("#modalcontent").fadeOut(300);
			$('#dataFrm').submit();
		});
	}
}
function openGoalModal(content){
	$('#modalcontent').find('#confirmbtn').attr('id','submitBtn');
	$(".noticemodal").fadeIn(300);
	$("#modalcontent").fadeIn(300);
	$('#modaltext').text(content);
}
function photo(){
	$("#photoBtn").click(function() {
		$(".backmodal").fadeIn();
		$(".modal").fadeIn();
	});
}
function saveImage(){
	$("#savePhoto").click(function() {
		$(".backmodal").fadeOut();
		$(".modal").fadeOut();
		document.getElementById("pic").setAttribute("value",
				$('div[name=picdiv]').length + " 장의 사진");
	});
}