package com.joosure.server.mvc.wechat.service.itf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joosure.server.mvc.wechat.entity.domain.NoteArticle;
import com.joosure.server.mvc.wechat.entity.pojo.Article;
import com.joosure.server.mvc.wechat.entity.pojo.ArticleComment;
import com.joosure.server.mvc.wechat.entity.pojo.Note;
import com.joosure.server.mvc.wechat.entity.pojo.NoteComment;
import com.joosure.server.mvc.wechat.entity.pojo.NtatComment;

/**
 * 
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 下午4:27:22
 *
 */
public interface INoteService {
	
	/**
	 * 添加帖子
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午4:56:43
	 * @param record
	 * @return
	 */
	int addNote(Note record);
	
	/**
	 * 添加文章
	 * @author Ted-wuhuhu
	 * @Time 2016年11月22日 下午2:43:36
	 * @param article
	 * @return
	 */
	int saveArticle(Article article);
	
	/**
	 * 更新帖子，如点赞数，评论数
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午4:56:50
	 * @param record
	 * @return
	 */
	int updateNote(Note record);
	int updateArticle(Article article);
	
	/**
	 * 获取 社群首页中热门的帖子或文章
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午4:58:28
	 * @return
	 */
	List<NoteArticle> getCmtyPageHot();
	
	/**
	 * 通过条件和分页获取帖子
	 * @param pageInfo
	 * @param cond
	 * @return
	 */
	List<Note> getNotesByPage(Note cond);
	List<Article> getArticlesByPage(Note cond);

	/**
	 * 加载置顶 帖子
	 * @author Ted-wuhuhu
	 * @Time 2016年11月10日 上午11:10:33
	 * @param eo
	 * @param pageNum
	 * @param type
	 * @param request
	 * @return
	 */
	List<NoteArticle> loadNoteArticleByPage(HttpServletRequest request);

	/**
	 * 根据id获取帖子、文章
	 * @author Ted-wuhuhu
	 * @Time 2016年11月11日 下午3:59:39
	 * @param noteId
	 * @return
	 */
	Note getNoteById(String noteId);
	Article getArticleById(String articleId);

	/**
	 * 根据id获取评论
	 * @author Ted-wuhuhu
	 * @Time 2016年11月11日 下午3:59:26
	 * @param noteId
	 * @return
	 */
	List<NoteComment> getNoteCmtsByNtId(String noteId,int pageRow,int pageSize);
	List<ArticleComment> getArticleCmtsByAtId(String articleId,int pageRow,int pageSize);

	/**
	 * 获取 评论
	 * @author Ted-wuhuhu
	 * @Time 2016年11月11日 下午5:20:31
	 * @param pageNum
	 * @param itemId
	 * @return
	 */
	List<NtatComment> loadComments(int pageNum, String itemId);


	/**
	 * 获取某个用户的帖子列表
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 下午3:43:00
	 * @param currentUserId
	 * @return
	 */
	List<Note> getNotesByOwnerId(Integer ownerId);

	/**
	 * 获取总数量
	 * @author Ted-wuhuhu
	 * @Time 2016年11月21日 下午3:50:32
	 * @param cond
	 * @return
	 */
	int getNoteCountByPage(Note cond);
	int getArticleCountByPage(Note cond);

	/**
	 * 通过条件分页获取
	 * @author Ted-wuhuhu
	 * @Time 2016年11月22日 上午9:58:59
	 * @param ntatType
	 * @param cmtyId
	 * @param page 
	 * @return
	 */
	List<NoteArticle> loadNtatByPage(String ntatType, Note cond);

	/**
	 * 更新帖子、文章信息（置顶、加精、删、移...）
	 * @author Ted-wuhuhu
	 * @Time 2016年11月23日 下午3:01:01
	 * @param note
	 * @return
	 */
	int changeNoteInfo(Note note);
	int changeArticleInfo(Article article);

}
