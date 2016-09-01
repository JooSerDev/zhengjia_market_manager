package com.joosure.manager.mvc.wechat.buz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joosure.common.base.entity.QryCondBean;
import com.joosure.manager.mvc.wechat.bean.WxUserCpt;
import com.joosure.manager.mvc.wechat.bean.dto.WxItemDetail;
import com.joosure.manager.mvc.wechat.buz.WxItemBuz;
import com.joosure.manager.mvc.wechat.dao.WxUserCptDao;
import com.joosure.manager.mvc.wechat.service.WxItemService;
import com.joosure.server.mvc.wechat.dao.database.UserDao;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;

@Component("wxItemBuz")
public class WxItemBuzImpl implements WxItemBuz{
	
	@Autowired
	private WxItemService wxItemService;
	@Autowired
	private WxUserCptDao userCptDao;
	@Autowired
	private UserDao userDao;

	@Override
	public List<WxItemDetail> getItemList(QryCondBean qryCondBean) {
		List<WxItemDetail> itemsDetailList = new ArrayList<WxItemDetail>();
		WxItemDetail itemDetail ;
		if(qryCondBean.getIsCpt()!=null && qryCondBean.getIsCpt() == 1){//查被举报的 商品信息
			List<WxUserCpt> userCptList = userCptDao.getCptList(qryCondBean);
			if(userCptList!=null && userCptList.size()>0){
				for(WxUserCpt userCpt : userCptList){
					itemDetail = new WxItemDetail();
					itemDetail.setUserCpt(userCpt);
					itemsDetailList.add(itemDetail);
				}
			}
		}else{
			List<Item> items = wxItemService.getItemList(qryCondBean);
			if(items!=null && items.size()>0){
				for(Item item:items){
					User user = userDao.getUserById(item.getOwnerId());
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
	public int getItemsCount(QryCondBean qryCondBean) {
		if(qryCondBean.getIsCpt() != null && qryCondBean.getIsCpt() == 1){
			return userCptDao.getCptListCount(qryCondBean);
		}else{
			return wxItemService.getItemsCount(qryCondBean);
		}
	}

}
