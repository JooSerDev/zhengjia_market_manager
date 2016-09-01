package com.joosure.manager.mvc.wechat.buz;

import java.util.List;

import com.joosure.common.base.entity.QryCondBean;
import com.joosure.manager.mvc.wechat.bean.dto.WxItemDetail;

public interface WxItemBuz {

	/**
	 * 获取物品列表
	 * @param qryCondBean
	 * @return
	 */
	List<WxItemDetail> getItemList(QryCondBean qryCondBean);

	int getItemsCount(QryCondBean qryCondBean);

}
