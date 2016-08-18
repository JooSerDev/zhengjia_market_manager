package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;
import com.joosure.manager.mvc.wechat.dao.SysConfParamDao;
import com.joosure.manager.mvc.wechat.service.SysConfParamService;

@Service("sysConfParamService")
public class SysConfParamServiceImpl implements SysConfParamService{
	
	@Autowired
	private SysConfParamDao sysConfParamDao;

	@Override
	public int saveConfParam(SysConfParam record) {
		return sysConfParamDao.insert(record);
	}

	@Override
	public List<SysConfParam> getParamsInfo(Map<String, Object> cond) {
		return sysConfParamDao.qryParamsInfo(cond);
	}

	@Override
	public int getParamsCount(Map<String, Object> cond) {
		return sysConfParamDao.qryParamsCount(cond);
	}

}
