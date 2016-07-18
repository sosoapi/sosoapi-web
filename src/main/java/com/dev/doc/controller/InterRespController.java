package com.dev.doc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.InterResp;
import com.dev.doc.service.InterRespService;

/**
 * 
		* <p>Title: 接口响应相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("auth/doc/inter/resp")
public class InterRespController extends BaseController{
	@Autowired
	private InterRespService interRespService;
	
	/**
	 * 
			*@name 查询接口响应列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/list.htm")
	public @ResponseBody Map list(HttpServletRequest request,Long docId,Long interId){
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		
		List<InterResp> interRespList = interRespService.listAllByInterId(docId,interId);
		return JsonUtils.createSuccess(interRespList);
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,InterResp interResp){
		ValidateUtils.notNull(interResp.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interResp.getInterId(), ErrorCode.SYS_001,"接口id不能为空");
		ValidateUtils.notNull(interResp.getCode(), ErrorCode.SYS_001,"响应编码不能为空");
		
		interRespService.add(interResp);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 编辑
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,InterResp interResp,Long respId){
		ValidateUtils.notNull(interResp.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(respId, ErrorCode.SYS_001,"响应id不能为空");
		ValidateUtils.notNull(interResp.getCode(), ErrorCode.SYS_001,"编码不能为空");
		
		interResp.setId(respId);
		interRespService.updateByDocId(interResp);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long docId,Long respId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(respId, ErrorCode.SYS_001,"响应id不能为空");
		
		interRespService.deleteByDocId(docId, respId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 查询基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(Long docId,Long respId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(respId, ErrorCode.SYS_001,"响应id不能为空");
		InterResp interResp = interRespService.getByDocId(docId,respId);
		
		return JsonUtils.createSuccess(interResp);
	}
}