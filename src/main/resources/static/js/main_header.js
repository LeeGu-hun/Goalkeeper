

$(document).ready(function(){

	//로그인 페이지 모달창 
	document.getElementById("modal_opne_btn").onclick = function() {
	    document.getElementById("modal").style.display="block";
	}
	//로그인창 닫기
	document.getElementById("modal_close_btn").onclick = function() {
	    document.getElementById("modal").style.display="none";
	}   
	//회원가입 모달창
	document.getElementById("joinButton").onclick = function() {
		document.getElementById("modal").style.display="none";
	    document.getElementById("joinModal").style.display="block";
	}
	//회원가입창 로그인 페이지로 돌아가기
	document.getElementById("backButton").onclick = function() {
	    document.getElementById("joinModal").style.display="none";
		 document.getElementById("modal").style.display="block";
	}
	//회원가입창 닫기
	document.getElementById("joinmodal_close_btn").onclick = function() {
	    document.getElementById("joinModal").style.display="none";
	}
	//새글 쓰기 모달창 띄우기
	document.getElementById("plus").onclick = function() {
	    document.getElementById("newPostModal").style.display="block";
	}
	//새글 쓰기창 닫기
	document.getElementById("newPostModal_close_btn").onclick = function() {
	    document.getElementById("newPostModal").style.display="none";
	} 
	function loginChk() {
		if (UserId.value) {
		    alert("아이디를 입력해 주십시오.");
		    form.user_id.focus();
		    return;
		}
	}
	$("input[type=submit]").click(function(e){
		checking(e);
	});
	
	function checking(e){
		$('.pilsu').each(function(i,item){
			if($(item).val().trim()==''){
				alert($(item).attr('id')+' 확인!');
				$(item).focus();
				e.preventDefault();
				return false;
			} else {
				if(i==0) {
					return koChecking($(item),e);
				} else if(i==1) {
					return enChecking($(item),e);
				}else if(i==2){
					return pwCheck();
				}else if(i==3){
					return phChecking(node,e);
				}
				}
			});
	}
	function getDate() {
		var newDate = new Date();
		var yyyy = newDate.getFullYear();
		var mm = newDate.getMonth() + 1;
		if (mm < 10) mm = "0" + mm;
		var dd = newDate.getDate();
		if (dd < 10) dd = "0" + dd;
		var toDay = yyyy + "-" + mm + "-" + dd;
		document.getElementById("saleDate").value = toDay;
	}
	
	function pwCheck(){
		if($('input[placeholder=Pass]').val()!=$('input[placeholder=RePass]').val()){
			alert("패스워드가 같지 않습니다.");
			$('input[placeholder=Pass]').val('');
			$('input[placeholder=RePass]').val('');
			$('input[placeholder=Pass]').focus();
			e.preventDefault();
		}
	}
	function koChecking(node,e){
		if(node.val().replace(/[가-힣]/g,'').length!=0){
			alert('한글이름을 입력해주세요');
			node.focus();
			e.preventDefault();
			return false;
		}
	}
	function enChecking(node,e){
		if(node.val().replace(/[a-zA-Z0-9]/g,'').length!=0){
			alert('영어로 된 ID를 입력해주세요');
			node.focus();
			e.preventDefault();
			return false;
		}
	}
	
	var input1 = document.getElementById('m1');
	var input2 = document.getElementById('m2');
	var numCheck = RegExp(/[^0-9]$/g);
	
	if(numCheck.test(input1.value)){
			input1.value = input1.value.replace(/[^0-9]$/g,'');
	}
	if(input1.value.length>=4){
			input2.focus();
	}
		
		input1.onkeydown = function(){
			if(numCheck.test(input1.value)){
			input1.value = input1.value.replace(/[^0-9]$/g,'');
		}
	}
	if(numCheck.test(input2.value)){
			input2.value = input2.value.replace(/[^0-9]$/g,'');
	}
		
	input2.onkeydown = function(){
		if(numCheck.test(input2.value)){
		input2.value = input2.value.replace(/[^0-9]$/g,'');
		}
	}
	e.preventDefault();
	return false;

});	

$(document).ready(function(){
		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd',
			prevText : '이전 달',
			nextText : '다음 달',
			monthNames : ['1월','2월','3월','4월','5월','6월',
				'7월','8월','9월','10월','11월','12월'],
			monthNamesShort : ['1월','2월','3월','4월','5월','6월',
				'7월','8월','9월','10월','11월','12월'],
			dayNames : ['일','월','화','수','목','금','토' ],
			dayNamesShort : ['일','월','화','수','목','금','토' ],
			dayNamesMin : ['일','월','화','수','목','금','토' ],
			showMonthAfterYear : true,
			yearSuffix : '년'
		});
		getDate();
	});
