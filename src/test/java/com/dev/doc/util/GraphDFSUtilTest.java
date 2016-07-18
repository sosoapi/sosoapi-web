package com.dev.doc.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;

public class GraphDFSUtilTest {

	@Test
	public void test() {
		List<Long> vertexList = addList("1,2,3,4,5");
		
		Map<Long,Set<Long>> vertexRelation = MapUtils.newMap();
		vertexRelation.put(3L, addSet("1"));
		vertexRelation.put(4L, addSet("2"));
		vertexRelation.put(4L, addSet("5"));
		vertexRelation.put(5L, addSet("3,1"));
		
		List<Long> result = GraphDFSUtil.topo(vertexList, vertexRelation);
		System.out.println(JsonUtils.toJson(result));
	}
	
	private List<Long> addList(String valueList){
		String[] valueArray = valueList.split(",");
		List<Long> result = new ArrayList<Long>();
		for (String value : valueArray) {
			result.add(Long.parseLong(value));
		}
		
		return result;
	}

	private Set<Long> addSet(String valueList){
		String[] valueArray = valueList.split(",");
		Set<Long> result = new HashSet<Long>();
		for (String value : valueArray) {
			result.add(Long.parseLong(value));
		}
		
		return result;
	}
}
