package com.dev.base.enums;

import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: 数据类型</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午10:16:26</p>
 */
public enum SchemaType {
	/** 以下为系统内置*/
	/** 字符串*/
	sys_string("string","string",""),
	
	/** 数组*/
	sys_array("array","array",""),
	
	/** 对象*/
	sys_object("object","object",""),
	
	/** int*/
	sys_integer_int32("int","integer","int32"),
	
	/** long*/
	sys_integer_int64("long","integer","int64"),
	
	/** float*/
	sys_number_float("float","number","float"),
	
	/** double*/
	sys_number_double("double","number","double"),
	
	/** decimal*/
	sys_number_decimal("decimal","number",""),
	
	/** 文件*/
	sys_file("file","file",""),
	
	/** boolean*/
	sys_boolean("boolean","boolean",""),
	
	/** 引用其他数据结构*/
	sys_ref("ref","ref",""),
	
	/** 自定义扩展结构体*/
	cust_json("cust_json","cust","json"),
	
	/** 未指定*/
	none("none","none","none");
	
	
	/** 编码*/
	String code;
	
	/** 对应的swagger property中的format*/
	String format;
	
	/** 对应的swagger property中的type*/
	String type;
	
	private SchemaType(String code,String type,String format){
		this.code = code;
		this.format = format;
		this.type = type;
	}

	/**
	 * 
			*@name 根据type获取枚举
			*@Description  
			*@CreateDate 2016年1月30日下午3:58:53
	 */
	public static SchemaType getByType(String type){
		return getByType(type, null);
	}
	
	/**
	 * 
			*@name 根据type和format获取枚举
			*@Description  
			*@CreateDate 2016年1月30日下午4:11:33
	 */
	public static SchemaType getByType(String type,String format){
		SchemaType result = SchemaType.none;
		if (StringUtils.isEmpty(type)) {
			return result;
		}
		
		SchemaType[] typeArray = SchemaType.values();
		boolean checkFormat = !StringUtils.isEmpty(format);
		for (SchemaType schemaType : typeArray) {
			if (checkFormat) {//需要验证格式
				if (schemaType.getType().equals(type)
						&& schemaType.getFormat().equals(format)) {
					result = schemaType;
					break;
				}
			}
			else if (schemaType.getType().equals(type)) {
				result = schemaType;
			}
		}
		
		return result;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
