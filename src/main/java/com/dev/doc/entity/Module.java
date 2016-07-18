package com.dev.doc.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 模块消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:33:24</p>
 */
public class Module extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** api文档id */
	private Long docId;		
	
	/** 编码 */
	private String code;		
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 排序权重，值越小，排序越靠前，默认步长为50*/
	private int sortWeight;
	
	public void setDocId(Long docId){
		this.docId = docId;
	}
	
	public Long getDocId(){
		return docId;
	}
	
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

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}
}
