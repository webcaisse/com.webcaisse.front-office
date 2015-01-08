<body>
<form action="${rc.getContextPath()}/parametrage/sauvegarderParametresLivreur" modelAttribute="parametreLivreurIn" method="POST">

   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Sauvegarder Parametres Livreurs </legend>
     
         <label style="display: block;width: 150px;float: left;" for="nom">Nom</label>
          <input name="nom" ><br>
          <label style="display: block;width: 150px;float: left;" for="prenom">Prenom</label>
          <input name="prenom"><br>
          <label style="display: block;width: 150px;float: left;" for="adresse">Adresse</label>
          <input name="adresse"><br>
          <label style="display: block;width: 150px;float: left;" for="telephone">Télephone</label>
          <input name="telephone"><br><br>
          <label style="display: block;width: 150px;float: left;" for="">N°SS</label>
          <input name="nss"><br>
           
    	<br>
    </fieldset>
   <p><input type="submit" value="Sauvegarder" style="float: left;"></p>
</form> </br></br> </br>
<div id="listeUsers">
	<table>
		<tr>
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Nom & Prenom</th> 
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Adresse</th>
			
		</tr>
	   <#if livreurs??>
	       <#list livreurs as livreur>
			   <tr >
					<td style="padding:10px;margin:10px;border:1px dotted gray; background:white">${livreur.nom!} ${livreur.prenom!}</td>
					<td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${livreur.adresse!}</td>
		     </tr>
		</#list>
		</#if>
			
	
	</table>
</div>	
