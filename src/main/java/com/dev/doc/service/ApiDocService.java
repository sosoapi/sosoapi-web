package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.vo.ApiDocInfo;

/**
 * 
		* <p>Title: Api文档相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:37:33</p>
 */
public interface ApiDocService extends BaseMybatisService<ApiDoc, Long>{
	/**
	 * 
			*@name 根据项目id查询相关api文档
			*@Description  
			*@CreateDate 2015年8月6日下午3:34:06
	 */
	ApiDoc getByProjId(Long projId);
	
	/**
	 * 
			*@name 根据用户id查询相关的api文档列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<ApiDocInfo> listByUserId(Long userId,String title,Pager pager);
	
	/**
	 * 
			*@name 根据用户id查询相关的api文档总数
			*@Description  
			*@CreateDate 2015年8月19日下午3:26:29
	 */
	int countByUserId(Long userId,String title);
}
