package com.dev.doc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.ReqMethod;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.Inter;

/**
 * 
		* <p>Title: 接口信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:27:56</p>
 */
public interface InterDao extends BaseMybatisDao<Inter,Long> {
	/**
	 * 
			*@name 获取所有接口
			*@Description  
			*@CreateDate 2015年8月15日下午2:23:41
	 */
	List<Inter> listAllByDocId(@Param("docId")Long docId,@Param("deprecated")Boolean deprecated);
	
	/**
	 * 
			*@name 查询api文档相关接口列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<Inter> listByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
			@Param("name")String name,@Param("description")String description,@Param("pager")Pager pager);
	
	/**
	 * 查询api文档相关接口总数
	 * @param docId
	 * @param moduleId
	 * @return
	 */
	int countByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
			@Param("name")String name,@Param("description")String description);
	
	/**
	 * 获取接口详细信息
	 * @param interId
	 * @return
	 */
	Map getDetailByDocId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 获取接口信息
			*@Description  
			*@CreateDate 2016年5月15日上午10:37:18
	 */
	Inter getByDocId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 根据id和文档id更新
			*@Description  
			*@CreateDate 2015年9月9日上午11:36:54
	 */
	void updateByDocId(Inter inter);
	
	/**
	 * 
			*@name 根据id和文档id删除
			*@Description  
			*@CreateDate 2015年9月9日上午11:37:21
	 */
	void deleteByDocId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 根据请求url和请求方式查询接口信息
			*@Description  
			*@CreateDate 2015年10月10日下午8:51:50
	 */
	Inter getByPathAndMethod(@Param("docId")Long docId,
					@Param("path")String path,@Param("method")ReqMethod method);
}
