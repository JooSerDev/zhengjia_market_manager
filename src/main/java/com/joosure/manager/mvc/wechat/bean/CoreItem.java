package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class CoreItem implements Serializable {
	/**
	 * item_item.itemId
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer itemid;

	/**
	 * item_item.name
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private String name;

	/**
	 * item_item.wishItem
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private String wishitem;

	/**
	 * item_item.itemType
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer itemtype;

	/**
	 * item_item.ownerId
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer ownerid;

	/**
	 * item_item.ownerNickname
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private String ownernickname;

	/**
	 * item_item.itemImgNum
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer itemimgnum;

	/**
	 * item_item.itemImgIds
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private String itemimgids;

	/**
	 * item_item.likeNum
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer likenum;

	/**
	 * item_item.unlikeNum
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer unlikenum;

	/**
	 * item_item.markNum
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer marknum;

	/**
	 * item_item.isRecommended
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer isrecommended;

	/**
	 * item_item.recommendedTime
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Date recommendedtime;

	/**
	 * item_item.status
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Integer status;

	/**
	 * item_item.createTime
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Date createtime;

	/**
	 * item_item.lastModifyTime
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Date lastmodifytime;

	/**
	 * item_item.refreshTime
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private Date refreshtime;

	/**
	 * item_item.approvalStatus
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private String approvalstatus;

	/**
	 * item_item.lockStatus
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:40
	 */
	private String lockstatus;
	/**
	 * item_item.description
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:41
	 */
	private String description;

	/**
	 * item_item.itemImgUrls
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:41
	 */
	private String itemimgurls;

	/**
	 * item_item.itemCenterImgUrls
	 * 
	 * @ibatorgenerated 2016-08-16 17:16:41
	 */
	private String itemcenterimgurls;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemimgurls() {
		return itemimgurls;
	}

	public void setItemimgurls(String itemimgurls) {
		this.itemimgurls = itemimgurls;
	}

	public String getItemcenterimgurls() {
		return itemcenterimgurls;
	}

	public void setItemcenterimgurls(String itemcenterimgurls) {
		this.itemcenterimgurls = itemcenterimgurls;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWishitem() {
		return wishitem;
	}

	public void setWishitem(String wishitem) {
		this.wishitem = wishitem;
	}

	public Integer getItemtype() {
		return itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}

	public Integer getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(Integer ownerid) {
		this.ownerid = ownerid;
	}

	public String getOwnernickname() {
		return ownernickname;
	}

	public void setOwnernickname(String ownernickname) {
		this.ownernickname = ownernickname;
	}

	public Integer getItemimgnum() {
		return itemimgnum;
	}

	public void setItemimgnum(Integer itemimgnum) {
		this.itemimgnum = itemimgnum;
	}

	public String getItemimgids() {
		return itemimgids;
	}

	public void setItemimgids(String itemimgids) {
		this.itemimgids = itemimgids;
	}

	public Integer getLikenum() {
		return likenum;
	}

	public void setLikenum(Integer likenum) {
		this.likenum = likenum;
	}

	public Integer getUnlikenum() {
		return unlikenum;
	}

	public void setUnlikenum(Integer unlikenum) {
		this.unlikenum = unlikenum;
	}

	public Integer getMarknum() {
		return marknum;
	}

	public void setMarknum(Integer marknum) {
		this.marknum = marknum;
	}

	public Integer getIsrecommended() {
		return isrecommended;
	}

	public void setIsrecommended(Integer isrecommended) {
		this.isrecommended = isrecommended;
	}

	public Date getRecommendedtime() {
		return recommendedtime;
	}

	public void setRecommendedtime(Date recommendedtime) {
		this.recommendedtime = recommendedtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastmodifytime() {
		return lastmodifytime;
	}

	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

	public Date getRefreshtime() {
		return refreshtime;
	}

	public void setRefreshtime(Date refreshtime) {
		this.refreshtime = refreshtime;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}

	public String getLockstatus() {
		return lockstatus;
	}

	public void setLockstatus(String lockstatus) {
		this.lockstatus = lockstatus;
	}
}