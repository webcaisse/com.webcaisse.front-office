
<body>
</br></br>

<form action="${rc.getContextPath()}/parametrage/sauvegarderParametres" modelAttribute="parametres" method="POST">

   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size: 16px;font-weight: bold;padding-bottom: 10px;text-shadow: 0 1px 1px #c0d576;">Sauvegarder Parametres</legend>
     
         <label style="display: block;width: 150px;float: left;" for="E1">Entite 1 ticket</label>
          <input name="E1" path="parametre.nomParametre" value=""><br>
          <label style="display: block;width: 150px;float: left;" for="E2">Entite 2 ticket</label>
          <input name="E2"><br>
          <label style="display: block;width: 150px;float: left;" for="E3">Entite 3 ticket</label>
          <input name="E3"><br>
          <label style="display: block;width: 150px;float: left;" for="E1">Entite 4 ticket</label>
          <input name="E4"><br><br>
          <label style="display: block;width: 150px;float: left;" for="P1">Pied 1 ticket</label>
          <input name="P1"><br>
           <label style="display: block;width: 150px;float: left;" for="P2">Pied 2 ticket</label>
          <input name="P2"><br>
           <label style="display: block;width: 150px;float: left;" for="P3">Pied 3 ticket</label>
          <input name="P3"><br>
           <label style="display: block;width: 150px;float: left;" for="P4">Pied 4 ticket</label>
          <input name="P4"><br>
           
         
    	<br>

    </fieldset>
   <p><input type="submit" value="Sauvegarder" style="float: left;"></p>
</br></br>
</form>
