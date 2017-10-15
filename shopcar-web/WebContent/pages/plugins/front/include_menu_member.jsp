<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%!
	public static final String GOODS_SEARCH_URL = "pages/front/goods/GoodsActionFront!list.action" ;
	public static final String MEMBER_BASE_EDIT_URL = "pages/front/center/member/MemberCenterActionFront!eidtBasePre.action" ;
%>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="pages/front/goods/GoodsActionFront!list.action"><strong>购物车</strong></a>
	</div>
	<ul class="nav navbar-nav">
		<c:if test="${mid==null}"> 
			<li><a href="MemberActionFront!loginPre.action"><span class="glyphicon glyphicon-certificate"></span>&nbsp;登录</a></li>
		</c:if>
		<c:if test="${mid!=null}"> 
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><span class="glyphicon glyphicon-globe"></span>&nbsp;个人中心<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="pages/front/center/orders/OrdersActionFront!list.action">
						<span class="glyphicon glyphicon-list-alt"></span>&nbsp;订单列表</a></li>
					<li><a href="MemberActionFront!logout.action">
						<span class="glyphicon glyphicon-log-out"></span>&nbsp;系统注销</a></li>
				</ul></li>
			<li><a href="pages/front/center/shopcar/ShopcarActionFront!list.action">
				<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;我的购物车</a></li>
		</c:if>
	</ul> 
	<form class="navbar-form navbar-left" action="<%=GOODS_SEARCH_URL%>" method="post">
		<div class="form-group"> 
			<input type="text" class="form-control input-xs" name="kw" placeholder="请输入商品关键字..." value="${keyWord}" style="width:600px;background: #F5F5F5;height:30px;">
			<input type="hidden" name="col" value="name">
			<button class="btn btn-danger" type="submit" style="height:30px;">搜索</button>
		</div>
	</form>
</nav>
