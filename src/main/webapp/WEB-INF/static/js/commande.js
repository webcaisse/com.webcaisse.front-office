$(document).ready(
		function() {
	
			
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
			
			
			chargerLivreurs= function (idCommande) {
				
				$.get("ajax/commandes/loadLivreurs" , function(livreur) {
				
						// cr�ation de popup
						createPopup(livreur, idCommande);
						// display popup
						displayPopupWithEffect();
					
						
				});
			};
			
			
			
			
			createPopup = function(livreur, idCommande){
				var li = $('#popupLivreur ul li:first');
				$("#popupLivreur ul").empty();
				$("#idCommande").val(idCommande);
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
				       url : 'ajax/commandes/affecter/'+idCommande+'/'+idLivreur,
				       type : 'GET',
				       success : function(data){
							location.reload();           
				       }
				 });
			};
			
			//gestion des evenements
			
			$( document ).on( "click", ".affecterLivreur",function() {
				chargerLivreurs($(this).data('idcmd'));
			});
			
			$( document ).on( "click", 'li.livreur.popup',function() {
				affecterLivreur($('#idCommande').val(), $(this).data('livreur-id'));
			});
			
			
			
	}) ;