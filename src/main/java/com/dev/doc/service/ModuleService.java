package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.doc.entity.Module;

/**
 * 
		* <p>Title: 模块相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface ModuleService extends BaseMybatisService<Module, Long>{
	/**
	 * 
			*@name 根据api文档id查询所有模块列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:41:18
	 */
	List<Module> listByDocId(Long docId,String code,String name,
								String description,Pager pager);
	
	/**
	 * 查询api文档相关的模块总数
	 * @param docId
	 * @return
	 */
	int countByDocId(Long docId,String code,String name,String description);
	
	/**
	 * 
			*@name 查询所有模块信息
			*@Description  
			*@CreateDate 2015年10月11日上午10:31:41
	 */
	List<Module> listAllByDocId(Long docId);
	
	/**
	 * 
			*@name 根据id和文档id进行更新
			*@Description  
			*@CreateDate 2015年9月9日上午10:53:49
	 */
	void updateByDocId(Module module);
	
	/**
	 * 
			*@name 根据id和文档id进行删除
			*@Description  
			*@CreateDate 2015年9月9日上午11:13:24
	 */
	void deleteByDocId(Long docId,Long moduleId);
}
