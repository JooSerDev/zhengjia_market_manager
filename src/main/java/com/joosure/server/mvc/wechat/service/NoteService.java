package com.joosure.server.mvc.wechat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.server.mvc.wechat.dao.database.NoteDao;
import com.joosure.server.mvc.wechat.entity.domain.NoteArticle;
import com.joosure.server.mvc.wechat.entity.pojo.Article;
import com.joosure.server.mvc.wechat.entity.pojo.ArticleComment;
import com.joosure.server.mvc.wechat.entity.pojo.Note;
import com.joosure.server.mvc.wechat.entity.pojo.NoteComment;
import com.joosure.server.mvc.wechat.entity.pojo.NtatComment;
import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.service.db.IUserDbService;
import com.joosure.server.mvc.wechat.service.itf.INoteService;
import com.joosure.server.mvc.wechat.service.itf.IScoreService;
import com.shawn.server.core.util.StringUtil;

/**
 * 
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 下午4:27:33
 *
 */
@Service("noteService")
public class NoteService implements INoteService{

	@Autowired
	private NoteDao noteDao;
	@Autowired
	private IUserDbService userDbService;
	@Autowired
	private IScoreService scoreService;

	@Override
	public int addNote(Note record) {
		return noteDao.insertNote(record);
	}
	
	@Override
	public int saveArticle(Article article) {
		return noteDao.insertArticle(article);
	}

	@Override
	public int changeNoteInfo(Note note) {
		return noteDao.updateNote(note);
	}

	@Override
	public int changeArticleInfo(Article article) {
		return noteDao.updateArticle(article);
	}
	
	@Override
	public int updateNote(Note record) {
		return noteDao.updateNote(record);
	}

	@Override
	public int updateArticle(Article article) {
		return noteDao.updateArticle(article);
	}

	@Override
	public List<Note> getNotesByPage(Note cond) {
		return noteDao.getNotesByPage(cond);
	}
	@Override
	public List<Article> getArticlesByPage(Note cond) {
		return noteDao.getArticlesByPage(cond);
	}

	@Override
	public Note getNoteById(String noteId) {
		return noteDao.getNoteById(noteId);
	}
	
	@Override
	public Article getArticleById(String articleId) {
		return noteDao.getArticleById(articleId);
	}

	@Override
	public List<Note> getNotesByOwnerId(Integer ownerId) {
		return noteDao.getNotesByOwnerId(ownerId);
	}

	@Override
	public List<NoteComment> getNoteCmtsByNtId(String noteId,int pageRow,int pageSize) {
		return noteDao.getNoteCmtsByPage(noteId, pageRow, pageSize);
	}
	@Override
	public List<ArticleComment> getArticleCmtsByAtId(String articleId,int pageRow,int pageSize) {
		return noteDao.getArticleCmtsByPage(articleId, pageRow, pageSize);
	}

	@Override
	public List<NoteArticle> getCmtyPageHot() {
		List<NoteArticle> hotNtats = new ArrayList<NoteArticle>();
		List<Note> hotNotes = noteDao.getCmtyPageNotes();
		for(Note note : hotNotes){
			User user = userDbService.getUserById(note.getUserId());
			hotNtats.add(new NoteArticle(note,user));
		}
		return hotNtats;
	}

	@Override
	public List<NoteArticle> loadNoteArticleByPage(HttpServletRequest request){
		String type = request.getParameter("type");
		String ntatType = request.getParameter("ntatType");
		if (StringUtil.isBlank(type)) {
			//throw new RequestParamsException("请求参数错误");
		}
		Date hotTime = null;
		Date createTime = null;
		Integer cmtyId = null;
		try {
			cmtyId = Integer.parseInt(request.getParameter("cmtyId"));
			if(NoteArticle.compareHotNormal.equals(type.trim())){
				hotTime = new Date(Long.parseLong(request.getParameter("hotTime")));
			}
			if(NoteArticle.compareAllNormal.equals(type.trim())
					|| NoteArticle.compareAll.equals(type.trim())){
				createTime = new Date(Long.parseLong(request.getParameter("createTime")));
			}
		} catch (Exception e) {
			//throw new RequestParamsException("请求参数错误");
		}
		Note cond = new Note();
		cond.setCmtyId(cmtyId);
		String compareFlag = type;
		if(NoteArticle.compareHotTop.equals(type.trim())){
			//获取 加精页面所有的置顶  帖子、文章
			cond.setHotFlag("yes");
			cond.setTopFlag("yes");
			cond.setLimit(0);//获取全部，不分页
			cond.setTopTime(new Date());
		}
		if(NoteArticle.compareHotNormal.equals(type.trim())){
			//获取 加精页面所有的非置顶  帖子、文章
			cond.setHotFlag("yes");
			cond.setTopFlag("no");
			cond.setHotTime(hotTime);
		}
		if(NoteArticle.compareAllTop.equals(type.trim())){
			//获取 加精页面所有的置顶  帖子、文章
			cond.setTopFlag("yes");
			cond.setHotFlag("no");
			cond.setLimit(0);//获取全部，不分页
			cond.setTopTime(new Date());
		}
		if(NoteArticle.compareAllNormal.equals(type.trim())){
			//获取 加精页面所有的置顶  帖子、文章
			cond.setHotFlag("no");
			cond.setTopFlag("no");
			cond.setCreateTime(createTime);
		}
		if(NoteArticle.compareAll.equals(type.trim())){
			cond.setCreateTime(createTime);
		}
		List<Note> allNotes = null;
		List<Article> allArticles = null;
		if(NoteArticle.NOTE.equals(ntatType)){
			allNotes = getNotesByPage(cond);
		}
		if(NoteArticle.NOTE.equals(ntatType)){
			allArticles = getArticlesByPage(cond);
		}
		if(NoteArticle.NTAT.equals(ntatType)){
			allNotes = getNotesByPage(cond);
			allArticles = getArticlesByPage(cond);
		}
		return buildNoteArticle(allNotes, allArticles,compareFlag,request);
	}

	private List<NoteArticle> buildNoteArticle(List<Note> top10Notes, List<Article> top10Articles,
			String compareFlag, HttpServletRequest request) {
		List<NoteArticle> top = new ArrayList<NoteArticle>();
		//note 和  article  两个都为空
		if((top10Notes == null || top10Notes.size() == 0) && 
				(top10Articles == null || top10Articles.size() == 0)){
			return top;
		}
		//note 为空
		String eo = request.getParameter("eo");
		if(top10Notes == null || top10Notes.size() == 0){
			for(int i=0;i<top10Articles.size();i++){
				NoteArticle noteArticle = new NoteArticle(top10Articles.get(i));
				noteArticle.setToDetailPath(getPreUrl(request)+"/cmty/detailNtat?eo"+eo
						+"&flag=at&pkId="+noteArticle.getId());
				top.add(noteArticle);
			}
			return top;
		}
		//article 为空
		if(top10Articles == null || top10Articles.size() == 0){
			for(int i=0;i<top10Notes.size();i++){
				User user = userDbService.getUserById(top10Notes.get(i).getUserId());
				NoteArticle noteArticle = new NoteArticle(top10Notes.get(i),user);
				noteArticle.setToDetailPath(getPreUrl(request)+"/cmty/detailNtat?eo"+eo
						+"&flag=nt&pkId="+noteArticle.getId());
				top.add(noteArticle);
			}
			return top;
		}
		/*//note的最小时间大于article的最大时间
		if(top10Notes.get(top10Notes.size()).getTopTime().getTime() >=
			top10Articles.get(0).getTopTime().getTime()){
			for(int i=0;i<top10Notes.size();i++){
				User user = userDbService.getUserById(top10Notes.get(i).getUserId());
				top.add(new NoteArticle(top10Notes.get(i),user));
			}
			for(int i=0;i<(10-top10Notes.size());i++){
				top.add(new NoteArticle(top10Articles.get(i)));
			}
			return top;
		}
		//article的最小时间大于note的最大时间
		if(top10Articles.get(top10Articles.size()).getTopTime().getTime() >=
				top10Notes.get(top10Notes.size()).getTopTime().getTime()){
			for(int i=0;i<top10Articles.size();i++){
				top.add(new NoteArticle(top10Articles.get(i)));
			}
			for(int i=0;i<(10-top10Articles.size());i++){
				User user = userDbService.getUserById(top10Notes.get(i).getUserId());
				top.add(new NoteArticle(top10Notes.get(i),user));
			}
			return top;
		}*/

		//note和article的时间交叉
		for(Note note : top10Notes){
			User user = userDbService.getUserById(note.getUserId());
			NoteArticle noteArticle = new NoteArticle(note,user,compareFlag);
			noteArticle.setToDetailPath(getPreUrl(request)+"/cmty/detailNtat?eo"+eo
					+"&flag=nt&pkId="+noteArticle.getId());
			top.add(noteArticle);
		}
		for(Article article : top10Articles){
			NoteArticle noteArticle = new NoteArticle(article,compareFlag);
			noteArticle.setToDetailPath(getPreUrl(request)+"/cmty/detailNtat?eo"+eo
					+"&flag=at&pkId="+noteArticle.getId());
			top.add(noteArticle);
		}
		Collections.sort(top);
		//如果是置顶的就不需要截取了。
		if(NoteArticle.compareHotTop.equals(compareFlag) 
				|| NoteArticle.compareAllTop.equals(compareFlag)){
			return top;
		}
		return top.subList(0, 10);//截取前十个
	}

	/**
	 * 获取请求路径前缀  http://xx.xx.xx/wechat/
	 * @author Ted-wuhuhu
	 * @Time 2016年11月10日 上午11:40:54
	 * @param request
	 * @return
	 */
	private String getPreUrl(HttpServletRequest request) {
		/*return request.getScheme() + "://" + request.getServerName()
				+ request.getContextPath() + WechatConstant.SCHEMA_MARKET;*/
		return "";
	}

	@Override
	public List<NtatComment> loadComments(int pageNum, String itemId) {
		return null;
	}

	@Override
	public int getNoteCountByPage(Note cond) {
		return noteDao.getNoteCount(cond);
	}

	@Override
	public int getArticleCountByPage(Note cond) {
		return noteDao.getArticleCount(cond);
	}

	@Override
	public List<NoteArticle> loadNtatByPage(String ntatType,Note cond) {
		List<NoteArticle> data = new ArrayList<NoteArticle>();
		if(NoteArticle.NOTE.equals(ntatType)){
			//帖子
			List<Note> notes = noteDao.getNotesByCond(cond);
			if(notes != null && notes.size() > 0){
				for(Note note : notes){
					data.add(new NoteArticle(note,userDbService.getUserById(note.getUserId())));
				}
			}
		}else if(NoteArticle.ARTICLE.equals(ntatType)){
			//文章
			List<Article> articles = noteDao.getArticlesByCond(cond);
			if(articles != null && articles.size() > 0){
				for(Article article : articles){
					data.add(new NoteArticle(article));
				}
			}
		}
		return data;
	}

}
