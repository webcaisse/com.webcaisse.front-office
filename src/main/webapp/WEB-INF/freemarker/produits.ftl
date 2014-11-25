<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">	
<link rel="stylesheet" href="<@spring.url '/css/themes/default/style.min.css' />" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<@spring.url '/js/dist/jstree.min.js' />"></script>
<script>
$(function () { $('#jstree').jstree(); 

$('#jstree').on("changed.jstree", function (e, data) {
      alert(data.selected);
    });
     $('button').on('click', function () {
      $('#jstree').jstree(true).select_node('child_node_1');
      $('#jstree').jstree('select_node', 'child_node_1');
      $.jstree.reference('#jstree').select_node('child_node_1');
    });
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
				<li class="tip-bottom settings margin-left"><a href="https://test.caisseenligne.fr/parametres" title="Paramètres">Paramètres</a></li>
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
										<li class="icon_export"><a href="https://test.caisseenligne.fr/index/dump" title="Sauvegarder la base de donnée">Sauvegarder</a></li>
										<li class="icon_import"><a href="https://test.caisseenligne.fr/index/restaurer" title="Restaurer la base de donnée">Restaurer</a></li>
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
									<li class="icon_dot"><a href="https://test.caisseenligne.fr/access/logout" title="Déconnexion"><span class="smaller">DECONNEXION</span></a></li>
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
  <div id="jstree">
    <!-- in this example the tree is populated from inline HTML -->
    <ul>
      <li>Root node 1
        <ul>
          <li id="child_node_1">Child node 1</li>
          <li>Child node 2</li>
        </ul>
      </li>
      <li>Root node 2</li>
    </ul>
  </div>
	<button>demo button</button>
	<footer>
	</footer>

</body>
</html>