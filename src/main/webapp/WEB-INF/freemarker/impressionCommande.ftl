

<body id="impCommande">
	 <div id="impCommande">
	 
	 
	 <#list referencesHeader as referenceHeader>
      
         <h4> ${referenceHeader.valeur}</h4>
     </#list>
	
	<table class="details commande">
		<tr>
		    <th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Libelle produit</th>
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Quantite</th> 
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">prix unitaireTTC</th>
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Total TTC</th>
            
			
		</tr>
	   <#if ligneComandeOuts??>
	       <#list ligneComandeOuts as ligneCommandeOut>
			   <tr >
					<td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${ligneCommandeOut.libelle}</td>
					<td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${ligneCommandeOut.quantite}</td>
		            <td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${ligneCommandeOut.prixUnitaire}</td>
		              <td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${ligneCommandeOut.total}</td>
		</#list>
		</#if>
			
              
		     </tr>
		         
	    
	
	</table> </br>
	
	<table>
	     <tr>
		    <th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Prix total : ${commandeOut.montant!} EUR</th> 
	     <tr/>	
	</table>       
		       
                
   <#list referencesFooter as referenceFooter>
      
         <h4> ${referenceFooter.valeur}</h4>
     </#list>

               
 

   </div>
   
