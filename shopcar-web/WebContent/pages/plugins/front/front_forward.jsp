<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
<script type="text/javascript" src="js/login.js"></script>
<%!
	public static final String LOGIN_URL = "MemberLoginActionFront!login.action" ;
%>
<body class="back">
	<div class="contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div> 
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-user"></span>&nbsp;用户登录</strong>
					</div>
					<div class="panel-body">
							<script type="text/javascript">
								window.onload = function() {
									goForward() ;
								}
								function goForward() {
									spanObject = document.getElementById("countSpan") ;
									count = parseInt(spanObject.innerHTML) ;	// 取得当前计数的内容
									count -- ;
									if (count == 0) {	// 要进行跳转
										window.location = "${pageContext.request.contextPath}${url}" ;	// 跳转
									} else {
										spanObject.innerHTML = count ;
										setTimeout(goForward,1000) ; 
									}
								}
							</script>
							<div>${msg}</div> 
							<div><span id="countSpan">5</span>秒后跳转到其它页面，如果没有跳转请按<a href="${pageContext.request.contextPath}${url}">这里</a>！</div>
					</div>
					<div class="panel-footer">
						<div class="alert alert-success" id="alertDiv" style="display: none;">
	                        <button type="button" class="close" data-dismiss="alert">&times;</button>
	                        <span id="alertText"></span>
	                    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/pages/plugins/front/front_footer.jsp"/>
