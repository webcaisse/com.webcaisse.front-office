    <div class="grid_7" id="facture">
<!-- Use the class js-tabs to enable JS tabs script -->
<ul class="tabs js-tabs same-height">
    <li class="current"><a href="#tab-liste">Liste des achats</a></li>
           <li> <a href="#tab-infos">Informations</a></li>
        	<li><a href="#tab-options">Options</a></li>
        <li id="li-paiement"><a href="#tab-paiements">Paiement</a></li>
</ul>

<div class="tabs-content">
   <form class="form" id="formInfoPaiement" method="post" action="https://test.caisseenligne.fr/index/infos">
   		<div id="infosClient" style="height: 2em; font-size: 1em; margin-bottom: 10px;"><span style="font-size: 0.7em;">
			</span>
	<input type="hidden" id="commandeState" value="aucune">
<div style="float: right; color: blue; padding-right: 20px;">Total : <span id="total">0 (E)</span></div>
<div class="clearfix"></div>
</div>
	    <div id="tab-liste">
	    
	    <#include "ligneCommande.ftl" />
        <table class="table sortable" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th scope="col">
						Libellé
					</th>
					<th scope="col" style="width: 50px;">
						PU
					</th>
					<th scope="col" style="width: 100px;">
						NB
					</th>
					<!--th scope="col">
						HT
					</th>
					<th scope="col">
						HT Remisé
					</th>
					<th scope="col">
						TVA
					</th-->
					<th scope="col" style="width: 120px;">
						Remise
					</th>
					<th scope="col" style="width: 50px;">
						TTC
					</th>
					<th scope="col" class="table-actions" style="width: 30px;"></th>
				</tr>
			</thead>

			<tbody>
																							</tbody>
		</table>
		<table class="table" cellspacing="0" width="100%">
			<tbody>
									<tr>
						<td style="text-align: right; width: 80%;"><b>Total Ht</b></td>
						<td>0 (E)</td>
					</tr>
					<tr>
						<td style="text-align: right;"><b>Tva</b></td>
						<td>0 (E)</td>
					</tr>
					<tr>
						<td style="text-align: right;"><b>Total Ttc</b></td>
						<td id="totalTtc">0 (E)</td>
					</tr>
							</tbody>
		</table>
		<input type="hidden" id="nbProduits" value="0">

		<div class="form container_12">
							<div class="grid_6">
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
					<fieldset>
						<legend>Vendeur / Collaborateur</legend>
						<ul class="checkable-list">
																								<li><input type="radio" name="idCaissier" value="2" checked="checked">&nbsp;admin</li>
																																															<li><input type="radio" name="idCaissier" value="21">&nbsp;kevin</li>
																					</ul>
					</fieldset>
				</div>
			
			<div class="clearfix"></div><br>
							<p>
					<label for="message">Message</label>
					<textarea name="message" style="width: 95%; height: 60px;"></textarea>
				</p>
						<p>
				<label for="commentaires">Commentaires</label>
				<textarea name="commentaires" style="width: 95%; height: 75px;"></textarea>
			</p>
		</div>
    </div>

    <div id="tab-infos" style="display: none;">
        <input type="hidden" name="livraison" id="livraison" value="0">
        <fieldset id="fieldsetTransmission" style="display: none;">
        	<legend>Informations de transmition</legend>
        	<div class="container_12">
			    <div class="grid_6">
					<p>
						<label for="dateExecution">Date d'execution</label>
						<span class="input-type-text margin-right relative"><input type="text" name="dateExecution" value="16/08/2014" size="9" class="datepicker hasDatepick"><img src="images/icons/fugue/calendar-month.png" width="16" height="16"></span>
					</p>
	        	</div>
		        <div class="grid_6">
					<p>
						<label for="file">Pièce jointe</label>
						<a href="javascript:;" title="Ajouter un fichier" id="addFile">Ajouter</a>
						<input type="hidden" value="" name="fichier">
													Aucun fichier joint
											</p>
		        </div>
	    	</div>
		   	<div class="container_12">
		        <div class="grid_6">
					<p>
						<label for="externe">Transmetteur</label>
													<input type="radio" name="externe" value="0" checked="checked"> Aucun
												<br><br>
											</p>
		        </div>
		        <div class="grid_6">
					<p>
						<label for="fraisTransmission">Frais</label>
						<input type="text" name="fraisTransmission" value="">					</p>
					<p>
						<label for="reference">Référence commande</label>
						<input type="text" name="reference" value="">					</p>
					<p>
						<label for="telTransmission">Numéro de téléphone</label>
						<input type="text" name="telTransmission" value="">					</p>
		        </div>
			</div>
       	</fieldset>

		<fieldset id="fieldsetLivraison" style="display: none;">
			<legend>Informations de livraison/commande</legend>
			<div class="container_12">
		    	<div class="grid_12" id="factureAdresse">
					<p>
						<label for="adresse">Saisissez une adresse</label>
						<a href="javascript:;" class="button float-right adresseSearch">Chercher et calculer frais de livraison...</a>
						<a href="javascript:;" class="button float-right newAdresseSearch" style="display: none;">Nouvelle recherche</a>
						<input type="text" name="adresse" value="" class="large inputAdresseClient">						<img src="images/info-loader.gif" width="16" height="11" alt="Chargement" style="display: none;" class="adresseLoader">
						<select style="display: none;" class="full-width adressesClient">
							<option value="0">-Lancez une recherche-</option>
						</select>
						<input type="hidden" name="ville" data-callback="facture" class="latlngClient" value="">
						<input type="hidden" name="cp">
						<input type="hidden" id="poidsFacture" value="0">
						<input type="hidden" id="coordMagasin" value="(43.5391734, 6.367804799999931)">
					</p>
					<div class="clearfix"></div>
				</div>
		        <div class="grid_6">
					<p>
						<label for="frais">Frais de livraison</label>
						<input class="numeric euro" type="text" id="fraisLivraison" name="frais" value="0" size="5">
						<span id="distance"></span>
					</p>
					<p class="livreur">
						<label for="livreur">Livreur</label>
																					<input type="radio" name="livreur" value="4"> Coco														<br><br>
																					<input type="radio" name="livreur" value="5"> mkjlkjlkj														<br><br>
											</p>
					<p>
						<label for="dateLivraison">Date de livraison/commande</label>
						<span class="input-type-text margin-right relative"><input type="text" name="dateLivraison" value="16/08/2014" size="9" class="datepicker hasDatepick"><img src="images/icons/fugue/calendar-month.png" width="16" height="16"></span>
					</p>
					<p>
						<label for="heures">Heure de livraison/commande</label>
						<input class="numeric" type="text" name="heures" value="20" size="2" maxlength="2"> :
						<input class="numeric" type="text" name="minutes" value="50" size="2" maxlength="2">
					</p>
		        	<p>
						<label for="telClient">Téléphone du client</label>
						<input type="text" name="telClient" value="">					</p>
		        </div>
		        <div class="grid_6">
					<p>
						<label for="nomBeneficiaire">Nom du bénéficiaire</label>
						<input type="text" name="nomBeneficiaire" value="">					</p>
					<p>
						<label for="telBeneficiaire">Téléphone bénéficiaire</label>
						<input type="text" name="telBeneficiaire" value="">					</p>
		        	<p>
						<label for="type">Type de livraison</label>
													</p><p><input type="radio" name="type" value="1"> Vente à distance</p>
							<p><input type="radio" name="type" value="2" checked="checked"> Au comptoir</p>
							<p class="nodisplay"><input type="radio" name="type" value="0"> Execution</p>
											<p></p>
											<p>
							<label for="externeLiv">Transmetteur</label>
															<input type="radio" name="externeLiv" value="0" checked="checked"> Aucun
														<br><br>
													</p>
												        </div>
			</div>
		</fieldset>


				<p class="with-children-tip float-right with-padding no-bottom-margin">
			<a class="tip-left button lancerCommande" style="cursor: pointer;" title="Sauvegarder-Lancer la commande-Aller au paiement" data-nouveau="false">Lancer et paiement</a>
			<a class="tip-left button lancerCommande" style="cursor: pointer;" title="Sauvegarder-Lancer la commande-Nouveau ticket" data-nouveau="true">Lancer et nouveau</a>
		</p>
		    </div>
</form>

		    <div id="tab-options" style="display: none;">
	        <form class="form" id="formOptions" method="post" action="https://test.caisseenligne.fr/index/options">
	        	<fieldset>
	        		<b>Options gratuites</b><br><br>
		        			        	<div class="clearfix"></div>
				</fieldset>
	        	<fieldset>
	        		<b>Options payantes</b><br><br>
		        			        		<p class="float-left margin-right margin-left grey-bg" style="text-align: center;">
							<label for="option3">Urgent (0.5 (E))</label>
															<input type="checkbox" name="option3" value="0" id="option3" class="switch" style="display: none;"><span class="switch-replace"></span>
													</p>
		        			        	<div class="clearfix"></div>
				</fieldset>
			</form>
	    </div>
	
    <div id="tab-paiements" style="display: none;">
    	<div class="container_12">
	        <div class="grid_6">
									<a class="paiement" href="javascript:;" title="Chèque">CHQ</a>
									<a class="paiement" href="javascript:;" title="ticket restaurant">TRT</a>
									<a class="paiement" href="javascript:;" title="Espèces">ESP</a>
									<a class="paiement" href="javascript:;" title="Carte Bleue">CB</a>
									<a class="paiement" href="javascript:;" title="Virement">VIR</a>
									<a class="paiement" href="javascript:;" title="Débiteur">DEB</a>
									<a class="paiement" href="javascript:;" title="carte de fidélité">FID</a>
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
							<td style="width: 111px;" id="resteAPayer" rel="0">0 (E)</td>
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
	<br>
	<div class="box">
		<h1>Promotion(s) du moment</h1>
				Aucune promotion en ce moment.
			</div>
<iframe id="ifrEnvoi" name="ifrEnvoi" style="width: 0; height: 0; margin: auto; border: 0;"></iframe></div>
</div>
