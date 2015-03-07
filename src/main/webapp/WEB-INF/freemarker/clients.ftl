<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">	
   <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css"	href="<@spring.url '/css/dataTable.css' />">
    <script type="text/javascript"  charset="utf-8" src="<@spring.url '/js/dist/dataTables.min.js' />"></script>
   <script>

 $( document ).ready(function() {
	$('#dataTableClients').dataTable();
 });
  </script>
</head>

<body>

	<#include "common/nav.ftl">
	<!-- Content -->	
	<article class="container_12" id="contentD">
		
		<section class=" block-content">
	<h1>Liste des clients <a href="${rc.getContextPath()}/clients/afficherFormulaireClient" class="addClient"><img src="${rc.getContextPath()}/images/icons/fugue/plus-circle.png" width="16" height="16"> ajouter </a> </h1>
	<div class="block-controls">
        <div class="controls-buttons">
        	<a href="${rc.getContextPath()}/clients/exporterClients" class="exporter"> exporter</a>
        </div>
    </div>

	
	<table class="table sortable" cellspacing="0" width="100%" id="dataTableClients">
		<thead>
			<tr>
				<th scope="col" style="width: 510.400001525879px;" class="sorting_asc">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Nom & Prenom
				</th>
				<th scope="col" style="width: 210.400001525879px;" class="sorting">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					 Rue
				</th>
				
				<th scope="col" style="width: 128.400001525879px;" class="sorting">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Téléphone
				</th>
				<th scope="col" class="table-actions sorting_disabled" style="width: 110.400001525879px;">Actions</th>
			</tr>
		</thead>

		

	<tbody>
	
	<#if clients??>
       <#list clients as client>
	   <tr class="odd">
					
				
					<td style="cursor: pointer;" title="" class="voirClient with-tip sorting_1" rel="88">${client.nom!} ${client.prenom!}</td>
					<td style="cursor: pointer;" title="Cliquez pour voir la fiche client" class="voirClient with-tip" rel="88">${client.numeroRue!} ${client.nomRue!}</td>
					<td style="cursor: pointer;" title="Cliquez pour voir la fiche client" class="voirClient with-tip" rel="88">${client.telephone!} </td>
					<td class="table-actions">
					  <a href="${rc.getContextPath()}/clients/afficherUpdateClient/${client.id}" title="Modifier" class="with-tip editClient" rel="88"><img src="${rc.getContextPath()}/images/icons/fugue/pencil.png" width="16" height="16"></a>
					  <a href="${rc.getContextPath()}/clients/supprimerClient/${client.id}" title="Supprimer" class="with-tip deleteClient" rel="88" onclick="return confirm('Voulez vous vraiment supprimer ce produit?')" ><img src="${rc.getContextPath()}/images/icons/fugue/delete.png" width="16" height="16"></a>
					</td>
				
	     </tr>
	</#list>
	</#if>
	</tbody></div>
</section>
<iframe id="ifrExport" name="ifrExport" style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
		<div class="clear"></div>

	</article>
	<footer>
	</footer>

</body>
</html>