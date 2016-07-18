package com.dev.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.user.entity.UserDetail;

/**
 * 
		* <p>Title: 用户详细信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
public interface UserDetailDao extends BaseMybatisDao<UserDetail,Long> {
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
	Map getDetailByUserId(Long userId);
	
	/**
	 * 
			*@name 查询所有用户详情
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<Map> listAll(@Param("email")String email,@Param("nickName")String nickName,
						@Param("valid")Boolean valid,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询用户总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(@Param("email")String email,@Param("nickName")String nickName,@Param("valid")Boolean valid);
}
