package com.dev.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.admin.entity.UserCube;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 用户统计</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月10日下午5:22:13</p>
 */
public interface UserCubeDao extends BaseMybatisDao<UserCube,Long> {
	/**
	 * 
			*@name 查询用户统计列表
			*@Description  
			*@CreateDate 2015年8月7日下午6:18:16
	 */
	List<UserCube> list(@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询用户统计总数
			*@Description  
			*@CreateDate 2015年8月7日下午6:19:18
	 */
	int count();
}
