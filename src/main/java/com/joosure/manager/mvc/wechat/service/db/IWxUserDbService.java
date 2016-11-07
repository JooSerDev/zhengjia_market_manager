package com.joosure.manager.mvc.wechat.service.db;

import java.util.List;

import com.joosure.server.mvc.wechat.entity.pojo.User;

public interface IWxUserDbService {

	int getWxUserCount(User cond);

	List<User> getWxUserList(User cond);

	/**
	 * 通过unionid获取用户数量
	 * @author Ted-wuhuhu
	 * @Time 2016年11月4日 下午5:59:31
	 * @param userCond
	 * @return
	 */
	int getUnionUserCount(User userCond);

	/**
	 * 通过 条件获取unionids ，然后再去遍历
	 * unionids获取用户信息
	 * @author Ted-wuhuhu
	 * @Time 2016年11月4日 下午5:59:49
	 * @param userCond
	 * @return
	 */
	List<String> getUnionIds(User userCond);
}
