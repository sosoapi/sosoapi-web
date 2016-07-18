package com.dev.doc.service;

import java.util.List;
import java.util.Map;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.entity.RespSchema;

/**
 * 
		* <p>Title: 接口公共响应相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:18</p>
 */
public interface RespSchemaService extends BaseMybatisService<RespSchema, Long>{
	/**
	 * 
			*@name 查询api文档相关公共响应列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<RespSchema> listByDocId(Long docId,Long moduleId,String code,
									String name,String description,Pager pager);
	
	/**
	 * 
			*@name 查询api文档相关的公共响应总数
			*@Description  
			*@CreateDate 2015年8月10日下午4:30:09
	 */
	int countByDocId(Long docId,Long moduleId,String code,
						String name,String description);
	
	/**
	 * 
			*@name 查询api文档相关所有公共响应列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<RespSchema> listAllByDocId(Long docId);
	
	/**
	 * 
			*@name 查询api文档相关所有公共响应列表，用于填充下拉框
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<SelectInfo> listSelectInfoByDocId(Long docId);
	
	/**
	 * 
			*@name 获取公共响应信息
			*@Description  key:公共响应id,value:公共响应code
			*@CreateDate 2015年8月15日下午3:35:31
	 */
	Map<Long, String> getAllByDocId(Long docId);
	
	/**
	 * 
			*@name 根据id查询对应的code
			*@Description  
			*@CreateDate 2015年9月8日上午11:30:20
	 */
	String getCodeById(Long refSchemaId);
	
	/**
	 * 
			*@name 根据id和文档id更新响应
			*@Description  
			*@CreateDate 2015年9月9日上午11:19:09
	 */
	void updateByDocId(RespSchema schema);
	
	/**
	 * 
			*@name 根据id和文档id删除响应
			*@Description  
			*@CreateDate 2015年9月9日上午11:19:48
	 */
	void deleteByDocId(Long docId,Long schemaId);
	
	/**
	 * 
			*@name 根据id和文档id获取响应
			*@Description  
			*@CreateDate 2015年9月9日上午11:19:48
	 */
	RespSchema getByDocId(Long docId,Long schemaId);
}
