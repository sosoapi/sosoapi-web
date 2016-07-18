package com.dev.base.constant;

import java.util.Properties;

import com.dev.base.utils.PropertiesUtils;
import com.dev.base.utils.RandomUtils;

/**
 * 
		* <p>Title: 配置信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午1:45:42</p>
 */
public class CfgConstants {
	// 配置信息
	private final static Properties cfgProperties = PropertiesUtils.getProperties("cfg.properties");

	/**
	 * 页面基路径
	 */
	public static final String WEB_BASE_URL = cfgProperties.getProperty("web.base.url");
	
	/**
	 * 项目contextPath,启动后在CfgInitListener.java中完成初始化
	 * /sosoapi-web
	 */
	public static String WEB_CONTEXT_PATH = "";
	
	/**
	 * 分页展示页码数
	 */
	public static final int SHOW_PAGE_SIZE = Integer.parseInt(cfgProperties.getProperty("show.page.number"));
	
	/**
	 * 一天最多登陆失败次数，超过账号锁定
	 */
	public static final int LIMIT_LOGIN_FAIL_COUNT = Integer.parseInt(cfgProperties.getProperty("limit.login.fail.count"));
	
	/**
	 * 网站名称
	 */
	public static final String WEB_NAME = cfgProperties.getProperty("web.name");
	
	/**
	 * 网站url
	 */
	public static final String WEB_SITE = cfgProperties.getProperty("web.site");
	
	/**
	 * 网站联系人邮箱
	 */
	public static final String WEB_SERVICE_EMAIL = cfgProperties.getProperty("web.service.email");
	
	/**
	 * 网站联系人电话
	 */
	public static final String WEB_SERVICE_TEL = cfgProperties.getProperty("web.service.tel");
	
	/**
	 * cookie域名
	 */
	public static final String COOKIE_DOMAIN = cfgProperties.getProperty("cookie.domain");
	
	/**
	 * 客户端保存token名称
	 */
	public static final String COOKIE_TOKEN_NAME = "token";
	
	/**
	 * 客户端保存token有效期，单位秒
	 */
	public static final int COOKIE_TOKEN_EXPIRE = Integer.parseInt(cfgProperties.getProperty("cookie.token.expire"));
	
	/**
	 * 图片域名
	 */
	public static final String IMG_DOMAIN = cfgProperties.getProperty("img.domain");
	
	/**
	 * swagger ui下载url
	 */
	public static final String SWAGGER_UI_DOWNLOAD_URL = cfgProperties.getProperty("swagger.ui.download.url");
	
	/**
	 * 线上编辑说明url
	 */
	public static final String ONLINE_HELP_URL = cfgProperties.getProperty("online.help.url");
	
	/**
	 * 线下部署说明url
	 */
	public static final String OFFLINE_HELP_URL = cfgProperties.getProperty("offline.help.url");
	
	/**
	 * 复制标志
	 */
	public static final String COPY_FLAG = cfgProperties.getProperty("copy.flag");
	
	/**
	 * 减少httpclient请求爬数据
	 */
	public static final String SYS_REQ_TOKEN = "" + RandomUtils.genRandomNum(1000, 1000000);
}
