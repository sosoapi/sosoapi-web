package com.dev.doc.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 文档相关信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:30:07</p>
 */
public class ApiDoc extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 项目id */
	private Long projId;		
	
	/** 标题 */
	private String title;		
	
	/** 描述信息 */
	private String description;		
	
	/** 访问主机 */
	private String host;		
	
	/** 基路径 */
	private String basePath;		
	
	/** 版本 */
	private String version;	
	
	/** 请求协议 */
	private String scheme;	
	
	/** 请求格式 */
	private String consume;		
	
	/** 响应格式 */
	private String produce;
	
	/** 是否发布*/
	private boolean pub;

	/** 是否公开*/
	private boolean open;
	
	public void setProjId(Long projId){
		this.projId = projId;
	}
	
	public Long getProjId(){
		return projId;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public String getHost(){
		return host;
	}
	
	public void setBasePath(String basePath){
		this.basePath = basePath;
	}
	
	public String getBasePath(){
		return basePath;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return version;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getConsume() {
		return consume;
	}

	public void setConsume(String consume) {
		this.consume = consume;
	}

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
