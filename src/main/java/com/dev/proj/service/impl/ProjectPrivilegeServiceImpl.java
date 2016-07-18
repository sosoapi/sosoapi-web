package com.dev.proj.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.proj.service.ProjectPrivilegeService;
import com.dev.proj.dao.ProjectPrivilegeDao;
import com.dev.proj.entity.ProjectPrivilege;

/**
 * 
		* <p>Title: 项目权限相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class ProjectPrivilegeServiceImpl extends BaseMybatisServiceImpl<ProjectPrivilege,Long,ProjectPrivilegeDao>
										implements ProjectPrivilegeService{
	
}
