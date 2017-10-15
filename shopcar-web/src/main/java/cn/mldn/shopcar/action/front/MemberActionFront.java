package cn.mldn.shopcar.action.front;

import cn.mldn.shopcar.service.front.IMemberServiceFront;
import cn.mldn.shopcar.vo.Member;
import cn.mldn.util.CookieUtil;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;

public class MemberActionFront extends AbstractAction {
	public static final String ACTION_TITLE = "用户" ;
	/**
	 * 登录前的页面跳转处理
	 * @return 返回到登录页
	 */
	public ModelAndView loginPre() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.member.login.page"));
		return mav;
	}
	/**
	 * 用户登录注销，登录注销后所有的Cookie信息将被删除
	 * @return 提示页面，随后跳转回登录页
	 */
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page"));
		CookieUtil cookieUtil = new CookieUtil(ServletObjectUtil.getRequest(),ServletObjectUtil.getResponse()) ;
		cookieUtil.cleanMid() ;
		ServletObjectUtil.getRequest().getSession().invalidate();
		super.setUrlAndMsg(mav, "front.index.page", "logout.success",ACTION_TITLE );
		return mav;
	}
	/**
	 * 验证码检测，用于ajax异步验证处理
	 * @param code 输入验证码
	 */
	public void check(String code) {
		String rand = (String) ServletObjectUtil.getRequest().getSession().getAttribute("rand") ;
		if (rand == null || "".equals(rand)) {
			super.print(false);
		} else {
			super.print(rand.equalsIgnoreCase(code));
		}
	}
	/**
	 * 用户登录处理
	 * @param vo 包含有用户登录信息
	 * @param rememberme 是否要执行免登录
	 * @return 登录成功返回信息提示页（随后跳转到商品列表页），登录失败返回登录页
	 */
	public ModelAndView login(Member vo,String rememberme) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.member.login.action"));
		IMemberServiceFront memberService = Factory.getServiceInstance("member.service.front");
		vo.setPassword(PasswordUtil.encoder(vo.getPassword()));
		try {
			if (memberService.login(vo)) {
				ServletObjectUtil.getRequest().getSession().setAttribute("mid", vo.getMid());
				mav.setUrl(ActionResourceUtil.getPage("forward.page"));
				super.setUrlAndMsg(mav, "front.index.page", "login.success",ACTION_TITLE );
				if (rememberme != null && "true".equals(rememberme)) {
					CookieUtil cookieUtil = new CookieUtil(ServletObjectUtil.getRequest(),ServletObjectUtil.getResponse()) ;
					cookieUtil.saveMid(vo.getMid()) ;	// 保存用户名到Cookie之中
				}
			} else {
				super.setMsg(mav, "login.failure");
			}
		} catch (Exception e) {
			super.setMsg(mav, "login.failure");
			e.printStackTrace();
		}
		return mav;
	}
}
