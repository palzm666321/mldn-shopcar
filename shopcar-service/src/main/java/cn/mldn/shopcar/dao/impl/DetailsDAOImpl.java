package cn.mldn.shopcar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.dao.IDetailsDAO;
import cn.mldn.shopcar.vo.Details;
import cn.mldn.util.dao.abs.AbstractDAO;

public class DetailsDAOImpl extends AbstractDAO implements IDetailsDAO {
	@Override
	public Map<Long, Integer> findAllByOrders(Long oid) throws SQLException {
		Map<Long,Integer> map = new HashMap<Long,Integer>() ;
		String sql = "SELECT gid,amount FROM details WHERE oid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, oid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			map.put(rs.getLong(1), rs.getInt(2)) ;
		}
		return map;
	}
	@Override
	public boolean doCreateBatch(List<Details> details) throws SQLException {
		String sql = "INSERT INTO details(oid,gid,amount) VALUES (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		Iterator<Details> iter = details.iterator() ;
		while (iter.hasNext()) {
			Details det = iter.next() ;
			super.pstmt.setLong(1, det.getOid());
			super.pstmt.setLong(2, det.getGid());
			super.pstmt.setInt(3, det.getAmount());
			super.pstmt.addBatch(); 
		}
		int result [] = super.pstmt.executeBatch() ;
		for (int x = 0 ; x < result.length ; x ++) {
			if (result[x] == 0) {
				return false ;
			}
		}
		return true ;
	} 
	@Override
	public boolean doCreate(Details vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Details vo) throws SQLException {
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
	public Details findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
