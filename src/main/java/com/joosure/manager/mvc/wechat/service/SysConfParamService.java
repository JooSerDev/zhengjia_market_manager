package com.joosure.manager.mvc.wechat.service;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;

public interface SysConfParamService {

	/**
	 * 保存系统参数信息
	 * @param record
	 * @return
	 */
	int saveConfParam(SysConfParam record);

}
