$(document).ready(
		function() {
	
			
			afficherFormulaireClient = function() {
				$('#popupClient').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			afficherPopupAjoutNouveauClient = function() {
				$('#popupNouveauClient').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			closePopup = function (){
				$('.button.b-close').click(); 
			};
			
			
			
			
			ajouterNouveauClient = function(nomClient, prenomClient,telephoneClient,numeroRue,nomRue) {
				var clientObj = {
					nom : nomClient,
					prenom : prenomClient,
					telephone:telephoneClient,
					numeroRue: numeroRue, 
					nomRue:nomRue ,
					
				};
				$.ajax({
					type : "GET",
					url : "ajax/client/ajouterNouveauClient",
					data : clientObj
				}).done(function() {
					//location.reload();
				});
				
			
			};
			
			//gestion des evenements
			
			$( document ).on( "keyup", '#idTelephone',function(e) {
				if(e.keyCode == 13) {
					afficherFormulaireClient() ;
		       }
				
			});
			
			$( document ).on( "click", '#ajouterNouveauClient',function() {
				ajouterNouveauClient($('input[id="nomClient"]').val(),$('input[id="prenomClient"]').val(),$('input[id="telephoneClient"]').val(),$('input[id="numeroRueClient"]').val(),$('input[id="nomRueClient"]').val());
				//closePopup();
			});
			
			
			$( document ).on( "click", '.dataTables_empty',function() {
				afficherPopupAjoutNouveauClient() ;
			});
	}) ;