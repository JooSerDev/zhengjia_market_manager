package com.joosure.server.mvc.wechat.entity.pojo;

import java.util.Date;

/**
 * 社群表
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 下午4:26:58
 *
 */
public class Community {
	/**
     * zj_community.cmtyId (社群编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer cmtyId;
    
    /**
     * zj_community.cmtyName (社群名称)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String cmtyName;

    /**
     * zj_community.createTime (创建时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date createTime;

    /**
     * zj_community.updateTime (更新时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date updateTime;

    /**
     * zj_community.status (状态，默认normal)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String status;

    /**
     * zj_community.articleNum (文章数量)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer articleNum;

    /**
     * zj_community.noteNum (帖子数量)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer noteNum;
    
    /**
     * zj_community.readNum (帖子数量)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer readNum;

    /**
     * zj_community.bgImgUrl (代表该社群的icon地址)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String bgImgUrl;
    
    /**
     * zj_community.showOrder (展示的顺序)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private int showOrder;
    
    
	public String getCmtyName() {
		return cmtyName;
	}

	public void setCmtyName(String cmtyName) {
		this.cmtyName = cmtyName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}

	public Integer getNoteNum() {
		return noteNum;
	}

	public void setNoteNum(Integer noteNum) {
		this.noteNum = noteNum;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public String getBgImgUrl() {
		return bgImgUrl;
	}

	public void setBgImgUrl(String bgImgUrl) {
		this.bgImgUrl = bgImgUrl;
	}

	public Integer getCmtyId() {
		return cmtyId;
	}

	public void setCmtyId(Integer cmtyId) {
		this.cmtyId = cmtyId;
	}

	public int getAllNum(){
		return 10;
	}
	public int getNewNum(){
		return 10;
	}
}
