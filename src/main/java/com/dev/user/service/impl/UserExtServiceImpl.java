package com.dev.user.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.user.dao.UserExtDao;
import com.dev.user.entity.UserExt;
import com.dev.user.service.UserExtService;

/**
 * 
		* <p>Title: 用户扩展信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月19日下午5:41:26</p>
 */
@Service
public class UserExtServiceImpl extends BaseMybatisServiceImpl<UserExt,Long,UserExtDao>
										implements UserExtService{
	@Override
	public UserExt getByUserId(Long userId) {
		UserExt result = getMybatisDao().getByUserId(userId);
		if (result == null) {//如果不存在，则新建
			result = createUserExt(userId);
			add(result);
		}
		
		return result;
	}
	
	//创建用户扩展信息
	private UserExt createUserExt(Long userId){
		UserExt ext = new UserExt();
		ext.setUserId(userId);
		
		return ext;
	}
}
