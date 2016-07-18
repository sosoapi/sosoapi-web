package com.dev.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.SuggestType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.user.service.SuggestService;

/**
 * 
		* <p>Title: 意见反馈</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:04:09</p>
 */
@Controller
@RequestMapping("/auth/suggest")
public class SuggestController extends BaseController{
	@Autowired
	private SuggestService suggestService;
	
	/**
	 * 
			*@name 跳转到建议反馈页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/forwardSuggest.htm")
	public String forwardSuggest(Model model){
		
		return "user/suggest";
	}
	
	/**
	 * 
			*@name 新增项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,String title,String content,SuggestType type){
		ValidateUtils.notNull(title, ErrorCode.SYS_001,"标题不能为空");
		
		Long userId = getUserId(request);
		suggestService.add(userId,title,type,content);
		
		return JsonUtils.createSuccess();
	}
}
