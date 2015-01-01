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

<form action="${rc.getContextPath()}/clients/saveUpdateClient" modelAttribute="clientIn" method="POST">
	
	<input type="hidden" name="id" value="${clientIn.id!}"/>
   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Mise à jour Familles</legend>
         <label style="display: block;width: 150px;float: left;" for="nom" >Nom</label>
      <input name="nom" value="${clientIn.nom!}"><br>
         <label style="display: block;width: 150px;float: left;" for="prenom" >Prenom</label>
       <input name="prenom" value="${clientIn.prenom!}"><br>
        <label style="display: block;width: 150px;float: left;" for="email" >Email</label>
       <input name="email" value="${clientIn.email!}"><br>
       <label style="display: block;width: 150px;float: left;" for="telephone" >Telephone</label>
       <input name="telephone" value="${clientIn.telephone!}"><br>

    	</table>
    	<br>

    </fieldset>
   <p><input type="submit" value="Update" style="float: left;"></p>
</br></br>
</form>




</body>
</html>