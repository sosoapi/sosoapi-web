package com.dev.proj.vo;

import java.util.Date;

/**
 * 
		* <p>Title: 项目日志信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月8日下午12:48:37</p>
 */
public class ProjectLogInfo {
	/** 日志id */
	private Long logId;	
	
	/** 项目id */
	private Long projId;		
	
	/** 发布时间 */
	private Date pubDate;		
	
	/** 标题 */
	private String title;		
	
	/** 内容 */
	private String content;	
	
	/** 发布者昵称*/
	private String pubNickName;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
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

	public String getPubNickName() {
		return pubNickName;
	}

	public void setPubNickName(String pubNickName) {
		this.pubNickName = pubNickName;
	}
}
