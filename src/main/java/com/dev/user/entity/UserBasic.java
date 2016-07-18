package com.dev.user.entity;

import java.util.Date;

import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 用户基本信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
public class UserBasic extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 手机号 */
	private String phone;		
	
	/** 邮箱 */
	private String email;		
	
	/** 密码 */
	private String password;		
	
	/** 是否验证 */
	private boolean valid;		
	
	/** 是否锁定 */
	private boolean locked;		
	
	/** 锁定时间 */
	private Date lockedDate;		
	
	/** 注册ip */
	private String registerIp;		
	
	/** 用户角色*/
	private UserRole role;

	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setLockedDate(Date lockedDate){
		this.lockedDate = lockedDate;
	}
	
	public Date getLockedDate(){
		return lockedDate;
	}
	
	public void setRegisterIp(String registerIp){
		this.registerIp = registerIp;
	}
	
	public String getRegisterIp(){
		return registerIp;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
