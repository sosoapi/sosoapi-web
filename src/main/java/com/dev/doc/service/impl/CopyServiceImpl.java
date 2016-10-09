package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.Role;
import com.dev.base.enums.SchemaType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.CopyService;
import com.dev.doc.service.InterParamService;
import com.dev.doc.service.InterRespService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.vo.SchemaNodeInfo;
import com.dev.proj.entity.Project;
import com.dev.proj.entity.ProjectMember;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: 复制服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:37:33</p>
 */
@Service
public class CopyServiceImpl implements CopyService{
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectMemberService memberService;
	
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private InterParamService interParamService;
	
	@Autowired
	private InterRespService interRespService;
	
	@Transactional
	@Override
	public ProjectInfo copyProj(Long userId, Long projId) {
		Role role = memberService.getRole(userId, projId);
		//只有管理员才能复制项目
		ValidateUtils.isTrue(Role.admin == role, ErrorCode.SYS_006);
		
		//原项目
		Project srcProj = projectService.getById(projId);
		ApiDoc srcApiDoc = apiDocService.getByProjId(srcProj.getId());
		Long srcDocId = srcApiDoc.getId();
		
		//复制项目
		Project cloneProj = cloneProj(userId,srcProj);
		ApiDoc cloneApiDoc = cloneApiDoc(cloneProj.getId(),srcApiDoc);
		Long cloneDocId = cloneApiDoc.getId();
		
		//复制模块
		Map<Long,Long> moduleInfo = cloneModule(srcDocId, cloneDocId);
		
		//复制公共引用
		Map<Long, Long> respSchemaInfo = cloneRespSchema(srcDocId, cloneDocId, moduleInfo);
		
		//复制接口
		Map<Long, Long> interInfo = cloneInter(srcDocId, cloneDocId, moduleInfo);
				
		//复制请求参数
		cloneInterParam(srcDocId, cloneDocId, interInfo, respSchemaInfo);
		
		//复制接口响应
		cloneInterResp(srcDocId,cloneDocId,interInfo,respSchemaInfo);
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProjId(cloneProj.getId());
		projectInfo.setDocId(cloneDocId);
		return projectInfo;
	}
	
	//复制project
	private Project cloneProj(Long userId,Project srcProj){
		Project clone = (Project)cloneBean(srcProj);
		Date now = DateUtil.getNow();
		clone.setCreateDate(now);
		clone.setModifyDate(now);
		clone.setId(null);
		clone.setUserId(userId);
		clone.setName(clone.getName() + CfgConstants.COPY_FLAG);
		projectService.simpleAdd(clone);
		
		//绑定项目成员
		ProjectMember member = new ProjectMember();
		member.setProjId(clone.getId());
		member.setUserId(userId);
		member.setRole(Role.admin);
		memberService.add(member);
		
		return clone;
	}
	
	//复制apiDoc
	private ApiDoc cloneApiDoc(Long projId,ApiDoc srcApiDoc){
		ApiDoc clone = (ApiDoc)cloneBean(srcApiDoc);
		Date now = DateUtil.getNow();
		clone.setCreateDate(now);
		clone.setModifyDate(now);
		clone.setProjId(projId);
		clone.setId(null);
		return apiDocService.add(clone);
	}
	
	//复制模块信息
	private Map<Long, Long> cloneModule(Long srcDocId,Long cloneDocId){
		List<Module> srcList = moduleService.listAllByDocId(srcDocId);
		Module cloneModule = null;
		Date now = DateUtil.getNow();
		Map<Long, Long> result = MapUtils.newMap();
		for (Module srcModule : srcList) {
			cloneModule = (Module)cloneBean(srcModule);
			cloneModule.setModifyDate(now);
			cloneModule.setCreateDate(now);
			cloneModule.setDocId(cloneDocId);
			moduleService.add(cloneModule);
			
			result.put(srcModule.getId(), cloneModule.getId());
		}
		
		return result;
	}
	
	//复制公共数据结构信息
	private Map<Long, Long> cloneRespSchema(Long srcDocId,Long cloneDocId,Map<Long, Long> moduleInfo){
		List<RespSchema> srcList = respSchemaService.listAllByDocId(srcDocId);
		RespSchema cloneSchema = null;
		Date now = DateUtil.getNow();
		Map<Long, Long> result = MapUtils.newMap();
		List<RespSchema> refList = new ArrayList<RespSchema>();
		for (RespSchema srcSchema : srcList) {
			cloneSchema = (RespSchema)cloneBean(srcSchema);
			cloneSchema.setModifyDate(now);
			cloneSchema.setCreateDate(now);
			cloneSchema.setDocId(cloneDocId);
			cloneSchema.setModuleId(moduleInfo.get(srcSchema.getId()));
			if (cloneSchema.getType() != SchemaType.sys_ref) {
				cloneSchema.setRefSchemaId(null);
			}
			respSchemaService.add(cloneSchema);
			
			//保存可能互相引用的公共结构
			if (cloneSchema.getType() == SchemaType.sys_ref
					|| cloneSchema.getType() == SchemaType.sys_array
					|| cloneSchema.getType() == SchemaType.sys_object) {
				refList.add(cloneSchema);
			}
			
			result.put(srcSchema.getId(), cloneSchema.getId());
		}
		
		boolean nodeUpdateFlag = false;
		for (RespSchema refSchema : refList) {
			nodeUpdateFlag = false;
			
			//当前结构包含引用
			if (refSchema.getType() == SchemaType.sys_ref) {
				refSchema.setRefSchemaId(result.get(refSchema.getRefSchemaId()));
				respSchemaService.update(refSchema);
			}
			
			//当前复合结构子级包含引用
			if (refSchema.getType() == SchemaType.sys_array
					|| refSchema.getType() == SchemaType.sys_object) {
				List<SchemaNodeInfo> nodeList = JsonUtils.toObject(refSchema.getCustSchema(),
													new TypeReference<List<SchemaNodeInfo>>(){});
				for (SchemaNodeInfo nodeInfo : nodeList) {
					//复合结构包含引用
					if (nodeInfo.getType() == SchemaType.sys_ref) {
						nodeUpdateFlag = true;
						nodeInfo.setRefSchemaId(result.get(nodeInfo.getRefSchemaId()));
					}
				}
				
				if (nodeUpdateFlag) {
					refSchema.setCustSchema(JsonUtils.toJson(nodeList));
					respSchemaService.updateByDocId(refSchema);
				}
			}
		}
		
		return result;
	}
	
	//复制接口
	private Map<Long, Long> cloneInter(Long srcDocId,Long cloneDocId,Map<Long, Long> moduleInfo){
		List<Inter> srcList = interService.listAllByDocId(srcDocId,null);
		Map<Long,Long> result = MapUtils.newMap();
		Inter cloneInter = null;
		Date now = DateUtil.getNow();
		for (Inter inter : srcList) {
			cloneInter = (Inter)cloneBean(inter);
			cloneInter.setCreateDate(now);
			cloneInter.setModifyDate(now);
			cloneInter.setId(null);
			cloneInter.setDocId(cloneDocId);
			cloneInter.setModuleId(moduleInfo.get(cloneInter.getModuleId()));
			interService.add(cloneInter);
			
			result.put(inter.getId(), cloneInter.getId());
		}
		
		return result;
	}
	
	//复制请求参数
	private void cloneInterParam(Long srcDocId,Long cloneDocId,
									Map<Long,Long> interInfo,Map<Long, Long> respSchemaInfo){
		List<InterParam> paramList = interParamService.listAllByDocId(srcDocId);
		List<InterParam> cloneList = new ArrayList<InterParam>();
		InterParam cloneParam = null;
		Date now = DateUtil.getNow();
		for (InterParam interParam : paramList) {
			cloneParam = (InterParam)cloneBean(interParam);
			cloneParam.setId(null);
			cloneParam.setDocId(cloneDocId);
			cloneParam.setModifyDate(now);
			cloneParam.setCreateDate(now);
			cloneParam.setInterId(interInfo.get(cloneParam.getInterId()));
			if (cloneParam.getType() == SchemaType.sys_ref) {
				cloneParam.setRefSchemaId(respSchemaInfo.get(cloneParam.getRefSchemaId()));
			}
			else{
				cloneParam.setRefSchemaId(null);
			}
			
			cloneList.add(cloneParam);
		}
		
		interParamService.batchAdd(cloneList);
	}
	
	//复制接口响应
	private void cloneInterResp(Long srcDocId,Long cloneDocId,
					Map<Long,Long> interInfo,Map<Long, Long> respSchemaInfo){
		List<InterResp> respList = interRespService.listAllByDocId(srcDocId);
		List<InterResp> cloneList = new ArrayList<InterResp>();
		InterResp cloneResp = null;
		Date now = DateUtil.getNow();
		for (InterResp interResp : respList) {
			cloneResp = (InterResp)cloneBean(interResp);
			cloneResp.setId(null);
			cloneResp.setDocId(cloneDocId);
			cloneResp.setModifyDate(now);
			cloneResp.setCreateDate(now);
			cloneResp.setInterId(interInfo.get(cloneResp.getInterId()));
			if (cloneResp.getType() == SchemaType.sys_ref) {
				cloneResp.setRefSchemaId(respSchemaInfo.get(cloneResp.getRefSchemaId()));
			}
			else{
				cloneResp.setRefSchemaId(null);
			}
			
			//当前复合结构子级包含引用
			if (cloneResp.getType() == SchemaType.sys_array
					|| cloneResp.getType() == SchemaType.sys_object) {
				List<SchemaNodeInfo> nodeList = JsonUtils.toObject(cloneResp.getCustSchema(),
													new TypeReference<List<SchemaNodeInfo>>(){});
				for (SchemaNodeInfo nodeInfo : nodeList) {
					//复合结构包含引用
					if (nodeInfo.getType() == SchemaType.sys_ref) {
						nodeInfo.setRefSchemaId(respSchemaInfo.get(nodeInfo.getRefSchemaId()));
					}
				}
				cloneResp.setCustSchema(JsonUtils.toJson(nodeList));
			}
			
			cloneList.add(cloneResp);
		}
		
		interRespService.batchAdd(cloneList);
	}
	
	//复制对象
	private Object cloneBean(Object object){
		Object result = null;
		try {
			result = BeanUtils.cloneBean(object);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Transactional
	@Override
	public void copyInter(Long userId, Long docId, Long interId) {
		Inter srcInter = interService.getByDocId(docId, interId);
		ValidateUtils.notNull(srcInter, ErrorCode.SYS_006);
		Long srcInterId = srcInter.getId();
		
		//复制接口信息
		Inter cloneInter = cloneInter(srcInter);
		Long cloneInterId = cloneInter.getId();
		
		//复制接口参数
		cloneInterParam(docId,srcInterId,cloneInterId);
		
		//复制响应参数
		cloneInterResp(docId,srcInterId,cloneInterId);
	}
	
	//复制接口信息
	private Inter cloneInter(Inter srcInter){
		//复制接口信息
		Date now = DateUtil.getNow();
		Inter cloneInter = (Inter)cloneBean(srcInter);
		cloneInter.setId(null);
		cloneInter.setCreateDate(now);
		cloneInter.setModifyDate(now);
		cloneInter.setName(cloneInter.getName() + CfgConstants.COPY_FLAG);
		cloneInter.setPath(cloneInter.getPath() + "_" + DateUtil.format("yyyyMMddHHmmss", now));
		return interService.add(cloneInter);
	}
	
	//复制接口参数
	private void cloneInterParam(Long docId,Long srcInterId,Long cloneInterId){
		List<InterParam> paramList = interParamService.listAllByInterId(docId, srcInterId);
		List<InterParam> addList = new ArrayList<InterParam>();
		Date now = DateUtil.getNow();
		for (InterParam interParam : paramList) {
			interParam.setId(null);
			interParam.setCreateDate(now);
			interParam.setModifyDate(now);
			interParam.setInterId(cloneInterId);
			
			addList.add(interParam);
		}
		
		interParamService.batchAdd(addList);
	}
	
	//复制响应参数
	private void cloneInterResp(Long docId,Long srcInterId,Long cloneInterId){
		List<InterResp> respList = interRespService.listAllByInterId(docId, srcInterId);
		List<InterResp> addList = new ArrayList<InterResp>();
		Date now = DateUtil.getNow();
		for (InterResp interResp : respList) {
			interResp.setId(null);
			interResp.setCreateDate(now);
			interResp.setModifyDate(now);
			interResp.setInterId(cloneInterId);
			
			addList.add(interResp);
		}
		
		interRespService.batchAdd(addList);
	}
}
