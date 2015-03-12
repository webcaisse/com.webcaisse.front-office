<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">
<link rel="stylesheet"
	href="<@spring.url '/css/themes/default/style.min.css' />" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="<@spring.url '/css/dataTable.css' />">

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script type="text/javascript"
	src="<@spring.url '/js/jquery.bpopup.min.js' />"></script>
<script type="text/javascript" src="<@spring.url '/js/paiement.js' />"></script>
<script type="text/javascript" src="<@spring.url '/js/commande.js' />"></script>
<script type="text/javascript" charset="utf-8"
	src="<@spring.url '/js/dist/dataTables.min.js' />"></script>
<script>
	$(function() {
		$("#dateDebut").datepicker({
			dateFormat : "dd-mm-yy"
		});
		
		$("#dateFin").datepicker({
			dateFormat : "dd-mm-yy"
		});
	});
</script>
</head>

<body>
	<!-- Header -->
	<#include "common/nav.ftl" >
	<article class="container_12" id="contentD">
		<br>
		<form action="${rc.getContextPath()}/statistique/modePaiement">
			<label style="display: block; width: 190px; float: left;"
				for="libelle">Date Début </label>
				
			<input type="text" id="dateDebut" name="dateDebut" value="${dateDebut!}"
				style="position: relative; z-index: 10000;"> 
				
			 <label	style="display: block; width: 150px; float: left;" for="libelle">Date Fin</label>
			<input	type="text" id="dateFin" name="dateFin" value="${dateFin!}"
				style="position: relative; z-index: 1000;">
			<input type="submit" value="Rechercher">
		</form>
		
		<br>
		<table style="width: 70%">
			<tr>
				<td width="50%">
					<img alt="" src="${rc.getContextPath()}/statistique/modePaiement">			
				</td>
			</tr>
		</table>
		
	</article>
	<footer> 
	</footer>

</body>
</html>