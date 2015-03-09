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
	
	   <#if ligneComandeOuts??>
	       <#list ligneComandeOuts as ligneCommandeOut>
			   
			
					${ligneCommandeOut.quantite} 	${ligneCommandeOut.libelle}
		             </br></br>
		       
		</#list>
		</#if>
			
              
	 </br>
	--------------------<br></br>
	     
		Total = ${commandeOut.montant!} EUR 
	    
	
	
	
	
		       
		       
		       
		</fieldset>   </br></br>
		
		    
   
</body>
</html>