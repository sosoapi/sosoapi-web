package com.dev.doc.entity;

import com.dev.base.enums.SchemaType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 接口响应信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:32:39</p>
 */
public class InterResp extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 文档id*/
	private Long docId;
	
	/** 接口id */
	private Long interId;		
	
	/** 编码 */
	private String code;		
	
	/** 名称*/
	private String name;
	
	/** 描述信息 */
	private String description;		
	
	/** 响应类型 */
	private SchemaType type;		
	
	/** 响应数据结构id,仅当type为sys_ref */
	private Long refSchemaId;		
	
	/** 自定义结构体,仅当type为sys_object或sys_array */
	private String custSchema;		
	
	/** 扩展自定义结构体,仅当type为cust_开头*/
	private String extSchema;
	
	/** 是否是默认 */
	private boolean def;
	
	/** 是否必输项 */
	private boolean required;
	
	/** 排序权重，值越小，排序越靠前，默认步长为50*/
	private int sortWeight;
	
	public void setInterId(Long interId){
		this.interId = interId;
	}
	
	public Long getInterId(){
		return interId;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
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

	public boolean isDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
	}

	public void setCustSchema(String custSchema){
		this.custSchema = custSchema;
	}
	
	public String getCustSchema(){
		return custSchema;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtSchema() {
		return extSchema;
	}

	public void setExtSchema(String extSchema) {
		this.extSchema = extSchema;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}
}
