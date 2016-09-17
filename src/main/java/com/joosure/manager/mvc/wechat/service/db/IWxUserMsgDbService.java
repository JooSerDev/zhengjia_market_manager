package com.joosure.manager.mvc.wechat.service.db;

import com.joosure.server.mvc.wechat.entity.pojo.WxUserMsg;

public interface IWxUserMsgDbService {

	/**
	 * 插入
	 * @param wxUserMsg
	 * @return
	 */
	int insertWxUserMsg(WxUserMsg wxUserMsg);
	
	/**
	 * 更新
	 * @param wxUserMsg
	 * @return
	 */
	int updateWxUserMsg(WxUserMsg wxUserMsg);
}
