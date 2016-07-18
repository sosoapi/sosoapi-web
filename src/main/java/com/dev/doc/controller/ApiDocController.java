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
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.vo.ApiDocInfo;

/**
 * 
		* <p>Title: 项目文档相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc")
public class ApiDocController extends BaseController{
	@Autowired
	private ApiDocService apiDocService;
	
	/**
	 * 
			*@api文档列表
			*@Description  
			*@CreateDate 2015年7月27日下午6:03:42
	 */
	@RequestMapping("list.htm")
	public String list(HttpServletRequest request,Model model,
						String title,Integer pageNumber,Integer pageSize){
		Long userId = getUserId(request);
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<ApiDocInfo> list = apiDocService.listByUserId(userId, title, pager);
		int count = apiDocService.countByUserId(userId, title);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "apidoc/docList";
	}
	
	/**
	 * 
			*@name 更新项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,ApiDoc apiDoc,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		apiDoc.setId(docId);
		apiDocService.update(apiDoc);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 文档信息
			*@Description  
			*@CreateDate 2015年7月27日下午6:04:52
	 */
	@RequestMapping("info.htm")
	public String getInfo(HttpServletRequest request,Long docId,Model model){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		ApiDoc docInfo = apiDocService.getById(docId);
		model.addAttribute("docInfo", docInfo);
		
		return "apidoc/docInfo";
	}
}
