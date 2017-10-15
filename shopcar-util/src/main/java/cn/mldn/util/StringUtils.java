package cn.mldn.util;

public class StringUtils {
	/**
	 * 进行首字母大写处理
	 * @param str 要处理的字符串
	 * @return 处理后的字符串 
	 */
	public static String initcap(String str) {
		if (str == null || "".equals(str)) {
			return str ;
		}
		if (str.length() == 1) {
			return str.toUpperCase() ;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1) ;
	}
}
