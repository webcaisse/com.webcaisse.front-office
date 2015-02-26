
<#import "spring.ftl" as spring />
<!doctype html>
<!--[if lt IE 8 ]><html lang="fr" class="no-js ie ie7"><![endif]-->
<!--[if IE 8 ]><html lang="fr" class="no-js ie"><![endif]-->
<!--[if (gt IE 8)|!(IE)]><!-->
<html lang="fr" class="no-js">
<!--<![endif]-->
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<title>WebCaisse 2.0</title>
	<meta name="description" content="">
	<meta name="author" content="a2n Informatique">
	
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.min.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/reset.css' />">
	
	<link rel="stylesheet" type="text/css" 	href="<@spring.url '/css/common.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/form.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/standard.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/block-lists.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/simple-lists.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/calendars.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/redmond.datepick.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/table.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/960.gs.fluid.css' />">
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/dataTable.css' />">
	
	<!-- Favicon -->
	<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>

    
 
  
   
 
	<script type="text/javascript"	src="<@spring.url '/js/jquery.bpopup.min.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/js/main.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/js/panier.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/js/produit.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/js/onglets.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/js/paiement.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/js/client.js' />"></script>
    <script type="text/javascript"  charset="utf-8" src="<@spring.url '/js/dist/dataTables.min.js' />"></script>
		
</head>

<body>
	<#include "common/nav.ftl">
	<!-- Content -->
	<article class="container_12" id="contentD">

		<div class="clearfix"></div>
		<input type="hidden" id="idCommande">
		<div class="container_12">
			<div class="indexAutocomplete grid_8">
				<div class="ui-widget float-left">
					<label class="combobox" style="font-size: 24px;"><img
						src="images/icons/Profile.png" width="24" height="24"
						style="vertical-align: middle;"> Client : </label>
						<input type="text" id="idTelephone">
				</div>
				<button class="addClient float-left"
					style="font-size: 14px; margin: 2px 0 0 50px;">
					<img src="images/icons/fugue/plus-circle.png" width="16" height="16"> Ajouter Client
				</button>
				<div class="clientInfos">
				</div>
			</div>
			<div class="grid_4" style="padding-top: 4px;">
				<!--<div id ="Aemporter"class="button float-left red menu-opener" title="E" tyle="z-index: 2;">
					A emporter
				</div>
				<div  id ="surPlace" class="button float-left red menu-opener" title="P" style="z-index: 2;">
					Sur place
				</div>
				<div id ="livraison" class="button float-left red menu-opener" title="L" style="z-index: 2;">
					Livraison
				</div>-->
				
				<div id ="en_preparation" class="button float-left red menu-opener" style="z-index: 2;height: 37px;width: 136px">
					En pr&eacute;paration
				</div>
				&nbsp;
				<div id ="paiementCommande" class="button float-left red menu-opener" style="z-index: 2;height: 37px;width: 136px;">
					Paiement
				</div>
			</div>
			<div class="clearfix" style="margin-bottom: 20px;"></div>

			<#include "modules/familles.ftl" />

			<#include "modules/panier.ftl">

			<iframe id="ifrImpression" name="ifrImpression"	style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
			<a href="javascript:;" id="barcode" class="selectProduit"
				style="visibility: hidden; width: 0; height: 0; padding: 0; margin: 0;"
				rel="" prix="" libre="" type="" remise="0%"></a>

			<div class="clear"></div>
	</article>

	<!-- End content -->

	<footer>

		<!--div class="float-left">
			<a href="http://caisseenligne.fr/aide" target="_blank" class="button">Help</a>
			<a href="http://caisseenligne.fr/about" target="_blank" class="button">About</a>
		</div-->

		<div class="float-right">
			<a href="http://caisseenligne.fr/aide" target="_blank" class="button">Help</a>
			<a href="http://caisseenligne.fr/about" target="_blank"	class="button">About</a> 
			<a href="#top" class="button">
				<img src="images/icons/fugue/navigation-090.png" width="16" height="16">Page top
			</a>
		</div>


		<#include "modules/popup.ftl"/>
		<#include "modules/product/popup_paiement.ftl"/> 
		<#include "modules/paiement.ftl"/>
		<#include "modules/product/remise.ftl"/>
		<#include "modules/nouvelleClient.ftl"/>
		<#include "modules/note.ftl"/>
		<#include "modules/popup_modeVente.ftl"/>
        <#include "modules/popup_nouveauClient.ftl"/>
        <#include "modules/popup_client.ftl"/>

	</footer>

</body>
</html>