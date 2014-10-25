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
			quantite : 1,
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
		 $.ajax({
		       url : 'ajax/product/supprimerDuPanier/'+index,
		       type : 'GET',
		       success : function(data){
					$('.table.sortable tbody tr:eq(' + index + ')').remove();
					calculerPrixPanier();		           
		       }
		 });
	};

	
	calculerPrixPanier = function() {
		$.get("ajax/product/calculerPrixPanier", function(data) {
			$('#totalTtc').html(data.prixTtc + ' ' + data.devise);
			$('#totalHt').html(data.prixHt + ' ' + data.devise);
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