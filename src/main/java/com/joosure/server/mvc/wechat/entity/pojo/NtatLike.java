package com.joosure.server.mvc.wechat.entity.pojo;

import java.util.Date;

public class NtatLike {
	 /**
     * zj_ntat_like.userId
     * @ibatorgenerated 2016-11-18 16:45:18
     */
    private Integer userId;

    /**
     * zj_ntat_like.ntatId
     * @ibatorgenerated 2016-11-18 16:45:18
     */
    private String ntatId;

    /**
     * zj_ntat_like.createTime
     * @ibatorgenerated 2016-11-18 16:45:18
     */
    private Date createTime;

    /**
     * zj_ntat_like.ntatFlag (//标识是什么东西)
     * @ibatorgenerated 2016-11-18 16:45:18
     */
    private String ntatFlag;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNtatId() {
		return ntatId;
	}

	public void setNtatId(String ntatId) {
		this.ntatId = ntatId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNtatFlag() {
		return ntatFlag;
	}

	public void setNtatFlag(String ntatFlag) {
		this.ntatFlag = ntatFlag;
	}
}
