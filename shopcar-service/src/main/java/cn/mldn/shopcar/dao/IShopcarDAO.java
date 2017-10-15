package cn.mldn.shopcar.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.dao.IBaseDAO;

public interface IShopcarDAO extends IBaseDAO<Long, Shopcar> {
	/**
	 * 实现购物车数据的批量修改处理
	 * @param cars 所有要修改的新数量
	 * @return 修改成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doEditBatch(List<Shopcar> cars) throws SQLException ;
	/**
	 * 实现购物车数据的批量删除处理
	 * @param cars 所有要删除的数据
	 * @return 修改成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doRemoveBatch(List<Shopcar> cars) throws SQLException ;
	
	/**
	 * 根据用户名以及要移除的商品编号从购物车之中删除数据
	 * @param mid 用户名
	 * @param gids 商品编号
	 * @return 删除成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doRemoveByMemberAndGoods(String mid,Long gid) throws SQLException ;
	/**
	 * 根据用户名以及要移除的商品编号从购物车之中删除数据
	 * @param mid 用户名
	 * @param gids 商品编号
	 * @return 删除成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doRemoveByMemberAndGoods(String mid,Set<Long> gids) throws SQLException ;
	
	/**
	 * 使用新的数量进行购物车指定商品的购买数量的变更操作
	 * @param vo 包含有新的购买数量的购物车内容
	 * @return 更新成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doEditShopcarAmount(Shopcar vo) throws SQLException ;
	
	/**
	 * 根据用户ID获取该用户的所有购物车中的商品购买数量信息
	 * @param mid 用户编号
	 * @return Map集合中的key为商品编号、value为商品数量
	 * @throws SQLException JDBC
	 */
	public Map<Long,Integer> findAmountByMember(String mid) throws SQLException ;
	
	/**
	 * 根据用户ID获取该用户的所有购物车中的商品购买数量信息
	 * @param mid 用户编号
	 * @param gids 商品编号
	 * @return Map集合中的key为商品编号、value为商品数量
	 * @throws SQLException JDBC
	 */
	public Map<Long,Integer> findAmountByMember(String mid,Set<Long> gids) throws SQLException ;
	/**
	 * 如果要想确认某一个商品已经添加过了，则可以根据用户名和商品编号进行数据查询
	 * @param mid 用户编号
	 * @param gid 商品编号
	 * @return 如果已经添加则返回购买的shopcar的对象，否则返回null
	 * @throws SQLException SQL
	 */
	public Shopcar findByMemberAndGoods(String mid,Long gid) throws SQLException ; 
	/**
	 * 实现购物车中某一个用户的某一商品购买数量的修改
	 * @param car 包括了用户的id，商品id，以及要追加的数量
	 * @return 更新amount字段成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doEditAmount(Shopcar car) throws SQLException ;
}
