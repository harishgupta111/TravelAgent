$(document).ready(function(){
//	script for handling reservation widget view and modify tab	
	$("#view_res a").on('click',function(){
		$('#reservation').hide();
		var title=$(this).attr('title');
		console.log(title);
		$("#"+title).show(); 
	})
//	script for going back to widget from view modify form	
	$(".back").on('click',function(){
		$('#reservation').show();
		$('#modifyReservation').hide();	
	})
//	modernizr test for placeholder	
	Modernizr.load(
		{
			test: Modernizr.input.placeholder,
			nope: 'js/placeholder.js',
			complete: function(){
				if(!Modernizr.input.placeholder){
					$('input, textarea').placeholder();
				}
			}	
		});
});  
