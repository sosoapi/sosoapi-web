package com.dev.user.entity;

import java.util.Date;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 用户登陆凭证</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月30日下午4:22:12</p>
 */
public class UserToken extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
	/** 登陆ip */
	private String loginIp;		
	
	/** token信息 */
	private String token;		
	
	/** 失效时间 */
	private Date expireDate;		
	

	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	
	public String getLoginIp(){
		return loginIp;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public String getToken(){
		return token;
	}
	
	public void setExpireDate(Date expireDate){
		this.expireDate = expireDate;
	}
	
	public Date getExpireDate(){
		return expireDate;
	}
	
}
