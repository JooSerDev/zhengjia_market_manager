package com.joosure.server.mvc.wechat.entity.domain;

import com.joosure.server.mvc.wechat.entity.pojo.User;

/**
 * 
 * @author Ted-wuhuhu
 * @Time 2016年10月28日 下午2:39:56
 *
 */
public class MultiUserInfo {

	private String openIds = "";//
	private String sysIds = "";
	private String userIds = "";
	private boolean multiFlag ;//是否存在多个用户信息   yes,no
	
	private String currentOpenid;
	private String currentSysid;
	private Integer currentUserId;
	private String unionId;
	private String mobile;
	
	private String nickname;
	private String headImgUrl;
	private String sex;
	private Integer state;
	
	private Integer likeNum = 0;
	private Integer itemNum = 0;
	private Integer exchangeNum = 0;
	private Integer finishNum = 0;// 正佳认证交易完成数量
	private Integer score = 0;
	
	public MultiUserInfo(){}
	
	public MultiUserInfo(User user){
		this.likeNum = user.getLikeNum();
		this.itemNum = user.getItemNum();
		this.exchangeNum = user.getExchangeNum();
		this.finishNum = user.getFinishNum();
		this.score = user.getScore();
		this.unionId = user.getUnionId();
		this.mobile = user.getMobile();
		this.nickname = user.getNickname();
		this.headImgUrl = user.getHeadImgUrl();
		this.sex = user.getSex();
		this.state = user.getState();
	}
	
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getOpenIds() {
		return openIds;
	}
	public void setOpenIds(String openIds) {
		this.openIds = openIds;
	}
	public String getSysIds() {
		return sysIds;
	}
	public void setSysIds(String sysIds) {
		this.sysIds = sysIds;
	}
	public String getCurrentOpenid() {
		return currentOpenid;
	}
	public void setCurrentOpenid(String currentOpenid) {
		this.currentOpenid = currentOpenid;
	}
	public String getCurrentSysid() {
		return currentSysid;
	}
	public void setCurrentSysid(String currentSysid) {
		this.currentSysid = currentSysid;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public Integer getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(Integer currentUserId) {
		this.currentUserId = currentUserId;
	}
	public boolean isMultiFlag() {
		return multiFlag;
	}
	public void setMultiFlag(boolean multiFlag) {
		this.multiFlag = multiFlag;
	}
	public boolean getMultiFlag(){
		return multiFlag;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public User getUserInfo(){
		User user = new User();
		user.setUserId(this.currentUserId);
		user.setOpenid(this.currentOpenid);
		user.setUnionId(this.unionId);
		user.setSysId(this.currentSysid);
		user.setNickname(this.nickname);
		user.setSex(this.sex);
		user.setHeadImgUrl(this.headImgUrl);
		user.setMobile(this.mobile);
		//下面的数据信息已经进行了累加，
		user.setLikeNum(this.likeNum);
		user.setItemNum(this.itemNum);
		user.setExchangeNum(this.exchangeNum);
		user.setFinishNum(this.finishNum);
		user.setScore(this.score);
		return user;
	}
}
