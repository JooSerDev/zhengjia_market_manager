package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    /**
     * sys_user.id (唯一标识)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private Integer id;

    /**
     * sys_user.loginid (登陆账号)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private String loginid;

    /**
     * sys_user.loginpass (陆登密码 SHA256 加密)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private String loginpass;

    /**
     * sys_user.salt (盐 用来加密)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private String salt;

    /**
     * sys_user.addtime (加添时间)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private Date addtime;

    /**
     * sys_user.updatetime (新更时间)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private Date updatetime;

    /**
     * sys_user.status (态状)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private Integer status;

    /**
     * sys_user.loginname (登陆后显示的名字)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private String loginname;

    /**
     * sys_user.extd (扩展信息)
     * @ibatorgenerated 2016-08-16 10:07:19
     */
    private String extd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getExtd() {
        return extd;
    }

    public void setExtd(String extd) {
        this.extd = extd;
    }
}