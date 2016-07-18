package com.dev.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;

/**
 * 
		* <p>Title: 主页</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:04:09</p>
 */
@Controller
@RequestMapping("/auth/home")
public class HomeController extends BaseController{
	/**
	 * 
			*@name 登陆后跳转到主页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/home.htm")
	public String forwardHome(Model model){
		return "user/home";
	}
}
