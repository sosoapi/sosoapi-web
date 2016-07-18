package com.dev.doc.entity;

import com.dev.base.enums.SchemaType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: api文档公用响应结构信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:34:50</p>
 */
public class RespSchema extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** api文档id */
	private Long docId;		
	
	/** 模块id */
	private Long moduleId;		
	
	/** 编码 */
	private String code;		
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 结构 */
	private String custSchema;		
	
	/** 类型 */
	private SchemaType type;		
	
	/** 响应数据结构id */
	private Long refSchemaId;	

	public void setDocId(Long docId){
		this.docId = docId;
	}
	
	public Long getDocId(){
		return docId;
	}
	
	public void setModuleId(Long moduleId){
		this.moduleId = moduleId;
	}
	
	public Long getModuleId(){
		return moduleId;
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
	
	public void setCustSchema(String custSchema){
		this.custSchema = custSchema;
	}
	
	public String getCustSchema(){
		return custSchema;
	}

	public SchemaType getType() {
		return type;
	}

	public void setType(SchemaType type) {
		this.type = type;
	}

	public Long getRefSchemaId() {
		return refSchemaId;
	}

	public void setRefSchemaId(Long refSchemaId) {
		this.refSchemaId = refSchemaId;
	}
}
