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
        <label style="display: block;width: 150px;float: left;" for="code1">Code1</label>
          <input name="code1" value="${clientIn.code1!}"><br>
          <label style="display: block;width: 150px;float: left;" for="code2">Code2</label>
          <input name="code2" value="${clientIn.code2!}"><br>
          <label style="display: block;width: 150px;float: left;" for="email">Code3</label>
          <input name="code3" value="${clientIn.code3!}"><br>
          <label style="display: block;width: 150px;float: left;" for="etage">Etage</label>
          <input name="etage"value="${clientIn.etage!}"><br>
          <label style="display: block;width: 150px;float: left;" for="immeuble">Immeuble</label>
          <input name="immeuble"value="${clientIn.immeuble!}"><br>
         <label style="display: block;width: 150px;float: left;" for="ville">Ville</label>
          <input name="ville"value="${clientIn.ville!}"><br>
          <label style="display: block;width: 150px;float: left;" for="codePostal">Code Postal</label>
          <input name="codePostal"value="${clientIn.codePostale!}"><br>
          <label style="display: block;width: 150px;float: left;" for="numeroRue">N°Rue (*)</label>
          <input name="numeroRue" value="${clientIn.numeroRue!}"><br>
           <label style="display: block;width: 150px;float: left;" for="nomRue">Rue (*)</label>
          <input name="nomRue"value="${clientIn.nomRue!}"><br>
          <label style="display: block;width: 150px;float: left;" for="interphone">Interphone</label>
          <input name="interphone" value="${clientIn.interphone!}"><br>
          
    	</table>
    	<br>

    </fieldset>
   <p><input type="submit" value="Update" style="float: left;"></p>
</br></br>
</form>




</body>
</html>