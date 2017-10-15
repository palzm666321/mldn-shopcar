package cn.mldn.shopcar.action.front;

import java.util.ArrayList;
import java.util.List;

import cn.mldn.shopcar.service.front.IShopcarServiceFront;
import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;

public class ShopcarActionFront extends AbstractAction {
	/**
	 * 执行购物车数据的批量修改操作
	 * @param data 购物车数据，组成：“gid:amount,gid:amount,”
	 */
	public void updateBatch(String data) {
		List<Shopcar> all = new ArrayList<Shopcar>() ;
		String result [] = data.split(",") ;
		for (int x = 0 ; x < result.length ; x ++) {
			String temp [] = result[x].split(":") ;
			Shopcar car = new Shopcar() ;
			car.setMid(super.getMid());
			car.setGid(Long.parseLong(temp[0]));
			car.setAmount(Integer.parseInt(temp[1]));
			all.add(car) ;
		}
		IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
		try {
			super.print(shopcarService.editBatch(all));
		} catch (Exception e) {
			e.printStackTrace(); 
			super.print(false); 
		}
	}
	/**
	 * 执行购物车数据的批量删除操作
	 * @param gids 要删除的全部的id数据
	 */
	public void remove(String gids) {
		IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
		try {
			super.print(shopcarService.remove(super.getMid(), super.handleLong(gids)));
		} catch (Exception e) {
			e.printStackTrace();
			super.print(false); 
		}
	}
	
	/**
	 * 进行单个商品购买数量的变更
	 * @param car 单个商品的信息
	 */
	public void edit(Shopcar car) {
		IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
		car.setMid(super.getMid());
		try {
			super.print(shopcarService.edit(car));
		} catch (Exception e) {
			e.printStackTrace();
			super.print(false);
		} 
	}
	
	/**
	 * 进行购物车数据的添加处理操作， 该操作使用ajax异步接收处理
	 * @param car 可以传递过来的数据只有一个商品ID
	 */ 
	public void add(Shopcar car) {
		IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
		car.setMid(super.getMid()); // 通过session获得已经登录的用户id
		try {
			super.print(shopcarService.add(car));
		} catch (Exception e) {
			e.printStackTrace();
			super.print(false);
		}
	}
	/**
	 * 根据用户的登录的编号查询购物车中的所有商品信息
	 * @return 购物车列表显示页面
	 */
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("front.shopcar.list.page")) ;
		IShopcarServiceFront shopcarService = Factory.getServiceInstance("shopcar.service.front") ;
		try {
			mav.addObjectMap(shopcarService.list(super.getMid()));
		} catch (Exception e) {
		}
		return mav ;
	}
}
