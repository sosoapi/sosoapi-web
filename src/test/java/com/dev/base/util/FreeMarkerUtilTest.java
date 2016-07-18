package com.dev.base.util;

import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;

import com.dev.base.utils.MapUtils;

public class FreeMarkerUtilTest {

	@Test
	public void testProcess() {
		Map<String, Object> dataModel = MapUtils.newMap();
		dataModel.put("basePackage", "com.dev");
		dataModel.put("baseName", "");
		
		StringWriter writer = new StringWriter();
		FreeMarkerUtil.process("test.ftl", dataModel, writer);
		System.out.println(writer.toString());
	}

}
