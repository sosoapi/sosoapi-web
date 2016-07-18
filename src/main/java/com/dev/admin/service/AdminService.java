package com.dev.admin.service;

import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 管理员相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午5:06:23</p>
 */
public interface AdminService {
	/**
	 * 
			*@name 添加管理员
			*@Description  
			*@CreateDate 2015年9月6日下午5:06:58
	 */
	void regist(String nickName,String email,String passwd);
	
	/**
	 * 
			*@name 更新用户信息
			*@Description  
			*@CreateDate 2015年9月12日下午3:17:47
	 */
	void updateUser(UserInfo userInfo);
}
