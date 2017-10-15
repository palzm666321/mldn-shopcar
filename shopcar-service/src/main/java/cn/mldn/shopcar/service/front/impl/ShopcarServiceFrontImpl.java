package cn.mldn.shopcar.service.front.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.dao.IGoodsDAO;
import cn.mldn.shopcar.dao.IShopcarDAO;
import cn.mldn.shopcar.service.front.IShopcarServiceFront;
import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class ShopcarServiceFrontImpl extends AbstractService implements IShopcarServiceFront {
	@Override
	public boolean editBatch(List<Shopcar> cars) throws Exception {
		if (cars == null || cars.size() == 0) {
			return false ;
		}
		List<Shopcar> updateList = new ArrayList<Shopcar>() ;
		List<Shopcar> deleteList = new ArrayList<Shopcar>() ;
		Iterator<Shopcar> iter = cars.iterator() ; 
		while (iter.hasNext()) {
			Shopcar car = iter.next() ;
			if (car.getAmount() == 0) {	// 要删除的数据
				deleteList.add(car) ;
			} else {
				updateList.add(car) ;
			}
		}
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		boolean updateFlag = false ;
		boolean deleteFlag = false ;
		if (updateList.size() > 0) {
			updateFlag = shopcarDAO.doEditBatch(updateList) ;
		}
		if (deleteList.size() > 0) {
			deleteFlag = shopcarDAO.doRemoveBatch(deleteList) ;
		}
		return updateList.size() > 0 && updateFlag || deleteList.size() > 0 && deleteFlag ; 
	}
	
	@Override
	public boolean remove(String mid, Set<Long> gids) throws Exception {
		if (gids == null || gids.size() == 0) {
			return false ;
		}
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		return shopcarDAO.doRemoveByMemberAndGoods(mid, gids); 
	} 
	@Override
	public boolean edit(Shopcar car) throws Exception {
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		if (car.getAmount() > 0) {
			return shopcarDAO.doEditShopcarAmount(car) ; 
		} else {	// 小于等于0
			return shopcarDAO.doRemoveByMemberAndGoods(car.getMid(),car.getGid()) ;
		}
 	}
	@Override
	public Map<String, Object> list(String mid) throws Exception {
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("shopcarGoods", goodsDAO.findAllByShopcar(mid)) ;
		map.put("shopcar", shopcarDAO.findAmountByMember(mid)) ;
		return map ;
	}
	
	@Override
	public boolean add(Shopcar car) throws Exception {
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		// 第一次添加购物车的时候购物车中的商品数量是1
		car.setAmount(1); 	// 现在第一次添加
		// 根据用户名以及商品编号判断该购物车信息是否已经添加过了，如果添加过了则不会返回null
		if (shopcarDAO.findByMemberAndGoods(car.getMid(), car.getGid()) != null) { 
			return shopcarDAO.doEditAmount(car) ;
		} else {
			return shopcarDAO.doCreate(car);
		} 
	}

}
