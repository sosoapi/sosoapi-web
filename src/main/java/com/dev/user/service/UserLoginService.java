package com.dev.user.service;

import java.util.Date;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.user.entity.UserLogin;

/**
 * 
		* <p>Title: 用户登陆信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
public interface UserLoginService extends BaseMybatisService<UserLogin, Long>{
	/**
	 * 
			*@name 根据用户id查询登陆信息
			*@Description  
			*@CreateDate 2015年8月8日上午11:09:44
	 */
	UserLogin getByUserId(Long userId);
	
	/**
	 * 
			*@name 查询当天登录用户总数
			*@Description  
			*@CreateDate 2015年10月10日下午6:01:54
	 */
	int countDayLogin(Date date);
	
	/**
	 * 
			*@name 查询当天登录的老用户数
			*@Description  
			*@CreateDate 2015年10月10日下午6:02:21
	 */
	int countDayOldLogin(Date date);
	
	/**
	 * 
			*@name 查询当前在线用户总数
			*@Description  
			*@CreateDate 2015年10月11日上午10:05:58
	 */
	int countOnline();
	
	/**
	 * 
			*@name 定时任务，重置登陆失败次数
			*@Description  
			*@CreateDate 2015年10月11日上午11:49:25
	 */
	void resetLoginFailCount();
}
