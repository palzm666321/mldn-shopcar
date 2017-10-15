package cn.mldn.util.action.abs;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;

public abstract class AbstractAction {
	/**
	 * 根据登录保存的session获取用户id
	 * @return 获取用户id，如果用户没有登录过，则返回的是null
	 */
	public String getMid() {
		return (String) ServletObjectUtil.getRequest().getSession().getAttribute("mid") ;
	}
	/**
	 * 实现信息输出
	 * @param obj 要输出的信息内容
	 */
	public void print(Object obj) {
		try {
			ServletObjectUtil.getResponse().setCharacterEncoding("UTF-8");
			ServletObjectUtil.getResponse().getWriter().println(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	/**
	 * 将传进来的ID的数据转为set集合
	 * @param ids 传入的id数据，使用“,”分割
	 * @return 转换后的Set集合
	 */
	public Set<Long> handleLong(String ids) {
		if (ids == null || "".equals(ids)) {
			return null ;
		}
		Set<Long> set = new HashSet<Long>() ;
		String result [] = ids.split(",") ;
		for (int x = 0 ; x < result.length ; x ++) {
			try {
				set.add(Long.parseLong(result[x])) ;
			} catch (Exception e) {}
		}
		return set ;
	} 
	
	/**
	 * 将传递过来的数据进行拆分，本次包含有图片信息
	 * @param ids 包含的数据，格式“ID:图片,”
	 * @return 处理后的数据信息
	 */
	public Map<Long,String> handlePhotoLong(String ids) {
		Map<Long,String> map = new HashMap<Long,String>() ;
		String result [] = ids.split(",") ;
		for (int x = 0 ; x < result.length ; x ++) {
			String temp [] = result[x].split(":") ;
			map.put(Long.parseLong(temp[0]), temp[1]) ;
		}
		return map ;
	}
	/**
	 * 定义一个专门设置提示信息与跳转路径属性的操作方法
	 * @param mav 要进行返回的ModelAndView对象
	 * @param urlKey 跳转路径的key
	 * @param msgKey 提示消息的文字key
	 * @param msgParam 占位符内容
	 */
	public void setUrlAndMsg(ModelAndView mav, String urlKey, String msgKey,String ... msgParam) {
		mav.addObject("msg", ActionResourceUtil.getMessage(msgKey, msgParam));
		mav.addObject("url", ActionResourceUtil.getPage(urlKey));
	}
	/**
	 * 社会资提示信息
	 * @param mav 要进行返回的ModelAndView对象
	 * @param msgKey 提示消息的文字key
	 * @param msgParam 占位符内容
	 */
	public void setMsg(ModelAndView mav ,String msgKey,String ... msgParam) {
		mav.addObject("msg", ActionResourceUtil.getMessage(msgKey, msgParam));
	}
}
