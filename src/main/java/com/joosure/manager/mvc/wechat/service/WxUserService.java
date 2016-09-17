package com.joosure.manager.mvc.wechat.service;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.ItemComment;
import com.joosure.server.mvc.wechat.entity.pojo.User;


public interface WxUserService {

	/**
	 * 对用户进行封号处理
	 * @param cond
	 * @return
	 */
	int banUser(User cond);

	/**
	 * 对用户进行解除封号处理
	 * @param cond
	 * @return
	 */
	int cancelBanUser(int userId);

	/**
	 * 清除所有评论 ,同时清除评分按条件清除 
	 * 1.0 ：清除所有，只需要传入userid
	 * 
	 * @param cond
	 * @return
	 */
	boolean clearAllComment(ItemComment cond);
	
	/**
	 * 获取该用户的商品
	 * @param cond
	 * @return
	 */
	List<Item> getItemsList(User cond);

	/**
	 * 获取该用户商品的总量
	 * @param cond
	 * @return
	 */
	int getItemsCount(User cond);

	int getDetailUserInfoCount(User cond);

	List<WxUserDetail> getDetailUserInfoList(User cond);

}
