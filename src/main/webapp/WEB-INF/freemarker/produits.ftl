<#import "spring.ftl" as spring />
<head>
	<#include "modules/head.ftl">
	<link rel="stylesheet" href="<@spring.url '/css/themes/default/style.min.css' />" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<@spring.url '/js/dist/jstree.min.js' />"></script>
</head>

<body>

	<#include "common/nav.ftl">
	<article class="container_12" id="contentD">

		<section class=" block-content">
			<h1>Liste des familles <a href="${rc.getContextPath()}/produits/afficherFormulaireFamille" class="addFamille"><img src="${rc.getContextPath()}/images/icons/fugue/plus-circle.png" width="16" height="16"> ajouter</a></h1>
			<div class="no-margin last-child">
				<div class="block-controls">
					<div class="controls-buttons">
						<div class="sub-hover paging_two_button">
							<div class="control-prev disabled" title="Précédent"></div>
							<div class="control-next disabled" title="Suivant"></div>
						</div>
					</div>
				</div>
				<div class="block-footer clearfix filter">
					<div class="float-left">
						Afficher 
						<select size="1">
							<option value="10">10</option>
							<option value="25">25</option>
							<option value="50">50</option>
							<option value="100" selected="selected">100</option>
						</select> éléments
					</div>
					<div class="float-right">
						Rechercher : <input type="text">
					</div>
				</div>
				<table class="table sortable" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th scope="col" style="width: 510.400001525879px;"	class="sorting_asc">
							<span class="column-sort"> 
								<a	href="#" title="Sort up" class="sort-up"></a> 
								<a href="#"	title="Sort down" class="sort-down"></a>
							</span> Libelle</th>
							<th scope="col" class="table-actions sorting_disabled"	style="width: 100px;">Actions</th>
						</tr>
					</thead>
					<tbody>
						<#list familles as famille>
						<tr class="odd">
							<td style="cursor: pointer;" title="" class="voirClient with-tip sorting_1" rel="88">${famille.libelle}</td>
							<td style="cursor: pointer;" title="Cliquez pour voir la fiche client"	class="voirClient with-tip" rel="88">
								<a href="${rc.getContextPath()}/produits/listeProduits?idFamilly=${famille.id}">Afficher Produits</a>
							</td>
							
							<td style="cursor: pointer;" title="Supprimer famille"	class="voirClient with-tip" rel="88">
									<a href="${rc.getContextPath()}/produits/supprimerFamille/${famille.id}" onclick="return confirm('Voulez vous vraiment supprimer cette Famille?')" ><img src="${rc.getContextPath()}/images/icons/fugue/delete.png"></a>
								    
							</td>
							<td td style="cursor: pointer;" title="Update famille"	class="voirClient with-tip" rel="88">
								<a href="${rc.getContextPath()}/produits/afficherUpdateFamille/${famille.id}"><img src="${rc.getContextPath()}/images/icons/fugue/pencil.png"></a>
                             </td>	
						</tr>
						</#list>
					</tbody>
				</table>
				<div class="message no-margin">
					Affichage de l'élement 1 à 5 sur 5 éléments
				</div>
			</div>
		</section>
		<iframe id="ifrExport" name="ifrExport" style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
		<div class="clear"></div>

	</article>
	<footer> </footer>

</body>
</html>