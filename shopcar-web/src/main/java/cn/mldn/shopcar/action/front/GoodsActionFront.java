package cn.mldn.shopcar.action.front;

import cn.mldn.shopcar.service.front.IGoodsServiceFront;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.SplitPageUtil;

public class GoodsActionFront extends AbstractAction {
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.goods.list.page"));
		SplitPageUtil spu = new SplitPageUtil("商品名称:name", "front.goods.list.action");
		IGoodsServiceFront newsService = Factory.getServiceInstance("goods.service.front");
		try {
			mav.addObjectMap(
					newsService.list(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
