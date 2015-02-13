$(document).ready(
		function() {
	
			
			displayPopupWithEffect = function() {
				$('#popupClient').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			closePopup = function (){
				$('.button.b-close').click(); 
			};
			
			
			afficherFormulaireClient= function () {
				
						// display popup
						displayPopupWithEffect();	
			
			};
			
			ajouterClient = function(nomClient, prenomClient,numeroRue,nomRue,etage,immeuble,interphone,codePostale) {
				var clientObj = {
					nom : nomClient,
					prenom : prenomClient,
					numeroRue: numeroRue, 
					nomRue:nomRue ,
					etage:etage,
					immeuble:immeuble,
					interphone:interphone,
					codePostale:codePostale 
				};
				$.ajax({
					type : "POST",
					url : "ajax/client/ajouterClient",
					data : clientObj
				}).done(function() {
					location.reload();
				});
				
			
			};
			
			//gestion des evenements
			
			$( document ).on( "keyup", '#idTelephone',function(e) {
				if(e.keyCode == 13) {
					afficherFormulaireClient() ;
		       }
				
			});
			
			$( document ).on( "click", '#ajoutClient',function() {
				ajouterClient($('input[id="nom"]').val(),$('input[id="prenom"]').val(),$('input[id="numeroRue"]').val(),$('input[id="nomRue"]').val(),$('input[id="etage"]').val(),$('input[id="immeuble"]').val(),$('input[id="interphone"]').val(),$('input[id="codePostale"]').val());
				//closePopup();
			});
			
			
			
	}) ;