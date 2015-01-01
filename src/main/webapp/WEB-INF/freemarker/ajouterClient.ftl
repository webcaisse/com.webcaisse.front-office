<#import "spring.ftl" as spring />
<head>
	<#include "modules/head.ftl">	
</head>

<body>
	<#include "common/nav.ftl">
</br></br>

<form action="${rc.getContextPath()}/clients/ajouterClient" modelAttribute="clientIn" method="POST">

   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Ajouter Clients</legend>
         <label style="display: block;width: 150px;float: left;" for="nom">Nom</label>
          <input name="nom"><br>
          <label style="display: block;width: 150px;float: left;" for="prenom">Prenom</label>
          <input name="prenom"><br>
          <label style="display: block;width: 150px;float: left;" for="telephone">Telephone</label>
          <input name="telephone"><br>
          <label style="display: block;width: 150px;float: left;" for="email">Email</label>
          <input name="email"><br>
         
    	<br>

    </fieldset>
   <p><input type="submit" value="Ajouter" style="float: left;"></p>
</br></br>
</form>