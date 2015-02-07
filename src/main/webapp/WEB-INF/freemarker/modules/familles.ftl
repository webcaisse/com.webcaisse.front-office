    <div class="grid_5">
    	 <div id="productFinderFamilles">	
    	    <h1 style="font-size: 20px;">Choisissez une catégorie</h1>
	        <div class="clearfix">
	        </div>

			<ul class="grid dark-grey-gradient" style="height: 400px; overflow: auto; position: relative;">
				<#if familles??>
					<#list familles as famille>
						<li class="famille" data-famille-id=${famille.id}>
							<a href="javascript:;" class="remise" style="display: block;background-color:${famille.couleur!}; margin: -10px; padding: 10px; cursor: pointer;">
				            	<p class="grid-name" style="width: 136px;">${famille.libelle}</p>
				            	
				    		</a>
				    	</li>
				    </#list>
			    </#if>
			</ul>

	       </div>
           <div class="clearfix">
           </div>
           <br>
			<div id="productFinderProducts">	
	    	</div>
    	</div>
