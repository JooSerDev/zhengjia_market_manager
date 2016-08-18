package com.joosure.manager.mvc.wechat.dao;

import java.util.List;
import java.util.Map;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.server.mvc.wechat.entity.pojo.User;

public interface WxUserDao {

	int getWxUserCount(Map<String, Object> cond);

	List<User> getWxUserList(Map<String, Object> cond);
	
	List<WxUserDetail> getDetailUser(Map<String, Object> cond);
	
	int getDetailUserCount(Map<String, Object> cond);

	int clearAllCmt(Map<String, Object> cond);

	int getItemsCount(Map<String, Object> cond);
	
	
}