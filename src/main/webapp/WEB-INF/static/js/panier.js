$(document).ready(function() {

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
			$('#solde').html(data.prixTtc + ' ' + data.devise);
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

	/**
	 * Afficher le popup de remise
	 */
	afficherPopupRemise = function (){
		$('#popup_remise').bPopup({
			easing : 'easeOutBack',
			speed : 450,
			transition : 'slideDown'
		});
	};
	
	saisirMontantRemise =  function (value){
		if (!value){
			return ;
		}
		var pattern = /^\d{0,2}(\.\d{0,2}){0,1}$/;
		var inputVal = $('input[id="prix"]');
		if (value.indexOf('%')!=-1 && value!='%'){
			inputVal.val(value);
		}else {
		   if (pattern.test(value)){
			   if (pattern.test(inputVal.val())){
				   inputVal.val(inputVal.val() + value);				   
			   }else{
				   inputVal.val(value);
			   }
		   }else{
			   inputVal.val(inputVal.val() + value);
		   }
		}
	};
	
	effacerMontantRemise = function (){
		$('input[id="prix"]').val('');
	};
	
	doSubmitRemise = function (valueRemise, idxLignePanier){
		if(valueRemise==''){
			return;
		}
		if (valueRemise.indexOf('%')!=-1){
			valueRemise = valueRemise.replace('%','')/100;
		}
		$.ajax({
			type : "GET",
			url : "ajax/product/remiseProduit",
			data :{indexLignePanier : 0, remiseValue:valueRemise} 
		}).done(function(line) {
			calculerPrixPanier();
		});
	};
	
	offrirLignePanier = function (index){
		var elementGiftOK = $('.with-tip.button.produitOffert:eq('+index+')').children().next();
		if (elementGiftOK.is(':visible')){
			elementGiftOK.hide();
		}else{
			elementGiftOK.show();
		}
	};
	
	viderPanier = function() {
		$.get("ajax/product/viderPanier");
	};
	
	// gestion des événements 

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
		afficherPopupRemise();
	});
	
	$( document ).on( "click", '.calculette',function() {
		saisirMontantRemise($(this).attr('title'));
	});
	
	$( document ).on( "click", '.calculette.effacer',function() {
		effacerMontantRemise();
	});
	
	$( document ).on( "click", '.submitRemise',function() {
		doSubmitRemise($('input[id="prix"]').val(), $(this).parent('td').parent('tr').index() );
	});
	
	$( document ).on( "click", '.produitOffert',function() {
		offrirLignePanier($(this).parent('td').parent('tr').index());
	});

	viderPanier();
});