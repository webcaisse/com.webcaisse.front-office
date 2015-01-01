$(document).ready(function() {

	/**
	 * afficher le parametrage de l'utilisateur
	 * 
	 */
	afficherParametrageUtilisateurs = function() {
		$.ajax({
			type : "GET",
			url : "ajax/caisse" 
		}).done(function(data) {
			$("#accordion div:eq('0')").html(data);
		});
		
	};
	
	
	$( document ).on( "click", "#accordion h3:eq('0')",function() {
		afficherParametrageUtilisateurs();
	});
	
});