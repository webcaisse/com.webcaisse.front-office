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
			
			
			chargerClients=function(){
				
				if ( $.fn.dataTable.isDataTable( '#listClient' ) ) {
				    table = $('#listClient').dataTable();
				}
				else {
				    table = $('#listClient').dataTable( {
				    	 "ajax": "ajax/client/afficherListClient",
				         "columns": [
                                     { "data": "id" },
				                     { "data": "prenom" },
				                     { "data": "nom" },
				                     { "data": "telephone" },
				                     { "data": "nomRue"}
				                 ]
				    } );
				}
				
				$("#popupListClient").bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
					
			}
			
			ajouterClient = function  (form, e){
				 e.preventDefault();
				 $.ajax({
			            url: form.attr('action'),
			            type: 'post',
			            data: form.serialize(),
			            success: function(data) {
			            	// close the popup 
			            	closePopup();
			            	var idClient=parseInt($("#idClient").html(data).text());
			            	MajClientEnMemoire(idClient)
			            
			            },
			            error: function() {
			                alert('Veuillez corriger les erreurs dans votre saisie svp !');
			                return false;
			            }
			        });
			}
			
			
			
			MajClientEnMemoire= function(id){
				
				$.ajax({
					type : "GET",
					url : "ajax/client/selectClient/"+id 
					}).success(function() {
						// on ferme le popup
						closePopup();
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
			
			$( document ).on( "click", '.addClient',function() {
				chargerClients();
			});
			
			$("#addClientForm").submit(function(event) {
				ajouterClient ($(this), event);
				
			});
			
			$( document ).on( "click", "#listClient >tbody >tr",function() {
				var idClient  = $(this).find("td:eq(0)").html();
				MajClientEnMemoire(idClient);
			});
	}) ;