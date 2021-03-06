<#import "spring.ftl" as spring />
<head>
	<#include "modules/head.ftl">
	<link rel="stylesheet"	href="<@spring.url '/css/themes/default/style.min.css' />" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<@spring.url '/js/dist/jstree.min.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/js/produit.js' />"></script>
	
</head>

<body>
	<#include "common/nav.ftl">
</br></br>

<form action="${rc.getContextPath()}/produits/ajouterProduits" modelAttribute="produitIn" method="POST">
<input type="hidden" name="familleId" value="${produit.getFamilleId()}"/>
   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Ajouter Produits</legend>
         <label style="display: block;width: 150px;float: left;" for="libelle">Libelle</label>
      <input name="libelle"><br>
         <label style="display: block;width: 150px;float: left;" for="Code">Code</label>
         <input name="code"><br>
		 <label style="display: block;width: 150px;float: left;" for="Code">Prix</label>
         <table id="matable" border="1" cellspacing="10" >
	         <tr id="row_1">
	            <td><input name="prix"></td>
	            <td><div class="plus">&nbsp;&nbsp;<img src="${rc.getContextPath()}/images/icons/plus.png"></div></td>
	         </tr>
    	</table>
    	<br>

    </fieldset>
   <p><input type="submit" value="Ajouter" style="float: left;"></p>
</br></br>
</form>




</body>
</html>