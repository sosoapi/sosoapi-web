package com.dev.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.Role;
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
public class PrivilegeInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LogManager.getLogger(PrivilegeInterceptor.class);

	@Autowired
	private LoginService loginService;
	
	//需要授权的操作
	private String[] authOperArray = {"/json/add.htm","/json/update.htm","/json/del.htm"};
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String reqUri = request.getRequestURI();
		logger.debug(reqUri);
		UserInfo userInfo = WebUtil.getUserInfo(request,loginService);
		if (userInfo == null) {
			throw new SessionTimeoutException();
		}
		
		//验证是否是httpclient请求爬数据
		validHttpClient(request);
		
		//TO-DO 网站稳定后需要屏蔽该功能
		//管理员可对api文档预览和下载，方便帮用户排错
		if (userInfo.getRole() == UserRole.admin 
				&& handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			String className = handlerMethod.getBean().getClass().getSimpleName();
			if ("SwaggerController".equals(className)) {
				return true;
			}
		}
		
		//项目相关授权
		authForProject(request, userInfo,reqUri);
		//文档相关授权
		authForDoc(request, userInfo,reqUri);
		
		return super.preHandle(request, response, handler);
	}
	
	//验证是否是httpclient爬数据
	private void validHttpClient(HttpServletRequest request){
		//减少httpclient爬数据
		if (WebUtil.isAjaxReq(request)) {
			String sysReqToken = request.getHeader("sysReqToken");
			if (!CfgConstants.SYS_REQ_TOKEN.equals(sysReqToken)) {
				logger.error("bad req without sys req token:");
				throw new SessionTimeoutException();
			}
		}
	}
	
	//项目相关权限验证
	private void authForProject(HttpServletRequest request,UserInfo userInfo,String reqUri){
		if (StringUtils.isEmpty(request.getParameter("projId"))) {
			return ;
		}
		
		Long projId = Long.parseLong(request.getParameter("projId"));
		Role role = userInfo.getRoleByProjId(projId);
		if (role == null) {
			throw new AuthException();
		}
		
		authForOper(role,reqUri);
	}
	
	//文档相关权限验证
	private void authForDoc(HttpServletRequest request,UserInfo userInfo,String reqUri){
		if (StringUtils.isEmpty(request.getParameter("docId"))) {
			return ;
		}
		
		Long docId = Long.parseLong(request.getParameter("docId"));
		Role role = userInfo.getRoleByDocId(docId);
		if (role == null) {
			throw new AuthException();
		}
		
		authForOper(role,reqUri);
	}
	
	//操作相关权限
	private void authForOper(Role role,String reqUri){
		for (String oper : authOperArray) {
			if (role != Role.admin && reqUri.endsWith(oper)) {
				throw new AuthException();
			}
		}
	}
}
