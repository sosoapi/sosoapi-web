package com.dev.doc.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.enums.ParamPosition;
import com.dev.base.enums.SchemaType;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.vo.SchemaNodeInfo;

/**
 * 
		* <p>Title: api文档导出word工具</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月13日下午5:41:29</p>
 */
public class DocExportUtil {
	private static Logger logger = LogManager.getLogger(DocExportUtil.class);
	
	//默认模块名称
	private static final String DEF_MODULE_NAME = "default";
	
	//默认list对象在map中的key名称
	private static final String LIST_KEY_NAME = "def_list_key";
	
	/**
	 * 
			*@name 组装接口模板数据
			*@Description  
			*@CreateDate 2015年11月14日下午2:12:13
	 */
	public static Map<String, List<Map>> buildInterTmplData(List<Module> moduleList,
						List<RespSchema> schemaList,List<Inter> interList,
						Map<Long, List<InterParam>> paramInfoMap,Map<Long, List<InterResp>> respInfoMap){
		Map<String, List<Map>> interTmplData = initTmplData(moduleList);
		
		//模块信息
		Map<Long, String> moduleInfoMap = getModuleMap(moduleList);
		
		//公共响应信息
		Map<Long, Map> respSchema = initRespSchema(schemaList);
		
		List<InterParam> interParamList = null;
		List<InterResp> interRespList = null;
		Long interId = null;
		String moduleName = "";
		Map<String, Object> tmplData = null;
		for (Inter inter : interList) {
			interId = inter.getId();
			interParamList = paramInfoMap.get(interId);
			interRespList = respInfoMap.get(interId);
			moduleName = parseInterModuleName(inter, moduleInfoMap);
			
			tmplData = buildInterTmplData(inter, interParamList, interRespList, respSchema);
			interTmplData.get(moduleName).add(tmplData);
		}
		
		if (interTmplData.get(DEF_MODULE_NAME).isEmpty()) {
			interTmplData.remove(DEF_MODULE_NAME);
		}
		
		return interTmplData;
	}
	
	//初始化模块列表
	//key:模块名称
	//value:该模块下的接口信息列表
	private static Map<String, List<Map>> initTmplData(List<Module> moduleList){
		Map<String, List<Map>> result = new LinkedHashMap<String, List<Map>>();
		for (Module module : moduleList) {
			result.put(module.getName(), new ArrayList<Map>());
		}
		//默认模块，当接口未指定模块时，都归属于当前模块下
		result.put(DEF_MODULE_NAME, new ArrayList<Map>());
		
		return result;
	}
	
	//组装模块信息,key:id,value:name
	private static Map<Long, String> getModuleMap(List<Module> moduleList){
		Map<Long, String> moduleMap = MapUtils.newMap();
		for (Module module : moduleList) {
			moduleMap.put(module.getId(), module.getName());
		}
		
		return moduleMap;
	}
	
	//获取接口对应的模块名称
	private static String parseInterModuleName(Inter inter,Map<Long, String> moduleInfoMap){
		String result = moduleInfoMap.get(inter.getModuleId());
		
		return StringUtils.isEmpty(result) ? DEF_MODULE_NAME : result;
	}
	
	//初始化公共模块
	//将模块间的引用用对应的信息替换
	public static Map<Long, Map> initRespSchema(List<RespSchema> schemaList){
		//保存公共响应id和节点关系
		Map<Long, SchemaNodeInfo> nodeInfoMap = MapUtils.newMap();
		//保存公共响应id和关联的节点关系
		Map<Long, Set<Long>> nodeRefMap = MapUtils.newMap();
		List<Long> respSchemaIdList = new ArrayList<Long>();
		SchemaNodeInfo tree = null;
		Set<Long> refIdSet = null;
		Long respSchemaId = -1L;
		for (RespSchema respSchema : schemaList) {
			respSchemaId = respSchema.getId();
			tree = DocBuildUtil.buildTreeNode(respSchema);
			
			refIdSet = tree.getChildRefSchemaIdList();
			
			nodeInfoMap.put(respSchemaId, tree);

			if (!CollectionUtils.isEmpty(refIdSet)) {
				nodeRefMap.put(respSchemaId, refIdSet);
			}
			
			respSchemaIdList.add(respSchemaId);
		}
		
		//根据依赖关系返回对应的顺序
		List<Long> sortList = GraphDFSUtil.topo(respSchemaIdList, nodeRefMap);
		logger.debug("sortList:" + JsonUtils.toJson(sortList));
		
		return buildRespSchemaTmplData(sortList,nodeInfoMap);
	}
	
	//组装公共响应数据
	private static Map<Long, Map> buildRespSchemaTmplData(List<Long> sortList,Map<Long, SchemaNodeInfo> nodeInfoMap){
		Map<Long, Map> result = MapUtils.newMap();
		SchemaNodeInfo nodeInfo = null;
		Map<String, Object> tmplData = null;
		for (Long respSchemaId : sortList) {
			nodeInfo = nodeInfoMap.get(respSchemaId);
			tmplData = parseNodeInfo(nodeInfo,result);
			if (nodeInfo.getType() == SchemaType.sys_array) {
				Object temp = tmplData.get(nodeInfo.getCode());
				tmplData.remove(nodeInfo.getCode());
				tmplData.put(LIST_KEY_NAME, temp);
			}
			
			result.put(respSchemaId, tmplData);
		}
		
		return result;
	}
	
	private static Map<String, Object> parseNodeInfo(SchemaNodeInfo nodeInfo,Map<Long, Map> dealedMap){
		Map<String, Object> tmplData = MapUtils.newMap();
		
		SchemaType type = nodeInfo.getType();
		switch (type) {
			case sys_object:
				tmplData = parseObjectNodeInfo(nodeInfo, dealedMap);
				break;
				
			case sys_ref:
				tmplData = parseRefNodeInfo(nodeInfo, dealedMap);
				break;
				
			case sys_array:
				tmplData = parseArrayNodeInfo(nodeInfo, dealedMap);
				break;
				
			case cust_json:
				tmplData = parseCustJsonNodeInfo(nodeInfo, dealedMap);
				break;
				
			default:
				tmplData = parseSimpleNodeInfo(nodeInfo, dealedMap);
				break;
		}
		
		return tmplData;
	}
	
	//组装简单节点
	private static Map<String, Object> parseSimpleNodeInfo(SchemaNodeInfo nodeInfo,Map<Long, Map> dealedMap){
		StringBuilder nodeBuilder = new StringBuilder();
		if (!StringUtils.isEmpty(nodeInfo.getName())) {
			nodeBuilder.append(nodeInfo.getName())
					   .append(",");
		}
		nodeBuilder.append(nodeInfo.getType().getCode());
		if (!StringUtils.isEmpty(nodeInfo.getDescription())) {
			nodeBuilder.append(",")
					   .append(nodeInfo.getDescription());
		}
				   
		Map<String, Object> tmplData = MapUtils.newMap();
		tmplData.put(nodeInfo.getCode(), nodeBuilder.toString());
		return tmplData;
	}
	
	//组装引用节点
	private static Map<String, Object> parseRefNodeInfo(SchemaNodeInfo nodeInfo,Map<Long, Map> dealedMap){
		Map<String, Object> tmplData = MapUtils.newMap();
		Map<String,Object> temp = dealedMap.get(nodeInfo.getRefSchemaId());
		if (CollectionUtils.isEmpty(temp)) {
			return tmplData;
		}
		if (temp.containsKey(LIST_KEY_NAME)) {//处理引用是数组情况
			tmplData.put(nodeInfo.getCode(), temp.get(LIST_KEY_NAME));
		}
		else {
			tmplData.put(nodeInfo.getCode(), temp);
		}
		
		return tmplData;
	}
	
	//组装对象节点
	private static Map<String, Object> parseObjectNodeInfo(SchemaNodeInfo nodeInfo,Map<Long, Map> dealedMap){
		Map<String, Object> tmplData = MapUtils.newMap();
		List<SchemaNodeInfo> childNodeList = nodeInfo.getChildList();
		for (SchemaNodeInfo childNode : childNodeList) {
			if (SchemaType.sys_object == childNode.getType()) {
				tmplData.put(childNode.getCode(),parseNodeInfo(childNode, dealedMap));
			}
			else{
				tmplData.putAll(parseNodeInfo(childNode, dealedMap));
			}
		}
		
		return tmplData;
	}
	
	//组装数组节点
	private static Map<String, Object> parseArrayNodeInfo(SchemaNodeInfo nodeInfo,Map<Long, Map> dealedMap){
		Map<String, Object> tmplData = MapUtils.newMap();
		Map<String, Object> temp = MapUtils.newMap();
		List<SchemaNodeInfo> childNodeList = nodeInfo.getChildList();
		
		if (childNodeList.size() == 1) {//处理只有一个子元素情况
			SchemaNodeInfo childNode = childNodeList.get(0);
			SchemaType type = childNode.getType();
			if (DocBuildUtil.isComplexSchema(type)) {//复合类型
				Map nodeInfoMap = parseNodeInfo(childNode, dealedMap);
				if (SchemaType.sys_ref == type 
						&& !(nodeInfoMap.get(childNode.getCode()) instanceof List)) {//引用非数组
					temp.putAll((Map)nodeInfoMap.get(childNode.getCode()));
				}
				else{
					temp.putAll(nodeInfoMap);
				}
				
				List<Map> list = new ArrayList<Map>();
				list.add(temp);
				tmplData.put(nodeInfo.getCode(), list);
			}
			else {//简单类型
				List<String> list = new ArrayList<String>();
				list.add(type.getType());
				tmplData.put(nodeInfo.getCode(), list);
			}
		}
		else if(childNodeList.size() > 1){//处理多个子元素情况
			Map nodeInfoMap = null;
			for (SchemaNodeInfo childNode : childNodeList) {
				nodeInfoMap = parseNodeInfo(childNode, dealedMap);
				if (SchemaType.sys_ref == childNode.getType() 
						&& !(nodeInfoMap.get(childNode.getCode()) instanceof List)) {//引用非数组
					temp.putAll((Map)nodeInfoMap.get(childNode.getCode()));
				}
				else{
					temp.putAll(nodeInfoMap);
				}
			}
			
			List<Map> list = new ArrayList<Map>();
			list.add(temp);
			tmplData.put(nodeInfo.getCode(), list);
		}
		return tmplData;
	}
	
	//组装自定义json节点
	private static Map<String, Object> parseCustJsonNodeInfo(SchemaNodeInfo nodeInfo,Map<Long, Map> dealedMap){
		return parseCustJsonSchema(nodeInfo.getExtSchema());
	}
	
	//将自定义json结构转化为map
	private static Map<String, Object> parseCustJsonSchema(String extSchema){
		Map<String, Object> result = MapUtils.newMap();
		if (StringUtils.isEmpty(extSchema)) {
			return result;
		}
		
		if (extSchema.trim().startsWith("[")) {//数组
			result.put(LIST_KEY_NAME, JsonUtils.toObject(extSchema, List.class));
		}
		else {
			result = JsonUtils.toObject(extSchema, Map.class);
		}
		
		return result; 
	}
	
	//获取展示的自定义结构
	private static Object getCustJsonSchema(Map<String, Object> schemaInfo){
		return schemaInfo.containsKey(LIST_KEY_NAME) ? schemaInfo.get(LIST_KEY_NAME) : schemaInfo;
	}
	
	//组装接口模板数据
	private static Map<String, Object> buildInterTmplData(Inter inter,List<InterParam> interParamList,
										List<InterResp> interRespList,Map<Long, Map> respSchema){
		Map<String, Object> result = MapUtils.newMap();
		result.put("path", inter.getPath());
		result.put("scheme", inter.getScheme());
		result.put("method", inter.getMethod());
		result.put("summary", inter.getSummary());
		result.put("description", inter.getDescription());
		result.put("name", inter.getName());
		
		result.put("paramList", buildInterParamTmplData(interParamList, respSchema));
		result.put("respList", buildInterRespTmplData(interRespList, respSchema));
		
		return result;
	}
	
	//组装方法参数列表
	private static List<Map> buildInterParamTmplData(List<InterParam> interParamList,Map<Long, Map> respSchema){
		List<Map> paramList = new ArrayList<Map>();
		if (CollectionUtils.isEmpty(interParamList)) {
			return paramList;
		}
		
		Map<String,Object> paramInfo = null;
		SchemaType type = null;
		Object schema = null;
		for (InterParam interParam : interParamList) {
			type = interParam.getType();
			schema = null;
			
			paramInfo = MapUtils.newMap();
			paramInfo.put("code", interParam.getCode());
			paramInfo.put("description", interParam.getDescription());
			paramInfo.put("position", interParam.getPosition());
			paramInfo.put("type", type.getCode());
			
			//只有参数位置为body才需要处理复合类型cust和ref
			if (ParamPosition.body == interParam.getPosition()) {
				if (SchemaType.sys_ref == type) {
					schema = respSchema.get(interParam.getRefSchemaId());
				}
				else if (SchemaType.cust_json == type) {
					schema = getCustJsonSchema(parseCustJsonSchema(interParam.getExtSchema()));
				}
			}
			else{
				paramInfo.put("defValue", interParam.getDefValue());
			}
			
			if (schema != null) {
				paramInfo.put("schema", schema);
				paramInfo.put("schemaFormat", JsonUtils.toFormatJson(schema));
			}
			
			paramList.add(paramInfo);
		}
		
		return paramList;
	}
	
	//组装方法响应列表
	private static List<Map> buildInterRespTmplData(List<InterResp> interRespList,Map<Long, Map> respSchema){
		List<Map> respList = new ArrayList<Map>();
		if (CollectionUtils.isEmpty(interRespList)) {
			return respList;
		}
		
		Map<String,Object> respInfo = null;
		SchemaNodeInfo tree = null;
		SchemaType type = null;
		Object schema = null;
		for (InterResp interResp : interRespList) {
			type = interResp.getType();
			schema = null;
			
			respInfo = MapUtils.newMap();
			respInfo.put("code", interResp.getCode());
			respInfo.put("name", interResp.getName());
			respInfo.put("type", type);
			respInfo.put("description", interResp.getDescription());
			
			if (SchemaType.sys_ref == type) {
				schema = respSchema.get(interResp.getRefSchemaId());
			}
			else if(SchemaType.sys_object == type){
				tree = DocBuildUtil.buildTreeNode(interResp);
				schema = parseNodeInfo(tree, respSchema);
			}
			else if (SchemaType.sys_array == type) {
				tree = DocBuildUtil.buildTreeNode(interResp);
				schema = parseNodeInfo(tree, respSchema).get(interResp.getCode());
			}
			else if (SchemaType.cust_json == type) {
				schema = getCustJsonSchema(parseCustJsonSchema(interResp.getExtSchema()));
			}
			
			if (schema != null) {
				respInfo.put("schema", schema);
				respInfo.put("schemaFormat", JsonUtils.toFormatJson(schema));
			}
			
			respList.add(respInfo);
		}
		
		return respList;
	}
	
}
