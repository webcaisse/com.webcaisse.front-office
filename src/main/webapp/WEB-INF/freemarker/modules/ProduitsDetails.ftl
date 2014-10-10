<span class="button b-close"><span>X</span></span>
	<ul class="grid dark-grey-gradient" style="height: 400px; overflow: auto; position: relative;">
      <#if produit ?? && produit.prixOut ??>
		<#list produit.prixOut as prixOut>
		<li class="product">
			<a href="javascript:;" class="selectProduit"  rel="4060" prix="1" type="1" libre="1" remise="0%" style="display: block; margin: -10px; padding: 10px; cursor: pointer;">
	           	<p class="grid-name" style="width: 131px;">${produit.libelle} </p>
	           	<p class="grid-name" style="width: 131px;">${prixOut.valeur} </p>
		    </a>
		 </li>
		 </#list>
	 </#if>
</ul>
