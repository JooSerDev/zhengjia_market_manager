package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.common.base.entity.QryCondBean;
import com.joosure.manager.mvc.wechat.dao.WxItemDao;
import com.joosure.manager.mvc.wechat.service.WxItemService;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.dao.database.ItemDao;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.service.ScoreService;

@Service("wxItemService")
public class WxItemServiceImpl implements WxItemService{
	
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private WxItemDao wxItemDao;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private WxUserService wxUserService;

	private static final int hasForbid = 0;//表示该物品已被封号，不需要进行封号操作
	private static final int forbidFail = -1;//表示该物品不存在，或下架失败
	private static final int hasApr = 0;//表示该物品已经审核了
	private static final int aprFail = -1;//表示该物品已经审核了
	
	
	@Override
	public int getItemsCount(QryCondBean qryCondBean) {
		return wxItemDao.getItemListCount(qryCondBean);
	}

	@Override
	public List<Item> getItemList(QryCondBean qryCondBean) {
		return wxItemDao.getItemListByCond(qryCondBean);
	}

	@Override
	public int approvalItem(Item item) {
		//审核通过 对用户进行  审核通过 积分奖励
		Item temp = itemDao.getItemById(item.getItemId());
		int ret = 0;
		if(temp!=null){
			if("wait".endsWith(temp.getApprovalStatus())){//如果是已经审核的则不进行审核
				ret = wxItemDao.approvalItem(item);
				if(ret>0){
					scoreService.updateScoreByEvent(item.getOwnerId(), 
							CommonConstant.SCORE_EVENT_APPROVAL);
					//当获得小编推荐时，积分奖励
					if(item.getIsRecommended()==CommonConstant.STATUS_1){
						scoreService.updateScoreByEvent(item.getOwnerId(), 
								CommonConstant.SCORE_EVENT_ITEM_RCM);
					}
				}
			}else{
				ret = hasApr;
			}
		}else{
			ret = aprFail;
		}
		return ret;
	}

	@Override
	public int forceToDowm(Item item) {
		int ret = 0;
		if(item!=null){
			item.setStatus(1);//因为强制封号而封号
			Item temp = itemDao.getItemById(item.getItemId());
			if(temp!=null){
				if(temp.getStatus()==1){//1表示已下架
					ret = hasForbid;
				}else{
					ret = wxItemDao.forceToDowm(item);
				}
			}else{
				ret = forbidFail;
			}
		}
		return ret;
	}

}
