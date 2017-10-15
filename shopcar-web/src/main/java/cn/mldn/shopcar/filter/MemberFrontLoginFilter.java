package cn.mldn.shopcar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.mldn.util.CookieUtil;
import cn.mldn.util.action.ActionResourceUtil;
public class MemberFrontLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req ;
		HttpSession session = request.getSession() ;
		if (session.getAttribute("mid") == null) {	// 用户未登录
			HttpServletResponse response = (HttpServletResponse) resp ;
			CookieUtil cookieUtil = new CookieUtil(request,response) ;
			String mid = cookieUtil.loadMid() ;	// 读取保存的mid数据
			if (mid != null) {
				session.setAttribute("mid", mid);
				chain.doFilter(req, resp);
			} else {	// 跳转到登录页
				req.getRequestDispatcher(ActionResourceUtil.getPage("front.member.login.action")).forward(req, resp);
			}
		} else {	// 用户已登录
			chain.doFilter(req, resp);
		}
	}

}
