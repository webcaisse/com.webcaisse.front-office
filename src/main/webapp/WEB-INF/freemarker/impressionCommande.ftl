

<body id="impCommande">
	 <div id="impCommande">
	 
	 
	 <#list referencesHeader as referenceHeader>
      
         <h4> ${referenceHeader.valeur}</h4>
     </#list>
	
	<br>
	   <#if ligneComandeOuts??>
	       <#list ligneComandeOuts as ligneCommandeOut>
			   
			${ligneCommandeOut.quantite}  ${ligneCommandeOut.libelle}      
			   
		        <br> <br>
		</#list>
		</#if>
			
              
      --------------- <br>
		      
	   Total : ${commandeOut.montant!} EUR
	    
	     
		       
                
   <#list referencesFooter as referenceFooter>
      
         <h4> ${referenceFooter.valeur}</h4>
     </#list>

               
 

   </div>
   
