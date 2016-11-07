package com.joosure.server.mvc.wechat.entity.pojo;

import java.util.Date;

import com.joosure.manager.mvc.wechat.common.PagenationBean;

public class User extends PagenationBean{

	private Integer userId;
	private String openid;
	private String nickname;
	private String mobile;
	private String headImgUrl;
	private String sex;
	private Integer state;
	private Date createTime;
	private Date lastModifyTime;

	private Integer likeNum;
	private Integer itemNum;
	private Integer exchangeNum;
	private Integer finishNum;// 正佳认证交易完成数量
	
	private Integer score;//总的积分数
	private Integer exchangeFailNum;//正佳认证交易失败数量  即  信息不实 交易数
	private Integer cptNum;//投诉次数
	private Date cptTime;//投诉时间
	
	private String sysId;
	private String unionId;

	public Integer getCptNum() {
		return cptNum;
	}

	public void setCptNum(Integer cptNum) {
		this.cptNum = cptNum;
	}

	public Date getCptTime() {
		return cptTime;
	}

	public void setCptTime(Date cptTime) {
		this.cptTime = cptTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getExchangeFailNum() {
		return exchangeFailNum;
	}

	public void setExchangeFailNum(Integer exchangeFailNum) {
		this.exchangeFailNum = exchangeFailNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Integer getExchangeNum() {
		return exchangeNum;
	}

	public void setExchangeNum(Integer exchangeNum) {
		this.exchangeNum = exchangeNum;
	}

	public Integer getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(Integer finishNum) {
		this.finishNum = finishNum;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
