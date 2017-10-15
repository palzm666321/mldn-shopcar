package cn.mldn.shopcar.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.mldn.shopcar.vo.Details;
import cn.mldn.util.dao.IBaseDAO;

public interface IDetailsDAO extends IBaseDAO<Long, Details> {
	/**
	 * 根据订单编号查询订单详情中的数据，主要就是商品编号以及购买数量 
	 * @param oid 订单编号
	 * @return 返回的集合组成：key = 商品编号、value = 购买数量 
	 * @throws SQLException SQL
	 */
	public Map<Long,Integer> findAllByOrders(Long oid) throws SQLException ;
	/**
	 * 实现订单详情信息的保存
	 * @param details 所有的订单详情
	 * @return 保存成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doCreateBatch(List<Details> details) throws SQLException ;
}
