package cn.mldn.shopcar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.dao.IShopcarDAO;
import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ShopcarDAOImpl extends AbstractDAO implements IShopcarDAO {
	
	
	@Override
	public boolean doRemoveBatch(List<Shopcar> cars) throws SQLException {
		String sql = "DELETE FROM shopcar WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		Iterator<Shopcar> iter = cars.iterator() ;
		while (iter.hasNext()) {
			Shopcar car = iter.next() ;
			super.pstmt.setString(1, car.getMid());
			super.pstmt.setLong(2, car.getGid());
			super.pstmt.addBatch();  
		}
		int result [] = super.pstmt.executeBatch() ;
		for (int x = 0 ; x < result.length; x ++) {
			if (result[x] == 0) {
				return false ;
			}
		}
		return true ; 
	} 
	@Override
	public boolean doEditBatch(List<Shopcar> cars) throws SQLException {
		String sql = "UPDATE shopcar SET amount=? WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		Iterator<Shopcar> iter = cars.iterator() ;
		while (iter.hasNext()) {
			Shopcar car = iter.next() ;
			super.pstmt.setInt(1, car.getAmount());
			super.pstmt.setString(2, car.getMid());
			super.pstmt.setLong(3, car.getGid());
			super.pstmt.addBatch(); 
		}
		int result [] = super.pstmt.executeBatch() ;
		for (int x = 0 ; x < result.length; x ++) {
			if (result[x] == 0) {
				return false ;
			}
		}
		return true ; 
	}
	
	@Override
	public boolean doRemoveByMemberAndGoods(String mid, Long gid) throws SQLException {
		String sql = "DELETE FROM shopcar WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setLong(2, gid);
		return super.pstmt.executeUpdate() > 0 ;
	} 
	
	@Override
	public boolean doRemoveByMemberAndGoods(String mid, Set<Long> gids) throws SQLException {
		StringBuffer sql = new StringBuffer("DELETE FROM shopcar WHERE mid=? AND gid IN (") ;
		sql.append(super.handleBatchLong(gids)).append(")") ;
		super.pstmt = super.conn.prepareStatement(sql.toString()) ;
		super.pstmt.setString(1, mid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doEditShopcarAmount(Shopcar car) throws SQLException {
		String sql = "UPDATE shopcar SET amount=? WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, car.getAmount());
		super.pstmt.setString(2, car.getMid());
		super.pstmt.setLong(3, car.getGid());
		return super.pstmt.executeUpdate() > 0 ;
	} 
	
	@Override
	public Map<Long, Integer> findAmountByMember(String mid, Set<Long> gids) throws SQLException {
		Map<Long,Integer> map = new HashMap<Long,Integer>() ;
		String sql = "SELECT gid,amount FROM shopcar WHERE mid=? AND gid IN (" + super.handleBatchLong(gids) + ")" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			map.put(rs.getLong(1), rs.getInt(2)) ;
		} 
		return map;
	}
	@Override
	public Map<Long, Integer> findAmountByMember(String mid) throws SQLException {
		Map<Long,Integer> map = new HashMap<Long,Integer>() ;
		String sql = "SELECT gid,amount FROM shopcar WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			map.put(rs.getLong(1), rs.getInt(2)) ;
		} 
		return map;
	}
	@Override
	public Shopcar findByMemberAndGoods(String mid, Long gid) throws SQLException {
		String sql = "SELECT scid,mid,gid,amount FROM shopcar WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setLong(2, gid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {	// 现在查找到了对象数据
			Shopcar car = new Shopcar() ;
			car.setScid(rs.getLong(1));
			car.setMid(rs.getString(2));
			car.setGid(rs.getLong(3));
			car.setAmount(rs.getInt(4));
			return car ;
		}
		return null;
	}
	@Override
	public boolean doEditAmount(Shopcar car) throws SQLException {
		String sql = "UPDATE shopcar SET amount=amount+" + car.getAmount() + " WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, car.getMid());
		super.pstmt.setLong(2, car.getGid());
		return super.pstmt.executeUpdate() > 0 ;
	} 
	@Override
	public boolean doCreate(Shopcar vo) throws SQLException {
		String sql = "INSERT INTO shopcar(mid,gid,amount) VALUES (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setLong(2, vo.getGid());
		super.pstmt.setInt(3, vo.getAmount());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doEdit(Shopcar vo) throws SQLException {
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
	public Shopcar findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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

}
