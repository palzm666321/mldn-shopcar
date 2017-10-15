<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%!
	public static final String MEMBER_BASE_EDIT_URL = "pages/front/center/member/MemberCenterActionFront!eidtBasePre.action" ;
%>
<ul class="nav nav-pills nav-stacked">									<!-- 定义导航 -->
	<li class="${param.ch == 1 ? "active" : ""}"><a href="pages/front/center/orders/OrdersActionFront!list.action">我的订单</a></li>			<!-- 活跃导航项 -->
	<li class="${param.ch == 2 ? "active" : ""}"><a href="pages/front/center/shopcar/ShopcarActionFront!list.action">购物车</a></li>			<!-- 禁用导航项 -->
</ul>
 