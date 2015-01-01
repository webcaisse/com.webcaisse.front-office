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
         <label style="display: block;width: 150px;float: left;" for="nom">Nom (*)</label>
          <input name="nom"><br>
          <label style="display: block;width: 150px;float: left;" for="prenom">Prenom (*)</label>
          <input name="prenom"><br>
          <label style="display: block;width: 150px;float: left;" for="telephone">Telephone (*)</label>
          <input name="telephone"><br>
          <label style="display: block;width: 150px;float: left;" for="email">Email</label>
          <input name="email"><br>
          <label style="display: block;width: 150px;float: left;" for="code1">Code1</label>
          <input name="code1"><br>
          <label style="display: block;width: 150px;float: left;" for="code2">Code2</label>
          <input name="code2"><br>
          <label style="display: block;width: 150px;float: left;" for="email">Code3</label>
          <input name="code3"><br>
          <label style="display: block;width: 150px;float: left;" for="etage">Etage</label>
          <input name="etage"><br>
          <label style="display: block;width: 150px;float: left;" for="immeuble">Immeuble</label>
          <input name="immeuble"><br>
         <label style="display: block;width: 150px;float: left;" for="ville">Ville</label>
          <input name="ville"><br>
          <label style="display: block;width: 150px;float: left;" for="codePostal">Code Postal</label>
          <input name="codePostal"><br>
          <label style="display: block;width: 150px;float: left;" for="numeroRue">N°Rue (*)</label>
          <input name="numeroRue"><br>
           <label style="display: block;width: 150px;float: left;" for="nomRue">Rue (*)</label>
          <input name="nomRue"><br>
          <label style="display: block;width: 150px;float: left;" for="interphone">Interphone</label>
          <input name="interphone"><br>
          
    	<br>

    </fieldset>
   <p><input type="submit" value="Ajouter" style="float: left;"></p>
</br></br>
</form>