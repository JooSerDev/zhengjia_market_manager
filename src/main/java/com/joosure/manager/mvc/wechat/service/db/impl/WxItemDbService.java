package com.joosure.manager.mvc.wechat.service.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.dao.WxItemDao;
import com.joosure.manager.mvc.wechat.service.db.IWxItemDbService;
import com.joosure.server.mvc.wechat.entity.pojo.Item;

@Service("wxItemDbService")
public class WxItemDbService implements IWxItemDbService{
	
	@Autowired
	private WxItemDao wxItemDao;

	@Override
	public int getItemsCount(QryCondBean qryCondBean) {
		return wxItemDao.getItemListCount(qryCondBean);
	}

	@Override
	public List<Item> getItemList(QryCondBean qryCondBean) {
		return wxItemDao.getItemListByCond(qryCondBean);
	}
}
