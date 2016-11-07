package com.joosure.manager.mvc.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.server.mvc.wechat.entity.pojo.ItemComment;
import com.joosure.server.mvc.wechat.entity.pojo.User;

public interface WxUserDao {

	int getWxUserCount(User cond);

	List<User> getWxUserList(User cond);
	
	List<WxUserDetail> getDetailUser(User cond);
	
	int getDetailUserCount(User cond);

	int clearAllCmt(ItemComment cond);

	int getItemsCount(User cond);
	
	List<User> getUnionUsers(@Param("unionId") String unionId);

	int getUnionUserCount(User userCond);

	List<String> getUnionIds(User userCond);

	User getFirstUserByUnionId(@Param("unionId")String unionId);
	
	User getSumUnionUser(@Param("unionId")String unionId);
	
}