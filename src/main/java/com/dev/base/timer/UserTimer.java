package com.dev.base.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserLoginService;

/**
 * 
		* <p>Title: 用户相关定时器</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月10日下午7:04:02</p>
 */
@Service
public class UserTimer {
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 
			*@name 用户自动解锁
			*@Description  
			*@CreateDate 2015年10月11日上午11:39:39
	 */
	public void unlock(){
		userBasicService.unlockJob();
		userLoginService.resetLoginFailCount();
	}
}
