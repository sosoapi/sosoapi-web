package com.dev.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;

/**
 * 
		* <p>Title: 常见问题</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月14日下午5:39:07</p>
 */
@Controller
@RequestMapping("/pass/faq")
public class FaqController extends BaseController{
	/**
	 * 
			*@name 常见问题首页
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/home.htm")
	public String forwardHome(Model model){
		return "faq/swagger";
	}
	
	/**
	 * 
			*@name swagger相关问题
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/swagger.htm")
	public String forwardSwagger(Model model){
		return "faq/swagger";
	}
	
	/**
	 * 
			*@name 线下部署相关问题
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/offline.htm")
	public String forwardOffline(Model model){
		return "faq/offline";
	}
	
	/**
	 * 
			*@name 线上编辑相关问题
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/online.htm")
	public String forwardOnline(Model model){
		return "faq/online";
	}
	
	/**
	 * 
			*@name 线上编辑说明文档
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/editWizard.htm")
	public String forwardEditWizard(Model model){
		return "faq/editWizard";
	}
	
	/**
	 * 
			*@name 线下部署说明文档
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/deployWizard.htm")
	public String forwardDeployWizard(Model model){
		return "faq/deployWizard";
	}
	
	/**
	 * 
			*@name 在线测试相关问题
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/test.htm")
	public String forwardTest(Model model){
		return "faq/test";
	}
	
	/**
	 * 
			*@name 关于我们
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/about.htm")
	public String forwardAbout(Model model){
		return "faq/about";
	}
}
