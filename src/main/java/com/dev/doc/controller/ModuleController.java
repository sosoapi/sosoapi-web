package com.dev.doc.controller;

import java.util.ArrayList;
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
import com.dev.doc.entity.Module;
import com.dev.doc.service.ModuleService;

/**
 * 
		* <p>Title: 项目模块相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/module")
public class ModuleController extends BaseController{
	@Autowired
	private ModuleService moduleService;
	
	/**
	 * 
			*@name 模块列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,Long docId,
						String code,String name,String description,
							Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<Module> list = moduleService.listByDocId(docId,code,name,description,pager);
		int count = moduleService.countByDocId(docId, code, name, description);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "apidoc/moduleList";
	}
	
	/**
	 * 
			*@name 模块列表
			*@Description  用于填充下拉框
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/list.htm")
	public @ResponseBody Map listJson(HttpServletRequest request,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<Module> list = moduleService.listAllByDocId(docId);
		
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		for (Module module : list) {
			result.add(new SelectInfo(module.getName(), module.getId().toString()));
		}

		return JsonUtils.createSuccess(result);
	}
	
	/**
	 * 
			*@name 新增模块信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Module module){
		ValidateUtils.notNull(module.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(module.getName(), ErrorCode.SYS_001,"模块名称不能为空");
		
		moduleService.add(module);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 编辑模块信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,Module module,Long moduleId){
		ValidateUtils.notNull(module.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(moduleId, ErrorCode.SYS_001,"模块id不能为空");
		ValidateUtils.notNull(module.getName(), ErrorCode.SYS_001,"模块名称不能为空");
		
		module.setId(moduleId);
		moduleService.updateByDocId(module);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除模块信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long docId,Long moduleId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(moduleId, ErrorCode.SYS_001,"模块id不能为空");
		
		moduleService.deleteByDocId(docId, moduleId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 模块基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(Long moduleId){
		ValidateUtils.notNull(moduleId, ErrorCode.SYS_001,"模块id不能为空");
		Module module = moduleService.getById(moduleId);
		
		return JsonUtils.createSuccess(module);
	}
}