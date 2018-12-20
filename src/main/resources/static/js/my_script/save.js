$(function(){

	var hiddenId = $('input:hidden[name=id]');

	//保存ボタン押下時
	$('#customer-finish-btn').click(function() {

		alert("保存しました");
		$('#save').attr('action', '/customer/customer_list');

	});


	//戻るボタン押下時
	$('#customer-back-btn').click(function(){
		if(hiddenId.val()) {
			$('#fix').attr('action', '/customer/' + hiddenId.val() + '/updated');
		} else {
			$('#fix').attr('action', '/customer/entry');
		}

		$('#fix').submit();
	});

})