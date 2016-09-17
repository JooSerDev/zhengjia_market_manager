package com.joosure.manager.mvc.wechat.service.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.dao.WxUserCptDao;
import com.joosure.manager.mvc.wechat.service.db.IWxUserCptDbService;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserCpt;

@Service("wxUserCptDbService")
public class WxUserCptDbService implements IWxUserCptDbService{
	
	@Autowired
	private WxUserCptDao userCptDao;


	@Override
	public int insertSelective(WxUserCpt record) {
		return userCptDao.insertSelective(record);
	}

	@Override
	public List<WxUserCpt> getCptList(QryCondBean qryCondBean) {
		return userCptDao.getCptList(qryCondBean);
	}

	@Override
	public int getCptListCount(QryCondBean qryCondBean) {
		return userCptDao.getCptListCount(qryCondBean);
	}
	

}
