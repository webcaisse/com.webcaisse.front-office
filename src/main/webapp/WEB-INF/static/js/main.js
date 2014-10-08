function loadFamilly() {
	$.get("loginSuccess", function(data) {
		location.reload();
	});
}
function loadProduct(famillyId) {
	$.get("ajax/loadProduct/" + famillyId, function(data) {
		$("#productFinder").html(data);
	});
}

function pop() {
	window.open('pop', 'height=x,width=y,top=z,left=t,resible=no');
}
