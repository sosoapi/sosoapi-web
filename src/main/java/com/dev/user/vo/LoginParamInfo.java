package com.dev.user.vo;

import com.dev.base.enums.LoginType;

/**
 * 
		* <p>Title: 登陆信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日上午11:00:59</p>
 */
public class LoginParamInfo {
	/** 邮箱*/
	private String email;
	
	/** 手机号*/
	private String phone;
	
	/** 密码*/
	private String password;
	
	/** 登陆ip*/
	private String loginIp;
	
	/** 手机验证码*/
	private String smsCode;
	
	/** 下次是否自动登录*/
	private boolean autoLogin;
	
	/** token信息*/
	private String token;
	
	/** 登陆类型*/
	private LoginType loginType;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
