package com.yam.portal.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yam.common.utils.CookieUtils;
import com.yam.pojo.TbUser;
import com.yam.portal.service.UserService;
import com.yam.portal.service.impl.UserServiceImpl;

/**
 * 
 * 拦截用户请求，强制登录
 * @author Yampery
 * @date 2017年3月28日 下午10:18:12
 */

public class LoginInterceptor implements HandlerInterceptor {
	
	// 注入UserService服务资源
	@Resource private UserServiceImpl service;

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		
		// 在Handler执行之前处理
		// 返回的boolean值决定Handler是否执行
		
		// 1. 判断用户是否登录---从cookie中取token
		String token = CookieUtils.getCookieValue(req, "YAM_TOKEN");
		
		// 2. 根据token获取用户信息
		TbUser user = service.getUserByToken(token);
		if (null == user) {
			// 如果娶不到用户信息，跳转登录（将请求的页面作为参数）
			resp.sendRedirect(service.SSO_BASE_URL +
					service.SSO_PAGE_LOGIN + "?redirect" + req.getRequestURL());
			// 返回false--表示要拦截
			return false;
		}	
			// 如果取到用户信息，放行
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object arg2, Exception arg3)
			throws Exception {
		
		// 在Handler执行之后，返回ModelView之前执行

	}

}















