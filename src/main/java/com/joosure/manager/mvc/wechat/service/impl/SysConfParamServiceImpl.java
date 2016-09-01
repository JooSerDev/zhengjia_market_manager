package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;
import com.joosure.manager.mvc.wechat.dao.SysConfParamDao;
import com.joosure.manager.mvc.wechat.service.SysConfParamService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;

@Service("sysConfParamService")
public class SysConfParamServiceImpl implements SysConfParamService{
	
	@Autowired
	private SysConfParamDao sysConfParamDao;
	
	@Override
	public int saveConfParam(SysConfParam record) {
		//
		SysConfParam conf= sysConfParamDao.chkIfExist(record);
		if(conf!=null){
			conf.setParamname(record.getParamname());
			conf.setParamdesc(record.getParamdesc());
			conf.setStatus(CommonConstant.STATUS_1);
			return sysConfParamDao.updateByPrimaryKeySelective(conf);
		}
		return sysConfParamDao.insertSelective(record);
	}

	@Override
	public List<SysConfParam> getParamsInfo(Map<String, Object> cond) {
		return sysConfParamDao.qryParamsInfo(cond);
	}

	@Override
	public int getParamsCount(Map<String, Object> cond) {
		return sysConfParamDao.qryParamsCount(cond);
	}

	@Override
	public int changeSysConfParam(SysConfParam sysConfParam) {
		return sysConfParamDao.updateByPrimaryKeySelective(sysConfParam);
	}

}
