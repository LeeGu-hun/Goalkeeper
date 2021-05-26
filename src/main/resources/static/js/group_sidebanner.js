	// 기본 위치(top)값
	var RfloatPosition = parseInt($("#right_widget").css('top'))
	var LfloatPosition = parseInt($("#left_widget").css('top'))
		// scroll 인식
	$(window).scroll(function() {
	    // 현재 스크롤 위치
	    var currentTop = $(window).scrollTop();
	    console.log(currentTop);
	    //이동 애니메이션
	    if(currentTop <550){
	    	return false;
	    } else{
	    	currentTop -= 550;
	    }
	    var RbannerTop = currentTop + RfloatPosition + "px";
	    var LbannerTop = currentTop + LfloatPosition + "px";
	    $("#right_widget").stop().animate({
	      "top" : RbannerTop 
	    }, 300);
	    $("#left_widget").stop().animate({
		  "top" : LbannerTop 
		}, 300);
	}).scroll();