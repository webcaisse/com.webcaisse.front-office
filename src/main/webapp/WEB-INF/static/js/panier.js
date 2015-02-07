$(document).ready(function() {

	

	/***
	 * Afficher le popup pour le saisie d'une nouveaux client 
	 */
	displayPopupNoteWithEffect = function() {
		$.ajax({
			type : "GET",
			url : "ajax/product/afficherNotes"
		}).success(function(data) {
			$(".message").html(data);
			$('#popupNote').bPopup({
				easing : 'easeOutBack',
				speed : 450,
				transition : 'slideDown'
			});
		
		});

	};
	
	/**
	 * Fermer de popup 
	*/
	closePopup = function (){
		$('.button.b-close').click(); 
	};
	
	
	/**
	 * ajouter un produit au panier
	 * 
	 * @param productId
	 * @param productPrice
	 */
	ajouterAuPanier = function(productId, productPrice,IdPrice) {
		var prodObj = {
			idProduit : productId,
			remise : 0,
			quantite : 1,
			prix : productPrice, 
			idPrix:IdPrice
		};
		$.ajax({
			type : "GET",
			url : "ajax/product/ajouterAuPanier",
			data : prodObj
		}).done(function(line) {
			// je verifier que cette lige est deja presente
			var index = verifierLigneDansPanier($(line).find( ":hidden:eq(0)" ).val(),$(line).find( ":hidden:eq(1)" ).val());
			if (index==-1){
				// ajout
				$(".table.sortable tbody").append(line);				
			}else{
				// maj
				$(".table.sortable tbody tr:eq("+index+")").replaceWith(line);
			}
			$('.button.b-close').click();
			calculerPrixPanier();
		});
	};
	
	verifierLigneDansPanier = function (productId, priceId){
		var tmpIndex = 0;
		var indexLignePanier = -1;
		$('.table.sortable > tbody  > tr').each(function() {
			var idProduit = $(this).find( ":hidden:eq(0)" ).val();
			var idPrix 	= $(this).find( ":hidden:eq(1)" ).val();
			if (productId==idProduit && idPrix==priceId){
				indexLignePanier = tmpIndex;
			}
			tmpIndex++;
		});
		return indexLignePanier ;
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
			$('#total').html(data.prixTtc + ' ' + data.devise);
			$('#totalTTC').html(data.prixTtc + ' ' + data.devise);
			$('#prix').html(data.prixTtc + ' ' + data.devise);
		});
	};

	/**
	 * incrementer decrementer quantite
	 * @param index
	 * @param qte
	 */
	incDecProduit = function (index, qte){
		$.ajax({
			type : "GET",
			url : "ajax/product/incDec",
			data :{indexLignePanier : index, quantite : qte} 
		}).done(function(line) {
			$(".table.sortable tbody tr:eq("+index+")").replaceWith(line);
			calculerPrixPanier();
		});
		
	};
	
	
	
	sauvegarderCommande=function(modeVente){
		$.ajax({
			type : "GET",
			url : "ajax/product/sauvegarderCommande/"+modeVente 
		}).done(function() {
			location.reload();
		});
	} ;
		
	viderPanierModePaiement = function() {
		$.get("ajax/product/viderPanierModePaiement");
	};
		
	// gestion des �v�nements 
	
	$( document ).on( "click", '.addNote',function() {
		displayPopupNoteWithEffect() ;
	});

	$( document ).on( "click", '.button.deleteProduit',function() {
		supprimerProduitDuPanier( $(this).parent('td').parent('tr').index() );
	});

	$( document ).on( "click", '.inc.button',function() {
		incDecProduit($(this).parent().parent().parent().index(), 1);
	});

	$( document ).on( "click", '.dec.button',function() {
		incDecProduit($(this).parent().parent().parent().index(), -1);		
	});
	

	$( document ).on( "click", '.button.editRemiseProduit',function() {
		afficherPopupRemise($(this).parent('td').parent('tr').index());
	});
	
	//$( document ).on( "click", '.calculette',function() {
		//saisirMontantRemise($(this).attr('title'));
	//});
	
	$( document ).on( "click", '.calculette.effacer',function() {
		effacerMontantRemise();
	});
	
	$( document ).on( "click", '.submitRemise',function() {
		doSubmitRemise($('input[id="prix"]').val(), $('#indexLigneProduit').val());
	});
	
	$( document ).on( "click", '.produitOffert',function() {
		offrirLignePanier($(this).parent('td').parent('tr').index());
	});
	
	$( document ).on( "click", '#terminer',function() {
		sauvegarderCommande($('#Aemporter').attr('title'));
	});
	

	viderPanierModePaiement();
});