$(document).ready(function() {

	$( document ).on( "click", '.tabs.js-tabs.same-height >li>a[href=#tab-liste]',function() {
		$('.tabs.js-tabs.same-height >li.current').removeClass('current');
		$(this).parent().addClass('current');
		// on cache les autres onglets 
	//	$('#formInfoPaiement').css('display', 'none');
		$('#tab-options').css('display', 'none');
		$('#tab-infos').css('display', 'none');
		$('#tab-paiements').css('display', 'none');
		$('#tab-liste').css('display', 'block');
		
	});
	
	
	
	$( document ).on( "click", '.tabs.js-tabs.same-height >li>a[href=#tab-infos]',function() {
		$('.tabs.js-tabs.same-height >li.current').removeClass('current');
		$(this).parent().addClass('current');
		// on cache les autres onglets 
		//$('#formInfoPaiement').css('display', 'none');
		$('#tab-paiement').css('display', 'none');
		$('#tab-options').css('display', 'none');
		$('#tab-liste').css('display', 'none');
		$('#tab-infos').css('display', 'block');
		
	});
	
	$( document ).on( "click", '.tabs.js-tabs.same-height >li>a[href=#tab-options]',function() {
		$('.tabs.js-tabs.same-height >li.current').removeClass('current');
		$(this).parent().addClass('current');
		// on cache les autres onglets 
		//$('#formInfoPaiement').css('display', 'none');
		
		$('#tab-infos').css('display', 'none');
		$('#tab-paiement').css('display', 'none');
		$('#tab-liste').css('display', 'none');
		$('#tab-options').css('display', 'block');
		
	});
	
	$( document ).on( "click", '.tabs.js-tabs.same-height >li>a[href=#tab-paiement]',function() {
		$('.tabs.js-tabs.same-height >li.current').removeClass('current');
		$(this).parent().addClass('current');
		// on cache les autres onglets 
		//$('#formInfoPaiement').css('display', 'none');
		$('#tab-options').css('display', 'none');
		$('#tab-infos').css('display', 'none');
		$('#tab-liste').css('display', 'none');
		$('#tab-paiements').css('display', 'block');
		
	});
	
});