package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class UserUser implements Serializable {
    /**
     * user_user.userId
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Integer userid;

    /**
     * user_user.openid
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private String openid;

    /**
     * user_user.nickname
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private String nickname;

    /**
     * user_user.mobile
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private String mobile;

    /**
     * user_user.headImgUrl
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private String headimgurl;

    /**
     * user_user.sex
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private String sex;

    /**
     * user_user.state
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Integer state;

    /**
     * user_user.createTime
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Date createtime;

    /**
     * user_user.lastModifyTime
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Date lastmodifytime;

    /**
     * user_user.likeNum
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Integer likenum;

    /**
     * user_user.itemNum
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Integer itemnum;

    /**
     * user_user.exchangeNum
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Integer exchangenum;

    /**
     * user_user.finishNum
     * @ibatorgenerated 2016-08-16 17:16:31
     */
    private Integer finishnum;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
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

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getItemnum() {
        return itemnum;
    }

    public void setItemnum(Integer itemnum) {
        this.itemnum = itemnum;
    }

    public Integer getExchangenum() {
        return exchangenum;
    }

    public void setExchangenum(Integer exchangenum) {
        this.exchangenum = exchangenum;
    }

    public Integer getFinishnum() {
        return finishnum;
    }

    public void setFinishnum(Integer finishnum) {
        this.finishnum = finishnum;
    }
}