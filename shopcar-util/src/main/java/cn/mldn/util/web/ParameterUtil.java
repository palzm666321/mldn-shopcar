package cn.mldn.util.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 进行所有请求参数的处理，该处理的操作之中可以帮助用户去避免表单是否封装的判断问题
 * @author mldn
 */
public class ParameterUtil {
	private static final long MAX_SIZE = 3221225472L ;
	private static final long FILE_MAX_SIZE = 1073741824L ;
	private static final String DEFAULT_CHARSET = "UTF-8" ;
	// 保存处理后的参数内容，其中key表示参数名称，而value表示的是参数内容
	private Map<String, List<FileItem>> paramMap = new HashMap<String, List<FileItem>>() ;
	private ServletFileUpload upload ;	// 进行上传处理的工具类
	private HttpServletRequest request ;	// 接收用户的请求对象
	private long maxSize ;	// 上传最大限制
	private long fileMaxSize ;	// 单个文件的限制
	private String tempDir = null ;	// 临时目录
	private String charset = null ;
	/**
	 * 进行ParameterUtil工具类的实例化
	 * @param request 请求对象
	 * @param tempDir 临时的保存目录
	 */
	public ParameterUtil(HttpServletRequest request,String tempDir) {
		this(request, tempDir, MAX_SIZE, FILE_MAX_SIZE,DEFAULT_CHARSET);
	}
	/**
	 * 进行ParameterUtil工具类的实例化
	 * @param request 请求对象
	 * @param tempDir 临时的保存目录
	 * @param maxSize 设置上传总大小
	 * @param fileMaxSize 单个上传文件的大小
	 * @param charset 文件的编码
	 */
	public ParameterUtil(HttpServletRequest request,String tempDir,long maxSize,long fileMaxSize,String charset) {
		this.request = request ;	// 参数保存
		this.tempDir = tempDir ;	// 参数保存
		this.maxSize = maxSize ;	// 参数保存
		this.fileMaxSize = fileMaxSize ;	// 参数保存
		this.charset = charset ;
		 
		if (!(this.request.getContentType() == null
				|| this.request.getContentType().contains("application/x-www-form-urlencoded"))) { // 直接提交请求
			DiskFileItemFactory factory = new DiskFileItemFactory() ;
			// request对象里面可以调用getServletContext()操作方法，以获得当前的application对象
			factory.setRepository(new File(request.getServletContext().getRealPath(this.tempDir))) ;
			this.upload = new ServletFileUpload(factory) ;	// 上传配置
			this.upload.setSizeMax(this.maxSize) ;	// 允许上传3M的总文件量
			this.upload.setFileSizeMax(this.fileMaxSize) ;	// 每个上传文件最多1M文件量
			try {
				this.paramMap = this.upload.parseParameterMap(this.request) ;
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据指定的参数名称获取参数的内容
	 * @param paramName 参数名称
	 * @return 返回参数内容，如果没有返回null
	 */
	public String getParameter(String paramName) {
		if (this.request.getContentType() == null) { // 直接提交请求
			return this.request.getParameter(paramName) ;
		}
		if (this.request.getContentType().contains("application/x-www-form-urlencoded")) {
			return this.request.getParameter(paramName) ;
		}
		if (this.paramMap.containsKey(paramName)) {	// 如果现在有指定的参数
			List<FileItem> allItems = this.paramMap.get(paramName) ;	// 所有保存的都是一组FileItem
			FileItem item = allItems.get(0) ;	// 既然只有一个参数，那么就获得第一个
			if (item.isFormField()) {	// 如果是一个普通的文本参数
				try {
					return item.getString(this.charset) ;
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		return null ;
	}
	/**
	 * 根据指定的参数名称获取该参数的全部内容（复选框） 
	 * @param paramName 参数名称
	 * @return 没有参数返回null
	 */
	public String [] getParameterValues(String paramName) {
		if (this.request.getContentType() == null) { // 直接提交请求
			return this.request.getParameterValues(paramName) ;
		}
		if (this.request.getContentType().contains("application/x-www-form-urlencoded")) {
			return this.request.getParameterValues(paramName) ;
		}
		if (this.paramMap.containsKey(paramName)) {	// 如果现在有指定的参数
			List<FileItem> allItems = this.paramMap.get(paramName) ;	// 所有保存的都是一组FileItem
			String backResult [] = new String [allItems.size()]  ; 	// 创建数组
			for (int x = 0 ; x < backResult.length ; x ++) {
				if (allItems.get(x).isFormField()) {
					try {
						backResult[x] = allItems.get(x).getString(this.charset) ;
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			return backResult ;
		}
		return null ;
	}
	/**
	 * 判断是否有指定的参数进行文件上传
	 * @param paramName 要处理的参数
	 * @return 如果有文件上传，则返回true，否则返回false
	 */
	public boolean isUpload(String paramName) {
		if (this.request.getContentType() == null) {
			return false ;
		}
		if (this.request.getContentType().contains("application/x-www-form-urlencoded")) {
			return false ;	// 没有名字处理
		}
		if (this.paramMap.containsKey(paramName)) {
			List<FileItem> fileItem = this.paramMap.get(paramName) ;
			Iterator<FileItem> iter = fileItem.iterator() ;
			while (iter.hasNext()) {
				FileItem item = iter.next() ;
				if (item.getSize() <= 0) {
					return false ;
				}
			}
		}
		return true ;
	} 
	/**
	 * 根据指定的参数创建要保存的文件的名称
	 * @param paramName 参数名称
	 * @return 创建好的文件名称
	 */
	public List<String> createUploadFileName(String paramName) {
		List<String> allNames = new ArrayList<String>() ;
		if (this.request.getContentType() == null) {
			return allNames ;
		}
		if (this.request.getContentType().contains("application/x-www-form-urlencoded")) {
			return allNames ;	// 没有名字处理
		}
		if (this.paramMap.containsKey(paramName)) {
			List<FileItem> fileItem = this.paramMap.get(paramName) ;
			Iterator<FileItem> iter = fileItem.iterator() ;
			while (iter.hasNext()) {
				FileItem item = iter.next() ;
				if (!item.isFormField()) {	// 是上传文件
					String fileName = UUID.randomUUID().toString() + "."
							+ item.getContentType().substring(item.getContentType().lastIndexOf("/") + 1);
					allNames.add(fileName) ;	// 保存文件的名称
				}
			}
		}
		return allNames ;
	}
	/**
	 * 保存一组上传文件信息
	 * @param paramName 要保存的文件的参数名称
	 * @param savePath 路径
	 * @return 保存成功返回true，否则返回false
	 */
	public boolean saveUploadFile(String paramName , List<String> savePath) {
		if (this.request.getContentType() == null) {
			return false ;
		}
		if (this.request.getContentType().contains("application/x-www-form-urlencoded")) {
			return false ;	// 没有名字处理
		}
		if (this.paramMap.containsKey(paramName)) {
			for (int x = 0 ; x < savePath.size() ; x ++) {
				FileItem item = this.paramMap.get(paramName).get(x) ;
				if (!item.isFormField()) {	// 是二进制文件
					try {
						item.write(new File(savePath.get(x)));
					} catch (Exception e) {
						return false ;
					}
				}
			}
		}
		return true ;
	}
	/**
	 * 保存上传文件的单个文件数据
	 * @param paramName 要保存的文件的参数名称
	 * @param savePath 路径
	 * @return 保存成功返回true，否则返回false
	 */
	public boolean saveUploadFile(String paramName , String savePath) {
		if (this.request.getContentType() == null) {
			return false ;
		}
		if (this.request.getContentType().contains("application/x-www-form-urlencoded")) {
			return false ;	// 没有名字处理
		}
		if (this.paramMap.containsKey(paramName)) {
			FileItem item = this.paramMap.get(paramName).get(0) ;
			if (!item.isFormField()) {	// 是二进制文件
				try {
					item.write(new File(savePath));
				} catch (Exception e) {
					return false ;
				}
			}
		}
		return true ;
	}
	/**
	 * 获取全部请求参数的名字
	 * @return 全部请求参数的名字
	 */
	public Set<String> getParameterNames() {
		if (this.request.getContentType() == null) {
			return this.request.getParameterMap().keySet() ;
		} 
		if (this.request.getContentType().contains("application/x-www-form-urlencoded")) {
			return this.request.getParameterMap().keySet() ;
		}
		return this.paramMap.keySet() ;
	}
}
