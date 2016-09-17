package com.joosure.manager.mvc.wechat.service.db;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;

public interface ISysConfParamDbService {

	List<SysConfParam> getParamsInfo(SysConfParam cond);

	int getParamsCount(SysConfParam cond);

	int changeSysConfParam(SysConfParam sysConfParam);

	SysConfParam chkIfExist(SysConfParam record);

	int insertSelective(SysConfParam record);

}
