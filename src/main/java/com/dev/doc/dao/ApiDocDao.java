package com.dev.doc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.ApiDoc;

/**
 * 
		* <p>Title: Api文档信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:27:39</p>
 */
public interface ApiDocDao extends BaseMybatisDao<ApiDoc,Long> {
	/**
	 * 
			*@name 根据项目id查询相关api文档
			*@Description  
			*@CreateDate 2015年8月6日下午3:34:06
	 */
	ApiDoc getByProjId(Long projId);
	
	/**
	 * 
			*@name 查询用户相关的doc记录
			*@Description  
			*@CreateDate 2015年8月7日下午3:10:23
	 */
	int countRecord(@Param("userId")Long userId,@Param("docId")Long docId);
	
	/**
	 * 
			*@name 根据用户id查询相关的api文档列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listByUserId(@Param("userId")Long userId,@Param("title")String title,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 根据用户id查询相关的api文档总数
			*@Description  
			*@CreateDate 2015年8月19日下午3:26:29
	 */
	int countByUserId(@Param("userId")Long userId,@Param("title")String title);
}
