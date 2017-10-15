$(function(){
	$("span[id^=allprice-]").each(function() {
		price = parseFloat($(this).text()) ;
		$(this).text("￥" + round(price,2)) ;
	})
}) ;