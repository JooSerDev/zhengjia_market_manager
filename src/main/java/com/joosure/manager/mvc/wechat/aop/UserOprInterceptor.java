package com.joosure.manager.mvc.wechat.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.service.SysUserOprService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;

public class UserOprInterceptor implements HandlerInterceptor {

	private static Logger log = Logger.getLogger(UserOprInterceptor.class);

	@Autowired
	private SysUserOprService sysUserOprService;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exp) throws Exception {
		log.info("********* afterCompletion **********");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView view) throws Exception {
		if (view != null) {
			log.info("转入视图"+view.getViewName()+".jsp");
		}
		log.info("********* postHandle **********");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		log.info("********* preHandle **********");
		StringBuffer oprUrl = new StringBuffer();
		String webSite = "";//项目路径 ，类似于  http://localhost:8080/server-mvc-manager/
		oprUrl.append(request.getScheme() + "://");
		oprUrl.append(request.getRemoteHost() + ":");
		oprUrl.append(request.getContextPath());
		webSite = oprUrl.toString();//
		log.info("项目路径:"+webSite);
		log.info("请求路径："+request.getServletPath());
		log.info("路径："+request.getRequestURL());
		oprUrl.append(request.getServletPath() + "?");
		oprUrl.append(request.getQueryString());
		String ip = request.getRemoteAddr();
		log.info(oprUrl.toString());
		log.info(ip);
		Enumeration<String> paramNames = request.getParameterNames();
		StringBuffer oprParam = new StringBuffer();
		String paramName = "";
		String paramValue = "";
		while (paramNames.hasMoreElements()) {
			paramName = paramNames.nextElement();
			paramValue = request.getParameter(paramName);
			oprParam.append(paramName+"="+paramValue+"&");
		}
		log.info(oprParam);
		//判断是否存在用户
		if(("/admin/loginout").equals(request.getServletPath())
			||	("/admin/login").equals(request.getServletPath())
			|| ("/admin/homeWithoutLogin").equals(request.getServletPath())){
			//执行相应操作
			log.info("----登录登出操作不做用户过滤----");
			return true;
		}else{
			SysUser sysUser = (SysUser) request.getSession().getAttribute(CommonConstant.CurrentSysUser);
			if(sysUser!=null){
				return true;
			}else{
				log.info(request.getContextPath()+"/login.jsp");
				request.getRequestDispatcher("temp.jsp").forward(request, response);
				return true;
			}
//			request.getRequestDispatcher("/admin/login").forward(request, response);
//			return false;
		}
		
	}

}
