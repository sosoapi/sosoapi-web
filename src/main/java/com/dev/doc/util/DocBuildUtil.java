package com.dev.doc.util;

import io.swagger.models.ArrayModel;
import io.swagger.models.CustModel;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.RefModel;
import io.swagger.models.Response;
import io.swagger.models.Scheme;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.CookieParameter;
import io.swagger.models.parameters.FormParameter;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.NestProperty;
import io.swagger.models.properties.ObjectProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.PropertyBuilder;
import io.swagger.models.properties.PropertyBuilder.PropertyId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.dev.base.enums.ReqMethod;
import com.dev.base.enums.SchemaType;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.vo.SchemaNodeInfo;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: api文档生成工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月15日上午10:45:45</p>
 */
public class DocBuildUtil {
	//空json对象
	private static final String EMPTY_JSON = "{}";
	
	/**
	 * 
			*@name 根据自定义结构组装树结构
			*@Description  
			*@CreateDate 2015年8月15日上午10:46:37
	 */
	public static SchemaNodeInfo buildTreeNode(String custSchema){
		SchemaNodeInfo tree = new SchemaNodeInfo();
		
		List<SchemaNodeInfo> rootList = new ArrayList<SchemaNodeInfo>();
		if (!StringUtils.isEmpty(custSchema)) {
			List<SchemaNodeInfo> nodeList = JsonUtils.toObject(custSchema,new TypeReference<List<SchemaNodeInfo>>(){});
			Map<String,SchemaNodeInfo> nodeMap = MapUtils.newMap();
			for (SchemaNodeInfo nodeInfo : nodeList) {
				nodeMap.put(nodeInfo.getNodeId(), nodeInfo);
				if (StringUtils.isEmpty(nodeInfo.getParentId())) {
					rootList.add(nodeInfo);
				}
				else{
					nodeMap.get(nodeInfo.getParentId())
						   .getChildList()
						   .add(nodeInfo);
				}
			}
		}
		
		tree.setChildList(rootList);
		
		return tree;
	}
	
	/**
	 * 
			*@name 根据接口响应组装树结构
			*@Description  
			*@CreateDate 2015年8月15日上午10:46:37
	 */
	public static SchemaNodeInfo buildTreeNode(InterResp resp){
		SchemaNodeInfo tree = buildTreeNode(resp.getCustSchema());
		tree.setType(resp.getType());
		tree.setCode(resp.getCode());
		tree.setDescription(resp.getDescription());
		tree.setExtSchema(resp.getExtSchema());
		tree.setName(resp.getName());
		tree.setRefSchemaId(resp.getRefSchemaId());
		
		return tree;
	}
	
	/**
	 * 
			*@name 根据数据结构组装树结构
			*@Description  
			*@CreateDate 2015年8月15日上午10:46:37
	 */
	public static SchemaNodeInfo buildTreeNode(RespSchema schema){
		SchemaNodeInfo tree = buildTreeNode(schema.getCustSchema());
		tree.setType(schema.getType());
		tree.setCode(schema.getCode());
		tree.setDescription(schema.getDescription());
		tree.setName(schema.getName());
		tree.setRefSchemaId(schema.getRefSchemaId());
		
		return tree;
	}
	
	/**
	 * 
			*@name 根据自定义结构组装swagger model
			*@Description  
			*@CreateDate 2015年8月15日上午11:00:53
	 */
	public static Model buildModel(RespSchema respSchema,Map<Long, String> refSchemaMap){
		SchemaNodeInfo tree = buildTreeNode(respSchema);
		tree.setCode(respSchema.getCode());
		tree.setName(respSchema.getName());
		tree.setDescription(respSchema.getDescription());
		tree.setRefSchemaId(respSchema.getRefSchemaId());

		return parseModel(tree,refSchemaMap);
	}
	
	/**
	 * 
			*@name 组装响应信息
			*@Description  
			*@CreateDate 2015年8月15日下午4:10:39
	 */
	public static Property buildProperty(InterResp interResp,Map<Long, String> refSchemaMap){
		SchemaType type = interResp.getType();
		SchemaNodeInfo tree = buildTreeNode(interResp);
		
		if (SchemaType.cust_json == type) {
			tree.setExtSchema(getCustJsonSchema(interResp.getExtSchema()));
		}

		return parseProperty(tree,refSchemaMap);
	}
	
	/**
	 * 
			*@name 组装方法
			*@Description  
			*@CreateDate 2015年8月15日下午2:33:06
	 */
	public static Path buildPath(Path path,ReqMethod reqMethod,Operation operation){
		if (path == null) {
			path = new Path();
		}

		switch (reqMethod) {
			case GET:
				path.setGet(operation);
				break;
				
			case POST:
				path.setPost(operation);
				break;
				
			case DELETE:
				path.setDelete(operation);
				break;
				
			case PUT:
				path.setPut(operation);
				break;
				
			default:
				break;
		}
		
		return path;
	}
	
	/**
	 * 
			*@name 组装操作
			*@Description  
			*@CreateDate 2015年9月7日下午10:38:29
	 */
	public static Operation buildOperation(Inter inter,List<InterParam> reqParamList,List<InterResp> respList,
							Map<Long, String> moduleMap,Map<Long, String> refSchemaMap){
		Operation operation = new Operation();
		operation.setConsumes(buildConsumeList(inter.getConsume()));
		operation.setProduces(buildProduceList(inter.getProduce()));
		operation.setSummary(inter.getName());
		operation.setDescription(inter.getDescription());
		operation.setDeprecated(inter.isDeprecated());
		//组装请求方式
		operation.setSchemes(buildSchemeList(inter.getScheme().name()));
		//组装分类
		operation.setTags(parseTags(inter, moduleMap));
		//组装请求参数
		operation.setParameters(parseParameter(reqParamList,refSchemaMap));
		//组装响应
		operation.setResponses(parseResponse(respList,refSchemaMap));
		//组装排序权重
		operation.setSortWeight(inter.getSortWeight());
		
		return operation;
	}
	
	//组装请求参数
	private static List<Parameter> parseParameter(List<InterParam> reqParamList,Map<Long, String> refSchemaMap){
		List<Parameter> result = new ArrayList<Parameter>();
		Parameter parameter = null;
		for (InterParam reqParam : reqParamList) {
			switch (reqParam.getPosition()) {
				case body:
					parameter = parseBodyParameter(reqParam,refSchemaMap);
					break;
					
				case cookie:
					parameter = parseCookieParameter(reqParam,refSchemaMap);
					break;
					
				case formData:
					parameter = parseFormDataParameter(reqParam,refSchemaMap);
					break;
					
				case header:
					parameter = parseHeaderParameter(reqParam,refSchemaMap);
					break;
					
				case path:
					parameter = parsePathParameter(reqParam,refSchemaMap);
					break;
					
				case query:
					parameter = parseQueryParameter(reqParam,refSchemaMap);
					break;
					
				default:
					break;
			}
			
			result.add(parameter);
		}
		
		return result;
	}
	
	//组装body类型的请求参数
	private static Parameter parseBodyParameter(InterParam reqParam,Map<Long, String> refSchemaMap){
		SchemaType type = reqParam.getType();
		BodyParameter bodyParameter = new BodyParameter();
		bodyParameter.setName(reqParam.getCode());
		bodyParameter.setRequired(reqParam.isRequired());
		bodyParameter.setDescription(reqParam.getDescription());
		
		if (type == SchemaType.sys_ref) {
			String code = refSchemaMap.get(reqParam.getRefSchemaId());
			bodyParameter.setSchema(new RefModel(getRefSchemaCode(code)));
		}
		
		if (type == SchemaType.cust_json) {
			CustModel model = new CustModel();
			model.setType(type.getType());
			model.setFormat(type.getFormat());
			model.setContent(getCustJsonSchema(reqParam.getExtSchema()));
			bodyParameter.setSchema(model);
		}
		
		return bodyParameter;
	}
	
	//组装cookie类型的请求参数
	private static Parameter parseCookieParameter(InterParam reqParam,Map<Long, String> refSchemaMap){
		SchemaType type = reqParam.getType();
		CookieParameter cookieParameter = new CookieParameter();
		cookieParameter.setDefaultValue(reqParam.getDefValue());
		cookieParameter.setDescription(reqParam.getDescription());
		cookieParameter.setRequired(reqParam.isRequired());
		cookieParameter.setType(type.getType());
		cookieParameter.setFormat(type.getFormat());
		cookieParameter.setName(reqParam.getCode());
		// TODO 
//		cookieParameter.setEnum(null);
//		cookieParameter.setItems(null);
		
		return cookieParameter;
	}
	
	//组装formData类型的请求参数
	private static Parameter parseFormDataParameter(InterParam reqParam,Map<Long, String> refSchemaMap){
		SchemaType type = reqParam.getType();
		
		FormParameter formParameter = new FormParameter();
		formParameter.setName(reqParam.getCode());
		formParameter.setType(type.getType());
		formParameter.setFormat(type.getFormat());
		formParameter.setDescription(reqParam.getDescription());
		formParameter.setRequired(reqParam.isRequired());
		formParameter.setDefaultValue(reqParam.getDefValue());
		
		// TODO 
//		formParameter.setEnum(null);
//		formParameter.setItems(null);
		
		return formParameter;
	}
	
	//组装header类型的请求参数
	private static Parameter parseHeaderParameter(InterParam reqParam,Map<Long, String> refSchemaMap){
		SchemaType type = reqParam.getType();
		
		HeaderParameter headerParameter = new HeaderParameter();
		headerParameter.setDefaultValue(reqParam.getDefValue());
		headerParameter.setDescription(reqParam.getDescription());
		headerParameter.setFormat(type.getFormat());
		headerParameter.setType(type.getType());
		headerParameter.setName(reqParam.getCode());
		headerParameter.setRequired(reqParam.isRequired());
		
		// TODO 
//		headerParameter.setEnum(null);
//		headerParameter.setProperty(null);
		
		return headerParameter;
	}
	
	//组装path类型的请求参数
	private static Parameter parsePathParameter(InterParam reqParam,Map<Long, String> refSchemaMap){
		SchemaType type = reqParam.getType();
		
		PathParameter pathParameter = new PathParameter();
		pathParameter.setDefaultValue(reqParam.getDefValue());
		pathParameter.setDescription(reqParam.getDescription());
		pathParameter.setFormat(type.getFormat());
		pathParameter.setType(type.getType());
		pathParameter.setName(reqParam.getCode());
		pathParameter.setRequired(reqParam.isRequired());
		
		// TODO 
//		pathParameter.setEnum(null);
//		pathParameter.setProperty(null);

		return pathParameter;
	}
	
	//组装query类型的请求参数
	private static Parameter parseQueryParameter(InterParam reqParam,Map<Long, String> refSchemaMap){
		SchemaType type = reqParam.getType();
		
		QueryParameter queryParameter = new QueryParameter();
		queryParameter.setDefaultValue(reqParam.getDefValue());
		queryParameter.setDescription(reqParam.getDescription());
		queryParameter.setFormat(type.getFormat());
		queryParameter.setType(type.getType());
		queryParameter.setName(reqParam.getCode());
		queryParameter.setRequired(reqParam.isRequired());
		// TODO 
//		queryParameter.setEnum(null);
//		queryParameter.setProperty(null);

		return queryParameter;
	}
	
	//组装响应
	private static Map<String,Response> parseResponse(List<InterResp> respList,Map<Long, String> refSchemaMap){
		Map<String,Response> result = MapUtils.newMap();
		Response response = null;
		Property property = null;
		for (InterResp interResp : respList) {
			response = new Response();
			response.setSortWeight(interResp.getSortWeight());
			response.setDescription(interResp.getDescription());
			
			if (isComplexSchema(interResp.getType())){
				property = buildProperty(interResp,refSchemaMap);
				response.setSchema(property);
			}
			
			result.put(interResp.getCode(), response);
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 判断是否是复杂类型
			*@Description  
			*@CreateDate 2016年1月14日下午1:56:58
	 */
	public static boolean isComplexSchema(SchemaType type){
		return (type == SchemaType.sys_array
				|| type == SchemaType.sys_object
				|| type == SchemaType.sys_ref
				|| type == SchemaType.cust_json);
	}
	
	//组装分类
	private static List<String> parseTags(Inter inter,Map<Long, String> moduleMap){
		List<String> result = new ArrayList<String>();
		String moduleName = moduleMap.get(inter.getModuleId());
		if (!StringUtils.isEmpty(moduleName)) {
			result.add(moduleName);
		}
		
		return result;
	}
	
	//根据节点信息组装model
	private static Model parseModel(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		Model model = null;
		SchemaType type = nodeInfo.getType();
		switch (type) {
			case sys_object:
				model = parseObjectModel(nodeInfo,refSchemaMap);
				break;
				
			case sys_array:
				model = parseArrayModel(nodeInfo,refSchemaMap);
				break;
				
			default:
				model = parseSimpleModel(nodeInfo,refSchemaMap);
				break;
		}
		
		return model;
	}
	
	//组装数组
	private static Model parseArrayModel(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		SchemaType type =nodeInfo.getType();
		
		ArrayModel model = new ArrayModel();
		model.setType(type.getType());
		model.setDescription(nodeInfo.getDescription());
		
		int childSize = nodeInfo.getChildList().size();
		if (childSize == 0) {
			model.setItems(new ObjectProperty());
		}
		else if (childSize == 1) {
			model.setItems(parseProperty(nodeInfo.getChildList().get(0),refSchemaMap));
		}
		else if (childSize > 1) {
			NestProperty nestProperty = new NestProperty();
			nestProperty.setTitle(nodeInfo.getCode());
			List<SchemaNodeInfo> childList = nodeInfo.getChildList();
			for (SchemaNodeInfo child : childList) {
				nestProperty.getProperties().put(child.getCode(),parseProperty(child, refSchemaMap));
			}
			model.setItems(nestProperty);
		}
		
		return model;
	}
	
	//组装对象
	private static Model parseObjectModel(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		SchemaType type =nodeInfo.getType();
		
		Map<String, Property> properties = MapUtils.newMap();
		List<SchemaNodeInfo> childList = nodeInfo.getChildList();
		for (SchemaNodeInfo temp : childList) {
			properties.put(temp.getCode(), parseProperty(temp,refSchemaMap));
		}
		
		ModelImpl model = new ModelImpl();
		model.setType(type.getType());
		model.setFormat(getFormat(type));
		model.setName(nodeInfo.getName());
		model.setDescription(nodeInfo.getDescription());
		model.setProperties(properties);
		
		return model;
	}
	
	//组装简单数据类型
	private static Model parseSimpleModel(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		return PropertyBuilder.toModel(parseSimpleProperty(nodeInfo,refSchemaMap));
	}
		
	//根据节点信息组装property
	private static Property parseProperty(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		Property property = null;
		SchemaType type = nodeInfo.getType();
		switch (type) {
			case sys_object:
				property = parseObjectProperty(nodeInfo,refSchemaMap);
				break;
				
			case sys_array:
				property = parseArrayProperty(nodeInfo,refSchemaMap);
				break;
				
			default:
				property = parseSimpleProperty(nodeInfo,refSchemaMap);
				break;
		}
		
		return property;
	}
		
	//组装嵌套结构
	private static NestProperty parseNestProperty(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		SchemaType type = nodeInfo.getType();
		
		NestProperty property = new NestProperty();
		property.setDescription(nodeInfo.getDescription());
		property.setName(nodeInfo.getCode());
		property.setType(type.getType());
		property.setFormat(type.getFormat());
		
		List<SchemaNodeInfo> childList = nodeInfo.getChildList();
		for (SchemaNodeInfo child : childList) {
			property.getProperties().put(child.getCode(),parseProperty(child, refSchemaMap));
		}
		
		return property;
	}
	
	//组装数组
	private static Property parseArrayProperty(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		SchemaType type = nodeInfo.getType();
		
		ArrayProperty property = new ArrayProperty();
		property.setDescription(nodeInfo.getDescription());
		property.setName(nodeInfo.getCode());
		property.setType(type.getType());
		property.setFormat(type.getFormat());
		
		int childSize = nodeInfo.getChildList().size();
		if (childSize == 0) {
			property.setItems(new ObjectProperty());
		}
		else if (childSize == 1) {
			property.setItems(parseProperty(nodeInfo.getChildList().get(0),refSchemaMap));
		}
		else if (childSize > 1) {
			NestProperty nestProperty = new NestProperty();
			nestProperty.setTitle(nodeInfo.getCode());
			List<SchemaNodeInfo> childList = nodeInfo.getChildList();
			for (SchemaNodeInfo child : childList) {
				nestProperty.getProperties().put(child.getCode(),parseProperty(child, refSchemaMap));
			}
			property.setItems(nestProperty);
		}
		
		return property;
	}
		
	//组装对象
	private static Property parseObjectProperty(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		Property result = parseNestProperty(nodeInfo, refSchemaMap);
		result.setTitle(nodeInfo.getCode());
		
		return result;
	}
		
	//组装简单类型
	private static Property parseSimpleProperty(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		SchemaType schemaType = nodeInfo.getType();
		return PropertyBuilder.build(schemaType.getType(),getFormat(schemaType), parsePropertyParam(nodeInfo,refSchemaMap));
	}
	
	//组装创建property需要的信息
	private static Map<PropertyId,Object> parsePropertyParam(SchemaNodeInfo nodeInfo,Map<Long, String> refSchemaMap){
		SchemaType type = nodeInfo.getType();
		
		Map<PropertyId,Object> result = MapUtils.newMap();
		result.put(PropertyId.DESCRIPTION, nodeInfo.getDescription());
		result.put(PropertyId.TYPE, type.getType());
		result.put(PropertyId.FORMAT, getFormat(type));
		
		if (SchemaType.cust_json == type) {
			result.put(PropertyId.EXT_SCHEMA, getCustJsonSchema(nodeInfo.getExtSchema()));
		}
		
		//如果是引用类型，则设置引用属性
		if (SchemaType.sys_ref == type) {
			String code = refSchemaMap.get(nodeInfo.getRefSchemaId());
			result.put(PropertyId.REF, getRefSchemaCode(code));
		}
		
		return result;
	}
	
	//获取自定义的json数据
	private static String getCustJsonSchema(String extSchema){
		return StringUtils.isEmpty(extSchema) ? EMPTY_JSON : extSchema;
	}
	
	//获取引用的schema编码
	private static String getRefSchemaCode(String code){
		return StringUtils.isEmpty(code) ? "" : code;
	}
	
	//获取swagger 类型格式
	private static String getFormat(SchemaType type){
		return StringUtils.isEmpty(type.getFormat()) ? null : type.getFormat();
	}
	
	/**
	 * 
			*@name 解析scheme列表
			*@Description  
			*@CreateDate 2015年8月15日上午10:50:44
	 */
	public static List<Scheme> buildSchemeList(String scheme){
		List<Scheme> result = new ArrayList<Scheme>();
		List<String> list = parseList(scheme);
		for (String temp : list) {
			result.add(Scheme.valueOf(temp));
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 组装客户端请求数据方式
			*@Description  
			*@CreateDate 2015年8月15日上午10:50:34
	 */
	public static List<String> buildConsumeList(String consume){
		return parseList(consume);
	}
	
	/**
	 * 
			*@name 组装服务端端响应数据方式
			*@Description  
			*@CreateDate 2015年8月15日上午10:50:24
	 */
	public static List<String> buildProduceList(String produce){
		return parseList(produce);
	}
	
	/**
	 * 
			*@name 将以","分隔的字符串转换为list
			*@Description  
			*@CreateDate 2015年8月15日上午10:50:12
	 */
	public static List<String> parseList(String value){
		List<String> result = new ArrayList<String>();
		if (StringUtils.isEmpty(value)) {
			return result;
		}
		
		String[] valueArray = value.split(",");
		for (String temp : valueArray) {
			result.add(temp);
		}
		
		return result;
	}
}
