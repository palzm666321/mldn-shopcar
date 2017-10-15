package cn.mldn.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个方法进行给定路径的拆分处理操作，返回Action的路径以及方法名称
 * @author mldn
 */
public class RequestUriUtil {
	private RequestUriUtil() {}
	/**
	 * 进行给定的路径拆分处理操作，路径的组成：“XxxAction!方法.action”
	 * @param request 请求对象
	 * @return 返回的信息里面包含有两个元素：
	 * 第一个元素（索引0）为Action的访问路径。
	 * 第二个元素（索引1）为Action类中的调用方法名称。
	 */
	public static String [] splitUri(HttpServletRequest request) {
		String result [] = new String [2] ;
		String uri = request.getRequestURI().replaceFirst(request.getContextPath(), "") ;	// 获取请求的路径信息
		String temp [] = uri.split("!|%21") ;	// 按照“!”拆分
		result[0] = temp[0] ;	// 获取Action的访问路径
		result[1] = temp[1].substring(0, temp[1].lastIndexOf(".")) ;
		return result ;
	}
}
