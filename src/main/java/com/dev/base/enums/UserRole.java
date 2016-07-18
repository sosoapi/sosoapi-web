package com.dev.base.enums;

/**
 * 
		* <p>Title: 用户角色</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月28日下午5:09:40</p>
 */
public enum UserRole {
	admin("管理员"),
	
	normal("普通用户");
	
	private UserRole(String displayName){
		this.displayName =  displayName;
	}
	
	/** 展示名称*/
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
