package cn.mldn.shopcar.service.front;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.vo.Shopcar;

public interface IShopcarServiceFront {
	/**
	 * 实现购物车数量的批量更新
	 * @param cars 要更新的数据
	 * @return 执行更新处理成功返回true
	 * @throws Exception SQL
	 */
	public boolean editBatch(List<Shopcar> cars) throws Exception ;
	
	/**
	 * 实现购物车数据的删除处理操作
	 * @param mid 用户ID
	 * @param gids 要删除的商品编号
	 * @return 删除成功返回true
	 * @throws Exception SQL
	 */
	public boolean remove(String mid,Set<Long> gids) throws Exception ;
	/**
	 * 更新指定购物数量，更新的数量不允许为0
	 * @param car 包含有用户ID、商品ID、新的数量
	 * @return 更新成功返回true
	 * @throws Exception SQL
	 */
	public boolean edit(Shopcar car) throws Exception ;
	
	/**
	 * 根据用户的编号查询出该用户所购买的商品信息，该操作要执行如下功能：
	 * 1、根据用户的编号查询出所待购买商品的全部详细信息，使用IGoodsDAO.findAllBYShopcar()；
	 * 2、根据用户id查询出所有的购物车的相关数据，使用IShopcarDAO.findAmountByMember()；
	 * @param mid 用户ID
	 * @return 返回的数据包含有两类内容：
	 * 1、key = shopcarGoods、value = 购物车中的商品的完整信息；
	 * 2、key = shopcar、value = 购物车中的商品和数量的Map集合
	 * @throws Exception SQL
	 */
	public Map<String,Object> list(String mid) throws Exception ; 
	/**
	 * 实现购物车数据的信息保存
	 * @param car 要添加到购物车之中的信息
	 * @return 添加成功返回true
	 * @throws Exception SQL
	 */
	public boolean add(Shopcar car) throws Exception ;
}
