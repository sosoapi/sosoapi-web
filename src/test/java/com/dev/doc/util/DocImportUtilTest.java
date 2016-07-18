package com.dev.doc.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.dev.base.json.JsonUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;

public class DocImportUtilTest {
	private Map<String, Object> swaggerInfo = null;
	
	@Before
	public void init() throws Exception{
		String fileName = "src\\main\\webapp\\swagger\\json\\sosoapi_demo.json";
		String content = FileUtils.readFileToString(new File(fileName));
		swaggerInfo = JsonUtils.toObject(content, Map.class);
//		System.out.println(JsonUtils.toFormatJson(swaggerInfo));
	}
	
	@Test
	public void testValidSchema() {
		
	}

	@Test
	public void testParseApiDoc() {
		ApiDoc apiDoc = DocImportUtil.parseApiDoc(swaggerInfo);
		System.out.println(JsonUtils.toFormatJson(apiDoc));
	}

	@Test
	public void testParseModuleList() {
		List<Module> moduleList = DocImportUtil.parseModuleList(swaggerInfo);
		System.out.println(JsonUtils.toFormatJson(moduleList));
	}

	@Test
	public void testParseRespSchemas() {
		List<RespSchema> schemaList = DocImportUtil.parseRespSchemas(swaggerInfo);
		System.out.println(schemaList.get(0).getCustSchema());
	}

}
