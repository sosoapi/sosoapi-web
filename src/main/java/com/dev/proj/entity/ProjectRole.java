package com.dev.proj.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 项目角色信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:34:32</p>
 */
public class ProjectRole extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 角色编码 */
	private String code;		
	
	/** 角色名称 */
	private String name;		
	
	/** 角色描述 */
	private String description;		
	

	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
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
	
}
