package com.dev.doc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.ParamPosition;
import com.dev.base.enums.SchemaType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.InterParam;
import com.dev.doc.service.InterParamService;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: 接口参数相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("auth/doc/inter/param")
public class InterParamController extends BaseController{
	@Autowired
	private InterParamService interParamService;
	
	/**
	 * 
			*@name 查询接口参数列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/list.htm")
	public @ResponseBody Map list(HttpServletRequest request,Long docId,Long interId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		
		List<InterParam> interParamList = interParamService.listAllByInterId(docId,interId);
		return JsonUtils.createSuccess(interParamList);
	}
	
	/**
	 * 
			*@name 新增接口参数
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Long docId,Long interId,String reqParam){
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<Map<String,String>> paramList = JsonUtils.toObject(reqParam, new TypeReference<List<Map<String,String>>>(){});
		
		List<InterParam> interParamList = parseParamList(docId,interId, paramList);
		interParamService.batchAdd(docId,interId, interParamList);
		return JsonUtils.createSuccess();
	}
	
	//解析请求参数列表
	private List<InterParam> parseParamList(Long docId,Long interId,List<Map<String,String>> paramMapList){
		List<InterParam> result = new ArrayList<InterParam>();
		String code;
		String refSchemaIdStr;
		InterParam interParam = null;
		for (Map<String,String> paramMap : paramMapList) {
			code = (String)paramMap.get("code");
			
			if (StringUtils.isEmpty(code)) {//参数编码非空
				continue ;
			}
			
			interParam = new InterParam();
			interParam.setCode(code);
			interParam.setName(paramMap.get("name"));
			interParam.setDefValue(paramMap.get("defValue"));
			interParam.setDescription(paramMap.get("description"));
			interParam.setInterId(interId);
			interParam.setDocId(docId);
			interParam.setPosition(ParamPosition.valueOf(paramMap.get("position")));
			interParam.setRequired(Boolean.parseBoolean(paramMap.get("required")));
			interParam.setType(SchemaType.valueOf(paramMap.get("type")));
			interParam.setExtSchema(paramMap.get("extSchema"));
			
			refSchemaIdStr = paramMap.get("refSchemaId");
			if (!StringUtils.isEmpty(refSchemaIdStr)) {
				interParam.setRefSchemaId(Long.parseLong(refSchemaIdStr));
			}
			
			result.add(interParam);
		}
		
		return result;
	}
}
