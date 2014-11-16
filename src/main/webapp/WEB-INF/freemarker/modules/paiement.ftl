	 <div id="tab-paiements" style="display: none;">
	    	<div class="container_12">
		        <div class="grid_6">
										<a class="paiement" href="javascript:paiement();" title="Cheque">CHQ</a>
										<a class="paiement" href="javascript:paiement();" title="ticket restaurant">TRT</a>
										<a class="paiement" href="javascript:paiement();" title="Especes">ESP</a>
										<a class="paiement" href="javascript:paiement();" title="Carte Bleue">CB</a>
										<a class="paiement" href="javascript:paiement();" title="Virement">VIR</a>
										<a class="paiement" href="javascript:paiement();" title="Debiteur">DEB</a>
										<a class="paiement" href="javascript:paiement();" title="carte de fidelite">FID</a>
			      </div>
			      </br></br>
			      <div class="grid_6" >
						<fieldset>
							<legend for="livraisonRadio">Mode de livraison</legend>
							<ul class="checkable-list">
								<li><input type="radio" name="livraisonRadio" value="0" checked="">&nbsp;Au comptoir</li>
								<li><input type="radio" name="livraisonRadio" value="1">&nbsp;A emporter</li>
								<li><input type="radio" name="livraisonRadio" value="2">&nbsp;A livrer</li>
								<li class="nodisplay"><input type="radio" name="livraisonRadio" value="3">&nbsp;Transmition</li>
							</ul>
						</fieldset>
		          </div>
		        <div class="grid_6">
					<table class="table" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th scope="col">
									Libellé
								</th>
								<th scope="col" style="width: 75px;">
									Montant
								</th>
								<th scope="col" class="table-actions" style="width: 16px;"></th>
							</tr>
						</thead>
	
						<tbody>
						</tbody>
					</table>
					<table class="table" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td style="text-align: right;">
									<b>
										Solde 
									</b>
								</td>
								<td style="width: 111px;" id="resteAPayer" rel="0"><span id="solde">0 (E)</span></td>
							</tr>
						</tbody>
					</table>
		 			<div class="clearfix"></div>
					<br><br><br>
				 	<button id="terminer" class="float-right"><img src="images/icons/fugue/tick-circle.png"> Terminer</button>
				 				 		<button class="saveDevis float-right red" style="margin-right: 30px;">Devis</button>
				</div>
		    </div>
		 
	    </div>
	    
	 	<div class="clearfix"></div>
	 	
	 	   
		
	</div>