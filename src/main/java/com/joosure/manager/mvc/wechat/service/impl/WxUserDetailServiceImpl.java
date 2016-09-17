package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.manager.mvc.wechat.dao.WxUserDao;
import com.joosure.manager.mvc.wechat.service.WxUserDetailService;
import com.joosure.server.mvc.wechat.entity.pojo.User;

@Service("wxUserDetailService")
public class WxUserDetailServiceImpl implements WxUserDetailService{
	
	@Autowired
	private WxUserDao wxUserDao;

	@Override
	public int getDetailUserInfoCount(User cond) {
		return wxUserDao.getDetailUserCount(cond);
	}

	@Override
	public List<WxUserDetail> getDetailUserInfoList(User cond) {
		return wxUserDao.getDetailUser(cond);
	}
	
}
