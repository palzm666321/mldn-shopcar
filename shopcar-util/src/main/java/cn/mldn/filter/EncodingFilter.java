package cn.mldn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	public String charset = "UTF-8" ;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig.getInitParameter("charset") != null) {	// 配置了默认编码
			this.charset = filterConfig.getInitParameter("charset") ;	// 使用新的编码
		}
	} 
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(this.charset);
		chain.doFilter(req, resp); // 过滤继续执行后续操作
		resp.setCharacterEncoding(this.charset);
	}

}