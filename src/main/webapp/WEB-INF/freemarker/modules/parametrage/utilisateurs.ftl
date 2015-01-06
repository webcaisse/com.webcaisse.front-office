<body>
<form action="${rc.getContextPath()}/parametrage/sauvegarderParametresUtilisateur" modelAttribute="parametreUtilisateurIn" method="POST">

   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Sauvegarder Parametres Utilsateurs </legend>
     
         <label style="display: block;width: 150px;float: left;" for="nom">Nom</label>
          <input name="nom" ><br>
          <label style="display: block;width: 150px;float: left;" for="prenom">Prenom</label>
          <input name="prenom"><br>
          <label style="display: block;width: 150px;float: left;" for="adresse">Adresse</label>
          <input name="adresse"><br>
          <label style="display: block;width: 150px;float: left;" for="telephone">Télephone</label>
          <input name="telephone"><br><br>
          <label style="display: block;width: 150px;float: left;" for="login">Login</label>
          <input name="username"><br>
           <label style="display: block;width: 150px;float: left;" for="MP">Mot de passe</label>
          <input name="password"><br>
           <label style="display: block;width: 150px;float: left;" for="type">Type</label>
           <SELECT name="profil">
           		<#if profils??>
           			<#list profils as profil>
			            <OPTION VALUE="${profil.id}">${profil.libelle}</OPTION>
		            </#list>
		         </#if>
	       </SELECT>
     
    	<br>
    </fieldset>
   <p><input type="submit" value="Sauvegarder" style="float: left;"></p>
</form>
<div id="listeUsers">
	<table>
		<tr>
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:pink">Nom & Prenom</th> 
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:pink">Adresse</th>
			
		</tr>
	   <#if users??>
	       <#list users as user>
			   <tr >
					<td style="padding:10px;margin:10px;border:1px dotted gray; background:white">${user.nom!} ${user.prenom!}</td>
					<td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${user.adresse!}</td>
		     </tr>
		</#list>
		</#if>
			
	
	</table>
</div>	