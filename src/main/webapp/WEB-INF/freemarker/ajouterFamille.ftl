<#import "spring.ftl" as spring />
<head>
	<#include "modules/head.ftl">	
</head>

<body>
	<#include "common/nav.ftl">
</br></br>

<form action="${rc.getContextPath()}/produits/ajouterFamille" modelAttribute="familleIn" method="POST">

   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Ajouter Familles</legend>
         <label style="display: block;width: 150px;float: left;" for="libelle">Libelle</label>
          <input name="libelle"><br>
          <label style="display: block;width: 150px;float: left;" for="libelle">Couleur</label>
          <input name="color"><br>
         
    	<br>

    </fieldset>
   <p><input type="submit" value="Ajouter" style="float: left;"></p>
</br></br>
</form>