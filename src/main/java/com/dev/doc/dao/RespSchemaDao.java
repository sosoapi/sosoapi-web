package com.dev.doc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.RespSchema;

/**
 * 
		* <p>Title: 公共响应结构信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:29:46</p>
 */
public interface RespSchemaDao extends BaseMybatisDao<RespSchema,Long> {
	/**
	 * 
			*@name 查询api文档相关公共响应列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<RespSchema> listByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
			@Param("code")String code,@Param("name")String name,
				@Param("description")String description,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询api文档相关的公共响应总数
			*@Description  
			*@CreateDate 2015年8月10日下午4:30:09
	 */
	int countByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
						@Param("code")String code,@Param("name")String name,
						@Param("description")String description);
	
	/**
	 * 
			*@name 查询api文档相关所有公共响应列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<RespSchema> listAllByDocId(Long docId);
	
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
	void deleteByDocId(@Param("docId")Long docId,@Param("schemaId")Long schemaId);
	
	/**
	 * 
			*@name 根据id和文档id获取响应
			*@Description  
			*@CreateDate 2015年9月9日上午11:19:48
	 */
	RespSchema getByDocId(@Param("docId")Long docId,@Param("schemaId")Long schemaId);
}
