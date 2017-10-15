package cn.mldn.util.web;

import cn.mldn.util.action.ActionResourceUtil;

/**
 * 进行分页的参数处理操作
 * @author mldn
 */
public class SplitPageUtil {
	private long currentPage = 1 ;	// 参数：cp
	private int lineSize = 5 ;		// 参数：ls
	private String column ;		// 参数：col
	private String keyWord ;	// 参数：kw
	/**
	 * 将你需要进行模糊查询的columnData（下拉框）传递到组件之中，目的是为了属性操作
	 * @param columnData 分页搜索的下拉列表数据
	 * @param handleUrlKey 设置分页路径的key
 	 */
	public SplitPageUtil(String columnData,String handleUrlKey) {
		ServletObjectUtil.getRequest().setAttribute("columnData", columnData);
		ServletObjectUtil.getRequest().setAttribute("handleUrl", ActionResourceUtil.getPage(handleUrlKey)); 
		try {	// 这行代码出错只有不传递或传递非法参数的时候出现
			this.currentPage = Long.parseLong(ServletObjectUtil.getParam().getParameter("cp")) ;
		} catch (Exception e) {}
		try {	// 如果出错就使用默认值
			this.lineSize = Integer.parseInt(ServletObjectUtil.getParam().getParameter("ls")) ;
		} catch (Exception e) {}
		this.column = ServletObjectUtil.getParam().getParameter("col") ;
		this.keyWord = ServletObjectUtil.getParam().getParameter("kw") ;
		if (this.column == null) {
			this.column = "" ;
		}
		if (this.keyWord == null) {
			this.keyWord = "" ;
		}
		ServletObjectUtil.getRequest().setAttribute("currentPage", this.currentPage);
		ServletObjectUtil.getRequest().setAttribute("lineSize", this.lineSize);
		ServletObjectUtil.getRequest().setAttribute("keyWord", this.keyWord);
		ServletObjectUtil.getRequest().setAttribute("column", this.column);
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public int getLineSize() {
		return lineSize;
	}
	public String getColumn() {
		return column;
	}
	public String getKeyWord() {
		return keyWord;
	}
}
