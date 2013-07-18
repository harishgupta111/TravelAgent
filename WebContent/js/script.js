$(document).ready(function(){
	 $(".datepicker").datepicker({
			minDate: 'today',
			maxDate: "+90D",
		    showOn: "button",
		    buttonImage:"images/calender.jpg",
		    buttonImageOnly: true,
		    onClose: function( selectedDate ) {
		    $( "#drop_date" ).datepicker( "option", "minDate", selectedDate );
		    }
	});
//	script for handling reservation widget view and modify tab	
	$("#view_res a").on('click',function(){
		$('#reservation').hide();
		var title=$(this).attr('title');
		console.log(title);
		$("#"+title).show(); 
	});
//	script for going back to widget from view modify form	
	$(".back").on('click',function(){
		$('#reservation').show();
		$('#modifyReservation').hide();	
	});
//	script for enabling and disabling drop off date and time		
	 $(".trip_way :radio:eq(0)").on('click',function(){
         $(".drop_info #drop_date").attr("disabled", "disabled");
         $("#drop_time").prop("disabled", true);
      });

      $(".trip_way :radio:eq(1)").on('click',function(){
    	  $(".drop_info #drop_date").removeAttr("disabled");
    	  $("#drop_time").prop("disabled", false);

      });
  //  script for signin form	      
  $("#top_quicklinks #sign_in").on('click',function(){
	  $('.signin_form').show();
  });
  $(".wrapper").on('click',function(){
	  $('.signin_form').hide();
   }); 
//script for offline payment
  $(".payment :radio:eq(0)").on('click',function(){
	  $("div.offlinePayment").hide();
	  $("form.paymentForm").show();
  });
  $(".payment :radio:eq(1)").on('click',function(){
	  $("div.offlinePayment").show();
	  $("form.paymentForm").hide();
  });
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
