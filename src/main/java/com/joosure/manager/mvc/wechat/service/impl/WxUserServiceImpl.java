package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.dao.WxUserDao;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.server.mvc.wechat.dao.database.ItemDao;
import com.joosure.server.mvc.wechat.dao.database.UserDao;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService{
	
	@Autowired
	private WxUserDao wxUserDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public int getWxUserCount(Map<String, Object> cond) {
		return wxUserDao.getWxUserCount(cond);
	}

	@Override
	public List<User> getWxUserList(Map<String, Object> cond) {
		return wxUserDao.getWxUserList(cond);
	}

	//TODO
	public boolean banUser(Map<String, Object> cond) {
		User user = userDao.getUserById(Integer.parseInt((String)cond.get("userId")));
		user.setState(2);//状态2为已注册，封号
		userDao.updateUser(user);
		// 当 item_exchange 表中 exchangeStatus 为 exchanging 则 置为 cancel
		return true;
	}

	@Override
	public boolean cancelBanUser(Map<String, Object> cond) {
		User user = userDao.getUserById((int)cond.get("userId"));
		user.setState(1);//状态1为已注册，正常
		userDao.updateUser(user);
		return true;
	}

	//TODO
	public boolean clearAllComment(Map<String, Object> cond) {
		if(wxUserDao.clearAllCmt(cond) > 0 ){
			//清除评分：
			User user = userDao.getUserById((int)cond.get("userId"));
			//将用户的积分减去 一个  固定的积分值
			user.setScore(0);
			userDao.updateUser(user);
			return true;
		}
		return false;
	}

	@Override
	public List<Item> getItemsList(Map<String, Object> cond) {
		return itemDao.getItemsByOwnerIdPages(Integer.parseInt((String)cond.get("ownerId")), 
				Integer.parseInt((String)cond.get("startIndex")), 
				Integer.parseInt((String)cond.get("limit")));
	}

	@Override
	public int getItemsCount(Map<String, Object> cond) {
		return wxUserDao.getItemsCount(cond);
	}

}
