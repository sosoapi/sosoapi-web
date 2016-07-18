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
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.user.entity.UserDetail;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 用户信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午3:53:04</p>
 */
@Controller
public class UserController extends BaseController{
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	/**
	 * 
			*@name 个人中心首页
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/auth/user/home.htm")
	public String forwardHome(Model model){
		return "forward:/auth/user/setting.htm";
	}
	
	/**
	 * 
			*@name 用户基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/auth/user/setting.htm")
	public String getInfo(HttpServletRequest request,Model model){
		Long userId = getUserId(request);
		
		UserInfo userInfo = userDetailService.getDetailByUserId(userId);
		model.addAttribute("userInfo", userInfo);
		
		return "user/setting";
	}
	
	/**
	 * 
			*@name 更改用户信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/auth/user/json/update.htm")
	public @ResponseBody Map update(HttpServletRequest request,String nickName){
		ValidateUtils.notNull(nickName, ErrorCode.SYS_001,"用户昵称不能为空");
		Long userId = getUserId(request);
		
		UserDetail userDetail = userDetailService.getByUserId(userId);
		userDetail.setNickName(nickName);
		userDetail.setUserId(userId);
		userDetailService.update(userDetail);
		
		//更新缓存中的用户信息
		UserInfo userInfo = getUserInfo(request);
		userInfo.setNickName(nickName);
		saveUserInfo(request, userInfo);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 更改密码
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/auth/user/json/updatePasswd.htm",method = RequestMethod.POST)
	public @ResponseBody Map updatePasswd(HttpServletRequest request,String oldPasswd,String newPasswd){
		ValidateUtils.notNull(oldPasswd, ErrorCode.SYS_001,"原密码不能为空");
		ValidateUtils.notNull(newPasswd, ErrorCode.SYS_001,"新密码不能为空");
		
		Long userId = getUserId(request);
		userBasicService.updatePasswd(userId, oldPasswd, newPasswd);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 发送更改邮箱授权码
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/auth/user/json/sendUpdateEmailCode.htm")
	public @ResponseBody Map sendUpdateEmailCode(HttpServletRequest request,String passwd,String email){
		ValidateUtils.isTrue(RegexUtil.isEmail(email), ErrorCode.SYS_001,"邮箱格式不正确");
		Long userId = getUserId(request);
		userBasicService.sendUpdateEmailCode(userId, passwd, email);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 更改邮箱
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/pass/user/updateEmail.htm")
	public String updateEmail(HttpServletRequest request,String code){
		ValidateUtils.notNull(code, ErrorCode.SYS_001,"授权码不能为空");
		userBasicService.updateEmail(code);
		
		return WebUtil.getRedirectUrl("/pass/user/updateEmailSuccess.htm");
	}
	
	/**
	 * 
			*@name 跳转到更改邮箱成功页面
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/pass/user/updateEmailSuccess.htm")
	public String updateSuccess(HttpServletRequest request){
		return "user/updateEmailSuccess";
	}
}
