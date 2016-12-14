package com.joosure.manager.mvc.wechat.service;

import java.util.Date;
import java.util.List;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.server.mvc.wechat.entity.domain.MultiUserInfo;
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

	List<User> getUnionUser(String unionId);
	
	MultiUserInfo getMultiUserInfo(String unionId);

	/**
	 * 通过 unionid 获取第一个 用户信息
	 * @author Ted-wuhuhu
	 * @Time 2016年11月4日 下午6:21:34
	 * @param unionId
	 * @return
	 */
	User getFirstUserByUnionId(String unionId);

	/**
	 * 根据条件获取详细的用户信息，多个unionid的用户信息需统一
	 * @author Ted-wuhuhu
	 * @Time 2016年11月5日 上午10:56:57
	 * @param cond
	 * @return
	 */
	WxUserDetail getDetailUser(User cond);

	/**
	 * 获取 相同 unionid 的用户的所有的宝贝 ，分页展示
	 * @author Ted-wuhuhu
	 * @Time 2016年11月5日 下午2:11:27
	 * @param userCond
	 * @return
	 */
	List<Item> getUnionItems(User userCond);
	
	/**
	 * 获取 相同 unionid 的用户的所有的宝贝 数量
	 * @author Ted-wuhuhu
	 * @Time 2016年11月5日 下午2:12:54
	 * @param userCond
	 * @return
	 */
	int getUnionItemsCount(User userCond);

	/**
	 * 获取当天人数总量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午10:51:49
	 * @param date
	 * @param date2
	 * @return
	 */
	int getUserCountByDay(Date date, Date date2);
}
