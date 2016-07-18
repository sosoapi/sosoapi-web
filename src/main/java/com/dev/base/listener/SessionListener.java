package com.dev.base.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dev.base.constant.AppConstants;
import com.dev.base.enums.LoginStatus;
import com.dev.base.utils.DateUtil;
import com.dev.user.entity.UserLogin;
import com.dev.user.service.UserLoginService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: session监听</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月16日上午11:42:39</p>
 */
public class SessionListener implements HttpSessionListener,
								HttpSessionAttributeListener {
	private static Logger logger = LogManager.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		UserInfo userInfo = (UserInfo)event.getSession().getAttribute(AppConstants.SESSION_KEY_USER);
		if (userInfo != null) {
			logger.debug(userInfo.getNickName() + " is offline");
			
			ServletContext servletContext = event.getSession().getServletContext();
			UserLoginService userLoginService = (UserLoginService)getBean(servletContext, UserLoginService.class);
			
			UserLogin userLogin = userLoginService.getByUserId(userInfo.getUserId());
			userLogin.setModifyDate(DateUtil.getNow());
			userLogin.setLoginStatus(LoginStatus.offline);
			userLoginService.update(userLogin);
		}
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
	
	/**
	 * 
			*@name 通过WebApplicationContextUtils 得到Spring容器的实例
			*@Description  
			*@CreateDate 2015年10月10日下午11:45:05
	 */
    private Object getBean(ServletContext servletContext,Class beanClass){  
        //通过WebApplicationContextUtils 得到Spring容器的实例。  
        ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
        
        //返回Bean的实例。  
        return application.getBean(beanClass);
    } 
}
