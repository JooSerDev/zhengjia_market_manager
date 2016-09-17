package com.joosure.manager.mvc.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;
import com.joosure.manager.mvc.wechat.service.SysConfParamService;
import com.joosure.manager.mvc.wechat.service.db.ISysConfParamDbService;

@Service("sysConfParamService")
public class SysConfParamServiceImpl implements SysConfParamService{
	
	@Autowired
	private ISysConfParamDbService sysConfParamDbService;
	
	public static final int saveerror = -1;
	
	
	@Override
	public int saveConfParam(SysConfParam record) {
		SysConfParam conf= sysConfParamDbService.chkIfExist(record);
		if(conf!=null){
			/*conf.setParamname(record.getParamname());
			conf.setParamdesc(record.getParamdesc());
			conf.setStatus(CommonConstant.STATUS_1);
			return sysConfParamDbService.changeSysConfParam(conf);*/
			return saveerror;
		}
		return sysConfParamDbService.insertSelective(record);
	}

}
