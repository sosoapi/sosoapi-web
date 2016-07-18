package com.dev.proj.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.proj.entity.ProjectLog;
import com.dev.proj.vo.ProjectLogInfo;

/**
 * 
		* <p>Title: 项目日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月8日上午11:04:04</p>
 */
public interface ProjectLogService extends BaseMybatisService<ProjectLog, Long>{
	/**
	 * 
			*@name 获取项目日志列表
			*@Description  
			*@CreateDate 2016年3月8日下午12:38:16
	 */
	List<ProjectLogInfo> listByProjId(Long projId,Pager pager);
	
	/**
	 * 
			*@name 获取项目日志总数
			*@Description  
			*@CreateDate 2016年3月8日下午12:39:16
	 */
	int countByProjId(Long projId);
	
	/**
	 * 
			*@name 删除项目日志
			*@Description  
			*@CreateDate 2016年3月8日下午1:08:53
	 */
	void delByLogId(Long projId,Long logId);
	
	/**
	 * 
			*@name 新增项目日志
			*@Description  
			*@CreateDate 2016年3月8日下午2:26:33
	 */
	void addLog(Long userId,Long projId,String title,String content);
}
