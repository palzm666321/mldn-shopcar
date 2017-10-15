package cn.mldn.shopcar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.shopcar.dao.IOrdersDAO;
import cn.mldn.shopcar.vo.Orders;
import cn.mldn.util.dao.abs.AbstractDAO;

public class OrdersDAOImpl extends AbstractDAO implements IOrdersDAO {
	@Override
	public Orders findByIdAndMember(Long oid, String mid) throws SQLException {
		String sql = "SELECT oid,mid,pid,cid,subdate,price,note,name,phone,address"
				+ " FROM orders WHERE mid=? AND oid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setLong(2, oid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Orders ods = new Orders() ;
			ods.setOid(rs.getLong(1));
			ods.setMid(rs.getString(2));
			ods.setPid(rs.getLong(3));
			ods.setCid(rs.getLong(4));
			ods.setSubdate(rs.getDate(5));
			ods.setPrice(rs.getDouble(6));
			ods.setNote(rs.getString(7));
			ods.setName(rs.getString(8));
			ods.setPhone(rs.getString(9));
			ods.setAddress(rs.getString(10)); 
			return ods ;
		}
		return null;
	} 
	@Override
	public List<Orders> findAllByMember(String mid, Long currentPage, Integer lineSize) throws SQLException {
		List<Orders> allOrders = new ArrayList<Orders>() ;
		String sql = "SELECT oid,mid,pid,cid,subdate,price,note,name,phone,address"
				+ " FROM orders WHERE mid=? ORDER BY subdate DESC LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Orders ods = new Orders() ;
			ods.setOid(rs.getLong(1));
			ods.setMid(rs.getString(2));
			ods.setPid(rs.getLong(3));
			ods.setCid(rs.getLong(4));
			ods.setSubdate(rs.getDate(5));
			ods.setPrice(rs.getDouble(6));
			ods.setNote(rs.getString(7));
			ods.setName(rs.getString(8));
			ods.setPhone(rs.getString(9));
			ods.setAddress(rs.getString(10)); 
			allOrders.add(ods) ;
		}
		return allOrders ;
	}
	@Override
	public Long getAllCountByMember(String mid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM orders WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid); 
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getLong(1) ;
		}
		return 0L ;
	}
	
	@Override
	public boolean doCreate(Orders vo) throws SQLException {
		String sql = "INSERT INTO orders(mid,pid,cid,subdate,price,note,name,phone,address) "
				+ " VALUES (?,?,?,?,?,?,?,?,?) " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setLong(2, vo.getPid());
		super.pstmt.setLong(3, vo.getCid());
		super.pstmt.setDate(4, new java.sql.Date(vo.getSubdate().getTime()));
		super.pstmt.setDouble(5, vo.getPrice());
		super.pstmt.setString(6, vo.getNote());
		super.pstmt.setString(7, vo.getName());
		super.pstmt.setString(8, vo.getPhone());
		super.pstmt.setString(9, vo.getAddress());
		return super.pstmt.executeUpdate() > 0 ;
	} 

	@Override
	public boolean doEdit(Orders vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Orders findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCreateId() throws SQLException {
		return super.getLastId();
	}

}
