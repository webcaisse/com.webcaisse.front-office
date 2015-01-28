$(document).ready(
		function() {
			
			/***
			 * Afficher le popup pour le saisie d'une nouveaux client 
			 */
			displayPopupWithEffectClient = function() {
				$('#popupClient').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			/**
			 * Fermer de popup 
			*/
			closePopup = function (){
				$('.button.b-close').click(); 
			};
			
		
			$("#idTelephone").autocomplete({
			    minLength: 2,
			    scrollHeight: 220, 
			    source: function(req, add){ $.ajax({
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
		                 	suggestions.push({"id": val.id, "value": constructAutocompleteName(val)});  
		             	});  
		             	//pass array to callback  
		             	add(suggestions); 
		            }
		        });
			   },
			   select: function (elem, data) {
				   affecterClientToSession(data.item.id);
			    }
			});
			
			constructAutocompleteName = function (val){
				return whiteIfNull(val.prenom) +' ' +
				whiteIfNull(val.nom) +', '+
				whiteIfNull(val.numeroRue)+' '+
				whiteIfNull(val.nomRue)+' '+
				whiteIfNull(val.codePostale)+' '+
				whiteIfNull(val.ville) +'\n' ;
			};
			
			/**
			 * Choisir le client courant 
			 */
			
			affecterClientToSession = function(idClient){
				$.ajax({
		            url:'ajax/client/selectClient/'+idClient,
		            type:"get",
		            dataType: 'json',
		            success: function(data){
		            	$('#idTelephone').val(data.telephone);
		            	$('.clientInfos').html(constructAutocompleteName(data));
		            }
		        });
			} ;
			
			whiteIfNull =  function (data){
				if (data==null || data=='undifined'){
					return '';
				}
				return data;
			} ;
			
			
			
			//-------------- gestion des evenements------------
			$( document ).on( "click", '.addCl',function() {
				displayPopupWithEffect();
			});
			
			$( document ).on("keypress",'#idTelephone',function(e){
			if( e.which == 13 ){
				displayPopupWithEffectClient();
		       }
			});
			
	}) ;