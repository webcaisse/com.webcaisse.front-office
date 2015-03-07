<#import "spring.ftl" as spring />
<head>
	<#include "modules/head.ftl">
	<link rel="stylesheet" href="<@spring.url '/css/themes/default/style.min.css' />" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<@spring.url '/js/dist/jstree.min.js' />"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/dataTable.css' />">
    <script type="text/javascript"  charset="utf-8" src="<@spring.url '/js/dist/dataTables.min.js' />"></script>
   <script>

 $( document ).ready(function() {
	$('#dataTableProduits').dataTable();
 });
  </script>
</head>

<body>

	<#include "common/nav.ftl">
	<article class="container_12" id="contentD">

		<section class=" block-content">
			<h1>Liste des familles <a href="${rc.getContextPath()}/produits/afficherFormulaireFamille" class="addFamille"><img src="${rc.getContextPath()}/images/icons/fugue/plus-circle.png" width="16" height="16"> ajouter</a></h1>
			<br><br>
				<table class="table sortable" cellspacing="0" width="100%" id="dataTableProduits">
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
						<#if familles??> 
							<#list familles as famille>
							<tr class="odd">
								<td style="cursor: pointer;" title="" class="voirClient with-tip sorting_1" rel="88">${famille.libelle}</td>
								<td class="table-actions">
								    <a href="${rc.getContextPath()}/produits/listeProduits?idFamilly=${famille.id}" title="Afficher produits" class="voirClient with-tip" rel="88"><img src="${rc.getContextPath()}/images/icons/fugue/detailsCommande.png" width="35"></a>
									<a href="${rc.getContextPath()}/produits/supprimerFamille/${famille.id}" onclick="return confirm('Voulez vous vraiment supprimer cette Famille?')" title="Supprimer famille" class="voirClient with-tip" rel="88"><img src="${rc.getContextPath()}/images/icons/fugue/delete.png"></a>
									<a href="${rc.getContextPath()}/produits/afficherUpdateFamille/${famille.id}" title="Update famille" class="voirClient with-tip" rel="88"><img src="${rc.getContextPath()}/images/icons/fugue/pencil.png"></a>
	                             </td>	
							</tr>
							</#list>
						</#if>
					</tbody>
				</table>
				
			</div>
		</section>
		<iframe id="ifrExport" name="ifrExport" style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
		<div class="clear"></div>

	</article>
	<footer> </footer>

</body>
</html>