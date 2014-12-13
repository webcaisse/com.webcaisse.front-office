<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">	
</head>

<body>

	<#include "common/nav.ftl">
	<!-- Content -->	
	<article class="container_12" id="contentD">
		
		<section class=" block-content">
	<h1>Liste des clients <a href="javascript:;" class="addClient"><img src="images/icons/fugue/plus-circle.png" width="16" height="16"> ajouter </a> </h1>
	<div class="block-controls">
        <div class="controls-buttons">
        	<a href="${rc.getContextPath()}/clients/exporterClients" class="exporter"> exporter</a>
        </div>
    </div>

	<div class="no-margin last-child"><div class="block-controls"><div class="controls-buttons"><div class="sub-hover paging_two_button"><div class="control-prev disabled" title="Précédent"></div><div class="control-next disabled" title="Suivant"></div></div></div></div><div class="block-footer clearfix filter"><div class="float-left">Afficher <select size="1"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100" selected="selected">100</option></select> éléments</div><div class="float-right">Rechercher : <input type="text"></div></div><table class="table sortable" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th scope="col" style="width: 510.400001525879px;" class="sorting_asc">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Nom
				</th>
				<th scope="col" style="width: 210.400001525879px;" class="sorting">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Prénom
				</th>
				<th scope="col" style="width: 258.400001525879px;" class="sorting">
					<span class="column-sort">
						<a href="#" title="Sort up" class="sort-up"></a>
						<a href="#" title="Sort down" class="sort-down"></a>
					</span>
					Email
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
					
				
					<td style="cursor: pointer;" title="" class="voirClient with-tip sorting_1" rel="88">${client.nom}</td>
					<td style="cursor: pointer;" title="Cliquez pour voir la fiche client" class="voirClient with-tip" rel="88">${client.prenom}</td>
					<td style="cursor: pointer;" title="Cliquez pour voir la fiche client" class="voirClient with-tip" rel="88">
						<a href="mailto:jessica4954@gmail.com" title="Envoyer un email">${client.email}</a>
					</td>
					<td style="cursor: pointer;" title="Cliquez pour voir la fiche client" class="voirClient with-tip" rel="88">${client.telephone} </td>
					<td class="table-actions">
					  <a href="javascript:;" title="Modifier" class="with-tip editClient" rel="88"><img src="images/icons/fugue/pencil.png" width="16" height="16"></a>
					  <a href="javascript:;" title="Supprimer" class="with-tip deleteClient" rel="88"><img src="images/icons/fugue/cross-circle.png" width="16" height="16"></a>
					</td>
				
	     </tr>
	</#list>
	</#if>
	</tbody></table><div class="message no-margin">Affichage de l'élement 1 à 5 sur 5 éléments</div></div>
</section>
<iframe id="ifrExport" name="ifrExport" style="width: 0; height: 0; margin: auto; border: 0;"></iframe>
		<div class="clear"></div>

	</article>
	<footer>
	</footer>

</body>
</html>