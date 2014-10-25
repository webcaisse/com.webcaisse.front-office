$(document).ready(function() {

	/**
	 * ajouter un produit au panier
	 * 
	 * @param productId
	 * @param productPrice
	 */
	ajouterAuPanier = function(productId, productPrice) {
		var prodObj = {
			idProduit : productId,
			remise : 0,
			qte : 1,
			prix : productPrice 
		};
		$.ajax({
			type : "GET",
			url : "ajax/product/ajouterAuPanier",
			data : prodObj
		}).done(function(line) {
			$("#kk").find('tbody').append(line);
			calculerPrixPanier();
		});
	};
	
	/**
	 * Supprimer un produit de panier
	 * 
	 * @param idLignePanier
	 */
	supprimerProduitDuPanier = function(index) {

		if (index<0){
			return ;
		}
		$.get("ajax/product/supprimerDuPanier/" + index,
			function(data) {
				$('.table.sortable tbody tr:eq(' + index + ')').remove();
				calculerPrixPanier();
			});
	};

	
	calculerPrixPanier = function() {
		$.get("ajax/product/calculerPrixPanier", function(data) {
			$(".panierPrix").html(data);
		});
	};

	viderPanier = function() {
		$.get("ajax/product/viderPanier");
	};
	
	// gestion des événements 

	$( document ).on( "click", '.button.deleteProduit',function() {
		supprimerProduitDuPanier( $(this).parent('td').parent('tr').index() );
	});
		
	viderPanier();
});