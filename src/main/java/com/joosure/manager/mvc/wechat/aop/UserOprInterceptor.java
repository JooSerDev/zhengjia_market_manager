package com.joosure.manager.mvc.wechat.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.joosure.manager.mvc.wechat.service.SysUserOprService;

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
		oprUrl.append(request.getScheme() + "://");
		oprUrl.append(request.getRemoteHost() + ":");
		oprUrl.append(request.getRemotePort());
		oprUrl.append(request.getContextPath());
		oprUrl.append(request.getServletPath() + "?");
		oprUrl.append(request.getQueryString());
		String ip = request.getRemoteAddr();
		System.out.println(oprUrl.toString());
		System.out.println(ip);
		Enumeration<String> paramNames = request.getParameterNames();
		StringBuffer oprParam = new StringBuffer();
		String paramName = "";
		String paramValue = "";
		while (paramNames.hasMoreElements()) {
			paramName = paramNames.nextElement();
			paramValue = request.getParameter(paramName);
			oprParam.append(paramName+"="+paramValue+"&");
		}
		System.out.println(oprParam);
		return true;
	}

}
