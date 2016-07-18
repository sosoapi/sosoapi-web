package com.dev.doc.util;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.base.json.JsonUtils;
import com.dev.base.test.BaseTest;
import com.dev.doc.service.RespSchemaService;

public class DocExportUtilTest extends BaseTest{
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Test
	public void testInitRespSchema() {
		Map<Long, Map> result = DocExportUtil.initRespSchema(respSchemaService.listAllByDocId(1430L));
		System.out.println(JsonUtils.toFormatJson(result));
	}
}
