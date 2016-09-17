package com.joosure.manager.mvc.wechat.service.db;

import java.util.List;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.server.mvc.wechat.entity.pojo.Item;

public interface IWxItemDbService {

	int getItemsCount(QryCondBean qryCondBean);

	List<Item> getItemList(QryCondBean qryCondBean);

}
