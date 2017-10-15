package cn.mldn.util.web;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.servlet.http.HttpServletRequest;

import cn.mldn.util.DataConverterUtil;
import javassist.ClassClassPath;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class ParameterValueUtil {
	/**
	 * 判断给定的参数类型是否是普通类型（long、Long、int、Integer、Double、double、Date）
	 * @param type 判断类型
	 * @return 如果是普通类型返回true
	 */
	public static boolean isBasic(String type) {
		return "long".equals(type) || "java.lang.Long".equals(type) || "int".equals(type) || "java.lang.Integer".equals(type) || "double".equals(type)
				|| "java.lang.Double".equals(type) || "java.lang.String".equals(type) || "java.util.Date".equals(type);
	} 
	/**
	 * 判断类型是否是数组类型
	 * @param type 要判断的类型
	 * @return 如果是数组返回true，否则返回false
	 */
	public static boolean isArray(String type) {
		return type.contains("[]") ;	// 数组有“[]”
	}
	
	/**
	 * 进行Action类中方法参数的设置
	 * @param request 主要用于接收请求参数
	 * @param actionObject 要操作的Action对象
	 * @param actionMethod 要调用的方法
	 * @return 方法的返回类型
	 * @throws Exception 产生的反射异常
	 */
	public static Object setActionMethodParameterValue(HttpServletRequest request , Object actionObject, Method actionMethod) throws Exception {
		// 1、获取方法参数中的所有参数的名称，该名称一定与表单的提交参数名称一一对应
		String paramNames [] = getActionParameterNames(actionObject.getClass(),actionMethod) ;
		// 2、定义一个接收所有参数数据内容的数组
		Object [] values = new Object [paramNames.length] ;
		// 3、接收一个方法之中所有的类型，这样就可以实现参数类型的获取了
		Class<?> types [] = actionMethod.getParameterTypes() ;
		// 4、接收到所有的方法参数之后肯定要进行参数的接收，接收参数统一使用request.getParameter()处理
		for (int x = 0 ; x < paramNames.length ; x ++) {
			try {
				if (isBasic(types[x].getName())) {	// 你是一个普通类型
					String value = ServletObjectUtil.getParam().getParameter(paramNames[x]) ;
					values [x] = DataConverterUtil.converter(value, types[x].getName()) ;
				} else if (isArray(types[x].getSimpleName())) {
					String value[] = ServletObjectUtil.getParam().getParameterValues(paramNames[x]) ;	// 按照数组进行处理
					values[x] = DataConverterUtil.converterArray(value, types[x].getSimpleName());
				} else {	// 现在不是一个普通类型，可能就是VO类
					// 1、获取VO的类型，获取VO类型之后就可以利用反射进行实例化了
					values [x] = DataConverterUtil.converterVO(types[x]) ;
				}
			} catch (Exception e) {}
		} 
		return actionMethod.invoke(actionObject, values) ; 
	}
	/**
	 * 获取Action上方法的参数的名称信息，利用javassist开发包可以获取
	 * @param actionClass 要处理执行的Action类名称
	 * @param actionMethod 要执行的方法的处理对象
	 * @return 参数名称数组
	 * @throws Exception 产生的反射异常
	 */
	private static String[] getActionParameterNames(Class<?> actionClass,Method actionMethod) throws Exception {
		Class<?> params [] = actionMethod.getParameterTypes() ;	// 方法中全部参数的类型
		String paramNames [] = new String [params.length] ;
		// 随后如果要想获取参数的名称信息，则就需要使用Javassist开发包之中所给的类进行处理了
		ClassPool classPool = ClassPool.getDefault() ; // 定义要进行解析处理的工具类
		// 如果javassist开发包与Tomcat整合一起的话，那么就会出现Tomcat的CLASSPATH覆盖了Javassist设置的CLASSPATH
		ClassPath classPath = new ClassClassPath(actionClass) ;	// 将传递进来的Action的类型设置到ClassPath之中
		classPool.insertClassPath(classPath) ;	// 修改了javassist的CLASSPATH
		CtClass ctClass = classPool.get(actionClass.getName()) ;	// 定义要操作的字节码文件，这个文件一定在CLASSPATH中
		// 获取要进行字节码解析部分的class的二进制数据信息
		CtMethod ctMethod = ctClass.getDeclaredMethod(actionMethod.getName()) ;	// 获取操作的方法信息
		MethodInfo methodInfo = ctMethod.getMethodInfo() ;	// 获取要解析的二进制的方法具体内容
		// 解析二进制的字节码文件获取相关全部属性内容
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute() ;	// 得到源代码之中定义的相关属性信息
		// 现在需要的是方法中参数的名称，参数名称就属于一个标记，所有的参数信息都在attribute之中
		LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag) ;
		int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1 ;
		for (int x = 0 ; x < params.length ; x ++) {
			paramNames [x] = attribute.variableName(x + pos) ;// 根据参数定义的顺序取出参数的名称
		}
		return paramNames ;
	}
}
