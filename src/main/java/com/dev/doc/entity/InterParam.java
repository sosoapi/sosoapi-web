package com.dev.doc.entity;

import com.dev.base.enums.ParamPosition;
import com.dev.base.enums.SchemaType;
import com.dev.base.mybatis.BaseMybatisEntity;
/**
 * 
		* <p>Title: 接口参数信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:31:53</p>
 */
public class InterParam extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 文档id*/
	private Long docId;
	
	/** 接口id */
	private Long interId;		
	
	/** 编码*/
	private String code;
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 数据类型 */
	private SchemaType type;		
	
	/** 数据类型格式化*/
	private String format;
	
	/** 参数位置 */
	private ParamPosition position;		
	
	/** 是否必输项 */
	private boolean required;		
	
	/** 默认值 */
	private String defValue;		
	
	/** 自定义结构体,仅当type为sys_object或sys_array */
	private String custSchema;
	
	/** 扩展自定义结构体,仅当type为cust_开头*/
	private String extSchema;

	/** 响应数据结构id,仅当type为sys_ref */
	private Long refSchemaId;	
	
	public void setInterId(Long interId){
		this.interId = interId;
	}
	
	public Long getInterId(){
		return interId;
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
	
	public SchemaType getType() {
		return type;
	}

	public void setType(SchemaType type) {
		this.type = type;
	}

	public ParamPosition getPosition() {
		return position;
	}

	public void setPosition(ParamPosition position) {
		this.position = position;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setDefValue(String defValue){
		this.defValue = defValue;
	}
	
	public String getDefValue(){
		return defValue;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustSchema() {
		return custSchema;
	}

	public void setCustSchema(String custSchema) {
		this.custSchema = custSchema;
	}

	public String getExtSchema() {
		return extSchema;
	}

	public void setExtSchema(String extSchema) {
		this.extSchema = extSchema;
	}

	public Long getRefSchemaId() {
		return refSchemaId;
	}

	public void setRefSchemaId(Long refSchemaId) {
		this.refSchemaId = refSchemaId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}
}
