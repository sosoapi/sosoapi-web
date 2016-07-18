package com.dev.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.constant.AppConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.LoginType;
import com.dev.base.exception.TipException;
import com.dev.base.exception.ValidateException;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.ValidInfo;
import com.dev.user.service.RegistService;
import com.dev.user.service.UserBasicService;
import com.dev.user.vo.RegistParamInfo;

/**
 * 
		* <p>Title: 新用户注册处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:04:09</p>
 */
@Controller
@RequestMapping("/regist")
public class RegistController extends BaseController{
	@Autowired
	private RegistService registService;
	
	@Autowired
	private UserBasicService userBasicService;
	
	/**
	 * 
			*@name 跳转到注册页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/forwardRegist.htm")
	public String forwardRegist(Model model){
		model.addAttribute("isRegist", true);
		
		return "user/login";
	}
	
	/**
	 * 
			*@name 跳转到注册成功页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/regSuccess.htm")
	public String regSuccess(Model model){
		return "user/regSuccess";
	}
	
	/**
	 * 
			*@name 跳转到账号激活成功页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/activeSuccess.htm")
	public String activeSuccess(Model model){
		return "user/activeSuccess";
	}
	
	/**
	 * 
			*@name 通过邮箱注册
			*@Description  
			*@CreateDate 2015年8月8日下午2:20:03
	 */
	@RequestMapping("/regist.htm")
	public String regist(HttpServletRequest request,String loginName,String passwd,
							String nickName,String validCode,Model model){
		ValidateUtils.notNull(loginName, ErrorCode.SYS_001,"登录名不能为空");
		ValidateUtils.notNull(passwd, ErrorCode.SYS_001,"密码不能为空");
		ValidateUtils.notNull(nickName, ErrorCode.SYS_001,"昵称不能为空");
		ValidateUtils.notNull(validCode, ErrorCode.SYS_001,"验证码不能为空");
		
		//目前默认为邮箱注册
		RegistParamInfo paramInfo = new RegistParamInfo();
		paramInfo.setLoginType(LoginType.email);
		paramInfo.setEmail(loginName);
		paramInfo.setNickName(nickName);
		paramInfo.setPassword(passwd);
		paramInfo.setRegistIp(getClientIp(request));
		
		try {
			ValidateUtils.isTrue(validCode.equals(WebUtil.getSessionAttr(request, AppConstants.CAPTCHA_REGIST)), ErrorCode.LOGIN_003);
			registService.registByEmail(paramInfo);
		} 
		catch (ValidateException e) {
			throw new TipException("forward:/regist/forwardRegist.htm", e);
		}
		
		model.addAttribute("email", loginName);
		return WebUtil.getRedirectUrl("/regist/regSuccess.htm");
	}
	
	/**
	 * 
			*@name 重新发送激活授权码
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/sendActiveCode.htm")
	public @ResponseBody Map sendActiveCode(HttpServletRequest request,String email){
		ValidateUtils.isTrue(RegexUtil.isEmail(email), ErrorCode.SYS_001,"邮箱格式错误");
		
		registService.sendActiveCode(email);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 激活账号
			*@Description  
			*@CreateDate 2015年8月23日下午5:54:19
	 */
	@RequestMapping("/active.htm")
	public String active(HttpServletRequest request,String code,Model model){
		ValidateUtils.notNull(code, ErrorCode.SYS_001,"激活码不能为空");
		
		registService.activeByEmail(code);
		
		return WebUtil.getRedirectUrl("/regist/activeSuccess.htm");
	}
	
	/**
	 * 
			*@name 判断邮箱是否已存在
			*@Description  
			*@CreateDate 2015年8月24日下午6:36:46
	 */
	@RequestMapping("/validEmail.htm")
	public @ResponseBody ValidInfo isEmailExist(String email){
		ValidInfo result = new ValidInfo();
		result.setValid(!userBasicService.isEmailExist(email, null));
		
		return result;
	}
}
