package cn.mldn.util.factory;

import cn.mldn.util.ResourceUtil;
import cn.mldn.util.service.ServiceProxy;

public class Factory {
	private static ResourceUtil DAO_RESOURCE = new ResourceUtil("cn.mldn.resource.dao") ;
	private static ResourceUtil SERVICE_RESOURCE = new ResourceUtil("cn.mldn.resource.service") ;
	private Factory() {}	// 本类不需要构造方法
	@SuppressWarnings("unchecked")
	public static <T> T getServiceInstance(String serviceKey) {
		Object proxy = null ;
		try {
			Object realObject = Class.forName(SERVICE_RESOURCE.get(serviceKey)).newInstance() ;
			proxy = new ServiceProxy().bind(realObject) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) proxy ; 
	}
	@SuppressWarnings("unchecked")
	public static <T> T getDAOInstance(String daoKey) {
		try { 
			return (T) Class.forName(DAO_RESOURCE.get(daoKey)).newInstance() ;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
}
