package com.dev.doc.service;

import io.swagger.models.Swagger;

import java.util.Map;

/**
 * 
		* <p>Title: swagger api文档服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月13日下午2:20:54</p>
 */
public interface SwaggerService {
	/**
	 * 
			*@name 生成api文档相关的json
			*@Description  
			*@CreateDate 2015年8月13日下午2:22:33
	 */
	Swagger buildApiDoc(Long userId,Long docId);
	
	/**
	 * 
			*@name 组装文档模板数据
			*@Description  
			*@CreateDate 2015年11月14日下午1:42:04
	 */
	Map<String, Object> buildDocTmplData(Long userId,Long docId);
}
