$(document).ready(function() {

	var pattern = /^\d{0,2}(\.\d{0,2}){0,1}$/;

  afficherPopupPaiement=function(){
		
		$('#tab-paiements').bPopup({
			easing : 'easeOutBack',
			speed : 450,
			transition : 'slideDown'
		});
	};
	
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

	/**
	 * payer en plusieurs forme
	 * 
	 */ 
	PayerEnPlusieursForme= function(valeur){
		var mode  = $('#modePaiement').val();
		$.ajax({
			type : "GET",
			url : "ajax/product/payerEnPlusieursForme/"+valeur+"/"+mode,
			success :function(data) {
				calculerSoldePaiement () ;
				afficherLignePaiement(data, mode) ;
				 
	
			}
		});
	};
	
	afficherLignePaiement = function(modePaiement, mode) {

		var  tr= $('.table.tablePaiement > tbody tr:eq(0)')    ;
		var trClone= tr.clone();
			
		//ESPECE(1),CB(2), CHEQUE(3), FIDELITE(4), TR(5);
		if (mode==1){
			trClone.find(("td:eq(0)")).html('Espèces') ;
			trClone.find(("td:eq(1)")).html(modePaiement.espece);
		}else if (mode==2){
			trClone.find(("td:eq(0)")).html('Carte bancaire') ;
			trClone.find(("td:eq(1)")).html(modePaiement.cb);
		}else if (mode==3){
			trClone.find(("td:eq(0)")).html('Chèque') ;
			trClone.find(("td:eq(1)")).html(modePaiement.cheque);
		}else if (mode==4){
			trClone.find(("td:eq(0)")).html('Carte fidelité') ;
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
		       url : 'ajax/product/deletePaiement/'+mode,
		       type : 'GET',
		       success : function(){
					$('.table.tablePaiement tbody tr:eq(' + index + ')').remove();
					calculerSoldePaiement();         
		       }
		 });
	};	
	
	
		 calculerSoldePaiement = function() {		
			 var valeur= $('input[id="prixPopupModePaiement"]').val() ;
			 $.ajax({
			       url : 'ajax/product/calculerSoldePaiement/'+valeur,
			       type : 'GET',
			       success : function(data){
			    	   $('#solde').html(data.solde + ' ' + data.devise);     
			       }
			 });
			 
				
			};
		 
			
	  //gestion des evenements
			
     $( document ).on( "click", '#Aemporter',function() {
		afficherPopupPaiement() ;
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

	$( document ).on( "click", '.paiement',function() {
		afficherPopupModePaiement($(this).data('mode'));
	});

	$( document ).on( "click", '.validerMontant.grey',function() {
		doSubmitModePaiement($('#prixPopupModePaiement').val(), $('#modePaiement').val());
	});
	
	$( document ).on( "click", '#validerPopupPaiement',function() {
		PayerEnPlusieursForme($('input[id="prixPopupModePaiement"]').val()) ;
	});
	
	$( document ).on( "click", '.deletePaiement',function() {
		deletePaiement($(this).parent('td').parent('tr').index() ) ;
	});
	
	
	
	
});
