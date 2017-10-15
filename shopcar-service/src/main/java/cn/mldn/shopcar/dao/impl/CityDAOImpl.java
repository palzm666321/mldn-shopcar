package cn.mldn.shopcar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.shopcar.dao.ICityDAO;
import cn.mldn.shopcar.vo.City;
import cn.mldn.util.dao.abs.AbstractDAO;

public class CityDAOImpl extends AbstractDAO implements ICityDAO {

	@Override
	public boolean doCreate(City vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(City vo) throws SQLException {
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
	public City findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
	public List<City> findAllByProvince(Long pid) throws SQLException {
		List<City> all = new ArrayList<City>() ;
		String sql = "SELECT cid,pid,title FROM city WHERE pid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, pid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			City vo = new City() ;
			vo.setCid(rs.getLong(1));
			vo.setPid(rs.getLong(2));
			vo.setTitle(rs.getString(3));
			all.add(vo) ;
		}
		return all;
	}

}
