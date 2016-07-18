package com.dev.proj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.enums.ProjectStatus;
import com.dev.base.enums.Role;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.service.ApiDocService;
import com.dev.proj.dao.ProjectDao;
import com.dev.proj.entity.Project;
import com.dev.proj.entity.ProjectMember;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;
import com.dev.user.service.AuthService;

/**
 * 
		* <p>Title: 项目相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class ProjectServiceImpl extends BaseMybatisServiceImpl<Project,Long,ProjectDao>
										implements ProjectService{
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Autowired
	private AuthService authService;
	
	@Override
	public List<ProjectInfo> listByUserId(Long userId, String code,String name,ProjectStatus status,Pager pager) {
		List<ProjectInfo> result = new ArrayList<>();
		
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		List<Map> infoList = getMybatisDao().listByUserId(userId, code,name,status, pager);
		ProjectInfo projectInfo = null;
		for (Map info : infoList) {
			projectInfo = new ProjectInfo();
			projectInfo.setProjId((Long)info.get("projId"));
			projectInfo.setCode((String)info.get("code"));
			projectInfo.setName((String)info.get("name"));
			projectInfo.setRole(Role.valueOf((String)info.get("role")));
			projectInfo.setStatus(ProjectStatus.valueOf((String)info.get("status")));
			projectInfo.setCreateDate((Date)info.get("createDate"));
			
			result.add(projectInfo);
		}
		
		return result;
	}

	@Override
	public Project add(Long userId,Project project) {
		project.setUserId(userId);
		return add(project);
	}

	@Override
	@Transactional
	public Project add(Project project){
		getMybatisDao().add(project);
		
		//绑定一个api文档
		ApiDoc apiDoc = new ApiDoc();
		apiDoc.setProjId(project.getId());
		apiDocService.add(apiDoc);
		
		//绑定项目成员
		ProjectMember member = new ProjectMember();
		member.setProjId(project.getId());
		member.setUserId(project.getUserId());
		member.setRole(Role.admin);
		projectMemberService.add(member);
		
		return project;
	}
	
	@Override
	public int countByUserId(Long userId,String code,String name, ProjectStatus status) {
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		
		return getMybatisDao().countByUserId(userId,code,name,status);
	}

	@Override
	public ProjectInfo getInfo(Long userId, Long projId) {
		Project project = getById(projId);
		ApiDoc apiDoc = apiDocService.getByProjId(projId);
		Role role = projectMemberService.getRole(userId, projId);
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setDescription(project.getDescription());
		projectInfo.setCode(project.getCode());;
		projectInfo.setName(project.getName());
		projectInfo.setProjId(projId);
		projectInfo.setStatus(project.getStatus());
		projectInfo.setDocId(apiDoc.getId());
		projectInfo.setRole(role);
		
		return projectInfo;
	}

	@Override
	public List<ProjectInfo> listAll(String code, String nickName,
			ProjectStatus status, Pager pager) {
		code = getLikeExpr(code);
		nickName = getLikeExpr(nickName);
		
		List<Map> infoList = getMybatisDao().listAll(code, nickName, status, pager);
		List<ProjectInfo> result = new ArrayList<ProjectInfo>();
		ProjectInfo tempInfo = null;
		for (Map info : infoList) {
			tempInfo = new ProjectInfo();
			tempInfo.setCode((String)info.get("code"));
			tempInfo.setName((String)info.get("name"));
			tempInfo.setCreateDate((Date)info.get("createDate"));
			tempInfo.setProjId((Long)info.get("projId"));
			tempInfo.setCreaterNickName((String)info.get("nickName"));
			tempInfo.setMemCount((Long)info.get("memCount"));
			tempInfo.setStatus(ProjectStatus.valueOf((String)info.get("status")));
			
			result.add(tempInfo);
		}
		
		return result;
	}

	@Override
	public int countAll(String code, String nickName, ProjectStatus status) {
		code = getLikeExpr(code);
		nickName = getLikeExpr(nickName);
		
		return getMybatisDao().countAll(code, nickName, status);
	}

	@Override
	public int countTotal() {
		return getMybatisDao().countTotal();
	}
	
	@Override
	public int countDay(Date date) {
		return getMybatisDao().countDay(DateUtil.getDayStart(date), DateUtil.getDayEnd(date));
	}

	@Override
	public Long upload(Long userId, Map<String, Object> swaggerInfo) {
		return null;
	}

	@Override
	public Project simpleAdd(Project project) {
		getMybatisDao().add(project);
		return project;
	}
}
