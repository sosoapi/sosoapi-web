package com.dev.base.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 请求日志拦截器</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月24日下午4:17:24</p>
 */
public class LogFilter implements Filter{
	private static Logger logger = LogManager.getLogger(LogFilter.class);
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse)response;
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//		resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, "
//				+ "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, "
//				+ "Content-Type, X-E4M-With");
//		resp.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");  
//		resp.addHeader("Access-Control-Allow-Credentials", "true");
		
		HttpServletRequest req = (HttpServletRequest) request;  
		String uri = req.getRequestURI();
		Enumeration<String> names = request.getParameterNames();
		String key = "";
		String value = "";
		Map<String, String> paramMap = MapUtils.newMap();
		while (names.hasMoreElements()) {
			key = names.nextElement();
			value = request.getParameter(key);
			paramMap.put(key, value);
		}
		
		logger.error(uri + "\r\n" + JsonUtils.toFormatJson(paramMap));
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
