package com.joosure.manager.mvc.wechat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.dao.cache.InitDataCache;
import com.joosure.server.mvc.wechat.entity.domain.NoteArticle;
import com.joosure.server.mvc.wechat.entity.pojo.Article;
import com.joosure.server.mvc.wechat.entity.pojo.Community;
import com.joosure.server.mvc.wechat.entity.pojo.CommunityType;
import com.joosure.server.mvc.wechat.entity.pojo.Note;
import com.joosure.server.mvc.wechat.entity.pojo.User;
import com.joosure.server.mvc.wechat.service.db.IUserDbService;
import com.joosure.server.mvc.wechat.service.itf.INoteService;

/**
 * 
 * @author Ted-wuhuhu
 * @Time 2016年10月25日 上午10:26:29
 *
 */
@Controller
@RequestMapping("/community")
public class CommunityController {
	
	private static Logger log = Logger.getLogger(CommunityController.class);
	
	@Autowired
	private INoteService noteService;
	
	@Autowired
	private IUserDbService userDbService;
	
	@RequestMapping("/cmtyManage")
	public String toCmtyManagePage(Model model){
		model.addAttribute("cmtyTypes",InitDataCache.getCommunity());
		return "community/communityManage";
	}
	
	@RequestMapping("/articleManage")
	public String toArticleManagePage(Model model){
		model.addAttribute("cmtyTypes",InitDataCache.getCommunity());
		return "community/articleManage";
	}
	
	@RequestMapping("/articleDetail")
	public String toArticleDetailPage(){
		return "community/articleItemDetail";
	}
	
	@RequestMapping("/ntatDetail")
	public String toNtatDetailPage(Model model,HttpServletRequest request){
		model.addAttribute("ntatId",request.getParameter("ntatId"));
		return "community/ntatDetail";
	}
	
	@RequestMapping("/writeArticle")
	public String toAddArticlePage(){
		return "community/writeArticle";
	}
	
	@RequestMapping("/scanArticle")
	public String scanArticle(String atId,Model model){
		Article at = noteService.getArticleById(atId);
		model.addAttribute("article", at);
		return "community/articleView";
	}
	
	/**
	 * 获取社群分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月21日 下午2:50:21
	 * @return
	 */
	@RequestMapping("/getCmtyTypes")
	@ResponseBody
	public JsonResultBean<Community> getCmtyTypes(){
		List<Community> cmtyTypes = InitDataCache.getCommunity();
		JsonResultBean<Community> ret = new JsonResultBean<Community>();
		ret.setResultList(cmtyTypes);
		return ret;
	}
	
	/**
	 * 获取社群关联的二级分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月21日 下午2:50:21
	 * @return
	 */
	@RequestMapping("/getCmtySubTypes")
	@ResponseBody
	public JsonResultBean<CommunityType> getCmtySubTypes(int cmtyId){
		List<CommunityType> cmtySubTypes = InitDataCache.getCmtySubTypes(cmtyId);
		JsonResultBean<CommunityType> ret = new JsonResultBean<CommunityType>();
		ret.setResultList(cmtySubTypes);
		return ret;
	}
	
	
	
/*	*//**
	 * 分页加载帖子文章
	 * @author Ted-wuhuhu
	 * @Time 2016年11月21日 下午4:34:12
	 * @param request
	 * @return
	 *//*
	@RequestMapping("/getNtatListByPage")
	@ResponseBody
	public JsonResultBean<NoteArticle> getNtatList(HttpServletRequest request){
		JsonResultBean<NoteArticle> ret = new JsonResultBean<NoteArticle>();
		String ntatType = request.getParameter("ntatType");
		String cmtyId = request.getParameter("cmtyId");
		if(StringUtils.isBlank(ntatType) || StringUtils.isBlank(cmtyId)){
			ret.setRetCode("1001");
			ret.setRetMsg("请求参数错误！");
			return ret;
		}
		List<NoteArticle> ntatList = null;
		int total = 0;
		//将最后一个的时间传过去，方便下次分页接收查询的分割时间
		int lastIndex = 0;
		if(ntatList.size() > 0){
			lastIndex = ntatList.size() - 1;
			ret.setResult(ntatList.get(lastIndex).getCreateTime().getTime());
		}
		ret.setRows(ntatList);
		ret.setTotal(total);
		return ret;
	}*/
	
	/**
	 * 分页加载帖子文章
	 * @author Ted-wuhuhu
	 * @Time 2016年11月21日 下午4:34:12
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNtatListByPage")
	@ResponseBody 
	public JsonResultBean<NoteArticle> getNtatByPage(HttpServletRequest request,HttpServletResponse response, Note cond){
		
		JsonResultBean<NoteArticle> ret = new JsonResultBean<NoteArticle>();
		String ntatType = request.getParameter("ntatType");
		if(StringUtils.isBlank(ntatType)){
			ret.setRetCode("1001");
			ret.setRetMsg("请求参数错误！");
			return ret;
		}
		List<NoteArticle> ntatList = null;
		int total = 0;
		ntatList = noteService.loadNtatByPage(ntatType,cond);
		if(NoteArticle.NOTE.equals(ntatType)){
			total = noteService.getNoteCountByPage(cond);
		}
		if(NoteArticle.ARTICLE.equals(ntatType)){
			total = noteService.getArticleCountByPage(cond);
		}
		ret.setRows(ntatList);
		ret.setTotal(total);
		
		/*String json = JsonUtil.Object2JsonStr(ret);
		ResponseHandler.output(response, json);*/
		return ret;
	}
	
	@RequestMapping("/saveArticle")
	@ResponseBody
	public JsonResultBean<String> saveArticle(Article article,HttpServletRequest request){
		JsonResultBean<String> retData = new JsonResultBean<String>();
		article.setArticleId(ManagerUtils.generateIdForNote(false));
		SysUser admin = (SysUser) request.getSession().getAttribute(CommonConstant.CurrentSysUser);
		article.setUserId(admin.getId());
		if(StringUtils.isBlank(article.getAuthor())){
			article.setAuthor(admin.getLoginname());
		}
		try {
			noteService.saveArticle(article);
		} catch (Exception e) {
			log.error("添加文章失败==> "+e.getMessage());
			retData.setRetFlag(false);
			retData.setRetCode("1001");
			retData.setRetMsg("添加文章失败");
			return retData;
		}
		retData.setResult(article.getContent());
		retData.setRetFlag(true);
		return retData;
	}
	
	/**
	 * 加载帖子文章详细信息
	 * @author Ted-wuhuhu
	 * @Time 2016年11月21日 下午4:34:12
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNtatDetail")
	@ResponseBody 
	public JsonResultBean<NoteArticle> getNtatDetail(HttpServletRequest request,HttpServletResponse response, Note cond){
		
		JsonResultBean<NoteArticle> ret = new JsonResultBean<NoteArticle>();
		String ntatId = request.getParameter("ntatId");
		List<NoteArticle> noteArticle = new ArrayList<NoteArticle>();
		NoteArticle ntat = null;
		if(StringUtils.isBlank(ntatId)){
			ntat = new NoteArticle();
			ntat.setRetCode("1001");
			ntat.setRetMsg("请求参数错误！");
			noteArticle.add(ntat);
			ret.setRows(noteArticle);
			return ret;
		}
		if(ntatId.contains(NoteArticle.SUFFIX_NOTE)){
			Note note = noteService.getNoteById(ntatId);
			if(note==null){
				ntat = new NoteArticle();
				ntat.setRetCode("1001");
				ntat.setRetMsg("帖子不存在或已被管理员删除，无法获取信息！");
				noteArticle.add(ntat);
				ret.setRows(noteArticle);
				return ret;
			}
			User user = userDbService.getUserById(note.getUserId());
			ntat = new NoteArticle(note,user);
			ret.setRetFlag(true);
		}
		if(ntatId.contains(NoteArticle.SUFFIX_ARTICLE)){
			Article article = noteService.getArticleById(ntatId);
			if(article==null){
				ntat = new NoteArticle();
				ntat.setRetCode("1001");
				ntat.setRetMsg("文章不存在或已被管理员删除，无法获取信息！");
				noteArticle.add(ntat);
				ret.setRows(noteArticle);
				return ret;
			}
			ntat = new NoteArticle(article);
			ret.setRetFlag(true);
		}
		noteArticle.add(ntat);
		ret.setRows(noteArticle);
		return ret;
	}
	
	/*********************  帖子文章一系列操作   ********************************/
	
	/**
	 * 更新操作： 置顶、取消置顶、加精、取消加精、移帖、删帖、发布、下撤
	 * @author Ted-wuhuhu
	 * @Time 2016年11月23日 上午11:54:37
	 * @param ntatId
	 * @return
	 */
	@RequestMapping("/opNtat")
	@ResponseBody
	public JsonResultBean<String> updateDetailInfo(HttpServletRequest request){
		JsonResultBean<String> ret = new JsonResultBean<String>();
		//操作类型，注释中对应的类型
		String opType = request.getParameter("opType");
		String ntatId = request.getParameter("ntatId");
		if(StringUtils.isBlank(opType) || StringUtils.isBlank(ntatId)){
			ret.setRetFlag(false);
			ret.setRetCode("1001");
			ret.setRetMsg("请求参数错误。");
			return ret;
		}
		int opFlag = 0;
		if(ntatId.contains(NoteArticle.SUFFIX_NOTE)){
			Note note = noteService.getNoteById(ntatId);
			if(note==null){
				ret.setRetFlag(false);
				ret.setRetCode("1002");
				ret.setRetMsg("帖子不存在或已被管理员删除，无法获取信息！");
				return ret;
			}
			opFlag = opNote(note,opType,request);
		}
		if(ntatId.contains(NoteArticle.SUFFIX_ARTICLE)){
			Article article = noteService.getArticleById(ntatId);
			if(article==null){
				ret.setRetFlag(false);
				ret.setRetCode("1002");
				ret.setRetMsg("文章不存在或已被管理员删除，无法获取信息！");
				return ret;
			}
			opFlag = opArticle(article,opType,request);
		}
		if(opFlag > 0){
			ret.setRetFlag(true);
			ret.setRetCode("1000");
			ret.setRetMsg("操作成功。");
			return ret;
		}
		ret.setRetFlag(false);
		if(opFlag == -1){
			ret.setRetCode("1002");
			ret.setRetMsg("该项状态与操作之前相同，请勿重复操作。");
		} else {
			ret.setRetCode("1003");
			ret.setRetMsg("操作失败，请稍后再试。");
		}
		return ret;
	}
	
	/**
	 * 对帖子的操作
	 * @author Ted-wuhuhu
	 * @Time 2016年11月23日 下午3:16:49
	 * @param note
	 * @param opType
	 * @param request
	 * @return
	 */
	private int opNote(Note note, String opType, HttpServletRequest request) {
		switch(opType){
			case "forceToTop"://置顶
				if("yes".equals(note.getTopFlag())){
					return -1;
				}
				note.setTopFlag("yes");
				note.setTopTime(new Date());
				break;
			case "cancelToTop"://取消置顶
				if("no".equals(note.getTopFlag())){
					return -1;
				}
				note.setTopFlag("no");
				break;
			case "forceToEssence"://加精
				if("yes".equals(note.getHotFlag())){
					return -1;
				}
				note.setHotFlag("yes");
				note.setHotTime(new Date());
				break;
			case "cancelToEssence"://取消加精
				if("no".equals(note.getHotFlag())){
					return -1;
				}
				note.setHotFlag("no");
				break;	
			case "deleteNtat"://删帖
				note.setStatus("deleted");
				note.setUpdateTime(new Date());
				break;
			case "changeCmty"://移帖
				try {
					Integer cmtyId = Integer.parseInt(request.getParameter("cmtyId"));
					note.setCmtyId(cmtyId);
					note.setCmtyName(InitDataCache.getCommunityById(cmtyId).getCmtyName());
				} catch (Exception e) {
					return -1;
				}
				break;
		}
		return noteService.changeNoteInfo(note);
	}
	
	/**
	 * 对帖子的操作
	 * @author Ted-wuhuhu
	 * @Time 2016年11月23日 下午3:16:49
	 * @param note
	 * @param opType
	 * @param request
	 * @return
	 */
	private int opArticle(Article article, String opType, HttpServletRequest request) {
		switch(opType){
		case "forceToTop"://置顶
			if("yes".equals(article.getTopFlag())){
				return -1;
			}
			article.setTopFlag("yes");
			article.setTopTime(new Date());
			break;
		case "cancelToTop"://取消置顶
			if("no".equals(article.getTopFlag())){
				return -1;
			}
			article.setTopFlag("no");
			break;
		case "forceToEssence"://加精
			if("yes".equals(article.getHotFlag())){
				return -1;
			}
			article.setHotFlag("yes");
			article.setHotTime(new Date());
			break;
		case "cancelToEssence"://取消加精
			if("no".equals(article.getHotFlag())){
				return -1;
			}
			article.setHotFlag("no");
			break;	
		case "deleteNtat"://删帖
			article.setStatus("deleted");
			article.setUpdateTime(new Date());
			break;
		case "changeCmty"://移帖
			try {
				Integer cmtyId = Integer.parseInt(request.getParameter("cmtyId"));
				article.setCmtyId(cmtyId);
				article.setCmtyName(InitDataCache.getCommunityById(cmtyId).getCmtyName());
			} catch (Exception e) {
				return -1;
			}
			break;
		}
		return noteService.changeArticleInfo(article);
	}

}
