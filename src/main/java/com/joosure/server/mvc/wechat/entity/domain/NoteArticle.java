package com.joosure.server.mvc.wechat.entity.domain;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.joosure.manager.mvc.wechat.common.PagenationBean;
import com.joosure.server.mvc.wechat.dao.cache.InitDataCache;
import com.joosure.server.mvc.wechat.dao.cache.ItemCache;
import com.joosure.server.mvc.wechat.entity.pojo.Article;
import com.joosure.server.mvc.wechat.entity.pojo.Community;
import com.joosure.server.mvc.wechat.entity.pojo.ItemType;
import com.joosure.server.mvc.wechat.entity.pojo.Note;
import com.joosure.server.mvc.wechat.entity.pojo.User;

/**
 * 帖子和文章
 * @author Ted-wuhuhu
 * @Time 2016年11月10日 上午10:46:52
 *
 */
public class NoteArticle extends PagenationBean implements Comparable<NoteArticle> {

	public static final String NOTE = "note";
	public static final String ARTICLE = "article";
	public static final String SUFFIX_NOTE = "nt";
	public static final String SUFFIX_ARTICLE = "at";
	public static final String NTAT = "all";

	public static final String articleHeadImg = "/fxsj.png";
	public static final String articleAuthorName = "分享市集";
	private static SimpleDateFormat sdf = null;
	public static final String compareHotTop = "hotTop"; //加精页面中置顶排序
	public static final String compareHotNormal = "hotNormal"; //加精页面中非置顶排序
	public static final String compareAllNormal = "allNormal"; //全部界面中 非置顶排序
	public static final String compareAllTop = "allTop"; //全部界面中，置顶排序

	public static final String compareAll = "all"; //所有的

	private List<String> imgUrls = null;

	private String compareFlag;

	@Override
	public int compareTo(NoteArticle other) {
		if(this.compareFlag == null || "".equals(compareFlag)){
			return 0;
		}
		if(compareHotTop.equals(this.compareFlag) || compareAllTop.equals(this.compareFlag)){
			if(this.getTopTime().getTime() >= other.getTopTime().getTime()){
				return 1;
			}
		}
		if(compareHotNormal.equals(this.compareFlag)){
			if(this.getHotTime().getTime() >= other.getHotTime().getTime()){
				return 1;
			}
		}
		if(compareAllNormal.equals(this.compareFlag)){
			if(this.getCreateTime().getTime() >= other.getCreateTime().getTime()){
				return 1;
			}
		}
		return 0;
	}

	private String toDetailPath; //点击帖子/文章跳转页面

	private String headImgUrl;
	private String authorName;
	private Integer authorId;
	private String id;
	private boolean noteFlag;//文章 or 帖子
	private String title;
	private String contentImgUrls;
	private Integer likeNum;
	private Integer cmtNum;
	private String typeName;//社群下的分类
	private Integer typeId;
	private String cmtyName;
	private Integer cmtyId;
	private Date createTime;
	private String showCreateTime;
	private String topFlag;//置顶标识
	private String hotFlag;//加精标识
	private Date topTime;
	private Date hotTime;

	private String content;
	private Integer shareNum;
	private Integer readNum;
	private String status;
	private Date updateTime;
	private String sysId;
	private String shareUrl;

	private String uId;


	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentImgUrls() {
		return contentImgUrls;
	}
	public void setContentImgUrls(String contentImgUrls) {
		this.contentImgUrls = contentImgUrls;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	public Integer getCmtNum() {
		return cmtNum;
	}
	public void setCmtNum(Integer cmtNum) {
		this.cmtNum = cmtNum;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getCmtyName() {
		if(StringUtils.isBlank(this.cmtyName)){
			Community cmty = InitDataCache.getCommunityById(this.cmtyId);
			return cmty.getCmtyName();
		}
		return this.cmtyName;
	}
	public void setCmtyName(String cmtyName) {
		this.cmtyName = cmtyName;
	}
	public Integer getCmtyId() {
		return cmtyId;
	}
	public void setCmtyId(Integer cmtyId) {
		this.cmtyId = cmtyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTopFlag() {
		return topFlag;
	}
	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}
	public String getHotFlag() {
		return hotFlag;
	}
	public void setHotFlag(String hotFlag) {
		this.hotFlag = hotFlag;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public boolean isNoteFlag() {
		return noteFlag;
	}
	public boolean getNoteFlag() {
		return noteFlag;
	}
	public void setNoteFlag(boolean noteFlag) {
		this.noteFlag = noteFlag;
	}
	public String getCompareFlag() {
		return compareFlag;
	}
	public void setCompareFlag(String compareFlag) {
		this.compareFlag = compareFlag;
	}

	public NoteArticle(Article article){
		this(article,null);
	}
	public NoteArticle(Note note,User user){
		this(note, user, null);
	}
	/**
	 * 通过文章构造页面元素
	 * @param article
	 */
	public NoteArticle(Article article,String compareFlag){
		this.headImgUrl = articleHeadImg;
		this.authorName = articleAuthorName;
		this.authorId = article.getUserId();
		this.id = article.getArticleId();
		this.noteFlag = false;
		this.title = article.getArticleTitle();
		this.contentImgUrls = article.getImgUrls();
		this.likeNum = article.getLikeNum();
		this.cmtNum = article.getCmtNum();
		this.typeName = article.getTypeName();//社群下的分类
		this.typeId = article.getTypeId();
		this.cmtyName = article.getCmtyName();
		this.cmtyId = article.getCmtyId();
		this.createTime = article.getCreateTime();
		this.topFlag = article.getTopFlag();//置顶标识
		this.hotFlag = article.getHotFlag();//加精标识
		this.hotTime = article.getHotTime();
		this.topTime = article.getTopTime();
		this.status = article.getStatus();
		if(compareFlag != null){
			this.compareFlag = compareFlag;
		}
		this.content = article.getContent();
	}

	/**
	 * 通过 帖子构造页面元素
	 * @param note
	 * @param user
	 */
	public NoteArticle(Note note,User user,String compareFlag){
		this.headImgUrl = user.getHeadImgUrl();
		this.authorName = user.getNickname();
		this.authorId = note.getUserId();
		this.id = note.getNoteId();
		this.noteFlag = true;
		this.title = note.getNoteTitle();
		this.contentImgUrls = note.getImgUrls();
		this.likeNum = note.getLikeNum();
		this.cmtNum = note.getCmtNum();
		this.typeName = note.getTypeName();//社群下的分类
		this.typeId = note.getTypeId();
		this.cmtyName = note.getCmtyName();
		this.cmtyId = note.getCmtyId();
		this.createTime = note.getCreateTime();
		this.topFlag = note.getTopFlag();//置顶标识
		this.hotFlag = note.getHotFlag();//加精标识
		this.hotTime = note.getHotTime();
		this.topTime = note.getTopTime();
		this.status = note.getStatus();
		if(compareFlag != null ){
			this.compareFlag = compareFlag;
		}
		this.content = note.getContent();
		this.uId = user.getUnionId();
	}

	public NoteArticle(){}

	public String getShowCreateTime() {
		if(this.createTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.createTime);
		}
		return "";
	}
	public Date getTopTime() {
		return topTime;
	}
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	public Date getHotTime() {
		return hotTime;
	}
	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}
	public String getToDetailPath() {
		return toDetailPath;
	}
	public void setToDetailPath(String toDetailPath) {
		this.toDetailPath = toDetailPath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}

	public List<String> getImgUrls(){
		if(this.noteFlag && !StringUtils.isBlank(this.contentImgUrls)){
			this.imgUrls = Arrays.asList(this.contentImgUrls.split(";"));
			return this.imgUrls;
		}
		return null;
	}

	public String getNtatTypeBg() {
		if (cmtyId != null) {
			ItemType it = ItemCache.getItemType(cmtyId);
			if (it != null) {
				return it.getBgColor();
			}
		}
		return "";
	}

	public String getNtatTypeFlag(){
		if(!this.noteFlag){
			return "文章";
		}
		return "帖子";
	}

	public String getIsPublished(){
		switch(this.status){
		case "normal":return "yes";
		case "waiting":return "no";
		default:return "yes";
		}
	}

	public String getShowStatus(){
		switch(this.status){
		case "normal":return "已发布";
		case "waiting":return "待发布";
		default:return "已发布";
		}
	}

	public String getShowUpdateTime(){
		if(this.updateTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.updateTime);
		}
		return "";
	}

}
