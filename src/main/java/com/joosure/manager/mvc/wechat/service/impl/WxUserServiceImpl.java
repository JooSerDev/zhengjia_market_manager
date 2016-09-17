package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.manager.mvc.wechat.dao.WxExgDao;
import com.joosure.manager.mvc.wechat.dao.WxItemDao;
import com.joosure.manager.mvc.wechat.dao.WxUserDao;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.ItemComment;
import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.service.ScoreService;
import com.joosure.server.mvc.wechat.service.db.IItemDbService;
import com.joosure.server.mvc.wechat.service.db.IUserDbService;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {

	@Autowired
	private WxUserDao wxUserDao;
	
	@Autowired
	private IUserDbService userDbService;
	@Autowired
	private IItemDbService itemDbService;
	
	@Autowired
	private WxExgDao wxExgDao;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private WxItemDao wxItemDao;
	
	/**
	 * 成功
	 */
	private static final int SUCCESS = 1;
	/**
	 * 失败
	 */
	private static final int FAIL = -1;
	
	/**
	 * 用户状态 正常，已注册
	 */
	private static final int USERSTATE_0 = 0;	
	/**
	 * 用户状态 不正常，已封号
	 */
	private static final int USERSTATE_2 = 2;	



	// TODO
	public int banUser(User cond) {
		User user = userDbService.getUserById(cond.getUserId());
		if (user != null) {
			if(user.getState() == USERSTATE_2){
				return 0;//该用户已被封号
			}
			user.setState(USERSTATE_2);
			// 状态0为已注册，2 封号
			userDbService.updateUser(user);
			// 当 item_exchange 表中 exchangeStatus 为 exchanging 则 置为 cancel
			wxExgDao.banExg(user.getUserId());
			// 将用户的宝贝进行下线操作  0->2  1->3
			wxItemDao.banIten(user.getUserId());
			// 进行积分惩罚处理,暂时没有这个动作
			// scoreService.updateScoreByEvent(user.getUserId(), "")
			return SUCCESS;
		}
		return FAIL;
	}

	@Override
	public int cancelBanUser(int userId) {
		User user = userDbService.getUserById(userId);
		if(user!=null){
			if(user.getState() == USERSTATE_0){
				return 0;//该用户已被封号
			}
			user.setState(USERSTATE_0);// 状态0为已注册，1为未注册 ？
			userDbService.updateUser(user);
			//将因为用户封号而下架的物品 改为正常的状态  2->0  3->1
			wxItemDao.cancelBanItem(user.getUserId());
			return SUCCESS;
		}
		return FAIL;
	}

	/**
	 * 清除所有评论
	 */
	public boolean clearAllComment(ItemComment cond) {
		wxUserDao.clearAllCmt(cond);
		scoreService.updateScoreByEvent(cond.getFromUserId(), 
				CommonConstant.SCORE_EVENT_CLEAR_CMT);
		return true;
		// return false;
	}

	@Override
	public List<Item> getItemsList(User cond) {
		return itemDbService.getItemsByOwnerIdPages(cond.getUserId(), cond.getOffset(), cond.getLimit());
	}

	@Override
	public int getItemsCount(User cond) {
		return wxUserDao.getItemsCount(cond);
	}
	
	@Override
	public int getDetailUserInfoCount(User cond) {
		return wxUserDao.getDetailUserCount(cond);
	}

	@Override
	public List<WxUserDetail> getDetailUserInfoList(User cond) {
		return wxUserDao.getDetailUser(cond);
	}


}
