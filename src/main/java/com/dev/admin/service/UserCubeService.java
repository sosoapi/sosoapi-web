package com.dev.admin.service;

import java.util.List;

import com.dev.admin.entity.UserCube;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 用户统计</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月10日下午5:22:34</p>
 */
public interface UserCubeService extends BaseMybatisService<UserCube, Long>{
	/**
	 * 
			*@name 统计当前用户
			*@Description  
			*@CreateDate 2015年10月10日下午5:31:21
	 */
	UserCube cubeCurrent();
	
	/**
	 * 
			*@name 查询用户统计列表
			*@Description  
			*@CreateDate 2015年8月7日下午6:18:16
	 */
	List<UserCube> list(Pager pager);
	
	/**
	 * 
			*@name 查询用户统计总数
			*@Description  
			*@CreateDate 2015年8月7日下午6:19:18
	 */
	int count();
	
	/**
	 * 
			*@name 定时任务，统计当天用户信息
			*@Description  
			*@CreateDate 2015年10月11日上午11:40:59
	 */
	void cubeDayJob();
}
