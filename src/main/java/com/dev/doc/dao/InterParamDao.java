package com.dev.doc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.doc.entity.InterParam;

/**
 * 
		* <p>Title: 接口参数</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:07</p>
 */
public interface InterParamDao extends BaseMybatisDao<InterParam,Long> {
	/**
	 * 
			*@name 查询接口相关的所有参数
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	List<InterParam> listAllByInterId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 删除接口相关的所有参数
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	void deleteByDocIdAndInterId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 查询文档对应的所有请求参数列表
			*@Description  
			*@CreateDate 2015年10月11日上午12:21:48
	 */
	List<InterParam> listAllByDocId(Long docId);
}
