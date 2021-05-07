
	//상세보기 모달창 켜기 
	document.getElementById("detail-modal").onclick = function() {
        document.getElementById("modal").style.display="block";
        $.ajax({
       		type : 'post',
			url : '/onClickDetail',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
     }
     //상세보기 모달창 켜기 
	document.getElementById("imageBox").onclick = function() {
        document.getElementById("modal").style.display="block";
     }
     //모달 끄기
	$(document).mouseup(function (e){
	var container = $('detail-modal');
	if( container.has(e.target).length === 0){
		document.getElementById("modal").style.display="none";
	}
	});
	
	