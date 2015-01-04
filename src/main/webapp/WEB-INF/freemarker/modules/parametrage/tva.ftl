<body>
</br></br>

<form action="${rc.getContextPath()}/parametrage/sauvegarderParametresTVA" modelAttribute="parametreTVAIn" method="POST">

   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Sauvegarder parametres TVA </legend>
     
         <label style="display: block;width: 150px;float: left;" for="E1">Tva sur place</label>
          <input name="tvaSurPlace" ><br>
          <label style="display: block;width: 150px;float: left;" for="E2">Tva à emporter</label>
          <input name="tvaEmporter"><br>
          <label style="display: block;width: 150px;float: left;" for="E3">Tva livraison</label>
          <input name="tvaLivraison"><br>
      
    	<br>

    </fieldset>
   <p><input type="submit" value="Sauvegarder" style="float: left;"></p>
</br></br>
</form>