package com.dev.proj.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.enums.ProjectStatus;
import com.dev.base.test.BaseTest;
import com.dev.base.util.Pager;
import com.dev.proj.entity.Project;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;

public class ProjectServiceImplTest extends BaseTest{
	@Autowired
	private ProjectService projectService;
	
	@Test
	public void testListByUserId() {
		List<ProjectInfo> list = projectService.listByUserId(1L,"","",ProjectStatus.open, new Pager(1, 10));
		System.out.println(list);
	}
	
	@Test
	public void testAdd(){
		for (int i = 1; i < 101; i++) {
			Project project = new Project();
			project.setCode("code" + i);
			project.setName("test" + i);
			project.setStatus(ProjectStatus.open);
			project.setUserId(3L);
			
			projectService.add(project);
		}
	}
}
