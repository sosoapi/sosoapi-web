package com.dev.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.constant.MailConstants;
import com.dev.base.enums.LoginType;
import com.dev.base.enums.Role;
import com.dev.base.enums.UserRole;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.util.MailUtil;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.proj.service.ProjectMemberService;
import com.dev.user.entity.UserBasic;
import com.dev.user.entity.UserDetail;
import com.dev.user.entity.UserLogin;
import com.dev.user.service.RegistService;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;
import com.dev.user.service.UserLoginService;
import com.dev.user.vo.RegistParamInfo;

/**
 * 
		* <p>Title: 注册相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日下午12:54:35</p>
 */
@Service
public class RegistServiceImpl implements RegistService{
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Override
	@Transactional
	public void registByEmail(RegistParamInfo registParamInfo) {
		ValidateUtils.isTrue(LoginType.email == registParamInfo.getLoginType(),ErrorCode.LOGIN_007);
		
		UserBasic temp = userBasicService.getByEmail(registParamInfo.getEmail());
		//注册验证
		registValid(temp);
		
		//保存用户信息
		Long userId = saveUserInfo(registParamInfo);
		
		//发送邮件
		sendEmail(registParamInfo.getNickName(),registParamInfo.getEmail());
	}
	
	//处理邮件发送
	private void sendEmail(String nickName,String registEmail){
		String code = CryptUtil.encryptAES(registEmail,AppConstants.DEFAULT_SECRET_KEY);
		
		Map<String,Object> model = MapUtils.newMap();
		model.put("nickName", nickName);
		model.put("activeUrl", CfgConstants.WEB_BASE_URL + "regist/active.htm?code=" + code);
		
		MailUtil.send(registEmail, MailConstants.SUB_REGIST, MailConstants.TMPL_REGIST, model);
	}
	
	//处理用户信息保存
	private Long saveUserInfo(RegistParamInfo registParamInfo){
		//处理用户基本信息
		UserBasic userBasic = new UserBasic();
		userBasic.setEmail(registParamInfo.getEmail());
		userBasic.setLocked(false);
		userBasic.setRegisterIp(registParamInfo.getRegistIp());
		userBasic.setValid(false);
		userBasic.setRole(UserRole.normal);
		userBasicService.add(userBasic);
		
		Long userId = userBasic.getId();
		//设置密码
		String passwordEncry = userBasicService.encryPasswd(userId,registParamInfo.getPassword());
		userBasic.setPassword(passwordEncry);
		userBasicService.update(userBasic);
		
		//处理用户详情信息
		UserDetail userDetail = new UserDetail();
		userDetail.setNickName(registParamInfo.getNickName());
		userDetail.setUserId(userId);
		userDetailService.add(userDetail);
		
		//处理用户登陆信息
		UserLogin userLogin = new UserLogin();
		userLogin.setUserId(userId);
		userLoginService.add(userLogin);
		
		return userId;
	}

	//注册验证
	private void registValid(UserBasic basic){
		if (basic != null) {
			ValidateUtils.isTrue(basic.isValid(), ErrorCode.LOGIN_005);
			ValidateUtils.isTrue(false, ErrorCode.LOGIN_002);
		}
	}

	@Override
	public void activeByEmail(String code) {
		String registEmail = CryptUtil.decryptAES(code,AppConstants.DEFAULT_SECRET_KEY);
		ValidateUtils.isTrue(RegexUtil.isEmail(registEmail), ErrorCode.SYS_001);
		
		UserBasic userBasic = userBasicService.getByEmail(registEmail);
		if (userBasic != null) {
			userBasic.setValid(true);
			userBasic.setModifyDate(DateUtil.getNow());
			userBasicService.update(userBasic);
			
			//加入到demo项目组中
//			projectMemberService.accept(userBasic.getId(), 1L, Role.viewer);
		}
	}

	@Override
	public void sendActiveCode(String email) {
		UserBasic temp = userBasicService.getByEmail(email);
		sendActiveCodeValid(temp);
		
		UserDetail detail = userDetailService.getByUserId(temp.getId());
		sendEmail(detail.getNickName(),email);
	}
	
	//发送激活码验证
	private void sendActiveCodeValid(UserBasic basic){
		ValidateUtils.notNull(basic, ErrorCode.LOGIN_001);
		ValidateUtils.isTrue(!basic.isValid(), ErrorCode.LOGIN_009);
	}
}
