package com.dev.user.dao;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.user.entity.UserExt;

/**
 * 
		* <p>Title: 用户扩展信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月19日下午5:40:45</p>
 */
public interface UserExtDao extends BaseMybatisDao<UserExt,Long> {
	/**
	 * 
			*@name 获取用户扩展信息
			*@Description  
			*@CreateDate 2015年10月19日下午5:43:27
	 */
	UserExt getByUserId(Long userId);
}
