package com.joosure.manager.mvc.wechat.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;
import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.service.SysConfParamService;
import com.joosure.manager.mvc.wechat.service.SysUserService;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private SysConfParamService sysConfParamService;
	@Autowired
	private SysUserService	sysUserService;
	
	@RequestMapping("/home")
	public String login(@Param("loginId")String loginId,@Param("loginPass")String loginPass,
			HttpServletResponse response,HttpServletRequest request){
		//先检查是否存在该id，若不存在则提示账号或密码错误；若存在则取得盐和密文密码，并且进行加密比较
		response.setCharacterEncoding("utf8");
		SysUser sysUser = sysUserService.getUserById(loginId);
		if(sysUser != null){
			try {
				String secretPass = ManagerUtils.encryptBySHA256(loginPass, sysUser.getSalt());
				if(sysUser.getLoginpass().equals(secretPass)){
					return "home";
				}
			} catch (NoSuchAlgorithmException e) {
				log.error(e.getMessage()+e.getStackTrace());
			}
		}
		request.setAttribute("prompt", "账号或密码不正确");
		return "login";
	}
	
	@RequestMapping("/homeWithoutLogin")
	public String login(){
		return "home";
	}
	
	@RequestMapping("/sysConfParamInit")
	public String toConfParam(){
		return "sysConfParamManage";
	}
	
	@RequestMapping("/showParamsInfo")
	@ResponseBody
	public JsonResultBean<SysConfParam> showParamsInfo(@Param("limit") int limit,@Param("offset")int offset){
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("startIndex", offset);
		cond.put("limit", limit);
		List<SysConfParam> data = sysConfParamService.getParamsInfo(cond);
		int count = sysConfParamService.getParamsCount(cond);
		JsonResultBean<SysConfParam> ret = new JsonResultBean<SysConfParam>(data,count);
		return ret;
	}
}
