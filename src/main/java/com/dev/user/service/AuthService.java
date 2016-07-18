package com.dev.user.service;

import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 授权服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月7日下午2:41:29</p>
 */
public interface AuthService {
	/**
	 * 
			*@name 加载项目相关权限
			*@Description  
			*@CreateDate 2015年9月2日下午10:13:12
	 */
	void loadProjAuth(UserInfo userInfo);
}
