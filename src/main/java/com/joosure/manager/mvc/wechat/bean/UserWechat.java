package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class UserWechat implements Serializable {
    /**
     * user_wechat.openid
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String openid;

    /**
     * user_wechat.nickname
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String nickname;

    /**
     * user_wechat.sex
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String sex;

    /**
     * user_wechat.province
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String province;

    /**
     * user_wechat.city
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String city;

    /**
     * user_wechat.country
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String country;

    /**
     * user_wechat.headimgurl
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String headimgurl;

    /**
     * user_wechat.unionid
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private String unionid;

    /**
     * user_wechat.createTime
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private Date createtime;

    /**
     * user_wechat.lastUpdateTime
     * @ibatorgenerated 2016-08-16 17:16:52
     */
    private Date lastupdatetime;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}