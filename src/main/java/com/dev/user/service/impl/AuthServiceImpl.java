package com.dev.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.base.enums.Role;
import com.dev.base.utils.MapUtils;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.vo.ProjectInfo;
import com.dev.user.service.AuthService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 授权服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月7日下午2:41:54</p>
 */
@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Override
	public void loadProjAuth(UserInfo userInfo) {
		//设置项目权限信息
		List<ProjectInfo> projectInfoList = projectMemberService.listAuthProjectInfo(userInfo.getUserId());
		Map<Long,Role> projMap = MapUtils.newMap();
		Map<Long,Role> docMap = MapUtils.newMap();
		for (ProjectInfo projectInfo : projectInfoList) {
			projMap.put(projectInfo.getProjId(), projectInfo.getRole());
			docMap.put(projectInfo.getDocId(), projectInfo.getRole());
		}
		
		userInfo.setProjMap(projMap);
		userInfo.setDocMap(docMap);
	}
	
}
