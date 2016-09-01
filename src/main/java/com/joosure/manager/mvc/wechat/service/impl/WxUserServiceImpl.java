package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.dao.UserItemCmtDao;
import com.joosure.manager.mvc.wechat.dao.WxExgDao;
import com.joosure.manager.mvc.wechat.dao.WxItemDao;
import com.joosure.manager.mvc.wechat.dao.WxUserDao;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.dao.database.ItemDao;
import com.joosure.server.mvc.wechat.dao.database.UserDao;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.service.ScoreService;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {

	@Autowired
	private WxUserDao wxUserDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private WxExgDao wxExgDao;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private WxItemDao wxItemDao;
	@Autowired
	private UserItemCmtDao userItemCmtDao;

	@Override
	public int getWxUserCount(Map<String, Object> cond) {
		return wxUserDao.getWxUserCount(cond);
	}

	@Override
	public List<User> getWxUserList(Map<String, Object> cond) {
		return wxUserDao.getWxUserList(cond);
	}

	// TODO
	public int banUser(Map<String, Object> cond) {
		User user = userDao.getUserById((Integer) cond.get("userId"));
		if (user != null) {
			if(user.getState() == 2){
				return 0;//该用户已被封号
			}
			user.setState(2);
			// 状态0为已注册，2 封号
			userDao.updateUser(user);
			// 当 item_exchange 表中 exchangeStatus 为 exchanging 则 置为 cancel
			wxExgDao.banExg(user.getUserId());
			// 将用户的宝贝进行下线操作  0->2  1->3
			wxItemDao.banIten(user.getUserId());
			// 进行积分惩罚处理,暂时没有这个动作
			// scoreService.updateScoreByEvent(user.getUserId(), "")
			return 1;
		}
		return -1;
	}

	@Override
	public int cancelBanUser(Map<String, Object> cond) {
		User user = userDao.getUserById((Integer) cond.get("userId"));
		if(user!=null){
			if(user.getState() == 0){
				return 0;//该用户已被封号
			}
			user.setState(0);// 状态0为已注册，1为未注册 ？
			userDao.updateUser(user);
			//将因为用户封号而下架的物品 改为正常的状态  2->0  3->1
			wxItemDao.cancelBanItem(user.getUserId());
			return 1;
		}
		return -1;
	}

	/**
	 * 清除所有评论
	 */
	public boolean clearAllComment(Map<String, Object> cond) {
		wxUserDao.clearAllCmt(cond);
		// 清除评论：(int)cond.get("fromUserId")
		// userItemCmtDao.clearAllCmt(cond);
		// 将用户的积分减去 一个 固定的积分值
		scoreService.updateScoreByEvent((int) cond.get("fromUserId"), 
				CommonConstant.SCORE_EVENT_CLEAR_CMT);
		return true;
		// return false;
	}

	@Override
	public List<Item> getItemsList(Map<String, Object> cond) {
		return itemDao.getItemsByOwnerIdPages((Integer) cond.get("ownerId"), (Integer) cond.get("startIndex"), (Integer) cond.get("limit"));
	}

	@Override
	public int getItemsCount(Map<String, Object> cond) {
		return wxUserDao.getItemsCount(cond);
	}

}
