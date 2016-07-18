package com.dev.base.exception.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dev.base.exception.AuthException;
import com.dev.base.exception.BaseRuntimeException;
import com.dev.base.exception.SessionTimeoutException;
import com.dev.base.exception.TipException;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.exception.errorcode.ErrorCodeTool;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 异常处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午1:56:03</p>
 */
public class BaseExceptionHandler implements HandlerExceptionResolver {
	private static Logger logger = LogManager.getLogger(BaseExceptionHandler.class);
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
											Object handler,Exception ex) {
		logger.error("请求处理失败：" + WebUtil.getClientIp(request) + "\t" + request.getRequestURI());
		
		//判断是否是ajax请求
		boolean isAjaxReq = WebUtil.isAjaxReq(request);
		//组装错误信息
		Map<String,Object> errorInfo = parseErrorInfo(ex);
		//组装跳转页面
		String viewName = parseViewName(ex, isAjaxReq);
		
		ModelAndView result = new ModelAndView();
		result.setViewName(viewName);
		result.addAllObjects(errorInfo);
		
		ex.printStackTrace();
		
		return result;
	}

	//组装错误信息
	private Map<String, Object> parseErrorInfo(Exception ex){
		Map<String,Object> errorInfo = MapUtils.newMap();
		if (ex instanceof BaseRuntimeException) {//自定义异常
			BaseRuntimeException exception = (BaseRuntimeException)ex;
			errorInfo = parseExceptionInfo(exception.getErrorCode(), 
					exception.getErrorMsg(), exception.getData());
		}
		else {//其他异常
			errorInfo = parseExceptionInfo(ErrorCode.BUSY, null, null);
		}
		
		return errorInfo;
	}
	
	//组装跳转的页面
	private String parseViewName(Exception ex,boolean isAjaxReq){
		//ajax请求对应的页面
		if (isAjaxReq) {
			return "forward:/jsp/error/ajax.jsp";
		}
		
		//非ajax请求对应的页面
		String viewName = "forward:/jsp/error/error.jsp";
		if (ex instanceof SessionTimeoutException) {//登陆超时异常
			viewName = "forward:/forwardLogin.htm";
		}
		else if (ex instanceof AuthException) {//无权限异常
			viewName = "forward:/jsp/error/403.jsp";
		}
		else if (ex instanceof TipException) {//用户提示信息异常
			viewName = ((TipException)ex).getViewName();
		}
		
		return viewName;
	}
	
	//组装异常信息
	private Map<String, Object> parseExceptionInfo(String errorCode, String errorMsg, Object data) {
		Map<String, Object> resultMap = MapUtils.newMap();

		// 如果异常消息中已经有提示信息，就不到属性文件中查找
		if (StringUtils.isEmpty(errorMsg)) {
			errorMsg = ErrorCodeTool.get(errorCode);
		}
		// 如果查找不到异常说明，则设置默认说明
		if (StringUtils.isEmpty(errorMsg)) {
			errorMsg = "系统繁忙";
		}

		logger.error("系统发生错误，异常码：" + errorCode + "异常消息：" + errorMsg);

		resultMap.put("errorCode", errorCode);
		resultMap.put("errorMsg", errorMsg);

		// 处理额外提示数据
		if (data != null) {
			resultMap.put("data", data);
		}

		return resultMap;
	}
}
