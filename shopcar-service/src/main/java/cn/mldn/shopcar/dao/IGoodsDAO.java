package cn.mldn.shopcar.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.shopcar.vo.Goods;
import cn.mldn.util.dao.IBaseDAO;

public interface IGoodsDAO extends IBaseDAO<Long, Goods> {
	/**
	 * 根据用户的编号查询出该用户购物车之中的所有商品信息的详情
	 * @param mid 用户编号
	 * @return 返回所有的购买的商品信息
	 * @throws SQLException JDBC 
	 */
	public List<Goods> findAllByShopcar(String mid) throws SQLException ;
	/**
	 * 该方法的主要功能是根据指定的用户ID以及要购买的商品编号查询出所有的商品信息
	 * @param mid 用户编号
	 * @param gids 商品编号集合
	 * @return 根据商品编号查询数据
	 * @throws SQLException JDBC
	 */
	public List<Goods> findAllByMemberAndGoods(Set<Long> gids) throws SQLException ;
}
