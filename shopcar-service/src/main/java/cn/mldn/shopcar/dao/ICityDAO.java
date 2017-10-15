package cn.mldn.shopcar.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.shopcar.vo.City;
import cn.mldn.util.dao.IBaseDAO;

public interface ICityDAO extends IBaseDAO<Long, City> {
	/**
	 * 根据省份查询出对应的城市信息
	 * @param pid 省份编号
	 * @return 城市信息
	 * @throws SQLException SQL 
	 */
	public List<City> findAllByProvince(Long pid) throws SQLException ;
}
