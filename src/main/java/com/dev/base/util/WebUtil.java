package com.dev.base.util;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.LoginType;
import com.dev.user.service.LoginService;
import com.dev.user.vo.LoginParamInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: web相关工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:06:36</p>
 */
public class WebUtil {
	/**
	 * 获取请求参数
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getParamStr(HttpServletRequest request) {
		StringBuffer sbArgs = new StringBuffer();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);

			sbArgs.append(paramName);
			sbArgs.append("=");
			sbArgs.append(paramValue);
			sbArgs.append("&");
		}

		if (sbArgs.length() > 0) {
			sbArgs.deleteCharAt(sbArgs.length() - 1);
		}

		return sbArgs.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static void setParam(HttpServletRequest request, ModelAndView mv) {
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			mv.addObject(paramName, paramValue);
		}

	}
	
	/**
	 * 添加session属性
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setSessionAttr(HttpServletRequest request,String key,Object value){
		request.getSession().setAttribute(key, value);
	}
	
	/**
	 * 移除session属性
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void removeSessionAttr(HttpServletRequest request,String key){
		request.getSession().removeAttribute(key);
	}
	
	/**
	 * 添加session属性
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getSessionAttr(HttpServletRequest request,String key){
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * 重定向
	 * 采用绝对路径，防止相对路径无法访问问题
	 * @param url
	 */
	public static String getRedirectUrl(String url){
		return getRedirectUrl(url, null);
	}
	
	/**
	 * 重定向
	 * 采用绝对路径，防止相对路径无法访问问题
	 * @param url
	 */
	public static String getRedirectUrl(String url,Map<String, Object> paramMap){
		//项目名称
		StringBuilder path = new StringBuilder();
		path.append("redirect:")
//			.append(CfgConstants.WEB_CONTEXT_PATH)
			.append(url);
		if (!CollectionUtils.isEmpty(paramMap)) {
			path.append("?");
			Iterator<String> keyIterator = paramMap.keySet().iterator();
			String key = "";
			try {
				while (keyIterator.hasNext()) {
					key = keyIterator.next();
					path.append(key).append("=")
					    .append(URLEncoder.encode(paramMap.get(key).toString(),"UTF-8"))
					    .append("&");
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return path.substring(0, path.length() - 1);
		}
		
		return path.toString();
	}
	
	/**
	 * 获取经过nginx转发后的用户真实ip
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request){
		String clientIp = request.getHeader(AppConstants.NGINX_REMOTE_ADDR);
		return clientIp == null ? request.getRemoteAddr() : (String)clientIp;
	}
	
	/**
	 * 
			*@name 判断是否是ajax请求
			*@Description  
			*@CreateDate 2015年10月12日下午7:26:37
	 */
	public static boolean isAjaxReq(HttpServletRequest request){
		return !StringUtils.isEmpty(request.getHeader("X-Requested-With"));
	}
	
	/**
	 * 
			*@name 写入cookie
			*@Description  
			*@CreateDate 2015年11月30日下午5:06:06
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int expire){
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expire);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 
			*@name 删除cookie
			*@Description  
			*@CreateDate 2015年11月30日下午5:06:06
	 */
	public static void delCookie(HttpServletResponse response,String name){
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(-1);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 
			*@name 获取cookie值
			*@Description  
			*@CreateDate 2015年11月30日下午5:06:06
	 */
	public static String getCookieValue(HttpServletRequest request,String name){
		String value = "";
		if (StringUtils.isEmpty(name)) {
			return value;
		}
		
		// 获取所有的cookie值
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					value = cookie.getValue();
					break;
				}
			}
		}
		  
		return value;
	}
	
	/**
	 * 
			*@name 获取用户信息
			*@Description  
			*@CreateDate 2015年11月30日下午6:38:28
	 */
	public static UserInfo getUserInfo(HttpServletRequest request,LoginService loginService){
		UserInfo userInfo = (UserInfo)WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_USER);
		String token = WebUtil.getCookieValue(request, CfgConstants.COOKIE_TOKEN_NAME);
		if (userInfo == null && !StringUtils.isEmpty(token)) {
			LoginParamInfo loginParamInfo = new LoginParamInfo();
			loginParamInfo.setAutoLogin(false);
			loginParamInfo.setLoginIp(WebUtil.getClientIp(request));
			loginParamInfo.setLoginType(LoginType.token);
			loginParamInfo.setToken(token);
			
			userInfo = loginService.loginByToken(loginParamInfo);
			//保存登陆用户信息
			WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_USER, userInfo);
		}
		
		return userInfo;
	}
}
