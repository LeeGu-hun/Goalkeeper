window.onload = function() {
	$('#doSignUp').click(function(e){	
		e.preventDefault();
	    var userid = $("#userId").val();
	    var userpwd = $("#userPw").val();
	    var inputPwdCfm = $("#re_password").val();
	    var email = $("#userMail").val();
	    var birthday = $("#userBirthdate").val();
	    var phoneNumber = $("#userPhone").val();
	  
	    if(userid == ""){
	        openModal("아이디를 입력해 주세요"); 
	        $("#userId").focus();
	        return false;
	    }else if($('#userName').val() == ""){
	        openModal("이름을 입력해주세요");
	        $('#u_name').focus();
	        return false;
	    }else if(userpwd == ""){
	        openModal("비밀번호를 입력해 주세요"); 
	        $("#userPw").focus();
	        return false;
	    }else if(userpwd != inputPwdCfm){
	        openModal("비밀번호가 서로 다릅니다. 비밀번호를 확인해 주세요."); 
	        $("#re_password").focus();
	        return false; 
	    }else if(email == ""){
	        openModal("이메일을 입력해주세요");
	        $("#userMail").focus();
	        return false;
	    }else if(birthday == ""){
	        openModal("생년월일을 입력해주세여");
	        $("#userBirthdate").focus();
	        return false;
	    }else if(phoneNumber == ""){
	        openModal("전화번호를 입력해주세요");
	        $("#userPhone").focus();
	        return false;
	    }
	    openModal($('#userId').val() + "님 환영합니다!");
  		$('#confirmbtn').click(function(){
  			$("#noticemodal").fadeOut(300);
			$("#modalcontent").fadeOut(300);
			$('#registerFrm').submit();
  		});
  		
	});
	$('#birth_check').click(function(){
		var birthDate = $('#type_birth').val();
		var splitDate = birthDate.split("-");
		if(birthDate == "" || splitDate.length !=3 || splitDate[1].length !=2 || splitDate[2].length !=2 || splitDate[0].length !=4){
			openModal("맞는 양식을 입력하세요");
			return false;
		}
		$("#userBirthdate").attr('value', birthDate);
	});
	$('#userId').on("propertychange change keyup paste input", function(){
	console.log("keyup 테스트");	
		$.ajax({
			url : "/memberIdChk",
			type : "post",
			dataType : "json",
			data : {"userId" : $("#userId").val()},
			success : function(data){
				if(data == 1){
					$('.id_input_re_2').css("display","inline-block");
					$('.id_input_re_1').css("display", "none");	
					$("#doSignUp").attr("disabled", "disabled");
				}else if(data == 0){
					$("#doSignUp").removeAttr("disabled");
					$('.id_input_re_1').css("display","inline-block");
					$('.id_input_re_2').css("display", "none");	
				}
			}
		})
	});
	
}
$(document).ready(function(){
       $("#userPw1").keypress(function (e) {
        if (e.which == 13){
             check();  
        }
    });
});

function openModal(content){
	$('.modal-con').attr('style','min-height:24%');
	$("#noticemodal").fadeIn(300);
	$("#modalcontent").fadeIn(300);
	$('#modaltext').text(content);
	$("#noticemodal, .modalclose, #confirmbtn").on('click',function(){
		  $("#noticemodal").fadeOut(300);
		  $(".modal-con").fadeOut(300);
	});
}
function openWarningModal(content){
	$('.modal-con').attr('style','min-height:24%');
	$("#noticemodal").fadeIn(300);
	$("#warningmodalcontent").fadeIn(300);
	$('#warningmodaltext').text(content);
	$("#noticemodal, .modalclose, #confirmbtn").on('click',function(){
		  $("#noticemodal").fadeOut(300);
		  $(".modal-con").fadeOut(300);
	});
}
var rtn = true;
function check(){
    if($('#userId1').val() == ""){
        openModal("아이디를 입력해주세요")
        $('#userId1').focus();
        return false;
    } 
    if($('#userPw1').val() == ""){
        openModal("비밀번호를 입력해주세요"); 
        $('#userPw1').focus();
        return false;
    }
    var param = $('#loginFrm').serialize();
	$.ajax({
		url : '/login_check',
		type : 'POST',
		cache : false,
		data : param,
		datatype : 'json',
	    success: function(data) {
	    	if(data.userMail == "fail"){
	    		openWarningModal("회원 정보가 맞지 않습니다");
	    		return false;
	    	} else{
   				$('#loginFrm').submit();
   			}
	    },
	    error: function(request, status, error){
   			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function getCheck(){
	
}
