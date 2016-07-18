package com.dev.base.util;

import java.io.Serializable;
import java.util.List;

/**
 * 
		* <p>Title: 分页查询信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日下午2:29:20</p>
 */
public class Pager implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 默认当前页页码*/
	public final static int DEF_PAGE_NUMBER = 1;
	
	/** 默认每页显示条数*/
	public final static int DEF_PAGE_SIZE = 10;
	
	/** 当前页*/
	private int pageNumber = 1;
	
	/** 每页显示数目*/
    private int pageSize = 10;
    
    /** 总记录数*/
    private int totalCount;
    
    /** 起始行*/
    private int start;
    
    /** 记录列表*/
    private List list;

    public Pager(){
    	
    }
    
    public Pager(Integer pageNumber,Integer pageSize){
    	if (pageNumber == null) {
			pageNumber = 1;
		}
    	
    	if (pageSize == null) {
    		pageSize = 10;
		}
    	
    	this.pageNumber = pageNumber;
    	this.pageSize = pageSize;
    }
    
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getStart() {
		if (pageNumber < 1) {
			pageNumber = 1;
        } 
		
		return pageSize * (pageNumber - 1);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
