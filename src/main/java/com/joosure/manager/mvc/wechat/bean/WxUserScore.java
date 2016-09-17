package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class WxUserScore implements Serializable {
    /**
     * wx_user_score.oId (积分记录id)
     * @ibatorgenerated 2016-08-17 23:52:16
     */
    private Integer oid;

    /**
     * wx_user_score.ruleId (则规id)
     * @ibatorgenerated 2016-08-17 23:52:16
     */
    private Integer ruleid;

    /**
     * wx_user_score.userId (微信户用id)
     * @ibatorgenerated 2016-08-17 23:52:16
     */
    private Integer userid;

    /**
     * wx_user_score.score (前当产生的积分)
     * @ibatorgenerated 2016-08-17 23:52:16
     */
    private Integer score;

    /**
     * wx_user_score.addTime (加时间添)
     * @ibatorgenerated 2016-08-17 23:52:16
     */
    private Date addtime;

    /**
     * wx_user_score.extdInfo (展扩信息)
     * @ibatorgenerated 2016-08-17 23:52:16
     */
    private String extdinfo;

    /**
     * wx_user_score.memo (备注)
     * @ibatorgenerated 2016-08-17 23:52:16
     */
    private String memo;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getExtdinfo() {
        return extdinfo;
    }

    public void setExtdinfo(String extdinfo) {
        this.extdinfo = extdinfo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}