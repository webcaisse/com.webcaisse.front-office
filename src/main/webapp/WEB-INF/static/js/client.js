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
				$("#popupListClient").find("span[class='button b-close']").click();
				$('#popupNouveauClient').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			closePopup = function (){
				$('.button.b-close').click(); 
			};
			
			ajouterClient = function  (form, e){
				 e.preventDefault();
				 $.ajax({
			            url: form.attr('action'),
			            type: 'post',
			            data: form.serialize(),
			            success: function(data) {
			            	// close the popup 
			            	closePopup();
			            },
			            error: function() {
			                alert('Veuillez corriger les erreurs dans votre saisie svp !');
			                return false;
			            }
			        });
			}
			
			//gestion des evenements
			
			$( document ).on( "keyup", '#idTelephone',function(e) {
				if(e.keyCode == 13) {
					afficherFormulaireClient() ;
		       }
				
			});
			
			$( document ).on( "click", '.dataTables_empty',function() {
				afficherPopupAjoutNouveauClient() ;
			});
			
			$("#addClientForm").submit(function(event) {
				ajouterClient ($(this), event);
			});
	}) ;