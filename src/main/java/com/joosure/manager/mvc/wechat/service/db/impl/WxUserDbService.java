package com.joosure.manager.mvc.wechat.service.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.dao.WxUserDao;
import com.joosure.manager.mvc.wechat.service.db.IWxUserDbService;
import com.joosure.server.mvc.wechat.entity.pojo.User;

@Service("wxUserDbService")
public class WxUserDbService implements IWxUserDbService{
	
	@Autowired
	private WxUserDao wxUserDao;
	
	@Override
	public int getWxUserCount(User cond) {
		return wxUserDao.getWxUserCount(cond);
	}

	@Override
	public List<User> getWxUserList(User cond) {
		return wxUserDao.getWxUserList(cond);
	}
}
