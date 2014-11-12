<h1 style="font-size: 20px;">Menu MAXI best-of </h1>
<div class="clearfix"></div>
	<ul class="grid dark-grey-gradient">
		<li style="height: 2em; width: 50px;">
			<a href="javascript:;" class="inCategorie" rel="0" style="display: block; height: 2em; cursor: pointer;">
	            <p class="grid-name" style="height: 2em; width: 0px;"><img src="images/icons/web-app/24/Back.png" width="50" height="50" style="vertical-align: middle;"></p>
	    	</a>
	    </li>
    </ul>
	<ul class="grid dark-grey-gradient" style="height: 400px; overflow: auto; position: relative;">
	      <#if produits ?? >
			<#list produits as produit>
			<li class="product" data-product-id=${produit.id}>
				<a href="javascript:;" class="selectProduit"  rel="4060" prix="1" type="1" libre="1" remise="0%" style="display: block; margin: -10px; padding: 10px; cursor: pointer;background-color:${produit.couleur}">
		           	<p class="grid-name" style="width: 131px;">${produit.libelle} </p>
			    </a>
			 </li>
			 </#list>
		 </#if>
		
	</ul>
