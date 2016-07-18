package com.dev.user.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.user.dao.UserTokenDao;
import com.dev.user.entity.UserToken;
import com.dev.user.service.UserTokenService;

/**
 * 
		* <p>Title: 用户登陆凭证</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月30日下午4:22:56</p>
 */
@Service
public class UserTokenServiceImpl extends BaseMybatisServiceImpl<UserToken,Long,UserTokenDao>
										implements UserTokenService{

	@Override
	public UserToken getByToken(String token) {
		return getMybatisDao().getByToken(token);
	}

	@Override
	public UserToken getByUserId(Long userId) {
		return getMybatisDao().getByUserId(userId);
	}
	
}
