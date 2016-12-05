package com.joosure.server.mvc.wechat.entity.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.joosure.manager.mvc.wechat.common.PagenationBean;


public class Activity extends PagenationBean{
	
	public static final String STATUS_normal = "normal";
	public static final String STATUS_invisible = "invisible";
	public static final String STATUS_deleted = "deleted";
	public static final String STATUS_stoped = "stoped";
	
	/**
	 * zj_activity.id (活动编号)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Integer id;

	/**
	 * zj_activity.title (活动标题)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private String title;

	/**
	 * zj_activity.createTime (添加时间)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Date createTime;

	/**
	 * zj_activity.showOrder (0展示在最上面，展示顺序)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Integer showOrder;

	/**
	 * zj_activity.bgImgUrl (活动banner的图片地址)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private String bgImgUrl;

	/**
	 * zj_activity.adminId (添加人的编号)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Integer adminId;

	/**
	 * zj_activity.outSideUrl (点击跳转外部链接)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private String outSideUrl;

	/**
	 * zj_activity.description (活动描述)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private String description;

	/**
	 * zj_activity.status (状态：stop、normal、invisible、deleted)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private String status;

	/**
	 * zj_activity.statusTime (状态时间)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Date statusTime;

	/**
	 * zj_activity.enterCount (报名名额)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Integer enterCount;

	/**
	 * zj_activity.limitCount (限制可通过审核数)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Integer limitCount;

	/**
	 * zj_activity.alreadyCount (报名剩余名额)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Integer alreadyCount;

	/**
	 * zj_activity.topFlag (置顶标识)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private String topFlag;

	/**
	 * zj_activity.topTime (置顶时间)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Date topTime;

	/**
	 * zj_activity.publishTime (发布时间)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Date publishTime;

	/**
	 * zj_activity.enterStartTime (报名开始时间)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Date enterStartTime;

	/**
	 * zj_activity.enterEndTime (报名截止时间)
	 * @ibatorgenerated 2016-11-29 15:57:36
	 */
	private Date enterEndTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public String getBgImgUrl() {
		return bgImgUrl;
	}

	public void setBgImgUrl(String bgImgUrl) {
		this.bgImgUrl = bgImgUrl;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getOutSideUrl() {
		return outSideUrl;
	}

	public void setOutSideUrl(String outSideUrl) {
		this.outSideUrl = outSideUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	public Integer getEnterCount() {
		return enterCount;
	}

	public void setEnterCount(Integer enterCount) {
		this.enterCount = enterCount;
	}

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

	public String getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getEnterStartTime() {
		return enterStartTime;
	}

	public void setEnterStartTime(Date enterStartTime) {
		this.enterStartTime = enterStartTime;
	}

	public Date getEnterEndTime() {
		return enterEndTime;
	}

	public void setEnterEndTime(Date enterEndTime) {
		this.enterEndTime = enterEndTime;
	}

	private SimpleDateFormat sdf = null;

	public String getShowEnterStartTime(){
		if(this.enterStartTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.enterStartTime);
		}
		return "";
	}
	
	public String getShowPublishTime(){
		if(this.publishTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.publishTime);
		}
		return "";
	}
	
	public String getShowStatusTime(){
		if(this.statusTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.statusTime);
		}
		return "";
	}
	
	public String getShowCreateTime(){
		if(this.createTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.createTime);
		}
		return "";
	}

	public Integer getAlreadyCount() {
		return alreadyCount;
	}

	public void setAlreadyCount(Integer alreadyCount) {
		this.alreadyCount = alreadyCount;
	}

}
