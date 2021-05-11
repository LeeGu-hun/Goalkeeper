window.onload = function() {
	function doActive(){
		$('a[class="section-menu-item active"]').removeClass('active');
		$(this).addClass('active');
	};
}