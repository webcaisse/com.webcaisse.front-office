	<tr>
		 <input type="hidden" value="${lignePanier.idProduit}">
		 <input type="hidden" value="${lignePanier.idPrix}">
		  
		  <td> ${productName}<img src="images/icons/fugue/pencil.png" width="16" height="16" style="cursor: pointer;" class="commentProduit" prix="2" idproduit="4083" comment="">
		  </td>
		  <td style="cursor: pointer;" rel="4083" prix="2" type="1" libre="1" taux="20" nb="1" remise="0%">
			<a href="#" class="button"> ${lignePanier.prix}</a>
		  </td>
			<td style="white-space: nowrap;">
			    <div class="numbers-row" style="overflow: hidden;  margin: 0 0 5px 0;">
			    <div class="inc button">+</div> 
			    <input type="text" name="french-hens" id="french-hens" value="${lignePanier.quantite}" style=" width: 15px;">
			    <div class="dec button">-</div>
                   
                </div>
		   </td>
		  <td style="width:10%">
			<a href="javascript:;" class="button editRemiseProduit" rel="4083" prix="2" type="1" libre="1" taux="20" nb="1" remise="0%" style="cursor: pointer;">
															Remise 
			</a>
		  </td>
			<td>
				<#assign prix="${(lignePanier.prix - lignePanier.prix*lignePanier.remise ) * lignePanier.quantite}">
				<a href="#" class="button lignePanierPrix">  ${prix}</a>
			</td>
			
			<td style="width:20%">				
				<a href="javascript:;" title="Supprimer" class="with-tip button deleteProduit" rel="4083" prix="2" type="1" libre="1" taux="20" nb="-1" style="margin-left: 5px;"><img src="images/icons/fugue/trash.png" width="16"></a>
				<a href="javascript:;" title="Offrir" class="with-tip button produitOffert" rel="4083" prix="2" type="1" libre="1" taux="20" nb="-1" style="margin-left: 5px;">
					<img src="images/icons/fugue/gift.png" width="16" >
					<img src="images/icons/fugue/gift_OK.png" width="16" style="display:none">
				</a>
			</td>
	</tr>
