package com.dev.proj.entity;

import com.dev.base.enums.ProjectStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 项目信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:33:39</p>
 */
public class Project extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 创建者id */
	private Long userId;
	
	/** 编码*/
	private String code;
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 项目状态 */
	private ProjectStatus status;		
	

	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
