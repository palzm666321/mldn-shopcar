package cn.mldn.shopcar.service.front.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.shopcar.dao.IGoodsDAO;
import cn.mldn.shopcar.service.front.IGoodsServiceFront;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class GoodsServiceFrontImpl extends AbstractService implements IGoodsServiceFront {


	@Override
	public Map<String, Object> list(String column, String keyWord, long currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao");
		if (super.isLikeSearch(column) && super.isLikeSearch(keyWord)) { // 需要进行模糊查询
			map.put("allGoods", goodsDAO.findSplit(column, keyWord, currentPage, lineSize));
			map.put("allRecorders", goodsDAO.getSplitCount(column, keyWord));
		} else {
			map.put("allGoods", goodsDAO.findAll(currentPage, lineSize));
			map.put("allRecorders", goodsDAO.getAllCount());
		}
		return map;
	}


}
