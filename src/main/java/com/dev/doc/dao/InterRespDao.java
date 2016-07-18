package com.dev.doc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.doc.entity.InterResp;

/**
 * 
		* <p>Title: 接口响应</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:19</p>
 */
public interface InterRespDao extends BaseMybatisDao<InterResp,Long> {
	/**
	 * 
			*@name 根据接口查询所有响应
			*@Description  
			*@CreateDate 2015年8月6日下午3:39:30
	 */
	List<InterResp> listAllByInterId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 根据id和文档id更新
			*@Description  
			*@CreateDate 2015年9月9日下午3:11:23
	 */
	void updateByDocId(InterResp interResp);
	
	/**
	 * 
			*@name 根据id和文档id删除
			*@Description  
			*@CreateDate 2015年9月9日下午3:11:59
	 */
	void deleteByDocId(@Param("docId")Long docId,@Param("respId")Long respId);
	
	/**
	 * 
			*@name 查询响应信息
			*@Description  
			*@CreateDate 2015年9月9日下午3:11:59
	 */
	InterResp getByDocId(@Param("docId")Long docId,@Param("respId")Long respId);
	
	/**
	 * 
			*@name 查询文档对应的所有请求响应列表
			*@Description  
			*@CreateDate 2015年10月11日上午12:21:48
	 */
	List<InterResp> listAllByDocId(Long docId);
	
	/**
	 * 
			*@name 删除接口相关的所有响应
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	void deleteByDocIdAndInterId(@Param("docId")Long docId,@Param("interId")Long interId);
}
