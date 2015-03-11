<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">
<link rel="stylesheet" href="<@spring.url '/css/themes/default/style.min.css' />" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
 <link rel="stylesheet" type="text/css"	href="<@spring.url '/css/dataTable.css' />">

  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <script type="text/javascript"	src="<@spring.url '/js/jquery.bpopup.min.js' />"></script>
  <script type="text/javascript" src="<@spring.url '/js/paiement.js' />"></script>
  <script type="text/javascript" src="<@spring.url '/js/commande.js' />"></script>
  <script type="text/javascript"  charset="utf-8" src="<@spring.url '/js/dist/dataTables.min.js' />"></script>
  	<script>
  $(function() {
    $( "#dateDebut" ).datepicker({ dateFormat: "dd-mm-yy" });
  });
  </script>
  
  <script>
  $(function() {
    $( "#dateFin" ).datepicker({ dateFormat: "dd-mm-yy" });
  });
  </script>
   <script>

 $( document ).ready(function() {
	$('#dataTableCommandeEnCours').dataTable() ;
 });  </script>
</head>

<body>
	<!-- Header -->
	<#include "common/nav.ftl" >	
	<article class="container_12" id="contentD">
		<br>
          <form action="${rc.getContextPath()}/statistique/demo-chart.png"> 
                <label style="display: block;width:190px;float: left;" for="libelle" >Date Début </label>         <label style="display: block;width: 150px;float: left;" for="libelle" >Date Fin</label></br>
				<input type="text" id="dateDebut" name="dateDebut" value="" style="position: relative; z-index: 10000;"> <input type="text" id="dateFin" name="dateFin" value="" style="position: relative; z-index: 1000;">
		 		<br></br>
		 		<input type="submit" value="Rechercher">
		 	</form> 




	</article>
	<footer>
	</footer>
  
</body>
</html>