package com.joosure.manager.mvc.wechat.buz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joosure.manager.mvc.wechat.bean.dto.ExchangeDetailInfo;
import com.joosure.manager.mvc.wechat.buz.WxExgBuz;
import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.dao.WxUserCptDao;
import com.joosure.manager.mvc.wechat.service.WxExgService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.dao.database.ItemDao;
import com.joosure.server.mvc.wechat.dao.database.UserDao;
import com.joosure.server.mvc.wechat.entity.pojo.Exchange;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserCpt;
import com.joosure.server.mvc.wechat.service.ScoreService;

@Component("wxExgBuz")
public class WxExgBuzImpl implements WxExgBuz {

	@Autowired
	private WxExgService wxExgService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ScoreService scoreService;
	@Autowired
	private WxUserCptDao userCptDao;

	@Override
	public List<ExchangeDetailInfo> getExgList(QryCondBean qryCond) {
		List<ExchangeDetailInfo> exgsDetailInfo = new ArrayList<ExchangeDetailInfo>();
		List<Exchange> exgs = wxExgService.getExgByMob(qryCond);
		if (exgs != null && exgs.size() > 0) {
			for (Iterator<Exchange> iterator = exgs.iterator(); iterator.hasNext();) {
				Exchange exchange = iterator.next();

				int ownerId = exchange.getOwnerId();
				int changerId = exchange.getChangerId();
				User owner = userDao.getUserById(ownerId);
				User changer = userDao.getUserById(changerId);

				int ownerItemId = exchange.getOwnerItemId();
				int changerItemId = exchange.getChangerItemId();
				Item ownerItem = itemDao.getItemById(ownerItemId);
				Item changerItem = itemDao.getItemById(changerItemId);

				ExchangeDetailInfo mei = new ExchangeDetailInfo();
				mei.setExchange(exchange);

				mei.setUser(owner);
				mei.setTarget(changer);
				mei.setUserItem(ownerItem);
				mei.setTargetItem(changerItem);

				exgsDetailInfo.add(mei);
			}
		}
		return exgsDetailInfo;
	}

	@Override
	public int getExgListCount(QryCondBean qryCond) {
		return wxExgService.getExgListCount(qryCond);
	}

	@Override
	public String dealSuccessExg(ExchangeDetailInfo exgDetailInfo) {
		//认证交易成功 1.改交易状态值和时间，2.给双方用户进行积分奖励
		int ret = wxExgService.dealExg(exgDetailInfo.getExchange().getExchangeId(),"success");
		if(ret > 0){
			if(exgDetailInfo.getUser()!=null){
				scoreService.updateScoreByEvent(exgDetailInfo.getUser().getUserId(),
						CommonConstant.SCORE_EVENT_FACE_EXG);
			}
			if(exgDetailInfo.getTarget()!=null){
				scoreService.updateScoreByEvent(exgDetailInfo.getTarget().getUserId(),
						CommonConstant.SCORE_EVENT_FACE_EXG);
			}
			return "认证交易成功";
		}
		return "认证交易出错，请联系维护人员查看相关数据！";
	}

	@Override
	public String dealFailExg(ExchangeDetailInfo exgDetailInfo) {
		return "交易出错，请联系管理员！";
	}

	@Override
	public String dealFailExg(ExchangeDetailInfo exgDetailInfo, String[] infoNotReal, String[] charge, String chargeTargetMsg, String chargeOwnMsg) {
		//认证交易失败 1.改交易状态值和时间，2.给相应用户进行积分惩罚 3.记录用户信息不实 4.或直接投诉信息记录
		int ret = wxExgService.dealExg(exgDetailInfo.getExchange().getExchangeId(),"fail");
		if(ret > 0){
			if(infoNotReal!=null){
				List<String> notRealInfo = Arrays.asList(infoNotReal);
				if(notRealInfo.contains(CommonConstant.OWN)){
					scoreService.updateScoreByEvent(exgDetailInfo.getUser().getUserId(),
							CommonConstant.SCORE_EVENT_ItemInfoNotReal);
				}
				if(notRealInfo.contains(CommonConstant.TARGET)){
					scoreService.updateScoreByEvent(exgDetailInfo.getTarget().getUserId(),
							CommonConstant.SCORE_EVENT_ItemInfoNotReal);
				}
			}
			if(charge!=null){
				boolean dealCpt = makeCptFromExg(exgDetailInfo,charge,chargeTargetMsg,chargeOwnMsg);
				if(!dealCpt){
					return "认证交换完成 -02"; //添加评论失败
				}
			}
			return "认证交换成功";
		}
		return "认证交换出错，请联系管理员！";
	}

	/**
	 * 从交易信息中创建投诉 并处理
	 * @param exgDetailInfo
	 * @param charge
	 * @param chargeTargetMsg
	 * @param chargeOwnMsg
	 * @return
	 */
	private boolean makeCptFromExg(ExchangeDetailInfo exgDetailInfo, String[] charge, String chargeTargetMsg, String chargeOwnMsg){
		List<String> charges = Arrays.asList(charge);
		WxUserCpt userCpt = null;
		//进行投诉操作 1.记表，2.惩罚
		if(charges.contains(CommonConstant.OWN)){
			userCpt = new WxUserCpt();
			userCpt.setUserid(exgDetailInfo.getTarget().getUserId());
			userCpt.setUsername(exgDetailInfo.getTarget().getNickname());
			userCpt.setTouserid(exgDetailInfo.getUser().getUserId());
			userCpt.setTousername(exgDetailInfo.getUser().getNickname());
			userCpt.setExchangeid(exgDetailInfo.getExchange().getExchangeId());
			userCpt.setItemid(exgDetailInfo.getUserItem().getItemId());
			userCpt.setItemname(exgDetailInfo.getUserItem().getName());
			userCpt.setMessage(chargeOwnMsg);
			userCpt.setRemark(chargeOwnMsg);
			userCptDao.insertSelective(userCpt);
			scoreService.updateScoreByEvent(exgDetailInfo.getUser().getUserId(),
					CommonConstant.SCORE_EVENT_UserNotReach);
		}
		if(charges.contains(CommonConstant.TARGET)){
			userCpt = new WxUserCpt();
			userCpt.setUserid(exgDetailInfo.getUser().getUserId());
			userCpt.setUsername(exgDetailInfo.getUser().getNickname());
			userCpt.setTouserid(exgDetailInfo.getTarget().getUserId());
			userCpt.setTousername(exgDetailInfo.getTarget().getNickname());
			userCpt.setExchangeid(exgDetailInfo.getExchange().getExchangeId());
			userCpt.setItemid(exgDetailInfo.getTargetItem().getItemId());
			userCpt.setItemname(exgDetailInfo.getTargetItem().getName());
			userCpt.setMessage(chargeTargetMsg);
			userCpt.setRemark(chargeTargetMsg);
			userCptDao.insertSelective(userCpt);
			scoreService.updateScoreByEvent(exgDetailInfo.getTarget().getUserId(),
					CommonConstant.SCORE_EVENT_UserNotReach);
		}
		return true;
	}
}
