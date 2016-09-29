package com.joosure.manager.mvc.wechat.service;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.ItfTokenApp;
import com.joosure.manager.mvc.wechat.bean.ItfTokenLog;

public interface ItfTokenService {

	List<ItfTokenApp> getTokenAppByPage(ItfTokenApp cond);

	int getTokenAppCountByPage(ItfTokenApp cond);

	/**
	 * 添加之前先检查是否已经存在 该公司名称
	 * @param cond
	 * @return
	 */
	int addTokenApp(ItfTokenApp cond);

	int insertTokenLog(ItfTokenLog tokenLog);

}
