package cn.mldn.shopcar.service.front;

import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.vo.Orders;

public interface IOrdersServiceFront {
	/**
	 * 实现订单的详情显示，包括所购买的商品信息以及购买的商品数量
	 * @param mid 用户id
	 * @param oid 订单编号
	 * @return 返回的数据如下：
	 * 1、key = allGoods、value = 所购买的商品信息；
	 * 2、key = allDetails、value = 商品与数量的Map集合；
	 * 3、key = orders、value = 单个订单信息；
	 * @throws Exception SQL
	 */
	public Map<String,Object> show(String mid,long oid) throws Exception ;
	/**
	 * 实现用户的订单数据查询
	 * @param mid 用户ID
	 * @param currentPage 当前页
	 * @param lineSize 每页行
	 * @return 包含有如下结果：
	 * 1、key = allOrders、value = 所有的订单信息；
	 * 2、key = allRecorders、value = 订单量
	 * @throws Exception SQL
	 */
	public Map<String,Object> list(String mid,long currentPage,int lineSize) throws Exception ;
	/**
	 * 订单创建前的查询处理，在订单创建时要查询出省份的信息以及所有要购买的商品信息
	 * @param mid 用户编号
	 * @param gids 要购买的商品信息
	 * @return 返回的数据包含有如下内容：
	 * 1、key = allGoods、value = 所有的购买的商品信息；
	 * 2、key = allProvinces、value = 所有的省份信息
	 * @throws Exception SQL
	 */
	public Map<String,Object> preAdd(String mid,Set<Long> gids) throws Exception ;
	/**
	 * 实现订单创建处理
	 * @param mid 用户的编号
	 * @param gids 所有要购买的商品信息
	 * @param orders 订单的具体信息
	 * @return 订单创建成功返回true
	 * @throws Exception SQL
	 */
	public boolean add(String mid,Set<Long> gids,Orders orders) throws Exception ;
}
