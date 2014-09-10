function loadFamilly () {
	$.get("loginSuccess", function(data) {
		 location.reload();
	});
}
function loadProduct () {
	$.get("ajax/loadProduct", function(data) {
		$("#productFinder").html(data);
	});
}