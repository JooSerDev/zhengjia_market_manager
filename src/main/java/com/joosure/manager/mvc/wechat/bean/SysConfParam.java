package com.joosure.manager.mvc.wechat.bean;

import java.io.Serializable;
import java.util.Date;

import com.joosure.manager.mvc.wechat.common.PagenationBean;

public class SysConfParam extends PagenationBean implements Serializable {
    /**
     * sys_conf_param.paramname (数参名)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private String paramname;

	private String paramvalue;
    
    /**
     * sys_conf_param.paramdesc (数参描述)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private String paramdesc;

    /**
     * sys_conf_param.addtime (加添时间)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private Date addtime;

    /**
     * sys_conf_param.status (态状)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private Integer status;

    /**
     * sys_conf_param.updatetime (新更时间)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private Date updatetime;

    /**
     * sys_conf_param.memo (备注)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private String memo;

    /**
     * sys_conf_param.paramgroup (数参组)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private String paramgroup;

    /**
     * sys_conf_param.paramid (参数id)
     * @ibatorgenerated 2016-08-15 16:32:23
     */
    private String paramid;

    public String getParamgroup() {
        return paramgroup;
    }

    public void setParamgroup(String paramgroup) {
        this.paramgroup = paramgroup;
    }

    public String getParamid() {
        return paramid;
    }

    public void setParamid(String paramid) {
        this.paramid = paramid;
    }
    
    public String getParamname() {
        return paramname;
    }

    public void setParamname(String paramname) {
        this.paramname = paramname;
    }

    public String getParamdesc() {
        return paramdesc;
    }

    public void setParamdesc(String paramdesc) {
        this.paramdesc = paramdesc;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public String getParamvalue() {
		return paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

}