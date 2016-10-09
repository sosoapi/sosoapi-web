package com.dev.doc.service.impl;

import io.swagger.models.Info;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.InterParamService;
import com.dev.doc.service.InterRespService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.service.SwaggerService;
import com.dev.doc.util.DocBuildUtil;
import com.dev.doc.util.DocExportUtil;

/**
 * 
		* <p>Title: swagger api文档相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月13日下午2:21:31</p>
 */
@Service
public class SwaggerServiceImpl implements SwaggerService{
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private InterParamService interParamService;
	
	@Autowired
	private InterRespService interRespService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Override
	public Swagger buildApiDoc(Long userId, Long docId) {
		Swagger swagger = new Swagger();
		ApiDoc apiDoc = apiDocService.getById(docId);
		if (apiDoc == null) {
			return swagger;
		}
		
		//公共响应信息
		Map<Long, String> refSchemaMap = respSchemaService.getAllByDocId(docId);
		
		//查询所有的模块信息列表
		List<Module> moduleList = moduleService.listAllByDocId(docId);
		//模块信息
		Map<Long, String> moduleMap = getModuleMap(moduleList);
				
		//创建文档基本信息
		createBasicInfo(swagger, apiDoc);
		
		//创建公用数据结构
		createDefinition(swagger,docId,refSchemaMap);
		
		//创建文档标签信息
		createTags(swagger,moduleList);
		
		//创建方法
		createPath(swagger,docId,refSchemaMap,moduleMap);
		
		return swagger;
	}
	
	//创建文档标签信息
	private void createTags(Swagger swagger,List<Module> moduleList){
		List<Tag> tagList = new ArrayList<Tag>();
		Tag tag = null;
		for (Module module : moduleList) {
			tag = new Tag();
			tag.setName(module.getName());
			tag.setDescription(module.getDescription());
			tag.setSortWeight(module.getSortWeight());
			tagList.add(tag);
		}
		
		swagger.setTags(tagList);
	}
	
	//创建文档基本信息
	private void createBasicInfo(Swagger swagger,ApiDoc apiDoc){
		swagger.setHost(apiDoc.getHost());
		swagger.setBasePath(apiDoc.getBasePath());
		swagger.setSchemes(DocBuildUtil.buildSchemeList(apiDoc.getScheme()));
		swagger.setConsumes(DocBuildUtil.buildConsumeList(apiDoc.getConsume()));
		swagger.setProduces(DocBuildUtil.buildProduceList(apiDoc.getProduce()));
		
		Info info = new Info();
		info.setDescription(apiDoc.getDescription());
		info.setTitle(apiDoc.getTitle());
		info.setVersion(apiDoc.getVersion());
		swagger.setInfo(info);
	}
	
	//创建公用数据结构
	private void createDefinition(Swagger swagger,Long docId,Map<Long, String> refSchemaMap){
		List<RespSchema> schemaList = respSchemaService.listAllByDocId(docId);
		Map<String, Model> definitionMap = MapUtils.newMap();
		Model model = null;
		for (RespSchema respSchema : schemaList) {
			model = DocBuildUtil.buildModel(respSchema,refSchemaMap);
			definitionMap.put(respSchema.getCode(), model);
		}

		swagger.setDefinitions(definitionMap);
	}
	
	//创建方法
	private void createPath(Swagger swagger,Long docId,Map<Long, String> refSchemaMap,Map<Long, String> moduleMap){
		List<Inter> interList = interService.listAllByDocId(docId,false);
		//获取文档所有的接口请求参数信息
		Map<Long, List<InterParam>> paramInfoMap = getInterParamInfo(docId);
		//获取文档所有的接口请求响应参数
		Map<Long, List<InterResp>> respInfoMap = getInterRespInfo(docId);
		
		LinkedHashMap<String,Path> pathMap = new LinkedHashMap<String,Path>();
		List<InterParam> reqParamList = null;
		List<InterResp> respList = null;
		Long interId = null;
		Path path = null;
		Operation operation = null;
		for (Inter inter : interList) {
			interId = inter.getId();
			reqParamList = paramInfoMap.containsKey(interId) ? paramInfoMap.get(interId) : Collections.EMPTY_LIST;
			respList = respInfoMap.containsKey(interId) ? respInfoMap.get(interId) : Collections.EMPTY_LIST;
			
			operation = DocBuildUtil.buildOperation(inter, reqParamList, respList, moduleMap,refSchemaMap);
			path = DocBuildUtil.buildPath(pathMap.get(inter.getPath()),inter.getMethod(),operation);

			pathMap.put(inter.getPath(), path);
		}

		swagger.setPaths(pathMap);
	}
	
	//查询文档相关的所有接口请求参数，接口id为key
	private Map<Long, List<InterParam>> getInterParamInfo(Long docId){
		List<InterParam> paramList = interParamService.listAllByDocId(docId);
		Map<Long, List<InterParam>> result = MapUtils.newMap();
		Long interId = -1L;
		for (InterParam interParam : paramList) {
			interId = interParam.getInterId();
			if (result.containsKey(interId)) {
				result.get(interId).add(interParam);
			}
			else{
				List<InterParam> temp = new ArrayList<InterParam>();
				temp.add(interParam);
				result.put(interId, temp);
			}
		}
		
		return result;
	}
	
	//查询文档相关的所有接口请求响应，接口id为key
	private Map<Long, List<InterResp>> getInterRespInfo(Long docId){
		List<InterResp> respList = interRespService.listAllByDocId(docId);
		Map<Long, List<InterResp>> result = MapUtils.newMap();
		Long interId = -1L;
		for (InterResp interResp : respList) {
			interId = interResp.getInterId();
			if (result.containsKey(interId)) {
				result.get(interId).add(interResp);
			}
			else{
				List<InterResp> temp = new ArrayList<InterResp>();
				temp.add(interResp);
				result.put(interId, temp);
			}
		}
		
		return result;
	}
	
	//组装模块信息,key:id,value:name
	private Map<Long, String> getModuleMap(List<Module> moduleList){
		Map<Long, String> moduleMap = MapUtils.newMap();
		for (Module module : moduleList) {
			moduleMap.put(module.getId(), module.getName());
		}
		
		return moduleMap;
	}

	@Override
	public Map<String, Object> buildDocTmplData(Long userId, Long docId) {
		Map<String, Object> tmplData = MapUtils.newMap();
		ApiDoc apiDoc = apiDocService.getById(docId);
		if (apiDoc == null) {
			return tmplData;
		}
		
		//组装基本信息
		buildBasicInfoTmplData(tmplData,apiDoc);
		
		//组装接口信息
		buildInterTmplData(tmplData,docId);
		
		return tmplData;
	}
	
	//组装基本信息
	private void buildBasicInfoTmplData(Map<String, Object> tmplData,ApiDoc apiDoc){
		Map<String, Object> basicInfo = MapUtils.newMap();
		basicInfo.put("title", apiDoc.getTitle());
		basicInfo.put("version", apiDoc.getVersion());
		basicInfo.put("description", apiDoc.getDescription());
		basicInfo.put("host", apiDoc.getHost());
		basicInfo.put("basePath", apiDoc.getBasePath());
		
		tmplData.put("basicInfo", basicInfo);
	}
	
	//组装接口信息
	private void buildInterTmplData(Map<String, Object> tmplData,Long docId){
		//公共数据列表
		List<RespSchema> schemaList = respSchemaService.listAllByDocId(docId);
		
		//模块信息列表
		List<Module> moduleList = moduleService.listAllByDocId(docId);
		
		//接口列表
		List<Inter> interList = interService.listAllByDocId(docId,false);
		
		//请求信息列表
		Map<Long, List<InterParam>> paramInfoMap = getInterParamInfo(docId);
		
		//响应信息列表
		Map<Long, List<InterResp>> respInfoMap = getInterRespInfo(docId);
		
		//接口模板数据
		Map<String, List<Map>> interTmplData = DocExportUtil.buildInterTmplData(moduleList, schemaList, 
															interList, paramInfoMap, respInfoMap);
		
		tmplData.put("moduleMap", interTmplData);
	}
	
}
