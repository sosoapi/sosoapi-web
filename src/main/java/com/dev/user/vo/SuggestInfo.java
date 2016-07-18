package com.dev.user.vo;

import java.util.Date;

import com.dev.base.enums.SuggestStatus;
import com.dev.base.enums.SuggestType;

/**
 * 
		* <p>Title: 反馈信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月16日下午1:45:47</p>
 */
public class SuggestInfo {
	/** 记录id*/
	private Long suggestId;
	
	/** 用户id*/
	private Long userId;		
	
	/** 用户邮箱*/
	private String email;
	
	/** 用户昵称*/
	private String nickName;
	
	/** 标题*/
	private String title;
	
	/** 内容 */
	private String content;		
	
	/** 提出时间 */
	private Date createDate;		
	
	/** 状态 */
	private SuggestStatus status;

	/** 类型*/
	private SuggestType type;
	
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public SuggestStatus getStatus() {
		return status;
	}

	public void setStatus(SuggestStatus status) {
		this.status = status;
	}

	public Long getSuggestId() {
		return suggestId;
	}

	public void setSuggestId(Long suggestId) {
		this.suggestId = suggestId;
	}

	public SuggestType getType() {
		return type;
	}

	public void setType(SuggestType type) {
		this.type = type;
	}
}