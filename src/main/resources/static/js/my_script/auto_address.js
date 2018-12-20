//住所自動入力
$(function () {

	$('#auto_address').click( function () {

		var postalCode = $('#postal_code').val();

    	if (postal_code != null) {
			$.ajax({
		       	  type: 'GET',
		       	  url: '/getAddressList',
		       	  dataType: 'json',
		       	  data: {postalCode},

			      success: function (res) {
			    	if (res) {
			    		$('#state').val(res.state);
			    		$('#city').val(res.city);
			    		$('#postal-code-error').attr('style', 'display: none;');
			    	}
			      }
			});
    	}
	});
})