package com.joosure.server.mvc.wechat.entity.pojo;



/**
 * 文章，帖子的评论
 * @author Ted-wuhuhu
 * @Time 2016年11月11日 下午3:38:32
 *
 */
public class NtatComment {
	
	private NoteComment noteCmt;
	private User user;
	private ArticleComment articleCmt;
	private String noteFlag;
	
	/*private Integer fromUserId;
	private String articleId;
	private String articleTitle;
	private Integer toUserId;
	private String content;
	private String fromUserName;
	private String toUserName;
	private Integer createTime;
	private String status;
	private Date modifyTime;*/
	
	public NtatComment() {	}
	
	public NtatComment(NoteComment noteCmt,User user) {
		this.noteCmt = noteCmt;
		this.user = user;
		this.noteFlag = "yes";
	}
	
	public NtatComment(ArticleComment articleCmt) {
		this.articleCmt = articleCmt;
		this.noteFlag = "no";
	}
	
	public NoteComment getNoteCmt() {
		return noteCmt;
	}
	public void setNoteCmt(NoteComment noteCmt) {
		this.noteCmt = noteCmt;
	}
	public ArticleComment getArticleCmt() {
		return articleCmt;
	}
	public void setArticleCmt(ArticleComment articleCmt) {
		this.articleCmt = articleCmt;
	}
	public String getNoteFlag() {
		return noteFlag;
	}
	public void setNoteFlag(String noteFlag) {
		this.noteFlag = noteFlag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
