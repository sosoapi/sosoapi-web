package com.dev.base.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;

/**
 * 
		* <p>Title: 前台分页信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日下午6:35:28</p>
 */
public class WebPaginate {
	/** 分页页码参数名称*/
	private static String KEY_PAGE_NUMBER = "pageNumber";
	
	/** 分页显示数目参数名称*/
	private static String KEY_PAGE_SIZE = "pageSize";
	
	/** 当前页*/
	private int pageNumber = 1;
	
	/** 每页显示数目*/
    private int pageSize = 10;
    
    /** 总记录数*/
    private int totalCount;
    
    /** 总页数*/
    private int totalPage;
    
    /** 展示页数*/
    private int showPageSize;
    
    /** 当前分页开始页码*/
    private int pageStart;
    
    /** 当前分页结束页码*/
    private int pageEnd;
    
    /** 是否是第一页*/
    private boolean first;
    
    /** 是否是最后一页*/
    private boolean last;
    
    /** 请求的url，需要在最后拼上当前页码*/
    /** 事例:http://example.com?param=1&pageSize=10&pageNumber=*/
    private String url;
    
    /** 第一页url*/
    private String firstUrl;
    
    /** 上一页url*/
    private String prevUrl;
    
    /** 下一页url*/
    private String nextUrl;
    
    /** 最后一页url*/
    private String lastUrl;
    
    private WebPaginate(){

    }
    
    /**
     * 
    		*@name 组装分页信息
    		*@Description  
    		*@CreateDate 2015年8月6日下午7:23:11
     */
    public static WebPaginate build(HttpServletRequest request,Integer pageNumber,
    									Integer pageSize,int totalCount){
    	return build(request, pageNumber, pageSize, totalCount, CfgConstants.SHOW_PAGE_SIZE);
    }
    
    /**
     * 
    		*@name 组装分页信息
    		*@Description  
    		*@CreateDate 2015年8月6日下午7:23:11
     */
    public static WebPaginate build(HttpServletRequest request,Integer pageNumber,
									Integer pageSize,int totalCount,int showPageSize){
    	if (totalCount == 0) {
			return null;
		}
    	
    	if (pageNumber == null) {
			pageNumber = 1;
		}
    	
    	if (pageSize == null) {
    		pageSize = 10;
		}
    	
    	WebPaginate paginate = new WebPaginate();
    	
    	int totalPage = paginate.calculateTotalPage(totalCount, pageSize);
    	int pageEnd = paginate.calculatePageEnd(pageNumber, totalPage, showPageSize);
    	String url = paginate.buildUrl(request, pageSize);
    	
    	
    	paginate.setPageNumber(pageNumber);
    	paginate.setPageSize(pageSize);
    	paginate.setTotalCount(totalCount);
    	paginate.setShowPageSize(showPageSize);
    	paginate.setTotalPage(totalPage);
    	paginate.setFirst(paginate.calculateFirst(pageNumber));
    	paginate.setLast(paginate.calculateLast(pageNumber, totalPage));
    	paginate.setPageStart(paginate.calculatePageStart(pageNumber,totalPage,showPageSize,pageEnd));
    	paginate.setPageEnd(pageEnd);
    	paginate.setUrl(url);
    	paginate.setFirstUrl(url + 1);
    	paginate.setPrevUrl(url + (pageNumber - 1));
    	paginate.setNextUrl(url + (pageNumber + 1));
    	paginate.setLastUrl(url + totalPage);
    	
    	return paginate;
    }

    //获取请求url
    //处理nginx转换bug
    private String getRequestURI(HttpServletRequest request){
    	String uri = request.getRequestURI()
    						.replace(CfgConstants.WEB_CONTEXT_PATH + "/", "");
    	return uri.startsWith("/") ? uri.replaceFirst("/*", "") : uri;
    }
    
    //组装请求url
    private String buildUrl(HttpServletRequest request,int pageSize){
    	StringBuilder urlBuilder = new StringBuilder();
    	urlBuilder.append(getRequestURI(request))
    			  .append("?");
    	
    	Enumeration<String> paramNames = request.getParameterNames();
    	String name = "";
    	String value = "";
		while (paramNames.hasMoreElements()) {
			name = paramNames.nextElement();
			value = request.getParameter(name);
			if (KEY_PAGE_NUMBER.equals(name) || StringUtils.isEmpty(value)) {
				continue ;
			}
			
			urlBuilder.append(name)
			          .append("=")
			          .append(value)
			          .append("&");
		}
    	urlBuilder.append(KEY_PAGE_NUMBER)
    	          .append("=");
		
    	return urlBuilder.toString();
    }
    
    //计算总页数
    private int calculateTotalPage(int totalCount,int pageSize) {
		if (totalCount <= pageSize) {
			return 1;
		}
		
		int temp = totalCount % pageSize;
		return temp == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
	}
    
    //计算是否是第一页
    private boolean calculateFirst(int pageNumber){
    	return pageNumber == 1;
    }
    
    //计算是否是最后一页
    private boolean calculateLast(int pageNumber,int totalPage){
		return pageNumber == totalPage;
	}
    
    //计算当前分页第一个页码
    private int calculatePageStart(int pageNumber,int totalPage,int showPageSize,int pageEnd){
    	int middle = showPageSize / 2;
    	if ((totalPage < showPageSize) || (pageNumber <= (middle + 1))) {//总页数小于展示页码数或当前页小于中间值
			return 1;
		}
    	else{
    		return pageEnd - showPageSize + 1;
    	}
	}
	
    //计算当前分页最后一个页码
    private int calculatePageEnd(int pageNumber,int totalPage,int showPageSize){
    	if (totalPage < showPageSize) {//总页数小于展示页码数
			return totalPage;
		}
    	
    	int middle = showPageSize / 2;
    	if (pageNumber <= (middle + 1)) {//如果当前页在中间值前面则最后一个页码为展示页码最后一个
			return showPageSize;
		}
    	
    	int pageEnd = 0;
    	if (showPageSize % 2 == 0) {//处理展示页为偶数情况
    		pageEnd = pageNumber + middle - 1;
		}
    	else{//处理展示页为奇数情况
    		pageEnd = pageNumber + middle;
    	}
		
		return pageEnd >= totalPage ? totalPage : pageEnd;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	private void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getShowPageSize() {
		return showPageSize;
	}

	public void setShowPageSize(int showPageSize) {
		this.showPageSize = showPageSize;
	}

	public int getPageStart() {
		return pageStart;
	}

	private void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	private void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public boolean isFirst() {
		return first;
	}

	private void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	private void setLast(boolean last) {
		this.last = last;
	}

	public String getUrl() {
		return url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	public String getPrevUrl() {
		return prevUrl;
	}

	private void setPrevUrl(String prevUrl) {
		this.prevUrl = prevUrl;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	private void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getFirstUrl() {
		return firstUrl;
	}

	public void setFirstUrl(String firstUrl) {
		this.firstUrl = firstUrl;
	}

	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}
}
