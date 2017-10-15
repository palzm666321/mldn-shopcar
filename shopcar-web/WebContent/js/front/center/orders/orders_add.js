function handleAddress() {	// 实现地址处理过程
	address = $("#address").val() ;	// 获得address原始内容
	ptitle = $("#pid option:selected").text() + " " ;
	ctitle = " " ;
	if ($("#cid option:selected").val() != "") {
		ctitle = $("#cid option:selected").text() + " " ;
	}
	adr = address.split(" ") ; 
	if (adr.length >= 3) {	// 都填写完了，现在要修改了
		str = ptitle + ctitle + adr[2] ;
		for (x = 2 ; x < adr.length ; x ++) {
			str += adr[x] + " " ; 
		}  
		$("#address").val(str) ;  
	} else {
		$("#address").val(ptitle + ctitle) ;  
	}
}

$(function() {
	$("span[id^=allprice-]").each(function() {
		price = parseFloat($(this).text()) ;
		$(this).text("￥" + round(price,2)) ;
	})
	
	$(cid).on("change",function() {
		handleAddress() ;	// 处理地址 
	}) ;
	$(pid).on("change",function(){
		if (this.value != "") {	// 有内容，需要进行ajax异步加载
			$.post("pages/front/center/orders/CityActionFront!listProvince.action",{"pid":this.value},
					function(data){
				$("#cid option:gt(0)").remove() ;
				handleAddress() ;	// 处理地址
				for (x = 0 ; x < data.allCities.length ; x ++) {
					$("#cid").append("<option value='"+data.allCities[x].cid+"'>"+data.allCities[x].title+"</option>") ;
				}
			},"json") ;
		} else {
			$("#cid option:gt(0)").remove() ;
		}
	}) ;
	
	
	
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"name" : {
				required : true,
			},
			"phone" : {
				required : true ,
				digits : true
			} ,
			"pid" : {
				required : true 
			},
			"cid" : {
				required : true 
			},
			"address" : {
				required : true 
			}
		}
	});
})