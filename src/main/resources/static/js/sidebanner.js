	// 기본 위치(top)값
		var RfloatPosition = parseInt($("#right_widget").css('top'))
		var LfloatPosition = parseInt($("#left_widget").css('top'))
		// scroll 인식
		$(window).scroll(function() {
	  
	    // 현재 스크롤 위치
	    var currentTop = $(window).scrollTop();
	    var RbannerTop = currentTop + RfloatPosition + "px";
	    var LbannerTop = currentTop + LfloatPosition + "px";
	    //이동 애니메이션
	     if($(window).scrollTop()>390){
	    	document.getElementById("lefthidden").style.visibility = "visible";
	    } else if($(window).scrollTop()<390){
	    	document.getElementById("lefthidden").style.visibility = "hidden";
	    }
	    	
	     if($(window).scrollTop()>390){
	    	document.getElementById("righthidden").style.visibility = "visible";
	    } else if($(window).scrollTop()<390){
	    	document.getElementById("righthidden").style.visibility = "hidden";
	    }	
	    $("#right_widget").stop().animate({
	      "top" : RbannerTop
	    }, 300);
	    $("#left_widget").stop().animate({
		  "top" : LbannerTop
		}, 300);
	}).scroll();