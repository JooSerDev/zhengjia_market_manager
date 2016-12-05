package com.joosure.server.mvc.wechat.entity.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.joosure.manager.mvc.wechat.common.PagenationBean;

public class ActivityEnter extends PagenationBean{
	
	private SimpleDateFormat sdf = null;
	
	public static final String ApprovalStatus_Refused = "refused";//审核拒绝
	public static final String ApprovalStatus_Approved = "approved";//审核通过
	public static final String ApprovalStatus_Waiting = "waiting";//等待审核
	
	
	/**
     * zj_activity_enter.oId (流水号)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private Integer oId;

    /**
     * zj_activity_enter.activityId (活动编号)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private Integer actyId;

    /**
     * zj_activity_enter.activityTitle (活动标题)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private String actyTitle;

    /**
     * zj_activity_enter.userMobile (报名人 手机)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private String userMobile;
    
    private Integer userId;

    /**
     * zj_activity_enter.userName (报名人 姓名)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private String userName;

    /**
     * zj_activity_enter.userRemark (报名人 附言)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private String userRemark;

    /**
     * zj_activity_enter.approvalStatus (审核状态)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private String approvalStatus;

    /**
     * zj_activity_enter.approvalTime (审核时间)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private Date approvalTime;

    /**
     * zj_activity_enter.enterTime (报名时间)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private Date enterTime;

    /**
     * zj_activity_enter.actyTime (活动周期时间，年月日)
     * @ibatorgenerated 2016-11-29 15:57:36
     */
    private String actyTime;

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public Integer getActyId() {
		return actyId;
	}

	public void setActyId(Integer actyId) {
		this.actyId = actyId;
	}

	public String getActyTitle() {
		return actyTitle;
	}

	public void setActyTitle(String actyTitle) {
		this.actyTitle = actyTitle;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public String getActyTime() {
		return actyTime;
	}

	public void setActyTime(String actyTime) {
		this.actyTime = actyTime;
	}
	
	public String getShowEnterTime(){
		if(this.enterTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.enterTime);
		}
		return "";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
