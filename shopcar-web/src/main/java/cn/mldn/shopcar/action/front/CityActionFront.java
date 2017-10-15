package cn.mldn.shopcar.action.front;

import cn.mldn.shopcar.service.front.ICityServiceFront;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import net.sf.json.JSONObject;

public class CityActionFront extends AbstractAction {
	public void listProvince(long pid) {
		ICityServiceFront cityService = Factory.getServiceInstance("city.service.front");
		JSONObject obj = new JSONObject();
		try {
			obj.put("allCities", cityService.listByProvince(pid)) ;
			super.print(obj); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
