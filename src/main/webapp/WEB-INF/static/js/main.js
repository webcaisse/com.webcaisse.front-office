function loadFamilly() {
	$.get("loginSuccess", function(data) {
		location.reload();
	});
}

function loadProduct(famillyId) {
	$.get("ajax/loadProduct/" + famillyId, function(data) {
		$("#productFinder").html(data);
	});
}

function loadProductDetails(produitId) {
	$.get("ajax/product/details/"+ produitId, function(product) {
		
		// parsing json object 
		if (product.nbResult>1){
			var produitOut = product.produitOut;
	
			// je vide le popup
			$("#popup ul").empty();
			
			$.each(produitOut.prixOut, function() {
				/*var li =
					'<li class="product">'
						+'<a href="javascript:;" class="selectProduit"  rel="4060" prix="1" type="1" libre="1" remise="0%" style="display: block; margin: -10px; padding: 10px; cursor: pointer;">'
							+'<p class="grid-name productLabel" style="width: 131px;">'+produitOut.libelle+'</p>'
							+'<p class="grid-name productPrice" style="width: 131px;">'+this.valeur+'</p>'
	           		+'</li>' ;
						+'</a>'*/
				var li0 = $('#popup ul').first();
				//$('#popup ul').append($('#popup ul li').first().clone());
				$("#popup ul li:last").after(li0);
				
			});
			
			//
			// construire le popup
			// display popup
			$('#popup').bPopup({
			    easing: 'easeOutBack', //uses jQuery easing plugin
		        speed: 450,
		        transition: 'slideDown'
			});
		
		}else if (product.nbResult ===1){
			// direct add produit au panier 
			//alert(1) ;
			var produitOut = product.produitOut;
			
			var prix = 0;
			if (produitOut.prixOut!=null && produitOut.prixOut.length>0){
				prix = produitOut.prixOut[0].valeur;
			}
			
			var tr =
			'<tr>'+
			      '<td>'+ produitOut.libelle+'<img src="/images/icons/fugue/pencil.png" width="16" height="16" style="cursor: pointer;" class="commentProduit" prix="2" idproduit="4083" comment="">'
			      +'</td>'
			      +'<td class="editProduit" style="cursor: pointer;" rel="4083" prix="2" type="1" libre="1" taux="20" nb="1" remise="0%">'
					+'<a href="javascript:;" class="button">'+prix+'</a>'
			      +'</td>'+
			        +'<td style="white-space: nowrap;">'
					   +'<a href="javascript:;" title="Enlever" class="with-tip button deleteProduit" rel="4083" prix="2" type="1" libre="1" taux="20" nb="-1" style="width: 10px; text-align: center;">-</a>'
					   +'<a href="javascript:;" class="editNbProduit button" style="cursor: pointer;" rel="4083" prix="2" type="1" libre="1" taux="20" nb="1" remise="0%">1</a>'
					   +'<a href="javascript:;" title="Ajouter" class="with-tip button deleteProduit" rel="4083" prix="2" type="1" libre="1" taux="20" nb="1" style="width: 10px; text-align: center;">+</a>'
			      '</td>'+
			
			       +'<td>'
				    +'<a href="javascript:;" class="button editRemiseProduit" rel="4083" prix="2" type="1" libre="1" taux="20" nb="1" remise="0%" style="cursor: pointer;">'
																	+'Remise '+
					'</a>'
				+'</td>'
				+'<td>'
					+'2'	
				+'</td>'+
			+'</td>'
			+'<td>'
				
					+'<a href="javascript:;" title="Supprimer" class="with-tip button deleteProduit" rel="4083" prix="2" type="1" libre="1" taux="20" nb="-1" style="margin-left: 5px;"><img src="/images/icons/fugue/trash.png" width="16" height="16"></a>'
			+'</td>'
		+'</tr>' ;
			
			
			
			$("#kk").find('tbody').append(tr) ;
			
	
		}
		
		
	});
}
