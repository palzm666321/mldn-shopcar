package cn.mldn.shopcar.service.front.impl;

import java.util.List;

import cn.mldn.shopcar.dao.ICityDAO;
import cn.mldn.shopcar.service.front.ICityServiceFront;
import cn.mldn.shopcar.vo.City;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class CityServiceFrontImpl extends AbstractService implements ICityServiceFront {

	@Override
	public List<City> listByProvince(long pid) throws Exception {
		ICityDAO cityDAO = Factory.getDAOInstance("city.dao") ;
		return cityDAO.findAllByProvince(pid);
	}

}
