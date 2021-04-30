
	//상세보기 모달창 켜기 
	document.getElementById("detail-modal").onclick = function() {
        document.getElementById("modal").style.display="block";
     }
	//Hide modal
	close.addEventListener('click', () => {
  		document.getElementById("modal").style.display="none";
	})
	
	