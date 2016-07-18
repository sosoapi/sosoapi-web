package com.dev.base.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.constant.AppConstants;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.MapUtils;
import com.dev.user.service.AuthService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: controller基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:02:25</p>
 */
public class BaseController {
	@Autowired
	private AuthService authService;
	
	/**
	 * 
			*@name 获取请求参数
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:14
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String> parseReqParam(HttpServletRequest request) {
		Map<String, String> result = MapUtils.newMap();
		Enumeration<String> names = request.getParameterNames();
		String key = "";
		String value = "";
		while (names.hasMoreElements()) {
			key = names.nextElement();
			value = request.getParameter(key);
			result.put(key, value);
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 从缓存中获取用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:25
	 */
	protected UserInfo getUserInfo(HttpServletRequest request){
		UserInfo userInfo = (UserInfo)WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_USER);
		
		return userInfo;
	}
	
	/**
	 * 
			*@name 从缓存中获取登录用户的id
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:35
	 */
	protected Long getUserId(HttpServletRequest request) {
		UserInfo userInfo = getUserInfo(request);
		return userInfo == null ? null : userInfo.getUserId(); 
	}
	
	/**
	 * 
			*@name 保存用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:08:45
	 */
	protected void saveUserInfo(HttpServletRequest request,UserInfo userInfo) {
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_USER, userInfo);
	}
	
	/**
	 * 
			*@name 移除用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:09:27
	 */
	protected void removeUserInfo(HttpServletRequest request) {
		WebUtil.removeSessionAttr(request, AppConstants.SESSION_KEY_USER);
	}
	
	/**
	 * 
			*@name 获取用户实际ip地址
			*@Description  
			*@CreateDate 2015年9月1日下午8:55:53
	 */
	protected  String getClientIp(HttpServletRequest request){
		return WebUtil.getClientIp(request);
	}
	
	/**
	 * 
			*@name 重新加载项目权限
			*@Description  
			*@CreateDate 2015年9月2日下午10:21:13
	 */
	protected void reloadProjAuth(HttpServletRequest request){
		UserInfo userInfo = getUserInfo(request);
		authService.loadProjAuth(userInfo);
		saveUserInfo(request, userInfo);
	}
}
