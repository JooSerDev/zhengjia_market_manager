package com.joosure.manager.mvc.wechat.service;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.dto.WxItemDetail;
import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.server.mvc.wechat.entity.pojo.Item;


public interface WxItemService {

	/**
	 * 通过条件获取 商品 数
	 * @param qryCondBean
	 * @return
	 */
	int getItemsCount(QryCondBean qryCondBean);

	/**
	 * 通过条件获取 商品信息列表
	 * @param qryCondBean
	 * @return
	 */
	List<Item> getItemList(QryCondBean qryCondBean);

	/**
	 * 审批宝贝
	 * @param item
	 * @return
	 */
	int approvalItem(Item item);

	/**
	 * 下架宝贝
	 * @param item
	 * @return
	 */
	int forceToDowm(Item item);

	List<WxItemDetail> getDetailItemList(QryCondBean qryCondBean);


}
