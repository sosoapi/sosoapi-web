package com.dev.doc.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dev.base.enums.SchemaType;

/**
 * 
		* <p>Title: 数据结构项</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月13日下午5:57:30</p>
 */
public class SchemaNodeInfo {
	/** 编码*/
	private String code;
	
	/** 名称*/
	private String name;
	
	/** 描述*/
	private String description;
	
	/** 类型*/
	private SchemaType type;
	
	/** 引用的共用数据结构id*/
	private Long refSchemaId;
	
	/** 扩展自定义结构体,仅当type为cust_开头*/
	private String extSchema;
	
	/** 当前节点id*/
	private String nodeId;
	
	/** 父节点id*/
	private String parentId;

	/** 子节点列表*/
	private List<SchemaNodeInfo> childList = new ArrayList<SchemaNodeInfo>();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<SchemaNodeInfo> getChildList() {
		return childList;
	}

	public void setChildList(List<SchemaNodeInfo> childList) {
		this.childList = childList;
	}

	public String getExtSchema() {
		return extSchema;
	}

	public void setExtSchema(String extSchema) {
		this.extSchema = extSchema;
	}

	/**
	 * 
			*@name 当前节点及子节点所涉及到的所有引用的数据结构id列表
			*@Description  
			*@CreateDate 2015年11月14日下午4:21:39
	 */
	public Set<Long> getChildRefSchemaIdList() {
		Set<Long> result = new HashSet<Long>();
		if (SchemaType.sys_ref == type) {//当前为引用类型
			result.add(getRefSchemaId());
		}
		else{
			for (SchemaNodeInfo child : childList) {
				result.addAll(child.getChildRefSchemaIdList());
			}
		}
		
		return result;
	}
}
