package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class SysUserOpr implements Serializable {
    /**
     * sys_user_opr.id (主键，记录id)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private Long id;

    /**
     * sys_user_opr.userId (理员管id)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private Integer userid;

    /**
     * sys_user_opr.oprUrl (请求地址)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private String oprurl;

    /**
     * sys_user_opr.oprParam (请求参数)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private String oprparam;

    /**
     * sys_user_opr.oprStartTime (请求开始时间)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private Date oprstarttime;

    /**
     * sys_user_opr.oprEndTime (请求结束时间)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private Date oprendtime;

    /**
     * sys_user_opr.usingTime (所用时间： /s)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private Integer usingtime;

    /**
     * sys_user_opr.oprFlag (请求标识：增、删、改、查。。。)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private Integer oprflag;

    /**
     * sys_user_opr.memo (备注)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private String memo;

    /**
     * sys_user_opr.retFlag (回返标识：成功、失败)
     * @ibatorgenerated 2016-08-22 22:34:34
     */
    private Integer retflag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOprurl() {
        return oprurl;
    }

    public void setOprurl(String oprurl) {
        this.oprurl = oprurl;
    }

    public String getOprparam() {
        return oprparam;
    }

    public void setOprparam(String oprparam) {
        this.oprparam = oprparam;
    }

    public Date getOprstarttime() {
        return oprstarttime;
    }

    public void setOprstarttime(Date oprstarttime) {
        this.oprstarttime = oprstarttime;
    }

    public Date getOprendtime() {
        return oprendtime;
    }

    public void setOprendtime(Date oprendtime) {
        this.oprendtime = oprendtime;
    }

    public Integer getUsingtime() {
        return usingtime;
    }

    public void setUsingtime(Integer usingtime) {
        this.usingtime = usingtime;
    }

    public Integer getOprflag() {
        return oprflag;
    }

    public void setOprflag(Integer oprflag) {
        this.oprflag = oprflag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getRetflag() {
        return retflag;
    }

    public void setRetflag(Integer retflag) {
        this.retflag = retflag;
    }
}