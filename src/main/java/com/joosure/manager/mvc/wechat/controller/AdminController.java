package com.joosure.manager.mvc.wechat.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;
import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.service.ISysUserDbService;
import com.joosure.manager.mvc.wechat.service.SysConfParamService;
import com.joosure.manager.mvc.wechat.service.db.ISysConfParamDbService;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.dao.cache.DictsCache;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private SysConfParamService sysConfParamService;
	@Autowired
	private ISysConfParamDbService sysConfParamDbService;
	
	@Autowired
	private ISysUserDbService sysUserService;
	@Autowired
	private ISysUserDbService sysUserDbService;
	
	

	/**
	 * 管理员登陆，成功则跳转至主页
	 * 
	 * @param loginId
	 * @param loginPass
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@RequestParam("loginId") String loginId, @RequestParam("loginPass") String loginPass, HttpServletResponse response,
			HttpServletRequest request) {
		// 先检查是否存在该id，若不存在则提示账号或密码错误；若存在则取得盐和密文密码，并且进行加密比较
		response.setCharacterEncoding("utf8");
		SysUser sysUser = sysUserService.getUserById(loginId);
		if (sysUser != null) {
			SysUser loginUser = (SysUser) request.getSession().getAttribute(CommonConstant.CurrentSysUser);
			if (loginUser != null) {
				if (sysUser.getLoginid().equals(loginUser.getLoginid())) {
					//return "redirect:/admin/home";
					return "redirect:../tempHome.jsp";
				}
			}
			try {
				String secretPass = ManagerUtils.encryptBySHA256(loginPass, sysUser.getSalt());
				if (sysUser.getLoginpass().equals(secretPass)) {
					request.getSession().setAttribute(CommonConstant.CurrentSysUser, sysUser);
					//					return "redirect:/admin/home";
					return "redirect:../tempHome.jsp";
				}
			} catch (NoSuchAlgorithmException e) {
				log.error(e.getMessage());
			}
		}
		request.setAttribute("prompt", "账号或密码错误，请查验后重试！");
		return "login";
	}

	@RequestMapping("/tempHome")
	public String toHomePage(){
		//		return "home";
		return "redirect:../tempHome.jsp";
	}

	@RequestMapping("/home")
	public String toHome(){
		return "home";
	}

	@RequestMapping("/homeWithoutLogin")
	public String login() {
		return "home";
	}

	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest request, HttpSession session) {
		clearSession(session);
		return "redirect:../temp.jsp";
	}

	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request, HttpSession session) {
		clearSession(session);
		return "redirect:../login.jsp";
	}
	/**
	 * 跳转至系统参数查询页
	 * 
	 * @return
	 */
	@RequestMapping("/sysConfParamInit")
	public String toConfParam() {
		// return "sysConfParamManage";
		return "sysConfParamList";
	}

	/**
	 * 展示系统参数配置列表
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/showParamsInfo")
	@ResponseBody
	public JsonResultBean<SysConfParam> showParamsInfo(SysConfParam cond) {
		List<SysConfParam> data = sysConfParamDbService.getParamsInfo(cond);
		int count = sysConfParamDbService.getParamsCount(cond);
		JsonResultBean<SysConfParam> ret = new JsonResultBean<SysConfParam>(data, count);
		return ret;
	}

	/**
	 * 跳转至系统用户查询页面
	 * 
	 * @return
	 */
	@RequestMapping("/sysUserManage")
	public String toSysUserManage() {
		return "sysUserManage";
	}

	/**
	 * 展示系统用户列表
	 * 
	 * @param qryCond
	 * @return
	 */
	@RequestMapping("/sysUserList")
	@ResponseBody
	public JsonResultBean<SysUser> listSysUserInfo(QryCondBean qryCond) {
		List<SysUser> data = sysUserService.getUserList(qryCond);
		int count = sysUserService.getUserListCount(qryCond);
		JsonResultBean<SysUser> ret = new JsonResultBean<SysUser>(data, count);
		return ret;
	}

	/**
	 * 添加系统用户
	 * 
	 * @param loginidA
	 * @param loginnameA
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addSysUser")
	public String addSysUser(@RequestParam("loginidA") String loginidA, @RequestParam("loginnameA") String loginnameA, @RequestParam("extdA") String extdA) {
		SysUser user = new SysUser();
		user.setLoginid(loginidA);
		user.setLoginname(loginnameA);
		user.setExtd(extdA);
		user.setLoginpass(ManagerUtils.getDefaultPass(user.getSalt()));
		user.setSalt(UUID.randomUUID().toString());
		sysUserService.addSysUser(user);
		return "forward:/admin/sysUserManage";
	}

	/**
	 * 删除系统用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/deleteSysUser")
	@ResponseBody
	public JsonResultBean<String> deleteSysUser(SysUser user) {
		JsonResultBean<String> ret = new JsonResultBean<String>();
		if(user!=null){
			user.setStatus(0);
			int retCount = sysUserService.deleteSysUser(user);
			if (retCount > 0) {
				ret.setRetFlag(true);
				ret.setRetMsg("用户已删除成功");
			}else{
				ret.setRetFlag(false);
				ret.setRetMsg("用户删除失败，请稍后重试或联系维护人员查看相关数据");
			}
		}
		return ret;
	}

	/**
	 * 修改系统用户：重置密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/changeSysUser")
	@ResponseBody
	public JsonResultBean<String> updateSysUser(SysUser user) {
		if ("null".equals(user.getSalt())) {
			user.setSalt(null);
		}
		user.setLoginpass(ManagerUtils.getDefaultPass(user.getSalt()));
		int retCount = sysUserService.changeSysUser(user);
		JsonResultBean<String> ret = new JsonResultBean<String>();
		if (retCount > 0) {
			ret.setRetFlag(true);
			ret.setRetMsg("该用户密码重置成功");
		}else{
			ret.setRetFlag(false);
			ret.setRetMsg("重置密码失败，请稍后重试或联系维护人员查看相关数据");
		}
		return ret;
	}

	/**
	 * 添加系统参数配置
	 * 
	 * @param paramGroupA
	 * @param paramIdA
	 * @param paramNameA
	 * @param paramDescA
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addSysConfParam")
	public String addSysConfParam(@RequestParam("paramGroupA") String paramGroupA, @RequestParam("paramIdA") String paramIdA,
			@RequestParam("paramNameA") String paramNameA, @RequestParam("paramDescA") String paramDescA, HttpServletRequest request) {
		SysConfParam conf = new SysConfParam();
		conf.setParamgroup(paramGroupA);
		conf.setParamid(paramIdA);
		conf.setParamname(paramNameA);
		conf.setParamdesc(paramDescA);
		int retCount = sysConfParamService.saveConfParam(conf);
		if (retCount != 1) {
			request.setAttribute("savePrompt", "添加失败，已存在");
		}
		return "forward:/admin/sysConfParamInit";
	}

	/**
	 * 跳转至修改系统用户信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toChgSysUserInfo")
	public String toChgSysUserInfo(HttpSession session) {
		/*SysUser sysUser = (SysUser) session.getAttribute(CommonConstant.CurrentSysUser);
		if (sysUser == null) {
			clearSession(session);// 清空session
			return "login";
		}*/
		return "chgSysUser";
	}

	/**
	 * 修改密码
	 * 
	 * @param session
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/chgSysUserInfo")
	public String changeSysUserInfo(HttpServletRequest request, @RequestParam("loginPass") String loginPass, @RequestParam("oldPass") String oldPass) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute(CommonConstant.CurrentSysUser);
		if (sysUser != null) {
			try {
				if (oldPass != null && !"".equals(oldPass)) {
					String oldEncryptPass = ManagerUtils.encryptBySHA256(oldPass, sysUser.getSalt());
					if (oldEncryptPass.equals(sysUser.getLoginpass())) {
						String newEncryptPass = ManagerUtils.encryptBySHA256(loginPass, sysUser.getSalt());
						if (newEncryptPass.equals(sysUser.getLoginpass())) {
							request.setAttribute("prompt", "新密码与旧密码不能相同，请重新填写");
							return "chgSysUser";
						}
						sysUser.setLoginpass(newEncryptPass);
					} else {
						request.setAttribute("prompt", "原密码输入不正确，请重新填写");
						return "chgSysUser";
					}
				}
			} catch (NoSuchAlgorithmException e) {
				// e.printStackTrace();
				sysUser.setLoginpass(ManagerUtils.getDefaultPass(sysUser.getSalt()));
			}
			sysUserService.changeSysUser(sysUser);
			request.getSession().setAttribute(CommonConstant.CurrentSysUser, sysUser);
		}
		// clearSession(session);
		request.setAttribute("prompt", "密码修改成功");
		return "welcome";
	}

	/**
	 * 修改系统参数： 改状态
	 * 
	 * @param paramGroupA
	 * @param paramIdA
	 * @return
	 */
	@RequestMapping(value = "/changeSysConfParam")
	@ResponseBody
	public JsonResultBean<String> changeSysConfParam(@RequestParam("paramGroupA") String paramGroupA, @RequestParam("paramIdA") String paramIdA) {
		SysConfParam sysConfParam = new SysConfParam();
		sysConfParam.setParamgroup(paramGroupA);
		sysConfParam.setParamid(paramIdA);
		sysConfParam.setStatus(0);
		int retCount = sysConfParamDbService.changeSysConfParam(sysConfParam);
		JsonResultBean<String> ret = new JsonResultBean<String>(true, null);
		if (retCount != 1) {
			ret.setRetFlag(false);
			ret.setRetMsg("删除失败，请联系管理员检查");
		}
		return ret;
	}

	/**
	 * 修改系统参数
	 * 
	 * @param paramGroupA
	 * @param paramIdA
	 * @return
	 */
	@RequestMapping(value = "/chgConfParamInfo")
	public String chgConfParamInfo(SysConfParam sysConfParam,HttpServletRequest request) {
		if (sysConfParam != null) {
			int retCount = sysConfParamDbService.changeSysConfParam(sysConfParam);
			//更新缓存中的数据
			DictsCache.refreshGroup(sysConfParam.getParamgroup());
			if (retCount > 0) {

			}else{
				request.setAttribute("chgConfParamInfoPrompt", "修改字典配置信息出错，请联系管理员检查");
			}
		}
		return "sysConfParamList";
	}

	/**
	 * 清空session
	 * 
	 * @param session
	 */
	private void clearSession(HttpSession session) {
		Enumeration<String> em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
	}

}
