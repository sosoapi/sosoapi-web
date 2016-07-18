package com.dev.user.entity;

import com.dev.base.enums.MsgType;
import com.dev.base.mybatis.BaseMybatisEntity;

import java.util.Date;

/**
 * 
		* <p>Title: 用户消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:30:35</p>
 */
public class UserMsg extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 是否系统消息 */
	private boolean sys;		
	
	/** 系统消息id */
	private Long sysMsgId;		
	
	/** 发送者id */
	private Long senderId;		
	
	/** 接收者id */
	private Long receiverId;		
	
	/** 发布时间 */
	private Date pubDate;		
	
	/** 标题 */
	private String title;		
	
	/** 内容 */
	private String content;		
	
	/** 消息类型 */
	private MsgType msgType;		
	
	/** 是否已读 */
	private boolean deal;		
	
	/** 阅读时间 */
	private Date dealDate;		
	

	public void setSysMsgId(Long sysMsgId){
		this.sysMsgId = sysMsgId;
	}
	
	public Long getSysMsgId(){
		return sysMsgId;
	}
	
	public void setSenderId(Long senderId){
		this.senderId = senderId;
	}
	
	public Long getSenderId(){
		return senderId;
	}
	
	public void setReceiverId(Long receiverId){
		this.receiverId = receiverId;
	}
	
	public Long getReceiverId(){
		return receiverId;
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
	
	public void setDealDate(Date dealDate){
		this.dealDate = dealDate;
	}
	
	public Date getDealDate(){
		return dealDate;
	}

	public boolean isSys() {
		return sys;
	}

	public void setSys(boolean sys) {
		this.sys = sys;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public boolean isDeal() {
		return deal;
	}

	public void setDeal(boolean deal) {
		this.deal = deal;
	}
}
