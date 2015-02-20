<div id="popupListClient" style="display:none ; block; left: 330px; position: absolute; top: 256.5px; z-index: 9999; opacity: 1; border: 5px solid rgb(90, 158, 181); background-color: white;  width:810px">
	<span class="button b-close" style="z-index: 88"><span>X</span></span>
	<ul class="grid dark-grey-gradient" style="height: 300px; width:800px ; overflow: auto; position: relative;">
	
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="listClient"> 
		    <thead> 
		        <tr> 

		            <th>Prénom</th> 
		            <th>Nom</th> 
		            <th>Téléphone</th> 
		            <th>Adresse</th> 
		        </tr> 
		    </thead> 
		    <tbody>
		    	<#if clients??> 
		    		<#list clients as client>
		    			<#if client.telephone??>

                          
					        <tr id="maj" data-idclient="${client.id}"> 
					        
					            <td>${client.prenom!}</td> 
					            <td>${client.nom!}</td> 
					            <td>${client.telephone!}</td> 
					            <td class="center">${client.numeroRue!}  ${client.nomRue!}</td>  
					           
					            
					        </tr>
					     
				        </#if>
		        	</#list>
		        </#if> 
		</table>
	</ul>
</div>
