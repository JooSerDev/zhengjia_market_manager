package com.joosure.server.mvc.wechat.dao.database;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.entity.pojo.UserWechatInfo;

public interface UserDao {

	/**
	 * 基本用户信息相关-开始--------------------------------------------------------------
	 */
	public void saveUser(User user);

	public void updateUser(User user);

	public User getUserById(@Param("userId") int userId);

	public User getUserByOpenid(@Param("openid") String openid);

	public User getUserByMobile(@Param("mobile") String mobile);
	
	public List<User> getUsersOrderByItemNumTop8();
	
	public List<User> getMultiTop8User();//替换上面的  getUsersOrderByItemNumTop8
	List<User> getUnionUsers(@Param("unionId")String unionId);
	/*
	 * 基本用户信息相关 结束--------------------------------------------------------------
	 */

	/**
	 * 微信用户信息相关-开始--------------------------------------------------------------
	 */
	public void saveUserWechatInfo(UserWechatInfo userWechatInfo);

	public void updateUserWechatInfo(UserWechatInfo userWechatInfo);

	public UserWechatInfo getUserWechatInfoByOpenid(@Param("openid") String openid);
	/*
	 * 微信用户信息相关 结束--------------------------------------------------------------
	 */

}
