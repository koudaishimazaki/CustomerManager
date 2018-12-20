//削除ボタン押下時
$(function(){

	var hiddenId = $('input:hidden[name=id]');

	$('#delete-btn').click(function(){
	    if(confirm("本当に削除しますか？")) {
	    	$('#delete').attr('action', '/customer/' + hiddenId.val() + '/delete');
	        alert("削除しました");
	    }
	    $('#delete').submit();
	});

})