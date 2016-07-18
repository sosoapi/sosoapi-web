package com.dev.base.enums;

/**
 * 
		* <p>Title: 参数位置</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:54:34</p>
 */
public enum ParamPosition {
	/** http请求body*/
	body,
	
	/** 本地cookie*/
	cookie,
	
	/** 表单参数*/
	formData,
	
	/** http请求header*/
	header,
	
	/** http请求url,如getInfo/{userId}*/
	path,
	
	/** http请求拼接，如getInfo?userId={userId}*/
	query
}
