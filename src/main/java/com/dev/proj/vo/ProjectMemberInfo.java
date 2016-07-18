package com.dev.proj.vo;

import java.util.Date;

import com.dev.base.enums.Role;

/**
 * 
		* <p>Title: 项目成员相关信息 </p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月7日下午6:15:24</p>
 */
public class ProjectMemberInfo {
	/** 用户id*/
	private Long userId;
	
	/** 邮箱*/
	private String email;
	
	/** 昵称*/
	private String nickName;
	
	/** 角色名称*/
	private Role role;
	
	/** 加入时间*/
	private Date createDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
