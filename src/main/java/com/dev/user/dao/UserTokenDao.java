package com.dev.user.dao;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.user.entity.UserToken;

/**
 * 
		* <p>Title: 用户登陆凭证</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月30日下午4:22:29</p>
 */
public interface UserTokenDao extends BaseMybatisDao<UserToken,Long> {
	/**
	 * 
			*@name 根据token获取用户信息
			*@Description  
			*@CreateDate 2015年11月30日下午4:24:29
	 */
	UserToken getByToken(@Param("token")String token);
	
	/**
	 * 
			*@name 查询用户token信息
			*@Description  
			*@CreateDate 2015年11月30日下午4:24:58
	 */
	UserToken getByUserId(@Param("userId")Long userId);
}
