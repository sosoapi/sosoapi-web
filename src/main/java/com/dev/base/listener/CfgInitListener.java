package com.dev.base.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dev.base.constant.CfgConstants;

/**
 * 
		* <p>Title: 系统初始化</p>
		* <p>Description: 用于系统启动时相关信息的预处理</p>
		* <p>CreateDate: 2015年7月11日下午1:39:37</p>
 */
public class CfgInitListener implements ServletContextListener{
	private ApplicationContext appContext;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		//保存cfg.properties中设置的公共属性
		setCfg(servletContext);
		
		//保存下拉框常量
		setSelectData(servletContext,appContext);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	//保存cfg.properties中设置的公共属性
	private void setCfg(ServletContext application){
		//初始化contextPath
		CfgConstants.WEB_CONTEXT_PATH = application.getContextPath();
		
		Map<String, String> cfgMap = new HashMap<String, String>();
		cfgMap.put("WEB_BASE_URL", CfgConstants.WEB_BASE_URL);
		cfgMap.put("SWAGGER_UI_DOWNLOAD_URL", CfgConstants.SWAGGER_UI_DOWNLOAD_URL);
		cfgMap.put("IMG_DOMAIN", CfgConstants.IMG_DOMAIN);
		cfgMap.put("ONLINE_HELP_URL", CfgConstants.ONLINE_HELP_URL);
		cfgMap.put("OFFLINE_HELP_URL", CfgConstants.OFFLINE_HELP_URL);
		cfgMap.put("SYS_REQ_TOKEN", CfgConstants.SYS_REQ_TOKEN);
		cfgMap.put("COPY_FLAG", CfgConstants.COPY_FLAG);
		
		application.setAttribute("Cfg", cfgMap);
	}
	
	//保存下拉框常量
	private void setSelectData(ServletContext application,ApplicationContext ctx){

	}
}
