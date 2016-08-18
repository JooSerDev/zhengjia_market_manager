package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class CoreItemComment implements Serializable {
	/**
	 * item_item_comment.toUserId
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:48
	 */
	private Integer touserid;

	/**
	 * item_item_comment.itemId
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:48
	 */
	private Integer itemid;

	/**
	 * item_item_comment.state
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:48
	 */
	private Integer state;

	/**
	 * item_item_comment.comment
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:48
	 */
	private String comment;

	/**
	 * item_item_comment.createTime
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:48
	 */
	private Date createtime;

	/**
	 * item_item_comment.fromUserId
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:48
	 */
	private Integer fromuserid;

	public Integer getTouserid() {
		return touserid;
	}

	public void setTouserid(Integer touserid) {
		this.touserid = touserid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getFromuserid() {
		return fromuserid;
	}

	public void setFromuserid(Integer fromuserid) {
		this.fromuserid = fromuserid;
	}
}