package com.dev.base.enums;

/**
 * 
		* <p>Title: 项目状态</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:49:25</p>
 */
public enum ProjectStatus {
	open("开启"),
	
	close("关闭");
	
	private ProjectStatus(String displayName){
		this.displayName = displayName;
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
