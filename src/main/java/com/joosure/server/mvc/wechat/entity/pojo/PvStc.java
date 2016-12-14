package com.joosure.server.mvc.wechat.entity.pojo;

import java.util.Date;

/**
 * 做pv统计的公用 entity
 * @author Ted-wuhuhu
 * @Time 2016年11月24日 上午10:10:28
 *
 */
public class PvStc {
	 /**
     * zj_pv.pkId
     * @ibatorgenerated 2016-11-24 10:11:22
     */
    private String pkId;

    /**
     * zj_pv.flag
     * @ibatorgenerated 2016-11-24 10:11:22
     */
    private String flag;

    /**
     * zj_pv.addTime
     * @ibatorgenerated 2016-11-24 10:11:22
     */
    private Date addTime;

    /**
     * zj_pv.ip
     * @ibatorgenerated 2016-11-24 10:11:22
     */
    private String ip;

    /**
     * zj_pv.remark
     * @ibatorgenerated 2016-11-24 10:11:22
     */
    private String remark;

    /**
     * zj_pv.userId
     * @ibatorgenerated 2016-11-24 10:11:22
     */
    private Integer userId;
    
    private String unionId;

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
