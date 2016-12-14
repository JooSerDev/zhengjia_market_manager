package com.joosure.manager.mvc.wechat.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.manager.mvc.wechat.dao.WxExgDao;
import com.joosure.manager.mvc.wechat.dao.WxItemDao;
import com.joosure.manager.mvc.wechat.dao.WxUserCptDao;
import com.joosure.manager.mvc.wechat.dao.WxUserDao;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.server.mvc.wechat.entity.domain.MultiUserInfo;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.ItemComment;
import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.entity.pojo.UserWechatInfo;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserCpt;
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
	
	@Autowired
	private WxUserCptDao wxUserCptDao;

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
		//封号一个人的时候需要把它相关联的用户全部封号 161104
		List<User> unionUsers = wxUserDao.getUnionUsers(cond.getUnionId());
		if(unionUsers != null && unionUsers.size() > 0){
			for(User user : unionUsers){
				if(user.getState() == USERSTATE_2){
					//continue;
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
			}
			return SUCCESS;
		}
		/* 以下为以前的逻辑，针对单个公众号 */				
		/*User user = userDbService.getUserById(cond.getUserId());
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
		}*/
		return FAIL;
	}

	@Override
	public int cancelBanUser(int userId) {
		//161104
		List<User> unionUsers = wxUserDao.getUnionUsers(
				userDbService.getUserById(userId).getUnionId());
		if(unionUsers != null && unionUsers.size() > 0){
			for(User user : unionUsers){
				if(user.getState() == USERSTATE_0){
					return 0;//该用户已被封号
				}
				user.setState(USERSTATE_0);// 状态0为已注册，1为未注册 ？
				userDbService.updateUser(user);
				//将因为用户封号而下架的物品 改为正常的状态  2->0  3->1
				wxItemDao.cancelBanItem(user.getUserId());
			}
			return SUCCESS;
		}
		/*User user = userDbService.getUserById(userId);
		if(user!=null){
			if(user.getState() == USERSTATE_0){
				return 0;//该用户已被封号
			}
			user.setState(USERSTATE_0);// 状态0为已注册，1为未注册 ？
			userDbService.updateUser(user);
			//将因为用户封号而下架的物品 改为正常的状态  2->0  3->1
			wxItemDao.cancelBanItem(user.getUserId());
			return SUCCESS;
		}*/
		return FAIL;
	}

	/**
	 * 清除所有评论
	 */
	public boolean clearAllComment(ItemComment cond) {
		wxUserDao.clearAllCmt(cond);
		/*scoreService.updateScoreByEvent(cond.getFromUserId(), 
				CommonConstant.SCORE_EVENT_CLEAR_CMT);*/
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

	public List<User> getUnionUser(String unionId){
		return wxUserDao.getUnionUsers(unionId);
	}
	
	@Override
	public MultiUserInfo getMultiUserInfo(String unionId){
		if(unionId == null || "".equals(unionId)){
			return null;
		}
		List<User> users = userDbService.getUnionUsers(unionId);
		MultiUserInfo multiUser;
		if(users == null || users.size() == 0){
			return null;
		}
		if(users.size() == 1){//说明该用户只在一个系统中登陆，此时就将
			multiUser = new MultiUserInfo(users.get(0));
			multiUser.setMultiFlag(false);
			multiUser.setCurrentOpenid(users.get(0).getOpenid());
			multiUser.setCurrentSysid(users.get(0).getSysId());
			multiUser.setCurrentUserId(users.get(0).getUserId());
			return multiUser;
		}
		
		multiUser = new MultiUserInfo();
		multiUser.setMultiFlag(true);
		User tempU = users.get(0);
		multiUser.setUnionId(tempU.getUnionId());
		multiUser.setMobile(tempU.getMobile());
		multiUser.setCurrentOpenid(tempU.getOpenid());
		multiUser.setCurrentSysid(tempU.getSysId());
		multiUser.setCurrentUserId(tempU.getUserId());
		multiUser.setHeadImgUrl(tempU.getHeadImgUrl());
		multiUser.setSex(tempU.getSex());
		multiUser.setNickname(tempU.getNickname());
		for(User temp:users){
			multiUser.setLikeNum(multiUser.getLikeNum()+temp.getLikeNum());
			multiUser.setItemNum(multiUser.getItemNum()+temp.getItemNum());
			multiUser.setExchangeNum(multiUser.getExchangeNum()+temp.getExchangeNum());
			multiUser.setFinishNum(multiUser.getFinishNum()+temp.getFinishNum());
			multiUser.setScore(multiUser.getScore()+temp.getScore());
			multiUser.setOpenIds(multiUser.getOpenIds()+temp.getOpenid()+";");
			multiUser.setSysIds(multiUser.getSysIds()+temp.getSysId()+";");
			multiUser.setUserIds(multiUser.getUserIds()+temp.getUserId()+";");
		}
		return multiUser;
	}

	@Override
	public User getFirstUserByUnionId(String unionId) {
		return wxUserDao.getFirstUserByUnionId(unionId);
	}

	@Override
	public WxUserDetail getDetailUser(User cond) {
		long startTime = System.currentTimeMillis();
		if(cond == null || StringUtils.isBlank(cond.getUnionId())){
			return null;
		}
		WxUserDetail detailUser = null;
		// lastModifyTime 为最新时间的 user
		User firstUser = this.getFirstUserByUnionId(cond.getUnionId());
		detailUser = new WxUserDetail(firstUser,null);
		//基本信息： 编号、昵称、头像、unionId、手机号、性别、状态、最新时间
		UserWechatInfo wechatUser = userDbService.getUserWechatInfoByOpenid(firstUser.getOpenid());
		//基本信息： 地址, 
		detailUser.setAddress(wechatUser.getCountry()+wechatUser.getProvince()+wechatUser.getCity());
		//基本信息： 多个相同 unionid 用户被投诉次数
		detailUser.setCptNum(wxUserCptDao.getCptCountByUnionId(cond.getUnionId()));
		//基本信息： 最新被投诉时间
		WxUserCpt cpt = wxUserCptDao.getLatestCptByUnionId(cond.getUnionId());
		if(cpt != null){
			detailUser.setCptTime(cpt.getAddtime());
		}
		//基本信息： 累计积分、累计点赞、累计认证完成数
		User sumUser = wxUserDao.getSumUnionUser(cond.getUnionId());
		detailUser.setScore(sumUser.getScore());
		detailUser.setLikeNum(sumUser.getLikeNum());
		detailUser.setFinishNum(sumUser.getFinishNum());
		// 累计信息不实交换数 暂时去掉
		//detailUser.setExchangeFailNum(detailUser.getExchangeFailNum() + temp.getExchangeFailNum());
		long endTime = System.currentTimeMillis();
		System.out.println("WxUserService.getDetailUser 用时间： " + (endTime - startTime));
		return detailUser;
	}

	@Override
	public List<Item> getUnionItems(User userCond) {
		return wxItemDao.getUnionUserItems(userCond);
	}

	@Override
	public int getUnionItemsCount(User userCond) {
		return wxItemDao.getUnionUserItemsCount(userCond);
	}

	@Override
	public int getUserCountByDay(Date startTime, Date endTime) {
		return wxUserDao.getUserCountByDay(startTime,endTime);
	}
	

}
