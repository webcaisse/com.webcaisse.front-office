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
			
//			 $("#telephone").autocomplete({
//		    	   minLength: 2,
//		           delay: 500,
//		           source : function(request, response) {
//		               $.ajax({
//		                   url : "ajax/client/autoCompleteClient",
//		                   dataType : "json",
//		                   data : {
//		                       term : request.term
//		                   },
//		                   success : function(data) {
//		                       /*response(data/*$.map(data, function(item) {
//		                           return {
//		                               label : item.nom,
//		                               value : item.telephone
//		                           } ;
//		                       })/)*/
//		                	   var suggestions = [];  
//		                       //process response  
//		                       $.each(data, function(i, val){  
//		                        	suggestions.push({"id": val.nom, "value": val.telephone});  
//		                    	});  
//		                    	//pass array to callback
//		                       alert (suggestions);
//		                       response(suggestions); 
//		                   }
//		               });
//		           }
//		       });
		
			$("#idTelephone").autocomplete({
			    minLength: 2,
			    scrollHeight: 220, 
			       source: function(req, add){
				  $.ajax({
			            url:'ajax/client/autoCompleteClient',
			            type:"get",
			            dataType: 'json',
			            data: 'term='+req.term,
			            async: true,
			            cache: true,
			            success: function(data){
			                var suggestions = [];  
			                //process response  
			                $.each(data, function(i, val){  
			                 	suggestions.push({"id": val.id, "value": val.nom});  
			             	});  
			             	//pass array to callback  
			             	add(suggestions); 
			            }
			        });
			   }
			});
			//gestion des evenements
			
			$( document ).on( "click", '.addCl',function() {
				afficherFormulaireClient() ;
			});
			
			$( document ).on( "click", '#ajoutClient',function() {
				ajouterClient($('input[id="nom"]').val(),$('input[id="telephone"]').val(),$('input[id="telephone"]').val(),$('input[id="numeroRue"]').val(),$('input[id="nomRue"]').val(),$('input[id="etage"]').val(),$('input[id="immeuble"]').val(),$('input[id="interphone"]').val(),$('input[id="codePostale"]').val());
				//closePopup();
			});
			
//			$( document ).on( "click", '#telephone',function() {
//				autocompliteClient();
//			});
			
	}) ;