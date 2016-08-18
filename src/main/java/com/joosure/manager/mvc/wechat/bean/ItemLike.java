package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class ItemLike implements Serializable {
	/**
	 * item_like.createTime
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:59
	 */
	private Date createtime;

	/**
	 * item_like.itemId
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:59
	 */
	private Integer itemid;

	/**
	 * item_like.userId
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:59
	 */
	private Integer userid;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}