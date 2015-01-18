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
			
			ajouterClient = function(nomClient, prenomClient,numeroRue,telephone,nomRue,etage,immeuble,interphone,codePostale) {
				var clientObj = {
					nom : nomClient,
					prenom : prenomClient,
					telephone:telephone,
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
			
			
			function split(val) {
			    return val.split(/,\s*/);
			}
			function extractLast(term) {
			    return split(term).pop();
			}
			
		
			
			autocompliteClient=function(){
				
				 $("#telephone").autocomplete(
					       {
					    	   
					    	   minLength: 1,
					           delay: 500,
					           source : function(request, response) {
					               $.ajax({
					                   url : "ajax/client/autoCompleteClient",
					                  
					                   dataType : "json",
					                   data : {
					                       term : request.term
					                   },
					                   success : function(data) {
					                       response($.map(data, function(item) {
					                           return {
					                               label : item.telephone,
					                               value : item.telephone
					                           } ;
					                       }));
					                   }
					               });
					           },
					              
					           
					       });
		
			};
			
			//gestion des evenements
			
			$( document ).on( "click", '.addCl',function() {
				afficherFormulaireClient() ;
			});
			
			$( document ).on( "click", '#ajoutClient',function() {
				ajouterClient($('input[id="nom"]').val(),$('input[id="telephone"]').val(),$('input[id="telephone"]').val(),$('input[id="numeroRue"]').val(),$('input[id="nomRue"]').val(),$('input[id="etage"]').val(),$('input[id="immeuble"]').val(),$('input[id="interphone"]').val(),$('input[id="codePostale"]').val());
				//closePopup();
			});
			
			$( document ).on( "click", '#telephone',function() {
				autocompliteClient();
			});
			
	}) ;