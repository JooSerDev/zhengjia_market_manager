package com.joosure.manager.mvc.wechat.service.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.dao.WxUserMsgDao;
import com.joosure.manager.mvc.wechat.service.db.IWxUserMsgDbService;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserMsg;

@Service("wxUserMsgDbService")
public class WxUserMsgDbService implements IWxUserMsgDbService{
	
	@Autowired
	private WxUserMsgDao wxUserMsgDao;

	@Override
	public int insertWxUserMsg(WxUserMsg wxUserMsg) {
		return wxUserMsgDao.insertSelective(wxUserMsg);
	}

	@Override
	public int updateWxUserMsg(WxUserMsg wxUserMsg) {
		return wxUserMsgDao.updateByPrimaryKeySelective(wxUserMsg);
	}
	
}
