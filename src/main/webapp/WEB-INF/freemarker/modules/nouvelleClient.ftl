 <div id="popupClient" style="display:none;left: 433px; position: absolute; top: 302px; z-index: 9999; opacity: 1; background-color:white;border: 5px solid rgb(90, 158, 181);>
	<span class="button b-close" style="z-index: 88"><span>X</span></span>
<form action="${rc.getContextPath()}/clients/ajouterClientMemoire" modelAttribute="clientIn">
	
   <fieldset style="margin-bottom: 15px;padding: 10px;">
      <legend style=" color: #384313;font-size:18px;text-align:center;text-shadow:1px 0px 3px gray;">Ajout Client</legend></br></br>
          <label style="display: block;width: 150px;float: left;" for="nom" >Nom (*)</label>
          <input name="nom" id="nom"><br>
          <label style="display: block;width: 150px;float: left;" for="prenom" >Prenom (*)</label>
          <input name="prenom" id="prenom"><br>
          <div id="telephones" >
          <label style="display: block;width: 150px;float: left;" for="telephone" >Telephone (*)</label>
          <input name="telephone" id="phoneId"><br>
          </div>
          <label style="display: block;width: 150px;float: left;" for="etage">Etage</label>
          <input name="etage" id="etage"><br>
          <label style="display: block;width: 150px;float: left;" for="immeuble">Immeuble (*)</label>
          <input name="immeuble" id="immeuble"><br>
          <label style="display: block;width: 150px;float: left;" for="codePostal">Code Postale</label>
          <input name="codePostal" id="codePostale"><br>
          <label style="display: block;width: 150px;float: left;" for="numeroRue">N°Rue (*)</label>
          <input name="numeroRue" id="numeroRue"><br>
           <label style="display: block;width: 150px;float: left;" for="nomRue">Rue (*)</label>
          <input name="nomRue" id="nomRue"><br>
          <label style="display: block;width: 150px;float: left;" for="interphone">Interphone</label>
          <input name="interphone" id="interphone"><br>
          
    	</table>
    	<br>

    </fieldset>
   <p><input type="submit" value="Save" style="float: left;"></p>
</br></br>
</form>
	
	
</div>