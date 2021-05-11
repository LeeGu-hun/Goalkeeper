window.onload = function() {
	$('#img_border').hide();
	var change = false;
	function removeInput(){
		if(change){
			$('#input_self').remove();
			$('#self_button').remove();
			change = true;
		}
	}
	$('#goal_down').change(function(){
		var self = $('#goal_down').val();
		if(self == "2week"){
			removeInput();
			var date = getDate(0, 0, 14); 
			$('#g_goaldate').val(date);
		} else if(self == "1month"){
			removeInput();
			var date = getDate(0, 1, 0);
			$('#g_goaldate').val(date);
		} else if(self == "3month"){
			removeInput();
			var date = getDate(0, 3, 0);
			$('#g_goaldate').val(date);
		} else if(self == "6month"){
			removeInput();
			var date = getDate(0, 6, 0);
			$('#g_goaldate').val(date);
		} else if(self == "1year"){
			removeInput();
			var date = getDate(1, 0, 0);
			$('#g_goaldate').val(date);
		} else if(self == "input_self"){
			$('#goal_input').append('<p>')
			$('#goal_input').append('<input type="text" id="input_self" style="display:inline-block; width:65%;" class="form-control" placeholder="원하는 일수를 입력해주세요">');
			$('#goal_input').append('<button type="button" id="self_button" class="btn btn-success btn-min-width ml-3 md-1" style="display:inline-block; width:22%;">확인</button>');
			$('#self_button').click(function(){				
				var input_value = $('#input_self').val();
				if(input_value==""){
						alert("아무것도 입력되지 않았습니다.");
					} else{
						var date = getDate(0, 0, parseInt(input_value));
						$('#g_goaldate').val(date);
					}
				});
			change = true;
		}
	});
	
	$('#add_stats').click(function(){
		$('#stats').append('<input type="text" class="form-control" style="width:46%; display:inline-block;" placeholder="측정할 목표" name="hor_data">&nbsp');
		$('#stats').append('<input type="text" class="form-control" style="width:46%; display:inline-block;" placeholder="달성 목표값" name="data_goal">');
	});

	function checkExtension(fileName,fileSize){
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	    var maxSize = 20971520;  //20MB
	    if(fileSize >= maxSize){
	    	alert('파일 사이즈 초과');
	        $("input[type='file']").val("");  //파일 초기화
	        return false;
	   	}
	    if(regex.test(fileName)){
	        alert('업로드 불가능한 파일이 있습니다.');
	        $("input[type='file']").val("");  //파일 초기화
	        return false;
	    }
	    return true;
	}
	
	$('#goal_submit').click(function(e){
		if($('#g_name').val() == ""){
			alert("그룹명을 입력해주세요.");
			return false;
		} else if($('#g_intro').val() == ""){
			alert("그룹 소개글을 입력해주세요.");
			return false;
		} else if($('input[name="g_open"]:checked').length < 1){
			alert("그룹 공개여부를 설정해주세요.");
			return false;
		} else if($('#g_cate').val() == ""){
			alert("그룹 카테고리를 정해주세요.");
			return false;
		} else if($('#g_goaldate').val() == ""){
			alert("목표 달성일을 설정해주세요.");
			return false;
		} 
		if(!checkExtension(file.name, file.size)) return false;
		alert("그룹을 정상적으로 만들었습니다.");
		this.submit();
	});
	
	function getDate(addYear, addMonth, addDay, token){		//받은 매개변수로 날짜를 추가하는 함수
		token = token == undefined || token == null ? "-" : token;
		addYear = addYear == null ? 0 : addYear;
		addMonth = addMonth == null ? 0 : addMonth;
		addDay = addDay == null ? 0 : addDay;
		var now = new Date();
		var ry = now.getFullYear();
		var rm = now.getMonth() + 1;
		var rd = now.getDate();
		
		if(addYear != 0) { //cal year
			ry = ry + addYear;
		}
		
		if(addMonth != 0) {//cal month
			var isRun = true;
			rm = rm + addMonth;
			while (isRun == true) {
				if(rm > 12){
					ry++;
					rm = rm - 12;
				} else if(rm < 1) {
					ry--;
					rm = 12 + rm;
				}
				
				if(rm > 0 && rm < 13){
					isRun = false;
				}
			}
		}
		var cal = new Date(ry, rm, 0);
		if(rd > cal.getDate())
			rd = cal.getDate();
		if (addDay != 0) {
			rd = rd + addDay;
			if(rd > cal.getDate() || rd < 1) {
				var isRun = true;
				while(isRun == true) {
					if(rd > cal.getDate()) {
						rd = rd - cal.getDate();
						rm++;
						if (rm > 12) {
							ry++;
							rm = 1;
						}
					}
					if(rd < 1) { 
						rm--;
						if(rm < 1) { 
							ry--;
							rm = 12;
						}
						cal = new Date(ry, rm, 0);
						rd = cal.getDate() + rd;
					}
					cal = new Date(ry, rm, 0);
					if(rd <= cal.getDate() && rd >= 1)
						isRun = false;
				}
			}
			if(rd > cal.getDate() || rd < 1) {
				cal = new Date(ry, rm, 0);
			}
		}
			
		if(rm.toString().length < 2) rm = '0' + rm;
		if(rd.toString().length < 2) rd = '0' + rd;
		
		return ry + token + rm + token + rd;
	};
	function goDetail(){
		$('#goDetail').submit();
	};
	var profile = document.getElementById("profile");
	profile.src = profile.dataset.src; 
}
