<#import "spring.ftl" as spring />
<head>
	<#include "modules/head.ftl">
	<link rel="stylesheet"	href="<@spring.url '/css/themes/default/style.min.css' />" />
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/dataTable.css' />">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<@spring.url '/js/dist/jstree.min.js' />"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	
    <script type="text/javascript"  charset="utf-8" src="<@spring.url '/js/dist/dataTables.min.js' />"></script>
   <script>

 $( document ).ready(function() {
	$('#dataTableListProduits').dataTable();
 });
  </script>
</head>

<body>
	<#include "common/nav.ftl">
	<article class="container_12" id="contentD">

		<section class=" block-content">
			<h1>Liste des produits <a href="${rc.getContextPath()}/produits/afficherFormulaireProduit/${idFamilly}" class="addProduct"><img src="${rc.getContextPath()}/images/icons/fugue/plus-circle.png" width="16" height="16"> ajouter</a></h1>
			<br><br>
				<table class="table sortable" cellspacing="0" width="100%" id="dataTableListProduits">
					<thead>

						<tr>
							<th scope="col" style="width: 510.400001525879px;"
								class="sorting_asc"><span class="column-sort"> <a
									href="#" title="Sort up" class="sort-up"></a> 
									<a href="#"	title="Sort down" class="sort-down"></a>
							</span> Libelle</th>
							<th scope="col" class="table-actions sorting_disabled"
								style="width: 110.400001525879px;">Code</th>
						     <th scope="col" class="table-actions sorting_disabled"
								style="width: 110.400001525879px;">Actions</th>		
						</tr>
					</thead>
					<tbody>
						<#if produits??>
							<#list produits as produit>
							<tr class="odd">
								<td style="cursor: pointer;" title="" class="voirClient with-tip sorting_1" rel="88">${produit.libelle!}</td>
								<td style="cursor: pointer;" title="" class="voirClient with-tip sorting_1" rel="88">${produit.code!}</td>
								<td class="table-actions">
									<a href="${rc.getContextPath()}/produits/supprimerProduit/${produit.id}" onclick="return confirm('Voulez vous vraiment supprimer ce produit?')" title="update produit"	class="voirClient with-tip" rel="88" ><img src="${rc.getContextPath()}/images/icons/fugue/delete.png"></a>
								    <a href="${rc.getContextPath()}/produits/afficherUpdateProduct/${produit.id}" title="Modifier" class="with-tip editClient" rel="88"><img src="${rc.getContextPath()}/images/icons/fugue/pencil.png"></a>
								</td>
													
							</tr>
							
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
		</section>
		<iframe id="ifrExport" name="ifrExport"
			style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
		<div class="clear"></div>

	</article>

	<footer> </footer>

	<footer>
</footer></br>

</body>
</html>