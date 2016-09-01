package com.joosure.manager.mvc.wechat.buz;

import java.util.List;

import com.joosure.common.base.entity.QryCondBean;
import com.joosure.manager.mvc.wechat.bean.dto.ExchangeDetailInfo;

public interface WxExgBuz {

	/**
	 * 通过查询条件获取交易列表
	 * @param qryCond
	 * @return
	 */
	List<ExchangeDetailInfo> getExgList(QryCondBean qryCond);

	/**
	 * 通过查询条件获取交易数量
	 * @param qryCond
	 * @return
	 */
	int getExgListCount(QryCondBean qryCond);

	/**
	 * 认证交易成功
	 * @param exgDetailInfo
	 * @return
	 */
	String dealSuccessExg(ExchangeDetailInfo exgDetailInfo);

	/**
	 * 认证交易失败
	 * @param exgDetailInfo
	 * @return
	 */
	String dealFailExg(ExchangeDetailInfo exgDetailInfo);

	/**
	 * 认证交易失败
	 * @param exgDetailInfo
	 * @return
	 */
	String dealFailExg(ExchangeDetailInfo exgDetailInfo, String[] infoNotReal, String[] charge, String chargeTargetMsg, String chargeOwnMsg);

}
