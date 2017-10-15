package cn.mldn.shopcar.service.front.impl;

import cn.mldn.shopcar.dao.IMemberDAO;
import cn.mldn.shopcar.service.front.IMemberServiceFront;
import cn.mldn.shopcar.vo.Member;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class MemberServiceFrontImpl extends AbstractService implements IMemberServiceFront {

	@Override
	public boolean login(Member vo) throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao") ;
		Member member = memberDAO.findById(vo.getMid()) ;	// 根据mid获取Member信息
		if (member != null) {
			return member.getPassword().equals(vo.getPassword()) ;
		}
		return false;
	}

}
