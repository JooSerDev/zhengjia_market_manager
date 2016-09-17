package com.joosure.server.mvc.wechat.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class WxUserMsg implements Serializable {
    /**
     * wx_user_msg.msgId (消息编号)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private Integer msgid;

    /**
     * wx_user_msg.userId (微信用户id)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private Integer userid;

    /**
     * wx_user_msg.userName (微信用户名)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private String username;

    /**
     * wx_user_msg.msgType (消息类型，逗号分隔开；交易请求通知，交易响应通知，宝贝审核通知)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private String msgtype;

    /**
     * wx_user_msg.msgCount (消息数量，逗号分隔开)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private String msgcount;

    /**
     * wx_user_msg.total (消息总量)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private Integer total;

    /**
     * wx_user_msg.createTime (添加时间)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private Date createtime;

    /**
     * wx_user_msg.readTime (读 时间)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private Date readtime;

    /**
     * wx_user_msg.readFlag (已读标识)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private Integer readflag;

    /**
     * wx_user_msg.remark (备注)
     * @ibatorgenerated 2016-09-14 14:33:21
     */
    private String remark;

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsgcount() {
        return msgcount;
    }

    public void setMsgcount(String msgcount) {
        this.msgcount = msgcount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getReadtime() {
        return readtime;
    }

    public void setReadtime(Date readtime) {
        this.readtime = readtime;
    }

    public Integer getReadflag() {
        return readflag;
    }

    public void setReadflag(Integer readflag) {
        this.readflag = readflag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}