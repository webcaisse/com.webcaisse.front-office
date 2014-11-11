	 <div class="grid_7" id="facture">
	<!-- Use the class js-tabs to enable JS tabs script -->
	<ul class="tabs js-tabs same-height">
	    <li class="current"><a href="#tab-liste">Liste des achats</a></li>
	    <li> <a href="#tab-infos">Informations</a></li>
	    <li><a href="#tab-options">Options</a></li>
	    <li><a href="#tab-paiement">Paiement</a></li>
	</ul>
	
	<div class="tabs-content">
		   <form class="form" id="formInfoPaiement" method="post" action="https://test.caisseenligne.fr/index/infos">
			 <div id="infosClient" style="height: 2em; font-size: 1em; margin-bottom: 10px;">
			   		    <span style="font-size: 0.7em;"></span>	
				        <input type="hidden" id="commandeState" value="aucune">
			<div style="float: right; color: blue; padding-right: 20px;">Total : <span id="total">0 (E)</span> </div>
			<div class="clearfix"></div>
			 </div>
	         </form>
	<#include "listeAchats.ftl"/>
	
	<#include "info.ftl"/>
	
	<#include "options.ftl"/>
		
	<#include "paiement.ftl"/>
	 	
		<br>
		<div class="box">
			<h1>Promotion(s) du moment</h1>
					Aucune promotion en ce moment.
		</div>
	<iframe id="ifrEnvoi" name="ifrEnvoi" style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
	</div>
	</div>
