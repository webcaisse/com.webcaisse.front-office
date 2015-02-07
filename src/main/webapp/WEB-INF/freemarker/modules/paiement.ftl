<<<<<<< HEAD
	
<div id="tab-paiements" style="left: 433px; position: absolute; top: 302px; z-index: 9999; opacity: 1;">
 	<span class="button b-close" style="z-index: 88"><span>X</span></span>
 	<ul class="grid dark-grey-gradient" style="height: 300px; overflow: auto; position: relative;background-color: white; padding-top: 26px">
=======
 <div id="tab-paiements" style="left: 433px; position: absolute; top: 302px; z-index: 9999; opacity: 1;">
 	<span class="button b-close"><span>X</span></span>
 	<ul class="grid dark-grey-gradient" style="height: 400px; overflow: auto; position: relative;background-color: white;">
>>>>>>> refacto
    	<div class="container_12">
	        <div class="grid_6">
				<a class="paiement" href="javascript:;" title="Cheque" data-mode="3">CHQ</a>
				<a class="paiement" href="javascript:;" title="ticket restaurant" data-mode="5">T R</a>
				<a class="paiement" href="javascript:;" title="Especes" data-mode="1">ESP</a>
				<a class="paiement" href="javascript:;" title="Carte Bleue" data-mode="2">CB</a>
				<a class="paiement" href="javascript:;" title="carte de fidelite" data-mode="4">FID</a>
	        </div>
	        <div class="grid_6">
				<table class="table tablePaiement" cellspacing="0" width="100%">
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
<<<<<<< HEAD
						<tr style="display :none;color: black;" class="lignePaiement">
							<td></td>
							<td class="montant"></td>
=======
						<tr style="display :none;">
							<td>CB</td>
							<td class="montant">2</td>
>>>>>>> refacto
							<td class="table-actions">
								<a href="javascript:;" title="Supprimer" class="with-tip deletePaiement" rel="CB">
									<img src="images/icons/fugue/cross-circle.png" width="16" height="16">
								</a>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="table" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td style="text-align: right;background-color: black;">
								<b>
									Solde 
								</b>
							</td>
<<<<<<< HEAD
							<td style="width: 111px; background-color: black;" id="resteAPayer" rel="0">
								<span id="solde">0</span><span>EUR</span>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;background-color: black;">
								<b>
									Total TTC 
								</b>
							</td>
							<td style="width: 111px; background-color: black;" id="resteAPayer" rel="0">
								<span id="totalTTC">0</span><span>EUR</span>
							</td>
						</tr>
					</tbody>
				</table>
	 			<div class="clearfix"></div>
				<br><br><br>
			 	<button id="terminer" class="float-right"><img src="images/icons/fugue/tick-circle.png"> Terminer</button>
				<button class="saveDevis float-right red" style="margin-right: 30px;">Devis</button>
				<div class="clearfix"></div> 		
				</div>
		    </div>
		 
	    </div>
	    
	 	<div class="clearfix"></div>
	  </ul>
</div>
	
	