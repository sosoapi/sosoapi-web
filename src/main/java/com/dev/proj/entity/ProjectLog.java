package com.dev.proj.entity;

import com.dev.base.mybatis.BaseMybatisEntity;
import java.util.Date;

/**
 * 
		* <p>Title: 项目日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月8日上午11:02:50</p>
 */
public class ProjectLog extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 项目id */
	private Long projId;		
	
	/** 发布者id */
	private Long userId;		
	
	/** 发布时间 */
	private Date pubDate;		
	
	/** 标题 */
	private String title;		
	
	/** 内容 */
	private String content;		
	

	public void setProjId(Long projId){
		this.projId = projId;
	}
	
	public Long getProjId(){
		return projId;
	}
	
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
	
}
