package com.dev.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dev.base.enums.UserRole;
import com.dev.base.exception.AuthException;
import com.dev.base.exception.SessionTimeoutException;
import com.dev.base.util.WebUtil;
import com.dev.user.service.LoginService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 权限拦截器</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:00:35</p>
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LogManager.getLogger(AdminInterceptor.class);

	@Autowired
	private LoginService loginService;
	
	//登录请求
//	private static String LOGIN_CONTROLLER = "LoginController";
		
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug(request.getRequestURI());
		
		//过滤登陆相关请求
//		if (handler instanceof HandlerMethod) {
//			HandlerMethod handlerMethod = (HandlerMethod)handler;
//			//读取拦截下来的 class 名字
//			String className = handlerMethod.getBean().getClass().getSimpleName();
//			//过滤掉登录请求
//			if (className.equals(LOGIN_CONTROLLER)) {
//				return true;
//			}
//		}
		
		//判断是否登陆过期
		UserInfo userInfo = WebUtil.getUserInfo(request,loginService);
		if (userInfo == null) {
			throw new SessionTimeoutException();
		}
		
		//判断当前是否是管理员角色
		if (userInfo.getRole() != UserRole.admin) {
			throw new AuthException();
		}
		
		return super.preHandle(request, response, handler);
	}
}
