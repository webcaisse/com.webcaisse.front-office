$(document).ready(
		function() {
			
			paiement=function(){
				
				$('#popup_paiement').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			

			chargerFamilles = function () {
				$("#productFinderFamilles").show();
				$("#productFinderProducts").empty();
			};

			loadProduct = function (famillyId) {
				$.get("ajax/loadProduct/" + famillyId, function(data) {
					$("#productFinderFamilles").hide();
					$("#productFinderProducts").html(data);
				});
				
			};

			loadProductDetails = function (produitId) {
				$.get("ajax/product/details/" + produitId, function(product) {
					// parsing json object
					if (product.nbResult > 1) {
						// création de popup
						createPopup(product);
						// display popup
						displayPopupWithEffect();
					} else if (product.nbResult == 1) {
						// direct ajouter produit au panier
						ajouterAuPanier(product.produitOut.id, product.produitOut.prixOut[0].valeur,product.produitOut.prixOut[0].idPrix);
					}
				});
			};
			
			createPopup = function(product) {
				var li = $('#popup ul li:first');
				$("#popup ul").empty();
				$.each(product.produitOut.prixOut, function() {
					var liClone =  li.clone();
					liClone.children("a").children("p.productLabel").text(product.produitOut.libelle);
					liClone.children("a").children("p.productPrice").text(this.valeur +' ' + product.devise );
					liClone.data("product-id", product.produitOut.id);
					liClone.data("product-price", this.valeur);
					liClone.data("price-id", this.idPrix);
					$('#popup ul').append(liClone);
				});
				li.remove();
			};

			displayPopupWithEffect = function() {
				$('#popup').bPopup({
					easing : 'easeOutBack',
					speed : 450,
					transition : 'slideDown'
				});
			};
			
			closePopup = function (){
				$('.button.b-close').click(); 
			};
			
			ajouterLignePrixAuFormulaire =  function (){
				var first_row = $('#matable tr:first');
			    var new_id = parseInt($(first_row).attr('id').split("_")[1]) + 1;
			    var firstTr  = $(first_row).clone();
			    firstTr.find("td:last").remove();
			    firstTr.insertAfter('#matable tr:last').attr('id', 'row_' + new_id);
			};
		
			// Gestion des événements 
			
			$('li.famille').click(function(e){ 
			    loadProduct($(this).data('famille-id'));
			 });
			
						
			$( document ).on( "click", 'li.product',function() {
				loadProductDetails( $(this).data('product-id'));
			});
			
			$( document ).on( "click", 'li.product.popup',function() {
				ajouterAuPanier($(this).data('product-id'), $(this).data('product-price'),$(this).data('price-id'));
				//closePopup();
			});
			
			$( document ).on( "click", '.inCategorie',function() {
				chargerFamilles();
			});

			$( document ).on( "click", '.plus',function() {
				ajouterLignePrixAuFormulaire();
			});

		});