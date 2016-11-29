/**
 * 
 */
package com.joosure.server.mvc.wechat.entity.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 帖子评论表
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 下午5:06:01
 * 
 */
public class NoteComment {
	
	private static SimpleDateFormat sdf = null;
	
	private Integer fromUserId;
	
	/**
     * zj_note_cmt.noteId (帖子编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String noteId;

    /**
     * zj_note_cmt.noteTitle (帖子标题)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String noteTitle;

    /**
     * zj_note_cmt.toUserId (被评论人编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Integer toUserId;

    /**
     * zj_note_cmt.content (评论内容)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String content;

    /**
     * zj_note_cmt.fromUserName (评论人名称)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String fromUserName;

    /**
     * zj_note_cmt.toUserName (被评论人名称)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String toUserName;

    /**
     * zj_note_cmt.createTime (被评论人编号)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date createTime;
    
    /**
     * zj_note_cmt.status (状态)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private String status;

    /**
     * zj_note_cmt.modifyTime (更新时间)
     * @ibatorgenerated 2016-10-20 16:12:43
     */
    private Date modifyTime;

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

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	
	public String getShowCreateTime(){
		if(this.createTime != null){
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			return sdf.format(this.createTime);
		}
		return "";
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
