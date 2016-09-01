package com.joosure.manager.mvc.wechat.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.common.base.entity.QryCondBean;
import com.joosure.server.mvc.wechat.entity.pojo.Exchange;

public interface WxExgDao {

	List<Exchange> getExgsByMobile(QryCondBean qryCond);

	int getExgsCount(QryCondBean qryCond);

	int dealExg(@Param("exchangeId")Integer exchangeId, @Param("dealFlag")String dealFlag);

	/**
	 * 当用户封号时，有关他的交易都置为cancel
	 * @param userId
	 */
	void banExg(@Param("userId")Integer userId);

}
