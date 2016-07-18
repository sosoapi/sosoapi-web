package com.dev.user.service;

import com.dev.user.vo.LoginParamInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 登陆相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日上午10:29:25</p>
 */
public interface LoginService {
	/**
	 * 
			*@name 通过邮箱登陆
			*@Description  
			*@CreateDate 2015年8月8日上午10:31:07
	 */
	UserInfo loginByEmail(LoginParamInfo loginParamInfo);
	
	/**
	 * 
			*@name 通过token登陆
			*@Description  
			*@CreateDate 2015年11月30日下午5:30:51
	 */
	UserInfo loginByToken(LoginParamInfo loginParamInfo);
	
	/**
	 * 
			*@name 发送密码重置授权码
			*@Description  
			*@CreateDate 2015年8月22日下午3:00:52
	 */
	void sendResetCode(String email);
	
	/**
	 * 
			*@name 重置密码
			*@Description  
			*@CreateDate 2015年8月22日下午3:06:49
	 */
	void resetPasswd(String code,String passwd);
}
