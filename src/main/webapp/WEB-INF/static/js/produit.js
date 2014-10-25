$(document).ready(
		function() {

			chargerFamilles = function () {
				$.get("ajax/product/loadFamillies", function(familles) {

					$('#productFinder ul:first').remove();
					$('#productFinder h1').html("Choisissez une catégorie");
					$('#productFinder ul:last').empty();

				});
			};

			loadProduct = function (famillyId) {
				$.get("ajax/loadProduct/" + famillyId, function(data) {
					$("#productFinder").html(data);
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
						ajouterAuPanier(product.produitOut.id, product.produitOut.prixOut[0].valeur);
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
			
			// Gestion des événements 
			
			$('li.famille').click(function(e){ 
			    loadProduct($(this).data('famille-id'));
			 });
			
						
			$( document ).on( "click", 'li.product',function() {
				loadProductDetails( $(this).data('product-id'));
			});
			
			$( document ).on( "click", 'li.product.popup',function() {
				ajouterAuPanier($(this).data('product-id'), $(this).data('product-price'));
				//closePopup();
			});
		});