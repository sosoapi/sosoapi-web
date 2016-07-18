package com.dev.user.entity;

import java.util.Date;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 用户扩展信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月19日下午5:40:12</p>
 */
public class UserExt extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
	/** 最后获取系统消息时间 */
	private Date lastFetchSysMsgDate;		
	

	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setLastFetchSysMsgDate(Date lastFetchSysMsgDate){
		this.lastFetchSysMsgDate = lastFetchSysMsgDate;
	}
	
	public Date getLastFetchSysMsgDate(){
		return lastFetchSysMsgDate;
	}
	
}
