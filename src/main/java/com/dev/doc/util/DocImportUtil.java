package com.dev.doc.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.enums.SchemaType;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.FormatUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.vo.SchemaNodeInfo;

/**
 * 
		* <p>Title: api文档导入工具</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月13日下午5:42:05</p>
 */
public class DocImportUtil {
	/**
	 * 
			*@name 判断json格式是否符合规范
			*@Description  
			*@CreateDate 2016年1月23日下午5:16:24
	 */
	public static boolean validSchema(Map<String, Object> swaggerInfo){
		if (CollectionUtils.isEmpty(swaggerInfo)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
			*@name 组装api文档信息
			*@Description  
			*@CreateDate 2016年1月29日下午3:24:09
	 */
	public static ApiDoc parseApiDoc(Map<String, Object> swaggerInfo){
		ApiDoc apiDoc = new ApiDoc();
		apiDoc.setHost((String)swaggerInfo.get("host"));
		apiDoc.setBasePath((String)swaggerInfo.get("basePath"));
		apiDoc.setScheme(parseList((List<String>)swaggerInfo.get("schemes")));
		apiDoc.setConsume(parseList((List<String>)swaggerInfo.get("consumes")));
		apiDoc.setProduce(parseList((List<String>)swaggerInfo.get("produces")));
		
		Map<String, Object> infoMap = (Map<String,Object>)swaggerInfo.get("info");
		if (!CollectionUtils.isEmpty(infoMap)) {
			apiDoc.setDescription((String)infoMap.get("description"));
			apiDoc.setTitle((String)infoMap.get("title"));
			apiDoc.setVersion((String)infoMap.get("version"));
		}
		
		return apiDoc;
	}
	
	/**
	 * 
			*@name 组装模块列表
			*@Description  
			*@CreateDate 2016年1月29日下午4:45:07
	 */
	public static List<Module> parseModuleList(Map<String, Object> swaggerInfo){
		List<Module> moduleList = new ArrayList<Module>();
		
		//直接从定义中组装
		List<Map> tagList = (List<Map>)swaggerInfo.get("tags");
		Set<String> tagNameSet = new HashSet<String>();
		if (!CollectionUtils.isEmpty(tagList)) {
			String tagName = "";
			Module module = null;
			for (Map tagInfo : tagList) {
				tagName = (String)tagInfo.get("name");
				if (!StringUtils.isEmpty(tagName) && !tagName.contains(tagName)) {
					module = new Module();
					module.setName(tagName);
					module.setDescription((String)tagInfo.get("description"));
					module.setSortWeight(FormatUtils.parseInt((String)tagInfo.get("sortWeight"), 0));
					
					tagNameSet.add(tagName);
					moduleList.add(module);
				}
			}
		}
		
		//从path中组装
		Map<String, Object> pathMap = (Map<String, Object>)swaggerInfo.get("paths");
		if (!CollectionUtils.isEmpty(pathMap)) {
			Set<String> pathKeySet = pathMap.keySet();
			Map<String, Object> methodMap = null;
			Set<String> methodKeySet = null;
			List<String> methodTagList = null;
			Module module = null;
			for (String pathKey : pathKeySet) {
				methodMap = (Map<String, Object>)pathMap.get(pathKey);
				methodKeySet = methodMap.keySet();
				for (String methodKey : methodKeySet) {
					methodTagList = (List<String>)((Map)methodMap.get(methodKey)).get("tags");
					if (!CollectionUtils.isEmpty(methodTagList)) {
						for (String methodTag : methodTagList) {
							if (!tagNameSet.contains(methodTag)) {
								module = new Module();
								module.setName(methodTag);
								module.setSortWeight(0);
								
								tagNameSet.add(methodTag);
								moduleList.add(module);
							}
						}
					}
				}
			}
		}
		
		return moduleList;
	}
	
	/**
	 * 
			*@name 组装公共数据
			*@Description  
			*@CreateDate 2016年1月29日下午5:33:29
	 */
	public static List<RespSchema> parseRespSchemas(Map<String, Object> swaggerInfo){
		List<RespSchema> schemaList = new ArrayList<RespSchema>();
		Map<String, Object> defMap = (Map)swaggerInfo.get("definitions");
		if (!CollectionUtils.isEmpty(defMap)) {
			Set<String> defKeySet = defMap.keySet();
			Map<String, Object> defInfo = null;
			SchemaType defType = SchemaType.none;
			RespSchema respSchema = null;
			for (String defKey : defKeySet) {
				defInfo = (Map)defMap.get(defKey);
				defType = SchemaType.getByType((String)defInfo.get("type"));
				
				respSchema = new RespSchema();
				respSchema.setCode(defKey);
				respSchema.setType(defType);
				respSchema.setDescription((String)defInfo.get("description"));
				if (defType == SchemaType.sys_ref) {
					//TO-DO
					
				}
				else{
					respSchema.setCustSchema(parseSchema(defType,defKey, defInfo));
				}
				
				schemaList.add(respSchema);
			}
		}
		
		return schemaList;
	}
	
	//组装schema
	private static String parseSchema(SchemaType type,String key,Map<String, Object> value){
		if (CollectionUtils.isEmpty(value)) {
			return "";
		}
		
		List<SchemaNodeInfo> result = new ArrayList<SchemaNodeInfo>();
		
		SchemaNodeInfo nodeInfo = new SchemaNodeInfo();
		nodeInfo.setType(type);
		nodeInfo.setNodeId(0 + "");
		nodeInfo.setCode(key);
		nodeInfo.setDescription((String)value.get("description"));
		parseSchemaNode(result,nodeInfo, value);
		
		return JsonUtils.toJson(result);
	}
	
	//组装对象schema
	private static void parseSchemaNode(List<SchemaNodeInfo> result,SchemaNodeInfo nodeInfo,Map<String, Object> properties){
		switch (nodeInfo.getType()) {
			case sys_object:
				parseObjectNode(result,nodeInfo,properties);
				break;
				
			case sys_array:
				parseArrayNode(result,nodeInfo,properties);
				break;
			
			case sys_ref:
				break;
				
			case cust_json:
				parseCustNode(result,nodeInfo,properties);
				break;
				
			default:
				break;
		}
	}
	
	//判断是否是复合结构
	private static boolean isComplex(SchemaType type){
		return (SchemaType.sys_object == type 
				|| SchemaType.sys_array == type
				|| SchemaType.sys_ref == type
				|| SchemaType.cust_json == type);
	}
	
	//组装对象schema
	private static void parseObjectNode(List<SchemaNodeInfo> result,SchemaNodeInfo nodeInfo,Map<String, Object> map){
		Map<String, Object> properties = (Map)map.get("properties");
		int childId = Integer.parseInt(nodeInfo.getNodeId()) * 100 + 10;
		Set<String> keySet = properties.keySet();
		SchemaNodeInfo temp = null;
		Map<String, Object> info = null;
		SchemaType type = null;
		for (String key : keySet) {
			info = (Map)properties.get(key);
			type = SchemaType.getByType((String)info.get("type"));
			
			temp = new SchemaNodeInfo();
			temp.setNodeId(childId + "");
			temp.setCode(key);
			temp.setDescription((String)info.get("description"));
			temp.setType(type);
			temp.setParentId(nodeInfo.getNodeId());
			if (isComplex(type)) {
				parseSchemaNode(result,temp, info);
			}
			else{
				result.add(temp);
			}
			
			childId ++;
		}
	}
	
	//组装数组schema
	private static void parseArrayNode(List<SchemaNodeInfo> result,SchemaNodeInfo nodeInfo,Map<String, Object> map){
		int childId = Integer.parseInt(nodeInfo.getNodeId()) * 100 + 10;
		Map<String,Object> items = (Map)map.get("items");
		Set<String> keySet = items.keySet();
		SchemaNodeInfo temp = null;
		Map<String, Object> info = null;
		SchemaType type = null;
		for (String key : keySet) {
			info = (Map)items.get(key);
			type = SchemaType.getByType((String)info.get("type"));
			
			temp = new SchemaNodeInfo();
			temp.setNodeId(childId + "");
			temp.setCode(key);
			temp.setDescription((String)info.get("description"));
			temp.setType(type);
			temp.setParentId(nodeInfo.getNodeId());
			if (isComplex(type)) {
				parseSchemaNode(result,temp, info);
			}
			else{
				result.add(temp);
			}
			
			childId ++;
		}
	}
	
	//组装自定义schema
	private static void parseCustNode(List<SchemaNodeInfo> result,SchemaNodeInfo nodeInfo,Map<String, Object> map){
		nodeInfo.setExtSchema((String)map.get("content"));
	}
	
	//将列表转换为字符串
	private static String parseList(List<String> paramList){
		StringBuilder listBuilder = new StringBuilder();
		if (CollectionUtils.isEmpty(paramList)) {
			return listBuilder.toString();
		}
		
		int size = paramList.size();
		for (int i = 0; i < size; i++) {
			listBuilder.append(paramList.get(i));
			if (i != (size - 1)) {
				listBuilder.append(",");
			}
		}
		
		return listBuilder.toString();
	}
}
