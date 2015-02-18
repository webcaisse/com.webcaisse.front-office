<div id="popupListClient" style="display:none; ">
	<span class="button b-close" style="z-index: 88"><span>X</span></span>
	<ul class="grid dark-grey-gradient" style="height: 300px; width:600px ; overflow: auto; position: relative;background-color: white; ">
	
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
					        <tr> 
					            <td>${client.prenom!}</td> 
					            <td>${client.nom!}</td> 
					            <td>${client.telephone!}</td> 
					            <td class="center">${client.nomRue!}</td> 
					        </tr>
				        </#if>
		        	</#list>
		        </#if> 
		</table>
	</ul>
</div>
