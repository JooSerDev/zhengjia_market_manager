package com.joosure.server.mvc.wechat.entity.pojo;

import java.util.Date;

import com.joosure.manager.mvc.wechat.common.PagenationBean;

/**
 * 帖子表
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 下午4:27:04
 *
 */
public class Note extends PagenationBean{
	/**
     * zj_note.noteId (帖子编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String noteId;

    /**
     * zj_note.noteTitle (帖子标题)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String noteTitle;

    /**
     * zj_note.userId (编写帖子的用户id)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer userId;

    /**
     * zj_note.author (编写帖子的用户名)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String author;

    /**
     * 二级分类
     */
    private Integer typeId;
    
    /**
     * 二级分类
     */
    private String typeName;
    
    /**
     * zj_note.cmtyId (社群编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer cmtyId;

    /**
     * zj_note.cmtyName (社群名称)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String cmtyName;

    /**
     * zj_note.cmtNum (评论数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer cmtNum;

    /**
     * zj_note.likeNum (点赞，喜欢数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer likeNum;

    /**
     * zj_note.shareNum (分享数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer shareNum;

    /**
     * zj_note.readNum (阅读数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer readNum;

    /**
     * zj_note.hotFlag (是否加精  yes  no)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String hotFlag;

    /**
     * zj_note.hotTime (加精时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date hotTime;

    /**
     * zj_note.topFlag (是否置顶   yes  no)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String topFlag;

    /**
     * zj_note.topTime (置顶时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date topTime;

    /**
     * zj_note.status (状态)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String status;

    /**
     * zj_note.createTime (编写时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date createTime;

    /**
     * zj_note.updateTime (更新时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date updateTime;

    /**
     * zj_note.imgUrls (帖子中的图片地址)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String imgUrls;

    /**
     * zj_note.imgCenterUrls (帖子中的图片原始地址)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String imgCenterUrls;

    /**
     * zj_note.shareUrl (分享出去的链接)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String shareUrl;

    /**
     * zj_note.sysId (系统编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String sysId;

    /**
     * zj_note.outUserId (外部系统的用户编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String outUserId;

    /**
     * zj_note.content (正文内容)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String content;
    
    /**
     * 用于显示帖子简介的图片，默认为用户上传的第一张图片
     */
    private String headImgUrl;

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCmtyName() {
		return cmtyName;
	}

	public void setCmtyName(String cmtyName) {
		this.cmtyName = cmtyName;
	}

	public Integer getCmtNum() {
		return cmtNum;
	}

	public void setCmtNum(Integer cmtNum) {
		this.cmtNum = cmtNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public String getHotFlag() {
		return hotFlag;
	}

	public void setHotFlag(String hotFlag) {
		this.hotFlag = hotFlag;
	}

	public Date getHotTime() {
		return hotTime;
	}

	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}

	public String getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getImgCenterUrls() {
		return imgCenterUrls;
	}

	public void setImgCenterUrls(String imgCenterUrls) {
		this.imgCenterUrls = imgCenterUrls;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getOutUserId() {
		return outUserId;
	}

	public void setOutUserId(String outUserId) {
		this.outUserId = outUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setCmtyId(Integer cmtyId) {
		this.cmtyId = cmtyId;
	}

	public Integer getCmtyId() {
		return cmtyId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFirstCenterImgUrl() {
		if(this.imgCenterUrls != null && this.imgCenterUrls.trim() != ""){
			return this.imgCenterUrls.split(";")[0];
		}
		return "";
	}
}
