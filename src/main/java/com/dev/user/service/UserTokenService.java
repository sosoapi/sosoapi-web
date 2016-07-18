package com.dev.user.service;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.user.entity.UserToken;

/**
 * 
		* <p>Title: 用户登陆凭证</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月30日下午4:22:45</p>
 */
public interface UserTokenService extends BaseMybatisService<UserToken, Long>{
	/**
	 * 
			*@name 根据token获取用户信息
			*@Description  
			*@CreateDate 2015年11月30日下午4:24:29
	 */
	UserToken getByToken(String token);
	
	/**
	 * 
			*@name 查询用户token信息
			*@Description  
			*@CreateDate 2015年11月30日下午4:24:58
	 */
	UserToken getByUserId(Long userId);
}
