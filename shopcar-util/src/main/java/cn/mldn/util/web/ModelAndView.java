package cn.mldn.util.web;

import java.util.Iterator;
import java.util.Map;

/**
 * 该类的主要功能将控制层之中的属性设置到request范围之内，而后再设置跳转路径
 * @author mldn
 */
public class ModelAndView {
	private String url ;	// 跳转的JSP视图路径
	public ModelAndView() {}
	public ModelAndView(String url) {
		this.url = url ;
	}
	/**
	 * 将map集合的内容设置到属性范围之中，其中map的key就是属性名字，而map的value就是属性内容
	 * @param map map集合
	 */
	public void addObjectMap(Map<String,Object> map) {
		if (map == null || map.size() == 0) {
			return ;
		}
		Iterator<Map.Entry<String,Object>> iter = map.entrySet().iterator() ;
		while (iter.hasNext()) {
			Map.Entry<String,Object> me = iter.next() ;
			ServletObjectUtil.getRequest().setAttribute(me.getKey(), me.getValue());
		}
	}
	/**
	 * 设置要保存到JSP视图中的属性名称和属性内容，保存范围默认就是request
	 * @param name request属性名称
	 * @param value request属性内容
	 */
	public void addObject(String name,Object value) {
		ServletObjectUtil.getRequest().setAttribute(name, value);
	}
	public void setUrl(String url) {	// 修改跳转路径
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}
