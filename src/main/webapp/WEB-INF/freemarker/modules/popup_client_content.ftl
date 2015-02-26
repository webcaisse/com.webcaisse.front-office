<table cellpadding="0" cellspacing="0" border="0" class="display">
	<thead>
		<tr>

			<th>Prénom</th>
			<th>Nom</th>
			<th>Téléphone</th>
			<th>Adresse</th>
		</tr>
	</thead>
	<tbody>
		<#if clients??> <#list clients as client> <#if client.telephone??>


		<tr id="maj" data-idclient="${client.id}">

			<td>${client.prenom!}</td>
			<td>${client.nom!}</td>
			<td>${client.telephone!}</td>
			<td class="center">${client.numeroRue!} ${client.nomRue!}</td>


		</tr>

		</#if> </#list> </#if>
		</tbody>
</table>
