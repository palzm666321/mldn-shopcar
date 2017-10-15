package cn.mldn.util;

import java.util.ResourceBundle;

public class ResourceUtil {
	private ResourceBundle resource ;
	public ResourceUtil(String baseName) {
		this.resource = ResourceBundle.getBundle(baseName) ;
	} 
	public String get(String key) {
		String val = null ;
		try {
			val = this.resource.getString(key) ;
		} catch (Exception e) {}
		return val ;
	}
}
