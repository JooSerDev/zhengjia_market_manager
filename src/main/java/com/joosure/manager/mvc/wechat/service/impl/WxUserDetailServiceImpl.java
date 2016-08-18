package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.manager.mvc.wechat.dao.WxUserDao;
import com.joosure.manager.mvc.wechat.service.WxUserDetailService;
import com.joosure.manager.mvc.wechat.service.WxUserService;

@Service("wxUserDetailService")
public class WxUserDetailServiceImpl implements WxUserDetailService{
	
	@Autowired
	private WxUserService wxUserService;
	
	@Autowired
	private WxUserDao wxUserDao;

	@Override
	public int getDetailUserInfoCount(Map<String, Object> cond) {
		return wxUserDao.getDetailUserCount(cond);
	}

	@Override
	public List<WxUserDetail> getDetailUserInfoList(Map<String, Object> cond) {
		return wxUserDao.getDetailUser(cond);
	}
	
}
