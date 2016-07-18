package com.dev.admin.entity;

import java.util.Date;

import com.dev.base.enums.MsgStatus;
import com.dev.base.enums.MsgType;
import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 系统消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:25:03</p>
 */
public class SysMsg extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 发布者id */
	private Long userId;		
	
	/** 发布时间 */
	private Date pubDate;		
	
	/** 标题 */
	private String title;		
	
	/** 内容 */
	private String content;		
	
	/** 撤销 */
	private boolean cancel;		
	
	/** 撤销时间 */
	private Date cancelDate;		
	
	/** 消息类型 */
	private MsgType msgType;		
	
	/** 消息状态*/
	private MsgStatus msgStatus;
	
	/** 接收对象类型 */
	private UserRole userRole;		
	

	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setPubDate(Date pubDate){
		this.pubDate = pubDate;
	}
	
	public Date getPubDate(){
		return pubDate;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setCancelDate(Date cancelDate){
		this.cancelDate = cancelDate;
	}
	
	public Date getCancelDate(){
		return cancelDate;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public MsgStatus getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(MsgStatus msgStatus) {
		this.msgStatus = msgStatus;
	}
}
