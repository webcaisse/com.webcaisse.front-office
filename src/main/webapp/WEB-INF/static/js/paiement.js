$(document).ready(function() {

	var pattern = /^\d{0,2}(\.\d{0,2}){0,1}$/;
	
	var contextPath  = function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
	
	afficherPopupPaiement=function(modeVente){
			
			$.ajax({
					type : "GET",
					url : contextPath()+"/ajax/product/afficherPopupPaiement/"+modeVente
				}).done(function(data) {
					
					var obj = jQuery.parseJSON(data);
					
					// renseignement sur le prix de panier
					$('#solde').html(obj.total_ttc);
					$('#totalTTC').html(obj.total_ttc);
					
					// affichage de popup
					$('#tab-paiements').bPopup({
						easing : 'easeOutBack',
						speed : 450,
						transition : 'slideDown'
					});
					
				});;
			
			};
	
	
	afficherPopupModePaiement = function(mode){
		$.ajax({
			type : "GET",
			url : contextPath()+"/ajax/product/afficherPopupModePaiement/"+mode
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
			url : contextPath()+"/ajax/product/saisirModePaiement",
			data :{montant : montant, idModePaiement:modePaiement} 
		});
	};
	
	doSubmitModePaiement =  function (valeur, mode){
		$.ajax({
			type : "GET",
			url : contextPath()+"/ajax/product/saisirMontantParModePaiement",
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
			url : contextPath()+"/ajax/product/remiseProduit",
			data :{indexLignePanier : idxLignePanier, remiseValue:formatterValeurRemise(valueRemise)} 
		}).done(function(nouveauPrix) {
			if (nouveauPrix!='PAS_DE_REMISE'){
				$('.button.lignePanierPrix:eq('+idxLignePanier+')').html(nouveauPrix);
				calculerPrixPanier();				
			}
		});
	};

	
	saisirMontant =  function (value, inputVal){
		//value.indexOf('%')!=-1 
		if (pattern.test(value)==false && value!='%'){
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


	/**
	 * payer en plusieurs forme
	 * 
	 */ 
	PayerEnPlusieursForme= function(valeur){
		var mode  = $('#modePaiement').val();
		$.ajax({
			type : "GET",
			url : contextPath()+"/ajax/product/payerEnPlusieursForme/"+valeur+"/"+mode,
			success :function(data) {
				afficherLignePaiement(data, mode) ;
				calculerSoldePaiement () ;
				$('#closePopupPaiement').click();
			}
		});
	};
	
	afficherLignePaiement = function(modePaiement, mode) {

		var  tr= $('.table.tablePaiement > tbody tr:eq(0)')    ;
		var trClone= tr.clone();
			
		//ESPECE(1),CB(2), CHEQUE(3), FIDELITE(4), TR(5);
		if (mode==1){
			trClone.find(("td:eq(0)")).html('Especes') ;
			trClone.find(("td:eq(1)")).html(modePaiement.espece);
		}else if (mode==2){
			trClone.find(("td:eq(0)")).html('Carte bancaire') ;
			trClone.find(("td:eq(1)")).html(modePaiement.cb);
		}else if (mode==3){
			trClone.find(("td:eq(0)")).html('ChÃ©que') ;
			trClone.find(("td:eq(1)")).html(modePaiement.cheque);
		}else if (mode==4){
			trClone.find(("td:eq(0)")).html('Carte fidelitÃ©') ;
			trClone.find(("td:eq(1)")).html(modePaiement.fidelite);
		}else if (mode==5){
			trClone.find(("td:eq(0)")).html('Ticket restaurent') ;
			trClone.find(("td:eq(1)")).html(modePaiement.ticketRestau);
		}

		// rendre le TR visible
		trClone.removeAttr("style");
	
		$('.table.tablePaiement > tbody').append(trClone);
		
	
	};
	
	deletePaiement= function(index){
		
		var mode  = $('#modePaiement').val() ;
		if (index<0){
			return ;
		}
		 $.ajax({
		       url : contextPath()+'/ajax/product/deletePaiement/'+mode,
		       type : 'GET',
		       success : function(){
					$('.table.tablePaiement tbody tr:eq(' + index + ')').remove();
					calculerSoldePaiement();         
		       } 
		 });
	};	
	
	
	 calculerSoldePaiement = function() {		
		var montantSaisie=0;
		$('.montant').each(function(){
			 if ($(this).html() && $(this).html()!==''){
				 montantSaisie+=parseFloat($(this).html());
			 }			 
		});
		$('#solde').html(parseFloat($('#totalTTC').html())-montantSaisie);
	 } ;
			
	 // afficher popup modeVente(A emporter, en livraison, sur place)
	  afficherpopupModeVente=function(){
			
			$('#popupModeVente').bPopup({
				easing : 'easeOutBack',
				speed : 450,
				transition : 'slideDown'
			});
		};
		
		
	  //gestion des evenements
			
	$( document ).on( "click", '.button.editRemiseProduit',function() {
		afficherPopupRemise($(this).parent('td').parent('tr').index());
	});
		
	$( document ).on( "click", '.calculette.calculettePaiement',function() {
		saisirMontant($(this).data('montant'), $('input[id="prixPopupModePaiement"]'));
	});
	
	$( document ).on( "click", '#Effacer',function() {
		$('input[id="prixPopupModePaiement"]').html("0");
	});
	
	$( document ).on( "click", '.submitRemise',function() {
		doSubmitRemise($('input[id="prix"]').val(), $('#indexLigneProduit').val());
	});
	
	$( document ).on( "click", '.produitOffert',function() {
		offrirLignePanier($(this).parent('td').parent('tr').index());
	});

	$( document ).on( "click", '#paiementCommande',function() {
		 afficherpopupModeVente() ;
		 
	});
	
	$( document ).on( "click", '#popupModeVente ul  li:eq(0)',function() {
		afficherPopupPaiement('E') ;
		$('#modePaiement').html('E') ;
	});
	$( document ).on( "click", '#popupModeVente ul  li:eq(1)',function() {
		afficherPopupPaiement('L') ;
		$('#modePaiement').html('L') ;
	});
	$( document ).on( "click", '#popupModeVente ul  li:eq(2)',function() {
		afficherPopupPaiement('P') ;
		$('#modePaiement').html('P') ;
	});
	
	$( document ).on( "click", '.paiement',function() {
		afficherPopupModePaiement($(this).data('mode'));
	});
	
	$( document ).on( "click", '#validerPopupPaiement',function() {
		PayerEnPlusieursForme($('input[id="prixPopupModePaiement"]').val()) ;
	});
			
	$( document ).on( "click", '.deletePaiement',function() {
		 deletePaiement($(this).parent('td').parent('tr').index() ) ;
	});
	
	
});

