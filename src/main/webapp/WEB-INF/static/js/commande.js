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
			
				$.ajax({
				       url : "ajax/loadLivreurs",
				       type : "GET",
				       success : function(livreur){
				    	// cr�ation de popup
							createPopup(livreur, idCommande);
							// display popup
							displayPopupWithEffect();           
				       }
				 });
				
				
			};
			
			affecterEtatALaCommande = function (idCommande, etatCommande){
				
				$.ajax({
					type : "GET",
					url : "ajax/affecterEtat/"+ etatCommande+"/"+idCommande
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
			
			//gestion des evenements
			
			$( document ).on( "click", ".affecterLivreur",function() {
				chargerLivreurs($(this).data('idcmd'));
			});
			
			$( document ).on( "click", 'li.livreur.popup',function() {
				affecterLivreur($("#idCom").val(), $(this).data('livreur-id'));
			});		
			
			$( document ).on( "click", '.changerEtatCommande',function() {
				
				affecterEtatALaCommande($(this).data('idcmd'),"PRETE")  ;
			});
	}) ;