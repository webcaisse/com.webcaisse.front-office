<div id="tab-liste" style="display: block;">
		    
	 <#include "ligneCommande.ftl" />
	  		<div id="panier_ttc">
			<table class="table" cellspacing="0" width="100%">
				<tbody>
						<tr>
							<td style="text-align: right; width: 80%;"><b>Total Ht</b></td>
							<td id="totalHt">0 EUR</td>
						</tr>
						<tr>
							<td style="text-align: right;"><b>Tva</b></td>
							<td>0 EUR</td>
						</tr>
						<tr>
							<td style="text-align: right;"><b>Total Ttc</b></td>
							<td id="totalTtc">0 EUR</td>
						</tr>
				</tbody>
			</table>
			</div>
			<input type="hidden" id="nbProduits" value="0">
	        </br></br>
			<div class="form container_12">
					
				
				<div class="clearfix"></div><br>
				    <p>
						<label for="message">Message</label>
						<textarea id="message" name="message" style="width: 95%; height: 60px;"></textarea>
					</p>
				   <button class="addNote float-left" style="font-size: 14px; margin: 2px 0 0 50px;"><img src="images/icons/fugue/plus-circle.png" width="16" height="16"> Ajouter Notes</button>
			</div>
			
	   </div>