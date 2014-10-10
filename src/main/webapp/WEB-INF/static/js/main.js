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

function loadProductDetails(produitId) {
	$.get("ajax/product/details/"+ produitId, function(data) {
		
		// je rempli le popup avec les contenus de produit
		$("#popup").html(data);
		// ceci le code d'affichage de popup 
		$('#popup').bPopup({
			    easing: 'easeOutBack', //uses jQuery easing plugin
		        speed: 450,
		        transition: 'slideDown'
	     });
		
	});
}
