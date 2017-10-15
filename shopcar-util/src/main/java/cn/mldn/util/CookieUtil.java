package cn.mldn.util;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.util.enctype.PasswordUtil;

public class CookieUtil {
	private HttpServletRequest request ;
	private HttpServletResponse response ;
	public CookieUtil(HttpServletRequest request,HttpServletResponse response) {
		this.request = request ;
		this.response = response ;
	}
	/**
	 * 清除保存的member的数据信息
	 */
	public void cleanMid() {
		Cookie cookie = new Cookie("member","hello") ;
		cookie.setPath(this.request.getContextPath()); 
		cookie.setMaxAge(0);
		this.response.addCookie(cookie);	// 保存Cookie的数据
	}
	/**
	 * 向Cooke之中保存有mid的数据信息，但是这个mid在进行保存的时候应该考虑加密处理
	 * @param mid 要保存的用户百年好
	 */
	public void saveMid(String mid) {	
		Cookie cookie = new Cookie("member",PasswordUtil.encoderString(mid)) ;
		cookie.setPath(this.request.getContextPath()); 
		// 转换之后的类型为long，而setMaxAge()需要的是int类型
		cookie.setMaxAge((int) TimeUnit.SECONDS.convert(10L, TimeUnit.DAYS));
		this.response.addCookie(cookie);	// 保存Cookie的数据
	}
	/**
	 * 通过Cookie读取保存的用户名
	 * @return 返回保存的用户名，如果没有保存返回null
	 */
	public String loadMid() {
		Cookie cookie [] = this.request.getCookies() ;
		if (cookie == null) {
			return null ;
		}
		for (int x = 0 ; x < cookie.length ; x ++) {
			if ("member".equals(cookie[x].getName())) {
				return PasswordUtil.decoderString(cookie[x].getValue()) ;	// 取得用户名
			}
		}
		return null ; 
	}
}
