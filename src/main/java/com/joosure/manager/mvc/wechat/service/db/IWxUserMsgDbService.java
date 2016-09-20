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
	
	/**
	 * 通过 用户id 获取用户未读消息
	 * @param userId
	 * @return
	 */
	WxUserMsg getWxUserMsg(WxUserMsg wxUserMsg);
	
	/**
	 * 删除 用户 未读消息 记录
	 * @param record
	 * @return
	 */
	int deleteWxUserMsg(WxUserMsg record);
	
	/**
	 * 检查是否存在，如果存在则返回查询到的，不存在则返回空，对外
	 * @param userId
	 * @return
	 */
	public WxUserMsg chkIfExist(int userId);
	
	/**
	 * 收到一条消息，对外
	 * @param msgType
	 * @param userId
	 * @return
	 */
	public int receiveWxUserMsg(String msgType,int userId);
	
	/**
	 * 阅读一条消息 ，对外
	 * @param msgType
	 * @param userId
	 * @return
	 */
	public int readWxUserMsg(String msgType,int userId);
}
