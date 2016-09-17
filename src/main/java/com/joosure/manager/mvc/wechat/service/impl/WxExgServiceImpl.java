package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.dao.WxExgDao;
import com.joosure.manager.mvc.wechat.service.WxExgService;
import com.joosure.server.mvc.wechat.entity.pojo.Exchange;

@Service("wxExgService")
public class WxExgServiceImpl implements WxExgService{
	
	@Autowired
	private WxExgDao wxExgDao;

	@Override
	public List<Exchange> getExgByMob(QryCondBean qryCond) {
		return wxExgDao.getExgsByMobile(qryCond);
	}

	@Override
	public int getExgListCount(QryCondBean qryCond) {
		return wxExgDao.getExgsCount(qryCond);
	}

	@Override
	public int dealExg(Integer exchangeId, String dealFlag) {
		return wxExgDao.dealExg(exchangeId,dealFlag);
	}


}
