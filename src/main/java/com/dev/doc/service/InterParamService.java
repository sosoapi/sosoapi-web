package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.doc.entity.InterParam;

/**
 * 
		* <p>Title: 接口参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:02</p>
 */
public interface InterParamService extends BaseMybatisService<InterParam, Long>{
	/**
	 * 
			*@name 查询接口相关的所有参数
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	List<InterParam> listAllByInterId(Long docId,Long interId);
	
	/**
	 * 
			*@name 删除接口相关的所有参数
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	void deleteByDocIdAndInterId(Long docId,Long interId);
	
	/**
	 * 
			*@name 新增接口参数
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	void batchAdd(Long docId,Long interId,List<InterParam> list);
	
	/**
	 * 
			*@name 查询文档对应的所有请求参数列表
			*@Description  
			*@CreateDate 2015年10月11日上午12:21:48
	 */
	List<InterParam> listAllByDocId(Long docId);
}
