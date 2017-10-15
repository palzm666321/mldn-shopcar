package cn.mldn.util.web;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.action.ActionResourceUtil;

public class ParameterValidatorUtil {
	private String validateRule ; // 验证规则
	// 保存错误信息，key = 参数名称、value = 错误提示文字（message.properties）
	private Map<String,String> errors = new HashMap<String,String>() ;
	public ParameterValidatorUtil(String validateRule) {
		this.validateRule = validateRule ;
	}
	/**
	 * 进行数据验证的处理操作，在该操作之中要针对于给定的验证规则进行处理
	 * @return 如果验证通过返回true，否则返回false
	 */
	public boolean validate() {
		String result [] = this.validateRule.split("\\|") ;
		for (int x = 0 ; x < result.length ; x ++) {
			String temp [] = result[x].split(":") ;	// 第一个是参数，第二个是规则
			this.validateParam(temp[0], temp[1]) ;	// 没有通过验证
		}
		return this.errors.size() == 0  ; 
	}
	public Map<String, String> getErrors() {
		return this.errors;
	}
	/**
	 * 对请求参数进行具体的验证操作
	 * @param paramName 参数名称
	 * @param rule 验证规则
	 * @return 验证通过返回true，否则返回false
	 */
	private boolean validateParam(String paramName,String rule) {
		String val = ServletObjectUtil.getParam().getParameter(paramName) ;	// 获得请求参数信息
		// 1、给出的是参数名称，那么必须依据参数名称获得参数的内容
		if ("string".equalsIgnoreCase(rule)) {	// 进行字符串验证
			if (!this.validateString(val)) {	// 没有通过验证
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.string.error")) ;
			}
		}
		if ("int".equalsIgnoreCase(rule)) {
			if (!this.validateInt(val)) {
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.int.error")) ;
			}
		}
		if ("long".equalsIgnoreCase(rule)) {
			if (!this.validateLong(val)) {
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.long.error")) ;
			}
		}
		if ("double".equalsIgnoreCase(rule)) {
			if (!this.validateDouble(val)) {
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.double.error")) ;
			}
		}
		if ("date".equalsIgnoreCase(rule)) {
			if (!this.validateDate(val)) {
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.date.error")) ;
			} 
		}
		if ("datetime".equalsIgnoreCase(rule)) {
			if (!this.validateDatetime(val)) {
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.datetime.error")) ;
			}
		}
		if ("rand".equalsIgnoreCase(rule)) {
			if (!this.validateRand(val)) {
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.rand.error")) ;
			}
		}
		if ("string[]".equalsIgnoreCase(rule)) {	// 进行字符串验证
			String vals [] = ServletObjectUtil.getParam().getParameterValues(paramName) ;
			if (!this.validateStringArray(vals)) {	// 没有通过验证
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.string.error")) ;
			}
		}
		if ("long[]".equalsIgnoreCase(rule)) {	// 进行字符串验证
			String vals [] = ServletObjectUtil.getParam().getParameterValues(paramName) ;
			if (!this.validateLongArray(vals)) {	// 没有通过验证
				this.errors.put(paramName, ActionResourceUtil.getMessage("validator.long.error")) ;
			}
		}
		return false ;
	}
	private boolean validateRand(String str) {
		if (this.validateString(str)) {
			String rand = (String) ServletObjectUtil.getRequest().getSession().getAttribute("rand") ;
			if (rand == null || "".equals(rand)) {
				return false ;
			}
			return str.equalsIgnoreCase(rand) ;
		}
		return false ;
	}
	private boolean validateStringArray(String str[]) {
		if (str == null || str.length == 0) {
			return false ;
		}
		for (int x = 0 ; x < str.length ; x ++) {
			if (str[x] == null || "".equals(str[x])) {	// 验证数组的每一个元素
				return false ;
			}
		}
		return true ;
	}
	private boolean validateLongArray(String str[]) {
		if (this.validateStringArray(str)) {	// 数据不为空
			for (int x = 0 ; x < str.length ; x ++) {
				if (!str[x].matches("\\d+")) {	// 验证数组的每一个元素
					return false ;
				}
			}
		}
		return true ; 
	}
	private boolean validateString(String str) {
		if (str == null || "".equals(str)) {
			return false ;
		}
		return true ;
	}
	private boolean validateInt(String str) {
		if (this.validateString(str)) {	// 数据不为空
			return str.matches("\\d+") ;
		}
		return false ;
	}
	private boolean validateLong(String str) {
		if (this.validateString(str)) {	// 数据不为空
			return str.matches("\\d+") ;
		}
		return false ;
	}
	private boolean validateDouble(String str) {
		if (this.validateString(str)) {	// 数据不为空
			return str.matches("\\d+(\\.\\d+)?") ;
		}
		return false ;
	}
	private boolean validateDate(String str) {
		if (this.validateString(str)) {	// 数据不为空
			return str.matches("\\d{4}-\\d{2}-\\d{2}") ;
		}
		return false ;
	}
	private boolean validateDatetime(String str) {
		if (this.validateString(str)) {	// 数据不为空
			return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
		}
		return false ;
	}
}
