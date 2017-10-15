package cn.mldn.shopcar.action.front;

import cn.mldn.shopcar.service.front.IOrdersServiceFront;
import cn.mldn.shopcar.vo.Orders;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.SplitPageUtil;

public class OrdersActionFront extends AbstractAction {
	public static final String ACTION_TITLE = "订单" ;
	/**
	 * 实现订单信息列表显示
	 * @return 跳转到订单列表页
	 */
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.orders.list.page")) ;
		SplitPageUtil spu = new SplitPageUtil(null, "front.orders.list.action");
		IOrdersServiceFront ordersService = Factory.getServiceInstance("orders.service.front") ;
		try {
			mav.addObjectMap(ordersService.list(super.getMid(), spu.getCurrentPage(), spu.getLineSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	/**
	 * 实现订单创建处理
	 * @return 订单创建页面
	 */
	public ModelAndView add(Orders orders,String gids) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		orders.setMid(super.getMid());
		IOrdersServiceFront ordersService = Factory.getServiceInstance("orders.service.front") ;
		try {
			if (ordersService.add(super.getMid(), super.handleLong(gids), orders)) {
				super.setUrlAndMsg(mav, "front.orders.list.action", "vo.add.success",ACTION_TITLE );
			} else {
				super.setUrlAndMsg(mav, "front.orders.list.action", "vo.add.failure",ACTION_TITLE );
			}
		} catch (Exception e) {
			super.setUrlAndMsg(mav, "front.orders.list.action", "vo.add.failure",ACTION_TITLE );
		}
		return mav ;
	}
	/**
	 * 查看订单详情信息
	 * @return 订单详情显示
	 */
	public ModelAndView show(long oid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.orders.show.page")) ;
		IOrdersServiceFront ordersService = Factory.getServiceInstance("orders.service.front") ;
		try {
			mav.addObjectMap(ordersService.show(super.getMid(), oid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	/**
	 * 实现订单创建前的处理
	 * @return 订单创建页面
	 */
	public ModelAndView addPre(String gids) {
		IOrdersServiceFront ordersService = Factory.getServiceInstance("orders.service.front") ;
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.orders.addpre.page")) ;
		try {
			mav.addObjectMap(ordersService.preAdd(super.getMid(), super.handleLong(gids)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
}
