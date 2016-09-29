package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.ItfTokenApp;
import com.joosure.manager.mvc.wechat.bean.ItfTokenLog;
import com.joosure.manager.mvc.wechat.dao.ItfTokenDao;
import com.joosure.manager.mvc.wechat.service.ItfTokenService;

@Service("itfTokenService")
public class ItfTokenServiceImpl implements ItfTokenService{
	
	@Autowired
	private ItfTokenDao itfTokenDao;

	@Override
	public List<ItfTokenApp> getTokenAppByPage(ItfTokenApp cond) {
		return itfTokenDao.getTokenAppByPage(cond);
	}

	@Override
	public int getTokenAppCountByPage(ItfTokenApp cond) {
		return itfTokenDao.getTokenAppCountByPage(cond);
	}

	@Override
	public int addTokenApp(ItfTokenApp cond) {
		ItfTokenApp temp = new ItfTokenApp();
		if(cond==null){
			return 0;
		}
		temp.setApplyName(cond.getApplyName());
		List<ItfTokenApp> records = getTokenAppByPage(temp);
		if(records != null && records.size()>0){
			return 0;
		}
		return itfTokenDao.insertTokenApp(cond);
	}

	@Override
	public int insertTokenLog(ItfTokenLog tokenLog) {
		return itfTokenDao.insertTokenAppLog(tokenLog);
	}

}
