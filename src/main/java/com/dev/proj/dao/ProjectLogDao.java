package com.dev.proj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.proj.entity.ProjectLog;

/**
 * 
		* <p>Title: 项目日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月8日上午11:02:29</p>
 */
public interface ProjectLogDao extends BaseMybatisDao<ProjectLog,Long> {
	/**
	 * 
			*@name 获取项目日志列表
			*@Description  
			*@CreateDate 2016年3月8日下午12:38:16
	 */
	List<Map> listByProjId(@Param("projId")Long projId,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 获取项目日志总数
			*@Description  
			*@CreateDate 2016年3月8日下午12:39:16
	 */
	int countByProjId(@Param("projId")Long projId);
	
	/**
	 * 
			*@name 删除项目日志
			*@Description  
			*@CreateDate 2016年3月8日下午1:08:53
	 */
	void delByLogId(@Param("projId")Long projId,@Param("logId")Long logId);
}
