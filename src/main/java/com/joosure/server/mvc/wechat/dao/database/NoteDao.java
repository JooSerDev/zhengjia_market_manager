package com.joosure.server.mvc.wechat.dao.database;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.server.mvc.wechat.entity.pojo.Article;
import com.joosure.server.mvc.wechat.entity.pojo.ArticleComment;
import com.joosure.server.mvc.wechat.entity.pojo.Note;
import com.joosure.server.mvc.wechat.entity.pojo.NoteComment;
import com.joosure.server.mvc.wechat.entity.pojo.NtatCpt;
import com.joosure.server.mvc.wechat.entity.pojo.NtatLike;

public interface NoteDao {
	
	/**
	 * 添加note
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午2:31:18
	 * @param record
	 * @return
	 */
	int insertNote(Note record);
	int insertArticle(Article record);

	/**
	 * 更新note
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午2:31:31
	 * @param record
	 * @return
	 */
	int updateNote(Note record);
	int updateArticle(Article article);

	/**
	 * 通过条件和分页获取帖子
	 * @param pageInfo
	 * @param cond
	 * @return
	 */
	List<Note> getNotesByPage(Note cond);
	List<Article> getArticlesByPage(Note cond);
	
	/**
	 * 获取社群首页 顶端的两条帖子
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午2:34:39
	 * @return
	 */
	List<Note> getCmtyPageNotes();

	/**
	 * 根据id获取note
	 * @author Ted-wuhuhu
	 * @Time 2016年11月11日 下午3:08:27
	 * @param noteId
	 * @return
	 */
	Note getNoteById(@Param("noteId")String noteId);
	Article getArticleById(@Param("articleId")String articleId);
	
	/**
	 * 根据 id 分页获取评论
	 * @author Ted-wuhuhu
	 * @Time 2016年11月11日 下午6:13:32
	 * @param noteId
	 * @param startRow
	 * @param limitSize
	 * @return
	 */
	List<NoteComment> getNoteCmtsByPage(@Param("noteId")String noteId,
		@Param("startRow")int startRow,@Param("limitSize")int limitSize);
	List<ArticleComment> getArticleCmtsByPage(@Param("articleId")String articleId,
			@Param("startRow")int startRow,@Param("limitSize")int limitSize);
	
	/**
	 * 保存 帖子或文章评论
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 上午11:17:41
	 * @param ntComment
	 */
	void saveNtComment(NoteComment ntComment);
	void saveAtComment(ArticleComment atComment);
	
	/**
	 * 获取某个用户的帖子列表
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 下午3:43:00
	 * @param ownerId
	 * @return
	 */
	List<Note> getNotesByOwnerId(@Param("userId")Integer userId);
	
	/**
	 * 保存用户点赞行为
	 * @author Ted-wuhuhu
	 * @Time 2016年11月18日 下午4:47:27
	 * @param record
	 */
	void saveNtatLike(NtatLike record);
	
	/**
	 * 查询某个用户对帖子或文章的点赞行为
	 * @author Ted-wuhuhu
	 * @Time 2016年11月18日 下午5:00:42
	 * @param ntatId
	 * @param userId
	 * @return
	 */
	NtatLike getUserNtatLike(@Param("ntatId")String ntatId,@Param("userId")Integer userId);
	
	/**
	 * 获取某个用户对帖子或文章的投诉行为
	 * @author Ted-wuhuhu
	 * @Time 2016年11月18日 下午8:23:30
	 * @param ntatId
	 * @param userId
	 * @return
	 */
	NtatCpt getUserNtatCpt(@Param("ntatId")String ntatId, @Param("userId")Integer userId);
	
	/**
	 * 保存对帖子或文章的 投诉行为
	 * @author Ted-wuhuhu
	 * @Time 2016年11月18日 下午8:29:11
	 * @param ntatCpt
	 */
	int saveNtatCpt(NtatCpt ntatCpt);
	
	/**
	 * 获取总量
	 * @author Ted-wuhuhu
	 * @Time 2016年11月21日 下午3:52:05
	 * @param cmtyType
	 * @return
	 */
	int getNoteCount(Note cond);
	int getArticleCount(Note cond);
	
	/**
	 * 通过条件获取帖子文章集合
	 * @author Ted-wuhuhu
	 * @Time 2016年11月22日 上午10:24:29
	 * @param cond
	 * @return 
	 */
	List<Note> getNotesByCond(Note cond);
	List<Article> getArticlesByCond(Note cond);
}
