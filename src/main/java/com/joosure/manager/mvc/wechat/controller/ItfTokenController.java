package com.joosure.manager.mvc.wechat.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.ItfTokenApp;
import com.joosure.manager.mvc.wechat.bean.ItfTokenLog;
import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.service.ItfTokenService;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.service.db.IDictDbService;

/**
 * @author Administrator
 * 
 *                    _oo8oo_                      
 *                   o8888888o                     
 *                   88" . "88                     
 *                   (| -_- |)                     
 *                   0\  =  /0                     
 *                 ___/'==='\___                   
 *               .' \\|     |// '.                 
 *              / \\|||  :  |||// \                
 *             / _||||| -:- |||||_ \               
 *             |   | \\\  -  /// |   |             
 *             | \_|  ''\---/''  |_/ |             
 *             \  .-\__  '-'  __/-.  /             
 *           ___'. .'  /--.--\  '. .'___           
 *        ."" '<  '.___\_<|>_/___.'  >' "".        
 *        | | :  `- \`.:`\ _ /`:.`/ -`  : | |      
 *        \  \ `-.   \_ __\ /__ _/   .-` /  /      
 *    =====`-.____`.___ \_____/ ___.`____.-`=====  
 *                      `=---=`                    
 *	    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 *
 *           佛祖保佑         永不宕机/永无bug
 */
@Controller
@RequestMapping("/itfToken")
public class ItfTokenController {
	
	private static Logger log = Logger.getLogger(ItfTokenController.class);
	
	@Autowired
	private ItfTokenService itfTokenService;
	@Autowired
	private IDictDbService dictDbService;
	
	@RequestMapping("/toItfToken")
	public String toManagePage(){
		return "itfTokenManage";
	}
	
	@RequestMapping("/toItfTokenLog")
	public String toLogManagePage(){
		return "itfTokenLogManage";
	}
	
	@RequestMapping("/listTokenApp")
	@ResponseBody
	public JsonResultBean<ItfTokenApp> listTokenApp(ItfTokenApp cond){
		JsonResultBean<ItfTokenApp> ret = new JsonResultBean<ItfTokenApp>();
		List<ItfTokenApp> data = itfTokenService.getTokenAppByPage(cond);
		int count = itfTokenService.getTokenAppCountByPage(cond);
		ret.setRows(data);
		ret.setTotal(count);
		return ret;
	}
	@RequestMapping("/listTokenAppLog")
	@ResponseBody
	public JsonResultBean<ItfTokenLog> listItfTokenLog(ItfTokenLog cond){
		JsonResultBean<ItfTokenLog> ret = new JsonResultBean<ItfTokenLog>();
		List<ItfTokenLog> data = itfTokenService.getTokenLogByPage(cond);
		int count = itfTokenService.getTokenLogCount(cond);
		ret.setRows(data);
		ret.setTotal(count);
		return ret;
	}
	
	
	
	@RequestMapping("/addTokenApp")
	@ResponseBody
	public JsonResultBean<String> addTokenApp(ItfTokenApp cond,HttpServletRequest req){
		JsonResultBean<String> ret = new JsonResultBean<String>(false,null);
		if(cond!=null){
			String applyName = cond.getApplyName();
			String appId = null;
			if(StringUtils.isBlank(applyName)){
				ret.setRetMsg("传入的申请者名称为空，无法生成 appid ");
				return ret;
			}else{
				appId = ManagerUtils.encryptAppId(applyName);
			}
			if(StringUtils.isBlank(appId)){
				ret.setRetMsg("生成 appid 失败，请联系维护人员或稍后再试");
				log.warn("生成 appid 失败");
				return ret;
			}
			cond.setAppId(appId);
			SysUser admin = (SysUser) req.getSession().getAttribute(CommonConstant.CurrentSysUser);
			cond.setOprId(admin.getId());
			cond.setOprLoginId(admin.getLoginid());
			int count = itfTokenService.addTokenApp(cond);
			if(count > 0){
				ret.setRetFlag(true);
				ret.setRetMsg("生成 appid 成功,请牢记： "+appId);
				return ret;
			}
		}
		ret.setRetMsg("生成 appid 失败,传入值非法!");
		return ret;
	}
	
	@RequestMapping("/deleteTokenApp")
	@ResponseBody
	public JsonResultBean<String> forbidAppId(ItfTokenApp cond,HttpServletRequest req){
		JsonResultBean<String> ret = new JsonResultBean<String>(false,null);
		if(cond == null){
			ret.setRetMsg("传入值非法");
			return ret; 
		}
		List<ItfTokenApp> data = itfTokenService.getTokenAppByPage(cond);
		if(data!= null && data.size()>0){
			data.get(0).setStatus("deleted");//当为删除时，置 status 为 deleted
			data.get(0).setInvalidTime(new Date());
			int re = itfTokenService.updateTokenApp(data.get(0));
			if(re>0){
				ret.setRetMsg("删除成功！");
				ret.setRetFlag(true);
			}else{
				log.warn("禁用appId失败,appId:"+cond.getAppId());
				ret.setRetMsg("删除失败，请联系维护人员！");
			}
		}
		return ret;
	}
	
}
