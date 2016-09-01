package com.joosure.manager.mvc.wechat.aop;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.bean.SysUserOpr;
import com.joosure.manager.mvc.wechat.service.SysUserOprService;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.shawn.server.core.http.RequestHandler;
import com.shawn.server.core.util.SpringUtil;

@Component
public class SysFilter implements Filter {

	private Logger log = Logger.getLogger(SysFilter.class);

	@Autowired
	private SysUserOprService sysUserOprService;

	@Override
	public void destroy() {
		log.info("---destroy---");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (sysUserOprService == null) {
			sysUserOprService = SpringUtil.getBean("sysUserOprService");
		}
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
//		this.getUrlMapping(request);
		String path = request.getServletPath();
		String async = request.getHeader("x-requested-with");
		if ("XMLHttpRequest".equals(async)) {
//			log.info("正在访问：" + request.getServletPath()+" 该请求为异步请求");
		}
		
		
		
		SysUserOpr userOpr = new SysUserOpr();
		userOpr.setOprurl(request.getContextPath() + request.getServletPath());
		userOpr.setMemo("ip:" + RequestHandler.getIpAddr(request));
		userOpr.setOprparam(request.getQueryString());

		if (isExclude(path)) {
			try {
				sysUserOprService.insertUserOpr(userOpr);
			} catch (Exception e) {
				log.warn("插入请求记录失败");
			}
			chain.doFilter(request, response);
			return;
		}
		SysUser user = (SysUser) request.getSession().getAttribute(CommonConstant.CurrentSysUser);
		if (user != null || path.contains("login")) {
			if (user != null) {
				userOpr.setUserid(user.getId());
			}
			try {
				sysUserOprService.insertUserOpr(userOpr);
			} catch (Exception e) {
				log.warn("插入请求记录失败");
			}
			chain.doFilter(request, response);
		} else {
			try {
				sysUserOprService.insertUserOpr(userOpr);
			} catch (Exception e) {
				log.warn("插入请求记录失败");
			}
			// response.sendRedirect(request.getContextPath()+"/temp.jsp");
			/*
			 * if("XMLHttpRequest".equals(async)){ JsonResultBean<String> ret =
			 * new JsonResultBean<String>(); List<String> data = new
			 * ArrayList<String>(); data.add("1001"); ret.setResultList(data);
			 * response.getWriter().write(JSON.toJSONString(ret)); return ; }
			 */
			request.getRequestDispatcher("/admin/loginout").forward(request, response);
			return;
		}

	}

	@Override
	public void init(FilterConfig filterConf) throws ServletException {
		log.info("---init---");
	}

	public boolean isExclude(String path) {
		if (path.endsWith(".png") || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jpg") || path.endsWith("temp.jsp")) {
			return true;
		}
		return false;
	}

	public void getUrlMapping(HttpServletRequest request) {
		WebApplicationContext wc = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		RequestMappingHandlerMapping rmhp = wc.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
		for (RequestMappingInfo info : map.keySet()) {
			System.out.println(info.getPatternsCondition().toString() + "," + map.get(info).getBean().toString());
		}
	}

}
