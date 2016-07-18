package com.dev.user.vo;

import java.util.Date;
import java.util.Map;

import com.dev.base.enums.Role;
import com.dev.base.enums.UserRole;
import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 用户信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日下午5:05:47</p>
 */
public class UserInfo {
	/** 用户id*/
	private Long userId;

	/** 邮箱*/
	private String email;
	
	/** 昵称*/
	private String nickName;
	
	/** 头像*/
	private String headUrl;
	
	/** 登陆token*/
	private String token;
	
	/** 是否验证 */
	private boolean valid;	
	
	/** 是否锁定 */
	private boolean locked;	
	
	/** 用户角色*/
	private UserRole role;
	
	/** 注册时间*/
	private Date registDate;
	
	/** 新消息数目*/
	private int newMsgCount;
	
	/** 具有权限的项目集合*/
	private Map<Long,Role> projMap = MapUtils.newMap();
	
	/** 具有权限的文档集合*/
	private Map<Long,Role> docMap = MapUtils.newMap();
	
	/**
	 * 
			*@name 根据项目id获取对应的角色
			*@Description  
			*@CreateDate 2015年8月29日下午3:55:34
	 */
	public Role getRoleByProjId(Long projId){
		return projMap.get(projId);
	}
	
	/**
	 * 
			*@name 根据文档id获取对应的角色
			*@Description  
			*@CreateDate 2015年8月29日下午3:55:54
	 */
	public Role getRoleByDocId(Long docId){
		return docMap.get(docId);
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Map<Long, Role> getProjMap() {
		return projMap;
	}

	public void setProjMap(Map<Long, Role> projMap) {
		this.projMap = projMap;
	}

	public Map<Long, Role> getDocMap() {
		return docMap;
	}

	public void setDocMap(Map<Long, Role> docMap) {
		this.docMap = docMap;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public int getNewMsgCount() {
		return newMsgCount;
	}

	public void setNewMsgCount(int newMsgCount) {
		this.newMsgCount = newMsgCount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
