package com.joosure.manager.mvc.wechat.common;

import java.io.Serializable;
import java.util.Date;

public class QryCondBean implements Serializable{

	private static final long serialVersionUID = -1143893959219871269L;

	private static final int DEFAULT_SIZE = 10;

	private Integer limit = DEFAULT_SIZE;// 每页大小
	private Integer offset = 0;// 偏移，起始位置
	private Integer pageNum = 1;// 第几页
	private Date startTime;
	private Date endTime;
	
	private Integer exchangeId;//交易号
	private Integer userId;//用户标识
	private Integer itemId;//商品标识
	private String mobile;//号码
	private Integer itemType;//商品类别
	private Integer status; //状态
	private Integer state;// TODO exd
	
	private String itemFilter;//物品 过滤晒选
	
	private Integer isRecommended;//是否被推荐
	private String approvalStatus;//审核状态
	private Integer isCpt;//被举报
	private String itemName;//商品名称
	
	private String exchangeFinishStatus;//见证状态
	
	private String loginid;
	private String loginname;
	
	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getItemFilter() {
		return itemFilter;
	}

	public void setItemFilter(String itemFilter) {
		this.itemFilter = itemFilter;
	}

	public String getExchangeFinishStatus() {
		return exchangeFinishStatus;
	}

	public void setExchangeFinishStatus(String exchangeFinishStatus) {
		this.exchangeFinishStatus = exchangeFinishStatus;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public QryCondBean() {}
	
	public Integer getExchangeId() {
		return exchangeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public String getMobile() {
		return mobile;
	}

	public Integer getLimit() {
		return limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public Integer getItemType() {
		return itemType;
	}
	public Integer getStatus() {
		return status;
	}
	public Integer getState() {
		return state;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Integer isRecommended) {
		this.isRecommended = isRecommended;
	}

	public Integer getIsCpt() {
		return isCpt;
	}

	public void setIsCpt(Integer isCpt) {
		this.isCpt = isCpt;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
