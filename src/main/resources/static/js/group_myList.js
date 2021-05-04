$(document).ready(function() {
		if($('#nullCheck').text()==""){
			$('#toggleDiv').append('style', 'display:inline-block');
		} else{
			$('#toggleDiv').remove();
		}
		var toggle = true;
		$('#removeBtn').click(function(){
			if($('input[type="checkbox"]:checked').val()==null){
				alert("삭제할 항목을 선택하세요.");
				return false;		
			}
			$('input[type="checkbox"]:checked').attr("name", "gno");
			$('input[type="checkbox"]:checked').closest("form").attr("id", "removeFrm");
			toggle=false;
			$('#confirmBtn').click(function(){
				alert("삭제가 완료되었습니다.");
				$('#removeFrm').submit();	
			});
		});
		$('#selectBtn').click(function(){
			if(toggle){
				$('input[type="checkbox"]').attr("style", "display:inline-block");
				$('#selectBtn').html("최소");
				toggle = false;
			} else{
				$('input[type="checkbox"]').attr("style", "display:none");
				$('#selectBtn').html("그룹 선택");
				toggle=true;
			}			
		});
	});
	
	function goDetail(){
		var gno = $(this).attr("id");
		alert(gno);
		$(this).closet("from").attr("id", "detailForm");
		$('#detailForm').submit();
	};