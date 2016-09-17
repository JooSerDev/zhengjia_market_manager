package com.joosure.manager.mvc.wechat.service;

import java.util.List;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.server.mvc.wechat.entity.pojo.Exchange;

public interface WxExgService {

	/**
	 * 通过条件 ，包括手机号，状态 查询 交易信息
	 * @param qryCond
	 * @return
	 */
	List<Exchange> getExgByMob(QryCondBean qryCond);

	/**
	 * 通过条件 ，包括手机号，状态 查询 交易 量
	 * @param qryCond
	 * @return
	 */
	int getExgListCount(QryCondBean qryCond);

	/**
	 * 交易认证成功
	 * @param exchangeId
	 * @param i
	 * @return
	 */
	int dealExg(Integer exchangeId, String dealFlag);

}
