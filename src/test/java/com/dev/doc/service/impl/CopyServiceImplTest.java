package com.dev.doc.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.test.BaseTest;
import com.dev.doc.service.CopyService;

public class CopyServiceImplTest extends BaseTest{
	@Autowired
	private CopyService copyService;
	
	@Test
	public void testCopyProj() {
		copyService.copyProj(399L, 1203L);
	}
}
