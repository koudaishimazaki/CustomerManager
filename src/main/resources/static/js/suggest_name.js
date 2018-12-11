/**
 * suggest.jsを使って検索条件のAjaxサジェスト
 */

$(function(){

	var size = $('#customer_name').parent().width() - 1;

	// 姓
	new Suggest.Local(
      "first_name",	// アクションに送るデータ
      "first_name_suggest",	// アクションから受け取ったデータを表示する場所
      [],
      {
    	highlight: true,	// 一致する文字列をハイライト
        hookBeforeSearch: function(firstNameSearchData) {

        	var self = this,
        	suggestArea = $(self.suggestArea);

	        suggestArea.width(size);

	        $self = $(this);
	        $self.width(size);

	        $.ajax({
	       	  type: 'GET',
	       	  url: '/f_name/search',
	       	  dataType: 'json',
	       	  data: {firstNameSearchData},

		      success: function (res) {
		    	  self.clearSuggestArea();
	              self.candidateList = res;
	              var resultList = self._search(firstNameSearchData);
	              if (resultList.length != 0){
	                  self.createSuggestArea(resultList);
	              }
		      }
	        });
        }
      }
   );

   // 名
   new Suggest.Local(
     "last_name",	// アクションに送るデータ
     "last_name_suggest",	// アクションから受け取ったデータを表示する場所
     [],
     {
       highlight: true,	// 一致する文字列をハイライト
       hookBeforeSearch: function(lastNameSearchData) {
    	   var self = this,
       	   suggestArea = $(self.suggestArea);

	       suggestArea.width(size);

	       $self = $(this);
	       $self.width(size);

	       $.ajax({
    	     type: 'GET',
       	     url: '/l_name/search',
       	     dataType: 'json',
       	     data: {lastNameSearchData},

       	     success: function (res) {
       	    	self.clearSuggestArea();
	            self.candidateList = res;
	            var resultList = self._search(lastNameSearchData);
	            if (resultList.length != 0){
	                self.createSuggestArea(resultList);
	            }
       	     }
       	   });
       }
     }
   );


// 姓(かな)
new Suggest.Local(
  "first_name_kana",	// アクションに送るデータ
  "f_name_kana_suggest",	// アクションから受け取ったデータを表示する場所
  [],
  {
	highlight: true,	// 一致する文字列をハイライト
    hookBeforeSearch: function(firstKanaSearchData) {

    	var self = this,
    	suggestArea = $(self.suggestArea);

        suggestArea.width(size);

        $self = $(this);
        $self.width(size);

        $.ajax({
       	  type: 'GET',
       	  url: '/f_kana/search',
       	  dataType: 'json',
       	  data: {firstKanaSearchData},

	      success: function (res) {
	    	  self.clearSuggestArea();
              self.candidateList = res;
              var resultList = self._search(firstKanaSearchData);
              if (resultList.length != 0){
                  self.createSuggestArea(resultList);
              }
	      }
        });
    }
  }
);

// 名(かな)
new Suggest.Local(
  "last_name_kana",	// アクションに送るデータ
  "l_name_kana_suggest",	// アクションから受け取ったデータを表示する場所
  [],
  {
    highlight: true,	// 一致する文字列をハイライト
    hookBeforeSearch: function(lastKanaSearchData) {
 	   var self = this,
    	   suggestArea = $(self.suggestArea);

	       suggestArea.width(size);

	       $self = $(this);
	       $self.width(size);

	       $.ajax({
 	     type: 'GET',
    	     url: '/l_kana/search',
    	     dataType: 'json',
    	     data: {lastKanaSearchData},

    	     success: function (res) {
    	    	self.clearSuggestArea();
	            self.candidateList = res;
	            var resultList = self._search(lastKanaSearchData);
	            if (resultList.length != 0){
	                self.createSuggestArea(resultList);
	            }
    	     }
    	   });
    }
  }
);
});