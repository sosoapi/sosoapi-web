package com.dev.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.base.constant.CfgConstants;

/**
 * 
		* <p>Title: cookie工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月2日下午5:33:32</p>
 */
public class CookieUtil {
	/**
	 * cookie信息保存在浏览器内存中，关闭后自动清除
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name,String value) {
		setCookie(response, name, value, -1);
	}

	/**
	 * 设置cookie信息
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletResponse response, String name,
									String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(CfgConstants.COOKIE_DOMAIN);
		cookie.setPath("/");
		if (maxAge >= 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * 清除cookie
	 * @param response
	 * @param name
	 */
	public static void removeCookie(HttpServletResponse response, String name) {
		setCookie(response, name, null,0);
	}
	
	/**
	 * 获取cookie信息
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (name.equals(c.getName())) {
					return c;
				}
			}
		}

		return null;
	}
	
	/**
	 * 获取cookie信息
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie == null ? "" : cookie.getValue();
	}
}
