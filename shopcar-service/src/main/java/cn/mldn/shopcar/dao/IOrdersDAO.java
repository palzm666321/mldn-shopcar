package cn.mldn.shopcar.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.shopcar.vo.Orders;
import cn.mldn.util.dao.IBaseDAO;

public interface IOrdersDAO extends IBaseDAO<Long, Orders> {
	/**
	 * 根据订单编号和用户名查询订单信息
	 * @param oid 订单编号
	 * @param mid 用户名
	 * @return 指定用户一个订单的信息
	 * @throws SQLException SQL
	 */
	public Orders findByIdAndMember(Long oid,String mid) throws SQLException ;
	/**
	 * 实现用户的订单信息查询
	 * @param mid 用户ID
	 * @param currentPage 当前页
	 * @param lineSize 每页显示行
	 * @return 用户的部分订单信息
	 * @throws SQLException SQL
	 */
	public List<Orders> findAllByMember(String mid,Long currentPage,Integer lineSize) throws SQLException ;
	/**
	 * 根据用户名称查询订单数量
	 * @param mid 用户名
	 * @return 订单数量
	 * @throws SQLException SQL
	 */
	public Long getAllCountByMember(String mid) throws SQLException ;
	
	/**
	 * 取得当前增长后的ID信息
	 * @return 当前增长后的id
	 * @throws SQLException SQL 
	 */
	public Long getCreateId() throws SQLException ;
}
