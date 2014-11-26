<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>	<script>
  $(function() {
    $( "#datepicker" ).datepicker({ dateFormat: "dd/mm/yy" });
  });
  </script>
</head>

<body data-base="https://test.caisseenligne.fr">
	<!-- Header -->
<div id="headerHide">
		<!-- Server status -->
		<header><div class="container_12">
		</div>
		</header>
		<!-- End server status -->

		<!-- Main nav -->
		<nav id="main-nav">
			<!--div id="resumeContainer" style="width: 48px; height: 48px;">
				<div id="resume"><img src="images/icons/web-app/48/Info.png"></div>
			</div-->
			<ul class="container_12">
				<li class="tip-bottom home current">
					<a href="https://test.caisseenligne.fr" title="Caisse">Caisse</a>
				</li>
				<li class="tip-bottom commandes">
					<a href="https://test.caisseenligne.fr/commandes" title="Commandes en cours">Commandes</a>
				</li>
				<li class="tip-bottom planningNav"><a href="https://test.caisseenligne.fr/planning" title="Gestion du planning">Planning</a></li>
				<li class="tip-bottom users">
					<a href="https://test.caisseenligne.fr/clients" title="Gestion des clients">Clients</a>
				</li>
				<li class="tip-bottom fournisseurs"><a href="https://test.caisseenligne.fr/fournisseurs" title="Gestion des fournisseurs">Fournisseurs</a></li>
				<li class="tip-bottom products"><a href="https://test.caisseenligne.fr/produits" title="Gestion des produits">Produits</a></li>
				<li class="tip-bottom promotions"><a href="https://test.caisseenligne.fr/promotions" title="Gestion des promotions">Promotions</a></li>
				<li class="tip-bottom caisse margin-right"><a href="https://test.caisseenligne.fr/caisse" title="Gestion de la caisse">Caisse</a></li>
				<li class="tip-bottom settings margin-left"><a href="https://test.caisseenligne.fr/parametres" title="Param�tres">Param�tres</a></li>
				<li class="tip-bottom stats"><a href="https://test.caisseenligne.fr/stats" title="Statistiques">Statistiques</a></li>
		</ul>

		<div class="pull-right" style="padding-top: 5px; padding-right: 15px;">

				<ul id="status-infos" class="float-right">
											<li>
							<div class="button menu-opener">
								<img src="images/icons/fugue/database.png">
								<div class="menu-arrow">
									<img src="images/menu-open-arrow.png" width="16" height="16">
								</div>
								<div class="menu">
									<ul>
										<li class="icon_export"><a href="https://test.caisseenligne.fr/index/dump" title="Sauvegarder la base de donn�e">Sauvegarder</a></li>
										<li class="icon_import"><a href="https://test.caisseenligne.fr/index/restaurer" title="Restaurer la base de donn�e">Restaurer</a></li>
									</ul>
								</div>
							</div>
						</li>
																<li>
							<a href="javascript:;" class="button" title="Alertes de stock"><img src="images/icons/fugue/balloon.png" width="16" height="16"> <strong id="nbAlertes"></strong></a>
							<div class="result-block">
								<ul class="small-files-list icon-warning" id="alertesListe"></ul>
							</div>
						</li>
																<li>
							<a href="javascript:;" class="button" title="Planning"><img src="images/icons/fugue/calendar-day.png" width="16" height="16"> <strong id="nbPlanning"></strong></a>
							<div class="result-block">
								<table cellspacing="0" class="list-calendar">
								    <tbody id="planningListe">
								    </tbody>
								</table>
							</div>
						</li>
										<li>
						<a href="javascript:;" class="button" title="Envoyez un message" id="sendMail"><img src="images/icons/fugue/mail.png" width="16" height="16"></a>
					</li>
					<li>
						<div class="button red menu-opener">
							<b>ADMIN</b>
							<div class="menu-arrow">
								<img src="images/menu-open-arrow.png" width="16" height="16">
							</div>
							<div class="menu">
								<ul class="reverted">
									<li class="icon_dot"><a href="https://test.caisseenligne.fr/access/logout" title="D�connexion"><span class="smaller">DECONNEXION</span></a></li>
									<li class="sep"></li>
									<li class="icon_dot"><a href="javascript:;" title="Changer d'utilisateur">Changer d'utilisateur</a></li>
																																																																											
																	</ul>
							</div>
						</div>
					</li>
				</ul>
				<p class="float-right" style="line-height: 30px; background: -moz-linear-gradient(center top , white, #E5E5E5 88%, #D8D8D8) repeat scroll 0 0 transparent; border-radius: 5px 5px 0 0; padding: 0 10px 0 10px; margin: 6px 20px 0 0;">
					16/08/2014 ()
				</p>

				
			</div>
		</nav>
		<!-- End main nav -->

		<div id="sub-nav">

		</div>

		<div id="header-shadow"></div>

	</div>
	
	<article class="container_12" id="contentD">
		
		<section class=" block-content">
	<h1>Liste des commandes</h1>

	<div class="no-margin last-child"><div class="block-controls"><div class="controls-buttons"><div class="sub-hover paging_two_button"><div class="control-prev disabled" title="Pr�c�dent"></div><div class="control-next disabled" title="Suivant"></div></div></div></div><div class="block-footer clearfix filter"><div class="float-left">Afficher <select size="1"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100" selected="selected">100</option></select> �l�ments</div><div class="float-right">Rechercher : <input type="text" id="datepicker" style="position: relative; z-index: 100000;">
	<a href="javascript:;" class="addClient">Actualiser</a>
	</div></div><table class="table sortable" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th scope="col" style="width: 149.400001525879px;" class="sorting_desc">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Date
				</th>
				<th scope="col" style="width: 99.400001525879px;" class="sorting">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Etat
				</th>
				<th scope="col" style="width: 149.400001525879px;" class="sorting">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Libell� Produits
				</th>
			
				<th scope="col" class="table-actions sorting_disabled" style="width: 269.400001525879px;">Actions</th>
			</tr>
		</thead>

		

	<tbody id="commandesTbody">
	   <#if commandes??>
		   <#list commandes as commande>
		     <tr class="odd">
		       <td>${(commande.dateCommande?date)!}</td>
		       <td>0</td>
		       <td>${commande.libelleProduit}</td>
		        <td>0</td>
		      </tr>
		   </#list>   
	   </#if>
	 </tbody>
	 </table>
	 <div class="message no-margin">Affichage de l'�lement 0 � 0 sur 0 �l�ments</div></div>
	<iframe id="ifrImpression" name="ifrImpression" style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
</section>
		<div class="clear"></div>

	</article>
	<footer>
	</footer>

</body>
</html>