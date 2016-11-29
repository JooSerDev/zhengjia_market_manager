package com.joosure.server.mvc.wechat.entity.pojo;

import java.util.Date;

public class NtatCpt {
	/**
     * zj_ntat_cpt.userId
     * @ibatorgenerated 2016-11-18 20:18:40
     */
    private Integer userId;

    /**
     * zj_ntat_cpt.ntatId
     * @ibatorgenerated 2016-11-18 20:18:40
     */
    private String ntatId;

    /**
     * zj_ntat_cpt.createTime
     * @ibatorgenerated 2016-11-18 20:18:40
     */
    private Date createTime;

    /**
     * zj_ntat_cpt.content
     * @ibatorgenerated 2016-11-18 20:18:40
     */
    private String content;

    /**
     * zj_ntat_cpt.ntatFlag
     * @ibatorgenerated 2016-11-18 20:18:40
     */
    private String ntatFlag;

    /**
     * zj_ntat_cpt.toUserId
     * @ibatorgenerated 2016-11-18 20:18:40
     */
    private Integer toUserId;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNtatFlag() {
		return ntatFlag;
	}

	public void setNtatFlag(String ntatFlag) {
		this.ntatFlag = ntatFlag;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

}
