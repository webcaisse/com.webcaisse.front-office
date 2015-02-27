$(document).ready(function() {
	
			var pattern = /^\d{0,2}(\.\d{0,2}){0,1}$/;
			
			displayPopupWithEffect = function() {
				$('#popupLivreur').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			closePopup = function (){
				$('.button.b-close').click(); 
			};
			
			
			afficherpopupModeVenteParCommande=function(){
				
				$('#popupModeVente').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			afficherPopupPaiementParCommande=function(modeVente){
				
				$.ajax({
						type : "GET",
						url : "ajax/afficherPopupPaiement/"+modeVente
					}).done(function(data) {
						// renseignement sur le prix de panier
						$('#solde').html($('#montantCommande').text());
						$('#totalTTC').html($('#montantCommande').text());
						
						// affichage de popup
						$('#tab-paiements').bPopup({
							easing : 'easeOutBack',
							speed : 450,
							transition : 'slideDown'
						});
						
					});;
				
				};
			
			afficherPopupModePaiementParCommande = function(mode){
				$.ajax({
					type : "GET",
					url : "ajax/afficherPopupModePaiement/"+mode
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
			
			chargerLivreurs= function (idCommande) {
			
				$.ajax({
				       url : "ajax/loadLivreurs",
				       type : "GET",
				       success : function(livreur){
				    	// creation de popup
							createPopup(livreur, idCommande);
							// display popup
							displayPopupWithEffect();           
				       }
				 });
				
				
			};
			
			affecterEtatALaCommandeParCommande = function (idCommande, etatCommande){
				
				$.ajax({
					type : "GET",
					url : "ajax/affecterEtat/"+ etatCommande+"/"+idCommande
				}).success(function() {
					location.reload();
				});
				
			} ;
			
            affecterEtatALaCommandeAvecMode = function (idCommande, etatCommande,mode){
				
				$.ajax({
					type : "GET",
					url : "ajax/affecterEtatAvecMode/"+ etatCommande+"/"+idCommande+"/"+mode
				}).success(function() {
					location.reload();
				});
				
			} ;
			
			
			createPopup = function(livreur, idCommande){
				var li = $('#popupLivreur ul li:first');
				$("#popupLivreur ul").empty();
				$("#idCom").val(idCommande);
				$.each(livreur.livreursOut, function(index, liv) {
					var liClone =  li.clone();
					liClone.children("a").children("p.livreurName").text(liv.nom);
					liClone.data("livreur-id", liv.id);
					$('#popupLivreur ul').append(liClone);
				});
				li.remove();
			
			};
			
			affecterLivreur = function( idCommande,idLivreur) {

				 $.ajax({
				       url : 'ajax/affecter/'+idCommande+'/'+idLivreur,
				       type : 'GET',
				       success : function(data){
							location.reload();           
				       }
				 });
			};
			
		// Amettre a part
			
			saisirMontantParCommande =  function (value, inputVal){
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


			
			
		PayerEnPlusieursFormeParCommande= function(valeur){
				var mode  = $('#modePaiement').val();
				$.ajax({
					type : "GET",
					url : "ajax/payerEnPlusieursForme/"+valeur+"/"+mode,
					success :function(data) {
						
						afficherLignePaiement(data, mode) ;
						calculerSoldePaiement () ;
						$('#closePopupPaiement').click();
					}
				});
			};
			
// A METTRE A PART			
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
					trClone.find(("td:eq(0)")).html('Cheque') ;
				trClone.find(("td:eq(1)")).html(modePaiement.cheque);
				}else if (mode==4){
					trClone.find(("td:eq(0)")).html('Carte fidelite') ;
					trClone.find(("td:eq(1)")).html(modePaiement.fidelite);
				}else if (mode==5){
					trClone.find(("td:eq(0)")).html('Ticket restaurent') ;
					trClone.find(("td:eq(1)")).html(modePaiement.ticketRestau);
				}

//				// rendre le TR visible
				trClone.removeAttr("style");
			
				$('.table.tablePaiement > tbody').append(trClone);
				
			
	};
			
			deletePaiement= function(index){
//				
			var mode  = $('#modePaiement').val() ;
				if (index<0){
					return ;
				}
				 $.ajax({
				       url : 'ajax/deletePaiement/'+mode,
				       type : 'GET',
				       success : function(){
						$('.table.tablePaiement tbody tr:eq(' + index + ')').remove();
							calculerSoldePaiement();         
				       } 
				 });
			};	
//		
			//A METTRE A PART
			
			 calculerSoldePaiement = function() {		
				var montantSaisie=0;
				$('.montant').each(function(){
					 if ($(this).html() && $(this).html()!==''){
						 montantSaisie+=parseFloat($(this).html());
					 }			 
			});
				$('#solde').html(parseFloat($('#totalTTC').html())-montantSaisie);
			 } ;
			
			
			 
			//gestion des evenements
			
			$( document ).on( "click", ".affecterLivreur",function() {
				chargerLivreurs($(this).data('idcmd'));
			});
			
			$( document ).on( "click", 'li.livreur.popup',function() {
				affecterLivreur($("#idCom").val(), $(this).data('livreur-id'));
			});		
			
			$( document ).on( "click", '.changerEtatCommande',function() {
				affecterEtatALaCommandeParCommande($(this).data('idcmd'),"PRETE")  ;
			});
			
			$( document ).on( "click", '.paiementCommande',function() {
				$('#montantCommande').html($(this).data('montant'))
				$('#commandeID').html($(this).data('idcmd'))
				 afficherpopupModeVenteParCommande() ;
				 
			});
			
			$( document ).on( "click", '#popupModeVente ul  li:eq(0)',function() {
				afficherPopupPaiementParCommande('E') ;
				$('#modePaiement').html('E') ;
			});
			$( document ).on( "click", '#popupModeVente ul  li:eq(1)',function() {
				afficherPopupPaiementParCommande('L') ;
				$('#modePaiement').html('L') ;
			});
			$( document ).on( "click", '#popupModeVente ul  li:eq(2)',function() {
				afficherPopupPaiementParCommande('P') ;
				$('#modePaiement').html('P') ;
			});
			
			$( document ).on( "click", '.paiement',function() {
				afficherPopupModePaiementParCommande($(this).data('mode'));
			});
			
			$( document ).on( "click", '.calculette.calculettePaiement',function() {
				saisirMontantParCommande($(this).data('montant'), $('input[id="prixPopupModePaiement"]'));
			});
			$( document ).on( "click", '#Effacer',function() {
				$('input[id="prixPopupModePaiement"]').html("0");
			});
			
			$( document ).on( "click", '#validerPopupPaiement',function() {
				PayerEnPlusieursFormeParCommande($('input[id="prixPopupModePaiement"]').val()) ;
			});
			
			$( document ).on( "click", '.deletePaiement',function() {
				deletePaiement($(this).parent('td').parent('tr').index() ) ;
			}) ;
			$( document ).on( "click", '#terminer',function() {
			   affecterEtatALaCommandeAvecMode(parseInt($('#commandeID').text()),"PAYEE",$('#modePaiement').text());
			});		
	});
	