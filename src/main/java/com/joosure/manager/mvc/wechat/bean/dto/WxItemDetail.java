package com.joosure.manager.mvc.wechat.bean.dto;

import com.joosure.manager.mvc.wechat.bean.WxUserCpt;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;

public class WxItemDetail {
	private Item item;//商品信息
	private User ownerInfo;//用户信息
	private WxUserCpt userCpt;//用户投诉或举报信息
	
	public WxUserCpt getUserCpt() {
		return userCpt;
	}
	public void setUserCpt(WxUserCpt userCpt) {
		this.userCpt = userCpt;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public User getOwnerInfo() {
		return ownerInfo;
	}
	public void setOwnerInfo(User ownerInfo) {
		this.ownerInfo = ownerInfo;
	}
}
