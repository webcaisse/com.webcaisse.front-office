    <div class="grid_5">
    	<div class="grid" style="position: relative; float: right; margin: 5px;">
			<a href="javascript:;" title="Remise à 0 du filtre" class="width-tip button" id="razFiltre">
				<img src="images/icons/fugue/cross-circle.png" width="24" height="24">
			</a>
			<input type="text" id="produitFiltre" class="width-tip" title="Filtrer la liste des produits" value="" style="height: 2em; width: 50px;">
			<input type="text" id="barcodeSearch" class="width-tip" title="Recherche code barre" placeholder="Code barre" value="" style="height: 2em; width: 100px;">
		 </div>
    	 <div id="abonnementsFinder">
         </div>
    	 <div id="productFinderFamilles">	
    	    <h1 style="font-size: 20px;">Choisissez une catégorie</h1>
	        <div class="clearfix">
	        </div>

			<ul class="grid dark-grey-gradient" style="height: 400px; overflow: auto; position: relative;">
				<#list familles as famille>
					<li class="famille" data-famille-id=${famille.id}>
						<a href="javascript:;" class="remise" style="display: block; margin: -10px; padding: 10px; cursor: pointer;background-color:${famille.couleur};">
			            	<p class="grid-name" style="width: 136px;">${famille.libelle}</p>
			            	
			    		</a>
			    	</li>
			    </#list>
			</ul>

	       </div>
           <div class="clearfix">
           </div>
           <br>
			<div id="productFinderProducts">	
	    	</div>
    	</div>
