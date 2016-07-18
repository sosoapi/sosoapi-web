package com.dev.proj.vo;

import java.util.Date;

import com.dev.base.enums.ProjectStatus;
import com.dev.base.enums.Role;

/**
 * 
		* <p>Title: 项目信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:33:39</p>
 */
public class ProjectInfo {
	/** 项目id*/
	private Long projId;
	
	/** 文档id*/
	private Long docId;
	
	/** 编码*/
	private String code;
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 项目状态 */
	private ProjectStatus status;

	/** 角色*/
	private Role role;
	
	/** 创建者昵称*/
	private String createrNickName;
	
	/** 成员数目*/
	private long memCount;
	
	/** 项目创建时间*/
	private Date createDate;
	
	
	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreaterNickName() {
		return createrNickName;
	}

	public void setCreaterNickName(String createrNickName) {
		this.createrNickName = createrNickName;
	}

	public long getMemCount() {
		return memCount;
	}

	public void setMemCount(long memCount) {
		this.memCount = memCount;
	}
}
