package com.dev.user.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.constant.MailConstants;
import com.dev.base.enums.LoginStatus;
import com.dev.base.enums.LoginType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.util.MailUtil;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.MD5Utils;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.user.entity.UserBasic;
import com.dev.user.entity.UserDetail;
import com.dev.user.entity.UserLogin;
import com.dev.user.entity.UserToken;
import com.dev.user.service.AuthService;
import com.dev.user.service.LoginService;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;
import com.dev.user.service.UserLoginService;
import com.dev.user.service.UserMsgService;
import com.dev.user.service.UserTokenService;
import com.dev.user.vo.LoginParamInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 登陆相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日上午10:29:42</p>
 */
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserTokenService userTokenService;
	
	@Autowired
	private UserMsgService userMsgService;
	
	@Autowired
	private AuthService authService;

	@Override
	public UserInfo loginByEmail(LoginParamInfo loginParamInfo) {
		ValidateUtils.isTrue(LoginType.email == loginParamInfo.getLoginType(), ErrorCode.LOGIN_007);
		
		UserBasic basic = userBasicService.getByEmail(loginParamInfo.getEmail());
		//登陆验证
		loginValid(basic);
		
		//用户登陆信息
		UserLogin userLogin = userLoginService.getByUserId(basic.getId());
		String passwdEncry = userBasicService.encryPasswd(basic.getId(), loginParamInfo.getPassword());
		if (!basic.getPassword().equals(passwdEncry)) {
			dealLoginFail(basic,loginParamInfo,userLogin);
		}
		
		return dealLongSuccess(basic,loginParamInfo,userLogin);
	}
	
	//登陆成功处理
	private UserInfo dealLongSuccess(UserBasic basic,LoginParamInfo loginParamInfo,UserLogin userLogin){
		Long userId = basic.getId();
		
		//更新用户登陆信息
		userLogin.setLoginFailureCount(0);
		userLogin.setLoginIp(loginParamInfo.getLoginIp());
		userLogin.setLoginType(loginParamInfo.getLoginType());
		userLogin.setAuthCode(loginParamInfo.getSmsCode());
		userLogin.setLoginDate(DateUtil.getNow());
		userLogin.setLoginStatus(LoginStatus.online);
		userLogin.setLoginCount(userLogin.getLoginCount() + 1);
		userLoginService.update(userLogin);
		
		//设置用户信息
		UserDetail detail = userDetailService.getByUserId(userId);
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail(basic.getEmail());
		userInfo.setUserId(userId);
		userInfo.setNickName(detail.getNickName());
		userInfo.setHeadUrl(detail.getHeadUrl());
		userInfo.setRole(basic.getRole());
		
		//加载项目权限信息
		authService.loadProjAuth(userInfo);
		
		//插入新发布的系统消息
		userMsgService.fetchSysMsg(userId, basic.getRole());
		int newMsgCount = userMsgService.countUnread(userId);
		userInfo.setNewMsgCount(newMsgCount);
		
		//设置自动登录
		String token = dealAutoLogin(userId,loginParamInfo);
		userInfo.setToken(token);
		
		return userInfo;
	}
	
	//处理自动登录
	private String dealAutoLogin(Long userId,LoginParamInfo loginParamInfo){
		String token = "";
		if (!loginParamInfo.isAutoLogin()) {
			return token;
		}
		
		//token由用户id和当前时间戳进行md5生成
		token = MD5Utils.Md5("" + userId + "," + DateUtil.getNowTime());
		UserToken userToken = userTokenService.getByUserId(userId);
		if (userToken == null) {//无记录则新建
			userToken = new UserToken();
			userToken.setLoginIp(loginParamInfo.getLoginIp());
			userToken.setUserId(userId);
			userToken.setToken(token);
			userToken.setExpireDate(getTokenExpireDate());
			userTokenService.add(userToken);
		}
		else{//更新记录信息
			userToken.setToken(token);
			userToken.setExpireDate(getTokenExpireDate());
			userTokenService.update(userToken);
		}
		
		return token;
	}
	
	//获取token失效时间
	private Date getTokenExpireDate(){
		return new Date(DateUtil.getNowTime() + CfgConstants.COOKIE_TOKEN_EXPIRE * 1000);
	}
	
	//登陆失败处理
	private void dealLoginFail(UserBasic basic,LoginParamInfo loginParamInfo,UserLogin userLogin){
		int currentFailCount = userLogin.getLoginFailureCount() + 1;
		Date nowDate = DateUtil.getNow();
		
		//更新用户登陆信息
		userLogin.setLoginFailureCount(currentFailCount);
		userLogin.setLoginIp(loginParamInfo.getLoginIp());
		userLogin.setLoginType(loginParamInfo.getLoginType());
		userLogin.setAuthCode(loginParamInfo.getSmsCode());
		userLogin.setLoginDate(nowDate);
		
		//登陆错误次数超过限制，账号锁定
		if (currentFailCount >= CfgConstants.LIMIT_LOGIN_FAIL_COUNT) {
			basic.setLocked(true);
			basic.setLockedDate(nowDate);
			userBasicService.update(basic);
			
			userLoginService.update(userLogin);
			ValidateUtils.isTrue(false, ErrorCode.LOGIN_010);
		}
		else{
			userLoginService.update(userLogin);
			ValidateUtils.isTrue(false,ErrorCode.LOGIN_004);
		}
	}

	//登录验证
	private void loginValid(UserBasic basic){
		ValidateUtils.notNull(basic, ErrorCode.LOGIN_001);
		ValidateUtils.isTrue(!basic.isLocked(), ErrorCode.LOGIN_006);
		ValidateUtils.isTrue(basic.isValid(), ErrorCode.LOGIN_005);
	}

	@Override
	public void sendResetCode(String email) {
		UserBasic basic = userBasicService.getByEmail(email);
		ValidateUtils.notNull(basic, ErrorCode.LOGIN_001);
		
		UserDetail detail = userDetailService.getByUserId(basic.getId());
		String code = CryptUtil.encryptAES(email,AppConstants.DEFAULT_SECRET_KEY);
		Map<String,Object> model = MapUtils.newMap();
		model.put("nickName", detail.getNickName());
		model.put("email", email);
		model.put("resetPasswdUrl", CfgConstants.WEB_BASE_URL + "forwardReset.htm?code=" + code);
		
		MailUtil.send(email, MailConstants.SUB_PASSWD_RESET, MailConstants.TMPL_PASSWD_RESET, model);
	}

	@Override
	public void resetPasswd(String code, String passwd) {
		String email = CryptUtil.decryptAES(code,AppConstants.DEFAULT_SECRET_KEY);
		ValidateUtils.isTrue(RegexUtil.isEmail(email), ErrorCode.SYS_001);
		
		UserBasic userBasic = userBasicService.getByEmail(email);
		if (userBasic != null) {
			userBasic.setPassword(userBasicService.encryPasswd(userBasic.getId(), passwd));
			userBasic.setModifyDate(DateUtil.getNow());
			userBasicService.update(userBasic);
		}
	}

	@Override
	public UserInfo loginByToken(LoginParamInfo loginParamInfo) {
		UserToken userToken = userTokenService.getByToken(loginParamInfo.getToken());
		if (userToken == null) {
			return null;
		}
		
		Long userId = userToken.getUserId();
		UserBasic basic = userBasicService.getById(userId);
		//用户登陆信息
		UserLogin userLogin = userLoginService.getByUserId(userId);
		return dealLongSuccess(basic, loginParamInfo, userLogin);
	}
}
