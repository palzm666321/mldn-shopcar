<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
</head>
<body>
	<div class=""> 
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div>
		<div id="contentDiv" class="row">
			<div class="col-md-2 col-xs-2">
				<jsp:include page="/pages/plugins/front/center/include_center_item.jsp">
					<jsp:param value="1" name="ch"/>
				</jsp:include>
			</div>
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;订单详情</strong>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3"><strong>收件人：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.name}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>下单日期：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.subdate}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>配送地址：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.address}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>总金额：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.price}</div>
						</div>
						<div class="row">
							<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center"><strong>商品图片</strong></th>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>价格</strong></th>
									<th class="text-center"><strong>购买数量</strong></th>
									<th class="text-center"><strong>总额</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allGoods}" var="goods">
									<tr>
										<td class="text-center"><img src="upload/goods/${goods.photo}" style="width:30px;"></td>
										<td class="text-center">${goods.name}</td>
										<td class="text-center">${goods.price}</td>
										<td class="text-center">${allDetails[goods.gid]}</td>
										<td class="text-center"><span id="allprice-${goods.gid}">${goods.price * allDetails[goods.gid] }</span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table> 
						</div>
					</div>
					<div class="panel-footer" style="height:80px;">  
						<jsp:include page="/pages/plugins/include_alert.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/pages/plugins/front/front_footer.jsp"/>
