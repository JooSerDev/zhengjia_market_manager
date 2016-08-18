package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class ItemExchange implements Serializable {
    /**
     * item_exchange.exchangeId
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Integer exchangeid;

    /**
     * item_exchange.ownerItemId
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Integer owneritemid;

    /**
     * item_exchange.ownerItemName
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private String owneritemname;

    /**
     * item_exchange.ownerId
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Integer ownerid;

    /**
     * item_exchange.changerItemId
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Integer changeritemid;

    /**
     * item_exchange.changerItemName
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private String changeritemname;

    /**
     * item_exchange.changerId
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Integer changerid;

    /**
     * item_exchange.state
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Integer state;

    /**
     * item_exchange.exchangeFinishStatus
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private String exchangefinishstatus;

    /**
     * item_exchange.exchangeState
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private String exchangestate;

    /**
     * item_exchange.createTime
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Date createtime;

    /**
     * item_exchange.exchangeTime
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Date exchangetime;

    /**
     * item_exchange.exchangeFinishTime
     * @ibatorgenerated 2016-08-16 17:16:36
     */
    private Date exchangefinishtime;

    public Integer getExchangeid() {
        return exchangeid;
    }

    public void setExchangeid(Integer exchangeid) {
        this.exchangeid = exchangeid;
    }

    public Integer getOwneritemid() {
        return owneritemid;
    }

    public void setOwneritemid(Integer owneritemid) {
        this.owneritemid = owneritemid;
    }

    public String getOwneritemname() {
        return owneritemname;
    }

    public void setOwneritemname(String owneritemname) {
        this.owneritemname = owneritemname;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public Integer getChangeritemid() {
        return changeritemid;
    }

    public void setChangeritemid(Integer changeritemid) {
        this.changeritemid = changeritemid;
    }

    public String getChangeritemname() {
        return changeritemname;
    }

    public void setChangeritemname(String changeritemname) {
        this.changeritemname = changeritemname;
    }

    public Integer getChangerid() {
        return changerid;
    }

    public void setChangerid(Integer changerid) {
        this.changerid = changerid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExchangefinishstatus() {
        return exchangefinishstatus;
    }

    public void setExchangefinishstatus(String exchangefinishstatus) {
        this.exchangefinishstatus = exchangefinishstatus;
    }

    public String getExchangestate() {
        return exchangestate;
    }

    public void setExchangestate(String exchangestate) {
        this.exchangestate = exchangestate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getExchangetime() {
        return exchangetime;
    }

    public void setExchangetime(Date exchangetime) {
        this.exchangetime = exchangetime;
    }

    public Date getExchangefinishtime() {
        return exchangefinishtime;
    }

    public void setExchangefinishtime(Date exchangefinishtime) {
        this.exchangefinishtime = exchangefinishtime;
    }
}