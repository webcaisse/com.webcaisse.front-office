<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script type="text/javascript" src="<@spring.url '/js/parametrage.js' />"></script>
  <script>
  $(function() {
    $( "#accordion" ).accordion();
  });
  </script>  
</head>

<body>
	<#include "common/nav.ftl">
	<!-- Content -->	
 <div id="accordion">
  <h3>Caisse</h3>
  <div>
	   <#include "modules/parametrage/caisse.ftl">
  </div>
  <h3>Utilisateurs</h3>
  <div>
   <#include "modules/parametrage/utilisateurs.ftl">
  </div>
  <h3>Livreurs</h3>
  <div>
	   <#include "modules/parametrage/livreurs.ftl">
  </div>
  <h3>Villes-plans itinéraire</h3>
  <div>
	   <#include "modules/parametrage/ville-plans-itineraire.ftl">
  </div>
  <h3>Sauce</h3>
  <div>
	   <#include "modules/parametrage/sauce.ftl">
  </div>
  <h3>TVA</h3>
  <div>
	   <#include "modules/parametrage/tva.ftl">
  </div>
  <h3>Sauvegarde</h3>
  <div>
	   <#include "modules/parametrage/sauvegarde.ftl">
  </div>
</div>
 
</body>
</html>