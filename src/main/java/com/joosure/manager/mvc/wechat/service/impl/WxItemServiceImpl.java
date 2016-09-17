package com.joosure.manager.mvc.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.dto.WxItemDetail;
import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.dao.WxItemDao;
import com.joosure.manager.mvc.wechat.service.WxItemService;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.manager.mvc.wechat.service.db.IWxItemDbService;
import com.joosure.manager.mvc.wechat.service.db.IWxUserCptDbService;
import com.joosure.manager.mvc.wechat.service.db.IWxUserDbService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserCpt;
import com.joosure.server.mvc.wechat.service.ScoreService;
import com.joosure.server.mvc.wechat.service.db.IItemDbService;
import com.joosure.server.mvc.wechat.service.db.IUserDbService;

@Service("wxItemService")
public class WxItemServiceImpl implements WxItemService{
	
	@Autowired
	private IWxItemDbService wxItemDbService;
	
	@Autowired
	private IWxUserCptDbService wxUserCptDbService;
	@Autowired
	private IWxUserDbService wxUserDbService;
	@Autowired
	private IUserDbService userDbService;
	
	
	
	@Autowired
	private IItemDbService itemDbService;
	
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
	public List<WxItemDetail> getDetailItemList(QryCondBean qryCondBean) {
		List<WxItemDetail> itemsDetailList = new ArrayList<WxItemDetail>();
		WxItemDetail itemDetail ;
		if(qryCondBean.getIsCpt()!=null && qryCondBean.getIsCpt() == 1){//查被举报的 商品信息
			List<WxUserCpt> userCptList = wxUserCptDbService.getCptList(qryCondBean);
			if(userCptList!=null && userCptList.size()>0){
				for(WxUserCpt userCpt : userCptList){
					itemDetail = new WxItemDetail();
					itemDetail.setUserCpt(userCpt);
					itemsDetailList.add(itemDetail);
				}
			}
		}else{
			List<Item> items = wxItemDbService.getItemList(qryCondBean);
			if(items!=null && items.size()>0){
				for(Item item:items){
					User user = userDbService.getUserById(item.getOwnerId());
					itemDetail = new WxItemDetail();
					itemDetail.setItem(item);
					itemDetail.setOwnerInfo(user);
					itemDetail.setUserCpt(null);
					itemsDetailList.add(itemDetail);
				}
			}
		}
		return itemsDetailList;
	}

	@Override
	public int approvalItem(Item item) {
		//审核通过 对用户进行  审核通过 积分奖励
		Item temp = itemDbService.getItemById(item.getItemId());
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
			Item temp = itemDbService.getItemById(item.getItemId());
			if(temp!=null){
				if(temp.getStatus()==1){//1表示已下架
					ret = hasForbid;
				}else{
					ret = wxItemDao.forceToDowm(item);
					//物品下架 做积分处理
					scoreService.updateScoreByEvent(temp.getOwnerId(), 
							CommonConstant.SCORE_EVENT_ITEM_DOWN);
				}
			}else{
				ret = forbidFail;
			}
		}
		return ret;
	}

}
