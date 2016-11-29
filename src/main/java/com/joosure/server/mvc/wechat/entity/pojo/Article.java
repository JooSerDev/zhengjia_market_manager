package com.joosure.server.mvc.wechat.entity.pojo;

import java.util.Date;

import com.joosure.manager.mvc.wechat.common.PagenationBean;

/**
 * 文章表
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 下午4:26:53
 *
 */
public class Article extends PagenationBean{
	/**
     * zj_article.articleId (文章编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String articleId;

    /**
     * zj_article.articleTitle (文章标题)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String articleTitle;

    /**
     * zj_article.userId (编写文章的用户id)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer userId;

    /**
     * zj_article.author (编写文章的作者名，默认为微信用户名)
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
     * zj_article.cmtyId (社群编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer cmtyId;

    /**
     * zj_article.cmtyName (社群名称)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String cmtyName;

    /**
     * zj_article.cmtNum (评论数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer cmtNum;

    /**
     * zj_article.likeNum (点赞，喜欢数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer likeNum;

    /**
     * zj_article.shareNum (分享数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer shareNum;

    /**
     * zj_article.readNum (阅读数)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer readNum;

    /**
     * zj_article.hotFlag (是否加精  yes  no)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String hotFlag;

    /**
     * zj_article.hotTime (加精时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date hotTime;

    /**
     * zj_article.topFlag (是否置顶   yes  no)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String topFlag;

    /**
     * zj_article.topTime (置顶时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date topTime;

    /**
     * zj_article.status (状态)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String status;

    /**
     * zj_article.createTime (编写时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date createTime;

    /**
     * zj_article.updateTime (更新时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date updateTime;
    
    private String imgUrls;
    private String imgCenterUrls;

    /**
     * zj_article.shareUrl (分享出去的链接)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String shareUrl;

    /**
     * zj_article.sysId (系统编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String sysId;

    /**
     * zj_article.outUserId (外部系统的用户编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String outUserId;

    /**
     * zj_article.content (正文内容)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String content;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOutUserId() {
		return outUserId;
	}

	public void setOutUserId(String outUserId) {
		this.outUserId = outUserId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Integer getCmtyId() {
		return cmtyId;
	}

	public void setCmtyId(Integer cmtyId) {
		this.cmtyId = cmtyId;
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
	
	public String getFirstCenterImgUrl() {
		if(this.imgCenterUrls != null && this.imgCenterUrls.trim() != ""){
			return this.imgCenterUrls.split(";")[0];
		}
		return "";
	}
}
