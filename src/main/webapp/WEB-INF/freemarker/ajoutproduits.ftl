<#import "spring.ftl" as spring />
<head>
	<#include "modules/head.ftl">
	<link rel="stylesheet"	href="<@spring.url '/css/themes/default/style.min.css' />" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<@spring.url '/js/dist/jstree.min.js' />"></script>
</head>

<body>
	<#include "common/nav.ftl">
</br></br>

<form action="${rc.getContextPath()}/produits/ajouterProduits" modelAttribute="produit" method="POST">
   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Ajouter Produits</legend>
         <label style="display: block;width: 150px;float: left;" for="libelle">Libelle</label>
         <input id="libelle" path="libelle"><br>
         <label style="display: block;width: 150px;float: left;" for="Code">Code</label>
         <input id="Code" path="Code"><br>

    </fieldset>
   <p><input type="submit" value="Ajouter" style="float: left;"></p>
</br></br>
</form>




</body>
</html>