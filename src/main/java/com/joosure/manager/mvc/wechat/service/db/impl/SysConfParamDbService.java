package com.joosure.manager.mvc.wechat.service.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;
import com.joosure.manager.mvc.wechat.dao.SysConfParamDao;
import com.joosure.manager.mvc.wechat.service.db.ISysConfParamDbService;

@Service("sysConfParamDbService")
public class SysConfParamDbService implements ISysConfParamDbService{
	
	@Autowired
	private SysConfParamDao sysConfParamDao;
	
	
	@Override
	public List<SysConfParam> getParamsInfo(SysConfParam cond) {
		return sysConfParamDao.qryParamsInfo(cond);
	}

	@Override
	public int getParamsCount(SysConfParam cond) {
		return sysConfParamDao.qryParamsCount(cond);
	}

	@Override
	public int changeSysConfParam(SysConfParam sysConfParam) {
		return sysConfParamDao.updateByPrimaryKeySelective(sysConfParam);
	}

	@Override
	public SysConfParam chkIfExist(SysConfParam record) {
		return sysConfParamDao.chkIfExist(record);
	}

	@Override
	public int insertSelective(SysConfParam record) {
		return sysConfParamDao.insertSelective(record);
	}

}
