$(document).ready(function() {

	var pattern = /^\d{0,2}(\.\d{0,2}){0,1}$/;

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
	afficherPopupRemise = function (index){
		$('#popup_remise').bPopup({
			easing : 'easeOutBack',
			speed : 450,
			transition : 'slideDown'
		});
		$('#indexLigneProduit').val(index);
	};
	
	saisirMontant =  function (value, inputVal){
		if (!value){
			return ;
		}
		if (pattern.test(value)==false && value.indexOf('%')!=-1 && value!='%'){
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

		$.ajax({
			type : "GET",
			url : "ajax/product/remiseProduit",
			data :{indexLignePanier : idxLignePanier, remiseValue:formatterValeurRemise(valueRemise)} 
		}).done(function(nouveauPrix) {
			if (nouveauPrix!='PAS_DE_REMISE'){
				$('.button.lignePanierPrix:eq('+idxLignePanier+')').html(nouveauPrix);
				calculerPrixPanier();				
			}
		});
	};
	
	formatterValeurRemise = function (valueRemise){
	   if (pattern.test(valueRemise)){
		   if (valueRemise==1){
			   valueRemise=100;
		   }
		   return valueRemise/100;
	   }else if (valueRemise.indexOf('%')!=-1){
		   return formatterValeurRemise(valueRemise.replace('%',''));
	   }else{
		   return 0;// pas de remise
	   }
	};
	
	offrirLignePanier = function (index){
		var valueRemise = 0; // pas de remise
		var elementGiftOK = $('.with-tip.button.produitOffert:eq('+index+')').children().next();
		if (elementGiftOK.is(':visible')){
			elementGiftOK.hide();
		}else{
			// faire une remise à 100%
			valueRemise=1;
			elementGiftOK.show();
		}
		doSubmitRemise(valueRemise, index);
	};
	
	
	ajouterNote = function(){
		var message=$('#message').val() ;
		$.ajax({
			type : "GET",
			url : "ajax/product/ajouterNote/"+message
		}).done(function(notes) {
			$( "<li>"+notes+"</li>" ).insertAfter( "#notes" );
			$('#message').val("");
			$('#message').focus();
		});


	};
	
	afficherPopupModePaiement = function(mode){
		$.ajax({
			type : "GET",
			url : "ajax/product/afficherPopupModePaiement/"+mode
		}).done(function(montant) {
			$('#prixPopupModePaiement').val(montant);
			$('#popup_paiement').bPopup({
				easing : 'easeOutBack',
				speed : 450,
				transition : 'slideDown'
			});
		});;
		
	};
	
	saisirModePaiement=function(montant,modePaiement){
		$.ajax({
			type : "GET",
			url : "ajax/product/saisirModePaiement",
			data :{montant : montant, idModePaiement:modePaiement} 
		});
	};
		
	viderPanierModePaiement = function() {
		$.get("ajax/product/viderPanierModePaiement");
	};
		
	// gestion des événements 
	
	$( document ).on( "click", '.paiement',function() {
		afficherPopupModePaiement($(this).data('mode'));
	});

	$( document ).on( "click", '.addNote',function() {
		ajouterNote() ;
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
		
	$( document ).on( "click", '.calculette.calculettePaiement',function() {
		saisirMontant($(this).data('montant'), $('input[id="prixPopupModePaiement"]'));
	});
	
	$( document ).on( "click", '.calculette',function() {
		saisirMontant($(this).attr('title'), $('input[id="prix"]'));
	});

	$( document ).on( "click", '.calculette.effacer',function() {
		effacerMontantRemise();
	});
	
	$( document ).on( "click", '.submitRemise',function() {
		doSubmitRemise($('input[id="prix"]').val(), $('#indexLigneProduit').val());
	});
	
	$( document ).on( "click", '.produitOffert',function() {
		offrirLignePanier($(this).parent('td').parent('tr').index());
	});
	

	viderPanierModePaiement();
});