package com.joosure.manager.mvc.wechat.buz;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.dto.WxItemDetail;
import com.joosure.manager.mvc.wechat.common.QryCondBean;

public interface WxItemBuz {

	/**
	 * 获取物品列表
	 * @param qryCondBean
	 * @return
	 */
	List<WxItemDetail> getDetailItemList(QryCondBean qryCondBean);

	int getItemsCount(QryCondBean qryCondBean);

}
