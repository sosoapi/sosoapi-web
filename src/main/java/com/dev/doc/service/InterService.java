package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.doc.entity.Inter;
import com.dev.doc.vo.InterInfo;

/**
 * 
		* <p>Title: 接口相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface InterService extends BaseMybatisService<Inter, Long>{
	/**
	 * 
			*@name 获取所有接口
			*@Description  
			*@CreateDate 2015年8月15日下午2:23:41
	 */
	List<Inter> listAllByDocId(Long docId,Boolean deprecated);
	
	/**
	 * 
			*@name 查询api文档相关接口列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<Inter> listByDocId(Long docId,Long moduleId,String name,String description,Pager pager);
	
	/**
	 * 查询api文档相关接口总数
	 * @param docId
	 * @param moduleId
	 * @return
	 */
	int countByDocId(Long docId,Long moduleId,String name,String description);
	
	/**
	 * 获取接口详细信息
	 * @param interId
	 * @return
	 */
	InterInfo getDetailByDocId(Long docId,Long interId);
	
	/**
	 * 
			*@name 获取接口信息
			*@Description  
			*@CreateDate 2016年5月15日上午10:37:18
	 */
	Inter getByDocId(Long docId,Long interId);
	
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
	void deleteByDocId(Long docId,Long interId);
}
