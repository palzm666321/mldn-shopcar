package cn.mldn.util;

import java.lang.reflect.Method;

public class MethodUtil {	// 获取指定类对象的方法信息
	private MethodUtil() {}
	/**
	 * 根据指定的Class类对象获得指定的处理方法
	 * @param cls 要操作的Class类对象
	 * @param methodName 要取得操作方法
	 * @return 如果有指定方法返回方法对象，如果没有返回null
	 */
	public static Method getMethod(Class<?> cls,String methodName) {
		Method methods [] = cls.getMethods() ;	// 获取全部的方法
		for (int x = 0 ; x < methods.length ; x ++) {
			if (methodName.equals(methods[x].getName())) {
				return methods[x] ;
			}
		}
		return null ; 
	}
}
