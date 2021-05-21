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
function openJoin(result){
	$('.modal-con').attr('style','min-height:24%');
	if(result == "joinDenied"){
		openModal("이미 가입된 그룹입니다.");
	} else if(result =="loginRequire"){
		openModal("로그인이 필요합니다");
	} else{
		var popupWidth = 1215;
		var popupHeight = 391;
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		window.open('../../group_join', '_blank', 'height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
	}
};
