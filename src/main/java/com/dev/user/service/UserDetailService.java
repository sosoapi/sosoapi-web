package com.dev.user.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.user.entity.UserDetail;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 用户详细信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
public interface UserDetailService extends BaseMybatisService<UserDetail, Long>{
	/**
	 * 
			*@name 查询用户详情信息
			*@Description  
			*@CreateDate 2015年8月8日上午11:16:46
	 */
	UserDetail getByUserId(Long userId);
	
	/**
	 * 
			*@name 查询用户详细信息
			*@Description  
			*@CreateDate 2015年9月12日上午10:59:16
	 */
	UserInfo getDetailByUserId(Long userId);
	
	/**
	 * 
			*@name 查询所有用户详情
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<UserInfo> listAll(String email,String nickName,Boolean valid,Pager pager);
	
	/**
	 * 
			*@name 查询用户总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(String email,String nickName,Boolean valid);
}
