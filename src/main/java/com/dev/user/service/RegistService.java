package com.dev.user.service;

import com.dev.user.vo.RegistParamInfo;

/**
 * 
		* <p>Title: 注册服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日下午12:48:03</p>
 */
public interface RegistService {
	/**
	 * 
			*@name 通过邮箱注册
			*@Description  
			*@CreateDate 2015年8月8日下午12:53:20
	 */
	void registByEmail(RegistParamInfo registParamInfo);
	
	/**
	 * 
			*@name 账户激活
			*@Description  
			*@CreateDate 2015年8月22日下午2:45:38
	 */
	void activeByEmail(String code);
	
	/**
	 * 
			*@name 重新发送账号激活邮件
			*@Description  
			*@CreateDate 2015年8月23日下午6:28:46
	 */
	void sendActiveCode(String email);
}
