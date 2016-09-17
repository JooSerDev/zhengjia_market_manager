package com.joosure.manager.mvc.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.server.mvc.wechat.entity.pojo.Item;

public interface WxItemDao {
	/**
	 * 通过条件获取 itemList
	 * @param qryCondBean
	 * @return
	 */
	List<Item> getItemListByCond(QryCondBean qryCondBean);

	int getItemListCount(QryCondBean qryCondBean);

	int approvalItem(Item item);

	int forceToDowm(Item item);

	int banIten(@Param("ownerId")Integer userId);

	void cancelBanItem(@Param("ownerId")Integer userId);
}
