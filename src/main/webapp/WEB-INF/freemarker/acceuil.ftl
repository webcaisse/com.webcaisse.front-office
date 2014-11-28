<#import "spring.ftl" as spring />
<!doctype html>
<!--[if lt IE 8 ]><html lang="fr" class="no-js ie ie7"><![endif]-->
<!--[if IE 8 ]><html lang="fr" class="no-js ie"><![endif]-->
<!--[if (gt IE 8)|!(IE)]><!--><html lang="fr" class="no-js"><!--<![endif]-->
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

	<title>WebCaisse 2.0</title>
	<meta name="description" content="">
	<meta name="author" content="a2n Informatique">
	
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.min.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/reset.css' />">
	
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/common.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/form.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/standard.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/block-lists.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/simple-lists.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/calendars.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/redmond.datepick.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/table.css' />">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/960.gs.fluid.css' />">
	
	
	<!-- Favicon -->
	<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	
		
	  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
      <script type="text/javascript" src="<@spring.url '/js/jquery.bpopup.min.js' />"></script>
      <script type="text/javascript" src="<@spring.url '/js/main.js' />"></script>
      <script type="text/javascript" src="<@spring.url '/js/panier.js' />"></script>
      <script type="text/javascript" src="<@spring.url '/js/produit.js' />"></script>
      <script type="text/javascript" src="<@spring.url '/js/onglets.js' />"></script>
      <script type="text/javascript" src="<@spring.url '/js/paiement.js' />"></script>
     
      
  

	<!-- script src="/js/libs/modernizr.custom.min.js"></script-->

</head>

<body>
	<#include "common/nav.ftl">
	<!-- Content -->
	<article class="container_12" id="contentD">
		
	<div class="clearfix"></div>
	<div class="container_12">
	<div class="indexAutocomplete grid_8">
		<div class="ui-widget float-left">
			<label class="combobox" style="font-size: 24px;"><img src="images/icons/Profile.png" width="24" height="24" style="vertical-align: middle;"> Client : </label>
			<select id="combobox" am-focus="0">
				<option value="2" selected="selected">Anonyme</option>
															<option value="75">Alexis </option>
																													<option value="76">GANA SAMY</option>
																				<option value="73">Mansouri Hocine</option>
																				<option value="74">Mansouri Hocine</option>
																				<option value="72">Mansouri Hocine</option>
																				<option value="70">Mansouri Leila</option>
																				<option value="71">Mansouri Sara</option>
																				<option value="1">Perte </option>
												</select>
		</div>
					<button class="addClient float-left" style="font-size: 14px; margin: 2px 0 0 50px;"><img src="images/icons/fugue/plus-circle.png" width="16" height="16"> Nouveau</button>
			</div>
	<div class="grid_4" style="padding-top: 4px;">
		<div class="button menu-opener float-right" style="z-index: 2; margin-left: 5px;">
							Gestion ticket
						<div class="menu-arrow">
				<img src="images/menu-open-arrow.png" width="16" height="16">
			</div>
			<div class="menu">
				<ul class="reverted">
					<li class="icon_delete"><a href="javascript:;" class="razFacture" title="Effacer ticket en cours">Effacer ticket en cours</a></li>
					<li class="icon_save"><a href="javascript:;" class="saveFacture" data-type="0" title="Enregistrer ticket et nouveau">Enregistrer et nouveau</a></li>
											<li class="icon_doc_web"><a href="javascript:;" class="saveDevis" data-type="0" title="Transformer en devis et imprimer">Transformer en devis</a></li>
										<li class="icon_load">
													<a href="javascript:;" title="Charger un ticket">Charger un ticket</a>
							<ul>
																																						<li>08/08/2014</li>
																																																									<li class="icon_doc_web"><a href="javascript:;" class="editCaisse" rel="321">16:15:25 - sara mansouri</a></li>
																																															<li>22/07/2014</li>
																																																									<li class="icon_doc_web"><a href="javascript:;" class="editCaisse" rel="279">18:26:48 - </a></li>
																																																						<li class="icon_doc_web"><a href="javascript:;" class="editCaisse" rel="270">12:57:04 - </a></li>
																																															<li>19/07/2014</li>
																																																									<li class="icon_doc_web"><a href="javascript:;" class="editCaisse" rel="251">08:09:25 - Table 11</a></li>
																																																						<li class="icon_doc_web"><a href="javascript:;" class="editCaisse" rel="249">07:04:52 - 4</a></li>
																								</ul>
											</li>
											<li class="icon_print"><a href="javascript:;" class="imprimerBon" title="Imprimer bon">Imprimer bon</a></li>
									</ul>
			</div>
		</div>
					<div class="button float-right red menu-opener" style="z-index: 2;">
				Gestion caisse
				<div class="menu-arrow">
					<img src="images/menu-open-arrow.png" width="16" height="16">
				</div>
				<div class="menu">
					<ul class="reverted">
						<li class="icon_dot"><a href="https://test.caisseenligne.fr/index/open" title="Modifier fond de caisse de départ">Fond de caisse départ</a></li>
						<li class="icon_dot">
															<a href="javascript:;" class="releveCaisse" data-id="0" data-idfont="168" title="Nouveau relevé de caisse">Relevé de caisse</a>
													</li>
						<li class="icon_dot"><a href="https://test.caisseenligne.fr/index/close" title="Entrer le fond de caisse de fin">Fermer la caisse</a></li>
					</ul>
				</div>
			</div>
			</div>
	<div class="clearfix" style="margin-bottom: 20px;"></div>

	<#include "modules/familles.ftl" />

	<div class="float-right" style="margin-right: 20px; text-align: right;">
    	<small>
    		CA : 0 (E)			NB tickets : 0			Ticket moyen : 0 (E)	    	    	</small>
	</div>
	<#include "modules/panier.ftl">

<iframe id="ifrImpression" name="ifrImpression" style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
		<a href="javascript:;" id="barcode" class="selectProduit" style="visibility: hidden; width: 0; height: 0; padding: 0; margin: 0;" rel="" prix="" libre="" type="" remise="0%"></a>

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
			<a href="http://caisseenligne.fr/about" target="_blank" class="button">About</a>
			<a href="#top" class="button"><img src="images/icons/fugue/navigation-090.png" width="16" height="16"> Page top</a>
		</div>
		
		
	<#include "modules/popup.ftl"/>
	<#include "modules/product/popup_paiement.ftl"/>
	<#include "modules/product/remise.ftl"/>
		
	</footer>

</body>
</html>