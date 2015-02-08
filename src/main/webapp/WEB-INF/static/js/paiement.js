$(document).ready(function() {

	var pattern = /^\d{0,2}(\.\d{0,2}){0,1}$/;

	afficherPopupModePaiement = function(mode){
		$.ajax({
			type : "GET",
			url : "ajax/product/afficherPopupModePaiement/"+mode
		}).done(function(montant) {
			$('#prixPopupModePaiement').val(montant);
			$('#modePaiement').val(mode);
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
	
	doSubmitModePaiement =  function (valeur, mode){
		$.ajax({
			type : "GET",
			url : "ajax/product/saisirMontantParModePaiement",
			data :{montant : valeur, idModePaiement:mode} 
		}).done(function() {
			$('#closePopupPaiement').click();
		});
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

	effacerMontantRemise = function (){
		$('input[id="prix"]').val('');
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

	$( document ).on( "click", '.paiement',function() {
		afficherPopupModePaiement($(this).data('mode'));
	});

	$( document ).on( "click", '.validerMontant.grey',function() {
		doSubmitModePaiement($('#prixPopupModePaiement').val(), $('#modePaiement').val());
	});

});
