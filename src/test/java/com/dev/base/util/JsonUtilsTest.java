package com.dev.base.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class JsonUtilsTest {
	
	@Test
	public void testJson() throws Exception{
		String filePath = "src\\test\\resources\\json.txt";
		String content = FileUtils.readFileToString(new File(filePath));
		System.out.println(content);
		System.out.println(content.trim().startsWith("["));
		
		
//		Map<String, Object> json = JsonUtils.toObject(content, Map.class);
//		System.out.println(JsonUtils.toJson(json));
	}
	
}