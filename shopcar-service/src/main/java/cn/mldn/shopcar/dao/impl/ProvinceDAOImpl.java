package cn.mldn.shopcar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.shopcar.dao.IProvinceDAO;
import cn.mldn.shopcar.vo.Province;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ProvinceDAOImpl extends AbstractDAO implements IProvinceDAO {

	@Override
	public boolean doCreate(Province vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Province vo) throws SQLException {
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
	public Province findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Province> findAll() throws SQLException {
		List<Province> all = new ArrayList<Province>() ;
		String sql = "SELECT pid,title FROM province" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Province pro = new Province() ;
			pro.setPid(rs.getLong(1));
			pro.setTitle(rs.getString(2));
			all.add(pro) ;
		}
		return all; 
	}

	@Override
	public List<Province> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Province> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
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
