package com.dev.user.entity;

import java.util.Date;

import com.dev.base.enums.LoginStatus;
import com.dev.base.enums.LoginType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 用户登陆信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:43:15</p>
 */
public class UserLogin extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 登陆时间 */
	private Date loginDate;		
	
	/** 连续登陆失败次数 */
	private int loginFailureCount;		
	
	/** 总登陆次数*/
	private int loginCount;
	
	/** 登陆ip */
	private String loginIp;		
	
	/** 用户id */
	private Long userId;		
	
	/** 登陆方式 */
	private LoginType loginType;		
	
	/** 登陆状态*/
	private LoginStatus loginStatus;
	
	/** 登陆验证码 */
	private String authCode;		
	

	public void setLoginDate(Date loginDate){
		this.loginDate = loginDate;
	}
	
	public Date getLoginDate(){
		return loginDate;
	}
	
	public void setLoginFailureCount(int loginFailureCount){
		this.loginFailureCount = loginFailureCount;
	}
	
	public int getLoginFailureCount(){
		return loginFailureCount;
	}
	
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	
	public String getLoginIp(){
		return loginIp;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setAuthCode(String authCode){
		this.authCode = authCode;
	}
	
	public String getAuthCode(){
		return authCode;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
}
