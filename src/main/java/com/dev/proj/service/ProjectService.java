package com.dev.proj.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dev.base.enums.ProjectStatus;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.proj.entity.Project;
import com.dev.proj.vo.ProjectInfo;

/**
 * 
		* <p>Title: 项目相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface ProjectService extends BaseMybatisService<Project, Long>{
	/**
	 * 
			*@根据用户id查询相关的项目列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<ProjectInfo> listByUserId(Long userId,String code,String name,ProjectStatus status,Pager pager);
	
	/**
	 * 
			*@name 查询用户相关的项目记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:43:29
	 */
	int countByUserId(Long userId,String code,String name,ProjectStatus status);
	
	/**
	 * 
			*@name 新增项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:15:50
	 */
	Project add(Long userId,Project project);
	
	/**
	 * 
			*@name 仅添加项目信息
			*@Description  
			*@CreateDate 2016年5月14日上午10:40:11
	 */
	Project simpleAdd(Project project);
	
	/**
	 * 
			*@name 查询项目信息
			*@Description  
			*@CreateDate 2015年8月7日下午2:25:03
	 */
	ProjectInfo getInfo(Long userId,Long projId);
	
	/**
	 * 
			*@name 查询所有项目详情
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<ProjectInfo> listAll(String code,String nickName,
						ProjectStatus status,Pager pager);
	
	/**
	 * 
			*@name 查询用户总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(String code,String nickName,ProjectStatus status);
	
	/**
	 * 
			*@name 查询总项目数
			*@Description  
			*@CreateDate 2015年9月11日下午6:13:32
	 */
	int countTotal();
	
	/**
	 * 
			*@name 查询指定日期当天新增项目数
			*@Description  
			*@CreateDate 2015年10月10日下午5:49:37
	 */
	int countDay(Date date);
	
	/**
	 * 
			*@name 导入项目
			*@Description  
			*@CreateDate 2016年1月23日下午5:21:50
	 */
	Long upload(Long userId,Map<String, Object> swaggerInfo);
}
