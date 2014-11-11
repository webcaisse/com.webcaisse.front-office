	
	    <div id="tab-infos" style="display: none;">
	        <input type="hidden" name="livraison" id="livraison" value="0">
	        <fieldset id="fieldsetTransmission" style="display: block;">
	        	<legend>Informations de transmition</legend>
	        	<div class="contaner_12">
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
							<input type="hidden" value="" name="fichier">Aucun fichier joint
														
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
							<input type="text" name="fraisTransmission" value="">					
						</p>
						<p>
							<label for="reference">Référence commande</label>
							<input type="text" name="reference" value="">					
						</p>
						<p>
							<label for="telTransmission">Numéro de téléphone</label>
							<input type="text" name="telTransmission" value="">					
						</p>
			        </div>
				</div>
	       	</fieldset>
	
			<fieldset id="fieldsetLivraison" style="display: block;">
				<legend>Informations de livraison/commande</legend>
				<div class="container_12">
			    	<div class="grid_12" id="factureAdresse">
						<p>
							<label for="adresse">Saisissez une adresse</label>
							<a href="javascript:;" class="button float-right adresseSearch">Chercher et calculer frais de livraison...</a>
							<a href="javascript:;" class="button float-right newAdresseSearch" style="display: none;">Nouvelle recherche</a>
							<input type="text" name="adresse" value="" class="large inputAdresseClient">						
							<img src="images/info-loader.gif" width="16" height="11" alt="Chargement" style="display: none;" class="adresseLoader">
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
								<input type="radio" name="livreur" value="4"> Coco </input>
								<input type="radio" name="livreur" value="5"> mkjlkjlkj	</input>
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
							<input type="text" name="telClient" value="">					
						</p>
			        </div>
			        <div class="grid_6">
						<p>
							<label for="nomBeneficiaire">Nom du bénéficiaire</label>
							<input type="text" name="nomBeneficiaire" value=""/>					
						</p>
						<p>
							<label for="telBeneficiaire">Téléphone bénéficiaire</label>
							<input type="text" name="telBeneficiaire" value=""/>					
						</p>
			        	<p>
							<label for="type">Type de livraison</label>
														
								</p><p><input type="radio" name="type" value="1"/> Vente à distance</p>
								<p><input type="radio" name="type" value="2" checked="checked"/> Au comptoir</p>
								<p class="nodisplay"><input type="radio" name="type" value="0"/> Execution</p>
												<p></p>
						<p>
								<label for="externeLiv">Transmetteur</label>
								<input type="radio" name="externeLiv" value="0" checked="checked"/> Aucun<br><br>
															
						</p>
			      </div>
				</div>
			</fieldset>
	
	
			<p class="with-children-tip float-right with-padding no-bottom-margin">
				<a class="tip-left button lancerCommande" style="cursor: pointer;" title="Sauvegarder-Lancer la commande-Aller au paiement" data-nouveau="false">Lancer et paiement</a>
				<a class="tip-left button lancerCommande" style="cursor: pointer;" title="Sauvegarder-Lancer la commande-Nouveau ticket" data-nouveau="true">Lancer et nouveau</a>
			 </p>
	 </div>