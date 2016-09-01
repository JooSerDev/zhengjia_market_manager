package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class WxUserCpt implements Serializable {
    /**
     * wx_user_cpt.oId (记录id)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Integer oid;

    /**
     * wx_user_cpt.userId (户用id)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Integer userid;

    /**
     * wx_user_cpt.userNmae (投诉人名称)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private String username;

    /**
     * wx_user_cpt.itemId (被投诉品物id)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Integer itemid;

    /**
     * wx_user_cpt.itemName (被投诉物品名称)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private String itemname;

    /**
     * wx_user_cpt.exchangeId (被投诉交易id)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Integer exchangeid;

    /**
     * wx_user_cpt.toUserId (被投诉人id)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Integer touserid;

    /**
     * wx_user_cpt.toUserName (被投诉人名称)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private String tousername;

    /**
     * wx_user_cpt.addTime (加添时间)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Date addtime;

    /**
     * wx_user_cpt.message (用户留言)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private String message;

    /**
     * wx_user_cpt.status (诉投状态)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Integer status;

    /**
     * wx_user_cpt.remark (理处备注)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private String remark;

    /**
     * wx_user_cpt.modifyTime (最新处理时间)
     * @ibatorgenerated 2016-08-22 22:33:08
     */
    private Date modifytime;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsernmae(String username) {
        this.username = username;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getExchangeid() {
        return exchangeid;
    }

    public void setExchangeid(Integer exchangeid) {
        this.exchangeid = exchangeid;
    }

    public Integer getTouserid() {
        return touserid;
    }

    public void setTouserid(Integer touserid) {
        this.touserid = touserid;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}