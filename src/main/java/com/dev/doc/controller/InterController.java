package com.dev.doc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.entity.Inter;
import com.dev.doc.service.CopyService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.vo.InterInfo;

/**
 * 
		* <p>Title: 项目接口相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/inter")
public class InterController extends BaseController{
	@Autowired
	private InterService interService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private CopyService copyService;
	
	/**
	 * 
			*@name 接口列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,
			Long docId,Long moduleId,String name,String description,
			Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");

		Pager pager = new Pager(pageNumber, pageSize);
		List<Inter> list = interService.listByDocId(docId, moduleId,name,description,pager);
		int count = interService.countByDocId(docId, moduleId,name,description);
		pager.setTotalCount(count);
		pager.setList(list);

		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));

		return "apidoc/interList";
	}
	
	/**
	 * 
			*@name 新增项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/copy.htm",method = RequestMethod.POST)
	public @ResponseBody Map copy(HttpServletRequest request,Long docId,Long interId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		
		Long userId = getUserId(request);
		copyService.copyInter(userId, docId, interId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 跳转到接口基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/forwardInfo.htm")
	public String forwardInfo(HttpServletRequest request,Long docId,Long interId,Model model){
		InterInfo interInfo = interService.getDetailByDocId(docId,interId);
		model.addAttribute("interInfo", interInfo);
		
		List<SelectInfo> refSchemaList = respSchemaService.listSelectInfoByDocId(docId);
		model.addAttribute("refSchemaList", refSchemaList);
		
		return "apidoc/interInfo";
	}
	
	/**
	 * 
			*@name 新增接口
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/add.htm")
	public @ResponseBody Map add(HttpServletRequest request,Inter inter,Model model){
		ValidateUtils.notNull(inter.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(inter.getName(), ErrorCode.SYS_001,"接口名称不能为空");
		ValidateUtils.notNull(inter.getPath(), ErrorCode.SYS_001,"接口url不能为空");
		
		interService.add(inter);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 更新项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,Inter inter,Long interId){
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		ValidateUtils.notNull(inter.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(inter.getName(), ErrorCode.SYS_001,"接口名称不能为空");
		ValidateUtils.notNull(inter.getPath(), ErrorCode.SYS_001,"接口url不能为空");
		
		inter.setId(interId);
		interService.updateByDocId(inter);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long docId,Long interId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		
		interService.deleteByDocId(docId,interId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 接口基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(HttpServletRequest request,Long docId,Long interId,Model model){
		InterInfo interInfo = interService.getDetailByDocId(docId,interId);
		return JsonUtils.createSuccess(interInfo);
	}
}
