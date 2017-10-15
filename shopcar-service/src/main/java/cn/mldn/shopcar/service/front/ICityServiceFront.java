package cn.mldn.shopcar.service.front;

import java.util.List;

import cn.mldn.shopcar.vo.City;

public interface ICityServiceFront {
	/**
	 * 根据省份列出所有的城市信息
	 * @param pid 省份编号
	 * @return 城市信息
	 * @throws Exception SQL
	 */
	public List<City> listByProvince(long pid) throws Exception ;
}
