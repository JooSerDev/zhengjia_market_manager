package com.joosure.server.mvc.wechat.service.db;

import java.util.List;

import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.entity.pojo.UserWechatInfo;

public interface IUserDbService {

	/**
	 * 基本用户信息相关-开始--------------------------------------------------------------
	 */
	void saveUser(User user);

	void updateUser(User user);

	User getUserById(int userId);

	User getUserByOpenid(String openid);

	User getUserByMobile(String mobile);
	
	List<User> getUsersOrderByItemNumTop8();
	/*
	 * 基本用户信息相关 结束--------------------------------------------------------------
	 */

	/**
	 * 微信用户信息相关-开始--------------------------------------------------------------
	 */
	void saveUserWechatInfo(UserWechatInfo userWechatInfo);

	void updateUserWechatInfo(UserWechatInfo userWechatInfo);

	UserWechatInfo getUserWechatInfoByOpenid(String openid);
	/*
	 * 微信用户信息相关 结束--------------------------------------------------------------
	 */
}
