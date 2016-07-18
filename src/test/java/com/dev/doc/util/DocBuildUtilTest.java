package com.dev.doc.util;

import org.junit.Test;

import com.dev.doc.vo.SchemaNodeInfo;

public class DocBuildUtilTest {

	@Test
	public void testBuildTreeNode() {
		String schema = "[{\"code\":\"name\",\"name\":\"名称\",\"type\":\"sys_string\",\"refSchemaId\":\"1\",\"nodeId\":\"10\",\"parentId\":null},{\"code\":\"age\",\"name\":\"年龄\",\"type\":\"sys_integer_int32\",\"refSchemaId\":\"1\",\"nodeId\":\"11\",\"parentId\":null},{\"code\":\"address\",\"name\":\"地址\",\"type\":\"sys_object\",\"refSchemaId\":\"1\",\"nodeId\":\"12\",\"parentId\":null},{\"code\":\"city\",\"name\":\"城市\",\"type\":\"sys_string\",\"refSchemaId\":\"1\",\"nodeId\":\"1210\",\"parentId\":\"12\"},{\"code\":\"province\",\"name\":\"省份\",\"type\":\"sys_string\",\"refSchemaId\":\"1\",\"nodeId\":\"1211\",\"parentId\":\"12\"}]";
		SchemaNodeInfo nodeInfo = DocBuildUtil.buildTreeNode(schema);
		System.out.println(nodeInfo.getChildList());
	}

}
