$(function() {
	calAllPrice() ;	// 页面加载完成之后要调用计算总价的处理函数
	$("#addBtn").on("click",function(){
		gids = "" ;
		$("#gid:checked").each(function(){
			gids += this.value + "," ;
		}) ;
		if (gids == "") {
			operateAlert(false,"","请选择要购买的商品信息。") ;
		} else {
			window.location = "pages/front/center/orders/OrdersActionFront!addPre.action?gids=" + gids ;
		}
	}) ;
	$("#editBtn").on("click",function(){
		data = "" ;
		$("input[id^=amount]").each(function(){	// 获取全部拥有数量的信息的输入元素
			gid = this.id.split("-")[1] ;
			amount = parseInt(this.value) ; // 获取数量
			data += gid + ":" + amount + "," ;
		}) ;
		if (data != "") {
			$.post("pages/front/center/shopcar/ShopcarActionFront!updateBatch.action",{"data":data},
					function(data){
				flag = data.trim()=="true" ;
				if (flag) {	// 删除元素
					$("input[id^=amount]").each(function(){	// 获取全部拥有数量的信息的输入元素
						gid = this.id.split("-")[1] ;
						amount = parseInt(this.value) ; // 获取数量
						if (amount == 0) {	// 要执行删除处理
							$("#shopcar-" + gid).remove() ;	// 删除当前行
						}
					}) ;
					operateAlert(flag,"购物车修改成功！","购物车修改失败！") ;
					calAllPrice() ;
				}
			},"text") ;
		}
	}) ;
	$("#rmBtn").on("click",function(){
		gids = "" ;
		$("#gid:checked").each(function(){
			gids += this.value + "," ;
		}) ;
		if (gids == "") {
			operateAlert(false,"","请选择要移除的商品信息。") ;
		} else {	// 要进行移除处理
			$.post("pages/front/center/shopcar/ShopcarActionFront!remove.action",{"gids":gids},
					function(data){
				flag = data.trim()=="true" ;
				if (flag) {	// 删除元素
					$("#gid:checked").each(function(){
						$("#shopcar-" + this.value).remove() ;
					}) ;
				}
				operateAlert(flag,"购物车修改成功！","购物车修改失败！") ;
				calAllPrice() ;
			},"text") ;
		}
	}) ;
	$("#selectAll").on("click",function(){
		checkboxSelectAll('gid',this.checked) ;
	}) ; 
	$("button[id^=updateBtn]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			amount = $("#amount-" + gid).val() ;
			$.post("pages/front/center/shopcar/ShopcarActionFront!edit.action",{"gid":gid,"amount":amount},
					function(data){
				flag = data.trim()=="true" ;
				operateAlert(flag,"购物车修改成功！","购物车修改失败！") ;
				if (flag && amount == "0") {	// 数量为0
					$("#shopcar-" + gid).remove() ;	// 删除当前行
				}
			},"text") ;
		})
	}) ;
	$("button[id^=add]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			$("#amount-" + gid).val(parseInt($("#amount-" + gid).val()) + 1) ;
			calAllPrice() ;
		})
	}) ;
	$("button[id^=sub]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			if (parseInt($("#amount-" + gid).val()) - 1 >= 0) {
				count = parseInt($("#amount-" + gid).val()) - 1 ;
				$("#amount-" + gid).val(count) ;
				calAllPrice() ;
				if (count == 0) { // 要进行删除了
					$("#updateBtn-" + this.id.split("-")[1]).attr("class","btn btn-danger") ;
					$("#updateBtn-" + this.id.split("-")[1]).text("删除") ;
				}
			}
		}) ;
	}) ;
}) 

function calAllPrice() {	// 实现总价的计算处理操作
	sum = 0.0 ;	// 保存购物车中的总价格
	$("input[id^=amount]").each(function(){	// 获取全部拥有数量的信息的输入元素
		gid = this.id.split("-")[1] ;
//		console.log("*** shopcar、gid = " + gid) ;
		amount = parseInt(this.value) ; // 获取数量
		price = parseFloat($("#price-" + gid).text()) ;
		sum += amount * price ;
	}) ;
	$(allPrice).text(round(sum,2)) ;
}