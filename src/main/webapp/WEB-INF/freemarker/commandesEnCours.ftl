<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>	<script>
  $(function() {
    $( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy" });
  });
  </script>
</head>

<body>
	<!-- Header -->
	<#include "common/nav.ftl" >	
	<article class="container_12" id="contentD">
		
	<section class=" block-content">
	<#assign dateExport="DATE_VIDE" />
	<#if dateCommande??>
		<#assign dateExport=dateCommande />
	</#if>
	<h1>Liste des commandes <a href="${rc.getContextPath()}/commandes/exporterCommande/${dateExport!}/" class="exporter"> exporter</a></h1>
	
	<div class="no-margin last-child">
		<div class="block-controls">
			<div class="controls-buttons">
				<div class="sub-hover paging_two_button">
					<div class="control-prev disabled" title="Pr�c�dent">
				</div>
				<div class="control-next disabled" title="Suivant"></div>
			</div>
		</div>
	</div>
	<div class="block-footer clearfix filter">
		<div class="float-left">Afficher 
			<select size="1">
				<option value="10">10</option>
				<option value="25">25</option>
				<option value="50">50</option>
				<option value="100" selected="selected">100</option>
			</select> �l�ments
		</div>
		<div class="float-right">
			<form action="${rc.getContextPath()}/commandes/rechercherCommande">Rechercher : 
				<input type="text" id="datepicker" name="dateCommande" value="${dateCommande!}" style="position: relative; z-index: 100000;">
		 		<input type="submit" value="Actualiser">
		 	</form> 
		</div>
	</div>
	<table class="table sortable" cellspacing="0" width="100%">
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
		       <td>
		       		<#if commande.dateCommande??>
			       		${commande.dateCommande?string["dd/MM/yyyy HH:mm"]}
		       		<#else>
		       			Inconnu
		       		</#if>
		       	</td>
		       <td>
		       		<#if commande.etat??>
		       			<#if commande.etat="L">
		       				Livraison
						<#elseif commande.etat="E">
		       				A emporter
			       		<#else>
			       			Sur place
			       		</#if>
		       		<#else>
		       			Inconnu
		       		</#if>
		       </td>
		       <td>${commande.libelleProduit}</td>
		        <td><a href="${rc.getContextPath()}/commandes/details/${commande.id}" title="details">details</a></td>
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