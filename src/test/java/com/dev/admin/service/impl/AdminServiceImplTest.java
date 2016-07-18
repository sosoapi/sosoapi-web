package com.dev.admin.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.admin.service.AdminService;
import com.dev.base.test.BaseTest;

public class AdminServiceImplTest extends BaseTest{
	@Autowired
	private AdminService adminService;
	
	@Test
	public void testRegist(){
		adminService.regist("admin", "admin@qq.com", "123456");
	}
}
