function loadFamilly() {
	$.get("ajax/product/loadFamillies", function(familles) {
		//alert (familles);
		$('#productFinder ul:first').remove();
		$('#productFinder h1').html("Choisissez une catégorie");
		
		$('#productFinder ul:last').empty();
		$.each(familles.famillesOut,
				function() {
					var li = '<li class="product">'
								+'<a href="javascript:loadProduct('+this.id+');" class="remise" style="display: block; margin: -10px; padding: 10px; cursor: pointer;">'
								+'<p class="grid-name" style="width: 136px;">'+this.libelle+'</p>'
								+'</a>'
							  +'</li>';

					$('#productFinder ul').append(li);

				});
	});
}

function loadProduct(famillyId) {
	$.get("ajax/loadProduct/" + famillyId, function(data) {
		$("#productFinder").html(data);
	});
}

function loadProductDetails(produitId) {
	$.get(
					"ajax/product/details/" + produitId,
					function(product) {

						// parsing json object
						if (product.nbResult > 1) {
							var produitOut = product.produitOut;

							// je vide le popup
							$("#popup ul").empty();

							ajouterAuPanier(produitOut.id, prix);
							$.each(produitOut.prixOut,
											function() {
												var li = '<li class="product">'
														+ '<a href="javascript:;" class="selectProduit"  rel="4060" prix="1" type="1" libre="1" remise="0%" style="display: block; margin: -10px; padding: 10px; cursor: pointer;">'
														+ '<p class="grid-name productLabel" style="width: 131px;">'
														+ produitOut.libelle
														+ '</p>'
														+ '<p class="grid-name productPrice" style="width: 131px;">'
														+ this.valeur + '</p>'
														+ '</li>';
												+'</a>';

												$('#popup ul').append(li);

											});

							//
							// construire le popup
							// display popup
							$('#popup').bPopup({
								easing : 'easeOutBack', // uses jQuery easing
														// plugin
								speed : 450,
								transition : 'slideDown'
							});

						} else {
							// direct add produit au panier
							var produitOut = product.produitOut;
							var prix = produitOut.prixOut[0].valeur;

							ajouterAuPanier(produitOut.id, prix);

						}

					});
}

function ajouterAuPanier(productId, price) {

	$.ajax({
		type : "GET",
		url : "ajax/product/ajouterAuPanier",
		data : {
			idProduit : productId,
			remise : 0,
			qte : 1,
			prix : price
		}
	}).done(function(msg) {
		$("#kk").find('tbody').append(msg);
		
		calculerPrixPanier();
	});
}



function deleteFromCart(idLignePanier) {
	
	$.get("ajax/product/supprimerDuPanier/"+idLignePanier, function(data) {
		
		$('#kk tr:eq('+ idLignePanier+')').remove();
		
		calculerPrixPanier();

	});
}




function calculerPrixPanier() {

	$.get("ajax/product/calculerPrixPanier", function(data) {
		$(".panierPrix").html(data);
	});

}

function viderPanier (){
	$.get("ajax/product/viderPanier");
}
$( document ).ready(function() {
	viderPanier();  
});