<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">
</head>

<body>
	 
	<#include "common/nav.ftl" >	
	</br></br>
	<fieldset style="border:solid 1px black; padding:50px; width:300px; color:midnightblue;margin:20px; font-family:verdana;"> 
   <legend style="font-size:14; font-weight:bold;"> Partie Client </legend> 
         
   
		       <td>
		       		<#if commandeOut.mode??>
		       			<#if commandeOut.mode="null">
		       				Inconnu
		       		     <#elseif commandeOut.mode="L" >
                            Livraison
                          <#elseif commandeOut.mode="E" >
                            A emporter
                           <#elseif commandeOut.mode="P" >
                            Sur place
                         </#if>
		       			
		       		</#if>
		       </td>
		        
		</fieldset>   </br></br>
		
		<fieldset style="border:solid 1px black; padding:50px; width:400px; color:midnightblue;margin:20px; font-family:verdana;"> 
        <legend style="font-size:14; font-weight:bold;"> Partie Commande </legend> 
		       
	<table class="details commande">
		<tr>
		    <th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Libelle produit</th>
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Quantite</th> 
			<th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">prix unitaireTTC</th>
            
			
		</tr>
	   <#if ligneComandeOuts??>
	       <#list ligneComandeOuts as ligneCommandeOut>
			   <tr >
					<td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${ligneCommandeOut.libelle}</td>
					<td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${ligneCommandeOut.quantite}</td>
		            <td  style="padding:10px;margin:10px;border:1px dotted gray; background:white">${ligneCommandeOut.prixUnitaire}</td>
		       
		</#list>
		</#if>
			
              
		     </tr>
		         
	    
	
	</table> </br>
	
	<table>
	     <tr>
		    <th style="padding:10px;margin:10px;border:1px dotted gray; background:yellow">Prix total : ${commandeOut.montant!} EUR</th> 
	     <tr/>	
	</table>
	
	
	
	
		       
		       
		       
		</fieldset>   </br></br>
		
		    
   
</body>
</html>