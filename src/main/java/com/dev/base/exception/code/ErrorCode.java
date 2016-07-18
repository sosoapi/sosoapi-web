package com.dev.base.exception.code;

import com.dev.base.exception.errorcode.SysErrorCode;

/**
 * 
		* <p>Title: 系统公共异常码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午1:57:02</p>
 */
public interface ErrorCode extends SysErrorCode {
	/**
	 * 用户未注册
	 */
	public static final String LOGIN_001 = "51001";

	/**
	 * 用户已注册
	 */
	public static final String LOGIN_002 = "51002";

	/**
	 * 验证码错误
	 */
	public static final String LOGIN_003 = "51003";

	/**
	 * 用户名或密码错误
	 */
	public static final String LOGIN_004 = "51004";

	/**
	 * 账号未激活
	 */
	public static final String LOGIN_005 = "51005";

	/**
	 * 账号被锁定
	 */
	public static final String LOGIN_006 = "51006";
	
	/**
	 * 登陆/注册方式与提交信息不匹配
	 */
	public static final String LOGIN_007 = "51007";
	
	/**
	 * 原密码错误
	 */
	public static final String LOGIN_008 = "51008";
	
	/**
	 * 账号已激活
	 */
	public static final String LOGIN_009 = "51009";
	
	/**
	 * 登录失败次数超过限制，账号被锁定，请联系管理员或第二天自动解锁
	 */
	public static final String LOGIN_010 = "51010";
	
	/**
	 * 项目至少需要一名管理员
	 */
	public static final String PROJ_001 = "52001";
	
	/**
	 * 只有管理员才有该权限
	 */
	public static final String PROJ_002 = "52002";
	
	/**
	 * 已存在相同的请求url和请求方式
	 */
	public static final String DOC_001 = "53001";
	
	/**
	 * swagger文档格式错误
	 */
	public static final String DOC_002 = "53002";
}
