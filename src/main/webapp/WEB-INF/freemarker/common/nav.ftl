<div id="headerHide">
	<!-- Server status -->
	<header>
		<div class="container_12"></div>
	</header>
	<!-- End server status -->

	<!-- Main nav -->
	<nav id="main-nav">
		<ul class="container_12">
			<li class="tip-bottom home current">
				<a href="${rc.getContextPath()}/" title="Caisse">Caisse</a>
			</li>
			<li class="tip-bottom commandes">
				<a href="${rc.getContextPath()}/commandes/enCours" title="Commandes en cours">Commandes</a>
			</li>
			<!-- <li class="tip-bottom planningNav">
				<a href="#" title="Gestion du planning">Planning</a>
			</li> -->
			<li class="tip-bottom users">
				<a href="${rc.getContextPath()}/clients/afficher" title="Gestion des clients">Clients</a>
			</li>
			<!-- <li class="tip-bottom fournisseurs">
				<a href="#" title="Gestion des fournisseurs">Fournisseurs</a>
			</li> -->
			<li class="tip-bottom products">
				<a href="${rc.getContextPath()}/produits/afficher" title="Gestion des produits">Produits</a>
			</li>
			<!-- <li class="tip-bottom promotions">
				<a href="#" title="Gestion des promotions">Promotions</a>
			</li> -->
			<li class="tip-bottom settings margin-left">
				<a href="${rc.getContextPath()}/parametrage/afficher" title="Paramètres">Paramètres</a>
			</li>
			<li class="tip-bottom stats">
				<a href="${rc.getContextPath()}/statistique/afficher" title="Statistiques">Statistiques</a>
			</li>
		</ul>
	<div class="pull-right" style="padding-top: 5px; padding-right: 15px;">
		<ul id="status-infos" class="float-right">
			<!--<li>
				<div class="button menu-opener">
					<img src="images/icons/fugue/database.png">
					<div class="menu-arrow">
						<img src="images/menu-open-arrow.png" width="16" height="16">
					</div>
					<div class="menu">
						<ul>
							<li class="icon_export">
								<a href="https://test.caisseenligne.fr/index/dump" title="Sauvegarder la base de donnée">Sauvegarder</a>
							</li>
							<li class="icon_import">
								<a href="https://test.caisseenligne.fr/index/restaurer" title="Restaurer la base de donnée">Restaurer</a>
							</li>
						</ul>
					</div>
				</div>
			</li>
			<li>
				<a href="javascript:;" class="button" title="Alertes de stock">
					<img src="images/icons/fugue/balloon.png" width="16" height="16"> <strong id="nbAlertes"></strong>
				</a>
				<div class="result-block">
					<ul class="small-files-list icon-warning" id="alertesListe"></ul>
				</div>
			</li>
			<li>
				<a href="javascript:;" class="button" title="Planning">
					<img src="images/icons/fugue/calendar-day.png" width="16" height="16"> <strong id="nbPlanning"></strong>
				</a>
				<div class="result-block">
					<table cellspacing="0" class="list-calendar">
					    <tbody id="planningListe">
					    </tbody>
					</table>
				</div>
			</li>
			<li>
				<a href="javascript:;" class="button" title="Envoyez un message" id="sendMail">
					<img src="images/icons/fugue/mail.png" width="16" height="16">
				</a>
			</li>-->
			<li>
				<div class="button red menu-opener">
					<b>ADMIN</b>
					<div class="menu-arrow">
						<img src="${rc.getContextPath()}/images/menu-open-arrow.png" width="16" height="16">
					</div>
					<div class="menu">
						<ul class="reverted">
							<li class="icon_dot">
								<a href="${rc.getContextPath()}/logout" title="Déconnexion">
									<span class="smaller">DECONNEXION</span>
								</a>
							</li>
							<li class="sep"></li>
							<li class="icon_dot">
								<a href="javascript:;" title="Changer d'utilisateur">Changer d'utilisateur</a>
							</li>
						</ul>
					</div>
				</div>
			</li>
		</ul>
		<p class="float-right" style="line-height: 30px; background: -moz-linear-gradient(center top , white, #E5E5E5 88%, #D8D8D8) repeat scroll 0 0 transparent; border-radius: 5px 5px 0 0; padding: 0 10px 0 10px; margin: 6px 20px 0 0;">
			${.now?date} (${.now?time})
		</p>
		</div>
	</nav>
	<!-- End main nav -->

	<div id="sub-nav">
	</div>
	<div id="header-shadow"></div>
	</div>
