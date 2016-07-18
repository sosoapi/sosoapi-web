package com.dev.user.service;

import java.util.Date;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.user.entity.UserBasic;

/**
 * 
		* <p>Title: 用户基本信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>

 */
public interface UserBasicService extends BaseMybatisService<UserBasic, Long>{
	/**
	 * 
			*@name 查询指定的邮箱是否存在
			*@Description  
			*@CreateDate 2016年5月21日下午10:12:19
	 */
	boolean isEmailExist(String email,Long exceptId);
	
	/**
	 * 
			*@name 根据邮箱查询用户信息
			*@Description  
			*@CreateDate 2015年8月8日上午10:25:53
	 */
	UserBasic getByEmail(String email);
	
	/**
	 * 
			*@name 加密密码
			*@Description  
			*@CreateDate 2015年8月8日上午10:56:10
	 */
	String encryPasswd(Long userId,String password);
	
	/**
	 * 
			*@name 更改密码
			*@Description  
			*@CreateDate 2015年8月22日下午4:04:23
	 */
	void updatePasswd(Long userId,String oldPasswd,String newPasswd);
	
	/**
	 * 
			*@name 查询总用户数
			*@Description  
			*@CreateDate 2015年9月11日下午6:13:32
	 */
	int countTotalRegist();
	
	/**
	 * 
			*@name 查询指定日期当天注册用户数
			*@Description  
			*@CreateDate 2015年10月10日下午5:49:37
	 */
	int countDayRegist(Date date);
	
	/**
	 * 
			*@name 定时任务，解锁
			*@Description  
			*@CreateDate 2015年10月11日上午11:43:47
	 */
	void unlockJob();
	
	/**
	 * 
			*@name 发送更改邮箱验证码
			*@Description  
			*@CreateDate 2016年5月21日下午8:28:32
	 */
	void sendUpdateEmailCode(Long userId,String passwd,String email);
	
	/**
	 * 
			*@name 换绑邮箱
			*@Description  
			*@CreateDate 2016年5月21日下午8:29:44
	 */
	void updateEmail(String code);
}
