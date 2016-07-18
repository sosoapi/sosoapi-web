package com.dev.proj.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.ProjectStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.proj.entity.Project;

/**
 * 
		* <p>Title: 项目信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:44</p>
 */
public interface ProjectDao extends BaseMybatisDao<Project,Long> {
	/**
	 * 
			*@根据用户id查询相关的项目列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listByUserId(@Param("userId")Long userId,@Param("code")String code,@Param("name")String name,
								@Param("status")ProjectStatus status,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询用户相关的项目记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(@Param("userId")Long userId,@Param("code")String code,
						@Param("name")String name,@Param("status")ProjectStatus status);
	
	/**
	 * 
			*@name 查询项目编码是否已存在
			*@Description  
			*@CreateDate 2015年8月25日下午2:28:24
	 */
	int countByCode(@Param("userId")Long userId,@Param("code")String code);
	
	/**
	 * 
			*@name 查询所有项目详情
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<Map> listAll(@Param("code")String code,@Param("nickName")String nickName,
						@Param("status")ProjectStatus status,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询用户总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(@Param("code")String code,@Param("nickName")String nickName,
					@Param("status")ProjectStatus status);

	/**
	 * 
			*@name 查询总项目数
			*@Description  
			*@CreateDate 2015年9月11日下午6:13:32
	 */
	int countTotal();
	
	/**
	 * 
			*@name 查询指定日期当天新增的项目数
			*@Description  
			*@CreateDate 2015年10月10日下午5:41:51
	 */
	int countDay(@Param("dayStart")Date dayStart,@Param("dayEnd")Date dayEnd);
}
