package com.dev.user.entity;

import java.util.Date;

import com.dev.base.enums.SuggestStatus;
import com.dev.base.enums.SuggestType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 用户建议意见</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月29日下午10:25:48</p>
 */
public class Suggest extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id*/
	private Long userId;		
	
	/** 标题*/
	private String title;
	
	/** 内容 */
	private String content;		
	
	/** 处理时间 */
	private Date dealDate;		
	
	/** 状态 */
	private SuggestStatus status;		
	
	/** 类型*/
	private SuggestType type;
	
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
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

	public SuggestStatus getStatus() {
		return status;
	}

	public void setStatus(SuggestStatus status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SuggestType getType() {
		return type;
	}

	public void setType(SuggestType type) {
		this.type = type;
	}
}
