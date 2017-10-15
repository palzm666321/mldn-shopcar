package cn.mldn.shopcar.service.front.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.dao.IDetailsDAO;
import cn.mldn.shopcar.dao.IGoodsDAO;
import cn.mldn.shopcar.dao.IOrdersDAO;
import cn.mldn.shopcar.dao.IProvinceDAO;
import cn.mldn.shopcar.dao.IShopcarDAO;
import cn.mldn.shopcar.service.front.IOrdersServiceFront;
import cn.mldn.shopcar.vo.Details;
import cn.mldn.shopcar.vo.Goods;
import cn.mldn.shopcar.vo.Orders;
import cn.mldn.util.MyMath;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class OrdersServiceFrontImpl extends AbstractService implements IOrdersServiceFront {
	@Override
	public Map<String, Object> show(String mid, long oid) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>() ;
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao") ;
		Orders orders = ordersDAO.findByIdAndMember(oid, mid) ;
		map.put("orders", orders) ;
		if (orders != null) {	// 该订单存在
			IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
			IDetailsDAO detailsDAO = Factory.getDAOInstance("details.dao") ;
			Map<Long,Integer> allDetails = detailsDAO.findAllByOrders(oid) ;
			map.put("allDetails", allDetails) ;
			map.put("allGoods", goodsDAO.findAllByMemberAndGoods(allDetails.keySet())) ;
		}
		return map ;
	}
	
	@Override
	public Map<String, Object> list(String mid, long currentPage, int lineSize) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao") ;
		map.put("allOrders", ordersDAO.findAllByMember(mid, currentPage, lineSize)) ; 
		map.put("allRecorders", ordersDAO.getAllCountByMember(mid)) ;
		return map;
	}
	@Override
	public boolean add(String mid, Set<Long> gids,Orders orders) throws Exception {
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao") ;
		IDetailsDAO detailsDAO = Factory.getDAOInstance("details.dao") ;
		// 1、首先需要知道购买商品的信息是什么，因为没有这些信息无法实现总量的计算
		List<Goods> allGoods = goodsDAO.findAllByMemberAndGoods(gids)  ;	// 得到所有的商品信息，目的是为了得到价格
		// 2、如果要计算总价不能够光有价格，还需要有数量，数量在购物车中保存
		Map<Long, Integer> shopcar = shopcarDAO.findAmountByMember(mid, gids) ;
		// 3、实现总价的计算处理
		double allPrice = 0.0 ;
		Iterator<Goods> iterGoods = allGoods.iterator() ;
		while (iterGoods.hasNext()) {
			Goods tempGoods = iterGoods.next() ;
			allPrice += tempGoods.getPrice() * shopcar.get(tempGoods.getGid()) ;
		}
		orders.setPrice(MyMath.round(allPrice, 2));
		orders.setSubdate(new Date());
		if (ordersDAO.doCreate(orders)) {	// 4、创建订单信息
			// 5、获取当前的订单ID信息
			long oid = ordersDAO.getCreateId() ;
			// 6、有了订单信息需要创建订单详情内容
			List<Details> allDetails = new ArrayList<Details>() ;
			Iterator<Long> iterGids = gids.iterator() ;
			while (iterGids.hasNext()) {
				Long gid = iterGids.next() ;
				Details dt = new Details() ;
				dt.setOid(oid);
				dt.setGid(gid);
				dt.setAmount(shopcar.get(gid)); 
				allDetails.add(dt) ;	// 保存所有订单详情数据
			}
			if (detailsDAO.doCreateBatch(allDetails)) {	// 购物车里面要删除对应的商品信息
				return shopcarDAO.doRemoveByMemberAndGoods(mid, gids) ;
			}
		}
		return false ;
	}  
	
	@Override
	public Map<String, Object> preAdd(String mid, Set<Long> gids) throws Exception {
		if (gids == null || gids.size() == 0) {
			return null ;
		}
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		IProvinceDAO provinceDAO = Factory.getDAOInstance("province.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allProvinces", provinceDAO.findAll()) ;
		map.put("allGoods", goodsDAO.findAllByMemberAndGoods(gids)) ;
		map.put("allShopcars", shopcarDAO.findAmountByMember(mid, gids)) ;
		return map;
	}

}
